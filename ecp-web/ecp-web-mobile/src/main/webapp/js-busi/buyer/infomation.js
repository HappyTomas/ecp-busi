
//页面初始化模块
$(function(){
    //跳转个人信息
	$('#nickname').click(function(){
		$('#nickNameInput').offCanvas('open');
		$(window).off('resize.offcanvas.amui');
	});
	
	$('#nsubmit').click(function(){
		var nickName = $('#nickName').val();
		$.eAjax({
			url : GLOBAL.WEBROOT+"/infomation/update",
			data : {"nickname":nickName},
			async : true,
			type : "post",
			dataType : "json",
			success : function(datas) {
				if(datas.resultFlag=='ok'){
					location.reload();
				}
			}
		});
	});
	
	$('#bsubmit').click(function(){
		var custBirthday = $('#custBirthday').val();
		$.eAjax({
			url : GLOBAL.WEBROOT+"/infomation/update",
			data : {"custBirthday":custBirthday},
			async : true,
			type : "post",
			dataType : "json",
			success : function(datas) {
				if(datas.resultFlag=='ok'){
					location.reload();
				}
			}
		});
	});
	
	$('#birthday').click(function(){
		$('#birthdayInput').offCanvas('open');
		$(window).off('resize.offcanvas.amui');
	});
	
	$('#genders').click(function(){
		$('#genderInput').offCanvas('open');
		$(window).off('resize.offcanvas.amui');
	});
	
	$('#gsubmit').on('click',function(){
		var gender = $('#gender').val();
		$.eAjax({
			url : GLOBAL.WEBROOT+"/infomation/update",
			data : {"gender":gender},
			async : true,
			type : "post",
			dataType : "json",
			success : function(datas) {
				if(datas.resultFlag=='ok'){
					location.reload();
				}
			}
		});
	});
/*	$('#custpic').click(function(){
		$('#custPicInput').offCanvas('open');
		$(window).off('resize.offcanvas.amui');
	});*/
	
	$('#custaddr').click(function(){
		window.location.href=GLOBAL.WEBROOT+"/custaddr/index"; 
	});
	
	$('#unbind').click(function(){
		$.eAjax({
			url : GLOBAL.WEBROOT+"/login/unbind",
			data : {},
			async : true,
			type : "post",
			dataType : "json",
			success : function(datas) {
				if(datas.resultFlag=='ok'){
					window.location.href=GLOBAL.WEBROOT+"/j_spring_security_logout"; 
				}
			}
		});
	});
	
	$('#piced').on('click',function(){
		$("#image").click();
	});
	
	$("#image").on("change",function(){
		ajaxFileUpload();
	});
	//修改密码
	$('#pwd').click(function(){
		window.location.href = GLOBAL.WEBROOT+"/infomation/pwd"; 
	});
});



