var ordersub_const = {
		subkey:"rConfirmSubInfo"
		
};
var ordersub_grid={
		num:function(_sub_id){
			var $num = $("input[name='"+_sub_id+"num']");
			if($num.val()=='') $num.val(1);
			return $num;
		},
		addAndMinus:function(_sub_id,min,max){
			var _num = ordersub_grid.num(_sub_id).val();
			var reg = new RegExp("^[0-9]*$");
			if(reg.test(_num)){
				var ad = parseInt(_num);
				if(ad>=max){
					ordersub_grid.num(_sub_id).val(max);
					return;
				}
				if(ad<=min){
					ordersub_grid.num(_sub_id).val(min);
					return;
				}
				if(min<=ad&&ad<=max){
					ordersub_grid.num(_sub_id).val(ad);
				}
				
			}else{
				ordersub_grid.num(_sub_id).val(max);
			}
		},
		minus:function(_sub_id,max){
			var _num = ordersub_grid.num(_sub_id).val();
			var reg = new RegExp("^[0-9]*$");
			if(reg.test(_num)){
				var min = _num-1;
				if(min>=1&&min<=max){
					ordersub_grid.num(_sub_id).val(min);
				}else{
					ordersub_grid.num(_sub_id).val(1);
				}
			}else{
				ordersub_grid.num(_sub_id).val(max);
			}
		},
		add:function(_sub_id,max){
			var _num = ordersub_grid.num(_sub_id).val();
			var reg = new RegExp("^[0-9]*$");
			if(reg.test(_num)){
				var ad = parseInt(_num)+1;
				if(ad<=max){
					ordersub_grid.num(_sub_id).val(ad);
				}else{
					ordersub_grid.num(_sub_id).val(max);
				}
			}else{
				ordersub_grid.num(_sub_id).val(max);
			}
			
		},
		
		getIsSendAll:function(selected){
			if(selected.length == $('#AllRecordNum').val())
			{
				return true;
			}
			return false;
		},
		getOrderId:function(){
			return orderId;
		},
		getIsImport:function(_sub_id){
			return 0;
		},
		pushOrdSubInfos:function(data,select){
			
			$.each(select,function(n,value){
				data.push({name:ordersub_const.subkey+"["+n+"].deliveryAmount",value:ordersub_grid.num(value).val()});
				data.push({name:ordersub_const.subkey+"["+n+"].isImport",value:ordersub_grid.getIsImport(value)});
				data.push({name:ordersub_const.subkey+"["+n+"].orderSubId",value:value});
			});
			
			return data;
		},
		inputCheck:function(){
			$("input[name$='num']").on('keydown',function(){
				var reg = new RegExp("^[0-9]*$");
				var _num = $(this).val();
				if(!reg.test(_num)) $(this).val(1);
			});
		},
		orderInfoFormValid:function(){
			var valid = $("#orderInfoForm").valid();
			eDialog.alert($('.error').text()=='必填字段'?'请输入快递单号':$('.error').text());
			var company = $("input[name='deliveryType']:checked").hasClass('_kd');
			if(company){
				if(isEmpty($('#expressCompanyId').val())){
					eDialog.alert('快递公司未选择');
					return false;
				}
			}
			return valid;
		}
	
};
var checkbox_fun = {
	    selectAll:function(){ 
	        if($("#allCheckBoxBtn").attr("checked")){    
	            $("#tableBody :checkbox").attr("checked", true);   
	        }else{    
	            $("#tableBody :checkbox").attr("checked", false); 
	        }    
	     }, 
		getAllCheckedSubId:function(){
	        var subid = new Array; 
	        $("#tableBody :checkbox[checked]").each(function(i){ 
	        	subid[i] = $(this).val(); 
	        }); 
	        return subid;
		}
};
$(function(){

    $("#tableBody :checkbox").click(function(){ 
        if(this.checked){    
        	 $(this).attr("checked", true);   
        }else{    
        	 $(this).attr("checked", false); 
        }  
    }); 
	$("#btn_code_publish").click(function(){

		if(!ordersub_grid.orderInfoFormValid()) {
			//$('#expressNo').next().text('请输入快递单号');
			return false;
		}
		var val = checkbox_fun.getAllCheckedSubId();
		var message = "子订单未全部发货，您确认发货吗？";
		var isSendAll = ordersub_grid.getIsSendAll(val);
		if(isSendAll){
			message = "您确认要发货吗？";
		}
		if(val && val.length){
			eDialog.confirm(message, {
				buttons : [{
					caption : '确认',
					callback : function(){
						//$.gridLoading({"message":"正在加载中...."});
						//获取重要数据
						var data = ebcForm.formParams($("#orderInfoForm"));
						var orderId = $('#orderId').val();
						var shopId  = $('#shopId').val();
						data.push({name:"isSendAll",value:ordersub_grid.getIsSendAll(val)});
						ordersub_grid.pushOrdSubInfos(data,val);						
						$.eAjax({
							url : GLOBAL.WEBROOT + "/seller/order/delivery/confirmsend",
							data : data,
							datatype:'json',
							success : function(returnInfo) {
								//$.gridUnLoading();
								if(returnInfo.resultFlag=='ok'){
									if(ordersub_grid.getIsSendAll(val)){
										$("#btn_code_return").click();
									}else{										
										window.location.href = GLOBAL.WEBROOT + '/seller/order/delivery/dosend/'+shopId+'/'+orderId;
									}
								}
								if(returnInfo.resultFlag=='expt'){
									eDialog.alert(returnInfo.resultMsg,function(){
										window.location.href = GLOBAL.WEBROOT + '/seller/order/delivery/dosend/'+shopId+'/'+orderId;
									});
								}
							}
						});
						
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个子订单进行操作！');
		}
	});
	
	
	$("#btn_code_return").click(function(){
		window.location.href = GLOBAL.WEBROOT + '/seller/order/delivery/index';
	});
	$("._zt").click(function(){
		//去物流公司遗留样式
		$('#expressNo').val('').prop('disabled',true).parent().removeClass('f_error').find('label').hide();
		$('#expressCompanyId').val('').prop('disabled',true);
		$("#userInfo").hide();
	});
	
	$("._kd").click(function(){
		$('#expressCompanyId').prop('disabled',false);
		$('#expressNo').prop('disabled',false);
		$("#userInfo").show();
	});

	$("._py").click(function(){
		$('#expressCompanyId').val('').prop('disabled',true);
		$('#expressNo').val('').prop('disabled',false);
		$("#userInfo").show();
	});
	var packupway = $("input[name='deliveryType']:checked").attr("id");
	$("#"+packupway).trigger('click');


});