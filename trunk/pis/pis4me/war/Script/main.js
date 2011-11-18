Ext.onReady(function(){
	Ext.Ajax.request({
		url: '/menu.json',
		success: function(resp, opt){
			var o = Ext.util.JSON.decode(resp.responseText);			
			//alert(resp.responseText);
			var viewPort = new Ext.Viewport({
		        layout: 'border',
				items: [{
					xtype: 'box',
					region: 'north',
					height: 32, // give north and south regions a height
					autoEl: {
						tag: 'div',
						html: '<p><b>个人信息管理</b></p>'
					}		
				},{
					region: 'west',
					collapsible: true,
					title: '导航菜单',
					id: 'treeMenu',
					xtype: 'treepanel',
					width: 200,
					autoScroll: true,
					split: true,
					rootVisible: false,
					root: o,
					listeners: {
						click: function (node) {	
							if(!node.isLeaf())return;
								
							if (node.id == "0") return;
							var tabpanel = Ext.getCmp('mainContainer');
							var tab = tabpanel.getComponent(node.id);
							
							if (!tab) {
								var myMask = new Ext.LoadMask(Ext.getBody(), {msg:"Please wait..."});
								myMask.show();
								tab = tabpanel.add({
									id: node.id,
									title: node.text,
									closable: true,
									html: '<iframe id="reporter-iframe" src=\'' + node.attributes.url + '\' frameborder="0" scrolling="auto" style="border:0px none;height:100%; width:100%;"></iframe>'
								});
								tabpanel.setActiveTab(tab);
								myMask.hide();
							} else {
								tabpanel.setActiveTab(tab);
							}
						}
					}
				}, {
					region: 'center',
					id: 'mainContainer',
					xtype: 'tabpanel'
				}]
		    });
			
			Ext.getCmp('treeMenu').expandAll();
		}
	});
})