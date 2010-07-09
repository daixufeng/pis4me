Ext.onReady(function(){
	Ext.Ajax.request({
		url: '/menu.json',
		success: function(re, op){
			var o = Ext.decode(re.responseText);
			
			
			//组织菜单
			var menus = [];
			
			for(var i = 0; i < o.length; i++){
				var item = o[i];
				var menuItem = {
					title: '<div style="font-size:15px;height:19px">' + item.title + '</div>',
					items: []
				}
				for(var k = 0; k < item.children.length; k++){
					menuItem.items.push({
						html: '<div style="padding-left:10px;"><a href="' +
							item.children[k].url + '" target= "desk">' + item.children[k].title + '</a></div>'
					})
				}
				menus.push(menuItem);
			}
			
			var viewPort = new Ext.Viewport({
				layout: 'fit',
				items:{
					layout: 'border',
					items: {
						layout: 'border',
						border: false,
						region: 'center',
						margins: '60 0 0 0',
						items:[{
							region: 'west',			
							title: 'Menu',
							id: 'menus',
							width: 220,				
							collapsible: true,
							split: true,
							layout: {
								type: 'accordion',
								animate: true
							},
							items: menus
						},{
							region: 'center',
							border: false,
							html: '<iframe id="desk" frameborder="0" name="desk"  width="100%" height="100%" src = "Desk.jsp"></iframe>'
						}]
					}
				}
			})		
			
			
			
			
			
		}
	});
	
	
})