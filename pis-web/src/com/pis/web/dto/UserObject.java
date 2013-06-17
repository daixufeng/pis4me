package com.pis.web.dto;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.pis.domain.sm.Button;
import com.pis.domain.sm.Menu;

public class UserObject implements Principal, Serializable {

	private static final long serialVersionUID = 4053565013489298999L;
	private Long loginId;
	private String loginCode;
	private Integer loginType;
	private String userName;
	private Long staffId;
	private Integer loginLevel;
	private Integer loginNum;
	private Date lastChgPwdTime;
	private Integer state;
	
	private List<Menu> menus;
	private List<Button> buttons;
	private List<String> urls;
	private int isInitPwd;
	
	public String getName() {
		return this.loginCode;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Integer getLoginLevel() {
		return loginLevel;
	}

	public void setLoginLevel(Integer loginLevel) {
		this.loginLevel = loginLevel;
	}

	public Integer getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public Date getLastChgPwdTime() {
		return lastChgPwdTime;
	}

	public void setLastChgPwdTime(Date lastChgPwdTime) {
		this.lastChgPwdTime = lastChgPwdTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}

	public List<String> getUrls() {
		if(urls == null) {
			setUrls();
		}
		if(urls.isEmpty()) {
			for(Menu menu : menus) {
				urls.add(menu.getMenuUrl());
			}
			for(Button button : buttons) {
				urls.add(button.getButtonUrl());
			}
		}
		return urls;
	}

	public void setUrls() {
		if(this.urls == null) {
			this.urls = new ArrayList<String>();	
		}
	}

	public int getIsInitPwd() {
		return isInitPwd;
	}

	public void setIsInitPwd(int isInitPwd) {
		this.isInitPwd = isInitPwd;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("state", this.state)
				.append("loginType", this.loginType).append("name",
						this.getName()).append("loginCode", this.loginCode)
				.append("loginLevel", this.loginLevel).append("loginNum",
						this.loginNum).append("staffId", this.staffId).append(
						"lastChgPwdTime", this.lastChgPwdTime).append(
						"loginId", this.loginId).append("userName",
						this.userName).toString();
	}

}
