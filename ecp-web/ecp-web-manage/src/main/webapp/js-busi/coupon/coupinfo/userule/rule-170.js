$(function(){
	 
});

var rule170={ 
	//查询数据
	qryData:function(){
		var _id=$('#id').val();
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/coupcomm/170/"+_id,
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					if(returnInfo.useRuleValue){
						    var arr=new Array();
						     arr=returnInfo.useRuleValue.split(",");
							 $(".channelCls").find("input[type=checkbox]").each(function() {
									 for(var i=0;i<arr.length;i++){
										if ($(this).val() == arr[i]){
											  $(this).attr("checked",'checked');
										}
									 }
								});
								 
						 }else{
								hide=true;
								rule170.viewHide(hide,$('#useForm170'));
						   }
					}
				}
		});
	},
	//检查
	check:function(){
		return true;
	},
	//查看隐藏 控件
	viewHide:function(hide,obj){
			if($('#doAction').val()=="view"){
			    $(".delRowTd").hide();//删除按钮 列隐藏
			    if(hide && obj){
			    	obj.hide();
			    }
			}
		},
	//提醒信息
	getTips:function(){
		 var data=new Array();
         var c=false;
		 $(".channelCls").find("input[type=checkbox]:checked").each(function() {
			 c=true;
			 return false;
		 });
		 
		 if (c){
	    	 
	     }else{
	    	 data.push("渠道使用限制");
	     }
		 return data;
	},
	//保存
	getData:function(){
		  var data= ebcForm.formParams($("#useForm170"));
		  return data;
	}
}
