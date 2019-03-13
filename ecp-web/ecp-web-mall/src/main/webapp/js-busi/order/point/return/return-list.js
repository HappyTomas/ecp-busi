//页面初始化模块
$(function(){ 
	//页面业务逻辑处理内容
    var pageInit = function(){
        var init = function(){
        	
        	//controller配置
            var controllConfig = {
            		'01':'moneyList',
            		'02':'returnList'
            };
            
            //常量
        	var constant = {
        			status:$('#status').val()
        	};
        	
            //选项跳转
        	$('#teamTab li').click(function(){
        		var id = $(this).attr('id');
        		window.location.href = GLOBAL.WEBROOT + '/order/point/return/'+controllConfig[id];
        	});
        	
        	//分页
        	$('#pageControlbar').bPage({
        	    url : GLOBAL.WEBROOT + '/order/point/return/'+controllConfig[constant.status],
        	    asyncLoad : false,
        	    params : function(){}
        	}); 
			 
			//退货申请页面统计当前所有商品的总数量
			var checkItemCount = function(){
				var itemCount = 0; 
				$('#subList :checkbox:checked').each(function(i,row){
					var box = $(this).closest('.item-body');
					var check = $('.itemCheck',$(box));
					if($(check).prop('checked')){
						itemCount ++;
					}
				});
				$('#selectItemCount').text(itemCount);
			};
			
			$('#selectAllItem').on('click',function(e){
				checkItemCount();
			});
		    //退货申请页面默认选中所有商品
			$('#selectAllItem').click();
			//退货申请页面选中商品
			$('#subList .itemCheck').on('click',function(e){
				checkItemCount();
			});
			
			//订单详情
			$('.return-ord-detail .number').click(function(){
        		detailOrd($(this));
        	});
			//方法定义
            var detailOrd = function(_this){
            	var orderId = _this.parents('.return-ord-detail').find('input[name="orderId"]').val();
        		//$('#detailForm').find('input[name="orderId"]').val(orderId);
        		
        		window.open(GLOBAL.WEBROOT + "//seller/order/orderdetail/detail/"+orderId);
        		//$('#detailForm').submit();
            };
			
          //退货发货保存
			$('#btn_send').click(function(){
				var backId = $("#backId").val();
				var orderId = $("#orderId").val();
				var express = $.trim($("#express").val());
				var expressNo = $.trim($("#expressNo").val());
				if(express == ""){
					eDialog.alert('请输入物流公司名称！');
				}else if(expressNo == ""){
					eDialog.alert('请输入快递单号！');
				}else{
					var isStatus = true;
	        		var url = GLOBAL.WEBROOT + "/order/point/return/checkReturn";
		        	$.eAjax({
	        			url:url,
	        			data:[{name:"backId",value:backId},{name:"orderId",value:orderId},{name:"oper",value:"02"}],
	        			async:false,
	        			method:'post',
	        			success:function(returnInfo){
	        				if(returnInfo&&returnInfo.flag==false){
	        					isStatus = false; 
	        					eDialog.alert(returnInfo.msg);
	        				}
	        			}
	        		}); 
		        	if(isStatus == true){
		        		var url = GLOBAL.WEBROOT + "/order/point/return/saveBackGdsSend";
						$.eAjax({
							url:url,
							data:ebcForm.formParams($("#gdsSendForm")),
							async:false,
							method:'post',
							success:function(returnInfo){
								if(returnInfo&&returnInfo.resultFlag=='ok'){
									eDialog.alert('退货发货成功',function(){
										window.location.href = GLOBAL.WEBROOT + "/order/return/returnList";
									}); 
								}else{
									eDialog.alert(returnInfo.resultMsg);
								}
								
							}
						}); 
		        	} 
				} 
			});
		
			
			$("#pictrue").live("change", function(o) {
				var url1=$("#image1").attr("src");
				var url2=$("#image2").attr("src");
				var url3=$("#image3").attr("src");
				var url4=$("#image4").attr("src");
				var url5=$("#image5").attr("src");
				if(!url1 || !url2 || !url3 || !url4 || !url5){
					var path = $(this).val();
					uploadImage(this, path);
				}
				
			});
			
			//退货申请提交页面
			$('.apply-btn').click(function(){
				if($('.itemCheck:checked').size()==0){
					eDialog.alert('请至少选择一项商品');
					return;
				}else{
					$.eAjax({
						url:GLOBAL.WEBROOT + '/order/point/return/saveSessionSub',
						data:ebcForm.formParams($("#childForm")),
						success:function(returnInfo){ 
							if(returnInfo&&returnInfo.resultFlag=='ok'){
								window.location.href = GLOBAL.WEBROOT + '/order/point/return/returnApplySub/'+$("#orderId").val();
							}else{
								eDialog.alert(returnInfo.resultMsg,function(){
									window.location.replace(GLOBAL.WEBROOT + '/order/point/return/returnChild/'+$("#orderId").val());
								},'error');
							}
						}
					});
				} 
			});
			
			//退款申请保存
			$('#btn_apply').click(function(){
				if(!$("#applyForm").valid()) return false;
				$("#backTypeName").val($("#backType option:selected").text());
				var orderId = $("#orderId").val();
			    var isStatus = true;
	        	var url = GLOBAL.WEBROOT + "/order/point/return/checkReturn";
	        	$.eAjax({
        			url:url,
        			data:[{name:"backId",value:""},{name:"orderId",value:orderId},{name:"oper",value:"01"}],
        			async:false,
        			method:'post',
        			success:function(returnInfo){
        				if(returnInfo&&returnInfo.flag==false){
        					isStatus = false; 
        					eDialog.alert(returnInfo.msg);
        				}
        				
        			}
        		}); 
	        	if(isStatus == true){
	        		var url = GLOBAL.WEBROOT + "/order/point/return/saveBackMoneyApply";
			    	$.eAjax({
						url:url,
						data:ebcForm.formParams($("#applyForm")),
						async:false,
						method:'post',
						success:function(returnInfo){
							if(returnInfo&&returnInfo.resultFlag=='ok'){
								eDialog.alert('退款申请成功',function(){
									window.location.href = GLOBAL.WEBROOT + "/order/return/moneyList";
								}); 
							}else{
								eDialog.alert(returnInfo.resultMsg);
							}
							
						}
					}); 
	        	} 
			});
			
			//退货申请保存
			$('#btn_apply_sub').click(function(){
				if(!$("#applySubForm").valid()) return false;
				$("#backTypeName").val($("#backType option:selected").text());  
			    var orderId = $("#orderId").val();
			    var isStatus = true;
        		var url = GLOBAL.WEBROOT + "/order/point/return/checkReturn";
	        	$.eAjax({
        			url:url,
        			data:[{name:"backId",value:""},{name:"orderId",value:orderId},{name:"oper",value:"00"}],
        			async:false,
        			method:'post',
        			success:function(returnInfo){
        				if(returnInfo&&returnInfo.flag==false){
        					isStatus = false; 
        					eDialog.alert(returnInfo.msg);
        				}
        			}
        		}); 
	        	if(isStatus == true){
	        		var url = GLOBAL.WEBROOT + "/order/point/return/saveBackGdsApply";
			    	$.eAjax({
						url:url,
						data:ebcForm.formParams($("#applySubForm")),
						async:false,
						method:'post',
						success:function(returnInfo){
							if(returnInfo&&returnInfo.resultFlag=='ok'){
								eDialog.alert('退货申请成功',function(){
									window.location.href = GLOBAL.WEBROOT + "/order/return/returnList";
								}); 
							}else{
								eDialog.alert(returnInfo.resultMsg);
							}
							
						}
					}); 
	        	} 
			}); 
			
			//关闭页面
			$("#btnReturn").click(function(){
				window.close();
			});
			
			//关闭图片1
			$("#closeimage1").click(function(){
				for(var i=1;i<5;i++){
					$("#image"+i).attr("src",$("#image"+(i+1)).attr("src"));
					$("#pic"+i).val($("#pic"+(i+1)).val());
				}
				$("#image5").attr("src","");
				$("#pic5").val("");
				$(this).hide();
				for(var j=1;j<=5;j++){
					if(!$("#image"+j).attr("src")){
						$("#li"+j).hide();
					}
				}
			});
			//关闭图片2
			$("#closeimage2").click(function(){
				for(var i=2;i<5;i++){
					$("#image"+i).attr("src",$("#image"+(i+1)).attr("src"));
					$("#pic"+i).val($("#pic"+(i+1)).val());
				}
				$("#image5").attr("src","");
				$("#pic5").val("");
				$(this).hide();
				for(var j=1;j<=5;j++){
					if(!$("#image"+j).attr("src")){
						$("#li"+j).hide();
					}
				}
			});
			//关闭图片3
			$("#closeimage3").click(function(){
				for(var i=3;i<5;i++){
					$("#image"+i).attr("src",$("#image"+(i+1)).attr("src"));
					$("#pic"+i).val($("#pic"+(i+1)).val());
				}
				$("#image5").attr("src","");
				$("#pic5").val("");
				$(this).hide();
				for(var j=1;j<=5;j++){
					if(!$("#image"+j).attr("src")){
						$("#li"+j).hide();
					}
				}
			});
			//关闭图片4
			$("#closeimage4").click(function(){
				for(var i=4;i<5;i++){
					$("#image"+i).attr("src",$("#image"+(i+1)).attr("src"));
					$("#pic"+i).val($("#pic"+(i+1)).val());
				}
				$("#image5").attr("src","");
				$("#pic5").val("");
				$(this).hide();
				for(var j=1;j<=5;j++){
					if(!$("#image"+j).attr("src")){
						$("#li"+j).hide();
					}
				}
			});
			//关闭图片5
			$("#closeimage5").click(function(){
				$("#image5").attr("src","");
				$("#pic5").val("");
				$(this).hide();
				$("#li5").hide();
			});
			 
			 $(".express>li").mouseover(function(){
				var id = $(this).find("img").attr("id");
				if($("#"+id).attr("src") != ""){
					$("#close"+id).show();
				} 
			});
			 $(".express>li").mouseout(function(){
				var id = $(this).find("img").attr("id");
				if($("#"+id).attr("src") != ""){
					$("#close"+id).hide();
				}
			});
			 
			var uploadImage = function (object, path) {
		    	var filepath = path;
		    	filepath=(filepath+'').toLowerCase();
		    	var regex = new RegExp(
		    			'\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$', 'gi');
		    	/** 上传图片文件格式验证 */
		    	if (!filepath || !filepath.match(regex)) {
		    		eDialog.alert('请选择图片格式为(.jpg,.png,.jpeg,.gif).');
		    		uploadfile.value = "";
		    		return;
		    	}
		    	var url = GLOBAL.WEBROOT + '/order/point/return/uploadimage';
		    	var callback = function(returnInfo) {
		    		/** 上传成功，隐藏上传组件，并显示该图片 */
		    		if (returnInfo.success == "ok") {
		    			var url1=$("#image1").attr("src");
		    			var url2=$("#image2").attr("src");
		    			var url3=$("#image3").attr("src");
		    			var url4=$("#image4").attr("src");
		    			var url5=$("#image5").attr("src");
		    			
		    			if(!url1){
		    				$("#image1").attr("src",returnInfo.map.imagePath);
							$("#pic1").val(returnInfo.map.vfsId);
							$("#li1").show();
		    			}else if(!url2){
		    				$("#image2").attr("src",returnInfo.map.imagePath);
							$("#pic2").val(returnInfo.map.vfsId);
							$("#li2").show();
		    			}else if(!url3){
		    				$("#image3").attr("src",returnInfo.map.imagePath);
							$("#pic3").val(returnInfo.map.vfsId);
							$("#li3").show();
		    			}else if(!url4){
		    				$("#image4").attr("src",returnInfo.map.imagePath);
							$("#pic4").val(returnInfo.map.vfsId);
							$("#li4").show();
		    			}else if(!url5){
		    				$("#image5").attr("src",returnInfo.map.imagePath);
							$("#pic5").val(returnInfo.map.vfsId);
							$("#li5").show();
		    			}
		    		} else {
		    			eDialog.error(returnInfo.message);
		    		}
		    	};
		    	ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback);
		    };
		    var ajaxFileUpload = function (url, secureuri, fileElementId, type, dataType, callback) {
				$.ajaxFileUpload({
					url : url, // 用于文件上传的服务器端请求地址
					secureuri : secureuri, // 一般设置为false
					fileElementId : fileElementId, // 文件上传空间的id属性 <input
					// type="file" id="imageFile"
					// name="imageFile" />
					type : type, // get 或 post
					dataType : dataType, // 返回值类型
					success : callback, // 服务器成功响应处理函数
					error : function(data, status, e) // 服务器响应失败处理函数
					{
						alert(e);
					}
				});
			};
        };

        return {
            init : init
        };
    };
    pageConfig.config({
        //指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
        plugin : ['bPage'],
        //指定页面
        init : function(){
            var p = new pageInit();
            p.init();
        }
    });
});