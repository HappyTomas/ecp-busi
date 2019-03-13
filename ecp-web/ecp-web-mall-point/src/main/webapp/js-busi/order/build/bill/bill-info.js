//页面初始化模块
$(function(){
	//页面业务逻辑处理内容
	var pInit = function(){
		var init = function(){
			$('#btnSaveBillInfo').click(function(){
		    	if(!$("#billForm1").valid())return false;
		    	var billContent = $('input:radio[name=billContent]:checked');
		    	if(billContent.size()>0){
		    		//返回发票信息
		    		bDialog.closeCurrent({
		    			billType : '0',
		    			billTypeName : '普通发票',
		    			billTitle : $('#billTitle').val(),
		    			billContent : billContent.val()
		    		});
		    	}else{
		    		eDialog.alert('请至少选择一项发票内容！');
		    	}
			});
		    $('#btnNext').click(function(){
		    	if(!$("#billForm2").valid())return false;
		    	$("#pane1").hide();
				$("#pane2").hide();
				$("#pane3").show();
			}); 
		    $('#btnUp').click(function(){ 
		    	$("#pane1").hide();
		    	$("#pane2").show();
				$("#pane3").hide();
			}); 
		    $('#btnSaveBillInfo2').click(function(){
		    	if(!$("#billForm3").valid())return false;
		    	var billContent = $('input:radio[name=billInvoiceContent]:checked');
		    	if(billContent.size()>0){
		    		//返回发票信息
		    		bDialog.closeCurrent({
		    			billType : '1',
		    			billTypeName : '增值税发票',
		    			billTitle : $('#billInvoiceTitle').val(),
		    			billContent : billContent.val(),
		    			billTaxpayerNo : $('#billTaxpayerNo').val(),
		    			billContactInfo : $('#billContactInfo').val(),
		    			billPhone : $('#billPhone').val(),
		    			billBankName : $('#billBankName').val(),
		    			billAcctInfo : $('#billAcctInfo').val(),
		    			billVfsId2 : $('#billPicId').val(),
		    			billTakerName : $('#billTakerName').val(),
		    			billTakerPhone : $('#billTakerPhone').val(),
		    			billTakerProvince : $('#billTakerProvince').val(),
		    			billTakerCity : $('#billTakerCity').val(),
		    			billTakerCounty : $('#billTakerCounty').val(),
		    			billTakerAddress : $('#billTakerAddress').val()
		    		});
		    	}else{
		    		eDialog.alert('请至少选择一项发票内容！');
		    	}
			}); 
		    
		    $('#btnCancel1').click(function(){
				bDialog.closeCurrent();
			});
		    $('#btnCancel2').click(function(){
				bDialog.closeCurrent();
			});
		    $('#btnCancel3').click(function(){
				bDialog.closeCurrent();
			});
		};
		
		return {
			init : init
		};
	};
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bForm'],
		//指定页面
		init : function(){
			var ppp = new pInit();
			ppp.init();
		}
	});
	
	$("#pictrue").live("change", function(o) {
		var path = $(this).val();
		bill.uploadImage(this, path);
	});
	
	$('div').find('a').click(function(){
		var tab = $(this).attr("class");
		if(tab == 'pane1 checked'){
			//解决IE11 二次修改发票光标不锁定问题
			$("#pane1 #billTitle")[0].focus();
			$("#pane1").show();
			$("#pane2").hide();
			$("#pane3").hide();
		}else if(tab == 'pane2 checked'){
			$("#pane1").hide();
			$("#pane2").show();
			$("#pane3").hide();
		}
	});  
	
});

var bill = { 
		uploadImage : function (object, path) {
//			 var Img = new Image();
//			   Img.src = object.value;
//			       alert(object.height+":"+object.width);
//			       return false;
		    	var filepath = path;
		    	filepath=(filepath+'').toLowerCase();
		    	var regex = new RegExp(
		    			'\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$', 'gi');
		    	/** 上传图片文件格式验证 */
		    	if (!filepath || !filepath.match(regex)) {
		    		eDialog.alert('请选择图片格式为(.jpg,.png,.jpeg,.gif,.bmp)');
		    		uploadfile.value = "";
		    		return;
		    	}
		    	var url = GLOBAL.WEBROOT + '/order/build/uploadimage';
		    	var callback = function(returnInfo) {
		    		/** 上传成功，隐藏上传组件，并显示该图片 */
		    		if (returnInfo.success == "ok") { 
						$("#billPicId").val(returnInfo.map.vfsId);
						$("#billPicName").val(returnInfo.map.imageName);
		    		} else {
		    			eDialog.error(returnInfo.message);
		    		}
		    	};
		    	bill.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback);
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
								eDialog.alert(e);
							}
						});
			},
};