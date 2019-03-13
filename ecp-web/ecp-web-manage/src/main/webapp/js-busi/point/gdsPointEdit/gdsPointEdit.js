var stock_switch = "";
var price_switch = "";
var multi_switch = "";
var _number = /^([0-9]+)$/;
var _price = /^\d+(\.\d{1,2})?$/;
$(function(){
	GdsInfoEntry.initValid();
	/**
	 * 商品详情
	 */
	if ($("#GDS_DESC").val() != "") {
		GdsInfoEntry.setCkeditValue("gdsDesc", $("#GDS_DESC").val());
	}
	/**
	 * 包装清单
	 */
	if ($("#GDS_PARTLIST").val() != "") {
		GdsInfoEntry.setCkeditValue("gdsPartlist", $("#GDS_PARTLIST").val());
	}
	/**
	 * 富文本的属性
	 */
	//获取富文本的
	$("textarea[nameValue='editor']").each(function(){
		var _obj = $(this);
		var _url = _obj.attr("gdsValue");
		$.ajax({
			url : _url,
			async : true,
			dataType : 'jsonp',
			jsonp : 'jsonpCallback',// 注意此处写死jsonCallback
			success : function(data) {
				_obj.html(data.result);
				KindEditor.html(_obj,data.result);
				KindEditor.sync(_obj);
			}
		 });
	});
	var copyFlag = $("#copyFlag").val();
	stock_switch = $("#stock-switch").val();
	price_switch = $("#price-switch").val();
	multi_switch = $("#multi-switch").val();
	var skuList = $("#skuList").val();
	if(skuList == ""){
		skuList = "[]";
	}
	var json = eval(skuList);
	var jsonsize = json.length;
	for(var i = 0;i< jsonsize ;i++){
		var obj = json[i];
		var _propStr = "";
		//单品属性串
		var skuPropList = obj.props; 
		if(skuPropList!= null && multi_switch =="1"){
			for( var j =0 ;j<skuPropList.length;j++){
				var tempObj = skuPropList[j];
				//属性值
				var valuesList = tempObj.values;
				for(var v =0;v<valuesList.length;v++){
					var value = valuesList[v];
					_propStr = _propStr + value.id +"_";
					var checkobj = $("#checkPropValue_" + tempObj.id + "_" + value.id);
					if(checkobj.attr("checked")!="checked"){
						checkobj.trigger("click");
					}
				}
			}
		}
		//单品库存
		var stockParam = "";
		if(obj.ifDisperseStock=='1'){
			//分仓库存
			var skuStockList = obj.stockRepDTOs;
			if(skuStockList.length>0){
				if(stock_switch=="1"){
					stockParam += "[";
					for(var s = 0 ;s<skuStockList.length;s++){
						var _stockObj = skuStockList[s];
						var _stockAdapt = _stockObj.stockInfoDTOs;
						var _areaListStr = "[";
						for(var a = 0;a<_stockAdapt.length;a++){
							var _area = _stockAdapt[a];
							//{country:'"+ $!{area.adaptCountry}+"',provinceCode:'"+ $!{area.adaptProvince}+"',cityCode:'"+ $!{area.adaptCity}+"'}
							_areaListStr += "{country:'"+_area.adaptCountry+"',provinceCode:'"+_area.adaptProvince+"',cityCode:'"+_area.adaptCity+"'},";
						}
						_areaListStr += "]";
						//param += "{skuStock:'"+$this.val()+"',repType:'"+$this.attr('repTyep')+"',repCode:'"+$this.attr('repCode')+"',switch:'1',areaList:"+$this.attr('areaStr')+"},";
						stockParam += "{skuStock:'"+_stockAdapt[0].availCount+"',repType:'"+_stockAdapt[0].repType+"',repCode:'"+_stockAdapt[0].repCode+"',switch:'1',areaList:"+_areaListStr+"},";
					}
					stockParam += "]";
				}
			}
		}else{
			var commonStock = obj.stockInfoRespDTO;
			if(commonStock != null){
				stockParam = commonStock.realCount;
			}
		}
		
		//单品图片
		var skuMediaList = obj.sku2MediaRespDTOs;
		var mediaParam = "[";
		for(var m = 1;m<skuMediaList.length+1;m++){
			var mediaObj = skuMediaList[m-1];
			//meidaRtype 1 为媒体库引用，2 直接上传 。 mediaType 1位图片，2位视频 3为音频
			mediaParam += "{picVfsId"+m+":'"+mediaObj.mediaUuid+"',picName:'',mediaRtype:'"+mediaObj.meidaRtype+"',mediaType:'"+mediaObj.mediaType+"',sortNo:'"+mediaObj.sortNo+"',url:'"+mediaObj.URL+"',mediaId:'"+mediaObj.mediaId+"'},";
			
		}
		mediaParam += "]";
		//单品价格
		var skuPriceList = obj.sku2PriceRespDTOs;
		var priceParam = "";
		if(skuPriceList.length > 0){
			if(price_switch=="1"){
				priceParam += "[";
				for(var p = 0 ;p<skuPriceList.length;p++){
					var priceObj = skuPriceList[p];
					//[{skuPrice:'',priceType:'',priceTarget:'',defaultPrice:'',switch:''}]
					if(priceObj.priceTypeId =='1'){
						priceParam += "{skuPrice:'',priceTarget:'"+priceObj.price.priceTarget+"',priceTypeCode:'"+priceObj.priceTypeCode+"',priceTypeId:'"+priceObj.priceTypeId+"',defaultPrice:'"+GdsInfoEntry.strToPrice(priceObj.price.price)+"',switch:'1'},";
					}else{
						priceParam += "{skuPrice:'"+GdsInfoEntry.strToPrice(priceObj.price.price)+"',priceTarget:'"+priceObj.price.priceTarget+"',priceTypeCode:'"+priceObj.priceTypeCode+"',priceTypeId:'"+priceObj.priceTypeId+"',defaultPrice:'',switch:'1'},";
					}
					
				}
				priceParam += "]";
				
			}else{
				for(var s = 0 ;s<skuPriceList.length;s++){
					var priceObj = skuPriceList[s];
					priceParam += priceObj.price.price;
				}
			}
		}
		if(multi_switch =="1"){
			$("#sku-param-table").find('tr').each(function(index, element){
				var $tr = $(this);
				if(index ==0){
					
				}else{
					var hprop = "";
					$tr.find('.property').each(function(){
						var $prop = $(this);
						hprop = hprop+$prop.attr('valueId')+"_";
					});
					if(_propStr == hprop){
						if(stock_switch ==1){
							$tr.find('td[id="senior-stock"]').children('a').attr('value',stockParam);
						}else{
							if(copyFlag !=1){
								$tr.find('input[name="stock_param"]').attr('disabled',true);
							}
							$tr.find('input[name="stock_param"]').val(stockParam);
						}
						if(price_switch ==1){
							$tr.find('td[id="senior-price"]').children('a').attr('value',priceParam);
						}else{
							$tr.find('input[name="price_param"]').val(GdsInfoEntry.strToPrice(priceParam));
						}
						$tr.children().last().find('a').attr('value',mediaParam);
						$tr.attr('skuId',obj.id);
					}
				}
			});
		}else{
			//高级库存没开
			if(stock_switch ==1){
				$("#productStock").attr('value',stockParam);
			}else{
				$("#productStock").val(stockParam);
			}
			if(price_switch ==1){
				$(this).find('a[class="gjpri"]').attr('value',priceParam);
			}else{
				$("#productPrice").val(GdsInfoEntry.strToPrice(priceParam));
			}
			$("#singleSkuId").val(obj.id);
		}
		$("#productStock").val(stockParam);
	}
	$("#gdsCombine").live('click',function(){
		var param = $("#combineGds").val();
		if(param ==""){
			param = "[]";
		}
		bDialog.open({
            title : "选择组合产品",
            width : 900,
            height : 850,
            params : {
            	'param' : param
            },
            url : GLOBAL.WEBROOT + "/gdspointentry/togdscombine",
            callback:function(data){
            	if(data && data.results && data.results.length > 0 ){
            		$("#combineGds").val(data.results[0].param);
            	}
            }
        });
	});
	$('#selptPlat').live('click',function(){
		bDialog.open({
            title : '平台分类选择',
            width : 350,
            height : 550,
            params:{},
            url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=1&catlogId=2",
            callback:function(data){
            	if(data && data.results && data.results.length > 0 ){
                    var _catgs = data.results[0].catgs;
					var size = _catgs.length;
					for(var i =0;i<size;i++){
						var count = 0;
						var obj = _catgs[i];
						$("#catagoryList").find('tr').each(function(){
							$this = $(this);
							catgCode = $this.children('td').eq(0).attr('id');
								if(catgCode == obj.catgCode){
									count ++;
								}
						});
						if(count == 0){
							$("#catagoryList").append('<tr><td id="'+obj.catgCode+'">'+obj.catgName+'</td><td id="2">副分类</td><td><a href="javascript:void(0)" onclick="deleteCatPlat(this)">删除</a></td></tr>');
						}
					}
				}
            }
        });
    });
	$('#seldpShop').live('click',function(){
		bDialog.open({
            title : '店铺分类选择',
            width : 350,
            height : 550,
            params:{},
            url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=2&catlogId=2",
            callback:function(data){
            	if(data && data.results && data.results.length > 0 ){
                    var _catgs = data.results[0].catgs;
					var size = _catgs.length;
					for(var i =0;i<size;i++){
						var count = 0;
						var obj = _catgs[i];
						$("#shopCatagoryList").find('tr').each(function(){
							$this = $(this);
							catgCode = $this.children('td').eq(0).attr('id');
								if(catgCode == obj.catgCode){
									count ++;
								}
						});
						if(count == 0){
							$("#shopCatagoryList").append('<tr><td id="'+obj.catgCode+'">'+obj.catgName+'</td><td><a href="javascript:void(0)" onclick="deleteCatPlat(this)">删除</a></td></tr>');
						}
					}
				}
            }
        });
    });
	
   $("#openGdsShiptemp").live('click',function(){
       bDialog.open({
           title : "运费模板管理",
           width : 800,
           height : 700,
           params : {
        	   'shopId' : $("#shopId").val()
           },
           url : GLOBAL.WEBROOT + "/gdspointentry/toshiptemp",
           callback:function(data){
        	   if(data && data.results && data.results.length > 0 ){
        		   $("#shipTemplateId").val(data.results[0].tempName);
				   $("#shipTemplateId").attr('tempId',data.results[0].tempId);
				}
           }
       });
   });
    $(".switch-button").find('.btn').live('click',function(e){
    	$this_btn = $(this);
		$this = $this_btn.closest('.btns_state');
//		if($this_btn.hasClass('active')) {
			if("multi-open" == $this_btn.val()){
				multi_switch = "1";
				$("#setting-price-label").hide();
				$("#setting-price-input").hide();
				$("#param-senior-button").find('button[value=multi-open]').addClass('active');
				$("#param-senior-button").find('button[value=multi-close]').removeClass('active');
				$("#gds-skuParam").show();
				$("#param-senior-button").show();
				$("#price-senior-button").hide();
			}else if('multi-close' == $this_btn.val()){
				multi_switch = "0";
				$("#setting-price-label").show();
				$("#setting-price-input").show();
				$("#price-senior-button").find('button[value=multi-close]').addClass('active');
				$("#price-senior-button").find('button[value=multi-open]').removeClass('active');
				$("#gds-skuParam").hide();
				$("#param-senior-button").hide();
				$("#price-senior-button").show();
			}
		e.preventDefault();
    });
    $("#topSave").click(function(){
    	$("#saveGdsInfo").trigger('click');
    });
    $("#topback").click(function(){
    	$("#backGdsManage").trigger('click');
    });
    $("#saveGdsInfo").click(function(){
    	if(!$("#baseInfoForm").valid()){
    		$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html')
    				: $('body'))
    				: $('html,body');
    		$body.animate({
    			scrollTop : $("#gds-category").offset().top
    		}, "fast");
    		return ;
    	}
    	if(!$("#priceSettingForm").valid()){
    		$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html')
    				: $('body'))
    				: $('html,body');
    		$body.animate({
    			scrollTop : $("#gds-skuParam").offset().top
    		}, "fast");
    		return;
    	} 
    	if(!$("#gdsSkuForm").valid()){
    		$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html')
    				: $('body'))
    				: $('html,body');
    		$body.animate({
    			scrollTop : $("#gds-skuParam").offset().top
    		}, "fast");
    		return ;
    	} 
    	if(!$("#attributeForm").valid()){
    		$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html')
    				: $('body'))
    				: $('html,body');
    		$body.animate({
    			scrollTop : $("#gds-pictrue").offset().top
    		}, "fast");
    		return;
    	}
    	if(!$("#settingForm").valid()){
    		$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html')
    				: $('body'))
    				: $('html,body');
    		$body.animate({
    			scrollTop : $("#gds-attr").offset().top
    		}, "fast");
    		return;
    	} 
    	if(!GdsInfoEntry.saveGdsInfoValidator()){
    		$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html')
    				: $('body'))
    				: $('html,body');
    		$body.animate({
    			scrollTop : $("#gds-skuParam").offset().top
    		}, "fast");
    		return;
    	}
		GdsInfoEntry.saveGdsInfo();
    });
    $("#backGdsManage").click(function(){
    	window.location.href = GLOBAL.WEBROOT+"/gdspointmanage?shopId="+$("#shopId").val();
    });
    
    $(".add-lader-price").live('click',function(){
    	var $thisInput = $("#lader-price-table").find('tr').last().find("#score");
    	var _length = $("#lader-price-table").find('tr').length;
//    	if(_length >=3){
//    		return;
//    	}
    	$thisInput.siblings('br').remove();
		$thisInput.siblings('span').remove();
		var html = "<select style='width:120px;' id='pointPricingModel' onchange='changePoint(this)' name='pointPricingModel"+_length+"'>"+
		"<option value='1'>纯积分</option>"+
		"<option value='3'>纯价格</option>"+
		"<option value='2'>积分和价格</option>"+
		"</select>";
    	var htmlContext = "<tr>"+
    	"<td>"+html+"</td>"+
        "<td><input type='text' class='input-mini required digits' id='score' name='score"+_length+"' value='' onblur='validateLaderAmount(this)' maxlength='9'/></td>"+
        "<td><input type='text' class='input-mini priceNumber priceLength' id='scorePrice' name='score-price"+_length+"' value='' onblur='validateLaderPrice(this)' maxlength='9' disabled/></td>"+
        "<td><input type='radio' name='ifDefault' checked/></td>"+
        "<td><a href='javascript:void(0);' onclick='delteLaderPrice(this)'>删除</a></td></tr>";
    	$("#lader-price-table").append(htmlContext);
//    	if($("#lader-price-table").find('tr').length >= 3){
//    		$(".add-lader-price").attr('style','color:#999');
//    		return;
//    	}
    });
    var more = 1;
    $("#more-pictrue").click(function(){
    	$this = $(this);
    	if(more == 1){
    		$this.find('i').removeClass('icon-caret-down');
    		$this.find('i').addClass('icon-caret-up');
    		$this.find('span').text('收起');
    		more = -1;
    		$("#picture-block").show();
    		GdsInfoEntry.queryMediaList();
    	}else{
    		$this.find('i').removeClass('icon-caret-up');
    		$this.find('i').addClass('icon-caret-down');
    		$this.find('span').text('更多（从图片库选择）');
    		more = 1;
    		$("#picture-block").hide();
    		
    	}
    });
    $("#gds-lader-price-open").click(function(){
    	var $this = $(this);
    	if($this.attr('checked') == 'checked'){
    		$("#lader-price-block").show();
    	}else{
    		$("#lader-price-block").hide();
    	}
    });
    /**
     * 图片上传
     */
