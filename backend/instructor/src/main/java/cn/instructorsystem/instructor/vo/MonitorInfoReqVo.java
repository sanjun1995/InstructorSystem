package cn.instructorsystem.instructor.vo;

import cn.instructorsystem.instructor.model.Monitor;
import cn.instructorsystem.instructor.model.Notice;

/**
 * @author sanjun
 * @date 2019/3/27 14:12
 */
public class MonitorInfoReqVo {
    private Integer pageNum;
    private Integer pageSize = 10;
    private String token;
    private Monitor monitor;

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

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }
}
