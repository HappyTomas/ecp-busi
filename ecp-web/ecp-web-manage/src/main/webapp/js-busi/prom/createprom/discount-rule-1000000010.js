$(function(){ 
     var _result=3;
    $(".divCalNum").each(function () {
            var _data=$(this).attr("data");
             if( _data ){
            	 if(parseInt(_result)<parseInt(_data)){
            		 _result=_data;
            	 }
             }
             $('#index_1000000010').val(_result); 
        });
    var _i=parseInt(_result)+1;
    if (!$('#index_1000000010')){
    	 _i=parseInt($('#index_1000000010').val())+1;
    }
	//追加 事件
	 $("#btnMultBuyAdd").click(function(){
		   
     var _html=	'<div class="row-fluid" id="div'+_i+'" >'
			+'<div class="span6">'
			+'<div class="control-group">'	
			+'<label class="control-label" for="name">购买数量'+_i+'：</label>'	    
			+'<div class="controls">'		
			+'<input type="text" maxlength="12"  class="span8  number" id="buyAmount'+_i+'" name="discountMap[\'buyAmount'+_i+'\']" placeholder="请输入购买数量 "  />'			
			+'</div>'		
			+'</div>'	
			+'</div>'
			+'<div class="span6">'
			+'	<div class="control-group">'
			+'		 <label class="control-label" for="name">购买金额'+_i+'：</label>'
			+'		<div class="controls">'
			+'			<input type="text" maxlength="12"  class="span8  number" id="buyMoney'+_i+'" name="discountMap[\'buyMoney'+_i+'\']" placeholder="请输入购买金额 "  />'
			+'         <span class="help-inline"><button type="button" class="btn btnMultBuyDel" id='+_i+'  ><i class="icon-del icon-white"></i>删除</button></span>'
			+'		</div>'
			+'	</div>'
			+'</div>'
			+'</div>';
		   $('#multBuyAdd').before(_html); 
		   _i=_i+1;
		 });
	 
	 $(".btnMultBuyDel").live("click",function(){
		 var _id=$(this)[0].id;
		 $("#div"+_id).remove();
		});
	 
/*	//查询  隐藏按钮
	if($('#doAction').val()=="view"){
		//删除按钮
		$('.btnMultBuyDel').hide();
		//追加按钮
		$('#multBuyAdd').hide();
		
	}*/
});
//促销类型1000000010 
var discountRuleFun={
			hide:function(){
				$('.multiBuyClass').hide();
				$('.btnMultBuyDel').hide();
			},
			show:function(){
				$('.multiBuyClass').show();
				$('.btnMultBuyDel').show();
			}
};
