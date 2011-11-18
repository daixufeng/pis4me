Ext.onReady(function(){
	new Ext.Viewport({
		layout: 'fit',
		items: {
			layout: 'border',
			title: 'Hello',
			items: {
				region: 'center',
				border: false,
				html: '<div>Hello</div>'
			}
		}
	})
	
})