package com.atsonic.integrate.modules.moduleA.dao;

import com.atsonic.integrate.modules.moduleA.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Sonic
 * @since 2019-03-22
 */
public interface UserMapper extends BaseMapper<User> {

	User findByUsername(@Param("username") String username);

}
