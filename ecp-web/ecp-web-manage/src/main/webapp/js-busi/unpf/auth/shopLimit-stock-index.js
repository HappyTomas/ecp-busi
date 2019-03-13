$(function(){
	$('#btnStockGdsBlackDelete').click(function(){
		var _val = $('#gdsStockDataGridTable').getSelectedData();
		var deleteIdArray = new Array();
	    for(var i=0;i<_val.length;i++){
	    	deleteIdArray.push(_val[i].id);
	    }
	    shopStocklimit.del(deleteIdArray,"gds");
	});
	$('#btnStockCatgBlackDelete').click(function(){
		var _val = $('#catgStockDataGridTable').getSelectedData();
		var deleteIdArray = new Array();
	    for(var i=0;i<_val.length;i++){
	    	deleteIdArray.push(_val[i].id);
	    }
	    shopStocklimit.del(deleteIdArray,"catg");
	});
	
	//黑名单分类选择 window
	$('#btnStockCatgBlackOpenWindow').click(function(){
		bDialog.open({
            title : '分类选择',
            width : 350,
            height : 550,
            url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=1",
            params:{multi : true,showRoot : "0" , siteId : $('#siteId').val()},
            callback:function(data){
                     if(data && data.results && data.results.length > 0 ){
                    	   var _catgs = data.results[0].catgs;
                    	   //保存黑名单分类
                    	   var catgCodeArray = new Array();
                           for(var i in _catgs){
                        	   catgCodeArray.push(_catgs[i].catgCode);
                           }
                    	   shopStocklimit.insertCatg(catgCodeArray);
                    	   //加载黑名单分类
                  }
            }
        });
	});
	//黑名单商品选择 window
	$('#btnStockGdsBlackOpenWindow').click(function(){
		bDialog.open({
			title : '黑名单商品选择',
			width : 800,
			height : 560,
			url : GLOBAL.WEBROOT + "/platauth/stocklimit/opengds?shopId="+$('#shopId').val()+"&siteId="+$('#siteId').val(),
			callback:function(data){
				   //确定 按钮_if_query==1
				   if(data && data.results && data.results[0]._if_query=='1'){
					   var gdsIds = data.results[0].gdsIds;
					   //保存黑名单商品
					   shopStocklimit.insertGds(gdsIds);
					   //加载黑名单商品
				   }
				  
			}
		});
	});
	
	
	$("#gdsStockDataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheck' : "multi",
        "sAjaxSource": GLOBAL.WEBROOT + '/platauth/stocklimit/gdsgrid',
        'params' : [{name:"shopId",value:$('#shopId').val()},
                    {name:"platType",value:$('#platType').val()},
                    {name:"shopAuthId",value:$('#shopAuthId').val()}],
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"编码", "sClass":"center","bSortable":false},
            //{ "mData": "skuId", "sTitle":"单品编码","sWidth":"80px","bSortable":false},
            { "mData": "gdsId", "sTitle":"商品编码", "sClass":"center","bSortable":false},
            { "mData": "gdsName", "sTitle":"商品名称", "sClass":"center","bSortable":false},
            { "mData": "gdsCatgCodeName", "sTitle":"商品分类", "sClass":"center","bSortable":false},
            { "mData": "gdsStatus", "sTitle":"商品状态", "sClass":"center","bSortable":false},
			{ "mData": "info", "sTitle":"操作", "sClass":"center","bSortable":false, "mRender":function(data,type,row){
				
                var _html="";
	        		 _html=_html+"<a href='javascript:void(0)' onclick='shopStocklimit.delGds("+row.id+")'>删除 </a>";

  				return _html;
  			}} 
        ],
        "eDbClick" : function(){
        	
        }
	});
	$("#catgStockDataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheck' : "multi",
        "sAjaxSource": GLOBAL.WEBROOT + '/platauth/stocklimit/catggrid',
        'params' : [{name:"shopId",value:$('#shopId').val()},
                    {name:"platType",value:$('#platType').val()},
                    {name:"shopAuthId",value:$('#shopAuthId').val()}],
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"编码", "sClass":"center", "bSortable":false},
            { "mData": "catgCode", "sTitle":"分类编码", "sClass":"center", "bSortable":false},
            { "mData": "catgCodeName", "sTitle":"分类名称", "sClass":"center","bSortable":false},
			{ "mData": "info", "sTitle":"操作", "sClass":"center","bSortable":false, "mRender":function(data,type,row){
				
                var _html="<a href='javascript:void(0)' onclick='shopStocklimit.delCatg("+row.id+")'>删除 </a>";

  				return _html;
  			}} 
        ],
        "eDbClick" : function(){
        	
        }
	});
});
var shopStocklimit = {
		delGds : function(id){
			var ids = new Array();
			ids.push(id);
			shopStocklimit.del(ids,"gds");
		},
		delCatg : function(id){
			var ids = new Array();
			ids.push(id);
			shopStocklimit.del(ids,"catg");
		},
		del : function(ids,type){
			var keys = {
					name:'keys',
					value:ids
			};
			var actiontype = {
					name:'type',
					value:type
			};
			
			eDialog.confirm("确定要删除吗", {
				buttons : [ {
					caption : '确认',
					callback : function() {
						$.eAjax({
							url :  GLOBAL.WEBROOT + '/platauth/stocklimit/delete',
							data : [keys, actiontype],
							async : false,
							dataType :  'json',
							type  : 'post',
							//traditional: true,  
							success : function(returnInfo) {
								eDialog.success('删除成功！',{
									buttons:[{
										caption:"确定",
										callback:function(){
											if(type == "gds"){
												$("#gdsStockDataGridTable").gridReload();
											}else if(type == "catg"){
												$("#catgStockDataGridTable").gridReload();
											}								        }
									}]
								}); 

							}
						});
					}
				}, {
					caption : '取消',
					callback : $.noop
				} ]
			});
		},
		insertGds : function(gdsIds){
			var gdsIds = {
					name:'gdsIds',
					value:gdsIds
			};
			var shopId = {
					name:'shopId',
					value:$('#shopId').val()
				};
			var platType = {
					name:'platType',
					value:$('#platType').val()
				};
			var shopAuthId = {
					name:'shopAuthId',
					value:$('#shopAuthId').val()
				};
			$.eAjax({
				url :  GLOBAL.WEBROOT + '/platauth/stocklimit/insertGds',
				data : [gdsIds, shopId, platType, shopAuthId],
				async : false,
				dataType :  'json',
				type  : 'post',
				//traditional: true,  
				success : function(returnInfo) {
					   $("#gdsStockDataGridTable").gridReload();
				}
			});
		},
		insertSku : function(skuIds, gdsIds){
			var skuIds = {
				name:'skuIds',
				value:skuIds
			};
			var gdsIds = {
					name:'gdsIds',
					value:gdsIds
			};
			var shopId = {
					name:'shopId',
					value:$('#shopId').val()
				};
			var platType = {
					name:'platType',
					value:$('#platType').val()
				};
			var shopAuthId = {
					name:'shopAuthId',
					value:$('#shopAuthId').val()
				};
			$.eAjax({
				url :  GLOBAL.WEBROOT + '/platauth/stocklimit/insertSku',
				data : [skuIds, gdsIds, shopId, platType, shopAuthId],
				async : false,
				dataType :  'json',
				type  : 'post',
				//traditional: true,  
				complete:function(returnInfo){

				},
				success : function(returnInfo) {
				}
			});
		},
		insertCatg : function(catgs){
			var catgs = {
				name:'catgs',
				value:catgs
			};
			var shopId = {
				name:'shopId',
				value:$('#shopId').val()
			};
			var platType = {
					name:'platType',
					value:$('#platType').val()
				};
			var shopAuthId = {
					name:'shopAuthId',
					value:$('#shopAuthId').val()
				};
			$.eAjax({
				url :  GLOBAL.WEBROOT + '/platauth/stocklimit/insertCatg',
				data : [catgs,shopId,platType,shopAuthId],
				async : false,
				dataType :  'json',
				type  : 'post',
				//traditional: true,  
				success : function(returnInfo) {
             	   $("#catgStockDataGridTable").gridReload();
				}
			});
		}
};