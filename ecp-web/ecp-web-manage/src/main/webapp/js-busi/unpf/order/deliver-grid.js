var unpfDeliver = {
	deliver:function(id){
		window.location.href = GLOBAL.WEBROOT + '/unpfdeliver/send?orderId='+id;
	}
}

$(function(){

	var deliver_url = GLOBAL.WEBROOT + '/unpfdeliver/list';
	var finish_url = GLOBAL.WEBROOT + '/order/delyedlist';

	gridInit({id:"#deliverGridTable",url:deliver_url});
	gridInit({id:"#finishGridTable",url:finish_url});

	function gridInit(data) {

		var params = ebcForm.formParams($("#searchForm"));

		var aoColumns = [
			{ "mData": "outerId", "sTitle":"订单编号", "sClass":"center","bSortable":false, "mRender": function(data,type,row){
				return '<a href="javascript:void(0)" onclick="window.open(\''+GLOBAL.WEBROOT+"/unpforder/detail?orderId="+row.id+'\')">'+data+'</a>';
			}},
			{ "mData": "orderTime", "sTitle":"下单日期", "sClass":"center" ,"bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "shopName", "sTitle":"店铺","sWidth":"300px", "sClass":"center", "bSortable":false},
			{ "mData": "platType", "sTitle":"平台类型",  "sClass":"center","bSortable":false, "mRender": function(data,type,row){
				var val = "";
				if(data == "taobao") {
					val = "天猫&淘宝";
				} else if(data == "youzan") {
					val = "有赞";
				} else if(data == "jd") {
					val = "京东";
				}
				return val;
			}},
			{ "mData": "sysFlag", "sTitle":"是否需要手工开单", "sClass":"center" ,"bSortable":false,"mRender": function(data,type,row){
				if(data=='1'){
					return "否";
				}else if(data==null || data==""){
					return "";
				}
				else{
					return "是";
				}
			}},
			{ "mData": "payTime", "sTitle":"支付时间", "sClass":"center" ,"bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "realMoney", "sTitle":"实付金额",  "sClass":"center","bSortable":false, "mRender": function(data,type,row){
				return ebcUtils.numFormat(data/100, 2);
			}},
			{ "mData": "contractName", "sTitle":"联系人",  "sClass":"center","bSortable":false},
			{ "mData": "dispatchType", "sTitle":"配送方式",  "sClass":"center","bSortable":false,"mRender": function(data,type,row){
				if(data=='0'){
					return "邮局挂号";
				}else if(data=='1'){
					return "快递";
				}else if(data=='2'){
					return "自提";
				}else {
					return "未定义";
				}
			}}
		];
		if(data.id == '#deliverGridTable'){
			aoColumns.push({ "mData": "id", "sTitle":"操作",  "sClass":"center","bSortable":false,"mRender": function(data,type,row){
				return '<a class="_send" href="javascript:void(0)" onclick="unpfDeliver.deliver(\''+data+'\')">发货</a>';
			}});
			params.push({name:"status",value:"0"});
		} else {
			params.push({name:"status",value:"1"});
		}
		$(data.id).initDT({
			'pTableTools' : false,
			'pCheckRow' : false,
			'pCheckColumn' : false,
			'pSingleCheckClean' : false,
			'params': params,
			"sAjaxSource": GLOBAL.WEBROOT + '/unpfdeliver/list',
			//指定列数据位置
			"aoColumns": aoColumns,
			"eDbClick" : function(){
			}
		});
	}

	function getClickTabId() {
		var type = $("#deliverStatus>li.active").find("a").attr("href");
		var _id= type.replace('Tab','');
		return _id;
	}
	$('#deliverStatus a').click(function (e) {
		e.preventDefault();
		$(this).tab('show');
		if(!$("#searchForm").valid()) return false;

		var id = getClickTabId();

		if(id=='#deliver'){
			$("#finish").hide();
			$("#deliver").show();
		}else{
			$("#finish").show();
			$("#deliver").hide();
		}

		var p = ebcForm.formParams($("#searchForm"));
		$(id+'GridTable').gridSearch(p);
	});
	
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		var id = getClickTabId();
		$(id + 'GridTable').gridSearch(p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		$("#begDate").val($("#resetBegDate").val());
		$("#endDate").val($("#resetEndDate").val());
	});

});