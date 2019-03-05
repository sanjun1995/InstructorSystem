package cn.instructorsystem.instructor.service;

import cn.instructorsystem.instructor.model.Notice;
import cn.instructorsystem.instructor.model.Punishment;
import cn.instructorsystem.instructor.vo.NoticeInfoReqVo;

import java.util.List;

public interface NoticeService {
    int insertNoticeInfo(NoticeInfoReqVo vo);

    List<Notice> getNoticeInfosByInsAccount(NoticeInfoReqVo vo);

    int deleteNoticeInfo(NoticeInfoReqVo vo);

    int updateNoticeInfo(NoticeInfoReqVo vo);
}
