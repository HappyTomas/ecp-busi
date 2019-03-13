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
                    url : GLOBAL.WEBROOT + '/point/order/updateGroupNumber',
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
        var orderTotal = 0;//订单总金额
        var scoreTotal = 0;
        var orderBaseTotal = 0;
        $('.car-item').each(function(i,row){
            var total = 0;
            var baseTotal = 0;
            var scores = 0;
            var _this = this;
            $('span.itemPriceCount',$(_this)).each(function(i,itemPrice){
                //处理组合商品 //微商已修改
                var box = $(itemPrice).closest('.item-body');
                var check = $('.itemCheck',$(box)).not('.groupCheck');
                if($(check).prop('checked')){
                    var smallTotal = Number($(itemPrice).text());
                    var unitPrice = Number($('.unitBasePrice',$(box)).text());
                    if($('.unitBasePrice',$(box)).is(':hidden')) unitPrice = Number($('.unitPrice',$(box)).text());
                    //累加总金额
                    total += smallTotal;
                    //商品数量
                    var num = $('.itemNumber',$(box)).val();
                    baseTotal += accMul(unitPrice,num);
                    //统计积分
                    var score = Number($('span.itemScoreCount',box).text());
                    scores = accAdd(score,scores);
                }
            });
            $('#shopCount',$(_this)).text(ebcUtils.numFormat(baseTotal));
            $('#shopScore',$(_this)).text(scores);
            //累加店铺总金额
            scoreTotal += scores;
            orderTotal = accAdd(orderTotal,total);
            orderBaseTotal += baseTotal;
        });
        //设置订单总金额
        $('#cartScoreConut').text(scoreTotal);
        $('#cartAmountCount').text(ebcUtils.thousandSeparator(ebcUtils.numFormat(orderBaseTotal>0?orderBaseTotal:0)));
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

            var itemScore = $('#itemScore',$(box)).val();
            var itemPromCaclPrice = Number(caclPrice);
            var itemPromFinalPrice = Number(finalPrice);
            if(!ifFulfillProm){
                itemPromCaclPrice = $('#itemFreBasePrice',$(box)).val();
                itemPromFinalPrice = $('#itemFreBuyPrice',$(box)).val();
                discountPriceMoney = accMul(accSub(itemPromCaclPrice,itemPromFinalPrice),number);
            }

            var scoreVal = ebcUtils.numFormat(accMul(itemScore,number),0);

            //原价，现价
            $(box).find('span.unitBasePrice').text(ebcUtils.numFormat(itemPromCaclPrice));
            $(box).find('span.unitPrice').text(ebcUtils.numFormat(itemPromFinalPrice));

            //做个小计，随时准备展示
            var buyPriceCountMoney = accMul(itemPromFinalPrice,number);
            var itemPriceCount = accAdd(buyPriceCountMoney,discountPriceMoney);
            $(box).find('span.itemPriceCount').text(ebcUtils.numFormat(buyPriceCountMoney));
            $(box).find('span.itemScoreCount').text(scoreVal);

        };
        //原始最终价
        var finalPrice = Number($('.itemPromDiscountFinalPrice',$(box)).val());
        //原始基准价
        var caclPrice = Number($('.itemPromDiscountCaclPrice',$(box)).val());
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
                    url : GLOBAL.WEBROOT + '/point/order/updateCartItemNumber',
                    async :false,//同步请求
                    data : params,
                    success : function(returnInfo){

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
            url : GLOBAL.WEBROOT + '/point/order/deleteCartItem',
            async :false,//同步请求
            data : params,
            success : function(returnInfo){
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
            url : GLOBAL.WEBROOT + '/point/order/deleteGroupItems',
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
            url : GLOBAL.WEBROOT + '/point/order/batchDeleteItem',
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
                url:GLOBAL.WEBROOT + '/point/order/build/checkcar',
                data:ebcForm.formParams($("#cartForm")),
                success:function(returnInfo){
                    $.AMUI.progress.done();//关闭进度条，请求完成
                    if(returnInfo&&returnInfo.resultFlag=='ok'){
                        window.location.replace(GLOBAL.WEBROOT + '/point/order/build/preord/'+returnInfo.mainHashKey);
                    }else{
                        $(obj).removeClass('gobtn-grad');
                        eDialog.alert(returnInfo.resultMsg,function(){
                            window.location.replace(GLOBAL.WEBROOT + '/point/order/cart/list');
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
                    url : GLOBAL.WEBROOT + '/point/order/cart/selectAll',
                    async :false,//同步请求
                    success : function(returnInfo){
                        //updateAllProm(returnInfo.prom);
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
                url : GLOBAL.WEBROOT + '/point/order/checkShopItem',
                async :false,//同步请求
                data : params,
                success : function(returnInfo){
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
                url : GLOBAL.WEBROOT + '/point/order/checkShopItem',
                async :false,//同步请求
                data : params,
                success : function(returnInfo){
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
    });

    //增加组合商品数量
    $('.groupAmountAdd').on('click',function(){
        checkGroupItemClick(this);
        var ipt = $(this).prev('.groupAmounts');
        var num = Number(ipt.val());
        ipt.val(++num);
        calcGroupAmount(this,true);
    });

    //输入框修改组合商品数量
    $('.groupAmounts').on('keypress',function(e){
        return ebcUtils.checkNum(e, this);
    }).on('blur',function(){
        if(!$(this).val() || Number($(this).val()) < 1) $(this).val(1);
        calcGroupAmount(this,true);
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


    //====================================================初始化（Start）============================================================
    $('#cartList :checkbox').prop('checked',false);
    $('#selectAllItem2').trigger('click');
    /*			document.title = '我的购物车 - 人卫商城';*/
    _init = false;
    //====================================================初始化（End）============================================================

});

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