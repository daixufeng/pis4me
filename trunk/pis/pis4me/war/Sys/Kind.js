Ext.onReady(function(){	
	var store = new Ext.data.JsonStore({
		url: '/Kind/getByCondition',
		fields: ['id', 'kindName', 'kindNo', 'property', 'type'],
		listeners: {
			beforeload: function(st, op){			
			
			}
		}
	});

	var grid = new Ext.grid.GridPanel({
		region: 'center',
		border: false,
		store: store,
		columns: [
			{ header: 'id', dataIndex: 'id'},
			{ header: '名称', dataIndex: 'kindName'},
			{ header: '编号', dataIndex: 'kindNo'},
			{ header: '属性', dataIndex: 'property'},
			{ header: '类别', dataIndex: 'type'}
		],
		tbar: [{
			text: '新增',
			handler: function(){
				win.show();
			}
		}, {
		
		
		}]
	})
	
	var win = new Ext.Window({
		title: '编辑任务',
		layout: 'form',
		bodyStyle: 'padding:15px;',
		width: 500,
		height: 309,
		closeAction: 'hide',
		modal: true,
		items: [{
			xtype: 'textfield',
			fieldLabel: '类别名称',
			id: 'txtName'
		}, {
			xtype: 'textfield',
			fieldLabel: 'txtKindNo',
			id: 'txtKindNo'
		}, {
			xtype: 'textfield',
			fieldLabel: '属性',
			id: 'txtProperty'		
		}, {
			xtype: 'textfield',
			fieldLabel: '类别',
			id: 'txtType'
		}],
		buttons: [{
			text: '保存',
			handler: function(){
				Ext.Ajax.request({
					url: '/Kind/Save.action',
					method: "POST",
					waitMsg: "请等待!",
					params: {
						Kind: {
							kindName: Ext.getCmp('txtName').getValue(),
							kindNo: Ext.getCmp('txtKindNo').getValue(),
							property: Ext.getCmp('txtProperty').getValue(),
							type: Ext.getCmp('txtType').getValue()
						}
					},
					success: function (resp, opt) {
						var reVal = Ext.util.JSON.decode(resp.responseText);							
						win.hide();							
						Ext.Msg.showInfo(reVal.Msg);
						store.load();
					},
					failure: function (resp, opt) {
						
					}
				});
			}
		},{
			text: '取消',
			handler: function(){
				win.hide();
			}
		}]
	})
	
	new Ext.Viewport({
		layout: 'fit',
		items: {
			layout: 'border',
			border: false,
			items: [grid]
		}		
	})	
})