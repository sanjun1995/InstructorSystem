package cn.instructorsystem.student.controller;

import cn.instructorsystem.student.model.Student;
import cn.instructorsystem.student.model.res.ResResult;
import cn.instructorsystem.student.service.StudentService;
import cn.instructorsystem.student.util.ResponseCode;
import cn.instructorsystem.student.util.TokenUtil;
import cn.instructorsystem.student.vo.StudentReqVo;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/student")
public class StudentController {

    private static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/login")
    public ResResult<Student> login(@RequestBody Student stu) {
        Student student = studentService.login(stu);
        ResResult<Student> res = new ResResult<>();
        if (student != null) {
            // 清空密码
            student.setPassword(null);
            List<Student> students = new ArrayList<>();
            students.add(student);
            res.setData(students);
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

    @PostMapping(value = "/getStudentInfos")
    public ResResult<Student> getStudentInfos(@RequestBody StudentReqVo studentReqVo) {
        List<Student> students = studentService.getStudentInfosByPage(studentReqVo);
        ResResult<Student> res = new ResResult<>();
        if (students != null || students.size() != 0) {
            res.setData(students);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("query student information success!");
            logger.info("student.getStudentInfos() studentReqVo: {}，查询成功！", JSON.toJSONString(studentReqVo));
        } else {
            res.setData(students);
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("query student information failure!");
        }
        return res;
    }
}
