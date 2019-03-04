package cn.instructorsystem.sysmanager.model;

public class SysManager {
    private Integer id;

    private String account;

    private String password;

    private String sysName;

    public SysManager(Integer id, String account, String password, String sysName) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.sysName = sysName;
    }

    public SysManager() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName == null ? null : sysName.trim();
    }
}