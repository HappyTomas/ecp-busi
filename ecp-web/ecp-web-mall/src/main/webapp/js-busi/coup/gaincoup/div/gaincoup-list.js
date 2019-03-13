$(function(){
    var pInit = function(){
    	var init = function(){
    		   //分页
    		 $('#pageControlbar').bPage({
    				 url : GLOBAL.WEBROOT + "/gaincoup/list",
	    		     asyncLoad : true,
	    		   //每页显示记录数设置
	    		     "pageSizeMenu"   : [9,18,27,36,50],
	    		     asyncTarget : '#pageMainBox',
	    		     params : function(){}
    		 });
    		 //解绑
    		 $(".coup-class-del").off(); 
    		 //删除 事件绑定
    		 $('.coup-class-del').on('click',function(){
    		 	 var data=new Array();
    		 	  data.push({'name':'id',value:$(this).attr("data")});
    		 	 $.eAjax({
    					url : GLOBAL.WEBROOT + "/gaincoup/gain",
    					data : data,
    					success : function(returnInfo) {
    						if(returnInfo.resultFlag=='ok'){
    							 eDialog.alert('领取成功！',null);
     		 					 return ;
    						}else{
    							eDialog.alert(returnInfo.resultMsg,null);
       		 					return ;
    						}
    						//重新 刷新页面
    					},
    					exception : function(returnInfo) {
    						   eDialog.alert('亲！系统发生异常了。请稍等再重试...',null);
    		 					return ;
    						}
    					});
    		 });    
    		 //解绑
    		 $(".shop-class").off(); 
    		 //进入店铺  事件绑定
    		 $('.shop-class').on('click',function(){
    		 	window.location=GLOBAL.WEBROOT+"/homepage";
    		 });  

              };
    	return {
    		init : init
    	};
	};    	
	pageConfig.config({
		plugin : ['bPage'],
		init : function(){
			var exchangeList = new pInit();
			exchangeList.init();
		}
	});
});