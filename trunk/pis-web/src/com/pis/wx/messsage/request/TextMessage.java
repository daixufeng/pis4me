package com.pis.wx.messsage.request;

/**
 * 文本消息
 * 
 * @author zhengxp
 * @date 2013-10-21
 */
public class TextMessage extends BaseMessage {
	
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}