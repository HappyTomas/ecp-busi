//页面信息分页列表页面JS处理
$(function(){
	//初始化查询条件，回填值
	SearchObj.initSearchParams({paramsId:"searchParams",formId:"searchForm"});
	
	//初始化列表，由于站点，模板，内容位置特殊，进行重置数据处理
	var params = ebcForm.formParams($("#searchForm"));
	for(var index in params){
		if(params.hasOwnProperty(index) && params[index]){
			var name = params[index].name;
			if("siteId"==name || "templateId"==name || "placeId"==name){
				params[index] = ({'name':name,'value':$("#"+name).attr('attrId')});
			}
		}
	}
	
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false, //是否显示单选/复选框的列
        'params' : params,
        "sAjaxSource": $webroot + 'info/gridlist',
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"编码","bVisible":false},
			{ "mData": "infoTitle", "sTitle":"标题","bSortable":true},
			{ "mData": "siteName", "sTitle":"所属站点","bVisible":true,"sWidth":"100px","bSortable":false},
			{ "mData": "templateName", "sTitle":"所属模板","bVisible":true,"sWidth":"100px","bSortable":false},
			{ "mData": "placeName","bVisible":true, "sTitle":"内容位置","sWidth":"100px","bSortable":false},
			{ "mData": "typeName", "sTitle":"信息类型","sWidth":"100px","bSortable":false},
			{ "mData": "statusName", "sTitle":"状态","sWidth":"80px","bSortable":false},
			{ "mData": "pubTime", "sTitle":"发布时间","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "lostTime", "sTitle":"失效时间","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "id","sTitle":"操作","sWidth":"180px","bSortable":false,"sClass": "left","mRender": function(data,type,row){
				var updatestr="<a href='javascript:void(0)' onclick='edit("+row.id+")'>编辑</a>";
				var checkstr="<a href='javascript:void(0)' onclick='viewCmsInfo("+row.id+")'>查看</a>";
				var delstr="<a href='javascript:void(0)' onclick='deleteCmsInfo("+row.id+")'>使失效</a>";
				var actstr="<a href='javascript:void(0)' onclick='activeCmsInfo("+row.id+")'>发布</a>";
				var inactstr="<a href='javascript:void(0)' onclick='invalidCmsInfo("+row.id+")'>撤消</a>";
				var midstr="&nbsp;|&nbsp;";
				var retstr="";
				if(row.status=="1"){//有效
					retstr=checkstr+midstr+inactstr;
				}
				if(row.status=="0"){//失效
					retstr=checkstr+midstr+actstr+midstr+updatestr+midstr+delstr;
				}
				if(row.status=="2"){//使失效
					retstr=checkstr;
				}
				return retstr;
			}}
        ],
        //选中一条数据后双击事件
        "eDbClick" : function(){
        	//modifyBiz();
        }
	});
	
	//新增按钮点击事件，跳转到新增页面
	$('#btn_code_add').click(function(){
		eNav.setSubPageText('新增信息');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'info/infoadd',
			"params" : params,
			"method" :"post"
		});
	});
	
	//修改按钮点击事件
	$('#btn_code_modify').click(function(){
		modifyBiz();
	});
	
	//查询按钮点击事件
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		$.gridLoading({"message":"正在加载中...."});
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
		$.gridUnLoading();
	});
	
	//重置按钮点击事件
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
});

//修改编辑页面信息方法
function modifyBiz(){
	var ids = $('#dataGridTable').getCheckIds();
	if(ids && ids.length==1){
		var status=$("#dataGridTable").getSelectedData()[0].status;
		if(status=="2"){
			eDialog.alert('该页面信息状态为使失效状态，不允许修改！');
		}else{
			//window.location.href = $webroot+'info/infoedit?id='+ids[0];
			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var params = {
				"id":ids[0],
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'info/infoedit',
				"params" : params,
				"method" :"post"
			});
		}
	}else if(ids && ids.length>1){
		eDialog.alert('只能选择一个项目进行操作！');
	}else if(!ids || ids.length==0){
		eDialog.alert('请选择至少选择一个项目进行操作！');
	}
};

//修改编辑页面信息方法
function edit(id){
	$.gridLoading({"message":"正在加载中...."});
	eNav.setSubPageText('编辑信息');
	var searchParams = SearchObj.getFormParam($("#searchForm"));
	var params = {
		"id":id,
		"searchParams":searchParams
	}
	SearchObj.openPage({
		"url" : $webroot+'info/infoedit',
		"params" : params,
		"method" :"post"
	});
};

//使失效
function deleteCmsInfo(id){
	
	eDialog.confirm("您确定要将该页面信息使失效吗？", {
		buttons : [{
			caption : '确认',
			callback : function(){
				$.eAjax({
					url : $webroot + "info/infodelete?id="+id,
					data : ebcForm.formParams($("#detailInfoForm")),
					beforeSend:function(){
						$.gridLoading({"message":"正在加载中...."});
					},
					success : function(returnInfo) {
						$.gridUnLoading();
						eDialog.success('页面信息使失效成功！'); 
						//跳转到页面信息列表查询页面
//						window.location.href = $webroot + 'info/infogrid';
						$('#dataGridTable').gridReload();
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

};

//发布
function activeCmsInfo(id){
//	$.gridLoading({"message":"正在加载中...."});
	$.eAjax({
		url : $webroot + "info/infoactive?id="+id,
		data : ebcForm.formParams($("#detailInfoForm")),
		success : function(returnInfo) {
			eDialog.success('发布成功！'); 
			//跳转到页面信息列表查询页面
//			window.location.href = $webroot + 'info/infogrid';
			$('#dataGridTable').gridReload();
		}
	});
};

//撤销
function invalidCmsInfo(id){
	eDialog.confirm("您确定撤销吗？", {
		buttons : [{
			caption : '确认',
			callback : function(){
				$.eAjax({
					url : $webroot + "info/infoinvalid?id="+id,
					data : ebcForm.formParams($("#detailInfoForm")),
					success : function(returnInfo) {
						eDialog.success('使页面信息撤消成功！'); 
						//跳转到页面信息列表查询页面
	//					window.location.href = $webroot + 'info/infogrid';
						$('#dataGridTable').gridReload();
					}
				});
			}
		},{
			caption : '取消',
			callback : $.noop
		}]
	});
};

function viewCmsInfo(id){
	//window.location.href = $webroot+'info/infoview?id='+id;
	eNav.setSubPageText('查看信息');
	var searchParams = SearchObj.getFormParam($("#searchForm"));
	var params = {
		"id":id,
		"searchParams":searchParams
	}
	SearchObj.openPage({
		"url" : $webroot+'info/infoview',
		"params" : params,
		"method" :"post"
	});
};
