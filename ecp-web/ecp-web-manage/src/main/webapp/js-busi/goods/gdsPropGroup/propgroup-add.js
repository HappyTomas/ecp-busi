$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : true,
        'pCheck' : 'multi',
        'pCheckColumn' : true, //是否显示单选/复选框的列
        "sAjaxSource": GLOBAL.WEBROOT + '/gdspropgroup/gridproplist',
        //指定列数据位置
        "aoColumns": [
			{ "mData": "id", "sTitle":"属性编码","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "propName", "sTitle":"属性名称","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "id","sTitle":"操作","sWidth":"80px","sClass": "center",
				"mRender": function(data,type,row){
					return "<a href='javascript:void(0);' onclick=\"propAdd(this,'"+row.id+"','"+row.propName+"')\">添加</a>";
				}
			}
        ]
	});
	
	var modifyBiz = function(){
		var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length>=1){
			//
		}else if(ids && ids.length>1){
			eDialog.alert('只能选择一条商品记录进行操作！');
		}else if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一条商品记录进行操作！');
		}
	};
	//查询
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	//重置
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	//批量添加属性
	$('#btn_code_add').click(function(){
		PropGroup.batchAddGds();
	});
	//批量删除
	$("#btn_code_remove").click(function(){
		PropGroup.batchRemove();
	});
	$("#btnFormSave").click(function(){
		if(!$("#addForm").valid())return;
		PropGroup.saveGroupInfo(this);
	});
	$("#btnFormCancle").click(function(){
		window.location.href= GLOBAL.WEBROOT+'/gdspropgroup';
	});
});
function propAdd(obj,propId,propName){
	PropGroup.propAdd(obj,propId,propName);
}
function checkAllGoodsList(obj){
	PropGroup.checkAllGoodsList(obj);
}
function delteGds(obj){
	PropGroup.delteGds(obj);
}
var PropGroup = {
		propAdd : function(obj,propId,propName){
			var count = 0;
			$("#selectedProp").find('tr').each(function(){
				var $this = $(this);
				var id = $this.find("input[name='propList']").attr('id');
				if(propId == id){
					count ++;
				}
			});
			if(count == 0){
				var html ="<tr id='36994' role='row' class='odd'>" +
				"<td class=' center selectColumn'><input type='checkbox' name='propList' id='"+propId+"'></td>" +
				"<td class=' center'>"+propId+"</td>" +
				"<td class=' center'>"+propName+"</td>" +
				"<td class=' center'><a href='javascript:void(0);' onclick='delteGds(this)'>删除</a></td></tr>";
				$("#selectedProp").append(html);
			}
		},
		batchAddGds : function(){
			var ids = $('#dataGridTable').getCheckIds();
			for(var i = 0 ;i<ids.length;i++){
				var count = 0;
				var propId = ids[i];
				$("#selectedProp").find('tr').each(function(){
					var $this = $(this);
					var id = $this.find("input[name='propList']").attr('id');
					if(propId == id){
						count ++;
					}
				});
				if(count == 0){
					var propName = "";
					$("#dataGridTable").find('tr').each(function(){
						$this = $(this);
						if($this.attr('id')==propId){
							propName = $this.find('td').eq(2).text();
						}
					});
					var html ="<tr id='36994' role='row' class='odd'>" +
					"<td class=' center selectColumn'><input type='checkbox' name='propList' id='"+propId+"'></td>" +
					"<td class=' center'>"+propId+"</td>" +
					"<td class=' center'>"+propName+"</td>" +
					"<td class=' center'><a href='javascript:void(0);' onclick='delteGds(this)'>删除</a></td></tr>";
					$("#selectedProp").append(html);
				}
			}
		},
		checkAllGoodsList : function(obj){
			if($(obj).attr("checked") == "checked"){
				$(":input[name='propList']").attr("checked", true);
			} else {
				$(":input[name='propList']").attr("checked", false);
			}
		},
		delteGds : function(obj){
			$(obj).parent().parent().remove();
		},
		batchRemove : function(){
			var _count = 0;
			$(":input[name='propList']").each(function(){
				var _this = $(this);
				if(_this.attr('checked')=='checked'){
					_this.parent().parent().remove();
					_count ++;
				}
			});
			if(_count ==0){
				eDialog.error("请至少选择一条记录删除");
				return ;
			}
		},
		saveGroupInfo : function(obj){
			var param = {};
			var propParam = "[";
			var _c = 0;
			$("#selectedProp").find('tr').each(function(){
				_c ++;
				var $this = $(this);
				var id = $this.find("input[name='propList']").attr('id');
				var propName = $this.find("td").eq(2).text();
				propParam += "{propId:"+id+",propName:'"+propName+"'},";
			});
			propParam += "]";
			if(_c == 0){
				eDialog.alert("请至少添加一条属性");
				return ;
			}
			param.propIdParam = propParam;
			param.groupDesc = $("#groupDesc").val();
			param.groupName = $("#groupName").val();
			var btn = $(obj);
			btn.button('loading');//设置按钮为处理状态，并为中读，不允许点击
			$.eAjax({
				url : GLOBAL.WEBROOT + "/gdspropgroup/savegroupinfo",
				data : param,
				success : function(returnInfo) {
					btn.button('reset');
					if(returnInfo.ecpBaseResponseVO.resultFlag=='ok'){
						eDialog.success('属性组保存成功！',{
							buttons:[{
								caption:"确定",
								callback:function(){
									window.location.href = GLOBAL.WEBROOT + '/gdspropgroup';
						        }
							}]
						});
					}else{
						eDialog.error('性组保存失败！');
					}	
				},
				exception : function(){
					btn.button('reset');
				}
			});
		}
};