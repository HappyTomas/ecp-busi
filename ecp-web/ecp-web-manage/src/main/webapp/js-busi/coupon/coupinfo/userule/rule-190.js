$(function(){
	 
});

var rule190={
	//检查
	check:function(){
		return true;
	},
	//提醒信息
	getTips:function(){
		 var data=new Array();
		 return data;
	},
	//保存
	getData:function(){
		  var data= ebcForm.formParams($("#useForm190"));
		  return data;
	}
}
