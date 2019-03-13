/**
 * @name jQuery imageMaps plugin
 * @license GPL
 * @version 0.0.4
 * @date 11 22, 2010
 * @category jQuery plugin
 * @author Simon Tang (www@yiye.name)
 * @copyright (c) 2010 Simon Tang (http://yiye.name/)
 */
(function($) {
    $.fn.hotMaps = function(opt) {
        var actClz='active-hotBox';
        var movClz='moving-hotBox';
        var clz='hotBox';
        var _this=this;
        var options= $.extend(
            {
                dragCreat:false,
                posChangAfter:$.noop
            },opt);
        var hotObject;
        var boxData = {};
        var addPos=_this.addPos=function(datas,container,id){
            var $container=container?container:$(this);
            $container.each(function(){
                var localData=converJson(datas);
                $.each(localData,function(i,item){
                    var aDiv=$('<div class="'+clz+'">' +
                        '<div class="map-position-bg"></div>' +
                        '<span class="link-number-text"></span>' +
                        '<span class="delete">X</span>' +
                        '<span class="resize resize-l" data-res="resize-l"></span>' +
                        '<span class="resize resize-ltc" data-res="resize-ltc"></span>' +
                        '<span class="resize resize-r" data-res="resize-r"></span>' +
                        '<span class="resize resize-rbc" data-res="resize-rbc"></span>' +
                        '<span class="resize resize-rb" data-res="resize-rb"></span>' +
                        '<span class="resize resize-rlc" data-res="resize-rlc"></span>' +
                        '<span class="resize resize-lb" data-res="resize-lb"></span>' +
                        '<span class="resize resize-lbc" data-res="resize-lbc"></span>' +
                        '</div>');
                    if(id){
                        aDiv.attr('id',id);
                    }
                    $container.append(aDiv);
                    aDiv.css({"top":item.top,"left":item.left,"z-index":'18'});
                    if(container){
                        //如果是正在画图加上样式
                        aDiv.addClass(actClz);
                    }
                    if(item.width){
                        aDiv.css('width',item.width);
                    }
                    if(item.height){
                        aDiv.css('height',item.height);
                    }

                    options.posChangAfter(aDiv, item);

                    bindResize($container);
                });
            });
        };
        _this.getHotPos=function(){
            var pos=[];
            $('.'+clz,_this).each(function(){
                pos.push([
                    parseFloat($(this).css('left')),
                    parseFloat($(this).css('top')),
                    parseFloat($(this).css('width')),
                    parseFloat($(this).css('height'))
                ]);
            });
            return pos;
        };
        function bindResize($container){
            $('.resize',$container).each(function(){
                resize($(this));
            });
            function resize(obj){
                var hotResize = $(obj);
                var conrainer = $(obj).parent().parent();
                var hotFade=$('<div class="hot-fade"></div>');
                if($('.hot-fade').size()<=0){
                    hotFade.css({
                        'position':'absolute',
                        'width':'100%',
                        'height':'100%',
                        'top':'30px',
                        'left':'0',
                        'z-index':17
                    });
                    conrainer.append(hotFade);
                };
                conrainer.bind("mousemove",function(event){
                    if (!hotResize.data('mousedown')) return false;
                    var dx = event.pageX - hotResize.data('pageX');
                    var dy = event.pageY - hotResize.data('pageY');
                    if ((dx == 0) && (dy == 0)){
                        return false;
                    }
                    hotObject = hotResize.parent();
                    var p = hotObject.position();
                    var left = p.left;
                    var top = p.top;
                    var height = hotObject.height();
                    var width = hotObject.width();
                    switch (hotResize.data("res"))
                    {
                        case "resize-l":
                            height=height-dy;
                            width = width-dx;
                            top=top+dy;
                            left=left+dx;
                            break;
                        case "resize-ltc":
                            height=height-dy;
                            top=top+dy;
                            break;
                        case "resize-r":
                            height=height-dy;
                            width = width+dx;
                            top=top+dy;
                            break;
                        case "resize-rbc":
                            width = width+dx;
                            break;
                        case "resize-rb":
                            height=height+dy;
                            width = width+dx;
                            break;
                        case "resize-rlc":
                            height=height+dy;
                            break;
                        case "resize-lb":
                            height=height+dy;
                            width = width-dx;
                            left=left+dx;
                            break;
                        case "resize-lbc":
                            width = width-dx;
                            left=left+dx;
                            break;
                    }
                    if((top+height) > conrainer.height()){
                        height = height-((top+height)-conrainer.height());
                    }
                    if (height <20) height = 20;
                    if((left+width) > conrainer.width()){
                        width = width-((left+width)-conrainer.width());
                    }
                    if(width <20) width = 20;
                    hotObject.css({
                        width:width,
                        height:height
                    });
                    if(width>20){
                        hotObject.css({
                            left:left
                        });
                    }
                    if(height>20){
                        hotObject.css({
                            top:top
                        });
                    }
                    hotResize.data('pageX', event.pageX);
                    hotResize.data('pageY', event.pageY);
                    boxData={
                        left: left,
                        top: top,
                        width: width,
                        height:height
                    };
                    return false;
                }).on('mouseup mouseleave',function(event){
                    hotResize.data('mousedown', false);
                    return false;
                });
                hotResize.unbind('mousedown').mousedown(function(event){
                    hotResize.data('mousedown', true);
                    hotResize.data('pageX', event.pageX);
                    hotResize.data('pageY', event.pageY);
                    return false;
                }).unbind('mouseup mouseleave').bind('mouseup',function(event){
                    hotResize.data('mousedown', false);
                    if(boxData.left){
                        options.posChangAfter(hotObject, boxData);
                    }
                    return false;
                });

            }
        };
        function converJson(arr){
            var dataArr=[];
            $.each(arr,function(i,item){
                var a={};
                a.left=item[0];
                a.top=item[1];
                if(item[2]){
                    a.width=item[2];
                }
                if(item[3]){
                    a.height=item[3];
                }
                dataArr.push(a);
            });
            return dataArr;
        };
        return _this.each(function(){
            var $container = $(this);
            if ($container.size() == 0) return false;
            // startX, startY 为鼠标点击时初始坐标
            // diffX, diffY 为鼠标初始坐标与 box 左上角坐标之差，用于拖动
            var startX, startY, diffX, diffY;
            var dragging = false;
            var activeBox=$('.'+actClz,$container);
            var moveBox=$('.'+movClz,$container);
            // 鼠标按下
            $container.bind("mousedown", function(e) {
                startX = e.pageX;
                startY = e.pageY;

                // 如果鼠标在 box 上被按下
                if(e.target.className.match(/hotBox/)) {
                    // 允许拖动
                    dragging = true;
                    if(moveBox.size()>0) {
                        moveBox.removeClass(movClz);
                    }
                    $(e.target).addClass(movClz);
                    moveBox=$('.'+movClz,$container);
                    // 计算坐标差值
                    diffX = startX - e.target.offsetLeft;
                    diffY = startY - e.target.offsetTop;
                }
                else {
                    if(options.dragCreat){
                        addPos([[startX,startY]],$container);
                    }
                }
            });
            // 鼠标移动
            $container.bind("mousemove", function(e) {
            	var boxl,boxt,boxw,boxh;
                activeBox = $('.'+actClz,$container);
                moveBox = $('.'+movClz,$container);
                if(activeBox.size()>0) {
                    boxw = e.pageX - startX;
                    boxh = e.pageY - startY;
                    activeBox.css({width: boxw, height: boxh});
                }
                if(moveBox.size()>0 && dragging) {
                    boxl = e.pageX - diffX;
                    boxt = e.pageY - diffY;
                    moveBox.css({top:boxt,left:boxl});
                    hotObject = moveBox;
                }

                if(!boxw){
                    boxw = moveBox.width();
                    boxh = moveBox.height();
				}

                boxData={
                    left: boxl,
                    top: boxt,
                    width: boxw,
                    height:boxh
                }
            });
            // 鼠标抬起
            $container.bind("mouseup mouseleave",function(e) {
                // 禁止拖动
                activeBox=$('.'+actClz,$container);
                dragging = false;
                if(activeBox.size()>0) {
                    activeBox.removeClass(actClz);
                    if(activeBox.width() < 3 || activeBox.height() < 3) {
                        activeBox.remove();
                    }
                }
                if(boxData.left){
                    options.posChangAfter(hotObject, boxData);
				}

            });

            return this;
        });

    };

})(jQuery); 