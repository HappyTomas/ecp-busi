$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false, 
        'params': [{name:'status',value:$("#status").val()}],
        "sAjaxSource": GLOBAL.WEBROOT + '/myprom/gridlist',
        'params' : ebcForm.formParams($("#searchForm")),
        //指定列数据位置
        "aoColumns": [
			{ "mData": "id", "sTitle":"编码","sWidth":"80px",/*"autoWidth":true,*/"bSortable":false},
			 { "mData": "siteName", "sTitle":"站点","sWidth":"100px",/*"autoWidth":true,*/"bSortable":false},
			 { "mData": "shopName", "sTitle":"店铺名称","sWidth":"100px",/*"autoWidth":true,*/"bSortable":false},
			{ "mData": "promTheme", "sTitle":"促销名称","sWidth":"100px",/*"autoWidth":true,*/"bSortable":false},
			{ "mData": "promTypeName", "sTitle":"促销类型","sWidth":"100px",/*"autoWidth":true,*/"bSortable":false},
			{ "mData": "promTypeCode", "sTitle":"促销类型","sWidth":"100px",/*"autoWidth":true,*/"bSortable":false,"bVisible":false},
			//{ "mData": "promTypeShow", "sTitle":"促销展示",/*"autoWidth":true,*/"bSortable":false},
			{ "mData": "status", "sTitle":"状态","bSortable":false,"bVisible":false},
			{ "mData": "statusName", "sTitle":"状态","sWidth":"80px","bSortable":false},
			//{ "mData": "priority", "sTitle":"优先级",/*"autoWidth":true,*/"bSortable":false},
			{ "mData": "startTime", "sTitle":"促销开始时间","bSortable":false,"sWidth":"120px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "endTime", "sTitle":"促销截止时间","bSortable":false,"sWidth":"120px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
		/*	{ "mData": "showStartTime", "sTitle":"开始展示时间","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "showEndTime", "sTitle":"截止展示时间","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}}*/
			{ "mData": "ifFreePost", "sTitle":"是否免邮","bSortable":false,"mRender": function(data,type,row){
			        if(data=="1"){
			        	return "免邮";
			        }else{
			        	return "";
			        }
			}},
			{ "mData": "info", "sTitle":"操作","sWidth":"160px","bSortable":false, "mRender":function(data,type,row){
				
                var _html="<a href='javascript:void(0)' onclick='mypromgrid.copy("+row.id+")'>&nbsp;复制 &nbsp;</a>";
                    _html=_html+"| <a href='javascript:void(0)' onclick='mypromgrid.detail("+row.id+")'>&nbsp;详情 &nbsp;</a>";
                //生效
	        	if(row.status=='10'){
	        		 _html=_html+"| <a href='javascript:void(0)' onclick='mypromgrid.invalid("+row.id+")'>&nbsp;失效 &nbsp;</a>";
	        		 if(row.promTypeCode!='1000000011'&& row.promTypeCode !='1000000013' && row.promTypeCode!='1000000014'){
	        			 _html=_html+"| <a href='javascript:void(0)' onclick='mypromgrid.importData("+row.id+")'>批量导入商品 </a>";
	        		 }
	        		 _html=_html+"| <a href='javascript:void(0)' onclick='mypromgrid.fresh("+row.id+")'>搜索更新 </a>";
	        	}
	        	   //失效
	        	if(row.status=='20'){
	        		 _html=_html+"| <a href='javascript:void(0)' onclick='mypromgrid.fresh("+row.id+")'>搜索更新 </a>";
	        	}
	        	//草稿  待审核 
	        	if(row.status=='00' || row.status=='40'){
	        		 _html=_html+"| <a href='javascript:void(0)' onclick='mypromgrid.edit("+row.id+")'>&nbsp;编辑 &nbsp;</a>";
	        		 _html=_html+"| <a href='javascript:void(0)' onclick='mypromgrid.del("+row.id+")'>&nbsp;删除 &nbsp;</a>";
	        		 if(row.promTypeCode!='1000000011'&&row.promTypeCode !='1000000013' && row.promTypeCode!='1000000014'){
	        			 _html=_html+"| <a href='javascript:void(0)' onclick='mypromgrid.importData("+row.id+")'>批量导入商品 </a>";
	        		 }
	        	}
	        	//操作状态可发布
	    		if(row.status=='00'){
	    			 _html=_html+"| <a href='javascript:void(0)' onclick='mypromgrid.publish("+row.id+")'>&nbsp;发布&nbsp;</a>";
	    		} 
  				return _html;
  			}}
        ],
        "eDbClick" : function(){
    		var ids = $('#dataGridTable').getCheckIds();
    		if(ids && ids.length==1){
    			window.location.href = GLOBAL.WEBROOT+'/createprom/view/'+ids[0];
    		}else if(ids && ids.length>1){
    			eDialog.alert('只能选择一个促销进行操作！');
    		}else if(!ids || ids.length==0){
    			eDialog.alert('请选择至少选择一个促销进行操作！');
    		}
        },
        "eClick" : function(data){ }
	});
	
 
	
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	
});

