package cn.instructorsystem.instructor.service;

import cn.instructorsystem.instructor.util.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface NotificationService {
    void listen(ConsumerRecord<?, ?> record);

    void insertNotificationInfo(Message message);


}
