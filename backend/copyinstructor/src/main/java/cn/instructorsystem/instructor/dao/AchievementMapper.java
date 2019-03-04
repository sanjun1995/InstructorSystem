package cn.instructorsystem.instructor.dao;

import cn.instructorsystem.instructor.model.Achievement;
import cn.instructorsystem.instructor.model.AchievementExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AchievementMapper {
    int deleteByExample(AchievementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Achievement record);

    int insertSelective(Achievement record);

    List<Achievement> selectByExample(AchievementExample example);

    Achievement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Achievement record, @Param("example") AchievementExample example);

    int updateByExample(@Param("record") Achievement record, @Param("example") AchievementExample example);

    int updateByPrimaryKeySelective(Achievement record);

    int updateByPrimaryKey(Achievement record);
}