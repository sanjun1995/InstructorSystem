package cn.instructorsystem.instructor.controller;

import cn.instructorsystem.instructor.model.Achievement;
import cn.instructorsystem.instructor.model.res.ResResult;
import cn.instructorsystem.instructor.service.AchievementService;
import cn.instructorsystem.instructor.util.ResponseCode;
import cn.instructorsystem.instructor.vo.AchievementReqVo;
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
 * @date 2019/3/4 8:31
 */
@RestController
@RequestMapping("/api/achievement")
public class AchievementController {
    private static Logger logger = LoggerFactory.getLogger(AchievementController.class);

    @Autowired
    private AchievementService achievementService;

    @PostMapping(value = "/getAchievementsByPage")
    public ResResult<Achievement> getAchievementsByPage(@RequestBody AchievementReqVo vo) {
        List<Achievement> achievements = achievementService.getAchievementsByPage(vo);
        int size = achievements.size();
        ResResult<Achievement> res = new ResResult<>();
        res.setData(achievements);
        if (achievements != null) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("getAchievementsByPage success!");
            logger.info("achievement.getAchievementsByPage() AchievementReqVo: {}，查询成绩成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("getAchievementsByPage failure!");
            logger.info("achievement.getAchievementsByPage() AchievementReqVo: {}，查询成绩失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/updateAchievementInfo")
    public ResResult<Achievement> updateAchievementInfo(@RequestBody AchievementReqVo vo) {
        boolean isUpdate = achievementService.updateAchievementInfo(vo);
        ResResult<Achievement> res = new ResResult<>();
        if (isUpdate) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("updateAchievementInfo success!");
            logger.info("achievement.updateAchievementInfo() AchievementReqVo: {}，更新成绩信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("updateAchievementInfo failure!");
            logger.info("achievement.updateAchievementInfo() AchievementReqVo: {}，更新成绩信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

}
