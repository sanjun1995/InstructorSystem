package cn.instructorsystem.student.model;

import java.util.Date;

public class Appointment {
    private Integer id;

    private String account;

    private String stuName;

    private String appointmentType;

    private Date startTime;

    private Date endTime;

    private String reason;

    private Integer appointmentNumber;

    private Integer status;

    private Date operationTime;

    private String rejectReason;

    private String orderNumber;

    private String insAccount;

    public Appointment(Integer id, String account, String stuName, String appointmentType, Date startTime, Date endTime, String reason, Integer appointmentNumber, Integer status, Date operationTime, String rejectReason, String orderNumber, String insAccount) {
        this.id = id;
        this.account = account;
        this.stuName = stuName;
        this.appointmentType = appointmentType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.appointmentNumber = appointmentNumber;
        this.status = status;
        this.operationTime = operationTime;
        this.rejectReason = rejectReason;
        this.orderNumber = orderNumber;
        this.insAccount = insAccount;
    }

    public Appointment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType == null ? null : appointmentType.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(Integer appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason == null ? null : rejectReason.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getInsAccount() {
        return insAccount;
    }

    public void setInsAccount(String insAccount) {
        this.insAccount = insAccount == null ? null : insAccount.trim();
    }
}