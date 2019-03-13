$(function(){
	//有优惠码标识时，隐藏时间类型变更为固定时间，并隐藏时间类型选择
	$(".effType[value=1]").click(function(){
		coupInfo.effTypeClick(this);
	});
	if($("#coupCodeFlag").val()=="true"){
	$(".effType[value=1]").click();
	}
	//无限制发行点击事件
	 $("#publishNum").click(function(){
		  coupInfo.publishClick(this);
		 });
		//有效时间类型点击事件
	 $(".effType").click(function(){
		  coupInfo.effTypeClick(this);
		 });
	 $("#coupValue").blur(function () {
			coupInfo.checkCoupValue();
		}).on('keypress', function (e) {
			//只能输入数字
			return coupInfo.checkNum(e, this, false);
		});
	 $("#coup_code").keyup(function(){
		 var i = $(this).val();
		 if(i.length>10){
			var value = i.substring(0,10);
			$(this).val(value);
			$("#code_info").html("<span style='color:red;'>优惠码最多为10个字符串。</span>");
		 }else{
			 $("#code_info").html("请填写<span  style='color:red'>10</span>位优惠码，只能是<span  style='color:red'>数字</span>和<span  style='color:red'>字母</span>组合");
		 }
	 });
});

var coupInfo={
	//页面信息 展示	
	//copy 同create ,view 隐藏相关按钮功能。edit可编辑
	show:function(_doAction){ },
	
	//无限制发行点击事件
    publishClick:function(_obj){
    	  //选中
	   	 if($(_obj).prop('checked')==true){
		   	  $('#coupNum').hide();
	      }else{
	    	  $('#coupNum').show();
	     }
   },
	//有效时间类型点击事件
    effTypeClick:function(_obj){
    	 if($(_obj).val()=='1'){
	    	  //固定类型   天数需要隐藏
	          $(".time").show();
	          $(".days").hide();
	          
	    }else{
	    	  //浮动类型  开始时间和截止时间 需要隐藏
	    	   $(".time").hide();
	    	   $(".days").show();
	    }
   },
   //验证面额和满减限制 额度值比较
   checkCoupValue:function(_obj){
	    var _v=$('#sumLimit').val();
		 var _coupValue=$('#coupValue').val();
		 //满额为空 不处理
		 if(_v==null || _v==""){
			 
		 }else{
			 //面额为空 不处理
			 if(_coupValue==null || _coupValue==""){
				 
			 }else{
	           if(isNaN(_coupValue)){
	        	   eDialog.alert('请设置优惠券面额有效金额。');
			    	  return false;
				 }
	           if(isNaN(_v)){
	        	   eDialog.alert('请设置满减限制为有效金额。');
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
   //验证面额
   validCoupValue:function(){
	   if($("#coup").val()== "true"){
		   var coupValue=$('#coupValue').val();
		   if(coupValue!=null && coupValue!=""){
			   if(coupValue<=0){
				   eDialog.alert("优惠券面额不能小于0或者等于0");
				   return false;
			  }else{
				  return true;
			  }
		   } 
	   }
	   return true;
   },
   checkNum:function(eventObj,obj,integer,positive) {
		//获得当前按键的键值
		var key_code = window.event ? window.event.keyCode : eventObj.which ? eventObj.which : eventObj.charCode;
		var int = true,pos = true;
		if($.type(integer)!='undefined' && $.type(integer)=='boolean') int = integer;
		if($.type(positive)!='undefined' && $.type(positive)=='boolean') pos = positive;
		if (!(((key_code >= 48) && (key_code <= 57)) ||
			(key_code == 13) ||
			(key_code == 8) ||
			(key_code == 46 && !int) ||
			(key_code == 45 && !pos))) {
			try{
				window.event.keyCode = 0 ;
			}catch(e){
				eventObj.cancelBubble = true;
			}
			return false;
		}
		//限制只允许输入一个小数点符号
		if (key_code == 46 && $(obj).val().indexOf(".") != -1) {
			try{
				window.event.keyCode = 0 ;
			}catch(e){
				eventObj.cancelBubble = true;
			}
			return false;
		}

		//限制只允许输入一个负数符号
		if (key_code == 45 && $(obj).val().indexOf("-") != -1) {
			try{
				window.event.keyCode = 0 ;
			}catch(e){
				eventObj.cancelBubble = true;
			}
			return false;
		}

		return true;
	},
	//站点编号 需要清空当前分类信息
	onchangeSiteId:function(_obj){
		//查看不处理
	/*	if($('#doAction').val()=='view'){
			return;
		}*/
		//清空分类信息
		//清空商品信息
		//清空单品信息
		//清空黑名单分类信息
		//清空黑名单商品信息
		//清空黑名单单品信息
     
	}
}
