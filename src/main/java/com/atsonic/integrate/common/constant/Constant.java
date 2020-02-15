package com.atsonic.integrate.common.constant;

/**
 * @author Sonic
 * @since 2020-02-13
 */
public class Constant {

    public static enum FsServerResult {
        SUCCESS("0","成功"),
        LIMITEDERROR("1","失败，服务器参会人数超过限制"),
        SERVERERROR("2","失败，服务器逻辑错误");

        private FsServerResult(String value, String name){
            this.value = value;
            this.name = name;
        }
        private final String value;
        private final String name;

        public String getValue() {
            return value;
        }
        public String getName() {
            return name;
        }

    }

    public static enum ConfStatus {
        NOTSTART("0","未开始"),
        PROCESSING("1","进行中"),
        COMPLATED("2","已结束");

        private ConfStatus(String value, String name){
            this.value = value;
            this.name = name;
        }
        private final String value;
        private final String name;

        public String getValue() {
            return value;
        }
        public String getName() {
            return name;
        }

    }

    public static enum MeetingUserStatus {
        ENABLE("00","正常"),
        DISABLE("01","删除");

        private MeetingUserStatus(String value, String name){
            this.value = value;
            this.name = name;
        }
        private final String value;
        private final String name;

        public String getValue() {
            return value;
        }
        public String getName() {
            return name;
        }

    }
}
