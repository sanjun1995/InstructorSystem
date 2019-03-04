package cn.instructorsystem.instructor.model.res;

import java.util.List;

/**
 * @author sanjun
 * @date 2018/11/25 15:02
 */
public class ResResult<T> {
    private int code;
    private String msg;
    private String token;
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
