package cn.instructorsystem.student.service.impl;

import cn.instructorsystem.student.dao.StudentMapper;
import cn.instructorsystem.student.model.Student;
import cn.instructorsystem.student.model.StudentExample;
import cn.instructorsystem.student.service.StudentService;
import cn.instructorsystem.student.vo.StudentReqVo;
import com.github.pagehelper.PageHelper;
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

    @Override
        public List<Student> getStudentInfosByPage(StudentReqVo vo) {
        Integer pageNum = vo.getPageNum();
        Integer pageSie = vo.getPageSize();
        Student stu = vo.getStudent();
        PageHelper.startPage(pageNum, pageSie);
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();

        String stuName = stu.getStuName();
        String account = stu.getAccount();
        String dormitory = stu.getDormitory();
        if (!"".equals(stuName)) {
            criteria.andStuNameLike("%" + stuName + "%");
        }
        if (!"".equals(account)) {
            criteria.andAccountLike(account + "%");
        }
        if (!"".equals(dormitory)) {
            criteria.andDormitoryLike("%" + dormitory + "%");
        }
        List<Student> stuInfos = studentMapper.selectByExample(example);
        return stuInfos;
    }
}
