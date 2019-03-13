$(function(){
	
	//子系统事件
	$('#sysCode').change(function(){
		var treeObj = _getZTreeObj();
		treeObj.setting.async.otherParam = {"sysCode":$(this).val()};
		treeObj.reAsyncChildNodes(null, "refresh", true);
	});
	
	//新增顶级
	$('#btnFormAddTop').click(function(){
		_getZTreeObj().cancelSelectedNode();
		_newDataFunc(1);
	});
	//新增下级
	$('#btnFormAddSon').click(function(){
		var nodes = _getSelectedNodes();
		if(nodes.length != 1){
			eDialog.warning('请选择一个数据功能！');
			return;
		}
		_newDataFunc();
	});
	
	//数据功能状态 OPTION不可选
	$("#status").change(function(e){
		$("#status").val(Math.abs($("#status").val()-1));
	});
	
	//新增子节点
	function _newDataFunc(top){
		//1.清空表格
		$("#detailInfoForm input,#detailInfoForm textarea").each(function(i){
			var self = $(this);
			if(self&&self.attr("id")=="parentId"){
				var treeObj = $.fn.zTree.getZTreeObj("treeFunc");
				var nodes = treeObj.getSelectedNodes();
				var selected = {};
				if(nodes.length>0){
					selected = nodes[0];
				}
				$("#parentIdLabel").html(selected.name);
				self.attr("value", selected.id);
				if(top){
					$("#parentIdLabel").html("");
					self.attr("value", "0");
				}
				return true;
			}
			self.val("");
		});
		if(top){//新增顶级节点，初始化状态为“激活”
			$("#status").val(1);
		}
		//2.修改状态
		$("#objectState").attr("value","1");//1表示新增，0表示修改
	}
	
	//保存提交； 
	$('#btnFormSaveSubmit').click(function(){
		
		//表单数据
		if(!$("#detailInfoForm").valid())return false;
		var _val = ebcForm.formParams($("#detailInfoForm"));
		//数据处理
		//1.sysCode
		var _sysCode = {"name" : "sysCode", "value" : $("#sysCode").val()};
		_val.push(_sysCode);//追加sysCode
		
		var objectState = $("#objectState").val();//对象状态：  新增？修改
		var _url = "";
		if(objectState==1){
			_url = GLOBAL.WEBROOT + "/dataauth/datafunc/add";
		}else{
			_url = GLOBAL.WEBROOT + "/dataauth/datafunc/update";
		}
		//request
		$.eAjax({
			url : _url,
			data : _val,
			datatype:'json',
			success : function(data) {
				var reflag = data.resultFlag;//ok  fail
		    	var remsg = data.resultMsg;
		    	if(reflag=="ok"){
		    		eDialog.success((objectState==1?'新增':'修改')+'保存成功！');
		    		_refreshChangedNode();
		    		if(objectState==0){
		    			var selectedNode = _getSelectedNodes()[0];
		    			var treeObj = _getZTreeObj();
		    			selectedNode.name = $("#name").val();
		    			selectedNode.code = $("#code").val();
		    			selectedNode.status = $("#status").val();
		    			selectedNode.sortOrder = $("#sortOrder").val();
		    			selectedNode.funcDesc = $("#funcDesc").val();
		    			treeObj.updateNode(selectedNode);
		    		}
		    	}else{
		    		eDialog.error((objectState==1?'新增':'修改')+'保存失败！'+remsg); 
		    	}
				
			}
		});
	});
	
	//禁用
	$('#btnFormInvalidSubmit').click(function(){
		_chstaDataFunc({type:0,text:"禁用"});
	});
	
	//激活
	$('#btnFormActiveSubmit').click(function(){
		_chstaDataFunc({type:1,text:"激活"});
	});
	
	/**
	 * 数据功能状态变更
	 * e - {type:1,text:"激活"}
	 * e - {type:0,text:"禁用"}
	 */
	function _chstaDataFunc(e){
		var nodes = _getSelectedNodes();
		if(nodes.length != 1){
			eDialog.warning('请选择一个数据功能！');
			return;
		}
		var funcNode = nodes[0];//当前功能
		
		if(funcNode.status==e.type){
			eDialog.warning('功能 '+funcNode.name+' 已'+e.text+'！');
			return;
		}
		
		//变更
		eDialog.confirm("是否确定"+e.text+"功能[ "+funcNode.name+" ]？", {
			buttons : [{
				caption : e.text,
				callback : function(){
					$.eAjax({
						url : GLOBAL.WEBROOT + "/dataauth/datafunc/chsta",
						data : {'id':funcNode.id,'status':e.type},
						datatype:'json',
						success : function(data) {
							var reflag = data.resultFlag;//ok  fail
					    	var remsg = data.resultMsg;
					    	if(reflag=="ok"){
					    		eDialog.success(e.text+'成功！');
					    		_refreshChangedNode();
					    		//更新当前节点
					    		var selectedNode = _getSelectedNodes()[0];
				    			var treeObj = _getZTreeObj();
				    			selectedNode.status = e.type;
				    			if(e.type==1){
				    				selectedNode.font = {"color":"black"};
				    			}else if(e.type==0){
				    				selectedNode.font = {"color":"red"};
				    			}
				    			//修改表单 status
			    				$("#status").val(e.type);
				    			treeObj.updateNode(selectedNode);
					    	}else{
					    		eDialog.error(e.text+'失败！'+remsg); 
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
	}
	
	
	//得到当前选中的树节点
	function _getSelectedNodes(){
		var treeObj = $.fn.zTree.getZTreeObj("treeFunc");
		var nodes = treeObj.getSelectedNodes();
		return nodes;
	}
	
	//得到树对象
	function _getZTreeObj(){
		return $.fn.zTree.getZTreeObj("treeFunc");
	}

	//刷新节点
	function _refreshChangedNode(){
		var treeObj = $.fn.zTree.getZTreeObj("treeFunc");
		var nodes = treeObj.getSelectedNodes();
		var refresh = "refresh";
		var objectState = $("#objectState").val();//对象状态：  新增？修改
		if(nodes.length>0){
			var selectedNode = nodes[0];
			if(selectedNode.isParent){
				treeObj.reAsyncChildNodes(selectedNode, refresh, false);
				treeObj.expandNode(selectedNode, true, false, true);
			}else{
				if(objectState==1){
//					if(selectedNode.getParentNode()){
//						treeObj.reAsyncChildNodes(selectedNode.getParentNode(), refresh, false);
//						treeObj.expandNode(selectedNode.getParentNode(), true, false, true);
//					}else{
//						treeObj.reAsyncChildNodes(null, refresh);
//					}
					selectedNode.isParent = true;
					treeObj.updateNode(selectedNode);
					treeObj.reAsyncChildNodes(selectedNode, refresh, false);
					treeObj.expandNode(selectedNode, true, false, true);
				}
			}
		}else{
			treeObj.reAsyncChildNodes(null, refresh);
		}
		
	}
	
	
	
});
