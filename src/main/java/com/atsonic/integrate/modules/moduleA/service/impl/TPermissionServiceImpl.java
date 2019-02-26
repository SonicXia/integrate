package com.atsonic.integrate.modules.moduleA.service.impl;

import com.atsonic.integrate.modules.moduleA.entity.TPermission;
import com.atsonic.integrate.modules.moduleA.dao.TPermissionMapper;
import com.atsonic.integrate.modules.moduleA.service.TPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Sonic
 * @since 2019-02-26
 */
@Service
public class TPermissionServiceImpl extends ServiceImpl<TPermissionMapper, TPermission> implements TPermissionService {

}
