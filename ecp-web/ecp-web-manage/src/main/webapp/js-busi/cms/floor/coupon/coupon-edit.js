$(function(){
	coupon_edit.init();
});

var coupon_edit = {
	"selectedCp":"",
	init : function(){//初始化
		//获取已选择优惠券
		coupon_edit.getSelectedCp();
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			coupon_edit.saveFrom();
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			coupon_edit.pubSaveFrom();
		});
		//新增返回
		$('#btnReturn').click(function(){
			var floorId = $("#floorId").val();
			var searchParams = $("#searchParams").val();
			var floorSearchParams = $("#floorSearchParams").val();
			var params = {
				"floorId":floorId,
				"searchParams":searchParams,
				"floorSearchParams":floorSearchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'floorcoupon/grid',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定选择内容
		$('#select_link_coupon').click(function(){
			//var floorId = $("#floorId").val();
			var siteId = $("#siteId").val();
			var title = "选择优惠券";
			var	url = "floorcoupon/opencoupon?siteId="+siteId;
			bDialog.open({
				title : title,
				width : 900,
				height : 550,
				url : $webroot + url,
				params : {
					"selectedCp":coupon_edit.selectedCp||"",
				},
				callback:function(data){
					$("#couponId").val(data.results[0].couponIds);
					$("#couponName").val(data.results[0].couponNames);
				}
			});
		});
		//初始化字数
		$.isFunction(checkLen) && checkLen($("#remark").get(0),'count','250');
	},
	
	pubSaveFrom : function(){//发布保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "floorcoupon/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				eDialog.success('发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							//保存完，跳转至楼层广告列表页签
							var searchParams = $("#searchParams").val();
							var floorSearchParams = $("#floorSearchParams").val();
							var params = {
								"floorId":returnInfo.floorId,
								"searchParams":searchParams,
								"floorSearchParams":floorSearchParams
							}
							SearchObj.openPage({
								"url" : $webroot+'floorcoupon/grid',
								"params" : params,
								"method" :"post"
							});
				        }
					}]
				}); 
			},
			error : function(e,xhr,opt){
				eDialog.error("保存遇到异常!");
				$.gridUnLoading();
			},
			exception : function(msg){
				$.gridUnLoading();
			}
		});
	},
	
	saveFrom : function(){//保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "floorcoupon/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				eDialog.success('楼层优惠券保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							//保存完，跳转至楼层广告列表页签
							var searchParams = $("#searchParams").val();
							var floorSearchParams = $("#floorSearchParams").val();
							var params = {
								"floorId":returnInfo.floorId,
								"searchParams":searchParams,
								"floorSearchParams":floorSearchParams
							}
							SearchObj.openPage({
								"url" : $webroot+'floorcoupon/grid',
								"params" : params,
								"method" :"post"
							});
				        }
					}]
				}); 
			},
			error : function(e,xhr,opt){
				eDialog.error("保存遇到异常!");
				$.gridUnLoading();
			},
			exception : function(msg){
				$.gridUnLoading();
			}
		});
	},
	"getSelectedCp":function(){
		var floorId = $("#floorId").val();
		//获取已选择的数据
		if(floorId){
			var params = {
					url:$webroot + "floorcoupon/getselectedcp",//请求获取已选择数据的地址
					param:{
						floorId : floorId || ""
					},//请求的参数
					text:"正在加载已选择的优惠券....",//当在加载已选择的记录时遮罩显示的文本
					target:[coupon_edit,"selectedCp"]//用于存储返回的数据，格式为",34,34,34,"
			}
			batchAddObj.getSelectedItem(params);
		}
	}
};

