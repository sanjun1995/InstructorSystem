package cn.instructorsystem.student.controller;

import cn.instructorsystem.student.model.Appointment;
import cn.instructorsystem.student.model.res.ResResult;
import cn.instructorsystem.student.service.AppointmentService;
import cn.instructorsystem.student.util.ResponseCode;
import cn.instructorsystem.student.vo.AppointmentInfoReqVo;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sanjun
 * @date 2019/3/3 19:47
 */
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    private static Logger logger = LoggerFactory.getLogger(LeaveController.class);

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping(value = "/insertAppointmentInfo")
    public ResResult<Appointment> insertAppointmentInfo(@RequestBody AppointmentInfoReqVo vo) {
        int n = appointmentService.insertAppointmentInfo(vo);
        ResResult<Appointment> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (n != 0) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("insertAppointmentInfo success!");
            logger.info("leave.insertAppointmentInfo() AppointmentInfoReqVo: {}，插入预约信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("insertAppointmentInfo failure!");
            logger.info("leave.insertAppointmentInfo() AppointmentInfoReqVo: {}，插入预约信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getAppointmentInfosByStuAccount")
    public ResResult<Appointment> getAppointmentInfosByStuAccount(@RequestBody AppointmentInfoReqVo vo) {
        List<Appointment> appointments = appointmentService.getAppointmentInfosByStuAccount(vo);
        ResResult<Appointment> res = new ResResult<>();
        if (appointments != null || appointments.size() != 0) {
            res.setData(appointments);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("query appointment information success!");
            logger.info("leave.getAppointmentInfosByStuAccount() appointmentInfoReqVo: {}，获取请假信息成功！", JSON.toJSONString(vo));
        } else {
            res.setData(appointments);
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("query appointment information failure!");
            logger.info("leave.getAppointmentInfosByStuAccount() appointmentInfoReqVo: {}，获取请假信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getAppointmentInfosByInsAccount")
    public ResResult<Appointment> getAppointmentInfosByInsAccount(@RequestBody AppointmentInfoReqVo vo) {
        List<Appointment> appointments = appointmentService.getAppointmentInfosByInsAccount(vo);
        ResResult<Appointment> res = new ResResult<>();
        if (appointments != null || appointments.size() != 0) {
            res.setData(appointments);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("query appointment information success!");
            logger.info("leave.getAppointmentInfosByInsAccount() appointmentReqVo: {}，获取请假信息成功！", JSON.toJSONString(vo));
        } else {
            res.setData(appointments);
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("query appointment information failure!");
            logger.info("leave.getAppointmentInfosByInsAccount() appointmentReqVo: {}，获取请假信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getAppointmentInfoByOrderNumber")
    public ResResult<Appointment> getAppointmentInfoByOrderNumber(@RequestBody AppointmentInfoReqVo vo) {
        Appointment appointment = appointmentService.getAppointmentInfoByOrderNumber(vo);
        ResResult<Appointment> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (appointment != null) {
            List<Appointment> appointments = new ArrayList<>();
            appointments.add(appointment);
            res.setData(appointments);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("getAppointmentInfoByOrderNumber success!");
            logger.info("leave.getAppointmentInfoByOrderNumber() appointmentInfoReqVo: {}，获取个人预约信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("getAppointmentInfoByOrderNumber failure!");
            logger.info("leave.getAppointmentInfoByOrderNumber() appointmentInfoReqVo: {}，修改个人预约信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }
}
