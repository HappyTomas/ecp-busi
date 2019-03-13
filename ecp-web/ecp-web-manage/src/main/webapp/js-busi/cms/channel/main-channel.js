$(function(){
	
	//绑定链接小工具
	linkInputInit();
	
	//清空
	$('#clean_link').click(function(){
    	$('#channelUrl').val("");
	});
	
	//初始化上传图片
	initUploadImage();
	
	$("#clean_image").unbind("click.ci").bind("click.ci",function(){
		$("#channelIcon").val('');
		$("#imagePreview").attr("src",$("#imagePreviewHidden").val());
	});
	//绑定站点选择查询
	var initFormBySearch = function(){
		$('#siteId').attr('value', $("#sysSiteIdSearch").val()||"");
		$('#platformType').attr('value', $("#cmsPlatformTypeSearch").val()||"");
		$('#channelType').attr('value', $("#cmsChannelTypeSearch").val()||"");
	}
	$('#sysSiteIdSearch').change(function(){
		clearFrom();
		initFormBySearch();
		$.fn.zTree.init($("#tree"), classify.setting);
	});
	//绑定平台选择查询
	$('#cmsPlatformTypeSearch').change(function(){
		clearFrom();
		initFormBySearch();
		$.fn.zTree.init($("#tree"), classify.setting);
	});
	//绑定栏目类型选择查询
	$('#cmsChannelTypeSearch').change(function(){
		clearFrom();
		initFormBySearch();
		$.fn.zTree.init($("#tree"), classify.setting);
	});
	/*$('#btnserachTree').click(function(evt){
		clearFrom();
        var siteId = $("#sysSiteIdSearch").val();
		$('#siteId').attr('value', siteId);
		$.fn.zTree.init($("#tree"), classify.setting);
	});*/
	
	//绑定树查询
	$('#das').click(function(){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getSelectedNodes();
		if (nodes.length>0) { 
			treeObj.cancelSelectedNode(nodes[0]);
		}	
		ebcForm.resetForm('#channeldetail');
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
        
		if(!$("#channeldetail").valid()) return false;
		var val = ebcForm.formParams($("#channeldetail"));
		eDialog.confirm("您确认要保存吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					$.eAjax({
						url : GLOBAL.WEBROOT + "/cms/channel/save",
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
	
	//绑定新增栏目按钮
	$('#btn_menu_dir_add').click(function(){
		$("#imagePreview").attr('src',$("#imagePreviewHidden").val());
		//--用于保存上一个节点数据---只在在一个节点基础上建子节点有用 s//
		var preSiteId = $('#siteId').val();
		var prePlatformType = $('#platformType').val();
		var preChannelType = $('#channelType').val();
		//--用于保存上一个节点数据---e//
		ebcForm.resetForm('#channeldetail');
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes =treeObj.getSelectedNodes(); 
        if(nodes[0] != null){
        	$('#channelParentName').attr('value', nodes[0].name);
        	$('#channelParent').attr('value', nodes[0].id);
        	$('#siteId').attr('value', preSiteId||"");
    		$('#platformType').attr('value', prePlatformType||"");
    		$('#channelType').attr('value', preChannelType||"");
        }else{
        	$('#channelParentName').attr('value', "");
        	$('#channelParent').attr('value', 0);
        	initFormBySearch();
        }
		
		isresiteinfoChange();
		platformTypeChange();
		isOutLinkChange();
		initFrom();
		$('#btn_menu_save').show();
	});

	//绑定删除按钮
	$('#btn_menu_del').click(function(evt){
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes =treeObj.getSelectedNodes(); 
        var id = nodes[0];
        if(nodes[0] == null){
			eDialog.alert('请选择选择一个选项进行操作！');
        }else{
        	$.eAjax({
        		url : GLOBAL.WEBROOT + "/cms/channel/isexistarticle",
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
        									url : GLOBAL.WEBROOT + "/cms/channel/delete",
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
        					eDialog.confirm("您确认要删除该选项吗？", {
        						buttons : [{
        							caption : '确认',
        							callback : function(){
        								$.eAjax({
        		    						url : GLOBAL.WEBROOT + "/cms/channel/delete",
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
        				
        			}
        		}
        	});
        	//--------------------------
			
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
				url:GLOBAL.WEBROOT+"/cms/channel/loadnodes",
				type:"post",
				dataType:"json",
				autoParam:["id=channelParent"],
				otherParam:{
					"siteId":function(){
						return $('#sysSiteIdSearch').val();
					},
					"platformType":function(){
						return $('#cmsPlatformTypeSearch').val();
					},
					"channelType":function(){
						return $('#cmsChannelTypeSearch').val();
					}
				},
				dataFilter: null
			},
			callback: {
				onClick :onTreeClick
			}
		}
	};
	
	//绑定是否外部链接
	$('#isOutLink').bind('change', function(){
		isOutLinkChange();
		linkInputInit();
	});
	
	//绑定是否选择关联网站信息
	$('#isresiteinfo').bind('change', function(){
		isresiteinfoChange();
	});
	
	//绑定网站信息
	$('#siteinfoId').bind('change', function(){
		siteinfoChange();
	});
	
	//绑定平台类型
	$('#siteId').bind('change', function(){
		linkInputInit();
	});
	
	//绑定平台类型
	$('#platformType').bind('change', function(){
		platformTypeChange();
		linkInputInit();
	});
	
	//初始化树及右侧表单
	$.fn.zTree.init($("#tree"), classify.setting);
	
	//清空右侧表单
	clearFrom();
	
	isOutLinkChange();
	
	isresiteinfoChange();
	
	platformTypeChange();
});

//绑定点击树事件
function onTreeClick(event, treeId, treeNode, clickFlag) {
	$.eAjax({
		url : GLOBAL.WEBROOT+"/cms/channel/onenode",
		type : "POST",
		cache : false,
		timeout : 5000,
		data : {
			"id":treeNode.id,
			"siteId":treeNode.siteId
		},
		success : function(json) {
		    if(null != json){
		    	ebcForm.resetForm('#channeldetail');
		    	// 自动填充分类信息并展示。
		    	ebcForm.formDataFill('channeldetail',json);
		    	
		    	// 执行父节点名称填充。
		    	var pNode = treeNode.getParentNode();
		    	if(pNode){
		    		$('#channelParentName').attr("value",pNode.name);
		    	}else{
		    		$('#channelParentName').attr("value","");
		    	}
		    	
		    	$("#siteinfoId_hid").val(json.siteinfoId);
		    	$("#channelUrl_hid").val(json.channelUrl);
		    	$("#imagePreview").attr("src",json.vfsUrl);
		    	isOutLinkChange();
		    	isresiteinfoChange();
		    	platformTypeChange();
		    	initFrom();
		    	$('#btn_menu_save').show();
		    }else{
		    	eDialog.alert('获取菜单信息失败!');
		    }
		}
	});
}

//绑定是否外部链接
function isOutLinkChange(){
    var isOutLink = $("#isOutLink").val();
	if(isOutLink == '1'){//是，绝对路径
		$("#isresiteinfo_div").hide();
		$("#siteinfo_div").hide();
		$("#channelUrl").addClass("fullTypeUrl");
		$("#channelUrl").removeAttr("readonly");
		$("#channelUrl_span").html("请填绝对路径：例如：http://www.163.com！");
	}else{//否，相对路径
		$("#isresiteinfo_div").show();
		$("#channelUrl").removeClass("fullTypeUrl");
		$("#channelUrl_span").html("请填相对路径：例如：about?siteInfoId=1！");
		//isresiteinfoChange();
	}
	
	linkInputInit();//重置链接工具
}

//绑定是否关联网站信息下拉框
function isresiteinfoChange(){
	var	siteinfoId_hid = $("#siteinfoId_hid").val();
	var	channelUrl_hid = $("#channelUrl_hid").val();
    var isresiteinfo = $("#isresiteinfo").val();
    $("#channelUrl").val(channelUrl_hid);
	if(isresiteinfo == '1'){//是
		qrySiteInfo(siteinfoId_hid);
		$("#siteinfo_div").show();
		$("#channelUrl").attr("readonly","readonly");
	}else{//否
		//$("#siteinfoId").val(siteinfoId);
		$("#siteinfo_div").hide();
		$("#channelUrl").removeAttr("readonly");
	}
}

//绑定网站信息下拉框
function siteinfoChange(){
	var channelUrl = "";
    var siteinfoId = $("#siteinfoId").val();
    if(siteinfoId){
    	channelUrl = "/about?siteInfoId="+ siteinfoId;
    }
    $("#channelUrl").val(channelUrl);
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
				$("#channelIcon").val(fileInfo.fileId);
				$("#imagePreview").attr("src",fileInfo.url);
			}
		}
	});
}
//绑定平台类型下拉框
function platformTypeChange(){
    var platformType = $("#platformType").val();
    if(platformType == "01"){
    	$("#uploadFileObj").data("placeWidth","100");
    	$("#uploadFileObj").data("placeHeight","100");
    	$("#uploadWarn").text("温馨提醒：请上传小于100k的图片（jpg,png,jpeg,gif），规格为100*100px！");
    }else if(platformType == "02"){
    	$("#uploadFileObj").data("placeWidth","80");
    	$("#uploadFileObj").data("placeHeight","80");
    	$("#uploadWarn").text("温馨提醒：请上传小于100k的图片（jpg,png,jpeg,gif），规格为80*80px！");
    }else if(platformType == "03"){
    	$("#uploadFileObj").data("placeWidth","80");
    	$("#uploadFileObj").data("placeHeight","80");
    	$("#uploadWarn").text("温馨提醒：请上传小于100k的图片（jpg,png,jpeg,gif），规格为80*80px！");
    }
	initUploadImage();
}

//根据站点获取网站信息
function qrySiteInfo(siteinfoId){
	//var siteinfoId = $("#siteinfoId_hid").val();
	$('#siteinfoId').empty();
	$("#siteinfoId").append("<option value = ''>--请选择--</option>");
	var siteId = $('#siteId').val();
	if(siteId != "" && siteId != null){
		$.eAjax({
			url : $webroot + 'siteinfo/qrySiteInfoList',
			data : {
				"siteId":siteId,
				"status":"1"
			},
			success : function(returnInfo){
				for(var i in returnInfo){
					var selectedStr = "";
					if(siteinfoId == String(returnInfo[i].id)){
						selectedStr = "selected";
					}
					var option = "<option value ="+"\""+returnInfo[i].id+"\" "+selectedStr+" >"+returnInfo[i].siteInfoName+"</option>";
					$("#siteinfoId").append(option);
				}
				//同步下网站信息与地址关系
				siteinfoChange();
			}	
		});
	}
}

//判断栏目下是否存在文章f
function isExistArticle(id){
	var treeObj = $.fn.zTree.getZTreeObj("tree");
    var nodes =treeObj.getSelectedNodes(); 
	$.eAjax({
		url : GLOBAL.WEBROOT + "/cms/channel/isexistarticle",
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
									url : GLOBAL.WEBROOT + "/cms/channel/delete",
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
						url : GLOBAL.WEBROOT + "/cms/channel/delete",
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
//初始化右侧表单
function initFrom(){
	var channelId = $("#id").val();
	var $isOutLink = $("#isOutLink");
	//初始化字数
	$.isFunction(checkLen) && checkLen($("#remark").get(0),'count','250');
	if(channelId){
		$isOutLink.attr("disabled","disabled");
		$isOutLink.next("span.help-inline").text("");
	}else{
		$isOutLink.removeAttr("disabled");
		$isOutLink.next("span.help-inline").text("请选择是否外部链接，保存后不能修改！");
	}
}

//清空右侧表单
function clearFrom(){
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	var nodes = treeObj.getSelectedNodes();
	if (nodes.length>0) { 
		treeObj.cancelSelectedNode(nodes[0]);
	}	
	ebcForm.resetForm('#channeldetail');
	$('#btn_menu_save').hide();
};

//初始化链接小工具
function linkInputInit(){
	//站点id
	var siteId = $("#siteId").val();
	//平台类型
	var flatType = $("#platformType").val();
	var tayes = null;
	var busiParams = null;
	var urlMap = null;
	if(flatType == '01'){
		flatType = "web";
		tayes = ["secondpage","catg","prom"];//,"sitenav"
		busiParams = {"secondpage":{"pageTypeId":5},"prom":{"pageTypeId":2}}
	}else if(flatType == '02'){
		flatType = "app";
		tayes = ["catg"];//,"sitenav"
		if(1 == siteId){
			urlMap = {
					"catgCode":"/pmph/goodList/pageInit?catgCode={key}",
					"keyWord":"/pmph/goodList/pageInit?keyWord={key}"
					}
		}else if (2 == siteId){
			urlMap = {
					"catgCode":"/pmph/jfGoodList/pageInit?catgCode={key}",
					"keyWord":"/pmph/jfGgoodList/pageInit?keyWord={key}"
					}
		}
	}else if(flatType == '03'){
		flatType = "mobile";
	}
	//是否外部链接
	var isOutLink = $('#isOutLink').val();
	if(isOutLink && isOutLink != '1' && flatType != "mobile"){
		$("#select_link").show();
		//绑定链接小工具
		$("#select_link").cmsLinkInputInit({
			"siteId":siteId,
			"types":tayes,
			//info-信息,prom-活动,good-商品,catg-分类,homepage-首页,secondpage-二级页,sitenav-站内导航
			"busiParams":busiParams,//格式为{"good"：{"siteId":1}}
			"flatType":flatType,//web , app , mobile 
			"urlMap":urlMap,//格式为{"good":"gdsdetail/{key}-""}
			"callback":function(data){
				if(data && data.success == true && data.url){
					$("#channelUrl").val(data.url);
		    	}
			}
		});
	}else{
		$("#select_link").unbind("click");
		$("#select_link").hide();
	}
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