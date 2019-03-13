$(function() {

	//页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			 //满数量 满金额  radio
			 $(".fullLimit-type").click(function(){
				 rule140.radioClick(this);
			 });
			 $("#sumLimit").blur(function(){
				 rule140.checkInfo(this);
			 });
			 

		};

		return {
			init : init
		};
	};


	pageConfig.config({
		//指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm','bPage' ],
		//指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
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
				url : GLOBAL.WEBROOT + "/seller/coupcomm/140/"+_id,
				success : function(returnInfo) {
					if(returnInfo.resultFlag=='ok'){
						
						if(returnInfo.sumLimit){
							$('#sumLimit').val(returnInfo.sumLimit/100);
							$("input[name='fullLimitVO.type'][value=1]").attr("checked",'checked');
						}
						
						if(returnInfo.amount){
							$('#amount').val(returnInfo.amount);
							$("input[name='fullLimitVO.type'][value=2]").attr("checked",'checked');
						}
					}
				}
			});
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
