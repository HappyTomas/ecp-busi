$(function() {

	//页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			//获得当前弹出窗口对象
			var _dlg = bDialog.getDialog();
			//获得父窗口传递给弹出窗口的参数集
			var _param = bDialog.getDialogParams(_dlg);
			//获得父窗口设置的回调函数
			//var _callback = bDialog.getDialogCallback(_dlg);

			//判断是否为数字
			isNum = function(s) {
				if (s != null && s != "") {
					return !isNaN(s);
				}
				return false;
			};
			
			//重置
			$('#btnFormReset').click(function() {
				ebcForm.resetForm('#searchForm');
			});
			//关闭
			$('#btnReturn').click(function() {
				bDialog.closeCurrent();
			});

			$('#moreGdsQueryBtn').unbind("click");
			//绑定提交按钮事件
			$('#moreGdsQueryBtn').click(
					function() {
						var gdsId = $("#gdsId").val();
						var skuId = $("#skuId").val();
						var promId= $("#promId").val();
						 if(gdsId!=""){
							 if(!isNum(gdsId)){
		 		    			    eDialog.alert('商品编码请输入有效的数字  '); 
		 		    				return ;
		 		    		  } 
						 }
						 if(skuId!=""){
		 		    		  if(!isNum(skuId)){
		 		    			    eDialog.alert('单品编码请输入有效的数字  '); 
		 		    				return ;
		 		    		  }  
						 }
						 if(promId!=""){
							 if(!isNum(promId)){
		 		    			    eDialog.alert('促销编码请输入有效的数字  '); 
		 		    				return ;
		 		    		  }  
						 }
						$('#gdsListDiv').load(
								GLOBAL.WEBROOT + '/seller/gdsprom/gridlist?promTypeCode='+$('#promTypeCode').val(), {"gdsId" : gdsId,"skuId" : skuId,"promId":promId});
					});
			//初始化加载
			$('#moreGdsQueryBtn').click();
		};

		return {
			init : init
		};
	};
	pageConfig.config({
		//指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm' ],
		//指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});
});