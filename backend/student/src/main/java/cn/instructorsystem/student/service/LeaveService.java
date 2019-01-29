package cn.instructorsystem.student.service;

import cn.instructorsystem.student.model.Leave;
import cn.instructorsystem.student.model.Student;
import cn.instructorsystem.student.vo.ClassInfoReqVo;
import cn.instructorsystem.student.vo.LeaveInfoReqVo;

import java.util.List;

public interface LeaveService {
    int insertLeaveInfo(LeaveInfoReqVo leaveInfoReqVo);

    List<Leave> getLeaveInfosByPage(LeaveInfoReqVo leaveInfoReqVo);

    Leave getLeaveInfoByOrderNumber(LeaveInfoReqVo leaveInfoReqVo);

    boolean updateLeaveInfo(LeaveInfoReqVo leaveInfoReqVo);
}
