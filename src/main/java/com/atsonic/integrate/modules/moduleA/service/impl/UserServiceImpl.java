package com.atsonic.integrate.modules.moduleA.service.impl;

import com.atsonic.integrate.modules.moduleA.entity.User;
import com.atsonic.integrate.modules.moduleA.dao.UserMapper;
import com.atsonic.integrate.modules.moduleA.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sonic
 * @since 2019-02-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Cacheable(value = {"user"}/*,keyGenerator = "myKeyGenerator",condition = "#a0>1",unless = "#a0==2"*/)
    public User getUser(Integer id){
        System.out.println("查询"+id+"号用户");
        User user = userMapper.getUserById(id);
        return user;
    }

    @Cacheable(value = {"user1", "user2"}, key = "#root.caches[0].name")
    @Override
    public User getUser2(Integer id) {
        System.out.println("2查询"+id+"号用户");
        return baseMapper.selectById(id);
    }


}
