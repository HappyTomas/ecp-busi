$(function(){
	//开关设置时间
	 $(".switch-button").find('.btn').live('click',function(e){
		      //var _code=$(this).val();

		      var _code=$(this).prop("name"); 
		      $("button[name="+_code+"]").removeClass('btn-primary');
		      $("button[name="+_code+"]").removeClass('btn-active');
		      $(this).addClass("btn-primary");
		      $(this).addClass("active");
			  e.preventDefault();
	    });
	 
	$('#btn_code_del').click(function(){
		var _ids = $('#dataGridTable').getCheckedRowsIndex();
        if(_ids && _ids.length>=1){
			eDialog.confirm("确定删除吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
		            	for(var i=0;i<_ids.length;i++){
		            		$("#dataGridTable").removeRow(_ids[i]);
		            	}
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		
        
		}else if(!_ids || _ids.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	});

	$('#btnFormAuthSave').click(function(){
		
		//保存提交
		  var _shopIdList=new Array();
		  var _promTypeCodeList=new Array();
	      var _l=$("#shopTable tbody tr td input[ name='checkboxgds']");
	      var _btn=$('#collapseOne .btn-primary');
	      if(_l.length<=0){
	    		eDialog.alert('请选择需要参与的店铺',null); 
	    		return ;
	      }else{
	    	  for(var i=0;i<_l.length;i++){
	    		  var _tdId="#tips-"+_l[i].id;
	    		  //暂时验证去掉
	    		 /* if($(_tdId).text() !=null && $(_tdId).text() !=""){
	    			  eDialog.alert('选择的店铺存在错误信息，请检查！',null); 
	  	    		  return ;
	    		  }*/
	    		  _shopIdList.push(_l[i].id );
	    	  }
	      }
	      if(_btn.length<=0){
	    	  eDialog.alert('请选择需要授权的类型',null); 
	    		return ;
	      }else{
	    	  for(var i=0;i<_btn.length;i++){
	    		  if(_btn[i].value == "1"){
	    			  _promTypeCodeList.push(_btn[i].id)
	    		  }
	    	  }
	      }
		$("#shopIds").val(_shopIdList.join(','));
		$("#promTypeCode").val(_promTypeCodeList.join(','));
		$(this).attr("disabled","true");
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/promauth/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				$('#btnFormAuthSave').removeAttr("disabled");
				//成功返回grid
				if(returnInfo.resultFlag=='ok'){
					window.location.href = GLOBAL.WEBROOT+'/promauth';
				//数据验证错误	
				}else if(returnInfo.resultFlag=='fail'){
				       //清空当前列表 
						 var _l=$("#shopTable tbody tr td input[ name='checkboxgds']");
	        			 var _tr=null;
	        			 for(var i=0;i<_l.length;i++){
	        					 //删除
	        					 //_tr=_l[i].parentNode.parentNode;
	        				     _tr=$(_l[i]).parent().parent();
	        					 _tr.remove();
	        			 }
					   //insert table数据
	        			 var _html="";
	        			 _html=_html+"<div class=\"row-fluid\" style=\"margin-bottom: 10px;\">";
	        			 _html=_html+"<div class=\"span6\">";
	        			 _html=_html+"<a class=\"btn \" id=\"btn_shop_del_batch\" href=\"javascript:void(0)\"><i class=\"icon-remove icon-white\"></i>批量删除</a>";
	        			 _html=_html+"</div>";
	        			 _html=_html+"</div>";
	        			 _html=_html+"<table id=\"shopTable\" class=\"table table-striped table-bordered dTableR dataTable\" role=\"grid\" aria-describedby=\"dataGridTable_info\">";
        				 _html=_html+"<thead>";
        				 _html=_html+"<tr role=\"row\">";
        				 _html=_html+"<th class=\"center selectColumn sorting_disabled\" rowspan=\"1\" colspan=\"1\" aria-label=\"选择\" align=\"center\" style=\"width: 35px; vertical-align: middle; text-align: center; padding: 0px;\"><input type=\"checkbox\" id=\"dt_row_all_check\" title=\"全选/全取消\" style=\"margin: 0px;\"></th>";
        				 _html=_html+"<th class=\"sorting_disabled\" rowspan=\"1\" colspan=\"1\" aria-label=\"店铺编码\" style=\"width: 100px;\">店铺编码</th>";
        				 _html=_html+"<th class=\"sorting_disabled\" rowspan=\"1\" colspan=\"1\" aria-label=\"店铺名称\" style=\"width: 120px;\">店铺名称</th>";
        				 _html=_html+"<th class=\"sorting_disabled\" rowspan=\"1\" colspan=\"1\" aria-label=\"错误信息\" style=\"width: 140px;\">错误信息</th>";
        				 _html=_html+"<th class=\"sorting_disabled\" rowspan=\"1\" colspan=\"1\" aria-label=\"操作\" style=\"width: 100px;\">操作</th>";
        				 _html=_html+"</tr>";
        				 _html=_html+"</thead>";
	        			if(returnInfo && returnInfo.shopVOList && returnInfo.shopVOList.length>0){
	        				for(var i=0;i<returnInfo.shopVOList.length;i++){
	        					 _html=_html+"<tr id='"+returnInfo.shopVOList[i].shopId+"' role=\"row\" class='odd'><td class=\" center selectColumn\" align=\"center\" style=\"width: 35px; vertical-align: middle; text-align: center; padding: 0px;\"><input type=\"checkbox\" id='"+returnInfo.shopVOList[i].shopId+"' name=\"checkboxgds\"></td><td>"+returnInfo.shopVOList[i].shopId+"</td><td>"+returnInfo.shopVOList[i].shopName+"</td><td id='tips-"+returnInfo.shopVOList[i].shopId+"'><span style='color:red'>"+returnInfo.shopVOList[i].tips+"</span></td><td><a class=\"btn\" data='"+returnInfo.shopVOList[i].shopId+"' name=\"delGdsRow\" href=\"javascript:void(0)\"><i class=\"icon-remove icon-white\"></i>删除</a></td></tr>" ;
	        				}
	        			}
	        			 $("#dataGridTable").empty();
	        			 $("#dataGridTable").append(_html);
				}else{
					eDialog.alert(returnInfo.resultMsg,null);
				}
			},
			exception : function(returnInfo){
				$('#btnFormAuthSave').removeAttr("disabled");
			}
		});
	
	});
	//返回
	$('#btnReturnAuth').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/promauth';
	});
	//错误信息
	$('#btnShowError').click(function(){
		$('div.formValidateMessages').slideToggle('fast');
	});
	
	 var shopTr;
	 //店铺删除按钮功能
	 $("#shopTable tr td a[ name='delGdsRow']").live('click',function(e){
		 
		 //shopTr=e.currentTarget.parentNode.parentNode;
		 shopTr=$(e.currentTarget).parent().parent();
			eDialog.confirm("确定删除吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						shopTr.remove();
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		 });
	 
	 //批量删除功能 
	 $("#btn_shop_del_batch").live('click',function(e){
		     var _doAction=false;
			 var _l=$("#shopTable tbody tr td input[ name='checkboxgds']");
			 for(var i=0;i<_l.length;i++){
				 if(_l[i].checked){
					 _doAction=true;
					 break;
				 }
			 }
		      if(!_doAction){
		    	  eDialog.alert('请，选择需要删除的数据。');
		    	  return;
		      }
			eDialog.confirm("确定批量删除吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
		            		 var _l=$("#shopTable tbody tr td input[ name='checkboxgds']");
		        			 var _tr=null;
		        			 for(var i=0;i<_l.length;i++){
		        				 if(_l[i].checked){
		        					 //选中 删除
		        					 _tr=$(_l[i]).parent().parent();
		        					 _tr.remove();
		        				 }
		        			 }
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		 });
	 //checkbox店铺选择事件
	 $("#shopTable thead tr input[ id='dt_row_all_check']").live('click',function(e){
		   //选中 表示全选
		   if(e.currentTarget.checked){
			   $("#shopTable tbody tr td input[ name='checkboxgds']").prop('checked',true);
		   }else{
			   //全部取消
			   $("#shopTable tbody tr td input[ name='checkboxgds']").prop('checked',false);
		   }
		 });
	 //店铺查询
		$('#btnShopQuery').click(function(){
			bDialog.open({
				title : '店铺选择',
				width : 800,
				height : 550,
				url : GLOBAL.WEBROOT + "/promshop",
				callback:function(data){
					   //返回结果
				 if(data && data.results){
					if(!data.results[0].shopIds){
						return;
					}
					var _shopIds=data.results[0].shopIds;
					//当前页面的列表数据 需要加入到_shopIds中
					 var _l=$("#shopTable tbody tr td input[ name='checkboxgds']");
					 if(_l && _l.length>0){
						 for(var i=0;i<_l.length;i++){
							 _shopIds.push(_l[i].parentNode.parentNode.id);
						 }
					 }
					 else{
						 //没有数据 清空
						 if(_shopIds && _shopIds.length==0){
							 _shopIds='';
						 }
					 }
					//ajax请求
					$.eAjax({
						url : GLOBAL.WEBROOT + "/promshop/shoptable?shopIds="+_shopIds,
						"dataType": "text",
						success : function(returnInfo) {
							$('#dataGridTable').empty();
							$('#dataGridTable').append(returnInfo);
						}
					});
				}
			 }
			});
		});
});