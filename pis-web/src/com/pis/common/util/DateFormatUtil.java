package com.pis.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

	/**
	 * 
	 * @param date
	 * @return yyyyMMddHHmmss
	 */
	public static String formatyyyyMMddHHmmss(Date date) {
		return format("yyyyMMddHHmmss", date);
	}

	/**
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String formatyyyy_MM_dd_HH_mm_ss(Date date) {
		return format("yyyy-MM-dd HH:mm:ss", date);
	}

	private static String format(String formatStr, Date date) {
		DateFormat df = new SimpleDateFormat(formatStr);
		return df.format(date);
	}
}
