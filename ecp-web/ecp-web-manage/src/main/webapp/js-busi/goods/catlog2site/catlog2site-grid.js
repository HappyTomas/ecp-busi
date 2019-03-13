$(function(){
	site.init();
});

var site = {
	init : function(){//初始化
		//初始化查询条件，回填值
		SearchObj.initSearchParams({paramsId:"searchParams",formId:"searchForm"});
		
		//初始化列表
		var params = ebcForm.formParams($("#searchForm"));
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheckColumn' : false, //是否显示单选/复选框的列
	        'params' : params,
	        "sAjaxSource": $webroot + '/goods/catlog2site/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"50px","bSortable":true},
				{ "mData": "siteName", "sTitle":"站点名称","sWidth":"100px","bSortable":false},
				{ "mData": "siteUrl", "sTitle":"访问路径","sWidth":"250px","bSortable":false},	
				{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"100px","bSortable":false},
				{ "mData": "isdefaultZH", "bVisible":true,"sTitle":"是否默认站点","sWidth":"100px","bSortable":false},
				{ "mData": "catlogNames", "sTitle":"关联目录","sWidth":"120px","bSortable":false,"mRender": function(data,type,row){
					var showData = "";
					if(data!==null&&data.length>10){
						showData = data.substring(0,10) + "...";
					}else{
						showData = data;
					}
					var castStr = '<a title="'+data+'" href="javascript:void(0)" >'+showData+'</a><input type="hidden" value="'+row.rule+'" sysCode="'+row.sysCode+'" />';
					return castStr;
				}},
				{ "mData": "id", "sTitle":"操作","sWidth":"80px","bSortable":false,"mRender":function(data,type,row){
					return '<a href="javascript:void(0)"  onClick="catlog2SiteEdit.relation('+row.id+')">编辑</a>';
				}}
	        ],
	        "eDbClick" : function(){
	        	//modifyBiz();
	        }
		});
		//绑定查询按钮
		$('#btnFormSearch').click(function(){
			if(!$("#searchForm").valid()) return false;
			$.gridLoading({"message":"正在加载中...."});
			var p = ebcForm.formParams($("#searchForm"));
			$('#dataGridTable').gridSearch(p);
			$.gridUnLoading();
		});
		//绑定重置按钮
		$('#btnFormReset').click(function(){
			ebcForm.resetForm('#searchForm');
		});
	}
};
var catlog2SiteEdit = {
		/**
		 * 与目录关联
		 */
		relation:function(siteId){
			bDialog.open({
		        title : '关联目录',
		        width : 800,
		        height : 500,
		        params:{multi : true,showRoot : false},
		        url : GLOBAL.WEBROOT+"/goods/catlog2site/edit?siteId="+siteId,
		        callback:function(data){
		        	if(data && data.results && data.results.length > 0 ){
		                var _relations = data.results[0].relations;
		                catlog2SiteEdit.saveRelation(siteId, _relations);
					}
		        }
		    });
		  },
		  saveRelation:function(siteId,relations){
			  var prompt = "";
			  if("" == relations){
				  prompt = "您确定要清空目录关联关系吗?";
			  }else{
				  prompt = "您确定要修改该站点的目录关联关系吗?";
			  }
			  ////////////
			  eDialog.confirm(prompt,{
					buttons:[{
						caption:"确定",
						callback:function(){
							 $.eAjax({
									url : GLOBAL.WEBROOT+"/goods/catlog2site/save",
									type : "POST",
									cache : false,
									timeout : 5000,
									data : {"siteId":siteId,"catlogIds":relations},
									success : function(json) {
									    if(null != json && 'ok' == json.resultFlag){
									    	eDialog.success('目录与站点关联关系维护成功!');
									    	$('#btnFormSearch').trigger('click');
									    }else if(null != json && 'fail' == json.resultFlag){
									    	eDialog.error(json.resultMsg);
									    }else{
									    	eDialog.error('站点与目录关联维护异常!');
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