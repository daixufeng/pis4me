Ext.onReady(function(){
	new Ext.Viewport({
		layout: 'fit',
		items: {
			layout: 'border',
			title: 'Helllo',
			items:[{
				region: 'center',
				html: '<p>Helllo</p>'
			}]
		}
	})
})