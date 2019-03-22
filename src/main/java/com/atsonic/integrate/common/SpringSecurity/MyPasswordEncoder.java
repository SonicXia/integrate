package com.atsonic.integrate.common.SpringSecurity;

import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Sonic
 */
public class MyPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence charSequence) {
		MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("MD5");
		return encoder.encode(charSequence);
	}

	@Override
	public boolean matches(CharSequence charSequence, String s) {
		return false;
	}
}
