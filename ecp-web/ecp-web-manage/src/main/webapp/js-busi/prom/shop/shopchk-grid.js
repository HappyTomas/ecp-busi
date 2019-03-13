var shopchkgrid = {
		
	modifyBiz:function(id){
		eNav.setSubPageText('编辑店铺');
		var val = $('#dataGridTable').getSelectedData();
		var sid = 'id';
		if(id!=undefined&&id!=null&&id!='') sid = id;
		if(val && val.length==1){
			return val[0][sid];
		}
		return '';
	},
	verify:function(id){
		eNav.setSubPageText('审核店铺');
		if(id){
			window.location.href = GLOBAL.WEBROOT +"/shopchk/edit/"+id;
		}
	},
	detail:function(id){
		eNav.setSubPageText('店铺促销详情');
		if(id){
			window.location.href = GLOBAL.WEBROOT + "/shopchk/detail/"+id;
		}
		
	}
};
$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pCheckColumn' : false, 
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/shopchk/gridlist',
        'params' : ebcForm.formParams($("#searchForm")),
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"id","autoWidth":true,"bSortable":false,"bVisible":false},
  			{ "mData": "shopId", "sTitle":"店铺ID","autoWidth":true,"bSortable":true,"bVisible":false},
  			{ "mData": "siteName", "sTitle":"站点","sWidth":"100px","bSortable":false},
  			{ "mData": "shopName", "sTitle":"店铺名称","sWidth":"120px","bSortable":false,"bVisible":false},
  			{ "mData": "status", "sTitle":"审核状态","autoWidth":true,"bSortable":false,"bVisible":false},
  			{ "mData": "statusName", "sTitle":"审核状态","sWidth":"90px","bSortable":false},
  			{ "mData": "promTypeName", "sTitle":"促销类型","sWidth":"120px","bSortable":false},
  			{ "mData": "promTheme", "sTitle":"促销主题","sWidth":"120px","bSortable":false},
  			{ "mData": "status", "sTitle":"状态","bSortable":false,"bVisible":false},
  			{ "mData": "priority", "sTitle":"优先级","sWidth":"80px","bSortable":false},
  			{ "mData": "startTime", "sTitle":"促销开始时间","bSortable":false,"mRender": function(data,type,row){
  				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
  			}},
  			{ "mData": "endTime", "sTitle":"促销截止时间","bSortable":false,"mRender": function(data,type,row){
  				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
  			}},
  		/*	{ "mData": "showStartTime", "sTitle":"开始展示时间","bSortable":false,"mRender": function(data,type,row){
  				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
  			}},
  			{ "mData": "showEndTime", "sTitle":"截止展示时间","bSortable":false,"mRender": function(data,type,row){
  				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
  			}},*/
  			{ "mData": "info", "sTitle":"操作","sWidth":"80px","bSortable":false,"sClass": "center","mRender":function(data,type,row){
  				if(row.status=='40'){
  					return "<a href='javascript:void(0)' onclick='shopchkgrid.verify("+row.id+")'>&nbsp;审核&nbsp;</a>"
  				}
  				
  				return "<a href='javascript:void(0)' onclick='shopchkgrid.detail("+row.id+")'>&nbsp;详情&nbsp;</a>"
  			}}/**/
          ],
        "eDbClick" : function(){
        	modifyBiz();
        }
	});
	
	var modifyBiz = function(){
		var ids = $('#dataGridTable').getCheckIds();
		shopchkgrid.detail(ids);
	};
	
	$('#btnFormSearch').click(function(){
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	
	$('#btn_code_add').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/shopchk/add';
	});
	
	$('#btn_code_modify').click(function(){
		modifyBiz();
	});
	
});