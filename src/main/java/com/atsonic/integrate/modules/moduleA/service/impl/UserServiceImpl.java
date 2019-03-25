package com.atsonic.integrate.modules.moduleA.service.impl;

import com.atsonic.integrate.modules.moduleA.dao.UserMapper;
import com.atsonic.integrate.modules.moduleA.entity.User;
import com.atsonic.integrate.modules.moduleA.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sonic
 * @since 2019-03-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public User findByUsername(String username) {
		return baseMapper.findByUsername(username);
	}
}
