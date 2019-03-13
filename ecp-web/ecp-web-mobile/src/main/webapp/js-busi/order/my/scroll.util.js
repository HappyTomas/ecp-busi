/**
 * Created by wang on 16/9/5.
 */
/**
 * Created by zqr on 2016/5/25.
 */
function LoadScroll(element, options) {
    var defaults = $.extend({
        url: '',
        isAjax: false,
        callback: null,
        downCallback: null,
        preventDefault: false,
        params: {
            page: 1,
            count: 1
        }
    }, options);
    var $main = $('#' + element);
    var $list = $main.find('.scrollCont');
    var $pullDown = $main.find('.pull-down');
    var $pullDownLabel = $pullDown.find('.pull-label');
    var $pullUp = $main.find('.pull-up');
    var $pullUpLabel = $pullUp.find('.pull-label');
    var topOffset = -$pullDown.outerHeight();
    var _this = this;
    var loadTxt = "加载完成";
    var noDataTxt = "无数据了";

    this.iScroll = null;

    /*设置url*/
    this.getURL = function (params) {
        var queries = [];
        for (var key in  params) {
            if (key !== 'page') {
                queries.push(key + '=' + params[key]);
            }
        }
        queries.push('page=');
        return defaults.url + '?' + queries.join('&');
    };

    /* ajax 渲染界面 */
    this.renderList = function (page, type) {
        var $el = $pullDown;
        if (type === 'load') {
            $el = $pullUp;
        }
        $.getJSON(this.URL + page).then(function (data) {
            var values = data.values;
            if (defaults.callback) {
                defaults.callback(_this, data);
            } else {
                _this.total = values.total;
                var html = template(element + '-tpl', data);//Handlebars.compile($('#'+element+"-tpl").html());
                if (type === 'refresh') {
                    $list.empty().append(html);
                } else if (type === 'load') {
                    $list.append(html);
                } else {
                    $list.html(html);
                }
            }
            if (type === 'refresh') {
                $pullDownLabel.text(loadTxt);
            } else if (type === 'load') {
                $pullUpLabel.text("上拉加载更多");
            }
            // refresh iScoll
            calHeight();

            //有一些scroll插件表达式不太好处理的，需要加载之后做操作的，放到这个属性
            if(defaults.call){
                defaults.call();
            }

            var timeClear;
            if (timeClear) {
                clearTimeout(timeClear);
            } else {
                timeClear = setTimeout(function () {
                    _this.iScroll.refresh();
                }, 100);
            }


        }, function () {
            calHeight();
            eDialog.alert("加载错误，请检查网络问题!");
        }).always(function () {
            if (type !== 'load') {
                _this.iScroll.scrollTo(0, topOffset, 800, $.AMUI.iScroll.utils.circular);
            } else {

            }
        });

    };

    /* 初始化界面 */
    this.init = function () {
        var $params = defaults.params;
        var _this = this;
        var pullFormTop = false;
        var pullStart;

        _this.iScroll = new $.AMUI.iScroll('#' + element, {
            preventDefault: defaults.preventDefault
        });

        document.addEventListener('touchmove', function (e) {
            e.preventDefault();
        }, false);


        var myScroll = _this.iScroll;
        if (defaults.isAjax) {//存在ajax请求的情况
            initParam();
            this.renderList(this.page);
            var directionY = 0;
            myScroll.off('scrollStart');
            myScroll.on('scrollStart', function () {
                $pullDown.find('.am-icon-spin').hide();
                $pullUp.find('.am-icon-spin').hide();
                if (this.y >= topOffset) {
                    pullFormTop = true;
                }
                pullStart = this.y;
                directionY = this.directionY;
                if (this.directionY === -1) {
                    $pullDownLabel.text("下拉刷新");
                }
                /*  if(this.directionY === 1 && (this.y == this.maxScrollY )){
                 if (_this.next < _this.total) {
                 $pullUpLabel.text("正在加载..");
                 }else{
                 $pullUpLabel.text(noDataTxt);
                 $pullUp.find('.am-icon-spin').hide();
                 }
                 }*/
            });
            $(".scroll", myScroll.wrapper).on("touchmove", function () {
                $pullUpLabel.text("正在加载");
                $pullUp.find('.am-icon-spin').show();
            });
            myScroll.off('scrollEnd');
            myScroll.on('scrollEnd', function () {
                //下拉
                if (pullFormTop && this.directionY === -1 && $pullDown.size() >= 1) {
                    _this.handlePullDown();
                }
                //上拉
                pullFormTop = false;
                if (this.y == this.maxScrollY && $pullUp.size() >= 1) {
                    _this.handlePullUp();
                }
            });
        } else { //非列表的刷新
            if ($pullDown.size() > 0) {
                _this.iScroll.scrollTo(0, topOffset, 800, $.AMUI.iScroll.utils.circular);
            }
            myScroll.off('scrollStart');
            myScroll.on('scrollStart', function () {
                $pullDown.find('.am-icon-spin').hide();
                if (this.y >= topOffset) {
                    pullFormTop = true;
                }
                pullStart = this.y;
                if (this.directionY === -1) {
                    $pullDownLabel.text("下拉刷新");
                }
            });
            myScroll.off('scrollEnd');
            myScroll.on('scrollEnd', function () {
                //下拉
                $pullDown.find('.am-icon-spin').show();
                if (pullFormTop && this.directionY === -1 && $pullDown.size() >= 1) {
                    $pullDownLabel.text("正在加载");
                    if (defaults.downCallback) {
                        defaults.downCallback(_this, afterDownBack);
                    }
                }
            });
        }
    };

    this.handlePullDown = function () {
        $pullDownLabel.text("正在加载");
        $pullDown.find('.am-icon-spin').show();
        this.prev = this.next = 1;
        this.renderList(this.prev, 'refresh');
        $pullUpLabel.text("上拉加载更多");
    };

    this.handlePullUp = function () {
        $pullUp.find('.am-icon-spin').show();
        $pullUpLabel.text("正在加载");
        if (this.accMul(this.count, this.page) < this.total) {
            //这个的一个逻辑是，this.next负责控制叠加每次返回的值如果超过this.total就不用再发起请求
            //this.page 其实就是页码了
            this.next += this.count;
            this.page += 1;
            this.renderList(this.page, 'load');
        } else {
            $pullUpLabel.text(noDataTxt);
            $pullUp.find('.am-icon-spin').hide();
        }
    };

    /* 刷新iscroll */
    this.refresh = function () {
        this.iScroll.refresh();
    };

    function afterDownBack() {
        $pullDownLabel.text(loadTxt);
        calHeight();
        _this.iScroll.scrollTo(0, topOffset, 800, $.AMUI.iScroll.utils.circular);
    }

    function calHeight() {
        var aHi = 0;
        $list.children().each(function () {
            aHi += $(this).outerHeight();
        })
        if (aHi <= $('#' + element).height()) {
            $list.height($('#' + element).outerHeight() + 20)
        } else {
            $list.height("auto");
        }
    }

    function initParam() {
        var $params = defaults.params;
        _this.prev = _this.next = _this.page = $params.page ? $params.page : 1;
        _this.count = $params.count ? $params.count : 1;
        _this.total = null;
        _this.URL = _this.getURL(defaults.params);
    }

    /* 刷新页面 */
    this.refreshPage = function (opt) {
        defaults = $.extend(defaults, opt);
        initParam();
        this.renderList(this.page);
    };

    this.accMul = function(arg1,arg2){
        var m=0,s1=arg1.toString(),s2=arg2.toString();
        try{m+=s1.split(".")[1].length;}catch(e){}
        try{m+=s2.split(".")[1].length;}catch(e){}
        return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
    };

    this.init();
};


