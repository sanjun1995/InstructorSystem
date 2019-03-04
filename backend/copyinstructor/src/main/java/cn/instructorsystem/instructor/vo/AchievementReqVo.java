package cn.instructorsystem.instructor.vo;

import cn.instructorsystem.instructor.model.Achievement;

/**
 * @author sanjun
 * @date 2019/3/4 8:16
 */
public class AchievementReqVo {
    private Integer pageNum;
    private Integer pageSize = 10;
    private Achievement achievement;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }
}
