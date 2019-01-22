package cn.instructorsystem.instructor.model;

public class InstructorStudent {
    private Integer id;

    private String insAccount;

    private String stuAccount;

    public InstructorStudent(Integer id, String insAccount, String stuAccount) {
        this.id = id;
        this.insAccount = insAccount;
        this.stuAccount = stuAccount;
    }

    public InstructorStudent() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInsAccount() {
        return insAccount;
    }

    public void setInsAccount(String insAccount) {
        this.insAccount = insAccount == null ? null : insAccount.trim();
    }

    public String getStuAccount() {
        return stuAccount;
    }

    public void setStuAccount(String stuAccount) {
        this.stuAccount = stuAccount == null ? null : stuAccount.trim();
    }
}