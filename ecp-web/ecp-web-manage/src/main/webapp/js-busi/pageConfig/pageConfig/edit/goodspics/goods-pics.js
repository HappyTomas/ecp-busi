/**
 * 
 * 宝贝图片编辑界面
 * 
 * @author Terry
 * 
 */
$(function(){
	//是否初始化完成开关
	var init = false;
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	
	$('#btnCancel').click(function(){
		bDialog.closeCurrent();
	});
	
	//初始化表单内容（在动态内容加载之后进行）
	var formItemInit = function(){
		var main = $('div.dynamicFormMainBox');
		//图片预览区域，任何要在表单中新增加的内容都要在它之前添加
		var pre = $('div.imgPreviewArea',$(main));
		var extend = $('div.formExtendArea');
		$(pre).before($('div.addGoodsRow',$(extend)));
		
		var buildItemBox = $('div.buildItems',$(extend));
		var buildItems = $('div.buildItem',$(buildItemBox));
		if($(buildItems).size() > 0){
			//手动生成项目区域KEY前缀
			var keyPrefix = 'manualSet_';
			$(buildItems).each(function(i, ele) {
				var key = keyPrefix + i.toString();
				$('#' + key).after($(ele).html());
			});
		}
		//设置两个下拉列表样式
		$('select:first',$(main)).addClass('goodsCount');
		$('select:last',$(main)).addClass('goodsConfigType');
		//设置模板块，以供动态添加进行复制
		$('input.goodsItem',$(main)).closest('div.row-fluid').addClass('goodsItemTemplate').addClass('goodsDataRow');
		//动态生成表单部分
		$('#btnAddGoods',$(main)).click(function(){
			var count = parseInt($('select.goodsCount',$(main)).val());
			if($('div.goodsDataRow',$(main)).size() < count){
				var nameBox = $('div.goodsItemTemplate',$(main)).clone(true);
				$('div.addGoodsRow',$(main)).before($(nameBox).removeClass('goodsItemTemplate'));
				$('input.goodsItem',$(nameBox)).val('');
				cmsTemplate.cleanRowContent(nameBox);
			}
		});
		//设置宝贝数据最大数量
		$('select.goodsCount',$(main)).change(function(){
			var goods = $('div.goodsDataRow',$(main));
			var max = parseInt($(this).val());
			if($(goods).size() > max){
				$(goods).not('div.goodsItemTemplate').remove();
				for (var i = 0; i < (max-1); i++) {
					var nameBox = $('div.goodsItemTemplate',$(main)).clone(true);
					$('div.addGoodsRow',$(main)).before($(nameBox).removeClass('goodsItemTemplate'));
				}
			}
		});
		//设置宝贝配置方式 onChange 方法
		$('div.controls').delegate('select.goodsConfigType', 'change', function(i,e) {
			if(this.value==1){
//				alert("手动配置");
				$('#btnAddGoods').show();
				var mainRow = $('span.rowSelectButton').closest('div.row-fluid');
				$('#propValue',$(mainRow)).val('');
				$('#remark',$(mainRow)).val('');
				$('input.goodsItem',$(mainRow)).val('');
			}else if(this.value==2){
//				alert("获取楼层");
				$('#btnAddGoods').hide();
				var mainRow = $('span.rowSelectButton').closest('div.row-fluid');
				$('#propValue',$(mainRow)).val('');
				$('#remark',$(mainRow)).val('');
				$('input.goodsItem',$(mainRow)).val('');
				var goods = $('div.goodsDataRow',$(main));
				var max = 1;
				if($(goods).size() > max){
					$(goods).not('div.goodsItemTemplate').remove();
					for (var i = 0; i < (max-1); i++) {
						var nameBox = $('div.goodsItemTemplate',$(main)).clone(true);
						$('div.addGoodsRow',$(main)).before($(nameBox).removeClass('goodsItemTemplate'));
					}
				}
			}
		});
		
		
		
		$()
		
		//一行显示多少个宝贝项目设置
		$('ul.goodsRowShowNum li',$(main)).click(function(e) {
			var mainRow = $(this).closest('div.row-fluid');
			$('ul.goodsRowShowNum li',$(main)).removeClass('selected');
			$(this).addClass('selected');
			$('#propValue',$(mainRow)).val($(this).data('result'));
		});
		
		//移除宝贝
		$('div.dynamicFormMainBox').delegate('i.closeRow', 'click', function(e) {
			var row = $(this).closest('div.row-fluid');
			if(!$(row).hasClass('goodsItemTemplate')) $(row).remove();
		});

		//选择商品/楼层
		$('div.dynamicFormMainBox').delegate('span.rowSelectButton', 'click', function(e) {
			var mainbox = $('div.dynamicFormMainBox');
			var mainRow = $(this).closest('div.row-fluid');
			//获得配置方式(1：手工配置2：获取楼层)
			var configType = $('select.goodsConfigType',$(mainbox)).val();
			switch (configType) {
			case '1':
				var siteId = '1';//站点
				var title = "选择商品";
				var	url = "floorgds/opengds?siteId="+siteId;
				bDialog.open({
					title : title,
					width : 860,
					height : 600,
					url : $webroot + url,
					params : {},
					callback:function(data){
						if(data && data.results && data.results[0]){
							$('#propValue',$(mainRow)).val(data.results[0].gdsIds);
							$('#remark',$(mainRow)).val(data.results[0].gdsNames);
							$('input.goodsItem',$(mainRow)).val(data.results[0].gdsNames);
						}
					}
				});
				break;
			case '2':
				bDialog.open({
					title : '楼层选择',
					width : 860,
					height : 600,
					url : $webroot + 'modular-load/floorLayerSelecter',
					params : {},
					callback:function(data){
						if(data && data.results && data.results[0]){
							$('#propValue',$(mainRow)).val(data.results[0].floorId);
							$('#remark',$(mainRow)).val(data.results[0].floorName);
							$('input.goodsItem',$(mainRow)).val(data.results[0].floorName);
						}
					}
				});
				break;
			default:
				break;
			}
		});
	};
	
	var params = {
		modularId : $('#publicModularId').val(),
		itemId : $('#publicItemId').val(),
		pageId : $('#publicPageId').val()
	};
	//读取动态表单项目放入页面
	$.eAjax({
		url : $webroot + "paramsrender/init",
		data : params,
		dataType : 'text',
		success : function(returnInfo){
			if(returnInfo){
				//将动态表单内容放入表单中
				$('#paramsSetForm').html(returnInfo);
				
				//处理扩展表单内容
				formItemInit();
				
				//获得布局项属性设置数据后的回调处理（将数据回填到表单中）
				var callback = function(data){
					var form = $('form.templateItemForms');
					//自动生成的字段，进行内容回填，并返回手动生成的字段配置内容
					var manualList = cmsTemplate.dynamicPropValueSet(data);
					
					/**
					 * 定义自定义属性回填事件
					 * 完成格式：[{'key_pageId_itemId_propId':function(rowdata){}},{...}]
					 * pageId、itemId、propId根据具体数值进行替换
					 */
					var defineDataRender = function(){
						var render = [];
						var pageId = $('#modularPageId',$(form)).val();
						var itemId = $('#modularItemId',$(form)).val();
						//宝贝排列模式数据回填处理
						var showTypeRow = $('ul.goodsRowShowNum',$(form)).closest('div.formItem');
						var showTypeKey = 'key_'+pageId+'_'+itemId+'_'+$('#propId',$(showTypeRow)).val();
						var showTypeFunction = function(row,rowdata){
							console.log(rowdata);
							cmsTemplate.setBaseInfo(row,rowdata);							
							
							if(row && rowdata && $.isPlainObject(rowdata)){
								$('ul.goodsRowShowNum li',$(row)).each(function(i, ele) {
									if($(ele).data('result') == rowdata.propValue) {
										$(ele).addClass('selected');
										return false;
									}
								});
							}
						};
						render.push({key:showTypeKey,func:showTypeFunction});
						//宝贝数据回填处理
						var goodsDataRow = $('div.goodsItemTemplate',$(form));
						var goodsDataKey = 'key_'+pageId+'_'+itemId+'_'+$('#propId',$(goodsDataRow)).val();
						var goodsDataFunction = function(row,rowdata){
							var form = $('form.templateItemForms');
							var tmp = $('div.goodsItemTemplate',$(form));//获得模板行
							var filled = $('div.filled',$(form));
							var r = null;
							if($(filled).size() == 0){//没有已填充项，则将数据填充到模板行中
								r = tmp;
							}else{//若有至少一个已填充项，则需要动态生成一个宝贝数据行
								r = $(tmp).clone(true);
								$('div.addGoodsRow',$('form.templateItemForms')).before($(r).removeClass('goodsItemTemplate'));
							}
							cmsTemplate.setBaseInfo(r,rowdata);
							$('input.goodsItem',$(r)).val(rowdata.remark);
							$(r).addClass('filled');
						};
						render.push({key:goodsDataKey,func:goodsDataFunction});
						return render;
					};
					var mainbox = $('div.dynamicFormMainBox');
					//获得配置方式(1：手工配置2：获取楼层)
					var configType = $('select.goodsConfigType',$(mainbox)).val();
					if(configType==2){
						$('#btnAddGoods').hide();
					}
					//处理手动生成的列表
					if(manualList && $.isArray(manualList) && manualList.length > 0){
						console.log(manualList);
						var renders = defineDataRender();
						$.each(manualList, function(i, elt) {
							var keystr = 'key_'+elt.pageId+'_'+elt.itemId+'_'+elt.propId;
							console.log(keystr);
							var key = $('#'+keystr,$(form));
							var row = null,func = null;
							if($(key).size()>0) row = $(key).closest('div.formItem');
							$.each(renders, function(i, ren) {
								if(keystr == ren.key){
									func = ren.func;
									return false;
								}
							});
							
							if(func && $.isFunction(func)) func(row,elt);
						});
					}
					//设置开关初始化完成
					init = true;
				};
				//读取布局项属性设置数据
				cmsTemplate.loadFormSet(callback);
			}
		}
	});
	
	//保存表单
	$('#btnSave').click(function(){
		//重新排列排序号
		cmsTemplate.resetSortNo();
		cmsTemplate.formDataFill();//处理表单数据，将输入控件的内容填入隐藏域
		var itemsData = cmsTemplate.getFormData();
		var url = $('form.templateItemForms').data('url');
		if(itemsData[4].propValue.length==0){
			$("#error-msg-goods-pics").text("请选择宝贝数据").show();
			return;
		}else{
			$("#error-msg-goods-pics").hide();
		}
		if(itemsData && $.isArray(itemsData) && itemsData.length > 0 && url){
			var submitData = {'forms' : itemsData};
			$.eAjax({
				url : url,
				data : ebcUtils.serializeObject(submitData),
				success : function(returnInfo){
					if(returnInfo && $.type(returnInfo.resultFlag)!='undefined' && returnInfo.resultFlag == 'ok'){
						eDialog.alert('宝贝图片内容设置成功！',function(){
							//bDialog.closeCurrent();
							parent.location.reload(); 
						},'confirmation');
					}
				}
			});
		}
	});
});