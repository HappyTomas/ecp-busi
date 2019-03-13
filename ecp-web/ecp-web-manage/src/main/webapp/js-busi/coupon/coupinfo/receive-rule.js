 
$(function(){
	 
    //清空
    $(".clearTr").live('click',function(_obj){ 
    	$("."+_obj).val('');
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