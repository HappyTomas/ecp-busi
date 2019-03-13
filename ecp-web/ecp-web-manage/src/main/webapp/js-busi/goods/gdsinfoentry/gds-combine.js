$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : true,
        'pCheck' : 'multi',
        'pPageSize' : 5,//设置每页显示记录数
        "sAjaxSource": GLOBAL.WEBROOT + '/gdsmanage/gridlist',
        //指定列数据位置
        "aoColumns": [
			{ "mData": "id", "sTitle":"商品编码","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "gdsName", "sTitle":"商品名称","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "gdsStatus","sTitle":"操作","sWidth":"80px","sClass": "center",
				"mRender": function(data,type,row){
					return "<a href='#' onclick=\"gdsAdd(this,'"+row.id+"','"+row.gdsName+"')\">添加</a>";
				}
			}
        ]
	});
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	var getParam = _param.param;
	var json = eval(getParam);
	for(var i =0;i<json.length;i++){
		var obj = json[i];
		var html ="<tr id='36994' role='row' class='odd'>" +
		"<td class=' center selectColumn'><input type='checkbox' name='gdsList' id='"+obj.gdsId+"'></td>" +
		"<td class='center sorting_1'>"+obj.gdsId+"</td>" +
		"<td class=' center'>"+obj.gdsName+"</td>" +
		"<td class=' center'><a href='#' onclick='GdsCombine.delteGds(this)'>删除</a></td></tr>";
		$("#addGdsInfo").append(html);
	}
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
	//批量添加商品
	$('#btn_code_add').click(function(){
		GdsCombine.batchAddGds();
	});
	//批量删除
	$("#btn_code_remove").click(function(){
		GdsCombine.batchRemove();
	});
	
	//确定
	$("#btnFormSave").click(function(){
		var param = "[";
		$("#addGdsInfo").find('tr').each(function(){
			var $this = $(this);
			var id = $this.find("input[name='gdsList']").attr('id');
			var gdsName = $this.find("td").eq(2).text();
			param += "{gdsId:'"+id+"',gdsName:'"+gdsName+"'},";
		});
    	param += "]";
		bDialog.closeCurrent({
			'param' : param
		});
	});
	$('#btnFormCancle').click(function(){
		bDialog.closeCurrent();
	});
});

function gdsAdd(obj,gdsId,gdsName){
	GdsCombine.gdsAdd(obj,gdsId,gdsName);
}
function checkAllGoodsList(obj){
	GdsCombine.checkAllGoodsList(obj);
}
var GdsCombine = {
		gdsAdd : function(obj,gdsId,gdsName){
			var count = 0;
			$("#addGdsInfo").find('tr').each(function(){
				var $this = $(this);
				var id = $this.find("input[name='addGdsInfo']").attr('id');
				if(propId == id){
					count ++;
				}
			});
			if(count ==0){
				var html ="<tr id='36994' role='row' class='odd'>" +
				"<td class=' center selectColumn'><input type='checkbox' name='gdsList' id='"+gdsId+"'></td>" +
				"<td class='center sorting_1'>"+gdsId+"</td>" +
				"<td class=' center'>"+gdsName+"</td>" +
				"<td class=' center'><a href='#' onclick='GdsCombine.delteGds(this)'>删除</a></td></tr>";
				$("#addGdsInfo").append(html);
			}
		},
		batchAddGds : function(){
			var ids = $('#dataGridTable').getCheckIds();
			for(var i = 0 ;i<ids.length;i++){
				var count = 0;
				var gdsId = ids[i];
				$("#addGdsInfo").find('tr').each(function(){
					var $this = $(this);
					var id = $this.find("input[name='gdsList']").attr('id');
					if(gdsId == id){
						count ++;
					}
				});
				if(count == 0){
					var gdsName = "";
					$("#dataGridTable").find('tr').each(function(){
						$this = $(this);
						if($this.attr('id')==gdsId){
							gdsName = $this.find('td').eq(2).text();
						}
					});
					var html ="<tr role='row' class='odd'>" +
					"<td class=' center selectColumn'><input type='checkbox' name='gdsList' id='"+gdsId+"'></td>" +
					"<td class='center sorting_1'>"+gdsId+"</td>" +
					"<td class=' center'>"+gdsName+"</td>" +
					"<td class=' center'><a href='#' onclick='GdsCombine.delteGds(this)'>删除</a></td></tr>";
					$("#addGdsInfo").append(html);
				}
			}
		},
		checkAllGoodsList : function(obj){
			if($(obj).attr("checked") == "checked"){
				$(":input[name='gdsList']").attr("checked", true);
			} else {
				$(":input[name='gdsList']").attr("checked", false);
			}
		},
		delteGds : function(obj){
			$(obj).parent().parent().remove();
		},
		batchRemove : function(){
			$("#btnFormCancle").html('');
		}
};
