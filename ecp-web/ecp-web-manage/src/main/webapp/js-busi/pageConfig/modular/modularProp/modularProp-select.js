$(function(){
	modularPropSelect.init();
});

var modularPropSelect = {
	"init" : function(){//初始化
		//初始化查询条件，回填值
		//SearchObj.initSearchParams({paramsId:"searchParams",formId:"searchForm"});
		var params = ebcForm.formParams($("#searchForm"));
		
		//初始化列表
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
//	        'pSingleCheckClean' : true,
	        'pCheckColumn' : true, //是否显示单选/复选框的列
	        'pCheck' : "multi",
	        'params' : params,
	        "sAjaxSource": $webroot + 'modularPara/selectlist',
	        //指定列数据位置
	        "aoColumns": [
  	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"50px","bSortable":true},
  	        	{ "mData": "propName", "sTitle":"属性名称","bSortable":false},	
  	        	{ "mData": "propValueTypeZH", "sTitle":"属性值类型","sWidth":"140px","bSortable":false},	
  	        	{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"100px","bSortable":false},
  				{ "mData": "createTime", "sTitle":"创建时间","sWidth":"200px","bSortable":false,"mRender": function(data,type,row){
  					return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
  				}}
//  	        	,
//  				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"180px","sClass": "left","bSortable":false,
//  					"mRender": function(data,type,row){
//  						//isValid 0可以编辑 1只能查看
//  						var isValid='0';
//  						if($('#isValid').val()&&$('#isValid').val()!=''){
//  							isValid=$('#isValid').val();
//  						}
//  						var preViewOption="<a href='javascript:void(0)' onclick='prop.editview("+data+","+row.status+",1,"+isValid+")'>查看</a>";
//  						
//  						if(isValid==1){
//  							return preViewOption;
//  						}else{
//  							if(row.status == "1"){//已发布
//  								return preViewOption+" | <a href='javascript:void(0)' onclick='prop.changeStatus("+row.id+",0)'>撤销</a>";
//  							}else if(row.status == "0"){//有效
//  								return "<a href='javascript:void(0)' onclick='prop.editview("+data+","+row.status+",0,"+isValid+")'>编辑</a> | "+preViewOption+" | <a href='javascript:void(0)' onclick='prop.changeStatus("+data+",1)'>发布</a> | <a href='javascript:void(0)' onclick='prop.cancelPublish("+data+",2)'>使失效</a>";
//  							}else{//已失效
//  								return preViewOption;
//  							}
//  						}
//  						
//  					}
//  				}
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
		//绑定确定按钮
		$('#saveSelectProp').click(function(){
			modularPropSelect.saveSelectProp();
		});
		
	},
	"saveSelectProp":function(){
//		var data = $("#dataGridTable").getSelectedData();
		var data = $("#dataGridTable").getCheckIds();
		if(!data || data.length==0){
			eDialog.alert('请先选择记录！');
			return;
		}
		var param = {
				'selectPropIds' : data.join(","),
				'modularId' : $('#modularId').val()
			};
		$.eAjax({
			url : $webroot + "modularPara/saveSelectProp",
			type : "POST",
			data : param,
			datatype : 'json',
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					bDialog.closeCurrent({
						"save": true
					});
				}else{
					bDialog.closeCurrent();
				}
			},
			error : function(e,xhr,opt){
				eDialog.error("保存遇到异常!");
				$.gridUnLoading();
			},
			exception : function(msg){
				$.gridUnLoading();
			}
		});
		
	},
	//绑定选定
	selectInfo : function (id,title){
		bDialog.closeCurrent({
			"linkUrl": id,
			"infoTitle":title
		});
	}
};
