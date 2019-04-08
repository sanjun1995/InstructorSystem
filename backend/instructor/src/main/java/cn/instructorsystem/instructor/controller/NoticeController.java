package cn.instructorsystem.instructor.controller;

import cn.instructorsystem.instructor.model.Notice;
import cn.instructorsystem.instructor.model.res.ResResult;
import cn.instructorsystem.instructor.service.NoticeService;
import cn.instructorsystem.instructor.util.ResponseCode;
import cn.instructorsystem.instructor.vo.NoticeInfoReqVo;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sanjun
 * @date 2019/3/5 11:26
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    private static Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

    @PostMapping(value = "/insertNoticeInfo")
    public ResResult<Notice> insertNoticeInfo(@RequestBody NoticeInfoReqVo vo) {
        int n = noticeService.insertNoticeInfo(vo);
        ResResult<Notice> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (n != 0) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("insertNoticeInfo success!");
            logger.info("notice.insertNoticeInfo() NoticeInfoReqVo: {}，插入公告信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("insertNoticeInfo failure!");
            logger.info("notice.insertNoticeInfo() NoticeInfoReqVo: {}，插入通报信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getNoticeInfosByInsAccount")
    public ResResult<Notice> getNoticeInfosByInsAccount(@RequestBody NoticeInfoReqVo vo) {
        List<Notice> notices = noticeService.getNoticeInfosByInsAccount(vo);
        ResResult<Notice> res = new ResResult<>();
        if (notices != null || notices.size() != 0) {
            res.setData(notices);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("query notice information success!");
            logger.info("notice.getNoticeInfosByInsAccount() NoticeInfoReqVo: {}，获取公告信息成功！", JSON.toJSONString(vo));
        } else {
            res.setData(notices);
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("query notice information failure!");
            logger.info("notice.getNoticeInfosByInsAccount() NoticeInfoReqVo: {}，获取公告信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/deleteNoticeInfo")
    public ResResult<Notice> deleteNoticeInfo(@RequestBody NoticeInfoReqVo vo) {
        int n = noticeService.deleteNoticeInfo(vo);
        ResResult<Notice> res = new ResResult<>();
        res.setToken(vo.getToken());
        if (n != 0) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("deleteNoticeInfo success!");
            logger.info("notice.deleteNoticeInfo() NoticeInfoReqVo: {}，插入公告信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("deleteNoticeInfo failure!");
            logger.info("notice.deleteNoticeInfo() NoticeInfoReqVo: {}，插入公告信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/updateNoticeInfo")
    public ResResult<Notice> updateNoticeInfo(@RequestBody NoticeInfoReqVo vo) {
        int n = noticeService.updateNoticeInfo(vo);
        ResResult<Notice> res = new ResResult<>();
        if (n != 0) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("updateNoticeInfo success!");
            logger.info("notice.updateNoticeInfo() NoticeInfoReqVo: {}，更新公告信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("updateAchievementInfo failure!");
            logger.info("notice.updateNoticeInfo() NoticeInfoReqVo: {}，更新公告信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getLastNotice")
    public ResResult<Notice> getLastNotice(@RequestBody NoticeInfoReqVo vo) {
        List<Notice> notices = noticeService.getLastNotice(vo);
        ResResult<Notice> res = new ResResult<>();
        if (notices != null || notices.size() != 0) {
            res.setData(notices);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("getLastNotice success!");
            logger.info("getLastNotice NoticeInfoReqVo: {}，获取公告信息成功！", JSON.toJSONString(vo));
        } else {
            res.setData(notices);
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("getLastNotice failure!");
            logger.info("getLastNotice NoticeInfoReqVo: {}，获取公告信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/getLastAppointmentNotice")
    public ResResult<Notice> getLastAppointmentNotice(@RequestBody NoticeInfoReqVo vo) {
        List<Notice> notices = noticeService.getLastAppointmentNotice(vo);
        ResResult<Notice> res = new ResResult<>();
        if (notices != null || notices.size() != 0) {
            res.setData(notices);
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("getLastAppointmentNotice success!");
            logger.info("getLastAppointmentNoticeNoticeInfoReqVo: {}，获取公告信息成功！", JSON.toJSONString(vo));
        } else {
            res.setData(notices);
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("getLastAppointmentNotice failure!");
            logger.info("getLastAppointmentNotice NoticeInfoReqVo: {}，获取公告信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }
}
