$(function(){
//页面业务逻辑处理内容

	var pageconst = {
			picconst:'annex'
	}
	
	var pageInit = function(){
		var init = function(){
			var imageAddr = function(){
				var addimg = $('.add-img:eq(0)').clone().attr('style','margin-top:2px');
				addimg.find('a').remove();
				$('.add-img:last').after($('.add-img:eq(0)').clone().attr('style','margin-top:2px'));
			}
			var getParams = function(){
				var data = [];
				data.push({name:'orderId',value:$('#orderId').val()});
				data.push({name:'shopId',value:$('#shopId').val()});
				data.push({name:'remark',value:$('#remark').val()});
				$('.img-off').each(function(i){
					data.push({name:pageconst.picconst+'['+i+'].vfsId',value:$(this).find('img').attr('id')});
					data.push({name:pageconst.picconst+'['+i+'].picName',value:$(this).find('img').attr('id')});
				});
				return data;
			}
			
			var checkImage = function(){
				if($('.img-off:eq(0)').find('img').attr('src')==undefined){
					return false;
				}
				return true;
			}
			
			$('#save').click(function(){
				if(checkImage()){
					$.eAjax({
						url:GLOBAL.WEBROOT+'/pay/offlineapply',
						data:getParams(),
						success:function(result){
							if(result&&result.resultFlag=='ok'){
								eDialog.alert("保存成功",function(){
									window.location.href = GLOBAL.WEBROOT+'/order/pay';
								},'success');
							}else{
								eDialog.alert(result.resultMsg);
							}
							
						}
					});
				}else{
					eDialog.alert('未上传图片',$.noop,'error');
				}
				
			});
			
			$('#offlineImageId').click(function(evt){
				busSelector.uploader({
					callback : function(data){
						if(data && data.results && data.results.length > 0){
							$.each(data.results,function(i,result){
								if(i>0)imageAddr();
							});
							$('.add-img').each(function(n){
								$('img',this).attr('src',data.results[n].url).attr('id',data.results[n].fileId);
							});
						}
					}
				}, evt);
			});
		}
		
		return {
			init:init
		}
	}
	pageConfig.config({
        //指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
        plugin : [],
        //指定页面
        init : function(){
            var p = new pageInit();
            p.init();
        }
    });
});

