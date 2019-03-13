$(function(){
	modularComponent.init();
});

var modularComponent = {
	"init" : function(){//初始化
		//初始化查询条件，回填值
		SearchObj.initSearchParams({paramsId:"searchParams",formId:"searchForm"});
		var params = ebcForm.formParams($("#searchForm"));
		
		//初始化列表
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheckColumn' : false, //是否显示单选/复选框的列
	        'pCheck' : "single",
	        'params' : params,
	        "sAjaxSource": $webroot + 'modularComponent/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"80px","bSortable":true},
	        	{ "mData": "modularName", "sTitle":"模块名称","bSortable":false,"mRender": function(data,type,row){
	        		var modularName=$('#modularName').val();
	        		return modularName;
	        	}},
	        	{ "mData": "modularClass", "sTitle":"模块样式","sWidth":"120px","bSortable":false},	
	        	{ "mData": "applyPageTypeZH", "sTitle":"适用页面类型","sWidth":"120px","bSortable":false},	
	        	{ "mData": "applyItemSize", "sTitle":"适用布局尺寸","sWidth":"120px","bSortable":false},	
	        	/*{ "mData": "showPic", "sTitle":"模块缩略图","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
	        		if(row.showPic!=null && row.showPic.length>0){
	        			return "<img src='"+data+"' width='50' height='50'>";
	        		}else{
	        			return "暂无图片";
	        		}
	        	}},*/
	        	{ "mData": "componentRespDTO.componentName", "sTitle":"关联组件","sWidth":"160px","bSortable":false},	
	        	{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"100px","bSortable":false},
				{ "mData": "createTime", "sTitle":"创建时间","sWidth":"140px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd");
				}},
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"160px","sClass": "left","bSortable":false,
					"mRender": function(data,type,row){
						var preViewOption="<a href='javascript:void(0)' onclick='modularComponent.editview("+data+",1)'>查看</a>";
						var configAttrOpt="";
						if(row.status == "1"){//已发布
							return configAttrOpt+preViewOption+" | <a href='javascript:void(0)' onclick='modularComponent.editview("+data+",0)'>编辑</a> | <a href='javascript:void(0)' onclick='modularComponent.invalid("+data+",2)'>使失效</a>";
						}else{//已失效
							return configAttrOpt+preViewOption;
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
			var modularId=$('#modularId').val();
			eNav.setSubPageText('配置组件');
			window.location.href = GLOBAL.WEBROOT+'/modularComponent/editview?modularId='+modularId;
		});
		//绑定属性配置按钮
		$('#btn_attr_config').click(function(){
			modularComponent.attrConfig();
		});
		//绑定组件配置按钮
		$('#btn_attr_component').click(function(){
			modularComponent.attrComponent();
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
	},
	"paraview":function(modularId,isValid){
		if(1==isValid){
//			eNav.setSubPageText('查看属性');
			eNav.setSubPageText('配置组件');
		}else{
			eNav.setSubPageText('配置组件');
		}
		window.location.href = GLOBAL.WEBROOT+'/modularComponent/init?modularId='+modularId+'&isValid='+isValid;
	},
	"editview" : function(id,isRead){ //编辑
		if(1==isRead){
			eNav.setSubPageText('配置组件');
		}else{
			eNav.setSubPageText('配置组件');
		}
		var modularId=$('#modularId').val();
		window.location.href = GLOBAL.WEBROOT+'/modularComponent/editview?id='+id+'&isRead='+isRead+'&modularId='+modularId;
	},
	"invalid":function(id,status){
		eDialog.confirm("您确认将该记录失效吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					modularComponent.changeStatus(id,status);
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
			url : $webroot + "modularComponent/isCancle",
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
			modularComponent.changeStatus(id,status);
		}else{
			eDialog.warning("该模块已被使用，不允许撤销！");
		}
	},
	"changeStatus":function(id,status){
		 //生效、失效
		var param = {
				'id':id,
				'status':status
		};
		$.eAjax({
			url : $webroot + "modularComponent/changestatus",
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
