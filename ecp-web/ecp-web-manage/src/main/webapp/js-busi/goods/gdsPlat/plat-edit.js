$(function() {
	
	
$("#dataGridTable").initDT({
		

		'overflow-y' : scroll,
        'pTableTools' : false,
        'pSingleCheckClean' : true,
        'pCheckColumn' : false, 
        'pCheck' : 'multi',
		"sAjaxSource" : GLOBAL.WEBROOT + '/gdsplat/addlist',
		
	
		// 指定列数据位置
		"aoColumns" : [{
					"mData" : "id",
					"sTitle" : "商品编码",
					"sWidth" : "80px",
					"sClass" : "center",
					"bSortable" : true
				}, {
					"mData" : "gdsName",
					"sTitle" : "商品名称",
					"sWidth" : "80px",
					"sClass" : "center",
					"bSortable" : false
				},
				
				{
					
					"mData": "id",
					"sTitle" : "操作",
					"sWidth" : "80px",
					"sClass" : "center",
					"bSortable" : false,
					"mRender" : function(data, type, row) {
						var optStr = "<span><a href='javascript:void(0)' onclick='GdsPlat.gdsSelect(\""+row.id+"\",\""+row.gdsName+"\")'>选择</a></span>";
					
						return optStr;
					}
				}

		],
		"params" : [{
			name : 'shopId',
			value : $("#shopId").val()
		}]

	});

	$('#isDefault').click(function(){
		$('#relationGroup').html("");
	});
	
	$('#noDefault').click(function(){
		$('#relationGroup').html("<div class='formSep'>" +
				"<div class='control-group' >"+
					"<label class='control-label'>关联分类：</label>"+
							"<div class='controls'>"+
								"<input type='text' id='relatedCatgName' name='relatedCatgName' class='input-large required' placeholder='选择关联分类' value='' readonly='readonly'/>"+
									"<input type='hidden' id='relatedCatgCode' name='relatedCatgCode' value=''/>"+
											"<button type='button' class='btn btn-info' id='catgChoose'><i class='icon-magic'></i>选择</button>"+
											"<span class='help-inline' style='color: red;'>默认配置不需要选择关联分类，否则关联分类必选</span>"+
										"</div>"+
                                    "</div>"+
								"</div>");
	});

	$("#catgChoose").live('click',function(){
		bDialog.open({
            title : '分类选择',
            width : 350,
            height : 550,
            url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=1&catlogId=1",
            callback:function(data){
                     if(data && data.results && data.results.length > 0 ){
                    var _catgs = data.results[0].catgs;
                    for(var i in _catgs){
                             $("#relatedCatgName").val(_catgs[i].catgName);
             				$("#relatedCatgCode").val(_catgs[i].catgCode);
                    }
                                     }
            }
        });
	});
	$('#btnFormSave').click(function(){ 
		if(!$("#baseInfoForm").valid())return false;
		
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsplat/editsave",
			data : ebcForm.formParams($("#baseInfoForm")),
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){;
				eDialog.success('保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							window.location.href = GLOBAL.WEBROOT + "/gdsplat";
						}
					}]
				}); 
			}else{
				eDialog.alert(returnInfo.resultMsg);
			}
			}
		});
	});
	
	



	
	$('#btnFormSearch').click(function(){
		if(!$("#relationForm").valid()) return false;
		var p = ebcForm.formParams($("#relationForm"));
		$('#dataGridTable').gridSearch(p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#relationForm');
	});
	
	$('#btnReturn').click(function(){
		window.location.href = GLOBAL.WEBROOT
		+ '/gdsplat';
	});
	
	

	

});

var GdsPlat = {
		gdsSelect : function (Id,gdsName){
			
				$("#gdsId").val(Id);
				$("#relatedName").val(gdsName);
				
				
			
		},
		
		choosePlatCatg : function(){
			bDialog.open({
	            title : '分类选择',
	            width : 350,
	            height : 550,
	            url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=1&catlogId=1",
	            callback:function(data){
	                     if(data && data.results && data.results.length > 0 ){
	                    var _catgs = data.results[0].catgs;
	                    for(var i in _catgs){
	                             $("#catgName").val(_catgs[i].catgName);
	             				$("#platCatgs").val(_catgs[i].catgCode);
	                    }
	                                     }
	            }
	        });
		},
		

};

