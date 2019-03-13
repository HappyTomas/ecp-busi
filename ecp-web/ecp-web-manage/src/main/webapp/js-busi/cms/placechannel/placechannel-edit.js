$(function(){
	placechannel_edit.init();
});

var placechannel_edit = {
	init : function(){//初始化
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			placechannel_edit.saveFrom();
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			placechannel_edit.pubSaveFrom();
		});
		//新增返回
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'placechannel/grid',
				"params" :{"searchParams":(searchParams?searchParams:"")},
				"method" :"post"
			});
		});
		
		$("#select_link_detail").click(function(){
			placechannel_edit.selectChannel();
		});
	},
	
	selectChannel : function(){//选择
		var $this = $(this);
		bDialog.open({
			title : '选择栏目',
			width : 340,
			height : 565,
			url : GLOBAL.WEBROOT + '/cms/channel/openchannel?siteId='+$("#siteId").val()+"&isOutLink=0",/*&isresiteinfo=1&siteInfoType=02*/
			params : {
				'param' : $this.parent().find('input[id="channelId"]').val(),
				'checkType':"radio",
				'siteId' : $("#siteId").val()
			},
			callback:function(data){
				if(data && data.results && data.results.length > 0 ){
//					$this.parent().find('input[id="channelName"]').val(data.results[0].stringShow);
//					$this.parent().find('input[id="channelId"]').val(data.results[0].param);
					$('#channelName').val(data.results[0].stringShow);
					$('#channelId').val(data.results[0].param);
				} 
			}
		});
	},
	saveFrom : function(){//保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "placechannel/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('位置与栏目关系保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'placechannel/grid',
								"params" :{"searchParams":(searchParams?searchParams:"")},
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
	pubSaveFrom : function(){//发布保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "placechannel/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				if(returnInfo.id != null){
				eDialog.success('位置与栏目关系发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'placechannel/grid',
								"params" :{"searchParams":(searchParams?searchParams:"")},
								"method" :"post"
							});
				        }
					}]
				}); 
			
			}else{
				eDialog.success('该内容位置下已有栏目，请先撤销后再发布新的！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								$.gridUnLoading();
					        }
						}]
					});
				}
			},
			error : function(e,xhr,opt){
				eDialog.error("保存遇到异常!");
				$.gridUnLoading();
			},
			exception : function(msg){
				$.gridUnLoading();
			}
		});
	}
};

