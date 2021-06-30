package spring_boot.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptionPassword implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		StringBuilder pass = new StringBuilder(rawPassword);
		int hashpw = rawPassword.hashCode();
		pass.append(hashpw);
		return pass.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encodedPassword.equals(encode(rawPassword));
	}

}
