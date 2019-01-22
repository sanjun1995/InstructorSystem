package cn.instructorsystem.instructor.dao;

import cn.instructorsystem.instructor.model.InstructorStudent;
import cn.instructorsystem.instructor.model.InstructorStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InstructorStudentMapper {
    int deleteByExample(InstructorStudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InstructorStudent record);

    int insertSelective(InstructorStudent record);

    List<InstructorStudent> selectByExample(InstructorStudentExample example);

    InstructorStudent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InstructorStudent record, @Param("example") InstructorStudentExample example);

    int updateByExample(@Param("record") InstructorStudent record, @Param("example") InstructorStudentExample example);

    int updateByPrimaryKeySelective(InstructorStudent record);

    int updateByPrimaryKey(InstructorStudent record);
}