// 默认商品全局状态0,为待上架
var status = "0";
$(function(){
	
	U.tab(".seller-tab .tab-nav",".seller-tab .tab-content");

	GdsManage.initValid();
	
	// 绑定搜索按钮事件.
	$('#btnFormSearch').click(function(){
		
		if($("input[name='gdsId']")){
			var gdsId=$("input[name='gdsId']").val();
			if(gdsId && gdsId!=""){
				var r = /^[0-9]*$/;
				if(!r.test(gdsId)){
					eDialog.alert('商品编码只能是整数！');
					return false;
				}
			}
		}
		
		var param = new Object();
		param.shopId = $("#shopId").val();
		$.eAjax({
			url : GLOBAL.WEBROOT + "/seller/goods/gdsmanage/shopStatus",
			data : param,
			success : function(returnInfo) {
			  if(returnInfo.status == '1'){
				  $("#batchOpt").show();
				  
				  
			  }else{
				  $("#batchOpt").hide();  
				  
			  }
			}
		});	
		var p = ebcForm.formParams($('#searchForm'));
		if(undefined != $("#catgCode").attr('catgcode') && ''!=$("#catgCode").attr('catgcode')){
			p.push({ 'name': 'catgCode','value' : $("#catgCode").attr('catgcode') });
		}
		p.push({'name':'isbn','value':$("#isbn").val()});
		
		$('#listDiv').empty();
		
		$('#listDiv').load(GLOBAL.WEBROOT + '/seller/goods/gdsmanage/gridlist',
				p);		
	});
	// 搜索表单重置按钮绑定重置事件.
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		$('#status').val(status);
		$('#catgCode').attr('catgcode','');
	});
	
	// 绑定全选事件.
	$('#checkAll').live('click',function(){
		if('checked' == $(this).attr('checked')){
			var unchecked = $('input[type=checkbox][name=gdsId]').not("input:checked");
			$.each(unchecked,function(i,n){
				$(this).attr('checked','checked');
			});
		}else{
			var checked = $('input[type=checkbox][name=gdsId]:checked');
			$.each(checked,function(i,n){
				$(this).removeAttr('checked');
			});
		}
	});
	// 绑定每一行复选框选中事件.
	$('input[type=checkbox][name=gdsId]').live('click',function(i,n){
		var gdsIds = $('input[type=checkbox][name=gdsId]');
		var checkedGdsIds = $('input[type=checkbox][name=gdsId]:checked');
		if(checkedGdsIds.length == gdsIds.length){
			$('#checkAll').attr('checked','checked');
		}else{
			$('#checkAll').removeAttr('checked');
		}
	});
	
	//Tab换页查询
	$(".seller-tab .tab-nav").children().each(function(i,n){
		$(n).bind('click',function(){
				switch(i){
				case 0 :
					status ="0";
					$('#status').val(status);
					GdsManage.gridGdsList();
					$("#btn_code_up").show();
				    $("#btn_code_down").hide();
				    $("#btn_code_remove").show();
				    $("#btn_code_copy").show();
					break;
				case 1 : 
					status ="11";
					$('#status').val(status);
					GdsManage.gridGdsList();
				    $("#btn_code_up").hide();
				    $("#btn_code_down").show();
				    $("#btn_code_remove").hide();
				    $("#btn_code_copy").show();
					break;
				case 2 : 
					status ="22";
					$('#status').val(status);
					GdsManage.gridGdsList();
				    $("#btn_code_up").show();
				    $("#btn_code_down").hide();
				    $("#btn_code_remove").show();
				    $("#btn_code_copy").show();
					break;
				case 3 :
					status ="99";
					$('#status').val(status);
					GdsManage.gridGdsList();
				    $("#btn_code_up").hide();
				    $("#btn_code_down").hide();
				    $("#btn_code_remove").hide();
				    $("#btn_code_copy").hide();
					break;
				default : 
					break;
				}
		});
	});
	
	//添加商品
	$('#btn_code_add').click(function(){
		var _ifGdsScore = $("#ifGdsScore").val();
		if(_ifGdsScore=='1'){
			window.location.href = GLOBAL.WEBROOT+'/seller/goods/gdspointentry';
		}else{
			window.location.href = GLOBAL.WEBROOT+'/seller/goods/gdsinfoentry';
		}
	});
	
	//批量上架
	$("#btn_code_up").live('click',function(){
		if($("#GDS_VERIFY_SWITCH").val()=='1'){
			GdsManage.batchCommitForVerify('11');
		}else{
			GdsManage.gdsBatchUp();
		}
	});
	//批量下架
	$("#btn_code_down").live('click',function(){
		    GdsManage.gdsBatchDown();
	});
	//批量删除
	$('#btn_code_remove').live('click',function(){
		if($("#GDS_VERIFY_SWITCH").val()=='1'){
				GdsManage.batchCommitForVerify('99');
		}else{
		        GdsManage.gdsBatchRemove();
		}
	});
	//复制商品
	$("#btn_code_copy").live("click",function(){
		var ids = $('input[type=checkbox][name=gdsId]:checked');
		
		if(!ids || ids.length==0){
			eDialog.alert('请选择一条商品记录进行操作！');
			return ;
		}else if(ids[0]==undefined){
			eDialog.alert('请选择一条商品记录进行操作！');
			return ;
		}
		if(ids && ids.length >=2){
			eDialog.alert("只能操作一条记录");
			return;
		}
		var _ifGdsScore = $("#ifGdsScore").val();
		if(_ifGdsScore=='1'){
			window.location.href = GLOBAL.WEBROOT +"/seller/goods/gdspointentry/togdsedit?formCopyFlag=1&gdsId="+ids.val();
		}else{
			window.location.href = GLOBAL.WEBROOT +"/seller/goods/gdsinfoentry/togdsedit?formCopyFlag=1&gdsId="+ids.val();
		}
		
	});
	
	//提交上架审核的绑定事件
	$(".commitForVerify").live('click',function(e){
		var $this = $(this);
		var params = {};
		params.operateType = $this.attr('value');
		params.gdsId = $this.attr('gdsId');
		params.shopId = $this.attr('shopId');
		
		var title = $this.attr('title');
		if(undefined != title){
			title = "您确认要"+title + "吗?";
			eDialog.confirm(
					title,
					{
						buttons : [
								{
									caption : '确认',
									callback : function() {
										GdsManage.commitForVerify(params);
									}
								}, {
									caption : '取消',
									callback : $.noop
								}]
					});
		}else{
			GdsManage.commitForVerify(params);
		}
		e.preventDefault();
	});
	
	// 分类选择框绑定选择事件
	$("#catgCode").click(function(){
		
		if(undefined == $('#shopId').val() || "" == $('#shopId').val()){
			 eDialog.alert('店铺必须选择!');
			 return;
		}
		
		/*catlogId = '1';
		if($("#ifGdsScore").val()=='1'){
			catlogId = '2';
		}*/
		bDialog.open({
            title : '分类选择',
            width : 350,
            height : 550,
            params:{multi : false,ignoreDataAuth:'true',shopIds:[$('#shopId').val()]},
            url : GLOBAL.WEBROOT+"/seller/goods/category/open/catgselect?catgType=1",
            callback:function(data){
            	if(data && data.results && data.results.length > 0 ){
                    var _catgs = data.results[0].catgs;
					var size = _catgs.length;
					for(var i =0;i<size;i++){
						var obj = _catgs[i];
						$("#catgCode").val(obj.catgName);
						$("#catgCode").attr('catgCode',obj.catgCode);
					}
				}
            }
        });
	});
	
	GdsManage.initSearch();
});
/**
 * 图片自动缩放
 * @param maxWidth
 * @param maxHeight
 * @param objImg
 */
