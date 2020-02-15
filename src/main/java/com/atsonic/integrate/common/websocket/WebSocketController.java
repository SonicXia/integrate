package com.atsonic.integrate.common.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sonic
 * @since 2020-02-13
 */
@Controller
@RequestMapping("/websocket")
public class WebSocketController {

    /**
     * 前端页面的请求地址（当前测试使用）
     * 跳转至 webapp/pages/webSocket.jsp 页面
     *
     * @param userId
     * @return
     */
    @GetMapping("/test/{userId}")
    public ModelAndView socket(@PathVariable String userId) {
        ModelAndView mav = new ModelAndView("/webSocket");
        mav.addObject("userId", userId);
        return mav;
    }

    /**
     * 推送数据接口
     *
     * @param userId
     * @param message
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/push/{userId}", "/push"})
    public Map pushToWeb(@PathVariable(required = false) String userId, String message) {
        Map result = new HashMap();
        try {
            WebSocketServer.sendInfo(message, userId);
            result.put("code", 200);
            result.put("msg", "success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}