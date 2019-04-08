package cn.instructorsystem.student.service;

import cn.instructorsystem.student.model.Monitor;
import cn.instructorsystem.student.vo.MonitorInfoReqVo;

import java.util.List;

public interface MonitorService {
    int insertMonitorInfo(MonitorInfoReqVo vo);

    List<Monitor> getMonitorInfosByInsAccount(MonitorInfoReqVo vo);

    int deleteMonitorInfo(MonitorInfoReqVo vo);

    int updateMonitorInfo(MonitorInfoReqVo vo);
}
