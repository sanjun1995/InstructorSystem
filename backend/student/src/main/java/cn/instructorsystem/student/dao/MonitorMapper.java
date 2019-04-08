package cn.instructorsystem.student.dao;

import cn.instructorsystem.student.model.Monitor;
import cn.instructorsystem.student.model.MonitorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MonitorMapper {
    int deleteByExample(MonitorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Monitor record);

    int insertSelective(Monitor record);

    List<Monitor> selectByExample(MonitorExample example);

    Monitor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Monitor record, @Param("example") MonitorExample example);

    int updateByExample(@Param("record") Monitor record, @Param("example") MonitorExample example);

    int updateByPrimaryKeySelective(Monitor record);

    int updateByPrimaryKey(Monitor record);
}