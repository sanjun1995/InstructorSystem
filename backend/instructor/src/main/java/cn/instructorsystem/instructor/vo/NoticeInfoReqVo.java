package cn.instructorsystem.instructor.vo;

import cn.instructorsystem.instructor.model.Notice;
import cn.instructorsystem.instructor.model.Punishment;

/**
 * @author sanjun
 * @date 2019/3/5 11:02
 */
public class NoticeInfoReqVo {
    private Integer pageNum;
    private Integer pageSize = 10;
    private String token;
    private Notice notice;

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

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }
}
