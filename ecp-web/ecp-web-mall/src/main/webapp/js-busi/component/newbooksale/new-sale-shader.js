;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(['jquery-powerSwitch','ecp-component/newbooksale/new-book-sale-data' ], function(slide,newBookData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".new-sale-shader").new_sale_shader();
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

	$newSaleShader = {
		/**
		 * 生成商品分类菜单信息；
		 * @param el
		 * @param images
		 * @author zhanbh
		 */
		"doData" : function(el, datas) {
			$(el).empty();
			$(el).html(datas);
			/*$('.clamp',el).each(function(){
				alert($.trim($(this).text()));
				$newSaleShader.initNameLength(this,45);
			})*/
			$newSaleShader.newBookSwitch(el);
		},
		/**
		 * 初始化书名的长度，显示两行，超过用...表示
		 * @param el
		 * @param datas
		 */
		"newBookSwitch":function(el) {
			var wordLimit=function(objs,h){
                objs.each(function(){
                	if($(this).parents('li.newBookSaleLi').css('display')!='none'){
                		 limit($(this),45);
                	}
                })
                function limit(o,h){
                    if(o.height()>h){
                        var txt= $.trim(o.text());
                        o.text(txt.substring(0,txt.length-3));
                        o.html(o.html()+'…');
                        limit(o,h);
                    }
                }
            }
			
            wordLimit($('.clamp',el),45);
            $(".dian-box a",el).powerSwitch({
                classAdd: "current",
                animation: "translate",
                hoverStop: false,
                container: $(".switchbtn"),
                onSwitch:function(item){
                    wordLimit($('.clamp',item),45);
                }
            });
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			$(".new-sale-content",el).append("<div class='loading-small'></div>");
			$newBookSaleData.getData({
				"gdsSize" : opts.gdsSize,
				"placeSize" : opts.placeSize,
				"menuType" : opts.menuType,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"versionType" : opts.versionType,
				"callback":function(datas){
					$newSaleShader.doData(el,datas);
				}
			});
		}
	};

	$.fn.new_sale_shader = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
//			if(opts == null || opts.placeId =="" || opts.placeId=="undefined"){
//				return ;
//			}
			$newSaleShader.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