function autoResizeImage(maxWidth,maxHeight,objImg){
	var img = new Image();
	img.src = objImg.src;
	var hRatio;
	var wRatio;
	var Ratio = 1;
	var w = img.width;
	var h = img.height;
	wRatio = maxWidth / w;
	hRatio = maxHeight / h;
	if (maxWidth ==0 && maxHeight==0){
	Ratio = 1;
	}else if (maxWidth==0){//
	if (hRatio<1) Ratio = hRatio;
	}else if (maxHeight==0){
	if (wRatio<1) Ratio = wRatio;
	}else if (wRatio<1 || hRatio<1){
	Ratio = (wRatio<=hRatio?wRatio:hRatio);
	}
	if (Ratio<1){
	w = w * Ratio;
	h = h * Ratio;
	}
	objImg.height = h;
	objImg.width = w;
}
/**
 * 商品上架
 * @param obj
 * @param str
 * @param shopId
 */
function gdsUp(gdsId,str,shopId){
	GdsManage.gdsUp(gdsId,str,shopId);
}
/**
 * 商品下架
 * @param obj
 * @param str
 * @param shopId
 */
function gdsDown(gdsId,str,shopId){
	GdsManage.gdsDown(gdsId,str,shopId);
}
/**
 * 商品删除
 * @param obj
 * @param str
 * @param shopId
 */
