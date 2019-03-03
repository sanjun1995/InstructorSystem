package cn.instructorsystem.instructor.service.impl;

import cn.instructorsystem.instructor.dao.NotificationMapper;
import cn.instructorsystem.instructor.model.Notification;
import cn.instructorsystem.instructor.model.NotificationExample;
import cn.instructorsystem.instructor.service.NotificationService;
import cn.instructorsystem.instructor.util.Message;
import cn.instructorsystem.instructor.util.WebSocketServer;
import cn.instructorsystem.instructor.vo.NotificationReqVo;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author sanjun
 * @date 2019/1/24 11:11
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    private static Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Autowired
    private NotificationMapper notificationMapper;

    private Gson gson = new GsonBuilder().create();

    @Override
    @KafkaListener(topics = {"notification"})
    public void listen(ConsumerRecord<?, ?> record) throws IOException {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            Message message = gson.fromJson((String)kafkaMessage.get(), Message.class);
            this.insertNotificationInfo(message);
            logger.info("listen() recored = {}" , record);
        }
    }

    @Override
    public void insertNotificationInfo(Message message) throws IOException {
        Notification notification = new Notification();
        notification.setAccount(message.getAccount());
        notification.setStuName(message.getStuName());
        notification.setIsRead(message.getIsRead());
        notification.setNewsType(message.getNewsType());
        notification.setInsAccount(message.getInsAccount());
        notification.setOrderNumber(message.getOrderNumber());
        int n = notificationMapper.insertSelective(notification);
        if (n != 0) {
            logger.info("insertNotificationInfo success! message = {}", message.toString());
            WebSocketServer.sendInfo("有新消息！");
        } else {
            logger.error("insertNotificationInfo failure! message = {}", message.toString());
        }
    }

    @Override
    public List<Notification> getNotificationsByPage(NotificationReqVo vo) {
        Integer pageNum = vo.getPageNum();
        Integer pageSize = vo.getPageSize();
        Notification notification = vo.getNotification();
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        NotificationExample example = new NotificationExample();
        NotificationExample.Criteria criteria = example.createCriteria();
        criteria.andIsReadEqualTo(notification.getIsRead());
        List<Notification> notifications = notificationMapper.selectByExample(example);
        return notifications;
    }

    @Override
    public boolean updateNotificationInfo(NotificationReqVo vo) {
        Integer isRead = vo.getNotification().getIsRead();
        String orderNumber = vo.getNotification().getOrderNumber();
        Notification notification = new Notification();
        notification.setIsRead(isRead);
        NotificationExample example = new NotificationExample();
        NotificationExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNumberEqualTo(orderNumber);
        int n = notificationMapper.updateByExampleSelective(notification, example);
        if (n != 0) {
            return true;
        }
        return false;
    }
}
