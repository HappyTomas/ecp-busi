/**
*支付通道新增
*wangbh
**/

var up = {
	
		edit : function(){
			if(!$("#editForm").valid())return false;
			 if ($('#changeInput').attr('checked')) {
				 $('#keyName').val($('#knameinput').val());
			 }
			 //商户证书
			 if ($('#changeInputCer').attr('checked')) {
				 $('#cerName').val($('#cnameinput').val());
			 }
			var val = ebcForm.formParams($("#editForm"));
			$.eAjax({
	  			url : GLOBAL.WEBROOT + "/seller/payway/edit",
	  			data : val,
	  			async : true,
				type : "post",
	  			dataType:'json',
	  			success : function(returnInfo) {
	  				if(returnInfo.resultFlag=='ok'){
	  					eDialog.alert(returnInfo.resultMsg,function(){
	  						window.location.href= GLOBAL.WEBROOT + "/seller/payway/";
	  					});
	  				}
	  			}
	  		});
			
		},
		uploadFile : function (object, path) {
			var data;
	    	var url = $webroot + 'seller/payway/uploadFile';
	    	var callback = function(returnInfo) {
	    		/** 上传成功，隐藏上传组件，并显示该图片 */
	    		if (returnInfo.success == "ok") {
					$("#cerName").val(returnInfo.resultMap.vfsId);
					$("#cName").val(returnInfo.resultMap.vfsName);
					eDialog.alert(returnInfo.message);
	    		} else {
	    			/*$("#imagePreview").attr("src","");
					$("#vfsId").val("");
					$("#vfsName").val("");*/
	    			eDialog.alert(returnInfo.message);
	    		}
	    	};
	    	up.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback,data);
	    },
	    cleanCnameValue : function(){
	    	$('#cName').val('');
	    	$('#cerName').val('');
	    },
	    cleanKnameValue : function(){
	    	$('#kName').val('');
	    	$('#keyName').val('');
	    },
	    uploadFile1 : function (object, path) {
			var data;
	
	    	var url = $webroot + 'seller/payway/uploadFile';
	    	var callback = function(returnInfo) {
	    		/** 上传成功，隐藏上传组件，并显示该图片 */
	    		if (returnInfo.success == "ok") {
					$("#keyName").val(returnInfo.resultMap.vfsId);
					$("#kName").val(returnInfo.resultMap.vfsName);
					eDialog.alert(returnInfo.message);
	    		} else {
	    			/*$("#imagePreview").attr("src","");
					$("#vfsId").val("");
					$("#vfsName").val("");*/
	    			eDialog.alert(returnInfo.message);
	    		}
	    	};
	    	up.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback,data);
	    },
	    ajaxFileUpload : function (url, secureuri, fileElementId, type, dataType, callback,data) {
			$.ajaxFileUpload({
				url : url, // 用于文件上传的服务器端请求地址
				secureuri : secureuri, // 一般设置为false
				data : data,
				fileElementId : fileElementId, // 文件上传空间的id属性 <input type="file" id="imageFile" name="imageFile" />
				type : type, // get 或 post
				dataType : dataType, // 返回值类型
				success : callback, // 服务器成功响应处理函数
				error : function(data, status, e) // 服务器响应失败处理函数
				{
					$("#uploadFileObj1").val("");
					$("#uploadFileObj").val("");
					$("#keyName").val("");
					$("#kName").val("");
					$("#cerName").val("");
					$("#cName").val("");
					eDialog.alert(e);
				}
			});
		}
		
}




//页面初始化模块
$(function(){
	
	$("#uploadFileObj2").live("change", function(o) {
		var path = $(this).val();
	    if(path==""){
	    	return false;
	    }else{
	    	up.uploadFile1(this, path);
	    }
	});
	$("#uploadFileObj").live("change", function(o) {
		var path = $(this).val();
	    if(path==""){
	    	return false;
	    }else{
	    	up.uploadFile(this, path);
	    }
	});
	
	$('#edit').on('click',function(){
		up.edit();
		
	});
	$('#cleanCname').on('click',function(){
		up.cleanCnameValue();
	});
	$('#cleanKname').on('click',function(){
		up.cleanKnameValue();
	});
	$("#changeInput").click(function(){
		 if ($(this).attr('checked')) {
             $('#kNamec').hide();
             $(this).before('<input type="text" name="knameinput" id="knameinput" value="" class="input-large required"> &nbsp;');
             if(null!=$('#keyName').val()&&$('#keyName').val()!=''){
            	 $('#knameinput').val($('#keyName').val());
             }
             $(this).next().html('商户秘钥切换为文件提交模式');
         }else{
        	 $('#kNamec').show();
        	 $('#knameinput').remove();
        	 $(this).next().html('商户秘钥切换为输入模式');
         }
	});
	//商户证书
	$("#changeInputCer").click(function(){
		if ($(this).attr('checked')) {
			$('#cNamec').hide();
			$(this).before('<input type="text" name="cnameinput" id="cnameinput" value="" class="input-large required"> &nbsp;');
			if(null!=$('#cerName').val()&&$('#cerName').val()!=''){
				$('#cnameinput').val($('#cerName').val());
			}
			$(this).next().html('商户证书切换为文件提交模式');
		}else{
			$('#cNamec').show();
			$('#cnameinput').remove();
			$(this).next().html('商户证书切换为输入模式');
		}
	});
});
