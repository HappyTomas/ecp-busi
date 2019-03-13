;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(["ecp-component/wap/cms-url-tool"], function(urlTool) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-wap-seckill").modular_wap_seckill();
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
	};
	var _gdsHander = (function(){//商品处理器
			var isPub = false;
			var webRoot = $webroot||"";
			var isPc = false;
			var colorClass=["color-blue","color-org","color-red"];
			var gdsJson = {
				basePrice:5000,
				killPrice:2500
			}
			var gdsTemplat = function(gdsJson,i){
				if(!gdsJson){
					return "";
				}
				if(!isPub || isPc){
					gdsJson.detailURL = null;
				}
				i = parseInt(i) || 0;
				var liStr = "";
				liStr +="<li class='"+colorClass[i%3]+"'>";
				liStr += 	"<div class='pro-img'>";
				if(gdsJson.url){
					liStr += 	'<img class="img" src="'+gdsJson.url+'" alt="'+(gdsJson.gdsName||"")+'">';	
				}
				liStr += 	"</div>";
				liStr += 	"<div class='price-box'>";
				liStr += 		"<p class='p-price'>"+(gdsJson.killPrice?("&yen;"+ebcUtils.numFormat(accDiv(gdsJson.killPrice,100),2)):"");
				//liStr +=             " <span>"+(gdsJson.basePrice?("&yen;"+ebcUtils.numFormat(accDiv(gdsJson.basePrice,100),2)):"")+"</span>";
				liStr +=		"</p>";
				liStr += 		"<p><a href='"+_eCmsUrlTool.getHtmlAbsUrl(webRoot,gdsJson.detailURL)+"'>立即抢购</a></p>";
				liStr += 	"</div>";
				liStr += "</li>";
				return liStr;
			}
			var gdsHander = {
				"setIsPub":function(p){
					isPub = p?true:false;
				},
				"getIsPub":function(){
					return isPub;
				},
				"setWebRoot":function(p){
					webRoot	= p ||$webroot||'';		
				},
				"setIsPc":function(p){
					isPc = p?true:false;
				},
				"gdsShader":function(gdsList,el){
					var $content = $("ul",el);
					$content.empty();
					if(!gdsList || 0 >= gdsList.length){
						gdsList = [gdsJson,gdsJson,gdsJson];//用于预览页
					}
					var len = gdsList.length;
					for(var  i = 0;i < len ;i++){
						$content.append(gdsTemplat(gdsList[i],i));
					}
				},
				"doHref":function(el){
					if(isPub && !isPc){
						var $dataList = $("a[lazy-href]",$(el));
						$dataList.each(function(){
							var href = $(this).attr("lazy-href");
							if(href){
								$(this).attr("href",_eCmsUrlTool.getHtmlAbsUrl(webRoot,href));
							}
							$(this).removeAttr("lazy-href");
						});
					}
				}
			}
			
			return gdsHander;
	})();
	$modularWapSeckill = {
		/**
		 * 获取秒杀商品数据
		 */
		"getData" : function(el,opts){
			$.eAjax({
				url : $webroot + '/cmscommongetdata/qryseckillforwap',
				data : {
					"siteId":opts.siteId,
					"pageSize" : opts.pageSize,
					"placeHeight":opts.placeHeight,
					"placeWidth" : opts.placeWidth,
				},
				async : true,
				type : "post",
				dataType : "json",
				success : function(data, textStatus) {
					if(opts.callback && $.isFunction(opts.callback)){
						opts.callback(data);
					}
				},
				error :  function(){
					if(opts.callback && $.isFunction(opts.callback)){
						opts.callback(null);
					}
				}
			});
		},
		"control":function(el,opts){
			_gdsHander.setIsPub(opts.isPub);//设置商品处理器的模式
			_gdsHander.setWebRoot(opts.webRoot);//设置商品处理器的根地址
			_gdsHander.setIsPc(opts.isPc);//设置商品处理器是否为pc模式
			
			$modularWapSeckill.getData(el,{
				"siteId":opts.siteId,
				"pageSize" : opts.pageSize,
				"placeHeight":opts.placeHeight,
				"placeWidth" : opts.placeWidth,
				"callback":function(datas){
					$modularWapSeckill.doData(el,datas,opts);
				}
			});
		},
		"doData":function(el,datas,opts){
			if(!datas){
				datas={};
			}
			if('ok' == datas.resultFlag || !_gdsHander.getIsPub()){
				$modularWapSeckill.startUp(el);
				_gdsHander.gdsShader(datas.gdsList,el);
				_gdsHander.doHref(el);
			}else{
				$modularWapSeckill.shutDown(el);
			}
			
		},
		"shutDown":function(el){
			if(el && $(el).length > 0){
				$(el).remove();
			}
		},
		"startUp":function(el){
			if(el && $(el).length > 0){
				$(el).removeClass("hide");
				$(el).show();
			}
		}
	};
	$.fn.modular_wap_seckill = function() {//入口
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){
			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			if(null == opts){
				opts = {};
			}
			$modularWapSeckill.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
