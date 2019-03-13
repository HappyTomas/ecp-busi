;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-prom-specialheader").modular_prom_specialheader();
				}
			};
		});
	} else {
		// 全局模式 ，不使用AMD 规范的时候，使用的插件
		factory(jQuery);
	}
}(function($) {
	// / 具体插件的定义；
	var defaultOpts = {
		placeId : ""
	};
	
	$modularSpecialHeader = {
		/**
		 * 根据FileID获得文件URL
		 * @param fileId
		 */
		"loadImgUrlById" : function(fileId){		
			var url = '';
			if(!fileId) return '';
			//根据FileId读取图片
			$.eAjax({
				url : $webroot + "modular-dynamic/loadImgById",
				data : {fileId : fileId},
				async : false,//同步执行
				success : function(returnInfo){
					url = returnInfo.imgUrl;
				}
			});
			return url;
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			var main = $(el).closest('div.template');
			var pageId = $('#pageId',$(main)).val();
			var hotItemPropId=opts.hotitempropid;
			var hotPicId=opts.hotpicid;
			var pagePub=opts.pagepub;
			var imgUrl = $modularSpecialHeader.loadImgUrlById(hotPicId);
		    $(el).append('<img src="'+imgUrl+'" usemap="#planetmap">');
		    var url=$webroot + 'modular-load/loadHotImg';
		    var hotImg = $('#imgMap_'+hotItemPropId,$(main)).hotMaps(
					{
						posChangAfter:function(hotObject, boxData){
							var id=hotObject.attr('id');
							var urlInfo=hotObject.attr('urlInfo');
							var showTitle=hotObject.attr('showTitle');
							var relative_coord = parseFloat(boxData.left)
							+ ',' + parseFloat(boxData.top)
							+ ',' + parseFloat(boxData.width)
							+ ',' + parseFloat(boxData.height);
							$.eAjax({
								url : $webroot + "/modular-dynamic/saveHotImg",
								data : {"id":id,"pageId":pageId,"picId":hotPicId,"itemPropId":hotItemPropId,
									"urlInfo":urlInfo,"showTitle":showTitle,"relativeCoord":relative_coord},
								success : function(returnInfo){
									if(returnInfo && $.type(returnInfo.resultFlag)!='undefined' && returnInfo.resultFlag == 'ok'){
										hotObject.attr('id',returnInfo.resultMsg);
									}
								}
							});
						}
					});
		    if(pagePub){
		    	url=$webroot + 'modular-load/loadHotImgPub';
		    }
		    $.eAjax({
				url : url,
				data : {'itemPropId' : hotItemPropId,"pageId":pageId},
				async: false,
				success : function(returnInfo){
					var lnkHotSpot=$('a.lnkHotSpot');//热点点击
					if (lnkHotSpot.length==0){//预览页
						$('#imgMap_'+hotItemPropId).imagesLoaded(function(){
							var imgw=$('#imgMap_'+hotItemPropId).find('img').width();
							var imgDiffLeng=parseFloat((1920-imgw)/2);
							var map=$('<map name="planetmap"></map>');
							for (var i = 0; i < returnInfo.hotImgList.length; i++) {
								var picHotPre=returnInfo.hotImgList[i];
								var pos=picHotPre.relativeCoord.split(',');
								var left_px=parseFloat(parseFloat(pos[0])-imgDiffLeng);
								var top_px=parseFloat(pos[1]);
								var right_px=parseFloat(left_px+parseFloat(pos[2]));
								var botton_px=parseFloat(parseFloat(pos[1])+parseFloat(pos[3]));
								$(map).append('<area shape="rect" target="_blank" coords="'+left_px+','+top_px+','+right_px+','+botton_px
										+'" href="'+picHotPre.urlInfo+'" title="'+picHotPre.showTitle+'" alt="'+picHotPre.showTitle 
										+'" hidefocus="true" onfocus="this.blur()">');
							}
							$('#imgMap_'+hotItemPropId).append(map);
						});
					}else{//编辑页
						for (var i = 0; i < returnInfo.hotImgList.length; i++) {
							var picHotPre=returnInfo.hotImgList[i];
							var pos=picHotPre.relativeCoord.split(',');
							hotImg.addPos([ [ parseFloat(pos[0]),parseFloat(pos[1]),parseFloat(pos[2]),parseFloat(pos[3]) ] ],false,picHotPre.id);
							$('div.hotBox',$('#imgMap_'+hotItemPropId)).eq(i).attr({'id':picHotPre.id,'urlInfo':picHotPre.urlInfo,'showTitle':picHotPre.showTitle});
						}
					}
				}
			});
		    //添加热点
			$('a.lnkHotSpot').die('click.bs').live('click.bs',function(){
				hotImg.addPos([ [ 600, 100, 100, 100 ] ]);
			});
			//删除链接
		    $('.hotImg-content .delete').live('click',function(){
		    	var main = $(this).closest('div.template');
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
										+ ',' + parseFloat($(this).css("height"));//left,top,width,height
		    	var main = $(this).closest('div.template');
		    	var itemId = $('#itemId',$(main)).val();
				var pageId = $('#pageId',$(main)).val();
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
		            	if(null!=data && null!=data.results && null!=data.results[0].id)
		            	$this.attr('id',data.results[0].id);
		            }
		        });
		    });
		}
	};

	$.fn.modular_prom_specialheader = function() {
		return this.each(function() {
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			$modularSpecialHeader.control(this,opts);
			return $(this);
		});
	};
	
	
}));
