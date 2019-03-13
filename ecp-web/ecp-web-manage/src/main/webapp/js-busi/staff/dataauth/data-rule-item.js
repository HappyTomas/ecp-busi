$(function(){
	
	//表格数据初始化
	$("#dataGridTable").initDT({
        'pSingleCheckClean' : true,
        "pPageBar" : false,
        'pCheckColumn' : true,
        'params' : [{"name":"listAll","value":1},{"name":"funcId","value":-1}],
        "sAjaxSource": GLOBAL.WEBROOT + '/dataauth/dataitem/gridlist',
        //指定列数据位置
        "aoColumns": [
            { "mData": "name", "sTitle":"名称","sWidth":"80px","bSortable":false},      
            { "mData": "orderBy", "sTitle":"顺序","sWidth":"40px","bSortable":false},      
            { "mData": "attrName", "sTitle":"对象属性名","sWidth":"90px","bSortable":false},         
            { "mData": "fieldName", "sTitle":"表字段名","sWidth":"90px","bSortable":false},         
			{ "mData": "valueType", "sTitle":"值类型","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return data;
			}},
			{ "mData": "viewType", "sTitle":"UI类型","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
				switch(data){
					case 'textinput':
						return "文本输入框";
					case 'numberinput':
						return "数字输入框";
					case 'select':
						return "下拉框";
					case 'multiselect':
						return "下拉多选框";
					case 'datepicker':
						return "日期控件";
					case 'gdscatgtree':
						return "商品分类树";
					default:
						return "未知控件";
				}
			}},
			{ "mData": "defaultValue", "sTitle":"UI默认值","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return data;
			}},
			{ "mData": "initValue", "sTitle":"UI初始值","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return data;
			}},
			{ "mData": "valueFormate", "sTitle":"数据格式","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
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
        		$("#fieldName").val(row.fieldName);
        		$("#valueType").val(row.valueType);
        		$("#viewType").val(row.viewType);
        		$("#defaultValue").val(row.defaultValue);
        		$("#initValue").val(row.initValue);
        		$("#valueFormate").val(row.valueFormate);
        		$("#orderBy").val(row.orderBy);
//        		$("#saveFlag").text("update");
        		$("#btnFormSaveSubmit").html('<i class="icon-edit"></i> 修　改');
        	}else{//取消选中;“保存”时,新增规则项目
        		$("#id").val("");
        		$("#btnFormSaveSubmit").html('<i class="icon-ok-sign"></i> 保　存');
//        		$("#saveFlag").text("new");
        	}
        },
        "eDrawComplete" : function(){
        	$("#id").val("");
    		$("#saveFlag").text("new");
    		$("#btnFormSaveSubmit").html('<i class="icon-ok-sign"></i> 保　存');
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
	    	/*
		if (str == null) return 0;
		if (typeof str != "string"){
			str += "";
		}
		return str.replace(/[^\x00-\xff]/g,"__").length;
		*/
	    return strlen;
	}
	
	//输入框长度控制绑定事件
	function validateInput() {
		var inputArray = [];
		inputArray.push($("input[name='name']"));
		inputArray.push($("input[name='attrName']"));
		inputArray.push($("input[name='fieldName']"));
		inputArray.push($("input[name='defaultValue']"));
		inputArray.push($("input[name='initValue']"));
		inputArray.push($("input[name='valueFormate']"));
		inputArray.push($("input[name='orderBy']"));
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
	
	//保存提交； 无则新增，有则修改
	$('#btnFormSaveSubmit').click(function(){
		var nodes = _getSelectedNodes();
		if(nodes.length!=1){
			eDialog.warning("请选择一个功能点！");
			return;
		}
		if(!$("#detailInfoForm").valid())return false;
		//"对象属性名" "表字段名" 至少一个不能为空
		if($.trim($("#attrName").val())==""&&$.trim($("#fieldName").val())==""){
			eDialog.warning('"对象属性名"与"表字段名"至少选一个填写！',{
				buttons : [{
					'caption':'确定',
					'callback':function(){
						$("#attrName").focus();
					}
				}]
			});
			return false;
		}
		//“默认值” “初始值” 对应UI类型需要验证
		var uiViewArray = ["03","04","06"];//下拉框，多选下拉框，选择框
		if($.inArray($("#viewType").val(),uiViewArray)!=-1){
			//"默认值" "初始值" 不能为空
			if($.trim($("#defaultValue").val())==""&&$.trim($("#initValue").val())==""){
				return false;
			}
		}
		
		var funcId = nodes[0].id;//功能id
		var _val = ebcForm.formParams($("#detailInfoForm"));
		//追加funcId
		_val.push({"name":"funcId","value":funcId});
		var _url = "";
		var _isUpdate = true;
		if($("#dataGridTable").getSelectedData()){//修改
			_url = GLOBAL.WEBROOT + "/dataauth/dataitem/update";
		}else{//新增
			_url = GLOBAL.WEBROOT + "/dataauth/dataitem/add";
			_isUpdate = false;
		}
		$.eAjax({
			url : _url,
			data : _val,
			datatype:'json',
			success : function(data) {
				var reflag = data.resultFlag;//ok  fail
		    	var remsg = data.resultMsg;
		    	if(reflag=="ok"){
		    		eDialog.success(_isUpdate==true?'修改成功！':'保存成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								$('#dataGridTable').gridSearch([{"name":"funcId","value":funcId}]);//刷新表格
					        }
						}]
					});
		    	}else{
		    		eDialog.error(_isUpdate==true?'修改失败！':'保存失败！'+remsg); 
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
							url : GLOBAL.WEBROOT + "/dataauth/dataitem/delete",
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