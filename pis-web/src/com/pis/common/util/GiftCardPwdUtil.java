/**
 * Copyright 2011-2013. Chongqing Crun Information & Network Co., Ltd. All rights reserved.
 * <a>http://www.crunii.com</a>
 */
package com.pis.common.util;

import org.apache.commons.lang.StringUtils;

/**
 * @author Qiuxj create 2013-1-31
 *
 */
public class GiftCardPwdUtil {
	
	/**
	 * 生成卡密
	 * @param cardSn
	 * @param salt
	 * @return
	 */
	public static String createPwd(String cardSn, String salt) {
		if(StringUtils.isBlank(cardSn) || StringUtils.isBlank(salt)){
			throw new RuntimeException("CARDSN or SALT must be not null or empty");
		}
		String md5Str = Md5Encrypt.md5(cardSn, salt);
		
		return extraPwd(md5Str);
	}
	
	/**
	 * 校验密码
	 * @param cardSn
	 * @param salt
	 * @param pwd
	 * @return
	 */
	public static boolean validPwd(String cardSn, String salt, String pwd) {
		String md5Str = createPwd(cardSn, salt);
		return md5Str.equals(pwd);
	}
	
	/**
	 * 生成salt
	 * @return
	 */
	public static String createSalt(){
		return NumberUtil.randomNumberStr(6);
	}
	

	private static String extraPwd(String md5Str) {
		return md5Str.substring(10, 26).toUpperCase();
	}
	
	public static void main(String[] args) {
		System.err.println(createPwd("qqqq", "ccc").length());
		System.err.println(createSalt());
	}
}
