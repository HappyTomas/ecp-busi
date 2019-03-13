$(function(){
	floor_edit.init();
});

var floor_edit = {
	init : function(){//初始化
		
		//floor_edit.changeDataSource();
		//根据数据来源初始化界面效果
		var dataSource = $('#dataSource').val();
		if(dataSource=="01"){//手工配置
			$("#countType_div").hide();
			$("#catgCode_div").hide();
		}else{
			$("#countType_div").show();
			$("#catgCode_div").show();
		}
		//绑定保存按钮
		$('#floor_btnFormSave').click(function(){ 
			floor_edit.saveFrom();
		});
		//新增返回
		$('#floor_btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'floor/grid',
				"params" :{"searchParams":(searchParams?searchParams:"")},
				"method" :"post"
			});
		});
		//站点来匹配模板
		$('#floor_siteId').change(function(){
			floor_edit.changeSite();
		});
		//模板来匹配模板
		$('#floor_templateId').change(function(){
			floor_edit.changeTemplate();
		});
		$('#floor_templateId').finish(function(){
			floor_edit.changeTemplate();
		});
		//初始化是否加载联动效果
		var id = $("#floor_floorId").val();
		if(id == null || id == ""){
			floor_edit.changeSite();
		}
		//数据来源
		$('#dataSource').change(function(){
			floor_edit.changeDataSource();
		});
		
		//绑定tab页切换
