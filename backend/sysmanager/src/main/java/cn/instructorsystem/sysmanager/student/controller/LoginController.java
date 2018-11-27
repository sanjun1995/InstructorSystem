package cn.instructorsystem.sysmanager.student.controller;

import cn.instructorsystem.model.Student;
import cn.instructorsystem.model.SysManager;
import cn.instructorsystem.model.res.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/sys_manager/login", method = RequestMethod.POST)
    public ResponseResult<SysManager> login(SysManager sysManager) {
        String account = sysManager.getAccount();
        String password = sysManager.getPassword();
        return null;
    }

    @RequestMapping(value = "/student/login", method = RequestMethod.POST)
    public ResponseResult<Student> login(Student student) {
        String account = student.getAccount();
        String password = student.getPassword();
        return null;
    }
}
