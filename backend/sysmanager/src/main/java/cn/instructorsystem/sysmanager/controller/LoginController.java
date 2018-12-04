package cn.instructorsystem.sysmanager.controller;

import cn.instructorsystem.sysmanager.model.SysManager;
import cn.instructorsystem.sysmanager.model.res.ResponseResult;
import cn.instructorsystem.sysmanager.service.SysManagerService;
import cn.instructorsystem.sysmanager.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sysmanager")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SysManagerService sysManagerService;

    @RequestMapping(value = "/login")
    public ResponseResult<SysManager> login(SysManager student) {
        boolean isLogin = sysManagerService.login(student);
        ResponseResult<SysManager> res = new ResponseResult<>();
        // 清空密码
        student.setPassword(null);
        res.setData(student);
        if (isLogin) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("登录成功!");
            logger.info("sysmanager.login() account: {}，登录成功！", student.getAccount());
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("账号或密码错误!");
            logger.info("sysmanager.login() account: {}，登录失败！", student.getAccount());
        }
        return res;
    }
}
