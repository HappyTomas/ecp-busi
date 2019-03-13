var offline_grid = {
		
		getOrdDetail: function(orderid){
			var url = GLOBAL.WEBROOT+"/order/orderdetails?orderId="+orderid;
			window.open(url);
		},
		checkOrd:function(id,siteId){
			$('#reivewForm_orderId').val(id);
			$('#reivewForm_shopId').val($('#offline_grid_shopId').val());
			//$('#reivewForm_siteId').val(siteId);
			$('#reivewForm').submit();
		},
		getTabId:function(){
			var type = $("#myTab>li.active").find("a").attr("href");
			var _id= type.replace('Tab','');
			return _id;
		},
	    getText: function (data) {
			var show = "";
			var text = (data==null?"":data);
			if(text.length >= 15){
				show = text.substr(0,15) + '...';
				show = '<span title='+data+'>'+show+'</span>';
				return show;
			}
			return text;
		},
		reviewOrd:function(orderId,offlineNo,staffId){
			bDialog.open({
			    title : '审核意见',
			    width : 400,
			    height : 300,
			    scroll : true,
			    onShowed : function(_this){
			    	var dlg = $(_this);
					$('#reivewForm_orderId',$(dlg)).val(orderId);
					$('#reivewForm_offlineNo',$(dlg)).val(offlineNo);
					$('#reivewForm_staffId',$(dlg)).val(staffId);

					bValidate.clearValidate($('#reviewForm',$(dlg)));
					bValidate.labelValidate($('#reviewForm',$(dlg)));
			    },
			    callback:function(data){
			    	if(data && data.results && data.results.length > 0 ){
			    		if(!$("#searchForm").valid()) return false;
			    		var p = ebcForm.formParams($("#searchForm"));
			    		$(offline_grid.getTabId()+'GridTable').gridSearch(p);
			    	}
			    }
			},$('#reviewFormDiv'));

			//清空内容
			$('#checkCont').val('');
		}

};
$(function(){
	var uncheck_url = GLOBAL.WEBROOT + '/order/pay/unchecklist';
	var checked_url = GLOBAL.WEBROOT + '/order/pay/checkedlist';

	gridInitDT({url:uncheck_url,id:"uncheckGridTable",checkColumn:false});
	gridInitDT({url:checked_url,id:"checkedGridTable",checkColumn:false});

	function gridInitDT(json){
		var url = json.url,id=json.id;
		var pCheckColumn = json.checkColumn;
		var aoColumns = [
			{ "mData": "staffId", "sTitle":"买家账号","bSortable":false, "sClass":"center", "bVisible":false},
			{ "mData": "staffName", "sTitle":"买家账号","bSortable":false, "sClass":"center"},
			{ "mData": "orderId", "sTitle":"订单编号","bSortable":false, "sClass":"center", "mRender":function(data,type,row){
				return '<a href="javascript:void(0)" onclick="offline_grid.getOrdDetail(\''+data+'\')">'+data+'</a>';
			}},
			{ "mData": "orderTime", "sTitle":"下单时间","bSortable":false,"sClass":"center", "mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "realMoney", "sTitle":"金额","bSortable":false, "sClass":"center", "mRender": function(data,type,row){
				return ebcUtils.numFormat(data/100, 2);
			}},
			{ "mData": "applyType", "sTitle":"支付方式","bSortable":false, "sClass":"center"}

		];
		if(id=='uncheckGridTable'){
			aoColumns.push({ "mData": "orderId", "sTitle":"操作","bSortable":false, "sClass":"center", "mRender": function(data,type,row){
				return '<span><a href="javascript:void(0)" onclick="offline_grid.reviewOrd(\''+data+'\',\''+row.offlineNo+'\',\''+row.staffId+'\')">审核</a></span>';
			}})
		}else{
			aoColumns.push({ "mData": "status", "sTitle":"审核状态","bSortable":false, "sClass":"center","mRender": function(data,type,row){
				var status = data==null?"":data;
				if(status == '1'){
					return '已通过'
				}else if(status == '2'){
					return '未通过'
				}
			}});
			aoColumns.push({ "mData": "adminName", "sTitle":"审核工号","bSortable":false, "sClass":"center","mRender": function(data,type,row){
				return data==null?"":data;
			}});
			aoColumns.push({ "mData": "checkCont", "sTitle":"审核内容","bSortable":false, "width":200, "sClass":"center", "mRender": function(data,type,row){
				return offline_grid.getText(data);
			}});
		}

		$("#"+id).initDT({
			'pTableTools' : false,
			'pCheckRow' : false,
			'pCheckColumn' : pCheckColumn,
			'pSingleCheckClean' : false,
			'params':[{name:"shopId",value:$("#offline_grid_shopId").val()}],
			"sAjaxSource": url,
			//指定列数据位置
			"aoColumns": aoColumns,
			"eDbClick" : function(){
				//modifyBiz(json);
			}
		});

	}

	
	var modifyBiz = function(){
		var ids = $('#searchGridTable').getCheckIds();
	};
	
	$('#btnFormSearch').click(function(){
		if(!$('#searchForm').valid()) return false;
		var p = ebcForm.formParams($('#searchForm'));
		$(getTabId()+'GridTable').gridSearch(p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('searchForm');
	});

	/**
	 * 获取当前tab页面的id
	 */
	function getTabId(){
		var type = $("#myTab>li.active").find("a").attr("href");
		var _id= type.replace('Tab','');
		return _id;
	}
	//去弹出label位置
	//$('#notcontrols').css('margin-left','0');
	$('#myTab a').click(function (e) {
		e.preventDefault();
		$(this).tab('show');
		var id = getTabId();
		if(!$("#searchForm").valid()) return false;

		/*这个方法是ajax的，后台跳转的，自然和它无关了，难道这就叫ajax跨域吗*/
		/*带了tab之后就必须要获取当前是那个tab的数据*/
		var p = ebcForm.formParams($("#searchForm"));
		$(id+'GridTable').gridSearch(p);
	});

	$('body').delegate('div.dialogInActive #allow', 'click', function(e) {
		var dlg = bDialog.getDialog();
		var _this = this;
		if($(_this).hasClass('disabled')){
			return false;
		}

		if(!$('#reviewForm',$(dlg)).valid()) return false;

		var data = ebcForm.formParams($('#reviewForm',$(dlg)));

		data.push({name:'status',value:1});
		$(_this).addClass('disabled');
		$.eAjax({
			url:GLOBAL.WEBROOT+'/order/pay/offlinesave',
			data:data,
			success:function(result){
				$(_this).removeClass('disabled');
				bDialog.closeCurrent({result:'ok'});
				if(result&&result.resultFlag=='ok'){
					eDialog.alert('审核成功',function(){
					},'confirmation');
				}else{
					eDialog.alert(result.resultMsg,function(){
						window.location.href = GLOBAL.WEBROOT+'/order/pay/offlinegrid';
					},'error');
				}
			},
			failure:function(){
				$(_this).removeClass('disabled');
				bDialog.closeCurrent();
			}
		});

	});

	$('body').delegate('div.dialogInActive #unallow', 'click', function(e) {
		var dlg = bDialog.getDialog();
		var _this = this;
		if($(_this).hasClass('disabled')){
			return false;
		}

		if(!$('#reviewForm',$(dlg)).valid()) return false;
		var data = ebcForm.formParams($('#reviewForm',$(dlg)));
		data.push({name:'status',value:2});
		$(_this).addClass('disabled');
		$.eAjax({
			url:GLOBAL.WEBROOT+'/order/pay/offlinesave',
			data:data,
			success:function(result){
				$(_this).removeClass('disabled');
				bDialog.closeCurrent();
				if(result&&result.resultFlag=='ok'){

					eDialog.alert("审核不通过",function(){
						window.location.href = GLOBAL.WEBROOT+'/order/pay/offlinegrid';
					});
				}else{
					eDialog.alert(result.resultMsg,function(){
						window.location.href = GLOBAL.WEBROOT+'/order/pay/offlinegrid';
					});
				}
			},
			failure:function(){
				$(_this).removeClass('disabled');
				bDialog.closeCurrent();
			}
		});
	});

});