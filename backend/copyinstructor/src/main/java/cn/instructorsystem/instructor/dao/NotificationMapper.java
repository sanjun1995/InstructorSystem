package cn.instructorsystem.instructor.dao;

import cn.instructorsystem.instructor.model.Notification;
import cn.instructorsystem.instructor.model.NotificationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NotificationMapper {
    int deleteByExample(NotificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Notification record);

    int insertSelective(Notification record);

    List<Notification> selectByExample(NotificationExample example);

    Notification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Notification record, @Param("example") NotificationExample example);

    int updateByExample(@Param("record") Notification record, @Param("example") NotificationExample example);

    int updateByPrimaryKeySelective(Notification record);

    int updateByPrimaryKey(Notification record);
}