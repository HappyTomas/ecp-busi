$(function(){
	siteInfo.init();
});

var siteInfo = {
	init : function(){//初始化
		//初始化查询条件，回填值
		SearchObj.initSearchParams({paramsId:"searchParams",formId:"searchForm"});
		
		var params = ebcForm.formParams($("#searchForm"));
		//初始化列表
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheckColumn' : false, //是否显示单选/复选框的列
	        'pCheck' : "multi",
	        "sAjaxSource": $webroot + 'siteinfo/gridlist',
	        'params' : params, //后台查询参数，参数格式：[{name:'a',value:'aaa'},{name:'b',value:'bbb'},{……}]
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","sWidth":"40px","bSortable":false},
	        	{ "mData": "parentName", "bVisible":true,"sTitle":"父级信息","sWidth":"100px","bSortable":false},
				{ "mData": "siteInfoName", "sTitle":"信息名称","sWidth":"100px","bSortable":false},
				{ "mData": "siteZH", "sTitle":"所属站点","bVisible":true,"sWidth":"100px","bSortable":false},
				{ "mData": "channelZH", "sTitle":"所属栏目","bVisible":true,"sWidth":"100px","bSortable":false},
				{ "mData": "siteInfoTypeZH", "bVisible":true,"sTitle":"网站信息类型","sWidth":"100px","bSortable":false},
				{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"80px","bSortable":false},
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"180px","bSortable":false,"sClass": "left",
					"mRender": function(data,type,row){
						if(row.status == "0"){//无效
							return "<a href='javascript:void(0)' onclick='siteInfo.showByID("+data+")'>查看</a> | <a href='javascript:void(0)' onclick='siteInfo.pubstatus("+data+")'>发布</a> | <a href='javascript:void(0)' onclick='siteInfo.edit("+data+")'>编辑</a> | <a href='javascript:void(0)' onclick='siteInfo.deleteByID("+data+")'>使失效</a>";
						}else if(row.status == "1"){//有效
							return "<a href='javascript:void(0)' onclick='siteInfo.showByID("+data+")'>查看</a>| <a href='javascript:void(0)' onclick='siteInfo.limitedit("+data+")'>编辑</a> | <a href='javascript:void(0)' onclick='siteInfo.cancelstatus("+data+","+row.parent+")'>撤消</a>";
						}else{//使失效
							return "<a href='javascript:void(0)' onclick='siteInfo.showByID("+data+")'>查看</a>";
						}
					}
				}
	        ],
	        "eDbClick" : function(){
	        }
		});
		//绑定站点变动事件
		$("#siteId").bind("change",function(){
			$("#parent").val("");
			siteInfo.initParentSelect($(this).val());
		});
		//初始化父级信息
		siteInfo.initParentSelect($("#siteId").val());
		$("#parent-select").bind("change",function(){
			$("#parent").val($(this).val());
		});
		//绑定查询按钮
		$('#btnFormSearch').click(function(){
			if(!$("#searchForm").valid()) return false;
			var p = ebcForm.formParams($("#searchForm"));
			$('#dataGridTable').gridSearch(p);
		});
		//绑定重置按钮
		$('#btnFormReset').click(function(){
			ebcForm.resetForm('#searchForm');
		});
		//绑定添加按钮
		$('#btn_code_add').click(function(){
			eNav.setSubPageText('新增网站信息');
			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var params = {
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'siteinfo/add',
				"params" : params,
				"method" :"post"
			});
		});
	},
	edit : function(id){   //编辑
		$.gridLoading({"message":"正在加载中...."});
		eNav.setSubPageText('编辑网站信息');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"id":id,
			"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'siteinfo/edit',
			"params" : params,
			"method" :"post"
		});
	},
	limitedit : function(id){   //限制编辑  在发布状态下的编辑
		$.gridLoading({"message":"正在加载中...."});
		eNav.setSubPageText('编辑网站信息');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"id":id,
			"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'siteinfo/limitedit',
			"params" : params,
			"method" :"post"
		});
	},
	deleteByID : function(id){   //使失效
		var param = {
				'id':id
		};
		eDialog.confirm("您确认使失效该记录吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					$.eAjax({
						url : $webroot + 'siteinfo/delstatus',
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
							}else{
								var text = returnInfo.resultMsg?returnInfo.resultMsg:"操作失败";
								eDialog.alert(text); 
							}
						},error : function(e,xhr,opt){
							eDialog.error("出现异常!");
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
	pubstatus : function(id){ //生效
		var param = {
				'id':id,
		};
		$.eAjax({
			url : $webroot + "siteinfo/pubstatus",
			type : "POST",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在加载中...."});
			},
			success : function(returnInfo) {
				returnInfo = returnInfo ? returnInfo :{};
				if(returnInfo.resultFlag=='ok'){
					eDialog.success("发布成功"); 
				}else{
					var text = returnInfo.resultMsg?returnInfo.resultMsg:"发布失败";
					eDialog.alert(text); 
				}
				$('#dataGridTable').gridReload();
			},error : function(e,xhr,opt){
				eDialog.error("出现异常!");
			},complete:function(){
				$.gridUnLoading();
			}
		});
	},
	cancelstatus : function(id,parent){ //失效
		if("0" == parent+""){
			eDialog.confirm("该网站信息为父级信息，撤销会同时撤销其子级信息，确认撤销？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						siteInfo.doCancelStatus(id);
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		}else{
			siteInfo.doCancelStatus(id);
		}
	},
	doCancelStatus:function(id){
		var param = {
				'id':id,
		};
		$.eAjax({
			url : $webroot + "siteinfo/cancelstatus",
			type : "POST",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在加载中...."});
			},
			success : function(returnInfo) {
				returnInfo = returnInfo ? returnInfo :{};
				if(returnInfo.resultFlag=='ok'){
					eDialog.success("撤销成功"); 
				}else{
					var text = returnInfo.resultMsg?returnInfo.resultMsg:"撤销失败";
					eDialog.alert(text); 
				}
				$('#dataGridTable').gridReload();
			},error : function(e,xhr,opt){
				eDialog.error("出现异常!");
			},complete:function(){
				$.gridUnLoading();
			}
		});
	},
	//查看详情
	showByID : function (id) {
		eNav.setSubPageText('查看网站信息');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"id":id.toString(),
			"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'siteinfo/view',
			"params" : params,
			"method" :"post"
		});
	},
	initParentSelect : function(siteId){
		var $selector = $("#parent-select");
		$selector.html('<option value="" >--请选择--</option>');
		if(siteId || 0 === siteId){
			$.eAjax({
				url : $webroot + "siteinfo/getTopSiteInfo",
				type : "POST",
				data : {"siteId":siteId},
				datatype:'json',
				success : function(returnInfo) {
					var datas = null;
					if(returnInfo.code=='ok'){
						datas = returnInfo.datas;
					}
					if(datas && 0 < datas.length){
						var parent = $("#parent").val();
						for(var i in datas){
							var data = datas[i];
							var selectedStr = "";
							if(data && (data.id || 0 === data.id)){
								data.id = data.id +"";
								selectedStr = data.id == parent ?' selected="selected" ':"";
								$selector.append('<option value="'+data.id+'" '+selectedStr+'>'+data.siteInfoName+'</option>');
							}
						}
					}
				}
			});
		}
	}
};
