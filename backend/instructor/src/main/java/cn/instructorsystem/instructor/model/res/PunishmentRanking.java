package cn.instructorsystem.instructor.model.res;

/**
 * @author sanjun
 * @date 2019/3/4 10:03
 */
public class PunishmentRanking {
    private String account;
    private String stuName;
    private String punishmentTimes;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getPunishmentTimes() {
        return punishmentTimes;
    }

    public void setPunishmentTimes(String punishmentTimes) {
        this.punishmentTimes = punishmentTimes;
    }
}
