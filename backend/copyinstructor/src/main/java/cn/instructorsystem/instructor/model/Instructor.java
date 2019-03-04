package cn.instructorsystem.instructor.model;

public class Instructor {
    private Integer id;

    private String account;

    private String password;

    private String insName;

    private Integer sex;

    private String phoneNum;

    private String officeLocation;

    private String headPath;

    public Instructor(Integer id, String account, String password, String insName, Integer sex, String phoneNum, String officeLocation, String headPath) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.insName = insName;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.officeLocation = officeLocation;
        this.headPath = headPath;
    }

    public Instructor() {
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

    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName == null ? null : insName.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation == null ? null : officeLocation.trim();
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath == null ? null : headPath.trim();
    }
}