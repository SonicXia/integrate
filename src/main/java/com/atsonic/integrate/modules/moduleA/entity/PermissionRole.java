package com.atsonic.integrate.modules.moduleA.entity;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Sonic
 * @since 2019-03-22
 */
public class PermissionRole extends Model<PermissionRole> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer rid;
    private Integer pid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PermissionRole{" +
                "id=" + id +
                ", rid=" + rid +
                ", pid=" + pid +
                '}';
    }
}
