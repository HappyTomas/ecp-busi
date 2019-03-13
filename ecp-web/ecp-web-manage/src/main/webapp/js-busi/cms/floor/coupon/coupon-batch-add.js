$(function(){
	coupon_batch_add["init"]["dataTleObj"]={//表格显示数据的配置参数
		"$contain":$("#selecting-table"),
		"dataMap":[{title:"优惠券编码",data:"id"},{title:"优惠券名称",data:"coupName",width:"150"},{title:"优惠券类型",data:"coupTypeName",width:"150"},{title:"面额（元）",data:"coupValue",width:"30"},{title:"排序",data:"sortNo",width:"50",events:[{name:"change",event:batchAddEvent.changeValue}]},{title:"操作",data:"delete",events:[{name:"click",event:batchAddEvent.deleteRow}]}],
		"data":{obj:coupon_batch_add,prop:"selectingCp"}
		}
	
	coupon_batch_add.init();
});

var coupon_batch_add = {
	"selectedCp":"",
	"selectingCp":{},
	init : function(){//初始化
		//获取已选择优惠券
		coupon_batch_add.getSelectedCp();
		//显示已选择优惠券
		batchAddObj.showDtInTle(coupon_batch_add["init"]["dataTleObj"]);
		//绑定选择内容
		$('#select_link_coupon').click(function(){
			//var floorId = $("#floorId").val();
			var siteId = $("#siteId").val();
			var title = "批量选择优惠券";
			var	url = "floorcoupon/openbatchcoupon?siteId="+siteId;
			bDialog.open({
				title : title,
				width : 900,
				height : 550,
				url : $webroot + url,
				params : {
					"selectedCp":coupon_batch_add.selectedCp||"",
					"selectingCp":$.extend(true, {}, coupon_batch_add.selectingCp||{}) 
				},
				callback:function(data){
					if(data && data.results && data.results[0]){
						coupon_batch_add.selectingCp = data.results[0].selectingCp || {};
					}
					
					//显示已选择优惠券
					batchAddObj.showDtInTle(coupon_batch_add["init"]["dataTleObj"]);
				}
			});
		});
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			coupon_batch_add.saveFrom(0);
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			coupon_batch_add.saveFrom(1);
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
	},
	saveFrom : function(status){//保存
		if(!$("#detailInfoForm").valid())return false;
		var datas = coupon_batch_add["selectingCp"];
		if(Object.getOwnPropertyNames(datas).length <=0){
			eDialog.alert("请先选择优惠券!");
			return false;
		}
		$.gridLoading({"message":"正在加载中...."});
		
		var saveType = "发布";
		//拼装请求数据
		if(status != 1){
			status = 0;
			saveType = "保存";
		}		
		
		var floorId = $("#floorId").val();
		
		if(!floorId){
			$.gridUnLoading();
			eDialog.alert("无法获取楼层数据，请刷新页面!");
			return this;
		}
		
		var param = {
				"floorCoupList":[]
			};
		
		for(var name in datas){
			datas[name]["status"] = status;
			datas[name]["floorId"] = floorId;
			datas[name]["placeId"] = $("#placeId").val()||"";
			datas[name]["couponId"] = name;
			param["floorCoupList"].push(datas[name]);
		}
		
		$.eAjax({
			url : $webroot + "floorcoupon/batchsave",
			data :  ebcUtils.serializeObject(param),
			success : function(returnInfo) {
				if(returnInfo && returnInfo.resultFlag == "ok"){
					eDialog.success('楼层优惠券'+saveType+'成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								//保存完，跳转至楼层优惠券页签
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
					        }
						}]
					});
				}else{
					eDialog.alert("批量新增失败，请刷新页面!");
					$.gridUnLoading();
				}
			},
			error : function(e,xhr,opt){
				eDialog.error(saveType+"遇到异常!");
				$.gridUnLoading();
			},
			exception : function(msg){
				$.gridUnLoading();
			}
		});
	},
	"getSelectedCp":function(){
		var floorId = $("#floorId","#detailInfoForm").val();
		//获取已选择的数据
		if(floorId){
			var params = {
					url:$webroot + "floorcoupon/getselectedcp",//请求获取已选择数据的地址
					param:{
						floorId : floorId || ""
					},//请求的参数
					text:"正在加载已选择的优惠券....",//当在加载已选择的记录时遮罩显示的文本
					target:[coupon_batch_add,"selectedCp"]//用于存储返回的数据，格式为",34,34,34,"
			}
			batchAddObj.getSelectedItem(params);
		}
	}
};

//批量删除显示列表事件
var batchAddEvent ={
	"changeValue":function(){
		var id = $(this).attr("data-id");
		var prop = $(this).attr("data-prop")
		if(id && prop){
			coupon_batch_add["selectingCp"][id][prop]=$(this).val() || "";
		}
	},
	"deleteRow":function(){
		var id = $(this).attr("data-id");
		$(this).closest("tr").remove();
		if(id){
			delete coupon_batch_add["selectingCp"][id];
			
		}
	}
}
