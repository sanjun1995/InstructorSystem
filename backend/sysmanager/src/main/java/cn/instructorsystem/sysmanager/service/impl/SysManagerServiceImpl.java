package cn.instructorsystem.sysmanager.service.impl;

import cn.instructorsystem.sysmanager.dao.SysManagerMapper;
import cn.instructorsystem.sysmanager.model.SysManager;
import cn.instructorsystem.sysmanager.model.SysManagerExample;
import cn.instructorsystem.sysmanager.service.SysManagerService;
import cn.instructorsystem.sysmanager.util.TokenUtil;
import cn.instructorsystem.sysmanager.vo.ChangePasswordReqVo;
import cn.instructorsystem.sysmanager.vo.PersonalCenterReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sanjun
 * @date 2018/11/25 17:18
 */
@Service
public class SysManagerServiceImpl implements SysManagerService {

    @Autowired
    private SysManagerMapper sysManagerMapper;

    @Override
    public SysManager login(SysManager sysManager) {
        SysManagerExample example = new SysManagerExample();
        SysManagerExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(sysManager.getAccount());
        criteria.andPasswordEqualTo(sysManager.getPassword());
        List<SysManager> list = sysManagerMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean changePassword(ChangePasswordReqVo vo) {
        String token = vo.getToken();
        String originPass = vo.getOriginPass();
        String newPass = vo.getNewPass();
        // 通过token获取登录账号
        String account = TokenUtil.getContent(token);
        SysManagerExample example = new SysManagerExample();
        SysManagerExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        criteria.andPasswordEqualTo(originPass);
        List<SysManager> sysManagers = sysManagerMapper.selectByExample(example);
        if (sysManagers.size() != 0) {
            // 清空条件
            example.clear();
            criteria = example.createCriteria();
            SysManager sysManager = new SysManager();
            sysManager.setPassword(newPass);
            criteria.andAccountEqualTo(account);
            int n = sysManagerMapper.updateByExampleSelective(sysManager, example);
            if (n != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public SysManager getPersonalInfo(PersonalCenterReqVo personalCenterReqVo) {
        String token = personalCenterReqVo.getToken();
        String account = TokenUtil.getContent(token);
        SysManagerExample example = new SysManagerExample();
        SysManagerExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        List<SysManager> sysManagers = sysManagerMapper.selectByExample(example);
        if (sysManagers.size() != 0) {
            return sysManagers.get(0);
        }
        return null;
    }
}
