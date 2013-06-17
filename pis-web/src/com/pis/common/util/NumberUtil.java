package com.pis.common.util;

import java.util.Random;

public class NumberUtil {
	
	public static final char[] CHARS = {'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N'};

	/**
	 * @param length 随机数长度 只能取 1-19
	 * @return
	 */
	public static String randomNumberStr(int length) {
		Random r = new Random();
		Long randomLong = r.nextLong();
		if (randomLong < 0) {
			randomLong = 0 - randomLong;
		}
		String longStr = randomLong.toString();
		
		while (longStr.length() < length) {
			longStr += "0";
		}
		int longStrlen = longStr.length();
		return longStr.substring(longStrlen - length);
	}
	
	/**
	 * 生产随机数，并把数字转换为指定字符
	 * @param length 随机数长度 只能取 1-19
	 * @return
	 */
	public static String randomStr(int length) {
		String s = randomNumberStr(length);
		char[] ss = s.toCharArray();
		StringBuffer sb = new StringBuffer("");
		for(int i = 0; i < ss.length; i++){
			int num = Integer.valueOf(ss[i] + "");
			sb.append(CHARS[num]);
		}
		return sb.toString();
	}
	
}