var mypromgrid = {
		//复制
		copy:function(id){
				window.location.href = GLOBAL.WEBROOT+'/createprom/copy/'+id;
		},
		//失效
		invalid:function(id){
				eDialog.confirm("您确认要失效该促销吗？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							$.eAjax({
								url : GLOBAL.WEBROOT + "/myprom/valid",
								data : {'id':id},
								datatype:'json',
								success : function(returnInfo) {
									eDialog.success('已失效！',{
										buttons:[{
											caption:"确定",
											callback:function(){
												$('#dataGridTable').gridReload();
									        }
										}]
									}); 
								}
							});
							
						}
					},{
						caption : '取消',
						callback : $.noop
					}]
				});
		},
		//搜索更新
		fresh:function(id){
				eDialog.confirm("您确认要更新搜索吗？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							$.eAjax({
								url : GLOBAL.WEBROOT + "/myprom/fresh",
								data : {'id':id},
								datatype:'json',
								success : function(returnInfo) {
									eDialog.success('更新搜索成功！',{
										buttons:[{
											caption:"确定",
											callback:function(){
									        }
										}]
									}); 
								}
							});
							
						}
					},{
						caption : '取消',
						callback : $.noop
					}]
				});
		},
		//导入商品 弹出新页面
		importData:function(id){
			bDialog.open({
				title : '促销商品批量导入',
				width : 900,
				height : 560,
				url : GLOBAL.WEBROOT + "/gdsprom/importGds?promId="+id,
				params : {
				},
				onHidden:function(){
					//$("#dataGridTable").gridReload();
				},
				callback:function(data){
					//$("#dataGridTable").gridReload();
				}
			});
		
		},
		//详情
		detail:function(id){
			eNav.setSubPageText('促销信息详情');
			window.location.href = GLOBAL.WEBROOT+'/createprom/view/'+id;
		},
		//发布
		publish:function(id){
			eNav.setSubPageText('发布促销信息');
			eDialog.confirm("您确认要发布该促销吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/myprom/publish",
							data : {'id':id},
							datatype:'json',
							success : function(returnInfo) {
								eDialog.success('发布成功！',{
									buttons:[{
										caption:"确定",
										callback:function(){
											$('#dataGridTable').gridReload();
								        }
									}]
								}); 
							}
						});
						
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		},
		//编辑
		edit:function(id){
			eNav.setSubPageText('编辑促销信息');
			window.location.href = GLOBAL.WEBROOT+'/createprom/edit/'+id;
		},
		//删除
		del:function(id){
			eDialog.confirm("您确认删除该促销吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/myprom/del",
							data : {'id':id},
							datatype:'json',
							success : function(returnInfo) {
								eDialog.success('已删除！',{
									buttons:[{
										caption:"确定",
										callback:function(){
											$('#dataGridTable').gridReload();
								        }
									}]
								}); 
							}
						});
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		}
	};
