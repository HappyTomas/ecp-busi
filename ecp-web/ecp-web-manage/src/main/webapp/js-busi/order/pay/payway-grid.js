$(function(){
	//var ids = $('#dataGridTable').getCheckIds();
	 $("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheckColumn' : false,
	        'pCheckRow' : false,
	        'pIdColumn' : 'id',
	        'pCheck' : 'single', //multi  single
	        'pCheckColumn' : true, //是否显示单选/复选框的列
	        'scrollX' :true,
	        'pLengthMenu' : [25,10,50,100],
	        "sAjaxSource": GLOBAL.WEBROOT + '/order/pay/paywayList',
	      //  'params':ebcForm.formParams($("#searchForm")),
	        //指定列数据位置
	        "aoColumns": [	          
	            { "mData": "id", "sTitle":"支付通道编号","bSortable":false, "sClass": "center"},
	            { "mData": "payWayName", "sTitle":"支付通道名称","bSortable":false, "sClass": "center"},
	            { "mData": "payWayType", "sTitle":"支付通道类型","bSortable":false, "sClass": "center","mRender": function(data,type,row){
	            	if(row.payWayType=='01'){
	            		return '支付平台';
	            	}else if(row.payWayType=='02'){
	            		return '银行卡支付';
	            	}else if(row.payWayType=='03'){
	            		return '信用卡支付';
	            	}else if(row.payWayType=='04'){
	            		return '供应链贷款支付（沃金融）';
	            	}else if(row.payWayType=='05'){
	            		return '线下支付';
	            	}else{
	            		return row.payWayType;
	            	}
	            }},
	            { "mData": "payAcctType", "sTitle":"支持账户类型","bSortable":false, "sClass": "center","mRender": function(data,type,row){
	            	if(row.payWayType=='01'){
	            		return '个人账户';
	            	}else if(row.payWayType=='02'){
	            		return '对公账户';
	            	}else{
	            		return row.payWayType;
	            	}
	            }},
	            //{ "mData": "payImage", "sTitle":"支付图标","bSortable":false, "sClass": "center"},
	           // { "mData": "payLogo", "sTitle":"水印图标","bSortable":false, "sClass": "center"},
	            { "mData": "charSet", "sTitle":"编码格式","bSortable":false, "sClass": "center"},	            
	            { "mData": "useFlag", "sTitle":"是否启用","bSortable":false, "sClass": "center","mRender": function(data,type,row){
	            	if(row.useFlag=='1'){
	            		return '是';
	            	}else if(row.useFlag=='2'){
	            		return '否';
	            	}else{
	            		return row.payWayType;
	            	}
	            }},
	            { "mData": "showOrder", "sTitle":"展示顺序","bSortable":false, "sClass": "center"}
	        ]
	    });
	 
	 $('#btn_payway_add').click(function(){
			window.location.href = GLOBAL.WEBROOT+ '/order/pay/addPayWay';
	 });
	 
	 $('#btn_payway_edit').click(function(){
		 var id = $('#dataGridTable').getCheckIds();
		 if(id!=''){
			 window.location.href = GLOBAL.WEBROOT+ '/order/pay/editPayWay?id='+id;			
		 }else{
			 eDialog.error('请选择您要编辑的记录！');
		 }
			
	 });
});