$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : true,
        'pCheck' : 'single', //multi  single
        'pCheckColumn' : false, //是否显示单选/复选框的列  
        "sAjaxSource": GLOBAL.WEBROOT + '/solrconfig/gridlist',
        //指定列数据位置
        "aoColumns": [
			{ "mData": "id", "sTitle":"编码","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "configName", "sTitle":"配置名称","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "configCollectionName", "sTitle":"索引名称","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "configIfActive", "sTitle":"状态","sWidth":"80px","sClass": "center","bSortable":false,
				"mRender": function(data,type,row){
					if(data=='1'){
						return "<span class='green'>已启用</span>";
					}else{
						return "<span class='red'>已禁用</span>";
					}
				}
			},
			{"targets": -1,"data": "shopId","sTitle":"操作","sWidth":"80px","sClass": "center",
				"mRender": function(data,type,row){
					var forbidStr="启用";
					if(row.configIfActive=='1'){
						forbidStr="禁用";
					}
					return  "<a href='javascript:void(0);' rowStatus='"+row.configIfActive+"' class='forbidIndex' rowId='"+row.id+"'>"+forbidStr+"</a>" +
							" | <a href='javascript:void(0);' class='readIndex' rowId='"+row.id+"'>查看</a>"+
							" | <a href='javascript:void(0);' class='editIndex' rowId='"+row.id+"'>编辑</a>"+
							" | <a href='javascript:void(0);' class='clearIndex' rowId='"+row.id+"'>删除</a>"+
							" | <a href='javascript:void(0);' class='mgrIndex' rowId='"+row.id+"'>管理</a>" ;
				}
			}
        ]
	});
	//添加
	$('#btn_code_add').click(function(){
		eNav.setSubPageText('新增搜索配置');
		window.location.href = GLOBAL.WEBROOT+'/solrconfig/tosolrconfigadd';
	});
	/**
	 * 初始化集合
	 */
	$("#clearCash").live('click',function(e){
		SolrManage.clearCash();
		e.preventDefault();
	});
	//查询
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	//重置
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	/**
	 * 删除索引
	 */
	$(".clearIndex").live('click',function(e){
		var params = {id:$(this).attr('rowId'),status:0};
		SolrManage.clearIndex(params);
		e.preventDefault();
	});
	/**
	 * 禁用索引
	 */
	$(".forbidIndex").live('click',function(e){
		var params = {id:$(this).attr('rowId'),configIfActive:($(this).attr('rowStatus')=='1'?'0':'1')};
		SolrManage.forbidIndex(params,$(this));
		e.preventDefault();
	});
	/**
	 * 查看索引
	 */
	$(".readIndex").live('click',function(e){
		
		eNav.setSubPageText('查看搜索配置');
		window.location.href = GLOBAL.WEBROOT+'/solrconfig/querysolrconfiginfo?isRead=1&id='+$(this).attr('rowId');
	});
	/**
	 * 编辑索引
	 */
	$(".editIndex").live('click',function(e){
		eNav.setSubPageText('编辑搜索配置');
		window.location.href = GLOBAL.WEBROOT+'/solrconfig/querysolrconfiginfo?id='+$(this).attr('rowId');
	});
	/**
	 *管理索引
	 */
	$(".mgrIndex").live('click',function(e){
		eNav.setSubPageText('管理搜索配置');
		window.location.href = GLOBAL.WEBROOT+'/solrdataobj?id='+$(this).attr('rowId');
	});
});
var SolrManage = {
		gridList : function(verifyStatus){
			var p = ebcForm.formParams($("#searchForm"));
			$.gridLoading({"el":"#gridLoading","messsage":"正在加载中...."});
			$('#dataGridTable').gridSearch(p);
			$.gridUnLoading({"el":"#gridLoading"});
		},
		clearCash : function(params){
			eDialog.confirm("确认清除搜索缓存吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){	$.gridLoading({
						"messsage" : "正在操作中...."
					});
					$.eAjax({
						url : GLOBAL.WEBROOT + "/solrconfig/clearCash",
						data : params,
						success : function(returnInfo) {
							$.gridUnLoading();
							if(returnInfo.resultFlag=='ok'){
								eDialog.success(returnInfo.resultMsg, {
									buttons : [{
										caption : "确定",
										callback : function() {
											SolrManage.gridList();
										}
									}]
								});
							}else{
								eDialog.error(returnInfo.resultMsg);
							}
						},
						exception : function() {
							$.gridUnLoading();
						}
					});
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		},
		forbidIndex : function(params,$this){
			var firmstr="确认"+(params.configIfActive=='1'?"启用":"禁用")+"选中索引配置？";
		    //假如有同名索引已启动
			if(params.configIfActive=='1'){
				var configCollectionName=$.trim($this.parents('tr').find('td').eq(2).text());
				var isSameName=false;
				$.eAjax({
					async:false,
					url : GLOBAL.WEBROOT + "/solrconfig/samenameconfig",
					data : {'configCollectionName':configCollectionName},
					success : function(returnInfo) {
						if(returnInfo.resultMsg=='1'){
							isSameName=true
						}else{
							eDialog.warning(returnInfo.resultMsg);
						}
						
					},
					exception : function() {
						//eDialog.warning("请先禁用同名集合配置");
					}
				});
				if(isSameName){
					eDialog.warning("请先禁用同名索引配置");
					return false;
				}
			}
			eDialog.confirm(firmstr, {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.gridLoading({
							"messsage" : "正在操作中...."
						});
						$.eAjax({
							url : GLOBAL.WEBROOT + "/solrconfig/forgitsolrconfig",
							data : params,
							success : function(returnInfo) {
								$.gridUnLoading();
								if(returnInfo.resultFlag=='ok'){
									var text = "启动成功！";
									if(params.configIfActive == "0"){
										text = "禁用成功！";
									}
									eDialog.success(text); 
									SolrManage.gridList();
								}
							},
							exception : function() {
								$.gridUnLoading();
							}
						});
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		},
		clearIndex : function(params){
			eDialog.confirm("确认删除选中索引配置？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.gridLoading({
							"messsage" : "正在操作中...."
						});
						$.eAjax({
							url : GLOBAL.WEBROOT + "/solrconfig/removesolrconfig",
							data : params,
							success : function(returnInfo) {
								$.gridUnLoading();
								if(returnInfo.resultFlag=='ok'){
									eDialog.success(returnInfo.resultMsg, {
										buttons : [{
											caption : "确定",
											callback : function() {
												SolrManage.gridList();
											}
										}]
									});
								}else{
									eDialog.error(returnInfo.resultMsg);
								}
							},
							exception : function() {
								$.gridUnLoading();
							}
						});
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
			
		},
		restartIndex : function(params){
			bDialog.open({
	            title : '重建索引信息确认',
	            width : 550,
	            height : 350,
	            params:params,
	            url : GLOBAL.WEBROOT+"/solrmanage/tosolrconfirmwindow",
	            callback:function(data){
	            	if(data && data.results && data.results.length > 0 ){
	            		if(data.results[0].param=='ok'){
	            			SolrManage.gridList();
	            		}
	            	}
	            }
	        });
		}
		
};