$(function(){
	
	//全局变量
	 var matchGdsTr;
	 //商品删除按钮功能
	 $("#matchTable tr td a[ name='delGdsRow']").live('click',function(e){
		    matchGdsTr=$(e.currentTarget).parent().parent();
			eDialog.confirm("确定删除吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						matchGdsTr.remove();
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		 });
	 
		 //批量删除功能 
	 $("#btn_match_del_batch").live('click',function(e){
		 

		  var _doAction=false;
		  var _l=$("#matchTable tbody tr td input[ name='checkboxgds']");
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
		            		 var _l=$("#matchTable tbody tr td input[ name='checkboxgds']");
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
	  
	 //checkbox搭配商品选择事件
	 $("#matchTable thead tr input[ id='dt_row_all_check']").live('click',function(e){
		   //选中 表示全选
		   if(e.currentTarget.checked){
			   $("#matchTable tbody tr td input[ name='checkboxgds']").prop('checked',true);
		   }else{
			   //全部取消
			   $("#matchTable tbody tr td input[ name='checkboxgds']").prop('checked',false);
		   }
	  });
	  
	    //搭配商品选择 window
	 $("#btnMatchGdsSelect").live('click',function(e){
		//$('#btnMatchGdsSelect').unbind('click').click(function(){
		 var _siteId=$('#siteId').val();
			bDialog.open({
				title : '商品选择',
				width : 800,
				height : 550,
				url : GLOBAL.WEBROOT + "/gdsprom/gdsselect?shopId="+$('#shopId').val()+"&siteId="+_siteId,
				callback:function(data){
					if(data && data.results && data.results[0]){
						discountRuleFun.initList(data.results[0].skuIds);
					}
					
				}
			});
		});
	     
});

//促销类型1000000013 自由搭配 js包装
var discountRuleFun={
		
		  //判断是否为数字
	       isNum:function(s)
	        {
	            if (s!=null && s!="")
	            {
	                return !isNaN(s);
	            }
	            return false;
	        },
	        checkPriceNum:function(s){
	        	if(discountRuleFun.isNum(s)){
	        		var decimal = /^\d+(\.\d{1,2})?$/;
	    			return decimal.test(s)|| s=="";
	        	}
	        	return false;
	        },
	      //保存 提交验证
		 valid:function(){
			    var _reData=new Object();
			        _reData.value=false;//返回默认 失败
			        
			    var data=new Array();
			    //促销类型为自由搭配时，搭配必需选择
			    var _l=$("#matchTable tbody tr td input[ name='checkboxgds']");
			    if(_l.length<=1){
			  		eDialog.alert('请设置搭配商品2个及以上',null);
			  		 _reData.value=false;//返回默认 失败
				  		return _reData;
			    }else{
			    	//搭配商品 设置数量
			  	  var _promCnt=null;
			  	  var _isNeedStock=null;
			  	  var _price=null;
			  	  var _sort=null;
			  	  for(var i=0;i<_l.length;i++){
			  		  //验证是否为有效数字
		    		  _promCnt=$('#promCnt-'+_l[i].id).val();//搭配商品总数量取值
		    		  _price=$('#price-'+_l[i].id).val();//单价
		    		  
		    		  _isNeedStock=$('#promCnt-'+_l[i].id).attr("isNeedStock");
	 		    		 //实体需要验证库存
		    		  if(_isNeedStock=="true"){
	 		    			  if(!this.isNum(_promCnt)){
	 		    				    eDialog.alert('优惠规则，商品编码-单品编码：'+_l[i].id +" 对应的<span style='color:red'>搭配总量</span>，请输入有效的数字  "+_promCnt+" ！",null);
	 			    			    _reData.value=false;//返回默认 失败
	 						  		return _reData;
		 		    		  }  
	 		    		  }else{
	 		    			  //虚拟不需要验证
	 		    			 _promCnt=0;
	 		    		  }
		    		  if(!this.checkPriceNum(_price)){
		    			    eDialog.alert('优惠规则，商品编码-单品编码：'+_l[i].id +" 对应的<span style='color:red'>单价</span>，请输入有效的数字(最多保留2位小数) "+_price+" ！",null);
		    			    _reData.value=false;//返回默认 失败
					  		return _reData;
		    		  }
		    		  
		    		  _sort=$('#sort-'+_l[i].id).val();//排序
		    		  if( !ebcCheck.isInt(_sort)){
	    				    eDialog.alert('优惠规则，商品编码-单品编码：'+_l[i].id +" 对应的搭配<span style='color:red'>排序</span>，请输入有效的正整数  "+_sort+" ！",null);
		    			    _reData.value=false;//返回默认 失败
					  		return _reData;
		    		  }  
		    		  data.push({'name':'matchMap['+_l[i].id+']','value':_promCnt+"-"+_price+"-"+_sort});
			  	  }
			    }
			    _reData.value=true; 
			    _reData.result=data;
			    return _reData;
			},
			//促销商品列表
		initList:function(_skuIds){
				//编辑 进入 promId非空
				var _promId=$('#promId').val();
				//_skuIds 初始化页面为空  ，open页面回掉可非空
				if(!_skuIds){
					_skuIds=new Array();
				}
				//当前页面的列表数据 需要加入到skuIds中
				 var _l=$("#matchTable tbody tr td input[ name='checkboxgds']");
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
					url : GLOBAL.WEBROOT + "/matchprom/matchList",
					data:[{name:'promId',value:_promId},{name:'skuIds',value:_skuIds}],
					"dataType": "text",
					success : function(returnInfo) {
						$('#matchGdsTableId').empty();
						$('#matchGdsTableId').append(returnInfo);
						//查看
						if($('#doAction').val()=="view"){
						    $(".matchClass").hide();//批量删除按钮  赠品选择按钮
						    $("a[ name='delGdsRow']").hide();//删除按钮
						}
					}
				});
			},
			hide:function(){
				$('.matchDivClass').hide();
			},
			show:function(){
				$('.matchDivClass').show();
			}
};