package cn.instructorsystem.student.model;

import java.util.Date;

public class Appointment {
    private Integer id;

    private String account;

    private String stuName;

    private String type;

    private Date startTime;

    private Date endTime;

    private String reason;

    private Integer number;

    private Integer state;

    private Date operateTime;

    private String rejectReason;

    private String orderNumber;

    private String insAccount;

    public Appointment(Integer id, String account, String stuName, String type, Date startTime, Date endTime, String reason, Integer number, Integer state, Date operateTime, String rejectReason, String orderNumber, String insAccount) {
        this.id = id;
        this.account = account;
        this.stuName = stuName;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.number = number;
        this.state = state;
        this.operateTime = operateTime;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
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