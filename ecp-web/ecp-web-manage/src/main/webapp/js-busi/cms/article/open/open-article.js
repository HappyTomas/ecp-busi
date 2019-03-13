$(function(){
	open_article.init();
});

var open_article = {
	
	init : function(){//初始化
		var siteId = $("#siteId").val();
		//初始化列表
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheck' : "multi",
	        'pAutoload' : true,
	        'pCheckColumn' : false, //是否显示单选/复选框的列
	        "sAjaxSource": $webroot + 'article/queryArticle?siteId='+siteId,
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"Id","bVisible":false,"bSortable":true},
				{ "mData": "articleTitle", "sTitle":"标题","bSortable":false,"mRender": function(data,type,row){
					return "<p style='max-width:200px;word-wrap:break-word;'>"+data+"</p>";
				}},
				{ "mData": "thumbnailUrl", "sTitle":"缩略图片","sWidth":"160px","bSortable":false,"mRender": function(data,type,row){
					return "<img src='"+data+"' width='160' height='80'>";
				}},		
				{ "mData": "siteZH", "sTitle":"所属站点","bVisible":true,"sWidth":"120px","bSortable":false},
				{ "mData": "channelZH", "sTitle":"所属栏目","bVisible":true,"sWidth":"120px","bSortable":false},
				{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"80px","bSortable":false},
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"80px","sClass": "left",
					"mRender": function(data,type,row){
						return "<a href='javascript:void(0)' onclick='open_article.selectInfo(\""+row.id+"\",\""+row.articleTitle+"\")'>选定</a> ";
					}
				}
	        ],
	        "eDbClick" : function(){
	        	//update();
	        }
		});
		
		//绑定选择所属栏目
		$("#channelName").live('click',function(){
			var $this = $(this);
			bDialog.open({
				title : '选择栏目',
				width : 340,
				height : 565,
				url : GLOBAL.WEBROOT + '/cms/channel/openchannel?siteId='+$('#siteId').val(),
				params : {
					'param' : "",
					'siteId' : $("#siteId").val(),
					'checkType':"radio"
				},
				callback:function(data){
					if(data && data.results && data.results.length > 0 ){
						$this.parent().find('input[id="channelId"]').val(data.results[0].param);
						$this.parent().find('input[id="channelName"]').val(data.results[0].stringShow);
					} 
				}
			});
		});
		
		//绑定查询按钮
		$('#btnFormSearch').click(function(){
			open_article.search();
		});
		//绑定重置按钮
		$('#btnFormReset').click(function(){
			ebcForm.resetForm('#searchForm');
		});
	},
	//查询方法
	search : function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		//p.push({ 'name': 'catgCode','value' : $("#catgCode").attr('catgCode') });
		//p.push({'name':'isbn','value':$("#isbn").val()});
		$('#dataGridTable').gridSearch(p);
	},
	//绑定选定
	selectInfo : function (id,title){
		bDialog.closeCurrent({
			"linkUrl": id,
			"infoTitle":title
		});
	},
	/**
	 * 判断字数
	 * @param {} thisObj  当前对象
	 * @param {} showObj  要显示提示的对象
	 */
	checkLen : function (thisObj,showObj,maxChars){
		//var maxChars = 200;
		if (thisObj.value.length > maxChars) thisObj.value = thisObj.value.substring(0,maxChars);  
		var curr = maxChars - thisObj.value.length;  
		$("#"+showObj).html(curr.toString());
	}
};

