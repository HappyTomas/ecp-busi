$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/msgauth/gridlist',
        'params' : [{name:'shopAuthId',value:$('#shopAuthId').val()}],
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"编码","sWidth":"80px","bSortable":false},
			{ "mData": "shopId", "sTitle":"店铺编码","sWidth":"120px","bSortable":false},
			{ "mData": "shopName", "sTitle":"店铺名称","sWidth":"160px","bSortable":false},
			{ "mData": "platTypeName", "sTitle":"对接平台","sWidth":"160px","bSortable":false},
			{ "mData": "topic", "sTitle":"topic消息","sWidth":"120px","bSortable":false},
			{ "mData": "nick", "sTitle":"昵称","sWidth":"120px","bSortable":false},
			{ "mData": "status", "sTitle":"状态","sWidth":"80px","bSortable":false, "mRender":function(data,type,row){
				if('1' === row.status){
					return "已开通";
				}else{
					return "未开通";
				}
			}},
			{ "mData": "info", "sTitle":"操作","sWidth":"160px","bSortable":false, "mRender":function(data,type,row){
				
                var _html="<a href='javascript:void(0)' onclick='msgauthGrid.edit("+row.id+")'>&nbsp;编辑 &nbsp;</a>";
  				return _html;
  			}} 
        ],
        "eDbClick" : function(){
        	modifyBiz();
        },
        "eClick" : function(data){
        	if(data.status=='1'){
        		$("#btn_code_del").show();
        	}else{
        		$("#btn_code_del").hide();
        	}
        }
	});
	
	$('#btn_msg_close').click(function(){
		eNav.setSubPageText('关闭消息授权');
		var id = $("#shopAuthId").val();
		$.eAjax({
			url : GLOBAL.WEBROOT + '/msgauth/close/'+id,
			success : function(returnInfo) {
				//成功返回grid
				if(returnInfo.resultFlag=='ok'){
					window.location.href = GLOBAL.WEBROOT+'/msgauth/'+$('#shopAuthId').val();
				//数据验证错误	
				}else if(returnInfo.resultFlag=='fail'){
					eDialog.alert(returnInfo.resultMsg,null);
				}
			},
		});
		//window.location.href = GLOBAL.WEBROOT+'/msgauth/close/'+id;
	});
	
	$('#btn_msg_add').click(function(){ 
		var id = $("#shopAuthId").val();
		window.location.href = GLOBAL.WEBROOT+'/msgauth/add/'+id;
	});
	
	$('#btnReturn').click(function(){ 
		window.location.href = GLOBAL.WEBROOT+'/platauth';
	});
	var modifyBiz = function(){
		var _ids = $('#dataGridTable').getCheckIds();
		msgauthGrid.edit(_ids[0])
	};
	
});


var msgauthGrid = {
		//编辑
		edit:function(id){
			window.location.href = GLOBAL.WEBROOT+'/msgauth/edit/'+id;
		},
};