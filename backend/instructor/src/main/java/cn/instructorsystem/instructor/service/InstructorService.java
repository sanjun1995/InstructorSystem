package cn.instructorsystem.instructor.service;

import cn.instructorsystem.instructor.model.Instructor;
import cn.instructorsystem.instructor.vo.ChangePasswordReqVo;

public interface InstructorService {
    Instructor login(Instructor instructor);

    boolean changePassword(ChangePasswordReqVo changePasswordReqVo);
}
