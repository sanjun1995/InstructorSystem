package cn.instructorsystem.instructor.model;

import java.util.Date;

public class Punishment {
    private Integer id;

    private String account;

    private String stuName;

    private String punishmentReason;

    private Date punishmentTime;

    private String insAccount;

    public Punishment(Integer id, String account, String stuName, String punishmentReason, Date punishmentTime, String insAccount) {
        this.id = id;
        this.account = account;
        this.stuName = stuName;
        this.punishmentReason = punishmentReason;
        this.punishmentTime = punishmentTime;
        this.insAccount = insAccount;
    }

    public Punishment() {
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

    public String getPunishmentReason() {
        return punishmentReason;
    }

    public void setPunishmentReason(String punishmentReason) {
        this.punishmentReason = punishmentReason == null ? null : punishmentReason.trim();
    }

    public Date getPunishmentTime() {
        return punishmentTime;
    }

    public void setPunishmentTime(Date punishmentTime) {
        this.punishmentTime = punishmentTime;
    }

    public String getInsAccount() {
        return insAccount;
    }

    public void setInsAccount(String insAccount) {
        this.insAccount = insAccount == null ? null : insAccount.trim();
    }
}