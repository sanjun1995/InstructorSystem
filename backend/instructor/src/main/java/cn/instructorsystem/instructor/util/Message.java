package cn.instructorsystem.instructor.util;

import java.io.Serializable;

/**
 * @author sanjun
 * @date 2019/1/24 10:48
 */
public class Message implements Serializable {
    private Integer id;

    private String account;

    private String stuName;

    private Integer newsType;

    private Integer isRead;

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
        this.account = account;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
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

    @Override
    public String toString() {
        return "Message{" +
                ", account='" + account + '\'' +
                ", stuName='" + stuName + '\'' +
                ", newsType='" + newsType + '\'' +
                ", isRead='" + isRead + '\'' +
                '}';
    }
}
