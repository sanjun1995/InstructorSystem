package cn.instructorsystem.student.model;

import java.util.Date;

public class Leave {
    private Integer id;

    private String account;

    private String stuName;

    private String leaveType;

    private Date startTime;

    private Date endTime;

    private String reason;

    private Integer status;

    private String attachment;

    private String duration;

    private String insAccount;

    private String orderNumber;

    private String rejectReason;

    private Date operationTime;

    public Leave(Integer id, String account, String stuName, String leaveType, Date startTime, Date endTime, String reason, Integer status, String attachment, String duration, String insAccount, String orderNumber, String rejectReason, Date operationTime) {
        this.id = id;
        this.account = account;
        this.stuName = stuName;
        this.leaveType = leaveType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.status = status;
        this.attachment = attachment;
        this.duration = duration;
        this.insAccount = insAccount;
        this.orderNumber = orderNumber;
        this.rejectReason = rejectReason;
        this.operationTime = operationTime;
    }

    public Leave() {
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

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType == null ? null : leaveType.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    public String getInsAccount() {
        return insAccount;
    }

    public void setInsAccount(String insAccount) {
        this.insAccount = insAccount == null ? null : insAccount.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason == null ? null : rejectReason.trim();
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }
}