package com.atsonic.integrate.modules.moduleA.controller;

import com.atsonic.integrate.modules.moduleA.entity.Myuser;
import com.atsonic.integrate.modules.moduleA.service.MyuserService;
import com.atsonic.integrate.modules.moduleA.task.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class MyuserController {

    @Autowired
    MyuserService myuserService;

    @Autowired
    AsyncService asyncService;

    @GetMapping("/{id}")
    public Myuser getUser(@PathVariable("id") Integer id){
        Myuser myuser = myuserService.getUser(id);
//        Myuser myuser = myuserService.getUser2(id);
        return myuser;
    }

    @GetMapping
    public Myuser updateUser(Myuser myuser){
        Myuser newMyuser = myuserService.updateUser(myuser);
        return newMyuser;
    }

    @GetMapping("/hello")
    public String hello(){
        asyncService.hello();
        System.out.println("方法执行结束2");
        return "success";
    }

    @GetMapping("/test")
    public String test(){
        String username = myuserService.getUsername();
        return username;
    }

}

