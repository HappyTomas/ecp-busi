	/*
	 * 获取当前tab页面的id
	 */
	function getTabId(){
		var type = $("#membercoupinfo>li.am-active").attr("id");
		var _id= type;
		return _id;
	}
	function addLoadParamsToPayUrl(_load_url_){
		var date = new Date();
		_load_url_ += '&t='+date.getTime();
		/*
	    var orderId = $('input[name="orderId"]').val();
	    if(orderId != "")
	    {
	    	_load_url_ += '&orderId=' + orderId;
	    }
		 */
	    return _load_url_;
	};
//页面初始化模块
$(function(){

	//页面业务逻辑处理内容
	var pInit = function(){

    	var init = function(){
    		/*选项卡事件注册*/
    		  //初始化完成后，加载数据
    		   var cur_active_tab = getTabId();
    		   $('#wrapper2').height($(window).height()-$('.am-header').height()-$('.am-tabs-nav').height()-20);
               var loadScroll = new LoadScroll("wrapper2", {
                   url: GLOBAL.WEBROOT+'/membercoup/data',
                   isAjax:true,
                   params:{
                       "datatype":$('#'+cur_active_tab).data('type'),
                   }
               });
    		$('#membercoupinfo li').click(function(){
            	//$('#dataType').val('1');
    			
    			var $this=$(this);
    			loadScroll.refreshPage({
    				params:{
                        "datatype":$this.data('type'),
                    }
    			});
             
            });
          
    	
    		//$('#'+cur_active_tab).click();
    	};
    	return {
    		init : init
    	};
	};
	var p = new pInit();
	p.init();
	/*
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bForm','bPage'],
		//指定页面
		init : function(){
			var p = new pInit();
			p.init();
		}
	});*/
});