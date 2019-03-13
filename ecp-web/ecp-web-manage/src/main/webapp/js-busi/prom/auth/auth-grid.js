$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/promauth/gridlist',
        'params' : ebcForm.formParams($("#searchForm")),
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"id","sWidth":"80px","bSortable":false,bVisible:false},
			{ "mData": "shopId", "sTitle":"店铺编码","sWidth":"120px","bSortable":false},
			{ "mData": "shopName", "sTitle":"店铺名称","sWidth":"160px","bSortable":false},
			{ "mData": "promTypeCode", "sTitle":"类型编码","sWidth":"120px","bSortable":false},
			{ "mData": "promTypeName", "sTitle":"类型名称","sWidth":"160px","bSortable":false},
			{ "mData": "status", "sTitle":"状态","sWidth":"80px","bSortable":false,bVisible:false},
			{ "mData": "statusName", "sTitle":"授权状态","sWidth":"100px","bSortable":false},
			{ "mData": "startTime", "sTitle":"使用开始时间","bSortable":false,"sWidth":"100px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd");
			}},
			{ "mData": "endTime", "sTitle":"使用截止时间","bSortable":false,"sWidth":"100px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd");
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
	
	var modifyBiz = function(){
		var _ids = $('#dataGridTable').getCheckIds();
		if(_ids && _ids.length==1){
			//已经失效 不可编辑
			var _val = $('#dataGridTable').getSelectedData();
			if(_val[0].status=='0'){
				eDialog.alert("已经失效，不能编辑！");
				return ;
			}
			window.location.href = GLOBAL.WEBROOT+'/promauth/edit?id='+_val[0].id;
		}else if(_ids && _ids.length>1){
			eDialog.alert('只能选择一个店铺进行操作！');
		}else if(!_ids || _ids.length==0){
			 eDialog.alert('请选择至少选择一个店铺进行操作！');
		}
	};
	
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
		window.location.href = GLOBAL.WEBROOT+'/promauth/add';
	});
	
	$('#btn_code_adds').click(function(){
		eNav.setSubPageText('批量授权');
		window.location.href = GLOBAL.WEBROOT+'/promauth/adds';
	});
	
	
	$('#btn_code_modify').click(function(){
		eNav.setSubPageText('编辑授权');
		modifyBiz();
	});
	
	$('#btn_code_del').click(function(){
		var _val = $('#dataGridTable').getSelectedData();
		if(_val && _val.length==1){
			//已经失效 不可再次失效
			if(_val[0].status=='0'){
				eDialog.alert("已经失效，请不要重复操作！");
				return ;
			}
			
			eDialog.confirm("您确认失效该店铺的促销类型吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : $webroot + 'promauth/invalid?id=' + _val[0].id,
							success : function(returnInfo) {
								if(returnInfo.resultFlag=='ok'){
									window.location.reload();
								}else{
									eDialog.alert(returnInfo.resultMsg);
								}
							}
						});
						
					}
				},{
					caption : '取消',
					callback : function(){
						$.noop;
					}
				}]
			});
		}else if(_val && _val.length>1){
			eDialog.alert('只能选择一个店铺进行操作！');
		}else if(!_val || _val.length==0){
			eDialog.alert('请选择至少选择一个店铺进行操作！');
		}
	});
});