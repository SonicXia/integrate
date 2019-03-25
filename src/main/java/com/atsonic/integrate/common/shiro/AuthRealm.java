package com.atsonic.integrate.common.shiro;

import com.atsonic.integrate.modules.moduleA.entity.Permission;
import com.atsonic.integrate.modules.moduleA.entity.Role;
import com.atsonic.integrate.modules.moduleA.entity.User;
import com.atsonic.integrate.modules.moduleA.service.UserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Sonic
 */
public class AuthRealm extends AuthorizingRealm {

	@Autowired
	UserService userService;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();
		// 权限list
		List<String> permissionList = new ArrayList<>();
		// 角色list
		List<String> roleNameList = new ArrayList<>();

		Set<Role> roleSet = user.getRoles();
		if (CollectionUtils.isNotEmpty(roleSet)) {
			for (Role role : roleSet) {
				roleNameList.add(role.getRname());
				Set<Permission> permissionSet = role.getPermissions();
				if (CollectionUtils.isNotEmpty(permissionSet)) {
					for (Permission permission : permissionSet) {
						permissionList.add(permission.getName());
					}
				}
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissionList);
		info.addRoles(roleNameList);
		return info;
	}

	// 认证登录
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		User user = userService.findByUsername(username);
		return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
	}
}
