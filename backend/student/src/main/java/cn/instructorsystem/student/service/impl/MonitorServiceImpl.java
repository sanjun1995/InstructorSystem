package cn.instructorsystem.student.service.impl;

import cn.instructorsystem.student.dao.MonitorMapper;
import cn.instructorsystem.student.model.Monitor;
import cn.instructorsystem.student.model.MonitorExample;
import cn.instructorsystem.student.service.MonitorService;
import cn.instructorsystem.student.vo.MonitorInfoReqVo;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author sanjun
 * @date 2019/3/27 14:14
 */
@Service
public class MonitorServiceImpl implements MonitorService {

    private static Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);

    @Autowired
    private MonitorMapper monitorMapper;

    @Override
    public int insertMonitorInfo(MonitorInfoReqVo vo) {
        Monitor monitor = vo.getMonitor();
        int n = monitorMapper.insertSelective(monitor);
        return n;
    }

    @Override
    public List<Monitor> getMonitorInfosByInsAccount(MonitorInfoReqVo vo) {
        Integer pageNum = vo.getPageNum();
        Integer pageSize = vo.getPageSize();
        Monitor monitor = vo.getMonitor();
        String insAccount = monitor.getInsAccount();

        PageHelper.startPage(pageNum, pageSize);
        MonitorExample example = new MonitorExample();
        MonitorExample.Criteria criteria = example.createCriteria();
        if (insAccount != null && !"".equals(insAccount)) {
            criteria.andInsAccountEqualTo(monitor.getInsAccount());
        }
        List<Monitor> monitors = monitorMapper.selectByExample(example);
        return monitors;
    }

    @Override
    public int deleteMonitorInfo(MonitorInfoReqVo vo) {
        Integer id = vo.getMonitor().getId();
        int n = monitorMapper.deleteByPrimaryKey(id);
        return n;
    }

    @Override
    public int updateMonitorInfo(MonitorInfoReqVo vo) {
        Monitor monitor = vo.getMonitor();
        Integer id = monitor.getId();
        MonitorExample example = new MonitorExample();
        MonitorExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        int n = monitorMapper.updateByExampleSelective(monitor, example);
        return n;
    }

}
