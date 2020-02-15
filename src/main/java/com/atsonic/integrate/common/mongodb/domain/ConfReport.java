package com.atsonic.integrate.common.mongodb.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "conf_report")
public class ConfReport {
    @Field("conf_uuid")
    private String conf_uuid;
    @Field("conf_id")
    private String conf_id;
    @Field("conf_status")
    private String conf_status;
    @Field("access_num")
    private String access_num;
    @Field("attendance")
    private Integer attendance;
    @Field("attendance_real")
    private Integer attendance_real;
    @Field("starttime")
    private String starttime;
    @Field("endtime")
    private String endtime;
    @Field("attend_detail")
    private List<AttendDetail> attend_detail; // 嵌套list的处理

    @Field("userid")
    private String userid;
    @Field("conf_name")
    private String conf_name;
    @Field("create_time")
    private Date create_time;

    public String getConf_uuid() {
        return conf_uuid;
    }

    public void setConf_uuid(String conf_uuid) {
        this.conf_uuid = conf_uuid;
    }

    public String getConf_id() {
        return conf_id;
    }

    public void setConf_id(String conf_id) {
        this.conf_id = conf_id;
    }

    public String getConf_status() {
        return conf_status;
    }

    public void setConf_status(String conf_status) {
        this.conf_status = conf_status;
    }

    public String getAccess_num() {
        return access_num;
    }

    public void setAccess_num(String access_num) {
        this.access_num = access_num;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public Integer getAttendance_real() {
        return attendance_real;
    }

    public void setAttendance_real(Integer attendance_real) {
        this.attendance_real = attendance_real;
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

    public List<AttendDetail> getAttend_detail() {
        return attend_detail;
    }

    public void setAttend_detail(List<AttendDetail> attend_detail) {
        this.attend_detail = attend_detail;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getConf_name() {
        return conf_name;
    }

    public void setConf_name(String conf_name) {
        this.conf_name = conf_name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
