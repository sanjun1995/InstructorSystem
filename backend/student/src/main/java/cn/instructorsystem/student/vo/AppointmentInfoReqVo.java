package cn.instructorsystem.student.vo;

import cn.instructorsystem.student.model.Appointment;

/**
 * @author sanjun
 * @date 2019/3/3 20:02
 */
public class AppointmentInfoReqVo {
    private Integer pageNum;
    private Integer pageSize = 10;
    private String phoneNum;
    private Appointment appointment;

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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
