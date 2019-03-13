$(function () { 
        //$('#myTab a:first').tab('show');//初始化显示哪个tab 
        $('#myTab a').click(function (e) {
         e.preventDefault();
          $(this).tab('show');
        });
        //优惠码页面返回
        $('#btnReturn').click(function (codeObj) {
        	var code=$(codeObj).attr("code");
        	couptab.goBack(code);
           });
   var couptab={
	  //初始化function	   
	 functionJs:function(){
		   var _f=new Array();
		   $("#use-rule-all > form").each(function(){
			       var _obj="";
			       _obj="rule"+$(this).attr("rule-data");
			       _f.push(eval(_obj));
           });
		  return _f;
	  },
	  //返回
	  goBack:function(_b){
		  //_b有值 表示跳转到优惠码页面
		  if(_b){
			  window.location.href = GLOBAL.WEBROOT+'/coup/code/'+_b;
		  }else{
			  //disabled 保存 和提交
	 	      $('#btnFormSave').attr('disabled',"true"); 
	 	      $('#btnFormSubmit').attr('disabled',"true"); 
	 	      
			  var _backId=$('#doAction').val();
			  if(_backId=='copy' || _backId=='edit' ){
				  window.location.href = GLOBAL.WEBROOT+'/coupinfo';
				  return ;
			  }
			  //新增返回上一页
			  if(_backId=='create' || _backId==''||_backId==null || _backId=="undefined" ){
			      window.location.href = GLOBAL.WEBROOT+'/coupinfo';
			      return ;
			  }
			  history.go(-1);
		  }
	  },
	  //判断是否为数字
	   isNum:function(s){
	           if (s!=null && s!="")
		            {
		                return !isNaN(s);
		            }
	            return false;
	        },
	   //展示
	   coupshow:function(){
		   var _doAction=$('#doAction').val();
		   if(_doAction==null || _doAction=="" ||_doAction==undefined ||_doAction=="create"){
			   return;
		   }
		    //组织参数
 		   $.each(couptab.functionJs(),function(n,_f) {  
 			    try{
 			    	 _f.qryData();
 			    }catch(ex){
 			    	
 			    }
 			    
 	        }); 
	   },
	   coupInfoData:function(){
	     //基本信息
 	     var data= ebcForm.formParams($("#coupInfoForm"));
 	     var data1= ebcForm.formParams($("#receiveRuleForm"));
 	     for(var index in data1){  
 	    	data.push(data1[index]);
 	      } 
 	      //disabled 保存 和提交
 	      $('#btnFormSave').attr('disabled',"true"); 
 	      $('#btnFormSubmit').attr('disabled',"true");  
 	      //组织参数
 		  $.each(couptab.functionJs(),function(n,_f) { 
 			   //返回结果值
 			     var _data =new Array();
 			     try{
 			    	 //调用报错 处理
 			    	  _data= _f.getData();
 			     }catch(ex){
 			    	 
 			     }
 			    
 			    for(var index in _data)  
 		 	      {  
 		 	    	data.push(_data[index]);
 		 	      } 
 	       });
 		   return data;
	   },
	   //数据验证
	   coupCheck:function(){
		   var isStatus = true; 
		   var data= couptab.coupInfoData(); 
		   //ajax请求
		   $.eAjax({
				url : GLOBAL.WEBROOT + "/coup/coupCheck",
				data : data,
				async : false,
				success : function(returnInfo) {
					//成功返回
	 				if(returnInfo.resultFlag=='ok'){
	 					isStatus = true;
	 				//数据验证错误	
	 				}else if(returnInfo.resultFlag=='fail'){
						eDialog.alert(returnInfo.resultMsg,null);
						isStatus = false;
					}else{
						eDialog.alert(returnInfo.resultMsg,null);
						isStatus = false;
					}
				},
				exception : function(returnInfo) {
					isStatus = false;
				}
		   });
		   $('#btnFormSave').removeAttr("disabled");
		   $('#btnFormSubmit').removeAttr("disabled");
		   return isStatus;
	   },
	   //保存 提交公共代码
	   save:function(s){
		       //基础信息
	 		   //if(!$("#coupInfoForm").valid())return false;
	 		   //领取规则
	 		   //if(!$("#receiveRuleForm").valid())return false;
	 		   //获得各个使用规则form
	 		   var _check=true;//true通过 false不通过
	 		   $.each(couptab.functionJs(),function(n,_f) {  
	 			   //默认通过
	 			      var _r=true;
	 			      try{
	 			    	  //无此方法 或者报错
	 			    	   _r=_f.check(); 
	 			      }catch(ex){
	 			    	 _r=true;
	 			      }
	 			   
	 			      if(!_r){
	 			    	 _check=false;
	 			    	  return;
	 			      }
	 	        });  
	 		   if(!_check){
	 			  return;
	 		   }
	 	       //基本信息
	 	       var data= couptab.coupInfoData(); 
	     		//ajax请求
	     		$.eAjax({
	     			url : GLOBAL.WEBROOT + "/coup/"+s,
	     			data : data,
	     			async : false,
	     			success : function(returnInfo) {
	     				//成功返回
	     				if(returnInfo.resultFlag=='ok'){
	     					 couptab.goBack(returnInfo.coupInfoVO.coupCode);
	     				//数据验证错误	
	     				}else if(returnInfo.resultFlag=='fail'){
	     					//enable  保存 和提交
		     				$('#btnFormSave').removeAttr("disabled");
		     				$('#btnFormSubmit').removeAttr("disabled");  
	     					eDialog.alert(returnInfo.resultMsg,null);
	     				}else{
	     					//enable  保存 和提交
		     				$('#btnFormSave').removeAttr("disabled");
		     				$('#btnFormSubmit').removeAttr("disabled");  
	     					eDialog.alert(returnInfo.resultMsg,null);
	     				}
	     			},
	     			exception : function(returnInfo) {
	     				//enable  保存 和提交
	     				$('#btnFormSave').removeAttr("disabled");
	     				$('#btnFormSubmit').removeAttr("disabled");  
	     			}
	     		});
	     	
	     	
	         },
	         //验证 是否需要提醒信息
	        ifShowCheck:function(s){ 
	        	   var data=new Array();
		 		   $.each(couptab.functionJs(),function(n,_f) { 
		 			   //返回结果值
		 			     var _data =new Array();
		 			     try{
		 			    	 //调用报错 处理
		 			    	  _data= _f.getTips();
		 			     }catch(ex){
		 			    	 
		 			     }
		 			    
		 			    for(var index in _data)  
		 		 	      {  
		 		 	    	data.push(_data[index]);
		 		 	      } 
		 	        });   
		 		   return data;
	        }
   }
   //保存
   $('#btnFormSave').click(function(){ 
	   if(!$("#coupInfoForm").valid()){
		   coupInfo.validCoupValue();
		   return false;
		   
	   } 
	   //基本信息
       var isStatus = couptab.coupCheck();
	   if(isStatus == true){
		   if(!coupInfo.validCoupValue())return false;
		   //验证优惠券面额和满减值
		   if(!coupInfo.checkCoupValue()){
			   return false;
		   }
		   //获得提醒说明 
		   var tipsArr=couptab.ifShowCheck();
		   var tips=tipsArr.join("，");
		   if(tips){
				eDialog.confirm("亲,您没有对<span style='color:red'>"+tips+"</span>进行设置,是否确认<span style='color:blue'>保存</span>此优惠券？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							couptab.save("save");
						}
					},{
						caption : '取消',
						callback : $.noop
					}]
				});   
		   }else{
			   couptab.save("save");
		   }
	   }	
   });
   
   //提交
   $('#btnFormSubmit').click(function(){
	   if(!$("#coupInfoForm").valid()){
		   coupInfo.validCoupValue();
		   return false;
		   
	   } 
	   //基本信息 
	   var isStatus = couptab.coupCheck();
	   if(isStatus == true){
		   if(!coupInfo.validCoupValue())return false;
		   //验证优惠券面额和满减值
		   if(!coupInfo.checkCoupValue()){
			   return false;
		   }
	       //获得提醒说明 
		   var tipsArr=couptab.ifShowCheck();
		   var tips=tipsArr.join("，");
		   if(tips){
				eDialog.confirm("亲,您没有对<span style='color:red'>"+tips+"</span>进行设置,是否确认<span style='color:blue'>提交</span>此优惠券？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							couptab.save("submit");
						}
					},{
						caption : '取消',
						callback : $.noop
					}]
				});   
		   }else{
			   couptab.save("submit");
		   }
	   }
   });
   
   //返回(编辑  修改 详情 新增 返回 页面不同)
	$('#btnReturnTab').click(function(){
		 couptab.goBack();
	});
   
	//初始化
	couptab.coupshow();
	
	
	$("#auto_code").click(function(){
		var flag = $(this).prop("checked");
		if(flag){
			//ajax请求
			$.eAjax({
				url:GLOBAL.WEBROOT + "/coup/coupCode",
				success:function(returnInfo){
					//成功返回
					if(returnInfo.resultFlag=='ok'){
						$("#coup_code").val(" ");
						$("#coup_code").val(returnInfo.resultMsg);
						$("#coup_code").attr({"disabled":"disabled"});
					}
				}
			})
		}else{
			$("#coup_code").removeAttr("disabled");
		}
	});
});