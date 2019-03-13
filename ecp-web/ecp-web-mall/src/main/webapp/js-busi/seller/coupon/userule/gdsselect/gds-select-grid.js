$(function(){
	
    //页面业务逻辑处理内容
    var pageInit = function(){
    	
        var init = function(){

        	//获得当前弹出窗口对象
        	var _dlg = bDialog.getDialog();
        	//获得父窗口传递给弹出窗口的参数集
        	var _param = bDialog.getDialogParams(_dlg);
        	//获得父窗口设置的回调函数
        	//var _callback = bDialog.getDialogCallback(_dlg);
        	
        	$('#btn_code_add_gdsList').unbind('click').click(function(){
        		
        		var _idsArr=new Array();
        		$("input[name='checkboxgds']:checkbox").each(function(){ 
        			if($(this).attr("checked")){
        				_idsArr.push($(this).val());
        			}
        		});
        		if(_idsArr && _idsArr.length>=1){
        			
        			var parm=new Object();
        			parm._if_query="1";
        			parm.gdsIds=_idsArr;
        			bDialog.closeCurrent(parm);
        			
        		}else if(!_idsArr || _idsArr.length==0){
        			eDialog.alert('请至少选择一个商品进行操作！');
        		}
        	});
        	
        	//checkbox商品选择事件
			$("#gdsTable thead tr input[ id='dt_row_all_check']").live('click',function(e) {
				//选中 表示全选
				if (e.currentTarget.checked) {
					$("#gdsTable tbody tr td input[ name='checkboxgds']").prop(
							'checked', true);
				} else {
					//全部取消
					$("#gdsTable tbody tr td input[ name='checkboxgds']").prop(
							'checked', false);
				}
			});
        	
        	//重置
        	$('#btnFormReset').click(function(){
        		//ebcForm.resetForm('#searchGdsForm');
        		$('#gdsName').val('');
        		$('#gdsStatus').val('11');
        		$('#platCatgs').val('');
        		$('#mainCatgsName').val('');
        		
        	});
        	//关闭
        	$('#btnReturn').click(function(){
        		bDialog.closeCurrent();
        	});
        	//查询
    		$('#gdsQueryBtn').unbind("click");
			//绑定提交按钮事件
			$('#gdsQueryBtn').click(function() {
				var gdsStatus = $('#gdsStatus').val();
				var gdsName = $('#gdsName').val();
				var shopId=$('#shopId').val();
				var platCatgs=$('#platCatgs').val();
				var siteId=$('#siteId').val();
				$('#goodsListDiv').load(GLOBAL.WEBROOT + '/seller/coupgds/gdsgrid',{"gdsStatus":gdsStatus,"gdsName":gdsName,"shopId":shopId,"platCatgs":platCatgs,"siteId":siteId});
			});
			//加载初始化
			$('#gdsQueryBtn').click();

        };

        return {
            init : init
        };
    };
    pageConfig.config({
        //指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
        plugin : ['bForm'],
        //指定页面
        init : function(){
            var p = new pageInit();
            p.init();
        }
    });

});