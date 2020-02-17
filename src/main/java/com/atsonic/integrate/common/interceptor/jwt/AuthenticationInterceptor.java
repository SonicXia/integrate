package com.atsonic.integrate.common.interceptor.jwt;

import com.atsonic.integrate.common.annotation.PassToken;
import com.atsonic.integrate.common.annotation.UserLoginToken;
import com.atsonic.integrate.common.config.MyConfig;
import com.atsonic.integrate.common.redis.RedisServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * token校验
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(AuthenticationInterceptor.class.getName());

	@Autowired
	private RedisServiceImpl redisService;

	@Autowired
	private MyConfig myConfig;

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
		System.out.println("进入token认证拦截...");
		String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
		// 如果不是controller方法则直接通过
		if(!(handler instanceof HandlerMethod)){
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Method method=handlerMethod.getMethod();
		//检查是否有passtoken注释，有则跳过认证
		// （当前如果没有标注PassToken和UserLoginToken的，默认按PassToken直接放行）
		if (method.isAnnotationPresent(PassToken.class)) {
			PassToken passToken = method.getAnnotation(PassToken.class);
			if (passToken.required()) {
				return true;
			}
		}
		//检查有没有需要用户权限的注解
		// （后期如果需要验证token的接口太多，可以将拦截到的请求统一做token验证，不再校验是否含有UserLoginToken注解）
		if (method.isAnnotationPresent(UserLoginToken.class)) {
			UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
			if (userLoginToken.required()) {
				// 执行认证
				if (token == null) {
					throw new RuntimeException("请求参数无token，请重新登录");
				}
				// 获取 jwt 中的 user id
				String userId;
				try {
					userId = JWT.decode(token).getAudience().get(0);
				} catch (JWTDecodeException j) {
					throw new RuntimeException("401");
				}

				boolean hasKey = redisService.hasKey(myConfig.getRedisPrefix() + token);

				if (!hasKey) {
					throw new RuntimeException("登录失效，请重新登录");
				}
				// 验证 jwt
				JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(myConfig.getJwtSalt())).build();
				try {
					jwtVerifier.verify(token);
				} catch (JWTVerificationException e) {
					throw new RuntimeException("401");
				}
				return true;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler,
						   ModelAndView modelAndView) throws Exception {
	}
 
	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
								Object handler, Exception ex) throws Exception {
	}
}