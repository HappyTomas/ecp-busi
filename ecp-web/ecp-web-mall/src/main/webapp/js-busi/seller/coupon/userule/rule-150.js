$(function() {

	//页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			 //互斥点击事件
			 $(".coupExclude").click(function(){
				 rule150.coupExcludeRadioClick(this);
				 });
			 //优惠券选择事件
			 $("#btnCoupInfoQuery").click(function(){
				 var _shopId = $('#shopId').val();
				 rule150.queryCoupInfo(_shopId);
				 });
			 //批量删除
			 $("#btn150AllDel").live('click',function(e){
				 rule150.btn150AllDel();
			 });
			 //checkbox事件
			 $("#coup150Table thead tr input[ id='dt_row_all_check']").live('click',function(e){
				 rule150.selectCheckBox(e);
			 });
			//全局变量 删除按钮
			 var coupInfoTr;
			 $("#coup150Table tr td a[ name='delRow']").live('click',function(e){
				 rule150.deleteCoup(e);
			 });

			// $(".coupExclude").click();
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

var rule150={
	//互斥点击事件
	coupExcludeRadioClick:function(_obj){
    	 if($(_obj).val()=='1'){
	          $(".btnCoupInfo150").show();
	          
	    }else{
	    	   $(".btnCoupInfo150").hide();
	    	   $('#coup-info-table-id').empty();
	    }
   },
	//批量删除事件
   btn150AllDel:function(){
	   var _doAction=false;
	   var _l=$("#coup150Table tbody tr td input[ name='checkboxcoup150']");
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
	            		 var _l=$("#coup150Table tbody tr td input[ name='checkboxcoup150']");
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
	//删除事件
    deleteCoup:function(e){
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
	//优惠券checkbox选择
    selectCheckBox:function(e){
 	   //选中 表示全选
 	   if(e.currentTarget.checked){
 		   $("#coup150Table tbody tr td input[ name='checkboxcoup150']").prop('checked',true);
 	   }else{
 		   //全部取消
 		   $("#coup150Table tbody tr td input[ name='checkboxcoup150']").prop('checked',false);
 	   }
    },
	//查询优惠券
	queryCoupInfo:function(_shopId){
		bDialog.open({
			title : '优惠券选择',
			width : 800,
			height : 550,
			url : GLOBAL.WEBROOT + "/seller/coupinfo/selectgrid?shopId="+_shopId,
			callback:function(data){
				  if(data && data.results){
					  rule150.initCoupList(data.results[0].ids);
				   }
			}
		});
	},
	//优惠券列表
	initCoupList:function(_coupIds){
		//编辑 进入 id非空
		var _coupId=$('#id').val();
		//_coupIds 初始化页面为空  ，open页面回掉可非空
		if(!_coupIds){
			_coupIds=new Array();
		}
		//当前页面的列表数据 需要加入到id中
		 var _l=$("#coup150Table tbody tr td input[ name='checkboxcoup150']");
		 if(_l && _l.length>0){
			 for(var i=0;i<_l.length;i++){
				 _coupIds.push(_l[i].parentNode.parentNode.id);
			 }
		 }
		 else{
			 //没有数据 清空
			 if(_coupIds && _coupIds.length==0){
				 _coupIds='';
			 }
		 }
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/seller/coupinfo/coupList",
			data:[{name:'coupId',value:_coupId},{name:'coupIds',value:_coupIds}],
			"dataType": "text",
			success : function(returnInfo) {
				$('#coup-info-table-id').empty();
				$('#coup-info-table-id').append('<span class="sbtn sbtn-blue btnCoupInfo150" id="btnCoupInfoQuery" style="display: inline-block;" data-toggle="modal" data-target="#myModal">优惠券查询</span>');
				$('#coup-info-table-id').append(returnInfo);
				//view 隐藏按钮
				var hide=false;
				if(returnInfo.trim()==""){
					hide=true;
				}
				rule150.viewHide(hide,$('#coup-info-table-id'));
			}
		});
	},
	//查看隐藏 控件
	viewHide:function(hide,obj){
		$("#btnCoupInfoQuery").hide();
		if($('#doAction').val()=="view"){
		    $(".delRowTd").hide();//删除按钮 列隐藏
		    if(hide && obj){
		    	obj.hide();
		    }
		}
	},
	//查询数据
	qryData:function(){
		rule150.initCoupList();
	},
	//检查
	check:function(){
	    return true;
	},
	//提醒信息
	getTips:function(){
		 var data=new Array();
		 //互斥限制规则
		 var _e=$("input[name='coupInfoVO.coupExclude']:checked").val(); 
		 if(_e==1){
			 var _l=$("#coup150Table tbody tr td input[ name='checkboxcoup150']");
			 if(_l && _l.length>0){
				 
			 }else{
				 data.push("券共同使用限制");
			 }
		 }
	   return data;
	},
	//保存
	getData:function(){
		 var data=new Array();
		 //互斥限制规则
		 var _e=$("input[name='coupInfoVO.coupExclude']:checked").val(); 
		 data.push({'name':'coupInfoVO.coupExclude','value':_e});
		 if(_e==1){
			 var _l=$("#coup150Table tbody tr td input[ name='checkboxcoup150']");
			 if(_l && _l.length>0){
				 for(var i=0;i<_l.length;i++){
					 data.push({'name':'useMap['+_l[i].id+']','value':$(_l[i]).attr("coupid")});
				 }
			 }
		 }else{
			 data.push({'name':'useMap[150]','value':_e});
		 }
	   return data;
	}
}
