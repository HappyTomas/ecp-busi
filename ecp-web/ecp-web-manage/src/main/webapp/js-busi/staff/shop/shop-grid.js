$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/shop/gridlist',
        "params":[{name:"companyId",value:$('#ids').val()}],
        //指定列数据位置
        "aoColumns": [
            { "mData": "logoPath", "sTitle":"店铺LOGO","sWidth":"40px","bSortable":false, "mRender": function(data,type,row){
//            	return "<img src="+row.logoPathURL+" style='width:50px;height:50px'  onerror=this.src="+IMG_PATH +"/yf-tpl.jpg"+"\>";
            	var url = row.logoPathURL;
            	if(!url){
            		return'<img style="width:50px;height:50px"  src="'+IMG_PATH +'/staff/default.png" \>';
            	}else{
            		return '<img style="width:50px;height:50px"  src="'+url+'" \>';
            	}
            	
            	
            	
            }},  
            { "mData": "id", "sTitle":"店铺编码","sWidth":"80px","bSortable":false},      
            { "mData": "shopName", "sTitle":"店铺名称","sWidth":"80px","bSortable":false},    
            { "mData": "shopFullName", "sTitle":"店铺全称","sWidth":"80px","bSortable":false,"bVisible":false}, 
			{ "mData": "shopStatus", "sTitle":"店铺状态","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				if(data=='1'){
					return '有效';
				}else{
					return '失效';
				}
			}},
			{ "mData": "hotShowSupported", "sTitle":"是否热门店铺","sWidth":"80px","bSortable":false,"bVisible":false,"mRender": function(data,type,row){
				if(data=='1'){
					return '是';
				}else{
					return '否';
				}
			}},
			{ "mData": "hotShowSort", "sTitle":"热门店铺排序","sWidth":"80px","bSortable":false,"bVisible":false,"mRender": function(data,type,row){
				if (row.hotShowSupported != '1') {
					return '-';
				} else {
					return '<input type="text" maxLength="4" style="width:50px;" onBlur="saveShowSort(this,'+row.id+');" value="'+data+'"/>';
				}
			}},
			{ "mData": "companyId", "sTitle":"企业id","sWidth":"80px","bSortable":false,"bVisible":false},
			{ "mData": "companyName", "sTitle":"所属企业","sWidth":"80px","bSortable":false},
			{ "mData": "linkPerson", "sTitle":"店铺联系人","sWidth":"80px","bSortable":false,"bVisible":false}, 
			{ "mData": "linkMobilephone", "sTitle":"联系人手机","sWidth":"80px","bSortable":false,"bVisible":false}, 
			{ "mData": "linkEmail", "sTitle":"联系人邮件","sWidth":"80px","bSortable":false,"bVisible":false}, 
			{ "mData": "isUseVip", "sTitle":"是否支持店铺VIP","sWidth":"80px","bSortable":false,"bVisible":false},
			{ "mData": "offlineSupported", "sTitle":"是否支持线下支付","sWidth":"80px","bSortable":false,"bVisible":false},
			{ "mData": "createStaff", "sTitle":"店铺申请人","sWidth":"80px","bSortable":false,"bVisible":false},
			{ "mData": "id", "sTitle":"操作","sWidth":"80px","bSortable":false,"mRender":function(data,type,row){
				
				if(row.isEnter=='0'){
					return "";
				}
				
				return '<a href="javascript:void(0)"  onClick="shopShipEdit.editshopship('+row.id+')">运费配置</a>';
			}}
			/*
			{ "mData": "serialNumber", "sTitle":"手机号码","sWidth":"80px","bSortable":false},
			{ "mData": "checkDate", "sTitle":"审核时间","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd");
			}},

			{ "mData": "sortno", "sTitle":"排序","sWidth":"40px","bSortable":false}
			 */
        ],
        "eDbClick" : function(){
        	//modifyBiz();
        }
	});


	var modifyBiz = function(){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			$.eAjax({
				url : GLOBAL.WEBROOT + "/shop/edit",
				data : val,
				datatype:'json',
			});
			
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	};
	
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
	$('#btn_code_edit').click(function(){
//		modifyBiz();
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			eNav.setSubPageText("编辑店铺信息");
			var shopId = val[0].id;
			$('#shopId').val(shopId);
			$('#editFrom').submit();
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
		
		//window.location.href = GLOBAL.WEBROOT+'/shop/edit';
	});

	$('#btn_code_active').click(function(){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			if(val[0].shopStatus != 1){
				eDialog.confirm("您确认要生效该店铺吗？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							eDialog.alert('生效成功！');
							
							$.eAjax({
								url : GLOBAL.WEBROOT + "/shop/active",
								data : {'id':val[0].id},
								datatype:'json',
								success : function(returnInfo) {
									eDialog.success('生效成功！',{
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
		}else
			{eDialog.alert('该店铺已经是生效状态！');}
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	});
	$('#btn_code_valid').click(function(){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			if(val[0].shopStatus != 0){
				eDialog.confirm("您确认要失效该店铺吗？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							eDialog.alert('失效成功！');
							
							$.eAjax({
								url : GLOBAL.WEBROOT + "/shop/valid",
								data : {'id':val[0].id},
								datatype:'json',
								success : function(returnInfo) {
									eDialog.success('失效成功！',{
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
			}else
			{eDialog.alert('该店铺已经是失效状态！');}
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	});
	
	
	
	$('#btn_express_edit').click(function(){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
		    bDialog.open({
				title : '店铺物流管理',
				width : 600,
				height : 450,
				params : {
					'shopId':val[0].id,
					'shopName':val[0].shopName
					},
				url : GLOBAL.WEBROOT+'/shop/express?shopId='+val[0].id,
				callback:function(data){
						$('#dataGridTable').gridReload();
					}
			});
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}	
	});

	
});
var shopShipEdit = {
		
		editshopship:function(shopid){
			    bDialog.open({
					title : '运费模版配置',
					width : 900,
					height : 300,
					params : {
						'shopid':shopid,
						},
					url : GLOBAL.WEBROOT+'/shop/shipgrid?shopid='+shopid,
					callback:function(data){
							$('#dataGridTable').gridReload();
						}
				});
		}
}
//保存热门店铺排序
function saveShowSort(obj,shopId) {
	var reg = /^[0-9]*[1-9][0-9]*$/ ; 
	//判断是否为数字
	if (!reg.test(obj.value)) {
		eDialog.alert('请输入正整数！');
		return;
	}
	$.eAjax({
		url : GLOBAL.WEBROOT + "/shop/editshop",
		data : {'hotShowSort':obj.value,'id':shopId},
		datatype:'json',
		success : function(returnInfo) {
			
		}
	});
}