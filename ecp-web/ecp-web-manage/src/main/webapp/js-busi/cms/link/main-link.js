$(function(){
	
	$("#select_channel").live('click',function(){
		var $this = $(this);
		var siteId=$("#siteId").val();
		bDialog.open({
			title : '选择栏目',
			width : 340,
			height : 565,
			url : GLOBAL.WEBROOT + '/cms/channel/openchannel?siteId='+$("#siteId").val()+"&channelType=04&isOutLink=0",
			params : {
				'param' : $this.parent().find('input[id="channelId"]').val(),
				'checkType':"radio",
				'siteId' : $("#siteId").val(),
				'channelType':'04'
			},
			callback:function(data){
				if(data && data.results && data.results.length > 0 ){
					$this.parent().find('input[id="linkUrl"]').val("/helpcenter/channel/"+data.results[0].param);
				} 
			}
		});
	});
	
	$("#select_article").live('click',function(){
		var $this = $(this);
		var siteId=$("#siteId").val();
		bDialog.open({
			title : '选择文章',
			width : 860,
			height : 500,
			url : GLOBAL.WEBROOT + '/article/openArticle?siteId='+$("#siteId").val(),
			params : {
				'siteId' : $("#siteId").val()
			},
			callback:function(data){
				if(data && data.results && data.results.length > 0 ){
					$this.parent().find('input[id="linkUrl"]').val("/channel/article/"+data.results[0].linkUrl);
				} 
			}
		});
	});
	
	//清空
	$('#clean_url').click(function(){
    	$('#linkUrl').val("");
	});
	
	//初始化上传图片
	initUploadImage();
	
	$("#clean_image").unbind("click.ci").bind("click.ci",function(){
		$("#mediaUuid").val('');
		$("#imagePreview").attr("src",$("#imagePreviewHidden").val());
	});
	
	//绑定站点选择查询
	$('#sysSiteIdSearch').change(function(){
		clearFrom();
        var siteId = $("#sysSiteIdSearch").val();
		$('#siteId').attr('value', siteId);
		$.fn.zTree.init($("#tree"), classify.setting);
	});
	
	//绑定链接类型选择查询
	$('#cmslinkTypeSearch').change(function(){
		clearFrom();
		var linkType = $("#cmslinkTypeSearch").val();
		$('#linkType').attr('value', linkType);
		$.fn.zTree.init($("#tree"), classify.setting);
	});
	
	//绑定树查询
	$('#das').click(function(){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getSelectedNodes();
		if (nodes.length>0) { 
			treeObj.cancelSelectedNode(nodes[0]);
		}	
		ebcForm.resetForm('#linkdetail');
    	$('#btn_menu_save').hide();
	});
	
	$('#tree').click(function(){
//		$(this).stopPropagation();
		return false;
	});
	
	//绑定保存按钮
	$('#btn_menu_save').click(function(){
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes = treeObj.getSelectedNodes();
        
		if(!$("#linkdetail").valid()) return false;
		var val = ebcForm.formParams($("#linkdetail"));
		eDialog.confirm("您确认要保存吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					$.eAjax({
						url : GLOBAL.WEBROOT + "/link/saveNode",
						data : val,
						datatype:'json',
						beforeSend:function(){
							$.gridLoading({"message":"正在加载中...."});
						},
						success : function(data) {
							if(data != null){
								if(data.newCreate){
									var newNode = {id:data.id,name:data.name,pId:data.pId,isParent:data.isParent};
									treeObj.addNodes(nodes[0], newNode);
							    	$('#id').attr('value', data.id);
								}else{
									nodes[0].name = data.name;
									treeObj.updateNode(nodes[0]);
								}
								eDialog.alert('保存成功！');
								treeObj.refresh();	
								
								//设置选中当前节点
								var allNodes = treeObj.getNodes();
								if (allNodes.length>0) {
									treeObj.selectNode(nodes[0]);
								}
							}
							/*clearFrom();*/
							//隐藏保存按钮
							$("#btn_menu_save").hide();
						},error : function(e,xhr,opt){
							eDialog.error("出现异常!");
							$.gridUnLoading();
						},complete:function(){
							$.gridUnLoading();
						}
					});
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
	});
	
	//绑定新增链接按钮
	$('#btn_menu_dir_add').click(function(){
		$("#imagePreview").attr('src',$("#imagePreviewHidden").val());
		ebcForm.resetForm('#linkdetail');
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes =treeObj.getSelectedNodes(); 
        if(nodes[0] != null){
        	$('#linkParentName').attr('value', nodes[0].name);
        	$('#linkParent').attr('value', nodes[0].id);
        }else{
        	$('#linkParentName').attr('value', "");
        	$('#linkParent').attr('value', 0);	
        }
        var siteId = $("#sysSiteIdSearch").val();
		$('#siteId').attr('value', siteId);
		$('#btn_menu_save').show();
	});

	//绑定删除按钮
	$('#btn_menu_del').click(function(evt){
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes =treeObj.getSelectedNodes(); 
        var id = nodes[0];
        if(nodes[0] == null){
			eDialog.alert('请选择一个选项进行操作！');
        }else{
			//----------------
			eDialog.confirm("您确认要删除该选项吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
    						url : GLOBAL.WEBROOT + "/link/deleteNode",
    						data : nodes[0],
    						datatype:'json',
    						beforeSend:function(){
    							$.gridLoading({"message":"正在加载中...."});
    						},
    						success : function(data) {
    							if(data != null){
    								treeObj.removeNode(nodes[0]);
    								clearFrom();
    								eDialog.alert('删除成功！');
    							}
    						},error : function(e,xhr,opt){
    							eDialog.error("出现异常!");
    							$.gridUnLoading();
    						},complete:function(){
    							$.gridUnLoading();
    						}
    					});
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			}); 
			//---------------------------------
	    }
        //----------------------------
	});
	
