//页面初始化模块
$(function(){
	
	
	//店铺list
	/**
	 * {shopId : shopInfo}
	 */
	var shopListData = {};
	//当前显示的店铺id
	var currentShopId = "";
	//图表对象
	var dailyTradeDataChart;
	
	//名片鼠标事件
	$(".seller-list").delegate("li", "click", function () {
        $(this).addClass("shop-box").siblings().removeClass("shop-box");
        var shopId = $(this).find("input").eq(0).val();
        showShop(shopId);
    });
    
    $(".seller-list").delegate("li", "mouseover", function () {
        $(this).addClass("shop-box").siblings().removeClass("shop-box");
    });
    $(".seller-list").delegate("li", "mouseleave", function () {
        $(".seller-list li").each(function(i){
        	var self = $(this);
        	if(self.find("input").val()==currentShopId){
        		self.addClass("shop-box").siblings().removeClass("shop-box");
        		return false;
        	}
        });
    });
    
    
    //分页鼠标事件
    $("#shopSlideList").delegate("li", "click", function () {
    	var self = $(this);
    	if(self.find("a").attr("class")=="active"){//点击当前页不刷新
    		return;
    	}
    	self.find("a").addClass("active")
    	self.siblings().find("a").removeClass("active");
        var list = [];
        var pageno = self.find("a").attr("pageno");
        var pagecount = self.find("a").attr("pagecount");
        var pagesize = 4;
        var start = pageno*pagesize;
        var end = start+pagecount;
        var j=0;
        for(var i in shopListData){
        	if(j>=start && j<end){
        		list.push(shopListData[i]);
        	}
        	j++;
        }
        _removeCardAll();//清空
        _appendCardRequire(list);//刷新名片
    });
    
    
	
	//初始化函数
	var init = function(){
    	//图表初始化
		dailyTradeDataChart = echarts.init(document.getElementById('shopORDTrend'));
		
		//1.我的店铺
        
		//查询
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/shopdashboard/myshops",
  			async : true,
  			data : {},
			type : "post",
  			dataType:'json',
  			success : function(data) {
  				//TODO data.length==0
  				var len = data.length;
  				
  				//只有一个店铺，隐藏“我的店铺”
  				if(len>1){
  					$("#multShop").show();
  				}
  				
  				//排序id从小到大 -- 保持与shopListData一致
  				data.sort(function(a,b){ return a.id - b.id});
  				if(data){//更新shopListData
  					for(var i in data){
  						shopListData[""+data[i].id+""] = data[i];
  					}
  				}
  				
  				//清空
  				_removeCardAll();
  				_removeSlideAll();
  				//1.处理名片
  				_appendCardRequire(data);
  				//2.处理分页
  				_appendSlideAll(data);
  				
  			}
  		});
		
	};
	
	/**
	 * 初始化
	 */
	init();
	
	/**
	 * 清空名片组
	 */
	function _removeCardAll(){
		$("#shopList").html("");
	}
	
	/**
	 * 刷新名片
	 */
	function _appendCardRequire(data){
		var size = 4;//一页展示数量
		var ul = $("#shopList");
		for(var i=0; i<size; i++){
			if(data[i]){
				ul.append(_createCardLi(data[i], i==0));
			}
		}
		
		//初始化店铺 首个
		showShop(data[0].id);
	}
	
	/**
	 * 清空分页滑块
	 */
	function _removeSlideAll(){
		$("#shopSlideList").html("");
	}
	
	/**
	 * 刷新分页
	 */
	function _appendSlideAll(data){
		var count = data.length;
		var size = 4;//分页容器容量上限
		var ul = $("#shopSlideList");
		var pageCount = (count%size==0)?parseInt(count/size):parseInt(count/size+1);
		for(var i=0; i<pageCount; i++){
			var pageinfo = {};
			pageinfo.pageno = i; //当前页
			pageinfo.pagecount = size;  //当前页容量
			if(i==(pageCount-1)) pageinfo.pagecount = count-i*size;//当前页为最后一页时的容量
			
			ul.append(_createSlideLi(pageinfo, i==0));
		}
		
	}
	
	/**
	 * 创建一个名片li
	 */
	function _createCardLi(shop, isShow){
		
		var card = '';
		if(isShow){//完整名片
			card += '<li class="shop-box">';
		}else{//图片
			card += '<li class="">';	
		}
		card += '<input type="hidden" value="'+shop.id+'">';
		card += '<img style="width:160px;height:160px" src="' + shop.logoPathURL + '" alt=""/>';
		card += '<div class="imgmargin"><h2 title="'+shop.shopFullName+'">' + shop.shopFullName + '</h2>';
		card += '<p style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:160px;" title="'+shop.linkPerson+'" >联系人：' + shop.linkPerson + '</p>';
		card += '<p style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:160px;">联系电话：' + shop.linkMobilephone + '</p>';
		var cautionMoney = shop.cautionMoney||"0";
		card += '<p>入店保障资金：' + cautionMoney + '</p>';
		card += '</div>';
		card += '</li>';

		return card;
	}
	
	/**
	 * 创建一个分页滑块li
	 */
	function _createSlideLi(pageinfo, isActive){
		
		
		var slide = '';
		var active = !!isActive?'active':'';
		slide += '<li>';
		slide += '<a class="'+active+'" href="javascript:" pageno='+pageinfo.pageno+' pagecount='+pageinfo.pagecount+'></a>';
		slide += '</li>';

		return slide;
	}
	
	/**
	 * 店铺展现
	 */
	var showShop = function(shopId){
		if(currentShopId==shopId){//当前店铺不刷新
			return;
		}
		//店铺装饰不采用事件来打开新页面了。直接用a标签的href属性。防止打开新页面，shopId传的不对  原:third/sys/shopfishing
		$("#shopDecoration").attr('href', $("#shopDecoration").attr('url')+"/union/login/shopfishing?shopId="+shopId)
		currentShopId = shopId;//当前店铺
		//1.
		//2.店铺详情
		//店铺图片
		$("#currentShopImg").attr("src", shopListData[shopId].logoPathURL);
		//店铺名称
		$("#currentShopName").html(shopListData[shopId].shopFullName);
		$("#currentShopName").attr("title", shopListData[shopId].shopFullName);
		//2.1店铺设置 /seller/shopmgr/shopset/100
		$("#shopConfig").click(function(){
			window.location.href = GLOBAL.WEBROOT + "/seller/shopmgr/shopset?shopId="+shopId;
		});
		//2.2店铺首页  shopIndex/100
		$("#shopIndex").click(function(){
			//window.location.href = GLOBAL.WEBROOT + "/shopIndex/"+shopId;
			window.location.href = GLOBAL.WEBROOT + "/modularcommon?pageTypeId=1"+"&shopId="+shopId;
		});
		//2.3 差评
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/goods/dashboard/badEvaluateCount",
  			async : true,
  			data : {shopId : shopId},
			type : "post",
  			dataType:'json',
  			success : function(data) {
  				var evaluateLow = $("#evaluateLow").html("("+data+")");
  				$("#evaluateLow").click(function(){
  					window.location.href = GLOBAL.WEBROOT + "/seller/gdsevalshop?shopId="+shopId+"&evalScore=3";
  				});
  			}
  		});
		//2.4 中评
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/goods/dashboard/middleEvaluateCount",
  			async : true,
  			data : {shopId : shopId},
			type : "post",
  			dataType:'json',
  			success : function(data) {
  				var evaluateMid = $("#evaluateMid").html("("+data+")");
  				$("#evaluateMid").click(function(){
  					window.location.href = GLOBAL.WEBROOT + "/seller/gdsevalshop?shopId="+shopId+"&evalScore=2";
  				});
  			}
  		});
		//2.5 好评
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/goods/dashboard/goodEvaluateCount",
  			async : true,
  			data : {shopId : shopId},
			type : "post",
  			dataType:'json',
  			success : function(data) {
  				var evaluateHi = $("#evaluateHi").html("("+data+")");
  				$("#evaluateHi").click(function(){
  					window.location.href = GLOBAL.WEBROOT + "/seller/gdsevalshop?shopId="+shopId+"&evalScore=1";
  				});
  			}
  		});
		//3.店铺动态
		//3.1 商品好评度
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/goods/dashboard/goodEvalRate",
  			async : true,
			type : "post",
			data : {shopId: shopId},
  			dataType:'text',
  			success : function(data) {
  				if(data){
  					$("#shopGDSEvalRate").html(data+" %");
  				}else{
  					$("#shopGDSEvalRate").html("0 %");
  				}
  			}
  		});
		//3.2 商品总数
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/goods/dashboard/legalGds",
  			async : true,
			type : "post",
			data : {shopId: shopId},
  			dataType:'json',
  			success : function(data) {
  				if(data){
  					$("#shopLegalGDS").html(data);
  				}else{
  					$("#shopLegalGDS").html("0");
  				}
  			}
  		});
		//3.3 卖出宝贝（商品）数
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/goods/dashboard/sales",
  			async : true,
			type : "post",
			data : {shopId: shopId},
  			dataType:'json',
  			success : function(data) {
  				if(data){
  					$("#shopGDSSales").html(data);
  				}else{
  					$("#shopGDSSales").html("0");
  				}
  			}
  		});
		//3.4 出售中的宝贝（商品）
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/goods/dashboard/gdscount",
  			async : true,
  			data : {shopId: shopId, status: "11"},
			type : "post",
  			dataType:'json',
  			success : function(data) {
  				if(data && data.resultFlag=="ok"){
  					$("#shopGDSSaling").html(data.gdsCount);
  				}else{
  					$("#shopGDSSaling").html("0");
  				}
  			}
  		});
		
		//4.待办事项
		//4.1 订单管理
		//4.1.1待发货
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/workcenter/sendList",
  			async : true,
			type : "post",
			data : {shopId: shopId},
  			dataType:'json',
  			success : function(data) {
  				if(data){
  					$("#shopORDSend").html("("+data+")");
  				}else{
  					$("#shopORDSend").html("(0)");
  				}
				$("#shopORDSend").click(function(){
  					window.location.href = GLOBAL.WEBROOT + "/seller/order/delivery/index?shopId="+shopId;
  				});
  			}
  		});
		//4.1.2待支付
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/workcenter/payList",
  			async : true,
			type : "post",
			data : {shopId: shopId},
  			dataType:'json',
  			success : function(data) {
  				if(data){
  					$("#shopORDPay").html("("+data+")");
  				}else{
  					$("#shopORDPay").html("(0)");
  				}
  				$("#shopORDPay").click(function(){
  					window.location.href = GLOBAL.WEBROOT + "/seller/order/manage/index?status=01&shopId="+shopId;
  				});
  			}
  		});
		//4.1.3.待退货
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/workcenter/returnList",
  			async : true,
			type : "post",
			data : {shopId: shopId},
  			dataType:'json',
  			success : function(data) {
  				if(data){
  					$("#shopORDReturn").html("("+data+")");
  				}else{
  					$("#shopORDReturn").html("(0)");
  				}
  				$("#shopORDReturn").click(function(){
  					window.location.href = GLOBAL.WEBROOT + "/seller/order/backgds/index?shopId="+shopId;
  				});
  			}
  		});
		//4.1.4待退款
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/workcenter/moneyList",
  			async : true,
			type : "post",
			data : {shopId: shopId},
  			dataType:'json',
  			success : function(data) {
  				if(data){
  					$("#shopORDMoney").html("("+data+")");
  				}else{
  					$("#shopORDMoney").html("(0)");
  				}
  				$("#shopORDMoney").click(function(){
  					window.location.href = GLOBAL.WEBROOT + "/seller/order/refund/index?shopId="+shopId;
  				});
  			}
  		});
		
		//4.2 商品管理
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/goods/dashboard/gdscount",
  			async : true,
			type : "post",
			data : {shopId: shopId, status: "0"},
  			dataType:'json',
  			success : function(data) {
  				if(data && data.resultFlag=="ok"){
  					$("#shopGDSWaitShelves").html("("+data.gdsCount+")");
  				}else{
  					$("#shopGDSWaitShelves").html("(0)");
  				}
  				$("#shopGDSWaitShelves").click(function(){
  					window.location.href = GLOBAL.WEBROOT + "/seller/goods/gdsmanage?gdsStatus=0&shopId="+shopId;
  				});
  			}
  		});
		
		//4.3 库存预警
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/goods/dashboard/lackStockGds",
  			async : true,
			type : "post",
			data : {shopId: shopId, stockStatus: "00"},
  			dataType:'json',
  			success : function(data) {
  				if(data){
  					$("#shopGDSStock").html("("+data+")");
  				}else{
  					$("#shopGDSStock").html("(0)");
  				}
  				$("#shopGDSStock").click(function(){
  					window.location.href = GLOBAL.WEBROOT + "/seller/goods/stockinfo/pageStockInit?stockStatus=00&shopId="+shopId;
  				});
  			}
  		});
		
		
		//4.4 促销管理
		//4.4.1 已结束的店铺活动
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/myprom/promcount",
  			async : true,
			type : "post",
			data : {shopId: shopId, status: "20"},
  			dataType:'json',
  			success : function(data) {
  				if(data && data.resultFlag=="ok"){
  					$("#shopPromOver").html("("+data.promCount+")");
  				}else{
  					$("#shopPromOver").html("(0)");
  				}
  				$("#shopPromOver").click(function(){
  					window.location.href = GLOBAL.WEBROOT + "/seller/myprom?status=20&shopId="+shopId;
  				});
  			}
  		});
		//4.4.2 进行中的店铺活动
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/myprom/promcount",
  			async : true,
			type : "post",
			data : {shopId: shopId, status: "10"},
  			dataType:'json',
  			success : function(data) {
  				if(data && data.resultFlag=="ok"){
  					$("#shopPromActive").html("("+data.promCount+")");
  				}else{
  					$("#shopPromActive").html("(0)");
  				}
  				$("#shopPromActive").click(function(){
  					window.location.href = GLOBAL.WEBROOT + "/seller/myprom?status=10&shopId="+shopId;
  				});
  			}
  		});
		
		//5. 交易趋势
		$.eAjax({
//  			url : GLOBAL.WEBROOT + "/seller/shopdashboard/dailyTradeData",
  			url : GLOBAL.WEBROOT + "/seller/shopdashboard/dailyTradeDataWrapped",
  			async : true,
			type : "post",
			data : {shopId: shopId},
  			dataType:'text',
  			success : function(data) {
  				if(typeof data == 'string') data = $.parseJSON(data);
  				if(data && data.serviceState=='0000'){//正常
  					$("#shopORDTrendZW").hide();
  					$("#shopORDTrend").show();
  					var list = data.tradeList;
  					//排序处理
  					list.sort(function(a,b){return Number(a.dataDate)-Number(b.dataDate)});
  					//无数据判断
  					var flag = false;
  					for(var i in list){
  						flag = flag||(list[i].orderAmount!="0");
  						if(flag) break;
  					}
  					if(flag){//有数据刷新图表
  						_drawChart(dailyTradeDataChart, list);
  					}else{//无数据，展现“无数据”图例
  						$("#shopORDTrend").hide();
  	  					$("#shopORDTrendZW").show();
  					}
  				}else {
  					$("#shopORDTrend").hide();
  					$("#shopORDTrendZW").show();
  				}
  			}
  		});
		
		
		//6. 商品热销排行
		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/shopdashboard/shophotsales",
  			async : true,
			type : "post",
			data : {shopId: shopId},
  			dataType:'json',
  			success : function(data) {
  				if(data && data.length>0){
  					$("#hotList").show();
  					$("#hotListZW").hide();
  					for(var i=0; i<5; i++ ){
  						var shopHotLi = "#shopHotLi"+(parseInt(i)+1);
  						if(data[i]){
  							var title = data[i].gdsName;//标题（商品名称）
  	  						var con = data[i].gdsSubHead;//内容（副标题）
  	  						con = !!con?con:"";//无内容显示空
  	  						var html = '<a href="javascript:void(0)"><h3>'+title+'</h3><p>'+con+'</p></a>';
	  						$(shopHotLi).html(html);
	  						//商品详情钻取
	  						var gdsUrl = data[i].gdsUrl;
	  						(function(gdsUrl){
	  							$(shopHotLi).click(function(){
		  		  					window.location.href = GLOBAL.WEBROOT + gdsUrl;
		  		  				});
	  					    })(gdsUrl);
	  						if(i==0){
	  							$("#shopHotPic1").attr("src", data[i].imageUrl);//冠军展示图
	  							(function(gdsUrl){
	  								$("#shopHotPic1").click(function(){
			  		  					window.location.href = GLOBAL.WEBROOT + gdsUrl;
			  		  				});
		  					    })(gdsUrl);
	  						}
  						}else{
  							$(shopHotLi).html('<img src="../../images/seller/zw-phb2.png" alt=""/>');//暂无数据
  						}
  					}
  				}else{
  					$("#hotListZW").show();
  					$("#hotList").hide();
  				}
  			}
  		});
	};
	
	
	/**
	 *  刷新图表
	 */
	function _drawChart(selector, data){
		var xAxisData = [];//日期
		var orderAmountSeries = [];//下单
		var paidAmountSeries = [];//支付
		
		//处理数据
		for(var i=0; i<data.length; i++){
			xAxisData.push(data[i].dataDate);
			orderAmountSeries.push(data[i].orderAmount/100);//分转元
			paidAmountSeries.push(data[i].paidAmount/100);//分转元
		}
		
		// 指定图表的配置项和数据
	    var option = {
	        title: {    
	            text: '近30天交易情况',
	            textStyle: {
	                fontSize: 18,
	                fontWeight: 'bolder',
	                color: 'orange'
	            }
	        },
	        tooltip: {
	        	trigger: 'axis'
	        },
	        legend: {
	            data:['支付金额', '下单金额']
	        },
	        calculable : true,
	        xAxis: {
//	            data: ["2016/05/11","2016/05/13","2016/05/15","2016/05/17","2016/05/19","2016/05/21"]
	        	data: xAxisData
	        },
            yAxis: [
		             {
		                 type : 'value',
		                 axisLabel : {
		                     formatter: '{value} 元'
		                 }
		             }
		         ],
	        series: [
	            {
		            name: '支付金额',
		            type: 'line',
//		            data: [5000, 20000, 36000, 10000, 10000, 20000]
		            data: paidAmountSeries
	            },
	            {
		            name: '下单金额',
		            type: 'line',
//		            data: [19200, 220000, 89000, 210000, 70500, 120900]
	            	data: orderAmountSeries
	            }
	        ]
	    };

	    // 使用刚指定的配置项和数据显示图表。
	    selector.setOption(option);
	}
		
});