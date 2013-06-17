/**
 * 
 */
package com.pis.common.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;

/**
 * @author Qiuxj
 * 
 */
public class FileNameUtils {

	public static String genFileName() {
		return Num62.longToN62(System.currentTimeMillis())
				+ RandomStringUtils.random(4, Num62.N36_CHARS);
	}

	public static String genFileName(String ext) {
		return genFileName() + "." + ext;
	}
	
	public static String genFileName(String fileName, String ext) {
		return fileName + "." + ext;
	}
	
	public static String getExtension(String fileName) {
		return FilenameUtils.getExtension(fileName);
	}

}
