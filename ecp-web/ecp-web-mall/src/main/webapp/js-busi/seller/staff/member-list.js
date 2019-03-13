//页面初始化模块
$(function(){
    var pInit = function(){
    	var init = function(){
    		//var shopId = $("#shopId").val();
    		var payMoneyFrom = $('input[name="payMoneyFrom"]').val();
    	    var payMoneyEnd = $('input[name="payMoneyEnd"]').val();
    	    var tradesNumFrom = $('input[name="tradesNumFrom"]').val();
    	    var tradesNumEnd = $('input[name="tradesNumEnd"]').val();
    	    var custLevelCode = $('#custLevelCode').val();
    	    var staffCode = $('#staffCode').val();
    	   
    	    //分页
	    	$('#pageControlbar').bPage({
	    	    url : GLOBAL.WEBROOT + '/seller/shopmgr/shopviplist',
	    	    asyncLoad : true,
	    	    asyncTarget : '#pageMainBox',
	    	    params : {
	    	    	payMoneyFrom : $('input[name="payMoneyFrom"]').val(),
	    	    	payMoneyEnd : $('input[name="payMoneyEnd"]').val(),
	    	    	tradesNumFrom : $('input[name="payMoneyEnd"]').val(),
	    	    	tradesNumEnd : $('input[name="payMoneyEnd"]').val(),
	    	    	custLevelCode : $('#custLevelCode').val(),
	    	    	staffCode : $('#staffCode').val(),
	    	    	
	    	    }
	    	});
	    	//重置
	    	
    		$('#btnFormReset').click(function(){
    			ebcForm.resetForm('#shopInformation');
    			var shopId = $('#shopId').val();
    			var level = $('#custLevelCode');//获取等级列表
    			level.html('');//清空列表
    			$.eAjax({
    				url : GLOBAL.WEBROOT + "/seller/shopmgr/shoplevellist",
    				data : {'shopId':shopId},
    				datatype:'json',
    				success : function(returnInfo) {
    					if (returnInfo.flag == 'ok') {
    						if (!returnInfo.levelList) {
    							level.html("<option value=''>请选择</option>");
    							return;
    						}
    						var levelList = JSON.parse(returnInfo.levelList);//店铺等级列表
    						var levelContent = "<option value=''>请选择</option>";
    						for (var a = 0; a < levelList.length; a++) {
    							levelContent +="<option value=" + levelList[a].vipLevelCode + ">" + levelList[a].vipLevelName + "</option>";
    						}
    						level.html(levelContent);
    					} else {
    						level.html("<option value=''>请选择</option>");
    					}
    				}
    			});
    		});
	    	//绑定查询按钮事件
	    	$('#queryCommitBtn').unbind('click');
	    	$('#queryCommitBtn').click(function(){
	    		var shopId = $("#shopId").val();
	    		var payMoneyFrom = $('input[name="payMoneyFrom"]').val();
	    	    var payMoneyEnd = $('input[name="payMoneyEnd"]').val();
	    	    var tradesNumFrom = $('input[name="tradesNumFrom"]').val();
	    	    var tradesNumEnd = $('input[name="tradesNumEnd"]').val();
	    	    var custLevelCode = $('#custLevelCode').val();
	    	    var staffCode = $('#staffCode').val();
	    	    //交易次数不为空
	    	    if(tradesNumFrom!=''){
	    	    	//判断是否是数字
	    	    	if(!isNumber(tradesNumFrom))
	    	    	{
	    	    		eDialog.alert('交易次数最小值请输入数字！');
	    	    		return ; 
	    	    	} 
	    	    	if(tradesNumFrom < 0){
						eDialog.alert('交易次数-最小值不能小于0');
						return ; 
	    	    	}
	   			   //判断是不是整数，不是则提示输入整数
	    	    	if(!ispositive(tradesNumFrom)){
		   				eDialog.alert('交易次数最大值请输入整数！');
			    		return ; 
	   			   }
	   			   /*if( !ebcCheck.isInt(tradesNumFrom)){
	   				   	eDialog.alert('交易次数-最小值不是正整数');
	  					return ; 
	   			   }*/
	    	    }
	   	
	    	    if(tradesNumEnd!=''){
	    	    	if(!isNumber(tradesNumEnd))
	    	    	{
	    	    		eDialog.alert('交易次数最大值请输入数字！');
	    	    		return ; 
	    	    	} 
	   			   if(tradesNumEnd < 0){
			    		eDialog.alert('交易次数-最大值不能小于0');
			    		return ; 
	   			   }
	   			   //不是正整数
	   			   if(!ispositive(tradesNumEnd)){
		   				eDialog.alert('交易次数最大值请输入整数！');
			    		return ; 
	   			   }
	   			 /*  if( !ebcCheck.isInt(tradesNumEnd)){
	   				   	eDialog.alert('交易次数-最大值不是正整数');
	  					return ; 
	   			   }*/
	    	    }
	    	    if(tradesNumEnd!='' && tradesNumFrom!=''){
	    	    	i=parseInt(tradesNumEnd);
	    	    	j=parseInt(tradesNumFrom);
	   			   if(i < j){
					   	eDialog.alert('交易次数-最小值大于交易次数-最大值');
						return ;
				   }
	    	    }
	    	    //交易金额不为空
	    	    if(payMoneyFrom!=''){
	    	    	//判断是否是数字
	    	    	if(!isNumber(payMoneyFrom))
	    	    	{
	    	    		eDialog.alert('交易金额最小值请输入数字！');
	    	    		return ; 
	    	    	} 
	    	    	if(payMoneyFrom < 0){
						eDialog.alert('交易金额-最小值不能小于0');
						return ; 
	    	    	}
	    	    	 if(!ispositive(payMoneyFrom)){
			   				eDialog.alert('交易金额最小值请输入整数！');
				    		return ; 
	    	    	 }
	   			   /*//不是正整数
	   			   if( !ebcCheck.isInt(payMoneyFrom)){
	   				   	eDialog.alert('交易金额-最小值不是正整数');
	  					return ; 
	   			   }*/
	    	    }
	    	    //交易 
	    	    if(payMoneyEnd!=''){
	    	    	if(!isNumber(payMoneyEnd))
	    	    	{
	    	    		eDialog.alert('交易金额最大值请输入数字！');
	    	    		return ; 
	    	    	} 
	   			   if(payMoneyEnd < 0){
			    		eDialog.alert('交易金额-最大值不能小于0');
			    		return ; 
	   			   }
	   			 if(!ispositive(payMoneyEnd)){
		   				eDialog.alert('交易金额最大值请输入整数！');
			    		return ; 
	   			 }
	   			   //不是正整数
	   			   /*if( !ebcCheck.isInt(payMoneyEnd)){
	   				   	eDialog.alert('交易金额-最大值不是正整数');
	  					return ; 
	   			   }*/
	    	    }
	    	    if(payMoneyEnd!='' && payMoneyFrom!=''){
	    	    	i=parseInt(payMoneyEnd);
	    	    	j=parseInt(payMoneyFrom);
	   			   if(i < j){
					   	eDialog.alert('交易金额-最小值大于交易金额-最大值');
						return ;
				   }
	    	    }
	    	    
	    		$('#pageMainBox').load(GLOBAL.WEBROOT + '/seller/shopmgr/shopviplist?v='+Math.random(), 
	    		{ 	
	    			"payMoneyFrom":payMoneyFrom,
	    			"payMoneyEnd":payMoneyEnd,
	    			"tradesNumFrom":tradesNumFrom,
	    			"tradesNumEnd":tradesNumEnd,
	    			"custLevelCode":custLevelCode,
	    			"staffCode":staffCode,
	    			"shopId":shopId,
	    		});
	    	});
	    	
	    	
	    	
		};
    	return {
    		init : init
    	};
	};    	
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bPage','bForm'],
		//指定页面
		init : function(){
			var scoreList = new pInit();
			scoreList.init();
		}
	});
});

