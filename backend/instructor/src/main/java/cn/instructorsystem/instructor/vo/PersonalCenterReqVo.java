package cn.instructorsystem.instructor.vo;

import cn.instructorsystem.instructor.model.Instructor;

public class PersonalCenterReqVo {
    private String token;
    private Instructor instructor;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
