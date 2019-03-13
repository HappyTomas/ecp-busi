;$(function(){
	//获取传递到弹出框的参数
	var _getDialogParam = function(name){
		if(bDialog && bDialog.getDialogParams && bDialog.getDialog){
			var params = bDialog.getDialogParams(bDialog.getDialog());
		}
		if(params){
			if(name){
				return params[name+""];
			}
		}
		return params;
	}
	var busiParams = _getDialogParam("busiParams") || {};
	var siteId = _getDialogParam("siteId") || 1;
	var flatType = _getDialogParam("flatType") || "mobile";
	//获取链接类型所需要传递的参数方法
	var _getParam = function(typeName){//info,prom,good,catg,page
		if(!typeName){
			return null;
		}
		typeName = typeName + "";
		var params = {};
		params.typeName = typeName;
		switch(typeName){
				case "info" :
					break;
				case "good" :
					busiParams["good"] = busiParams["good"] || [];
					params.siteId = busiParams["good"]["siteId"] || siteId || 1;
					break;
				case "catg" :
					busiParams["catg"] = busiParams["catg"] || [];
					params.siteId = busiParams["catg"]["siteId"] || siteId || 1;
					break;
				case "prom" :
					busiParams["prom"] = busiParams["prom"] || [];
					params.siteId = busiParams["prom"]["siteId"] || siteId || 1;
					params.pageTypeId = busiParams["prom"]["pageTypeId"] ;
					break;
				case "homepage" :
					busiParams["homepage"] = busiParams["homepage"] || [];
					params.siteId = busiParams["homepage"]["siteId"] || siteId || 1;
					params.pageTypeId = busiParams["homepage"]["pageTypeId"] ;
					break;
				case "secondpage" :
					busiParams["secondpage"] = busiParams["secondpage"] || [];
					params.siteId = busiParams["secondpage"]["siteId"] || siteId || 1;
					params.pageTypeId = busiParams["secondpage"]["pageTypeId"] ;
					break;
				case "sitenav":
					busiParams["sitenav"] = busiParams["sitenav"] || [];
					params.siteId = busiParams["sitenav"]["siteId"] || siteId || 1;
					params.flatType = busiParams["sitenav"]["flatType"] || flatType;
					break;
			}
		
		return params;
	}
	//获取请求地址方法
	var _objToUrl =function(params){
		//链接类型对应的地址-------------------------------------------------//
		var _baseUrl = "/cmslinkinpututil/open";
		if(!params || typeof params != "object" ){
			return _baseUrl;
		}
		var paramStr = "";
		for(var prop in params){
			if(params.hasOwnProperty(prop) && params[prop] != null){
				paramStr += "&"+prop+"="+params[prop];
			}
		}
		if(paramStr){
			paramStr = paramStr.replace(/^&/,"?");
		}
		return _baseUrl + paramStr;
	}
	//页签显示初始化---------------------------------------------//
	var $tabs = $("li","#link-type-tabs");
	if(!$tabs || $tabs.length <=0){
		return this;
	}
	if($tabs.length > 1){
		$("#link-type-tabs").show();
	}
	
	$tabs.bind("click",function(){
		var $this = $(this);
		var typeName = $this.attr("type-name");
		var $content = $("#"+typeName,".tab-content");
		//修改样式并展示对应的内容显示区
		if(!$this.hasClass("active")){
			$("li.active","#link-type-tabs").removeClass("active");
			$this.addClass("active");
			$(".active",".tab-content").removeClass("active");
			$content.addClass("active");
		}
		//获取数据
		if(!$this.hasClass("loaded")){
			$this.addClass("loaded")
			var url = _objToUrl(_getParam(typeName));
			if(url){
				$.eAjax({
					url : $webroot + url,
					async : true,
					type : "get",
					dataType : "html",
					success : function(data, textStatus) {
						$content.html(data);
					},
					error:function(data){
						$content.html("数据错误，请联系管理员！");
					}
				});
			}
		}
	});
	//激活当前显示的页签
	$("li.active","#link-type-tabs").trigger("click");
});

//公用选中数据回调方法
var _selectTypeItem = function(id,title){
	var $currentType = $("li.active","#link-type-tabs");
	bDialog.closeCurrent({
		"id": id,
		"name":title,
		"typeCode":$currentType.attr("type-code") || "",
		"typeName":$currentType.attr("type-name") || ""
	});
}