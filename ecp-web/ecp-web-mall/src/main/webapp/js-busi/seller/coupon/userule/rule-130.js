$(function() {

	//页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			 //品类黑名单选择事件
			 $("#btnCatg130").click(function(){
				     var _sid=$('#siteId').val();
				     var _shopId=$('#shopId').val();
				     rule130.queryCatg130(_sid,_shopId);
				 });
			 //商品黑名单选择事件
			 $("#coupGds130").click(function(){
				     var _shopId=$('#shopId').val();
				     var _sid=$('#siteId').val();
				     rule130.queryGds(_shopId,_sid);
				 });
			 //单品黑名单选择事件
			 $("#coupSku130").click(function(){
				     var _shopId=$('#shopId').val();
				     var _sid=$('#siteId').val();
				     rule130.querySku(_shopId,_sid);
				 });
			 //分类黑名单checkbox事件
			 $("#coup130Table thead tr input[ id='dt_row_all_check']").live('click',function(e){
				 rule130.selectCheckBox(e);
			 });
			 //商品黑名单checkbox事件
			 $("#gdsBlackTable thead tr input[ id='dt_row_all_check']").live('click',function(e){
				 rule130.selectGdsCheckBox(e);
			 });
			 //单品黑名单checkbox事件
			 $("#skuBlackTable thead tr input[ id='dt_row_all_check']").live('click',function(e){
				 rule130.selectSkuCheckBox(e);
			 });
			 //批量删除
			 $("#btn130AllDel").live('click',function(e){
				 rule130.btn130AllDel();
			 });
			 //商品黑名单批量删除
			 $("#btn_blackgds_del_batch").live('click',function(e){
				 rule130.btnGdsDelAll();
			 });
			 //单品黑名单批量删除
			 $("#btn_blacksku_del_batch").live('click',function(e){
				 rule130.btnSkuDelAll();
			 });
		     //分类黑名单删除全局变量 删除按钮
			 var coupInfo130Tr;
			 $("#coup130Table tr td a[ name='delRow']").live('click',function(e){
				 rule130.deleteCatg(e);
			 });
			//商品黑名单删除 全局变量 删除按钮
			 var gds130Tr;
			 $("#gdsBlackTable tr td a[ name='delRow']").live('click',function(e){
				 rule130.deleteGds(e);
			 });
			//单品黑名单删除 全局变量 删除按钮
			 var sku130Tr;
			 $("#skuBlackTable tr td a[ name='delRow']").live('click',function(e){
				 rule130.deleteSku(e);
			 });

		};

		return {
			init : init
		};
	};


	pageConfig.config({
		//指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm','bPage' ],
		//指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});

});


