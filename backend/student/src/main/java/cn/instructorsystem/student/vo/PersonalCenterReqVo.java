package cn.instructorsystem.student.vo;

import cn.instructorsystem.student.model.Student;

public class PersonalCenterReqVo {
    private String token;
    private Student student;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
