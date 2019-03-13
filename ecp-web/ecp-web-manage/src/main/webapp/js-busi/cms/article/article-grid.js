$(function(){
	article.init();
});

var article = {
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
	        "sAjaxSource": $webroot + 'article/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"id","bVisible":false,"bSortable":true},
				{ "mData": "articleTitle", "sTitle":"标题","bSortable":false,"mRender": function(data,type,row){
					return "<p style='max-width:250px;word-wrap:break-word;'>"+data+"</p>";
				}},
				{ "mData": "thumbnailUrl", "sTitle":"缩略图片","sWidth":"160px","bSortable":false,"mRender": function(data,type,row){
					return "<img src='"+data+"' width='160' height='80'>";
				}},		
				{ "mData": "siteZH", "sTitle":"所属站点","bVisible":true,"sWidth":"100px","bSortable":false},
				{ "mData": "channelZH", "sTitle":"所属栏目","bVisible":true,"sWidth":"100px","bSortable":false},
				{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"80px","bSortable":false},
				{ "mData": "istopZH", "bVisible":true,"sTitle":"是否置顶","sWidth":"100px","bSortable":false},
				{ "mData": "homepageIsShowZH", "bVisible":true,"sTitle":"首页是否显示","sWidth":"100px","bSortable":false},
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"180px","bSortable":false,"sClass": "left",
					"mRender": function(data,type,row){
						if(row.status == "0"){//无效
							return "<a href='javascript:void(0)' onclick='article.showByID("+data+")'>查看</a> | <a href='javascript:void(0)' onclick='article.changeStatus("+data+",\"1\")'>发布</a> | <a href='javascript:void(0)' onclick='article.edit("+data+")'>编辑</a> | <a href='javascript:void(0)' onclick='article.deleteByID("+data+")'>使失效</a>";
						}else if(row.status == "1"){//有效
							return "<a href='javascript:void(0)' onclick='article.showByID("+data+")'>查看</a> | <a href='javascript:void(0)' onclick='article.changeStatus("+data+",\"0\")'>撤消</a>";
						}else{//使失效
							return "<a href='javascript:void(0)' onclick='article.showByID("+data+")'>查看</a>";
						}
					}
				}
	        ],
	        "eDbClick" : function(){
	        	//edit();
	        }
		});
		//绑定查询按钮
		$('#btnFormSearch').click(function(){
			if(!$("#searchForm").valid()) return false;
			var p = ebcForm.formParams($("#searchForm"));
			//p.push({'name':'channelId','value':$("#channelId").attr('channelId')});
			$('#dataGridTable').gridSearch(p);
		});
		//绑定重置按钮
		$('#btnFormReset').click(function(){
			//$("#channelId").attr('channelId','');
			ebcForm.resetForm('#searchForm');
		});
		//绑定添加按钮
		$('#btn_code_add').click(function(){
			eNav.setSubPageText('新增文章');
			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var params = {
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'article/add',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定编辑按钮
		$('#btn_code_edit').click(function(){
			article.edit();
		});
		//绑定使失效按钮
		$('#btn_code_del').click(function(){
			article.deleteBatch();
		});
		//绑定生效按钮
		$('#btn_code_up').click(function(){
			article.changeStatusBatch("1");
		});
		//绑定失效按钮
		$('#btn_code_down').click(function(){
			article.changeStatusBatch("0");
		});
		//绑定批量使失效按钮
		$('#btn_code_batch_del').click(function(){
			article.deleteBatch();
		});
		
		//绑定选择所属栏目
		$("#channelName").live('click',function(){
			var $this = $(this);
			bDialog.open({
				title : '选择栏目',
				width : 340,
				height : 565,
				url : GLOBAL.WEBROOT + '/cms/channel/openchannel?siteId='+$('#siteId').val(),
				params : {
					'param' : "",
					'siteId' : $("#siteId").val(),
					'checkType':"radio"
				},
				callback:function(data){
					if(data && data.results && data.results.length > 0 ){
						$this.parent().find('input[id="channelId"]').val(data.results[0].param);
						$this.parent().find('input[id="channelName"]').val(data.results[0].stringShow);
					} 
				}
			});
		});
	
	},
	
	edit : function(id){   //编辑
		$.gridLoading({"message":"正在加载中...."});
		eNav.setSubPageText('编辑文章');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
				"id":id,
				"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'article/edit',
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
						url : $webroot + 'article/delete',
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
		$.gridUnLoading();
	},
	deleteBatch : function(){   //批量使失效
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
						url : $webroot + 'article/delete',
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
	changeStatus : function(id,status){ //生效、失效
		var param = {
				'ids':id,
				'status':status
		};
		$.eAjax({
			url : $webroot + "article/changestatus",
			type : "POST",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在加载中...."});
			},
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					var text = "发布成功！";
					if(status == "0"){
						text = "撤销成功！";
					}else{
						text = "发布成功！";
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
	changeStatusBatch : function(status){ //批量生效、失效 
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
			url : $webroot + "article/changestatus",
			type : "POST",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在加载中...."});
			},
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					var text = "发布成功！";
					if(status == "0"){
						text = "撤销成功！";
					}else{
						text = "发布成功！";
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
	//查看详情
	showByID : function (id) {
		eNav.setSubPageText('查看文章');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
				"id":id,
				"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'article/view',
			"params" : params,
			"method" :"post"
		});
	}
};
