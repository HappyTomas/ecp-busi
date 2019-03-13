$(function(){
	linkage_grid.init();
});

var linkage_grid = {
	init : function(){//初始化
		
		var siteAttrId = $("#siteId").attr("attrId");//站点属性（用于存放查询返回的站点ID）
		var templateAttrId = $("#templateId").attr("attrId");//模板属性（用于存放查询返回的模板ID）
		var placeAttrId = $("#placeId").attr("attrId");//内容位置属性（用于存放查询返回的内容位置ID）
		
		//初始化站点下拉
		linkage_grid.initSite(siteAttrId);
		linkage_grid.changeSite(siteAttrId,templateAttrId);
		linkage_grid.changeTemplate(templateAttrId,placeAttrId);
		
		//站点来匹配模板
		$('#siteId').change(function(){
			var siteId = $('#siteId').val();
			linkage_grid.changeSite(siteId,templateAttrId);
		});
		//模板来匹配内容位置
		$('#templateId').change(function(){
			var templateId = $('#templateId').val();
			linkage_grid.changeTemplate(templateId,placeAttrId);
		});
	},
	/**
	 * siteAttrId:站点属性（站点ID）
	 */
	initSite : function(siteAttrId){
		$.eAjax({
			url : $webroot + '/seller/common/initSite',
			data : {
				"status":""
			},
			success : function(returnInfo){
				for(var i in returnInfo){
					var selected = ""; 
					if(returnInfo[i].id == siteAttrId){
						selected = "selected";
					}
					var option = "<option value ="+"\""+returnInfo[i].id+"\""+ selected +">"+returnInfo[i].siteName+"</option>";
					$("#siteId").append(option);
				}
			}	
		});
	},
	/**
	 * siteId:站点ID
	 * templateAttrId：模板属性（模板ID）
	 */
	changeSite : function(siteId,templateAttrId){//站点来匹配模板
		$('#templateId').empty();
		$("#templateId").append("<option value = '' selected>-请选择-</option>");
		$('#placeId').empty();
		$("#placeId").append("<option value = '' selected>-请选择-</option>");
		//var siteId = $('#siteId').val();
		if(siteId != "" && siteId != null){
			$.eAjax({
				url : $webroot + '/seller/common/changeSite',
				data : {
					"siteId":siteId,
					"templateClass":"",
					"status":""
				},
				success : function(returnInfo){
					for(var i in returnInfo){
						var selected = ""; 
						if(returnInfo[i].id == templateAttrId){
							selected = "selected";
						}
						var option = "<option value ="+"\""+returnInfo[i].id+"\""+ selected +">"+returnInfo[i].templateName+"</option>";
						$("#templateId").append(option);
					}
				}	
			});
		}
	},
	/**
	 * templateId:模板ID
	 * placeAttrId：内容位置属性（内容位置ID）
	 */
	changeTemplate : function(templateId,placeAttrId){//模板来匹配内容位置
		$('#placeId').empty();
		$("#placeId").append("<option value = '' selected>-请选择-</option>");
		//var templateId = $('#templateId').val();
		if(templateId != "" && templateId != null){
			$.eAjax({
				url : $webroot + '/seller/common/changeTemplate',
				data : {
					"templateId":templateId,
					"placeType":"",
					"status":""
				},
				success : function(returnInfo){
					for(var i in returnInfo){
						var selected = ""; 
						if(returnInfo[i].id == placeAttrId){
							selected = "selected";
						}
						var option = "<option value ="+"\""+returnInfo[i].id+"\""+ selected +">"+returnInfo[i].placeName+"</option>";
						$("#placeId").append(option);
					}
				}
			});
		}
	}
};