//	$('.imgUpload').each(function(){
//		var $this = $(this);
//		$this.live("click", function(e) {
//			GdsInfoEntry.imgUpload(this,e);
//		});
//	});
    $('.com_input').parent().find("input[type=button]").unbind('click').click(
			function() {
				$(this).parent().find("input[type=file]").trigger("click");
			});

    $('.com_input').unbind('change').change(
			function() {
				var path = $(this).val();
				GdsInfoEntry.uploadImage(this, path);
			});
   //选择图片的
    $(".imgcont").click(function(){
	  	$(".imgcont").each(function(){
	  		 $(this).removeAttr("style");
		});
	    $(this).attr('style',"border-color: red");
	});	 
    //===========多选事件绑定start
    $.fn.mutiselect=function(){
        return this.each(function(){
            var _this=$(this);
            var _seldrop=$('.sel-drop', _this);
            $(_this).hover(function(){
                _this.find('.body').fadeToggle();
                _seldrop.addClass('icon-chevron-up').removeClass('icon-chevron-down');
            },function(){
            	_this.find('.body').fadeToggle();
			   	_seldrop.removeClass('icon-chevron-up').addClass('icon-chevron-down');
			});
            _this.find('label').on('click',function(){
                selCheckbox();
            });
            selCheckbox();
            function selCheckbox(){
                var _hdhtml='';
                var _c = 0;
                _this.find('.selcheckbox').each(function(){
                    if($(this).is(":checked")){
                        _hdhtml+=$(this).val()+',';
                        _c ++;
                    }
                });
                $('.head',_this).val(_hdhtml);
                if(_c > 0 ){
              	  $("#attributeForm").valid();
              }
            }
        });
    };
    $('.mutiselect').mutiselect();
	$('.sel-drop').click(function(){
	    $('.mutiselect').trigger('click');
	});
 //===========多选事件绑定end
	//是否免邮事件绑定===========start
	$("#ifFree").click(function(){
		var _this = $(this);
		if(_this.attr('checked')=='checked'){
			$("#gdsShiptempSetting").hide();
		}else{
			$("#gdsShiptempSetting").show();
		}
	});
	//是否免邮事件绑定===========end
});
/** 页面属性点击顺序 */
var sort = new Array();
/** 需要动态添加的html */
var addHtml = "";
/** 动态表格组件Id */
var tagIdCount = 0;
/** ckeditor */
var xiangQing = "";
var orderDescribe = "";
function checkSkuParameter(propId, propName, propValueId, propValue,currentIndex,indexCount,obj){
	GdsInfoEntry.checkSkuParameter(propId, propName, propValueId, propValue,currentIndex,indexCount,obj);
}
function textCounter(id,limitNum,showArea){
	GdsInfoEntry.textCounter(id,limitNum,showArea);
}
function choseGdsCat(obj){
	GdsInfoEntry.choseGdsCat(obj);
}
function validateLaderPrice(obj){
	GdsInfoEntry.validateLaderPrice(obj,'price');
}
function validateLaderAmount(obj){
	GdsInfoEntry.validateLaderPrice(obj,'amount');
}
function delteLaderPrice(obj){
	GdsInfoEntry.delteLaderPrice(obj);
}
function openStockWin(obj){
	GdsInfoEntry.openStockWin(obj);
}
function openPriceWin(obj){
	GdsInfoEntry.openPriceWin(obj);
}
function openSkuPictrueWindow(obj){
	GdsInfoEntry.openSkuPictrueWindow(obj);
}
function deleteCatPlat(obj){
	GdsInfoEntry.deleteCatPlat(obj);
}
function clickThis(obj){
	$(obj).parent().find("input[type=file]").trigger('click');
}
function validatorSku(obj,str){
	GdsInfoEntry.validatorSku(obj,str);
}
function searchMedia(){
	GdsInfoEntry.queryMediaList();
}
function changePoint(obj){
	var _value = $(obj).val();
	var _obj = $(obj).parent().parent();
	if(_value == '1'){
		_obj.find('input[id="scorePrice"]').attr("value","");
		_obj.find('input[id="scorePrice"]').attr('disabled',true);
		_obj.find('input[id="scorePrice"]').removeClass('required');
		_obj.find('input[id="score"]').removeAttr('disabled');
		_obj.find('input[id="score"]').addClass('required');
	}else if(_value == '3'){
		_obj.find('input[id="score"]').attr("value","");
		_obj.find('input[id="score"]').attr('disabled',true);
		_obj.find('input[id="score"]').removeClass('required');
		_obj.find('input[id="scorePrice"]').removeAttr('disabled');
		_obj.find('input[id="scorePrice"]').addClass('required');
	}else if(_value =='2'){
		_obj.find('input[id="score"]').removeAttr('disabled');
		_obj.find('input[id="score"]').addClass('required');
		_obj.find('input[id="scorePrice"]').removeAttr('disabled');
		_obj.find('input[id="scorePrice"]').addClass('required');
	}
}
var GdsInfoEntry = {
		bindPicUpload : function(){
			$('.com_input').parent().find("input[type=button]").unbind('click').click(
					function() {
						$(this).parent().find("input[type=file]").trigger("click");
					});

		    $('.com_input').unbind('change').change(
					function() {
						var path = $(this).val();
						GdsInfoEntry.uploadImage(this, path);
					});
		},
		initValid : function(){
			jQuery.validator.addMethod("compareTime", function(value, element) {
				var endTimeId = element.id;
				var startTime= $("#putonTime").val();
				var endTime = $("#" + endTimeId).val();

				if (endTime == "" || startTime == "") {
					return true;
				}
				/**
				 * 解决IE不支持trim问题
				 */
				if (!String.prototype.trim) {
					String.prototype.trim = function() {
						return this.replace(/^\s+|\s+$/g, '');
					};
				}
				if (startTime.trim().length == 10) {
					startTime = startTime + " 00:00:00";
				}
				if (endTime.trim().length == 10) {
					endTime = endTime + " 00:00:00";
				}
				var reg = new RegExp('-', 'g');
				startTime = startTime.replace(reg, '/');// 正则替换
				endTime = endTime.replace(reg, '/');
				startTime = new Date(parseInt(Date.parse(startTime), 10));
				endTime = new Date(parseInt(Date.parse(endTime), 10));
				if (startTime > endTime) {
					return false;
				} else {
					return true;
				}
			}, "<font color='#E47068'>自动下架时间不能早于自动上架时间</font>");
			jQuery.validator.addMethod("gdsMainMedia", function(value, element) {
				var id = element.id;
				if(id=="picVfsId1"){
					if($("#picVfsId1").val()==""){
						return false;
					}
				}
				return true;
			}, "<font color='#E47068'>请上传主图图片</font>");
			$("#settingForm").validate({
				rules : {
					mainPicVfsId : {
						gdsMainMedia : true
					},
					putoffTime : {
						compareTime : true
					}
				},
				messages : {
					mainPicVfsId : {
						gdsMainMedia : "<span style='color:red'>请上传主图图片</span>"
					},
					putoffTime : {
						compareTime : "<span style='color:red'>自动下架时间不能早于自动上架时间</span>"
					}
				},
				//	          debug: false,  //如果修改为true则表单不会提交  
				submitHandler : function() {
				}
			});
		},
		validSkuParam : function(){
			jQuery.validator.addMethod("price_param",  //addMethod第1个参数:方法名称  
		        function(value, element, params) {     //addMethod第2个参数:验证方法，参数（被验证元素的值，被验证元素，参数）  
				var decimal = /^\d+(\.\d{1,2})?$/;
				return (decimal.test(value)|| $.trim(value)=="");
		        },  
		        "价格格式错误");
			jQuery.validator.addMethod("stock_param",  //addMethod第1个参数:方法名称  
		        function(value, element, params) {     //addMethod第2个参数:验证方法，参数（被验证元素的值，被验证元素，参数）  
				var decimal = /^\d+(\.\d{1,2})?$/;
				return (decimal.test(value)|| $.trim(value)=="");
		        },  
		        "<b style='color:red'>请输入大于等于0的整数</b>");
			$("#gdsSkuForm").validate({
				rules : {
//					price_param : {
//						price_param : true
//					},
//					stock_param : {
//						stock_param : true
//					}
				},
				messages : {
//					price_param : {
//						price_param : "<b style='color:red'>价格格式错误</b>"
//					},
//					stock_param : {
//						stock_param : "<b style='color:red'>只能输入整数</b>",
//					}
				},
				//	          debug: false,  //如果修改为true则表单不会提交  
				submitHandler : function() {
				},
				errorPlacement : function(error, element) {
					error.appendTo(element.parent());
				}
			});
		},
		textCounter : function(id,limitNum,showArea)   {			
			if   ($("#"+id).val().length  > limitNum)        
				//如果元素区字符数大于最大字符数，按照最大字符数截断；        
				$("#"+id).val($("#"+id).val().substring(0, limitNum));      
			else      
				//在记数区文本框内显示剩余的字符数；        
				$("#"+showArea).text( limitNum - $("#"+id).val().length);     
		},
		choseGdsCat : function(obj){
			var $this = $(obj);
			if($this.val()=='3'){
				$this.after('<span class="help-inline"><a href="javascript:void(0)" id="gdsCombine">*选择组合产品</a></span>');
			}else{
				$this.next().remove();
			}
		},
		checkSkuParameter : function (propId, propName, propValueId, propValue,currentIndex,indexCount,obj) {
			if($("#sku-param-table").find('tr').length==0){
				var stockhtml = "";
				var pricehtml = "";
				if(stock_switch=='1'){
					stockhtml +="<a class='gjkc' href='javascript:void(0)' value='[]' onclick='openPriceWin(this)'>高级价格</a";
				}else{
					stockhtml +="<input type='text' name='price_param'  class='input-small' maxlength='9'/>";
				}
				if(price_switch=='1'){
					pricehtml +="<a class='gjkc' href='javascript:void(0)' value='[]' onclick='openStockWin(this)'>高级库存</a>";
				}else{
					pricehtml +="<input type='text' name='stock_param' class='input-small' maxlength='9'/>";
				}
				var html = "<thead>"+
                "<tr>"+
                    "<th style='display:none;'  id='getPriceTh'><span style='color: red'></span>价格</th>"+
                    "<th style='display:none;'  id='realCountTh'><span style='color: red'></span>库存数量</th>"+
                    "<th>操作</th>"+
                "</tr>"+
                "</thead>"+
                "<tbody>"+
                "<tr id='esp'>"+
                    "<td id='senior-price' style='display:none;' >"+pricehtml+"</td>"+
                    "<td id='senior-stock' style='display:none;' >"+stockhtml+"</td>"+
                    "<td><a href='javascript:void(0)' value='[]' onclick='openSkuPictrueWindow(this)'>图片管理</a></td>"+
                "</tr>"+
                "</tbody>";
				$("#sku-param-table").append(html);
			}
			
	    	addHtml = "";
	    	currentIndex = parseInt($(obj).attr('count'));
	    	indexCount = parseInt($(obj).attr('size'));
	    	var childIndex = $(obj).attr('i');
	    	childIndex = parseInt(childIndex) - 1;
	    	var first = $(".check_" + propId).eq(childIndex).prop("checked");
	    	var flag = false;
	    	var count = 0;
	    	var arr = new Array();
	    	// 查看当前选中的值，以及其他行被选中的值的个数
	    	for (var i = 1; i < indexCount + 1; i++) {
	    		var temp = false;
	    		var countFlag = Number(0);
	    		var propIdTemp = $("#propId_" + i).val();
	    		var arrChecked = new Array();
	    		arrChecked[countFlag] = propIdTemp;
	    		// 遍历改行属性，查看是否有属性被选中
	    		$(".check_" + propIdTemp).each(function(index, element) {
	    					var checkBox = $(".check_" + propIdTemp).eq(index);
	    					var checked = checkBox.prop("checked");
	    					if (checked) {
	    						temp = true;
	    						countFlag++;
	    						if (i == currentIndex) {
	    							arrChecked[1] = propValueId;
	    							flag = true;
	    						} else {
	    							arrChecked[countFlag] = checkBox.val();
	    						}
	    					}
	    				});
	    		if (temp) {
	    			arr[count] = arrChecked;
	    			count++;
	    		}
	    	}
	    	if (first) {// 选中操作
	    		var addTd = false;
	    		// 当前操作行，是否有选中判断
	    		if (flag) {
	    			// 如果该行属性有被选中，列不存在 ，则添加列
	    			if ($("#th_" + propId).length == 0) {
	    				addTd = true;
	    				$("#getPriceTh").before("<th id='th_" + propId + "'>" + propName
	    						+ "</th>");
	    				$(".gdsNameTd").before("<td class='td_" + propId
	    						+ "'  propId='td_" + propId + "_" + propValueId
	    						+ "'><input type='hidden'  class='input-small property' ifHaveto='"+$(obj).attr('ifHaveto')+"' ifBasic='"+$(obj).attr('ifBasic')+"'  propId='"
	    						+ propId + "' valueId='" + propValueId + "' value='"+ propValue + "' >" + propValue + "</td>");
	    				sort[sort.length] = propId;
	    			}

	    		} else {
	    			// 如果该行属性没有一个被选中，列存在则删除列
	    			if ($("#th_" + propId).length != 0) {
	    				$("#th_" + propId).remove();
	    				$(".td_" + propId).remove();
	    				var index = GdsInfoEntry.getIndex(sort, propId);
	    				if (index != null) {
	    					sort[index] = null;
	    				}
	    			}
	    		}
	    		// 针对列排序:
	    		var afterSort = GdsInfoEntry.getMin(arr, sort);
	    		var tableLens = $("#sku-param-table").find("tr").length;
	    		if(tableLens == 2 && $("#esp")){
	    			$("#esp").remove();
	    		}
	    		var tableLen = $("#sku-param-table").find("tr").length;
	    		if (!addTd || tableLen == 1) {
	    			GdsInfoEntry.listAllPro(afterSort, 0, afterSort.length, "",obj);
	    			var htmls = (addHtml + "").split("|||||");
	    			var afterHtml = GdsInfoEntry.getPramaListTdHtml();
	    			for (var i = 0; i < htmls.length; i++) {
	    				var html = htmls[i] + "";
	    				/**
	    				 * 解决IE不支持trim问题
	    				 */
	    				if (!String.prototype.trim) {
	    					String.prototype.trim = function() {
	    						return this.replace(/^\s+|\s+$/g, '');
	    					};
	    				}
	    				if (html.trim() != "") {
	    					$("#sku-param-table").append("<tr class='oneAsSku' skuId=''>" + html + afterHtml);
	    				}

	    			}
	    		}
	    	} else {
	    		if (!flag) {
	    			// 如果该行属性没有一个被选中，列存在则删除列
	    			if ($("#th_" + propId).length != 0) {
	    				$("#th_" + propId).remove();
	    				$(".td_" + propId).remove();
	    				var index = GdsInfoEntry.getIndex(sort, propId);
	    				if (index != null) {
	    					sort[index] = null;
	    				}
	    				var stockInputHtml = "";
	    				var priceInputHtml = "";
	    				if(price_switch=="1"){
	    					priceInputHtml += '<a class="gjpri" href="javascript:void(0)" value="[]" onclick="openPriceWin(this)">高级价格</a>';
	    				}else{
	    					priceInputHtml += "<input type='text' name='price_param' class='input-small' maxlength='9'/>";
	    				}
	    				if(stock_switch=="1"){
	    					stockInputHtml += '<a class="gjkc" href="javascript:void(0)" value="[]" onclick="openStockWin(this)">高级库存</a>';
	    				}else{
	    					stockInputHtml += "<input type='text' name='stock_param' class='input-small' maxlength='9'/>";
	    				}
	    				if($(".input-small").length==0){
			    			var htmlMe = "<tr id='esp'> <td class='senior-price'>"+priceInputHtml+"</td>" +
			    					" <td class='senior-stock'>"+stockInputHtml+"</td>" +
			    					"<td><a href='javascript:void(0)' value='[]' onclick='openSkuPictrueWindow(this)'>图片管理</a></td>";
			    			if ($("#sku-param-table").find("tr").length == 1) {
			    				$("#sku-param-table").append(htmlMe);
			    			};
	    				}
	    			}
	    		} else {
	    			$("[propId='td_" + propId + "_" + propValueId + "']").each(
	    					function(index, element) {
	    						$(this).parent().remove();
	    					});
	    		}
	    	}
	    	var size = $("#proSize").val();
	    	var count = 0;
	    	for (var i = 1; i < size + 1; i++) {
				var propIdTemp = $("#propId_" + i).val();
				// 遍历改行属性，查看是否有属性被选中
				
				$(".check_" + propIdTemp).each(function(index, element) {
					var checkBox = $(".check_" + propIdTemp).eq(index);
					var checked = checkBox.prop("checked");
					if (checked) {
						count ++;
					}
				});
			}
	    	if(count ==0){
	    		$("#sku-param-table").html('');
	    	}
	    	
	    },

	    /**
	     * 对遍历得到的选中值，按照勾选顺序进行排序
	     * 
	     * @param arr
	     *            属性从第一行，到最后一行的选中数组
	     * @param sort
	     *            单品属性行的点击顺序保存数据
	     * @returns {Array}
	     */
	    getMin : function (arr, sort) {
	    	var result = new Array();
	    	for (var i = 0; i < arr.length; i++) {
	    		var min = null;
	    		var minIndex = null;
	    		for (var j = 0; j < arr.length; j++) {
	    			if (arr[j] != null && arr[j].length > 0) {
	    				var tdId = arr[j][0];
	    				if (tdId != null) {
	    					var index = GdsInfoEntry.getIndex(sort, tdId);
	    					if (min == null) {
	    						min = index;
	    						minIndex = j;
	    					} else {
	    						if (Number(index) < Number(min)) {
	    							min = index;
	    							minIndex = j;
	    						}
	    					}
	    				}
	    			}
	    		}
	    		result[i] = arr[minIndex];
	    		arr[minIndex] = null;
	    	}
	    	return result;
	    },
	    /**
	     * 获取某个属性id在 属性选中顺序数组sort中的下标
	     * 
	     * @param arr
	     * @param propId
	     * @returns
	     */
	    getIndex : function (arr, propId) {
	    	for (var i = 0; i < arr.length; i++) {
	    		if (arr[i] == propId) {
	    			return i;
	    		}
	    	}
	    	return null;
	    },
	    getPramaListTdHtml : function () { 
	    	tagIdCount++;
	    	var tdHtml = "<td class='gdsNameTd' style='display:none;' id='senior-price'>" + GdsInfoEntry.getInputHtml("price", tagIdCount);
	    	tdHtml += "</td>" + "<td id='senior-stock' style='display:none;'>" + GdsInfoEntry.getInputHtml("stock", tagIdCount);
	    	tdHtml += "</td>" + "<td>" + GdsInfoEntry.getInputHtml("pictrue", tagIdCount);
	    	tdHtml += "</td>" + "</tr>";
	    	return tdHtml;
	    },
	    getInputHtml : function (name, tagIdCount) {
	    	var tagName = "";
	    	if(name == 'price'){
	    		tagName = "price";
	    	}else if(name == "stock"){
	    		tagName = "stock";
	    	}
	    	var html= "";
	    	if(name == 'pictrue'){
	    		html +="<a href='javascript:void(0)' value='[]' onclick='openSkuPictrueWindow(this)'>图片管理</a>";
	    	}else if(name =='stock'){
	    		if(stock_switch=='1'){
	    			html +="<a class='gjkc' href='javascript:void(0)' value='[]' onclick='openStockWin(this)'>高级库存</a>";
	    		}else{
	    			html +="<input name='"+name+"_param' id='" + name + "_" + tagIdCount+"'onblur=\"validatorSku(this,'"+tagName+"')\"   ";
	    			if(name == "price"){
	    	    		html=html+"' begId='guidePrice_" + tagIdCount+"'    ";
	    	    	}
	    	        html=html+"  class='input-small'  type='text' maxlength='9'/>";
	    		}
	    		
	    	}else if(name =='price'){
	    		if(price_switch=='1'){
	    			html +="<a class='gjpri' href='javascript:void(0)' value='[]' onclick='openPriceWin(this)'>高级价格</a>";
	    		}else{
	    			html +="<input name='"+name+"_param' id='" + name + "_" + tagIdCount+"'onblur=\"validatorSku(this,'"+tagName+"')\"   ";
	    			if(name == "price"){
	    	    		html=html+"' begId='guidePrice_" + tagIdCount+"'    ";
	    	    	}
	    	        html=html+"  class='input-small'  type='text' maxlength='9'/>";
	    		}
	    	}
//	    	else{
//	    		html +="<input name='"+name+"_param' id='" + name + "_" + tagIdCount+"'onblur=\"validatorSku(this,'"+tagName+"')\"   ";
//	    		if(name == "price"){
//		    		html=html+"' begId='guidePrice_" + tagIdCount+"'    ";
//		    	}
//		        html=html+"  class='input-small'  type='text' />";
//	    	}
	    	return html;

	    },
	    listAllPro : function (arr, index, size, html,obj) {
	    	if (Number(index) == Number(size)) {
	    		addHtml = addHtml + html + "|||||";
	    		return "";
	    	}
	    	var temp = arr[index];
	    	var propId = temp[0];
	    	index++;
	    	for (var i = 1; i < temp.length; i++) {
	    		var propValueId = temp[i];
	    		var value = $("#propValue_" + propId + "_" + propValueId).val();
	    		var temphtml = html;
	    		temphtml = temphtml + "<td class='td_" + propId + "' propId='td_"
	    				+ propId + "_" + propValueId + "'>"
	    				+ "<input type='hidden'  class='input-small property' ifHaveto='"+$(obj).attr('ifHaveto')+"' ifBasic='"+$(obj).attr('ifBasic')+"'  propId='" + propId
	    				+ "' valueId='" + propValueId + "' value='" + value + "' >"
	    				+ value + "</td>";
	    		GdsInfoEntry.listAllPro(arr, index, size, temphtml,obj);
	    	}
	    	return html;
	    },
	    validateLaderPrice : function(obj,str){
	    	var $this = $(obj);
	    	var $thisValue = Number($.trim($this.val()));
	    	var flag = true;
	    	var $sibling = $this.parent().parent().prevAll();
	    	var $nextAll = $this.parent().parent().nextAll();
	    	var fin = "";
	    	var error = "";
	    	if(str=='amount'){
	    		fin = "order-amount";
	    		error = "起订量要从小到大";
	    		if(!_number.test($thisValue)){
	    			$("#laderPrice-error").text('起订量只能输入整数');
	    		}else{
	    			$("#laderPrice-error").text('');
	    		}
	    	}else if(str=='price'){
	    		fin  = "lader-price";
	    		error = "销售价格要从大到小";
	    		if(!_price.test($thisValue)){
	    			$("#laderPrice-error").text('销售价格格式不合法');
	    		}else{
	    			$("#laderPrice-error").text('');
	    		}
	    	}
//	    	if($nextAll.length >= 1){
	    		$nextAll.each(function(){
		    		var $obj = $(this);
		    		if(str=='amount'){
		    			if(Number($.trim($obj.find('input[name="'+fin+'"]').val())) <= $thisValue){
			    			flag = false;
			    		}
		    		}else{
		    			if(Number($.trim($obj.find('input[name="'+fin+'"]').val())) >= $thisValue){
			    			flag = false;
			    		}
		    		}
		    	});
//	    	}else{
	    		$sibling.each(function(){
	    			var $obj = $(this);
		    		if(str=='amount'){
		    			if(Number($.trim($obj.find('input[name="'+fin+'"]').val())) >= $thisValue){
			    			flag = false;
			    		}
		    		}else{
		    			if(Number($.trim($obj.find('input[name="'+fin+'"]').val())) <= $thisValue){
			    			flag = false;
			    		}
		    		}
	    		});
//	    	}
	    	if(!flag){
	    		$("#laderPrice-error").text(error);
	    	}else{
	    		$("#laderPrice-error").text('');
	    	}
	    },
	    delteLaderPrice : function(obj){
	    	if($("#lader-price-table").find('tr').length <= 3){
	    		$(".add-lader-price").removeAttr('style');
	    	}
	    	$this = $(obj);
	    	var $pobj = $this.parent().parent();
	    	if($pobj.find("input[name='ifDefault']").attr('checked')=="checked"){
	    		$this.parent().parent().remove();
		    	$("#laderPrice-error").text('');
		    	$("#lader-price-table").find('tr').eq(0).find("input[name='ifDefault']").attr("checked",'checked');
	    	}else{
	    		$pobj.remove();
		    	$("#laderPrice-error").text('');
	    	}
	    },
	    openStockWin : function(obj){
	    	var _editFlag = '0';
	    	if($("#copyFlag").val()=='1'){
	    		_editFlag = '0';
	    	}else{
	    		_editFlag = '1';
	    	}
    	        bDialog.open({
    	            title : "高级库存",
    	            width : 680,
    	            height : 400,
    	            url :  GLOBAL.WEBROOT + "/gdspointentry/toseniorstock?shopId="+$("#shopId").val()+"&&editFlag="+_editFlag,
    	            params : {
    	            	'param' : $(obj).attr('value')
    	            },
    	            callback:function(data){
    	            	if(data && data.results && data.results.length > 0 ){
    						$(obj).attr('value',data.results[0].param);
    					}
    	            }
    	        });
	    },
	    openPriceWin : function(obj){
    	        bDialog.open({
    	            title : "高级价格",
    	            width : 760,
    	            height : 400,
    	            params : {
    	            	'param' : $(obj).attr('value')
    	            },
    	            url : GLOBAL.WEBROOT + "/gdspointentry/toseniorprice?shopId="+$("#shopId").val(),
    	            callback:function(data){
    	            	if(data && data.results && data.results.length > 0 ){
    						$(obj).attr('value',data.results[0].param);
    					}
    	            }
    	        });
	    },
	    openSkuPictrueWindow : function(obj){
	        bDialog.open({
	            title : "图片管理",
	            width : 860,
	            height : 550,
	            params : {
					'pictrueList' : $(obj).attr('value'),
					'shopId' : $("#shopId").val()
				},
	            url : GLOBAL.WEBROOT + "/gdspointentry/toskupictrues?shopId="+$("#shopId").val(),
	            callback:function(data){
	            	if(data && data.results && data.results.length > 0 ){
						$(obj).attr('value',data.results[0].param);
					}
	            }
	        });
	    },
	    /**
		 * 图片上传按钮监听.
		 */
//		imgUpload : function(obj,e){
//			busSelector.uploader({
//				fileTypeExts : '*.jpg;*.png;*.gif;*.bmp;', //定义文件上传的文件类型限制【可选项，默认为*.jpg;*.gif;*.bmp;*.png图片格式】
//				fileSizeLimit : "5MB",
//				checktype : "single",
//				callback : function(data){
//					if(data && data.results && data.results.length > 0){
//						var index = $(obj).next().attr('index');
//						$("#image"+index).attr("src",data.results[0].url);
//						$("#picVfsId"+index).val(data.results[0].fileId);
//						$("#picVfsId"+index).attr('mediaRtype','2');
//						$('#picVfsId'+index).attr('mediaType','1');
//					}
//				}
//			}, e);
//		},
	    uploadImage : function (object, path) {
	    	var filepath = path;
	    	filepath=(filepath+'').toLowerCase();
	    	var regex = new RegExp(
	    			'\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$', 'gi');
	    	/** 上传图片文件格式验证 */
	    	if (!filepath || !filepath.match(regex)) {
	    		eDialog.alert('请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp).');
	    		$(object).value = "";
	    		return;
	    	}
	    	var url = GLOBAL.WEBROOT + '/gdspointentry/uploadimage';
	    	var callback = function(data, status) {
	    		/** 上传成功，隐藏上传组件，并显示该图片 */
	    		if (data.success == "ok") {
	    			var index = $(object).attr('index');
					$("#image"+index).attr("src",data.map.imagePath);
					$("#picVfsId"+index).val(data.map.vfsId);
					$("#picVfsId"+index).attr('picName',data.map.imageName);
					$("#picVfsId"+index).attr('mediaRtype','2');
					$('#picVfsId'+index).attr('mediaType','1');
	    		} else {
	    			eDialog.error(data.message);
	    		}
	    		GdsInfoEntry.bindPicUpload();
	    	};
	    	GdsInfoEntry.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback);
	    },
	    ajaxFileUpload : function (url, secureuri, fileElementId, type, dataType, callback) {
			$.ajaxFileUpload({
						url : url, // 用于文件上传的服务器端请求地址
						secureuri : secureuri, // 一般设置为false
						fileElementId : fileElementId, // 文件上传空间的id属性 <input
						// type="file" id="imageFile"
						// name="imageFile" />
						type : type, // get 或 post
						dataType : dataType, // 返回值类型
						success : callback, // 服务器成功响应处理函数
						error : function(data, status, e) // 服务器响应失败处理函数
						{
							alert(e);
						}
					});
		},
		//新增保存商品
		saveGdsInfo : function(){
			if(GdsInfoEntry.initSaveGdsInfoParam()==""){
				return;
			}
			var param = GdsInfoEntry.initSaveGdsInfoParam();
			$.gridLoading({"messsage":"正在保存中...."});
			$.eAjax({
				url : GLOBAL.WEBROOT + "/gdspointentry/editgdsinfo",
				data :param,
				success : function(returnInfo) {
					$.gridUnLoading();
					var _successMsg = "";
					var _errorMsg = "";
					if($("#copyFlag").val() == "1"){
						_successMsg = "复制添加商品成功！";
						_errorMsg = "复制添加商品失败！";
					}else{
						_successMsg = "编辑保存成功！";
						_errorMsg = "编辑保存失败！";
					}
					if(returnInfo.ecpBaseResponseVO.resultFlag=='ok'){
						eDialog.success(_successMsg,{
							buttons:[{
								caption:"确定",
								callback:function(){
									window.location.href = GLOBAL.WEBROOT + '/gdspointmanage?shopId='+$("#shopId").val();
						        }
							}]
						}); 
					}else{
						eDialog.error(_errorMsg);
					}
				},
				exception : function(){
					$.gridUnLoading();
				}
			});
		},
		//获取商品保存的参数
		initSaveGdsInfoParam : function(){
			var param = {};
			param.shopId = $("#shopId").val();
			param.id = $("#gdsId").val();
			//多功能开关
			param.multiSwitch = multi_switch;
			// 平台分类
			param.getCategoryParam = GdsInfoEntry.getCategoryParam();
//			// 店铺分类
//			param.getShopCategoryParam = GdsInfoEntry.getShopCategoryParam();
			// 获取自动加载的参数
			param.autoParam = GdsInfoEntry.getAutoParam();
			// 获取单品参数
			param.skuParam = GdsInfoEntry.getSkuParam();
			//获取阶梯价
			param.scorePriceParam = GdsInfoEntry.getScorePriceParam();
			// 获取商品图片
			if(GdsInfoEntry.getPictrueParam()==""){
				return "";
			}
			param.pictrueParam = GdsInfoEntry.getPictrueParam();
			// 商品名称
			param.gdsName = $("#gdsName").val();
			// 商品副标题
			param.gdsSubHead = $("#gdsSubHead").val();
			// 商品知道价(产品价格)
			param.guidePrice = $("#guidePrice").val();
			// 商品描述
			param.gdsDesc = $("#gdsDesc").val();
			// 包装清单
			param.gdsPartlist = $("#gdsPartlist").val();
			// 商品类型
			param.gdsTypeId = $("#gdsTypeId").val();
			// 自动上架时间
			param.putonTime = $("#putonTime").val();
			// 自动下架时间
			param.putoffTime = $("#putoffTime").val();
			// 是否赠送积分
			var ifSendscore = "0";
			if ($("#ifSendscore").attr('checked') == 'checked') {
				ifSendscore = "1";
			}
			param.ifSendscore = ifSendscore;
			// 是否单独销售
			var ifSalealone  = "0";
			if ($("#ifSalealone").attr('checked') == 'checked') {
				ifSalealone = "1";
			}
			param.ifSalealone = ifSalealone;
			// 是否推荐
			var ifRecomm = "0";
			if ($("#ifRecomm").attr('checked') == 'checked') {
				ifRecomm = "1";
			}
			param.ifRecomm = ifRecomm;
			// 是否开启存量过低提醒
			var ifStocknotice = "0";
			if ($("#ifStocknotice").attr('checked') == 'checked') {
				ifStocknotice = "1";
			}
			param.ifStocknotice = ifStocknotice;
			// 是否免邮
			var ifFree = "0";
			if ($("#ifFree").attr('checked') == 'checked') {
				ifFree = "1";
			}else{
				// 运费模板编码
				param.shipTemplateId = $("#shipTemplateId").attr('tempId');
			}
			param.ifFree = ifFree;
			// 高级库存是否开启
			var ifDisperseStock = "0";
			if (stock_switch == 1) {
				ifDisperseStock = "1";
			}
			param.ifDisperseStock = ifDisperseStock;
			//是否开启高级价格
			param.ifSeniorPrice = price_switch;
			// 是否阶梯价
			var ifLadderPrice = "0";
			if ($("#gds-lader-price-open").attr('checked') == 'checked') {
				ifLadderPrice = "1";
				
			}
			param.ifLadderPrice = ifLadderPrice;
//			param.ifEntityCode = $("#ifEntityCode").val();
			param.copyFlag = $("#copyFlag").val();
			param.checkedParam = GdsInfoEntry.getCheckedParam();
			return param;
		},
		//获取价格形式
		getScorePriceParam : function(){
			var scorePriceParam = "[";
			$("#lader-price-table").find("tr").each(function(){
				var $this = $(this);
				var type = $this.find("select").val();
				var score = $.trim($this.find("input[id='score']").val());
				var scorePrice = $.trim($this.find("input[id='scorePrice']").val());
				var ifDefault = "0";
				if($this.find("input[name='ifDefault']").attr('checked')=="checked"){
					ifDefault = "1";
				}
				var scoreId = $this.find("input[name='scoreId']").val();
				scorePriceParam += "{id:'"+scoreId+"',scoreType:'"+type+"',score:'"+score+"',scorePrice:'"+scorePrice+"',ifDefault:'"+ifDefault+"'},";
			});
			scorePriceParam += "]";
			return scorePriceParam;
		},
		// 获取平台分类
		getCategoryParam : function(){
			var categoryParam = "[";
			$("#catagoryList").find('tr').each(function(){
				$this = $(this);
				var catgCode = $this.children().eq(0).attr('id');
				var gds2catgType = $this.children().eq(1).attr('id');
				categoryParam += "{catgCode:'" + catgCode + "',gds2catgType:'"
					+ gds2catgType + "',catType:'1'},";
			});
			$("#shopCatagoryList").find('tr').each(function(){
				$this = $(this);
				var catgCode = $this.children().eq(0).attr('id');
				categoryParam += "{catgCode:'" + catgCode + "',gds2catgType:'',catType:'2'},";
			});
			categoryParam += "]";
			return categoryParam;
		},
		//获取单品参数
		getSkuParam : function (){
			//库存
			var skuStock = $("#productStock").val();
			var skuStockList = "";
			skuStockList +="[{skuStock:'"+skuStock+"',repType:'01',repCode:'',switch:'"+stock_switch+"',areaList:[]}]";
			if(multi_switch=='0'){
				var _skuId = $("#singleSkuId").val();
				var defaultSku = "[{ifHaveto:'',ifBasic:'',propId:'',propValueId:'',propName:'',propValue:''" +
						",skuPriceList:[],skuStockList:"+skuStockList+",skuPictrueList:[],skuAmount:'1',skuId:'"+_skuId+"'}]";
				return defaultSku;
			}
			// 初始化单品参数
			var skuParam = "[";
			$("#sku-param-table").find("tr").each(function(index, element) {
				if (index == 0) {

				} else {
					var $this = $(this);
					//[{picId:'',meidaRtype:'',mediaType:'',sortNo:''}]
					var skuPictrueList = $this.children().last().find('a').attr('value');
					var skuId = $this.attr('skuId');
					var count  = 0;
					var skuAmount = $("#sku-param-table").find("tr[class='oneAsSku']").length;
					$(this).find(".property").each(function(index, element) {
						var obj = $(this);
						var propId = obj.attr("propId");
						var valueId = obj.attr("valueId");
						var propName = $("#propName_" + propId).val();
						var gdsValue = obj.attr("value");
						var ifHaveto = obj.attr('ifHaveto');
						var ifBasic = obj.attr('ifBasic');
						skuParam = skuParam + "{ifHaveto:'"+ifHaveto+"',ifBasic:'"+ifBasic+"',propId:'" + propId + "',propValueId:'"
								+ valueId + "',propName:'" + propName + "',propValue:'"
								+ gdsValue + "',skuPriceList:[],skuStockList:"+skuStockList+",skuPictrueList:"+skuPictrueList+",skuAmount:'"+skuAmount+"',skuId:'"+skuId+"'},";
						count ++;
					});
					if(count == 0){
						skuParam = skuParam + "{propId:'',propValueId:'',propName:'',propValue:'',skuPriceList:[],skuStockList:[],skuPictrueList:"+skuPictrueList+",skuAmount:'1',skuId:'"+skuId+"'},";
					}
				}
			});
			skuParam = skuParam + "]";
			return skuParam;
		},
		//获取商品图片参数
		getPictrueParam : function(){
			var pictrueParam = "[";
			var length = $("input[name='mainPicVfsId']").length;
			var count = 0;
			var picCount = 0;
			for(var i = 1 ;i<length + 1;i++){
				var vfsId = $("#picVfsId"+i).val();
				if(vfsId == undefined){
					continue;
				}
				var picName = $("#picVfsId"+i).attr('picName');
				var mediaRtype = $("#picVfsId"+i).attr('mediaRtype');
				var mediaType = $("#picVfsId"+i).attr('mediaType');
				var sortNo = $("#picVfsId"+i).attr('main');
				var mediaId = $("#picVfsId"+i).attr('mediaId');
				var url = $("#picVfsId"+i).parent().parent().find('img').attr('src');
				if($("#picVfsId1").val() == ""){
					count ++;
				}
				if($("#picVfsId"+i).val() != ""){
					pictrueParam += "{picVfsId"+picCount+":'"+vfsId+"',picName:'"+picName+"',mediaRtype:'"+mediaRtype+"',mediaType:'"+mediaType+"',sortNo:'"+sortNo+"',url:'"+url+"',mediaId:'"+mediaId+"'},";
					picCount ++;
				}
			}
			pictrueParam += "]";
			if(count >0){
				eDialog.error("请上传商品主图！");
				return "";
			}
			return pictrueParam;
		},
		getAutoParam : function(){
			var PROP_VALUE_TYPE_1 = $("#PROP_VALUE_TYPE_1").val();
			var PROP_VALUE_TYPE_2 = $("#PROP_VALUE_TYPE_2").val();
			var PROP_VALUE_TYPE_3 = $("#PROP_VALUE_TYPE_3").val();
			// 获取规格参数
			var detailParam = "[";
			$(".autoParam").each(function(index, element) {
				var obj = $(this);
				var ifmulti = obj.attr('ifmulti');
				var propId = obj.attr("propId");
				var propName = obj.attr("propName");
				var propType = obj.attr("propType");
				var ifBasic = obj.attr("ifBasic");
				if(ifmulti =='single'){//单选值的
					if (propId != undefined) {
						var value = $.trim(obj.val());
						if (true) {
							valueId = value;
							gdsValue = escape(obj.find("option:selected").text());
							detailParam = detailParam + "{propType:'"+propType+"',propValueType:'"+PROP_VALUE_TYPE_2+"',propId:'"
							+ propId + "',valueId:'" + value + "',propName:'"
							+ propName + "',gdsValue:'" + gdsValue + "',ifBasic:'"+ifBasic+"',editor:'0'},";
						}
					}
				}else if(ifmulti =='input'){//手动录入的
					if (propId != undefined) {
//						var propName = obj.attr("propname");
						if (true) {
							valueId =  obj.attr('valueId');
							html =  escape(obj.val());
							detailParam = detailParam + "{propType:'"+propType+"',propValueType:'"+PROP_VALUE_TYPE_1+"',propId:'"
							+ propId + "',valueId:'" + valueId + "',propName:'"
							+ propName + "',gdsValue:'" + html + "',ifBasic:'"+ifBasic+"',editor:'0'},";
						}
					}
				}else if(ifmulti == 'multi'){//多选值的
					var arr = new Array;
					obj.parent().find(".selcheckbox").each(function(){
						$this = $(this);
						if($this.attr('checked')=='checked'){
							arr.push($this.attr('id')+":"+$this.val());
						}
					});
					if(arr.length>=1){
						for(var i =0;i<arr.length;i++){
							detailParam = detailParam + "{propType:'"+propType+"',propValueType:'"+PROP_VALUE_TYPE_3+"',propId:'"
							+ propId + "',valueId:'" + arr[i].split(':')[0] + "',propName:'"
							+ propName + "',gdsValue:'" + escape(arr[i].split(':')[1]) + "',ifBasic:'"+ifBasic+"',editor:'0'},";
						}
					}else{
						detailParam = detailParam + "{propType:'"+propType+"',propValueType:'"+PROP_VALUE_TYPE_3+"',propId:'"
						+ propId + "',valueId:'',propName:'"
						+ propName + "',gdsValue:'',ifBasic:'"+ifBasic+"',editor:'0'},";
					}
					
				}
			});
			//获取富文本的
			$("textarea[nameValue='editor']").each(function(){
				var _obj = $(this);
				var _value = escape(_obj.val());
				var _propId = _obj.attr("propId");
				var _propName = _obj.attr("propName");
				var _propType = _obj.attr("propType");
				var _ifBasic = _obj.attr("ifBasic");
				detailParam = detailParam + "{propType:'"+_propType+"',propValueType:'"+PROP_VALUE_TYPE_1+"',propId:'"
				+ _propId + "',valueId:'',propName:'"
				+ _propName + "',gdsValue:'"+_value+"',ifBasic:'"+_ifBasic+"',editor:'1'},";
			});
			detailParam = detailParam + "]";
			return detailParam;
		},
		getCheckedParam : function(){
			var PROP_VALUE_TYPE_3 = $("#PROP_VALUE_TYPE_3").val();
			var param = "[";
			// 遍历改行属性，查看是否有属性被选中
			var size = $("#proSize").val();
			for (var i = 1; i < size + 1; i++) {
				var propIdTemp = $("#propId_" + i).val();
				// 遍历改行属性，查看是否有属性被选中
				$(".check_" + propIdTemp).each(function(index, element) {
					var checkBox = $(".check_" + propIdTemp).eq(index);
					var checked = checkBox.prop("checked");
					var valueId = checkBox.val();
					var propName = $("#propName_" + propIdTemp).val();
					var gdsValue = $("#propValue_" + propIdTemp + "_" + valueId).val();
					if (checked) {
						param = param + "{propType:'1',propValueType:'"+PROP_VALUE_TYPE_3+"',propId:'" + propIdTemp
								+ "',valueId:'" + valueId + "',propName:'" + propName
								+ "',gdsValue:'" + gdsValue + "'},";
					}
				});
			}
			param += "]";
			return param;
		},
		queryMediaList : function(){
			var param ={
					shopId : $("#shopId").val(),
					mediaName : $("#mediaName").val(),
					picCatgCode : $("#picCatgCode").val()
			};
			$.gridLoading({"el":"#mediaList","messsage":"正在加载中...."});
			$.eAjax({
				url : GLOBAL.WEBROOT + "/gdspointentry/gridmedialist",
				data : param,
				dataType : "html",
				success : function(returnInfo) {
					$.gridUnLoading({"el":"#mediaList"});
					$("#mediaList").empty();
					$("#mediaList").html(returnInfo);
					//双击选中
					$(".dbchoose").each(function(){
						$(this).dblclick(function(){
				    		var $dbthis = $(this);
				        	$(".imgcont").each(function(){
				    			var $this = $(this);
				    			if($this.attr('style') != undefined){
				    				$this.children('img').attr('src',$dbthis.children('img').attr('src'));
				    				$this.parent().find("input[name='mainPicVfsId']").val($dbthis.children('img').attr('mediaId'));
				    				$this.parent().find("input[name='mainPicVfsId']").attr('mediaRtype','1');
				    				$this.parent().find("input[name='mainPicVfsId']").attr('mediaType','1');
									$this.parent().find("input[name='mainPicVfsId']").attr('mediaId', $dbthis.children('img').attr('Id'));
				    				$this.removeAttr("style");
				    			}
				        	});
				    	});
					});
				}
			});
		},
		deleteCatPlat : function(obj){
			$(obj).parent().parent().remove();
		},
		setCkeditValue : function (edit, url) {
			if(url && url!=""){
				$.ajax({
					url : url,
					async : true,
					dataType : 'jsonp',
					jsonp : 'jsonpCallback',// 注意此处写死jsonCallback
					success : function(data) {
						$("#"+edit).html(data.result);
						KindEditor.html("#"+edit,data.result);
						KindEditor.sync("#"+edit);
					}
				 });
			}
		},
		strToPrice : function(data){
			var str = (data/100).toFixed(2) + '';
			var intSum = str.substring(0,str.indexOf("."));//取到整数部分.replace( /\B(?=(?:\d{3})+$)/g, ',' )
			var dot = str.substring(str.length,str.indexOf("."));//取到小数部分
			var ret = intSum + dot;
			return ret;
		},
		saveGdsInfoValidator : function(){
			if(multi_switch==1){
				//校验多功能开启时候，必选属性
				var size = $("#sku-param-table").find("tr").length;
				if(size<=0){
					eDialog.confirm("请至少选择一个规格属性！", {
						buttons : [ {
							caption : '确认',
							callback : function() {
								$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html')
										: $('body'))
										: $('html,body');
								$body.animate({
									scrollTop : $("#shopId").offset().top
								}, "fast");
							}
						}]});
					return false;
				}
			}
			if($("#lader-price-table").find("tr").length<=0){
				eDialog.confirm("请至少配置一条兑换方式！", {
					buttons : [ {
						caption : '确认',
						callback : function() {
							$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html')
									: $('body'))
									: $('html,body');
							$body.animate({
								scrollTop : $("#gds-skuParam").offset().top
							}, "fast");
						}
					}]});
				return false;
			}
			var _checkCount = 0;
			$("#lader-price-table").find("tr").each(function(){
				var $this = $(this);
				if($this.find("input[name='ifDefault']").attr('checked')=="checked"){
					ifDefault = "1";
					_checkCount ++;
				}
			});
			if(_checkCount==0){
				eDialog.confirm("请设置一条默认的价格方式！", {
					buttons : [ {
						caption : '确认',
						callback : function() {
							$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html')
									: $('body'))
									: $('html,body');
							$body.animate({
								scrollTop : $("#gdsSkuForm").offset().top
							}, "fast");
						}
					}]});
				return false;
			}
			return true;
		},
		validatorSku : function(obj,str){
			var value = $.trim($(obj).val());
			var skuPriceObj = $(obj);
			if(skuPriceObj.attr('name')=='price_param'){
				if(value !="" && !_price.test(value)){
					skuPriceObj.next().remove();
					skuPriceObj.parent().append("<b name='error' style='color:red'>价格格式不合法</b>");
				}else{
					skuPriceObj.next().remove();
				}
			}else if(skuPriceObj.attr('name')=='stock_param'){
				if(value !="" && !_number.test(value)){
					skuPriceObj.next().remove();
					skuPriceObj.parent().append("<b name='error' style='color:red'>只能输入整数</b>");
				}else{
					skuPriceObj.next().remove();
				}
			}
		}
		
};

