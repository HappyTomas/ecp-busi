/**
 * Created by zqr on 2016/5/25.
 */
function LoadScroll(element, options) {
	//private params==================
	var _defaults ={
        url: '',//请求地址	
        type:"post",
        isUpLoad:true,
        isDownRefresh:true,
        upBeforeCall:null,
        downBeforeCall:null,
        callback: null,//成功回调
        upCallback:null,//上拉加载成功回调
        downCallback: null,//下拉刷新成功回调
        preventDefault: false,//是否把默认动作禁止
        keepOrderReq:false,//是否按顺序进行请求，即等一个请求完成 才进行下一个请求
        dataType:'json',
        params: {
            pageNo: 1,//第几页
            pageSize:8,
            totalPage:1000
        }
    };
	var _options = $.extend(true,{},_defaults, options);
    //private important text
    var _loadTxt = "加载完成";
    var _noDataTxt = "无更多数据";
    
	//private jquery objs====================
	var _$main = $('#' + element);
	var _$contend = _$main.find('.scrollCont');
	var _$pullDown = _$main.find('.pull-down');
    var _$pullDownLabel = _$pullDown.find('.pull-label');
    var _$pullUp = _$main.find('.pull-up');
    var _$pullUpLabel = _$pullUp.find('.pull-label');
    var _$this = this;
    var els = {
    		$main:_$main,
    		$contend:_$contend,
    		$pullDown:_$pullDown,
    		$pullDownLabel:_$pullDownLabel,
    		$pullUp:_$pullUp,
    		$pullUpLabel:_$pullUpLabel,
    		scorller : _$this
    }
    //private important nums===================
    var _topOffset = -_$pullDown.outerHeight();
    var _total = 1000;
    //private other prop======================
    var _iScroll = null;
    var _isBusing = false; //如果为true 则对上拉及下拉不促发任何事件
    
    //private other mehtod========================
    var _doUpLoadRequest = function(){//上拉加载请求后台
    	if(_options.upBeforeCall && $.isFunction(_options.upBeforeCall)){
    		_options.upBeforeCall(els);
		}
    	var url=_options.url;
    	if(!url){
	    		if(_options.callback && $.isFunction(_options.callback)){
					_options.callback(els,null,null);
				}
	    		_isBusing = false;
    			return _$this;
    		}
    	$.eAjax({
			url : url,
			data : _options.params || _defaults.params||{},
			async : true,
			type : _options.type || _defaults.type||"post",
			dataType : _options.dataType||_defaults.dataType,
			success : function(data, textStatus) {
	            _$pullUpLabel.text("上拉加载更多");
				if(_options.upCallback && $.isFunction(_options.upCallback)){
					_options.upCallback(els,data, textStatus);
				}
				if(_options.callback && $.isFunction(_options.callback)){
					_options.callback(els,data,textStatus);
				}
				// refresh iScoll
	            _calHeight();
	            var timeClear;
	            if (timeClear) {
	                clearTimeout(timeClear);
	            } else {
	                timeClear = setTimeout(function () {
	                    _iScroll.refresh();
	                }, 100);
	            }
	            
	            _isBusing = false;
			},
			error :  function(){
				_options.callback(els,null,false);
				_calHeight();
				_isBusing = false;
			}
		});
    	_pageNoGoing();//设置为下一页
    	return _$this;
    };
    
    var _doDownRefreshRequest = function(){//下拉刷新请求后台
    	if(_options.downBeforeCall && $.isFunction(_options.downBeforeCall)){
    		_options.downBeforeCall(els);
		}
    	var url=_options.url;
    	_pageNoReset();
    	if(!url){
	    		if(_options.callback && $.isFunction(_options.callback)){
					_options.callback(els,null,null);
				}
	    		_isBusing = false;
    			return _$this;
    		}
    	$.eAjax({
			url : url,
			data : _options.params || _defaults.params||{},
			async : true,
			type : _options.type || _defaults.type||"post",
			dataType : _options.dataType||_defaults.dataType,
			success : function(data, textStatus) {
	            _$pullUpLabel.text(_loadTxt);
				if(_options.downCallback && $.isFunction(_options.downCallback)){
					_options.downCallback(els,data, textStatus);
				}
				if(_options.callback && $.isFunction(_options.callback)){
					_options.callback(els,data,textStatus);
				}
				// refresh iScoll
	            _calHeight();
	            var timeClear;
	            if (timeClear) {
	                clearTimeout(timeClear);
	            } else {
	                timeClear = setTimeout(function () {
	                    _iScroll.refresh();
	                }, 100);
	            }
	            _scorllTop();
	            _isBusing = false;
			},
			error :  function(){
				_options.callback(els,null,false);
				_calHeight();
				_scorllTop();
				_isBusing = false;
			}
		});
    	_pageNoGoing();//设置为下一页
    	return _$this;
    };
    var _scorllTop = function(){//回到顶部
    	_iScroll.scrollTo(0, _topOffset, 800, $.AMUI.iScroll.utils.circular);
    }
    var _pageNoGoing = function(){
    	_defaults.params && _defaults.params.pageNo && (_defaults.params.pageNo += 1);
    	_options.params && _options.params.pageNo && (_options.params.pageNo += 1);
    	return _$this;
    }
    var _getNextPage = function (){
    	var next = _options.params && _options.params.pageNo && _options.params.pageNo+1;
    	next = next || _defaults.params.pageNo + 1;
    	return next;
    }
    var _pageNoReset = function(){
    	_defaults.params && _defaults.params.pageNo && (_defaults.params.pageNo = 1);
    	_options.params && _options.params.pageNo && (_options.params.pageNo = 1);
    	return _$this;
    }
    var  _calHeight = function() {
    	var showHeight = $(window).height() - (+$(".am-header").outerHeight()||0)-(+$(".am-navbar").outerHeight()||0);
        var aHi = 0;
        _$contend.children().each(function () {
            aHi += $(this).outerHeight();
        })
        if (aHi <= showHeight) {
        	_$contend.height(showHeight+10);
        } else {
        	_$contend.height("auto");
        }
        return _$this;
    }
    var _handlePullDown = function () {
        _$pullDownLabel.text("正在加载");
        _$pullDown.find('.am-icon-spin').show();
        _doDownRefreshRequest();
        
    };

    var _handlePullUp = function () {
        var next = _getNextPage();
        if (_total && _total >0 && next < _total) {
            _$pullUpLabel.text("正在加载");
            _$pullUp.find('.am-icon-spin').show();
        	_doUpLoadRequest();
        } else {
            _$pullUpLabel.text(_noDataTxt);
            _$pullUp.find('.am-icon-spin').hide();
            _isBusing = false;
        }
    };
    
    var _init = function () {/* 初始化界面 */
        var pullFormTop = false;
        var pullStart;

        _iScroll = new $.AMUI.iScroll('#' + element, {
            preventDefault: null != _options.preventDefault ? _options.preventDefault: _defaults.preventDefault
        });
        document.addEventListener('touchmove', function (e) {
            e.preventDefault();
        }, false);
        var iScroll = _iScroll;
        var directionY = 0;
        var beginY=0;
        var endY=0;
        iScroll.off('scrollStart');
        iScroll.on('scrollStart', function () {
        	if(_options.keepOrderReq && _isBusing){
        		return false;
        	}
        	
            _$pullDown.find('.am-icon-spin').hide();
            _$pullUp.find('.am-icon-spin').hide();
            if (this.y >= _topOffset) {
                pullFormTop = true;
            }
            pullStart = this.y;
            directionY = this.directionY;
            if (this.directionY === -1) {
            	_$pullDown.show();
                _$pullDownLabel.text("下拉刷新");
            }else{
            	var next = _getNextPage();
            	_$pullUp.show();
            	if(_total && _total >0 && next < _total){
                	_$pullUpLabel.text("释放加载更多");
            	}
            }
            var trans = $('.scroll',_$main).css('transform').match(/\-?[0-9]+/g);
            beginY = trans[5];
        });
        $(".scroll", iScroll.wrapper).off("touchmove");
        $(".scroll", iScroll.wrapper).on("touchmove", function () {
            var trans = $('.scroll',_$main).css('transform').match(/\-?[0-9]+/g);
            endY = trans[5];
        });
        iScroll.off('scrollEnd');
        iScroll.on('scrollEnd', function () {
        	if(_options.keepOrderReq && _isBusing){
        		return false;
        	}
        	
            if (pullFormTop && (this.directionY === -1
                ||(endY-beginY>_topOffset)) && _$pullDown.size() >= 1 && _options.isDownRefresh) {
            	_isBusing = true;
            	_handlePullDown();
            }
            //上拉
            pullFormTop = false;
            if (this.y == this.maxScrollY && _$pullUp.size() >= 1 && _options.isUpLoad) {
            	_isBusing = true;
            	_handlePullUp();
            }
        });
    };
    
    //public mehtod=============================
    /* 刷新iscroll */
    this.refresh = function () {
        setTimeout(function(){
        	_calHeight();
            _iScroll.refresh();
        },100);
        _$pullDown.find('.am-icon-spin').hide();
        _$pullUp.find('.am-icon-spin').hide();
    };

    /* 刷新页面 */
    this.refreshPage = function (options) {
    	if(options){
    		this.refreshOptions(options);
    	}
    	_init();
    	_handlePullDown();
    	
    };
    /* 刷新options*/
    this.refreshOptions = function (options) {
    	if(options){
    		_options = $.extend(true,{},_defaults,_options,options);
    	}
    };
	/* 刷新参数*/
	this.refreshParams = function(params){
		if(params){
			_options.params = $.extend(_options.params,params);
		}
		_$pullDown.hide();
        _$pullUp.hide();
	}
	this.setCount = function(count){
		_total = +count;
		if(0 < _total){
			_$pullUpLabel.text("上拉加载更多");
		}else{
			_$pullUpLabel.text(_noDataTxt);
		}
		_$pullUp.find('.am-icon-spin').hide();
	}
	_init();
};
