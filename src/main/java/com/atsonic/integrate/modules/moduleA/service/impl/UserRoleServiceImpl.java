package com.atsonic.integrate.modules.moduleA.service.impl;

import com.atsonic.integrate.modules.moduleA.dao.UserRoleMapper;
import com.atsonic.integrate.modules.moduleA.entity.UserRole;
import com.atsonic.integrate.modules.moduleA.service.UserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
