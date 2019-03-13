
var deliver={
	num:function(_sub_id){
		var $num = $("input[name='"+_sub_id+"']");
		if($num.val()=='') $num.val(1);
		return $num;
	},
	addAndMinus:function(_sub_id,max){
		var min = 1;
		var _num = deliver.num(_sub_id).val();
		var reg = new RegExp("^[0-9]*$");
		if(reg.test(_num)){
			var ad = parseInt(_num);
			if(ad>=max){
				deliver.num(_sub_id).val(max);
				return;
			}
			if(ad<=min){
				deliver.num(_sub_id).val(min);
				return;
			}
			if(min<=ad&&ad<=max){
				deliver.num(_sub_id).val(ad);
			}

		}else{
			deliver.num(_sub_id).val(max);
		}
	},
	minus:function(_sub_id,max){
		var _num = deliver.num(_sub_id).val();
		var reg = new RegExp("^[0-9]*$");
		if(reg.test(_num)){
			var min = _num-1;
			if(min>=1&&min<=max){
				deliver.num(_sub_id).val(min);
			}else{
				deliver.num(_sub_id).val(1);
			}
		}else{
			deliver.num(_sub_id).val(max);
		}
	},
	add:function(_sub_id,max){
		var _num = deliver.num(_sub_id).val();
		var reg = new RegExp("^[0-9]*$");
		if(reg.test(_num)){
			var ad = parseInt(_num)+1;
			if(ad<=max){
				deliver.num(_sub_id).val(ad);
			}else{
				deliver.num(_sub_id).val(max);
			}
		}else{
			deliver.num(_sub_id).val(max);
		}

	},
	getOrderId:function(){
		var orderId = $('#orderId').val();
		return orderId;
	},
	getIsImport:function(_sub_id){
		return 0;
	},
	pushOrdSubInfos:function(data,keys){
		var select = $('#dataGridTable').getSelectedData();
		$.each(select,function(n,value){
			data.push({name:"rConfirmSubInfo"+"["+n+"].deliveryAmount",value:deliver.num(value["orderSubId"]).val()});
			data.push({name:"rConfirmSubInfo"+"["+n+"].isImport",value:deliver.getIsImport(value["orderSubId"])});
			data.push({name:"rConfirmSubInfo"+"["+n+"].isSendAll",value:parseInt(value["remainAmount"])==deliver.num(value["orderSubId"]).val()});
			$.each(keys,function(m,mvalue){
				data.push({name:"rConfirmSubInfo"+"["+n+"]."+keys[m],value:value[keys[m]]});
			});

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
		if(!valid){
			eDialog.alert($('.error').text()=='必填字段'?'请输入快递单号':$('.error').text());
		}
		var company = $("input[name='deliveryType']:checked").hasClass('_kd');
		if(company){
			if(isEmpty($('#company').val())){
				eDialog.alert('物流公司未选择');
				return false;
			}
		}
		return valid;
	}

};
$(function(){
	$("#selectAll").click(function () {
		if($(this).attr("checked")){
			$("#ordContent").find("input[type=checkbox]").each(function(){
				$(this).attr("checked", true);
			});
		} else {
			$("#ordContent").find("input[type=checkbox]").each(function(){
				$(this).attr("checked", false);
			});
		}
	});
	$("#ordContent").find("input[type=checkbox]").click(function() {
		var $subs = $("#ordContent").find("input[type=checkbox]");
		$("#selectAll").prop("checked" , $subs.length == $subs.filter(":checked").length ? true:false);
	});



	$("#btn_code_publish").click(function(){
		var subData = [];
		var sendNum = 0;
		var num = 0;
		$("#ordContent").find("input[type=checkbox]").filter(":checked").each(function () {
			// var input = $(this).parent().parent().find("input[type=text]");
			var input = $(this).parent().parent().find("input[type=hidden]");
			var subId = input.attr("name");
			var amount = input.val();
			var name1 = "unpfSendSubReqVOList["+num+"].id";
			var name2 = "unpfSendSubReqVOList["+num+"].deliveryAmount";
			subData.push({name:name1,value:subId});
			subData.push({name:name2,value:amount});
			sendNum += parseInt(amount);
			num++;
		});
		if(subData.length < 1){
			eDialog.alert('请选择至少选择一个子订单进行操作！');
		}
		if(!deliver.orderInfoFormValid()) {
			return false;
		}
		var allNum = 0;
		$("#ordContent").find(".remainAmount").each(function () {
			allNum += parseInt($(this).html());

		});
		var isSendAll = "1";
		var message = "您确认要发货吗？";
		if(sendNum != allNum){
			message = "子订单未全部发货，您确认发货吗？";
			isSendAll = "0";
		}
		// alert(sendNum +"    " + allNum + "  " + message);

		eDialog.confirm(message, {
			buttons : [{
				caption : '确认',
				callback : function(){
					$.gridLoading({"message":"正在加载中...."});
					var mainForm = ebcForm.formParams($("#orderInfoForm"));
					mainForm.push({name:"orderId",value:deliver.getOrderId()});
					mainForm.push({name:"isSendAll",value:isSendAll});
					var data = mainForm.concat(subData);
					$.eAjax({
						url : GLOBAL.WEBROOT + "/unpfdeliver/confirmsend",
						data : data,
						datatype:'json',
						success : function(returnInfo) {
							$.gridUnLoading();
							if(returnInfo.resultFlag=='ok'){

								if(sendNum == allNum){
									window.location.href = GLOBAL.WEBROOT + "/unpfdeliver/init";
								}else{
									window.location.href = GLOBAL.WEBROOT + '/unpfdeliver/send?orderId='+deliver.getOrderId();
								}
							} else if(returnInfo.resultFlag=='expt') {
								eDialog.alert(returnInfo.resultMsg,function(){
									window.location.href = GLOBAL.WEBROOT + '/unpfdeliver/send?orderId='+deliver.getOrderId();
								});

							}
						},
						exception:function() {
							$.gridUnLoading();
						}
					});
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});

	});

	$("#btn_code_return").click(function(){
		window.location.href = GLOBAL.WEBROOT + '/unpfdeliver/init';
	});

	$("._zt").click(function(){
		//去物流公司遗留样式
		$('#expressNo').val('').prop('disabled',true).parent().removeClass('f_error').find('label').hide();
		$('#company').val('').prop('disabled',true);
		$("#userInfo").hide();
	});

	$("._kd").click(function(){
		$('#company').prop('disabled',false);
		$('#expressNo').prop('disabled',false);
		$("#userInfo").show();
	});

	$("._py").click(function(){
		$('#company').val('').prop('disabled',true);
		$('#expressNo').val('').prop('disabled',false);
		$("#userInfo").show();
	});

	var packupway = $("input[name='deliveryType']:checked").attr("id");
	$("#"+packupway).trigger('click');


	$(":input").bind('keypress',function(event){
		if(event.keyCode == "13")
		{
			// alert('你输入的内容为：' + $(this).val());
			return false;
		}
	});
});