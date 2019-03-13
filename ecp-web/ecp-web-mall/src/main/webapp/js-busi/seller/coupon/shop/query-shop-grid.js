$(function() {

	// 页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			//获得当前弹出窗口对象
        	var _dlg = bDialog.getDialog();
        	//获得父窗口传递给弹出窗口的参数集
        	var _param = bDialog.getDialogParams(_dlg);
        	//获得父窗口设置的回调函数
        	//var _callback = bDialog.getDialogCallback(_dlg);
			
			//确定添加
			$('#btn_code_add').unbind('click').click(function(){
    		
    		var _idsArr=new Array();
    		$("input[name='checkboxshop']:checkbox").each(function(){ 
    			if($(this).attr("checked")){
    				_idsArr.push($(this).val());
    			}
    		});
    		if(_idsArr && _idsArr.length>=1){
    			
    			var parm=new Object();
    			parm.shopIds=_idsArr;
    			bDialog.closeCurrent(parm);
    			
    		}else if(!_idsArr || _idsArr.length==0){
    			eDialog.alert('请至少选择一家店铺进行操作！');
    		}
    	});
			//checkbox商品选择事件
			$("#shopTable thead tr input[ id='dt_row_all_check']").live('click',function(e) {
				//选中 表示全选
				if (e.currentTarget.checked) {
					$("#shopTable tbody tr td input[ name='checkboxshop']").prop(
							'checked', true);
				} else {
					//全部取消
					$("#shopTable tbody tr td input[ name='checkboxshop']").prop(
							'checked', false);
				}
			});
			//关闭
        	$('#btnReturn').click(function(){
        		bDialog.closeCurrent();
        	});
			
			//重置
			$('#btnFormReset').click(function() {
				ebcForm.resetForm('#myShopForm');
				//$("#shopId").val($('#oldId').val());
			});

			$('#shopQueryBtn').unbind("click");
			//绑定提交按钮事件
			$('#shopQueryBtn').click(function() {
						var shopId = $('#shopId').val();
						$('#shopListDiv').load(GLOBAL.WEBROOT + '/seller/promshop/shoplist', 
								{
							     "shopId":shopId,
								});
					});
		
			
			//初始化加载load数据
			$('#shopQueryBtn').click();
		
		};

		return {
			init : init
		};
	};
	pageConfig.config({
		// 指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm' ],
		//指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});

});
