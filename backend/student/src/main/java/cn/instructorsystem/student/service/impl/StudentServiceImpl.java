package cn.instructorsystem.student.service.impl;

import cn.instructorsystem.student.dao.StudentMapper;
import cn.instructorsystem.student.model.Student;
import cn.instructorsystem.student.model.StudentExample;
import cn.instructorsystem.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sanjun
 * @date 2018/11/25 17:18
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student login(Student student) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(student.getAccount());
        criteria.andPasswordEqualTo(student.getPassword());
        List<Student> list = studentMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
