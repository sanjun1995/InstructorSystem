package cn.instructorsystem.instructor.vo;

import cn.instructorsystem.instructor.model.Notification;

/**
 * @author sanjun
 * @date 2019/1/28 9:11
 */
public class NotificationReqVo {
    private Integer pageNum;
    private Integer pageSize = 10;
    private Notification notification;

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

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
