package cn.instructorsystem.student.service.impl;

import cn.instructorsystem.student.controller.LeaveController;
import cn.instructorsystem.student.dao.LeaveMapper;
import cn.instructorsystem.student.dao.StudentMapper;
import cn.instructorsystem.student.model.Leave;
import cn.instructorsystem.student.model.LeaveExample;
import cn.instructorsystem.student.service.LeaveService;
import cn.instructorsystem.student.util.Message;
import cn.instructorsystem.student.util.UUIDUtil;
import cn.instructorsystem.student.vo.LeaveInfoReqVo;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

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
        leave.setOrderNumber(UUIDUtil.getUUID());
        leave.setDuration(Integer.toString(days) + "天");
        int n = leaveMapper.insertSelective(leave);
        if (n != 0) {
            Message message = new Message();
            message.setAccount(leave.getAccount());
            message.setIsRead(0);
            message.setNewsType(1);
            message.setStuName(leave.getStuName());
            message.setInsAccount(leave.getInsAccount());
            message.setOrderNumber(leave.getOrderNumber());
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

    @Override
    public Leave getLeaveInfoByOrderNumber(LeaveInfoReqVo vo) {
        String orderNumber = vo.getLeave().getOrderNumber();
        LeaveExample example = new LeaveExample();
        LeaveExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNumberEqualTo(orderNumber);
        List<Leave> leaves = leaveMapper.selectByExample(example);
        if (leaves.size() != 0) {
            return leaves.get(0);
        }
        return null;
    }

    @Override
    public boolean updateLeaveInfo(LeaveInfoReqVo leaveInfoReqVo) {
        String orderNumber = leaveInfoReqVo.getLeave().getOrderNumber();
        Integer status = leaveInfoReqVo.getLeave().getStatus();
        Leave leave = new Leave();
        leave.setStatus(status);
        LeaveExample example = new LeaveExample();
        LeaveExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNumberEqualTo(orderNumber);
        int n = leaveMapper.updateByExampleSelective(leave, example);
        if (n != 0) {
            return true;
        }
        return false;
    }

    // kafka发送消息方法
    public void send(Message message) {
        logger.info("kafka send() message = {}", gson.toJson(message));
        ListenableFuture<SendResult<String, String>> resultListenableFuture =  kafkaTemplate.send("notification", gson.toJson(message));
    }
}
