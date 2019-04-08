package cn.instructorsystem.student.controller;

import cn.instructorsystem.student.model.Monitor;
import cn.instructorsystem.student.model.res.ResResult;
import cn.instructorsystem.student.service.MonitorService;
import cn.instructorsystem.student.util.ResponseCode;
import cn.instructorsystem.student.vo.MonitorInfoReqVo;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sanjun
 * @date 2019/3/27 14:19
 */
@RestController
@RequestMapping("/api/monitor")
public class MonitorController {

    private static Logger logger = LoggerFactory.getLogger(MonitorController.class);

    @Autowired
    private MonitorService monitorService;

    @PostMapping(value = "/insertMonitorInfo")
    public ResResult<Monitor> insertMonitorInfo(@RequestBody MonitorInfoReqVo vo) {
        int n = monitorService.insertMonitorInfo(vo);
        ResResult<Monitor> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (n != 0) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("insertMonitorInfo success!");
            logger.info("monitor.insertMonitorInfo() MonitorInfoReqVo: {}，插入负责人信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("insertMonitorInfo failure!");
            logger.info("monitor.insertMonitorInfo() MonitorInfoReqVo: {}，插入负责人信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getMonitorInfosByInsAccount")
    public ResResult<Monitor> getMonitorInfosByInsAccount(@RequestBody MonitorInfoReqVo vo) {
        List<Monitor> monitors = monitorService.getMonitorInfosByInsAccount(vo);
        ResResult<Monitor> res = new ResResult<>();
        if (monitors != null || monitors.size() != 0) {
            res.setData(monitors);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("query monitor information success!");
            logger.info("monitor.getMonitorInfosByInsAccount() MonitorInfoReqVo: {}，获取负责人信息成功！", JSON.toJSONString(vo));
        } else {
            res.setData(monitors);
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("query monitor information failure!");
            logger.info("monitor.getMonitorInfosByInsAccount() MonitorInfoReqVo: {}，获取负责人信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/deleteMonitorInfo")
    public ResResult<Monitor> deleteMonitorInfo(@RequestBody MonitorInfoReqVo vo) {
        int n = monitorService.deleteMonitorInfo(vo);
        ResResult<Monitor> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (n != 0) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("deleteMonitorInfo success!");
            logger.info("monitor.deleteMonitorInfo() MonitorInfoReqVo: {}，插入负责人信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("deleteMonitorInfo failure!");
            logger.info("monitor.deleteMonitorInfo() MonitorInfoReqVo: {}，插入负责人信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/updateMonitorInfo")
    public ResResult<Monitor> updateMonitorInfo(@RequestBody MonitorInfoReqVo vo) {
        int n = monitorService.updateMonitorInfo(vo);
        ResResult<Monitor> res = new ResResult<>();
        if (n != 0) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("updateMonitorInfo success!");
            logger.info("monitor.updateMonitorInfo() MonitorInfoReqVo: {}，更新负责人信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("updateMonitorInfo failure!");
            logger.info("monitor.updateMonitorInfo() MonitorInfoReqVo: {}，更新负责人信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

}
