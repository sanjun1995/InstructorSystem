package cn.instructorsystem.instructor.model;

public class Achievement {
    private Integer id;

    private String account;

    private String stuName;

    private String score1;

    private String score2;

    private String score3;

    private String comprehensive1;

    private String comprehensive2;

    private String comprehensive3;

    private String insAccount;

    private String score1Rank;

    private String score2Rank;

    private String score3Rank;

    private String comprehensive1Rank;

    private String comprehensive2Rank;

    private String comprehensive3Rank;

    public Achievement(Integer id, String account, String stuName, String score1, String score2, String score3, String comprehensive1, String comprehensive2, String comprehensive3, String insAccount, String score1Rank, String score2Rank, String score3Rank, String comprehensive1Rank, String comprehensive2Rank, String comprehensive3Rank) {
        this.id = id;
        this.account = account;
        this.stuName = stuName;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.comprehensive1 = comprehensive1;
        this.comprehensive2 = comprehensive2;
        this.comprehensive3 = comprehensive3;
        this.insAccount = insAccount;
        this.score1Rank = score1Rank;
        this.score2Rank = score2Rank;
        this.score3Rank = score3Rank;
        this.comprehensive1Rank = comprehensive1Rank;
        this.comprehensive2Rank = comprehensive2Rank;
        this.comprehensive3Rank = comprehensive3Rank;
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

    public String getInsAccount() {
        return insAccount;
    }

    public void setInsAccount(String insAccount) {
        this.insAccount = insAccount == null ? null : insAccount.trim();
    }

    public String getScore1Rank() {
        return score1Rank;
    }

    public void setScore1Rank(String score1Rank) {
        this.score1Rank = score1Rank == null ? null : score1Rank.trim();
    }

    public String getScore2Rank() {
        return score2Rank;
    }

    public void setScore2Rank(String score2Rank) {
        this.score2Rank = score2Rank == null ? null : score2Rank.trim();
    }

    public String getScore3Rank() {
        return score3Rank;
    }

    public void setScore3Rank(String score3Rank) {
        this.score3Rank = score3Rank == null ? null : score3Rank.trim();
    }

    public String getComprehensive1Rank() {
        return comprehensive1Rank;
    }

    public void setComprehensive1Rank(String comprehensive1Rank) {
        this.comprehensive1Rank = comprehensive1Rank == null ? null : comprehensive1Rank.trim();
    }

    public String getComprehensive2Rank() {
        return comprehensive2Rank;
    }

    public void setComprehensive2Rank(String comprehensive2Rank) {
        this.comprehensive2Rank = comprehensive2Rank == null ? null : comprehensive2Rank.trim();
    }

    public String getComprehensive3Rank() {
        return comprehensive3Rank;
    }

    public void setComprehensive3Rank(String comprehensive3Rank) {
        this.comprehensive3Rank = comprehensive3Rank == null ? null : comprehensive3Rank.trim();
    }
}