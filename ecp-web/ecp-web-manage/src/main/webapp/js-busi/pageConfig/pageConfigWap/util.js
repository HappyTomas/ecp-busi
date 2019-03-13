$(function() {
    /**
     * Created by zqr
     */
    var U = window.U || (window.U = {});

    /*U.toTop = function(obj){
     $(obj).click( function(event) {
     $('html, body').animate({scrollTop:0}, 500);
     return false;
     });
     };*/
    U.qtip = function(obj,opt){
        var _opt= $.extend({
            style: {
                classes: 'qtip-bootstrap'
            },
            position:{
                my: 'right center',
                at: 'left center'
            }
        },opt);
        var _p={}
        switch(_opt.position)
        {
            case 'top':
                _p={
                    my: 'bottom center',
                    at: 'top center'
                }
                break;
            case 'right':
                _p={
                    my: 'left center',
                    at: 'right center'
                }
                break;
            case 'bottom':
                _p={
                    my: 'top center',
                    at: 'bottom center'
                }
                break;
            default:
                _p={
                    my: 'right center',
                    at: 'left center'
                }
                break;
        }

        _opt.position=_p;
        var c=$(obj).qtip(_opt);

    }

    U.tab = function (head, body, opt) {
        var _opt = $.extend({
            event: 'click',
            filterD: null,
            callback: null,
            defItem: 0
        }, opt);

        var _hc = $(head).children();
        var _bc = $(body).children();
        var _actIndex = null;

        _hc = _opt.filterD ? _hc.not(_opt.filterD) : _hc;
        show(_opt.defItem);

        _hc.on(_opt.event, function (e) {
            var _id = $(this).index();

            if ((!_actIndex && _actIndex != 0) || _actIndex != _id) {
                show($(this).index());
                _opt.callback && _opt.callback($(this));
            }
            _actIndex = _id;
            e.preventDefault();

        });
        function show(index) {
            _hc.eq(index)
                .addClass('active')
                .siblings().removeClass('active');
            _bc.eq(index).show().siblings().hide();
        }

    };
});
