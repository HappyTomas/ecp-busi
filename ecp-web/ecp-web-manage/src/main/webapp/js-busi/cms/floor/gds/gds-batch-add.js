$(function(){
	gds_batch_add["init"]["dataTleObj"] = {//表格显示数据的配置参数
		"$contain":$("#selecting-gds-table"),
		"dataMap":[{title:"商品编码",data:"id"},{title:"商品名称",data:"gdsName",width:"250"},{title:"促销名称",data:"promName",width:"250"},{title:"排序",data:"sortNo",events:[{name:"change",event:batchAddEvent.changeValue}]},{title:"操作",data:"delete",events:[{name:"click",event:batchAddEvent.deleteRow}]}],
		"data":{obj:gds_batch_add,prop:"selectingGds"}
	},

	gds_batch_add.init();
});

var gds_batch_add = {
	"selectedGds":"",
	"selectingGds":{},
	init : function(){//初始化
		//获取已选择商品
		gds_batch_add.getSelectedGds();
		//显示已选择商品
		batchAddObj.showDtInTle(gds_batch_add["init"]["dataTleObj"]);
		
		//初始化tab改变事件  获取该页签下已选择商品
		$("#tabId").live("change",function(){
			gds_batch_add.getSelectedGds();
			gds_batch_add["selectingGds"] = {};//清除数据
			//显示已选择商品
			batchAddObj.showDtInTle(gds_batch_add["init"]["dataTleObj"]);
		});
		
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			gds_batch_add.saveFrom(0);//0为未发布状态
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			gds_batch_add.saveFrom(1);//1为发布状态
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
				"url" : $webroot+'floorgds/grid',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定选择内容
		$('#select_link_gds').live("click",function(){
			var siteId = $("#siteId").val();
			var title = "批量选择商品";
			var	url = "floorgds/openbatchgds?siteId="+(siteId||"");
			
			if($("#isProm").val() ==1){
				 title = "批量选择促销商品";
				 url = "floorgds/openbatchpromgds?siteId="+(siteId||"");
			}
			bDialog.open({
				title : title,
				width : 860,
				height : 500,
				url : $webroot + url,
				params : {"selectedGds":gds_batch_add.selectedGds||"",
						  "selectingGds":$.extend(true, {}, gds_batch_add.selectingGds||{}) 
				},
				callback:function(data){
					if(data && data.results && data.results[0]){
						gds_batch_add.selectingGds = data.results[0].selectingGds || {};
					}
					
					//显示已选择商品
					batchAddObj.showDtInTle(gds_batch_add["init"]["dataTleObj"]);
				}
			});
		});
	},
	saveFrom : function(status){//保存
		if(!$("#detailInfoForm").valid())return false;
		var datas = gds_batch_add["selectingGds"];
		if(Object.getOwnPropertyNames(datas).length <=0){
			eDialog.alert("请先选择商品!");
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
		var tabId = $("#tabId").val();
		
		if(!floorId && !tabId){
			$.gridUnLoading();
			eDialog.alert("数据错误，请刷新页面!");
			return this;
		}
		var param = {
				"floorGdsList":[]
			};
		
		for(var name in datas){
			datas[name]["status"] = status;
			datas[name]["floorId"] = floorId;
			datas[name]["placeId"] = $("#placeId").val()||"";
			datas[name]["tabId"] = tabId;
			datas[name]["gdsId"] = name;
			var dSortNo = datas[name]["sortNo"] || parseInt(datas[name]["sortNo"]);
			datas[name]["sortNo"] = dSortNo===dSortNo ? dSortNo : 1;
			param["floorGdsList"].push(datas[name]);
		}
		var reg=/^([1-9]\d?|100)$/;
		for(var name in datas){
			var dSortNo = datas[name]["sortNo"];
			if(!reg.test(dSortNo)){
				$.gridUnLoading();
				eDialog.alert("商品编码为【"+datas[name]["gdsId"]+"】的“排序”列请填写1-100的整数，值越小，排序越靠前！");
				return false;
			}
		}
		
		
		//保存入库
		$.eAjax({
			url : $webroot + "floorgds/batchsave",
			data : ebcUtils.serializeObject(param),
			success : function(returnInfo) {
				if(returnInfo && returnInfo.resultFlag == "ok"){
					eDialog.success('楼层商品'+saveType+'成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								//保存完，跳转至楼层广告列表页签
								var searchParams = $("#searchParams").val();
								var floorSearchParams = $("#floorSearchParams").val();
								var params = {
									"floorId":floorId,
									"searchParams":searchParams,
									"floorSearchParams":floorSearchParams
								}
								SearchObj.openPage({
									"url" : $webroot+'floorgds/grid',
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
	"getSelectedGds":function(){
		var floorId = $("#floorId","#detailInfoForm").val();
		var tabId = $("#tabId","#detailInfoForm").val();
		
		//获取已选择的数据
		if(floorId || tabId){
			var params = {
					url:$webroot + "floorgds/getselectedgds",//请求获取已选择数据的地址
					param:{
						floorId : floorId || "",
						tabId : tabId || ""
					},//请求的参数
					text:"正在加载已选择商品....",//当在加载已选择的记录时遮罩显示的文本
					target:[gds_batch_add,"selectedGds"]//用于存储返回的数据，格式为",34,34,34,"
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
			gds_batch_add["selectingGds"][id][prop]=$(this).val() || "";
		}
	},
	"deleteRow":function(){
		var id = $(this).attr("data-id");
		$(this).closest("tr").remove();
		if(id){
			delete gds_batch_add["selectingGds"][id];
			
		}
	}
}
