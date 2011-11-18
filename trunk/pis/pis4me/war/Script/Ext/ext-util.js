Ext.Ajax.on('beforerequest', function(conn, op){
    if(op.params != null) {
    	//op.jsonData = {params:op.jsonData};
    	op.params = Ext.util.JSON.encode({params: op.params });
    }
});

Ext.apply(Ext.Msg, {
	showWarn: function(msg) {
	    Ext.Msg.show({
	        title: "警告",
	        msg: msg,
	        buttons: Ext.Msg.OK,
	        icon: Ext.Msg.WARNING,
	        minWidth: 150,
	        maxWidth: 600
	    });
	},
	showInfo: function(msg) {
	    Ext.Msg.show({
	        title: "提示",
	        msg: msg,
	        buttons: Ext.Msg.OK,
	        icon: Ext.Msg.INFO,
	        maxWidth: 600
	    });
	},
	showError: function(msg) {
	    Ext.Msg.show({
	        title: "错误",
	        msg: msg,
	        buttons: Ext.Msg.OK,
	        icon: Ext.Msg.ERROR,
	        maxWidth: 600
	    })
	}
});