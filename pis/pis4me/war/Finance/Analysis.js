Ext.onReady(function(){
	new Ext.Viewport({
		layout: 'fit',
		items: {
			layout: 'border',
			title: '数据分析',
			items: [{
				region: 'center',
				html: 'welcome'
			}]
		}		
	})	
})