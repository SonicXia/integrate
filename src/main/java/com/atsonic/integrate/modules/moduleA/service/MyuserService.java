package com.atsonic.integrate.modules.moduleA.service;

import com.atsonic.integrate.modules.moduleA.entity.Myuser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sonic
 * @since 2019-02-26
 */
public interface MyuserService extends IService<Myuser> {

    public Myuser getUser(Integer id);

    public Myuser getUser2(Integer id);

    Myuser updateUser(Myuser myuser);

}
