/**
 * 页面编辑
 * @author Terry
 * 
 * 模块类型
 * 01:专题页头
 * 02:招牌标题
 * 03:自定义区
 * 04:图片轮播
 * 05:添加宝贝
 * 06:楼层导航
 * 07:店铺招牌
 * 08:店铺首页广告
 * 09:客服中心
 * 10:店铺导航
 * 11:商品推荐
 * 12:商品排行
 * 13:商品搜索
 * 14:商品展示
 * 15:页面广告
 * 16:店铺公告
 * 17:店铺基本信息
 */
$(function(){
	$('.rightNavSlide').click(function(e){
		if($('.rightNavSlide').hasClass('toLeft')){
			$('.skipfloor').show();
			$('.rightNavSlide').removeClass('toLeft');
		}else{
			$('.skipfloor').hide();
			$('.rightNavSlide').addClass('toLeft');
		}
	});
	var getUrl = function(modId){
		var url = '';
		switch (modId) {
		case "05"://宝贝图片
			url = 'modular-load/goodsPics'
			break;
		case "06"://楼层导航
			url = 'modular-load/floorNavInit'
			break;
		default://公共属性处理页面
			url = 'modular-dynamic/publicSet';
			break;
		}
		return url;
	};
	var hotImg;
	/**
	 * 热点
	 */
	$('a.lnkModuleHot').die('click.bs').live('click.bs',function(){
		var main = $(this).closest('div.modular');
		if (!hotImg) {
			hotImg = $('.hotImg-content',$(main)).hotMaps();
		}
		hotImg.addPos([ [ 400, 200, 100, 100 ] ]);
	});
	 //删除链接
    $('.hotImg-content .delete').live('click',function(){
    	var main = $(this).closest('div.modular');
    	var id=$(this).parents('.hotBox').attr('id');
		if (typeof(id) != "undefined"){
			$.eAjax({
				url : $webroot + 'modular-dynamic/delHotImg',
				data : {'id' : id},
				success : function(returnInfo){
					if(returnInfo && $.type(returnInfo.resultFlag)!='undefined' && returnInfo.resultFlag == 'ok'){
						
					}
				}
			});
		}
        $(this).parents('.hotBox').remove();
        $('.modular-mask',$(main)).show();
    });
    //双击添加
    $('.hotImg-content .hotBox').die('dblclick').live('dblclick',function(){ 
    	var $this=$(this);
    	var relative_coord = parseFloat($(this).css("left"))
								+ ',' + parseFloat($(this).css("top"))
								+ ',' + parseFloat($(this).css("width"))
								+ ',' + parseFloat($(this).css("height"));//top,width,height
    	var main = $(this).closest('div.modular');
    	var itemId = $('#itemId',$(main)).val();
		var pageId = $('#pageId',$(main)).val();
		var modularId = $('#modularId',$(main)).val();
		var modularType = $('#modularType',$(main)).val();
		var hotPicId = $('#hotPicId',$(main)).val();
		var hotItemPropId = $('#hotItemPropId',$(main)).val();
		url = $webroot + 'modular-load/hotImg' + '?pageId='+pageId+'&picId=' + hotPicId + 
					'&itemPropId=' + hotItemPropId+'&relative_coord='+relative_coord;
		var id=$this.attr('id');
		if (typeof(id) != "undefined"){
			url+='&id='+id;
		}
        bDialog.open({
            title : "热点编辑",
            width :400,
            height : 300,
            url : url,
            callback:function(data){
            	//debugger;
            	if(null!=data && null!=data.results && null!=data.results[0].id)
            	$this.attr('id',data.results[0].id);
            }
        });
    });
	/**
	 * 布局项目设置后，将内容回填到页面上
	 */
	$('a.lnkModuleEdit').click(function(e){
		var main = $(this).closest('div.tItem');
		if(main.size() == 0) main = $(this).closest('div.skipfloor');
		var itemId = $('#itemId',$(main)).val();
		var pageId = $('#pageId',$(main)).val();
		var modularId = $('#modularId',$(main)).val();
		var modularType = $('#modularType',$(main)).val();
		var url = getUrl(modularType);
		url = $webroot + url + '?pageId='+pageId+'&itemId=' + itemId + '&modularId=' + modularId+'&modularType='+modularType;
		var width = 600,height = 540;
		//宝贝图片需要更大一些的窗口
		if(modularType == '05'){
			width = 700;height = 540;
		}
		bDialog.open({
            title : "参数设置",
            width : width,
            height : height,
            url : url,
            callback:function(data){
            	if(modularType == '06'){//楼层导航
            		itemRender.floorNav(main,false);
            	}else{
            		$.eAjax({
            			url : $webroot + 'page-pre/loadLayoutItemProp',
            			data : {'itemId' : itemId},
            			success : function(returnInfo){
            				if(returnInfo && $.type(returnInfo.itemProp)!='undefined' && $.isPlainObject(returnInfo.itemProp)){
            					$('div.nodata',$(main)).hide();//隐藏默认无数据时的占位图片
            					itemRender.doRender($(main),returnInfo,false);
            				}
            			}
            		});
            	}            	
            }
        });
	});
	/**
	 * 删除布局项
	 */
	$('a.lnkModuleDel').click(function(e) {
		var _this = $(this);
		eDialog.confirm('确定移除该布局项吗？',{
			buttons : [{
				caption : '确定',
				callback : function(){
					var main = $(_this).closest('div.tItem');
					var row = $(main).closest('div.modular');
					if(main.size() == 0) {
						main = $(_this).closest('div.skipfloor');
						row = null;
					}
					var itemId = $('#itemId',$(main)).val();
					var pageId = $('#pageId',$(main)).val();
					var modularId = $('#modularId',$(main)).val();
					var modularType = $('#modularType',$(main)).val();
					$.eAjax({
						url : $webroot + 'page-edit/removeLayoutItem',
						data : {'itemId' : itemId},
						success : function(returnInfo){
							if(returnInfo && $.type(returnInfo.resultFlag)!='undefined' && returnInfo.resultFlag == 'ok'){
								$(main).remove();
								if(row){
									//移除布局项后，该行没有其它布局项，则移除该行
									if($('div.tItem',$(row)).size() == 0) $(row).remove();
								}
							}
						}
					});
				}
			},{caption : '取消',callback : $.noop}]
		});

	});
});