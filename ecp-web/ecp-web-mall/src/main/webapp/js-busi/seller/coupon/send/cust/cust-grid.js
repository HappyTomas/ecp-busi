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
			
				$('#btn_code_modify').unbind('click').click(function(){
        		
        		var _idsArr=new Array();
        		$("input[name='checkboxcust']:checkbox").each(function(){ 
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
        			eDialog.alert('请至少选择一个客户进行操作！');
        		}
        	});
			
			//checkbox商品选择事件
			$("#custTable thead tr td input[ id='dt_row_all_check']").live('click',function(e) {
				//选中 表示全选
				if (e.currentTarget.checked) {
					$("#custTable tbody tr td input[ name='checkboxcust']").prop(
							'checked', true);
				} else {
					//全部取消
					$("#custTable tbody tr td input[ name='checkboxcust']").prop(
							'checked', false);
				}
			});
	        	
			//关闭
        	$('#btnReturn').click(function(){
        		bDialog.closeCurrent();
        	});
				
			$('#btnFormReset').click(function(){
				ebcForm.resetForm('#searchForm');
				/*$('#staffCode').val('');
        		$('#serialNumber').val('');*/
			});

			$('#btnFormSearch').unbind("click");
			//绑定提交按钮事件
			$('#btnFormSearch').click(function() {
				
						var staffCode = $('#staffCode').val();
						var shopId = $('#shopId').val();
						var serialNumber = $('#serialNumber').val();
						
						$('#custListDiv').load(GLOBAL.WEBROOT + '/seller/cust/gridlist', 
								{
							     "staffCode":staffCode,
							     "shopId":shopId,
							     
							     "serialNumber":serialNumber		
								});
					});
		
			//初始化加载load数据
			$('#btnFormSearch').click();
		};

		return {
			init : init
		};
	};
	pageConfig.config({
		// 指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm'],
		// 指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});
});

