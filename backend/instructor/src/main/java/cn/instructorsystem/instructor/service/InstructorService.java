package cn.instructorsystem.instructor.service;

import cn.instructorsystem.instructor.model.Instructor;
import cn.instructorsystem.instructor.vo.ChangePasswordReqVo;
import cn.instructorsystem.instructor.vo.InstructorInfoReqVo;
import cn.instructorsystem.instructor.vo.PersonalCenterReqVo;

import java.util.List;

public interface InstructorService {
    Instructor login(Instructor instructor);

    boolean changePassword(ChangePasswordReqVo changePasswordReqVo);

    Instructor getPersonalInfo(PersonalCenterReqVo personalCenterReqVo);

    List<Instructor> getInstructorInfosByPage(InstructorInfoReqVo instructorReqVo);

    boolean updatePersonalInfo(PersonalCenterReqVo personalCenterReqVo);

    int insertInstructorInfo(InstructorInfoReqVo vo);

    int deleteInstructorInfo(InstructorInfoReqVo vo);
}
