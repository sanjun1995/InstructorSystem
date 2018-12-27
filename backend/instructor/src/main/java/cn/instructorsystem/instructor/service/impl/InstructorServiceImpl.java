package cn.instructorsystem.instructor.service.impl;

import cn.instructorsystem.instructor.dao.InstructorMapper;
import cn.instructorsystem.instructor.model.Instructor;
import cn.instructorsystem.instructor.model.InstructorExample;
import cn.instructorsystem.instructor.service.InstructorService;
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
}
