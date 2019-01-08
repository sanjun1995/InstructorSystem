package cn.instructorsystem.instructor.controller;

import cn.instructorsystem.instructor.model.Instructor;
import cn.instructorsystem.instructor.model.res.ResResult;
import cn.instructorsystem.instructor.service.InstructorService;
import cn.instructorsystem.instructor.util.ResponseCode;
import cn.instructorsystem.instructor.util.TokenUtil;
import cn.instructorsystem.instructor.vo.ChangePasswordReqVo;
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
@RequestMapping("/api/instructor")
public class InstructorController {

    private static Logger logger = LoggerFactory.getLogger(InstructorController.class);

    @Autowired
    private InstructorService instructorService;

    @PostMapping(value = "/login")
    public ResResult<Instructor> login(@RequestBody Instructor ins) {
        Instructor instructor = instructorService.login(ins);
        ResResult<Instructor> res = new ResResult<>();
        if (instructor != null) {
            // 清空密码
            instructor.setPassword(null);
            List<Instructor> instructors = new ArrayList<>();
            instructors.add(instructor);
            res.setData(instructors);
            res.setToken(TokenUtil.getToken(instructor.getAccount()));
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("登录成功!");
            logger.info("instructor.login() account: {}，登录成功！", ins.getAccount());
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("账号或密码错误!");
            logger.info("instructor.login() account: {}，登录失败！", ins.getAccount());
        }
        return res;
    }

    @PostMapping(value = "/changePassword")
    public ResResult<Instructor> changePassword(@RequestBody ChangePasswordReqVo vo) {
        boolean isSuccess = instructorService.changePassword(vo);
        ResResult<Instructor> res = new ResResult<>();
        Instructor student = new Instructor();
        student.setAccount(TokenUtil.getContent(vo.getToken()));
        List<Instructor> instructors = new ArrayList<>();
        instructors.add(student);
        res.setData(instructors);
        res.setToken(vo.getToken());
        if (isSuccess) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("change password success!");
            logger.info("instructor.changePassword() ChangePasswordReqVo: {}，修改密码成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("change password failure!");
            logger.info("instructor.changePassword() ChangePasswordReqVo: {}，修改密码失败！", JSON.toJSONString(vo));
        }
        return res;
    }
}
