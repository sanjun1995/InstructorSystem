package cn.instructorsystem.student.controller;

import cn.instructorsystem.student.model.Leave;
import cn.instructorsystem.student.model.res.ResResult;
import cn.instructorsystem.student.service.LeaveService;
import cn.instructorsystem.student.util.ResponseCode;
import cn.instructorsystem.student.vo.LeaveInfoReqVo;
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
 * @date 2019/1/22 15:31
 */
@RestController
@RequestMapping("/api/leave")
public class LeaveController {
    private static Logger logger = LoggerFactory.getLogger(LeaveController.class);

    @Autowired
    private LeaveService leaveService;

    @PostMapping(value = "/insertLeaveInfo")
    public ResResult<Leave> insertLeaveInfo(@RequestBody LeaveInfoReqVo vo) {
        int n = leaveService.insertLeaveInfo(vo);
        ResResult<Leave> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (n != 0) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("insertLeaveInfo success!");
            logger.info("leave.insertLeaveInfo() LeaveInfoReqVo: {}，更新个人信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("insertLeaveInfo failure!");
            logger.info("leave.insertLeaveInfo() LeaveInfoReqVo: {}，更新个人信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getLeaveInfos")
    public ResResult<Leave> getLeaveInfos(@RequestBody LeaveInfoReqVo vo) {
        List<Leave> leaves = leaveService.getLeaveInfosByPage(vo);
        ResResult<Leave> res = new ResResult<>();
        if (leaves != null || leaves.size() != 0) {
            res.setData(leaves);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("query leave information success!");
            logger.info("leave.getLeaveInfos() leaveReqVo: {}，获取请假信息成功！", JSON.toJSONString(vo));
        } else {
            res.setData(leaves);
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("query leave information failure!");
            logger.info("leave.getLeaveInfos() leaveReqVo: {}，获取请假信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }
}

