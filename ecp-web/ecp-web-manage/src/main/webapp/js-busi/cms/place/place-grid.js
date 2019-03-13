$(function(){
	place.init();
});

var place = {
	init : function(){//初始化
		//初始化查询条件，回填值
		SearchObj.initSearchParams({paramsId:"searchParams",formId:"searchForm"});
		
		//初始化列表
		var params = ebcForm.formParams($("#searchForm"));
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheckColumn' : false, //是否显示单选/复选框的列
	        //'pCheck' : "multi",
	        'params' : params,
	        "sAjaxSource": $webroot + 'place/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"50px","bSortable":true},
				{ "mData": "placeName", "sTitle":"位置名称","bSortable":false},	
				{ "mData": "siteZH", "sTitle":"所属站点","bVisible":true,"sWidth":"100px","bSortable":false},
				{ "mData": "templateZH", "sTitle":"所属模板","bVisible":true,"sWidth":"100px","bSortable":false},
				{ "mData": "placeTypeZH", "sTitle":"位置类型","bVisible":true,"sWidth":"100px","bSortable":false},
				//{ "mData": "linkUrl", "sTitle":"更多链接","sWidth":"80px","bSortable":false},
				//{ "mData": "placeCount", "sTitle":"显示数量","sWidth":"100px","bSortable":false},
				//{ "mData": "placeBgcolor", "sTitle":"背景颜色","sWidth":"80px","bSortable":false},
				{ "mData": "placeWidth", "sTitle":"宽度","sWidth":"80px","bSortable":false},
				{ "mData": "placeHeight", "sTitle":"高度","sWidth":"80px","bSortable":false},
				{ "mData": "placeSize", "sTitle":"大小","sWidth":"80px","bSortable":false},
				//{ "mData": "isscrollZH", "sTitle":"是否滚动","sWidth":"80px","bSortable":false},
				{ "mData": "sortNo", "sTitle":"排序","sWidth":"70px","bSortable":false},
				{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"80px","bSortable":false},
				/*{ "mData": "createTime", "sTitle":"创建时间","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
				}},*/
				{
					"targets": -1,"mData": "id","sTitle":"操作","sWidth":"180px","sClass": "left","bSortable":false,
					"mRender": function(data,type,row){
						var optionview=" <a href='javascript:void(0)' onclick='place.viewPlace("+row.id+")'>查看</a>";
						if(row.status == "0"){//无效
							return optionview+" | <a href='javascript:void(0)' onclick='place.changeStatus("+data+",\"1\")'>发布</a> | <a href='javascript:void(0)' onclick='place.edit("+data+")'>编辑</a> | <a href='javascript:void(0)' onclick='place.deleteByID("+data+")'>使失效</a>";
						}else if(row.status == "1"){//有效
							return optionview+" | <a href='javascript:void(0)' onclick='place.changeStatus("+data+",\"0\")'>撤销</a>";
						}else{//使失效
							return ""+optionview;
						}
					}
				}
	        ],
	        "eDbClick" : function(){
//	        	modifyBiz();
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
			eNav.setSubPageText('新增内容位置');
			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var params = {
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'place/add',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定更多按钮
		$("#btn_code_more").on("click",function(){
			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var params = {
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'place/more',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定使失效按钮
		$('#btn_code_del').click(function(){
			place.deleteBatch();
		});
		//绑定生效按钮
		$('#btn_code_up').click(function(){
			place.changeStatusBatch("1");
		});
		//绑定失效按钮
		$('#btn_code_down').click(function(){
			place.changeStatusBatch("0");
		});
		//绑定批量使失效按钮
		$('#btn_code_batch_del').click(function(){
			place.deleteBatch();
		});
		//站点来匹配模板
		$('#siteId').change(function(){
			place.changeSite();
		});
		//隐藏错误
		$('#btnShowError').click(function(){
			$('div.formValidateMessages').slideToggle('fast');
		});
	},
	viewPlace : function(id){//cha kan
		eNav.setSubPageText('查看内容位置');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
				"id":id,
				"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'place/view',
			"params" : params,
			"method" :"post"
		});
	},
	edit : function(id){ //编辑
		eNav.setSubPageText('编辑内容位置');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
				"id":id,
				"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'place/edit',
			"params" : params,
			"method" :"post"
		});
	},
	changeSite : function(){//站点来匹配模板
		var siteId = $('#siteId').val();
		$.eAjax({
			url : $webroot + 'place/changeSite',
			data : {
				"siteId":siteId,
				"templateClass":"",
				"status":"1"
			},
			success : function(returnInfo){
				$("#templateId").html("");
				$("#templateId").append("<option value= ''>--请选择--</option>");
				for(var info in returnInfo){
					var option = "<option value ="+"\""+returnInfo[info].id+"\""+">"+returnInfo[info].templateName+"</option>";
					$("#templateId").append(option);
				}
			}
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
						url : $webroot + 'place/delete',
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
						url : $webroot + 'place/delete',
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
			url : $webroot + "place/changestatus",
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
			url : $webroot + "place/changestatus",
			type : "POST",
			data : param,
			datatype:'json',
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
