$(function() {

	//页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			$('#myTab a').click(function(e) {
				e.preventDefault();
				$(this).tab('show');
			});

			var promtab = {
				//返回
				goBack : function(_b) {
					//disabled 保存 和提交
					$('#btnFormSave').attr('disabled', "true");
					$('#btnFormSubmit').attr('disabled', "true");

					var _backId = $('#doAction').val();
					if (_backId == 'copy' || _backId == 'edit') {
						window.location.href = GLOBAL.WEBROOT + '/seller/myprom';
						return;
					}
					//新增返回上一页
					if (_backId == 'create' || _backId == '' || _backId == null
							|| _backId == "undefined") {
						window.location.href = GLOBAL.WEBROOT + '/seller/createprom/ct/'+$('#shopId').val();
						return;
					}
					history.go(-1);
				},
				//判断是否为数字
				isNum : function(s) {
					if (s != null && s != "") {
						return !isNaN(s);
					}
					return false;
				},
				//优惠规则 取值 
				getDiscountRuleData : function(s) {
					var _data = new Object();
					_data.value = false;//返回false;

					var _result = new Array();//返回值列表设置

					//优惠规则
					var discountRule = ebcForm.formParams($("#discountRuleForm"));

					for ( var index in discountRule) {
						_result.push(discountRule[index]);
					}

					var _drData = new Object();
					//统一调用方式
					try {
						//验证是否存在
						if (typeof (discountRuleFun) == "undefined") {
							_drData.value = true;
						} else {
							//调用方法
							_drData = discountRuleFun.valid();
						}
					} catch (e) {
						_drData.value = true;
					}
					//为1000000008 促销类型 需要验证
					if ($('#promTypeCode').val() == '1000000008') {

						_drData = discountRuleFun.valid();
					}
					//为1000000011 促销类型 需要验证
					if ($('#promTypeCode').val() == '1000000011') {

						_drData = discountRuleFun.valid();

					}
					//验证不通过
					if (!_drData.value) {
						_data.value = false;
						return _data;
					} else {
						var _drResult = _drData.result;
						for ( var index in _drResult) {
							_result.push(_drResult[index]);
						}
					}

					_data.value = true;
					_data.result = _result;
					return _data;

				},
				//保存 提交公共代码
				save : function(s) {
					 //bug 只有当前页才可以验证 待修改
			 		   if(!$("#promInfoForm").valid())return false;
			 		   
			 		   if($("#discountRuleForm") && $("#discountRuleForm").length>0){
				 			  if(!$("#discountRuleForm").valid())return false;
			 		   }			 		   
			 		   if($("#promRuleForm") && $("#promRuleForm").length>0){
			 			  if(!$("#promRuleForm").valid())return false;
			 		   }

			 		  //促销基本信息
				 	      var data= ebcForm.formParams($("#promInfoForm"));
				 	      
				 	      //参与范围为部分参与时，商品必需选择
				 	      
				 	      var _joinRange=$("input[name='promInfoVO.joinRange']:checked").val();
				 	      
				 	      var _promTypeCode=$("#promTypeCode").val();
					//促销类型1000000013  1000000014  1000000016可不用填写参与的分类或者商品
					if ("0" == _joinRange) {
			 	    	  
			 		      //促销类型为单品时，商品必需选择
			 		      var _l=$("#gdsTable tbody tr td input[ othername='checkboxgds']");
			 		      
			 		     //促销类型为单品时，分类选择
			 		      var _catgList=$("#catgTable tbody tr td input[ name='checkboxcatg']");
			 		      
			 		      if(_l.length<=0 && _catgList.length<=0){
			 		    	 /*   if($('#ifCheckGds').val()!="true"){
				 		    	   	eDialog.alert('请选择需要参与促销的商品 或者 分类',null);
				 		    		return ;
			 		    	    }*/
			 		      }else{
			 		    		 if(_l.length>0 ){
				 		    		  //商品选择
						 		    	  var _promCnt=null;
						 		    	  var _isNeedStock=null;
						 		    	  for(var i=0;i<_l.length;i++){
						 		    		 var gdsMapValue = "";
						 		    		  //验证是否为有效数字
						 		    		  _promCnt=$('#promCnt-'+_l[i].id).val();//库存数量取值
						 		    		 _isNeedStock=$('#promCnt-'+_l[i].id).attr("isNeedStock");
						 		    		 //实体需要验证库存
						 		    		  if(_isNeedStock=="true"){
						 		    			  if(!this.isNum(_promCnt)){
							 		    			  
							 		    			    eDialog.alert('优惠规则，商品编码-单品编码：'+_l[i].name +" 对应的促销量，请输入有效的数字   "+_promCnt+" ！",null); 
							 		    				return ;
							 		    		  }  
						 		    		  }else{
						 		    			  //虚拟不需要验证
						 		    			 _promCnt=0;
						 		    		  }
						 		    		 gdsMapValue += _promCnt+"-";
							 		    		//处理秒杀促销情况
							 		    		if($('#promTypeCode').val() == "1000000019"){
								 		    		 var _seckillPrice = null;
								 		    		 var _priceType = null;//'促销价格类型：0折扣率；1固定价格'
								 		    		_priceType = $('#seckillPriceType-'+_l[i].id).val();
						 		    			    if(!this.isNum(_priceType)||(_priceType!="0"&&_priceType!="1")){
							 		    			  
							 		    			    eDialog.alert('商品编码-单品编码：'+_l[i].name +"请输入有效的数字  【 0:折扣率 】或者【1：固定价格】！",null); 
							 		    				return ;
							 		    		    }  
									 		    	if(!(_seckillPrice>0)){
							 		    			    eDialog.alert('商品编码-单品编码：'+_l[i].name +" 秒杀价格必须大于0！",null); 
													  	return;
									 		    	}
									 		    	_seckillPrice = $('#seckillPrice-'+_l[i].id).val();//秒杀价
									 		    	if(!promCheck.priceNumber(_seckillPrice))
									 		    	{
							 		    			    eDialog.alert('商品编码-单品编码：'+_l[i].name +" 秒杀价最多保留两位小数  【"+_seckillPrice+" 】！",null); 
													  	return;
									 		    	}
									 		    	if(_priceType=="0"&&_seckillPrice>100){
							 		    			    eDialog.alert('商品编码-单品编码：'+_l[i].name +" 秒杀价格类型为【0：折扣率】时，折扣率最大值不能超过【100】！",null); 
													  	return;
									 		    	}
									 		    	gdsMapValue += _priceType+"-";
									 		    	gdsMapValue += _seckillPrice+"-";
							 		    		}					 		    	
						 		    		  data.push({'name':'gdsMap['+_l[i].id+']','value':gdsMapValue});
						 		    	  }
						 		      }
			 		    	  if(_catgList.length>0 ){
			 		    		 //分类选择
				 		    	  for(var i=0;i<_catgList.length;i++){
				 		    		  data.push({'name':'catgMap['+_catgList[i].id+']','value':_catgList[i].id});
				 		    	  }
			 		    	  }
			 		      }
			 	      }
					//黑名单开启
					if ($("#ifBlackList").prop('checked')) {
			 		      //促销类型为单品时，商品必需选择
			 		      var _blackGds=$("#gdsBlackTable tbody tr td input[ name='checkboxgds']");
			 		      
			 		      var _catgBlackList=$("#catgBlackTable tbody tr td input[ name='checkboxcatg']");
			 		     
			 		      if(_blackGds.length<=0 &&_catgBlackList.length<=0){
			 		    		eDialog.alert('请选择需要设置黑名单的商品或分类',null); 
			 		    		return ;
			 		      }
			 		      if(_blackGds.length>0){
			 		    	  for(var i=0;i<_blackGds.length;i++){
			 		    		  data.push({'name':'gdsBlackMap['+_blackGds[i].id+']'});
			 		        }
			 		      }
			 		     if(_catgBlackList.length>0){
			 		    	  for(var i=0;i<_catgBlackList.length;i++){
			 		    		  data.push({'name':'catgLimitMap['+_catgBlackList[i].id+']'});
			 		        }
			 		      }
			 	      }

					//如果购买次数、数量为 每天、月、年 需要必填次数、数量
			 	      var timesLimit=$("input[name='promRuleVO.limitTimesType']:checked").val();
					if (timesLimit == '1' || timesLimit == '2'|| timesLimit == '3') {
			 	    	  var limitTimesTypeValue=$('#limitTimesTypeValue').val();
			 	    	  if(!limitTimesTypeValue){
			 	    			eDialog.alert('促销规则-请设置购买次数',null);
			 	    			return ;
			 	    	  }
			 	    	  if(limitTimesTypeValue<=0){
				    			eDialog.alert('促销规则-购买次数不能小于0或者等于0',null);
				    			return ;
				    	  }
			 	  	     //不是正整数
				 			 if( !ebcCheck.isInt(limitTimesTypeValue)){
				 				eDialog.alert('促销规则-购买次数不是正整数',null);
									return ; 
				 			 }
			 	      }
					 var totalLimit=$("input[name='promRuleVO.limitTotalType']:checked").val();
					if (totalLimit == '1' || totalLimit == '2'|| totalLimit == '3') {
		 		    	  var limitTotalTypeValue=$('#limitTotalTypeValue').val();
		 		    	  if(!limitTotalTypeValue){
		 		    			eDialog.alert('促销规则-请设置购买数量',null);
		 		    			return ;
		 		    	  }
		 		    	  if(limitTotalTypeValue<=0){
		 		    			eDialog.alert('促销规则-购买数量不能小于0或者等于0',null);
		 		    			return ;
		 		    	  }
		 		    	   //不是正整数
		 	 			 if( !ebcCheck.isInt(limitTotalTypeValue)){
		 	 				eDialog.alert('促销规则-购买数量不是正整数',null);
		 						return ; 
		 	 			 }
		 		      }
					 //最大值 最小值验证
			 		   var minBuyCnt=$('#minBuyCnt').val();
			 		   var maxBuyCnt=$('#maxBuyCnt').val();
			 		   
					if (maxBuyCnt && minBuyCnt) {
			 			   if(minBuyCnt<=0){
			  				  eDialog.alert('促销规则-每次最小购买量不能小于0或者等于0',null);
			 					return ;
			  			   }
			  			   if(maxBuyCnt<=0){
			   				  eDialog.alert('促销规则-每次最大购买量不能小于0或者等于0',null);
			  					return ;
			   			   }
			  			   //不是正整数
			  			 if( !ebcCheck.isInt(minBuyCnt)){
			  				  eDialog.alert('促销规则-每次最小购买量不是正整数',null);
			 					return ; 
			  			 }
			  			   //不是正整数
			  			 if( !ebcCheck.isInt(maxBuyCnt)){
			  				  eDialog.alert('促销规则-每次最大购买量不是正整数',null);
			 					return ; 
			  			 }
			  			   if(maxBuyCnt<minBuyCnt){
			  				   eDialog.alert('促销规则-每次最小购买量大于每次最大购买量',null);
			  					return ;
			  			   }
			  		   }
					 //优惠规则
			 	    var _discountData=this.getDiscountRuleData();
					//验证不通过
					if (!_discountData.value) {
						return;
					} else {
			   	    	  //设置优惠信息
			   	    	  var _discountReuslt=_discountData.result;
			   	    	  
			   	    	  for(var index in _discountReuslt)  
			   		      {  
			   	    		data.push(_discountReuslt[index]);
			   		      }
			   	      }

					 //促销规则
			 	      var promRule= ebcForm.formParams($("#promRuleForm"));
					for ( var index in promRule) {
						data.push(promRule[index]);
					}

					 //disabled 保存 和提交
			 	      $('#btnFormSave').attr('disabled',"true"); 
			 	      $('#btnFormSubmit').attr('disabled',"true"); 
					//ajax请求
					$.eAjax({
		     			url : GLOBAL.WEBROOT + "/seller/createprom/"+s,
		     			data : data,
		     			success : function(returnInfo) {
		     				//成功返回
		     				if(returnInfo.resultFlag=='ok'){
		     					//history.go(-1);
		     					 promtab.goBack();
		     				//数据验证错误	
		     				}else if(returnInfo.resultFlag=='fail'){
		     					//enable  保存 和提交
			     				$('#btnFormSave').removeAttr("disabled");
			     				$('#btnFormSubmit').removeAttr("disabled");  
		     					eDialog.alert(returnInfo.resultMsg,null);
		     				}else{
		     					//enable  保存 和提交
			     				$('#btnFormSave').removeAttr("disabled");
			     				$('#btnFormSubmit').removeAttr("disabled");  
		     					eDialog.alert(returnInfo.resultMsg,null);
		     				}
		     			},
		     			exception : function(returnInfo) {
		     				//enable  保存 和提交
		     				$('#btnFormSave').removeAttr("disabled");
		     				$('#btnFormSubmit').removeAttr("disabled");  
		     			}
		     		});

				}
			}
			
			//保存
			$('#btnFormSave').unbind("click");
			$('#btnFormSave').click(function() {
				promtab.save("save");
			});

			//提交
			$('#btnFormSubmit').unbind("click");
			$('#btnFormSubmit').click(function() {
				promtab.save("submit");
			});
			//返回
			$('#btnReturnTab').click(function() {
				//window.location.href=GLOBAL.WEBROOT+'/createprom';
				promtab.goBack();
			});

		};

		return {
			init : init
		};
	};
	pageConfig.config({
		//指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm' ],
		//指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});

});