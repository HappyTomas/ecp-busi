$(function(){
	
	$("#shipdataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false, //是否显示单选/复选框的列
        "sAjaxSource": GLOBAL.WEBROOT + '/shop/shiplist',
        "params":[{name:"shopid",value:$('#shopid').val()}],
        //指定列数据位置
        "aoColumns": [
			{ "mData": "id", "sTitle":"运费模板编码","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "shipTemplateName", "sTitle":"运费模板名称","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "shipTemplateType", "sTitle":"计价方式","sWidth":"80px","sClass": "center","bSortable":false,
				"mRender": function(data,type,row){
					if(data=='1'){
						return "按件";
					}else if(data=='2'){
						return "按重量";
					}else if(data=='3'){
						return "按体积";
					}else if(data=='4'){
						return "按金额";
					}else{
						return "为定义";
					}
				}
			},
			{ "mData": "ifFree", "sTitle":"是否免邮","sWidth":"80px","sClass": "center","bSortable":false,
				"mRender": function(data,type,row){
					if(data == 1){
						return "是";
					}else{
						return "否";
					}
				}
			},
			{ "mData": "ifDefaultTemplate", "sTitle":"是否默认运费模板","sWidth":"90px","sClass": "center","bSortable":false,
				"mRender": function(data,type,row){
					if(data == '1'){
						return "是";
					}else{
						return "否";
					}
				}
			},
			{ "mData": "shopName", "sTitle":"店铺","sWidth":"80px","sClass": "center","bSortable":false,"bVisible":false},
			{ "mData": "shopId","sTitle":"操作","sWidth":"80px","sClass": "center",
				"mRender": function(data,type,row){
					return "<a href='#' onclick=\"setShopShip("+row.id+",'"+row.shopId+"')\">选定</a>";
				}
			}
        ],
	});

});

function setShopShip(shipId,shopId){
	shopship.setDefaultShip(shipId,shopId);
}
var shopship = {
		
		setDefaultShip:function(shipId,shopid){
		eDialog.confirm("您确认设置其为默认运费模版吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					$.eAjax({
						url : GLOBAL.WEBROOT + "/shop/setDefaultShip",
						data : {'shipid':shipId,'shopid':shopid},
						datatype:'json',
						success : function(returnInfo) {
							if(returnInfo.resultFlag=='ok'){
								eDialog.alert('设置成功！',function(){
									bDialog.closeCurrent();
								},'confirmation');
							}else{
								eDialog.alert(returnInfo.resultMsg);
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
}