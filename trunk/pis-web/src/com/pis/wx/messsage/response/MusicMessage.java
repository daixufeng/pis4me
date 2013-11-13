package com.pis.wx.messsage.response;

/**
 * 音乐消息
 * 
 * @author zhengxp
 * @date 2013-10-21
 */
public class MusicMessage extends BaseMessage {

	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}