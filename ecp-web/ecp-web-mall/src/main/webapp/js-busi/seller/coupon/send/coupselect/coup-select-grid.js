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
        	
        	$('#btn_code_add_coup').unbind('click').click(function(){
        		
        		var _idsArr=new Array();
        		$("input[name='checkboxcoup']:checkbox").each(function(){ 
        			if($(this).attr("checked")){
        				_idsArr.push($(this).val());
        			}
        		});
        		if(_idsArr && _idsArr.length>=1){
        			
        			var parm=new Object();
        			parm._if_query="1";
        			parm.rows=_idsArr;
        			bDialog.closeCurrent(parm);
        			
        		}else if(!_idsArr || _idsArr.length==0){
        			eDialog.alert('请至少选择一个优惠券进行操作！');
        		}
        	});
        	
        	//checkbox商品选择事件
			$("#coupTable thead tr td input[ id='dt_row_all_check']").live('click',function(e) {
				//选中 表示全选
				if (e.currentTarget.checked) {
					$("#coupTable tbody tr td input[ name='checkboxcoup']").prop(
							'checked', true);
				} else {
					//全部取消
					$("#coupTable tbody tr td input[ name='checkboxcoup']").prop(
							'checked', false);
				}
			});
        	
        	//重置
        	$('#btnFormReset').click(function(){
        		ebcForm.resetForm('#searchForm');
        		/*$('#coupName').val('');
        		$('#shopId').val('');
        		$('#coupTypeId').val('');*/
        		
        	});
        	//关闭
        	$('#btnReturn').click(function(){
        		bDialog.closeCurrent();
        	});
        	//查询
    		$('#btnFormSearch').unbind("click");
			//绑定提交按钮事件
			$('#btnFormSearch').click(function() {
				var shopId = $('#shopId').val();
				var coupName = $('#coupName').val();
				var coupTypeId=$('#coupTypeId').val();
				var status=$('#status').val();
				$('#coupListDiv').load(GLOBAL.WEBROOT + '/seller/coupinfo/coupListgrid',{"shopId":shopId,"coupName":coupName,"coupTypeId":coupTypeId,"status":status});
			});
			//加载初始化
			$('#btnFormSearch').click();

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