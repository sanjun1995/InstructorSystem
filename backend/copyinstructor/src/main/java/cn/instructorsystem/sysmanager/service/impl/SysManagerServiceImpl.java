package cn.instructorsystem.sysmanager.service.impl;

import cn.instructorsystem.sysmanager.dao.SysManagerMapper;
import cn.instructorsystem.sysmanager.model.SysManager;
import cn.instructorsystem.sysmanager.model.SysManagerExample;
import cn.instructorsystem.sysmanager.service.SysManagerService;
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
    public boolean login(SysManager sysManager) {
        SysManagerExample example = new SysManagerExample();
        SysManagerExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(sysManager.getAccount());
        criteria.andPasswordEqualTo(sysManager.getPassword());
        List<SysManager> list = sysManagerMapper.selectByExample(example);
        if (list.size() > 0) {
            return true;
        }
        return false;
    }
}
