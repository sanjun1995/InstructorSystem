package cn.instructorsystem.sysmanager.vo;

import cn.instructorsystem.sysmanager.model.SysManager;

public class PersonalCenterReqVo {
    private String token;
    private SysManager sysManager;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SysManager getSysManager() {
        return sysManager;
    }

    public void setSysManager(SysManager sysManager) {
        this.sysManager = sysManager;
    }
}
