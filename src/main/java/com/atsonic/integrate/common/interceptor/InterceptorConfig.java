package com.atsonic.integrate.common.interceptor;

import com.atsonic.integrate.common.interceptor.ip.IPInterceptor;
import com.atsonic.integrate.common.interceptor.jwt.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 按照注册顺序进行拦截
     *
     * @param registry
     */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**") //所有路径都被拦截
                .excludePathPatterns("/meeting/login", "/websocket/**", "/webapi/**"); //添加不拦截路径

		registry.addInterceptor(ipInterceptor())
                .addPathPatterns("/webapi/**");
	}

    /**
     * token权限验证拦截器
     *
     * @return
     */
	@Bean
	public AuthenticationInterceptor authenticationInterceptor() {
		return new AuthenticationInterceptor();
	}

    /**
     * IP白名单验证拦截器
     *
     * @return
     */
	@Bean
	public IPInterceptor ipInterceptor() {
		return new IPInterceptor();
	}

}