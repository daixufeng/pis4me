Ext.onReady(function(){
	var store = new Ext.data.JsonStore({
		url: '/Cost/GetCost.action',
		root: 'ResponseObject',
		fields:['costId', 'costDate','kind','cost', 'remark'],
		listeners: {
			beforeLoad: function(st, op){
				//st.baseParams = re;
				st.proxy.conn.jsonData = {
					params:{
						MCost: {}
					}
				};
			}
		}		
	})
	
	
	var renderDate = function(val){
		return val.format('Y-m-d');
	}
	
	var sm = new Ext.grid.CheckboxSelectionModel();
	
	new Ext.Viewport({
		layout: 'fit',
		items: {
			layout: 'border',
			title: '科目设置',
			tbar: [{
				text: '保存',
				iconCls: 'save-icon',
				handler: function(){
				
				}
			},{
				text: '删除',
				iconCls: 'delete-icon',
				handler: function(){
				
				}
			}],
			
			items: [{
				//border: false,
				region: 'center',
				xtype: 'grid',
				store: store,
				sm: sm,
				autoExpandColumn: 'remark',
				columns: [
					new Ext.grid.RowNumberer(),
					sm,
					{header: '日期', renderer: renderDate, dataIndex: 'costDate'},
					{header: '类别', dataIndex: 'kind'},
					{header: '费用', dataIndex: 'cost'},
					{id:'remark', header: '备注', dataIndex: 'remark'}
				]
			}]
		}		
	})
	
})