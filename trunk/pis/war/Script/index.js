﻿Ext.onReady(function(){		
		
			var win = new Ext.Window({
				title: '登陆',
				width: 250,
				height: 160,
				modal: true,
				layout: 'form',
				closeAction: 'hide',
				labelWidth: 45,
				bodyStyle: 'padding:10px;',
				defaults: {anchor: '90%'},
				items: [{
					xtype: 'textfield',
					fieldLabel: '用户名',
					id: 'UserId'
				},{
					xtype: 'textfield',
					inputType:'password',
					fieldLabel: '密码',
					id: 'Password'
				}],
				buttons: [{
					text: '登陆',
					handler: function(){
						if(Ext.getCmp('UserId').getValue() == ""){
							Ext.Msg.showWarn("用户Id不能为空");
							return;
						}
						var params = {
							UserId: Ext.getCmp('UserId').getValue(),
							Password: Ext.getCmp('Password').getValue()
					   	}
						Ext.Ajax.request({
						   url: '*.login',
						   success: function(re, op){
								var o = Ext.decode(re.responseText);
								if(o.hasError){
									Ext.Msg.showWarn(o.Msg);	
								}
								else{
									//Ext.Msg.showInfo(o.Msg);
									document.location = "/main.jsp";
								}
						   },
						   failure: function(re,op){
								var o = Ext.decode(re.responseText);
						   		Ext.Msg.showError(o.Msg);	
						   },	
						   headers: {
						       'content-type': 'text/html'
						   },
						   jsonData : params
						});	
					}
				},{
					text: '离开',
					handler: function(){
						window.close();
					}
				}]
			})	
			win.show();	
		})