package cn.instructorsystem.instructor.controller;

import cn.instructorsystem.instructor.model.Notification;
import cn.instructorsystem.instructor.model.res.ResResult;
import cn.instructorsystem.instructor.service.NotificationService;
import cn.instructorsystem.instructor.util.ResponseCode;
import cn.instructorsystem.instructor.vo.NotificationReqVo;
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
 * @date 2019/1/28 9:20
 */
@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private static Logger logger = LoggerFactory.getLogger(InstructorController.class);

    @Autowired
    private NotificationService notificationService;

    @PostMapping(value = "/getNotificationsByPage")
    public ResResult<Notification> getNotificationsByPage(@RequestBody NotificationReqVo vo) {
        List<Notification> notifications = notificationService.getNotificationsByPage(vo);
        int size = notifications.size();
        ResResult<Notification> res = new ResResult<>();
        res.setData(notifications);
        if (notifications != null) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("getNotificationsByIsRead success!");
            logger.info("notification.getNotificationsByIsRead() NotificationReqVo: {}，查询消息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("getNotificationsByIsRead failure!");
            logger.info("notification.getNotificationsByIsRead() NotificationReqVo: {}，查询消息失败！", JSON.toJSONString(vo));
        }
        return res;
    }

    @PostMapping(value = "/updateNotificationInfo")
    public ResResult<Notification> updateNotificationInfo(@RequestBody NotificationReqVo vo) {
        boolean isUpdate = notificationService.updateNotificationInfo(vo);
        ResResult<Notification> res = new ResResult<>();
        if (isUpdate) {
            res.setCode(ResponseCode.SUCCESS);
            res.setMsg("updateNotificationInfo success!");
            logger.info("notification.updateNotificationInfo() NotificationReqVo: {}，更新消息信息成功！", JSON.toJSONString(vo));
        } else {
            res.setCode(ResponseCode.FAILURE);
            res.setMsg("updateNotificationInfo failure!");
            logger.info("notification.updateNotificationInfo() NotificationReqVo: {}，更新消息信息失败！", JSON.toJSONString(vo));
        }
        return res;
    }


}