/////////////-------------------------名字匹配-------------------------///////////////////
	//绑定Ztree查询按钮
	$('#treeSerach').click(function(evt){
		var menuName =  $("#searchValue").val(); 
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes = treeObj.getNodesByParamFuzzy("name", menuName, null);
        //隐藏树
        var allnodes = treeObj.getNodes(); 
        treeObj.hideNodes(allnodes);
        
        for(var i=0;i<nodes.length;i++){
        	//显示父节点
        	showParentNodes(nodes[i]);
        	treeObj.showNode(nodes[i]);
        	var node = nodes[i].getParentNode();
        	treeObj.expandNode(node, true, false, false);
    	}
       
	});
	
	// 树设置.
	var classify = {
		setting : {
			view:{
				selectedMulti: false
			},
			data: {
				simpleData: {
					enable: true,
					idKey:"id",
					pIdKey:"pId"
				}
			},
			async: {
				enable: true,
				url:GLOBAL.WEBROOT+"/link/loadnodes",
				type:"post",
				dataType:"json",
				autoParam:["id=linkParent"],
				otherParam:{
					"siteId":function(){
						return $('#sysSiteIdSearch').val();
					},
					"linkType":function(){
						return $('#cmsLinkTypeSearch').val();
					}
				},
				dataFilter: null
			},
			callback: {
				onClick :onTreeClick
			}
		}
	};
	
	//初始化树及右侧表单
	$.fn.zTree.init($("#tree"), classify.setting);
	
	//清空右侧表单
	clearFrom();
});

//绑定点击树事件
function onTreeClick(event, treeId, treeNode, clickFlag) {
	$.eAjax({
		url : GLOBAL.WEBROOT+"/link/onenode",
		type : "POST",
		cache : false,
		timeout : 5000,
		data : {
			"id":treeNode.id,
			"siteId":treeNode.siteId
		},
		success : function(json) {
		    if(null != json){
		    	ebcForm.resetForm('#linkdetail');
		    	// 自动填充分类信息并展示。
		    	ebcForm.formDataFill('linkdetail',json);
		    	
		    	// 执行父节点名称填充。
		    	var pNode = treeNode.getParentNode();
		    	if(pNode){
		    		$('#linkParentName').attr("value",pNode.name);
		    	}else{
		    		$('#linkParentName').attr("value","");
		    	}
		    	
		    	$("#siteinfoId_hid").val(json.siteinfoId);
		    	$("#linkUrl_hid").val(json.linkUrl);
		    	$("#imagePreview").attr("src",json.vfsUrl);
		    	$('#btn_menu_save').show();
		    }else{
		    	eDialog.alert('获取菜单信息失败!');
		    }
		}
	});
}

