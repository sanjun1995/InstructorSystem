package cn.instructorsystem.instructor.model;

import java.util.Date;

public class Notice {
    private Integer id;

    private String insAccount;

    private String noticeContent;

    private Date noticeTime;

    public Notice(Integer id, String insAccount, String noticeContent, Date noticeTime) {
        this.id = id;
        this.insAccount = insAccount;
        this.noticeContent = noticeContent;
        this.noticeTime = noticeTime;
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

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }
}