package com.atsonic.integrate.common.interceptor.ip;

import com.atsonic.integrate.common.config.MyConfig;
import com.atsonic.integrate.common.utils.IPUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * IP白名单校验
 */
public class IPInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(IPInterceptor.class.getName());

    @Autowired
    private MyConfig myConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //过滤ip,若用户在白名单内，则放行
        String ipAddress = IPUtils.getRealIP(request);
        logger.info("USER IP ADDRESS IS ==>" + ipAddress);
        if(!StringUtils.isNotBlank(ipAddress)) {
            return false;
        }

        if (!myConfig.getIpWhitelist().equals(ipAddress)) {
            throw new RuntimeException("REST请求方IP不在IP白名单内");
        }

//        if(ips.isEmpty()){
//            response.getWriter().append("<h1 style=\"text-align:center;\">Not allowed!</h1>");
//            return false;
//        }
        logger.info("IP白名单校验通过");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
    }

}