package cn.instructorsystem.student.vo;

import cn.instructorsystem.student.model.Leave;

public class LeaveInfoReqVo {
    private Integer pageNum;
    private Integer pageSize = 10;
    private String token;
    private Leave leave;

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

    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }
}
