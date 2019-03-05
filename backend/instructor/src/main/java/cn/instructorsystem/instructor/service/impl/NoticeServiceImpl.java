package cn.instructorsystem.instructor.service.impl;

import cn.instructorsystem.instructor.dao.NoticeMapper;
import cn.instructorsystem.instructor.model.*;
import cn.instructorsystem.instructor.service.NoticeService;
import cn.instructorsystem.instructor.vo.NoticeInfoReqVo;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author sanjun
 * @date 2019/3/5 11:00
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    private static Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public int insertNoticeInfo(NoticeInfoReqVo vo) {
        Notice notice = vo.getNotice();
        notice.setNoticeTime(new Date());
        int n = noticeMapper.insertSelective(notice);
        return n;
    }

    @Override
    public List<Notice> getNoticeInfosByInsAccount(NoticeInfoReqVo vo) {
        Integer pageNum = vo.getPageNum();
        Integer pageSize = vo.getPageSize();
        Notice notice = vo.getNotice();

        PageHelper.startPage(pageNum, pageSize);
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();
        criteria.andInsAccountEqualTo(notice.getInsAccount());
        List<Notice> notices = noticeMapper.selectByExample(example);
        return notices;
    }

    @Override
    public int deleteNoticeInfo(NoticeInfoReqVo vo) {
        Integer id = vo.getNotice().getId();
        int n = noticeMapper.deleteByPrimaryKey(id);
        return n;
    }

    @Override
    public int updateNoticeInfo(NoticeInfoReqVo vo) {
        Notice notice = vo.getNotice();
        notice.setNoticeTime(new Date());
        Integer id = notice.getId();
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        int n = noticeMapper.updateByExampleSelective(notice, example);
        return n;
    }
}
