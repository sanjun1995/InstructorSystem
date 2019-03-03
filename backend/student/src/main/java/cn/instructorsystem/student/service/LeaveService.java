package cn.instructorsystem.student.service;

import cn.instructorsystem.student.model.Leave;
import cn.instructorsystem.student.model.res.LeaveRanking;
import cn.instructorsystem.student.vo.LeaveInfoReqVo;

import java.util.List;

public interface LeaveService {
    int insertLeaveInfo(LeaveInfoReqVo leaveInfoReqVo);

    List<Leave> getLeaveInfosByStuAccount(LeaveInfoReqVo leaveInfoReqVo);

    Leave getLeaveInfoByOrderNumber(LeaveInfoReqVo leaveInfoReqVo);

    boolean updateLeaveInfo(LeaveInfoReqVo leaveInfoReqVo);

    List<Leave> getLeaveInfosByInsAccount(LeaveInfoReqVo leaveInfoReqVo);

    List<LeaveRanking> getLeaveRankingInfos(LeaveInfoReqVo leaveInfoReqVo);
}
