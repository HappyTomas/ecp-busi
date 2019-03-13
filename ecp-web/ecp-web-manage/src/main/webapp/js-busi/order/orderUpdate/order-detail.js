function updatebill(){
	var orderId = $('#orderId').val();
	bDialog.open({
		title : '修改发票信息',
		width : 680,
		height :500,
		url : GLOBAL.WEBROOT + '/order/customer/billinfo/'+orderId,
		callback:function(data){			
            location.reload();
		}
	});
}

function updateSellerMsg(){
	var orderId = $('#orderId').val();
    bDialog.open({
        title : "修改卖家备注",
        width : 580,
        height : 280,
        url : GLOBAL.WEBROOT+'/order/customer/sellerMsginfo/'+orderId,
        callback:function(returnInfo){
            location.reload();
        }
    });
}

function updateInfo(){
	var orderId = $('#orderId').val();
    bDialog.open({
        title : "修改收货人信息",
        width : 880,
        height : 400,
        url : GLOBAL.WEBROOT+'/order/customer/buyerinfo/'+orderId,
        callback:function(returnInfo){
            location.reload();
        }
    });
}

function updateMsg(){
	var orderId = $('#orderId').val();
    bDialog.open({
        title : "修改买家备注",
        width : 580,
        height : 280,
        url : GLOBAL.WEBROOT+'/order/customer/msginfo/'+orderId,
        callback:function(returnInfo){
            location.reload();
        }
    });
}