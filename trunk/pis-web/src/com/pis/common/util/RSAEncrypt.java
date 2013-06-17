/**
 * 
 */
package com.pis.common.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @author qiuxj
 * @date 2012-5-31
 */
public class RSAEncrypt {

	private static final String PASSWORD_CRYPT_KEY1 = "ec5dO*#M;]sc";
	private static final String PASSWORD_CRYPT_KEY2 = "ecD8*#[;e)sc";
	private static final String DES = "DES";

	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();

		DESKeySpec dks = new DESKeySpec(key);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

		SecretKey securekey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance(DES);

		cipher.init(1, securekey, sr);

		return cipher.doFinal(src);
	}

	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();

		DESKeySpec dks = new DESKeySpec(key);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

		SecretKey securekey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance(DES);

		cipher.init(2, securekey, sr);

		return cipher.doFinal(src);
	}

	public static String byte2hex(byte[] b) {
		String hs = "";

		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);

			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else {
				hs = hs + stmp;
			}
		}

		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if (b.length % 2 != 0) {
			throw new IllegalArgumentException("长度不是偶数");
		}
		byte[] b2 = new byte[b.length / 2];

		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);

			b2[(n / 2)] = (byte) Integer.parseInt(item, 16);
		}

		return b2;
	}

	public static final String encrypt(String password) throws Exception {
		return byte2hex(encrypt(password.getBytes(), PASSWORD_CRYPT_KEY1.getBytes()));
	}

	public static final String decrypt(String data) throws Exception {
		return new String(decrypt(hex2byte(data.getBytes()), PASSWORD_CRYPT_KEY1.getBytes()));
	}

	public static final String encrypt2(String password) throws Exception {
		return byte2hex(encrypt(password.getBytes(), PASSWORD_CRYPT_KEY2.getBytes()));
	}

	public static final String decrypt2(String data) throws Exception {
		return new String(decrypt(hex2byte(data.getBytes()), PASSWORD_CRYPT_KEY2.getBytes()));
	}
	
	public static void main(String[] args) throws Exception {
		String test = "tes";
		String en = encrypt(test);
		String de = decrypt(en);
		System.err.println(en);
		System.err.println(de);
		System.err.println(de.equals(test));
	}

}
