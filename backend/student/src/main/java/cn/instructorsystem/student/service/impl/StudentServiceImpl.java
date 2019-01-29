package cn.instructorsystem.student.service.impl;

import cn.instructorsystem.student.dao.StudentMapper;
import cn.instructorsystem.student.model.Student;
import cn.instructorsystem.student.model.StudentExample;
import cn.instructorsystem.student.service.StudentService;
import cn.instructorsystem.student.util.TokenUtil;
import cn.instructorsystem.student.vo.ChangePasswordReqVo;
import cn.instructorsystem.student.vo.ClassInfoReqVo;
import cn.instructorsystem.student.vo.PersonalCenterReqVo;
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
    public List<Student> getStudentInfosByPage(ClassInfoReqVo vo) {
        Integer pageNum = vo.getPageNum();
        Integer pageSize = vo.getPageSize();
        Student stu = vo.getStudent();
        PageHelper.startPage(pageNum, pageSize);
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

    @Override
    public boolean changePassword(ChangePasswordReqVo vo) {
        String token = vo.getToken();
        String originPass = vo.getOriginPass();
        String newPass = vo.getNewPass();
        // 通过token获取登录账号
        String account = TokenUtil.getContent(token);
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        criteria.andPasswordEqualTo(originPass);
        List<Student> students = studentMapper.selectByExample(example);
        if (students.size() != 0) {
            // 清空条件
            example.clear();
            criteria = example.createCriteria();
            Student student = new Student();
            student.setPassword(newPass);
            criteria.andAccountEqualTo(account);
            int n = studentMapper.updateByExampleSelective(student, example);
            if (n != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Student getPersonalInfo(PersonalCenterReqVo personalCenterReqVo) {
        String token = personalCenterReqVo.getToken();
        String account = TokenUtil.getContent(token);
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        List<Student> students = studentMapper.selectByExample(example);
        if (students.size() != 0) {
            return students.get(0);
        }
        return null;
    }

    @Override
    public boolean updatePersonalInfo(PersonalCenterReqVo personalCenterReqVo) {
        String token = personalCenterReqVo.getToken();
        String account = TokenUtil.getContent(token);
        Student student = personalCenterReqVo.getStudent();
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        int n = studentMapper.updateByExampleSelective(student, example);
        if (n != 0) {
            return true;
        }
        return false;
    }
}
