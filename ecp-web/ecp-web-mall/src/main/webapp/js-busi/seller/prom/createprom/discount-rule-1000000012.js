$(function() {

	// 页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			// 全局变量
			var matchGdsTr;
			// 商品删除按钮功能
			$("#matchTable tr td a[ name='delGdsRow']").live('click',
					function(e) {
						matchGdsTr = $(e.currentTarget).parent().parent();
						eDialog.confirm("确定删除吗？", {
							buttons : [ {
								caption : '确认',
								callback : function() {
									matchGdsTr.remove();
								}
							}, {
								caption : '取消',
								callback : $.noop
							} ]
						});
					});

			// 批量删除功能
			$("#btn_match_del_batch")
					.live(
							'click',
							function(e) {

								var _doAction = false;
								var _l = $("#matchTable tbody tr td input[ name='checkboxgds']");
								for (var i = 0; i < _l.length; i++) {
									if (_l[i].checked) {
										_doAction = true;
										break;
									}
								}
								if (!_doAction) {
									eDialog.alert('请，选择需要删除的数据。');
									return;
								}

								eDialog
										.confirm(
												"确定批量删除吗？",
												{
													buttons : [
															{
																caption : '确认',
																callback : function() {
																	var _l = $("#matchTable tbody tr td input[ name='checkboxgds']");
																	var _tr = null;
																	for (var i = 0; i < _l.length; i++) {
																		if (_l[i].checked) {
																			// 选中
																			// 删除
																			_tr = $(
																					_l[i])
																					.parent()
																					.parent();
																			_tr
																					.remove();
																		}
																	}
																}
															},
															{
																caption : '取消',
																callback : $.noop
															} ]
												});
							});

			// checkbox搭配商品选择事件
			$("#matchTable thead tr input[ id='dt_row_all_check']")
					.live(
							'click',
							function(e) {
								// 选中 表示全选
								if (e.currentTarget.checked) {
									$(
											"#matchTable tbody tr td input[ name='checkboxgds']")
											.prop('checked', true);
								} else {
									// 全部取消
									$(
											"#matchTable tbody tr td input[ name='checkboxgds']")
											.prop('checked', false);
								}
							});

			// 搭配商品选择 window
			$("#btnMatchGdsSelect").live('click',function(e) {
								// $('#btnMatchGdsSelect').unbind('click').click(function(){
								var _siteId = $('#siteId').val();
								bDialog
										.open({
											title : '商品选择',
											width : 800,
											height : 550,
											url : GLOBAL.WEBROOT
													+ "/seller/gdsprom/gdsselect?shopId="
													+ $('#shopId').val()
													+ "&siteId=" + _siteId,
											callback : function(data) {
												if (data && data.results
														&& data.results[0]) {
													discountRuleFun.initList(data.results[0].skuIds);
												}

											}
										});
							});
		};
		return {
			init : init
		};

	};
	pageConfig.config({
		// 指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm' ],
		// 指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});

});

// 促销类型1000000012 固定搭配（捆绑） js包装
var discountRuleFun = {

	// 判断是否为数字
	isNum : function(s) {
		if (s != null && s != "") {
			return !isNaN(s);
		}
		return false;
	},

	// 保存 提交验证
	valid : function() {

	    var _reData=new Object();
	        _reData.value=false;//返回默认 失败
	        
	    var data=new Array();

	    //验证金额
	    var bindPrice=$('#bindPrice').val();    
        if (!promCheck.priceNumber(bindPrice)){
        	eDialog.alert('促销规则-捆绑价格格式不正确，请最多保留两位小数。',null);
	  		 _reData.value=false;//返回默认 失败
		  		return _reData;
        }
	    
	    //促销类型为自由搭配时，搭配必需选择
	    var _l=$("#matchTable tbody tr td input[ name='checkboxgds']");
	    if(_l.length<=1){
	    	eDialog.alert('请设置捆绑商品2个及以上',null);
	  		 _reData.value=false;//返回默认 失败
		  		return _reData;
	    }else{
	    	//搭配商品 设置数量
	  	  var _promCnt=null;
	  	  var _bindPrice=$('#bindPrice').val();
	  	  var _guidePrice = null;
	  	  var _realPrice = null;
	   	  var _isNeedStock=null;
	  	  for(var i=0;i<_l.length;i++){
	  		  //验证是否为有效数字
    		  _promCnt=$('#promCnt-'+_l[i].id).val();//搭配捆绑商品总数量取值
    		  _guidePrice=$('#guidePrice-'+_l[i].id).val();
    		  _isNeedStock=$('#promCnt-'+_l[i].id).attr("isNeedStock");
    		  //实体需要验证库存
    		  	if(_isNeedStock=="true"){
	        		  if(!this.isNum(_promCnt)){
	        			    eDialog.alert('优惠规则，商品编码-单品编码：'+_l[i].id +" 对应的<span style='color:red'>搭配捆绑总量</span>，请输入有效的数字 "+_promCnt+" ！",null);
	        			    _reData.value=false;//返回默认 失败
	    			  		return _reData;
	        		  }
	    		  }else{
	    			  //虚拟不需要验证
	    			 _promCnt=0;
	    		  }
    		 
    		  data.push({'name':'matchMap['+_l[i].id+']','value':_promCnt+"-"+_bindPrice+"-"+_guidePrice});
	  	  }
	    }
	    _reData.value=true; 
	    _reData.result=data;
	    return _reData;
	},
	// 促销商品列表
	initList : function(_skuIds) {
		// 编辑 进入 promId非空
		var _promId = $('#promId').val();
		// _skuIds 初始化页面为空 ，open页面回掉可非空
		if (!_skuIds) {
			_skuIds = new Array();
		}
		// 当前页面的列表数据 需要加入到skuIds中
		var _l = $("#matchTable tbody tr td input[ name='checkboxgds']");
		if (_l && _l.length > 0) {
			for (var i = 0; i < _l.length; i++) {
				_skuIds.push(_l[i].parentNode.parentNode.id);
			}
		} else {
			// 没有数据 清空
			if (_skuIds && _skuIds.length == 0) {
				_skuIds = '';
			}
		}

		// ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/seller/matchprom/bindmatchList",
			data : [ {
				name : 'promId',
				value : _promId
			}, {
				name : 'skuIds',
				value : _skuIds
			} ],
			"dataType" : "text",
			success : function(returnInfo) {
				$('#matchGdsTableId').empty();
				$('#matchGdsTableId').append(returnInfo);
				//查看
				if ($('#doAction').val() == "view") {
					$(".matchClass").hide();//批量删除按钮  赠品选择按钮
					$("a[ name='delGdsRow']").hide();//删除按钮

				}
			}
		});
	},
	hide : function() {
		$('.bindClass').hide();
	},
	show : function() {
		$('.bindClass').show();
	}
};