$(function(){
	//阅读协议后，确定提交
	$('#submit').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/companysign/accompany';
	});
	//企业入驻信息填写完后提交，进入下一步
	$('#btnsignCompany').click(function(){
		if(!$("#companydetailfrom").valid()) return false;
		var val = ebcForm.formParams($("#companydetailfrom"));
		eDialog.confirm("请确认企业信息填写无误？", {
			buttons : [{
				caption : '确认',
				callback : function(){					
					$.eAjax({
						url : GLOBAL.WEBROOT + "/companysign/saveCompany",
						data : val,
						datatype:'json',
						success : function(returnInfo) {
							window.location.href = GLOBAL.WEBROOT+'/companysign/acshop';
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
		var val = ebcForm.formParams($("#shopdetailfrom"));
		eDialog.confirm("请确认店铺信息并提交审核？", {
			buttons : [{
				caption : '确认',
				callback : function(){					
					$.eAjax({
						url : GLOBAL.WEBROOT + "/companysign/saveShop",
						data : val,
						datatype:'json',
						success : function(returnInfo) {
							window.location.href = GLOBAL.WEBROOT+'/companysign/acdone';
						}
					});
					
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
	});
	
	$('#imageCompanyLegalUpload').click(function(evt){
		busSelector.uploader({
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
			callback : function(data){
				if(data && data.results && data.results.length > 0){
					$('#imgCompanyLicenceKey').val(data.results[0].fileId);
					$('#imgCompanyLicence').attr('src',data.results[0].url);
				}
			}
		}, evt);
	});
	
	
	$('#imageShopLogoUpload').click(function(evt){
		busSelector.uploader({
			callback : function(data){
				if(data && data.results && data.results.length > 0){
					$('#imgShopLogoPathKey').val(data.results[0].fileId);
					$('#imgShopLogo').attr('src',data.results[0].url);
				}
			}
		}, evt);
	});
});