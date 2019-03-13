$(function(){
	// 编辑按钮.
     var _btnEditCategory = $('#edit-category');
     
     // 编辑按钮监听.
     var _editBtnListener = function(){
    	// 获取zTree
     	var _catgTree = $.fn.zTree.getZTreeObj('mediacatg-tree');
     	var _nodes = _catgTree.getSelectedNodes();
	     $.eAjax({
				url : GLOBAL.WEBROOT+"/goods/mediacatg/mediacatgview",
				type : "POST",
				cache : false,
				timeout : 5000,
				data : {"catgCode":function(){return $('#view-catg-code').val();}},
				success : function(json) {
				    if(null != json && 'ok' == json.resultFlag){
				    	// 自动填充分类信息并展示。
				    	ebcForm.formDataFill('category-form',json.respDTO);
				    	var _imgUrl = json.respDTO.mediaUrl;
				    	if(null != _imgUrl && ''!=_imgUrl){
				    		$('#form-imgPreview01').hide();
				    		$('#form-imgPreview02').attr('src',_imgUrl);
				    		$('#form-imgPreview02').show();
				    	}else{
				    		$('#form-imgPreview01').show();
				    		$('#form-imgPreview02').hide();
				    	}
				    	
				    	// 执行父节点名称填充。
				    	var pNode = _nodes[0].getParentNode();
				    	if(pNode){
				    		$('#parent-name').val(pNode.name);
				    	}else{
				    		$('#parent-name').val('');
				    	}
				    	// 隐藏查看div
				    	$('#view-content').hide();
				    	// 显示编辑div
				    	$('#form-content').show();
				    	if(''!=$('#catg-code').val()){
				    		$('#code').show();
				    	}
				    }else{
				    	eDialog.alert('获取分类信息失败!');
				    }
				}
			});
     };
     
     _btnEditCategory.bind('click',_editBtnListener);
     // 禁用店铺下拉显示框.
     $('#view-shop').attr('disabled','disabled');
     
});
