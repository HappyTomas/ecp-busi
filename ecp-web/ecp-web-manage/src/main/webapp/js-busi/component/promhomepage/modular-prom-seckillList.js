;
var nowDateTime;
var endTime;
var countTimer;
var all_array_length=0;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-prom-seckillList").modular_prom_seckillList();
				}
			};
		});
	} else {
		// 全局模式 ，不使用AMD 规范的时候，使用的插件
		factory(jQuery);
	}
}(function($) {
	// / 具体插件的定义；
	var defaultOpts = {
		placeId : ""
	};
	
	$modularSeckillList = {
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			var date_str = opts.nowDate.replace(/-/g,"/");//一般得到的时间的格式都是：yyyy-MM-dd hh24:mi:ss，所以我就用了这个做例子，是/的格式，就不用replace了。  
			var optsNowDate = new Date(date_str);//将字符串转化为时间  
			nowDateTime=optsNowDate.getTime();
			var caroBox=$('#caroBox',el);
			var siteId=$('#siteId').val();
			$.eAjax({
				url : $webroot + "commonmodular/getPromTabList",
				data : {siteId : siteId},
				success : function(returnInfo){
					var promId_0=0;
					var inHtml='<ul class="clearfix">';
					for (var i = 0; i < returnInfo.length; i++) {
						var promInfo=returnInfo[i];
						if(0==i){
							promId_0=promInfo.id;
							inHtml+='<li class="item active" li_id="'+promInfo.id+'"><a href="javascript:void(0)" data-toggle="tab">'+promInfo.promTheme+'</a></li>';
						}else{
							inHtml+='<li class="item " li_id="'+promInfo.id+'"><a href="javascript:void(0)" data-toggle="tab">'+promInfo.promTheme+'</a></li>';
						}
					}
					//以下循环添加数据测试滚动
//					for (var i = 0; i < 12; i++) {
//						inHtml+='<li class="item " li_id="'+(100+i)+'"><a href="javascript:void(0)" data-toggle="tab">'+("限时秒杀"+(10+i))+'</a></li>';
//					}
					inHtml+='</ul>';
					caroBox.html(inHtml);
					$modularSeckillList.showTab();
					$("#caroBox ul li").live("click",function(){
						var promId=$(this).attr('li_id');
						$modularSeckillList.showProm(el,opts,promId,1);
					});
					if(0!=promId_0){
						$modularSeckillList.showProm(el,opts,promId_0,1);
					}
				}
			});
		},
		"showTab":function(){
			var seckillW=0;
	        var isFlag=false;
	        var wi=$('#caroBox').outerWidth();
	        $('.caro_trigger a').hide();
	        //now_w=0;
	        $('.seckill-tab li').each(function(i){
	            seckillW+=$(this).outerWidth();
	            all_array_length+=$(this).outerWidth();
	            if(seckillW>=wi && !isFlag){
	                isFlag=true;
	                $('.caro_trigger a').show();
	            }
	        });
	        $('.caro_trigger a').live("click",function(){
	            var scollUl=$('.seckill-tab ul');
	            var movel=0-scollUl.position().left;
	            if($(this).hasClass('caro_prev')){
	               if(movel>0){
            		   movel=movel-100;
            	   }
               }else{
            	   if(movel+wi<all_array_length){
            		   movel=movel+100;
            	   }
               }
	           var move_length=0-movel;
               scollUl.stop().animate({
                   left:move_length
               },100);
	        })
		},
		"pageShow":function(page,el,opts,promId){
			$('#secKill_pageSize').text(page.pageSize);
			$('#secKill_pageNo').text(page.pageNo);
			$('#secKill_pageCount').text(page.pageCount);
			if(page.count==0){
				$('.bPage').hide();
				$('.secKillNodataDiv').show();
			}else{
				$('.bPage').show();
				$('.secKillNodataDiv').hide();
				var bPageFirstPage= $('#bPageFirstPage');
				var bPagePreviousPage= $('#bPagePreviousPage');
				for (var i = 0; i < 5; i++) {
					var li=bPagePreviousPage.next();
					if(li.attr('id')!='bPageNextPage'){
						li.remove();
					}
				}
				var nowLi='<li class="active"><a href="javascript:void(0);">'+page.pageNo+'</a></li>';
				bPagePreviousPage.after(nowLi) ;
				for (var i = page.pageNo-1; i >0 && i>=page.pageNo-2; i--) {
					var beforeLi='<li ><a href="javascript:void(0);">'+i+'</a></li>';
					bPagePreviousPage.after(beforeLi) ;
				}
				var bPageNextPage= $('#bPageNextPage');
				var bPageLastPage= $('#bPageLastPage');
				for (var i = page.pageNo+1; i<=page.pageNo+2 && i<=page.pageCount; i++) {
					var afterLi='<li ><a href="javascript:void(0);">'+i+'</a></li>';
					bPageNextPage.before(afterLi) ;
				}
				if(page.pageNo!=1){
					bPageFirstPage.removeClass('disabled');
					bPagePreviousPage.removeClass('disabled');
				}else{
					bPageFirstPage.addClass('disabled');
					bPagePreviousPage.addClass('disabled');
				}
				
				if(page.pageNo!=page.pageCount){
					bPageNextPage.removeClass('disabled');
					bPageLastPage.removeClass('disabled');
				}else{
					bPageNextPage.addClass('disabled');
					bPageLastPage.addClass('disabled');
				}
				$("#secKill_ul").find("li").each(function (i) {
					if(i<2){
						return;
					}
					var p=$(this).text();
					var newPageNo;
					if("首页"==p){
						newPageNo=1;
					}else if("尾页"==p){
						newPageNo=page.pageCount;
					}else if("«"==p){
						newPageNo=page.pageNo-1;
					}else if("»"==p){
						newPageNo=page.pageNo+1;
					}else{
						newPageNo=p;
					}
					$(this).unbind("click.sk").bind("click.sk",function () {
						if($(this).hasClass('active')){
							return;
						}
						if($(this).hasClass('disabled')){
							return;
						}
						$modularSeckillList.showProm(el,opts,promId,newPageNo);
					});
				}) 
			}
		},
		/**
		 * 调用获取数据组件
		 */
		"showProm":function(el,opts,promId,pageNo){
			clearInterval(countTimer);//取消第一个
			var showWay=opts.showWay;
			var pageSize=showWay*4;
			$.eAjax({
				url : $webroot + "commonmodular/getSecKillInfo",
				data : {promId : promId,pageSize:pageSize,pageNo:pageNo,imgType:"_600x600!",isWeb:"true"},
				success : function(returnInfo){
					nowDateTime=returnInfo.promInfoResponseDTO.nowTime;
					var ifStart=returnInfo.promInfoResponseDTO.ifStart;
					var title="距开始：";
//					ifStart=0;
//					var nowDate2=new Date();
//					returnInfo.promInfoResponseDTO.startTime=nowDate2.getTime()+3000;
					if("0"==ifStart){
						$('.seckill-name').html('<i class="sec-icon"></i>即将开始');
						endTime=returnInfo.promInfoResponseDTO.startTime;
					}else if("1"==ifStart){
						title="距结束：";
						$('.seckill-name').html('<i class="sec-icon"></i>抢购中，先抢先得');
						endTime=returnInfo.promInfoResponseDTO.endTime;
					}else if("2"==ifStart){
						$('.seckill-name').html('<i class="sec-icon"></i>已结束，谢谢惠顾');
						$('.seckill-time').hide();
					}
					countTimer =setInterval(function(){
						nowDateTime+=1000;
						var nowDate=nowDateTime;
						if(nowDate>=endTime){
							clearInterval(countTimer);//取消第一个
							$modularSeckillList.showProm(el,opts,promId,pageNo);
						}else{
							var timeStr=$modularSeckillList.showTime(nowDate,endTime);
							$('.seckill-time').html(title+'<div class="time-box">'+timeStr+'</div>');
						}
					},1000);
					var row = $('ul.goodsPicsRow',el);
					$modularSeckillList.pageShow(returnInfo.page,el,opts,promId);
					var gdss = returnInfo.page.result;
					$(row).empty();
					if(gdss!=null){
						$.each(gdss, function(i, gds) {
							var gdsInfo = {
								gdsId : gds.gdsId,
								gdsName : gds.gdsName,
								gdsDesc: gds.gdsDesc,
								basePrice: (gds.basePrice/100).toFixed(2),
								buyPrice: (gds.buyPrice/100).toFixed(2),
								killPrice: (gds.killPrice/100).toFixed(2),
								mediaUuid : gds.mediaUuid,
								url : gds.url,
								detailURL : gds.detailURL,
								buyCnt : gds.buyCnt,
								promCnt : gds.promCnt,
								percent : gds.percent,
								ifSell : gds.ifSell,
								gdsTypeFlag: gds.gdsTypeFlag
							};
							var $thisObj=$(this);
							var item = '<li class="tLi">';
							if(opts.isPub){
								item += '<div class="tpl-img"><a href="'+gdsInfo.detailURL+'" target="_blank"><img id="gdsInfoPicUrl"  src="'+gdsInfo.url+'" style=""></a></div>';
							}else{
								item += '<div class="tpl-img"><img id="gdsInfoPicUrl" src="'+gdsInfo.url+'" style=""></div>';
							}
							item += '    <div class="box-wrap">';
							item += '<h4>'+gdsInfo.gdsName+'</h4>';
							var gdsDesc=null!=gdsInfo.gdsDesc&&""!=gdsInfo.gdsDesc?gdsInfo.gdsDesc:"简介：无";
							item += '<p class="p-com">'+gdsDesc+'</p>';
							if("0"==ifStart){
								var time = new Date(returnInfo.promInfoResponseDTO.startTime);
								var startTimeStr=ebcDate.dateFormat(time , "yyyy年MM月dd日 hh:mm");
								item += '<div class="progress-box">';
								item += '    <div class="tips">'+startTimeStr+'正式开抢</div>';
								item += '</div>';
							}else{
								item += '<div class="progress-box">';
								item += '    <div class="num clearfix">';
								if(gdsInfo.gdsTypeFlag){
								item += '        <span class="pull-left">抢购'+gdsInfo.percent+'%</span>';
								}
								item += '        <span class="pull-right">已抢'+gdsInfo.buyCnt+'件</span>';
								item += '    </div>';
								if(gdsInfo.gdsTypeFlag){
								item += '    <div class="progress">';
								item += '        <div class="bar" style="width: '+gdsInfo.percent+'%"></div>';
								item += '    </div>';
								}
								item += '</div>';
							}
							
							item += '<div class="price">';
							item += '    <span class="p-primary"><i>￥</i>'+gdsInfo.basePrice+'</span>';
							item += '    <span class="p-seckill"><i>￥</i><em class="p-price">'+gdsInfo.killPrice+'</em></span>';
							if("0"==ifStart){
								item += '    <a class="pbtn pbtn-start">即将开始</a>';
							}else if("1"==ifStart && "1"==gdsInfo.ifSell){
								if(opts.isPub){
									item += '    <a href="'+gdsInfo.detailURL+'" target="_blank" class="pbtn ">马上抢</a>';
								}else{
									item += '    <a class="pbtn ">马上抢</a>';
								}
							}else{
								item += '    <a class="pbtn pbtn-soldOut">已抢完</a>';
							}
							item += '</div>';
							item += '</div>';
							item += '</li>';
							$(row).append(item);
						});
					}
				},
				error:function(){
					eDialog.error("服务异常!");
				}
			});
		},
		/**
		 *  date1  //开始时间
		 *  date2  //结束时间
		 */
		"showTime":function(startDate,endDate){
			var date3=endDate-startDate;  //时间差的毫秒数
			//计算出相差天数
			var days=Math.floor(date3/(24*3600*1000));
			//计算出小时数
			var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数
			var hours=Math.floor(leave1/(3600*1000));
			//计算相差分钟数
			var leave2=leave1%(3600*1000);        //计算小时数后剩余的毫秒数
			var minutes=Math.floor(leave2/(60*1000));
			//计算相差秒数
			var leave3=leave2%(60*1000);      //计算分钟数后剩余的毫秒数
			var seconds=Math.round(leave3/1000);
			days=days<0?0:days;
			days=days>9?days:'0'+days;
			hours=hours<0?0:hours;
			hours=hours>9?hours:'0'+hours;
			minutes=minutes<0?0:minutes;
			minutes=minutes>9?minutes:'0'+minutes;
			seconds=seconds<0?0:seconds;
			seconds=seconds>9?seconds:'0'+seconds;
			return '<span class="time">'+days+'</span>天<span class="time">'+hours+'</span>时<span class="time">'+minutes+'</span>分<span class="time">'+seconds+'</span>秒';
		}
	};

	$.fn.modular_prom_seckillList = function() {
		return this.each(function() {
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			$modularSeckillList.control(this,opts);
			return $(this);
		});
	};
	
	
}));
