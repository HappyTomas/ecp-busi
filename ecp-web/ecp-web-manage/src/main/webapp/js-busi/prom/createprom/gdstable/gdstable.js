$(function(){
	var doAction=$('#doAction').val();
	var promTypeCode=$('#promTypeCode').val();
	var cols=new Array();

	//编辑数据列
	var c1=  { "mData": "shopName", "sTitle":"店铺","sWidth":"80px","bSortable":false};
	//var c2=  { "mData": "siteName", "sTitle":"站点","sWidth":"70px","bSortable":false};
	//var c3=  { "mData": "promId", "sTitle":"促销编码","sWidth":"70px","bSortable":false};
	var c4=  { "mData": "mainCatgName", "sTitle":"分类","sWidth":"80px","bSortable":false};
	var c5=  { "mData": "gdsId", "sTitle":"商品编码","sWidth":"60px","bSortable":false};
	var c6=  { "mData": "skuId", "sTitle":"单品编码","sWidth":"60px","bSortable":false};
	var c7=  { "mData": "gdsName", "sTitle":"单品名称","sWidth":"100px","bSortable":false};
	var c8=  { "mData": "promCnt", "sTitle":"促销量","sWidth":"70px","bSortable":false,"mRender":function(data,type,row){
		if(row.isNeedStock == 'false')
		{
			return "虚拟商品";
		}else{
			   if(doAction=='view'){
				   return data;
			   }else{
				   return "<input type='text' class='number required' maxlength='10' data-value='"+data+"' data-sku='"+row.skuId+"' onblur='promImportData.submitPromCnt(this)' value='"+data+"'/><br/><span style='color:red'></span>";
			   }
		}
	}};
	var c9=  { "mData": "priceType", "sTitle":"价格类型","sWidth":"60px","bSortable":false,"mRender":function(data,type,row){
		if(data=="0")
			return "折扣率";
		else
			return "固定价";
		}};	
	var c10=  { "mData": "priceValue", "sTitle":"秒杀价","sWidth":"60px","bSortable":false,"mRender":function(data,type,row){
		return ebcUtils.numFormat(data/100, 2);
		}};		

	var c11=  { "mData": "info", "sTitle":"操作","sWidth":"50px","bSortable":false,"mRender":function(data,type,row){
    	var _html="<a href='javascript:void(0)' onclick='promImportData.del("+row.id+","+row.promId+")'>&nbsp;删除 &nbsp;</a>";
			return _html;
		}};

	cols.push(c1);
	//cols.push(c2);
	//cols.push(c3);
	cols.push(c4);
	cols.push(c5);
	cols.push(c6);
	cols.push(c7);
	cols.push(c8);
	if(promTypeCode=="1000000019"){
		cols.push(c9);
		cols.push(c10);
	}
	
	var doAction=$('#doAction').val();
	if('edit'==doAction){
		cols.push(c11);
	}
	
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	
	//表格数据初始化
	$("#dataGridTableTemp").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/gdsprom/gridlist',
        'params' : [{name:'promId',value:$('#promId').val()},{name:'gdsId',value:$('#gdsId').val()},{name:'skuId',value:$('#skuId').val()}],
        //指定列数据位置
        "aoColumns": cols,
        "rowCallback": function( row, data ) {
//            if ( $.inArray(data.DT_RowId, selected) !== -1 ) {
//                $(row).addClass('selected');
//            }
        },
        "eDbClick" : function(){
        	//modifyBiz();
        }
	});
	//查询
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTableTemp').gridSearch(p);
	});
   //重置
	$('#btnFormReset').click(function(){
		promImportData.reset();
	});
	
});


var promImportData = {
		//删除
		del:function(id,promId){
			eDialog.confirm("您确认删除该数据吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/gdsprom/del",
							data : {'id':id,'promId':promId},
							datatype:'json',
							success : function(returnInfo) {
								eDialog.success('已删除！',{
									buttons:[{
										caption:"确定",
										callback:function(){
											$('#dataGridTableTemp').gridReload();
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
		//重置
		reset:function(){
			ebcForm.resetForm('#searchForm');
		},
		//提交促销数量
		submitPromCnt:function(obj){
			//查看 直接返回
			if($('#doAction').val()=='view'){
				return;
			}
			$(obj).next().next().html('');
			var v=$.trim($(obj).val());
			//验证是否有效数字
			if(v==null ||  v==""){
				$(obj).next().next().html('不能为空，请输入有效的数字');
				return;
			}
			if(isNaN(v)){
				$(obj).next().next().html('请输入有效的数字');
				return;
			}
			var r = /^[0-9]*[1-9][0-9]*$/;//正整数 
			if(!r.test(v)){
				$(obj).next().next().html('请输入有效的正整数');
				return;
			}
           var oldValue=$(obj).attr("data-value");
           //验证是否有变化 无变化不处理。有变化需要更新初始化
           if(oldValue != v){
	       		var _promId=$('#promId').val();
	       		var _skuId=$(obj).attr("data-sku");
	       		$.eAjax({
	       			url : GLOBAL.WEBROOT + "/gdsprom/updateCnt",
	       			data:[{name:'promId',value:_promId},{name:'skuId',value:_skuId},{name:'promCnt',value:v}],
	       			"dataType": "json",
	       			success : function(returnInfo) {
	        				//成功返回
	        				if(returnInfo.resultFlag=='ok'){
	        					 //更新 当前对象data-value属性
	        					$(obj).attr("data-value",promCnt);
	        					$(obj).next().next().html('保存成功');
	        				}else{
	        					$(obj).next().next().html(returnInfo.resultMsg);
	        				}
	        			},
	        			exception : function(returnInfo) {
	        				$(obj).next().next().html(returnInfo.resultMsg);
	        			}
	       	    });
           }
		}
	};