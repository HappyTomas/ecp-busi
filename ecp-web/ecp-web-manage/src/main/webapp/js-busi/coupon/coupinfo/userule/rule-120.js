$(function(){
	 //店铺批量删除
	 $("#btn_shop_del_batch").live('click',function(e){
		 rule120.btnShopDelAll();
	 });
	//店铺删除 全局变量 删除按钮
	 var shopTr;
	 $("#shopTable tr td a[ name='delRow']").live('click',function(e){
		 rule120.deleteShop(e);
	 });
	 //店铺查询
	$('#btnShopQuery').click(function(){
			rule120.qryShopGrid();
		});
	 //店铺checkbox事件
	 $("#shopTable thead tr input[ id='dt_row_all_check']").live('click',function(e){
		 rule120.selectShopCheckBox(e);
	 });
});

var rule120={ 
		qryShopGrid:function(){
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
					  rule120.initShopList(_shopIds);
				}
			 }
			});
		},
		  //店铺checkbox选择
	    selectShopCheckBox:function(e){
	 	   //选中 表示全选
	 	   if(e.currentTarget.checked){
	 		   $("#shopTable tbody tr td input[ name='checkboxshop']").prop('checked',true);
	 	   }else{
	 		   //全部取消
	 		   $("#shopTable tbody tr td input[ name='checkboxshop']").prop('checked',false);
	 	   }
	    },
		//单品批量删除
	    btnShopDelAll:function(){
	 	   var _doAction=false;
	 	   var _l=$("#shopTable tbody tr td input[ name='checkboxshop']");
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
	 					     var _l=$("#shopTable tbody tr td input[ name='checkboxshop']");
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
	    //店铺删除事件
	   deleteShop:function(e){
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
	       },
		 //商品checkbox选择
	    selectShopCheckBox:function(e){
	 	   //选中 表示全选
	 	   if(e.currentTarget.checked){
	 		   $("#shopTable tbody tr td input[ name='checkboxshop']").prop('checked',true);
	 	   }else{
	 		   //全部取消
	 		   $("#shopTable tbody tr td input[ name='checkboxshop']").prop('checked',false);
	 	   }
	    },
	 //店铺列表
	initShopList:function(_shopIds){
			//编辑 进入 coupId非空
			var _id=$('#id').val();
			// _shopIds初始化页面为空  ，open页面回掉可非空
			if(!_shopIds){
				_shopIds=new Array();
			}
			//当前页面的列表数据 需要加入到_catgIds中
			 var _l=$("#shopTable tbody tr td input[ name='checkboxshop']");
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
				url : GLOBAL.WEBROOT + "/coupshop/shopList",
				data:[{name:'coupId',value:_id},{name:'shopIds',value:_shopIds}],
				"dataType": "text",
				success : function(returnInfo) {
					$('#shop-table-id-120').empty();
					$('#shop-table-id-120').append(returnInfo);
					var hide=false;
					if(returnInfo.trim()==""){
						hide=true;
					}
					rule120.viewHide(hide,$('#useForm120'));
				}
			});
		},
		//查看隐藏 控件
	viewHide:function(hide,obj){
			if($('#doAction').val()=="view"){
			    $(".delRowTd").hide();//删除按钮 列隐藏
			    if(hide && obj){
			    	obj.hide();
			    }
			}
		},
		//查询数据
   qryData:function(){
		rule120.initShopList();
	},
	//检查
	check:function(){
		 return true;
	},
	//提醒信息
	getTips:function(){
		  var data=new Array();
	      //店铺列表数据
		  var _l=$("#shopTable tbody tr td input[ name='checkboxshop']");
		  if(_l && _l.length>0){
			  
		  }else{
			  data.push("店铺限制");
		  }
		  return data;
	},
	//保存
	getData:function(){
		 var data=new Array();
	    //店铺列表数据
		  var _l=$("#shopTable tbody tr td input[ name='checkboxshop']");
		  if(_l && _l.length>0){
			 for(var i=0;i<_l.length;i++){
				 data.push({'name':'useMap['+_l[i].id+']','value':$(_l[i]).attr("shopid")});
			 }
		  }
		  return data;
	}
}
