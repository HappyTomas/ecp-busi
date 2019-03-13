$(function() {

	//页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {
			 
		    //清空
		    $(".clearTr").live('click',function(_obj){ 
		    	$("."+_obj).val('');
		    });
           };

		return {
			init : init
		};
	};


	pageConfig.config({
		//指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm'],
		//指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});

});
var receiveInfo={
		//领取数量验证	
		valid:function(_obj){
			var _v=$(_obj).val();
			var _name=$(_obj).attr("name");
			var _errorId="#"+"num-error-"+_name;
			if(!ebcCheck.isInt(_v)){
				$(_errorId).show();
			}else{
				$(_errorId).hide();
			}
		},
		//清空
		clearTr:function(_objId){
			var _errorId="#"+"num-error-"+_objId;
			$(_errorId).hide();
			
			var _numId="#num-"+_objId;
			$(_numId).val('');
			
			var _startId="#start-"+_objId;
			$(_startId).val('');
			
			var _endId="#end-"+_objId;
			$(_endId).val('');
		}

};