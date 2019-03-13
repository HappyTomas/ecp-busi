$(function(){
	   //tab 切换
	   $("a[name='tab-num']").click(function (e) { 
	    	//清空
		   $("a[name='tab-num']").removeClass();
		   $(this).addClass("active");
		   //执行查询列表操作
		  var _qType= $(this).attr("data");
		   myCoupHeader.queryData(_qType);
	    });
	    //我的促销
        $(".i-pad").hover(function(){
            $(this).addClass("m-hover");
        },function(){
            $(this).removeClass("m-hover");
        });
        //下拉 事件
        $("a[name='li-num']").click(function (e) {
		   //执行查询列表操作
		  var _qType= $(this).attr("data");
		  myCoupHeader.queryCount(_qType); 
		  myCoupHeader.queryData(_qType);
		  
		  $("a[name='tab-num']").removeClass();
		  $("a[name='tab-num']").each(function(){
		      if($(this).attr("data")==_qType){
		    	  $(this).addClass("active");   	  
		      } 
          });
	    });
        
        //排序  当前操作项目变更 其他不排序
        $('.mycoup-sort').live('click',function(){
        	 $('.mycoup-sort').removeClass("m-font");
        	 $(this).addClass("m-font");
        	  var data = $(this).attr("data");
              var cls=$(this).find('i').attr('class');
              
        	  $(this).find('i').removeClass();
        	if(cls=="mi mi-up-grap"){
        		$(this).find('i').addClass('mi mi-up');
        		$(this).attr("sort-value","1");
        	}
        	if(cls=="mi mi-down"){
        		$(this).find('i').addClass('mi mi-down-red');
        		$(this).attr("sort-value","0");
        	} 
        	if(cls=="mi mi-up"){
        		$(this).find('i').addClass('mi mi-down-red');
        		$(this).attr("sort-value","0");
        	} 
        	if(cls=="mi mi-down-red"){
        		$(this).find('i').addClass('mi mi-up');
        		$(this).attr("sort-value","1");
        	} 
        	
        	var _id=$(this).attr("id");
            $('.mycoup-sort').each(function(){
            	  if(_id==$(this).attr("id")){
            		  return true;
            	  }else{
            		   var _cls=$(this).find('i').attr('class');
            		   $(this).find('i').removeClass();
                    	if(_cls=="mi mi-up"){
                    		$(this).find('i').addClass('mi mi-up-grap');
                    	} 
                    	if(_cls=="mi mi-up-grap"){
                    		$(this).find('i').addClass('mi mi-up-grap');
                    	} 
                    	if(_cls=="mi mi-down-red"){
                    		$(this).find('i').addClass('mi mi-down');
                    	} 
                      	if(_cls=="mi mi-down"){
                    		$(this).find('i').addClass('mi mi-down');
                    	} 
            	  }
        	 });
        	var _data="";
        	$("a[name='tab-num']").each(function(){
        		   if($(this).hasClass("active")){
        			   _data=$(this).attr("data");
        			   return false;
        		   }
        	  });
        	 myCoupHeader.queryData(_data);
        });  
            //已使用
            myCoupHeader.queryCount("1");
            //未使用
            myCoupHeader.queryCount("2");
            //已冻结
            myCoupHeader.queryCount("3");
            //已过期
            myCoupHeader.queryCount("0");
            
    });

var myCoupHeader={
		//数量计算
		queryCount : function(qType){
			 var data=new Array();
			 data.push({'name':'queryType',value:qType});
			 $.eAjax({
	     			url : GLOBAL.WEBROOT + "/mycoup/queryCount",
	     			data : data,
	     			success : function(returnInfo) {
	     				$(".num"+qType).html(returnInfo.resultMsg);
	     			},
	     			exception : function(returnInfo) { 
	     				alert('exception');
	     				}
	     			});
		},
		//列表查询
		queryData : function(qType){

			var sort=new Array();
			
			 var data=new Object();
			 data={'queryType':qType};
			 
			 var  arr=new Array();
			//获得排序
			 $('.m-font.mycoup-sort').each(function(){
				var col= $(this).attr("data");
				var value= $(this).attr("sort-value");
				arr.push({name:"mapSort["+col+"]",value:value});
			 });
			 
			  arr.push({name:'queryType',value:qType});
			 $('#pageMainBox').load(GLOBAL.WEBROOT + '/mycoup/list',arr);
		}
}