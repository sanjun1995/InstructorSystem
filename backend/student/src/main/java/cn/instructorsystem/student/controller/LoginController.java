package cn.instructorsystem.student.controller;

import cn.instructorsystem.student.model.Student;
import cn.instructorsystem.student.model.res.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login")
    public ResponseResult<Student> login(Student student) {
        System.out.println("student login");
        return null;
    }
}
