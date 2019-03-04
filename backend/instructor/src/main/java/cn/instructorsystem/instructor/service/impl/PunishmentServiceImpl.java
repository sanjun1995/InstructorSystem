package cn.instructorsystem.instructor.service.impl;

import cn.instructorsystem.instructor.dao.PunishmentMapper;
import cn.instructorsystem.instructor.model.Punishment;
import cn.instructorsystem.instructor.model.PunishmentExample;
import cn.instructorsystem.instructor.model.res.PunishmentRanking;
import cn.instructorsystem.instructor.service.PunishmentService;
import cn.instructorsystem.instructor.vo.PunishmentInfoReqVo;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sanjun
 * @date 2019/3/4 9:57
 */
@Service
public class PunishmentServiceImpl implements PunishmentService {

    private static Logger logger = LoggerFactory.getLogger(PunishmentServiceImpl.class);

    @Autowired
    private PunishmentMapper punishmentMapper;

    @Override
    public int insertPunishmentInfo(PunishmentInfoReqVo vo) {
        Punishment punishment = vo.getPunishment();
        int n = punishmentMapper.insertSelective(punishment);
        return n;
    }

    @Override
    public List<Punishment> getPunishmentInfosByInsAccount(PunishmentInfoReqVo vo) {
        Integer pageNum = vo.getPageNum();
        Integer pageSize = vo.getPageSize();
        Punishment punishment = vo.getPunishment();
        String insAccount = punishment.getInsAccount();
        String account = punishment.getAccount();
        String stuName = punishment.getStuName();

        PageHelper.startPage(pageNum, pageSize);
        PunishmentExample example = new PunishmentExample();
        PunishmentExample.Criteria criteria = example.createCriteria();
        criteria.andInsAccountEqualTo(insAccount);
        if (account != null) {
            criteria.andAccountLike(account + "%");
        }
        if (stuName != null) {
            criteria.andStuNameLike("%" + stuName + "%");
        }
        List<Punishment> punishments = punishmentMapper.selectByExample(example);
        return punishments;
    }

    @Override
    public List<PunishmentRanking> getPunishmentRankingInfos(PunishmentInfoReqVo vo) {
        Punishment leave = vo.getPunishment();
        List<PunishmentRanking> punishments = punishmentMapper.countPunishmentsByAccount(leave);
        punishments = punishments.stream().sorted(Comparator.comparing(PunishmentRanking::getPunishmentTimes).reversed()).collect(Collectors.toList());
        return punishments;
    }

    @Override
    public int deletePunishmentInfo(PunishmentInfoReqVo vo) {
        Integer id = vo.getPunishment().getId();
        int n = punishmentMapper.deleteByPrimaryKey(id);
        return n;
    }
}
