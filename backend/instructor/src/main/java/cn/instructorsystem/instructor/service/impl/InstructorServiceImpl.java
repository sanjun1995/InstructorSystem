package cn.instructorsystem.instructor.service.impl;

import cn.instructorsystem.instructor.dao.InstructorMapper;
import cn.instructorsystem.instructor.model.Instructor;
import cn.instructorsystem.instructor.model.InstructorExample;
import cn.instructorsystem.instructor.service.InstructorService;
import cn.instructorsystem.instructor.util.TokenUtil;
import cn.instructorsystem.instructor.vo.ChangePasswordReqVo;
import cn.instructorsystem.instructor.vo.InstructorInfoReqVo;
import cn.instructorsystem.instructor.vo.PersonalCenterReqVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: sanjun
 * @Date: 2018/12/27 21:35
 */
@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorMapper instructorMapper;

    @Override
    public Instructor login(Instructor instructor) {
        InstructorExample example = new InstructorExample();
        InstructorExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(instructor.getAccount());
        criteria.andPasswordEqualTo(instructor.getPassword());
        List<Instructor> list = instructorMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean changePassword(ChangePasswordReqVo vo) {
        String token = vo.getToken();
        String originPass = vo.getOriginPass();
        String newPass = vo.getNewPass();
        // 通过token获取登录账号
        String account = TokenUtil.getContent(token);
        InstructorExample example = new InstructorExample();
        InstructorExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        criteria.andPasswordEqualTo(originPass);
        List<Instructor> instructors = instructorMapper.selectByExample(example);
        if (instructors.size() != 0) {
            // 清空条件
            example.clear();
            criteria = example.createCriteria();
            Instructor student = new Instructor();
            student.setPassword(newPass);
            criteria.andAccountEqualTo(account);
            int n = instructorMapper.updateByExampleSelective(student, example);
            if (n != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Instructor getPersonalInfo(PersonalCenterReqVo personalCenterReqVo) {
        String token = personalCenterReqVo.getToken();
        String account = null;
        if (token != null && !"".equals(token)) {
            account = TokenUtil.getContent(token);
        }
        Instructor instructor = personalCenterReqVo.getInstructor();
        if (instructor.getAccount() != null && !"".equals(instructor.getAccount())) {
            account = instructor.getAccount();
        }
        InstructorExample example = new InstructorExample();
        InstructorExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        List<Instructor> students = instructorMapper.selectByExample(example);
        if (students.size() != 0) {
            return students.get(0);
        }
        return null;
    }

    @Override
    public List<Instructor> getInstructorInfosByPage(InstructorInfoReqVo vo) {
        Integer pageNum = vo.getPageNum();
        Integer pageSize = vo.getPageSize();
        Instructor instructor = vo.getInstructor();
        PageHelper.startPage(pageNum, pageSize);
        InstructorExample example = new InstructorExample();
        InstructorExample.Criteria criteria = example.createCriteria();

        String insName = instructor.getInsName();
        String account = instructor.getAccount();
        if (!"".equals(insName)) {
            criteria.andInsNameLike("%" + insName + "%");
        }
        if (!"".equals(account)) {
            criteria.andAccountLike(account + "%");
        }
        List<Instructor> instructors = instructorMapper.selectByExample(example);
        return instructors;
    }

    @Override
    public boolean updatePersonalInfo(PersonalCenterReqVo personalCenterReqVo) {
        String token = personalCenterReqVo.getToken();
        String account = null;
        if (token != null && !"".equals(token)) {
            account = TokenUtil.getContent(token);
            System.out.println("token account:" + account);
        }
        Instructor instructor = personalCenterReqVo.getInstructor();
        if (instructor.getAccount() != null) {
            account = instructor.getAccount();
            System.out.println("instructor account:" + account);
        }
        InstructorExample example = new InstructorExample();
        InstructorExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        int n = instructorMapper.updateByExampleSelective(instructor, example);
        if (n != 0) {
            return true;
        }
        return false;
    }

    @Override
    public int insertInstructorInfo(InstructorInfoReqVo vo) {
        Instructor instructor = vo.getInstructor();
        int n = instructorMapper.insertSelective(instructor);
        return n;
    }

    @Override
    public int deleteInstructorInfo(InstructorInfoReqVo vo) {
        Integer id = vo.getInstructor().getId();
        int n = instructorMapper.deleteByPrimaryKey(id);
        return n;
    }
}
