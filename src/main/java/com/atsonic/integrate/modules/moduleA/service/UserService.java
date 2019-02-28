package com.atsonic.integrate.modules.moduleA.service;

import com.atsonic.integrate.modules.moduleA.dao.UserMapper;
import com.atsonic.integrate.modules.moduleA.entity.User;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sonic
 * @since 2019-02-26
 */
public interface UserService extends IService<User> {

    public User getUser(Integer id);

    public User getUser2(Integer id);

    User updateUser(User user);

}
