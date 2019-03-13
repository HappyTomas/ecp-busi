$(function(){

	
	// zTree实例名称.
    var _treeName = "category-tree";
    // 确认按钮.
    var _btnConfirm = $('#confirm');
    // 关闭按钮
    var _btnClose = $('#close');
    
    function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
    	eDialog.alert('分类树异步加载出错!状态码['+textStatus+']-'+errorThrown);
    };
    
    // 商品分类树初始化功能代码段。
    
	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			async: {
				enable: true,
				url:GLOBAL.WEBROOT+"/goods/category/asyncData/getNodes",
				type:"post",
				autoParam:["id=catgParent"],
				otherParam:{"catgType":$('#catg-type').val()},
				dataFilter: null
			},
			callback: {
				onAsyncError : zTreeOnAsyncError
			}
	};
	
	var settingSearch = {
			data: {
				simpleData: {
					enable: true
				}
			},
			async: {
				enable: true,
				url:GLOBAL.WEBROOT+"/goods/category/asyncData/catgsearch",
				type:"post",
				otherParam:{"catgType":$('#catg-type').val(),"catgName":function(){return $('#keyword').val();}},
				dataFilter: null
			},
			callback: {
				onAsyncError : zTreeOnAsyncError
			}
	};
	
	/**
	 * 搜索功能.
	 */
	$('#search').bind('click',function(){
		var _keyword = $('#keyword').val();
		if(''!=$.trim(_keyword)){
			$.fn.zTree.destroy(_treeName);
			$.fn.zTree.init($("#category-tree"), settingSearch);
		}else{
			$.fn.zTree.init($("#category-tree"), setting);
		}
		
	});
	
	
	_btnConfirm.bind('click',function(){
		// 获取zTree
    	var _catgTree = $.fn.zTree.getZTreeObj(_treeName);
    	var nodes = _catgTree.getSelectedNodes();
    	if (nodes.length == 0) {
			eDialog.alert("请先选择一个分类!");
			return;
		}
    	// 获取选中节点
    	var _ary = new Array();
    	
    	
    	for(var i in nodes){
    		var _obj = new Object();
    		_obj.catgCode = nodes[i].id;
        	_obj.catgName = nodes[i].name;
        	_obj.catgLevel = nodes[i].catgLevel;
        	_ary.push(_obj);
    	}
    	bDialog.closeCurrent({
			"catgs":_ary
		});
	});
	
	_btnClose.bind('click', function(){
		bDialog.closeCurrent();
	});
	
	$('#keyword').bind('keydown',function(e){
	    // 回车键响应。
	    if(e.keyCode==13) {
	    	$('#search').click();
	    }
	});
	
	
	// 树展现
	$.fn.zTree.init($("#category-tree"), setting);

});
