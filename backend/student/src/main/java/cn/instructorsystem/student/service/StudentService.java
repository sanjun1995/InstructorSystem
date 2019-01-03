package cn.instructorsystem.student.service;

import cn.instructorsystem.student.model.Student;
import cn.instructorsystem.student.vo.StudentReqVo;

import java.util.List;

/**
 * @author sanjun
 * @date 2018/11/25 17:17
 */
public interface StudentService {
    Student login(Student student);

    List<Student> getStudentInfosByPage(StudentReqVo studentReqVo);
}
