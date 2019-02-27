package com.atsonic.integrate.modules.moduleA.controller;

import com.atsonic.integrate.modules.moduleA.entity.User;
import com.atsonic.integrate.modules.moduleA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sonic
 * @since 2019-02-26
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public User getEmployee(@PathVariable("id") Integer id){
        User user = userService.getUser2(id);
        return user;
    }

}

