$(function(){
	siteInfo_edit.init();
});

var siteInfo_edit = {
	init : function(){//初始化
		$("select#siteId").bind("change",function(){
			$.gridLoading({"message":"父级信息数据加载中...."});
			siteInfo_edit.changeSite($(this).val());
		});
		$(".chooseChannel").live('click',function(){
			var $this = $(this);
			bDialog.open({
				title : '选择栏目',
				width : 340,
				height : 565,
				url : GLOBAL.WEBROOT + '/cms/channel/openchannel?siteId='+$("#siteId").val()+"&isOutLink=0",
				params : {
					'param' : $this.parent().find('input[id="channelId"]').val(),
					'checkType':"radio",
					'siteId' : $("#siteId").val()
				},
				callback:function(data){
					if(data && data.results && data.results.length > 0 ){
						$this.parent().find('input[id="channelZH"]').val(data.results[0].stringShow);
						$this.parent().find('input[id="channelId"]').val(data.results[0].param);
					} 
				}
			});
		});
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			siteInfo_edit.saveFrom();
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			siteInfo_edit.pubSaveFrom();
		});
		//绑定限制更新按钮
		$('#btnFormLimitSave').click(function(){ 
			siteInfo_edit.limitSaveFrom();
		});
		//清除栏目
		$('#clean_channel').click(function(){
			$("#channelId").val("");
			$("#channelZH").val("");
		});
		//新增返回
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'siteinfo/grid',
				"params" :{"searchParams":(searchParams?searchParams:"")},
				"method" :"post"
			});
		});
		//绑定选择内容
		$('#select_link_detail').click(function(){
			siteInfo_edit.openLinkDetail();
		});
		//网站信息类型
		$('#siteInfoType').change(function(){
			siteInfo_edit.changeSiteInfoType();
		});
		//根据静态文件路径，填充富文本内容。
		var staticUrl = $("#staticUrl").val();
		if(staticUrl !=""){
			var editorText = $("#editorText"); 
			var url = staticUrl;
			$.ajax({
				url : url,
				async : true,
				type : "get",
				dataType : 'jsonp',
				jsonp :'jsonpCallback',//注意此处写死jsonCallback
				success: function (data) {
					editorText.empty();
	            	editorText.html(data.result);
	            	KindEditor.html(editorText,data.result);
					KindEditor.sync(editorText);
			    }
			});
		}
		siteInfo_edit.changeSiteInfoType();
	},
	changeSite : function(siteId){
		var $selector = $("select#parent");
		$selector.empty();
		if((siteId || 0 === siteId) && $selector && 0 < $selector.length){
			$.eAjax({
				url : $webroot + "siteinfo/getTopSiteInfo",
				type : "POST",
				data : {"siteId":siteId},
				datatype:'json',
				success : function(returnInfo) {
					var datas = null;
					if(returnInfo.code=='ok'){
						datas = returnInfo.datas;
					}
					if(datas && 0 < datas.length){
						for(var i in datas){
							var data = datas[i];
							if(data && (data.id || 0 === data.id)){
								$selector.append('<option value="'+data.id+'">'+data.siteInfoName+'</option>');
							}
						}
					}
				},complete:function(){
					$.gridUnLoading();
				}
			});
		}else{
			$.gridUnLoading();
		}
	},
	changeSiteInfoType : function(){
		var is = $('#siteInfoType').val();
		//如果文本类型，显示静态文件的，反之道理一样。
		if(is == "01"){
			$('#static_div').show();
		}else{
			$('#static_div').hide();
		}
	},
	saveFrom : function(){//保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		var siteInfoType = $('#siteInfoType').val();
		if(siteInfoType == "01"){
			var editorText = $("#editorText").val();
			if(editorText == null || editorText == ''){
				eDialog.alert('信息内容不允许为空，请重新填写！'); 
				$.gridUnLoading();
				return false;
			}
		}
		$.eAjax({
			url : $webroot + "siteinfo/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				if(returnInfo && 'ok' == returnInfo.resultFlag){
					eDialog.success('网站信息保存成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								var searchParams = $("#searchParams").val();
								SearchObj.openPage({
									"url": $webroot+'siteinfo/grid',
									"params" :{"searchParams":(searchParams?searchParams:"")},
									"method" :"post"
								});
					        }
						}]
					});
				}else{
					eDialog.error("保存遇到异常!");
				}
			},
			error : function(e,xhr,opt){
				eDialog.error("保存遇到异常!");
			},
			exception : function(msg){
				eDialog.error("保存遇到异常!");
			},complete:function(){
				$.gridUnLoading();
			}
		});
	},
	pubSaveFrom : function(){//发布保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		var siteInfoType = $('#siteInfoType').val();
		if(siteInfoType == "01"){
			var editorText = $("#editorText").val();
			if(editorText == null || editorText == ''){
				eDialog.alert('信息内容不允许为空，请重新填写！'); 
				$.gridUnLoading();
				return false;
			}
		}
		$.eAjax({
			url : $webroot + "siteinfo/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				if(returnInfo && 'ok' == returnInfo.resultFlag){
					eDialog.success('网站信息发布成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								var searchParams = $("#searchParams").val();
								SearchObj.openPage({
									"url": $webroot+'siteinfo/grid',
									"params" :{"searchParams":(searchParams?searchParams:"")},
									"method" :"post"
								});
					        }
						}]
					}); 
				}else{
					eDialog.error("保存遇到异常!");
				}
			},
			error : function(e,xhr,opt){
				eDialog.error("保存遇到异常!");
			},
			exception : function(msg){
				eDialog.error("保存遇到异常!");
			},complete:function(){
				$.gridUnLoading();
			}
		});
	},
	limitSaveFrom:function(){
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		var siteInfoType = $('#siteInfoType').val();
		if(siteInfoType == "01"){
			var editorText = $("#editorText").val();
			if(editorText == null || editorText == ''){
				eDialog.alert('信息内容不允许为空，请重新填写！'); 
				$.gridUnLoading();
				return false;
			}
		}
		$.eAjax({
			url : $webroot + "siteinfo/limitsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				if(returnInfo && 'ok' == returnInfo.resultFlag){
					eDialog.success('网站信息更新成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								var searchParams = $("#searchParams").val();
								SearchObj.openPage({
									"url": $webroot+'siteinfo/grid',
									"params" :{"searchParams":(searchParams?searchParams:"")},
									"method" :"post"
								});
					        }
						}]
					}); 
				}else{
					eDialog.error("保存遇到异常!");
				}
			},
			error : function(e,xhr,opt){
				eDialog.error("保存遇到异常!");
			},
			exception : function(msg){
				eDialog.error("保存遇到异常!");
			},complete:function(){
				$.gridUnLoading();
			}
		});
	}
};

