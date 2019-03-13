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
	        'pCheck' : "multi",
	        'params' : params,
	        "sAjaxSource": $webroot + 'site/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"50px","bSortable":true},
				{ "mData": "siteName", "sTitle":"站点名称","bSortable":false},	
				{ "mData": "siteLogo", "sTitle":"站点LOGO","sWidth":"120px","bSortable":false,"mRender": function(data,type,row){
	        		if(row.siteLogo!=null && row.siteLogo.length>0){
	        			return "<img src='"+data+"' width='120' height='50'>";
	        		}else{
	        			return "暂无图片";
	        		}
				}},
				{ "mData": "siteUrl", "sTitle":"访问路径","sWidth":"280px","bSortable":false},	
				{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"100px","bSortable":false},
				{ "mData": "isdefaultZH", "bVisible":true,"sTitle":"是否默认站点","sWidth":"150px","bSortable":false},
				{
					"targets": -1,"mData": "id","sTitle":"操作","sWidth":"250px","sClass": "left","bSortable":false,
					"mRender": function(data,type,row){
						var optiondefault,optiondelet;
						var optionview=" <a href='javascript:void(0)' onclick='site.viewSite("+row.id+")'>查看</a>";
						if(row.isdefault != 1){
							optiondefault = " | <a href='javascript:void(0)' onclick='site.changeDefault("+data+",\"1\")'>设为默认</a>";
							optiondelet = " | <a href='javascript:void(0)' onclick='site.deleteByID("+data+")'>使失效</a>";
						}
						else{
							optiondefault = " | <a href='javascript:void(0)' onclick='site.changeDefault("+data+",\"0\")'>取消默认</a>";
							optiondelet = "";
						}
						if(row.status == "0"){//无效
							return optionview+" | <a href='javascript:void(0)' onclick='site.changeStatus("+data+",\"1\")'>发布</a> | <a href='javascript:void(0)' onclick='site.edit("+data+")'>编辑</a>" +
							 optiondelet + optiondefault;
						}else if(row.status == "1"){//有效
							return optionview+" | <a href='javascript:void(0)' onclick='site.changeStatus("+data+",\"0\")'>撤销</a>";
						}else{
							return ""+optionview;
						}
					}
				}
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
		//绑定添加按钮
		$('#btn_code_add').click(function(){
			eNav.setSubPageText('新增站点');
			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var params = {
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'site/add',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定使失效按钮
		$('#btn_code_del').click(function(){
			site.deleteBatch();
		});
		//绑定生效按钮
		$('#btn_code_up').click(function(){
			site.changeStatusBatch("1");
		});
		//绑定失效按钮
		$('#btn_code_down').click(function(){
			site.changeStatusBatch("0");
		});
		//隐藏错误
		$('#btnShowError').click(function(){
			$('div.formValidateMessages').slideToggle('fast');
		});
	},
	viewSite : function(id){
		//var url = $webroot+'site/view?id='+id;
		eNav.setSubPageText('查看站点');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
				"id":id,
				"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'site/view',
			"params" : params,
			"method" :"post"
		});
	},
	edit : function(id){ //编辑
		$.gridLoading({"message":"正在加载中...."});
		eNav.setSubPageText('编辑站点');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"id":id,
			"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'site/edit',
			"params" : params,
			"method" :"post"
		});
	},
	deleteByID : function(id){   //使失效
		var param = {
				'ids':id
		};
		eDialog.confirm("您确认使失效该记录吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					$.eAjax({
						url : $webroot + 'site/delete',
						type : "POST",
						data : param,
						datatype:'json',
						beforeSend:function(){
							$.gridLoading({"message":"正在加载中...."});
						},
						success : function(returnInfo) {
							if(returnInfo.resultFlag=='ok'){
								eDialog.success('失效成功！'); 
								$('#dataGridTable').gridReload();
							}
						},error : function(e,xhr,opt){
							eDialog.error("出现异常!");
							$.gridUnLoading();
						},complete:function(){
							$.gridUnLoading();
						}
					});
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
	},
	deleteBatch : function(){   //使失效、批量使失效
		var ids = $('#dataGridTable').getCheckIds();
		if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
			return ;
		}
		var param = {
				'ids':ids.join(",")
		};
		eDialog.confirm("您确认使失效该记录吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					$.eAjax({
						url : $webroot + 'site/delete',
						type : "POST",
						data : param,
						datatype:'json',
						beforeSend:function(){
							$.gridLoading({"message":"正在加载中...."});
						},
						success : function(returnInfo) {
							if(returnInfo.resultFlag=='ok'){
								eDialog.success('使失效成功！'); 
								$('#dataGridTable').gridReload();
							}
						},error : function(e,xhr,opt){
							eDialog.error("出现异常!");
							$.gridUnLoading();
						},complete:function(){
							$.gridUnLoading();
						}
					});
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
	},
	
	changeDefault : function(id,status){ //设置默认站点
		var param = {
				'ids':id,
				'isDefault':status
		};
		$.eAjax({
			url : $webroot + "site/changedefault",
			type : "POST",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在加载中...."});
			},
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					var text = "设为默认站点成功！";
					if(status == "1"){
						text = "设为默认站点成功！";
					}else{
						text = "取消默认站点成功！";
					}
					eDialog.success(text); 
					$('#dataGridTable').gridReload();
				}
			},error : function(e,xhr,opt){
				eDialog.error("出现异常!");
				$.gridUnLoading();
			},complete:function(){
				$.gridUnLoading();
			}
		});
	},
	
	changeStatus : function(id,status){ //生效、失效
		var param = {
				'ids':id,
				'status':status
		};
		$.eAjax({
			url : $webroot + "site/changestatus",
			type : "POST",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在加载中...."});
			},
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					var text = "发布成功！";
					if(status == "1"){
						text = "发布成功！";
					}else{
						text = "撤销成功！";
					}
					eDialog.success(text); 
					$('#dataGridTable').gridReload();
				}
			},error : function(e,xhr,opt){
				eDialog.error("出现异常!");
				$.gridUnLoading();
			},complete:function(){
				$.gridUnLoading();
			}
		});
	},
	changeStatusBatch : function(status){ //生效、失效 
		var ids = $('#dataGridTable').getCheckIds();
		if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一条记录进行操作！');
			return ;
		}
		var param = {
				'ids':ids.join(","),
				'status':status
		};
		$.eAjax({
			url : $webroot + "site/changestatus",
			type : "POST",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在加载中...."});
			},
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					var text = "生效成功！";
					if(status == "1"){
						text = "生效成功！";
					}else{
						text = "失效成功！";
					}
					eDialog.success(text); 
					$('#dataGridTable').gridReload();
				}
			},error : function(e,xhr,opt){
				eDialog.error("出现异常!");
				$.gridUnLoading();
			},complete:function(){
				$.gridUnLoading();
			}
		});
	}
};
