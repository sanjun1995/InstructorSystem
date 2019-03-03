package cn.instructorsystem.student.dao;

import cn.instructorsystem.student.model.Leave;
import cn.instructorsystem.student.model.LeaveExample;
import java.util.List;

import cn.instructorsystem.student.model.res.LeaveRanking;
import org.apache.ibatis.annotations.Param;

public interface LeaveMapper {
    int deleteByExample(LeaveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Leave record);

    int insertSelective(Leave record);

    List<Leave> selectByExample(LeaveExample example);

    Leave selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Leave record, @Param("example") LeaveExample example);

    int updateByExample(@Param("record") Leave record, @Param("example") LeaveExample example);

    int updateByPrimaryKeySelective(Leave record);

    int updateByPrimaryKey(Leave record);

    List<LeaveRanking> countLeavesByAccount(Leave record);
}