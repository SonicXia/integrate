package com.atsonic.integrate.common.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author Sonic
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {

	/**
	 * 自定义密码匹配规则
	 *
	 * @param token
	 * @param info
	 * @return
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String password = new String(usernamePasswordToken.getPassword());
		String dbpassword = (String) info.getCredentials();
		return this.equals(password, dbpassword);
	}
}
