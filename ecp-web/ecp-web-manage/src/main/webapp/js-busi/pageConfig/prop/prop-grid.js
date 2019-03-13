$(function(){
	prop.init();
});

var prop = {
	"init" : function(){//初始化
		//初始化查询条件，回填值
		SearchObj.initSearchParams({paramsId:"searchParams",formId:"searchForm"});
		var params = ebcForm.formParams($("#searchForm"));
		
		//初始化列表
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheckColumn' : false, //是否显示单选/复选框的列
//	        'pCheck' : "multi",
	        'params' : params,
	        "sAjaxSource": $webroot + 'prop/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"50px","bSortable":true},
	        	{ "mData": "propName", "sTitle":"属性名称","bSortable":false},	
	        	{ "mData": "propValueTypeZH", "sTitle":"属性值类型","sWidth":"140px","bSortable":false},	
	        	//{ "mData": "controlPropName", "sTitle":"受控属性名称","bSortable":false},	
	        	//{ "mData": "ifHavetoZH", "sTitle":"是否必填","sWidth":"100px","bSortable":false},	
	        	//{ "mData": "isAutobuildZH", "sTitle":"是否自动生成","sWidth":"100px","bSortable":false},	
	        	{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"100px","bSortable":false},
				{ "mData": "createTime", "sTitle":"创建时间","sWidth":"200px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
				}},
				/*{ "mData": "updateTime", "sTitle":"修改时间","sWidth":"140px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
				}},*/
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"180px","sClass": "left","bSortable":false,
					"mRender": function(data,type,row){
						//isValid 0可以编辑 1只能查看
						var isValid='0';
						if($('#isValid').val()&&$('#isValid').val()!=''){
							isValid=$('#isValid').val();
						}
						var preViewOption="<a href='javascript:void(0)' onclick='prop.editview("+data+","+row.status+",1,"+isValid+")'>查看</a>";
						
						if(isValid==1){
							return preViewOption;
						}else{
							if(row.status == "1"){//已发布
								return preViewOption+" | <a href='javascript:void(0)' onclick='prop.changeStatus("+row.id+",0)'>撤销</a>";
							}else if(row.status == "0"){//有效
								return "<a href='javascript:void(0)' onclick='prop.editview("+data+","+row.status+",0,"+isValid+")'>编辑</a> | "+preViewOption+" | <a href='javascript:void(0)' onclick='prop.changeStatus("+data+",1)'>发布</a> | <a href='javascript:void(0)' onclick='prop.cancelPublish("+data+",2)'>使失效</a>";
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
		//绑定添加按钮
		$('#btn_code_add').click(function(){
			window.location.href = GLOBAL.WEBROOT+'/prop/editview';
		});
	},
	"editview" : function(id,status,isRead,isValid){ //编辑
		if(1==isRead){
			eNav.setSubPageText('查看模块属性');
		}else{
			eNav.setSubPageText('编辑模块属性');
		}
		window.location.href = GLOBAL.WEBROOT+'/prop/editview?id='+id+'&&status='+status+'&isRead='+isRead+'&isValid='+isValid;
	},
	"invalid":function(id,status){
		eDialog.confirm("您确认将该记录失效吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					prop.changeStatus(id,status);
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
			url : $webroot + "prop/isCancle",
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
			prop.changeStatus(id,status);
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
			url : $webroot + "prop/changestatus",
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
