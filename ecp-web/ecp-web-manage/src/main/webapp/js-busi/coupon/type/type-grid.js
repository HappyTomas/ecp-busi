$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false, 
        "sAjaxSource": GLOBAL.WEBROOT + '/coupontype/grid',
        'params' : ebcForm.formParams($("#searchForm")),
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"id","sWidth":"80px","bSortable":false,bVisible:false},
			{ "mData": "coupTypeName", "sTitle":"类型名称","sWidth":"160px","bSortable":false},
			{ "mData": "status", "sTitle":"状态","sWidth":"80px","bSortable":false,bVisible:false},
			{ "mData": "statusName", "sTitle":"状态","sWidth":"100px","bSortable":false},
			{ "mData": "sortNo", "sTitle":"排序","sWidth":"80px","bSortable":false},
			{ "mData": "createTime", "sTitle":"创建时间","bSortable":false,"sWidth":"100px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
           { "mData": "info", "sTitle":"操作","sWidth":"160px","bSortable":false, "mRender":function(data,type,row){
				
                var _html="";
                //生效  可编辑 可失效
	        	if(row.status=='1'){
	        		 _html=_html+"<a href='javascript:void(0)' onclick='coupTypeGridgrid.invalid("+row.id+")'>&nbsp;失效 &nbsp;</a>";
	        	}
	        	   //失效 可编辑 可生效
	        	if(row.status=='0' || row.status=='2'){
	        		 _html=_html+"<a href='javascript:void(0)' onclick='coupTypeGridgrid.edit("+row.id+")'>&nbsp;编辑 &nbsp;</a>";
	        		 _html=_html+"| <a href='javascript:void(0)' onclick='coupTypeGridgrid.valid("+row.id+")'>生效 </a>";
	        	}
  				return _html;
  			}}
        ],
        "eDbClick" : function(){
        	modifyBiz();
        },
        "eClick" : function(data){
         
        }
	});
	
	var modifyBiz = function(){
		var _ids = $('#dataGridTable').getCheckIds();
		if(_ids && _ids.length==1){
			
			//已经失效 不可编辑
			var _val = $('#dataGridTable').getSelectedData();
			if(_val[0].status=='0'){
				eDialog.alert("已经失效，不能编辑！");
				$('#btn_code_modify').removeAttr("disabled");
				return ;
			}
			
			window.location.href = GLOBAL.WEBROOT+'/coupontype/edit?id='+_val[0].id;
		}else if(_ids && _ids.length>1){
			eDialog.alert('只能选择一个优惠券类型进行操作！');
			$('#btn_code_modify').removeAttr("disabled");
		}else if(!_ids || _ids.length==0){
			 eDialog.alert('请选择至少选择一个优惠券类型进行操作！');
			 $('#btn_code_modify').removeAttr("disabled");
		}
	};
	
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var _p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(_p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
	$('#btn_code_add').click(function(){
		$(this).attr("disabled","true");
		eNav.setSubPageText('新增类型');
		window.location.href = GLOBAL.WEBROOT+'/coupontype/add';
	});
});


var coupTypeGridgrid = {
		//失效
		invalid:function(id){
				eDialog.confirm("您确认要失效该类型吗？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							$.eAjax({
								url : GLOBAL.WEBROOT + "/coupontype/invalid",
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
		//发布
		valid:function(id){
			eDialog.confirm("您确认要生效该类型吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/coupontype/valid",
							data : {'id':id},
							datatype:'json',
							success : function(returnInfo) {
								eDialog.success('已经生效！',{
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
			window.location.href = GLOBAL.WEBROOT+'/coupontype/edit?id='+id;
		}
	};