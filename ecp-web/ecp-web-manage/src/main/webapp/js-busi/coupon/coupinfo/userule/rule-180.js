$(function(){
	 
});

var rule180={
	//检查
	check:function(){
		return true;
	},
	//提醒信息
	getTips:function(){
		 var data=new Array();
	     if (ebcForm.formParams($("#useForm180"))){
	    	 
	     }else{
	    	 data.push("免邮限制");
	     }
		 return data;
	},
	//保存
	getData:function(){
		  var data= ebcForm.formParams($("#useForm180"));
		  return data;
	}
}
