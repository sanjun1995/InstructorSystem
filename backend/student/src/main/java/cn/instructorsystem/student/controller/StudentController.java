package cn.instructorsystem.student.controller;

import cn.instructorsystem.student.model.Student;
import cn.instructorsystem.student.model.res.ResResult;
import cn.instructorsystem.student.service.StudentService;
import cn.instructorsystem.student.util.ResponseCode;
import cn.instructorsystem.student.util.TokenUtil;
import cn.instructorsystem.student.vo.ChangePasswordReqVo;
import cn.instructorsystem.student.vo.ClassInfoReqVo;
import cn.instructorsystem.student.vo.PersonalCenterReqVo;
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
    public ResResult<Student> getStudentInfos(@RequestBody ClassInfoReqVo vo) {
        List<Student> students = studentService.getStudentInfosByPage(vo);
        ResResult<Student> res = new ResResult<>();
        if (students != null || students.size() != 0) {
            res.setData(students);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("query student information success!");
            logger.info("student.getStudentInfos() studentReqVo: {}，获取学生列表成功！", JSON.toJSONString(vo));
        } else {
            res.setData(students);
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("query student information failure!");
            logger.info("student.getStudentInfos() studentReqVo: {}，获取学生列表失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/changePassword")
    public ResResult<Student> changePassword(@RequestBody ChangePasswordReqVo vo) {
        boolean isSuccess = studentService.changePassword(vo);
        ResResult<Student> res = new ResResult<>();
        Student student = new Student();
        student.setAccount(TokenUtil.getContent(vo.getToken()));
        List<Student> students = new ArrayList<>();
        students.add(student);
        res.setData(students);
        res.setToken(vo.getToken());
        if (isSuccess) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("change password success!");
            logger.info("student.changePassword() ChangePasswordReqVo: {}，修改密码成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("change password failure!");
            logger.info("student.changePassword() ChangePasswordReqVo: {}，修改密码失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getPersonalInfo")
    public ResResult<Student> getPersonalInfo(@RequestBody PersonalCenterReqVo vo) {
        Student student = studentService.getPersonalInfo(vo);
        ResResult<Student> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (student != null) {
            List<Student> students = new ArrayList<>();
            students.add(student);
            res.setData(students);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("getPersonalInfo success!");
            logger.info("student.getPersonalInfo() PersonalCenterReqVo: {}，获取个人信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("getPersonalInfo failure!");
            logger.info("student.getPersonalInfo() PersonalCenterReqVo: {}，修改个人信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/updatePersonalInfo")
    public ResResult<Student> updatePersonalInfo(@RequestBody PersonalCenterReqVo vo) {
        boolean isUpdate = studentService.updatePersonalInfo(vo);
        ResResult<Student> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (isUpdate) {
            List<Student> students = new ArrayList<>();
            Student student = studentService.getPersonalInfo(vo);
            students.add(student);
            res.setData(students);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("updateStudentInfo success!");
            logger.info("student.updateStudentInfo() PersonalCenterReqVo: {}，更新个人信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("updateStudentInfo failure!");
            logger.info("student.updateStudentInfo() PersonalCenterReqVo: {}，更新个人信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }
}
