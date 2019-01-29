package cn.instructorsystem.instructor.model;

public class Notification {
    private Integer id;

    private String account;

    private String stuName;

    private Integer newsType;

    private Integer isRead;

    private String insAccount;

    private String orderNumber;

    public Notification(Integer id, String account, String stuName, Integer newsType, Integer isRead, String insAccount, String orderNumber) {
        this.id = id;
        this.account = account;
        this.stuName = stuName;
        this.newsType = newsType;
        this.isRead = isRead;
        this.insAccount = insAccount;
        this.orderNumber = orderNumber;
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

    public Integer getNewsType() {
        return newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
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
}