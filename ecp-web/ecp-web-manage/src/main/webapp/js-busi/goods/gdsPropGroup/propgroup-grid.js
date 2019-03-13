$(function(){
	var _status = "1";
	$("input[type='radio']").each(function(){
		$this = $(this);
		if($this.attr('checked')=="checked"){
			_status = $this.attr('value');
		}
	});
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : true,
        'pCheck' : 'multi',
        'pCheckColumn' : false, //是否显示单选/复选框的列
        "sAjaxSource": GLOBAL.WEBROOT + '/gdspropgroup/gridpropgrouplist',
        //指定列数据位置
        "aoColumns": [
			{ "mData": "id", "sTitle":"属性组编码","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "groupName", "sTitle":"属性组名称","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "status", "sTitle":"状态","sWidth":"80px","sClass": "center","bSortable":false,
				"mRender": function(data,type,row){
					if(data =="1"){
						return "启用";
					}else if(data =="0"){
						return "禁用";
					}else{
						return "";
					}
				}
			},
			{"mData": "status","sTitle":"操作","sWidth":"80px","sClass": "center",
				"mRender": function(data,type,row){
					if(data == '0'){
						return "<a href='#' onclick='propStartUp(this)'>启用</a> | <a href='#' onclick=\"propEdit(this,'"+row.id+"')\">编辑</a>";
					}else if(data=="1"){
						return "<a href='#' onclick='propShutDown(this)'>禁用</a> | <a href='#' onclick=\"propEdit(this,'"+row.id+"')\">编辑</a>";
					}else{
						return "";
					}
				}
			}
        ],
        "params" : [{
			name : 'status',
			value : _status
		}]
	});
	
	var modifyBiz = function(){
		var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length>=1){
			//
		}else if(ids && ids.length>1){
			eDialog.alert('只能选择一条商品记录进行操作！');
		}else if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一条商品记录进行操作！');
		}
	};
	//查询
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		var status = $('#status').val();
		/*$("input[type='radio']").each(function(){
			$this = $(this);
			if($this.attr('checked')=="checked"){
				status = $this.attr('value');
			}
		});*/
		p.push({ 'name': 'status','value' : status });
		$('#dataGridTable').gridSearch(p);
	});
	//重置
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		$("input[type='radio']").each(function(index){
			$this = $(this);
			if(index ==0){
//				$this.trigger('click');
			}
		});
		
	});
	//添加属性
	$('#btn_code_add').click(function(){
		eNav.setSubPageText('新增属性组');
		window.location.href = GLOBAL.WEBROOT+'/gdspropgroup/propadd';
	});
});
//启用
function propStartUp(obj){
	PropGrid.propStartUp(obj);
}
//禁用
function propShutDown(obj){
	PropGrid.propShutDown(obj);
}
//编辑
function propEdit(obj,id){
	eNav.setSubPageText('编辑属性组');
	window.location.href = GLOBAL.WEBROOT+'/gdspropgroup/propedit?id='+id;
}
var PropGrid = {
		gridList : function(){
			var p = ebcForm.formParams($("#searchForm"));
			$('#dataGridTable').gridSearch(p);
		},
		propStartUp : function(obj){
			var propId = $(obj).parent().siblings().eq(0).text();
			var param = {
					propId : propId
			};
			$.eAjax({
				url : GLOBAL.WEBROOT + "/gdspropgroup/propstartup",
				data : param,
				success : function(returnInfo) {
					if(returnInfo.resultFlag=='ok'){
						PropGrid.gridList();
						eDialog.success('启用成功！'); 
					}
				}
			});
		},
		propShutDown : function(obj){
			var propId = $(obj).parent().siblings().eq(0).text();
			var param = {
					propId : propId
			};
			$.eAjax({
				url : GLOBAL.WEBROOT + "/gdspropgroup/propshutdown",
				data : param,
				success : function(returnInfo) {
					if(returnInfo.resultFlag=='ok'){
						eDialog.success('禁用成功！'); 
						PropGrid.gridList();
					}
				}
			});
		}
};