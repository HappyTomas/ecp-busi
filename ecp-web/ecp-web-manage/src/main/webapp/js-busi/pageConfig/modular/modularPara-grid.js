$(function(){
	modularPara.init();
});

var modularPara = {
	"init" : function(){//初始化
		//初始化查询条件，回填值
		SearchObj.initSearchParams({paramsId:"searchParams",formId:"searchForm"});
		var params = ebcForm.formParams($("#searchForm"));
		var modularId=$('#modularId').val();
		//初始化列表
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheckColumn' : false, //是否显示单选/复选框的列
//	        'pCheck' : "multi",
	        'params' : params,
	        "sAjaxSource": $webroot + 'modularPara/gridlist?modularId='+modularId,
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"50px","bSortable":true},
	        	{ "mData": "propId", "sTitle":"属性ID","bVisible":true,"sWidth":"50px","bSortable":true},
	        	{ "mData": "propName", "sTitle":"属性名称","bSortable":false},	
	        	{ "mData": "propValueTypeZH", "sTitle":"属性值类型","bSortable":false},	
	        	{ "mData": "sortNo", "sTitle":"排序号","sWidth":"80px","bSortable":false},	
	        	{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"100px","bSortable":false},
				{ "mData": "createTime", "sTitle":"创建时间","sWidth":"140px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
				}},
				{ "mData": "updateTime", "sTitle":"修改时间","sWidth":"140px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
				}},
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"180px","sClass": "left","bSortable":false,
					"mRender": function(data,type,row){
						//isValid 0可以编辑 1只能查看
						var isValid='0';
						if($('#isValid').val()&&$('#isValid').val()!=''){
							isValid=$('#isValid').val();
						}
						var preViewOption="<a href='javascript:void(0)' onclick='modularPara.editview("+row.propId+","+row.modularId+")'>查看</a>";
						
						if(isValid==1){
							return preViewOption;
						}else{
							if(row.status != "2"){//有效
								return preViewOption+" | <a href='javascript:void(0)' onclick='modularPara.cancelPublish("+data+",2)'>使失效</a>";
							}else{//已失效
								return preViewOption;
							}
						}
						
					}
				}
	        ],
	        "eDbClick" : function(){
	        	//modifyBiz();
	        }
		});
		//返回楼层
		$('#btn_code_return').click(function(){
			var searchParams = $("#searchModularParams").val();
			var params = {
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'modular/init',
				"params" : params,
				"method" :"post"
			});
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
		//绑定选择按钮
		$('#btn_select_prop').click(function(){
			modularPara.openSelectDetail($('#modularId').val());
		});
		//绑定添加按钮
		$('#btn_code_add').click(function(){
			window.location.href = GLOBAL.WEBROOT+'/modularPara/editview?modularId='+$('#modularId').val();
		});
	},
	"editview" : function(id,modularId){ //编辑
		window.location.href = GLOBAL.WEBROOT+'/modularPara/editview?id='+id+'&isRead=1&isValid=1&modularId='+modularId;
	},
//	"editview" : function(id,status,isRead,isValid){ //编辑
//		if(1==isRead){
//			eNav.setSubPageText('查看模块属性');
//		}else{
//			eNav.setSubPageText('编辑模块属性');
//		}
//		window.location.href = GLOBAL.WEBROOT+'/modularPara/editview?id='+id+'&modularId='+$('#modularId').val()+'&status='+status+'&isRead='+isRead+'&isValid='+isValid;
//	},
	openSelectDetail : function(modularId){
		var title = "选择属性";
		var url = "modularPara/selectProp?modularId="+modularId;
		bDialog.open({
			title : title,
			width : 860,
			height : 500,
			url : $webroot + url,
			params : {
			},
			callback:function(data){
				if(data && data.results && data.results.length > 0 ){
					if(data.results[0].save==true){
						window.location.reload();
					}
				}
			}
		});
	},
	"invalid":function(id,status){
		eDialog.confirm("您确认将该记录失效吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					modularPara.changeStatus(id,status);
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
			url : $webroot + "modularPara/isCancle",
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
			modularPara.changeStatus(id,status);
		}else{
			eDialog.warning("该属性已经被其他属性选为受控属性，不允许失效！");
		}
	},
	"changeStatus":function(id,status){
		 //生效、失效
		var param = {
				'id':id,
				'status':status
		};
		$.eAjax({
			url : $webroot + "modularPara/changestatus",
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
