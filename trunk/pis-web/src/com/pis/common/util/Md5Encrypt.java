package com.pis.common.util;

/**
 * Alipay.com Inc. Copyright (c) 2004-2005 All Rights Reserved.
 * <p>
 * Created on 2005-7-9
 * </p>
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密算法
 */
public class Md5Encrypt {
	/**
	 * Used building output as Hex
	 */
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };
	
	/**
	 * 校验
	 * @param encPass 密文
	 * @param rawPass 明文
	 * @param salt 
	 * @return
	 */
	public static boolean valid(String encPass, String rawPass, Object salt) {
		String pass1 = "" + encPass;
		String pass2 = md5(rawPass, salt);

		return pass1.equals(pass2);
	}
	
	public static String md5(String rawPass, Object salt) {
		String sources = rawPass;
		if (salt != null && !"".equals(salt)) {
			sources = sources + salt.toString();
		}
		return md5(sources);
	}

	/**
	 * 对字符串进行MD5加密
	 * 
	 * @param text 明文
	 * @return 密文
	 */
	public static String md5(String rawPass) {
		MessageDigest msgDigest = null;

		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("System doesn't support MD5 algorithm.");
		}

		try {
			msgDigest.update(rawPass.getBytes("utf-8"));

		} catch (UnsupportedEncodingException e) {

			throw new IllegalStateException("System doesn't support your  EncodingException.");

		}

		byte[] bytes = msgDigest.digest();

		String md5Str = new String(encodeHex(bytes));

		return md5Str;
	}

	private static char[] encodeHex(byte[] data) {

		int l = data.length;

		char[] out = new char[l << 1];

		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}

		return out;
	}

}