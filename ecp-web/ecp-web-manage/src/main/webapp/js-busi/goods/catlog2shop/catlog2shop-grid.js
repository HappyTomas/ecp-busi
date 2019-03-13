$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pCheckColumn' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/goods/catlog2shop/gridlist',
        "params":[{name:"companyId",value:$('#ids').val()}],
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"店铺编码","sWidth":"80px","bSortable":false},      
            { "mData": "shopName", "sTitle":"店铺名称","sWidth":"80px","bSortable":false},    
            { "mData": "shopFullName", "sTitle":"店铺全称","sWidth":"80px","bSortable":false,"bVisible":false}, 
			{ "mData": "shopStatus", "sTitle":"店铺状态","sWidth":"20px","bSortable":false,"mRender": function(data,type,row){
				if(data=='1'){
					return '有效';
				}else{
					return '失效';
				}
			}},
			{ "mData": "hotShowSort", "sTitle":"是否热门店铺","sWidth":"80px","bSortable":false,"bVisible":false,"mRender": function(data,type,row){
				if(data=='1'){
					return '是';
				}else{
					return '否';
				}
			}},
			{ "mData": "companyName", "sTitle":"所属企业","sWidth":"80px","bSortable":false},
			{ "mData": "catlogNames", "sTitle":"关联目录","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				var showData = "";
				//var aruleId = 'ruleBtn'+row.DT_RowId;
				if(data!==null&&data.length>10){
					showData = data.substring(0,10) + "...";
				}else{
					showData = data;
				}
				var castStr = '<a title="'+data+'" href="javascript:void(0)" >'+showData+'</a><input type="hidden" value="'+row.rule+'" sysCode="'+row.sysCode+'" />';
				return castStr;
			}},
			{ "mData": "id", "sTitle":"操作","sWidth":"80px","bSortable":false,"mRender":function(data,type,row){
				return '<a href="javascript:void(0)"  onClick="catlog2ShopEdit.relation('+row.id+')">编辑</a>';
			}}
        ]
	});
	/**
	 * 搜索按钮绑定点击事件.
	 */
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	/**
	 * 重置按钮绑定重置事件.
	 */
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
	
	
});

var catlog2ShopEdit = {
		/**
		 * 与目录关联
		 */
		relation:function(shopId){
			bDialog.open({
		        title : '关联目录',
		        width : 800,
		        height : 500,
		        params:{multi : true,showRoot : false},
		        url : GLOBAL.WEBROOT+"/goods/catlog2shop/edit?shopId="+shopId,
		        callback:function(data){
		        	if(data && data.results && data.results.length > 0 ){
		                var _relations = data.results[0].relations;
		                catlog2ShopEdit.saveRelation(shopId, _relations);
					}
		        }
		    });
		  },
		  saveRelation:function(shopId,relations){
			  var prompt = "";
			  if("" == relations){
				  prompt = "您确定要清空目录关联关系吗?";
			  }else{
				  prompt = "您确定要修改该店铺的目录关联关系吗?";
			  }
			  ////////////
			  eDialog.confirm(prompt,{
					buttons:[{
						caption:"确定",
						callback:function(){
							 $.eAjax({
									url : GLOBAL.WEBROOT+"/goods/catlog2shop/save",
									type : "POST",
									cache : false,
									timeout : 5000,
									data : {"shopId":shopId,"catlogIds":relations},
									success : function(json) {
									    if(null != json && 'ok' == json.resultFlag){
									    	eDialog.success('目录与店铺关联关系维护成功!');
									    	$('#btnFormSearch').trigger('click');
									    }else if(null != json && 'fail' == json.resultFlag){
									    	eDialog.error(json.resultMsg);
									    }else{
									    	eDialog.error('店铺与目录关联维护异常!');
									    }
									}
								});
						}
				},{
						caption:'取消'
				}]
				});
			  ////////////
		  }
		};