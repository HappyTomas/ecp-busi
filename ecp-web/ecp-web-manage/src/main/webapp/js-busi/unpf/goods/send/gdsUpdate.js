$(function(){
	 var _shopId = $('#shopId').val();
	 var _gdsId = $('#gdsId').val();
	 $("input[name='platType']").click(function(){
		 var _platType = $(this).val();
		 var data = [];
	     data.push({name:'platType',value:_platType});
	     data.push({name:'shopId',value:_shopId});
	     data.push({name:'gdsId',value:_gdsId});
	     var url =  GLOBAL.WEBROOT+'/unpf/gdsManage/getOutGdsId';
	        $.eAjax({
	            url:url,
	            data:data,
	            success:function(result){
	                if(result&&result.resultFlag=='ok'){
	                	 $("#sendGds").val(result.resultMsg);
	                }
	            },
	            failure:function(){
	                bDialog.closeCurrent();
	            }
	        });
	 });
	//保存
	$('#saveGdsUpdate').click(function(){
        var _platType = $("input[name='platType']:checked").val();
        var _sendGds = $('#sendGds').val();
        var data = [];
        data.push({name:'platType',value:_platType});
        data.push({name:'shopId',value:_shopId});
        data.push({name:'gdsId',value:_gdsId});
        data.push({name:'sendGds',value:_sendGds});
        var url =  GLOBAL.WEBROOT+'/unpf/gdsManage/gdsUpdateSubmit';
        $.eAjax({
            url:url,
            data:data,
            success:function(result){
                if(result&&result.resultFlag=='ok'){

                    eDialog.alert('修改成功',function(){
                        bDialog.closeCurrent({result:'ok'});
                    },'confirmation');
                }else{
                    eDialog.alert(result.resultMsg,function(){
                        bDialog.closeCurrent({result:'ok'});
                    },'error');

                }
            },
            failure:function(){
                bDialog.closeCurrent();
            }
        });
	});
	//返回
	$('#backGdsUpdate').click(function(){
        bDialog.closeCurrent();
	});
});
