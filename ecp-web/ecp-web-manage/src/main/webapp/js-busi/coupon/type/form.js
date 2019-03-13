$(function(){
		var _coup_code = $("button[name="+190+"][value=1]");
		var coup_code_flag = $("#coup_code").val();
		if(coup_code_flag=="true"){
			if(_coup_code.hasClass("btn-primary")){
				$("button[name="+150+"]").removeClass('btn-primary btn-active');
				$("button[name="+160+"]").removeClass('btn-primary btn-active');
				$("button[name="+150+"]").attr({"disabled":"disabled"});
				$("button[name="+160+"]").attr({"disabled":"disabled"});
			}
		}
	//开关设置时间
	 $(".switch-button").find('.btn').live('click',function(e){
		      //var _code=$(this).val();

		      var _code=$(this).prop("name"); 
		      var _value = $(this).val();
		      $("button[name="+_code+"]").removeClass('btn-primary');
		      $("button[name="+_code+"]").removeClass('btn-active');
		      $(this).addClass("btn-primary");
		      $(this).addClass("active");
		      var coup_code_flag = $("#coup_code").val();
		      if(coup_code_flag=="true"){
		    	  if(_code=="190"&&_value=="1"){
		    		  $("button[name="+150+"]").removeClass('btn-primary btn-active');
		    		  $("button[name="+160+"]").removeClass('btn-primary btn-active');
		    		  $("button[name="+150+"]").attr({"disabled":"disabled"});
		    		  $("button[name="+160+"]").attr({"disabled":"disabled"});
		    	  }else if(_code=="190"&&_value=="0"){
		    		  if($("button[name="+150+"][value='1']").hasClass("active")){
		    			  $("button[name="+150+"][value='1']").addClass('btn-primary btn-active');
		    		  }else{
		    			  $("button[name="+150+"][value='0']").addClass('btn-primary btn-active');
		    		  }
		    		  if($("button[name="+160+"][value='1']").hasClass("active")){
		    			  $("button[name="+160+"][value='1']").addClass('btn-primary btn-active');
		    		  }else{
		    			  $("button[name="+160+"][value='0']").addClass('btn-primary btn-active');
		    		  }
		    		  $("button[name="+150+"]").removeAttr("disabled");
		    		  $("button[name="+160+"]").removeAttr("disabled");
		    	  }
		  	}
		      if(_code=="240"&&_value=="1"){
		    	  $("button[name="+180+"]").removeClass('btn-primary btn-active');
		    	  $("button[name="+190+"]").removeClass('btn-primary btn-active');
	    		  $("button[name="+140+"]").removeClass('btn-primary btn-active');
	    		  $("button[name="+160+"]").removeClass('btn-primary btn-active');
	    		  $("button[name="+180+"]").attr({"disabled":"disabled"});
	    		  $("button[name="+190+"]").attr({"disabled":"disabled"});
	    		  $("button[name="+140+"]").attr({"disabled":"disabled"});
	    		  $("button[name="+160+"]").attr({"disabled":"disabled"});
	    	  }else if(_code=="240"&&_value=="0"){
	    		  if($("button[name="+140+"][value='1']").hasClass("active")){
	    			  $("button[name="+140+"][value='1']").addClass('btn-primary btn-active');
	    		  }else{
	    			  $("button[name="+140+"][value='0']").addClass('btn-primary btn-active');
	    		  }
	    		  if($("button[name="+160+"][value='1']").hasClass("active")){
	    			  $("button[name="+160+"][value='1']").addClass('btn-primary btn-active');
	    		  }else{
	    			  $("button[name="+160+"][value='0']").addClass('btn-primary btn-active');
	    		  }
	    		  if($("button[name="+190+"][value='1']").hasClass("active")){
	    			  $("button[name="+190+"][value='1']").addClass('btn-primary btn-active');
	    		  }else{
	    			  $("button[name="+190+"][value='0']").addClass('btn-primary btn-active');
	    		  }
	    		  if($("button[name="+180+"][value='1']").hasClass("active")){
	    			  $("button[name="+180+"][value='1']").addClass('btn-primary btn-active');
	    		  }else{
	    			  $("button[name="+180+"][value='0']").addClass('btn-primary btn-active');
	    		  }
	    		  $("button[name="+150+"]").removeAttr("disabled");
	    		  $("button[name="+160+"]").removeAttr("disabled");
	    		  $("button[name="+190+"]").removeAttr("disabled");
	    		  $("button[name="+180+"]").removeAttr("disabled");
	    	  }
			  e.preventDefault();
	    });
	 if($('#coupTypeName').val()==''){
		 $("button[name="+190+"][value=0]").click();
	 }
	 //返回
	 $('#btnReturn').click(function(){
		 history.go(-1);
	 });
	 $('#typeLimitbtn').click(function(){
		 $("button[name="+120+"]").removeClass('btn-primary');
		 $("button[name="+120+"]").removeClass('btn-active');
		 $("button[name="+120+"]").attr({"disabled":"disabled"});
	 });
	 $('#typeLimitbtn').click();
	 //保存
	 $('#btnFormSave').click(function(){
		  
		   if(!$("#detailInfoForm").valid())return false;
		   
		   var _typeLimit=$("input[name='typeLimit']:checked").val();
		   if(!_typeLimit){
			   eDialog.alert('请选择使用级别!',null);
			   return;
		   }
		   var _data=ebcForm.formParams($("#detailInfoForm"));
		 
		   //获得选中条件
		   var _btn=$('#collapseOne .btn-primary');
		   for(var i=0;i<_btn.length;i++){
			   _data.push({'name':'useMap['+_btn[i].name+']','value':_btn[i].value});
		   }
		   
		   var _btn=$('#collapseTwo .btn-primary');
		   for(var i=0;i<_btn.length;i++){
			   _data.push({'name':'receiveMap['+_btn[i].name+']','value':_btn[i].value});
		   }
			$('#btnFormSubmit').attr("disabled","true");
			$('#btnFormSave').attr("disabled","true");
			//ajax请求
			$.eAjax({
				url : GLOBAL.WEBROOT + "/coupontype/save",
				data : _data,
				success : function(returnInfo) {
					$('#btnFormSave').removeAttr("disabled");
					$('#btnFormSubmit').removeAttr("disabled");
					//成功返回grid
					if(returnInfo.resultFlag=='ok'){
						history.go(-1);
					//数据验证错误	
					}else if(returnInfo.resultFlag=='fail'){
					       
					}else{
						eDialog.alert(returnInfo.resultMsg,null);
					}
				},
				exception:function(returnInfo){
					$('#btnFormSave').removeAttr("disabled");
					$('#btnFormSubmit').removeAttr("disabled");
					eDialog.alert(returnInfo.resultMsg,null);
				}
			});

		});
	 //提交
	 $('#btnFormSubmit').click(function(){
		 
		    if(!$("#detailInfoForm").valid())return false;
		    var _typeLimit=$("input[name='typeLimit']:checked").val();
			   if(!_typeLimit){
				   eDialog.alert('请选择使用级别!',null);
				   return;
			   }
			   
			   var _data=ebcForm.formParams($("#detailInfoForm"));
				 
			   //获得选中条件
			   var _btn=$('#collapseOne .btn-primary');
			   for(var i=0;i<_btn.length;i++){
				   _data.push({'name':'useMap['+_btn[i].name+']','value':_btn[i].value});
			   }
			   
			   var _btn=$('#collapseTwo .btn-primary');
			   for(var i=0;i<_btn.length;i++){
				   _data.push({'name':'receiveMap['+_btn[i].name+']','value':_btn[i].value});
			   }
			   
			$('#btnFormSubmit').attr("disabled","true");
			$('#btnFormSave').attr("disabled","true");
			//ajax请求
			$.eAjax({
				url : GLOBAL.WEBROOT + "/coupontype/submit",
				data : _data,
				success : function(returnInfo) {
					$('#btnFormSave').removeAttr("disabled");
					$('#btnFormSubmit').removeAttr("disabled");
					//成功返回grid
					if(returnInfo.resultFlag=='ok'){
						history.go(-1);
					//数据验证错误	
					}else if(returnInfo.resultFlag=='fail'){
					       
					}else{
						eDialog.alert(returnInfo.resultMsg,null);
					}
				},
				exception:function(returnInfo){
					$('#btnFormSave').removeAttr("disabled");
					$('#btnFormSubmit').removeAttr("disabled");
					eDialog.alert(returnInfo.resultMsg,null);
				}
			});

		});
});

