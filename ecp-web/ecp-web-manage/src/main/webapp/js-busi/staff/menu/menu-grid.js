$(function(){
	
	
	$('#btnserachTree').click(function(evt){
		clearFrom();
		$.fn.zTree.init($("#tree"), classify.setting);
	});
	var clearFrom = function(){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getSelectedNodes();
		if (nodes.length>0) { 
			treeObj.cancelSelectedNode(nodes[0]);
		}	
		ebcForm.resetForm('#menudetail');
    	$('#menu_id').html("");
    	$('#parentMenuId').html("");
    	$('#btn_menu_save').attr('disabled', true);

	};
	$('#btn_menu_clear').click(function(evt){
		clearFrom();
	});
	
	
	$('#das').click(function(){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getSelectedNodes();
		if (nodes.length>0) { 
			treeObj.cancelSelectedNode(nodes[0]);
		}	
		ebcForm.resetForm('#menudetail');
    	$('#menu_id').html("");
    	$('#parentMenuId').html("");
    	$('#btn_menu_save').attr('disabled', true);

	});
	
	$('#tree').click(function(){
//		$(this).stopPropagation();
		return false;
	});
	
	$('#btn_menu_save').click(function(){
		
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes = treeObj.getSelectedNodes();
        
		if(!$("#menudetail").valid()) return false;
		var val = ebcForm.formParams($("#menudetail"));
		eDialog.confirm("您确认要保存吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
				
					
					$.eAjax({
						url : GLOBAL.WEBROOT + "/menu/savemenu",
						data : val,
						datatype:'json',
						success : function(data) {
							if(data != null)
							{
								if(data.isNewCreate)
								{
									var newNode = {id:data.id,name:data.name,pId:data.pId,isParent:data.isParent,menuType:data.menuType,sysCode:data.sysCode};
									treeObj.addNodes(nodes[0], newNode);
							    	$('#id').attr('value', data.id);
							    	$('#menu_id').html(data.id);
								}
								else{
									nodes[0].name = data.name;
									treeObj.updateNode(nodes[0]);
								}
								treeObj.refresh();	
							}
							eDialog.alert('保存成功！');
						}
					});
					
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
	
	});
	
	$('#btn_menu_dir_add').click(function(){
		
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes =treeObj.getSelectedNodes(); 
        if(nodes[0] != null)
        {
	        if(nodes[0].menuType == 1 || nodes[0].menuType == 2)
	        {
	        	alert("菜单或者功能不能增加子目录");
	        	return ;
	        }
        }
        var parentName = "root";
        var parentId = "0";
        var menuType = "0";
        var sysCode = $("#sysCodeSearch").val();  
        $('#sysCode').removeAttr('disabled'); 
        ebcForm.resetForm('#menudetail');
        if(nodes[0] != null)
        {
        	parentName = nodes[0].name;
        	parentId = nodes[0].id;
        	$('#sysCode').attr('value', nodes[0].sysCode);
        	$('#sysCode').attr('disabled','true');
        }
		$('#menuType').attr('value', menuType);
    	$('#parentmenuID').attr('value', parentId);
    	$('#parentMenuId').html(parentName);
		
    	$('#menu_id').html("");
    	$('#id').attr('value', null);	
    	$('#menuUrl').attr('value', null);	
    	$('#btn_menu_save').attr('disabled', false);
    	$('#sortOrderDiv').hide();//隐藏

	});
	
	$('#btn_menu_add').click(function(){
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes =treeObj.getSelectedNodes(); 
        if(nodes[0] != null)
        {
	        if(nodes[0].menuType == 1 || nodes[0].menuType == 2)
	        {
	        	alert("菜单或者功能不能增加子菜单");
	        	return ;
	        }
        }
        var parentName = "root";
        var parentId = "0";
        var menuType = "1";
        var sysCode = $("#sysCodeSearch").val();  
        ebcForm.resetForm('#menudetail');
        if(nodes[0] != null)
        {
        	parentName = nodes[0].name;
        	parentId = nodes[0].id;
        	$('#sysCode').val(nodes[0].sysCode);
        	$('#sysCode').attr('disabled','true');
        }
		$('#menuType').attr('value', menuType);
    	$('#parentmenuID').attr('value', parentId);
    	$('#parentMenuId').html(parentName);
		
    	$('#menu_id').html("");
    	$('#id').attr('value', null);	
    	$('#menuUrl').attr('value', null);	
    	$('#btn_menu_save').attr('disabled', false);
    	$('#sortOrderDiv').hide();//隐藏
	});

	$('#btn_menu_del').click(function(evt){
		
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes =treeObj.getSelectedNodes(); 
        if(nodes[0] == null)
        {
			eDialog.alert('请选择选择一个选项进行操作！');
        }
        else
	    {
			eDialog.confirm("您确认要删除该选项吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						eDialog.alert('删除成功！');
						
						$.eAjax({
							url : GLOBAL.WEBROOT + "/menu/menudelete",
							data : nodes[0],
							datatype:'json',
							success : function(data) {
								if(data != null)
								{
									treeObj.removeNode(nodes[0]);
									$.fn.zTree.init($("#tree"), classify.setting);
									
								}
								
							}
						});
						
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			}); 
	    }
	});
/////////////-------------------------名字匹配-------------------------///////////////////
	$('#treeSerach').click(function(evt){
		var menuName =  $("#searchValue").val(); 
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes = treeObj.getNodesByParamFuzzy("name", menuName, null);
        //隐藏树
        var allnodes = treeObj.getNodes(); 
        treeObj.hideNodes(allnodes);
        
        for(var i=0;i<nodes.length;i++)
    	{
        	//显示父节点
        	showParentNodes(nodes[i]);
        	treeObj.showNode(nodes[i]);
        	var node = nodes[i].getParentNode();
        	treeObj.expandNode(node, true, true, true);

    	}
	});
	
	function showParentNodes(nodes)
	{
        var treeObj = $.fn.zTree.getZTreeObj("tree");
    	var node = nodes.getParentNode();
    	if(node != null)
    	{
        	treeObj.showNode(node);
    		showParentNodes(node);
    	}
	}
	
/////////////----------------------------zTree js代码 开始 -----------------------------///////////////////	
	
function onTreeClick(event, treeId, treeNode, clickFlag) {
	$.eAjax({
		url : GLOBAL.WEBROOT+"/menu/onenode",
		type : "POST",
		cache : false,
		timeout : 5000,
		data : {"menu_id":treeNode.id},
		success : function(json) {
		    if(null != json){
		    	// 自动填充分类信息并展示。
		    	$('#menuUrl').val("");
		    	$('#menuDesc').val("");
		    	$('#sortOrderDiv').show();//隐藏

		    	ebcForm.formDataFill('menudetail',json);
		    	$('#menu_id').html(treeNode.id);
		    	if(json.menuDesc != null)
		    	{
		    		$('#menuDesc').val(json.menuDesc);
		    	}

		    	
		    	// 执行父节点名称填充。
		    	var pNode = treeNode.getParentNode();
		    	if(pNode){
		    		$('#parentMenuId').html(pNode.name);
		    	}
		    	else
		    	{
		    		$('#parentMenuId').html("root");
		    	}
		    	$('#sysCode').attr('disabled','true');
		    }else{
		    	eDialog.alert('获取菜单信息失败!');
		    }
		}
	});
	$('#btn_menu_save').attr('disabled', false);

}

function getRoot()
        {
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



var classify = {
// 树设置.
setting : {
	
	view:{
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable: true,
//			idKey:"id",
//			pIdKey:"pId"
				
		}
	},
	async: {
		enable: true,
		url:GLOBAL.WEBROOT+"/menu/nodedatas",
		type:"post",
		dataType:"json",
		autoParam:["id=parentMenuId"],
		otherParam:{"sysCode":function(){
			return $('#sysCodeSearch').val();
			}
		},
		dataFilter: null
	},
	callback: {
		onClick :onTreeClick
		}
   }
};

$.fn.zTree.init($("#tree"), classify.setting);
clearFrom();
/////////////----------------------------zTree js代码 结束-----------------------------///////////////////	
});