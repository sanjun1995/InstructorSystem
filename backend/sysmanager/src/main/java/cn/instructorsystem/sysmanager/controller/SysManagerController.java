package cn.instructorsystem.sysmanager.controller;

import cn.instructorsystem.sysmanager.model.SysManager;
import cn.instructorsystem.sysmanager.model.res.ResResult;
import cn.instructorsystem.sysmanager.service.SysManagerService;
import cn.instructorsystem.sysmanager.util.ResponseCode;
import cn.instructorsystem.sysmanager.util.TokenUtil;
import cn.instructorsystem.sysmanager.vo.ChangePasswordReqVo;
import cn.instructorsystem.sysmanager.vo.PersonalCenterReqVo;
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

@RestController
@RequestMapping("/api/sysManager")
public class SysManagerController {

    private static Logger logger = LoggerFactory.getLogger(SysManagerController.class);

    @Autowired
    private SysManagerService sysManagerService;

    @PostMapping(value = "/login")
    public ResResult<SysManager> login(@RequestBody SysManager sys) {
        SysManager sysManager = sysManagerService.login(sys);
        ResResult<SysManager> res = new ResResult<>();
        if (sysManager != null) {
            // 清空密码
            sysManager.setPassword(null);
            List<SysManager> students = new ArrayList<>();
            students.add(sysManager);
            res.setData(students);
            res.setToken(TokenUtil.getToken(sysManager.getAccount()));
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("登录成功!");
            logger.info("sysManager.login() account: {}，登录成功！", sys.getAccount());
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("账号或密码错误!");
            logger.info("sysManager.login() account: {}，登录失败！", sys.getAccount());
        }
        return res;
    }

    @PostMapping(value = "/changePassword")
    public ResResult<SysManager> changePassword(@RequestBody ChangePasswordReqVo vo) {
        boolean isSuccess = sysManagerService.changePassword(vo);
        ResResult<SysManager> res = new ResResult<>();
        SysManager sysManager = new SysManager();
        sysManager.setAccount(TokenUtil.getContent(vo.getToken()));
        List<SysManager> sysManagers = new ArrayList<>();
        sysManagers.add(sysManager);
        res.setData(sysManagers);
        res.setToken(vo.getToken());
        if (isSuccess) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("change password success!");
            logger.info("sysManager.changePassword() ChangePasswordReqVo: {}，修改密码成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("change password failure!");
            logger.info("sysManager.changePassword() ChangePasswordReqVo: {}，修改密码失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getPersonalInfo")
    public ResResult<SysManager> getPersonalInfo(@RequestBody PersonalCenterReqVo vo) {
        SysManager sysManager = sysManagerService.getPersonalInfo(vo);
        ResResult<SysManager> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (sysManager != null) {
            List<SysManager> sysManagers = new ArrayList<>();
            sysManagers.add(sysManager);
            res.setData(sysManagers);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("getPersonalInfo success!");
            logger.info("sysManager.getPersonalInfo() PersonalCenterReqVo: {}，获取个人信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("getPersonalInfo failure!");
            logger.info("sysManager.getPersonalInfo() PersonalCenterReqVo: {}，修改个人信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }
}
