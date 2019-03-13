var basePath = GLOBAL.WEBROOT;
(function($){
	
	$.smsDialogPlugin = {
		
		show : function(options){
			 ///默认参数为 ，修改或绑定手机号码；
			var opts = $.extend({},{
				url:"",
				parentId:"",
				sonId:"",
				defaultValue:false,
				
			}, options);
            
			if(opts.defaultValue){
				var companyId = $('#'+opts.parentId).val();
				$.eAjax({
					url : opts.url,
					data : {companyId:companyId},
					datatype:'json',
					success : function(data) {
						$('#'+opts.sonId).empty();
						$('#'+opts.sonId).append('<option value="">--请选择--</option>')
						$.each(data,function(i,n){
							var _shopId = $('#shopId').attr('_shopId');
							if(_shopId==i){
								var option = $('<option value='+i+' selected>'+n.shopName+'</option>')	
							}else{
							var option = $('<option value='+i+'>'+n.shopName+'</option>')
							}
							$('#'+opts.sonId).append(option);
						  });
					}
				});
				
			}
			
			
			$('#'+opts.parentId).change(function(){
				var _val = $(this).val();
				$.eAjax({
					url : opts.url,
					data : {companyId:_val},
					datatype:'json',
					success : function(data) {
						$('#'+opts.sonId).empty();
						$('#'+opts.sonId).append('<option value="">--请选择--</option>')
						$.each(data,function(i,n){
							var option = $('<option value='+i+'>'+n.shopName+'</option>')
							$('#'+opts.sonId).append(option);
						  });
					}
				});
				
			});
			
		}
	};
	
})(jQuery);
