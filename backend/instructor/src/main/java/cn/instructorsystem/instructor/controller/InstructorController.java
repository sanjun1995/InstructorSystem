package cn.instructorsystem.instructor.controller;

import cn.instructorsystem.instructor.model.Instructor;
import cn.instructorsystem.instructor.model.Punishment;
import cn.instructorsystem.instructor.model.res.ResResult;
import cn.instructorsystem.instructor.service.InstructorService;
import cn.instructorsystem.instructor.util.ResponseCode;
import cn.instructorsystem.instructor.util.TokenUtil;
import cn.instructorsystem.instructor.vo.ChangePasswordReqVo;
import cn.instructorsystem.instructor.vo.InstructorInfoReqVo;
import cn.instructorsystem.instructor.vo.PersonalCenterReqVo;
import cn.instructorsystem.instructor.vo.PunishmentInfoReqVo;
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

    @PostMapping(value = "/getPersonalInfo")
    public ResResult<Instructor> getPersonalInfo(@RequestBody PersonalCenterReqVo vo) {
        Instructor instructor = instructorService.getPersonalInfo(vo);
        ResResult<Instructor> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (instructor != null) {
            List<Instructor> instructors = new ArrayList<>();
            instructors.add(instructor);
            res.setData(instructors);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("getPersonalInfo success!");
            logger.info("instructor.getPersonalInfo() PersonalCenterReqVo: {}，获取个人信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("getPersonalInfo failure!");
            logger.info("instructor.getPersonalInfo() PersonalCenterReqVo: {}，修改个人信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/updatePersonalInfo")
    public ResResult<Instructor> updatePersonalInfo(@RequestBody PersonalCenterReqVo vo) {
        boolean isUpdate = instructorService.updatePersonalInfo(vo);
        ResResult<Instructor> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (isUpdate) {
            List<Instructor> instructors = new ArrayList<>();
            Instructor instructor = instructorService.getPersonalInfo(vo);
            instructors.add(instructor);
            res.setData(instructors);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("updateInstructorInfo success!");
            logger.info("instructor.updateInstructorInfo() PersonalCenterReqVo: {}，更新个人信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("updateInstructorInfo failure!");
            logger.info("instructor.updateInstructorInfo() PersonalCenterReqVo: {}，更新个人信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/insertInstructorInfo")
    public ResResult<Instructor> insertInstructorInfo(@RequestBody InstructorInfoReqVo vo) {
        int n = instructorService.insertInstructorInfo(vo);
        ResResult<Instructor> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (n != 0) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("insertInstructorInfo success!");
            logger.info("instructor.insertInstructorInfo() InstructorInfoReqVo: {}，增加辅导员信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("insertInstructorInfo failure!");
            logger.info("instructor.insertInstructorInfo() InstructorInfoReqVo: {}，增加辅导员信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/deleteInstructorInfo")
    public ResResult<Instructor> deleteInstructorInfo(@RequestBody InstructorInfoReqVo vo) {
        int n = instructorService.deleteInstructorInfo(vo);
        ResResult<Instructor> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (n != 0) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("deleteInstructorInfo success!");
            logger.info("instructor.deleteInstructorInfo() InstructorInfoReqVo: {}，删除辅导员信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("deleteInstructorInfo failure!");
            logger.info("instructor.deleteInstructorInfo() InstructorInfoReqVo: {}，删除辅导员信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getInstructorInfos")
    public ResResult<Instructor> getInstructorInfos(@RequestBody InstructorInfoReqVo vo) {
        List<Instructor> instructors = instructorService.getInstructorInfosByPage(vo);
        ResResult<Instructor> res = new ResResult<>();
        res.setData(instructors);
        if (instructors != null || instructors.size() != 0) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("query instructor information success!");
            logger.info("instructor.getInstructorInfos() instructorReqVo: {}，获取辅导员信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("query instructor information failure!");
            logger.info("instructor.getInstructorInfos() instructorReqVo: {}，获取辅导员信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }
}
