//页面加载初始值
var linkage_siteId = "";//站点ID
var linkage_templateId = "";//模板ID
var linkage_placeId = "";//内容位置ID

//支持兼容性，做初始化调整。
//window.setTimeout(function(){
////	$(function(){
//	linkage_edit.init();
//	linkage_siteId = $("#linkage_siteId").val();
//	linkage_templateId = $("#linkage_templateId").val();
//	linkage_placeId = $("#linkage_placeId").val();
////	});
//},100);

$(function(){
	linkage_edit.init();
	linkage_siteId = $("#linkage_siteId").val();
	linkage_templateId = $("#linkage_templateId").val();
	linkage_placeId = $("#linkage_placeId").val();
});

var linkage_edit = {
	init : function(){//初始化
		//初始化站点下拉
		linkage_edit.initSite();
		//站点来匹配模板
		$('#siteId').change(function(){
			linkage_edit.changeSite();
		});
		//模板来匹配内容位置
		$('#templateId').change(function(){
			linkage_edit.changeTemplate();
		});
	},
	initSite : function(){
		$.eAjax({
			url : $webroot + '/seller/common/initSite',
			data : {
				"status":"1"//有效
			},
			success : function(returnInfo){
				for(var i in returnInfo){
					var selectedStr = "";
					if(linkage_siteId == returnInfo[i].id){
						selectedStr = "selected";
					}
					var option = "<option value ="+"\""+returnInfo[i].id+"\" "+selectedStr+" >"+returnInfo[i].siteName+"</option>";
					$("#siteId").append(option);
				}
				linkage_edit.changeSite();
			}	
		});
	},
	changeSite : function(){//站点来匹配模板
		$('#templateId').empty();
		$("#templateId").append("<option value = ''>-请选择-</option>");
		$('#placeId').empty();
		$("#placeId").append("<option value = ''>-请选择-</option>");
		var siteId = $('#siteId').val();
		if(siteId != "" && siteId != null){
			$.eAjax({
				url : $webroot + '/seller/common/changeSite',
				data : {
					"siteId":siteId,
					"templateClass":"",
					"status":"1"
				},
				success : function(returnInfo){
					for(var i in returnInfo){
						var selectedStr = "";
						if(linkage_templateId == returnInfo[i].id){
							selectedStr = "selected";
						}
						var option = "<option value ="+"\""+returnInfo[i].id+"\" "+selectedStr+" >"+returnInfo[i].templateName+"</option>";
						$("#templateId").append(option);
					}
					linkage_edit.changeTemplate();
				}	
			});
		}
	},
	changeTemplate : function(){//模板来匹配内容位置
		$('#placeId').empty();
		$("#placeId").append("<option value = ''>-请选择-</option>");
		var templateId = $('#templateId').val();
		if(templateId != "" && templateId != null){
			$.eAjax({
				url : $webroot + '/seller/common/changeTemplate',
				data : {
					"templateId":templateId,
					"placeType":"",
					"status":"1"
				},
				success : function(returnInfo){
					for(var i in returnInfo){
						var selectedStr = "";
						if(linkage_placeId == returnInfo[i].id){
							selectedStr = "selected";
						}
						var option = "<option " +
								" value ="+"\""+returnInfo[i].id+"\""+
								" place_count ="+"\""+returnInfo[i].placeCount+"\""+
								" place_size ="+"\""+returnInfo[i].placeSize+"\""+
								" place_width ="+"\""+returnInfo[i].placeWidth+"\""+
								" place_height ="+"\""+returnInfo[i].placeHeight+"\" "+ selectedStr +
								" >"+returnInfo[i].placeName+"</option>";
						$("#placeId").append(option);
					}
				}
			});
		}
	}
};

