$(function(){
	 //品类选择事件
	 $("#btnCatg110").click(function(){
		     var _sid=$('#siteId').val();
		     rule110.queryCatg110(_sid);
		 });
	 //商品选择事件
	 $("#coupGds110").click(function(){
		     var _shopId=$('#shopId').val();
		     var _sid=$('#siteId').val();
		     rule110.queryGds(_shopId,_sid);
		 });
	 //单品选择事件
	 $("#coupSku110").click(function(){
		     var _shopId=$('#shopId').val();
		     var _sid=$('#siteId').val();
		     rule110.querySku(_shopId,_sid);
		 });
	 //分类checkbox事件
	 $("#coup110Table thead tr input[ id='dt_row_all_check']").live('click',function(e){
		 rule110.selectCheckBox(e);
	 });
	 //商品checkbox事件
	 $("#gdsTable thead tr input[ id='dt_row_all_check']").live('click',function(e){
		 rule110.selectGdsCheckBox(e);
	 });
	 //单品品checkbox事件
	 $("#skuTable thead tr input[ id='dt_row_all_check']").live('click',function(e){
		 rule110.selectSkuCheckBox(e);
	 });
	 //批量删除
	 $("#btn110AllDel").live('click',function(e){
		 rule110.btn110AllDel();
	 });
	 //商品批量删除
	 $("#btn_gds_del_batch").live('click',function(e){
		 rule110.btnGdsDelAll();
	 });
	 //单品批量删除
	 $("#btn_sku_del_batch").live('click',function(e){
		 rule110.btnSkuDelAll();
	 });
     //分类删除全局变量 删除按钮
	 var coupInfoTr;
	 $("#coup110Table tr td a[ name='delRow']").live('click',function(e){
		 rule110.deleteCatg(e);
	 });
	//商品删除 全局变量 删除按钮
	 var gdsTr;
	 $("#gdsTable tr td a[ name='delRow']").live('click',function(e){
		 rule110.deleteGds(e);
	 });
	//单品删除 全局变量 删除按钮
	 var skuTr;
	 $("#skuTable tr td a[ name='delRow']").live('click',function(e){
		 rule110.deleteSku(e);
	 });
});

