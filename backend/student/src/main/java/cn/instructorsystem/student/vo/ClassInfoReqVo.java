package cn.instructorsystem.student.vo;

import cn.instructorsystem.student.model.Student;

/**
 * @Author: sanjun
 * @Date: 2019/1/1 23:55
 */
public class ClassInfoReqVo {
    private Integer pageNum;
    private Integer pageSize = 10;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
