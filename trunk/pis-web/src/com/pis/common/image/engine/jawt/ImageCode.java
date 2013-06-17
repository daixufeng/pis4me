/**
 * Copyright 2008-2009. Chongqing Communications Industry Services Co.,Ltd Information Technology Branch. All rights
 * reserved. <a>http://www.cqcis.com</a>
 */
package com.pis.common.image.engine.jawt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author liuhz create 2008-7-10
 */
public class ImageCode {
	private final static Log log = LogFactory.getLog(ImageCode.class);
	public static String IMAGE_CODE = "image_code";
	private static String[] characterLibrary = { "2", "3", "4", "5", "6", "7", "9", "A", "C", "D", "E", "F", "G", "J",
			"K", "L", "M", "N", "P", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
//	private static String[] lowcharacters = { "a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "m", "n", "p", "q", "r",
//			"s", "t", "u", "v", "w", "x", "y", "z" };
//	private static String[] adds = { "!", "@", "#", "$", "%", "^", "&", "*", "(", ")" };
	private static Color[] randomColors = { Color.red, Color.green, Color.blue };

	// 给定范围获得随机颜色

	public static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public static void writeImageCode(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 在内存中创建图象
		int width = 120, height = 40;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics2D g = image.createGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		// g.setColor(getRandColor(200, 250));
		g.setColor(Color.WHITE);

		// 随机设定字体
		g.setFont(getFont());

		g.fillRect(0, 0, width, height);

		String randCode = "";
		// 取随机产生的认证码(4位数字)
		for (int i = 0; i < 4; i++) {
			int randint = random.nextInt(characterLibrary.length);
			String rand = characterLibrary[randint];
			randCode += rand;
			// 将认证码显示到图象中
			// g.setColor(getRandColor(0, 40));
			g.setColor(new Color(random.nextInt(88), random.nextInt(188), random.nextInt(255)));

			// 调用函数出来的颜色相同 ， 可能是因为种子太接近 ， 所以只能直接生成
			// g.rotate(rotates[i], 13 * i + 6, 15);
			g.drawString(rand, 28 * i + 4, 34);
		}
		// 图象生效
		g.setColor(randomColors[random.nextInt(randomColors.length)]);
		//添加多条干扰线
//		g.drawLine(random.nextInt(width), height / 2, random.nextInt(width), height);
        for(int i=0; i<10; i++){  
            int x1 = random.nextInt(width) ;  
            int y1 = random.nextInt(height/2);  
            int x2 = random.nextInt(width) ;  
            int y2 = random.nextInt(height) ;  
            g.drawLine(x1, y1, x2, y2) ;  
        }
		g.dispose();

		// 图像code写入session
		request.getSession().setAttribute(IMAGE_CODE, randCode);

		// 输出图象到页面
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			ImageIO.write(image, "JPEG", outputStream);
			outputStream.flush();
			outputStream.close();
			outputStream = null;
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException ex) {
					log.error(ex.getMessage(), ex);
				}
			}
		}
	}

	private static Font getFont() {
		Random random = new Random();
		int fontSize = 36;
		Font[] font = new Font[6];
		font[0] = new Font("Ravie", Font.BOLD | Font.PLAIN, fontSize);
		font[1] = new Font("宋体", Font.BOLD | Font.ITALIC, fontSize);
		font[2] = new Font("Forte", Font.BOLD | Font.PLAIN, fontSize);
		font[3] = new Font("Wide Latin", Font.BOLD | Font.ITALIC, fontSize);
		font[4] = new Font("Gill Sans Ultra Bold", Font.BOLD | Font.PLAIN, fontSize);
		font[5] = new Font("Arial", Font.BOLD | Font.ITALIC, fontSize);
		return font[random.nextInt(6)];
	}

	/**
	 * 默认校验方法
	 * 
	 * @param validateCode
	 * @param session
	 * @return
	 */
	public static boolean validateCode(String validateCode, HttpSession session) {
		validateCode = validateCode.toLowerCase();
		String randCode = (String) session.getAttribute(IMAGE_CODE);
		if (randCode == null) {
			return false;
		}
		randCode = randCode.toLowerCase();
		session.removeAttribute(IMAGE_CODE);
		return (randCode != null && randCode.equals(validateCode)) ? true : false;
	}
}
