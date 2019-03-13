$(function(){
	
	//主题促销清空
	$('#btnClearGroup').click(function(){
		$('#groupId').val('');//主题id
		$('#groupName').val('');//主题名称
	});
	//黑名单
	 $("#ifBlackList").click(function(){
		 promInfo.ifBlackList(this);
		 });
	 //参与范围
	 $(".radioJoinRange").click(function(){
		 promInfo.joinRangeClick(this);
	 });
	
	//全局变量
	 var catgBlackTr;
	 //分类删除按钮功能
	 $("#catgBlackTable tr td a[ name='delGdsRow']").live('click',function(e){
		 promInfo.delCatgBlack(e);
	 });
	 
	//分类批量删除功能 
	 $("#btn_catg_delblack_batch").live('click',function(e){
		 promInfo.delAllCatgBlack(e);
	 });
	//全局变量
	 var catgTr;
	 //分类删除按钮功能
	 $("#catgTable tr td a[ name='delGdsRow']").live('click',function(e){
		 promInfo.delCatg(e);
	 });
	 
		 //分类批量删除功能 
	 $("#btn_catg_del_batch").live('click',function(e){
		 promInfo.delAllCatg(e);
	 });
	 
	//全局变量
	 var gdsTr;
	 //商品删除按钮功能
	 $("#gdsTable tr td a[ name='delGdsRow']").live('click',function(e){
		 promInfo.delGds(e);
	 });
	 
		 //批量删除功能 
	 $("#btn_gds_del_batch").live('click',function(e){
		 promInfo.delAllGds(e);
	 });
	 
	//全局变量
	 var gdsBlackTr;
	 //商品删除按钮功能
	 $("#gdsBlackTable tr td a[ name='delGdsRow']").live('click',function(e){
		 promInfo.delGdsBlack(e);
	 });
	 
		 //批量删除功能 
	 $("#btn_gds_delblack_batch").live('click',function(e){
		 promInfo.delAllGdsBlack(e);
	 });
	 //checkbox分类选择事件
	 $("#catgTable thead tr input[ id='dt_row_all_check']").live('click',function(e){
		 promInfo.selectCheckBoxCatg(e);
	 });
	 //checkbox商品选择事件
	 $("#gdsTable thead tr input[ id='dt_row_all_check']").live('click',function(e){
		 promInfo.selectCheckBoxGds(e);
	 });
	 //checkbox黑名单分类事件
	 $("#catgBlackTable thead tr input[ id='dt_row_all_check']").live('click',function(e){
		 promInfo.selectCheckBoxCatgBlack(e);
	 });
	 //checkbox黑名单商品事件
	 $("#gdsBlackTable thead tr input[ id='dt_row_all_check']").live('click',function(e){
		 promInfo.selectCheckBoxGdsBlack(e);
	 });
    //商品选择 window
	$('#btnGdsOpenWindow').unbind('click').click(function(){ 
		var _shopId=$('#shopId').val();
		var _sid=$('#siteId').val();
		promInfo.queryGds(_shopId,_sid);
	});
    //分类选择 window
	$('#btnCatgOpenWindow').unbind('click').click(function(){
		var _sid=$('#siteId').val();
		promInfo.queryCatg(_sid);
	});
    //黑名单分类选择 window
	$('#btnCatgBlackOpenWindow').click(function(){
		var _sid=$('#siteId').val();
		promInfo.queryBlackCatg(_sid);
	});
	//黑名单商品选择 window
	$('#btnGdsBlackOpenWindow').click(function(){
		var _shopId=$('#shopId').val();
		var _sid=$('#siteId').val();
		promInfo.queryBlackGds(_shopId,_sid);
	});
    //主题选择 window
	$('#btnSelectGroup').click(function(){
		//站点
		var _siteId=$('#siteId').val();
		promInfo.queryGroup(_siteId);
	});
	  //批量商品导入按钮功能
	$('#importGds').click(function(){
		//站点
		var _promId=$('#promId').val();
		promInfo.importData(_promId,$('#doAction').val());
	});
	 //更多详情展示参与商品列表
	 $("#manyGds").live('click',function(e){
		 var _promId=$('#promId').val();
		 promInfo.manyGds(_promId,$('#doAction').val());
	 });
	 //促销数量变更
	 $(".promCntBlur").live('blur',function(obj){
		 if($('#doAction').val()=='edit'){
			  //验证oldValue 和现在值 是否相同   相同跳过 不相同 需要提交数据库
			 if($(obj.currentTarget).attr('oldvalue')!=$(obj.currentTarget).val()){
				 var tr=$(obj.currentTarget).parent().parent();
				 var _skuId=$(tr).attr("id");//单品编码
				 var _promCnt=$(obj.currentTarget).val();
				   //不是正整数
 	 			 if( !ebcCheck.isInt(_promCnt)){
 	 				eDialog.alert('促销数量不是正整数，请检查',null);
 						return ; 
 	 			 }
				 promInfo.updateGdsPromCntDb($(obj.currentTarget),_skuId,_promCnt); 
			 }
		 }
	 });
	 
	//copy view edit create 展示不同信息
	promInfo.promInfoShow($('#doAction').val());
});

