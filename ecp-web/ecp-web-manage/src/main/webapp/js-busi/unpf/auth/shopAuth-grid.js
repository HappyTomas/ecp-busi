$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/platauth/gridlist',
        'params' : ebcForm.formParams($("#searchForm")),
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"编码","sWidth":"80px","bSortable":false},
			{ "mData": "shopId", "sTitle":"店铺编码","sWidth":"60px","bSortable":false,bVisible:true},
			{ "mData": "shopName", "sTitle":"店铺名称","sWidth":"60px","bSortable":false},
			{ "mData": "status", "sTitle":"状态","sWidth":"80px","bSortable":false, "mRender":function(data,type,row){
				switch(row.status){
				case '1':
					return "有效";
					break;
				case '0':
					return "无效";
					break;
				}
				}},
			{ "mData": "appkey", "sTitle":"APP_KEY","sWidth":"120px","bSortable":false},
			{ "mData": "platTypeName", "sTitle":"对接平台","sWidth":"40px"},
			{ "mData": "info", "sTitle":"操作","sWidth":"160px","bSortable":false, "mRender":function(data,type,row){
				
                var _html="<a href='javascript:void(0)' onclick='platauthGrid.detail("+row.id+")'>&nbsp;查看 &nbsp;</a>";
                if(row.status=='1'){
                	_html=_html+"|<a href='javascript:void(0)' onclick='platauthGrid.edit("+row.id+")'>&nbsp;编辑 &nbsp;</a>";
                	_html=_html+"|<a href='javascript:void(0)' onclick='platauthGrid.del("+row.id+")'>&nbsp;删除 &nbsp;</a>";
                	_html=_html+"|<a href='javascript:void(0)' onclick='platauthGrid.loginAuth("+row.id+")'>&nbsp;登录授权 &nbsp;</a>";
                	_html=_html+"|<a href='javascript:void(0)' onclick='platauthGrid.sync("+row.id+")'>&nbsp;同步信息设置&nbsp;</a>";
                	_html=_html+"|<a href='javascript:void(0)' onclick='platauthGrid.catgSave("+row.id+")'>&nbsp;第三方平台分类 &nbsp;</a>";
                }
  				return _html;
  			}} 
        ],
        "eDbClick" : function(){
        	//modifyBiz();
        },
        "eClick" : function(data){
        	if(data.status=='1'){
        		$("#btn_code_del").show();
        	}else{
        		$("#btn_code_del").hide();
        	}
        }
	});
	
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var _p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(_p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		$("#status").val('1');//特别处理 置为有效
	});
	
	$('#btn_code_add').click(function(){
		eNav.setSubPageText('新增授权');
		window.location.href = GLOBAL.WEBROOT+'/platauth/add';
	});
	
	$('#btn_msg_add').click(function(){
		eNav.setSubPageText('新增消息授权');
		var _val = $('#dataGridTable').getSelectedData();
		if(!_val || _val.length==0){
			eDialog.alert('请至少选择一个授权进行操作！');
			return null;
		}
		if(_val[0].platType != "taobao"){
			eDialog.alert('该平台无需消息授权！');
			return null;
		}

		window.location.href = GLOBAL.WEBROOT+'/msgauth/'+_val[0].id;
	});
	
});
var platauthGrid = {
		//详情
		loginAuth:function(id){
			$.eAjax({
				url : GLOBAL.WEBROOT + "/platauth/loginAuth/"+id,
				datatype:'json',
				success : function(returnInfo) {
					window.open(returnInfo.url,"newwindow");
				}
			});
		},
		//详情
		detail:function(id){
			window.location.href = GLOBAL.WEBROOT+'/platauth/view/'+id;
		},
		//编辑
		edit:function(id){
			window.location.href = GLOBAL.WEBROOT+'/platauth/edit/'+id;//sync
		},
		//第三方平台分类
		catgSave:function(id){
			$.gridLoading({"message":"正在处理中...."});
			eDialog.confirm("确定拉取第三方平台分类吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/platauth/catgSave/"+id,
							datatype:'json',
							success : function(returnInfo) {
								
								eDialog.success('操作成功！',{
									buttons:[{
										caption:"确定",
										callback:function(){
											$.gridUnLoading();
											$('#dataGridTable').gridReload();
										}
									}]
								}); 
							},
							error : function(e,xhr,opt){
								eDialog.error("遇到异常!");
								$.gridUnLoading();
							},
							exception : function(msg){
								$.gridUnLoading();
							}
						});
					}
				},{
					caption : '取消',
					callback : function(){
						$.gridUnLoading();
					},
					
				}]
			});
			
			//window.location.href = GLOBAL.WEBROOT+'/platauth/catgSave/'+id;//sync
		},
		//同步信息设置
		sync:function(id){
			window.location.href = GLOBAL.WEBROOT+'/platauth/sync/'+id;
		},
		//删除
		del:function(id){
			eDialog.confirm("您确认删除吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/platauth/del/"+id,
							data : {'id':id},
							datatype:'json',
							success : function(returnInfo) {
								eDialog.success('已删除！',{
									buttons:[{
										caption:"确定",
										callback:function(){
											$('#dataGridTable').gridReload();
										}
									}]
								}); 
							}
						});
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		},
};