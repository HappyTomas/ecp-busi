/**
 * 基于bootstrap的链接选择工具  字数限制
 * zhanbh
 * 插件本省依赖：
 * pageConfig/utils/cms-link-input.css
 * 
 * 用于连接内容获取需依赖以下文件：
 * pageConfig/utils/controller/CmsLinkInputUtil.java
 * velocity/pageConfig/utils/linkInput文件夹下所有VM文件
 * js-busi/pageConfig/utils/linkInput文件夹下所有js文件
 */
;$(function(){
	!function ($) {
//tools---------------------------------------------------------------//
		var _toLocalLowerCase = function(str){
			if(str != null){
				str =  (str+"").toLocaleLowerCase();
			}
			return str;
		}
		
//插件依赖函数定义----------------------------------------------------------//
		//输入框基本html
		var _$inputBaseEl = $(
			'<div class="cms-link-input">'+
			    '<input type="text">'+
			    '<span class="cms-link-add">'+
			        '<i class="cms-ico-link"></i>'+
			    '</span>'+
			'</div>');
		
		//输入框初始化
		var _linkInputBaseInit=function(el,p){
			var $el = $(el);
			if(!$el || $el.length<=0){
				return $el;
			}
			$el.each(function(){
				//只有类型为text 的  input会初始化样式
				var elDom = $(this)[0];
				if(_toLocalLowerCase(elDom.nodeName) == 'input'&& _toLocalLowerCase($(this).attr("type")) == 'text'){
					var $input = $(this);//将页面的输入框
					
					//初始化样式
					if($input.parent("div.cms-link-input").length<=0){//没初始化过的才会初始化样式
						var $inputBaseEl =_$inputBaseEl.clone(true);
						$input.replaceWith($inputBaseEl);
						$inputBaseEl.find("input[type=text]").replaceWith($input);
					}
					
					//初始化事件
					var $linkBtn = $input.next("span.cms-link-add");//activated
					if($linkBtn && $linkBtn.length > 0){
						_linkInputEventInit($input,$linkBtn,p);
					}
				}else{
					_linkInputEventInit(null,elDom,p);
				}
			});
			return $el;
		};
		
		//弹出事件的绑定
		var _linkInputEventInit = function($input,el,p){
			$input = $($input);
			var $el = $(el);
			if(!$el || $el.length<=0){
				return $el;
			}
			$el.each(function(){
				$(this).unbind("click.link").bind("click.link",function(){
					var thisParams = $.extend(true,{},p);
					thisParams.types = thisParams.types || _default.types;
					bDialog.open({
						title : thisParams.title||_default.title,
						width : thisParams.width||_default.width,
						height : thisParams.height||_default.heigth,
						url : $webroot + 'cmslinkinpututil?types='+thisParams.types.toString(),
						params : {"types":thisParams.types,"busiParams":thisParams.busiParams,"siteId":thisParams.siteId,"flatType":thisParams.flatType},
						callback:function(data){
							_dialogOpenCallback(data,thisParams,$input);
						}
					});
				});
			});
		}
		
		//弹出窗的回调函数
		var _dialogOpenCallback=function(data,elParams,$input){
			if(data && data.results && data.results.length > 0){//如果未进行选择则不必要进行处理
				data = data.results[0];
				if(data.id !=null || data.name != null){//id与name有值表示选择成功
					data.success=true;
					//拼接url
					if(data.typeName && elParams && elParams.urlMap){
						data.url = data.id;
						var urlTemp = elParams.urlMap[data.typeName]+"";
						
						//分类的特殊处理s
						if(data.typeName == "catg"){
							if(data.id != null){
								var urlTemp = elParams.urlMap["catgCode"]+"";
							}else if(data.name != null){
								var urlTemp = elParams.urlMap["keyWord"]+"";
								data.url = data.name;
							}
						}
						//分类的特殊处理e
						
						if(urlTemp && urlTemp.indexOf(_toLocalLowerCase("{key}")) >=0){
							data.url=urlTemp.replace(/\{key\}/ig,data.url);
						}
					}
				}else{
					data.success=false;
				}
				//调用用户定义的回调函数
				if(elParams && elParams.callback && $.isFunction(elParams.callback)){
					data.el=$($input);
					elParams.callback(data)
				}
			}
		}
//默认配置参数-------------------------------------------------------------//
		var _default = {
			"width":860,
			"heigth":500,
			"title":"链接小工具",
			"siteId":1,
			"types":["good","catg","secondpage","prom","sitenav"],
			//info-信息,prom-活动,good-商品,catg-分类,homepage-首页,secondpage-二级页,sitenav-站内导航
			"busiParams":undefined,//格式为{"good"：{"siteId":1}}
			"flatType":"mobile",//web , app , mobile 
			"urlMap":undefined,//格式为{"good":"gdsdetail/{key}-""}
		    "callback":function(data){//默认行为 将url 回填到input中
		    	if(data && data.success == true && data.url){
		    		$(data.el).val(data.url);
		    	}
		    }  //回调参数格式({'name':'',id:'','url':'','typeCode':'','typeName':'',success:true,el:$(el)})
		}
		
		var _defaultUrlMap = {
				"web":{
					"catgCode":"search?category={key}",
					"keyWord":"search?keyword={key}",
					"secondpage":"modularcommon?pageId={key}",
					"prom":"modularcommon?pageId={key}",
					"sitenav":"{key}"
				},
				"app":{
					"catgCode":"/pmph/goodList/pageInit?catgCode={key}",
					"keyWord":"/pmph/goodList/pageInit?keyWord={key}",
					"sitenav":"{key}"
				},
				"mobile":{
					"info":"/pmph/info/infoDetail?infoId={key}",
					"good":"gdsdetail/{key}-",
					"catgCode":"search?category={key}",
					"keyWord":"search?keyword={key}",
					"secondpage":"modularcommon/second/{key}",
					"prom":"modularcommon/prom/{key}",
					"sitenav":"{key}"
				}
		}
//插件定义---------------------------------------------------------------//
		$.fn.cmsLinkInputInit=function(p){
			return this.each(function(){
				//合并用户参数与默认参数  并验证必须的参数
				var userParams = $.extend({},_default,p);
				userParams = $.extend(true,{},userParams);
				if(userParams.flatType==null){
					userParams.flatType = _default.flatType;
				}
				if(userParams.types == null || userParams.types.length <= 0){
					userParams.types = _default.types;
				}
				//合并地址映射对象
				userParams.urlMap = $.extend({},_defaultUrlMap[userParams.flatType],userParams.urlMap);
				
				_linkInputBaseInit($(this),userParams);
			});
		}
		
	}(window.jQuery);
	
//字数限制插件定义------------------------------------------------------------//	
	!function ($) {
		//输入框基本html------------------------------------
		var _$inputBaseEl = $(
			'<div class="cms-p-w-limit">'+
			    '<input  />'+
			    '<span class="cms-limit-tip">'+
			    '</span>'+
			'</div>');
		//插件初始化---------------------------------------------
		$.fn.eCmsInputWorkLimit=function(){
			return this.each(function(){
				var $this =$(this);
				//初始化dom
				var $inputBaseEl =$this.parent("div.cms-p-w-limit")
				if($inputBaseEl.length<=0){//没初始化过的才会初始化样式
					$inputBaseEl =_$inputBaseEl.clone(true);
					$this.replaceWith($inputBaseEl);
					$inputBaseEl.find("input").replaceWith($this);
				}
				var $tip = $inputBaseEl.children(".cms-limit-tip").eq(0);
				
				//初始化事件
				var maxLength = parseInt($this.attr("max-length"));
				if(maxLength > 0 && $tip.length > 0){
					var inputFn = function(){
						$this.attr("maxlength",maxLength);
						var value = $this.val();
						var valLen = 0;
						if(value){
							valLen = value.length;;
						}
						
						$tip.text(valLen+"/"+maxLength);
					}
					var blurFn = function(){
						var value = $this.val();
						var valLen = 0;
						if(value){
							valLen = value.length;;
						}
						if(valLen > maxLength){
							$this.val(value.substring(0,maxLength));
						}
					}
					$this.unbind("input.wl").bind("input.wl",inputFn);
					$this.unbind("change.wl").bind("change.wl",blurFn);
					$this.trigger("change.wl");
					$this.trigger("input.wl");
				}
			});
		};
		$("input.cms-input-w-limit[type=text],textarea.cms-input-w-limit").eCmsInputWorkLimit();
	}(window.jQuery);
});