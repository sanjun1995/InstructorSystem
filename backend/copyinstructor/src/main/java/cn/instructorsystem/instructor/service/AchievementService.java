package cn.instructorsystem.instructor.service;

import cn.instructorsystem.instructor.model.Achievement;
import cn.instructorsystem.instructor.vo.AchievementReqVo;

import java.util.List;

public interface AchievementService {
    List<Achievement> getAchievementsByPage(AchievementReqVo vo);

    boolean updateAchievementInfo(AchievementReqVo vo);
}
