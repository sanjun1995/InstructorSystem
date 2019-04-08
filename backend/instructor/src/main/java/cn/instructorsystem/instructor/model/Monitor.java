package cn.instructorsystem.instructor.model;

public class Monitor {
    private Integer id;

    private String name;

    private String account;

    private String phoneNum;

    private String insAccount;

    public Monitor(Integer id, String name, String account, String phoneNum, String insAccount) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.phoneNum = phoneNum;
        this.insAccount = insAccount;
    }

    public Monitor() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getInsAccount() {
        return insAccount;
    }

    public void setInsAccount(String insAccount) {
        this.insAccount = insAccount == null ? null : insAccount.trim();
    }
}