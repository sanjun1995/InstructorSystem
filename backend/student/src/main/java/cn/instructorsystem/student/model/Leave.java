package cn.instructorsystem.student.model;

import java.util.Date;

public class Leave {
    private Integer id;

    private String account;

    private String insName;

    private String type;

    private Date startTime;

    private Date endTime;

    private String cause;

    private Integer state;

    public Leave(Integer id, String account, String insName, String type, Date startTime, Date endTime, String cause, Integer state) {
        this.id = id;
        this.account = account;
        this.insName = insName;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cause = cause;
        this.state = state;
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

    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName == null ? null : insName.trim();
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

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}