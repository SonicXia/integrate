package com.atsonic.integrate.modules.moduleA.service.impl;

import com.atsonic.integrate.modules.moduleA.dao.PermissionMapper;
import com.atsonic.integrate.modules.moduleA.entity.Permission;
import com.atsonic.integrate.modules.moduleA.service.PermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
