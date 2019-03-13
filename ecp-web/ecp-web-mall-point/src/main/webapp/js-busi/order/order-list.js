$(function(){
	
    //页面业务逻辑处理内容
    var pageInit = function(){
    	
        var init = function(){
        	
        	//controller配置
            var controllConfig = {
            		'01':'pay',
            		'02':'send',
            		'03':'recept',
            		'04':'recepted',
            		'05':'cancel'
            }
        	
            //title配置
            var titleConfig = {
            		'01':'待支付订单',
            		'02':'待发货订单',
            		'03':'已发货订单',
            		'04':'已收货订单',
            		'05':'已取消订单'
            }
            //常量
        	var constant = {
        			status:$('#status').val(),
        			begDate:ebcDate.dateFormat(ebcDate.yearCalc(new Date(),-1),'yyyy-MM-dd'),
        			endDate:ebcDate.dateFormat(ebcDate.dateCalc(new Date(),1),'yyyy-MM-dd')
        	};
            
            //=================-------================
            //方法定义
            var detailOrd = function(_this){
            	var orderId = _this.parents('.order-tb').find('input[name="orderId"]').val();
        		//$('#detailForm').find('input[name="orderId"]').val(orderId);
        		
        		window.open(GLOBAL.WEBROOT + "/ord/detail/"+orderId);
        		//$('#detailForm').submit();
            }
        	
            //选项跳转
        	$('#teamTab li a').click(function(){
        		var id = $(this).parent().attr('id');
        		window.location.href = GLOBAL.WEBROOT + '/order/'+controllConfig[id];
        	});
        	
        	//选项卡被选中
        	$('#teamTab li').each(function(){
        		if($(this).attr('id')==constant.status){
        			$(this).addClass('active');
            		$(this).siblings().removeClass('active');
        		}
        	})

        	//分页
        	$('#pageControlbar').bPage({
        	    url : GLOBAL.WEBROOT + '/order/'+controllConfig[constant.status],
        	    asyncLoad : false,
        	    params : function(){
        	    	var begDate = $('input[name="begDate"]').val();
        	    	var endDate = $('input[name="endDate"]').val();
        	    	return {
        	    		begDate: begDate?begDate:constant.begDate,
        	    		endDate: endDate?endDate:constant.endDate
        	    	}
        	    }
        	});
        	
        	//title设置
        	//$('title').text(titleConfig[constant.status]);
        	
        	//支付页面
        	$('._pay').click(function(){ 
        		var orderId = $(this).parents('.order-tb').find('input[name="orderId"]').val();
        		var shopId = $(this).parents('.order-tb').find('input[name="shopId"]').val();
        		var isStatus = true;
        		var url = GLOBAL.WEBROOT + "/pay/check"; 
        		$.eAjax({
        			url:url,
        			data:[{name:"orderId",value:orderId},{name:"oper",value:"04"}],
        			async:false,
        			method:'post',
        			success:function(returnInfo){
        				if(returnInfo&&returnInfo.flag==false){  
        					isStatus = false; 
        					eDialog.alert(returnInfo.msg,function(){
        						window.location.href = GLOBAL.WEBROOT + "/order/pay";
        					}); 
        				}  
        			}
        		});   
        		if(isStatus == true){
        			window.open(GLOBAL.WEBROOT + "/pay/queryWay?orderId="+orderId+"&shopId="+shopId);
        		} 
        	});
        	
        	//订单详情
        	$('._detail').click(function(){
        		detailOrd($(this));
        	});
        	$('.order-tb .number').click(function(){
        		detailOrd($(this));
        	});
        	
        	//取消订单
        	$('._cancel').click(function(){
        		var _this = this;
        		eDialog.confirm("取消订单" , {
        		    buttons : [{
        		        'caption' : '确定',
        		        'callback' : function(){
        		        	var orderId = $(_this).closest('.order-tb').find('input[name="orderId"]').val();
        		        	var url = GLOBAL.WEBROOT + "/ord/myorder/cancel";
        		        	$.eAjax({
        	        			url:url,
        	        			data:[{name:"orderId",value:orderId},{name:"oper",value:"04"}],
        	        			method:'post',
        	        			success:function(returnInfo){
        	        				if(returnInfo&&returnInfo.resultFlag=='true'){
        	        					window.location.href = GLOBAL.WEBROOT + "/order/pay";
        	        				}else{
        	        					eDialog.alert(returnInfo.resultMsg,function(){
        	        						window.location.href = GLOBAL.WEBROOT + "/order/pay";
        	        					});
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
        		
        		//$('#cancelForm').submit();
        	});
        	
        	/**
			 * 修改地址
			 * wangxq
			 */
			$('._update').live('click',function(){
				var orderId = $(this).parents('.order-tb').find('input[name="orderId"]').val();
		        bDialog.open({
		            title : "修改收货人信息",
		            width : 780,
		            height : 400,
		            params : {orderId:orderId},
		            url : GLOBAL.WEBROOT+'/ord/buyeraddrupdate?orderId='+orderId,
		            callback:function(returnInfo){
		            	if(returnInfo&&returnInfo.results){
		            		if(returnInfo.results[0].resultFlag == 'ok'){
		            		}else if(returnInfo.results[0].resultFlag == 'expt'){
		            			eDialog.alert('系统异常');
		            		}
		            	}
		            }
		        });
			});
			
        	//买家收货确认
        	$('._confirm').click(function(){
        		var _this = this;
        		eDialog.confirm("收货确认" , {
        		    buttons : [{
        		        'caption' : '确定',
        		        'callback' : function(){
        		        	var orderId = $(_this).closest('.order-tb').find("input[name='orderId']").val();
        		        	var url = GLOBAL.WEBROOT + "/order/confirmord";
        		        	$.eAjax({
        	        			url:url,
        	        			data:[{name:"orderId",value:orderId},{name:"oper",value:"03"}],
        	        			method:'post',
        	        			success:function(returnInfo){
        	        				if(returnInfo&&returnInfo.resultFlag=='true'){
        	        					window.location.href = GLOBAL.WEBROOT + "/order/recept";
        	        				}else{
        	        					eDialog.alert(returnInfo.resultMsg,function(){
        	        						window.location.href = GLOBAL.WEBROOT + "/order/recept";
        	        					});
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
        	
        	//待评价页面
        	$('._comment').click(function(){

        		window.open(GLOBAL.WEBROOT + "/comment");
        	});
        };

        return {
            init : init
        };
    };
    pageConfig.config({
        //指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
        plugin : ['bForm','bPage'],
        //指定页面
        init : function(){
            var p = new pageInit();
            p.init();
        }
    });

});