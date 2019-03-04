package cn.instructorsystem.instructor.dao;

import cn.instructorsystem.instructor.model.Instructor;
import cn.instructorsystem.instructor.model.InstructorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InstructorMapper {
    int deleteByExample(InstructorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Instructor record);

    int insertSelective(Instructor record);

    List<Instructor> selectByExample(InstructorExample example);

    Instructor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Instructor record, @Param("example") InstructorExample example);

    int updateByExample(@Param("record") Instructor record, @Param("example") InstructorExample example);

    int updateByPrimaryKeySelective(Instructor record);

    int updateByPrimaryKey(Instructor record);
}