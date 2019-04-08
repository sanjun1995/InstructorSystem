package cn.instructorsystem.student.util;

import cn.instructorsystem.student.service.impl.MonitorServiceImpl;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author sanjun
 * @date 2019/3/25 21:39
 */
public class MessageUtil {
    public static void main(String[] args) throws Exception {
        /*MessageUtil.appointment("13556115197", "曹志鑫", "班级事务需要请教老师", "2019-03-15 09:30:00", "2019-03-15 11:00:00");*/
        MessageUtil.leave("13556115197", "曹志鑫", "生病外出看医生", "2019-03-15 09:30:00", "2019-03-15 11:00:00");
    }
    private static Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    public static void leave(String phoneNum, String stuName, String reason, String startTime, String endTime) throws Exception {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        NameValuePair[] data = {
                new NameValuePair("Uid", "sanjun"), // 注册的用户名
                new NameValuePair("Key", "d41d8cd98f00b204e980"), // 注册成功后,登录网站使用的密钥
                new NameValuePair("smsMob", phoneNum), // 辅导员号码
                new NameValuePair("smsText", "老师好，" + stuName + "同学请假，理由：" + reason +
                        "，请假时间：" + startTime + " 至 " + endTime + "，望批准。") };//设置短信内容
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result);
        post.releaseConnection();
        logger.info("leave phoneNum: {}, stuName: {}, reason: {}, startTime: {}, endTime: {}",
                phoneNum, stuName, reason, startTime, endTime);
    }

    public static void appointment(String phoneNum, String stuName, String reason, String startTime, String endTime) throws Exception {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        NameValuePair[] data = {
                new NameValuePair("Uid", "sanjun"), // 注册的用户名
                new NameValuePair("Key", "d41d8cd98f00b204e980"), // 注册成功后,登录网站使用的密钥
                new NameValuePair("smsMob", phoneNum), // 辅导员号码
                new NameValuePair("smsText", "老师好，" + stuName + "同学预约，理由：" + reason +
                        "，请假时间：" + startTime + " 至 " + endTime + "，望批准。") };//设置短信内容
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result);
        post.releaseConnection();
        logger.info("appointment phoneNum: {}, stuName: {}, reason: {}, startTime: {}, endTime: {}",
                phoneNum, stuName, reason, startTime, endTime);
    }

    public static void agreeLeave(String phoneNum, String stuName, String reason, String startTime, String endTime) throws Exception {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        NameValuePair[] data = {
                new NameValuePair("Uid", "sanjun"), // 注册的用户名
                new NameValuePair("Key", "d41d8cd98f00b204e980"), // 注册成功后,登录网站使用的密钥
                new NameValuePair("smsMob", phoneNum), // 副班联系方式
                new NameValuePair("smsText", "副班好，" + stuName + "同学请假，理由：" +
                        reason + "，请假时间：" + startTime + " 至 " + endTime + "，已批准。") };//设置短信内容
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result);
        post.releaseConnection();
        logger.info("agreeLeave phoneNum: {}, stuName: {}, reason: {}, startTime: {}, endTime: {}",
                phoneNum, stuName, reason, startTime, endTime);
    }
}
