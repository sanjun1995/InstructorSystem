package cn.instructorsystem.student.service.impl;

import cn.instructorsystem.student.controller.LeaveController;
import cn.instructorsystem.student.dao.LeaveMapper;
import cn.instructorsystem.student.dao.StudentMapper;
import cn.instructorsystem.student.model.Leave;
import cn.instructorsystem.student.model.LeaveExample;
import cn.instructorsystem.student.service.LeaveService;
import cn.instructorsystem.student.util.Message;
import cn.instructorsystem.student.vo.LeaveInfoReqVo;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author sanjun
 * @date 2019/1/22 14:58
 */
@Service
public class LeaveServiceImpl implements LeaveService {
    private static Logger logger = LoggerFactory.getLogger(LeaveServiceImpl.class);

    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    @Override
    public int insertLeaveInfo(LeaveInfoReqVo vo) {
        Leave leave = vo.getLeave();
        Date startTime = leave.getStartTime();
        Date endTime = leave.getEndTime();
        long t1 = startTime.getTime();
        long t2 = endTime.getTime();
        int days = (int)((t2 - t1) / (1000 * 60 * 60 * 24));
        if (days == 0) {
            days = 1;
        }
        leave.setDuration(Integer.toString(days) + "天");
        int n = leaveMapper.insertSelective(leave);
        if (n != 0) {
            Message message = new Message();
            message.setAccount(leave.getAccount());
            message.setIsRead("未读");
            message.setNewsType("请假");
            message.setStuName(leave.getStuName());
            // kafka发送消息
            this.send(message);
        }
        return n;
    }

    @Override
    public List<Leave> getLeaveInfosByPage(LeaveInfoReqVo vo) {
        Integer pageNum = vo.getPageNum();
        Integer pageSize = vo.getPageSize();
        Leave leave = vo.getLeave();
        String account = leave.getAccount();

        PageHelper.startPage(pageNum, pageSize);
        LeaveExample example = new LeaveExample();
        LeaveExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        List<Leave> leaves = leaveMapper.selectByExample(example);
        return leaves;
    }

    // kafka发送消息方法
    public void send(Message message) {
        logger.info("kafka send() message = {}", gson.toJson(message));
        kafkaTemplate.send("notification", gson.toJson(message));
    }
}