var rule110={
	//批量删除事件
   btn110AllDel:function(){
	   
	   var _doAction=false;
	   var _l=$("#coup110Table tbody tr td input[ name='checkboxcoup110']");
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
	            		 var _l=$("#coup110Table tbody tr td input[ name='checkboxcoup110']");
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
    //商品批量删除
    btnGdsDelAll:function(){
 	   var _doAction=false;
 	   var _l=$("#gdsTable tbody tr td input[ name='checkboxgds']");
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
 	            		 var _l=$("#gdsTable tbody tr td input[ name='checkboxgds']");
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
  //单品批量删除
    btnSkuDelAll:function(){
 	   var _doAction=false;
 	   var _l=$("#skuTable tbody tr td input[ name='checkboxsku']");
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
 					     var _l=$("#skuTable tbody tr td input[ name='checkboxsku']");
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
	//分类删除事件
    deleteCatg:function(e){
    	coupInfoTr=$(e.currentTarget).parent().parent();
			eDialog.confirm("确定删除吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						coupInfoTr.remove();
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
     },
   //商品删除事件
     deleteGds:function(e){
    	 gdsTr=$(e.currentTarget).parent().parent();
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
      },
    //单品删除事件
      deleteSku:function(e){
    	  skuTr=$(e.currentTarget).parent().parent();
  			eDialog.confirm("确定删除吗？", {
  				buttons : [{
  					caption : '确认',
  					callback : function(){
  						skuTr.remove();
  					}
  				},{
  					caption : '取消',
  					callback : $.noop
  				}]
  			});
       },
	//品类checkbox选择
    selectCheckBox:function(e){
 	   //选中 表示全选
 	   if(e.currentTarget.checked){
 		   $("#coup110Table tbody tr td input[ name='checkboxcoup110']").prop('checked',true);
 	   }else{
 		   //全部取消
 		   $("#coup110Table tbody tr td input[ name='checkboxcoup110']").prop('checked',false);
 	   }
    },
	//商品checkbox选择
    selectGdsCheckBox:function(e){
 	   //选中 表示全选
 	   if(e.currentTarget.checked){
 		   $("#gdsTable tbody tr td input[ name='checkboxgds']").prop('checked',true);
 	   }else{
 		   //全部取消
 		   $("#gdsTable tbody tr td input[ name='checkboxgds']").prop('checked',false);
 	   }
    },
  //单品checkbox选择
    selectSkuCheckBox:function(e){
 	   //选中 表示全选
 	   if(e.currentTarget.checked){
 		   $("#skuTable tbody tr td input[ name='checkboxsku']").prop('checked',true);
 	   }else{
 		   //全部取消
 		   $("#skuTable tbody tr td input[ name='checkboxsku']").prop('checked',false);
 	   }
    },
	//查询优惠券
    queryCatg110:function(_sid){ 
    	 
		// 弹出框关闭回调，返回 catgs数组：包括catgName,catgCode,catgLevel
		// 遍历取值代码为请参看callback代码段。
		// URL参数说明: catgType=1为平台分类  catgType=2 为店铺分类 传其他值则页面不显示
		bDialog.open({
		            title : '分类选择',
		            width : 350,
		            height : 550,
		            url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=1",
		            params:{multi : true,showRoot : false,siteId:_sid},
		            callback:function(data){
		                     if(data && data.results && data.results.length > 0 ){
		                    	   var _catgs = data.results[0].catgs;
		                    	   var _cat=new Array();
		                           for(var i in _catgs){
		                        	   _cat.push(_catgs[i].catgCode+"-"+_catgs[i].catgName);
		                           }
		                           rule110.initCatgList(_cat);
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
		 var _l=$("#coup110Table tbody tr td input[ name='checkboxcoup110']");
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
			url : GLOBAL.WEBROOT + "/coupcatg/catgList",
			data:[{name:'coupId',value:_id},{name:'catgIds',value:_catgIds}],
			"dataType": "text",
			success : function(returnInfo) {
				$('#catg-table-id-110').empty();
				$('#catg-table-id-110').append(returnInfo);
				var hide=false;
				if(returnInfo.trim()==""){
					hide=true;
				}
				rule110.viewHide(hide,$('#catg-table-id-110'));
			}
		});
	},
	//查看隐藏 控件
	viewHide:function(hide,obj){
		if($('#doAction').val()=="view"){
		    $(".delRowTd").hide();//删除按钮 列隐藏
		    if(hide && obj){
		    	obj.parent().parent().hide();
		    }
		}
	},
	//查询商品
	queryGds:function(_shopId,_sid){
		bDialog.open({
			title : '商品选择',
			width : 800,
			height : 550,
			url : GLOBAL.WEBROOT + "/coupgds/gdsselect?shopId="+_shopId+"&siteId="+_sid,
			callback:function(data){
				   //确定 按钮_if_query==1
				   if(data && data.results && data.results[0]._if_query=='1'){
					   rule110.initGdsList(data.results[0].gdsIds);
				   }
			}
		});
	},
	//查询单品
	querySku:function(_shopId,_sid){
		bDialog.open({
			title : '单品选择',
			width : 800,
			height : 550,
			url : GLOBAL.WEBROOT + "/coupsku/skuselect?shopId="+_shopId+"&siteId="+_sid,
			callback:function(data){
				   //确定 按钮_if_query==1
				   if(data && data.results && data.results[0]._if_query=='1'){
					   rule110.initSkuList(data.results[0].skuIds);
				   }
			}
		});
	},
	//商品展示
	initGdsList:function(_gdsIds){
		//编辑 进入 coupId非空
		var _id=$('#id').val();
		//_gdsIds 初始化页面为空  ，open页面回掉可非空
		if(!_gdsIds){
			_gdsIds=new Array();
		}
		//当前页面的列表数据 需要加入到skuIds中
		 var _l=$("#gdsTable tbody tr td input[ name='checkboxgds']");
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
			url : GLOBAL.WEBROOT + "/coupgds/gdsList",
			data:[{name:'coupId',value:_id},{name:'gdsIds',value:_gdsIds}],
			"dataType": "text",
			success : function(returnInfo) {
				$('#gds-table-id-110').empty();
				$('#gds-table-id-110').append(returnInfo);
				var hide=false;
				if(returnInfo.trim()==""){
					hide=true;
				}
				rule110.viewHide(hide,$('#gds-table-id-110'));
			}
		});
	},
	//单品展示
	initSkuList:function(_skuIds){
		//编辑 进入 coupId非空
		var _id=$('#id').val();
		//_skuIds 初始化页面为空  ，open页面回掉可非空
		if(!_skuIds){
			_skuIds=new Array();
		}
		//当前页面的列表数据 需要加入到skuIds中
		 var _l=$("#skuTable tbody tr td input[ name='checkboxsku']");
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
			url : GLOBAL.WEBROOT + "/coupsku/skuList",
			data:[{name:'coupId',value:_id},{name:'skuIds',value:_skuIds}],
			"dataType": "text",
			success : function(returnInfo) {
				$('#sku-table-id-110').empty();
				$('#sku-table-id-110').append(returnInfo);
				var hide=false;
				if(returnInfo.trim()==""){
					hide=true;
				}
				rule110.viewHide(hide,$('#sku-table-id-110'));
			}
		});
	},
	//查询数据
	qryData:function(){
		rule110.initCatgList();
		rule110.initGdsList();
		rule110.initSkuList();
	},
	//验证
	check:function(){
		 return true;
	},
	//提醒信息
	getTips:function(){
		  var data=new Array();
		  var _c=true;
		  //验证是否有设置数据    ，如有设置验证通过，不需要提醒
		    //分类列表数据
		  var _l=$("#coup110Table tbody tr td input[ name='checkboxcoup110']");
		  if(_l && _l.length>0){
			  _c=false;
		  }
		  //商品列表数据
			 var _l=$("#gdsTable tbody tr td input[ name='checkboxgds']");
			 if(_l && _l.length>0){
				 _c=false;
			 }
			  //单品列表数据
	       var _l=$("#skuTable tbody tr td input[ name='checkboxsku']");
	       if(_l && _l.length>0){
	      	 _c=false;
	       }
	       if(_c){
	      	 data.push("品类限制");
	       }
       return data;
	},
	//保存
	getData:function(){
		     var data=new Array();
		        //单品列表数据
	         var _l=$("#skuTable tbody tr td input[ name='checkboxsku']");
	         if(_l && _l.length>0){
				 for(var i=0;i<_l.length;i++){
					 data.push({'name':'useMap['+_l[i].id+']','value':$(_l[i]).attr("skuid")});
				 }
	         }
			    //商品列表数据
			 var _l=$("#gdsTable tbody tr td input[ name='checkboxgds']");
			 if(_l && _l.length>0){
				 for(var i=0;i<_l.length;i++){
					 data.push({'name':'useMap['+_l[i].id+']','value':$(_l[i]).attr("gdsid")});
				 }
			 }
			    //分类列表数据
			  var _l=$("#coup110Table tbody tr td input[ name='checkboxcoup110']");
			  if(_l && _l.length>0){
				 for(var i=0;i<_l.length;i++){
					 data.push({'name':'useMap['+_l[i].id+']','value':$(_l[i]).attr("catgcode")});
				 }
			  }
			  return data;
	}
}
