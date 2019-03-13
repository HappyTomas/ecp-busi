$(function() {

	// 页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			// 获得当前弹出窗口对象
			var _dlg = bDialog.getDialog();
			// 获得父窗口传递给弹出窗口的参数集
			var _param = bDialog.getDialogParams(_dlg);
			// 获得父窗口设置的回调函数
			// var _callback = bDialog.getDialogCallback(_dlg);
			
			
			//重置
			$('#btnFormReset').click(function() {
				ebcForm.resetForm('#searchForm');
				$("#shopId").val($('#oldId').val())
			});
			//确定添加
			$('#btn_code_add').click(function() {
        		
        		var _idsArr=new Array();
        		$("#giftsTable tbody tr td input[name='checkboxgifts']").each(function(){ 
        			if($(this).attr("checked")){
        					_idsArr.push($(this).val());
        			}
        		});
        		if(_idsArr && _idsArr.length>=1){
        			var parm=new Object();
        			parm._if_query="1";
        			parm.skuIds=_idsArr;
        			bDialog.closeCurrent(parm);
        			
        		}else if(!_idsArr || _idsArr.length==0){
        			eDialog.alert('请至少选择一个商品进行操作！');
        		}
        	});

			//关闭
			$('#btnReturn').click(function() {
				bDialog.closeCurrent();
			});


			//checkbox商品选择事件
			$("#giftsTable thead tr input[ id='dt_row_all_check']").live('click',function(e) {
				//选中 表示全选
				if (e.currentTarget.checked) {
					$("#giftsTable tbody tr td input[ name='checkboxgifts']").prop(
							'checked', true);
				} else {
					//全部取消
					$("#giftsTable tbody tr td input[ name='checkboxgifts']").prop(
							'checked', false);
				}
			});
			
			$('#giftQueryBtn').unbind("click");
			//绑定提交按钮事件
			$('#giftQueryBtn').click(
					function() {
						var shopId = $('#shopId').val();
						var giftName = $('#giftName').val();
						$('#giftListDiv').load(GLOBAL.WEBROOT + '/seller/giftprom/giftselect', {"shopId" : shopId,"giftName" : giftName});
					});
			//初始化
			$('#giftQueryBtn').click();
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