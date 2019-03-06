package cn.instructorsystem.instructor.vo;

import cn.instructorsystem.instructor.model.Instructor;

/**
 * @author sanjun
 * @date 2019/3/6 14:47
 */
public class InstructorInfoReqVo {
    private Integer pageNum;
    private Integer pageSize = 10;
    private String token;
    private Instructor instructor;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

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
