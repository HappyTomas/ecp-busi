var oUtil = {
	constant:{
		'01':'待支付',
		'02':'已支付',
		'03':'待录入物流单',
		'04':'部分发货',
		'05':'已发货',
		'06':'已收货',
		'07':'已退货',
		'08':'已退款',
		'80':'关闭',
		'99':'已取消',
		pay:{
			'0':'线上支付',
			'1':'上门支付',
			'2':'邮局汇款',
			'3':'银行转账'
		},
		payWay:{
			'9002':'鸿支付',
			'9003':'支付宝',
			'9004':'农行支付',
			'9006':'微信支付',
			'9007':'微信扫码支付',
			'9008':'微信App支付',
		},
		backGdsType:{
			'01':'收到商品破损',
			'02':'商品错发/漏发',
			'03':'收到商品与描述不符',
			'04':'商品质量问题',
			'05':'其他',
		},
		backStatus:{
			'00':'待审核',
			'01':'同意退货',
			'02':'买家已发货',
			'03':'确认收货',
			'04':'已退款',
			'05':'拒绝退货',
		},
		refundStatus:{
			'00':'待审核',
			'01':'同意退款',
			'04':'已退款',
			'05':'拒绝退款',
		}
	},


		
	disable:function(ids){
		if(typeof ids ==='string'){
			$("#"+ids).attr("disabled",true);
		}
		if(typeof ids ==='object'){
			for(var i=0;i<ids.length;i++){
				$("#"+ids).attr("disabled",true);
			}
		}
	},
	enable:function(ids){
		if(typeof ids ==='string'){
			$("#"+ids).attr("disabled",false);
		}
		if(typeof ids ==='object'){
			for(var i=0;i<ids.length;i++){
				$("#"+ids).attr("disabled",false);
			}
		}
	},
	getSelectId:function(){
		var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length==1){
			return ids;
		}else if(ids && ids.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
		return '';
	},
	getOrdDetail:function(id){
		var url = GLOBAL.WEBROOT + "/order/orderdetails?orderId="+id;
		window.open(url);
	},
    getOrdUpdateDetail:function(id){
        var url = GLOBAL.WEBROOT + "/order/customer/details?orderId="+id;
        window.open(url);
    }
	
};