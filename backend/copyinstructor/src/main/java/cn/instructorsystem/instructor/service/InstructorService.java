package cn.instructorsystem.instructor.service;

import cn.instructorsystem.instructor.model.Instructor;
import cn.instructorsystem.instructor.vo.ChangePasswordReqVo;
import cn.instructorsystem.instructor.vo.PersonalCenterReqVo;

public interface InstructorService {
    Instructor login(Instructor instructor);

    boolean changePassword(ChangePasswordReqVo changePasswordReqVo);

    Instructor getPersonalInfo(PersonalCenterReqVo personalCenterReqVo);

    boolean updatePersonalInfo(PersonalCenterReqVo personalCenterReqVo);
}
