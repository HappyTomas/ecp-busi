$(function(){

	/*if($('#haveType').val()=="false"){
		eDialog.error("商品类型列表为空，请先到类型管理配置类型！");
	}
	*/
	/*
	
	$("#btn_next").click(function(){
		$("#catgClassForm").submit();
	});*/
	//$("#shopId option[value='101']").remove();
	$(".firstNode").die('click').live('click',function(){
	
			$(this).siblings().each(function(){
				$(this).removeClass('selected');
				$(this).find('span').html("");
			});
			 $this = $(this);
			var catgCode = $this.attr('id');
			var catgName = $this.attr('catgName');
			$("#selecEnd").html(catgName);
			$("#selecEnd").attr('value',catgCode);
			$("#selecEnd").attr('catgName',catgName);
			$("#catgCode").val(catgCode);
			$("#catgName").val(catgName);
			$this.parents(".sType-item").nextAll().remove();
			$this.addClass('selected');
			$this.children('span').html('&gt;');
			//var text = $this.children('span').text();
			//$this.children('div').html('');
			//$this.children('div').html("<p style='overflow: hidden; white-space: nowrap; text-overflow: ellipsis;width:120px;' >"+text+"</p>" +' <span class="more icon-angle-right"/>');
			GdsClass.getNextNode(catgCode);
			initTypeBox();
			$(".mscroll").mCustomScrollbar('update');
	
	});
	$(".icon-search").live('click',function(){
		GdsClass.search(this);
	});
	
	initTypeBox();
	
	GdsClass.queryClassShopId($("#shopId").val());
    $("#shopId").live('change',function(){
    	var selectShopId = $(this).val();
    	GdsClass.queryClassShopId(selectShopId);
    });
});

function _funBtnNext(){
	if(!GdsClass.isGdsTypeValid()){
		eDialog.alert('请选择产品类型！');
		return;
	}
	$('#gdsTypeId').val($('#gdsType option:selected').val());
	$('#gdsTypeName').val($('#gdsType option:selected').text());
	$('#shopIdVal').val($('#shopId option:selected').val());
	$("#catgClassForm").submit();
}
function checkedCat(obj,catgCode){
	GdsClass.checkedCat(obj,catgCode);
}

function initTypeBox(){
	var boxW = 0;
    $('.sType-box .sType-item').each(function () {
        boxW += $(this).outerWidth();
    });
    $('.sType-box').width(boxW);
    //var scrollPlu = $(".mscroll").mCustomScrollbar({
    $(".mscroll").mCustomScrollbar({
        axis: "x",
        theme: "light-3",
        advanced: {autoExpandHorizontalScroll: true}

    });
    $('.sType-item').each(function(){
        var scrollItem =$('.cont',$(this)).mCustomScrollbar({
            theme: "light-3"
        });
        $(this).hover(function(){
          $('.mCSB_draggerContainer',$(this)).show();
        },function(){
            $('.mCSB_draggerContainer',$(this)).hide();
        });
    });
    /*  window.setTimeout(function(){
     $(".mscroll").mCustomScrollbar("scrollTo","first");
     },1000);*/
}




var _chooseFlag = true;
var GdsClass = {
		
		queryClassShopId : function(shopId){
			var param = new Object();
			param.shopId = shopId;
			$.eAjax({
				url : GLOBAL.WEBROOT + "/seller/goods/gdsinfoentry/getCategorysByShop",
				data : param,
				dataType:'html',
				success : function(returnInfo) {
					$('#me').empty();
					$('#me').html(returnInfo);
					initTypeBox();
				}
			});	
			
		},
		queryClass : function(){
			$.eAjax({
				url : GLOBAL.WEBROOT + "/seller/goods/gdsinfoentry/queryclass",
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
				url : GLOBAL.WEBROOT + "/seller/goods/gdsinfoentry/getnextnode",
				async : true,
				data :{catgCode : catgCode},
				success : function(returnInfo) {
					var html="";
					var size = returnInfo.nextNodeList.length;
					if(returnInfo.ecpBaseResponseVO.resultFlag=='ok'){
						 for(var i= 0;i<size;i++){
			            	html+= "<li id='"+returnInfo.nextNodeList[i].catgCode+"' onclick=\"checkedCat(this,'"+returnInfo.nextNodeList[i].catgCode+"')\" class='listNode' name='catgCode"+catgCode+"' catgName='"+returnInfo.nextNodeList[i].catgName+"'>" +
			            	returnInfo.nextNodeList[i].catgName+'<span class="opt-left"></span>'+
			            	"</li>" ;
			             }
						
					}else{
						html+="<li name='catgCode"+catgCode+"'>" +
                     	returnInfo.ecpBaseResponseVO.resultMsg +
                     	"</li>";
					}
					var htmlContext = '<div class="sType-item" id="nextNode'+catgCode+'">'
					                      + '<div class="cont">'
					                        + '<ul class="cont-list">'
					                          + html   
					                        + '</ul>'
					                      + '</div>'
									 + '</div>';
					
//					if(size>=1 || returnInfo.ecpBaseResponseVO.resultFlag=='fail'){
					if(returnInfo.ecpBaseResponseVO.resultFlag=='fail'){
						console.error('fail');
						$("#me").append(htmlContext);
						$("#btn_next").attr("disabled",'true');
						$("#btn_next").removeClass('sbtn-blue');
						$("#btn_next").unbind('click');
					}else if(returnInfo.ecpBaseResponseVO.resultFlag=='ok' && size==0){
						$("#"+catgCode).find('span').html('&radic;');
						$("#btn_next").removeAttr('disabled');
						$("#btn_next").addClass('sbtn-blue');
						$("#selecEnd").append('<span class="icon-ok" style="color:green"/>');
						$("#btn_next").bind('click',_funBtnNext);
					}else{
						$("#me").append(htmlContext);
						$("#btn_next").removeAttr('disabled');
						$("#btn_next").addClass('sbtn-blue');
						$("#btn_next").bind('click',_funBtnNext);
					}
					_chooseFlag = true;
					initTypeBox();
					$(".mscroll").mCustomScrollbar('update');
				}
			});
		},
		checkedCat : function(obj,catgCode){
			$this = $(obj);
			$this.siblings().each(function(){
				$(this).removeClass('selected');
				$(this).find('span').html('');
			});
			var catgName = $this.attr('catgName');
			$this.addClass('selected');
			var newCatName = "";
			var count = 0;
			$this.parents(".sType-item").nextAll().each(function(){
				$(this).children().remove();
				$(this).remove();
			});
			var text = $this.children('span').text();
			$this.children('span').html('&gt;');
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
//			alert(catgCode);
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
			
		},
		/**
		 * 检查商品类型选择是否合法。
		 * @returns 商品类型合法返回true,否则返回false。
		 */
		isGdsTypeValid: function(){
			var bool = true;
			var gdsType = $('#gdsType').val();
			if($('#haveType').val()=="false" || '' == gdsType || undefined == gdsType){
				bool = false;
			}
			return bool;
		}
		

};