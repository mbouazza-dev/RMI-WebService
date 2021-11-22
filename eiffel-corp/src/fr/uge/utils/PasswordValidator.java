package fr.uge.utils;

import org.springframework.util.DigestUtils;

public class PasswordValidator {
	
	public static boolean checkPassword(String password, String hash) {
		String passwordHash = DigestUtils.md5DigestAsHex(password.getBytes()).toLowerCase();
		return hash.equals(passwordHash);
	}
}
