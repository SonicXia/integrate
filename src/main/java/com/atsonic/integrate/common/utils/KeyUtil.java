package com.atsonic.integrate.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * Created by gaogao
 * 2017-06-11 19:12
 */
public class KeyUtil {


    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };


    public static String getNextSalt() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }


    /**
     * 生成32位的主键
     * @return
     */
    public static synchronized String  getUUID() {
        String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
        uuid = uuid.replace("-", "");//因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可
        return uuid;
    }

    /**
     * 8位数字
     * @return
     */
    public static String getGenerId(){
        StringBuilder str=new StringBuilder();//定义变长字符串
        Random random=new Random();
        //随机生成数字，并添加到字符串
        for(int i=0;i<8;i++){
            str.append(random.nextInt(10));
        }
        //将字符串转换为数字并输出
        return  str.toString();
    }


    public static String getUUId(){
        StringBuilder str=new StringBuilder();//定义变长字符串
        Random random=new Random();
        //随机生成数字，并添加到字符串
        for(int i=0;i<8;i++){
            str.append(random.nextInt(10));
        }
        //将字符串转换为数字并输出
        return  str.toString();
    }

    /**
     * 5位数字
     * @return
     */
    public static String getGenerId5(){
        StringBuilder str=new StringBuilder();//定义变长字符串
        Random random=new Random();
        //随机生成数字，并添加到字符串
        for(int i=0;i<5;i++){
            str.append(random.nextInt(10));
        }
        //将字符串转换为数字并输出
        return  str.toString();
    }

    /**
     * 生成呼叫中心的时候自动生成客户超管id
     * @return
     */
    public static String getUId(){
        return "/^[[A-Za-z0-9]{6,20}$/";
    }

    /**
     * 生成呼叫中心的时候自动生成客户超管账号
     * @return
     */
    public static String getUAccount(){
        return "/^[[A-Za-z\\u4E00-\\u9FA50-9]{0,10}$/";
    }
}
