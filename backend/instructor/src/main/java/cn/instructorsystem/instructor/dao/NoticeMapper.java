package cn.instructorsystem.instructor.dao;

import cn.instructorsystem.instructor.model.Notice;
import cn.instructorsystem.instructor.model.NoticeExample;
import java.util.List;

import cn.instructorsystem.instructor.model.Punishment;
import cn.instructorsystem.instructor.model.res.PunishmentRanking;
import org.apache.ibatis.annotations.Param;

public interface NoticeMapper {
    int deleteByExample(NoticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    List<Notice> selectByExampleWithBLOBs(NoticeExample example);

    List<Notice> selectByExample(NoticeExample example);

    Notice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByExampleWithBLOBs(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByExample(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);

    List<Notice> selectLastAppointmentNotice(Notice notice);

    List<Notice> selectLastNotice(Notice notice);
}