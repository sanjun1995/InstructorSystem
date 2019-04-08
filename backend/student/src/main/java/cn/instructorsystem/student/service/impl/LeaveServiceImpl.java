package cn.instructorsystem.student.service.impl;

import cn.instructorsystem.student.dao.LeaveMapper;
import cn.instructorsystem.student.dao.MonitorMapper;
import cn.instructorsystem.student.model.Leave;
import cn.instructorsystem.student.model.LeaveExample;
import cn.instructorsystem.student.model.Monitor;
import cn.instructorsystem.student.model.MonitorExample;
import cn.instructorsystem.student.model.res.LeaveRanking;
import cn.instructorsystem.student.service.LeaveService;
import cn.instructorsystem.student.util.Message;
import cn.instructorsystem.student.util.MessageUtil;
import cn.instructorsystem.student.util.UUIDUtil;
import cn.instructorsystem.student.vo.LeaveInfoReqVo;
import com.alibaba.fastjson.JSON;
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
import sun.misc.MessageUtils;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private MonitorMapper monitorMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    @Override
    public int insertLeaveInfo(LeaveInfoReqVo vo) {
        logger.info("insertLeaveInfo LeaveInfoReqVo: {}", JSON.toJSONString(vo));
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
        leave.setOperationTime(new Date());
        int n = leaveMapper.insertSelective(leave);
        if (n != 0) {
            String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
            String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime);
            try {
                MessageUtil.leave(vo.getPhoneNum(), leave.getStuName(), leave.getReason(), startDate, endDate);
            } catch (Exception e) {
                logger.error("短信服务出错，{}", e);
            }
        }
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

    // kafka发送消息方法
    public void send(Message message) {
        logger.info("kafka send() message = {}", gson.toJson(message));
        ListenableFuture<SendResult<String, String>> resultListenableFuture
                =  kafkaTemplate.send("notification", gson.toJson(message));
    }

    @Override
    public List<Leave> getLeaveInfosByStuAccount(LeaveInfoReqVo vo) {
        Integer pageNum = vo.getPageNum();
        Integer pageSize = vo.getPageSize();
        Leave leave = vo.getLeave();
        String account = leave.getAccount();

        PageHelper.startPage(pageNum, pageSize);
        LeaveExample example = new LeaveExample();
        LeaveExample.Criteria criteria = example.createCriteria();
        if (account != null && !"".equals(account)) {
            criteria.andAccountEqualTo(account);
        }
        List<Leave> leaves = leaveMapper.selectByExample(example);
        return leaves;
    }

    @Override
    public List<Leave> getLeaveInfosByInsAccount(LeaveInfoReqVo vo) {
        Integer pageNum = vo.getPageNum();
        Integer pageSize = vo.getPageSize();
        Leave leave = vo.getLeave();
        String insAccount = leave.getInsAccount();
        String leaveType = leave.getLeaveType();
        String stuName = leave.getStuName();

        PageHelper.startPage(pageNum, pageSize);
        LeaveExample example = new LeaveExample();
        LeaveExample.Criteria criteria = example.createCriteria();
        criteria.andInsAccountEqualTo(insAccount);
        if (leaveType != null) {
            criteria.andLeaveTypeEqualTo(leaveType);
        }
        if (stuName != null) {
            criteria.andStuNameLike("%" + stuName + "%");
        }
        List<Leave> leaves = leaveMapper.selectByExample(example);
        return leaves;
    }

    @Override
    public List<LeaveRanking> getLeaveRankingInfos(LeaveInfoReqVo vo) {
        Leave leave = vo.getLeave();
        List<LeaveRanking> leaves = leaveMapper.countLeavesByAccount(leave);
        leaves = leaves.stream().sorted(Comparator.comparing(LeaveRanking::getLeaveTimes).reversed()).collect(Collectors.toList());
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
    public boolean updateLeaveInfo(LeaveInfoReqVo vo) {
        String orderNumber = vo.getLeave().getOrderNumber();
        Integer status = vo.getLeave().getStatus();
        String rejectReason = vo.getLeave().getRejectReason();
        String reason = vo.getLeave().getReason();
        Leave leave = new Leave();
        leave.setStatus(status);
        if (reason != null && !"".equals(reason)) {
            leave.setReason(reason);
        }
        leave.setRejectReason(rejectReason);
        LeaveExample example = new LeaveExample();
        LeaveExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNumberEqualTo(orderNumber);
        int n = leaveMapper.updateByExampleSelective(leave, example);
        if (n != 0 && status == 1) {
            List<Leave> leaves = leaveMapper.selectByExample(example);
            Leave leave1 = leaves.get(0);

            String account = leave1.getAccount();
            MonitorExample monitorExample = new MonitorExample();
            MonitorExample.Criteria monitorCriteria = monitorExample.createCriteria();
            monitorCriteria.andAccountLike("%" + account.substring(0, 7) + "%");
            List<Monitor> monitors = monitorMapper.selectByExample(monitorExample);
            String phoneNum = monitors.get(0).getPhoneNum();

            String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(leave1.getStartTime());
            String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(leave1.getEndTime());
            try {
                MessageUtil.agreeLeave(phoneNum, leave1.getStuName(), leave1.getReason(), startDate, endDate);
            } catch (Exception e) {
                logger.error("短信服务出错，{}", e);
            }
        }
        if (n != 0) {
            return true;
        }
        return false;
    }



}
