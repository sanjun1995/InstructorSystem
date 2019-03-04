package cn.instructorsystem.instructor.vo;

/**
 * @Author: sanjun
 * @Date: 2019/1/4 9:37
 */
public class ChangePasswordReqVo {
    private String token;
    private String originPass;
    private String newPass;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOriginPass() {
        return originPass;
    }

    public void setOriginPass(String originPass) {
        this.originPass = originPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
}
