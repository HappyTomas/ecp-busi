/**
 * 
 * 布局项目渲染公共脚本
 * 
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
 * 
 */
var itemRender = {
	//图片服务器URL前缀
	imgUrl : '',
	/**
	 * 渲染统一入口
	 * @param main 进行内容渲染的块
	 * @param data JSON数据
	 */	
	doRender : function(main,data,isPub){
		var item = data.itemProp;
		if(item && $.type(item.modularRespDTO)!='undefined'){
			var mod = item.modularRespDTO;
			if(null!=mod && null!=mod.modularType){
				switch (mod.modularType) {
				case '01'://专题页头
					itemRender.specialRender(main,item,isPub);
					break;
				case '05'://宝贝图片
					itemRender.goodsPicsRender(main,item,isPub);
					break;
				default:
					break;
				}
			}else {
				$('div.nodata',$(main)).show();
				$(main).addClass('template-4');
			}
		}
	},
	/**
	 * 专题页头
	 * @param main  内容块
	 * @param data  数据
	 * @param isPub 是否为发布模式
	 */
	specialRender : function(main,data,isPub){
		var list = isPub ? data.itemPropPubRespDTOList : data.itemPropPreRespDTOList;
		if(list && $.isArray(list) && list.length > 0){
			$('img',$(main)).remove();
			var template=$('.template',$(main));
			var havePic = false;
			$.each(list, function(i, row) {
				//propId:10标题propId:11图片
				if(row.propId == '11' && row.propValue){
					var url = cmsTemplate.loadImgUrlById(row.propValue);
					//TODO:目前仅使用样式强制控制图片高度为600px
					$(template).append('<div id="imgMap_'+row.id+'" class="hotImg-content"><img src="' + url + '" usemap="#planetmap" /></div>');//class="imgFullWidth"
					havePic = true;
					//初始化热点
					
					var hotItemPropId=$('#hotItemPropId',$(main)).val();
					var pageId = $('#pageId',$(main)).val();
					if (typeof(hotItemPropId) == "undefined" || null ==hotItemPropId){//预览页
						var hotImg = $('#imgMap_'+row.id).hotMaps();
						var imgw=$('#imgMap_'+row.id).find('img').width();
						var imgDiffLeng=parseFloat((1920-imgw)/2);
						var pos=hotImg.getHotPos();
				        var map=$('<map name="planetmap"></map>');
				        var url="";
				        if(isPub){
				        	url=$webroot + 'modular-load/loadHotImgPub';
				        }else{
				        	url=$webroot + 'modular-load/loadHotImg';
				        }
				        $.eAjax({
							url : url,
							data : {'itemPropId' : row.id,"pageId":row.pageId},
							async: false,
							success : function(returnInfo){
//								debugger;
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
							}
						});
				        $('#imgMap_'+row.id).append(map);
					}else{//编辑页
						$.eAjax({
							url : $webroot + 'modular-load/loadHotImg',
							data : {'itemPropId' : hotItemPropId,"pageId":pageId},
							async: false,
							success : function(returnInfo){
								//debugger;
								var hotImg = $('#imgMap_'+hotItemPropId,$(main)).hotMaps();
								for (var i = 0; i < returnInfo.hotImgList.length; i++) {
									var picHotPre=returnInfo.hotImgList[i];
									var pos=picHotPre.relativeCoord.split(',');
									hotImg.addPos([ [ parseFloat(pos[0]),parseFloat(pos[1]),parseFloat(pos[2]),parseFloat(pos[3]) ] ],false,picHotPre.id);
									$('div.hotBox',$('#imgMap_'+hotItemPropId)).eq(i).attr('id',picHotPre.id);
								}
							}
						});
					}
				}
			});
			//无图片设置时，显示默认点位图片
			if(havePic) $('div.nodata',$(main)).hide();
			else $('div.nodata',$(main)).show();
		}else $('div.nodata',$(main)).show();
	},
	/**
	 * 楼层导航
	 * @param main 内容块
	 * @param isPub 是否为发布模式
	 */
	floorNav : function(main,isPub){
		var itemId = $('#itemId',$(main)).val();
		var list = $('ul.navigation',$(main));
		if(!itemId) return;
		var requestPath = 'page-pre';
		if(isPub) requestPath = 'page-pub';
		$.eAjax({
			url : $webroot + requestPath + '/loadLayoutItemProp',
			data : {'itemId' : itemId},
			success : function(returnInfo){
				if(returnInfo && $.type(returnInfo.itemProp)!='undefined' && $.isPlainObject(returnInfo.itemProp)){
					var item = returnInfo.itemProp;
					var l = isPub ? item.itemPropPubRespDTOList : item.itemPropPreRespDTOList;
					if(l && $.isArray(l) && l.length > 0){
						$(list).empty();
						$.each(l, function(i, elt) {
							//目前只能依据PropId来决定属性类型
							//propId:60(导航图片)propId:61(层楼)
							if(elt.propId == '61') $(list).append('<li><a href="#layoutFloor_'+elt.propGroupId+'">'+(null==elt.propValue?'&nbsp;':elt.propValue)+'</a></li>');
							if(elt.propId == '60'){
								var img = cmsTemplate.loadImgUrlById(elt.propValue);
								if(img!=null&& img.length!=0){
									$('h2.top-img',$(main)).find('img').remove();
									$('h2.top-img',$(main)).append('<img src="'+img+'">');
								}
							}
						});
					}
				}
			}
		});
		//获得父容器
		var parentBox = $(main).closest('div.page-edit');
		//非编辑页面则设置导航功能
		if(!$(parentBox).hasClass('page-edit')){
			//设置导航效果
			$('ul.navigation',$(main)).onePageNav({
			    currentClass: 'current',
			    changeHash: false,
			    scrollSpeed: 250,
			    scrollThreshold: 0.5,
			    filter: '',
			    easing: 'swing',
			    scrollChange: function($currentListItem) {
			        //I get fired when you enter a section and I pass the list item of the section
			    }
			});
			
			//回到顶部统一处理
			$('span.backToTop',$(main)).click(function(e) {
				$('body,html').animate({"scrollTop":0},250);
			});
		}
	},
	/**
	 * 宝贝图片
	 * @param main  内容块
	 * @param data  数据
	 * @param isPub 是否为发布模式
	 */
	goodsPicsRender : function(main,data,isPub){
		var list = isPub ? data.itemPropPubRespDTOList : data.itemPropPreRespDTOList;
		if(list && $.isArray(list) && list.length > 0){
			var configType = '',maxGoods = 0,rowNum = 0;
			var ids = '',prefix = '',url='',clsPrefix = 'template-',gdsFloorName = '';
			$.each(list, function(i, row) {
				if(row){
					//获得楼层名称
					if(row.propId == '1') gdsFloorName = row.propValue;
					//获得宝贝数量
					if(row.propId == '2') maxGoods = row.propValueId;
					//获得展示方式
					if(row.propId == '3') rowNum = Number(row.propValue);
					//获得宝贝图片的配置方式
					//1：手工配置2：获取楼层
					if(row.propId == '4') configType = row.propValueId;
					//ID：5为宝贝数据属性
					if(row.propId == '5'){
						if(ids.length > 0) prefix = ',';
						if($.type(row.propValue)!='undefined' && row.propValue != 'null' && row.propValue != null)
							ids += prefix + row.propValue;
					}
				}
			});
			//根据一行显示多少个宝贝的数量，套用相应的样式
			if(null==rowNum||0==rowNum)rowNum=4;
			$(main).addClass(clsPrefix + rowNum);
			$(main).addClass("promTpl-list");
			var template=$('.template',$(main));
			//1：手工配置2：获取楼层
			if(configType=='1') url = $webroot + 'page-pre/loadGoodsInfo';
			else if(configType=='2') url = $webroot + 'page-pre/loadGoodsInfoByFloor';
			$.eAjax({
				url : url,
				data : {'ids' : ids,'date': (new Date()).getTime()},
				success : function(returnInfo){
					if(returnInfo && $.type(returnInfo.goodslist)!='undefined' && $.isArray(returnInfo.goodslist) && returnInfo.goodslist.length > 0){
						//移除原有展示的内容
						$('div.nodata',$(main)).remove();
						$('div.floor-tab',$(main)).remove();
						$('ul.goodsPicsRow',$(main)).remove();
						//添加新内容
						$(template).append('<div class="floor-tab"><div class="name-wrap"><h2 class="name">'+(gdsFloorName!=null?gdsFloorName:' ')+'</h2></div></div>');
						$(template).append('<div class="tUl-wrap"><ul class="tUl goodsPicsRow clearfix"></ul></div>');
						var row = $('ul.goodsPicsRow',$(main));
						var gdss = returnInfo.goodslist;
						$(row).empty();
						$.each(gdss, function(i, gds) {
							if(i == maxGoods) return false;
							var gdsInfo = {
								id : gds.id,
								name : gds.gdsName,
								pic : '',
								url : gds.url
							};
							if($.type(gds.mainPic)!='undefined' && $.isPlainObject(gds.mainPic)) gdsInfo.pic = gds.mainPic.mediaUuid;
							if($.type(gds.skuInfo)!='undefined' && $.isPlainObject(gds.skuInfo)){
								if($.type(gds.guidePrice)!='undefined' && gds.guidePrice!='null'){
									gdsInfo.basePrice = ebcUtils.numFormat(gds.guidePrice,2);//指导价
								}
								//现价/活动价
								if($.type(gds.skuInfo.discountPrice)!='undefined' && gds.skuInfo.discountPrice!='null')
									gdsInfo.discountPrice = ebcUtils.numFormat(gds.skuInfo.discountPrice,2);
								else
									gdsInfo.discountPrice = ebcUtils.numFormat(gds.skuInfo.realPrice,2);//若没有discountPrice则获取realPrice
							}
							gdsInfo.picUrl = cmsTemplate.loadImgUrlById(gdsInfo.pic);
							//http://image.pmph.com/imageServer/image/565333a30cf214b58f0fb3f3_176x176!.jpg
							var item = '<li class="tLi">';
							item += '<div class="li-inner">';
							item += 	'<a href="'+gdsInfo.url+'" title="'+gdsInfo.name+'" target="_blank">';
							item += 		'<div class="tpl-img" ><img src="'+gdsInfo.picUrl+'" ></div>';
							item += 		'<p class="pro-tit">'+gdsInfo.name+'</p>';
							item += 		'<div class="pro-pri">';
							item += 			'<div class="pri">';
							item += 				'<p><del>参考价:&yen;'+(gdsInfo.basePrice/100).toFixed(2)+'</del></p>';
							item += 				'<p class="act">活动价:&yen;'+(gdsInfo.discountPrice/100).toFixed(2)+'</p>';
							item += 			'</div>';
							item += 			'<a href="'+gdsInfo.url+'" target="_blank" class="pro-btn">立即购买</a>';
							item += 		'</div>';
							item += 	'</a>';
							item += '</div>';
							item += '</li>';
							$(row).append(item);
						});
					}else{
						//无宝贝数据
						$('div.nodata',$(main)).show();
					}
				}
			});
		}else $('div.nodata',$(main)).show();//无宝贝数据
	},
	/**
	 * 背景图片显示
	 */
	bgImgRender : function(){
		//获得父容器
		var parentBox = $('div.page-edit');
		var repeatType = $("#attr-backgroupShowType").val();
		if(repeatType == "01"){
			repeatType = "repeat";
		}else if(repeatType == "02"){
			repeatType = "no-repeat";
		}else if(repeatType == "03"){
			repeatType = "repeat-y";
		}
		
		if($("#attr-backgroundPic").val()){
			var imgUrl = cmsTemplate.loadImgUrlById($("#attr-backgroundPic").val());
			$(parentBox).css({
				"background-image":"url("+imgUrl+")",
				"background-repeat":repeatType
			});
		}else{
			$(parentBox).css({
				"background-image":"none",
				"background-repeat":repeatType
			});
		}
	},
	/**
	 * 标题颜色显示
	 */
	titleBgCRender : function(){
		//获得父容器
		var parentBox = $('div.page-edit');
		var matchingColr = $("#attr-titleBackgroundColor").val();
		var css_name = "";
		switch(matchingColr){
			case "1":
				css_name = "tpl-skin01";
				break;
			case "2":
				css_name = "tpl-skin02";
				break;
			case "3":
				css_name = "tpl-skin03";
				break;
			case "4":
				css_name = "tpl-skin04";
				break;
			case "5":
				css_name = "tpl-skin05";
				break;
			case "6":
				css_name = "tpl-skin06";
				break;
			case "7":
				css_name = "tpl-skin07";
				break;
			case "8":
				css_name = "tpl-skin08";
				break;
			case "9":
				css_name = "tpl-skin09";
				break;
			case "10":
				css_name = "tpl-skin10";
				break;
			case "11":
				css_name = "tpl-skin11";
				break;
			case "12":
				css_name = "tpl-skin12";
				break;
				
		}
		if(css_name==""){
			return;
		}
		css_name +=".css";
		itemRender.loadjscssfile($webroot+'css/pageConfig/skin/'+css_name,"css");
	},
	loadjscssfile: function(filename, filetype){
		if(!filename || !filetype) return;
		if (filetype=="js"){ //if filename is a external JavaScript file 
			var fileref=document.createElement('script') ;
			fileref.setAttribute("type","text/javascript") ;
			fileref.setAttribute("src", filename); 
		}else if (filetype=="css"){ //if filename is an external CSS file 
			var fileref=document.createElement("link"); 
			fileref.setAttribute("rel", "stylesheet");
			fileref.setAttribute("type", "text/css");
			fileref.setAttribute("href", filename);
		} 
		if (typeof fileref!="undefined") {
			try {
				document.getElementsByTagName("head")[0].appendChild(fileref);
			} catch (e) {}
		} 
//		 
//		loadjscssfile("myscript.js", "js") ;//dynamically load and add this .js file 
//		loadjscssfile("javascript.php", "js") ;//dynamically load "javascript.php" as a JavaScript file 
//		loadjscssfile("mystyle.css", "css"); //
	},
};
