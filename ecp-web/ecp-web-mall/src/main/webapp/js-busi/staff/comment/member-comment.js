$(function(){
//	$("#checkAll").bSelectAll($("#datalist"));
	//页面初始化
	comment.init();
//	$("#teamTab").children(".active").click();

	$("#teamTab").children(".active").trigger('click');
	
});
var comment = {
	updateTab:function (tab,_this){
		$(_this).attr("class","active");
		$(_this).siblings().attr("class","");
		//var tab = $(this).attr("id");
		//根据不同的tab 设置不同的url
		if(tab == "uneval"){
			url = $webroot + '/comment/unevallist';
		}
		if(tab == "evaled"){
			url = $webroot + '/comment/evaledlist' ;
		}
		//执行ajax
		$.eAjax({
			url:url,
			dataType : "text",
			success : function(returnInfo){
				//这里的returnInfo 是后台返回的html代码
				if(returnInfo){
					$("#dataBody").html(returnInfo);
				}
			}
		});//end of eAjax
	},
		//初始化页面
		init: function () {
			$("#teamTab").children().click(function(){
				var tab = $(this).attr("id");

				comment.updateTab(tab,this);
			});
		},//end of init
      

      updateEvaled : function (){
    	 
    	  //更新分页条
    	  $('#pageControlbar').bPage({
    			url : $webroot + '/comment/evaledlist',
    			pageSizeMenu : [5,10,20],
    			asyncLoad : true,
    			asyncTarget : '#dataBody',
    			
    		});
    	 
      },
		
	     updateUneval : function (){
	    	  
	    	  //更新分页条
	    	  $('#pageControlbar').bPage({
	    			url : $webroot + '/comment/unevallist',
	    			pageSizeMenu : [5,10,20],
	    			asyncLoad : true,
	    			asyncTarget : '#dataBody',
	    			
	    		});
	    	 
	      }

};//end of comment