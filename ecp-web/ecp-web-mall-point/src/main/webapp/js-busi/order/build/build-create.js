//页面初始化模块
$(function(){
	//页面业务逻辑处理内容
	var pInit = function(){
		var init = function(){
			/**=================
			 * 定义常量
			 * =================
			 */
			var build_create = {
				/**
				 * 构建订单的常量
				 */
				constant : {
					ORDCARTLIST:'ordCartList',
					ORDITEMLIST:'ordCartItemList',
					ADDR_DEF:'xxxx',
					MORE_ADDR_START:2,
					ADDR_MAX_NUM:20,
					COMMON_INVOICE_TYPE:0,
					TAX_INVOICE_TYPE:1,
					ENTITY_TYPE:1,
					SL_ADDR_MAX:10
				}
			};
			
			/**=================
			 * 定义对象
			 * =================
			 */
			
			
			/**=================================================================================
			 * 设计思路在于，1、放初始化数据的位置；2、联动变更之后的位置；3、以店铺为单元的统计位置；4、最终的合计；
			 * =================================================================================
			 */
			
			//修改realMoney
			var updateRealMoney = function(obj){
				//资金账户
				var _shop = $(obj).closest('._shop')?$(obj).closest('._shop'):$(obj);
				var _deduct = 0;
				_shop.find('input[class="_acctInfo_deduct"]').each(function(){
					_deduct = _deduct + Number($(this).val());
				});
				//固定订单金额
				var _orderMoney = _shop.find('input[class="_orderMoney"]').val();
				//联动之后的运费
				var _realExpressFees = _shop.find('._realExpressFees').val();
				//满减返现
				var _shopDiscounts = _shop.find('._shopDiscounts').val();
				var _shopMoney = Number(_shopDiscounts);
				
				var oldReal = Number(_orderMoney)+Number(_realExpressFees);
				_shop.find('._realMoney').val(oldReal-_deduct-_shopMoney);
				
			}
			
			//真实金额初始化统计
			var RealMoneyInit = function(){
				$('.sendmd a').each(function(){
					if($(this).hasClass('checked')){
						$(this).trigger('click');
					}
				});
			}
			
			//店铺返现计算
			var totalDiscount = function(){
				
				var shopDiscounts = 0;
				
				$('._shop').each(function(){
					var _this = this;
					//店铺满减返现
					var _showDiscountMoney = Number($(this).find('._shopPromDiscountMoney').val());
					
					//明细减免
					var _itemPromDiscountMoney = 0;
					$(_this).find('._itemPromDiscountMoney').each(function(){
						_itemPromDiscountMoney += Number($(this).val());
					});
					
					//明细价格减免
					var _itemPromDiscountPriceMoney = 0;
					$(_this).find('._itemPromDiscountPriceMoney').each(function(){
						_itemPromDiscountPriceMoney += Number($(this).val());
					});
					var shopMoney = _showDiscountMoney+_itemPromDiscountMoney+_itemPromDiscountPriceMoney;
					
					$('._shopDiscounts',$(this)).val(shopMoney);
				});
				
			}
			
			//获取减免
			var shopDiscountMoneyCount = function(obj){
				$('._shop').each(function(){
					var _shop = $(this);
					//小店统计
					//店铺减免只有一个金额
					var _shopPromDiscountMoney = Number(_shop.find('._shopPromDiscountMoney').val());
					//明细减免有多个金额
					var _itemPromDiscountMoney = 0;
					_shop.find('._itemPromDiscountMoney').each(function(){
						_itemPromDiscountMoney += Number($(this).val());
					});
					var shopDiscountMoney = _shopPromDiscountMoney+_itemPromDiscountMoney;
					//没有金额优惠，隐藏展示
					if(shopDiscountMoney == 0) _shop.find('.shopDiscount').parent().hide();
					_shop.find('.shopDiscount').text(ebcUtils.numFormat((_shopPromDiscountMoney+_itemPromDiscountMoney)/100,2));
				});
			}
			
			//计算总价
			var totalCount = function(){
				//店铺总的返现满减统计
				var shopDiscounts = 0;
				$('._shopDiscounts').each(function(){
					shopDiscounts += Number($(this).val());
				});
				
				
				
				//计算真实总金额
				var total = 0; 
				$('input._realMoney').each(function(){
					total += Number($(this).val());
				});
				
				//计算总运费
				var real_express_fees = 0;
				$('input._realExpressFees').each(function(){
					real_express_fees += Number($(this).val());
				});
				
				//计算资金账户费用
				var acct_fees = 0;
				$('._acctInfo_deduct').each(function(){
					acct_fees += Number($(this).val());
				});
				//为提交订单的隐藏域值
				$('input[name="realMoney"]').val(total);
				$('input[name="realExpressFee"]').val(real_express_fees);
				
				//展示金额
				$('#all_money').html("&yen;"+ebcUtils.numFormat((total)/100, 2));
				$('#real_express_fees').html("&yen;"+ebcUtils.numFormat(real_express_fees/100, 2));
				$('#shop_discounts').html("- &yen;"+ebcUtils.numFormat(Number(shopDiscounts)/100, 2));
				$('#acct_fees').html("- &yen;"+ebcUtils.numFormat(acct_fees/100, 2));
				$('.total').html("&yen;"+ebcUtils.numFormat((total)/100, 2));
				
			}
			
			/**
			 * 提交订单
			 */
			var submitOrd = function(obj){
				//截止地址
				//if($('#_gdsType').val().indexOf('1')!=-1){
				//	if($('#cust_addr .sl-addr').length == 0){
				//		eDialog.alert('收货地址不允许为空');
						//禁止多点
						//按钮置灰事件失效
						//$(obj).addClass('gobtn-grad');
					//}
				//}
				if(!$(obj).hasClass('gobtn-grad')){
					$.eAjax({
						url:GLOBAL.WEBROOT + '/order/build/submitOrd',
						data:ebcForm.formParams($("#submitForm")),
						success:function(returnInfo){
							if(returnInfo&&returnInfo.resultFlag == 'ok'){
								if($('._shop').length > 1){
									window.location.replace(GLOBAL.WEBROOT + '/pay/orderList/'+returnInfo.onlineKey);
								}else{
									window.location.replace(GLOBAL.WEBROOT + '/pay/way?onlineKey='+returnInfo.onlineKey);
								}
							}else{
								//去掉样式 按钮可点击
								$(obj).removeClass('gobtn-grad');
								eDialog.error(returnInfo.resultMsg);
							}
						}
					});
				}

				//禁止多点
				//按钮置灰事件失效
				$(obj).addClass('gobtn-grad');
				
			}
			
			/**
			 * 电话隐藏
			 */
			var hidePhone = function(phone){
				var newphone = phone;
				if(phone.length == 11){
					var head = phone.substring(0,3);
					var mid = "****";
					var tail = phone.substring(7,12);
					newphone = head+mid+tail;
				}
				return newphone;
			}
			
			/**
			 * 地址
			 */
			var buildAddr = function(data){
				var addrstr = data.chnlAddress;
            	
            	var finaladdr = data.contactName+'  '+data.cityCodeStr+'  '+data.countyCodeStr+'  ';
            	var addrlength = build_create.constant.ADDR_MAX_NUM;
            	if(addrlength<addrstr.length){
            		finaladdr = finaladdr + addrstr.substr(0,addrlength)+'... ';
            	}else{
            		finaladdr = finaladdr + addrstr;
            	}
            	var myaddr =  finaladdr+'&nbsp;&nbsp;'+hidePhone(data.contactPhone);
            	return myaddr;
			}

            /**=================*
			 * 定义对象
			 * =================*/
			 
			
			/**=========================---绑定事件---========================**/
			//资金账号展开折叠
            $('.bill-toggle .toggle-hd').click(function(){
                $(this).closest('.bill-toggle').toggleClass('toggle-down');
            });
		    
			//地址联动
            $('#cust_addr .order-switch a').click(function(){
            	var _this = this;
            	$(_this).find('input').attr('checked','checked');
            	$(_this).closest('.sl-addr').siblings().each(function(){
            		$(this).find('input').removeAttr('checked');
            	});
            });
            
			//运费联动
			$('.sendmd a').click(function(){
				//选中设置
				$(this).siblings().each(function(){
					$(this).find('input').removeAttr('checked');
				});
				
				var clazz = $(this).attr('class');
				var flag = clazz.indexOf('zt')!=-1;
				$(this).closest('._deliver_type').each(function(){
					
					//计算总运费
					var real_express_fees = $(this).find('input._realExpressFee').val();
					//当时运费
					var _realExpressFeeNum = $(this).find('input._realExpressFee').val();
					var _realExpressFee = Number(_realExpressFeeNum);
					
					//自提，消减邮费
					var reals = Number(real_express_fees);
					if(flag)reals =reals - _realExpressFee;
					
					//统计到内部单元当中
					$(this).find('input._realExpressFees').val(reals);
					
				});
				
				//更新真实金额
				updateRealMoney(this);
				
				//刷新统计以及页面展示
				totalCount();
			});
			
			/**=============================================
			 * 资金账号信息更改
			 * =============================================
			 */
			$('._acctInfo').each(function(){
				var _this = this;
				$(this).find('._deduct').on('keypress',function(e){
					//只能输入数字
					return ebcUtils.checkNum(e,this,false);
				});
				$(this).find('._deduct').on('blur',function(){
					//最大值
					var max = $(this).closest('.item-input').find('._deductOrderMoney').val();
					var num = $(this).val();
					if(Number(num)>Number(max)/100){
						$(this).val(ebcUtils.numFormat((Number(max))/100, 2));
					}else{
						$(this).val(ebcUtils.numFormat(num,2));
					}
				});
				
				//点击使用
				$(this).find('._useacct').click(function(){
					var _itemput = $(this).closest('.item-input');
					var _use = $(this).closest('.item-input').prev('._use');
					//使用金额
					var _useacctmoney = _itemput.find('._deduct').val();
					_useacctmoney = Number(_useacctmoney);
					//设置金额
					_use.find('._haveused').text(_useacctmoney);
					_itemput.toggle();
					_use.toggle();
					_itemput.find('input[class="_acctInfo_deduct"]').val(ebcUtils.numFormat(accMul(_useacctmoney,100),0));
					
					updateRealMoney(this);
					
					totalCount();
				});
				
				//取消使用
				$(this).find('.red').click(function(){
					$(this).closest('._use').toggle();
					var _itemput = $(this).closest('._use').next('.item-input');
					_itemput.find('._deduct').val('');
					_itemput.find('input[class="_acctInfo_deduct"]').val(0*100);
					_itemput.toggle();
					
					updateRealMoney(this);
					
					totalCount();
				});
			});



			/**
			 * 地址新增
			 */
			$('.pmropt').click(function(){
				if($('.body .sl-addr').length>=build_create.constant.SL_ADDR_MAX) {
					eDialog.alert('地址新增超出最大数量限制');
					return false;
				}else{
					bDialog.open({
						title : "新增收货人信息",
						width : 780,
						height : 400,
						url : GLOBAL.WEBROOT+'/order/build/buyeraddrnew',
						callback:function(returnInfo){

							if(returnInfo&&returnInfo.results){
								if(returnInfo.results[0].resultFlag == 'ok'){

									//模板
									var data = returnInfo.results[0].resultInfo;
									var _add = $('#addTemplate .sl-addr').clone(true);
									_add.attr('id',data.id);
									_add.find('input').attr('value',data.id);

									_add.find('.sl-name').html(buildAddr(data));
									_add.find('b').before(data.contactName);

									//全部清空情况
									if($('.body .sl-addr:eq(0)').attr('id')==undefined){
										_add.find('a').addClass('checked');
										_add.find('input').attr('checked','checked');
										$('.body .addropt').before(_add);
									}else{
										//有剩余情况
										$('.body .sl-addr:eq(0)').after(_add);
									}
									//必须展开简单控制
									$('.body .sl-addr').attr('style','display:block');
								}else{
									eDialog.error('或系统异常');
								}

							}

						}
					});
				}
			});

			
			/**
			 * 修改地址
			 * wangxq
			 */
			$('._update').live('click',function(){
				var id = $(this).parents('.sl-addr').attr('id');
				var index = $('.sl-addr').index($(this).parents('.sl-addr'));
				var _addr = $(this).parents('.sl-addr');
				
		        bDialog.open({
		            title : "修改收货人信息",
		            width : 780,
		            height : 400,
		            params : {id:id},
		            url : GLOBAL.WEBROOT+'/order/build/buyeraddrupdate?id='+id,
		            callback:function(returnInfo){
		            	if(returnInfo&&returnInfo.results){
		            		if(returnInfo.results[0].resultFlag == 'ok'){
		            		//模板
		            		
			            		var data = returnInfo.results[0].resultInfo;
			                	var _add = $('#addTemplate .sl-addr').clone(true);
			                	_add.attr('id',data.id);
			                	_add.find('input').attr('value',data.id);
			                	if(_addr.find('input').attr('checked')!=undefined) _add.find('input').attr('checked','checked');
			                	if(_addr.find('a').hasClass('checked')) _add.find('a').addClass('checked');
			                	_add.find('.sl-name').html(buildAddr(data));
			                	_add.find('b').before(data.contactName);
			                	_add.show();
			                	$('.body .sl-addr:eq('+index+')').after(_add);
			                	_addr.remove();
		            		}else if(returnInfo.results[0].resultFlag == 'expt'){
		            			eDialog.alert('系统异常');
		            		}
		            	}
		            }
		        });
			});

			
			/**
			 * 更多地址展示
			 * 20151002
			 */
			$('.addropt').click(function(){
				//更多地址展示数量
				//更多地址展示数量
				var addrNum = $('.body .sl-addr').length;
				var showNum = $('.body .sl-addr').length;
				if(showNum >= 2){
					$('.sl-addr').each(function(){
						if($(this).css('display') == 'none') showNum --;
					});
				}
				if(addrNum <=2 || showNum ==0) {
					$('.body .sl-addr').show();
					return true;
				}
				$('.sl-addr').slice(build_create.constant.MORE_ADDR_START).toggle();
				$(this).find('i').addClass('micon-up');
				if('micon-up'==$(this).find('i').attr('class')){
					$(this).find('i').removeClass('micon-up');
					$(this).find('i').addClass('micon');
				}else{
					$(this).find('i').addClass('micon-up');
					$(this).find('i').removeClass('micon');
				}
			});
			
			/**
			 * 设置默认
			 */
			$('._default').live('click',function(){
				
				var id = $(this).parents('.sl-addr').attr('id');
				var _this = this;
				$.eAjax({
					url : GLOBAL.WEBROOT + "/custaddr/setaddr",
					data : [{name:'addrid',value:id},{name:"staffid",value:$('._staffId').attr('id')}],
					datatype:'json',
					success : function(returnInfo) {
						$(_this).closest('.sl-addr').siblings().find("._default").text('设为默认地址');
						$(_this).text('默认地址');
						if(returnInfo&&returnInfo.resultFlag=='ok'){
							eDialog.alert('设置默认地址成功');
						}else{
							eDialog.alert('系统异常');
						}
						
					}
				});
				
			});
			
			/**
			 * 选中地址
			 */
			$('.sl-addr .order-switch').live('click',function(){
				$(this).parent().siblings().find("a").removeClass('checked');
			});
			
			/**
			 * 删除地址 
			 */
			$('._delete').live('click',function(){
				var id = $(this).parents('.sl-addr').attr('id');
				var index = $('.sl-addr').index($(this).parents('.sl-addr'));
				var _delete = $(this).parents('.sl-addr');
				
				
				
				$.eAjax({
					data : [{name:'addrid',value:id},{name:"staffid",value:$('._staffId').attr('id')}],
					url : GLOBAL.WEBROOT + "/custaddr/deladdr",
					success : function(returnInfo) {
						if(returnInfo&&returnInfo.resultFlag == 'ok'){
							_delete.remove();
						}else{
							eDialog.alert('系统异常');
						}
					}
				});
			});

			
			/**
			 * 提交订单
			 */
			$('#submitOrd').click(function(){
				//$("#submitForm").submit();
				submitOrd(this);
			});
		    
			/**
			 * 发票信息联动
			 */
		    $('.pay-bill #billModify').click(function(){
		    	//this变动记录当前this
		    	var _this = $(this).closest('.pay-bill');
		    	bDialog.open({
					title : '发票信息设置',
					width : 680,
					height :600,
					url : GLOBAL.WEBROOT + '/order/build/billInfoInit',
					callback:function(data){
						if(data && data.results && data.results.length > 0 ){
							var bill = data.results[0];
							_this.find('#billTypeText').text(bill.billTypeName);
							_this.find('#billUnitText').text(bill.billTitle);
							_this.find('#billContentText').text(bill.billContent);
							
							//发票类型
							_this.find('#mainBillType').val(bill.billType);
							
							_this.find('#billType').val(bill.billType);
							_this.find('#billTitle').val(bill.billTitle);
							_this.find('#billContent').val(bill.billContent);
							
							_this.find('#billInvoiceTitle').val(bill.billTitle);
							_this.find('#billTaxpayerNo').val(bill.billTaxpayerNo);
							_this.find('#billContactInfo').val(bill.billContactInfo);
							_this.find('#billPhone').val(bill.billPhone);
							_this.find('#billBankName').val(bill.billBankName);
							_this.find('#billAcctInfo').val(bill.billAcctInfo);
							_this.find('#billVfsId2').val(bill.billVfsId2);
							_this.find('#billTakerName').val(bill.billTakerName);
							_this.find('#billTakerPhone').val(bill.billTakerPhone);
							_this.find('#billTakerProvince').val(bill.billTakerProvince);
							_this.find('#billTakerCity').val(bill.billTakerCity);
							_this.find('#billTakerCounty').val(bill.billTakerCounty);
							_this.find('#billTakerAddress').val(bill.billTakerAddress);
							_this.find('#billInvoiceContent').val(bill.billContent);
						}
					}
				});
		    });
		    
		    //店铺返现
		    shopDiscountMoneyCount();
		    
		    //统计店铺返现
			totalDiscount();
			
			//初始化统计=============================初始化vm==========================初始化js==============================
			RealMoneyInit();
			//初始化统计
			totalCount();
			
			//满减是否展示===========================静态展示不做动态变化======================================
			var _shopisShow = 0;
			$('._shopDiscounts').each(function(){
				if(Number($(this).val()) == 0){
					$(this).closest('._shop').find('._shop_discounts').hide();
				}
			});
			$('._deduct').decimalinput(2);  
		};
		
		
		/**=============================================
		 * 初始化  清理缓存+初始化数据 立即执行
		 * =============================================
		 */
		//清理资金账号缓存
		$('._acctInfo_deduct').val(0);
		$('._deduct').val('');
		//折扣清缓存
		$('._shopDiscounts').val('');
		//清理发票信息
		$('._bill_info').find('input[id="billTitle"]').val('个人');
		$('._bill_info').find('input[id="billContent"]').val('明细');
		//去商场展示条
		$('.navBg').hide();
		
		
		return {
			init : init
		};
	};
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bForm'],
		//指定页面
		init : function(){
			var ppp = new pInit();
			ppp.init();
		}
	});
});