var promInfo={
	//页面信息 展示	
	//copy 同create ,view 隐藏相关按钮功能。edit可编辑
	promInfoShow:function(_doAction){
		//触发调用 查询商品事件
		if(_doAction=="view" || _doAction=="edit"|| _doAction=="copy"){
			
		     //统一调用方式
		       try {
		    	   //验证是否存在
		           if (typeof(discountRuleFun) == "undefined") {
		           } else {
		        	   if(_doAction=="view" ){
		        		   discountRuleFun.hide();
		        	   }else{
		        		   discountRuleFun.show();
		        	   }
		        	     //调用方法
		        	   discountRuleFun.initList();
		           }
		       } catch(e) {
		    	   
		       }
		       //初始化页面列表查询
		       //特别处理 copy不展示商品信息
		       if(_doAction!="copy"){
		    	   promInfo.initGdsList(null,null);   
		       }
		       promInfo.initBlackGdsList(null);
		       promInfo.initCatgList(null);
		       promInfo.initBlackCatgList(null);
		       
		       promInfo.joinRangeClick($("input[name='promInfoVO.joinRange']:checked"));
		       //查看
			   if(_doAction=="view" ){
			    	$(".view").hide();
			    } 
			  
		    //黑名单选择
			if($('#ifBlackList').prop('checked')==true){
			 	//黑名单商品选择按钮 
				 $(".blackGdsTableClass").show();
				 //黑名单 可选
				$('#btnGdsBlackOpenWindow').removeAttr("disabled"); 
				 //黑名单 可选
				$('#btnCatgBlackOpenWindow').removeAttr("disabled"); 
			 }else{
				 //黑名单 disabled
				 $('#btnGdsBlackOpenWindow').attr('disabled',"true"); 
				 $('#btnCatgBlackOpenWindow').attr('disabled',"true"); 
			 }
			
		}else{
			//默认 部分商品选择 并且 黑名单hide
			$("#ifBlackListLabel").hide();
			//黑名单
			$(".blackGdsTableClass").hide();
			//黑名单 商品
			$("#gdsBlackSelectId").hide();
			//黑名单 分类
			$("#catgBlackSelectId").hide();
		}
	},
	//主题促销组
	queryGroup:function(_siteId){
		bDialog.open({
			title : '主题促销',
			width : 800,
			height : 570,
			url : GLOBAL.WEBROOT + "/querygroup?siteId="+_siteId,
			callback:function(data){
				if(data && data.results && data.results[0]){
					$('#groupId').val(data.results[0].id);
					$('#groupName').val(data.results[0].promTheme);
				}
				
			}
		});
	},
	//黑名单商品
	queryBlackGds:function(_shopId,_siteId){
		bDialog.open({
			title : '黑名单商品选择',
			width : 800,
			height : 560,
			url : GLOBAL.WEBROOT + "/gdsprom/gdsblackselect?shopId="+_shopId+"&siteId="+_siteId,
			callback:function(data){
				   //确定 按钮_if_query==1
				   if(data && data.results && data.results[0]._if_query=='1'){
					   promInfo.initBlackGdsList(data.results[0].skuIds);
				   }
				  
			}
		});
	},
	//黑名单分类
	queryBlackCatg:function(_siteId){
		bDialog.open({
            title : '分类选择',
            width : 350,
            height : 550,
            url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=1",
            params:{multi : true,showRoot : false,siteId:_siteId},
            callback:function(data){
                     if(data && data.results && data.results.length > 0 ){
                    	   var _catgs = data.results[0].catgs;
                    	   var _cat=new Array();
                           for(var i in _catgs){
                        	   _cat.push(_catgs[i].catgCode+"-"+_catgs[i].catgName);
                           }
                           promInfo.initBlackCatgList(_cat);
                  }
            }
        });
	},
	//查询分类
	queryCatg:function(_siteId){ 
		// 弹出框关闭回调，返回 catgs数组：包括catgName,catgCode,catgLevel
		// 遍历取值代码为请参看callback代码段。
		// URL参数说明: catgType=1为平台分类  catgType=2 为店铺分类 传其他值则页面不显示
		bDialog.open({
		            title : '分类选择',
		            width : 350,
		            height : 550,
		            url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=1",
		            params:{multi : true,showRoot : false,siteId:_siteId},
		            callback:function(data){
		                     if(data && data.results && data.results.length > 0 ){
		                    	   var _catgs = data.results[0].catgs;
		                    	   var _cat=new Array();
		                           for(var i in _catgs){
		                        	   _cat.push(_catgs[i].catgCode+"-"+_catgs[i].catgName);
		                           }
		                           promInfo.initCatgList(_cat);
		                  }
		            }
		        });

	},
	//查询商品
	queryGds:function(_shopId,_siteId){
		bDialog.open({
			title : '商品选择',
			width : 800,
			height : 560,
			url : GLOBAL.WEBROOT + "/gdsprom/gdsselect?shopId="+_shopId+"&siteId="+_siteId,
			callback:function(data){
				   //确定 按钮_if_query==1
				   if(data && data.results && data.results[0]._if_query=='1'){
					   //编辑添加后 在初始化页面
					   if($('#doAction').val()=='edit'){
   					        //insert
						   promInfo.insertGdsList(data.results[0].skuIds);
   			           }else{
   			        	    promInfo.initGdsList(data.results[0].skuIds,true);
   			           }
				   }
			}
		});
	},
	//黑名单商品选择
   selectCheckBoxGdsBlack:function(e){
	   //选中 表示全选
	   if(e.currentTarget.checked){
		   $("#gdsBlackTable tbody tr td input[ name='checkboxgds']").prop('checked',true);
	   }else{
		   //全部取消
		   $("#gdsBlackTable tbody tr td input[ name='checkboxgds']").prop('checked',false);
	   }
   },
	//黑名单分类选择
   selectCheckBoxCatgBlack:function(e){
		   //选中 表示全选
		   if(e.currentTarget.checked){
			   $("#catgBlackTable tbody tr td input[ name='checkboxcatg']").prop('checked',true);
		   }else{
			   //全部取消
			   $("#catgBlackTable tbody tr td input[ name='checkboxcatg']").prop('checked',false);
		   }
   },
   //商品选择
   selectCheckBoxGds:function(e){
	   //选中 表示全选
	   if(e.currentTarget.checked){
		   $("#gdsTable tbody tr td input[ othername='checkboxgds']").prop('checked',true);
	   }else{
		   //全部取消
		   $("#gdsTable tbody tr td input[ othername='checkboxgds']").prop('checked',false);
	   }
   },
   //分类选择
   selectCheckBoxCatg:function(e){
	   //选中 表示全选
	   if(e.currentTarget.checked){
		   $("#catgTable tbody tr td input[ name='checkboxcatg']").prop('checked',true);
	   }else{
		   //全部取消
		   $("#catgTable tbody tr td input[ name='checkboxcatg']").prop('checked',false);
	   }
   },
   //商品黑名单批量删除
   delAllGdsBlack:function(e){

		  var _doAction=false;
		  var _l=$("#gdsBlackTable tbody tr td input[ name='checkboxgds']");
			 for(var i=0;i<_l.length;i++){
				 if(_l[i].checked){
					 _doAction=true;
					 break;
				 }
			 }
		      if(!_doAction){
		    	  eDialog.alert('请选择需要删除的数据。');
		    	  return;
		      }
		      
		eDialog.confirm("确定批量删除吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
	            		 var _l=$("#gdsBlackTable tbody tr td input[ name='checkboxgds']");
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
   },
   //商品黑名单删除
   delGdsBlack:function(e){
		 gdsBlackTr=$(e.currentTarget).parent().parent();
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
   },
   //商品批量删除
   delAllGds:function(e){

		  var _doAction=false;
		  var _l=$("#gdsTable tbody tr td input[ othername='checkboxgds']");
			 for(var i=0;i<_l.length;i++){
				 if(_l[i].checked){
					 _doAction=true;
					 break;
				 }
			 }
		      if(!_doAction){
		    	  eDialog.alert('请选择需要删除的数据。');
		    	  return;
		      }
		      
		eDialog.confirm("确定批量删除吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					     var _skuIds=new Array();
	            		 var _l=$("#gdsTable tbody tr td input[ othername='checkboxgds']");
	        			 var _tr=null;
	        			 for(var i=0;i<_l.length;i++){
	        				 if(_l[i].checked){
	        					 //选中 删除
	        					 _tr=$(_l[i]).parent().parent();
	        					 _skuIds.push($(_tr).attr("id"));
	        					 _tr.remove();
	        				 }
	        			 }
	        			 
	        		   if($('#doAction').val()=='edit'){
	    						var _promId=$('#promId').val();//促销编码
	    						//var _skuId=$(gdsTr).attr("id");//单品编码
	    						//编辑 需要删除数据库
	    						 promInfo.deleteGdsDb(_promId,_skuIds);
	    			   }
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
   },
   //商品删除
   delGds:function(e){
	    //gdsTr=e.currentTarget.parentNode.parentNode;
	    gdsTr=$(e.currentTarget).parent().parent();
		eDialog.confirm("确定删除吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					gdsTr.remove();
					if($('#doAction').val()=='edit'){
						var _promId=$('#promId').val();//促销编码
						var _skuId=$(gdsTr).attr("id");//单品编码
						//编辑 需要删除数据库
						 promInfo.deleteGdsDb(_promId,_skuId);
					}
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
   },
   //分类批量删除
   delAllCatg:function(e){

		  var _doAction=false;
		  var _l=$("#catgTable tbody tr td input[ name='checkboxcatg']");
			 for(var i=0;i<_l.length;i++){
				 if(_l[i].checked){
					 _doAction=true;
					 break;
				 }
			 }
		      if(!_doAction){
		    	  eDialog.alert('请选择需要删除的数据。');
		    	  return;
		      }
		      
		eDialog.confirm("确定批量删除吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
	            		 var _l=$("#catgTable tbody tr td input[ name='checkboxcatg']");
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
   },
   //分类删除
   delCatg:function(e){
	    //gdsTr=e.currentTarget.parentNode.parentNode;
		 catgTr=$(e.currentTarget).parent().parent();
			eDialog.confirm("确定删除吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						catgTr.remove();
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
   },
   //分类批量黑名单删除
   delAllCatgBlack:function(e){
	   
	   var _doAction=false;
	   var _l=$("#catgBlackTable tbody tr td input[ name='checkboxcatg']");
			 for(var i=0;i<_l.length;i++){
				 if(_l[i].checked){
					 _doAction=true;
					 break;
				 }
			 }
		      if(!_doAction){
		    	  eDialog.alert('请选择需要删除的数据。');
		    	  return;
		      }
		      
		eDialog.confirm("确定批量删除吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
	            		 var _l=$("#catgBlackTable tbody tr td input[ name='checkboxcatg']");
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
   },
   //分类黑名单删除
   delCatgBlack:function(e){
		 catgBlackTr=$(e.currentTarget).parent().parent();
			eDialog.confirm("确定删除吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						catgBlackTr.remove();
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
   },
    //促销分类列表
	initBlackCatgList:function(_catgIds){
		//编辑 进入 promId非空
		var _promId=$('#promId').val();
		//_skuIds 初始化页面为空  ，open页面回掉可非空
		if(!_catgIds){
			_catgIds=new Array();
		}
		//当前页面的列表数据 需要加入到_catgIds中
		 var _l=$("#catgBlackTable tbody tr td input[ name='checkboxcatg']");
		 if(_l && _l.length>0){
			 for(var i=0;i<_l.length;i++){
				 _catgIds.push(_l[i].parentNode.parentNode.id);
			 }
		 }
		 else{
			 //没有数据 清空
			 if(_catgIds && _catgIds.length==0){
				 _catgIds='';
			 }
		 }
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsprom/catgblackList",
			data:[{name:'promId',value:_promId},{name:'catgIds',value:_catgIds}],
			"dataType": "text",
			success : function(returnInfo) {
				$('#blackCatgTableId').empty();
				$('#blackCatgTableId').append(returnInfo);
				//查看
				if($('#doAction').val()=="view"){
				    $("#btn_catg_delblack_batch").hide();//批量删除按钮
				    $("a[ name='delGdsRow']").hide();//删除按钮
				}
			}
		});
	},
	//黑名单商品列表
	initBlackGdsList:function(_skuIdsBlack){
		//编辑 进入 promId非空
		var _promId=$('#promId').val();
		//_skuIds 初始化页面为空  ，open页面回掉可非空
		if(!_skuIdsBlack){
			_skuIdsBlack=new Array();
		}
		//当前页面的列表数据 需要加入到skuIds中
		 var _l=$("#gdsBlackTable tbody tr td input[ name='checkboxgds']");
		 if(_l && _l.length>0){
			 for(var i=0;i<_l.length;i++){
				 _skuIdsBlack.push(_l[i].parentNode.parentNode.id);
			 }
		 }
		 else{
			 //没有数据 清空
			 if(_skuIdsBlack && _skuIdsBlack.length==0){
				 _skuIdsBlack='';
			 }
		 }
		 
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsprom/gdsblackList?promId="+_promId+"&skuIds="+_skuIdsBlack,
			"dataType": "text",
			success : function(returnInfo) {
				$('#blackGdsTableId').empty();
				$('#blackGdsTableId').append(returnInfo);
				//查看
				if($('#doAction').val()=="view"){
				    $("#btn_gds_delblack_batch").hide();//批量删除按钮
				    $("a[ name='delGdsRow']").hide();//删除按钮
				    
				}
			}
		});
	},
	//促销分类列表
	initCatgList:function(_catgIds){
		//编辑 进入 promId非空
		var _promId=$('#promId').val();
		//_skuIds 初始化页面为空  ，open页面回掉可非空
		if(!_catgIds){
			_catgIds=new Array();
		}
		//当前页面的列表数据 需要加入到_catgIds中
		 var _l=$("#catgTable tbody tr td input[ name='checkboxcatg']");
		 if(_l && _l.length>0){
			 for(var i=0;i<_l.length;i++){
				 _catgIds.push(_l[i].parentNode.parentNode.id);
			 }
		 }
		 else{
			 //没有数据 清空
			 if(_catgIds && _catgIds.length==0){
				 _catgIds='';
			 }
		 }
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsprom/catgList",
			data:[{name:'promId',value:_promId},{name:'catgIds',value:_catgIds}],
			"dataType": "text",
			success : function(returnInfo) {
				$('#catgtableId').empty();
				$('#catgtableId').append(returnInfo);
				//查看
				if($('#doAction').val()=="view"){
				    $("#btn_catg_del_batch").hide();//批量删除按钮
				    $("a[ name='delGdsRow']").hide();//删除按钮
				}
			}
		});
	},
	//促销商品列表 ifmerge是否合并
	initGdsList:function(_skuIds,ifmerge){
		//编辑 进入 promId非空
		var _promId=$('#promId').val();
		//_skuIds 初始化页面为空  ，open页面回掉可非空
		if(!_skuIds){
			_skuIds=new Array();
		}
		//当前页面的列表数据 需要加入到skuIds中
		 var _l=$("#gdsTable tbody tr td input[ othername='checkboxgds']");
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
		 if(!ifmerge){
			 _skuIds='';
		 }
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsprom/gdsList",
			data:[{name:'promId',value:_promId},{name:'skuIds',value:_skuIds},{name:'promTypeCode',value:$('#promTypeCode').val()}],
			"dataType": "text",
			success : function(returnInfo) {
				$('#gdstableId').empty();
				$('#gdstableId').append(returnInfo);
				//查看
				if($('#doAction').val()=="view"){
				    $("#btn_gds_del_batch").hide();//批量删除按钮
				    $("a[ name='delGdsRow']").hide();//删除按钮
				    $(".stockInput").attr("readonly","readonly");//库存输入框
				    
				    //已选择商品表格
				    $("th[id='gdsTable_row_1']").hide();
				    $("td[name='gdsTableTbody_td_1']").hide();
				    $("th[id='gdsTable_row_12']").hide();
				    $("td[name='gdsTableTbody_td_12']").hide();
				    //已选择分类表格
					$("th[id='catgTable_row_1']").hide();
				    $("td[name='catgTableTbody_row_1']").hide();
					$("th[id='catgTable_row_4']").hide();
				    $("td[name='catgTableTbody_row_4']").hide(); 
				}
			}
		});
	},
	//参与范围 点击
    joinRangeClick:function(_obj){
	    if($(_obj).val()=='1'){
	    	  //黑名单 选择 启动
	          $("#ifBlackListLabel").show();
	          //隐藏商品选择 功能 和已选择列表
	          $(".gdsSelectDivClass").hide();
	          $("#catgSelectId").hide();
	          $("#gdsSelectId").hide();
	          
	          $("#catgBlackSelectId").show();
	          $("#gdsBlackSelectId").show();
	          
	    }else{
	    	  //清空选择
	    	  $('#ifBlackList').attr("checked",false);
	    	  //黑名单 不可用
	    	  $('#btnGdsBlackOpenWindow').attr('disabled',"true");
	    	  $('#btnCatgBlackOpenWindow').attr('disabled',"true");
	    	  $("#ifBlackListLabel").hide();
	    	  //展示商品选择 功能 和已选择列表
	          $(".gdsSelectDivClass").show();
	          $(".blackGdsTableClass").hide();
	          
              $("#catgBlackSelectId").hide();
	          $("#gdsBlackSelectId").hide();
	          $("#catgSelectId").show();
	          $("#gdsSelectId").show();
	    }
    },
    ifBlackList:function(_obj){
	   	 if($(_obj).prop('checked')==true){
		   	  //黑名单 选择 启动  黑名单查询按钮可选择
		   	  $('#btnGdsBlackOpenWindow').removeAttr("disabled"); 
		   	  $('#btnCatgBlackOpenWindow').removeAttr("disabled");
		   	  $(".blackGdsTableClass").show();
	   }else{
		   	  //黑名单 选择 启动  黑名单查询按钮不可选择
		   	 $('#btnGdsBlackOpenWindow').attr('disabled',"true"); 
		   	 $('#btnCatgBlackOpenWindow').attr('disabled',"true");
		   	 $(".blackGdsTableClass").hide();
	   }
    },
	//导入商品 弹出新页面
	importData:function(id,action){
		bDialog.open({
			title : '促销参与商品列表',
			width : 900,
			height : 560,
			url : GLOBAL.WEBROOT + "/gdsprom/importGds?promId="+id,
			params : {
			},
			onHidden:function(){
				//需要刷新 商品列表
				 promInfo.initGdsList(null,null);   
				//$("#dataGridTable").gridReload();
				 
			},
			callback:function(data){
				//$("#dataGridTable").gridReload();
			}
		});
	},
	//更多商品参与列表  弹出新页面
	manyGds:function(id,action){
		bDialog.open({
			title : '促销参与商品列表',
			width : 900,
			height : 500,
			url : GLOBAL.WEBROOT + "/gdsprom/manyGds?promId="+id+"&doAction="+action+"&promTypeCode="+$('#promTypeCode').val(),
		    params : {
			        'promId' : id,
			         'doAction':action,
			         'promTypeCode':$('#promTypeCode').val()
			    },
			onHidden:function(){
				//需要刷新 商品列表
				// promInfo.initGdsList(null,null);   
			},
			callback:function(data){
				//$("#dataGridTable").gridReload();
				//需要刷新 商品列表
				 promInfo.initGdsList(null,null);   
			}
		});
	},
	//ajax请求 删除商品
	deleteGdsDb:function(_promId,skuIds){
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsprom/batchDel",
			data:[{name:'promId',value:_promId},{name:'skuIds',value:skuIds}],
			"dataType": "json",
			success : function(returnInfo) {
 				//成功返回
 				if(returnInfo.resultFlag=='ok'){
 					 promInfo.initGdsList(null, null);
 				}else{
 					eDialog.alert(returnInfo.resultMsg,null);
 				}
 			},
 			exception : function(returnInfo) {
 				eDialog.alert(returnInfo.resultMsg,null);
 			}
	    });
	},
	//ajax请求 变更数量
	updateGdsPromCntDb:function($obj,skuIds,promCnt){
		var _promId=$('#promId').val();
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsprom/updateCnt",
			data:[{name:'promId',value:_promId},{name:'skuId',value:skuIds},{name:'promCnt',value:promCnt}],
			"dataType": "json",
			success : function(returnInfo) {
 				//成功返回
 				if(returnInfo.resultFlag=='ok'){
 					 //更新 当前对象oldvalue属性
 					$obj.attr("oldvalue",promCnt);
 				}else{
 					eDialog.alert(returnInfo.resultMsg,null);
 				}
 			},
 			exception : function(returnInfo) {
 				eDialog.alert(returnInfo.resultMsg,null);
 			}
	    });
	},
	//促销商品 添加保存
	insertGdsList:function(_skuIds){
		//编辑 进入 promId非空
		var _promId=$('#promId').val();
		//_skuIds 初始化页面为空  ，open页面回掉可非空
		if(!_skuIds){
			_skuIds=new Array();
		}
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsprom/insertGds",
			data:[{name:'promId',value:_promId},{name:'skuIds',value:_skuIds}],
			"dataType": "json",
			success : function(returnInfo) {
 				//成功返回
 				if(returnInfo.resultFlag=='ok'){
 					 promInfo.initGdsList(null,null);
 				}else{
 					eDialog.alert(returnInfo.resultMsg,null);
 				}
 			},
 			exception : function(returnInfo) {
 				eDialog.alert(returnInfo.resultMsg,null);
 			}
		});
	},
	//站点编号 需要清空当前分类信息
	onchangeSiteId:function(_obj){
		
		//查看不处理
		if($('#doAction').val()=='view'){
			return;
		}
		//分类信息
		 var _l=$("#catgTable tbody tr td input[ name='checkboxcatg']");
		 var _tr=null;
		 for(var i=0;i<_l.length;i++){
				 _tr=$(_l[i]).parent().parent();
				 _tr.remove();
		 }
       //黑名单分类信息
		 var _l=$("#catgBlackTable tbody tr td input[ name='checkboxcatg']");
		 var _tr=null;
		 for(var i=0;i<_l.length;i++){
				 _tr=$(_l[i]).parent().parent();
				 _tr.remove();
		 }

	}
}
