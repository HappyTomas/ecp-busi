$(function(){
	pageType.init();
});

var pageType = {
	"init" : function(){//初始化
		//初始化查询条件，回填值
		SearchObj.initSearchParams({paramsId:"searchParams",formId:"searchForm"});
		var params = ebcForm.formParams($("#searchForm"));
		
		//初始化列表
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheckColumn' : false, //是否显示单选/复选框的列
	        'params' : params,
	        "sAjaxSource": $webroot + 'pageType/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"50px","bSortable":true},
	        	{ "mData": "pageTypeName", "sTitle":"页面类型名称","bSortable":false},	
	        	{ "mData": "platformTypeZH", "sTitle":"平台类型","sWidth":"100px","bSortable":false},	
	        	{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"100px","bSortable":false},
				{ "mData": "createTime", "sTitle":"创建时间","sWidth":"160px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
				}},
				{ "mData": "updateTime", "sTitle":"修改时间","sWidth":"160px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
				}},
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"180px","sClass": "left","bSortable":false,
					"mRender": function(data,type,row){
						var preViewOption="<a href='javascript:void(0)' onclick='pageType.editview("+data+","+row.status+",2)'>查看</a>";
						if(row.status == "1"){//已发布
							return "<a href='javascript:void(0)' onclick='pageType.editview("+data+","+row.status+",1)'>编辑</a> | "+preViewOption+" | <a href='javascript:void(0)' onclick='pageType.cancelPublish("+row.id+",0)'>撤销</a>";
						}else if(row.status == "0"){//有效
							return "<a href='javascript:void(0)' onclick='pageType.editview("+data+","+row.status+",0)'>编辑</a> | "+preViewOption+" | <a href='javascript:void(0)' onclick='pageType.changeStatus("+data+",1)'>发布</a> | <a href='javascript:void(0)' onclick='pageType.invalid("+data+",2)'>使失效</a>";
						}else{//已失效
							return preViewOption;
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
			eNav.setSubPageText('新增页面类型');
			window.location.href = GLOBAL.WEBROOT+'/pageType/editview';
		});
	},
	"editview" : function(id,status,isRead){ //编辑
		if(isRead==2){
			eNav.setSubPageText('查看页面类型');
		}else{
			eNav.setSubPageText('编辑页面类型');
		}
		window.location.href = GLOBAL.WEBROOT+'/pageType/editview?id='+id+'&status='+status+'&isRead='+isRead;
	},
	"invalid":function(id,status){
		eDialog.confirm("您确认将该记录失效吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					pageType.changeStatus(id,status);
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
	},
	"cancelPublish":function(id,status){
		var param = {
				'id':id
		  };
		var b=false;
		$.eAjax({
			url : $webroot + "pageType/isCancle",
			async:false,
			type : "POST",
			data : param,
			datatype:'json',
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					b=true;
				}
			},error : function(e,xhr,opt){
				eDialog.error("出现异常!");
			}
		});
		if(b){
			pageType.changeStatus(id,status);
		}else{
			eDialog.warning("该页面类型已被页面或者模块使用，不允许撤销！");
		}
	},
	"changeStatus":function(id,status){
		 //生效、失效
		var param = {
				'id':id,
				'status':status
		};
		$.eAjax({
			url : $webroot + "pageType/changestatus",
			type : "POST",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在加载中...."});
			},
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					var text = "";
					if(status == "0"){
						text = "撤销成功！";
					}else if(status == "1"){
						text = "发布成功！";
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
