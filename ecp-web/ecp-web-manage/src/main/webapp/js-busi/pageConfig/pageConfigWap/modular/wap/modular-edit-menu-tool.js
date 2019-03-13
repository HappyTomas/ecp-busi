/**
 * 模块编辑属性组菜单操作工具
 * 16.9.6 zhanbh
 * 
 * 注意：上移下移删除的回调函数都是在操作前执行的
 */
;var _eCmsModMenuTool = {
	"siteId": $("#siteId").val(),
	"mUp":function(callBackFn){
		$(".mup",".form-group").die("click.em").live("click.em",function(){
			var $this = $(this);
			var $group = $this.closest(".form-group");
			var $prev = $group.prev(".form-group:visible");
			if($prev && $prev.length > 0){
				if(callBackFn && $.isFunction(callBackFn)){
					callBackFn($this);
				}
				
				$group.insertBefore($prev);
				_eCmsModMenuTool.updateGroup($group,false);
				_eCmsModMenuTool.updateGroup($prev,true);
			}
		});
	},
	"mDown":function(callBackFn){
		$(".mdown",".form-group").die("click.em").live("click.em",function(){
			var $this = $(this);
			var $group = $this.closest(".form-group");
			var $next = $group.next(".form-group:visible");
			var value = $("#controlPropId",$group).val();
			if($next && $next.length > 0){
				if(callBackFn && $.isFunction(callBackFn)){
					callBackFn($this);
				}
				
				$next.insertBefore($group);
				_eCmsModMenuTool.updateGroup($group,true);
				_eCmsModMenuTool.updateGroup($next,false);
			}
		});
	},
	"mDel":function(callBackFn){
		$(".mdel",".form-group").die("click.em").live("click.em",function(){
			var $this = $(this);
			var $group = $this.closest(".form-group");
			var $nextAll = $group.nextAll(".form-group");
			var $prev = $group.prev(".form-group");
			if($nextAll.filter(".form-group:visible").length > 0 || ($prev && $prev.length > 0)){
				if(callBackFn && $.isFunction(callBackFn)){
					callBackFn($this);
				}
				
				_eCmsModMenuTool.updateGroup($nextAll,false);
				_eCmsModMenuTool.updateScrollbar();
				$group.remove();
			}
		});
	},
	"isEmptyGroup":function($obj,propIds){//propIds用来判断为空组的属性id
		if(!$obj){
			return true;
		}
		$obj = $($obj).eq(0);
		if(!$obj.hasClass("form-group")){
			return true;
		}
		if(!$.isArray(propIds)){
			propIds =[];
		}
		var $blocks = $obj.find(".form-block");
		if($blocks && $blocks.length > 0){
			for(var i = 0;i < $blocks.length ; i++ ){
				var $block = $blocks.eq(i);
				var propId = parseInt($("#propId",$block).val());
				if(0 >= propIds.length || 0 <= $.inArray(propId,propIds)){
					var value = $("#propValue",$block).val();
					if(value && value.trim()){
						return false;
					}
				}
			}
		}
		
		return true;
	},
	"getGroup":function($script,num){
		if(!$script){
			return null;
		}
		$script = $($script).eq(0);
		num = parseInt(num);
		if(!num || num < 1){
			return null;
		}
		
		var $proGroup = $($script.html());
		$proGroup.find(".wap_img_upload").eCmsImageUploadInit({
			 "elWidth" : 250,//html元素宽度
			 "elHeight" : 128,//html元素高度
			 "showWidth" : 250,//图片展示规格宽度
			 "showHeight" :128,//图片展示规格高度
			 "showText":"建议选择{imageMaxWidth}*{imageMaxHeight}尺寸的图片，类型:{fileTypeExts}",//上传插件提示语
			 "callback":function (fileInfo) {
					if(fileInfo && fileInfo.success == true && fileInfo.fileId){
						$(fileInfo.el).parents(".formItem").children("#propValue").val(fileInfo.fileId);
					}
				}
		});
		$proGroup.find(".cms-input-w-limit").eCmsInputWorkLimit();
		
		//链接工具初始化
		$proGroup.find(".link-input-tool").cmsLinkInputInit({
			"types":["good","catg","secondpage","prom","sitenav"],
			"busiParams":{"prom":{"pageTypeId":51},"secondpage":{"pageTypeId":52}},//51为微信端活动页类型id  硬编码
			"siteId":_eCmsModMenuTool.siteId,
			"urlMap":(function(){return _eCmsModMenuTool.siteId==2?{"good":"gdspointdetail/{key}-"}:{}})(),
			"callback":function(data){
				if(data && data.success == true && data.url){
					$(data.el).val(data.url);
		    		$(data.el).parents(".form-block.formItem").find("#propValue").val(data.url);
		    	}
			}
		});
		$proGroup.find(".form-block").each(function(){
			var $obj = $(this);
			$("#controlPropId",$obj).val(num);
		});
		return $proGroup;
	},
	"updateGroup":function($objs,isAdd){
		//参数校验
		if(!$objs){
			return this;
		}
		var $objs = $($objs);
		var len = $objs.length;
		
		var blocks = [];
		$objs.each(function(){
			$.merge(blocks,$(".form-block",$(this)));
		});
		
		var groupIns = [];
		for(var i in blocks){
			$.merge(groupIns,$("#controlPropId",$(blocks[i])));
		}
		
		for(var j in groupIns){
			var $groupIn = $(groupIns[j]);
			var value = parseInt($groupIn.val());
			if(value){
				if(isAdd){
					$groupIn.val(value + 1);
				}else if((value - 1) >= 1){
					$groupIn.val(value - 1);
				}
			}
		}
	},
	"updateScrollbar":function(){//更新滚动条
		if($("#pageEdit").hasClass('mCustomScrollbar')){
			$('#pageEdit').mCustomScrollbar('update');
		}else{
			var pageEdit = $("#pageEdit").mCustomScrollbar({
				scrollInertia: 150,
				advanced: {
					autoScrollOnFocus: false
				}
			});
		}
	}
}