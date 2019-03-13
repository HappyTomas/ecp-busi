$(function() {
	
	/**
	 * html dom可初始化元素.
	 */
	var domEles = {
		
		// 可选属性表格.
		_optionalPropGridTable : $('#optionalPropGridTable'),
		// 已配置属性表格.
		_configedPropGridTable : $('#configedPropGridTable'),
		// 已配置属性搜索按钮.
		_searchBtn3 : $('#search-btn-3'),
		// 可选属性搜索按钮.
		_searchBtn4 : $('#search-btn-4'),
		// 批量添加属性.
		_btnBatchAddProps : $('#batch-add-props'),
		// 批量删除属性.
		_btnBatchDelProps : $('#batch-del-props'),
		// 属性配置返回按钮.
		_btnPropBack : $('#btnPropBack'),
		_configedPropForm : $('#configedPropsForm'),
	    _optionalPropsForm : $('#optionalPropsForm'),
	    _propBaseInfoForm : $('#propBaseInfoForm')
		
	}
	
	/**
	 * 已配置属性Grid.
	 */
	var _initConfigedPropGridDT = {
			'pTableTools' : false,
			'pSingleCheckClean' : true,
			"sAjaxSource" : GLOBAL.WEBROOT + '/goods/type/listconfigedprop',
			'pCheck' : 'multi',
			// 指定列数据位置
			"aoColumns" : [{
						"mData" : "propId",
						"sTitle" : "属性编码",
						"sWidth" : "70px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "propName",
						"sTitle" : "属性名称",
						"sWidth" : "70px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "propTypeName",
						"sTitle" : "属性类型",
						"sWidth" : "70px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "propValueTypeName",
						"sTitle" : "属性值类型",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "ifBasic",
						"sTitle" : "基础属性",
						"sWidth" : "70px",
						"sClass" : "center",
						"bSortable" : false,
						"mRender" : function(data, type, row) {
							var optStr = "";
							// 规格属性不能作为基础属性. 规格属性为1.
							if('1' != row.propType){
								if('1' == data){
									optStr = '<input type="checkbox" name="ifBasic" value="1" data="'+row.propId+'" checked/>';
								}else{
									optStr = '<input type="checkbox" name="ifBasic" value="1" data="'+row.propId+'"/>';
								}
							}
							return optStr;
						}
					},{
						"mData" : "ifHaveto",
						"sTitle" : "是否必填",
						"sWidth" : "70px",
						"sClass" : "center",
						"bSortable" : false,
						"mRender" : function(data, type, row) {
							var optStr = "";
							if('1' == data){
								optStr = '<input type="checkbox" name="ifHaveTo" value="1" data="'+row.propId+'" checked/>';
							}else{
								optStr = '<input type="checkbox" name="ifHaveTo" value="1" data="'+row.propId+'"/>';
							}
							return optStr;
						}
					},{
						"mData" : "ifSearch",
						"sTitle" : "是否搜索",
						"sWidth" : "70px",
						"sClass" : "center",
						"bSortable" : false,
						"mRender" : function(data, type, row) {
							var optStr = "";
							// 手动输入的属性，不能作为搜索属性 属性值类型:手工输入 1
							if('1' != row.propValueType){
								if('1' == data){
									optStr = '<input type="checkbox" name="ifSearch" value="1" data="'+row.propId+'" checked/>';
								}else{
									optStr = '<input type="checkbox" name="ifSearch" value="1" data="'+row.propId+'"/>';
								}
							}
							return optStr;
						}
					}
					,{
						
						"sTitle" : "操作",
						"sWidth" : "70px",
						"sClass" : "center",
						"bSortable" : false,
						"mRender" : function(data, type, row) {
							var optStr = null;
							if('1' != row.ifAbleEdit){
								optStr = "<span><a href='#' name='delProp' data="+row.propId+">删除</a></span>";
							}else{
								optStr = "";
							}
							return optStr;
						}
					}
			]

		};
		
	/**
	 * 可选属性Grid初始.
	 */
	var _initOptPropGridDT = {
			'pTableTools' : false,
			'pCheck' : 'multi',
			'pSingleCheckClean' : true,
			"sAjaxSource" : GLOBAL.WEBROOT + '/goods/type/listoptionalprop',
			// 指定列数据位置
			"aoColumns" : [{
						"mData" : "id",
						"sTitle" : "属性编码",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "propName",
						"sTitle" : "属性名称",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "propType",
						"sTitle" : "属性类型",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "propValueType",
						"sTitle" : "属性值类型",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false
					},{
						
						"sTitle" : "操作",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false,
						"mRender" : function(data, type, row) {
							var optStr = "<span><a href='#' name='addProp' data="+row.id+" >添加</a>";
							return optStr;
						}
					}
			]

		};
		
	/**
	 * 事件监听
	 */
	var actionListener = {
		backBtnAction : function(){
			window.location.href = GLOBAL.WEBROOT
					+ '/goods/type';
		},
		searchBtn3Action : function(){
			var p = ebcForm.formParams(domEles._configedPropForm);
			domEles._configedPropGridTable.gridSearch(p);
		},
		searchBtn4Action : function(){
			var p = ebcForm.formParams(domEles._optionalPropsForm);
			domEles._optionalPropGridTable.gridSearch(p);
		},
		/**
		 * 属性编辑检测。
		 * @param catgCode
		 */
		propDelCheck : function(id){
			//=======================
			var count = null;
			var datas = "typeId="+$('#typeId').val();
			if(null != id){
				datas=datas+"&propIds="+id;
			}else{
				var _val = domEles._configedPropGridTable.getSelectedData();
				$.each( _val, function(i, n){
					  datas=datas+"&propIds="+n.propId;
				}); 
			}
			//=======================
			///////属性属性关联关系删除检测开始。
			$.eAjax({
				url : GLOBAL.WEBROOT + '/goods/type/propDelCheck',
				data :datas,
				success : function(returnInfo) {
					if(null != returnInfo 
							&& null != returnInfo.ecpBaseResponseVO
							&& 'ok' == returnInfo.ecpBaseResponseVO.resultFlag){
						count = returnInfo.ecpBaseResponseVO.resultMsg;
					}else{
						//eDialog.error('属性可编辑检测遇到异常!');
						console.error("属性可编辑检测遇到异常!");
					}
				},
				async : false,
				error : function(e,xhr,opt){
					//eDialog.error("属性可编辑检测遇到异常!");
					console.error("属性可编辑检测遇到异常!");
				},
				exception : function(msg){
					//eDialog.error('属性可编辑检测遇到异常!');
					console.error("属性可编辑检测遇到异常!");
				}
			});
			
			return count;
		}
	};
	
	// 删除单个关联属性.
	var _fnSingleDelProp = function(id){
		if( null == id ){
			eDialog.alert('请选择一个属性进行删除');
			return;
		}
		
		var count = actionListener.propDelCheck(id);
        
		if(null == count){
			alert("删除属性前检测遇到异常!");
			return;
		}else{
			if(0 < count){
				alert("属性不允许删除!");
				return;
			}
		}
		
		
		$.eAjax({
			url : GLOBAL.WEBROOT+"/goods/type/delprops",
			type : "POST",
			data : {
				"propIds" : id,
				"typeId" :$('#typeId').val()
				},
			success : function(json) {
				if(null != json && 'ok' == json.resultFlag){
						eDialog.success('属性关联删除成功！');
						pageInit.fresh();
				}else{
						eDialog.error('删除属性关联遇到异常！异常信息:'+json.resultMsg);
				}
			},
			error : function(e,xhr,opt){
					eDialog.error("删除属性遇到异常!");
			}
		});
	};
	
	var _fnBatchDelProps = function(){
		var ids = domEles._configedPropGridTable.getCheckIds();
		var datas = "typeId="+$('#typeId').val();
		if(ids && ids.length>0){
			eDialog.confirm("您是否要批量删除选定属性?",{
				buttons:[{
					caption:"确定",
					callback:function(){
						var count = actionListener.propDelCheck();
                        
						if(null == count){
							alert("删除属性前检测遇到异常!");
							return;
						}else{
							if(0 < count){
								alert("部份属性不允许删除!");
								return;
							}
						}
						
						var _val = domEles._configedPropGridTable.getSelectedData();
						$.each( _val, function(i, n){
							  datas=datas+"&propIds="+n.propId;
						}); 
						$.eAjax({
							url : GLOBAL.WEBROOT+"/goods/type/delprops",
							type : "POST",
							data : datas,
							success : function(json) {
								if(null != json && 'ok' == json.resultFlag){
										eDialog.success('属性关联批量删除成功！');
										pageInit.fresh();
								}else{
										eDialog.error('批量删除属性遇到异常！异常信息:'+json.resultMsg);
								}
							},
							error : function(e,xhr,opt){
									eDialog.error("批量删除属性遇到异常!");
							}
						});
					}
			},{
					caption:'取消'
			}]
			});
		}else if(!ids || ids.length==0){
			eDialog.alert('请至少选择一个已配置属性进行删除！');
			return;
		}
	};
	
	// 添加单个属性.
	var _fnSingleAddProp = function(id){
		if( null == id ){
			eDialog.alert('请选择一个属性进行添加');
			return;
		}
		$.eAjax({
			url : GLOBAL.WEBROOT+"/goods/type/addprops",
			type : "POST",
			data : {
				"propIds" : id,
				"typeId" :$('#typeId').val()
				},
			success : function(json) {
				if(null != json && 'ok' == json.resultFlag){
						eDialog.success('添加属性成功！');
						pageInit.fresh();
				}else{
						eDialog.error('添加属性遇到异常！异常信息:'+json.resultMsg);
				}
			},
			error : function(e,xhr,opt){
					eDialog.error("添加属性遇到异常!");
			}
		});
	};
	
	var _fnBatchAddProps = function(){
		var ids = domEles._optionalPropGridTable.getCheckIds();
		var datas = "typeId="+$('#typeId').val();
		if(ids && ids.length>0){
			eDialog.confirm("您是否要批量添加选定属性?",{
				buttons:[{
					caption:"确定",
					callback:function(){
						var _val = domEles._optionalPropGridTable.getSelectedData();
						$.each( _val, function(i, n){
							  datas=datas+"&propIds="+n.id;
						}); 
						
						$.eAjax({
							url : GLOBAL.WEBROOT+"/goods/type/addprops",
							type : "POST",
							data : datas,
							success : function(json) {
								if(null != json && 'ok' == json.resultFlag){
										eDialog.success('属性关联批量添加成功！');
										pageInit.fresh();
								}else{
										eDialog.error('批量添加属性遇到异常！异常信息:'+json.resultMsg);
								}
							},
							error : function(e,xhr,opt){
									eDialog.error("批量添加属性遇到异常!");
							}
						});
						
					}
			},{
					caption:'取消'
			}]
			});
		}else if(!ids || ids.length==0){
			eDialog.alert('请至少选择一个属性进行添加！');
			return;
		}
	};
	
	/**
	 * 判断checkbox是否是选中状态.
	 * @param obj 请传入jQuery对象.
	 */
	function isChecked(obj){
		if(null == obj){
			return false;
		}else{
           if('checked' == obj.attr('checked')){
        	   return true;
           }else{
        	   return false;
           } 			
		}
	}
	
	/**
	 * 属性配置复选框点击事件.
	 */
	var _fnChkBoxChange = function(chkBox,typeId,type,msg){
		var _val = "0";
		if(isChecked(chkBox)){
			_val = "1";
		}
		$.eAjax({
			url : GLOBAL.WEBROOT+"/goods/type/propchange",
			type : "POST",
			data : {
				"typeId" : typeId,
				"propId"   : chkBox.attr('data'),
				"type" : type,
				"value" :_val
			},
			success : function(data) {
				if(null == data || 'ok' != data.ecpBaseResponseVO.resultFlag){
					eDialog.alert("属性ID=["+chkBox.attr('data')+"]"+msg+"配置遇到异常!异常信息:"+data.ecpBaseResponseVO.resultMsg);
					restoreCheckBox(chkBox);
				}
			},
			error : function(e,xhr,opt){
					eDialog.error("属性ID=["+chkBox.attr('data')+"]"+msg+"配置遇到异常!");
					restoreCheckBox(chkBox);
			}
		});
	};
		
	var pageInit = {
		
		initGrid : function(){
			domEles._optionalPropGridTable.initDT(_initOptPropGridDT);
			domEles._configedPropGridTable.initDT(_initConfigedPropGridDT);
		},
		
		bindClickEvent : function(){
	    	var clickEventType = 'click';
	    	domEles._btnPropBack.bind(clickEventType,actionListener.backBtnAction);
	    	domEles._searchBtn3.bind(clickEventType,actionListener.searchBtn3Action);
	        domEles._searchBtn4.bind(clickEventType,actionListener.searchBtn4Action);
	        domEles._btnBatchAddProps.bind(clickEventType,_fnBatchAddProps);
	        domEles._btnBatchDelProps.bind(clickEventType,_fnBatchDelProps);
	        
	        // 添加属性按钮绑定事件.
			$('a[name=addProp]').live('click', function() {
				var _ids=$(this).attr("data");
				
				eDialog.confirm("您确认要添加选定属性吗?",{
					buttons:[{
						caption:"确定",
						callback:function(){
							_fnSingleAddProp(_ids);						
						}
				},{
						caption:'取消'
				}]
				});
			});
			// 删除属性按钮添加事件.
			$('a[name=delProp]').live('click', function() {
				var _ids=$(this).attr("data");
				eDialog.confirm("您确认要删除选定属性吗?",{
					buttons:[{
						caption:"确定",
						callback:function(){
							_fnSingleDelProp(_ids);					
						}
				},{
						caption:'取消'
				}]
				});
			});
			
			// 是否基础属性勾选框监听.
			$('input[type=checkbox][name=ifBasic]').live('change',function(){
				_fnChkBoxChange($(this),$('#typeId').val(),1,"是否基础属性");
			});
			// 是否必填属性勾选框监听.
			$('input[type=checkbox][name=ifHaveTo]').live('change',function(){
				_fnChkBoxChange($(this),$('#typeId').val(),2,"是否必填属性");
			});
			// 是否搜索属性勾选框监听.
			$('input[type=checkbox][name=ifSearch]').live('change',function(){
				_fnChkBoxChange($(this),$('#typeId').val(),3,"是否搜索属性");
			});	
			// 是否输入属性勾选框监听.
			$('input[type=checkbox][name=ifGdsInput]').live('change',function(){
				_fnChkBoxChange($(this),$('#typeId').val(),4,"是否输入属性");
			});	
		},
		
		fresh : function refreshPropGrid(){
	        domEles._searchBtn3.click();
	        domEles._searchBtn4.click();
		},
		
		init : function(){
			pageInit.bindClickEvent();
			pageInit.initGrid();
			pageInit.fresh();
		}
		
	}
	
	pageInit.init();
	
});