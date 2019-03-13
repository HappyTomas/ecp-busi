$(function(){
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	$('#btnFormCancle').click(function(){
		bDialog.closeCurrent();
	});
	var _price = _param.param;
	if(_price == ""){
		_price = "[]";
	}
	var json = eval(_price);
	if(json.length >0){
		var html = $("#forCopy").html();
		for (var i = 1; i < json.length; i++) {
			var contentHtml = "<div class='groups clearfix senior_price'>";
			contentHtml += html+"</div>";
			$(".scroll-wrap").append(contentHtml);
		}
		var index = 1;
		if(json.length>=2){
			$(".senior_price").each(function(){
				var obj = json[index];
				//{skuPrice:'',priceType:'',priceTarget:'',defaultPrice:'',switch:''}s
				$this = $(this);
				//priceType 就是priceCode 会是一个字符串
				$this.find('select[name="priceType"]').val(obj.priceType);
				$this.find('select[name="priceTarget"]').val(obj.priceTarget);
				$this.find('input[name="skuPrice"]').val(obj.skuPrice);
				index ++;
			});
		}else{
			var obj = json[0];
			$("#defaultPrice").val(obj.defaultPrice);
		}
		
	}
	
	$('#btnFormSave').click(function(){
		if(!$("#seniorPriceForm").valid()){
			return false;
		}
		var defaultPrice = $("#defaultPrice").val();
		var param = "[{skuPrice:'',priceTarget:'',priceTypeCode:'',priceTypeId:'',defaultPrice:'"+defaultPrice+"',switch:'1'},";
    	$(".senior_price").each(function(){
    		//{skuPrice:'',priceType:'',priceTarget:'',defaultPrice:'',switch:''}s
    		$this = $(this);
    		//priceType 就是priceCode 会是一个字符串，还需要传priceTypeId
    		var priceType = $this.find('select[name="priceType"]').val();
    		var priceTypeId = $this.find('select[name="priceType"]').find("option:selected").attr('id');
    		var priceTarget = $this.find('select[name="priceTarget"]').val();
    		var skuPrice = $this.find('input[name="skuPrice"]').val();
    		if(priceType !=""){
    			param += "{skuPrice:'"+skuPrice+"',priceTarget:'"+priceTarget+"',priceTypeCode:'"+priceType+"',priceTypeId:'"+priceTypeId+"',defaultPrice:'',switch:'1'},";
    		}
    	});
    	param += "]";
		bDialog.closeCurrent({
			'param' : param
		});
	});
	
	$("#btn_code_add").click(function(){
		var contentHtml = "<div class='groups clearfix'>";
		var html = $("#forCopy").html();
		contentHtml += html+"</div>";
		$(".scroll-wrap").append(contentHtml);
	});
	$(".delPrice").live('click',function(){
		if($(this).parent().parent().attr('id') == 'forCopy'){
			return;
		}
		$(this).parent().parent().remove();
	});
	jQuery.validator.addMethod("regex",  //addMethod第1个参数:方法名称  
	        function(value, element, params) {     //addMethod第2个参数:验证方法，参数（被验证元素的值，被验证元素，参数）  
			var decimal = /^\d+(\.\d{1,2})?$/;
			return (decimal.test(value)|| $.trim(value)=="");
	        },  
	        "格式错误");
	jQuery.validator.addMethod("selectNull",  //addMethod第1个参数:方法名称  
	        function(value, element, params) {     //addMethod第2个参数:验证方法，参数（被验证元素的值，被验证元素，参数）  
			var decimal = /^\d+(\.\d{1,2})?$/;
			return (decimal.test(value)|| $.trim(value)=="");
	        },  
	        "格式错误");
	jQuery.validator.addMethod("comselect",  //addMethod第1个参数:方法名称  
	        function(value, element, params) {     //addMethod第2个参数:验证方法，参数（被验证元素的值，被验证元素，参数）  
				var decimal = /^\d+(\.\d{1,2})?$/;
			return (decimal.test(value)|| $.trim(value)=="");
	        },  
	        "格式错误");
	  $(function() {  
          $("#seniorPriceForm").validate(  
          {  
          rules: {  
        	  priceType : {
        		  required : true,
        	  },
        	  priceTarget : {
        		  required : true,
        	  },
              skuPrice: {
            	  required : true,
                  regex : true      
              },
              defaultPrice : {
            	  required : true,
            	  regex : true
              },
          },  
          messages: {  
        	  priceType : {
        		  required : "<b style='color:red'>请选择价格类型</b>",
        	  },
        	  priceTarget : {
        		  required : "<b style='color:red'>请选择客户分组</b>",
        	  },
              skuPrice: { 
            	  required : "<b style='color:red'>请输入价格</b>",
                  regex : "<b style='color:red'>价格不合法</b>"  
              },
              defaultPrice : {
            	  required : "<b style='color:red'>请输入价格</b>",
            	  regex : "<b style='color:red'>价格不合法</b>"  
              },
          },  
//          debug: false,  //如果修改为true则表单不会提交  
          submitHandler: function() {  
          }  
      });  
  });  	        
});
function getPriceTarget(obj){
	$("#priceTarget").html('');
	var priceTypeCode = $(obj).val();
	$.eAjax({
		url : GLOBAL.WEBROOT + "/gdsinfoentry/getpricetarget",
		data : {"priceTypeCode":priceTypeCode},
		success : function(returnInfo) {
			var $this = returnInfo.priceTargetList;
			var size = $this.length;
			var html = "";
			for(var i= 0;i<size;i++){
				html += "<option value="+$this[i].priceTypeCode+">"+$this[i].priceTypeName+"</option>";
			}
			$("#"+obj).parent().parent().parent().find('input[name="priceTarget"]').append(html);
		}
	});
}
function deletePrice(obj){
	$(obj).parent().parent().remove();
}