/**
 * 初始化图片上传
 */
function initUploadImage(){
	$("#uploadFileObj").eUploadBaseInit({
		imageMaxWidth:$("#uploadFileObj").data("placeWidth")||0,
		imageMaxHeight:$("#uploadFileObj").data("placeHeight")||0,
		fileSizeLimit:$("#uploadFileObj").data("placeSize")?$("#uploadFileObj").data("placeSize")+'KB':'0KB',
		callback:function(fileInfo){
			if(fileInfo && fileInfo.success){
				$("#mediaUuid").val(fileInfo.fileId);
				$("#imagePreview").attr("src",fileInfo.url);
			}
		}
	});
}

//判断栏目下是否存在文章
function isExistArticle(id){
	var treeObj = $.fn.zTree.getZTreeObj("tree");
    var nodes =treeObj.getSelectedNodes(); 
	$.eAjax({
		url : GLOBAL.WEBROOT + "/link/isexistarticle",
		data : id,
		datatype:'json',
		success : function(data) {
			if(data != null){
				if(data.resultFlag != "ok"){
					eDialog.confirm("该栏目下已经存在发布文章，您确认还要删除该选项吗？", {
						buttons : [{
							caption : '确认',
							callback : function(){
								//----------------
								$.eAjax({
									url : GLOBAL.WEBROOT + "/link/delete",
									data : nodes[0],
									datatype:'json',
									beforeSend:function(){
										$.gridLoading({"message":"正在加载中...."});
									},
									success : function(data) {
										if(data != null){
											treeObj.removeNode(nodes[0]);
											clearFrom();
											eDialog.alert('删除成功！');
										}
									},error : function(e,xhr,opt){
										eDialog.error("出现异常!");
										$.gridUnLoading();
									},complete:function(){
										$.gridUnLoading();
									}
								});
								//---------------------------------
							}
						},{
							caption : '取消',
							callback : $.noop
						}]
					}); 	
							
				}
				else{
					//----------------
					$.eAjax({
						url : GLOBAL.WEBROOT + "/link/delete",
						data : nodes[0],
						datatype:'json',
						beforeSend:function(){
							$.gridLoading({"message":"正在加载中...."});
						},
						success : function(data) {
							if(data != null){
								treeObj.removeNode(nodes[0]);
								clearFrom();
								eDialog.alert('删除成功！');
							}
						},error : function(e,xhr,opt){
							eDialog.error("出现异常!");
							$.gridUnLoading();
						},complete:function(){
							$.gridUnLoading();
						}
					});
					//---------------------------------
				}
				
			}
		}
	});
}

//清空右侧表单
function clearFrom(){
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	var nodes = treeObj.getSelectedNodes();
	if (nodes.length>0) { 
		treeObj.cancelSelectedNode(nodes[0]);
	}	
	ebcForm.resetForm('#linkdetail');
	$('#btn_menu_save').hide();
};


function showParentNodes(nodes){
    var treeObj = $.fn.zTree.getZTreeObj("tree");
	var node = nodes.getParentNode();
	if(node != null){
    	treeObj.showNode(node);
		showParentNodes(node);
	}
}

function getRoot(){
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    var nodes =treeObj.getSelectedNodes();  
    var selectNode;
    if(nodes.length>0){
        selectNode = nodes[0];
        while(0 != selectNode.level){
		   selectNode = selectNode.getParentNode();
		}
        return selectNode;
    }
}
	
/////////////----------------------------zTree js代码 结束-----------------------------///////////////////	