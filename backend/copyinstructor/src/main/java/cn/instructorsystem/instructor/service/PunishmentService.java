package cn.instructorsystem.instructor.service;

import cn.instructorsystem.instructor.model.Punishment;
import cn.instructorsystem.instructor.model.res.PunishmentRanking;
import cn.instructorsystem.instructor.vo.PunishmentInfoReqVo;

import java.util.List;

public interface PunishmentService {
    int insertPunishmentInfo(PunishmentInfoReqVo vo);

    List<Punishment> getPunishmentInfosByInsAccount(PunishmentInfoReqVo vo);

    List<PunishmentRanking> getPunishmentRankingInfos(PunishmentInfoReqVo vo);

    int deletePunishmentInfo(PunishmentInfoReqVo vo);
}
