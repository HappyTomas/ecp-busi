$(function(){
	/**
	 * 解决IE不支持trim问题
	 */
	if (!String.prototype.trim) {
		String.prototype.trim = function() {
			return this.replace(/^\s+|\s+$/g, '');
		};
	}
	
	leaflet.init();
});

var leaflet = {
	operationBar:{
		"status_0":"<a href='javascript:void(0)' class='show-seller-leaflet'>查看</a>"+
		           " | <a href='javascript:void(0)' class='pub-seller-leaflet'>发布</a>"+
				   " | <a href='javascript:void(0)' class='edit-seller-leaflet'>编辑</a>"+
				   " | <a href='javascript:void(0)' class='del-seller-leaflet'>使失效</a>",
				   
		"status_1":"<a href='javascript:void(0)' class='show-seller-leaflet'>查看</a>"+
		           " | <a href='javascript:void(0)' class='off-seller-leaflet'>撤销</a>",
		           
		"status_2":"<a href='javascript:void(0)' class='show-seller-leaflet'>查看</a>"
	},
	init : function(){//初始化
		//初始化查询条件，回填值
		SearchObj.initSearchParams({paramsId:"searchParams",formId:"searchForm"});
		
		// 绑定搜索按钮事件.
		$('#btnFormSearch').click(function(){
			var p = ebcForm.formParams($('#searchForm'));
			$('#listDiv').load(GLOBAL.WEBROOT + '/seller/leaflet/gridlist',p);
		});
		
		//绑定重置按钮
		$('#btnFormReset').click(function(){
			ebcForm.resetForm('#searchForm');
		});
		//绑定添加按钮
		$('#btn_code_add').live("click",function(){
			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var params = {
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'seller/leaflet/add',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定编辑
		$('.edit-seller-leaflet').live("click",function(){
			leaflet.edit($(this).parents("tr.seller-leaflet").find("td").eq(0).text());
		});
		//绑定查看
		$('.show-seller-leaflet').live("click",function(){
			leaflet.showByID($(this).parents("tr.seller-leaflet").find("td").eq(0).text());
		});
		//绑定发布
		$('.pub-seller-leaflet').live("click",function(){
			var $leaflet = $(this).parents("tr.seller-leaflet").eq(0); 
			sucCallback = function(){
				$leaflet.find(".operation-bar").html(leaflet.operationBar.status_1);
				$leaflet.find(".leaflet-status").html("已发布");
			};
			leaflet.changeStatus($(this).parents("tr.seller-leaflet").find("td").eq(0).text(),1,sucCallback);
		});
		//绑定撤销
		$('.off-seller-leaflet').live("click",function(){
			var $leaflet = $(this).parents("tr.seller-leaflet").eq(0); 
			sucCallback = function(){
				$leaflet.find(".operation-bar").html(leaflet.operationBar.status_0);
				$leaflet.find(".leaflet-status").html("未发布");
			};
			leaflet.changeStatus($(this).parents("tr.seller-leaflet").find("td").eq(0).text(),0,sucCallback);
		});
		//绑定使失效
		$('.del-seller-leaflet').live("click",function(){
			var $leaflet = $(this).parents("tr.seller-leaflet").eq(0); 
			sucCallback = function(){
				$leaflet.find(".operation-bar").html(leaflet.operationBar.status_2);
				$leaflet.find(".leaflet-status").html("已失效");
			};
			eDialog.confirm("您确认使失效该记录吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						leaflet.changeStatus($leaflet.find(".leaflet-id").eq(0).text(),2,sucCallback);
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		});
		// 初始化事件.
		$('#btnFormSearch').trigger('click');
	},
	edit : function(id){//编辑
		id = id.trim();
		if(id){
			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var params = {
					"id":id,
					"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'seller/leaflet/edit',
				"params" : params,
				"method" :"post"
			});
		}else{
			eDialog.error("获取数据失败，请刷新页面。");
		}
	},
	changeStatus : function(id,status,sucCallback){ //生效、失效
		id = id.trim();
		if(!$.isFunction(sucCallback)){
			sucCallback = function(){
				//刷新
				$('#btnFormSearch').trigger('click');
			}
		}
		if(id && status >= 0){
			
			var optionStr = "";
			if(status == 1){
				optionStr = "发布";
			}else if(status == 0){
				optionStr = "撤销";
			}else if(status == 2){
				optionStr = "失效";
			}
			
			var param = {
					'ids':id,
					'status':status
			};
			$.eAjax({
				url : $webroot + "seller/leaflet/changestatus",
				type : "POST",
				data : param,
				datatype:'json',
				success : function(returnInfo) {
					if(returnInfo.resultFlag=='ok'){
						eDialog.success(optionStr+"成功！"); 
						sucCallback();
					}
				},error : function(e,xhr,opt){
					eDialog.error(optionStr+"出现错误!");
				},exception:function(){
					eDialog.error(optionStr+"出现异常!");
				}
			});
		}else{
			if(!id){
				eDialog.error("获取数据失败，请刷新页面。");
			}else{
				eDialog.error("参数status为空，请联系管理员。");
			}
		}
	},
	//查看详情
	showByID : function (id) {
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"id":id,
			"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'seller/leaflet/view',
			"params" : params,
			"method" :"post"
		});
	}
};
