package cn.instructorsystem.student.model.res;

/**
 * @author sanjun
 * @date 2019/3/3 14:30
 */
public class LeaveRanking {
    private String account;
    private String stuName;
    private String leaveTimes;

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

    public String getLeaveTimes() {
        return leaveTimes;
    }

    public void setLeaveTimes(String leaveTimes) {
        this.leaveTimes = leaveTimes;
    }
}
