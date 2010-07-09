Ext.onReady(function(){
	Ext.getDom('btnLogin').onclick = function(){
	
		if(Ext.getDom('UserId').value == ""){
			Ext.Msg.showWarn("用户Id不能为空");
			return;
		}
		var params = {
			UserId: Ext.getDom('UserId').value,
			Password: Ext.getDom('Password').value
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
	
	Ext.getDom('btnCancel').onclick = function(){
		window.close();
	}

})