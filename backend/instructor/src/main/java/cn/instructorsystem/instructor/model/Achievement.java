package cn.instructorsystem.instructor.model;

public class Achievement {
    private Integer id;

    private String account;

    private String stuName;

    private String score1;

    private String score2;

    private String score3;

    private String score4;

    private String comprehensive1;

    private String comprehensive2;

    private String comprehensive3;

    private String comprehensive4;

    private String insAccount;

    public Achievement(Integer id, String account, String stuName, String score1, String score2, String score3, String score4, String comprehensive1, String comprehensive2, String comprehensive3, String comprehensive4, String insAccount) {
        this.id = id;
        this.account = account;
        this.stuName = stuName;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
        this.comprehensive1 = comprehensive1;
        this.comprehensive2 = comprehensive2;
        this.comprehensive3 = comprehensive3;
        this.comprehensive4 = comprehensive4;
        this.insAccount = insAccount;
    }

    public Achievement() {
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

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1 == null ? null : score1.trim();
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2 == null ? null : score2.trim();
    }

    public String getScore3() {
        return score3;
    }

    public void setScore3(String score3) {
        this.score3 = score3 == null ? null : score3.trim();
    }

    public String getScore4() {
        return score4;
    }

    public void setScore4(String score4) {
        this.score4 = score4 == null ? null : score4.trim();
    }

    public String getComprehensive1() {
        return comprehensive1;
    }

    public void setComprehensive1(String comprehensive1) {
        this.comprehensive1 = comprehensive1 == null ? null : comprehensive1.trim();
    }

    public String getComprehensive2() {
        return comprehensive2;
    }

    public void setComprehensive2(String comprehensive2) {
        this.comprehensive2 = comprehensive2 == null ? null : comprehensive2.trim();
    }

    public String getComprehensive3() {
        return comprehensive3;
    }

    public void setComprehensive3(String comprehensive3) {
        this.comprehensive3 = comprehensive3 == null ? null : comprehensive3.trim();
    }

    public String getComprehensive4() {
        return comprehensive4;
    }

    public void setComprehensive4(String comprehensive4) {
        this.comprehensive4 = comprehensive4 == null ? null : comprehensive4.trim();
    }

    public String getInsAccount() {
        return insAccount;
    }

    public void setInsAccount(String insAccount) {
        this.insAccount = insAccount == null ? null : insAccount.trim();
    }
}