package cn.instructorsystem.instructor.service.impl;

import cn.instructorsystem.instructor.dao.AchievementMapper;
import cn.instructorsystem.instructor.dao.NotificationMapper;
import cn.instructorsystem.instructor.model.Achievement;
import cn.instructorsystem.instructor.model.AchievementExample;
import cn.instructorsystem.instructor.model.Notification;
import cn.instructorsystem.instructor.model.NotificationExample;
import cn.instructorsystem.instructor.service.AchievementService;
import cn.instructorsystem.instructor.vo.AchievementReqVo;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sanjun
 * @date 2019/3/4 8:06
 */
@Service
public class AchievementServiceImpl implements AchievementService {

    private static Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Autowired
    private AchievementMapper achievementMapper;

    @Override
    public List<Achievement> getAchievementsByPage(AchievementReqVo vo) {
        Integer pageNum = vo.getPageNum();
        Integer pageSize = vo.getPageSize();
        Achievement achievement = vo.getAchievement();
        PageHelper.startPage(pageNum, pageSize);

        AchievementExample example = new AchievementExample();
        AchievementExample.Criteria criteria = example.createCriteria();
        String stuName = achievement.getStuName();
        String account = achievement.getAccount();
        if (!"".equals(stuName)) {
            criteria.andStuNameLike("%" + stuName + "%");
        }
        if (!"".equals(account)) {
            criteria.andAccountLike(account + "%");
        }
        criteria.andInsAccountEqualTo(achievement.getInsAccount());

        List<Achievement> achievements = achievementMapper.selectByExample(example);
        return achievements;
    }

    @Override
    public boolean updateAchievementInfo(AchievementReqVo vo) {
        String score1 = vo.getAchievement().getScore1();
        String score2 = vo.getAchievement().getScore2();
        String score3 = vo.getAchievement().getScore3();
        String score1Rank = vo.getAchievement().getScore1Rank();
        String score2Rank = vo.getAchievement().getScore2Rank();
        String score3Rank = vo.getAchievement().getScore3Rank();

        String comprehensive1 = vo.getAchievement().getComprehensive1();
        String comprehensive2 = vo.getAchievement().getComprehensive2();
        String comprehensive3 = vo.getAchievement().getComprehensive3();
        String comprehensive1Rank = vo.getAchievement().getComprehensive1Rank();
        String comprehensive2Rank = vo.getAchievement().getComprehensive2Rank();
        String comprehensive3Rank = vo.getAchievement().getComprehensive3Rank();

        String account = vo.getAchievement().getAccount();
        Achievement achievement = new Achievement();
        achievement.setScore1(score1);
        achievement.setScore2(score2);
        achievement.setScore3(score3);
        achievement.setScore1Rank(score1Rank);
        achievement.setScore2Rank(score2Rank);
        achievement.setScore3Rank(score3Rank);
        achievement.setComprehensive1(comprehensive1);
        achievement.setComprehensive2(comprehensive2);
        achievement.setComprehensive3(comprehensive3);
        achievement.setComprehensive1Rank(comprehensive1Rank);
        achievement.setComprehensive2Rank(comprehensive2Rank);
        achievement.setComprehensive3Rank(comprehensive3Rank);
        AchievementExample example = new AchievementExample();
        AchievementExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        int n = achievementMapper.updateByExampleSelective(achievement, example);
        if (n != 0) {
            return true;
        }
        return false;
    }
}