//删除操作
function deleteReal(id){
	eDialog.confirm("您确认要删除吗？", {
		buttons : [{
			caption : '确认',
			callback : function(){
				$.eAjax({
					url : GLOBAL.WEBROOT + "/seller/shopmgr/remove",
					data : {'id':id},
					datatype:'json',
					success : function(returnInfo) {
						if (returnInfo.resultFlag == 'ok') {
							var shopId = $("#shopId").val();
				    		var payMoneyFrom = $('input[name="payMoneyFrom"]').val();
				    	    var payMoneyEnd = $('input[name="payMoneyEnd"]').val();
				    	    var tradesNumFrom = $('input[name="tradesNumFrom"]').val();
				    	    var tradesNumEnd = $('input[name="tradesNumEnd"]').val();
				    	    var custLevelCode = $('#custLevelCode').val();
				    	    var staffCode = $('#staffCode').val();
				    	    $('#pageMainBox').load(GLOBAL.WEBROOT + '/seller/shopmgr/shopviplist?v='+Math.random(), 
				    	    		{ 	
				    	    			"payMoneyFrom":payMoneyFrom,
				    	    			"payMoneyEnd":payMoneyEnd,
				    	    			"tradesNumFrom":tradesNumFrom,
				    	    			"tradesNumEnd":tradesNumEnd,
				    	    			"custLevelCode":custLevelCode,
				    	    			"staffCode":staffCode,
				    	    			"shopId":shopId,
				    	    		});
				    	    
						} else {
							eDialog.alert(returnInfo.resultMsg);
						}
					}
				});
				
			}
		},{
			caption : '取消',
			callback : $.noop
		}]
	});
};
//失效操作
function invalid(id){
	eDialog.confirm("您确认要更改为失效状态吗？", {
		buttons : [{
			caption : '确认',
			callback : function(){
				$.eAjax({
					url : GLOBAL.WEBROOT + "/seller/shopmgr/statusEdit",
					data : {'status':'0','id':id},
					datatype:'json',
					success : function(returnInfo) {
						if (returnInfo.resultFlag == 'ok') {
							var shopId = $("#shopId").val();
				    		var payMoneyFrom = $('input[name="payMoneyFrom"]').val();
				    	    var payMoneyEnd = $('input[name="payMoneyEnd"]').val();
				    	    var tradesNumFrom = $('input[name="tradesNumFrom"]').val();
				    	    var tradesNumEnd = $('input[name="tradesNumEnd"]').val();
				    	    var custLevelCode = $('#custLevelCode').val();
				    	    var staffCode = $('#staffCode').val();
				    	    $('#pageMainBox').load(GLOBAL.WEBROOT + '/seller/shopmgr/shopviplist?v='+Math.random(), 
				    	    		{ 	
				    	    			"payMoneyFrom":payMoneyFrom,
				    	    			"payMoneyEnd":payMoneyEnd,
				    	    			"tradesNumFrom":tradesNumFrom,
				    	    			"tradesNumEnd":tradesNumEnd,
				    	    			"custLevelCode":custLevelCode,
				    	    			"staffCode":staffCode,
				    	    			"shopId":shopId,
				    	    		});
				    	    
						} else {
							eDialog.alert(returnInfo.resultMsg);
						}
						
						
					}
				});
				
			}
		},{
			caption : '取消',
			callback : $.noop
		}]
	});
};
//店铺切换事件
function shopChange() {
	var shopId = $('#shopId').val();
	var level = $('#custLevelCode');//获取等级列表
	level.html('');//清空列表
	$.eAjax({
		url : GLOBAL.WEBROOT + "/seller/shopmgr/shoplevellist",
		data : {'shopId':shopId},
		datatype:'json',
		success : function(returnInfo) {
			if (returnInfo.flag == 'ok') {
				if (!returnInfo.levelList) {
					level.html("<option value=''>请选择</option>");
					return;
				}
				var levelList = JSON.parse(returnInfo.levelList);//店铺等级列表
				var levelContent = "<option value=''>请选择</option>";
				for (var a = 0; a < levelList.length; a++) {
					levelContent +="<option value=" + levelList[a].vipLevelCode + ">" + levelList[a].vipLevelName + "</option>";
				}
				level.html(levelContent);
			} else {
				level.html("<option value=''>请选择</option>");
			}
		}
	});
}
//生效操作
function live(id){
	eDialog.confirm("您确认要更改为生效状态吗？", {
		buttons : [{
			caption : '确认',
			callback : function(){
				$.eAjax({
					url : GLOBAL.WEBROOT + "/seller/shopmgr/statusEdit",
					data : {'status':'1','id':id},
					datatype:'json',
					success : function(returnInfo) {
						if (returnInfo.resultFlag == 'ok') {
							var shopId = $("#shopId").val();
				    		var payMoneyFrom = $('input[name="payMoneyFrom"]').val();
				    	    var payMoneyEnd = $('input[name="payMoneyEnd"]').val();
				    	    var tradesNumFrom = $('input[name="tradesNumFrom"]').val();
				    	    var tradesNumEnd = $('input[name="tradesNumEnd"]').val();
				    	    var custLevelCode = $('#custLevelCode').val();
				    	    var staffCode = $('#staffCode').val();
				    	    $('#pageMainBox').load(GLOBAL.WEBROOT + '/seller/shopmgr/shopviplist?v='+Math.random(), 
				    	    		{ 
				    	    			"payMoneyFrom":payMoneyFrom,
				    	    			"payMoneyEnd":payMoneyEnd,
				    	    			"tradesNumFrom":tradesNumFrom,
				    	    			"tradesNumEnd":tradesNumEnd,
				    	    			"custLevelCode":custLevelCode,
				    	    			"staffCode":staffCode,
				    	    			"shopId":shopId,
				    	    		});
				    	    
						} else {
							eDialog.alert(returnInfo.resultMsg);
						}
						
					}
				});
				
			}
		},{
			caption : '取消',
			callback : $.noop
		}]
	});
};
//检测是否是数字
function isNumber(value){
	return /^[(-?\d+\.\d+)|(-?\d+)|(-?\.\d+)]+$/.test(value + '');
	}
//检测是否是正整数
function ispositive(value){
	return /^[0-9]*[1-9][0-9]*$/.test(value + '');
	}	