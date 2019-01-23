package cn.instructorsystem.instructor.model;

public class Notification {
    private Integer id;

    private Integer account;

    private String stuName;

    private String newsType;

    private String isRead;

    public Notification(Integer id, Integer account, String stuName, String newsType, String isRead) {
        this.id = id;
        this.account = account;
        this.stuName = stuName;
        this.newsType = newsType;
        this.isRead = isRead;
    }

    public Notification() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType == null ? null : newsType.trim();
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead == null ? null : isRead.trim();
    }
}