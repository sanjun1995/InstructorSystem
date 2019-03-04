package cn.instructorsystem.instructor.vo;

import cn.instructorsystem.instructor.model.Punishment;

/**
 * @author sanjun
 * @date 2019/3/4 10:01
 */
public class PunishmentInfoReqVo {
    private Integer pageNum;
    private Integer pageSize = 10;
    private String token;
    private Punishment punishment;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Punishment getPunishment() {
        return punishment;
    }

    public void setPunishment(Punishment punishment) {
        this.punishment = punishment;
    }
}
