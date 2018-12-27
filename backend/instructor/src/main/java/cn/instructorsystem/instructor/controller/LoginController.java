package cn.instructorsystem.instructor.controller;

import cn.instructorsystem.instructor.model.Instructor;
import cn.instructorsystem.instructor.model.res.ResponseResult;
import cn.instructorsystem.instructor.service.InstructorService;
import cn.instructorsystem.instructor.util.ResponseCode;
import cn.instructorsystem.instructor.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/instructor")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private InstructorService instructorService;

    @PostMapping(value = "/login")
    public ResponseResult<Instructor> login(@RequestBody Instructor ins) {
        Instructor instructor = instructorService.login(ins);
        ResponseResult<Instructor> res = new ResponseResult<>();
        if (instructor != null) {
            // 清空密码
            instructor.setPassword(null);
            res.setData(instructor);
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
}
