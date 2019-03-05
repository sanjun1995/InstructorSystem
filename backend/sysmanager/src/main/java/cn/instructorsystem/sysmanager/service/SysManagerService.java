package cn.instructorsystem.sysmanager.service;


import cn.instructorsystem.sysmanager.model.SysManager;
import cn.instructorsystem.sysmanager.vo.ChangePasswordReqVo;
import cn.instructorsystem.sysmanager.vo.PersonalCenterReqVo;

/**
 * @author sanjun
 * @date 2018/11/25 17:17
 */
public interface SysManagerService {
    SysManager login(SysManager sysManager);

    boolean changePassword(ChangePasswordReqVo changePasswordReqVo);

    SysManager getPersonalInfo(PersonalCenterReqVo personalCenterReqVo);
}
