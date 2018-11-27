package cn.instructorsystem.sysmanager.model;

public class Student {
    private Integer id;

    private String account;

    private String password;

    private String stuName;

    private Integer sex;

    private String phoneNum;

    private String address;

    private String graduateSchool;

    public Student(Integer id, String account, String password, String stuName, Integer sex, String phoneNum, String address, String graduateSchool) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.stuName = stuName;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.address = address;
        this.graduateSchool = graduateSchool;
    }

    public Student() {
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

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool == null ? null : graduateSchool.trim();
    }
}