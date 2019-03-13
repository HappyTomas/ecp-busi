$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : true,
        'pCheck' : 'single',
        'pCheckColumn' : false, //是否显示单选/复选框的列
        "sAjaxSource": GLOBAL.WEBROOT + '/gift/gridlist',
        //指定列数据位置
        "aoColumns": [
			{ "mData": "id", "sTitle":"赠品编码","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "giftName", "sTitle":"赠品名称","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "skuId", "sTitle":"关联单品编码","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "gdsName", "sTitle":"关联单品名称","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "giftValue", "sTitle":"赠品价值","sWidth":"80px","sClass": "center","bSortable":false,
				"mRender": function(data,type,row){
					var str = (data/100).toFixed(2) + '';
					var intSum = str.substring(0,str.indexOf("."));//取到整数部分.replace( /\B(?=(?:\d{3})+$)/g, ',' )
					var dot = str.substring(str.length,str.indexOf("."));//取到小数部分
					var ret = intSum + dot;
					return ret;
				}
			},
			{ "mData": "giftAmount", "sTitle":"赠品总量","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "giftSend", "sTitle":"已赠量","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "giftValid", "sTitle":"可赠量","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "shopId","sTitle":"操作","sWidth":"80px","sClass": "center",
				"mRender": function(data,type,row){
					return "<a href='#' onclick=\"giftRemove(this,'"+data+"')\">删除</a> | <a href='#' onclick='giftEdit(this)'>编辑</a>";
				}
			}
        ],
        "params" : [{
			name : 'shopId',
			value : $("#shopId").val()
		}]
	});
	
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
	//添加赠品
	$('#btn_code_add').click(function(){
		
		eNav.setSubPageText('新增赠品');
		window.location.href = GLOBAL.WEBROOT+'/gift/giftadd?shopId='+$("#shopId").val();
	});
});
//删除
function giftRemove(obj,shopId){
	GiftGrid.giftRemove(obj,shopId);
}
//编辑
function giftEdit(obj){
	eNav.setSubPageText('编辑赠品');
	window.location.href = GLOBAL.WEBROOT+'/gift/giftedit?giftId='+$(obj).parent().siblings().eq(0).text();;
}
//查看
function giftShow(){
	eNav.setSubPageText('查看赠品');
	window.location.href = GLOBAL.WEBROOT+'/gift/giftshow';
}
var GiftGrid = {
		gridGdsList : function(){
			var p = ebcForm.formParams($("#searchForm"));
			$('#dataGridTable').gridSearch(p);
		},
		giftRemove : function(obj,shopId){
			var giftId = $(obj).parent().siblings().eq(0).text();
			var param = {
					giftId : giftId,
					shopId : shopId
			};
			eDialog.confirm("您确认删除选中的商品吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/gift/giftremove",
							data : param,
							success : function(returnInfo) {
								if(returnInfo.ecpBaseResponseVO.resultFlag=='ok'){
									eDialog.success('删除成功！'); 
									GiftGrid.gridGdsList();
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
};