package cn.instructorsystem.instructor.model.res;

/**
 * @author sanjun
 * @date 2018/11/25 15:02
 */
public class ResponseResult<T> {
    private int code;
    private String msg;
    private String token;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
