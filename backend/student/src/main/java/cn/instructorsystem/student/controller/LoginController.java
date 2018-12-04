package cn.instructorsystem.student.controller;

import cn.instructorsystem.student.model.Student;
import cn.instructorsystem.student.model.res.ResponseResult;
import cn.instructorsystem.student.service.StudentService;
import cn.instructorsystem.student.util.ResponseCode;
import cn.instructorsystem.student.util.TokenUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/student")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/login")
    public ResponseResult<Student> login(@RequestBody Student stu) {
        Student student = studentService.login(stu);
        ResponseResult<Student> res = new ResponseResult<>();
        if (student != null) {
            // 清空密码
            student.setPassword(null);
            res.setData(student);
            res.setToken(TokenUtil.getToken(student.getAccount()));
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("登录成功!");
            logger.info("student.login() account: {}，登录成功！", stu.getAccount());
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("账号或密码错误!");
            logger.info("student.login() account: {}，登录失败！", stu.getAccount());
        }
        return res;
    }
}
