package cn.instructorsystem.instructor.util;

import java.util.UUID;

/**
 * @author sanjun
 * @date 2019/1/28 15:51
 */
public class UUIDUtil {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.replace("-", "");
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(UUIDUtil.getUUID());
    }
}
