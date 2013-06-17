/**
 * 
 * @author Qiuxj create 2012-11-6
 */
package com.pis.common.image.engine;

import javax.servlet.http.HttpSession;

import com.pis.common.image.engine.jawt.ImageCode;

/**
 * @author Qiuxj
 *
 */
public class ImageCodeUtil {

	/**
	 * 校验验证码
	 * @param validateCode
	 * @param session
	 * @return
	 */
	public static boolean validate(String validateCode, HttpSession session) {
//		return ImageCodeHandler.validateCode(validateCode);
		return ImageCode.validateCode(validateCode, session);
	}
	
}
