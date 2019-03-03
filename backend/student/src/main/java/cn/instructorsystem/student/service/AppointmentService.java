package cn.instructorsystem.student.service;

import cn.instructorsystem.student.model.Appointment;
import cn.instructorsystem.student.model.Leave;
import cn.instructorsystem.student.model.res.LeaveRanking;
import cn.instructorsystem.student.vo.AppointmentInfoReqVo;
import cn.instructorsystem.student.vo.LeaveInfoReqVo;

import java.util.List;

public interface AppointmentService {
    int insertAppointmentInfo(AppointmentInfoReqVo vo);

    List<Appointment> getAppointmentInfosByStuAccount(AppointmentInfoReqVo vo);

    Appointment getAppointmentInfoByOrderNumber(AppointmentInfoReqVo vo);

    boolean updateAppointmentInfo(AppointmentInfoReqVo vo);

    List<Appointment> getAppointmentInfosByInsAccount(AppointmentInfoReqVo vo);
}
