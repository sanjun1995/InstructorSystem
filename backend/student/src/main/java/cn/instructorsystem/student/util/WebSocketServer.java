package cn.instructorsystem.student.util;

import cn.instructorsystem.student.model.Appointment;
import cn.instructorsystem.student.model.Leave;
import cn.instructorsystem.student.service.AppointmentService;
import cn.instructorsystem.student.service.LeaveService;
import cn.instructorsystem.student.vo.AppointmentInfoReqVo;
import cn.instructorsystem.student.vo.LeaveInfoReqVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author sanjun
 * @date 2019/1/23 17:49
 */
@Controller
@ServerEndpoint("/websocket/{sid}")
public class WebSocketServer {
    private static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    // webSocket会生成多个对象，而spring的单例模式只会注入一次，所以用static
    private static LeaveService leaveService;

    // webSocket会生成多个对象，而spring的单例模式只会注入一次，所以用static
    private static AppointmentService appointmentService;

    @Autowired
    public void setLeaveService(LeaveService leaveService) {
        WebSocketServer.leaveService = leaveService;
    }

    @Autowired
    public void setAppointmentService(AppointmentService appointmentService) {
        WebSocketServer.appointmentService = appointmentService;
    }

    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    // concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    // 连接建立成功调用的方法
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        // 加入set中
        webSocketSet.add(this);
        // 在线数加1
        addOnlineCount();
        logger.info("有新连接加入！当前在线人数为:" + getOnlineCount());
    }

    // 连接关闭调用的方法
    @OnClose
    public void onClose() {
        // 从set中删除
        webSocketSet.remove(this);
        // 在线减1
        subOnlineCount();
        logger.info("有一连接关闭! 当前在线人数为:" + getOnlineCount());
    }

    // 收到客户端消息后调用的方法
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("来自客户端的消息:" + message);
        String[] arr = message.split("-");
        String newsType = arr[0];
        if ("leave".equals(newsType)) {
            handleLeaveInfo(arr);
        } else {
            handleAppoinmentInfo(arr);
        }
    }

    private void handleAppoinmentInfo(String[] arr) {
        String status = arr[1];
        String orderNumber = arr[2];
        AppointmentInfoReqVo vo = new AppointmentInfoReqVo();
        Appointment appointment = new Appointment();
        appointment.setOrderNumber(orderNumber);
        if ("agree".equals(status)) {
            appointment.setStatus(1);
        } else {
            appointment.setStatus(2);
            String rejectReason = arr[3];
            appointment.setRejectReason(rejectReason);
        }
        vo.setAppointment(appointment);
        boolean success = appointmentService.updateAppointmentInfo(vo);
        if (success) {
            logger.info("WebSocket updateAppointmentInfo success!");
        } else {
            logger.info("WebSocket updateAppointmentInfo failure!");
        }
    }

    private void handleLeaveInfo(String[] arr) {
        String status = arr[1];
        String orderNumber = arr[2];
        LeaveInfoReqVo vo = new LeaveInfoReqVo();
        Leave leave = new Leave();
        leave.setOrderNumber(orderNumber);
        if ("agree".equals(status)) {
            leave.setStatus(1);
        } else {
            leave.setStatus(2);
            String rejectReason = arr[3];
            leave.setRejectReason(rejectReason);
        }
        vo.setLeave(leave);
        boolean success = leaveService.updateLeaveInfo(vo);
        if (success) {
            logger.info("WebSocket updateLeaveInfo success!");
        } else {
            logger.info("WebSocket updateLeaveInfo failure!");
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误");
        error.printStackTrace();
    }

    // 群发自定义消息
    public static void sendInfo(String message) throws IOException {
        logger.info(message);
        for (WebSocketServer server : webSocketSet) {
            try {
                server.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
