package cn.instructorsystem.sysmanager.controller;

import cn.instructorsystem.sysmanager.model.SysManager;
import cn.instructorsystem.sysmanager.model.res.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login")
    public ResponseResult<SysManager> login(SysManager sysManager) {
        String account = sysManager.getAccount();
        String password = sysManager.getPassword();
        System.out.println("sysmanager login");
        return null;
    }
}
