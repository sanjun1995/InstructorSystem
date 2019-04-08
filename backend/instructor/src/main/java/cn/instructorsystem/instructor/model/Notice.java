package cn.instructorsystem.instructor.model;

import java.util.Date;

public class Notice {
    private Integer id;

    private String insAccount;

    private Date noticeTime;

    private Integer noticeType;

    private String noticeContent;

    public Notice(Integer id, String insAccount, Date noticeTime, Integer noticeType, String noticeContent) {
        this.id = id;
        this.insAccount = insAccount;
        this.noticeTime = noticeTime;
        this.noticeType = noticeType;
        this.noticeContent = noticeContent;
    }

    public Notice() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInsAccount() {
        return insAccount;
    }

    public void setInsAccount(String insAccount) {
        this.insAccount = insAccount == null ? null : insAccount.trim();
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public Integer getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
    }
}