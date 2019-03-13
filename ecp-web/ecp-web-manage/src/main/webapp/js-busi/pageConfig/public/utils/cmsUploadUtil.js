/**
 * 在框架已有的图片上传功能基础上改造的一个上传图片分支。
 * zhanbh
 * 依赖的文件：
 * /module/mod-upload.vm
 * pageConfig/utils/cms-img-upload.css
 */
;$(function(){
	!function ($) {
		//文件上传类型处理，统一处理因深度复制导致合并的问题，并统一处理默认参数问题
		var _fileTypeExtHandle = function(p,srcP){
			if(undefined!==srcP.validation.allowedExtensions && 
					$.isArray(srcP.validation.allowedExtensions) && 
					srcP.validation.allowedExtensions.length > 0){
				p.validation.allowedExtensions = srcP.validation.allowedExtensions;
			}else{
				p.validation.allowedExtensions = $.fn.eUpload.defaults.validation.allowedExtensions;
			}
		};
		
		//获取元素自有的独特属性
		var _getElParams = function(el,p){
			var elParams = p;
			
			if($(el).length <= 0){
				return elParams;
			}
			
			//获取展示图片的规格尺寸
			var showHeight = parseInt($(el).attr("show-height"));
			var showWidth = parseInt($(el).attr("show-width"));
			
			if(showWidth && showWidth > 0){
				elParams.showWidth = showWidth;
				elParams.showHeight = undefined;
				if(showHeight && showHeight > 0){
					elParams.showHeight = showHeight;
				}
			}
			
			//获取其他自有属性
			elParams.elWidth = +$(el).attr("el-width") || elParams.elWidth;
			elParams.elHeight = +$(el).attr("el-height") || elParams.elHeight;
			elParams.imageMaxHeight = parseInt($(el).attr("image-max-height")) || elParams.imageMaxHeight;
			elParams.imageMaxWidth = parseInt($(el).attr("image-max-width")) || elParams.imageMaxWidth;
			
			//获取文件大小限制
			var fileSizeLimit = $(el).attr("fileSizeLimit");
			if(fileSizeLimit && fileSizeLimit.match(/^[1-9][0-9]*(?:k|m)b$/i)){
				elParams.fileSizeLimit = fileSizeLimit.toLocaleUpperCase();
			}
			return elParams;
		}
		
		//根据长宽 获取图片规格格式
		var _getImgStandard = function(showWidth,showHeight){
			var standard = "";
			if(showWidth && showWidth > 0){
				standard +="_"+showWidth+"x";
				if(showHeight && showHeight >0){
					standard += showHeight+"";
				}
				standard +="!";
			}
			return standard;
		}
		//根据格式 获取准确的提示文本 {imageMaxWidth}*{imageMaxHeight}尺寸的图片，类型:{fileTypeExts}
		var _getShowText = function(p){
			var text = "";
			if(p && p.showText){
				text = p.showText;
				text = text.replace(/\{imageMaxWidth\}/ig,p.imageMaxWidth);
				text = text.replace(/\{imageMaxHeight\}/ig,p.imageMaxHeight);
				text = text.replace(/\{fileSizeLimit\}/ig,p.fileSizeLimit);
				if(p.fileTypeExts instanceof Array && p.fileTypeExts.length > 0){
					text = text.replace(/\{fileTypeExts\}/ig,p.fileTypeExts.join("、"));
				}
			}
			return text;
		}
		//提供简单上传方式
		//默认不使用批量上传、使用自动上传文件
		//集成Flash安装检查提示
		$.fn.eCmsUploadBaseInit = function(p){
			return this.each(function(){
				//当前上传按钮特殊数据处理 s
				var $this = $(this);
				p = _getElParams($this,p);
				delete p.elWidth;
				delete p.elHeight;
				//当前上传按钮特殊数据处理 e
				
				//合并用户传递参数与默认参数
				var userParams = $.extend({}, $.fn.eUpload.userDefaults, p);
				//组装成为fine-uploader初始参数
				var preParams = {
					request : {
						endpoint : userParams.uploadUrl ? userParams.uploadUrl : undefined,
						inputName : userParams.uploadFileObjName ? userParams.uploadFileObjName : undefined
					},
					multiple : false,
					validation : {
						allowedExtensions : userParams.fileTypeExts,
						sizeLimit : userParams.fileSizeLimit ? ebcUtils.fileSize2Bytes(userParams.fileSizeLimit,true) : undefined,
						sizeLimitStr : userParams.fileSizeLimit ? userParams.fileSizeLimit : undefined,
						image: {
			                maxHeight: userParams.imageMaxHeight,
			                maxWidth: userParams.imageMaxWidth,
			                minHeight: userParams.imageMinHeight,
			                minWidth: userParams.imageMinWidth
			            }
					}
				};
				//与默认参数合并
				var params = $.extend(true, {}, $.fn.eUpload.defaults, preParams);
				_fileTypeExtHandle(params,preParams);
				//设置服务端校验参数
				params.request.params = {
					'fileSizeLimit' : params.validation.sizeLimitStr,
					'fileTypeExts' : params.validation.allowedExtensions.toString()
				};
				
				delete params.template;
				params.button = $(this)[0];//document.getElementById('uploadDivButton');
				
				//错误事件回调
				params.callbacks.onError = function(id,name,errorReason,xhr){
					eDialog.alert(errorReason,$.noop,'error');
				};
				
				if(p && p.callback && $.isFunction(p.callback)){
					params.callbacks.onComplete = function(id,name,json,xhr){
						if(json && $.isPlainObject(json)){
							
							//按规格取图片s
							var limitUrl = json.url;
							if(limitUrl && json.fileId){
								limitUrl = limitUrl.replace(new RegExp(json.fileId+''+"(?:_(?:[0-9])*x(?:[0-9])*!)?"),json.fileId+""+_getImgStandard(p.showWidth,p.showHeight));
							}
							//按规格取图片e
							
							p.callback({
								'el':$this,
								'fileId' : json.fileId,
								'fileName' : name,
								'url' : json.url,
								'limitUrl':limitUrl,
								'success' : json.success
							});
						}
					};
				}
				var upload = new qq.FineUploaderBasic(params);
			});
		};
	  
		
		//提供带样式的上传插件--------------------------------------------------------//
		//重置或者显示图片
		var _setImgVal = function(isReset,$el,limitUrl){
			if(!isReset && !limitUrl){
				return this;
			}
			
			var cmsTxt = "重新上传";
			var imgHtml = "";
			if(isReset == true){
				cmsTxt = "+";
				$("div.cms-upload-img",$el).addClass("cms-upload-add");
			}else{
				$("div.cms-upload-img",$el).removeClass("cms-upload-add");
				imgHtml = "<img src='"+limitUrl+"' alt=''>";
			}
			
			$("div.cms-img",$($el)).html(imgHtml);
			$("div.cms-txt",$($el)).text(cmsTxt);
		}
		//上传图片组件html元素
		var _$uploadItem=$('<div class="cms-upload-img cms-upload-add">'+
                '<div class="cms-img-wrap">'+
                '<div class="cms-img"></div>'+
                '<div class="cms-txt">+</div>'+
                '<div class="cms-shade"></div>'+
             '</div>'+
             '<p class="cms-tip"></p>'+
         '</div>');
		//上传图片组件默认参数
		var _cmsImgDefault={
			 "fileSizeLimit":'1MB',
			 "fileTypeExts":['png','jpg','jepg'],
			 "imageMaxHeight" : 1024,//图片最大高度
			 "imageMaxWidth" : 1024,//图片最大宽度
			 "elWidth" : 250,//html元素宽度
			 "elHeight" : 160,//html元素高度
			 "showWidth" : 250,//图片展示规格宽度
			 "showHeight" :160,//图片展示规格高度
			 "showText":undefined,//上传插件提示语
			 "callback":$.noop
		}
		
		//插件定义
		$.fn.eCmsImageUploadInit = function(p){
			return this.each(function(){
				var $this = $(this);
				//与默认参数合并
				var userParams = $.extend({},_cmsImgDefault,p);
				//元素参数与实际参数合并
				var thisUserParams = $.extend(true,{},userParams);
				
				thisUserParams= _getElParams($this,thisUserParams);
				//元素html清楚及初始化
				$this.empty().append(_$uploadItem.clone());
				//样式初始化
				$this.find(".cms-upload-img").css({"width":thisUserParams.elWidth+'px',"min-height":thisUserParams.elHeight+'px'});
				$this.find(".cms-img").css({"min-height":thisUserParams.elHeight+'px'});
				if(thisUserParams.showText != undefined){
					$this.find("p.cms-tip").text(_getShowText(thisUserParams));
				}
				
				//初始化值
				var fileId = $this.attr("file-id");
				var fileUrl = $this.attr("file-url");
				if(fileId && fileUrl){
					fileId = fileId +"";
					fileUrl = fileUrl.replace(new RegExp(fileId+"(?:_(?:[0-9])*x(?:[0-9])*!)?"),fileId+_getImgStandard(thisUserParams.showWidth,thisUserParams.showHeight));
					_setImgVal(false,$this,fileUrl);
				}
				
				//删除非基础上传参数
				delete thisUserParams.elWidth;
				delete thisUserParams.elHeight;
				delete thisUserParams.showText;
				
				//回调函数合并
				var userCallback = thisUserParams.callback;
				thisUserParams.callback=function(fileInfo){
					if(fileInfo && fileInfo.success == true){
					   var el = fileInfo.el;
					   $this.attr("file-id",fileInfo.fileId);
					   $this.attr("file-url",fileInfo.url);
					   
					   _setImgVal(false,$this,fileInfo.limitUrl);
				     }
					if(userCallback && $.isFunction(userCallback)){
						userCallback(fileInfo); 
					}
					
				}
				
				//执行上传功能初始化
				$this.find(".cms-img-wrap").eCmsUploadBaseInit(thisUserParams);
				
			});
		}
		
		//图片清楚方法------------------------------
		$.fn.eCmsImageUploadClear=function(){
			return this.each(function(){
				var $this = $(this);
				$this.attr("file-id","");
				$this.attr("file-url","");
				_setImgVal(true,$this);
			});
		}
	  }(window.jQuery);
});

