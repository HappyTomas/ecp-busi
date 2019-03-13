/**
 * 页面编辑
 * @author gxq
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
	//页面业务逻辑处理内容
    var pageInit = function(){
        var init = function(){
			var getUrl = function(modularType){
				var param = {};
				switch (modularType) {
				case "01"://专题页头
					param.url = '';
					param.requestVmName = "promhomepage/prom-specialheader";
					param.title = "专题页头";
					break;
				case "05"://宝贝图片
					param.url = '';
					param.requestVmName = "promhomepage/prom-gdspictrues";
					param.title = "宝贝图片";
					break;
				case "06"://楼层导航
					param.url = '';//modular-load/floorNavInit
					param.requestVmName = "promhomepage/prom-floornav";
					param.title = "楼层导航";
					break;
				case "23"://商品排行
					param.url = '';
					param.requestVmName = "mallhomepage/mall-gdsrankinglist";
					param.title = "商品排行";
					break;
				case "24"://商品展示
					param.url = '';
					//弹出框请求展示的vm名称
					param.requestVmName = "gds-show";
					param.title = "商品展示";
					break;
				case "10"://店铺分类
					param.url = '';
					//弹出框请求展示的vm名称
					param.requestVmName = "shophomepage/shop-category";
					param.title = "店铺分类";
					break;	
				case "21"://轮播图片
					param.url = '';
					param.requestVmName = "shophomepage/shop-advertise";
					param.title = "轮播图片";
					break;
				case "20"://商城公告
					param.url = '';
					param.requestVmName = "mallhomepage/mall-announcement";
					param.title = "商城公告";
					break;
				case "22"://用户热评
					param.url = '';
					param.requestVmName = "mallhomepage/mall-hoteval";
					param.title = "用户热评";
					break;
				case "25"://首页导航
					param.url = '';
					param.requestVmName = "common-headnav";
					param.title = "首页导航";
					break;
				case "11"://店铺信息
					param.url = '';
					param.requestVmName = "shophomepage/shop-info";
					param.title = "店铺信息";
					break;
				case "12"://店铺热卖
					param.url = '';
					param.requestVmName = "shophomepage/shop-hotsale";
					param.title = "店铺热卖";
					break;
				case "13"://店铺内搜索
					param.url = '';
					param.requestVmName = "shophomepage/shop-innerindex";
					param.title = "店铺内搜索";
					break;
				case "26"://首页商品分类
					param.url = '';
					param.requestVmName = "mallhomepage/mall-category";
					param.title = "商品分类";
					break;
				case "29"://首页猜你喜欢
					param.url = '';
					param.requestVmName = "mallhomepage/mall-guess";
					param.title = "猜你喜欢";
					break;
				case "28"://新品上市
					param.url = '';
					param.requestVmName = "mallhomepage/mall-new-gds";
					param.title = "新品上市";
					break;
				case "02"://平铺图片
					param.url = '';
					param.requestVmName = "mallhomepage/mall-leaflets";
					param.title = "平铺图片";
					break;
				case "27"://二级页新品预告
					param.url = '';
					param.requestVmName = "secondpage/second-newgdsnotice";
					param.title = "新品预告";
					break;
				case "30"://二级页商品推荐
					param.url = '';
					param.requestVmName = "secondpage/second-gdsrecommend";
					param.title = "商品推荐";
					break;
				case "31"://秒杀列表
					param.url = '';
					param.requestVmName = "promhomepage/prom-seckillList";
					param.title = "秒杀列表";
					break;
				default://公共属性处理页面
					param.url = 'modular-dynamic/publicSet';
					break;
				}
				return param;
			};
			/**
			 * 布局项目设置后，将内容回填到页面上
			 */
			$('a.lnkModuleEdit').click(function(e){
				var main = $(this).closest('div.modular');
				if(main.size() == 0) main = $(this).closest('div.skipfloor');
				var itemId = $('#itemId',$(main)).val();
				var pageId = $('#pageId',$(main)).val();
				var modularId = $('#modularId',$(main)).val();
				var modularType = $('#modularType',$(main)).val();
				var modularName = $('#modularName',$(main)).val() || "";
				var requestVmName = $('#componentEditUrl',$(main)).val();//编辑窗路径
				var componentVmUrl = $('#componentVmUrl',$(main)).val();//组件头部vm路径
				/**
				 * 在原有的基础上进行改造。通过用户传的自身要渲染的vm名称，来请求数据。vm一定要放在modular目录下、
				 */
				var param = {url:''};//getUrl(modularType);
				var url = "";
				if(param.url != ""){
					url = param.url;
					url = $webroot + url + '?pageId='+pageId+'&itemId=' + itemId + '&modularId=' + modularId+ '&modularType=' + modularType;
				}else{
					url = $webroot +'commonmodular/modularcommonload?pageId='+pageId+'&itemId=' + itemId + '&modularId=' + modularId+'&requestVmName=' +requestVmName+'&pageTypeId='+$("#pageTypeId").val();
				}
				var width = 700,height = 500;
				bDialog.open({
		            title : modularName+"设置",
		            width : width,
		            height : height,
		            url : url,
		            callback:function(data){
		            	if(data && data.results && data.results.length > 0 ){
		            		if(data.results[0].param=="saveBtn"){
		            			//window.location.reload(); 
		            			$.eAjax({
		            				url : $webroot +'page-edit/getItemComponentVm',
		            				async : true,
		        					type : "post",
		        					dataType : "html",
		            				data : {
		            					"id":itemId,
		            					"componentVmUrl":componentVmUrl
		            				},
		            				success : function(returnInfo){
		            					//进行局部刷新     表单的保存及wap的局部刷新在modular-common.js  
		            					$('.ecp-component,.nodata',$(main)).remove();
		            					$(main).prepend(returnInfo);
		            					
		            					//有组件则启动组件
		            					var className = $(".ecp-component",$(main)).data("componentMethod") || "";
		            					var method = className.replace(/-/ig,"_");
		            					if(className && method){
		            						$("."+className,$(main))[method]();
		            					}
		            				}
		            			});      	
		            		}
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
							var _thismain = $(_this).closest('div.tItem');//列
							var itemObj = $(_this).closest('div.modular');//模块
							if(itemObj.size() == 0) {
								_thismain = $(_this).closest('div.skipfloor');
								itemObj=_thismain;
								//itemObj = null;
							}
							var itemId = $('#itemId',$(itemObj)).val();
							$.eAjax({
								url : $webroot + 'page-edit/removeLayoutItem',
								data : {'itemId' : itemId},
								success : function(returnInfo){
									if(returnInfo && $.type(returnInfo.resultFlag)!='undefined' && returnInfo.resultFlag == 'ok'){
										$(itemObj).remove();
									}
								}
							});
						}
					},{caption : '取消',callback : $.noop}]
				});
				e.preventDefault();
			});
	        
        };
        
        return {
            "init" : init
        };
    };
    pageConfig.config({    	
        //指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
        plugin : ['ePageTop'],
        //指定页面
        init : function(){
            var p = pageInit();
            p.init();
        }
    });
});