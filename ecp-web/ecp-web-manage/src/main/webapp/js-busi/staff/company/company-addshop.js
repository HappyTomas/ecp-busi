$(function(){
	var url =  GLOBAL.WEBROOT + '/company/addshopmsg';
    //设置全选复选框 
    $("#list :checkbox").click(function(){ 
    	
//        var chk = 0; 
//        $("#list :checkbox[checked]").each(function (i) {   
//                chk++; 
//        }); 
//        if(chk>1&&!this.checked)
//        {
//       	 	$(this).attr("checked", false);   
//        }
//        else{
//       	 	$(this).attr("checked", true); 
//        }
        if(this.checked){    
        	 $(this).attr("checked", true);   
        }else{    
        	 $(this).attr("checked", false); 
        }  
    }); 
	$('#btnFormSave').click(function(){ 
		if(!$("#detailInfoForm").valid())return false;
		//if(!$("#shipTemplateForm").valid())return false;
		if(!ShipAdd.validDefaultFree())return false;

		var password = $('#staffPasswd').val();
		var password1 = $('#staffPasswd2').val();
		if(password!=password1){
			eDialog.success('两次密码输入不正确',{
				buttons:[{
					caption:"确定",
					callback:function(){
						return false;
			        }
				}]
			}); 
		}
		
		var param = ebcForm.formParams($("#detailInfoForm"));
		//解析运费模版参数信息
		var shipTemplateType = "1";
		var ifFree = "0";
		var defaultFreeParam="";
		var seniorFreeParam="";
		var shipTemplateName="";
		$("input[type='radio']").each(function(){
			var $this = $(this);
			if($this.attr("checked")=="checked"){
				shipTemplateType = $this.attr('id');
			}
		});
		
		if($("#ifFree").attr("checked")=="checked"){
			ifFree = "1";
		}else{
			defaultFreeParam = ShopShipAdd.getDefaultParam();
		}
		shipTemplateName = $("#shipTemplateName").val();
		param.push(
		{
			name : 'shipTemplateName',
			value : shipTemplateName
		});
		param.push(
		{
			name : 'shipTemplateType',
			value : shipTemplateType
		});
		param.push(
		{
			name : 'ifFree',
			value : ifFree
		});
		param.push(
		{
			name : 'defaultFreeParam',
			value : defaultFreeParam
		});

        var valArr = new Array; 
        $("#list :checkbox[checked]").each(function(i){ 
            valArr[i] = $(this).val(); 
        }); 
        var vals = valArr.join(','); 
        
		param.push(
		{
			name : 'distribution',
			value : vals
		});
		
		$(this).attr('disabled', 'true');
		$.eAjax({
			url : url,
			data : param,
			datatype:'json',
			complete:function(){
				$('#btnFormSave').removeAttr('disabled');
			},
			success : function(returnInfo) {
				eDialog.success('企业店铺成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
//							$(this).button('reset');
							window.location.href = 'grid';
				        }
					}]
				}); 
				//刷新缓存
			}
		});
		
	});
	
	$('#btnReturn').click(function(){
		history.back();
	});

	$('#shoplogoUpload').click(function(evt){
		busSelector.uploader({
			uploadUrl : GLOBAL.WEBROOT + '/company/shoplogoUpload',
			title : '店铺logo上传',
			fileSizeLimit : '2MB',
			imageMaxHeight : 200,
			imageMaxWidth : 300,
			imageMinHeight : 11,
			imageMinWidth : 11,
			checktype : 'single',
			callback : function(data){
				if(data && data.results && data.results.length > 0){
					$('#logoMongodbKey').val(data.results[0].fileId);
					$('#imgPreview1').attr('src',data.results[0].url);
				}
			}
		}, evt);
	});
});

var ShopShipAdd = {
		getSeniorParam : function(){
			var param = "[";
			$("#seniorTempList").find('tr').each(function(){
				$this = $(this);
				var _firstObj = $this.find('input[name="first"]');
				var first = _firstObj.val();
				var _pricingModeObj = $this.find('select[name="pricingMode"]');
				var pricingMode = _pricingModeObj.val();
				var _secondObj = $this.find('input[name="second"]');
				var second = _secondObj.val();
				var _freeObj = $this.find('input[name="free"]');
				var free = _freeObj.val();
				var _secondFreeObj = $this.find('input[name="secondFree"]');
				var secondFree = _secondFreeObj.val();
				var _noFreeObj = $this.find('input[name="noFree"]');
				var noFree = _noFreeObj.val();
				var _areaCodeListObj = $this.find("input[id='area']");
				var areaCodeList = _areaCodeListObj.val();
				if(!_decimal.test(first)){
					
				}
				param += "{pricingMode:'"+pricingMode+"',firstAmount:'"+first+"',firstPrice:'"+free+"',continueAmount:'"+second+"'," +
						"continuePrice:'"+secondFree+"',freeAmount:'"+noFree+"',areaCodeList:"+areaCodeList+"},";
			});
			param += "]";
			return param;
		},

		getDefaultParam : function(){
			var param = "[";
			var $this = $("#defaultPei").find("tr");
			var pricingMode = $this.find('select[id="pricingMode"]').val();
			var first = $this.find('input[name="dfirst"]').val();
			var second = $this.find('input[name="dsecond"]').val();
			var free = $this.find('input[name="dfree"]').val();
			var secondFree = $this.find('input[name="dsecondFree"]').val();
			var noFree = $this.find('input[name="dnoFree"]').val();
			param += "{pricingMode:'"+pricingMode+"',firstAmount:'"+first+"',firstPrice:'"+free+"',continueAmount:'"+second+"'," +
				"continuePrice:'"+secondFree+"',freeAmount:'"+noFree+"',areaCodeList:[]},";
			param += "]";
			return param;
		},
};
