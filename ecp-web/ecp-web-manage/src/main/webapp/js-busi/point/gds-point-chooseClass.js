$(function(){
	
	if($('#haveType').val()=="false"){
		eDialog.error("商品类型列表为空，请先到类型管理配置类型！");
	}
	
	
	$("#btn_next").click(function(){
		$("#catgClassForm").submit();
	});
	
	$("li").each(function(){
		var $this = $(this);
		$this.click(function(){
			$this.siblings().each(function(){
				$(this).removeClass('selected');
				$(this).find('span').remove();
			});
			$this = $(this);
			var catgCode = $this.attr('id');
			var catgName = $this.attr('catgName');
			$("#selecEnd").html(catgName);
			$("#selecEnd").attr('value',catgCode);
			$("#selecEnd").attr('catgName',catgName);
			$("#catgCode").val(catgCode);
			$("#catgName").val(catgName);
			$this.parent().parent().parent().nextAll().remove();
			$this.addClass('selected');
			var text = $(this).children('div').text();
			$this.children('div').html('');
			$this.children('div').html(text +' <span class="more icon-angle-right"/>');
			GdsClass.getNextNode(catgCode);
		});
	
	});
	$(".icon-search").live('click',function(){
		GdsClass.search(this);
	});
});
function checkedCat(obj,catgCode){
	GdsClass.checkedCat(obj,catgCode);
}
var _chooseFlag = true;
var GdsClass = {
		queryClass : function(){
			$.eAjax({
				url : GLOBAL.WEBROOT + "/gdspointentry/queryclass",
				data : param,
				success : function(returnInfo) {
					
				}
			});
		},
		getNextNode : function(catgCode){
			if(!_chooseFlag){
				return ;
			}
			if(catgCode == undefined){
				catgCode = "";
			}
			_chooseFlag = false;
			$.eAjax({
				url : GLOBAL.WEBROOT + "/gdspointentry/getnextnode",
				async : true,
				data :{catgCode : catgCode},
				success : function(returnInfo) {
					var html="";
					var size = returnInfo.nextNodeList.length;
					if(returnInfo.ecpBaseResponseVO.resultFlag=='ok'){
						 for(var i= 0;i<size;i++){
			            	html+= "<li id='"+returnInfo.nextNodeList[i].catgCode+"' onclick=\"checkedCat(this,'"+returnInfo.nextNodeList[i].catgCode+"')\" class='listNode' name='catgCode"+catgCode+"' catgName='"+returnInfo.nextNodeList[i].catgName+"'>" +
		                     	"<div class='d-text' data-id='xx12' id='"+returnInfo.nextNodeList[i].catgCode+"'> "+returnInfo.nextNodeList[i].catgName+"</div>" +
		                     	"</li>" ;
			             }
						
					}else{
						html+="<li name='catgCode"+catgCode+"'>" +
                     	"<div class='d-text' data-id='xx12'> "+returnInfo.ecpBaseResponseVO.resultMsg+"</div>" +
                     	"</li>";
					}
					var htmlContext = " <div class='type-list' style='width:210px;' id='nextNode"+catgCode+"'>" +
//					"<div class='search'>"+
//		             "<div class='input-wrap'>" +
//		                 "<input style='width:148px;' type='text' placeholder='输入关键字查找'/><i class='icon-search'></i>" +
//		             "</div>" +
//		             "</div>" +
		             "<div class='lay-wrap'>" +
		             "<ul class='lay'>"+html+"</ul>" +
		             "</div>" +
		             "</div>";
//					if(size>=1 || returnInfo.ecpBaseResponseVO.resultFlag=='fail'){
					if(returnInfo.ecpBaseResponseVO.resultFlag=='fail'){
						$("#me").append(htmlContext);
						$("#btn_next").attr("disabled",'true');
						$("#btn_next").removeClass('btn-primary');
//					}else if(returnInfo.ecpBaseResponseVO.resultFlag=='ok' && size==0){
					}else if(returnInfo.ecpBaseResponseVO.resultFlag=='ok' && size==0){
						$("#"+catgCode).find('span').removeClass('icon-angle-right');
						$("#"+catgCode).find('span').addClass('icon-ok');
						$("#"+catgCode).find('span').attr('style','style="color:green"');
						if($('#haveType').val()=="true"){
						$("#btn_next").removeAttr('disabled');
						$("#btn_next").addClass('btn-primary');
						}
						$("#selecEnd").append('<span class="icon-ok" style="color:green"/>');
					}else{
						$("#me").append(htmlContext);
						if($('#haveType').val()=="true"){
						$("#btn_next").removeAttr('disabled');
						$("#btn_next").addClass('btn-primary');
						}
					}
					_chooseFlag = true;
				}
			});
		},
		checkedCat : function(obj,catgCode){
			$this = $(obj);
			$this.siblings().each(function(){
				$(this).removeClass('selected');
				$(this).find('span').remove();
			});
			var catgName = $this.attr('catgName');
			$this.addClass('selected');
			var newCatName = "";
			var count = 0;
			
			$this.parent().parent().parent().nextAll().each(function(){
				$(this).children().remove();
				$(this).remove();
			});
			var text = $this.children('div').text();
			$this.children('div').html('');
			$this.children('div').html(text +' <span class="more icon-angle-right"/>');
			$("#me").find('li').each(function(){
				$thisa = $(this);
				if($thisa.hasClass('selected')){
					count ++;
					if(count == 1){
						newCatName +=$thisa.attr('catgName');
					}else{
						newCatName += "→"+$thisa.attr('catgName');
					}
					
				}
				
			});
			$("#selecEnd").html('');
			$("#selecEnd").html(newCatName);
			$("#selecEnd").attr('value',catgCode);
			$("#selecEnd").attr('catgName',newCatName);
			$("#catgCode").val(catgCode);
			$("#catgName").val(catgName);
			GdsClass.getNextNode(catgCode);
		},
		search : function(obj){
			var _this = $(obj).parent().parent().parent();
			var keyWord = $(obj).parent().find('input').val();
			_this.find('.d-text').each(function(){
				var _obj = $(this);
				var _str = _obj.text();
				if(_str.indexOf(keyWord) > -1){
					_obj.parent().show();
				}else{
					_obj.parent().hide();
				}
			});
			
		}
		

};