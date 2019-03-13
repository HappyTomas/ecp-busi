$(function(){
	$("#dataGridTable").initDT({
	    'pTableTools' : false,
		'pSingleCheckClean' : true,
		'pCheck' : 'multi',
        "sAjaxSource": GLOBAL.WEBROOT + '/coupinfo/grid',
        'params' : ebcForm.formParams($("#searchForm")),
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"id","sWidth":"80px","bSortable":false,bVisible:false},
			{ "mData": "siteName", "sTitle":"站点","sWidth":"120px","bSortable":false},
			{ "mData": "shopName", "sTitle":"店铺","sWidth":"120px","bSortable":false},
			{ "mData": "coupName", "sTitle":"优惠券名称","sWidth":"120px","bSortable":false},
			{ "mData": "coupTypeName", "sTitle":"优惠券类型","sWidth":"120px","bSortable":false},
			{ "mData": "coupValue", "sTitle":"优惠额度","sWidth":"90px","bSortable":false,"mRender":function(data,type,row){ 
				  if(data<=0){
					  return "抵用券";
				  }else{
					  var obj = JSON.parse(row.useRuleCode) || {};
					  var count = + obj["240"];
					  if(count && 0 < count){
						  var str = (data/1000).toFixed(1) + '折';
						  return str;
					  }
					  var str = (data/100).toFixed(2) + '';
						var intSum = str.substring(0,str.indexOf("."));//取到整数部分.replace( /\B(?=(?:\d{3})+$)/g, ',' )
						var dot = str.substring(str.length,str.indexOf("."));//取到小数部分
						var ret = intSum + dot;
						return ret+"元";
				  }
			}},
			{ "mData": "coupNum", "sTitle":"发行总量","sWidth":"90px","bSortable":false},
			{ "mData": "getNum", "sTitle":"已领取总量","sWidth":"90px","bSortable":false},
			{ "mData": "status", "sTitle":"状态","sWidth":"80px","bSortable":false,bVisible:false},
			{ "mData": "effTypeName", "sTitle":"日期类型","sWidth":"80px","bSortable":false},
			{ "mData": "activeTime", "sTitle":"生效时间","bSortable":false,"sWidth":"120px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "inactiveTime", "sTitle":"失效时间","bSortable":false,"sWidth":"120px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
		       { "mData": "coupCode", "sTitle":"优惠码","sWidth":"160px","bSortable":false, "mRender":function(data,type,row){
	                //优惠码
		        	if(row.ifCode=='1'){
		        		return row.coupCode;
		        	}
		        	return "";
	  			}},
			{ "mData": "statusName", "sTitle":"状态","sWidth":"100px","bSortable":false},
            { "mData": "info", "sTitle":"操作","sWidth":"160px","bSortable":false, "mRender":function(data,type,row){
				
                var _html="<a href='javascript:void(0)' onclick='coupinfoGrid.copy("+row.id+")'>&nbsp;复制 &nbsp;</a>";
                    _html=_html+"|</br> <a href='javascript:void(0)' onclick='coupinfoGrid.detail("+row.id+")'>&nbsp;详情 &nbsp;</a>";
                //生效
	        	if(row.status=='1'){
	        		 _html=_html+"|</br> <a href='javascript:void(0)' onclick='coupinfoGrid.invalid("+row.id+")'>&nbsp;失效 &nbsp;</a>";
	        	}
	        	//草稿
	        	if(row.status=='2'){
	        		 _html=_html+"|</br> <a href='javascript:void(0)' onclick='coupinfoGrid.edit("+row.id+")'>&nbsp;编辑 &nbsp;</a>";
	        		 _html=_html+"|</br> <a href='javascript:void(0)' onclick='coupinfoGrid.del("+row.id+")'>&nbsp;删除 &nbsp;</a>";
	    			 _html=_html+"|</br> <a href='javascript:void(0)' onclick='coupinfoGrid.valid("+row.id+")'>&nbsp;生效&nbsp;</a>";
	    		} 
  				return _html;
  			}} 
		 
        ],
        "eDbClick" : function(){
        	modifyBiz();
        },
        "eClick" : function(data){
        },
        onSuccess :function(){
        
        }
	});
	
	var modifyBiz = function(){
		var _ids = $('#dataGridTable').getCheckIds();
		if(_ids && _ids.length==1){
			coupinfoGrid.detail(_ids[0]);
		}else if(_ids && _ids.length>1){
			eDialog.alert('只能选择一个优惠券类型进行操作！');
		}else if(!_ids || _ids.length==0){
			 eDialog.alert('请选择至少选择一个优惠券类型进行操作！');
		}
	};
	
	$('#btnFormSearch').click(function(){
		var status = $('#status').val();
		if(status == "0"||status == "2"||status == "3"){
			$('#btn_code_batch_invalid').addClass("hidden");
		}else{
			$('#btn_code_batch_invalid').removeClass("hidden");
		}
		if(!$("#searchForm").valid()) return false;
		var _p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(_p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
	$('#btn_code_add').click(function(){
		$(this).attr("disabled","true");
		eNav.setSubPageText('创建优惠券');
		window.location.href = GLOBAL.WEBROOT+'/coupinfo/type';
	});
   //固定时间选择
	$('#effType').change(function(){
		var p1=$(this).children('option:selected').val();
		$('.queryDateCls').hide();
		//清空
		$('#activeTime').val('');
		$('#inactiveTime').val('');
		if(p1==1){
			$('.queryDateCls').show();
		}
	});
	//批量失效
	$('#btn_code_batch_invalid').click(function(){
		coupinfoGrid.batchInvalid();
	});
	
	
});



var coupinfoGrid = {
		//复制
		copy:function(id){
				window.location.href = GLOBAL.WEBROOT+'/coup/copy/'+id;
		},
		//失效
		invalid:function(id){
				eDialog.confirm("您确认要失效吗？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							$.eAjax({
								url : GLOBAL.WEBROOT + "/coupinfo/invalid",
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
		//详情
		detail:function(id){
			window.location.href = GLOBAL.WEBROOT+'/coup/view/'+id;
		},
		//生效
		valid:function(id){
			eDialog.confirm("您确认要生效吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/coupinfo/valid",
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
			window.location.href = GLOBAL.WEBROOT+'/coup/edit/'+id;
		},
		//删除
		del:function(id){
			eDialog.confirm("您确认删除吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/coupinfo/del",
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
		},
		
		//批量失效
		batchInvalid:function(){
			var val = $('#dataGridTable').getSelectedData();
			var _ids=[];
			for(k=0;k<val.length;k++){
				if(val[k].status == '1'){
					_ids.push(val[k].id);
				}
			}
		    if(_ids && _ids.length>0){
		    	var _data=_ids.join("#");
				eDialog.confirm("您确认要批量失效吗？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							$.eAjax({
								url : GLOBAL.WEBROOT + "/coupinfo/batchinvalid",
								data : {'ids':_data},
								datatype:'json',
								success : function(returnInfo) {
									eDialog.success('已批量失效！',{
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
			}else if(!_ids || _ids.length==0){
				 eDialog.alert('只能失效有效优惠券，请至少选择一张有效优惠券进行失效操作！');
			}
		}
	};
