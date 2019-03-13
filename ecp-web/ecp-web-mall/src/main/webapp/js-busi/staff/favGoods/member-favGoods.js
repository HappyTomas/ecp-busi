$(function(){
//	$("#checkAll").bSelectAll($("#datalist"));
	//页面初始化
	FavGoods.init();
	//搜索按钮
	$("#favsear").click( function(){
		FavGoods.submit();
	});//
});
var FavGoods = {
		//初始化页面
		init: function () {
			$.eAjax({
				url : $webroot + '/favgoods/gridlist',
				dataType : "text",
				success : function(returnInfo) {
					if(returnInfo){
						$("#dataBody").html(returnInfo);
					}
				}//end of success
			});//end of eAjax
	    	  //处理没有值的隐藏框
	    	  var totalRow = 0;
	    	  if ($("#totalRow").val()){
	    		  totalRow = $("#totalRow").val();
	    	  }
	    	  //更新全部商品数
	    	  $("#sernum").text(totalRow);
//			
		},//end of init
      //点击搜索按钮
      submit : function(){
//    	  if($("#gdsName").val() == ''){
//    		  return false;
//    	  }
    	  $.eAjax({
				url : $webroot + '/favgoods/gridlist',
				dataType : "text",
				data : { gdsName : $("#gdsName").val()},
				success : function(returnInfo) {
					if(returnInfo){
						$("#dataBody").html("");
						$("#dataBody").append(returnInfo);
					}
				}//end of success
			});//end of eAjax
      },//end of submit
      //更新搜索栏于分页条状态
      updateBar : function (){
    	  //处理没有值的隐藏框
//    	  var totalPage = 1;
    	  var totalRow = 0;
//    	  var pageSize = 5;
//    	  var pageNumber = 1 ;
//    	  if ($("#totalPage").val()){
//    		  totalPage = $("#totalPage").val();
//    	  }
    	  if ($("#totalRow").val()){
    		  totalRow = $("#totalRow").val();
    	  }
//    	  if ($("#pageSize").val()){
//    		  pageSize = $("#pageSize").val();
//    	  }
//    	  if ($("#pageNumber").val()){
//    		  pageNumber = $("#pageNumber").val();
//    	  }
    	  //更新全部商品数
    	  $("#sernum").text(totalRow);
    	  //更新分页条
    	  $('#pageControlbar').bPage({
    			url : $webroot + '/favgoods/gridlist',
    			pageSizeMenu : [5,10,20],
    			asyncLoad : true,
    			asyncTarget : '#dataBody',
    			params : function(){
    		        return {
    		        	gdsName : $("#gdsName").val()
    		        };
    		    }
    		});
    	  //全选插件
    	  $('#checkAll').bSelectAll('#datalist');
      },//end of updateBar
      //删除一条记录
      addToCart : function(skuId,obj,gdsTypeId){
    	  var buyFlag = 0;
		  //虚拟产品，则判断是否已购买
			$.eAjax({
					url : GLOBAL.WEBROOT + "/gdsdetail/wetherbuyed",
			data : {skuId:skuId},
			async : false,
			success : function(returnInfo) {
				if(returnInfo.resultFlag=="ok"){
					if(returnInfo.buyedFlag==true){
						//已购买过咯
								buyFlag = 1;
							}
						}
					}
			});
    	  if(buyFlag == 1){
    		  eDialog.alert("抱歉，该商品只允许购买一次！");
    		  return;
    	  }
    	  $.eAjax({
				url : GLOBAL.WEBROOT + "/order/cart/mini/add",
				data : {"skuId" : skuId,"amount" : 1},
				plugin : ['ePageTop'],
				success : function(returnInfo) {
					if(returnInfo.ecpBaseResponseVO.resultFlag=="ok"){
						var startOffset = $(obj).offset();
		                var endOffset = $("#carGus").offset();
		                var img = $(obj).parent().parent().parent().find("img").attr('src');
		                var flyer = $('<img class="u-flyer"  src="'+img+'">');
		                flyer.fly({
							speed : 1.8,
		                    start: {
		                        left: startOffset.left,
		                        top: startOffset.top
		                    },
		                    end: {
		                        left: endOffset.left+50,
		                        top: endOffset.top+10,
		                        width: 0,
		                        height: 0
		                    },
		                    onEnd: function(){
		                        this.destory();
		                    }
		                });
						$.pageTop.setOrderCount({url:GLOBAL.WEBROOT + '/order/getcartcount'}); 
					}else{
						eDialog.error("加入购物车失败！");
					}
					
				 }
			 });
      },
      deleteOne : function (id){
    	  //获取关注商品记录id
    	  if(!id){
			   eDialog.alert("请选择要删除的对象");
			   return false;
		 }
		 eDialog.confirm('您确定要删除这条数据吗？',{
			buttons:[{
				caption:'确定',
				callback:function(){
					$.eAjax({
						url : GLOBAL.WEBROOT + "/favgoods/collremove",
						data : {"id" : id},
						success : function(returnInfo) {
							if(returnInfo.resultFlag=='ok'){
								eDialog.success('删除成功！'); 
								//刷新列表
								FavGoods.init();
							 }else{
								 eDialog.alert(" 删除失败，请联系管理员");
							 }
						 }
					 });
	             	         }
	             	},{
				caption:'取消',
				callback:$.noop
		           }]
		});
      },//end of deleteOne
     //批量删除
      deleteBatch : function (){
    	//获取选中的id值    格式为"121,121"
  		var ids = "";
    	$("td.p-check").each(function(){
    		if($(this).children(":checkbox").attr("checked")){
    			ids += $(this).parent().attr("id") + ",";
    		}
    	});//end of each
       //判断是否有选中值
    	if(!ids || ids.length==0 ||  !ids[0]){
			eDialog.alert('请选择至少选择一个项目进行操作！');
			return ;
		}
    	//ajax 的参数
		var param = {
				operateId:ids
		};
		eDialog.confirm("您确认删除这些记录吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					$.eAjax({
						url : GLOBAL.WEBROOT + "/favgoods/collbatchremove",
						type : "POST",
						data : param,
						datatype:'json',
						success : function(returnInfo) {
							if(returnInfo.resultFlag=='ok'){
								eDialog.success('删除成功！'); 
								//刷新列表
								FavGoods.init();
							}else if(returnInfo.resultFlag=='expt'){
								//异常
								eDialog.success('部分删除成功！'); 
								//刷新列表
								FavGoods.init();
							}else{
								 eDialog.alert(" 删除失败，请联系管理员");
							 }
						}//end of success
					});//end of eAjax
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});//end of confirm
      }//end of deleteBatch
}//end of FavGoods