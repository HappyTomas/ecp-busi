$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : true, 
        "sAjaxSource": GLOBAL.WEBROOT + '/coupontype/grid',
        'params' : ebcForm.formParams($("#searchForm")),
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"id","sWidth":"80px","bSortable":false,bVisible:false},
			{ "mData": "coupTypeName", "sTitle":"类型名称","sWidth":"160px","bSortable":false},
			{ "mData": "typeLimit", "sTitle":"类别","sWidth":"80px","bSortable":false,bVisible:false},
			{ "mData": "typeLimitName", "sTitle":"类别","sWidth":"80px","bSortable":false,"mRender":function(data,type,row){ 
				  if(row.typeLimit==0){
					  return "平台券";
				  }
				  if(row.typeLimit==1){
					  return "店铺券";
				  }
			}},
			{ "mData": "useRuleCodeDesc", "sTitle":"使用规则","sWidth":"100px","bSortable":false},
			{ "mData": "getRuleCodeDesc", "sTitle":"领取规则","sWidth":"100px","bSortable":false}
        ],
        "eDbClick" : function(){
        	modifyBiz();
        },
        "eClick" : function(data){
         
        }
	});
	
	var modifyBiz = function(){
		var _ids = $('#dataGridTable').getCheckIds();
		if(_ids && _ids.length==1){
			eNav.setSubPageText('创建优惠券');
			var _val = $('#dataGridTable').getSelectedData();
			window.location.href = GLOBAL.WEBROOT+'/coup/add/'+_val[0].id;
			
		}else if(_ids && _ids.length>1){
			eDialog.alert('只能选择一个优惠券类型进行操作！');
			$('#btn_code_modify').removeAttr("disabled");
		}else if(!_ids || _ids.length==0){
			 eDialog.alert('请选择至少选择一个优惠券类型进行操作！');
			 $('#btn_code_modify').removeAttr("disabled");
		}
	};
	
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var _p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(_p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
	$('#btn_code_publish').click(function(){
		  modifyBiz();
	});
});
