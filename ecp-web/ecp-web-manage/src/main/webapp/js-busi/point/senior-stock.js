var _number = /^([0-9]+)$/;
$(function(){
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	var _stock = _param.param;
	if(_stock ==""){
		_stock = "[]";
	}
	var json = eval(_stock);
	if(json.length >0){
		for(var i =0;i<json.length;i++){
			var objParam = json[i];
			$("#seniorStockList").find("tr").each(function(){
				//{skuStock:'',repCode:'',switch:''},{skuStock:'',repCode:'',switch:''}
				$thiss = $(this);
				var obj = $thiss.find('input');
				if(obj.attr('repCode')==objParam.repCode){
					obj.val(objParam.skuStock);
					var areaList = objParam.areaList;
					obj.attr('areaStr',JSON.stringify(areaList));
				}
			});	
			
		}
	}
	$('#btnFormCancle').click(function(){
		bDialog.closeCurrent();
	});
	$("#seniorStockList").find("tr").each(function(){
		//{skuStock:'',repCode:'',switch:''},{skuStock:'',repCode:'',switch:''}
		$thiss = $(this);
		$thiss.find('input').each(function(){
			$(this).live('blur',function(){
				var _value = $(this).val();
				if(!_number.test(_value)){
					$(this).nextAll().remove();
    				$(this).parent().append("<span generated='true' class='error'><br><span style='color:red'>请输入整数</span></span>");
    			}else{
    				$(this).nextAll().remove();
    			}
			});
		});
	});
	
	$('#btnFormSave').click(function(){
		if(!$("#seniorStockForm").valid()){
			return false;
		}
		var param = "[";
		var _c = 0;
    	$("#seniorStockList").find("tr").each(function(){
    		//{skuStock:'',repCode:'',switch:''},{skuStock:'',repCode:'',switch:''}
    		$thiss = $(this);
    		$thiss.find('input').each(function(){
    			$this = $(this);
    			$this.next().remove();
    			var _value = $this.val();
    			if(!_number.test(_value)){
    				$this.parent().append("<span generated='true' class='error'><br><span style='color:red'>请输入整数</span></span>");
    				_c ++;
    			}
    			param += "{skuStock:'"+_value+"',repType:'"+$this.attr('repTyep')+"',repCode:'"+$this.attr('repCode')+"',switch:'1',areaList:"+$this.attr('areaStr')+"},";
    		});
    		
    	});
    	param += "]";
    	if(_c>=1){
    		return;
    	}
//    	$(obj).attr('value',param);
		/*
		if($.isFunction(_callback)){
			_callback({
				'userName' : 'wangwu'
			});
		}
		*/
		bDialog.closeCurrent({
			'param' : param
		});
	});
	
	jQuery.validator.addMethod("regex",  //addMethod第1个参数:方法名称  
	        function(value, element, params) {     //addMethod第2个参数:验证方法，参数（被验证元素的值，被验证元素，参数）  
			var decimal = /^([0-9]+)$/;
			return (decimal.test(value)|| $.trim(value)=="");
	        },  
	        "格式错误");
	  $(function() {  
          $("#seniorStockForm").validate({  
          rules: {  
//              skuStock: {    
//                  regex : true      
//              },
          },  
          messages: {  
//              skuStock: { 
//                  regex : "<b style='color:red'>只能输入整数</b>"  
//              },
          },  
//          debug: false,  //如果修改为true则表单不会提交  
          submitHandler: function() {  
          }  
          });  
	  });  	   
});