var rule130={
	//批量黑名单删除事件
   btn130AllDel:function(){
	   
	   var _doAction=false;
	   var _l=$("#coup130Table tbody tr td input[ name='checkboxcoup130']");
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
	            		 var _l=$("#coup130Table tbody tr td input[ name='checkboxcoup130']");
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
    //商品黑名单批量删除
    btnGdsDelAll:function(){
 	   var _doAction=false;
 	   var _l=$("#gdsBlackTable tbody tr td input[ name='checkboxblackgds']");
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
 	            		 var _l=$("#gdsBlackTable tbody tr td input[ name='checkboxblackgds']");
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
  //单品黑名单批量删除
    btnSkuDelAll:function(){
 	   var _doAction=false;
 	   var _l=$("#skuBlackTable tbody tr td input[ name='checkboxblacksku']");
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
 					     var _l=$("#skuBlackTable tbody tr td input[ name='checkboxblacksku']");
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
	//分类黑名单删除事件
    deleteCatg:function(e){
    	coupInfo130Tr=$(e.currentTarget).parent().parent();
			eDialog.confirm("确定删除吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						coupInfo130Tr.remove();
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
     },
   //商品黑名单删除事件
     deleteGds:function(e){
    	 gds130Tr=$(e.currentTarget).parent().parent();
 			eDialog.confirm("确定删除吗？", {
 				buttons : [{
 					caption : '确认',
 					callback : function(){
 						gds130Tr.remove();
 					}
 				},{
 					caption : '取消',
 					callback : $.noop
 				}]
 			});
      },
    //单品黑名单删除事件
      deleteSku:function(e){
    	  sku130Tr=$(e.currentTarget).parent().parent();
  			eDialog.confirm("确定删除吗？", {
  				buttons : [{
  					caption : '确认',
  					callback : function(){
  						sku130Tr.remove();
  					}
  				},{
  					caption : '取消',
  					callback : $.noop
  				}]
  			});
       },
	//品类黑名单checkbox选择
    selectCheckBox:function(e){
 	   //选中 表示全选
 	   if(e.currentTarget.checked){
 		   $("#coup130Table tbody tr td input[ name='checkboxcoup130']").prop('checked',true);
 	   }else{
 		   //全部取消
 		   $("#coup130Table tbody tr td input[ name='checkboxcoup130']").prop('checked',false);
 	   }
    },
	//商品黑名单checkbox选择
    selectGdsCheckBox:function(e){
 	   //选中 表示全选
 	   if(e.currentTarget.checked){
 		   $("#gdsBlackTable tbody tr td input[ name='checkboxblackgds']").prop('checked',true);
 	   }else{
 		   //全部取消
 		   $("#gdsBlackTable tbody tr td input[ name='checkboxblackgds']").prop('checked',false);
 	   }
    },
  //单品黑名单checkbox选择
    selectSkuCheckBox:function(e){
 	   //选中 表示全选
 	   if(e.currentTarget.checked){
 		   $("#skuBlackTable tbody tr td input[ name='checkboxblacksku']").prop('checked',true);
 	   }else{
 		   //全部取消
 		   $("#skuBlackTable tbody tr td input[ name='checkboxblacksku']").prop('checked',false);
 	   }
    },
	//查询分类
    queryCatg130:function(_sid,_shopId){ 
    	 
		// 弹出框关闭回调，返回 catgs数组：包括catgName,catgCode,catgLevel
		// 遍历取值代码为请参看callback代码段。
		// URL参数说明: catgType=1为平台分类  catgType=2 为店铺分类 传其他值则页面不显示
		bDialog.open({
		            title : '分类选择',
		            width : 350,
		            height : 550,
		            url : GLOBAL.WEBROOT+"/seller/goods/category/open/catgselect?catgType=1",
		            params:{multi : true,showRoot : false,siteId:_sid,shopId:_shopId},
		            callback:function(data){
		                     if(data && data.results && data.results.length > 0 ){
		                    	   var _catgs = data.results[0].catgs;
		                    	   var _cat=new Array();
		                           for(var i in _catgs){
		                        	   _cat.push(_catgs[i].catgCode+"-"+_catgs[i].catgName);
		                           }
		                           rule130.initCatgList(_cat);
		                  }
		            }
		        });
    },
  //促销分类列表
	initCatgList:function(_catgIds){
		//编辑 进入 coupId非空
		var _id=$('#id').val();
		// _catgIds初始化页面为空  ，open页面回掉可非空
		if(!_catgIds){
			_catgIds=new Array();
		}
		//当前页面的列表数据 需要加入到_catgIds中
		 var _l=$("#coup130Table tbody tr td input[ name='checkboxcoup130']");
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
			url : GLOBAL.WEBROOT + "/seller/coupcatg/catgblackList",
			data:[{name:'coupId',value:_id},{name:'catgIds',value:_catgIds}],
			"dataType": "text",
			success : function(returnInfo) {
				$('#catg-table-id-130').empty();
				$('#catg-table-id-130').append(returnInfo);
				var hide=false;
				if(returnInfo.trim()==""){
					hide=true;
				}
				rule130.viewHide(hide,$('#catg-table-id-130'));
			}
		});
	},
	//查询商品黑名单
	queryGds:function(_shopId,_sid){
		bDialog.open({
			title : '商品选择',
			width : 800,
			height : 550,
			url : GLOBAL.WEBROOT + "/seller/coupgds/gdsselect?shopId="+_shopId+"&siteId="+_sid,
			callback:function(data){
				   //确定 按钮_if_query==1
				   if(data && data.results && data.results[0]._if_query=='1'){
					   rule130.initGdsList(data.results[0].gdsIds);
				   }
			}
		});
	},
	//查询单品黑名单
	querySku:function(_shopId,_sid){
		bDialog.open({
			title : '单品选择',
			width : 800,
			height : 550,
			url : GLOBAL.WEBROOT + "/seller/coupsku/skuselect?shopId="+_shopId+"&siteId="+_sid,
			callback:function(data){
				   //确定 按钮_if_query==1
				   if(data && data.results && data.results[0]._if_query=='1'){
					   rule130.initSkuList(data.results[0].skuIds);
				   }
			}
		});
	},
	//商品黑名单展示
	initGdsList:function(_gdsIds){
		//编辑 进入 coupId非空
		var _id=$('#id').val();
		//_gdsIds 初始化页面为空  ，open页面回掉可非空
		if(!_gdsIds){
			_gdsIds=new Array();
		}
		//当前页面的列表数据 需要加入到skuIds中
		 var _l=$("#gdsBlackTable tbody tr td input[ name='checkboxblackgds']");
		 if(_l && _l.length>0){
			 for(var i=0;i<_l.length;i++){
				 _gdsIds.push(_l[i].parentNode.parentNode.id);
			 }
		 }
		 else{
			 //没有数据 清空
			 if(_gdsIds && _gdsIds.length==0){
				 _gdsIds='';
			 }
		 }
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/seller/coupgds/gdsblackList",
			data:[{name:'coupId',value:_id},{name:'gdsIds',value:_gdsIds}],
			"dataType": "text",
			success : function(returnInfo) {
				$('#gds-table-id-130').empty();
				$('#gds-table-id-130').append(returnInfo);
				var hide=false;
				if(returnInfo.trim()==""){
					hide=true;
				}
				rule130.viewHide(hide,$('#gds-table-id-130'));
			}
		});
	},
	//单品黑名单展示
	initSkuList:function(_skuIds){
		//编辑 进入 coupId非空
		var _id=$('#id').val();
		//_skuIds 初始化页面为空  ，open页面回掉可非空
		if(!_skuIds){
			_skuIds=new Array();
		}
		//当前页面的列表数据 需要加入到skuIds中
		 var _l=$("#skuBlackTable tbody tr td input[ name='checkboxblacksku']");
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
			url : GLOBAL.WEBROOT + "/seller/coupsku/skublackList",
			data:[{name:'coupId',value:_id},{name:'skuIds',value:_skuIds}],
			"dataType": "text",
			success : function(returnInfo) {
				$('#sku-table-id-130').empty();
				$('#sku-table-id-130').append(returnInfo);
				var hide=false;
				if(returnInfo.trim()==""){
					hide=true;
				}
				rule130.viewHide(hide,$('#sku-table-id-130'));
			}
		});
	},
	//查看隐藏 控件
	viewHide:function(hide,obj){
			if($('#doAction').val()=="view"){
			    $(".delRowTd").hide();//删除按钮 列隐藏
			    if(hide && obj){
			    	obj.parent().parent().parent().hide();
			    }
			}
		},
	//查询数据
	qryData:function(){
		rule130.initCatgList();
		rule130.initGdsList();
		rule130.initSkuList();
	},
	//检查
	check:function(){
		return true;
	},
	//提醒信息
	getTips:function(){
		 var data=new Array();
		 //验证是否有设置数据 ，如有设置验证通过，不需要提醒   
		 var _c=true;
		 //分类列表数据
		 var _l=$("#coup130Table tbody tr td input[ name='checkboxcoup130']");
		 if(_l && _l.length>0){
			 _c=false;
		 }   
		  //商品列表数据
		 var _l=$("#gdsBlackTable tbody tr td input[ name='checkboxblackgds']");
		 if(_l && _l.length>0){
			 _c=false;
		 }
		 //单品列表数据
		 var _l=$("#skuBlackTable tbody tr td input[ name='checkboxblacksku']");
		 if(_l && _l.length>0){
			 _c=false;
		 }
		 if(_c){
			 data.push("黑名单限制");
		 }
		 return data;
	},
	//保存
	getData:function(){
	     var data=new Array();
	        //单品列表数据
		 var _l=$("#skuBlackTable tbody tr td input[ name='checkboxblacksku']");
		 if(_l && _l.length>0){
			 for(var i=0;i<_l.length;i++){
				 data.push({'name':'useBlackMap['+_l[i].id+']','value':$(_l[i]).attr("skuid")});
			 }
		 }
		    //商品列表数据
		 var _l=$("#gdsBlackTable tbody tr td input[ name='checkboxblackgds']");
		 if(_l && _l.length>0){
			 for(var i=0;i<_l.length;i++){
				 data.push({'name':'useBlackMap['+_l[i].id+']','value':$(_l[i]).attr("gdsid")});
			 }
		 }
		    //分类列表数据
		 var _l=$("#coup130Table tbody tr td input[ name='checkboxcoup130']");
		 if(_l && _l.length>0){
			 for(var i=0;i<_l.length;i++){
				 data.push({'name':'useBlackMap['+_l[i].id+']','value':$(_l[i]).attr("catgcode")});
			 }
		 }
		  return data;
	}
}
