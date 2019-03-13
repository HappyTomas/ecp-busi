$(function(){
	 //满数量 满金额  radio
	 $(".fullLimit-type").click(function(){
		 rule140.radioClick(this);
	 });
	 $("#sumLimit").blur(function(){
		 rule140.checkInfo(this);
	 });
	 
});

var rule140={
		//radio点击事件  满数量 满金额
		radioClick:function(obj){
			var _id=$(obj).attr("data-id");
			$('#'+_id).val('');
		},
	//查询数据
		qryData:function(){
			var _id=$('#id').val();
			//ajax请求
			$.eAjax({
				url : GLOBAL.WEBROOT + "/coupcomm/140/"+_id,
				success : function(returnInfo) {
					if(returnInfo.resultFlag=='ok'){
						var hide=true;
						if(returnInfo.sumLimit){
							$('#sumLimit').val(returnInfo.sumLimit/100);
							$("input[name='fullLimitVO.type'][value=1]").attr("checked",'checked');
							hide=false;
						}
						
						if(returnInfo.amount){
							$('#amount').val(returnInfo.amount);
							$("input[name='fullLimitVO.type'][value=2]").attr("checked",'checked');
							hide=false;
						}
						rule140.viewHide(hide,$('#useForm140'));
					}
				}
			});
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
	//满减限制 面额验证
	checkInfo:function(obj){
		 var _v=$(obj).val();
		 var _coupValue=$('#coupValue').val();
		 //满额为空 不处理
		 if(_v==null || _v==""){
			 
		 }else{
			 //面额为空 不处理
			 if(_coupValue==null || _coupValue==""){
				 
			 }else{
	           if(isNaN(_v)){
	        	   eDialog.alert('请设置满减限制为有效金额。');
			    	  return false;
				 }
	           if(isNaN(_coupValue)){
	        	   eDialog.alert('请设置优惠券面额有效金额。');
			    	  return false;
				 }
			    if(parseFloat(_v)<parseFloat(_coupValue)){
			    	  eDialog.alert('设置的满减金额【'+_v+'】小于优惠券面额【'+_coupValue+'】');
			    	  return false;
			    }
			 }
			 
		 }
		 return true;
	},
	//检查
	check:function(){
		return true;
	},
	//提醒信息
	getTips:function(){
		var data=new Array();
		 //验证是否有设置数据  ，如有设置验证通过，不需要提醒   
		 var _sumLimit=$('#sumLimit').val();
		 var _amount=$('#amount').val();
		 if(_sumLimit || _amount){
			 
		 }else{
			 data.push("满减限制");
		 }
	    return data;
	},
	//保存
	getData:function(){
		  var data= ebcForm.formParams($("#useForm140"));
		  return data;
	}
}
