package com.atsonic.integrate.modules.moduleA.service.impl;

import com.atsonic.integrate.modules.moduleA.dao.RoleMapper;
import com.atsonic.integrate.modules.moduleA.entity.Role;
import com.atsonic.integrate.modules.moduleA.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
