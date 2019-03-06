package cn.instructorsystem.instructor.controller;

import cn.instructorsystem.instructor.model.Punishment;
import cn.instructorsystem.instructor.model.res.PunishmentRanking;
import cn.instructorsystem.instructor.model.res.ResResult;
import cn.instructorsystem.instructor.service.PunishmentService;
import cn.instructorsystem.instructor.util.ResponseCode;
import cn.instructorsystem.instructor.vo.PunishmentInfoReqVo;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sanjun
 * @date 2019/3/4 10:29
 */
@RestController
@RequestMapping("/api/punishment")
public class PunishmentController {
    private static Logger logger = LoggerFactory.getLogger(PunishmentController.class);

    @Autowired
    private PunishmentService punishmentService;

    @PostMapping(value = "/insertPunishmentInfo")
    public ResResult<Punishment> insertPunishmentInfo(@RequestBody PunishmentInfoReqVo vo) {
        int n = punishmentService.insertPunishmentInfo(vo);
        ResResult<Punishment> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (n != 0) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("insertPunishmentInfo success!");
            logger.info("punishment.insertPunishmentInfo() PunishmentInfoReqVo: {}，插入通报信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("insertPunishmentInfo failure!");
            logger.info("punishment.insertPunishmentInfo() PunishmentInfoReqVo: {}，插入通报信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getPunishmentInfosByInsAccount")
    public ResResult<Punishment> getPunishmentInfosByInsAccount(@RequestBody PunishmentInfoReqVo vo) {
        List<Punishment> punishments = punishmentService.getPunishmentInfosByInsAccount(vo);
        ResResult<Punishment> res = new ResResult<>();
        if (punishments != null || punishments.size() != 0) {
            res.setData(punishments);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("query punishment information success!");
            logger.info("punishment.getPunishmentInfosByInsAccount() punishmentReqVo: {}，获取通报信息成功！", JSON.toJSONString(vo));
        } else {
            res.setData(punishments);
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("query punishment information failure!");
            logger.info("punishment.getPunishmentInfosByInsAccount() punishmentReqVo: {}，获取通报信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getPunishmentRankingInfos")
    public ResResult<PunishmentRanking> getPunishmentRankingInfos(@RequestBody PunishmentInfoReqVo vo) {
        List<PunishmentRanking> punishments = punishmentService.getPunishmentRankingInfos(vo);
        ResResult<PunishmentRanking> res = new ResResult<>();
        if (punishments != null || punishments.size() != 0) {
            res.setData(punishments);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("query punishment ranking information success!");
            logger.info("punishment.getPunishmentRankingInfos() punishmentReqVo: {}，获取通报排行成功！", JSON.toJSONString(vo));
        } else {
            res.setData(punishments);
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("query punishment ranking information failure!");
            logger.info("punishment.getPunishmentRankingInfos() punishmentReqVo: {}，获取请假排行失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/deletePunishmentInfo")
    public ResResult<Punishment> deletePunishmentInfo(@RequestBody PunishmentInfoReqVo vo) {
        int n = punishmentService.deletePunishmentInfo(vo);
        ResResult<Punishment> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (n != 0) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("deletePunishmentInfo success!");
            logger.info("punishment.deletePunishmentInfo() PunishmentInfoReqVo: {}，删除通报信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("deletePunishmentInfo failure!");
            logger.info("punishment.deletePunishmentInfo() PunishmentInfoReqVo: {}，删除通报信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }
}
