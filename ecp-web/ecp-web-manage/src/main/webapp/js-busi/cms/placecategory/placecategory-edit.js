$(function(){
	placecategory_edit.init();
});

var placecategory_edit = {
	init : function(){//初始化
		//站点触发事件
		$("#siteId").live("change", function(o) {
			$('#catgId').val('');
			$('#catgName').val('');
		});
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			placecategory_edit.saveFrom();
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			placecategory_edit.pubSaveFrom();
		});
		//新增返回
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'placecategory/grid',
				"params" :{"searchParams":(searchParams?searchParams:"")},
				"method" :"post"
			});
		});
		
		$("#select_link_detail").click(function(){
			placecategory_edit.selectCategory();
		});
	},
	
	selectCategory : function(){//选择商品分类
		var siteId = $("#siteId").val();
//		var catlogId;
//		if(siteId =='1'){
//			catlogId = '1';
//		}else{
//			catlogId = '2';
//		}                     ?catgType=1&catlogId="+catlogId
		bDialog.open({
            title : '分类选择',
            width : 350,
            height : 550,
            params:{},
            url : GLOBAL.WEBROOT+"/cms/category/open/catgselect?siteId="+siteId,
            callback:function(data){
            	if(data && data.results && data.results.length > 0 ){
                    var _catgs = data.results[0].catgs;
					var size = _catgs.length;
					for(var i =0;i<size;i++){
						var obj = _catgs[i];
						$("#catgId").val(obj.catgCode);
//						$("#vatgName").attr('catgCode',obj.catgCode);
						$("#catgName").val(obj.catgName);
					}
				}
            }
        });
	},
	saveFrom : function(){//保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "placecategory/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('位置与商品分类关系保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'placecategory/grid',
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
			url : $webroot + "placecategory/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				if(returnInfo.id != null){
				eDialog.success('位置与商品分类关系发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'placecategory/grid',
								"params" :{"searchParams":(searchParams?searchParams:"")},
								"method" :"post"
							});
				        }
					}]
				}); 
			
			}else{
				eDialog.success('该内容位置下已有分类，请先撤销后再发布新的！',{
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

