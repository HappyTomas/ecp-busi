$(function(){
	modular.init();
});

var modular = {
	"init" : function(){//初始化
		//初始化查询条件，回填值
		SearchObj.initSearchParams({paramsId:"searchParams",formId:"searchForm"});
		var params = ebcForm.formParams($("#searchForm"));
		
		//初始化列表
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
//	        'pCheckColumn' : false, //是否显示单选/复选框的列
	        'pCheck' : "single",
	        'params' : params,
	        "sAjaxSource": $webroot + 'modular/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"80px","bSortable":true},
	        	{ "mData": "modularName", "sTitle":"模块名称","bSortable":false},	
	        	{ "mData": "showPic", "sTitle":"模块缩略图","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
	        		if(row.showPic!=null && row.showPic.length>0){
	        			return "<img src='"+data+"' width='50' height='50'>";
	        		}else{
	        			return "暂无图片";
	        		}
				}},
	        	{ "mData": "modularTypeZH", "sTitle":"模块类型","bSortable":false},	
	        	{ "mData": "platformTypeZH", "sTitle":"平台类型","bSortable":false},
	        	/*{ "mData": "applyPageType", "sTitle":"适用页面类型","bSortable":false},	
	        	{ "mData": "applyItemSize", "sTitle":"适用布局尺寸","bSortable":false},*/	
	        	{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"100px","bSortable":false},
				{ "mData": "createTime", "sTitle":"创建时间","sWidth":"140px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd");
				}},
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"160px","sClass": "left","bSortable":false,
					"mRender": function(data,type,row){
						var preViewOption="<a href='javascript:void(0)' onclick='modular.editview("+data+",1)'>查看</a>";
						var configAttrOpt="";
						if(row.status == "1"){//已发布
							return configAttrOpt+preViewOption+" | <a href='javascript:void(0)' onclick='modular.cancelPublish("+row.id+",0)'>撤销</a>";
						}else if(row.status == "0"){//有效
							return configAttrOpt+"<a href='javascript:void(0)' onclick='modular.editview("+data+",0)'>编辑</a> | "+preViewOption+" | <a href='javascript:void(0)' onclick='modular.changeStatus("+data+",1)'>发布</a> | <a href='javascript:void(0)' onclick='modular.invalid("+data+",2)'>使失效</a>";
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
			eNav.setSubPageText('新增模块');
			window.location.href = GLOBAL.WEBROOT+'/modular/editview';
		});
		//绑定属性配置按钮
		$('#btn_attr_config').click(function(){
			modular.attrConfig();
		});
		//绑定组件配置按钮
		$('#btn_attr_component').click(function(){
			modular.attrComponent();
		});
		
	},
	"attrConfig":function(){
		var data = $("#dataGridTable").getSelectedData();
		if(!data || data.length==0){
			eDialog.alert('请先选择一条记录！');
			return;
		}
		if(data[0].status == "2"){
			eDialog.alert('请选择一条有效的记录进行操作');
			return;
		}
		var modularId = data[0].id;
		var isValid=0;
		if(1==isValid){
//			eNav.setSubPageText('查看属性');
			eNav.setSubPageText('配置属性');
		}else{
			eNav.setSubPageText('配置属性');
		}
		window.location.href = GLOBAL.WEBROOT+'/modularPara/init?modularId='+modularId+'&isValid='+isValid;
	},
	"attrComponent":function(){
		var data = $("#dataGridTable").getSelectedData();
		if(!data || data.length==0){
			eDialog.alert('请先选择一条记录！');
			return;
		}
		if(data[0].status == "2"){
			eDialog.alert('请选择一条有效的记录进行操作');
			return;
		}
		var modularId = data[0].id;
		var isValid=0;
		if(1==isValid){
//			eNav.setSubPageText('查看属性');
			eNav.setSubPageText('配置组件');
		}else{
			eNav.setSubPageText('配置组件');
		}
		window.location.href = GLOBAL.WEBROOT+'/modularComponent/init?modularId='+modularId+'&isValid='+isValid;
	},
	"paraview":function(modularId,isValid){
		if(1==isValid){
//			eNav.setSubPageText('查看属性');
			eNav.setSubPageText('配置属性');
		}else{
			eNav.setSubPageText('配置属性');
		}
		window.location.href = GLOBAL.WEBROOT+'/modularPara/init?modularId='+modularId+'&isValid='+isValid;
	},
	"editview" : function(id,isRead){ //编辑
		if(1==1){
			eNav.setSubPageText('查看模块');
		}else{
			eNav.setSubPageText('编辑模块');
		}
		window.location.href = GLOBAL.WEBROOT+'/modular/editview?id='+id+'&isRead='+isRead;
	},
	"invalid":function(id,status){
		eDialog.confirm("您确认将该记录失效吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					modular.changeStatus(id,status);
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
			url : $webroot + "modular/isCancle",
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
			modular.changeStatus(id,status);
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
			url : $webroot + "modular/changestatus",
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
