$(function(){
	var $helpCenter = $("#help-center"),//帮助中心dom对象
	    $chanBar = $(".help-bar",$helpCenter),//帮助中心栏目边栏树dom对象
	    $crumb = $(".breadcrumb",$helpCenter),//帮助中心面包屑dom对象
	    $helpBody = $("#help-body",$helpCenter),//帮助中心展示内容区dom对象
	    chanBarHander = null,//栏目对象处理器   拥有与栏目有关方法
	    crumbHander = null,//面包屑对象处理器   拥有与面包屑有关方法
	    articleHander= null,//文章对象处理器   拥有与文章有关方法
		initFn = null;//页面内容初始化 方法   只在栏目树加载完成后执行一次
	    
	//Objs init start
    chanBarHander = {
    		/**
    		 * 获取栏目树并初始化栏目事件
    		 */
		"getChanBar":function(){
			$.eAjax({
				url : GLOBAL.WEBROOT + "/helpcenter/getchanbar",
				dataType : "html",
				success : function(returnInfo) {
					$chanBar.empty();
					$chanBar.html(returnInfo);
					chanBarHander.bindPChanOpen();
					chanBarHander.bindCChanClick();
					initFn();
				}
			});
		},
		/**
		 * 展示指定栏目
		 * chanObj  必须包含id 与 name  否则表示初始化栏目
		 */
		"showChan":function(chanObj){
			chanObj = chanObj || {};
			var chanId = chanObj.id||"",
				chanName = chanObj.name||"",
				exChan = chanBarHander.activeCChan(chanId),
				$chan,
				$childChan;
			
			if(!exChan){//在栏目数不存在可展示文章栏目（二级栏目）
				crumbHander.channels=[chanObj];
				crumbHander.mod = crumbHander.status[1];
				if(chanId){
					$chan = $(".item[data-id="+chanId+"]",$chanBar);//查找对应父栏目
				}else{
					$chan = $(".item",$chanBar).eq(0);//无栏目id 则查找第一个父栏目
					if(!$chan || 0 >= $chan.length){//无第一个父栏目 则显示无信息
						crumbHander.mod = crumbHander.status[0];
						crumbHander.freshCrumbHtml();
						$helpBody.html("<div class='pro-empty'>暂无信息</div>");
						return false;
					}
				}
				if($chan && 0 < $chan.length){//有父栏目 查找可展示栏目
					crumbHander.channels=[$chan.data()];
					$childChan = $(".item-com li",$chan).eq(0);
					$chan.addClass("open");
					if(!$childChan || 0 >= $childChan.length){
						crumbHander.freshCrumbHtml();
						$helpBody.html("<div class='pro-empty'>暂无信息</div>");
						return true;
					}
				}
				if($childChan && 0 < $childChan.length){//有可展示栏目  替换为要展示的栏目
					chanId = $childChan.data("id");
					chanBarHander.activeCChan(chanId);
				}
			}
			crumbHander.freshCrumbHtml();
			chanBarHander.getArtisOfChan(chanId);
			return true;
		},
		/**
		 * 获取栏目对应的文章列表
		 */
		"getArtisOfChan":function(chanId){
			if(!chanId){return false}
			$helpBody.html("<div class='loading'></div>");
			$.eAjax({
				url : GLOBAL.WEBROOT + '/helpcenter/getartipage',
				data : {
					channelId : chanId
				},
				dataType : "html",
				success : function(returnInfo) {
					$helpBody.html(returnInfo);
				},
				error:function(returnInfo) {
					$helpBody.html("<div class='pro-empty'>网络错误</div>");
				}
			});
		},
		/**
		 * 绑定父栏目展开事件
		 */
		"bindPChanOpen":function(){
			$('.item-tit', $chanBar).click(function () {
		        $(this).closest(".item").toggleClass('open');
		    })
		},
		/**
		 * 绑定可展示栏目点击事件
		 */
		"bindCChanClick":function(){
			var $childItems = $(".item-com li",$chanBar);
			$childItems.bind("click",function(){
				var $this = $(this);
				if(!$this.hasClass("active") || crumbHander.mod != crumbHander.status[1]){
					chanBarHander.showChan({id:$this.data("id")||"",name:$this.data("name")||""});
				}
			});
		},
		/**
		 * 将指定栏目在栏目树中标为激活栏目   如无对应可展示栏目  返回false
		 */
		"activeCChan":function(chanId){
			var $childItems,
				$childItem,
				$item;
			
			$childItems = $(".item-com li",$chanBar);
			//$("li.item",$chanBar).removeClass("open");
			$childItems.filter(".active").removeClass("active");
			if(!chanId){
				return false;
			}
			$childItem =  $childItems.filter("[data-id="+chanId+"]");
			$item = $childItem.closest(".item");
			if(!$childItem || 0 >= $childItem.length){
				return false;
			}
			$childItem.addClass("active");
			$item.addClass("open");
			crumbHander.channels = [{id:$item.data("id"),name:$item.data("name")},{id:$childItem.data("id"),name:$childItem.data("name")}];
			crumbHander.mod = crumbHander.status[1];
			return true;
		}
	};
    /**
     * 面包屑对象处理器
     */
    crumbHander = {
    	status:["home","chan","arti"],//home：首页状态   chan：栏目状态，arti:文章状态
    	mod:"home",
    	channels:[],//面包屑要展示的栏目
    	article:{},//面包屑要展示的文章
    	/**
    	 * 刷新面包屑html展示
    	 */
    	freshCrumbHtml:function(){
    		var channels = crumbHander.channels || [],//缓存  提高效率
    			article = crumbHander.article||{},//缓存  提高效率
    			mod = crumbHander.mod,//缓存  提高效率
    		    chanMod = crumbHander.status[1],//缓存  提高效率
    		    artiMod = crumbHander.status[2],//缓存  提高效率
    			ci = 0,
    		    cl = channels.length || 0,
    		    chan,
    		    chanStr = '',
    		    artiStr = '',
    		    $crumbHome = $(".crumb-home",$crumb);//面包屑home标签
    		
    		$crumbHome.nextAll("li").remove();
    		if(mod == chanMod || mod == artiMod){//栏目 或者文章模式  展示面包屑
    			$crumb.show();
    			for(;ci < cl;ci += 1){
					chan = channels[ci];
					if(!chan.id){
						continue;
					}
					chanStr = $('<li data-mod="chan" data-id ="'+chan.id+'" data-name="'+(chan.name||"")+'"></li>');
					if(ci + 1 != cl || (mod == artiMod && article.id)){
						chanStr.append('<a href="javascript:void(0);">'+(chan.name||"")+'</a><span class="divider">&gt;</span>');
					}else{
						chanStr.addClass("active");
						chanStr.append((chan.name||""));
					}
					$crumb.append(chanStr);
				}
    			if(mod == artiMod && article.id){
    				artiStr = $('<li class="active" data-mod="arti" data-id ="'+crumbHander.article.id+'" data-name="'+(crumbHander.article.name||"")+'">'+(crumbHander.article.name||"")+'</li>');
    				$crumb.append(artiStr);
    			}
    		}else{//其他模式不展示
    			crumbHander.channels = [];
    			crumbHander.article = {};
    			$crumb.hide();
    		}
    	},
    	/**
    	 * 绑定面包屑栏目链接点击事件
    	 */
    	"bindChanClick":function(){
    		$("li[data-mod=chan] a",$crumb).live("click",function(){
    			var $chan = $(this).closest("li[data-mod=chan]");
    			if($chan.data("id")){
    				chanBarHander.showChan($chan.data());
    			}
    		})
    	}
    };
    articleHander = {
		/**
		 * 绑定文章标题点击事件
		 */
    	bindArtiClick:function(){
    		$(".help-arti-seed",$helpBody).live("click",function(){
    			var $this = $(this),
    				artiObj = $this.data()||{};
    				
    			if(artiObj.id){
    				articleHander.getArti(artiObj);
    			}
    		});
    	},
    	/**
    	 * 获取文章详情
    	 */
    	"getArti":function(artiObj){
    		var exChan = false;
    		if(!artiObj || !artiObj.id){
    			return false;
    		}
    		$helpBody.html("<div class='loading'></div>");
    		if(artiObj.channelId){
				exChan = chanBarHander.activeCChan(artiObj.channelId);
			}
			if(!exChan){
				crumbHander.channels=[{id:artiObj.channelId||"",name:artiObj.channelName||""}];
			}
			crumbHander.article = artiObj;
			crumbHander.mod = crumbHander.status[2];
			crumbHander.freshCrumbHtml();
			$.eAjax({
				url : GLOBAL.WEBROOT + '/helpcenter/getarti',
				data : {
					id:artiObj.id
				},
				dataType : "html",
				success : function(returnInfo) {
					$helpBody.html(returnInfo);
				},
				error:function(returnInfo) {
					$helpBody.html("<div class='pro-empty'>网络错误</div>");
				}
			});
    	}
    };
    //Objs init end
    
    //初始化页面函数
	initFn = function(){
		var chanId = $helpCenter.data("initChan")||"",
		 	artiId = $helpCenter.data("initArti")||"",
		 	chanName = $helpCenter.data("initChanName")||"",
		 	artiName = $helpCenter.data("initArtiName")||"",
		 	errMsg = $helpCenter.data("errMsg")||"";
		
		if(errMsg){//有错误信息则表示显示错误信息  优先级最高
			$helpBody.html("<div class='pro-empty'>"+errMsg+"</div>");
			crumbHander.freshCrumbHtml();
		}else if(artiId){//有文章则显示文章  优先级第二
			articleHander.getArti({id:artiId,name:artiName,channelId:chanId,channelName:chanName});
		}else if(chanId){//有栏目显示栏目  优先级第三
			chanBarHander.showChan({id:chanId,name:chanName});
		}else{//默认情况 展示第一个栏目
			chanBarHander.showChan();
		}
	}
	//初始化栏目边栏
	chanBarHander.getChanBar();
	articleHander.bindArtiClick();
	crumbHander.bindChanClick();
});