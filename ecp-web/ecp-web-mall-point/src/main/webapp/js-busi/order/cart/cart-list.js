//页面初始化模块
$(function(){
	//页面业务逻辑处理内容
	var pInit = function(){
		var init = function(){
			//====================================================对象定义（Start）============================================================
			//统计总金额
			var calcTotalAmount = function(){
				var scoreTotal = 0;
				var baseTotal = 0;
				$('span.itemScoreCount',$('#cartList')).each(function(i,row){
					var box = $(row).closest('.item-body');
					var check = $('.itemCheck',$(box));
					if($(check).prop('checked')){
						var num = Number($(this).text());
						scoreTotal += num;
					}
				});
				$('span.itemBasePriceCount',$('#cartList')).each(function(i,row){
					var box = $(row).closest('.item-body');
					var check = $('.itemCheck',$(box));
					if($(check).prop('checked')){
						var num = Number($(this).text());
						baseTotal += num;
					}
				});
				$('#cartScoreConut').text(scoreTotal);
				$('#cartBaseAmoutConut').text(ebcUtils.thousandSeparator(ebcUtils.numFormat(baseTotal, 2)));
			};
			//逐行统计单项金额(不区分是否被选中)，并统计总金额(选中项目的总金额)
			var doAllCalc = function(){
				$('#cartList .item-body').each(function(i,n){
					calcItem($(this),null,false);
				});
				calcTotalAmount();
			};
			//统计已选中商品个数
			var checkItemCount = function(){
				var itemCount = 0;
				$('#cartList :checkbox:checked').each(function(i,row){
					var box = $(this).closest('.item-body');
					var check = $('.itemCheck',$(box));
					if($(check).prop('checked')){
						var count = Number($('.itemNumber',box).val());
						itemCount += count;
					}
				});
				$('#selectItemCount').text(itemCount);
			};
			 
			//计算单项金额
			//obj操作对象区域
			//srcNum：源数量
			var calcItem = function(obj,srcNum,modify){
				var success = true;
				var box = $(obj).hasClass('item-body')?obj:$(obj).closest('.item-body');
				
				var setPriceCount = function(score,basePrice,number){
				var scoreVal = ebcUtils.numFormat(accMul(score,number),0);
				var basePriceVal = ebcUtils.numFormat(accMul(basePrice,number), 2);
				$(box).find('span.itemScoreCount').text(scoreVal);
				$(box).find('span.itemBasePriceCount').text(basePriceVal);
				};
				var score = Number($(box).find('span.score').text());
				var basePrice = Number($(box).find('span.basePrice').text());
				var num = Number($(box).find('input.itemNumber').val());
				if(!num) $(box).find('input.itemNumber').val(0);
				//修改后台购物车数量
				if(modify){
					if(num > 0){
						var data = collectPromInfo(obj,false);
						data.ordCartItem.id = $('#itemId',$(box)).val();
						data.ordCartItem.staffId =  $('#staffId').val();
						data.ordCartItem.cartId = $('#itemCartId',$(box)).val();
						data.ordCartItem.orderAmount = num;
						var params = ebcUtils.serializeObject(data);
						$.eAjax({
							url : GLOBAL.WEBROOT + '/order/updateCartItemNumber',
							async :false,//同步请求
							data : params,
							success : function(returnInfo){
								setPriceCount(score,basePrice,num);
								success = true;
							},
							exception : function(){
								success = false;
							}
						});
					}
				}else{
					setPriceCount(score,basePrice,num);
				}
				
				//恢复原数量
				//if(!success && srcNum) $(box).find('input.itemNumber').val(srcNum);
				return success;
			};			
			//计算单项的总金额并统计总金额
			//obj：操作对象
			//modify：是否为编辑模式
			var calcItemAmount = function(obj,modify){
				if(calcItem(obj,null,modify)){
					calcTotalAmount();
					checkItemCount();
				}
			};
			//收集明细优惠信息
			//unchange ：true[删除、不选择]，false[修改数量、修改促销]
			//shop：店铺级别的处理
			var collectPromInfo = function(obj,unchange,shopDo){
				var shop = $(obj).hasClass('car-item')?obj:$(obj).closest('.car-item');
				var shopPromId = $('#shopTempPromId',$(shop)).val();
				if(!shopPromId) shopPromId = -1;
				var prom = {
					source : $('#websiteSource').val(),
					countryCode : '',
					provinceCode : '',
					cityCode : '',
					ordCartItem : {},
					ordCartChg : {
						'rOrdCartCommRequest' : {
							id : $('#shopCartId',$(shop)).val(),
							promId : shopPromId,
							ordCartItemCommList : []
						},
						'rOrdCartItemCommRequest' : {}
					}
				};
				//设置选中的商品信息
				$('.item-list',$(shop)).each(function(i,row){
					if($('.itemCheck',$(row)).prop('checked')){
						prom.ordCartChg.rOrdCartCommRequest.ordCartItemCommList.push({
							id : $('#itemId',$(row)).val(),
							promId : $('#itemPromId',$(row)).val()
						});
					}
				});
				//修改数量、修改促销
				if($.type(unchange)!='undefined' && !unchange && !shopDo){
					var item = $(obj).closest('.item-list');
					prom.ordCartChg.rOrdCartItemCommRequest.id = $('#itemId',$(item)).val();
					var promId = $('#itemTempPromId',$(item)).val();
					if(!promId){
						prom.ordCartChg.rOrdCartItemCommRequest.promId = -1;//取不到优惠ID时，传递-1参数
					}else{
						prom.ordCartChg.rOrdCartItemCommRequest.promId = promId;
					}
				}
				return prom;
			};
			
			//删除当前明细
			var deleteCurrentItem = function(obj){
				var box = $(obj).closest('.item-body');
				var data = collectPromInfo(obj,true);
				var itemId = $('#itemId',$(box)).val();
				data.ordCartItem.id = itemId;
				data.ordCartItem.staffId =  $('#staffId').val();
				data.ordCartItem.cartId = $('#itemCartId',$(box)).val();
				//移除当前需要删除的数据项
				if(data.ordCartChg.rOrdCartCommRequest.ordCartItemCommList &&
						$.isArray(data.ordCartChg.rOrdCartCommRequest.ordCartItemCommList) &&
						data.ordCartChg.rOrdCartCommRequest.ordCartItemCommList.length > 0){
					var tmpArr = data.ordCartChg.rOrdCartCommRequest.ordCartItemCommList;
					$.each(tmpArr,function(i,row){
						if(row && row.id == itemId){
							tmpArr.splice(i,1);
							return false;
						}
					});
					data.ordCartChg.rOrdCartCommRequest.ordCartItemCommList = tmpArr;
				}
				
				var params = ebcUtils.serializeObject(data);
				$.eAjax({
					url : GLOBAL.WEBROOT + '/order/deleteCartItem',
					async :false,//同步请求
					data : params,
					success : function(returnInfo){
						$(obj).closest('.item-list').remove();
						refreshAmount();
					}
				});
			};
			//检查当前购物车是否还有项目，若没有，则显示无项目
			var checkAvailableItem = function(){
				$('.car-item').each(function(i,row){
					if($('.item-list',$(row)).size()==0) $(row).remove();
				});
				if($('.car-item').size() == 0) $('#nullList').show();
			};
			//将数字转换为金额，默认返回的数字单位为分，必须除以100，并格式化两位数字
			var numberToPrice = function(num){
				return ebcUtils.numFormat(accDiv(num,100), 2);
			};
			 
			//刷新金额
			var refreshAmount = function(){
				//检查当前有效商品，无则显示购物车无商品
				checkAvailableItem();
				//统计当前所有物品的总数量
				checkItemCount();
				//总金额统计
				doAllCalc();
			};
			//提交预订单审核
			var checkCar = function(obj){
				if(!$(obj).hasClass('gobtn-grad')){
					$.eAjax({
						url:GLOBAL.WEBROOT + '/order/build/checkcar',
						data:ebcForm.formParams($("#cartForm")),
						success:function(returnInfo){
							//$.gridUnLoading();
							if(returnInfo&&returnInfo.resultFlag=='ok'){
								window.location.replace(GLOBAL.WEBROOT + '/order/build/preord/'+returnInfo.mainHashKey);
							}else{
								$(obj).removeClass('gobtn-grad');
								eDialog.alert(returnInfo.resultMsg,function(){
									window.location.replace(GLOBAL.WEBROOT + '/order/cart/list');
								},'error');
							}
						}
					});
				}
				
				//禁止多点
				//按钮置灰事件失效
				$(obj).addClass('gobtn-grad');
			};
			//====================================================对象定义（End）============================================================
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			//====================================================事件绑定（Start）============================================================
		//	$('#selectAllItem').bSelectAll('#cartList');
			$('#selectAllItem').on('click',function(e){
				e.stopPropagation();
				//checkItemCount();
				//calcTotalAmount();
				//shopCount();
				refreshAmount();
			});
			//选中商品
			$('#cartList :checkbox').on('click',function(e){
				e.stopPropagation();
				var _this = this;
				//checkItemCount();
				//calcTotalAmount();
				//shopCount();
				if(!$(_this).prop('checked') && $(':checked',$(this)).size()==0){
					refreshAmount();
				}else{
					var box = $(_this).closest('.item-body');
					var data = collectPromInfo(_this,true);
					var params = ebcUtils.serializeObject(data);
					$.eAjax({
						url : GLOBAL.WEBROOT + '/order/checkShopItem',
						async :false,//同步请求
						data : params,
						success : function(returnInfo){
							refreshAmount();
						}
					});
				}
			});
			
			//减少购物车数量
			$('.itemNumberMinus').on('click',function(){
				var ipt = $(this).next('.itemNumber');
				var num = Number(ipt.val());
				if(num > 1){
					ipt.val(--num);
					calcItemAmount(this,true);
				}
			});
			
			//增加购物车数量
			$('.itemNumberAdd').on('click',function(){
				var ipt = $(this).prev('.itemNumber');
				var num = Number(ipt.val());
				ipt.val(++num);
				calcItemAmount(this,true);
			});
			
			//输入框修改购物车数量
			$('.itemNumber').on('keypress',function(e){
				return ebcUtils.checkNum(e, this);
			}).on('blur',function(){
				if(!$(this).val() || Number($(this).val()) < 1) $(this).val(1);
				calcItemAmount(this,true);
			});
			
			//删除指定行物品
			$('#delCheckedItem').click(function(){
				eDialog.confirm("确定要删除指定的物品吗？", {
					buttons : [{
						caption : '确定',
						callback : function(){
							$('.item-list :checked').closest('.item-list').remove();
							refreshAmount();
						}
					},{
						caption : '取消',
						callback : $.noop
					}]
				});
			});
			//删除当前行物品
			$('.deleteCurrentItem').click(function(e){
				e.stopPropagation();
				var _this = this;
				eDialog.confirm("确定要删除指定的物品吗？", {
					buttons : [{
						caption : '确定',
						callback : function(){
							deleteCurrentItem(_this);
						}
					},{
						caption : '取消',
						callback : $.noop
					}]
				});
			});
			
			//去结算
			$('#goCheck').click(function(){
				var carNum = $("#carNum").val();
				if(carNum == '0'){
					checkCar(this);
				}else{
					eDialog.alert('购物车没有有效商品',function(){
						window.location.href = GLOBAL.WEBROOT + '/order/cart/list';
					});
				}
			});
			
			$(".purchaseAmount").on('keyup',function(e){
				ladderLimitInputNum(this);
				e.preventDefault();
			});
			/**
        	 * 限制输入框输入的数字只能为正整数
        	 * @param obj
        	 */
        	var ladderLimitInputNum = function(obj){
        		var input=$(obj);
        		if(input.attr("gdsTypeId")=='2'){
        			input.val(1);
        			return;
        		}
        	};
			//====================================================事件绑定（End）============================================================
			
			
			 
			//====================================================初始化（Start）============================================================
			$('#selectAllItem').click();
			//refreshAmount();
			//====================================================初始化（End）============================================================
			document.title = '我的购物车-积分商城';
		};
		
		return {
			init : init
		};
	};
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bPlugin','bForm'],
		//指定页面
		init : function(){
			var ppp = new pInit();
			ppp.init();
		}
	});
});