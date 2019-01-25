package cn.instructorsystem.instructor.service.impl;

import cn.instructorsystem.instructor.dao.NotificationMapper;
import cn.instructorsystem.instructor.model.Notification;
import cn.instructorsystem.instructor.service.NotificationService;
import cn.instructorsystem.instructor.util.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

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
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            Message message = gson.fromJson((String)kafkaMessage.get(), Message.class);
            this.insertNotificationInfo(message);
            logger.info("listen() recored = {}" , record);
        }
    }

    @Override
    public void insertNotificationInfo(Message message) {
        Notification notification = new Notification();
        notification.setAccount(message.getAccount());
        notification.setStuName(message.getStuName());
        notification.setIsRead(message.getIsRead());
        notification.setNewsType(message.getNewsType());
        int n = notificationMapper.insertSelective(notification);
        if (n != 0) {
            logger.info("insertNotificationInfo success! message = {}", message.toString());
        } else {
            logger.error("insertNotificationInfo failure! message = {}", message.toString());
        }
    }
}
