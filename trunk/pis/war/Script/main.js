Ext.onReady(function(){
	var viewPort = new Ext.Viewport({
		layout: 'fit',
		items:{
			layout: 'border',
			border: false,
			items:[{ // raw element
                region:'north',
                height:32
	        },{
			    region: 'west',
				title: 'Menu',
				width: 220,				
				collapsible: true,
                split: true,
                layout: {
                    type: 'accordion',
                    animate: true
                },
                items: [{
                    title: 'Navigation'
                }, {
                    title: 'Settings',
                    html: '<li>项目管理</li>' +
						'<li>项目管理</li>' +
						'<li>项目管理</li>'
                }]
			},{
				region: 'center',
				margins: '0 0 0 0',
				html: '<iframe id="desk" frameborder="0" name="desk"  width="100%" height="100%" src = "Desk.jsp"></iframe>'
			}]
		}
	})
})