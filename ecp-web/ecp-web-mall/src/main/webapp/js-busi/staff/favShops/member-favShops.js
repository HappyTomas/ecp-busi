//页面初始化模块
$(function(){
    var pInit = function(){
    	var init = function(){
	    	
	    	//初始化店铺的热销商品数据
	    	$("a[name='hotGoods']").each(function(){
	    		this.click();
	    	});
	    	
	    	//搜索按钮事件
	    	$('#searchShop').unbind('click');
	    	$('#searchShop').click(function(){
	    		var shopName = "";
	    		if ($('#shopName').val()) {
	    			shopName = $('#shopName').val();
	    		}
	    		var url = GLOBAL.WEBROOT + '/favshops/index?shopName='+shopName;
	    		url = encodeURI(url,'UTF-8');
	    		url = encodeURI(url,'UTF-8');
	    		window.location.href = url;
	    	});
		};
    	return {
    		init : init
    	};
	};    	
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		//指定页面
		init : function(){
			var shopList = new pInit();
			shopList.init();
		}
	});
});

//热销商品
function hotGoods(shopId) {
	$.eAjax({
		url : $webroot + '/favshops/hotgoods?shopId='+shopId,
		dataType : "text",
		success : function(returnInfo) {
			if(returnInfo){
				$("#goodsDiv_" + shopId).html(returnInfo);
			}
		}
	});
	$('#newGoods_' + shopId).attr('class','');
	$('#hotGoods_' + shopId).attr('class','active');
}
//新上架商品
function newGoods(shopId) {
	$.eAjax({
		url : $webroot + '/favshops/newgoods?shopId='+shopId,
		dataType : "text",
		success : function(returnInfo) {
			if(returnInfo){
				$("#goodsDiv_" + shopId).html(returnInfo);
			}
		}
	});
	$('#newGoods_' + shopId).attr('class','active');
	$('#hotGoods_' + shopId).attr('class','');
}
//展示右边的更多数据
function showRight(pageNo,shopId) {
	if ($('#hotGoods_' + shopId).attr('class') == 'active') {
		$.eAjax({
			url : $webroot + '/favshops/hotgoods?shopId='+shopId+'&pageNumber='+pageNo,
			dataType : "text",
			success : function(returnInfo) {
				if(returnInfo){
					$("#goodsDiv_" + shopId).html(returnInfo);
				}
			}
		});
		$('#newGoods_' + shopId).attr('class','');
	} else {
		$.eAjax({
			url : $webroot + '/favshops/newgoods?shopId='+shopId+'&pageNumber='+pageNo,
			dataType : "text",
			success : function(returnInfo) {
				if(returnInfo){
					$("#goodsDiv_" + shopId).html(returnInfo);
				}
			}
		});
		$('#hotGoods_' + shopId).attr('class','');
	}
}

//展示左边的数据
function showLeft(pageNo,shopId) {
	if ($('#hotGoods_' + shopId).attr('class') == 'active') {
		$.eAjax({
			url : $webroot + '/favshops/hotgoods?shopId='+shopId+'&pageNumber='+pageNo,
			dataType : "text",
			success : function(returnInfo) {
				if(returnInfo){
					$("#goodsDiv_" + shopId).html(returnInfo);
				}
			}
		});
		$('#newGoods_' + shopId).attr('class','');
	} else {
		$.eAjax({
			url : $webroot + '/favshops/newgoods?shopId='+shopId+'&pageNumber='+pageNo,
			dataType : "text",
			success : function(returnInfo) {
				if(returnInfo){
					$("#goodsDiv_" + shopId).html(returnInfo);
				}
			}
		});
		$('#hotGoods_' + shopId).attr('class','');
	}
}
//取消关注
function cancelShop(shopId) {
	
	
	eDialog.confirm("您确定要取消关注吗？" , {
	    buttons : [{
	        'caption' : '确定',
	        'callback' : function(){
	        	$.eAjax({
	        		url : $webroot + '/favshops/cancel?shopId='+shopId,
	        		dataType : "json",
	        		success : function(returnInfo) {
	        			if(returnInfo.resultFlag == 'ok'){
	        				var shopName = "";
	        	    		if ($('#shopName').val()) {
	        	    			shopName = $('#shopName').val();
	        	    		}
	        	    		var url = GLOBAL.WEBROOT + '/favshops/index?shopName='+shopName;
	        	    		url = encodeURI(url,'UTF-8');
	        	    		url = encodeURI(url,'UTF-8');
	        	    		window.location.href = url;
	        			} else {
	        				eDialog.alert("取消店铺关注失败！");
	        			}
	        		}
	        	});
	        }
	    },{
	        'caption' : '取消',
	        'callback' : function(){}
	    }]
	});
}
//店铺列表的分页
//currPage：当前页码；totalPage：总页码；optType：操作类型up，上一页，down下一页
function pageShow(currPage,totalPage,optType) {
	var pageNo = 1;
	//上一页
	if (optType == 'up') {
		//处于第一页
		if (currPage == 1) {
			return;
		}
		pageNo = parseInt(currPage) - 1;
		//下一页
	} else if (optType == 'down') {
		//处于最后一页
		if (currPage == totalPage || totalPage == '0') {
			return;
		}
		pageNo = parseInt(currPage) + 1;
	}
	var shopName = "";
	if ($('#shopName').val()) {
		shopName = $('#shopName').val();
	}
	var url = GLOBAL.WEBROOT + '/favshops/index?shopName='+shopName+'&pageNumber='+pageNo;
	url = encodeURI(url,'UTF-8');
	url = encodeURI(url,'UTF-8');
	window.location.href = url;
}
//查看商品详情
function goodDetail(gdsId,skuId) {
	window.open(GLOBAL.WEBROOT + '/gdsdetail/'+gdsId+'-'+skuId);
}
//进入店铺
function shopDetail(shopId) {
	window.open(GLOBAL.WEBROOT + '/shopIndex/'+shopId);
}