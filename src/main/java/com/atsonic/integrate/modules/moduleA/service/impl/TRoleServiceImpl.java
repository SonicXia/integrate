package com.atsonic.integrate.modules.moduleA.service.impl;

import com.atsonic.integrate.modules.moduleA.entity.TRole;
import com.atsonic.integrate.modules.moduleA.dao.TRoleMapper;
import com.atsonic.integrate.modules.moduleA.service.TRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Sonic
 * @since 2019-02-26
 */
@Service
public class TRoleServiceImpl extends ServiceImpl<TRoleMapper, TRole> implements TRoleService {

}
