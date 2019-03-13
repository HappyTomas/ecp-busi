$(function(){
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	
	$('#btn_code_add_gdsList').unbind('click').click(function(){
		
		var ids = $('#dataGridTable').getCheckIds();
		
		if(ids && ids.length>=1){
			var _val = $('#dataGridTable').getSelectedData();
			var _gdsIdArr = new Array();
		    for(var i=0;i<_val.length;i++){
		    	_gdsIdArr.push(_val[i].id);
		    }
			var parm=new Object();
			parm._if_query="1";
			parm.gdsIds=_gdsIdArr;
			//bDialog.getDialogCallback(_dlg);
			bDialog.closeCurrent(parm);
			
		}else if(!ids || ids.length==0){
			eDialog.alert('请至少选择一个商品进行操作！');
		}
	});
	
	$('#btnReturn').click(function(){
		bDialog.closeCurrent();
	});
	
	advertise_gds.init();
});

var advertise_gds = {
	
	init : function(){//初始化
		var siteId = $("#siteId").val();
		var shopId = $("#shopId").val();
		//初始化列表
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheck' : "multi",
	        'pAutoload' : true,
	        'pCheckColumn' : true, //是否显示单选/复选框的列
	        "sAjaxSource": $webroot + 'common/querygds?siteId='+siteId+'&shopId='+shopId,
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"商品编码","bVisible":false,"sWidth":"40px","bSortable":false},
				{ "mData": "gdsName", "sTitle":"商品名称","sWidth":"240px","bSortable":false},
				{ "mData": "imageUrl", "sTitle":"图片","bSortable":false,"mRender": function(data,type,row){
					return "<img src='"+data+"' width='120' height='50'>";
				}},
				
				{ "mData": "gdsTypeName", "sTitle":"商品类型","sWidth":"120px","bSortable":false},
				//{ "mData": "gdsStatusName", "sTitle":"商品状态","sWidth":"80px","bSortable":false},
				{ "mData": "updateTime", "sTitle":"更新日期","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd");
				}},
				{ "mData": "shopName", "sTitle":"所属店铺","sWidth":"120px","bSortable":false}
	        ],
	        "eDbClick" : function(){
	        }
		});
		//绑定查询按钮
		$('#btnFormSearch').click(function(){
			if(!$("#searchForm").valid()) return false;
			var p = ebcForm.formParams($("#searchForm"));
			$('#dataGridTable').gridSearch(p);
		});
		//绑定重置按钮
		$('#btnFormReset').click(function(){
			$("#catgCode").attr('catgCode',"");
			ebcForm.resetForm('#searchForm');
		});
	},

};

