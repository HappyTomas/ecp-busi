/**
 * Created by wang on 16/6/22.
 */

$(function(){


    var _init = true;


    //====================================================对象定义（Start）============================================================
    //订单常量
    var constants = {
        tmpPromId:-1,
        nopromtext:'不使用优惠'
    };

    //统计总金额
    //逐行统计单项金额(不区分是否被选中)，并统计总金额(选中项目的总金额)
    var doAllCalc = function(){
        $('#cartList .item-body').each(function(i,n){
            calcItem($(this),null,false);
        });

    };
    //统计已选中商品个数
    var checkItemCount = function(){

    };
    //设置店铺优惠信息是否显示
    var setPromView = function(shop,type){

    };

    //组合商品处理（一个）
    var groupCount = function(obj,modify){
        var mainbox = $(obj).hasClass('item-group')?obj:$(obj).closest('.item-group');
        //组合金额总计
        var groupPriceTotal = 0;
        var amount = $('.groupAmounts',$(mainbox)).val();
        if(!amount) {
            amount = 0;
            $('.groupAmounts',$(mainbox)).val(amount);
        }
        $('.group-body',$(mainbox)).each(function(i,row){
            $('span.groupItemAmount',$(row)).text(amount);
            var itemPrice = $('span.groupItemPrice',$(row)).text();
            //明细金额小计
            var itemPriceCount = accMul(Number(itemPrice),amount);
            groupPriceTotal = accAdd(groupPriceTotal, itemPriceCount);
            $('span.groupItemPriceCount',$(row)).text(ebcUtils.numFormat(itemPriceCount));
        });
        //修改数量
        if(modify){
            if(amount > 0){
                var data = collectGroupInfo(obj,false);
                var params = ebcUtils.serializeObject(data);
                $.eAjax({
                    url : GLOBAL.WEBROOT + '/order/updateGroupNumber',
                    async :false,//同步请求
                    data : params,
                    success : function(returnInfo){
                        success = true;
                    },
                    exception : function(){
                        success = false;
                    }
                });
            }
        }
        $('span.itemPriceCount',$(mainbox)).text(ebcUtils.numFormat(groupPriceTotal));
    };
    //组合商品处理（多个）
    var groupsCount = function(){
        $('.item-group').each(function(i,row){
            groupCount(row);
        });
    };

    //店铺小计
    var shopCount = function(){

        var discountTotal = 0;
        var shopDiscountTotals = 0;
        var orderTotal = 0;//订单总金额
        var orderBaseTotal = 0;
        $('.car-item').each(function(i,row){
            var total = 0;
            var baseTotal = 0;
            var discount = 0;//店铺所有商品的优惠金额
            var shopDiscount = 0;//店铺自身的优惠减免金额
            var shopDiscountTotal = 0;//店铺所有优惠总计
            var _this = this;
            var selectNoPromCount = 0;//选中明细且在优惠列表中选择不使用优惠的个数
            var selectPromCount = 0;//选中明细且有优惠信息的个数
            var selectShopItemCount = 0;//选中明细的个数
            var shopPromDiscountMoney = 0;//累加店铺减免金额
            $('span.itemPriceCount',$(_this)).each(function(i,itemPrice){
                //处理组合商品 //微商已修改
                var group = $(itemPrice).closest('.item-group');
                var groupCheck = $('.groupCheck',$(group));
                if($(groupCheck).prop('checked')) {
                    var groupTotal = Number($(itemPrice).text());//组合购买价格
                    total += groupTotal;
                    $(group).find('.group-body').each(function(i,groupItem){

                        var unitBasePrice = Number($('.unitBasePrice', $(groupItem)).text());
                        var groupItemPrice = Number($('.groupItemPrice', $(groupItem)).text());
                        //累加总金额

                        //商品数量
                        var groupNum = $('.groupAmounts',$(group)).val();

                        baseTotal += accMul(unitBasePrice,groupNum);
                        //累加group减免的钱
                        var groupDiscountMoney = accMul(accSub(unitBasePrice, groupItemPrice),groupNum);
                        discount += groupDiscountMoney;
                    });
                }

                var box = $(itemPrice).closest('.item-body');
                var check = $('.itemCheck',$(box)).not('.groupCheck');
                if($(check).prop('checked')){
                    var smallTotal = Number($(itemPrice).text());
                    var unitPrice = Number($('.unitBasePrice',$(box)).text());

                    var promListItemCount = $('.itemPromOption',$(box)).size();
                    //选中明细且有优惠信息的个数累加
                    if(promListItemCount > 0) selectPromCount++;
                    //累加总金额
                    total += smallTotal;
                    //商品数量
                    var num = $('.itemNumber',$(box)).val();

                    baseTotal += accMul(unitPrice,num);
                    //累加商品减免金额itemPromDiscountMoney
                    var itemPromDiscountMoney = Number($('.itemPromDiscountMoney',$(box)).val());
                    //是否参与促销成功
                    var ifFulfillProm = ($('.ifFulfillProm',$(box)).val()=="true");
                    //原购买单价X数量后的小计
                    //原价
                    if(!ifFulfillProm) {
                        itemPromDiscountMoney = accMul(accSub($('#itemFreBasePrice', $(box)).val(),$('#itemFreBuyPrice', $(box)).val()),num)
                    }
                    if(isNaN(itemPromDiscountMoney)) itemPromDiscountMoney = 0;

                    //var itemPromDiscountPriceMoney = Number($('.itemPromDiscountPriceMoney',$(box)).val());
                    //------(旧)商品减免金额的构成是“优惠金额”+“非价格以外的优惠”
                    //现只累加明细直接减免金额
                    //discount += (itemPromDiscountMoney + itemPromDiscountPriceMoney);
                    discount += itemPromDiscountMoney;
                    //统计不使用优惠的商品数量累加
                    if($(':radio:checked[value="-1"]',$(box)).size()>0) selectNoPromCount++;
                    //选中的商品数量累加
                    selectShopItemCount++;
                }
                //选中统计店铺减免金额
                if($('.shopCheck',$(_this)).prop('checked'))shopPromDiscountMoney = Number($('.shopPromDiscountMoney',$(_this)).val());
            });
            //店铺减免金额前的价格小计
            $('#shopCount',$(_this)).text(ebcUtils.numFormat(baseTotal));
            //累加店铺总金额
            orderTotal = accAdd(orderTotal,total);

            orderBaseTotal += baseTotal;

            //var shopPromDiscountPriceMoney = Number($('.shopPromDiscountPriceMoney',$(_this)).val());
            //-------------(旧)店铺商品减免金额的构成是“优惠金额”+“非价格以外的优惠”
            //现只计算店铺的直接减免金额
            //shopDiscount += (shopPromDiscountMoney + shopPromDiscountPriceMoney);
            shopDiscount = shopPromDiscountMoney;
            //将商品的减免金额加上店铺的减免金额
            //discount += shopDiscount;
            shopDiscountTotal = accAdd(discount,shopDiscount);
            //设置店铺优惠金额总计（商品的直接减免+店铺的直接减免）
            $('.shopDiscount',$(_this)).text(ebcUtils.numFormat(shopDiscountTotal));
            //累加订单总优惠金额
            discountTotal = accAdd(discountTotal,shopDiscount);
            shopDiscountTotals = accAdd(shopDiscountTotals,shopDiscountTotal);

            //当前店铺中的所有选中商品若都不选择优惠，且店铺本身没有优惠项目，则隐藏店铺金额统计栏
            //if(selectNoPromCount == selectShopItemCount){
            var shopPromId = '';
            var shopProm = $('.shop-reduce',$(_this));
            //获得店铺优惠ID
            shopPromId = ($(shopProm).is(':hidden')) ? '-1' : $(':radio:checked',$(shopProm)).val();
            //明细全部没有设置优惠且店铺没有优惠，则隐藏店铺金额总计
            if((selectNoPromCount == selectPromCount) && (shopPromId == '-1')){
                setPromView(_this,false);
            }else{
                setPromView(_this,true);
            }
        });
        //设置订单总金额
        //$('#cartAmountCount').text(ebcUtils.thousandSeparator(ebcUtils.numFormat(orderTotal, 2)));
//				$('#cartAmountCount').text(ebcUtils.thousandSeparator(ebcUtils.numFormat(orderTotal-discountTotal>0?orderTotal-discountTotal:0)));
        $('#cartAmountCount').text(ebcUtils.thousandSeparator(ebcUtils.numFormat(orderBaseTotal-shopDiscountTotals>0?orderBaseTotal-shopDiscountTotals:0)));
        //计算订单总优惠金额
        $('#discountTotal').text(ebcUtils.numFormat(shopDiscountTotals));
    };
    //计算单项金额组合商品
    //obj：操作对象区域
    //srcNum：源数量
    var calcGroupItem = function(obj,srcNum,modify){

    };

    //计算单项金额
    //obj：操作对象区域
    //srcNum：源数量
    var calcItem = function(obj,srcNum,modify){
        var success = true;
        var box = $(obj).hasClass('item-body')?obj:$(obj).closest('.item-body');
        //排除组合商品系列
        if($(box).hasClass('item-body-tit') || $(box).hasClass('group-body')) return;
        //设置明细单价及小计
        var setPriceCount = function(finalPrice,caclPrice,number){
            var ifFulfillProm = ($('.ifFulfillProm',$(box)).val()=="true");
            //原购买单价X数量后的小计
            //原价
            var discountPriceMoney = 0;
            var itemPromCaclPrice = Number(caclPrice);
            var itemPromFinalPrice = Number(finalPrice);
            if(!ifFulfillProm){
                itemPromCaclPrice = $('#itemFreBasePrice',$(box)).val();
                itemPromFinalPrice = $('#itemFreBuyPrice',$(box)).val();
                discountPriceMoney = accMul(accSub(itemPromCaclPrice,itemPromFinalPrice),number);
            }

            //原价，现价
            $(box).find('span.unitBasePrice').text(ebcUtils.numFormat(itemPromCaclPrice));
            $(box).find('span.unitPrice').text(ebcUtils.numFormat(itemPromFinalPrice));

            //做个小计，随时准备展示
            var buyPriceCountMoney = accMul(itemPromFinalPrice,number);
            var itemPriceCount = accAdd(buyPriceCountMoney,discountPriceMoney);
            $(box).find('span.itemPriceCount').text(ebcUtils.numFormat(buyPriceCountMoney));

        };
        //原始最终价
        var finalPrice = Number($('.itemPromDiscountFinalPrice',$(box)).val());
        //原始基准价
        var caclPrice = Number($('.itemPromDiscountCaclPrice',$(box)).val());
        //单价优惠金额
        var unitPromPriceMoney = Number($('.itemPromDiscountPriceMoney',$(box)).val());
        //购买数量
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
                        updatePromInfo(obj,returnInfo.data);

                        //原始最终价更新之后再次获取
                        finalPrice = Number($('#itemBuyPrice',$(box)).val());
                        //原始基准价更新之后再次获取
                        caclPrice = Number($('#itemBasePrice',$(box)).val());
                        //单价优惠金额更新之后再次获取
                        //unitPromPriceMoney = Number($('.itemPromDiscountPriceMoney',$(box)).val());
                        setPriceCount(finalPrice,caclPrice,num);
                        success = true;
                    },
                    exception : function(){
                        success = false;
                    }
                });
            }
        }else{
            setPriceCount(finalPrice,caclPrice,num);
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
            //calcTotalAmount();
            checkItemCount();
            shopCount();
        }
    };
    //计算单组组合商品的金额并统计总金额总数量
    var calcGroupAmount = function(obj,modify){
        //当前组合商品的金额统计
        groupCount(obj,modify);
        //刷新商品数量个数
        checkItemCount();
        //刷新店铺金额及总金额
        shopCount();
    };
    //收集明细优惠信息
    //unchange ：true[删除、不选择]，false[修改数量、修改促销]
    //shop：店铺级别的处理
    var collectPromInfo = function(obj,unchange,shopDo){

        var shop = $(obj).hasClass('car-item')?obj:$(obj).closest('.car-item');
        var shopPromId = $('#shopTempPromId',$(shop)).val();
        if(!shopPromId) shopPromId = constants.tmpPromId;
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

        //微商改造，存在组合商品
        //设置选中的商品信息
        $('.item-body,.item-group',$(shop)).each(function(i,row){
            if($('.itemCheck',$(row)).prop('checked')){
                var _groupBox = $(row).hasClass('item-group')?row:$(row).closest('.item-group');
                var _a=$(_groupBox);
                //存在组合商品
                if(_a && _a.size()>0){
                    $('.group-body',$(_groupBox)).each(function(i,row){
                        if(!row) return;
                        prom.ordCartChg.rOrdCartCommRequest.ordCartItemCommList.push({
                            id : $('#itemId',$(row)).val(),
                            promId : $('#promId',$(row)).val()
                        });
                    });

                } else {
                    prom.ordCartChg.rOrdCartCommRequest.ordCartItemCommList.push({
                        id : $('#itemId',$(row)).val(),
                        promId : $('#itemPromId',$(row)).val()
                    });
                }
            }
        });

        //修改数量、修改促销
        if($.type(unchange)!='undefined' && !unchange && !shopDo){
            var item = $(obj).closest('.item-body');
            prom.ordCartChg.rOrdCartItemCommRequest.id = $('#itemId',$(item)).val();
            var promId = $('#itemTempPromId',$(item)).val();
            if(!promId){
                prom.ordCartChg.rOrdCartItemCommRequest.promId = constants.tmpPromId;//取不到优惠ID时，传递-1参数
            }else{
                prom.ordCartChg.rOrdCartItemCommRequest.promId = promId;
            }
        }
        return prom;


    };
    //收集组合商品需要传递到后台的数据对象
    //obj：操作对象
    //single：单个项目true，默认为空
    var collectGroupInfo = function(obj,single){

        var group = {
            ordCartItems : []
        };
        var setRowData = function(row){
            if(!row) return;
            var d = {};
            d.staffId = $('#staffId').val();
            //id可以使用，但是要确保在某个作用域内不重复
            d.cartId = $('#itemCartId',$(row)).val();
            d.id = $('#itemId',$(row)).val();
            var _groupBox = $(row).hasClass('item-group')?row:$(row).closest('.item-group');
            d.orderAmount = $('.groupAmounts',$(_groupBox)).val();
            group.ordCartItems.push(d);
        };
        var groupBox = $(obj).hasClass('item-group')?obj:$(obj).closest('.item-group');
        if(single){
            var row = $(obj).hasClass('group-body')?obj:$(obj).closest('.group-body');
            setRowData(row);
        }else{
            $('.group-body',$(groupBox)).each(function(i,row){
                setRowData(row);
            });
        }
        return group;
    };
    //收集批量删除需要传递到后台的数据对象
    var collectBatchDelete = function(){
        var items = {
            source : $('#websiteSource').val(),
            countryCode : '',
            provinceCode : '',
            cityCode : '',
            ordCartItems : [],
            rBatchCartsRequests : [],
            rBatchCartItemsRequests : []
        };
        //处理订单本域请求参数，微商改造选择checkbox 排除店铺选择狂
        $('#cartList :checkbox:checked').not('.shopCheck').each(function(i,item){
            if($(item).hasClass('groupCheck')){//组合套餐
                var group = $(item).closest('.item-group');
                $('.group-body',$(group)).each(function(j,gItem){
                    var id = $('#itemId',gItem).val();
                    var cartId = $('#itemCartId',gItem).val();
                    items.ordCartItems.push({
                        'id' : id,
                        'cartId' : cartId
                    });
                });
            }else{//普通商品
                var id = $(item).siblings('#itemId').val();
                var cartId = $(item).siblings('#itemCartId').val();
                items.ordCartItems.push({
                    'id' : id,
                    'cartId' : cartId
                });
            }
        });
        return items;
    };
    /**
     * （选择/不选、删除、修改数量、修改优惠）操作后，对于店铺促销信息的更新
     * prom：店铺优惠信息对象
     * shop：店铺最外层jquery对象
     */
    var updateShopProm = function(prom,shop){

        if(prom && $.isPlainObject(prom) && shop){
            //店铺信息区域
            var shopInfoBox = $('div.shop',$(shop));
            //最新优惠信息
            var d = prom;
            var p = prom.promInfoDTO;
            if(p && $.isPlainObject(p)){
                //设置此刻优惠ID以及临时ID
                var tmpPromId = p.id;
                if(!tmpPromId && $.type(tmpPromId)!='number') tmpPromId = constants.tmpPromId;
                $('#shopPromId,#shopTempPromId',$(shopInfoBox)).val(tmpPromId);
            }
            //设置优惠金额
            $('.shopPromDiscountPrice',$(shopInfoBox)).val(numberToPrice(d.discountPrice));
            $('.shopPromDiscountAmount',$(shopInfoBox)).val(numberToPrice(d.discountAmount));
            $('.shopPromDiscountMoney',$(shopInfoBox)).val(numberToPrice(d.discountMoney));
            $('.shopPromDiscountPriceMoney',$(shopInfoBox)).val(numberToPrice(d.discountPriceMoney));
            //设置优惠标签
            var promNameShort = '&nbsp;';
            if(p && $.isPlainObject(p) && p.id!=-1) promNameShort = p.nameShort;
            else promNameShort = constants.nopromtext;
            $('.shopPromTheme',$(shopInfoBox)).html(promNameShort);
            //店铺优惠展示 微商改造点
            //if(p && p.promTheme && p.id!=-1) $('.shopPromInfoBox',$(shopInfoBox)).parent().show();
            //else $('.shopPromInfoBox',$(shopInfoBox)).parent().hide();
            //设置优惠提示信息 微商改造
            var promMsg = '&nbsp;';
            if(d && $.isPlainObject(d) && d.promTypeMsgResponseDTO && $.isPlainObject(d.promTypeMsgResponseDTO)){
                promMsg = (d.ifFulfillProm) ? d.promTypeMsgResponseDTO.fulfilMsg : d.promTypeMsgResponseDTO.noFulfilMsg;
            }
            //微商改造
            $('.shopPromMessage',$(shopInfoBox)).html(promMsg);
            if(d.promInfoDTOList && $.isArray(d.promInfoDTOList) && d.promInfoDTOList.length > 0){
                //清空原优惠项目列表 微商改造点
                var shopPromList = $('.shop-reduce ul.body',$(shopInfoBox));
                $('li.shopPromOption',$(shopPromList)).remove();
                //生成新优惠项目
                $.each(d.promInfoDTOList,function(i,row){
                    var str = '<li class="shopPromOption modifyShopProm" style="cursor: pointer;">';
                    var checked = '';
                    var ui_arrow = '<span class="ui-arrow ui-arrow-right"></span>';
                    if(row.id == p.id) checked = ' checked="checked" ';
                    str += '<input name="shopProm_' + $('#shopCartId',$(shopInfoBox)).val() +
                        '" type="radio" style="display:none" '+checked+' value="'+row.id+'" class="shopPromSelect"/>';
                    str += '<span class="pro-mz">'+row.nameShort + '</span>';
                    str += '<div class="mz-txt">'+row.promTheme + '</div>';
                    str += ui_arrow + '</li>';
                    $('.noShopPromOption',$(shopPromList)).before(str);
                });
                //$('.shopPromInfoBox',$(shopInfoBox)).parent().show();
            }else{
                //$('.shopPromInfoBox',$(shopInfoBox)).parent().hide();
            }
            //有促销则展示 微商改造点
            if($('li.shopPromOption',$(shopInfoBox)).size()>0){
                $('.shopPromInfoBox',$(shopInfoBox)).children('div.notice').show();
                $('.shopPromInfoBox',$(shopInfoBox)).parent().show();
            }

        }

    };
    /**
     * （选择/不选、删除、修改数量、修改优惠）操作后，对于明细优惠信息的刷新处理
     * prom：明细优惠信息
     * item：明细最外层的jquery对象
     */
    var updateShopItemProm = function(prom,item){
        if(prom && $.isPlainObject(prom) && item){
            var d = prom;
            //最优/当前选择的优惠信息
            var p = d.promInfoDTO;
            if(p && $.isPlainObject(p)){
                //设置优惠ID及临时优惠ID
                var tmpPromId = p.id;
                if($.type(tmpPromId)!='number' && !tmpPromId) tmpPromId = -1;
                $('#itemPromId,#itemTempPromId',$(item)).val(tmpPromId);
            }
            if(d.ifFulfillProm){
                //设置优惠金额
                $('#itemBuyPrice',$(item)).val(numberToPrice(d.discountFinalPrice));
                $('#itemBasePrice',$(item)).val(numberToPrice(d.discountCaclPrice));
            }else{
                $('#itemBuyPrice',$(item)).val($('#itemFreBuyPrice',$(item)).val());
                $('#itemBasePrice',$(item)).val($('#itemFreBasePrice',$(item)).val());
            }
            //设置优惠信息
            $('.ifFulfillProm',$(item)).val(d.ifFulfillProm);
            //设置优惠金额
            $('.itemPromDiscountPrice',$(item)).val(numberToPrice(d.discountPrice));
            $('.itemPromDiscountFinalPrice',$(item)).val(numberToPrice(d.discountFinalPrice));
            $('.itemPromDiscountCaclPrice',$(item)).val(numberToPrice(d.discountCaclPrice));
            $('.itemPromDiscountAmount',$(item)).val(numberToPrice(d.discountAmount));
            $('.itemPromDiscountMoney',$(item)).val(numberToPrice(d.discountMoney));
            $('.itemPromDiscountPriceMoney',$(item)).val(numberToPrice(d.discountPriceMoney));
            //设置商品满足/不满足优惠的信息 微商改造
            var promMsg = '&nbsp;';
            if(p && $.isPlainObject(p) && d.promTypeMsgResponseDTO && $.isPlainObject(d.promTypeMsgResponseDTO)){
                promMsg = d.ifFulfillProm ? d.promTypeMsgResponseDTO.fulfilMsg : d.promTypeMsgResponseDTO.noFulfilMsg;
            }
            //微商改造
            $('#prom-notice',$(item)).html(promMsg);
            //设置优惠列表
            if(d.promInfoDTOList && $.isArray(d.promInfoDTOList) && d.promInfoDTOList.length > 0){
                //清空原优惠项目列表
                var promList = $('.p-reduce .body',$(item));
                $('li.itemPromOption',$(promList)).remove();
                //生成新优惠项目
                $.each(d.promInfoDTOList,function(i,row){
                    var str = '<li class="itemPromOption modifyShopItemProm" style="cursor: pointer;">';
                    var checked = '';
                    if(row.id == p.id) checked = ' checked="checked" ';
                    str += '<input name="prom_' + $('#itemId',$(item)).val() + '" type="radio" style="display:none" '+checked+' value="'+row.id+'" class="promSelect"/>';
                    str += '<span class="pro-mz">' + row.nameShort + '</span>';
                    str += '<div class="mz-txt">' + row.promTheme + '</div>';
                    str += '<span class="ui-arrow ui-arrow-right"></span></li>';

                    $('.noItemPromOption',$(promList)).before(str);
                });
                $('.slideButtom',$(item)).show();
            }else{
                //若无返回优惠列表，则隐藏优惠列表
                $('.slideButtom',$(item)).hide();
            }
            //设置赠品信息
            var giftStr = '',splitStr = '';
            if(d.promGiftDTOList && $.isArray(d.promGiftDTOList) && d.promGiftDTOList.length>0){
                $.each(d.promGiftDTOList,function(i,row){
                    if(i>0) splitStr = '、';
                    if(row && $.isPlainObject(row)){
                        giftStr += splitStr + row.giftName + ' X ' + row.everyTimeCnt + ';';
                    }
                });
            }
            $('div.prom-info-show > span',$(item)).text(giftStr);
            if(!giftStr) $('div.prom-info-show',$(item)).hide();
            else $('div.prom-info-show',$(item)).show();
        }
    };


    //（选择/不选、删除、修改数量、修改优惠）操作后，对于优惠信息的刷新处理
    //obj：触发操作的元素
    //data：请求返回的JSON数据
    var updatePromInfo = function(obj,data){
        if(!obj || !data) return;
        var mainBox = $(obj).hasClass('car-item')?obj:$(obj).closest('.car-item');
        var shopBox = $('div.shop',$(mainBox));
        //var itemBox = $(obj).hasClass('item-list')?obj:$(obj).closest('.item-list');
        //微商改造点
        var itemBox = $(obj).hasClass('item-body')?obj:$(obj).closest('.item-body');
        //处理店铺信息
        updateShopProm(data.cartPromDTO, mainBox);
        //处理商品优惠信息
        updateShopItemProm(data.cartPromItemDTO, itemBox);
    };
    /**
     * 更新全部店铺、全部商品的优惠信息（组合商品信息除外）
     * 适用于全选商品
     */
    var updateAllProm = function(data){
        if(!data) return;
        var shopProms = data.cartPromDTOMap;
        var itemProms = data.cartPromItemDTOMap;
        $('#cartList .car-item').each(function(i,shop){
            var cartId = $('#shopCartId',$(shop)).val();
            var shopProm = null;
            for ( var key in shopProms) {
                if(key == cartId){
                    shopProm = shopProms[key];
                    break;
                }
            }
            updateShopProm(shopProm, shop);
            $('.item-body',$(shop)).each(function(j,item){
                //组合商品系列跳过处理
                if(!$(item).hasClass('group-body')){
                    var itemId = $('#itemId',$(item)).val();
                    var itemProm = null;
                    for ( var itemkey in itemProms) {
                        if(itemkey == itemId){
                            itemProm = itemProms[itemkey];
                            break;
                        }
                    }
                    updateShopItemProm(itemProm, item);
                }
            });
        });
    };
    //修改店铺优惠信息
    var modifyShopProm = function(obj){

        var box = $(obj).closest('.car-item').find('div.shop');
        //var newPromId = $(':radio:checked',$(box)).val();
        var newPromId = $(':radio',$(obj)).val();
        //将新优惠ID设置到临时存放的隐藏域中
        $('#shopTempPromId',$(box)).val(newPromId);
        var data = collectPromInfo(obj,false,true);
        data.ordCartItem.promId = newPromId;
        data.ordCartItem.staffId =  $('#staffId').val();
        data.ordCartItem.id = $('#shopCartId',$(box)).val();
        var params = ebcUtils.serializeObject(data);
        $.eAjax({
            url : GLOBAL.WEBROOT + '/order/modifyShopProm',
            async :false,//同步请求
            data : params,
            success : function(returnInfo){
                $('#shopPromId',$(box)).val(newPromId);
                updatePromInfo(obj,returnInfo.data);
                refreshAmount();
                hideListItem();
            }
        });
    };
    //修改店铺明细优惠信息
    var modifyShopItemProm = function(obj){
        var box = $(obj).closest('.item-body');
        //var newPromId = $(':radio:checked',$(box)).val();
        var newPromId = $(':radio',$(obj)).val();
        //将新优惠ID设置到临时存放的隐藏域中
        $('#itemTempPromId',$(box)).val(newPromId);
        var data = collectPromInfo(obj,false);
        data.ordCartItem.id = $('#itemId',$(box)).val();
        data.ordCartItem.staffId =  $('#staffId').val();
        data.ordCartItem.cartId = $('#itemCartId',$(box)).val();
        data.ordCartItem.promId = newPromId;//Number($(box).find('input.itemNumber').val());
        var params = ebcUtils.serializeObject(data);
        $.eAjax({
            url : GLOBAL.WEBROOT + '/order/modifyShopItemProm',
            async :false,//同步请求
            data : params,
            success : function(returnInfo){
                $('#itemPromId',$(box)).val(newPromId);
                updatePromInfo(obj,returnInfo.data);
                refreshAmount();
                hideListItem();
            }
        });

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
                updatePromInfo(obj,returnInfo.data);
                //微商改造 item-list item-body
                //$(obj).closest('.item-list').remove();
                $(obj).closest('.item-body').remove();
                refreshAmount();
            }
        });
    };
    //删除组合商品
    var deleteGroupItem = function(obj,single){
        var mainbox = $(obj).hasClass('item-group')?obj:$(obj).closest('.item-group');
        var data = collectGroupInfo(obj,single);
        var params = ebcUtils.serializeObject(data);
        $.eAjax({
            url : GLOBAL.WEBROOT + '/order/deleteGroupItems',
            async :false,//同步请求
            data : params,
            success : function(returnInfo){
                //移除组合商品主框
                var removeMainBox = function(){
                    $(mainbox).remove();
                };
                if(single){//删除组合商品中的单个明细
                    $(obj).closest('.group-body').remove();
                    //组合商品的最后一条明细商品不需要虚线
                    if($('.group-body',$(mainbox)).size() > 0){
                        $('.group-body:last',$(mainbox)).removeClass('item-body-border');
                    }else{
                        removeMainBox();
                    }
                }else{//删除整套组合商品
                    removeMainBox();
                }
                refreshAmount();
            }
        });
    };
    //批量删除商品（包含组合商品）
    var deleteCartItems = function(){
        var data = collectBatchDelete();
        var params = ebcUtils.serializeObject(data);
        $.eAjax({
            url : GLOBAL.WEBROOT + '/order/batchDeleteItem',
            async :false,//同步请求
            data : params,
            success : function(returnInfo){
                // 微商修改 item-list 改成 item-body
                var check = $('.item-list :checkbox:checked');

                // 包括组合商品一起删除
                check.closest('.item-body,.item-group').remove();

                refreshAmount();
            }
        });
    };

    // 加入商品收藏的方法
    var addToCollection = function(_this){
        var box = $(_this).hasClass('item-body')?_this:$(_this).closest('.item-body');
        var skuId = $('#itemSkuId',$(box)).val();
        if(skuId){
            eDialog.confirm("移动后选中商品将不在购物车中显示，确认操作吗？", function(){
                $.eAjax({
                    url : GLOBAL.WEBROOT + '/gdsdetail/add',
                    data : {
                        'skuId' : skuId
                    },
                    success : function(returnInfo){
                        if (returnInfo.resultFlag == 'ok') {
                            deleteCurrentItem(_this);
                            eDialog.alert("您已成功收藏该商品！");
                        } else {
                            eDialog.alert(returnInfo.resultMsg);
                        }
                    }
                });
            });
        }else{
            eDialog.alert("数据读取异常（单品ID为空）", $.noop, "error");
        }
    };

    //检查输入框修改之前是否选中，必须选中
    var checkItemClick = function(obj){
        //修改之前先选中
        if(!$(obj).closest('.item-body').find('.itemCheck').prop('checked')){
            $(obj).closest('.item-body').find('.itemCheck').trigger('click');
        }//修改之前先选中
    };

    //检查组合加减输入框修改之前是否选中，必须选中
    var checkGroupItemClick = function(obj){
        //修改之前先选中
        if(!$(obj).closest('.item-group').find('.groupCheck').prop('checked')){
            $(obj).closest('.item-group').find('.groupCheck').trigger('click');
        }//修改之前先选中
    };
    //检查当前购物车是否还有项目，若没有，则显示无项目
    var checkAvailableItem = function(){
        //微商改造点，item-list改成item-body

        $('.car-item').each(function(i,row){
            if($('.item-body',$(row)).size()==0)
                $(row).remove();

        });

        if($('.car-item').size() == 0) {
            //微商改造点
            $('form').remove();
            $('#nullList').show();
        }

    };
    //将数字转换为金额，默认返回的数字单位为分，必须除以100，并格式化两位数字
    var numberToPrice = function(num){
        return ebcUtils.numFormat(accDiv(num,100));
    };
    //隐藏所有下拉列表项目
    var hideListItem = function(){
        $('div[id^="slideButtom_"]').offCanvas('close');
    };
    //刷新金额
    var refreshAmount = function(){
        //检查当前有效商品，无则显示购物车无商品
        checkAvailableItem();
        //统计所有组合商品金额
        groupsCount();
        //统计当前所有物品的总数量
        checkItemCount();
        //总金额统计
        doAllCalc();
        //店铺小计
        shopCount();
        //刷新购物车数量
        mainButtomNavbar.showBuyNum();
    };
    //提交预订单审核
    var checkCar = function(obj){
        $.AMUI.progress.start();//打开进度条
        if(!$(obj).hasClass('gobtn-grad')){
            $.eAjax({
                url:GLOBAL.WEBROOT + '/order/build/checkcar',
                data:ebcForm.formParams($("#cartForm")),
                success:function(returnInfo){
                    $.AMUI.progress.done();//关闭进度条，请求完成
                    if(returnInfo&&returnInfo.resultFlag=='ok'){
                    	window.location.replace(GLOBAL.WEBROOT+"/order/build/preord/"+returnInfo.mainHashKey); 
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

    /**
     *数据格式为Array["name","id",...]
     * @param needToggleIds
     */
    var needToggle = function(needToggleIds){
        if(!needToggleIds){
            return;
        }
        var ids = '';
        if(needToggleIds){
            var splitStr = '';
            for(var i=0;i<needToggleIds.length;i++){
                splitStr = ids.length>0?',':'';
                ids += splitStr + '#'+needToggleIds[i];
            }
        }
        $(ids).toggle();
    };
    //====================================================对象定义（End）============================================================






















    //====================================================事件绑定（Start）============================================================
    // 收开展合的div
    $('#edit,#finish').click(function(){
        // 哪些需要toggle事件
        needToggle(['edit','finish','edittotal','finishtotal','goCheck']);
        // 促销弹出板块
        $('div[id^=slideButtom]').toggle();
        $('.up-prom').toggle();
    });

    //店铺促销列表展示
    $('.shop-prom-select').click(function(e){
        e.stopPropagation();
        //修改促销之前先选中，使用trigger事件存在问题，在于Ajax的延时导致数据刷新不及时
        if($('#edit').is(":hidden")) {
            var car_item = $(this).closest('.car-item');
            var shopCheck =  $('.shopCheck',car_item);
            if(!shopCheck.prop('checked')){
                shopCheck.trigger('click');
            }//修改促销之前先选中

            var spanId=$(this).children('span').attr('id');
            var id = spanId.replace('shop-prom-','');
            $('#slideButtom_'+id).offCanvas('open');
        }
    });

    //店铺促销列表展示
    $('.item-prom-select').click(function(e){
        e.stopPropagation();

        //修改促销之前先选中，使用trigger事件存在问题，在于Ajax的延时导致数据刷新不及时
        var itemCheck =  $('.itemCheck',$(this).closest('.item-body'));
        if(!itemCheck.prop('checked')){
            itemCheck.trigger('click');
        }//修改促销之前先选中

        var myId = $(this).attr('id');
        var id = myId.replace('prom-select-','');
        $('#slideButtom_prom_select_'+id).offCanvas('open');
    });

    //全选事件绑定
    $("[id^=select_shop_]").each(function(){
        //全选区域
        var shopList = $(this).closest('.shopList');
        $(':checkbox',$(this)).fSelectAll(shopList);
    });
    //关闭促销弹出窗
    $('.icon-close').click(function () {
        $(this).closest('.slideButtom').offCanvas('close');
    });

    //绑定 全选事件
    $('#selectAllItem2').sSelectAll('#cartList');
    $('#selectAllItem2').on('click',function(e){
        e.stopPropagation();
        if($(this).prop('checked')){//全选时，刷新所有优惠促销信息
            if(!_init){
                $.eAjax({
                    url : GLOBAL.WEBROOT + '/order/cart/selectAll',
                    async :false,//同步请求
                    success : function(returnInfo){
                        updateAllProm(returnInfo.prom);
                        refreshAmount();
                    }
                });
            }else{
                refreshAmount();
            }
            //清除购物车组合商品选择隐藏checkbox
            $('.groupCheckHidden').val('on');
        }else{
            $('.groupCheckHidden').val('');
            refreshAmount();
        }
    });
    //选中商品
    $('#cartList .itemCheck').on('click',function(e){

        e.stopPropagation();
        //排除组合商品
        if($(this).hasClass('groupCheck')) return;
        var _this = this;
        var shop = $(_this).closest('.car-item');
        if(!$(_this).prop('checked') && $(':checkbox:checked',$(shop)).not('.groupCheck').size()==0){
            refreshAmount();
        }else{
            var data = collectPromInfo(_this,true);
            var params = ebcUtils.serializeObject(data);
            $.eAjax({
                url : GLOBAL.WEBROOT + '/order/checkShopItem',
                async :false,//同步请求
                data : params,
                success : function(returnInfo){
                    updatePromInfo(_this,returnInfo.data);
                    refreshAmount();
                }
            });
        }
        
    });


    //选中商品
    $('#cartList .shopCheck').on('click',function(e){
        e.stopPropagation();

        var _this = this;
        var shop = $(_this).closest('.car-item');
        if(!$(_this).prop('checked') && $(':checkbox:checked',$(shop)).not('.groupCheck').size()==0){
            refreshAmount();
        }else{
            var data = collectPromInfo(_this,true);
            var params = ebcUtils.serializeObject(data);
            $.eAjax({
                url : GLOBAL.WEBROOT + '/order/checkShopItem',
                async :false,//同步请求
                data : params,
                success : function(returnInfo){
                    updatePromInfo(_this,returnInfo.data);
                    refreshAmount();
                }
            });
        }
    });

    //选中组合商品
    $('#cartList .groupCheck').on('click',function(e){
        e.stopPropagation();
        var _this = this;
        refreshAmount();
        var mainbox = $(_this).hasClass('item-group')?_this:$(_this).closest('.item-group');
        if($(_this).prop('checked')){
            $('.groupCheckHidden',$(mainbox)).val('on');
        }else{
            $('.groupCheckHidden',$(mainbox)).val('');
        }
    });

    //删除指定行物品
    $('#delCheckedItem').click(function(){
        var check = $('.item-list :checkbox:checked');
        if(check.length < 1){
            eDialog.alert('请至少选择一项购物车商品');
            return;
        }
        eDialog.confirm("确定要删除指定的商品吗？",  function(){
                    deleteCartItems(); 
        });
       
    });

    //删除当前行物品
    $('.deleteCurrentItem').click(function(e){
        e.stopPropagation();
        var _this = this;
        eDialog.confirm("确定要删除指定的物品吗？", function(){
                    deleteCurrentItem(_this);
        });
        
    });

    //删除当前组合商品（一个）物品
    $('.deleteGroupItem').click(function(e){
        e.stopPropagation();
        var _this = this;
        eDialog.confirm("确定要删除该商品吗？", function(){
                    deleteGroupItem(_this,true);
        }); 
    });
    //删除当前组合商品（多个）物品
    $('.deleteGroup').click(function(e){
        e.stopPropagation();
        var _this = this;
        eDialog.confirm("确定要删除这套组合商品吗？", function(){
                    deleteGroupItem(_this,false);
        });
    });

    //减少购物车数量
    $('.itemNumberMinus').on('click',function(){
        var ipt = $(this).next('.itemNumber');
        //修改之前先选中
        checkItemClick(this);
        //修改之前先选中
        var num = Number(ipt.val());
        if(num > 1){
            ipt.val(--num);
            calcItemAmount(this,true);
        }
        mainButtomNavbar.showBuyNum();
    });

    //增加购物车数量
    $('.itemNumberAdd').on('click',function(){
        //修改之前先选中
        checkItemClick(this);
        //修改之前先选中
        var ipt = $(this).prev('.itemNumber');
        var num = Number(ipt.val());
        ipt.val(++num);
        calcItemAmount(this,true);
        mainButtomNavbar.showBuyNum();
    });

    //输入框修改购物车数量
    $('.itemNumber').on('keypress',function(e){
        return ebcUtils.checkNum(e, this);
    }).on('blur',function(){
        if(!$(this).val() || Number($(this).val()) < 1 || /\.+/.test($(this).val())) $(this).val(1);
        //修改之前先选中
        checkItemClick(this);
        calcItemAmount(this,true);
        mainButtomNavbar.showBuyNum();
    });

    //减少组合商品数量
    $('.groupAmountMinus').on('click',function(){
        checkGroupItemClick(this);
        var ipt = $(this).next('.groupAmounts');
        var num = Number(ipt.val());
        if(num > 1){
            ipt.val(--num);
            calcGroupAmount(this,true);
        }
        mainButtomNavbar.showBuyNum();
    });

    //增加组合商品数量
    $('.groupAmountAdd').on('click',function(){
        checkGroupItemClick(this);
        var ipt = $(this).prev('.groupAmounts');
        var num = Number(ipt.val());
        ipt.val(++num);
        calcGroupAmount(this,true);
        mainButtomNavbar.showBuyNum();
    });

    //输入框修改组合商品数量
    $('.groupAmounts').on('keypress',function(e){
        return ebcUtils.checkNum(e, this);
    }).on('blur',function(){
        if(!$(this).val() || Number($(this).val()) < 1) $(this).val(1);
        calcGroupAmount(this,true);
        mainButtomNavbar.showBuyNum();
    });

    //添加到我的关注
    $('.addToFollow').click(function(e){
        var _this = this;
        $(this).unbind('click');
        addToCollection(_this);
        e.preventDefault();
    });

     //添加到我的关注
    $('#addToFollows').click(function(e){
        //var collect = $('.item-body :checkbox:checked');
        //collect.each(function(){
        //    addToCollection(this);
        //});
        var collect = $('.item-body :checkbox:checked');
        if(collect.size()!=1){
            eDialog.alert('推荐每次收藏一种商品',function(){
                return 0;
            });
        }else{
            addToCollection(collect);
        }

    });

    //去结算
    $('#goCheck').click(function(){
        if($('.itemCheck:checked').size()==0){
            eDialog.alert('请至少选择一项购物车商品');
        }else{
            checkCar(this);
        }
    });

    //修改店铺促销信息 微商改造点 事件委派
    $('.body').delegate('.modifyShopProm','click', function(e){
        modifyShopProm(this);
        e.preventDefault();
    });
    //修改店铺促销信息 微商改造点 事件委派
    $('.body').delegate('.modifyShopItemProm','click', function(e){
        modifyShopItemProm(this);
        e.preventDefault();
    });

    //====================================================初始化（Start）============================================================
    $('#cartList :checkbox').prop('checked',false);
    $('#selectAllItem2').trigger('click');
    /*			document.title = '我的购物车 - 人卫商城';*/
    _init = false;
    //====================================================初始化（End）============================================================

});

var mainButtomNavbar = {
		"showBuyNum":function(){
			$.eAjax({
				url : GLOBAL.WEBROOT + "/order/getcartcount",
				data : "",
				success : function(returnInfo) {
					if(0<returnInfo){
						$('.num-tip').html(returnInfo);
						$('.num-tip').show();
					}else{
						$('.num-tip').html("");
						$('.num-tip').hide();
					}
				},
				error: function(){
					$('.num-tip').html("");
					$('.num-tip').hide();
				},
				exception : function(){
					$('.num-tip').html("");
					$('.num-tip').hide();
				}
			});
		}
};

$.fn.extend({
    //指定区域的复选框全选功能
    //参数匀为jquery选择器表达式
    //box：处理全选功能的区域，在该区域内的所有checkbox对象都会被操作
    //仅仅只是提供一个多项的初始化功能
    fSelectAll : function(box){
        if(!box || $(box).size()==0) return;
        var _this = this;
        var checkboxs = $(':checkbox',$(box)).not(_this);
        $(_this).on('click',function(e){
            e.stopPropagation();
            var checked = $(this).prop('checked');
            checkboxs.prop('checked',checked);
            $(_this).prop('checked',checked);
            //重置组合商品索引项
            if(checked){
                $('.groupCheckHidden',$(_this)).val('on');
            }else{
                $('.groupCheckHidden',$(_this)).val('');
            }
        });
        checkboxs.on('click',function(e){
            e.stopPropagation();
            if($(':checkbox:checked',$(box)).not(_this).size() == $(':checkbox',$(box)).not(_this).size()){
                $(_this).prop('checked',true);
            }else{
                $(_this).prop('checked',false);
            }
        });
    },
    sSelectAll : function(box){
        if(!box || $(box).size()==0) return;
        var _this = this;
        var checkboxs = $(':checkbox',$(box)).not(_this);
        $(_this).on('click',function(e){
            e.stopPropagation();
            var checked = $(this).prop('checked');
            checkboxs.prop('checked',checked);
            $(_this).prop('checked',checked);
        });

        checkboxs.on('click',function(e){
            e.stopPropagation();
            if($(':checkbox:checked',$(box)).not(_this).size() == $(':checkbox',$(box)).not(_this).size()){
                $(_this).prop('checked',true);
            }else{
                $(_this).prop('checked',false);
            }
        });
    }
});
