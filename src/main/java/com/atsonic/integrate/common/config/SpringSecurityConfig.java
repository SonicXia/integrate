package com.atsonic.integrate.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Sonic
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	UserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// 基于内存的验证
//		auth.inMemoryAuthentication()
//				.passwordEncoder(new BCryptPasswordEncoder())
//				.withUser("admin")
//				.password(new BCryptPasswordEncoder().encode("123456"))
//				.roles("ADMIN");
//
//		// 可以添加多个用户
//		auth.inMemoryAuthentication()
//				.passwordEncoder(new BCryptPasswordEncoder())
//				.withUser("zhangsan")
//				.password(new BCryptPasswordEncoder().encode("zhangsan"))
//				.roles("ADMIN");
//		// 不同角色
//		auth.inMemoryAuthentication()
//				.passwordEncoder(new BCryptPasswordEncoder())
//				.withUser("demo")
//				.password(new BCryptPasswordEncoder().encode("demo"))
//				.roles("USER");

//		auth.userDetailsService(myUserService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.anyRequest().authenticated()
				.and()
				.logout().permitAll()
				.and()
				.formLogin();
		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
	}
}
