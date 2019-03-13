$(function(){
	$('.tips-list li').click(function(e){
        $(this).toggleClass('select');
        e.preventDefault();
    });
});



function textCounter(id,limitNum,showArea){
	uneval.textCounter(id,limitNum,showArea);
}

function reply(id){
	uneval.reply(id);
} 

function save(obj,id){
	

	uneval.save(obj,id);
} 

function chooseScore(obj,score){
	uneval.chooseScore(obj,score);
}



	var uneval = {
			textCounter : function(id,limitNum,showArea)   {			
				if   ($("#"+id).val().length  > limitNum)        
					//如果元素区字符数大于最大字符数，按照最大字符数截断；        
					$("#"+id).val($("#"+id).val().substring(0, limitNum));      
				else      
					//在记数区文本框内显示剩余的字符数；        
					$("#"+showArea).text($("#"+id).val().length);     
			},

			//弹出评价
			reply : function (id){
		//		alert(id);
				$("#"+id).toggle();
			},
			
			//保存评价
			save : function (obj,id){
				$.validator.setDefaults({
					ignore: ""
					});
				var form = id+"Form";
//				alert(form);
				if(!$("#"+form).valid())return false;
				
				if($(obj).attr("already")!="already"){
					$(obj).attr("already","already");
				$.eAjax({
					url : GLOBAL.WEBROOT + "/comment/save",
					data : ebcForm.formParams($("#"+form)),
					success : function(returnInfo) {
						
						
						if(returnInfo.resultFlag=='ok'){
							
							eDialog.success('评价成功！',{
								buttons:[{
									caption:"确定",
									callback:function(){
										
										window.location.href = GLOBAL.WEBROOT + "/comment/evaled";
						       	 	}
								}]
							}); 
						}else{
							eDialog.alert(returnInfo.resultMsg);
						}
					}
				});
				}
			},
			//选择分数
			chooseScore : function(obj,score){
				var star="star"+score;
				var parent=$(obj).parent().children();
//				$(this).parent().children().removeClass("active");
				parent.removeClass("active");
				$(obj).addClass("active");
				$(obj).parent().nextAll("#score").val(score);
				$(obj).parent().nextAll("span").remove();
			}
	};
	







