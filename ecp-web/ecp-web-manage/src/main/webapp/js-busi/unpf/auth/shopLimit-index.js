$(function(){
	$('#btnGdsBlackDelete').click(function(){
		var _val = $('#gdsDataGridTable').getSelectedData();
		var deleteIdArray = new Array();
	    for(var i=0;i<_val.length;i++){
	    	deleteIdArray.push(_val[i].id);
	    }
	    shoplimit.del(deleteIdArray,"gds");
	});
	$('#btnCatgBlackDelete').click(function(){
		var _val = $('#catgDataGridTable').getSelectedData();
		var deleteIdArray = new Array();
	    for(var i=0;i<_val.length;i++){
	    	deleteIdArray.push(_val[i].id);
	    }
	    shoplimit.del(deleteIdArray,"catg");
	});
	
	//黑名单分类选择 window
	$('#btnCatgBlackOpenWindow').click(function(){
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
                    	   shoplimit.insertCatg(catgCodeArray);
                    	   //加载黑名单分类
                  }
            }
        });
	});
	//黑名单商品选择 window
	$('#btnGdsBlackOpenWindow').click(function(){
		bDialog.open({
			title : '黑名单商品选择',
			width : 800,
			height : 560,
			url : GLOBAL.WEBROOT + "/platauth/shoplimit/opengds?shopId="+$('#shopId').val()+"&siteId="+$('#siteId').val(),
			callback:function(data){
				   //确定 按钮_if_query==1
				   if(data && data.results && data.results[0]._if_query=='1'){
					   var gdsIds = data.results[0].gdsIds;
					   //保存黑名单商品
					   shoplimit.insertGds(gdsIds);
					   //加载黑名单商品
				   }
				  
			}
		});
	});
	
	
	$("#gdsDataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheck' : "multi",
        "sAjaxSource": GLOBAL.WEBROOT + '/platauth/shoplimit/gdsgrid',
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
	        		 _html=_html+"<a href='javascript:void(0)' onclick='shoplimit.delGds("+row.id+")'>删除 </a>";

  				return _html;
  			}} 
        ],
        "eDbClick" : function(){
        	
        }
	});
	$("#catgDataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheck' : "multi",
        "sAjaxSource": GLOBAL.WEBROOT + '/platauth/shoplimit/catggrid',
        'params' : [{name:"shopId",value:$('#shopId').val()},
                    {name:"platType",value:$('#platType').val()},
                    {name:"shopAuthId",value:$('#shopAuthId').val()}],
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"编码", "sClass":"center", "bSortable":false},
            { "mData": "catgCode", "sTitle":"分类编码", "sClass":"center", "bSortable":false},
            { "mData": "catgCodeName", "sTitle":"分类名称", "sClass":"center","bSortable":false},
			{ "mData": "info", "sTitle":"操作", "sClass":"center","bSortable":false, "mRender":function(data,type,row){
				
                var _html="<a href='javascript:void(0)' onclick='shoplimit.delCatg("+row.id+")'>删除 </a>";

  				return _html;
  			}} 
        ],
        "eDbClick" : function(){
        	
        }
	});
});
var shoplimit = {
		delGds : function(id){
			var ids = new Array();
			ids.push(id);
			shoplimit.del(ids,"gds");
		},
		delCatg : function(id){
			var ids = new Array();
			ids.push(id);
			shoplimit.del(ids,"catg");
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
							url :  GLOBAL.WEBROOT + '/platauth/shoplimit/delete',
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
												$("#gdsDataGridTable").gridReload();
											}else if(type == "catg"){
												$("#catgDataGridTable").gridReload();
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
				url :  GLOBAL.WEBROOT + '/platauth/shoplimit/insertGds',
				data : [gdsIds, shopId, platType, shopAuthId],
				async : false,
				dataType :  'json',
				type  : 'post',
				//traditional: true,  
				success : function(returnInfo) {
					   $("#gdsDataGridTable").gridReload();
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
				url :  GLOBAL.WEBROOT + '/platauth/shoplimit/insertSku',
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
				url :  GLOBAL.WEBROOT + '/platauth/shoplimit/insertCatg',
				data : [catgs,shopId,platType,shopAuthId],
				async : false,
				dataType :  'json',
				type  : 'post',
				//traditional: true,  
				success : function(returnInfo) {
             	   $("#catgDataGridTable").gridReload();
				}
			});
		}
};