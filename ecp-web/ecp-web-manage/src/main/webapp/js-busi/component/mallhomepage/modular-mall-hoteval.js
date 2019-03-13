;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'scrollTo','onePageNav'], function(scrollTo,onePageNav) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-mall-hoteval").modular_mall_hoteval();
				}
			};

		});
	} else {
		// 全局模式 ，不使用AMD 规范的时候，使用的插件
		factory(jQuery);
	}
}(function($) {
	var wordLimit=function(objs,h){
        objs.each(function(){
        	 limit($(this),45);
        });
        function limit(o,h){
            if(o.height()>h){
                var txt= $.trim(o.text());
                o.text(txt.substring(0,txt.length-3));
                o.html(o.html()+'…');
                limit(o,h);
            }
        }
    };
	 function startmarquee($obj,lh,speed,delay){
	        var t;
	        var p=false;
	        var o=$obj[0];
	        o.innerHTML+=o.innerHTML;
	        o.onmouseover=function(){p=true};
	        o.onmouseout=function(){p=false};
	        o.scrollTop = 0;
	        function start(){
	            t=setInterval(scrolling,speed);
	            if(!p){ o.scrollTop += 1;}
	        }
	        function scrolling(){
	            if(o.scrollTop%lh!=0){
	                o.scrollTop += 1;
	                if(o.scrollTop>=o.scrollHeight/2) o.scrollTop = 0;
	            }else{
	                clearInterval(t);
	                setTimeout(start,delay);
	            }
	        }
	        setTimeout(start,delay);
	    }
	// / 具体插件的定义；
	var defaultOpts = {
	};
	var $mall_hoteval = {
		/**
		 * 楼层导航；
		 * @param el
		 * @param images
		 * @author jiangzh
		 */
		"doData" : function($container, data) {
			$container.empty();
			
			var str = "<ul class='hot-pl'>";
			$.each(data, function(i, item) {
				if(!item.staffName){
				    item.staffName = "*****";
				}else if(item.staffName.length > 2){
					item.staffName = item.staffName.charAt(0)+"***"+item.staffName.charAt(item.staffName.length - 1);
				}else{
					item.staffName =item.staffName.charAt(0)+  "****";
				}
				str +=' <li>'+
                ' <i>&middot;</i>'+
                ' <p class="pl-date">'+ebcDate.dateFormat(item.evaluationTime,"yyyy-MM-dd hh:mm:ss")+'</p>'+
                ' <div class="pl-com" data-content="'+(item.detail?item.detail:"")+'">'+
                ' <span>'+(item.staffName?item.staffName:"")+':</span>'+
                ' <a href='+GLOBAL.MALLSITEURL+item.gdsDetailUrl+'><span class="pl-detail">'+(item.detail?item.detail:"")+'</span></a>'+
                ' </div>';
	            var intScore=item.intScore;
	            var scoreStr='';
	            if(intScore){
	            	 scoreStr='<p>';
	            	 for(var k=0;k<intScore;k++){
	            		 scoreStr+='<img src="'+GLOBAL.MALLSITEURL+'/images/x.png" alt=""/>';
	 	              }
	            	 scoreStr+='</p>';
	            }
	            scoreStr+='</li>';
	            str +=scoreStr;
			
			});
			str += "</ul>";
			$container.append(str);
			wordLimit($('.hot-detail'),45);
			
			var containHeight = +($container.children("ul").height());
			
			var divHeight = +($container.height());
			if(containHeight > divHeight){
				startmarquee($($container),50,50,0);
			}
		    $('.hot-pl .pl-com',$($container)).popover({trigger: 'hover', placement: 'bottom'});
		},
		"control":function(el,opts){
			var $container = $(".homepage-comment-cont", el);
			$container.append('<div class="loading"></div>');
			$.eAjax({
				url : $webroot + '/cmshomepagegetdata/hotComment',
				data : {
					"status" : opts.status,
					"size" : opts.size,
				},
				async : true,
				type : "post",
				dataType : "json",
				success : function(data, textStatus) {
					if (data && data.length > 0) {
						$mall_hoteval.doData($container,data);
					} else {
						$container.empty();
						$container.append("<div class ='pro-empty'>亲，暂无数据！</div>");
					}
				},error : function(e,xhr,opt){
					$container.empty();
					$container.append("<div class ='pro-empty'>亲，出现异常！</div>");
				}
			});
		}
	};

	$.fn.modular_mall_hoteval = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this,defaultOpts);
			if(opts == null || opts.templateID =="" || opts.templateID=="undefined"){
				return ;
			}
			$mall_hoteval.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
