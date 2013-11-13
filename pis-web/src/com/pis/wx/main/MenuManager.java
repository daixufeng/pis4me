package com.pis.wx.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pis.wx.pojo.AccessToken;
import com.pis.wx.pojo.Button;
import com.pis.wx.pojo.CommonButton;
import com.pis.wx.pojo.ComplexButton;
import com.pis.wx.pojo.Menu;
import com.pis.wx.pojo.ViewButton;
import com.pis.wx.util.WeixinUtil;

/**
 * 菜单管理器类
 * 
 * @author zhengxp
 * @date 2013-10-21
 */
public class MenuManager {

	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxf74da49fe3c55c0a";
		// 第三方用户唯一凭证密钥
		String appSecret = "65c505854a78a5efd47df875f0114ed1";

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			System.out.println(at.getToken());
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());
			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("展馆地图");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("中国国防纵览");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("中国近现代国防");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("中国当代国防");
		btn14.setType("click");
		btn14.setKey("14");

		CommonButton btn15 = new CommonButton();
		btn15.setName("世界国防博览");
		btn15.setType("click");
		btn15.setKey("15");

		CommonButton btn21 = new CommonButton();
		btn21.setName("主题活动");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("最新资讯");
		btn22.setType("click");
		btn22.setKey("22");

		CommonButton btn23 = new CommonButton();
		btn23.setName("知识讲座");
		btn23.setType("click");
		btn23.setKey("23");

		CommonButton btn31 = new CommonButton();
		btn31.setName("语音导览");
		btn31.setType("click");
		btn31.setKey("31");

		ViewButton btn32 = new ViewButton();
		btn32.setName("企业合作");
		btn32.setType("view");
		btn32.setUrl("http://61.161.127.160/cms/icity/memory/memorydetails.do?id=6847&h=food");

		CommonButton btn33 = new CommonButton();
		btn33.setName("主菜单");
		btn33.setType("click");
		btn33.setKey("33");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("展馆导览");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14, btn15 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("展馆资讯");
		mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("服务体验");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33 });

		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}