function gdsRemove(gdsId,str,shopId){
	GdsManage.gdsRemove(gdsId,str,shopId);
}
/**
 * 商品编辑
 * @param obj
 * @param ifScoreGds
 * @param shopId
 */
function gdsEdit(gdsId,ifScoreGds,shopId){
	GdsManage.gdsEdit(gdsId,ifScoreGds,shopId);
}
/**
 * 商品详情
 * @param obj
 * @param ifScoreGds
 */
function gdsDetail(gdsId,ifScoreGds){
	GdsManage.gdsDetail(gdsId,ifScoreGds);
}
/**
 * 单品管理
 * @param obj
 * @param gdsId
 * @param status
 */
function toSkuManage(gdsId,gdsId,status){
	GdsManage.toSkuManage(gdsId,gdsId,status);
}
/**
 * 设置运费模板
 * @param obj
 * @param gdsId
 * @param shopId
 */
function setGdsShiptemp(obj,gdsId,shopId,mainCatgs){
	GdsManage.setGdsShiptemp(obj,gdsId,shopId,mainCatgs);
}
/**
 * 取消运费模板
 * @param obj
 * @param gdsId
 * @param shopId
 */
function cancelGdsShiptemp(obj,gdsId,shopId){
	GdsManage.cancelGdsShiptemp(obj,gdsId,shopId);
}
/**
 * 显示审核记录
 * @param shopId
 * @param gdsId
 */
function showVeriyRecord(shopId,gdsId){
	GdsManage.showVeriyRecord(shopId,gdsId);
}


function convertToIdString(chks){
	var idStr = "";
	if(null == chks){
		return idStr;
	}
	$.each(chks,function(i,n){
		idStr = idStr+$(n).val() + ","; 
	});
	if(idStr.length > 0){
	  idStr = idStr.substr(0,idStr.length - 1);
	}
	return idStr;
	
}

