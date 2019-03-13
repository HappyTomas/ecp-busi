$(function(){
	
	//表格数据初始化
	$("#dataGridTable").initDT({
        'pSingleCheckClean' : true,
        "pPageBar" : false,
        'pCheckColumn' : true,
        'params' : [{"name":"listAll","value":1},{"name":"funcId","value":-1}],//初始化 funcId为-1，首次查询为空
        "sAjaxSource": GLOBAL.WEBROOT + '/dataauth/fielditem/gridlist',
        //指定列数据位置
        "aoColumns": [
            { "mData": "name", "sTitle":"名称","sWidth":"80px","bSortable":false},      
            { "mData": "attrName", "sTitle":"对象属性名","sWidth":"90px","bSortable":false},         
			{ "mData": "valueType", "sTitle":"数据类型","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return data;
			}},
			{ "mData": "valueFormate", "sTitle":"数据格式","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return data;
			}},
			{ "mData": "defaultValue", "sTitle":"默认值","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return data;
			}}
        ],
        "eDbClick" : function(){
        	//handleBiz();
        },
        "eClick" : function(row,dom){
        	if($("#dataGridTable").getSelectedData()){//选中行,“保存”时,修改规则项目
        		$("#id").val(row.id);
        		$("#name").val(row.name);
        		$("#attrName").val(row.attrName);
        		$("#valueType").val(row.valueType);
        		$("#defaultValue").val(row.defaultValue);
        		$("#valueFormate").val(row.valueFormate);
        		$("#btnFormSaveSubmit").removeClass("disabled");
        	}else{//取消选中;“保存”时,新增规则项目
        		$("#id").val("");
        		$("#btnFormSaveSubmit").addClass("disabled");
        	}
        },
        "eDrawComplete" : function(){
        	$("#id").val("");
        	$("#btnFormSaveSubmit").addClass("disabled");
        },
        "onSuccess" : function(){
        	
        }
	
	});
	
	/**
	 * 得到字符串的长度 
	 * 字节长度：一个中文字符字节长度为2
	 */
	var _getRealLen = function(str) {
		var reCh=/[^\u4E00-\u9FA5]/;  
		var strlen = 0;
	    for(var i=0;i<str.length;i++){  
	    	if(!reCh.test(str.charAt(i))){  
	    		strlen+=2;//中文为2个字符  
	    	}else{  
	    		strlen+=1;//英文一个字符  
	    	}  
	    }
	    return strlen;
	}
	
	//输入框长度控制绑定事件
	function validateInput() {
		var inputArray = [];
		inputArray.push($("input[name='name']"));
		inputArray.push($("input[name='attrName']"));
		inputArray.push($("input[name='defaultValue']"));
		inputArray.push($("input[name='valueFormate']"));
		for ( var i in inputArray) {
			inputArray[i].on('input propertychange', function(e){
				var self = $(this);
				var len = _getRealLen(self.val());
				var maxlength = Number(self.attr('maxlength'));
				if(len>maxlength){
					eDialog.warning("输入超出限制长度！已自动适应");
					var strlen=0; //初始定义长度为0
					var txtval = $.trim(self.val());
					var reCh=/[^\u4E00-\u9FA5]/;  
				    for(var i=0;i<txtval.length;i++){  
				    	if(!reCh.test(txtval.charAt(i))){  
				    		strlen=strlen+2;//中文为2个字符  
				    	}else{  
				    		strlen=strlen+1;//英文一个字符  
				    	}  
				    	if(strlen>maxlength){
				    		self.val(txtval.substring(0,i));
				    		return;
				    	}
				    }  
				}
			});
		}
	}
	validateInput();//验证启用
	
	//handleBiz
	var handleBiz = function(callback){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			if(callback){
				callback(val);
			}
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	};
	
	//新增提交
	$('#btnFormAddSubmit').click(function(){
		var nodes = _getSelectedNodes();
		if(nodes.length!=1){
			eDialog.warning("请选择一个功能点！");
			return;
		}
		if(!$("#detailInfoForm").valid())return false;
		
		var funcId = nodes[0].id;//功能id
		var _val = ebcForm.formParams($("#detailInfoForm"));
		//追加funcId
		_val.push({"name":"funcId","value":funcId});
		
		var _url = GLOBAL.WEBROOT + "/dataauth/fielditem/add";
		
		$.eAjax({
			url : _url,
			data : _val,
			datatype:'json',
			success : function(data) {
				var reflag = data.resultFlag;//ok  fail
		    	var remsg = data.resultMsg;
		    	if(reflag=="ok"){
		    		eDialog.success('新增成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								$('#dataGridTable').gridSearch([{"name":"funcId","value":funcId}]);//刷新表格
					        }
						}]
					});
		    	}else{
		    		eDialog.error('新增失败！'+remsg); 
		    	}
			}
		});
		
	});
	
	//保存提交； 修改
	$('#btnFormSaveSubmit').click(function(){
		//事件有效否
		if($(this).hasClass("disabled")){//如果按钮是“禁用”则不作为
			return;
		}
		
		var nodes = _getSelectedNodes();
		if(nodes.length!=1){
			eDialog.warning("请选择一个功能点！");
			return;
		}
		if(!$("#detailInfoForm").valid())return false;
		
		var funcId = nodes[0].id;//功能id
		var _val = ebcForm.formParams($("#detailInfoForm"));
		//追加funcId
		_val.push({"name":"funcId","value":funcId});
		var _url = GLOBAL.WEBROOT + "/dataauth/fielditem/update";
		$.eAjax({
			url : _url,
			data : _val,
			datatype:'json',
			success : function(data) {
				var reflag = data.resultFlag;//ok  fail
		    	var remsg = data.resultMsg;
		    	if(reflag=="ok"){
		    		eDialog.success('修改成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								$('#dataGridTable').gridSearch([{"name":"funcId","value":funcId}]);//刷新表格
					        }
						}]
					});
		    	}else{
		    		eDialog.error('修改失败！'+remsg); 
		    	}
				
			}
		});
	});
	
	//删除
	$('#btnFormDeleteSubmit').click(function(){
		handleBiz(function(){
			var rowData = $('#dataGridTable').getSelectedData()[0];
			eDialog.confirm("是否确定删除？", {
				buttons : [{
					caption : '删除',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/dataauth/fielditem/delete",
							data : {'id':rowData.id},
							datatype:'json',
							success : function(data) {
								var reflag = data.resultFlag;//ok  fail
						    	var remsg = data.resultMsg;
						    	if(reflag=="ok"){
						    		eDialog.success('删除成功！'); 
						    		$('#dataGridTable').gridSearch([{"name":"funcId","value":_getOnlySelectedNode().id}]);//刷新表格
						    	}else{
						    		eDialog.error('删除失败！'+remsg); 
						    	}
							}
						});
					}
				},{
					caption : '返回',
					callback : function(){
						bDialog.closeCurrent();
					}
				}]
			});
		});
	});
	
	//切换子系统
	$('select[name="sysCode"]').on("change", function(){
		var self = $(this);
		var treeObj = _getZTreeObj();
		treeObj.setting.async.otherParam = {"sysCode":self.val()};
		treeObj.reAsyncChildNodes(null, "refresh", true);
	});
	
	
	//得到当前选中的树节点
	function _getSelectedNodes(){
		var treeObj = $.fn.zTree.getZTreeObj("treeFunc");
		var nodes = treeObj.getSelectedNodes();
		return nodes;
	}
	
	//得到当前唯一节点
	function _getOnlySelectedNode(){
		var treeObj = $.fn.zTree.getZTreeObj("treeFunc");
		var nodes = treeObj.getSelectedNodes();
		if(nodes.length!=1){
			eDialog.warning("请选择一个功能点！");
			return false;
		}
		return nodes[0];
	}
	
	//得到树对象
	function _getZTreeObj(){
		return $.fn.zTree.getZTreeObj("treeFunc");
	}
});