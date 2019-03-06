package cn.instructorsystem.student.vo;

import cn.instructorsystem.student.model.Student;

/**
 * @author sanjun
 * @date 2019/3/6 14:42
 */
public class StudentInfoReqVo {
    private Integer pageNum;
    private Integer pageSize = 10;
    private String token;
    private Student student;

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
