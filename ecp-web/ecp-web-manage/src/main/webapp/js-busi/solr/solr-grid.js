$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : true,
        'pCheck' : 'single', //multi  single
        'pCheckColumn' : false, //是否显示单选/复选框的列  
        "sAjaxSource": GLOBAL.WEBROOT + '/solrmanage/gridlist',
        //指定列数据位置
        "aoColumns": [
			{ "mData": "id", "sTitle":"编码","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "configName", "sTitle":"配置名称","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "collectionStatus", "sTitle":"集合状态","sWidth":"80px","sClass": "center","bSortable":false,
				"mRender": function(data,type,row){
					if(data=='1'){
						return "<span class='green'>已创建</span>";
					}else{
						return "<span class='red'>未创建</span>";
					}
				}
			},
			{ "mData": "indexStatus", "sTitle":"索引状态","sWidth":"80px","sClass": "center","bSortable":false,
				"mRender": function(data,type,row){
					if(data=='1'){
						return "<span class='green'>已建立</span>";
					}else if(data=='0'){
						return "<span class='red'>未建立</span>";
					}else if(data=='2'){
						return "索引正在重建中";
					}
				}
			},
			{ "mData": "configDesc", "sTitle":"配置描述信息","sWidth":"80px","sClass": "center","bSortable":false},
			{"targets": -1,"data": "shopId","sTitle":"操作","sWidth":"80px","sClass": "center",
				"mRender": function(data,type,row){
				    var menus='';
				    if(row.configIfActive==0){
				    	menus='';
				    }else if(row.collectionStatus&&row.collectionStatus==1){
				    	menus= "<a href='javascript:void(0);' class='delCollect' id='"+row.id+"'>删除集合 | </a>"+
			                   "<a href='javascript:void(0);' class='clearIndex' id='"+row.id+"'>删除索引</a>" +
					           " | <a href='javascript:void(0);' class='restartIndex' id='"+row.id+"'>重建索引</a>";
				    }else{
				    	menus="<a href='javascript:void(0);' class='initCollect' id='"+row.id+"'>初始化集合 </a>";
				    }
					
					return menus;
				}
			}
        ]
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
	 * 初始化集合
	 */
	$(".initCollect").live('click',function(e){
		var params = {id:$(this).attr('id')};
		SolrManage.initCollect(params);
		e.preventDefault();
	});
	/**
	 * 删除集合
	 */
	$(".delCollect").live('click',function(e){
		var params = {id:$(this).attr('id')};
		SolrManage.delCollect(params);
		e.preventDefault();
	});
	/**
	 * 删除索引
	 */
	$(".clearIndex").live('click',function(e){
		var params = {id:$(this).attr('id')};
		SolrManage.clearIndex(params);
		e.preventDefault();
	});
	/**
	 * 重建索引
	 */
	$(".restartIndex").live('click',function(e){
		var params = {id:$(this).attr('id')};
		SolrManage.restartIndex(params);
		e.preventDefault();
	});
	
	$("#catgCode").click(function(){
		catlogId = '1';
		if($("#ifGdsScore").val()=='1'){
			catlogId = '2';
		}
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
						$("#catgCode").val(obj.catgName);
						$("#catgCode").attr('catgCode',obj.catgCode);
					}
				}
            }
        });
	});
	//批量审核
	$("#btn_verify_gds").click(function(){
		var ids = $('#dataGridTable').getCheckIds();
		if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一条商品记录进行操作！');
			return ;
		}else if(ids[0]==undefined){
			eDialog.alert('请选择至少选择一条商品记录进行操作！');
			return ;
		}	
		var params = {};
		params.shopId = $("#shopId").val();
		params.operateId = ids.join(",");
		bDialog.open({
            title : '商品审核',
            width : 550,
            height : 350,
            params:params,
            url : GLOBAL.WEBROOT+"/gdsverify/togdsverifywindow",
            callback:function(data){
            	if(data && data.results && data.results.length > 0 ){
            		alert(data.results[0].param);
                    window.location.href = GLOBAL.WEBROOT+ '/gdsverify?shopId='+ data.results[0].param;
            	}
            }
        });
	});
});
var SolrManage = {
		gridList : function(verifyStatus){
			var p = ebcForm.formParams($("#searchForm"));
			$.gridLoading({"el":"#gridLoading","messsage":"正在加载中...."});
			$('#dataGridTable').gridSearch(p);
			$.gridUnLoading({"el":"#gridLoading"});
		},
		whethercando :function(params){
			var b=false;
			$.eAjax({
				async:false,
				url : GLOBAL.WEBROOT + "/solrmanage/whethercando",
				data : params,
				success : function(returnInfo) {
					$.gridUnLoading();
					if(returnInfo.resultFlag=='ok'){
						b=true;
					}else{
						eDialog.warning(returnInfo.resultMsg);
						$.gridUnLoading();
					}
				},
				exception : function() {
					$.gridUnLoading();
				}
			});
			return b;
		},
		delCollect : function(params){
			eDialog.confirm("确认删除集合吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.gridLoading({
							"messsage" : "正在操作中...."
						});
						$.eAjax({
							url : GLOBAL.WEBROOT + "/solrmanage/delCollect",
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
		clearIndex : function(params){
			eDialog.confirm("确认删除索引吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.gridLoading({
							"messsage" : "正在操作中...."
						});
						if(!SolrManage.whethercando(params)){
							return;
						}
						$.eAjax({
							url : GLOBAL.WEBROOT + "/solrmanage/clearindex",
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
		initCollect : function(params){
			$.gridLoading({
				"messsage" : "正在操作中...."
			});
			$.eAjax({
				url : GLOBAL.WEBROOT + "/solrmanage/initcollect",
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
		},
		restartIndex : function(params){
		/*	if(!SolrManage.whethercando(params)){
				return;
			}*/
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