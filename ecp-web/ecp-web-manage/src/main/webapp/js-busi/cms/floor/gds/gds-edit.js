$(function(){
	gds_edit.init();
});

var gds_edit = {
	"selectedGds":"",
	init : function(){//初始化
		//获取已选择商品
		gds_edit.getSelectedGds();
		//初始化选择商品按钮id
		gds_edit.setSelectLinkId();
		//初始化tab改变事件  获取该页签下已选择商品
		$("#tabId").live("change",function(){
			//获取已选择商品
			gds_edit.getSelectedGds();
		});
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			gds_edit.saveFrom();
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			gds_edit.pubSaveFrom();
		});
		//初始化字数
		$.isFunction(checkLen) && checkLen($("#remark").get(0),'count','250');
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
		//绑定是否促销商品change 事件
		$("#isProm").live("change",function(){
			$("#gdsName").val("");
			$("#gdsId").val("");
			$("#promID").val("");
			gds_edit.setSelectLinkId();
		});
		//绑定选择内容
		$('#select_link_gds').live("click",function(){
			//var floorId = $("#floorId").val();
			var siteId = $("#siteId").val();
			var title = "选择商品";
			var	url = "floorgds/opengds?siteId="+siteId;
			bDialog.open({
				title : title,
				width : 860,
				height : 500,
				url : $webroot + url,
				params : {"selectedGds":gds_edit.selectedGds||""
				},
				callback:function(data){
					if(data && data.results && data.results[0]){
						$("#gdsId").val(data.results[0].gdsIds);
						$("#gdsName").val(data.results[0].gdsNames);
					}
				}
			});
		});
		//绑定促销商品选择内容
		$('#select_link_prom_gds').live("click",function(){
			//var floorId = $("#floorId").val();
			var siteId = $("#siteId").val();
			var title = "选择促销商品";
			var	url = "floorgds/openpromgds?siteId="+siteId;
			bDialog.open({
				title : title,
				width : 860,
				height : 500,
				url : $webroot + url,
				params : {"selectedGds":gds_edit.selectedGds||""
				},
				callback:function(data){
					if(data && data.results && data.results[0]){
						$("#gdsId").val(data.results[0].gdsIds);
						$("#promId").val(data.results[0].promIds);
						$("#gdsName").val(data.results[0].gdsNames);
					}
				}
			});
		});
	},
	"setSelectLinkId" : function(){//根据是否促销商品   改变选择商品按钮id
		var isProm = $("#isProm").val();
		var $gdsLinkBtn = $("#floorGdsDiv").find("button");
		if(isProm == "1"){
			$gdsLinkBtn.attr("id","select_link_prom_gds");
		}else if(isProm == "0"){
			$gdsLinkBtn.attr("id","select_link_gds");
		}
	},
	pubSaveFrom : function(){//发布保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "floorgds/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
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
								"url" : $webroot+'floorgds/grid',
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
			url : $webroot + "floorgds/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('楼层商品保存成功！',{
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
								"url" : $webroot+'floorgds/grid',
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
					target:[gds_edit,"selectedGds"]//用于存储返回的数据，格式为",34,34,34,"
			}
			batchAddObj.getSelectedItem(params);
		}
	}
};

