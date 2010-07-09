Ext.onReady(function(){
	new Ext.Viewport({
		layout: 'fit',
		items: {
			layout: 'border',
			title: '每日费用',
			items: [{
				region: 'center',
				html: 'welcome'
			}]
		}		
	})	
})