var GdsManage = {
		setGdsShiptemp : function(obj,gdsId,shopId,mainCatgs){
			bDialog.open({
				title : '设置运费模板',
				width : 860,
				height : 500,
				params : {
					'gdsId': gdsId,
					'shopId':shopId,
					'catgCode':mainCatgs
				},
				url : GLOBAL.WEBROOT + '/seller/goods/gdsmanage/free-open',
				callback:function(data){
					if(data && data.results && data.results.length > 0 ){
	                    var flag = data.results[0].flag;
	                    if(flag == "ok"){
	                    	GdsManage.gridGdsList();
	                    }else{
	                    	eDialog.error('配置运费模板失败！'); 
	                    }
					}
				}
			});
		},
		cancelGdsShiptemp : function(obj,gdsId,shopId){
			eDialog.confirm(
					"您确认取消该运费模板吗？",
					{
						buttons : [
								{
									caption : '确认',
									callback : function() {
										var param = {};
										param.gdsId = gdsId;
										param.shopId = shopId;
										$.eAjax({
											url : GLOBAL.WEBROOT + "/seller/goods/gdsmanage/setshiptemp",
											data : param,
											success : function(returnInfo) {
												if(returnInfo.resultFlag == "ok"){
							                    	GdsManage.gridGdsList();
							                    }else{
							                    	eDialog.error('取消运费模板失败！');
							                    }
											}
										});
									}
								}, {
									caption : '取消',
									callback : $.noop
								}]
					});
		},
		toSkuManage : function(obj,gdsId,status){
			bDialog.open({
				title : '单品列表',
				width : 860,
				height : 400,
				url : GLOBAL.WEBROOT + '/seller/goods/gdsmanage/sku-open',
				params : {
					'gdsId' : gdsId,
					'status' : status
				},
				callback:function(data){
					GdsManage.gridGdsList();
				}
			});
		},
		gdsUp : function(gdsId,str,shopId){
			var param = {
					operateId : gdsId,
					operateFlag : '1',
					shopId : shopId
			};
			
			eDialog.confirm(
					"您确认要上架该商品吗？",
					{
						buttons : [
								{
									caption : '确认',
									callback : function() {
										$.eAjax({
											url : GLOBAL.WEBROOT + "/seller/goods/gdsmanage/gdsupdown",
											data : param,
											success : function(returnInfo) {
												if(returnInfo.resultFlag=='ok'){
													eDialog.success('上架成功！'); 
													GdsManage.gridGdsList();
												}else{
													eDialog.error('上架失败！,原因是：'+returnInfo.resultMsg);
												}
											}
										});
									}
								}, {
									caption : '取消',
									callback : $.noop
								}]
					});
		},
		gdsDown : function(gdsId,str,shopId){
			var param = {
					operateId : gdsId,
					operateFlag : '0',
					shopId : shopId
			};
			
			eDialog.confirm(
					"您确认要下架该商品吗？",
					{
						buttons : [
								{
									caption : '确认',
									callback : function() {
										$.eAjax({
											url : GLOBAL.WEBROOT + "/seller/goods/gdsmanage/gdsupdown",
											data : param,
											success : function(returnInfo) {
												if(returnInfo.resultFlag=='ok'){
													eDialog.success('下架成功！'); 
													GdsManage.gridGdsList();
												}else{
													eDialog.error('下架失败！，原因是：'+returnInfo.resultMsg);
												}
											}
										});
									}
								}, {
									caption : '取消',
									callback : $.noop
								}]
					});
		},
		gdsBatchUp : function(){
			var ids = $('input[type=checkbox][name=gdsId]:checked');
			if(!ids || ids.length==0){
				eDialog.alert('请至少选择一条商品记录进行操作！');
				return ;
			}else if(ids[0]==undefined){
				eDialog.alert('请至少选择一条商品记录进行操作！');
				return ;
			}	
			var param = {
					operateId :convertToIdString(ids),
					operateFlag : '1',
					shopId : $("#shopId").val()
			};
			
			eDialog.confirm(
					"您确认要批量上架选定商品吗？",
					{
						buttons : [
								{
									caption : '确认',
									callback : function() {
										$.eAjax({
											url : GLOBAL.WEBROOT + "/seller/goods/gdsmanage/gdsbatchupdown",
											data : param,
											success : function(returnInfo) {
												if(returnInfo.resultFlag=='ok'){
													eDialog.success('批量上架成功！'); 
													GdsManage.gridGdsList();
												}else{
													eDialog.error('批量上架失败！原因是：'+returnInfo.resultMsg);
													GdsManage.gridGdsList();
												}
											}
										});
									}
								}, {
									caption : '取消',
									callback : $.noop
								}]
					});
		},
		gdsBatchDown : function(){
			// var ids = $('#dataGridTable').getCheckIds();
			var ids = $('input[type=checkbox][name=gdsId]:checked');
			if(!ids || ids.length==0){
				eDialog.alert('请至少选择一条商品记录进行操作！');
				return ;
			}else if(ids[0]==undefined){
				eDialog.alert('请至少选择一条商品记录进行操作！');
				return ;
			}	
			var param = {
					operateId : convertToIdString(ids),
					operateFlag : '0',
					shopId : $("#shopId").val()
					
			};
			
			eDialog.confirm(
					"您确认要批量下架选定商品吗？",
					{
						buttons : [
								{
									caption : '确认',
									callback : function() {

										$.eAjax({
											url : GLOBAL.WEBROOT + "/seller/goods/gdsmanage/gdsbatchupdown",
											data : param,
											success : function(returnInfo) {
												if(returnInfo.resultFlag=='ok'){
													eDialog.success('批量下架成功！'); 
													GdsManage.gridGdsList();
												}else{
													eDialog.error('批量下架失败！原因是：'+returnInfo.resultMsg);
													GdsManage.gridGdsList();
												}
											}
										});
									}
								}, {
									caption : '取消',
									callback : $.noop
								}]
					});
			
		},
		gdsRemove : function(gdsId,str,shopId){
			//var ids  = $(obj).parent().siblings().eq(1).text();
			var param = {
					operateId : gdsId,
					operateFlag : '9',
					shopId : shopId
			};
			eDialog.confirm("您确认删除该记录吗？", {
				buttons : [ {
					caption : '确认',
					callback : function() {
						$.eAjax({
							url : GLOBAL.WEBROOT + "/seller/goods/gdsmanage/gdsremove",
							data : param,
							success : function(returnInfo) {
								if (returnInfo.resultFlag == 'ok') {
									eDialog.success('删除成功！');
									GdsManage.gridGdsList();
								}else{
									eDialog.error('删除失败！，原因是：'+returnInfo.resultMsg);
								}
							}
						});
					}
				}, {
					caption : '取消',
					callback : $.noop
				} ]
			});
		},
		gdsEdit : function(gdsId,ifScoreGds,shopId){
			if(ifScoreGds=='1'){
				window.location.href= GLOBAL.WEBROOT +"/seller/goods/gdspointentry/togdsedit?gdsId="+gdsId;
			}else{
				if($("#GDS_VERIFY_SWITCH").val()=='1'){
					//审核按钮开启的时候，先判断该商品是否处于提交待审核状态。如果是，则不允许操作
					var params = {
							'gdsId' : gdsId,
							'shopId' : shopId
							};
					GdsManage.whetherCanOperate(params);
				}else{
					window.location.href= GLOBAL.WEBROOT +"/seller/goods/gdsinfoentry/togdsedit?gdsId="+gdsId;
				}
				
			}
		},
		gdsDetail : function(gdsId,ifScoreGds){
			if(ifScoreGds=='1'){
				window.location.href= GLOBAL.WEBROOT +"/seller/goods/gdspointentry/togdsdetail?gdsId="+gdsId;
			}else{
				window.location.href= GLOBAL.WEBROOT +"/seller/goods/gdsinfoentry/togdsdetail?gdsId="+gdsId;
			}
		},
		gridGdsList : function(){
			$('#btnFormSearch').trigger('click');
		},
		gdsBatchRemove : function(){
			// var ids = $('#dataGridTable').getCheckIds();
			var ids = $('input[type=checkbox][name=gdsId]:checked');
			if(!ids || ids.length==0){
				eDialog.alert('请至少选择一条商品记录进行操作！');
				return ;
			}else if(ids[0]==undefined){
				eDialog.alert('请至少选择一条商品记录进行操作！');
				return ;
			}
			var param = {
					operateId : convertToIdString(ids),
					shopId : $("#shopId").val()
			};

			eDialog.confirm("您确认删除选中的商品吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/seller/goods/gdsmanage/gdsbatchremove",
							data : param,
							success : function(returnInfo) {
								if(returnInfo.resultFlag=='ok'){
									eDialog.success('批量删除成功！');
									GdsManage.gridGdsList();
								}else{
									eDialog.error('批量删除失败！，原因是：'+returnInfo.resultMsg);
									GdsManage.gridGdsList();
								}
							}
						});
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		
		},
		initValid : function(){
			jQuery.validator.addMethod("compareTime", function(value, element) {
				var endTimeId = element.id;
				var startTime= $("#startTime").val();
				var endTime = $("#" + endTimeId).val();
				if(startTime =="" || endTime ==""){
					return true;
				}
				/**
				 * 解决IE不支持trim问题
				 */
				if (!String.prototype.trim) {
					String.prototype.trim = function() {
						return this.replace(/^\s+|\s+$/g, '');
					};
				}
				if (startTime.trim().length == 10) {
					startTime = startTime + " 00:00:00";
				}
				if (endTime.trim().length == 10) {
					endTime = endTime + " 00:00:00";
				}
				var reg = new RegExp('-', 'g');
				startTime = startTime.replace(reg, '/');// 正则替换
				endTime = endTime.replace(reg, '/');
				startTime = new Date(parseInt(Date.parse(startTime), 10));
				endTime = new Date(parseInt(Date.parse(endTime), 10));
				if (startTime > endTime) {
					return false;
				} else {
					return true;
				}
			}, "<font color='#E47068'>截至时间不能早于起始时间</font>");
			$("#searchForm").validate({
				rules : {
					endTime : {
						compareTime : true
					},
					gdsId : {
						digits : true
					}
				},
				messages : {
					endTime : {
						compareTime : "<span style='color:red'>截至时间不能早于起始时间</span>"
					},
					gdsId : {
						digits : "<span style='color:red'>只能输入整数</span>"
					}
				},
				//	          debug: false,  //如果修改为true则表单不会提交  
				submitHandler : function() {
				}
			});
		},
		commitForVerify : function(params){
			$.eAjax({
				url : GLOBAL.WEBROOT + "/seller/goods/gdsmanage/commitforverify",
				data : params,
				success : function(returnInfo) {
					if(returnInfo.resultFlag=='ok'){
						eDialog.success(returnInfo.resultMsg);
					}else{
						eDialog.error(returnInfo.resultMsg);
					}
				}
			});
		},
		batchCommitForVerify : function(operateType){
			//var ids = $('#dataGridTable').getCheckIds();
			var ids = $('input[type=checkbox][name=gdsId]:checked');
			if(!ids || ids.length==0){
				eDialog.alert('请至少选择一条商品记录进行操作！');
				return ;
			}else if(ids[0]==undefined){
				eDialog.alert('请至少选择一条商品记录进行操作！');
				return ;
			}	
			var params = {
					operateId : convertToIdString(ids),
					operateType : operateType,
					shopId : $("#shopId").val()
			};
			var operPrompt = "";
			if('99' == operateType){
				operPrompt = "批量删除审核";
			}else if('11' == operateType){
				operPrompt = "批量上架审核";
			}
			eDialog.confirm(
					"您确认要提交"+operPrompt+"吗？",
					{
						buttons : [
								{
									caption : '确认',
									callback : function() {
										$.eAjax({
											url : GLOBAL.WEBROOT + "/seller/goods/gdsmanage/batchcommitforverify",
											data : params,
											success : function(returnInfo) {
												if(returnInfo.resultFlag=='ok'){
													eDialog.success(returnInfo.resultMsg);
												}else{
													eDialog.error(returnInfo.resultMsg);
												}
											}
										});
									}
								}, {
									caption : '取消',
									callback : $.noop
								}]
					});
		},
		showVeriyRecord : function(shopId,gdsId){
			bDialog.open({
				title : '审核记录',
				width : 860,
				height : 500,
				params : {
					'gdsId': gdsId,
					'shopId':shopId
				},
				url : GLOBAL.WEBROOT + '/seller/goods/gdsmanage/gdsverifyrecord',
				callback:function(data){}
			});
		},
		whetherCanOperate : function(params){
			$.eAjax({
				url : GLOBAL.WEBROOT + "/seller/goods/gdsmanage/whethercanoperate",
				data : params,
				success : function(returnInfo) {
					if(returnInfo.resultFlag=='ok'){
						//表示可以进行操作
						window.location.href= GLOBAL.WEBROOT +"/seller/goods/gdsinfoentry/togdsedit?gdsId="+params.gdsId;
					}else{
						//不可进行操作
						eDialog.error(returnInfo.resultMsg);
					}
				}
			});
		},
		// 初始化事件.
		initSearch : function(){
			var _initGdsStatus = $('#GDS_STATUS').val();
			if(undefined != _initGdsStatus && null != _initGdsStatus && '' != _initGdsStatus){
				var tab = $('a[status="'+_initGdsStatus+'"]');
				if(tab.length > 0){
					tab.click();
				}else{
					$('#btnFormSearch').trigger('click');
				}
			}else{
				$('#btnFormSearch').trigger('click');
			}
		}
		
};

