package com.pis.wx.util;

import java.security.cert.CertificateException;  
import java.security.cert.X509Certificate;  

import javax.net.ssl.X509TrustManager;
  
/** 
 * 证书信任管理器（用于https请求） 
 * 信任所有的证书
 * @author zhengxp 
 * @date 2013-10-29 
 */  
public class MyX509TrustManager implements X509TrustManager {  
  
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
    }  
  
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
    }  
  
    public X509Certificate[] getAcceptedIssuers() {  
        return null;  
    }  
}
