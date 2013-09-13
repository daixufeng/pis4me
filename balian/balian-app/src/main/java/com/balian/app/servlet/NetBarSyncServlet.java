package com.balian.app.servlet;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.balian.app.utils.EnAes;
import com.balian.app.utils.MD5;

@SuppressWarnings("serial")
public class NetBarSyncServlet extends HttpServlet {

	private static final String AES_KEY = "CRUNII_NETBARSYNC";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String ramdCode = request.getParameter("ramdCode");
		String data = request.getParameter("data");
		try {
			String key = MD5.encode(AES_KEY + ramdCode);
			EnAes encryptAes = new EnAes();
			String strData = encryptAes.decrypt(data, key);
			Map<String, String> items = getParametersFormData(strData);
			System.out.println(items);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Map<String, param>
		catch (KeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Map<String, String> getParametersFormData(String str) {
		Map<String, String> params = new HashMap<String, String>();
		if (str != null && str != "") {

			String[] keyPairs = str.split("&");
			for (String s : keyPairs) {
				String[] keyVal = s.split("=");
				params.put(keyVal[0], keyVal[1]);
			}

		}

		return params;
	}
}
