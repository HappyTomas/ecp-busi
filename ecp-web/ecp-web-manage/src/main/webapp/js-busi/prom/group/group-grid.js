
var groudgrid = {
		promtab:function(_data){
			window.location.href = GLOBAL.WEBROOT+ "/promgroup/tab?id="+ _data;
		}	
};

$(function(){
	
	$("#btn_code_valid").hide();
	
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/promgroup/querypromtheme',
        'params' : [{name:'status',value:$('#status').val()},{name:'siteId',value:$('#siteId').val()}],
        //指定列数据位置
        "aoColumns": [
            { "mData": "siteName", "sTitle":"站点","sWidth":"80px","bSortable":false},
            //{ "mData": "id", "sTitle":"促销编码","sWidth":"80px","bSortable":false},
			{ "mData": "promTheme", "sTitle":"主题名称","sWidth":"180px","bSortable":false},
			{ "mData": "status", "sTitle":"状态","sWidth":"60px","bSortable":false,bVisible:false},
			{ "mData": "statusName", "sTitle":"状态","sWidth":"70px","bSortable":false},
			{ "mData": "showStartTime", "sTitle":"开始展示时间","sWidth":"120px","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "showEndTime", "sTitle":"结束展示时间","bSortable":false,"sWidth":"120px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "promCnt", "sTitle":"参与促销数量","sWidth":"100px","bSortable":false,"mRender":function(data,type,row){
				if(data != '0'){
					return "<a href='javascript:void(0)' onclick='groudgrid.promtab("+row.id+")'>"+data+"</a>";
				}
				return data;
			}}
        ],
        "eDbClick" : function(){//双击事件
        	modifyBiz();
        },
		"eClick" : function(data){
	    	if(data.status=='1'){
	    		$("#btn_code_valid").show();
	    	}else{
	    		$("#btn_code_valid").hide();
	    	}
    }
	});
	
	$("#btn_code_valid").on("click",function(){
		var val = $('#dataGridTable').getSelectedData();
		//console.info(val);
		if(val && val.length==1){
			if(val[0].status == '1'){
				eDialog.confirm("您确认要失效该主题吗？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							//eDialog.alert('success','发布成功！');
							
							$.eAjax({
								url : GLOBAL.WEBROOT + "/promgroup/valid",
								data : {'id':val[0].id},
								datatype:'json',
								success : function(returnInfo) {
									if(returnInfo.resultFlag=='ok'){
										eDialog.success('已失效！',{
											buttons:[{
												caption:"确定",
												callback:function(){
													$('#dataGridTable').gridReload();
										        }
											}]
										}); 
									}else{
										eDialog.alert(returnInfo.resultMsg,null);
									}
								}
							});
							
						}
					},{
						caption : '取消',
						callback : $.noop
					}]
				});
		}else
			{eDialog.alert('该促销已经是失效状态！');}
		}else if(val && val.length>1){
			eDialog.alert('只能选择一条信息进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一条信息进行操作！');
		}
	});
	var modifyBiz = function(){
		var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length==1){
			$('#detail-id').val(ids);
//			window.location.href = GLOBAL.WEBROOT+ "/promgroup/detail?eId="+ ids[0];
			$('#a').submit();
		}else if(ids && ids.length>1){
			eDialog.alert('只能选择一条信息进行操作！');
		}else if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一条信息进行操作！');
		}
	};
	$('#btn_code_detail').click(function(){
		var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length==1){
			eNav.setSubPageText('主题促销详情');
		  window.location.href = GLOBAL.WEBROOT + "/promgroup/detail?id="+ ids[0];
		}else if(ids && ids.length>1){
			eDialog.alert('只能选择一个条信息操作！');
		}else if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一条信息操作！');
		}
	});
	$('#btnFormSearch').click(function(){
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		$("#status").val('1');//状态默认为有效
	});
	
	$('#btn_code_add').click(function(){
		eNav.setSubPageText('新增主题促销');
		window.location.href = GLOBAL.WEBROOT+'/promgroup/add';
	});
	
	$('#btn_code_modify').click(function(){
		eNav.setSubPageText('编辑主题促销');
		modifyBiz();
	});
	
});