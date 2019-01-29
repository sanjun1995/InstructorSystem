package cn.instructorsystem.instructor.service;

import cn.instructorsystem.instructor.model.Notification;
import cn.instructorsystem.instructor.util.Message;
import cn.instructorsystem.instructor.vo.NotificationReqVo;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;
import java.util.List;

public interface NotificationService {
    void listen(ConsumerRecord<?, ?> record) throws IOException;

    void insertNotificationInfo(Message message) throws IOException;

    List<Notification> getNotificationsByPage(NotificationReqVo vo);
}
