package cn.instructorsystem.student.controller;

import cn.instructorsystem.student.model.Leave;
import cn.instructorsystem.student.model.res.LeaveRanking;
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
            logger.info("leave.insertLeaveInfo() LeaveInfoReqVo: {}，插入请假信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("insertLeaveInfo failure!");
            logger.info("leave.insertLeaveInfo() LeaveInfoReqVo: {}，插入请假信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getLeaveInfosByStuAccount")
    public ResResult<Leave> getLeaveInfosByStuAccount(@RequestBody LeaveInfoReqVo vo) {
        List<Leave> leaves = leaveService.getLeaveInfosByStuAccount(vo);
        ResResult<Leave> res = new ResResult<>();
        if (leaves != null || leaves.size() != 0) {
            res.setData(leaves);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("query leave information success!");
            logger.info("leave.getLeaveInfosByStuAccount() leaveReqVo: {}，获取请假信息成功！", JSON.toJSONString(vo));
        } else {
            res.setData(leaves);
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("query leave information failure!");
            logger.info("leave.getLeaveInfosByStuAccount() leaveReqVo: {}，获取请假信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getLeaveInfosByInsAccount")
    public ResResult<Leave> getLeaveInfosByInsAccount(@RequestBody LeaveInfoReqVo vo) {
        List<Leave> leaves = leaveService.getLeaveInfosByInsAccount(vo);
        ResResult<Leave> res = new ResResult<>();
        if (leaves != null || leaves.size() != 0) {
            res.setData(leaves);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("query leave information success!");
            logger.info("leave.getLeaveInfosByInsAccount() leaveReqVo: {}，获取请假信息成功！", JSON.toJSONString(vo));
        } else {
            res.setData(leaves);
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("query leave information failure!");
            logger.info("leave.getLeaveInfosByInsAccount() leaveReqVo: {}，获取请假信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getLeaveInfoByOrderNumber")
    public ResResult<Leave> getLeaveInfoByOrderNumber(@RequestBody LeaveInfoReqVo vo) {
        Leave leave = leaveService.getLeaveInfoByOrderNumber(vo);
        ResResult<Leave> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (leave != null) {
            List<Leave> leaves = new ArrayList<>();
            leaves.add(leave);
            res.setData(leaves);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("getLeaveInfoByOrderNumber success!");
            logger.info("leave.getLeaveInfoByOrderNumber() LeaveInfoReqVo: {}，获取个人请假信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("getLeaveInfoByOrderNumber failure!");
            logger.info("leave.getLeaveInfoByOrderNumber() LeaveInfoReqVo: {}，修改个人请假信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getLeaveRankingInfos")
    public ResResult<LeaveRanking> getLeaveRankingInfos(@RequestBody LeaveInfoReqVo vo) {
        List<LeaveRanking> leaves = leaveService.getLeaveRankingInfos(vo);
        ResResult<LeaveRanking> res = new ResResult<>();
        if (leaves != null || leaves.size() != 0) {
            res.setData(leaves);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("query leave ranking information success!");
            logger.info("leave.getLeaveRankingInfos() leaveReqVo: {}，获取请假排行成功！", JSON.toJSONString(vo));
        } else {
            res.setData(leaves);
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("query leave information failure!");
            logger.info("leave.getLeaveRankingInfos() leaveReqVo: {}，获取请假排行失败！", JSON.toJSONString(vo));
        }
        return res;
    }
}

