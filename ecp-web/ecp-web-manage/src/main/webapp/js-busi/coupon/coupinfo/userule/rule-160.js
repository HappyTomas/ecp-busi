$(function(){
	 
});

var rule160={ 
	//查询数据
	qryData:function(){
		var _id=$('#id').val();
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/coupcomm/160/"+_id,
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					if(returnInfo.useRuleValue){ 
						  $('#maxBuyCnt').val(returnInfo.useRuleValue);
					   }else{
							hide=true;
							rule160.viewHide(hide,$('#useForm160'));
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
		 var _maxBuyCnt=$('#maxBuyCnt').val();
		 if(_maxBuyCnt){
			 
		 }else{
			 data.push("使用数量限制");
		 }
		 return data;
	},
	//保存
	getData:function(){
		  var data= ebcForm.formParams($("#useForm160"));
		  return data;
	}
}
