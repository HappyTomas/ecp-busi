$(function(){
	
	//主题促销清空
	$('#btnClearGroup').click(function(){
		$('#groupId').val('');//主题id
		$('#groupName').val('');//主题名称
	});
	
	//主题促销选择 
	$('#btnSelectGroup').click(function(){
		 alert('待实现');
	});
	
	 $("#ifBlackList").click(function(){
		 if($(this).prop('checked')==true){
		    	  //黑名单 选择 启动  黑名单查询按钮可选择
		    	  $('#btnGdsBlackOpenWindow').removeAttr("disabled"); 
		    }else{
		    	  //黑名单 选择 启动  黑名单查询按钮不可选择
		    	 $('#btnGdsBlackOpenWindow').attr('disabled',"true"); 
		    }
		 });
	 $(".radioJoinRange").click(function(){
		    if($(this).val()=='1'){
		    	  //黑名单 选择 启动
		          $("#ifBlackListLabel").show();
		          //隐藏商品选择 功能 和已选择列表
		          $(".gdsSelectDivClass").hide();
		          
		          $(".blackGdsTableClass").show();
		          
		    }else{
		    	  //清空选择
		    	  $('#ifBlackList').attr("checked",false);
		    	  //黑名单 不可用
		    	  $('#btnGdsBlackOpenWindow').attr('disabled',"true");
		    	  $("#ifBlackListLabel").hide();
		    	  //展示商品选择 功能 和已选择列表
		          $(".gdsSelectDivClass").show();
		          $(".blackGdsTableClass").hide();
		    }
		 });
	//促销商品列表
	var initGdsList=function(_skuIds){
		//编辑 进入 promId非空
		var _promId=$('#promId').val();
		//_skuIds 初始化页面为空  ，open页面回掉可非空
		if(!_skuIds){
			_skuIds=new Array();
		}
		//当前页面的列表数据 需要加入到skuIds中
		 var _l=$("#gdsTable tbody tr td input[ name='checkboxgds']");
		 if(_l && _l.length>0){
			 for(var i=0;i<_l.length;i++){
				 _skuIds.push(_l[i].parentNode.parentNode.id);
			 }
		 }
		 else{
			 //没有数据 清空
			 if(_skuIds && _skuIds.length==0){
				 _skuIds='';
			 }
		 }
		 
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsprom/gdsList?promId="+_promId+"&skuIds="+_skuIds,
			"dataType": "text",
			success : function(returnInfo) {
				$('#gdstableId').empty();
				$('#gdstableId').append(returnInfo);
				/*$('#gdstableId').html('');
				$('#gdstableId').html(returnInfo);*/
			}
		});
	};	
	
	//黑名单商品列表
	var initBlackGdsList=function(_skuIds){
		//编辑 进入 promId非空
		var _promId=$('#promId').val();
		//_skuIds 初始化页面为空  ，open页面回掉可非空
		if(!_skuIds){
			_skuIds=new Array();
		}
		//当前页面的列表数据 需要加入到skuIds中
		 var _l=$("#gdsBlackTable tbody tr td input[ name='checkboxgds']");
		 if(_l && _l.length>0){
			 for(var i=0;i<_l.length;i++){
				 _skuIds.push(_l[i].parentNode.parentNode.id);
			 }
		 }
		 else{
			 //没有数据 清空
			 if(_skuIds && _skuIds.length==0){
				 _skuIds='';
			 }
		 }
		 
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsprom/gdsblackList?promId="+_promId+"&skuIds="+_skuIds,
			"dataType": "text",
			success : function(returnInfo) {
				$('#blackGdsTableId').empty();
				$('#blackGdsTableId').append(returnInfo);
				/*$('#gdstableId').html('');
				$('#gdstableId').html(returnInfo);*/
			}
		});
	};
	
	//全局变量
	 var gdsTr;
	 //商品删除按钮功能
	 $("#gdsTable tr td a[ name='delGdsRow']").live('click',function(e){
		    gdsTr=e.currentTarget.parentNode.parentNode;
			eDialog.confirm("确定删除吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						gdsTr.remove();
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		 });
	 
		 //批量删除功能 
	 $("#btn_gds_del_batch").live('click',function(e){
		 
			eDialog.confirm("确定批量删除吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
		            		 var _l=$("#gdsTable tbody tr td input[ name='checkboxgds']");
		        			 var _tr=null;
		        			 for(var i=0;i<_l.length;i++){
		        				 if(_l[i].checked){
		        					 //选中 删除
		        					 _tr=_l[i].parentNode.parentNode;
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
	 
	 
	//全局变量
	 var gdsBlackTr;
	 //商品删除按钮功能
	 $("#gdsBlackTable tr td a[ name='delGdsRow']").live('click',function(e){
		 gdsBlackTr=e.currentTarget.parentNode.parentNode;
			eDialog.confirm("确定删除吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						gdsBlackTr.remove();
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		 });
	 
		 //批量删除功能 
	 $("#btn_gds_delblack_batch").live('click',function(e){
		 
			eDialog.confirm("确定批量删除吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
		            		 var _l=$("#gdsBlackTable tbody tr td input[ name='checkboxgds']");
		        			 var _tr=null;
		        			 for(var i=0;i<_l.length;i++){
		        				 if(_l[i].checked){
		        					 //选中 删除
		        					 _tr=_l[i].parentNode.parentNode;
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
	 //checkbox商品选择事件
	 $("#gdsTable thead tr input[ id='dt_row_all_check']").live('click',function(e){
		   //选中 表示全选
		   if(e.currentTarget.checked){
			   $("#gdsTable tbody tr td input[ name='checkboxgds']").prop('checked',true);
		   }else{
			   //全部取消
			   $("#gdsTable tbody tr td input[ name='checkboxgds']").prop('checked',false);
		   }
		 });
	 //checkbox黑名单商品事件
	 $("#gdsBlackTable thead tr input[ id='dt_row_all_check']").live('click',function(e){
		   //选中 表示全选
		   if(e.currentTarget.checked){
			   $("#gdsBlackTable tbody tr td input[ name='checkboxgds']").prop('checked',true);
		   }else{
			   //全部取消
			   $("#gdsBlackTable tbody tr td input[ name='checkboxgds']").prop('checked',false);
		   }
		 });
	    //商品选择 window
		$('#btnGdsOpenWindow').click(function(){
			bDialog.open({
				title : '商品选择',
				width : 1000,
				height : 550,
				url : GLOBAL.WEBROOT + "/gdsprom/gdsselect",
				callback:function(data){
					   //确定 按钮_if_query==1
					   if(data && data.results && data.results[0]._if_query=='1'){
						   initGdsList(data.results[0].skuIds);
					   }
					  
				}
			});
		});
	    //黑名单商品选择 window
		$('#btnGdsBlackOpenWindow').click(function(){
			bDialog.open({
				title : '黑名单商品选择',
				width : 1000,
				height : 550,
				url : GLOBAL.WEBROOT + "/gdsprom/gdsblackselect",
				callback:function(data){
					   //确定 按钮_if_query==1
					   if(data && data.results && data.results[0]._if_query=='1'){
						   initBlackGdsList(data.results[0].skuIds);
					   }
					  
				}
			});
		});
	//触发调用 查询商品事件
	initGdsList();
	initBlackGdsList();
	//默认 部分商品选择 并且 黑名单hide
	$("#ifBlackListLabel").hide();
	//黑名单
	$(".blackGdsTableClass").hide();
	
});
