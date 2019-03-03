package cn.instructorsystem.student.service.impl;

import cn.instructorsystem.student.dao.AppointmentMapper;
import cn.instructorsystem.student.model.Appointment;
import cn.instructorsystem.student.model.AppointmentExample;
import cn.instructorsystem.student.service.AppointmentService;
import cn.instructorsystem.student.util.Message;
import cn.instructorsystem.student.util.UUIDUtil;
import cn.instructorsystem.student.vo.AppointmentInfoReqVo;
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
 * @date 2019/3/3 20:01
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private static Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    @Override
    public int insertAppointmentInfo(AppointmentInfoReqVo vo) {
        Appointment appointment = vo.getAppointment();
        appointment.setOrderNumber(UUIDUtil.getUUID());
        appointment.setOperationTime(new Date());
        int n = appointmentMapper.insertSelective(appointment);
        if (n != 0) {
            Message message = new Message();
            message.setAccount(appointment.getAccount());
            message.setIsRead(0);
            message.setNewsType(2);
            message.setStuName(appointment.getStuName());
            message.setInsAccount(appointment.getInsAccount());
            message.setOrderNumber(appointment.getOrderNumber());
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
    public List<Appointment> getAppointmentInfosByStuAccount(AppointmentInfoReqVo vo) {
        Integer pageNum = vo.getPageNum();
        Integer pageSize = vo.getPageSize();
        Appointment appointment = vo.getAppointment();
        String account = appointment.getAccount();

        PageHelper.startPage(pageNum, pageSize);
        AppointmentExample example = new AppointmentExample();
        AppointmentExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        List<Appointment> appointments = appointmentMapper.selectByExample(example);
        return appointments;
    }

    @Override
    public Appointment getAppointmentInfoByOrderNumber(AppointmentInfoReqVo vo) {
        String orderNumber = vo.getAppointment().getOrderNumber();
        AppointmentExample example = new AppointmentExample();
        AppointmentExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNumberEqualTo(orderNumber);
        List<Appointment> leaves = appointmentMapper.selectByExample(example);
        if (leaves.size() != 0) {
            return leaves.get(0);
        }
        return null;
    }

    @Override
    public boolean updateAppointmentInfo(AppointmentInfoReqVo vo) {
        String orderNumber = vo.getAppointment().getOrderNumber();
        Integer status = vo.getAppointment().getStatus();
        String rejectReason = vo.getAppointment().getRejectReason();
        Appointment appointment = new Appointment();
        appointment.setStatus(status);
        appointment.setRejectReason(rejectReason);
        AppointmentExample example = new AppointmentExample();
        AppointmentExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNumberEqualTo(orderNumber);
        int n = appointmentMapper.updateByExampleSelective(appointment, example);
        if (n != 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Appointment> getAppointmentInfosByInsAccount(AppointmentInfoReqVo vo) {
        Integer pageNum = vo.getPageNum();
        Integer pageSize = vo.getPageSize();
        Appointment appointment = vo.getAppointment();
        String insAccount = appointment.getInsAccount();
        String appointmentType = appointment.getAppointmentType();
        String stuName = appointment.getStuName();

        PageHelper.startPage(pageNum, pageSize);
        AppointmentExample example = new AppointmentExample();
        AppointmentExample.Criteria criteria = example.createCriteria();
        criteria.andInsAccountEqualTo(insAccount);
        if (appointmentType != null) {
            criteria.andAppointmentTypeEqualTo(appointmentType);
        }
        if (stuName != null) {
            criteria.andStuNameLike("%" + stuName + "%");
        }
        List<Appointment> appointments = appointmentMapper.selectByExample(example);
        return appointments;
    }
}