$(function(){
	floorTab_edit.init();
});

var floorTab_edit = {
	init : function(){//初始化
		floorTab_edit.changeSelect();
		
		floorTab_edit.changeDataSource();
		
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			floorTab_edit.saveFrom();
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
				"url" : $webroot+'floortab/grid',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定选择按钮，触发下面的链接地址联动
		$('#isLink').change(function(){
			floorTab_edit.changeSelect();
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			floorTab_edit.pubSaveFrom();
		});
		//初始化字数
		$.isFunction(checkLen) && checkLen($("#remark").get(0),'count','250');
		//点击商品分类弹出
        $("#select_people_health").click(function(){
    		var catlogId = '1';
    		bDialog.open({
                title : '分类选择',
                width : 350,
                height : 550,
                params:{multi : false},
                url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=1&catlogId="+catlogId,
                callback:function(data){
                	if(data && data.results && data.results.length > 0 ){
                        var _catgs = data.results[0].catgs;
    					var size = _catgs.length;
    					for(var i =0;i<size;i++){
    						var obj = _catgs[i];
    						$("#catg-code-name").val(obj.catgName);
    						$("#catg-code").val(obj.catgCode);
    						$("#catg-code").attr('catg-code',obj.catgCode);
    					}
    				}
                }
            });
    	});
        //点击积分商品分类弹出
        $("#select_Integral_mall").click(function(){
    		var catlogId = '2';
    		bDialog.open({
                title : '分类选择',
                width : 350,
                height : 550,
                params:{multi : false},
                url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=1&catlogId="+catlogId,
                callback:function(data){
                	if(data && data.results && data.results.length > 0 ){
                        var _catgs = data.results[0].catgs;
    					var size = _catgs.length;
    					for(var i =0;i<size;i++){
    						var obj = _catgs[i];
    						$("#catg-code-name").val(obj.catgName);
    						$("#catg-code").val(obj.catgCode);
    						$("#catg-code").attr('catg-code',obj.catgCode);
    					}
    				}
                }
            });
    	});
    	
        $("#clean_catgCode").click(function(){//清除
        	$("#catg-code-name").val("");
			$("#catg-code").val("");
			$("#catg-code").attr('catg-code',"");
        });
		
	},
	changeDataSource : function(){
		var dataSource = $('#dataSource').val();
		if(dataSource=="01"){
			$("#catgCode_div").hide();
		}else{
			$("#catgCode_div").show();
		}
	},
	pubSaveFrom : function(){//发布保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "floortab/pubsave",
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
								"url" : $webroot+'floortab/grid',
								"params" : params,
								"method" :"post"
							});
				        }
					}]
				}); 
			}
		});
	},
	//根据是否连接来显示或隐藏URL地址的编辑
	changeSelect : function(){
		if($("#isLink").val()!='1'){
			$("#linkUrl").val("");
			$("#linkUrl").removeClass("required");
			$("#linkUrl_div").hide();
		}
		else{
			$("#linkUrl").addClass("required");
			$("#linkUrl_div").show();
		}
	},
	saveFrom : function(){//保存
		if(!$("#detailInfoForm").valid())return false;  
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "floortab/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('楼层页签保存成功！',{
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
								"url" : $webroot+'floortab/grid',
								"params" : params,
								"method" :"post"
							});
				        }
					}]
				}); 
			}
		});
	}
};

