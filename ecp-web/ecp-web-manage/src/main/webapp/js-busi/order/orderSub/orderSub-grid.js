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
		
		getIsSendAll:function(){
		    var select = $('#dataGridTable').getSelectedData();
		    var all = $('#dataGridTable').getAllData();
		    var checkAllSelect = false;
		    try{
		    	if(select.length == all.length){
			    	checkAllSelect = true;
			    }
		    }catch(e){
		    	
		    }
		    var orderSubSelect;
		    //遍历
		    if(checkAllSelect){
		    	$.each(select,function(n,value){
		    		var row = value;
		    		var _sub_id = row.orderSubId;
		    		if(orderSubSelect!=undefined){
		    			orderSubSelect = orderSubSelect&&(parseInt(row.remainAmount)==ordersub_grid.num(_sub_id).val());
		    		}else{
		    			orderSubSelect = (parseInt(row.remainAmount)==ordersub_grid.num(_sub_id).val());
		    		}
		    	})
		    }
		    return checkAllSelect&&orderSubSelect;
		},
		getOrderId:function(){
			return orderId;
		},
		getIsImport:function(_sub_id){
			return 0;
		},
		pushOrdSubInfos:function(data,keys){
			var select = $('#dataGridTable').getSelectedData();
			$.each(select,function(n,value){
				data.push({name:ordersub_const.subkey+"["+n+"].deliveryAmount",value:ordersub_grid.num(value["orderSubId"]).val()});
				data.push({name:ordersub_const.subkey+"["+n+"].isImport",value:ordersub_grid.getIsImport(value["orderSubId"])});
				data.push({name:ordersub_const.subkey+"["+n+"].isSendAll",value:parseInt(value["remainAmount"])==ordersub_grid.num(value["orderSubId"]).val()});
				$.each(keys,function(m,mvalue){
					data.push({name:ordersub_const.subkey+"["+n+"]."+keys[m],value:value[keys[m]]});
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
			eDialog.alert($('.error').text()=='必填字段'?'请输入快递单号':$('.error').text());
			var company = $("input[name='deliveryType']:checked").hasClass('_kd');
			if(company){
				if(isEmpty($('#company').val())){
					eDialog.alert('快递公司未选择');
					return false;
				}
			}
			return valid;
		}
	
};
$(function(){

	
	$("#dataGridTable").initDT({
	    'pTableTools' : false,
	    'pCheck' : 'multi',
	    'pLengthMenu' : [100],
	    'pCheckRow' : false,
	    'pSingleCheckClean' : false,
	    "sAjaxSource": GLOBAL.WEBROOT + '/ordersub/ordersubs/'+orderId,
	    //指定列数据位置
	    "aoColumns": [
			{ "mData": "orderMoney", "sTitle":"订单信息","sClass":"center","bSortable":true,"mRender": function(data,type,row){
				return "<p>订单编号："+orderId+"</p>"
		          +"<p>子订单号："+row.orderSubId+"</p>"
		          +"<p>商品名称："+row.gdsName+"</p>";
			}},
			{ "mData": "skuInfo", "sTitle":"商品信息","sClass":"center","bSortable":true,"sClass":"center","mRender": function(data,type,row){
				var _num = row.remainAmount;
				var _ordernum=row.remainAmount+row.deliverAmount;
				var gdsUrl = $("#siteInfo").val()+row.gdsUrl;
				var str = "<div style=\"margin-right: 20px;float: left;\"><a href='"+gdsUrl+"' target='_blank'><img src='"+row.imageUrl+"' width='48'></img></a></div>";
				str += "<div style=\"float: left;\"><p>商品属性："+ (row.skuInfo?row.skuInfo:"")+" 价格："+ebcUtils.numFormat(row.orderMoney/100, 2)+(isEmpty(row.zsCode)?"":"&nbsp;&nbsp;ISBN："+row.zsCode)+" </p>" +
		        "<p>订购量：<span style=\"color:red\">"+_ordernum+"</span> </p>"+
				"<p>已发货：<span style=\"color:red\">"+row.deliverAmount+"</span>  未发货：<span style=\"color:red\">"+_num+"</span></p></div>"
				return str;
			}},
			{ "mData": "gdsName", "sTitle":"发货信息","sClass":"center","bSortable":true,"sClass":"center","mRender": function(data,type,row){
				var _sub_id = row.orderSubId;
				var _num = row.remainAmount;
				ordersub_grid.inputCheck();
				return "发货数量："
						+"<div class=\"count-input \" style=\"float:right\">"
                            +"<a class=\"minus\" href=\"javascript:void(0)\" onclick=\"ordersub_grid.minus(\'"+_sub_id+"\',"+_num+")\">-</a>"
                            +"<input type=\"text\" value="+_num+" name=\""+_sub_id+"num\" onblur=\"ordersub_grid.addAndMinus(\'"+_sub_id+"\',1,"+_num+")\" onkeypress=\"javascript:return ebcUtils.checkNum(event,this)\"></input>"
                            +"<a class=\"add\" href=\"javascript:void(0)\" onclick=\"ordersub_grid.add(\'"+_sub_id+"\',"+_num+")\">+</a>"
                        +"</div>";
			}}
		],
		'onSuccess':function(){
			$("#dataGridTable").selectAllRow();
			ordersub_grid.inputCheck();
		}
	});



	$('#btn_entity_code_input').click(function(){
		bDialog.open({
			title : '弹出窗口',
			width : 700,
			height : 450,
			url : GLOBAL.WEBROOT + '/ordersub/open',
			params : {
				'userName' : 'zhangsan'
			},
			callback:function(data){
				if(data && data.result && data.result.length > 0 ){
					eDialog.alert('已完成弹出窗口操作！接收到弹出窗口传回的 userName 参数，值为：' + data.results[0].userName );
				}else{
					eDialog.alert('弹出窗口未回传参数',$.noop,'error');
				}
			}
		});
	});
	
	$("#btn_code_publish").click(function(){
		var val = $('#dataGridTable').getSelectedData();
		if(!ordersub_grid.orderInfoFormValid()) {
			//$('#expressNo').next().text('请输入快递单号');
			return false;
		}

		var message = "子订单未全部发货，您确认发货吗？";
		var isSendAll = ordersub_grid.getIsSendAll();
		if(isSendAll){
			message = "您确认要发货吗？";
		}
		if(val && val.length){
			eDialog.confirm(message, {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.gridLoading({"message":"正在加载中...."});
						//eDialog.alert('success','发布成功！');
						//获取重要数据
						var data = ebcForm.formParams($("#orderInfoForm"));

						data.push({name:"orderId",value:ordersub_grid.getOrderId()});
						data.push({name:"isSendAll",value:ordersub_grid.getIsSendAll()});
						ordersub_grid.pushOrdSubInfos(data,["orderSubId"]);
						$.eAjax({
							url : GLOBAL.WEBROOT + "/ordersub/confirmsend",
							data : data,
							datatype:'json',
							success : function(returnInfo) {
								$.gridUnLoading();
								if(returnInfo.resultFlag=='ok'){
									if(ordersub_grid.getIsSendAll()){
										window.location.href = GLOBAL.WEBROOT + "/order";
									}else{
										var p = ebcForm.formParams("#dataGridTable");
										$("#dataGridTable").gridSearch(p);
										setTimeout(function(){
											$("#dataGridTable").selectAllRow();
										},1000);
									}
								}
								if(returnInfo.resultFlag=='expt'){
									eDialog.alert(returnInfo.resultMsg,function(){
										var p = ebcForm.formParams("#dataGridTable");
										$("#dataGridTable").gridSearch(p);
										setTimeout(function(){
											$("#dataGridTable").selectAllRow();
										},1000);
									})
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
		window.location.href = GLOBAL.WEBROOT + '/order';
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