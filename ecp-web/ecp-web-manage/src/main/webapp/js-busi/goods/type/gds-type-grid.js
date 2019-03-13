$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : true,
        'pCheck' : 'single', //multi  single
        'pCheckColumn' : true, //是否显示单选/复选框的列
        "sAjaxSource": GLOBAL.WEBROOT + '/goods/type/gridlist',
        //指定列数据位置
        "aoColumns": [
			{ "mData": "typeCode", "sTitle":"类型编码","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "typeName", "sTitle":"类型名称","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "ifNeedstock", "sTitle":"库存策略","sWidth":"80px","sClass": "center","bSortable":false,
				"mRender": function(data,type,row){
					if(row.ifNeedstock=='1'){
						return "有库存";
					}else{
						return "无库存";
					}
				}			
			},
			{ "mData": "ifBuyonce", "sTitle":"购买策略","sWidth":"80px","sClass": "center","bSortable":false,
				"mRender": function(data,type,row){
					if(row.ifBuyonce=='1'){
						return "允许多次购买";
					}else{
						return "不允许多次购买";
					}
				}		
			},
			{ "mData": "ifFree", "sTitle":"邮费策略","sWidth":"80px","sClass": "center","bSortable":false,
				"mRender": function(data,type,row){
					if(row.ifFree=='1'){
						return "免邮";
					}else{
						return "不免邮";
					}
				}					
			},
			{ "mData": "status", "sTitle":"状态","sWidth":"80px","sClass": "center","bSortable":false,
				"mRender": function(data,type,row){
					if(row.status=='1'){
						return "启用";
					}else{
						return "禁用";
					}
				}		
			},
			{ "mData": "status","sTitle":"操作","sWidth":"80px","sClass": "center",
				"mRender": function(data,type,row){
					if(row.status=='1'){
						var opts="<a href='javascript:void(0);' class='disableType' typeId='"+row.id+"'>禁用</a>"+" | "+
						"<a href='javascript:void(0);' class='editType' typeId='"+row.id+"'>编辑</a>"
						return opts;
					}else{
						var opts="<a href='javascript:void(0);' class='enableType' typeId='"+row.id+"'>启用</a>";
						return opts;
					}
				}
			}
        ],
        
        //默认查询条件
        "params" : [{name : 'status',value : '1'}]
	});
	
	//查询
	$('#btnFormSearch').click(function(){
		//if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		p.push({
			'name' : 'typeCode',
			'value' : $("#typeCode").val()
		});
		p.push({
			'name' : 'typeName',
			'value' : $("#typeName").val()
		});
		$('#dataGridTable').gridSearch(p);
	});
	
	//重置
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		$("#typeCode").val("");
		$("#typeCode").val("");
	});
	
	//Tab换页查询
	$("#myTab").children().each(function(){
		$(this).bind("click",function(){
			var id =$(this).attr("id");
			if(id == "tab1"){
				$("#btn_type_prop").show();
				GridUtil.gridList("1");
			    $('#myTab li:eq(0) a').tab('show');
			}else if(id == "tab2"){
				$("#btn_type_prop").hide();
				GridUtil.gridList("0");
				$('#myTab li:eq(1) a').tab('show');
			}
		});
	});
	
	//启用
	$(".enableType").live('click',function(e){
		GridUtil.enableType($(this).attr('typeId'));
	});
	
	//禁用
	$(".disableType").live('click',function(e){
		GridUtil.disableType($(this).attr('typeId'));
	});
	
	//新增类型
	$('#btn_type_add').click(function() {
		bDialog.open({
			title : '新增类型',
			width : 500,
			height : 600,
			url : GLOBAL.WEBROOT + '/goods/type/openAddType',
			callback : function() {
				$('#dataGridTable').gridReload();
			}
		});
	});
	
	//编辑类型
	$(".editType").live('click',function(e){
		bDialog.open({
			title : '编辑类型',
			width : 500,
			height : 600,
			url : GLOBAL.WEBROOT + '/goods/type/openEditType?id=' + $(this).attr('typeId'),
			callback : function() {
				$('#dataGridTable').gridReload();
			}
		});
	});
	
	//配置属性
	$('#btn_type_prop').click(function() {
		eNav.setSubPageText('配置属性');
		var ids = $('#dataGridTable').getCheckIds();
		if (ids && ids.length == 1) {
			var datas = $('#dataGridTable').getSelectedData();
			var data = datas[0];
			window.location.href = GLOBAL.WEBROOT
					+ '/goods/type/configProp?id=' + data.id;
		} else if (ids && ids.length > 1) {
			eDialog.alert('只能选择一个项目进行操作！');
		} else if (!ids || ids.length == 0) {
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	});
	
	
});
var GridUtil = {
	
	enableType : function(typeId){
		var param = {
			id : typeId
		};
		$.eAjax({
			url : GLOBAL.WEBROOT + "/goods/type/enableType",
			data : param,
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					eDialog.success('启用成功！'); 
					GridUtil.gridList("0");
				}else{
					eDialog.error('启用失败！，原因是：'+returnInfo.resultMsg);
				}
			}
		});
	},
	
	disableType : function(typeId){
		var param = {
			id : typeId
		};
		$.eAjax({
			url : GLOBAL.WEBROOT + "/goods/type/disableType",
			data : param,
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					eDialog.success('禁用成功！'); 
					GridUtil.gridList("1");
				}else{
					eDialog.error('禁用失败！，原因是：'+returnInfo.resultMsg);
				}
			}
		});
	},
	
	gridList : function(status){
		var p = ebcForm.formParams($("#searchForm"));
		p.push({ 'name': 'status','value' : status });
		p.push({
			'name' : 'typeCode',
			'value' : $("#typeCode").val()
		});
		p.push({
			'name' : 'typeName',
			'value' : $("#typeName").val()
		});
		$.gridLoading({"el":"#gridLoading","messsage":"正在加载中...."});
		$('#dataGridTable').gridSearch(p);
		$.gridUnLoading({"el":"#gridLoading"});
	}
};