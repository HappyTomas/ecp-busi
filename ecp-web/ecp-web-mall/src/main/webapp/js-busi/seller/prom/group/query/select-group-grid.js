$(function() {

	//页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			//获得当前弹出窗口对象
			var _dlg = bDialog.getDialog();
			//获得父窗口传递给弹出窗口的参数集
			var _param = bDialog.getDialogParams(_dlg);
			//获得父窗口设置的回调函数
			//var _callback = bDialog.getDialogCallback(_dlg);

			//重置
			$('#btnFormReset').click(function() {
				ebcForm.resetForm('#searchForm');
			});
			//确定添加
			$('#btn_code_add').click(function() {
				var promtheme = $("input[type=radio]:checked").val();
				var ids = $("input[type=radio]:checked").attr("id");
				if (!ids || ids.length == 0) {
					eDialog.alert('请至少选择一个主题进行操作！');
				} else {
					var parm = new Object();
					parm.id = ids;
					parm.pormTheme = promtheme;
					bDialog.closeCurrent(parm);
				}
			});

			//关闭
			$('#btnReturn').click(function() {
				bDialog.closeCurrent();
			});
			$('#groupQueryBtn').unbind("click");
			//绑定提交按钮事件
			$('#groupQueryBtn').click(
					function() {
						var promTheme = $('input[name="promTheme"]').val();
						var showStartTime = $('input[name="showStartTime"]')
								.val();
						var showEndTime = $('input[name="showEndTime"]').val();
						$('#groupListDiv').load(
								GLOBAL.WEBROOT + '/seller/querygroup/grouplist', {"showStartTime" : showStartTime,"showEndTime" : showEndTime,"promTheme" : promTheme});
					});
			//初始化加载
			$('#groupQueryBtn').click();
		};

		return {
			init : init
		};
	};
	pageConfig.config({
		//指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm' ],
		//指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});
});