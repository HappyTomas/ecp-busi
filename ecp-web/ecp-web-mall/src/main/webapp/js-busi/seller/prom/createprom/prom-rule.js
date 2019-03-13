$(function() {

	//页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			//客户组 事件
			$('#custGroup').click(
					function() {
						if ($("#custGroup").prop("checked")) {
							//客户组 勾选  组数据可以选
							$("[name = 'promRuleVO.custGroupValue']:checkbox")
									.attr("disabled", false);
						} else {
							//客户组 关闭  组数据清空并不能选择
							$("[name = 'promRuleVO.custGroupValue']:checkbox")
									.removeAttr("checked");
							$("[name = 'promRuleVO.custGroupValue']:checkbox")
									.attr("disabled", true);
						}
					});
			//客户组 --全部事件
			$('#custGroupAll').click(
					function() {
						if ($(this).prop("checked")) {
							$("[name = 'promRuleVO.custGroupValue']:checkbox")
									.attr("checked", "true");
						} else {
							$("[name = 'promRuleVO.custGroupValue']:checkbox")
									.removeAttr("checked");
						}
					});
			//渠道来源 事件
			$('#channel').click(
					function() {
						if ($("#channel").prop("checked")) {
							//渠道来源 勾选  组数据可以选
							$("[name = 'promRuleVO.channelValue']:checkbox")
									.attr("disabled", false);
						} else {
							//渠道来源 关闭  组数据清空并不能选择
							$("[name = 'promRuleVO.channelValue']:checkbox")
									.removeAttr("checked");
							$("[name = 'promRuleVO.channelValue']:checkbox")
									.attr("disabled", true);
						}
					});
			//渠道来源 --全部事件
			$('#channelAll').click(
					function() {
						if ($(this).prop("checked")) {
							$("[name = 'promRuleVO.channelValue']:checkbox")
									.attr("checked", "true");
						} else {
							$("[name = 'promRuleVO.channelValue']:checkbox")
									.removeAttr("checked");
						}
					});
			//客户等级 --全部事件
			$('#custLevelAll').click(
					function() {
						if ($(this).prop("checked")) {
							$("[name = 'promRuleVO.custLevelValue']:checkbox")
									.attr("checked", "true");
						} else {
							$("[name = 'promRuleVO.custLevelValue']:checkbox")
									.removeAttr("checked");
						}
					});

			//区域 --全部事件
			$('#areaAll').click(
					function() {
						if ($(this).prop("checked")) {
							$("[name = 'promRuleVO.areaValue']:checkbox").attr(
									"checked", "true");
						} else {
							$("[name = 'promRuleVO.areaValue']:checkbox")
									.removeAttr("checked");
						}
					});

			//客户级别 事件
			$('#custLevel').click(
					function() {
						if ($("#custLevel").prop("checked")) {
							//客户级别 勾选  组数据可以选
							$("[name = 'promRuleVO.custLevelValue']:checkbox")
									.attr("disabled", false);
						} else {
							//客户级别 关闭  组数据清空并不能选择
							$("[name = 'promRuleVO.custLevelValue']:checkbox")
									.removeAttr("checked");
							$("[name = 'promRuleVO.custLevelValue']:checkbox")
									.attr("disabled", true);
						}
					});
			//区域 事件
			$('#area').click(
					function() {
						if ($("#area").prop("checked")) {
							//区域勾选  组数据可以选
							$("[name = 'promRuleVO.areaValue']:checkbox").attr(
									"disabled", false);
							$("#areaExclude").attr("disabled", false);

						} else {
							//区域 关闭  组数据清空并不能选择
							$("[name = 'promRuleVO.areaValue']:checkbox")
									.removeAttr("checked");
							$("[name = 'promRuleVO.areaValue']:checkbox").attr(
									"disabled", true);
							$("#areaExclude").attr("disabled", true);
						}
					});
			//购买次数限制事件
			$('.limitTimesType').click(function() {
				//0 无限制
				if ($(this).val() != '0') {
					//次数必填( 使用input class = required 不能渲染)
					$('#limitTimesTypeValueLabel').text("*");
					$('.limitTimesTypeValueClass').show();
				} else {
					$('#limitTimesTypeValueLabel').text("");
					$('.limitTimesTypeValueClass').hide();
					$('#limitTimesTypeValue').val('');
					$("label[for='limitTimesTypeValue']").hide();

				}
			});
			//购买总量限制事件
			$('.limitTotalType').click(function() {
				//0 无限制
				if ($(this).val() != '0') {
					//次数必填( 使用input class = required 不能渲染)
					$('#limitTotalTypeValueLabel').text("*");
					$('.limitTotalTypeValueClass').show();
				} else {
					$('#limitTotalTypeValueLabel').text("");
					$('.limitTotalTypeValueClass').hide();
					$('#limitTotalTypeValue').val('');
					$("label[for='limitTotalTypeValue']").hide();
				}
			});
			//新增初始化
			if ($("#doAction").val() == '') {
				/*
				if($("#custGroup") && $("#custGroup").length>0){
				  $('#custGroup').click();
				}
				
				if($("#custGroupAll") && $("#custGroupAll").length>0){
				   $('#custGroupAll').click();
				 }
				
				if($("#channel") && $("#channel").length>0){
				   $('#channel').click();
				 }
				
				if($("#channelAll") && $("#channelAll").length>0){
				   $('#channelAll').click();
				 }
				if($("#custLevel") && $("#custLevel").length>0){
				   $('#custLevel').click();
				 }
				if($("#custLevelAll") && $("#custLevelAll").length>0){
				   $('#custLevelAll').click();
				 }
				if($("#area") && $("#area").length>0){
				   $('#area').click();
				 }
				if($("#areaAll") && $("#areaAll").length>0){
				   $('#areaAll').click();
				 }*/
				$("input[name='promRuleVO.limitTimesType'][value=0]").attr(
						"checked", 'checked');
				$("input[name='promRuleVO.limitTotalType'][value=0]").attr(
						"checked", 'checked');
				$('#minBuyCnt').val('1');
			}

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