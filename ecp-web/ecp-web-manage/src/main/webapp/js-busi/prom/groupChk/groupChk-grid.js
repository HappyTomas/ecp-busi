var groudgrid = {
		//审核
		promtab:function(id){
			eNav.setSubPageText('审核主题促销');
			window.location.href = GLOBAL.WEBROOT+ "/shopchk/edit/"+id;
		},
		//审核详情
		detail:function(id){
			eNav.setSubPageText('审核详情');
			window.location.href = GLOBAL.WEBROOT+ "/shopchk/detail/"+id;
		},
		//促销详情
		promview:function(id){
			eNav.setSubPageText('促销详情');
			window.location.href = GLOBAL.WEBROOT+'/createprom/view/'+id;
		}
};

$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false, 
        "sAjaxSource": GLOBAL.WEBROOT + '/promgroupchk/queryChktheme',
        //'params' : [{name:'statusGroup',value:$('#statusGroup').val()},{name:'chkStatus',value:$('#chkStatus').val()}],
        'params' : ebcForm.formParams($("#searchForm")),
        //指定列数据位置
        "aoColumns": [
            { "mData": "siteName", "sTitle":"站点","sWidth":"80px","bSortable":false},
			//{ "mData": "groupId", "sTitle":"主题编码","sWidth":"80px","bSortable":false},
			{ "mData": "groupName", "sTitle":"主题名称","sWidth":"140px","bSortable":false},
			{ "mData": "statusGroupName", "sTitle":"主题状态","sWidth":"80px","bSortable":false},
			//{ "mData": "shopId", "sTitle":"店铺编码","sWidth":"80px","bSortable":false},
			{ "mData": "shopName", "sTitle":"店铺名称","sWidth":"140px","bSortable":false},
			//{ "mData": "id", "sTitle":"促销编码","sWidth":"80px","bSortable":false},
			{ "mData": "promTheme", "sTitle":"促销名称","sWidth":"140px","bSortable":false},
			{ "mData": "statusName", "sTitle":"审核状态","sWidth":"80px","bSortable":false},
			{ "mData": "promTypeName", "sTitle":"促销类型","sWidth":"140px","bSortable":false},
			{ "mData": "startTime", "sTitle":"生效开始时间","bSortable":false,"sWidth":"140px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "endTime", "sTitle":"生效结束时间","bSortable":false,"sWidth":"140px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			/*{ "mData": "showStartTime", "sTitle":"开始展示时间","bSortable":false,"sWidth":"140px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			},bVisible:false},
			{ "mData": "showEndTime", "sTitle":"结束展示时间","bSortable":false,"sWidth":"140px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			},bVisible:false},*/
			{ "mData": "info", "sTitle":"操作","sWidth":"140px","bSortable":false, "mRender":function(data,type,row){
				
				var _html= "</br><a href='javascript:void(0)' onclick='groudgrid.promview("+row.id+")'>促销详情</a>";
  				if(row.status=='40'){
  					return "<a href='javascript:void(0)' onclick='groudgrid.promtab("+row.id+")'>&nbsp;审核&nbsp;</a> "+_html;
  				}
  				return "<a href='javascript:void(0)' onclick='groudgrid.detail("+row.id+")'>&nbsp;审核详情&nbsp;</a> "+_html;
  			}}
        ],
        "eDbClick" : function(){//双击事件
        	modifyBiz();
        }
	});
	
	
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});

/*
	$('#btn_code_detail').click(function(){
		modifyBiz();
	});*/
	
	var modifyBiz = function(){
		var _ids = $('#dataGridTable').getCheckIds();
		if(_ids && _ids.length==1){
			window.location.href = GLOBAL.WEBROOT+'/createprom/view/'+_ids[0];
		}else if(_ids && _ids.length>1){
			eDialog.alert('只能选择一个促销进行操作！');
		}else if(!_ids || _ids.length==0){
			 eDialog.alert('请选择至少选择一个促销进行操作！');
		}
	};
});
