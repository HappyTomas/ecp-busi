/**
 * 
 * 楼层导航属性编辑界面
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
		$(pre).before($('div.addFloorRow',$(extend)));
		
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
		
		var form = $('#paramsSetForm');
		//设置模板块，以供动态添加进行复制
		$('input.floorName',$(form)).closest('div.row-fluid').addClass('floorNameTemplate');
		//动态生成表单部分
		$('#btnAddFloor',$(form)).click(function(){
			var mainbox = $('div.dynamicFormMainBox');
			var nameBox = $('div.floorNameTemplate',$(mainbox)).clone(true);
			cmsTemplate.cleanRowContent(nameBox);
			$('div.addFloorRow',$(mainbox)).before($(nameBox).removeClass('floorNameTemplate'));
			$('input.floorName',$(nameBox)).val('');
		});
		
		//点击关联楼层
		$('div.dynamicFormMainBox').delegate('a.releatLayer', 'click', function(e) {
			var mainRow = $(this).closest('div.formItem');
			var groupId = $('#propGroupId',$(mainRow));
			var floorBox = $('div.floor-config-box');
			$(floorBox).data('target',groupId);
			//清除所有选项的选中状态
			$(':radio',$(floorBox)).prop('checked', false);
			var floorId = $(groupId).val();
			//设置关联项目选中
			if(floorId) $(':radio[value="'+floorId+'"]',$(floorBox)).prop('checked',true);
			$(floorBox).show();
		});
		
		//关闭当前行
		$('div.dynamicFormMainBox').delegate('i.closeRow', 'click', function(e) {
			var mainRow = $(this).closest('div.row-fluid');
			if(!$(mainRow).hasClass('floorNameTemplate')) $(mainRow).remove();
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
					var manualList = cmsTemplate.dynamicPropValueSet(data);
					console.log(data);
					//处理手动生成的列表
					if(manualList && $.isArray(manualList) && manualList.length > 0){
						//获得模板
						var template = $('div.floorNameTemplate',$(form)).addClass('floorNameRow');
						var addFloorRow = $('div.addFloorRow',$(form));
						if(manualList.length >= 1){
							//动态创建楼层名称行，需要排除模板行本身
							var needCreate = manualList.length - 1;//
							if(needCreate>0){
								console.log(needCreate);
								for (var i = 0; i < needCreate; i++) {
									var nameBox = $(template).clone(true);
									$(addFloorRow).before($(nameBox).removeClass('floorNameTemplate'));
								}
							}
							//数据填充
							$('div.floorNameRow',$(form)).each(function(i, row) {
								var rowData = manualList[i];
								cmsTemplate.setBaseInfo(row,rowData);
								$('input.floorName',$(row)).val(rowData.propValue);
							});
						}
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
		if(itemsData && $.isArray(itemsData) && itemsData.length > 0 && url){
			var submitData = {'forms' : itemsData};
			$.eAjax({
				url : url,
				data : ebcUtils.serializeObject(submitData),
				success : function(returnInfo){
					if(returnInfo && $.type(returnInfo.resultFlag)!='undefined' && returnInfo.resultFlag == 'ok'){
						eDialog.alert('楼层导航设置成功！',function(){
							bDialog.closeCurrent();
						},'confirmation');
					}
				}
			});
		}
	});
	
	//弹出现有楼层
	$('#btnFloorConfigOk').click(function(e) {
		var main = $('div.floor-config-box');
		var check = $(':radio:checked',$(main));
		if($(check).size() == 0){
			eDialog.alert('请选择一个楼层！',$.noop,'error');
		}else{
			var target = $(main).data('target');
			if(target) $(target).val($(check).val());
			$(main).hide();
		}
	});
	
	//关闭楼层展示
	$('#btnFloorConfigCancel').click(function(){
		$('div.floor-config-box').hide();
	});
	
	//将数字数组转化为百分比
    function numToTrueWidth(obj,chidClz ,blank, trueWidth) {
        $(obj).each(function(){
            var $this=$(this);
            if (!blank) {
                blank = 0;
            }
            /*  if (layoutDesign.isEmptyList(numList) || numList.length == 1 || !trueWidth) {
             return [trueWidth];
             }*/
            var numList=$(this).data('widths');
            numList=$.makeArray(numList);
            var blankPersent = blank * 100 * (numList.length - 1 ) / trueWidth;
            var total = 0;
            var error = false;
            $.each(numList, function (i, val) {
                if (!isNaN(val)) {
                    numList[i] = Number(val);
                    total += numList[i];
                } else {
                      eDialog.alert("未得到期望的数字：i=" + i + ",val=" + val + "!");
                    error = true;
                    return;
                }
            });
            if (error) {
                return false;
            }
            var persentList = [];
            var persentSum = 0;
            $.each(numList, function (i, val) {
                if (total == 0) {
                    persentList[i] = 0;
                } else {
                    if (i != numList.length - 1) {
                        persentList[i] = Math.round((((val * 100) / total) * (100 - blankPersent) / 10000) * trueWidth);
                        persentSum += persentList[i]-2;
                    } else {
                        persentList[i] = trueWidth - persentSum - (blank * (numList.length - 1 ))-2;
                    }
                }
            });
            if(persentList.length!=$(chidClz,$this).size()){
                eDialog.alert("请将格式配置正确!");
                return false;
            }
            $.each(persentList, function (k,v) {
                $(chidClz,$this).eq(k).width(v);
            })
        });
    }
    numToTrueWidth('.floor-laylout','.item' ,10, $('.config-box').width());
});