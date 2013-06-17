/**
 * 
 */
package com.pis.common.image.engine.jcaptcha;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.octo.captcha.service.CaptchaServiceException;

/**
 * @author qiuxj
 *
 */
public class ImageCodeHandler {
	
	private static final Log logger = LogFactory.getLog(ImageCodeHandler.class);
	public static String IMAGE_CODE = "image_code";

	/**
	 * 生成验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void writeImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 response.setHeader("Cache-Control", "no-store"); 
		 response.setHeader("Pragma", "no-cache"); 
		 response.setDateHeader("Expires", 0); 
		 response.setContentType("image/jpeg"); 
		 OutputStream jpegOutputStream = null; 
		 try { 
			 BufferedImage challenge = ImageServiceSingleton.getInstance().getImageChallengeForID(IMAGE_CODE, request.getLocale()); 
			 jpegOutputStream = response.getOutputStream();
			 ImageIO.write(challenge, "JPEG", jpegOutputStream);
		 } catch (IllegalArgumentException e) { 
			 response.sendError(HttpServletResponse.SC_NOT_FOUND); 
			 return; 
		 } catch (CaptchaServiceException e) { 
			 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); 
			 return; 
		 }
		 ServletOutputStream responseOutputStream = response.getOutputStream(); 
		 responseOutputStream.flush(); 
		 responseOutputStream.close(); 
	}
	
	/**
	 * 验证码校验
	 * @param validateCode
	 * @return
	 */
	public static boolean validateCode(String validateCode) {
		boolean result = false;
		try {
			result = ImageServiceSingleton.getInstance().validateResponseForID(IMAGE_CODE, validateCode);
		} catch (Exception e) {
			logger.info("validate code valid error.");
		}
		return result;
	}
	
}