//		$('#myTab a').not(":first").each(function(i){
//			$(this).click(function(e){
//				var floorId = $('#floor_floorId').val();
//				//如果楼层页签未保存，则不允许打开其他面签。
//				if(floorId == null || floorId == ''){
//					eDialog.alert('请先保存楼层信息！');
//					e.stopPropagation();
//					$('#tab_floor').tab('show');
//				}else{
//					var id =$(this).attr("id");
//					if(id == "tab_advertise"){
//						tab ="advertise";
//					}else if(id == "tab_tab"){
//						tab ="tab";
//					}else if(id == "tab_label"){
//						tab ="label";
//					}else if(id == "tab_gds"){
//						tab ="gds";
//					}else if(id == "tab_attrcount"){
//						tab ="attrcount";
//					}
//					floor_edit.queryByCondition(tab,floorId);
//				}
//			});
//		});
		
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			floor_edit.pubSaveFrom();
		});
		
		/*//点击商品分类弹出
        $("#select_people_health").click(function(){
    		catlogId = '1';
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
    		catlogId = '2';
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
    	});*/
    	
		$("#select_catg").live("click",function(){
			var siteId = $("#siteId").val();
			floor_edit.selectCatg(siteId||1);
		});
		
        $("#clean_catgCode").click(function(){//清除
        	$("#catg-code-name").val("");
			$("#catg-code").val("");
			$("#catg-code").attr('catg-code',"");
        });
        //初始化字数
    	$.isFunction(checkLen) && checkLen($("#floor_remark").get(0),'count','250');
	},
	"selectCatg":function(siteId){
		var catlogId = siteId || 1;
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
	},
	changeDataSource : function(){
		var dataSource = $('#dataSource').val();
		if(dataSource=="01"){//手工配置
			var id = $("#floor_floorId").val();
			if(id){//编辑状态
				eDialog.confirm("此操作会清空该楼层所属页签关联的商品分类。确认执行吗？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							$("#countType_div").hide();
							$("#catgCode_div").hide();
							$('#countType').val('');
							$('#catg-code').val('');
							$('#catg-code-name').val('');
						}
					},{
						caption : '取消',
						callback : function(){
							$("#countType_div").show();
							$("#catgCode_div").show();
							$('#dataSource').val($('#dataSource').attr('attrValue'));
							$('#countType').val($('#countType').attr('attrValue'));
							$('#catg-code').val($('#catg-code').attr('attrValue'));
							$('#catg-code-name').val($('#catg-code-name').attr('attrValue'));
						}
					}]
				});
			}else{//新增
				$("#countType_div").hide();
				$("#catgCode_div").hide();	
			}
		}else{
			$("#countType_div").show();
			$("#catgCode_div").show();
			$('#countType').val($('#countType').attr('attrValue'));
			$('#catg-code').val($('#catg-code').attr('attrValue'));
			$('#catg-code-name').val($('#catg-code-name').attr('attrValue'));
		}
	},
	pubSaveFrom : function(){//发布保存
		if(!$("#floor_detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "floor/pubsave",
			data : ebcForm.formParams($("#floor_detailInfoForm")),
			success : function(returnInfo) {
				if(returnInfo.id != null){
					eDialog.success('发布成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								var searchParams = $("#searchParams").val();
								SearchObj.openPage({
									"url": $webroot+'floor/grid',
									"params" :{"searchParams":(searchParams?searchParams:"")},
									"method" :"post"
								});
					        }
						}]
					});
				}else{
					eDialog.success('该内容位置下已有楼层，请先撤销后再发布新的！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								$.gridUnLoading();
					        }
						}]
					});
				}
			}
		});
	},
	changeSite : function(){//站点来匹配模板
		$('#floor_templateId').empty();
		$('#floor_placeId').empty();
		var siteId = $('#floor_siteId').val();
		$.eAjax({
			url : $webroot + 'floor/changeSite',
			data : {
				"siteId":siteId,
				"templateClass":"",
				"status":"1"
			},
			success : function(returnInfo){
				$("#floor_templateId").html("");
				$("#floor_templateId").append("<option value = ''>--请选择--</option>");
				for(var info in returnInfo){
					var option = "<option value ="+"\""+returnInfo[info].id+"\""+">"+returnInfo[info].templateName+"</option>";
					$("#floor_templateId").append(option);
				}
				floor_edit.changeTemplate();
			}	
		});
	},
	changeTemplate : function(){//模板来匹配模板
		$('#floor_placeId').empty();
		var templateId = $('#floor_templateId').val();
		if(templateId == "" || templateId==null){
			$("#placeId").append("<option value = ''>--请选择--</option>");
		}
		else{
		$.eAjax({
			url : $webroot + 'floor/changeTemplate',
			data : {
				"templateId":templateId,
				"placeType":"",
				"status":"1"
			},
			success : function(returnInfo){
				$("#floor_placeId").html("");
				$("#floor_placeId").append("<option value = ''>--请选择--</option>");
				for(var info in returnInfo){
					var option = "<option value ="+"\""+returnInfo[info].id+"\""+">"+returnInfo[info].placeName+"</option>";
					$("#floor_placeId").append(option);
				}
			}
		});
		}
	},

//	/**
//	 * 查询楼层下各属性列表
//	 * @param {} tab  页签标记
//	 */
//	queryByCondition : function (tab,floorId) {
//		if(tab=="advertise"){
//			$('#tab_advertise').tab('show');
//			advertise.init(floorId);
//		}else if(tab=="tab"){
//			$('#tab_tab').tab('show');
//			floorTab.init(floorId);
//		}else if(tab=="label"){
//			$('#tab_label').tab('show');
//			label.init(floorId);
//		}else if(tab=="gds"){
//			$('#tab_gds').tab('show');
//			gds.init(floorId);
//		}else if(tab=="attrcount"){
//			$('#tab_attrcount').tab('show');
//			attrcount.init(floorId);
//		}
//	},
	saveFrom : function(){//保存
		if(!$("#floor_detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "floor/save",
			data : ebcForm.formParams($("#floor_detailInfoForm")),
			success : function(returnInfo) {
				eDialog.success('楼层保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'floor/grid',
								"params" :{"searchParams":(searchParams?searchParams:"")},
								"method" :"post"
							});
				        }
					}]
				}); 
			}
		});
	}
};

