$(function() {

	//获得当前弹出窗口对象
	//var _dlg = bDialog.getDialog();
	
	
	$("#dataGridTable")
			.initDT(
					{
						'pTableTools' : false,
						'pCheckColumn' : false,
						'pSingleCheckClean' : true,
						'pCheck' : 'multi',
						"sAjaxSource" : GLOBAL.WEBROOT
								+ '/gdscatalog/cataloglist',
						'pLengthMenu ' : [1],
						// 指定列数据位置
						"aoColumns" : [
								{
									"mData" : "id",
									"sTitle" : "",
									"sWidth" : "80px",
									"bSortable" : true,
									"bVisible" : false
								},
								{
									"mData" : "catlogCode",
									"sTitle" : "目录编码",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : true

								},
								{
									"mData" : "catlogName",
									"sTitle" : "目录名称",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "statusName",
									"sTitle" : "状态",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "status",
									"sTitle" : "状态",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false,
									"bVisible" : false
								}
						],
						"eClick" : function(data,row){
							var ids = CatalogSelect.getRelations();
				        	if(row.hasClass('row_selected')){
				        		if(-1 == $.inArray(''+data.id,ids)){
				        			ids.push(data.id);
				        		}
				        	}else{
				        		ids = CatalogSelect.remove(ids,data.id);
				        	}
				        	CatalogSelect.setRelationsVal(CatalogSelect.convertToString(ids));
				        },
				        "eDrawComplete" : function(){
				        	// 选中已经关联目录
					    	var ids = CatalogSelect.getRelations();
					    	var tr = $("#dataGridTable tbody tr");
					    	$.each(tr, function(i,n){
					    		if(-1 != $.inArray($(this).attr('id'),ids)){
					    			$(this).trigger('click');
					    		}
					    	});
				        },
					    "onSuccess" : function(){
					    }
					});

	// 查询
	$('#btnFormSearch').click(function() {
		if (!$("#searchForm").valid())
			return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	// 重置
	$('#btnFormReset').click(function() {
		ebcForm.resetForm('#searchForm');
	});
	
	
	$('#btn_add_catlog').click(function(){
		var relations = $('#relations').val();
		bDialog.closeCurrent({
			"relations":relations
		});
	});
	
	$('#btn_close').click(function(){
		bDialog.closeCurrent();
	});
	
	var CatalogSelect = {
			
		/**
		* 从对象数组中删除属性为objPropery，值为objValue元素的对象
		* @param Array arrPerson  数组对象
		* @param String objPropery  对象的属性
		* @param String objValue  对象的值
		* @return Array 过滤后数组
		*/
		remove : function(arrPerson,objValue)
		{
		   return $.grep(arrPerson, function(cur,i){
		          return cur!=objValue;
		       });
		},
		/**
		 * 获取已关联目录数组.
		 * @returns
		 */
		getRelations : function(){
			// 获取已关联目录字符串.
	        var val = $('#relations').val();
	        var ids = val.split(',');
	        return ids;
		},
		/**
		 * 将数组转换成逗号分隔字符串.
		 * @param ids
		 */
		convertToString : function(ids){
			var str = "";
		    if(null != ids){
		    	$.each(ids, function(i,n){
		    		if('' != n){
		    	       str = str + n + ',';
		    		}
		    	});
		    }
		    if(0 < str.length){
		    	return str.substr(0, str.length -1);
		    }else{
		    	return str;
		    }
		},
		setRelationsVal : function(val){
			$('#relations').val(val);
		}
	};
	
	

});