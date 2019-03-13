$(function(){
	//阅读协议后，确定提交
	$('#btn-to-company').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/sign/companysign/accompany';
	});
	//企业入驻信息填写完后提交，进入下一步
	$('#btnsignCompany').click(function(){
		if(!$("#companydetailfrom").valid()) {
			$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html')
    				: $('body'))
    				: $('html,body');
    		$body.animate({
    			scrollTop : $("#companydetailfrom").offset().top
    		}, "fast");
    		return;
		}
		var val = ebcForm.formParams($("#companydetailfrom"));
		//校验证件是否上传了
		if ($("#legalPersonImageKey").val() == '' || $("#imgCompanyLicenceKey").val() == '' || $("#taxPathKey").val() == '') {
			eDialog.alert("您好，请确认相关证件都已上传齐全。");
			return;
		}
		eDialog.confirm("请确认企业信息填写无误？", {
			buttons : [{
				caption : '确认',
				callback : function(){					
					$.eAjax({
						url : GLOBAL.WEBROOT + "/sign/companysign/saveCompany",
						data : val,
						datatype:'json',
						success : function(returnInfo) {
							if (returnInfo.resultFlag == 'ok') {
								window.location.href = GLOBAL.WEBROOT+'/sign/companysign/acshop';
							}
						}
					});
					
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
	});

	//企业入驻信息填写完后提交，进入下一步
	$('#btnsignShop').click(function(){
		if(!$("#shopdetailfrom").valid()) return false;
		//校验证件是否上传了
		if ($("#imgShopLogoPathKey").val() == '') {
			eDialog.alert("您好，请上传店铺LOGO");
			return;
		}
		var text = "";
		   $("input[name=distribution]").each(function() {  
	            if ($(this).attr("checked")) {  
	                text += $(this).val()+",";  
	            }  
	        });
		var val = ebcForm.formParams($("#shopdetailfrom"));
		if(text!=null&&text!=''){
		val.distribution = text.substring(0,text.length-1);
		}
		eDialog.confirm("请确认店铺信息并提交审核？", {
			buttons : [{
				caption : '确认',
				callback : function(){					
					$.eAjax({
						url : GLOBAL.WEBROOT + "/sign/companysign/saveShop",
						data : val,
						datatype:'json',
						success : function(returnInfo) {
							window.location.href = GLOBAL.WEBROOT+'/sign/companysign/acdone';
						}
					});
					
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
	});
	
	//上传法人代表证件照
	$('#imageCompanyLegalUpload').click(function(evt){
		busSelector.uploader({
			'fileSizeLimit': '4MB',
			checktype : 'single',
			callback : function(data){
				if(data && data.results && data.results.length > 0){
					$('#legalPersonImageKey').val(data.results[0].fileId);
					$('#imgCompanyLegal').attr('src',data.results[0].url);
				}
			}
		}, evt);
	});

	$('#imageCompanyLicenceUpload').click(function(evt){
		busSelector.uploader({
			'fileSizeLimit': '4MB',
			checktype : 'single',
			callback : function(data){
				if(data && data.results && data.results.length > 0){
					$('#imgCompanyLicenceKey').val(data.results[0].fileId);
					$('#imgCompanyLicence').attr('src',data.results[0].url);
				}
			}
		}, evt);
	});
	//上传税务附件
	$('#imageTaxLicenceUpload').click(function(evt){
		busSelector.uploader({
			'fileTypeExts' : ['doc','docx','pdf'],  //文件选择类型限制
			'fileSizeLimit': '5MB',
			checktype : 'single',
			callback : function(data){
				if(data && data.results && data.results.length > 0){
					$('#taxPathKey').val(data.results[0].fileId);
					$('#taxFile').attr('href',data.results[0].url);
					$('#taxFile').html("查看附件");
				}
			}
		}, evt);
	});
	
	
	$('#imageShopLogoUpload').click(function(evt){
		busSelector.uploader({
			checktype : 'single',
			callback : function(data){
				if(data && data.results && data.results.length > 0){
					$('#imgShopLogoPathKey').val(data.results[0].fileId);
					$('#imgShopLogo').attr('src',data.results[0].url);
				}
			}
		}, evt);
	});
});