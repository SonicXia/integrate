package com.atsonic.integrate.common.mongodb.domain;

import org.springframework.data.mongodb.core.mapping.Field;

public class AttendDetail {
    @Field("number")
    private String number;
    @Field("starttime")
    private String starttime;
    @Field("endtime")
    private String endtime;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
