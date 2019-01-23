package cn.instructorsystem.student.service.impl;

import cn.instructorsystem.student.dao.LeaveMapper;
import cn.instructorsystem.student.dao.StudentMapper;
import cn.instructorsystem.student.model.Leave;
import cn.instructorsystem.student.model.LeaveExample;
import cn.instructorsystem.student.service.LeaveService;
import cn.instructorsystem.student.vo.LeaveInfoReqVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

/**
 * @author sanjun
 * @date 2019/1/22 14:58
 */
@Service
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int insertLeaveInfo(LeaveInfoReqVo vo) {
        Leave leave = vo.getLeave();
        Date startTime = leave.getStartTime();
        Date endTime = leave.getEndTime();
        long t1 = startTime.getTime();
        long t2 = endTime.getTime();
        int days = (int)((t2 - t1) / (1000 * 60 * 60 * 24));
        if (days == 0) {
            days = 1;
        }
        leave.setDuration(Integer.toString(days) + "天");
        int n = leaveMapper.insertSelective(leave);
        return n;
    }

    @Override
    public List<Leave> getLeaveInfosByPage(LeaveInfoReqVo vo) {
        Integer pageNum = vo.getPageNum();
        Integer pageSize = vo.getPageSize();
        Leave leave = vo.getLeave();
        String account = leave.getAccount();

        PageHelper.startPage(pageNum, pageSize);
        LeaveExample example = new LeaveExample();
        LeaveExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        List<Leave> leaves = leaveMapper.selectByExample(example);
        return leaves;
    }
}
