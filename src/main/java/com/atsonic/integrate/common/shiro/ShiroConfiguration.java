package com.atsonic.integrate.common.shiro;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager; // 自动导包有问题，需要手动指定
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author Sonic
 */
@Configuration
public class ShiroConfiguration {

	// 参数逐级传递

	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);

		bean.setLoginUrl("/shiro/login");
		bean.setSuccessUrl("/shiro/index");
		bean.setUnauthorizedUrl("/shiro/unauthorized");

		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		//当前测试websocket使用，anon 即开放shiro的拦截，直接放行
		// （注意放行的url要放在众多url的前面，至少要放到/**的前面，因为是从上到下的顺序过滤的）
		filterChainDefinitionMap.put("/websocket/**", "anon");

		filterChainDefinitionMap.put("/shiro/index", "authc"); // <url, 指定的拦截器处理>
		filterChainDefinitionMap.put("/shiro/login", "anon");
		filterChainDefinitionMap.put("/shiro/loginUser", "anon");
		filterChainDefinitionMap.put("/shiro/admin", "roles[admin]"); // 设置允许访问该页面所应具有的角色
		filterChainDefinitionMap.put("/shiro/edit", "perms[edit]"); // 设置允许访问该页面所应具有的权限
		filterChainDefinitionMap.put("/druid/**", "anon"); // 放开对durid数据库监控的请求权限
		filterChainDefinitionMap.put("/**", "user"); // 仅判断是否登陆
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return bean;
	}

	@Bean("securityManager")
	public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(authRealm);
		return manager;
	}

	@Bean("authRealm")
	public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher matcher) {
		AuthRealm authRealm = new AuthRealm();
		authRealm.setCacheManager(new MemoryConstrainedCacheManager()); // 设置缓存
		authRealm.setCredentialsMatcher(matcher);
		return authRealm;
	}

	@Bean("credentialMatcher")
	public CredentialMatcher credentialMatcher() {
		return new CredentialMatcher();
	}


	/*************** Shiro 与 Spring 的关联类 **************/

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}


}
