package com.atsonic.integrate.modules.moduleA.entity;

import com.atsonic.integrate.modules.moduleA.model.oPermission;
import com.atsonic.integrate.modules.moduleA.model.oUser;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 
 * </p>
 *
 * @author Sonic
 * @since 2019-03-22
 */
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;
    private String rname;

    @TableField(exist = false)
    private Set<Permission> Permissions = new HashSet<>();
    @TableField(exist = false)
    private Set<User> Users = new HashSet<>();

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Set<Permission> getPermissions() {
        return Permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        Permissions = permissions;
    }

    public Set<User> getUsers() {
        return Users;
    }

    public void setUsers(Set<User> users) {
        Users = users;
    }

    @Override
    protected Serializable pkVal() {
        return this.rid;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                ", Permissions=" + Permissions +
                ", Users=" + Users +
                '}';
    }
}
