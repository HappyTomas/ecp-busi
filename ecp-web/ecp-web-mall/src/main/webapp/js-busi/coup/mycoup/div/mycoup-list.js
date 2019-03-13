$(function(){
    var pInit = function(){
    	var init = function(){
    		   //分页
    		 $('#pageControlbar').bPage({
    				 url : GLOBAL.WEBROOT + "/mycoup/list",
	    		     asyncLoad : true,
	    		   //每页显示记录数设置
	    		     "pageSizeMenu"   : [9,18,27,36,50],
	    		     asyncTarget : '#pageMainBox',
	    		     params : function(){
	    		    	 var  p={};
	    		    	//获得排序
	    				 $('.m-font.mycoup-sort').each(function(){
	    						var col= $(this).attr("data");
	    						var value= $(this).attr("sort-value");
		    					p["mapSort["+col+"]"]=value;
	    					 });
	    					var _data="";
	    		        	$("a[name='tab-num']").each(function(){
	    		        		   if($(this).hasClass("active")){
	    		        			   _data=$(this).attr("data");
	    		        			   return false;
	    		        		   }
	    		        	  });
	    				 p["queryType"]=_data;
	    		    	  return p;
	    		     }
    		 });
    		 //解绑
    		 $(".coup-class-del").off(); 
    		 //删除 事件绑定
    		 $('.coup-class-del').on('click',function(){
    		 	 var data=new Array();
    		 	  data.push({'name':'id',value:$(this).attr("data")});
    		 	 $.eAjax({
    					url : GLOBAL.WEBROOT + "/mycoup/del",
    					data : data,
    					success : function(returnInfo) {
    						if(returnInfo.resultFlag=='ok'){
	    						var _tab="";
	    					  	$("a[name='tab-num']").each(function(){
	    			        		   if($(this).hasClass("active")){
	    			        			   _tab=$(this).attr("data");
	    			        			   return false;
	    			        		   }
	    			        	  });
	    						   
	    						myCoupHeader.queryCount(_tab);
	    						myCoupHeader.queryData(_tab);
    						}else{
    							eDialog.alert('亲！系统发生异常了。请稍等再重试...',null);
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