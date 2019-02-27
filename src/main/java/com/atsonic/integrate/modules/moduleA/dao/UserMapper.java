package com.atsonic.integrate.modules.moduleA.dao;

import com.atsonic.integrate.modules.moduleA.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Sonic
 * @since 2019-02-26
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM user WHERE id = #{id}")
    public User getUserById(Integer id);



}
