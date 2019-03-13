var delivery_list = {
	publish:function(id){
		if(isNotEmpty(id)){
			window.location.href = GLOBAL.WEBROOT + '/order/send?orderId='+id;
		}
	},
	print:function(id){
		if(isNotEmpty(id)){
			window.open(GLOBAL.WEBROOT + '/order/printDetail?orderId='+id);
		}
	},
	printList:function(ids){
		if(isNotEmpty(ids)){
			window.open(GLOBAL.WEBROOT + '/order/printDetailList?orderIds='+ids);
		}
	}
};
$(function(){
	
	var search_url = GLOBAL.WEBROOT + '/order/searchlist';
	var delyed_url = GLOBAL.WEBROOT + '/order/delyedlist';
	var editUrl = GLOBAL.WEBROOT+'/order/edit';
	
	gridInitDT({url:search_url,id:"searchGridTable",editUrl:editUrl,checkColumn:true});
	gridInitDT({url:delyed_url,id:"delyedGridTable",editUrl:editUrl,checkColumn:true});
	function gridInitDT(json){
		var url = json.url,id=json.id;
		var pCheckColumn = json.checkColumn;
		var aoColumns = [
		 				{ "mData": "orderId", "sTitle":"订单编号", "sClass":"center","bSortable":false, "mRender": function(data,type,row){
							return '<a href="javascript:void(0)" target="_blank" onclick="oUtil.getOrdDetail(\''+data+'\')">'+data+'</a>';
						}},
						{ "mData": "orderDate", "sTitle":"下单日期", "sClass":"center" ,"bSortable":false,"mRender": function(data,type,row){
							return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
						}},
						{ "mData": "payTime", "sTitle":"支付时间", "sClass":"center" ,"bSortable":false,"mRender": function(data,type,row){
							return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
						}},
						{ "mData": "realMoney", "sTitle":"实付金额",  "sClass":"center","bSortable":false, "mRender": function(data,type,row){
							return ebcUtils.numFormat(data/100, 2);
						}},
						{ "mData": "contactName", "sTitle":"联系人",  "sClass":"center","bSortable":false},
						{ "mData": "dispatchType", "sTitle":"配送方式",  "sClass":"center","bSortable":false,"mRender": function(data,type,row){
							if(data=='0'){
								return "邮局挂号";
							}else if(data=='1'){
								return "快递";
							}else if(data=='2'){
								return "自提";
							}else {
								return "未定义";
							}
						}},
						{ "mData": "orderId", "sTitle":"操作",  "sClass":"center","bSortable":false,"mRender": function(data,type,row){
							//return '<a href="javascript:void(0)" onclick="delivery_list.publish(\''+data+'\',\''+row.staffId+'\',\''+row.dispatchType+'\',\''+row.chnlAddress+'\',\''+row.contactName+'\',\''+row.contactPhone+'\',\''+row.orderAmounts+'\')">发货</a>';
							if(id=='searchGridTable'){
								var str = '<a class="_send" href="javascript:void(0)" onclick="delivery_list.publish(\''+data+'\')">发货</a>';
								str =  str + '&nbsp;&nbsp;&nbsp;&nbsp;<a class="_send" href="javascript:void(0)" onclick="delivery_list.print(\''+data+'\')">打印</a>';
								return str;
							}else{
								var str = '&nbsp;&nbsp;<a class="_send" href="javascript:void(0)" onclick="delivery_list.print(\''+data+'\')">打印</a>';
								return str;
							}
							
						}}
		];
		
		$("#"+id).initDT({
			'pCheck' : 'multi',
	        'pTableTools' : false,
	        'pCheckRow' : false,
	        'pCheckColumn' : pCheckColumn,
	        'pSingleCheckClean' : false,
	        'params':[{name:"begDate",value:$("input[name='begDate']").val()},{name:"endDate",value:$("input[name='endDate']").val()},{name:"shopId",value:$("#offline_grid_shopId").val()}],
	        "sAjaxSource": url,
	        //指定列数据位置
	        "aoColumns": aoColumns,
	        "eDbClick" : function(){
	        	//modifyBiz(json);
	        }
		});

	} 
	
	 
	$('#myTab a').click(function (e) {
		e.preventDefault();
		$(this).tab('show');
		var id = getTabId();
		if(id=='#delyed'){
			$("#btn_code_publish").hide();
		}else{
			$("#btn_code_publish").show();
			
		}
		if(!$("#searchForm").valid()) return false;

		/*这个方法是ajax的，后台跳转的，自然和它无关了，难道这就叫ajax跨域吗*/
		/*带了tab之后就必须要获取当前是那个tab的数据*/
		var p = ebcForm.formParams($("#searchForm"));
		var type = getTabId();
		$(type+'GridTable').gridSearch(p);
    });
	
    /**
	 * 发货清单打印
	 */
    $('#btnPrint').click(function(){
    	var val = $('#searchGridTable').getSelectedData();    	
    	if(val==null||val==''){
    		val = $('#delyedGridTable').getSelectedData();
    	}
    	if(val==null||val==''){
    		eDialog.alert('请选择至少选择一个订单进行操作！');
    		return;
    	}
    	var orderIds = '';
    	for(var i=0;i<val.length;i++){
    		if(i==val.length-1){
    			orderIds += val[i].orderId
    		}else{
    			orderIds += val[i].orderId+",";
    		}
    	}
    	delivery_list.printList(orderIds);	
	});
	
	$('#btnFormBaseSearch').click(function(){
		//console.info(ebcForm.formParams($("#searchForm")));
		if(!$("#searchForm").valid()) return false;
		
		/*这个方法是ajax的，后台跳转的，自然和它无关了，难道这就叫ajax跨域吗*/
		/*带了tab之后就必须要获取当前是那个tab的数据*/
		var p = ebcForm.formParams($("#searchForm"));
		var type = getTabId();
		$(type+'GridTable').gridSearch(p);
		
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
	/**
	 * 获取当前tab页面的id
	 */
	function getTabId(){
		var type = $("#myTab>li.active").find("a").attr("href");
		var _id= type.replace('Tab','');
		return _id;
	}
	
	$("#btn_code_detail").click(function(){
		var val = $(getTabId()+'GridTable').getSelectedData();
		if(val&&val.length==1){
			window.location.href = GLOBAL.WEBROOT + "/order/orderdetails?orderId="+val[0].orderId;
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个订单进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个订单进行操作！');
		}
	});
	
	/**
	 * 商品分类查询
	 */
	$('input[name="categoryCode"]').on('focus',function(){
		bDialog.open({
	        title : '分类选择',
	        width : 350,
	        height : 550,
	        url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=1",
	        callback:function(data){
	            if(data && data.results && data.results.length > 0 ){
	                var _catgs = data.results[0].catgs;
	                for(var i in _catgs){
	                	$('input[name="category"]').val(_catgs[i].catgName);
	                	$('input[name="categoryCode"]').val(_catgs[i].catgCode);
	                }
	            }
	        }
	    });
	});

	//店铺选中刷新
	//$('#offline_grid_shopId').on('change',function(){
	//	$('#btnFormBaseSearch').trigger('click');
	//});
});