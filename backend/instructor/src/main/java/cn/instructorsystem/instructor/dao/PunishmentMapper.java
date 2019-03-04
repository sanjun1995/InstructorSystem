package cn.instructorsystem.instructor.dao;

import cn.instructorsystem.instructor.model.Punishment;
import cn.instructorsystem.instructor.model.PunishmentExample;
import java.util.List;

import cn.instructorsystem.instructor.model.res.PunishmentRanking;
import org.apache.ibatis.annotations.Param;

public interface PunishmentMapper {
    int deleteByExample(PunishmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Punishment record);

    int insertSelective(Punishment record);

    List<Punishment> selectByExample(PunishmentExample example);

    Punishment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Punishment record, @Param("example") PunishmentExample example);

    int updateByExample(@Param("record") Punishment record, @Param("example") PunishmentExample example);

    int updateByPrimaryKeySelective(Punishment record);

    int updateByPrimaryKey(Punishment record);

    List<PunishmentRanking> countPunishmentsByAccount(Punishment record);
}