(function($){
	var move = false;
	var cloneDiv = null;
	var targetObj = null;  //选中可以放入的对象s
	var currentObj = null;  //选中拖拽的对象
	var targetSelected = null;//选中的对象
	var selected = false;
	var scrollTop = 0;
	var targetDrag=null;
	$.fn.jsmartdrag = function(options){
		var jQueryObj = this;
		var targetClz='jsmartdrag-target';
		var defaults = {
			sourceClass:"jsmartdrag-source",
			sourceHoverClass:"jsmartdrag-source-hover",
			cursorHoverClass:"jsmartdrag-cursor-hover",
			targetHoverClass:"jsmartdrag-target-hover",
			dragHoverClass:"jsmartdrag-target-drag",
			canSelect:false,
			self:true,
			target:".target",
			onDrag:    function(){},
			afterDrag: function(selected,currentObj,targetObj){}
		};
		options = $.extend(defaults, options);
		var freshTarget=this.freshTarget=function(){
			$(options.target).each(function(){
				$(this).addClass(targetClz);
			})
			targetObj = $('.'+targetClz);
		};
		this.each(function(){
			freshTarget();
			$(this).addClass(options.sourceClass);
			$(this).mousedown(function(){
				$("body").css("-moz-user-select","none");
				if(!options.canSelect){
					document.ondragstart = function () { return false; };
					document.onselectstart = function () {return false; };
				}
				currentObj = $(this);
				move = true;
				$(this).addClass(options.sourceHoverClass);
				cloneDiv = $(this).clone();
				cloneDiv.attr("id","cloneDiv");
				cloneDiv.addClass(options.sourceHoverClass);
				scrollTop = $("html,body").scrollTop();
			});

			if(targetObj.length>0){
				targetObj.each(function(){
					$(this).addClass(targetClz);
				});
			}
		});
		$(document).mousemove(debounce(function(event){
			if(move){
				if($("#cloneDiv").length<=0){
					$("body").append(cloneDiv);
				}
				var dragPos = {x1:0,x2:0,y1:0,y2:0};
				var pageX = 0;
				var pageY = 0;
				if(/msie/.test(navigator.userAgent.toLowerCase())){
					pageX = event.clientX;
					pageY = event.clientY+scrollTop;
				}else{
					pageX = event.pageX;
					pageY = event.pageY;
				}
				dragPos.x1 =pageX-cloneDiv.innerWidth()/2;
				dragPos.y1 = pageY-cloneDiv.innerHeight()/2;
				cloneDiv.css({left:dragPos.x1+"px",top:dragPos.y1+"px",position:'absolute'});
				if(options.self){   //拖拽到内容里面
					if(targetObj.length>0){
						targetObj.each(function(){
							if(checkAreaOverride(cloneDiv,$(this))){
								$(this).addClass(options.targetHoverClass);
							}else{
								$(this).removeClass(options.targetHoverClass);
							}
						});
					}
				}else{
					if(targetObj.length>0){  //拖拽到上下部分
						targetObj.each(function(){
							var place=checkAreaUpDown(cloneDiv,$(this));
							var adom=$(this).data('adom');
							var bdom=$(this).data('bdom');
							if(checkAreaOverride(cloneDiv,$(this))){
								if(!$(this).is(targetSelected)){
									$('.'+options.dragHoverClass).remove();
								}
							}
							if(1 == place && !bdom){
								bdom=$("<div class='"+options.dragHoverClass+"'></div>");
								$(this).before(bdom);
								$(this).data('bdom',bdom);
								targetSelected=$(this);
								if(adom){
									adom.remove();
									$(this).removeData('adom');
								}
								selected=true;
								targetDrag=bdom;
							}else if(2 == place  && !adom ) {
								adom=$("<div class='"+options.dragHoverClass+"'></div>");
								$(this).after(adom);
								$(this).data('adom',adom);
								targetSelected=$(this);
								selected=true;
								if(bdom){
									bdom.remove();
									$(this).removeData('bdom');
								}
								targetDrag=adom;
							}
						});

					}
				}
				options.onDrag();
			}
		},5));
		$(document).mouseup(function(){
			if(move){
				move = false;
				if(options.self){
					if(cloneDiv!=null && targetObj!=null){
						if($($("[class$='"+options.targetHoverClass+"']")[0]).length>0){
							targetSelected =	$($("[class$='"+options.targetHoverClass+"']")[0]);
							selected = true;
						}
						options.afterDrag(selected,currentObj,targetSelected);
						cloneDiv.remove();
						cloneDiv.removeClass(options.cursorHoverClass);
						$("[class$='"+options.targetHoverClass+"']").each(function(){
							$(this).removeClass(options.targetHoverClass);
						});
						currentObj.removeClass(options.sourceHoverClass);
						currentObj = null;
						if(selected == true){
							targetSelected.removeClass(options.targetHoverClass);
							targetSelected = null;
							selected = false;
						}
					}
				}else{
					options.afterDrag(selected,currentObj,targetDrag);
					if(targetSelected){
						targetSelected.removeData('adom');
						targetSelected.removeData('bdom');
					}
					freshTarget();
				    $('.'+options.dragHoverClass).remove();
					if(cloneDiv){
						cloneDiv.remove();
						cloneDiv.removeClass(options.cursorHoverClass);
					}
					selected = false;

				}

			}
			document.ondragstart = function () { return true; };
			document.onselectstart = function () {return true; };
		});

		//防止抖动
		function debounce(func, wait, immediate) {
			var timeout;
			return function() {
				var context = this, args = arguments;
				var later = function() {
					timeout = null;
					if (!immediate) func.apply(context, args);
				};
				var callNow = immediate && !timeout;
				clearTimeout(timeout);
				timeout = setTimeout(later, wait);
				if (callNow) func.apply(context, args);
			};
		}

        /* 判断在区域的上下 */
		function checkAreaUpDown(_cloneDiv,_targetObj){
			var source_left = _cloneDiv.offset().left+_cloneDiv.innerWidth()/2;
			var source_top = _cloneDiv.offset().top+cloneDiv.innerHeight()/2;

			var target_left = _targetObj.offset().left;
			var target1_top = _targetObj.offset().top;
			var target1_bottom = target2_top = _targetObj.offset().top+_targetObj.innerHeight()/2;
			var target_right = _targetObj.offset().left+_targetObj.innerWidth();
			var target2_bottom = _targetObj.offset().top+_targetObj.innerHeight();
			if((source_top>target1_top&&source_top<target1_bottom)&&(source_left>target_left&&source_left<target_right)){
				return 1;
			}else if((source_top>target2_top&&source_top<target2_bottom)
				&&(source_left>target_left&&source_left<target_right)){
				return 2;
			}
			return -1;
		}
		/* 判断在区域的上下 */
		function checkAreaOverride (_cloneDiv,_targetObj){
			var source_left = _cloneDiv.offset().left+_cloneDiv.innerWidth()/2;
			var source_top = _cloneDiv.offset().top+cloneDiv.innerHeight()/2;

			var target_left = _targetObj.offset().left;
			var target_top = _targetObj.offset().top;
			var target_right = _targetObj.offset().left+_targetObj.innerWidth();
			var target_bottom = _targetObj.offset().top+_targetObj.innerHeight();

			if((source_top>target_top&&source_top<target_bottom)&&(source_left>target_left&&source_left<target_right)){
				$("[class$='"+options.targetHoverClass+"']").each(function(){
					$(this).removeClass(options.targetHoverClass);
				});
				return true;
			}else{
				return false;
			}

		}
		return jQueryObj;
	};
})(jQuery); 