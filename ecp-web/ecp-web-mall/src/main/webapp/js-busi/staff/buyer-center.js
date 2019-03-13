$(function(){
	/获取用户的账户相关信息*/
	var buyerAcctInfo = function() {
		$.eAjax({
			url : $webroot + 'buyercenter/acctinfo',
			success : function(info) {
				if (info) {
					$('#myScore').html(info.score);
					$('#myAcct').html((parseInt(info.acct)/100).toFixed(2));
					$('#myCoup').html(info.coup);
					$('#orderPay').html(info.orderPay);
					$('#orderSend').html(info.orderSend);
					$('#orderRecept').html(info.orderRecept);
					$('#orderRecepted').html(info.orderRecepted);
				}
			}
		});
	}
	
	
	/*获取用户订单相关信息并加载页面*/
	var buyerOrderList = function (ordType) {
		//订单列表的内容
		var str = "<tr><td colspan='5' style='text-align:center;border-style:none'><div class='loading-small'></div></td></tr>";
		$('#buyer-orderlist').html(str);
		$.eAjax({
			url : $webroot + 'buyercenter/orderlist?orderStatus=' + ordType,
			success : function(info) {
				if (info) {
					var orderList = JSON.parse(info.orderList);
					if (orderList == null) {
						//渲染订单列表为空的列表
						renderOrdListEmpty();
					} else {
						//渲染订单列表
						renderOrdList(ordType,orderList);
					}
				}
			}
		});
	} 
	/*date  格式化 yyyy-MM-dd HH:mm:ss*/
	var dateFormat = function(time) {
		var dateTime = new Date(time);
		var year = dateTime.getFullYear();//年份yyyy
		var month = dateTime.getMonth() + 1;//月份
		month = month > 9 ?month:'0'+month;
		var day = dateTime.getDate();//日
		day = day > 9 ?day:'0'+day;
		var hour = dateTime.getHours();//时
		hour = hour > 9 ?hour:'0'+hour;
		var minute = dateTime.getMinutes();//分
		minute = minute > 9 ?minute:'0'+minute;
		var second = dateTime.getSeconds();//秒
		second = second > 9 ?second:'0'+second;
		return year+'-'+month+'-'+day+' '+hour+':'+minute+':'+second;
	}
	/*订单状态转状态名称*/
	var orderStatusChange = function(status) {
		 return $("#orderStatus option[value="+status+"]").text();
	}
	/*动态绑定付款事件*/
	$(document).on("click","._pay",function(){
		var ids = this.id.split("##");
		var orderId = ids[0];
		var shopId = ids[1];
		var isStatus = true;
		var url = GLOBAL.WEBROOT + "/pay/check"; 
		$.eAjax({
			url:url,
			data:[{name:"orderIds",value:orderId},{name:"oper",value:"04"}],
			async:false,
			method:'post',
			success:function(returnInfo){
				if(returnInfo&&returnInfo.flag==false){  
					isStatus = false; 
					eDialog.alert(returnInfo.msg,function(){
						window.location.href = GLOBAL.WEBROOT + "/order/pay";
					}); 
				} else {
					orderId = returnInfo.orderIds;
				}
			}
		});   
		if(isStatus == true){
			window.open(GLOBAL.WEBROOT + "/pay/queryWay?orderIds="+orderId);
		} 
	});
	/*动态绑定订单详情事件*/
	$(document).on("click","._detail",function(){
		window.open(GLOBAL.WEBROOT + "/ord/detail/"+this.id);
	});
	/*动态绑定取消订单事件*/
	$(document).on("click","._cancel",function(){
		var orderId = this.id;
		eDialog.confirm("您确定要取消该订单吗？" , {
		    buttons : [{
		        'caption' : '确定',
		        'callback' : function(){
	        		var url = GLOBAL.WEBROOT + "/buyercenter/ordcancel";
	        		$.eAjax({
	        			url:url,
	        			data:[{name:"orderId",value:orderId}],
	        			method:'post',
	        			success:function(result){
	        				if(result && result.resultFlag == 'ok'){
	        					eDialog.alert(result.resultMsg);
	        					//变更上面的订单统计数量
	        					var count = $('#orderPay').html();
	        					if (count != '') {
	        						$('#orderPay').html(parseInt(count) - 1);
	        					}
	        					$("#payOrdCnt").html('<b>' + (parseInt(count) - 1) + '</b>');//刷新我的人卫里面：待付款订单数量
	        					buyerOrderList('pay');
	        				}else{
	        					eDialog.alert(result.resultMsg);
	        				}
	        			}
	        		});
		        }
		    },{
		        'caption' : '取消',
		        'callback' : function(){}
		    }]
		});
	});
	
	/*动态绑定确认收货事件*/
	$(document).on("click","._recept",function(){
		var orderId = this.id;
		eDialog.confirm("您确定要对该笔交易进行收货确认吗？" , {
		    buttons : [{
		        'caption' : '确定',
		        'callback' : function(){
	        		var url = GLOBAL.WEBROOT + "/buyercenter/confirmord";
	        		$.eAjax({
	        			url:url,
	        			data:[{name:"orderId",value:orderId}],
	        			method:'post',
	        			success:function(result){
	        				if(result && result.resultFlag == 'ok'){
	        					eDialog.alert(result.resultMsg);
	        					//变更上面的订单统计数量
	        					debugger;
	        					var receptOrdCnt = $('#orderRecept').html();
	        					if (receptOrdCnt != '') {
	        						$('#orderRecept').html(parseInt(receptOrdCnt) - 1);
	        					}
	        					var receptedOrdCnt = $('#orderRecepted').html();
	        					if (receptedOrdCnt != '') {
	        						$('#orderRecepted').html(parseInt(receptedOrdCnt) + 1);
	        					}
	        					buyerOrderList('recept');
	        				}else{
	        					eDialog.alert(result.resultMsg);
	        				}
	        			}
	        		});
		        }
		    },{
		        'caption' : '取消',
		        'callback' : function(){
		           
		        }
		    }]
		});
	});
	/*动态绑定评价商品事件*/
	$(document).on("click","._comment",function(){
		window.open(GLOBAL.WEBROOT + "/comment/uneval");
	});
	/*动态绑定查看商品详情事件*/
	$(document).on("click",".btn-default",function(){
		window.open(GLOBAL.WEBROOT + this.id);
	});
	
	/*根据返回的订单列表，渲染订单列表信息*/
	var renderOrdList = function(ordType,ordList) {
		var defaultImageUrl = $('#defaultImageUrl').val();
		//如果是已确认收货，则统计一下是否已评价
		var evalFlag = new Array();
		if (ordType == 'recepted') {
			var eval_index = 0;
			for (var k = 0; k < ordList.length; k++) {
				var subOrd = ordList[k].sOrderDetailsSubs;
				for (var m = 0; m < subOrd.length; m++) {
					//如果子订单有未评价的商品，则主订单就加入未评价订单数组
					if (subOrd[m].evalFlag == '0') {
						evalFlag[eval_index] = ordList[k].sCustomerOrdMain.orderId;
						eval_index++;
						break;
					}
				}
			}
		}
		
		//订单列表的内容
		var str = "";
		for (var i = 0; i < ordList.length; i++) {
			var realMoney = parseInt(ordList[i].sCustomerOrdMain.realMoney)/100;//实付金额
			var expressMoney = parseInt(ordList[i].sCustomerOrdMain.realExpressFee)/100;//邮费
			var orderTime = ordList[i].sCustomerOrdMain.orderTime;
			var orderTimeStr = dateFormat(orderTime);
			var ordid_shopid = ordList[i].sCustomerOrdMain.orderId + "##" + ordList[i].sCustomerOrdMain.shopId;
			//子订单列表
			var subOrdList = ordList[i].sOrderDetailsSubs;
			//主订单信息
			str += '<tr class="sep-row">';
	        str += '    <td colspan="5"></td>';
	        str += '</tr>';
			str += '<tr class="tr-th">';
	        str += '    <td colspan="5">';
	        str += '        <span class="number">订单号：';
	        str += '            ' + ordList[i].sCustomerOrdMain.orderId;
	        str += '            </span>';
	        str += '        <span class="dealtime">下单时间：'+orderTimeStr+'</span>';
	        str += '        <span class="online-kf">';
	        str += '              <a href="javascript:void(0);" class="imUrl" target="_blank">';
	        str += '                  <span class="imInfo" style="display:none">';
	        str += '	                  '+$('#staffCode').html()+'#'+ordList[i].sCustomerOrdMain.shopId+'#1#'+ordList[i].sCustomerOrdMain.orderId;
	        str += '                 </span>';
	        str += '              <i class="micon micon-imkf" ></i>联系客服';
	        str += '              </a>';
	        str += '         </span>';
	        str += '    </td>';
	        str += '</tr>';
	        //子订单取商品信息列表
	        str += '<tr class="tr-bd">';
	        for (var j = 0; j < subOrdList.length; j++) {
	        	var price = parseInt(subOrdList[j].discountPrice)/100;//单价
	        	str += '    <td style="padding:14px">';
		        str += '       <div class="goods-item">';
		        str += '            <div class="p-img">';
		        //无图片，则用默认图片
		        if (subOrdList[j].picUrl == undefined || subOrdList[j].picUrl == null || subOrdList[j].picUrl == '') {
			        str += '                    <img class="" src="' + defaultImageUrl + '" title="" width="60" height="60">';
		        } else {
			        str += '                    <img class="" src="' + subOrdList[j].picUrl + '" title="" width="60" height="60">';
		        }
		        str += '            </div>';
		        str += '            <div class="p-msg">';
		        str += '                <div class="p-name">';
		        str += '                    <a href="' + $webroot + subOrdList[j].gdsUrl + '" class="a-link" target="_blank" title="">' + subOrdList[j].gdsName;
		        str += '                    </a>';
		        str += '                </div>';
		        str += '            </div>';
		        str += '        </div>';
		        str += '        <div class="goods-number">';
		        str += '            x ' + subOrdList[j].orderAmount;
		        str += '        </div>';
		        str += '        <div class="goods-repair">';
		      
		        str += '            ' + price.toFixed(2);
		      
		        str += '        </div>';
		        str += '        <div class="clr"></div>';
		        str += '    </td>';
		        if (j == 0) {
			        str += '    <td rowspan="' + subOrdList.length + '">';
			        str += '        <div class="amount">';
			        str += '            <strong>&yen;' + realMoney.toFixed(2) + '</strong> <br>';
			        str += '            <span class="t-gray">(含运费：' + expressMoney.toFixed(2) + ')</span><br>';
			        str += '        </div>';
			        str += '    </td>';
			        str += '    <td rowspan="' + subOrdList.length + '">';
			        str += '        <div class="order-status">';
			        str += '            <a href="javascript:void(0);">';
			        str += '            	  ' + orderStatusChange(ordList[i].sCustomerOrdMain.status);
			        str += '            </a>';
			        str += '            <br>';
			        str += '            <a href="javascript:void(0);" class="_detail" id="'+ordList[i].sCustomerOrdMain.orderId+'" >订单详情</a>';
			        str += '        </div>';
			        str += '    </td>';
			        str += '    <td rowspan="' + subOrdList.length + '">';
			        str += '        <div class="operate">';
			        //未付款
			        if (ordList[i].sCustomerOrdMain.payFlag == '0') {
			        	//类型为在线支付，才显示付款
			        	if (ordList[i].sCustomerOrdMain.payType == '0') {
				        	str += '            <a href="javascript:void(0);" class="btn-red" ><font color="blue" class="_pay" id="' + ordid_shopid + '" >付款</font></a>';
					        str += '            <br/>';
			        	}
				        str += '            <a href="javascript:void(0);">';
				        str += '            <font color="red" class="_cancel" id="'+ordList[i].sCustomerOrdMain.orderId+'">取消订单</font>';
				        str += '            </a>';
				    //已付款，已发货，未收货
			        } else if (ordType == 'recept' && ordList[i].sCustomerOrdMain.payFlag == '1' && ordList[i].sCustomerOrdMain.status != '06') {
			        	str += '            <a href="javascript:void(0);" class="_recept" id="'+ordList[i].sCustomerOrdMain.orderId+'" ><font color="blue" >确认收货</font></a>';
				        str += '            <br/>';
			        //已收货，待评价
			        } else if (ordList[i].sCustomerOrdMain.payFlag == '1' && ordList[i].sCustomerOrdMain.status == '06') {
			        	if ($.inArray(ordList[i].sCustomerOrdMain.orderId,evalFlag) != -1) {
			        		str += '            <a href="javascript:void(0);" class="btn-red" ><font color="blue" class="_comment" id="'+ordList[i].sCustomerOrdMain.orderId+'">评价商品</font></a>';
					        str += '            <br/>';
			        	}
			        }
			        str += '        </div>';
			        str += '    </td>';
			    }
		        str += '</tr>';
	        }
	        
		}
		$('#buyer-orderlist').html(str);
	    $('.imUrl').each(function(){
	    	$(this).attr("href",IM.imUrl($('.imInfo',$(this)).html()));
	    })
	}
	
	/*渲染空的列表*/
	var renderOrdListEmpty = function() {
		//订单列表的内容
		var str = "<tr><td colspan='5' style='text-align:center;border-style:none'><span ><font size='3'>没有查到符合条件的记录，请尝试其他条件。</font></span></td></tr>";
		$('#buyer-orderlist').html(str);
	}
	
	/*切换不同状态，查询订单列表*/
	
	//待付款订单
	$('#pay_list').click(function(){
		//把其他选项的样式 变为未滶活
		$('#recept_list').attr('class', ''); 
		$('#recepted_list').attr('class', ''); 
		$('#send_list').attr('class', ''); 
		$(this).attr('class', 'active'); 
		$('#moreOrder').attr('href', $webroot + 'order/pay'); 
		//调用订单列表渲染方法 
		buyerOrderList('pay');  
	});
	//已发货订单
	$('#send_list').click(function(){
		//把其他选项的样式 变为未滶活
		$('#recept_list').attr('class', ''); 
		$('#pay_list').attr('class', ''); 
		$('#recepted_list').attr('class', ''); 
		$(this).attr('class', 'active');
		$('#moreOrder').attr('href', $webroot + 'order/send');   
		//调用订单列表渲染方法 
		buyerOrderList('send');
	});
	//待收货订单
	$('#recept_list').click(function(){
		//把其他选项的样式 变为未滶活
		$('#pay_list').attr('class', ''); 
		$('#send_list').attr('class', ''); 
		$('#recepted_list').attr('class', ''); 
		$(this).attr('class', 'active');  
		$('#moreOrder').attr('href', $webroot + 'order/recept'); 
		//调用订单列表渲染方法 
		buyerOrderList('recept');
	});
	//已收货订单
	$('#recepted_list').click(function(){
		//把其他选项的样式 变为未滶活
		$('#recept_list').attr('class', ''); 
		$('#pay_list').attr('class', ''); 
		$('#send_list').attr('class', ''); 
		$(this).attr('class', 'active');   
		$('#moreOrder').attr('href', $webroot + 'order/recepted');
		//调用订单列表渲染方法 
		buyerOrderList('recepted');
	});
	
	
	/*获取我关注的商品信息并加载页面*/
	var atteGdsList = function () {
		//订单列表的内容
		var str = "<div class='loading-small'></div>";
		$('#careimg').html(str);
		$.eAjax({
			url : $webroot + 'buyercenter/atteGdsList',
			success : function(info) {
				if (info && info.atteGdsList) {
					var atteGdsList = JSON.parse(info.atteGdsList);
					if (atteGdsList != null) {
						//渲染订单列表
						renderGdsList(atteGdsList);
					} 
				} else {
					var str = "<span style='text-align:center;'><font size='3'>您暂时没有关注的商品。</font></span>";
					$('#careimg').html(str);
				}
			}
		});
	}
	
	/*渲染我关注的商品列表*/
	var renderGdsList = function(dataList) {
		var str = '';
		str += '<ul class="gzsp-com clearfix">';
		str += '<div class="slides_container">';
        str += '                        <div class="slide" >';
		for (var i = 0; i < dataList.length; i++) {
			var nowPrice = parseInt(dataList[i].nowPrice)/100;//商品价格
			var guidePrice = parseInt(dataList[i].guidePrice)/100;//商品价格
            str += '<li>';
            if (dataList[i].skuMainPic) {
            	str += '<img style="width:150px;height:150px;" src="'+dataList[i].skuMainPic+'">';
            } else {
            	str += '<img style="width:150px;height:150px;" src="">';
            }
            str += '    <p class="name">'+dataList[i].gdsName+' </p>';
            str += '    <p class="price" style="width:135px;">';
            str += '        <span class="rob">&yen;' +nowPrice.toFixed(2)+ '</span>';
            str += '        <span class="price_r">&yen;' +guidePrice.toFixed(2)+ '</span>';
            str += '    </p>';
            str += '    <p class="name">';
            str += '        <a href="javascript:void(0);" class="btn-default" id="'+dataList[i].gdsUrl+'">';
            str += '            	查看详情';
            str += '        </a>';
            str += '    </p>';
            str += '</li>';
		}
		str += '</ul>';
		str += '</div>';
        str += '                   </div>';
		$('#careimg').html(str);
	}
	
	
	/*数据初始化*/
	//初始化用户账户信息
	buyerAcctInfo();
	//初始化订单列表
	buyerOrderList('pay');
	//我关注的商品列表
	atteGdsList();
});