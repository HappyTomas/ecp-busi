/**
 * CMS模板化公共脚本
 * 
 * 
 * @author Terry
 * 
 */
var cmsTemplate = {
	/**
	 * 获得动态表单数据，并返回表单内容的JSON对象
	 */
	getFormData : function(){
		var form = $('form.templateItemForms');
		var formItems = $('div.formItem',$(form));
		var pageId = $('#modularPageId',$(form)).val();
		var itemId = $('#modularItemId',$(form)).val();
		var result = [];
		if(formItems && $(formItems).size() > 0){
			$.each(formItems, function(i, row) {
				var item = {};
				item.id = $('#propItemId',$(row)).val();//预览布局项属性关系表ID
				item.propId = $('#propId',$(row)).val();//输入项ID
				item.propName = $('#propName',$(row)).val();
				item.ifHaveto = $('#ifHaveto',$(row)).val();
				item.propValueType = $('#propValueType',$(row)).val();
				item.controlPropId = $('#controlPropId',$(row)).val();
				item.propGroupId = $('#propGroupId',$(row)).val();
				item.sortNo = $('#sortNo',$(row)).val();
				item.propValue = $('#propValue',$(row)).val();
				item.propValueId = $('#propValueId',$(row)).val();
				item.isAutobuild = $('#isAutobuild',$(row)).val();
				item.remark = $('#remark',$(row)).val();
				item.pageId = pageId;
				item.itemId = itemId;
				result.push(item);
			});
		}
		return result;
	},
	/**
	 * 清空行内部分内容数据（用于动态添加表单内容）
	 * @param r
	 */
	cleanRowContent : function(r){
		$('#propItemId',$(r)).val('');
		$('#sortNo',$(r)).val('');
		$('#propValue',$(r)).val('');
		$('#propValueId',$(r)).val('');
	},
	/**
	 * 将表单中的内容填写到隐藏域中主要处理propValue、propValueId字段
	 */
	formDataFill : function(){
		var form = $('form.templateItemForms');
		var formItems = $('div.formItem',$(form));
		if(formItems && $(formItems).size() > 0){
			$.each(formItems, function(i, row) {
				var type = $('#propValueType',$(row)).val();
				switch (type) {
				case '03'://文本输入框
					var value = $('.dynamicFormItem',$(row)).val();
					$('#propValue',$(row)).val(value);
					break;
				case '04'://下拉框
					var value = $('.dynamicFormItem',$(row)).val();
					$('#propValueId',$(row)).val(value);
					break;
				default:
					break;
				}
			});
		}
	},
	/**
	 * 读取布局项属性设置数据
	 * @param callback 获得配置数据并回填的方法
	 */
	loadFormSet : function(callback){
		var form = $('form.templateItemForms');
		var pageId = $('#modularPageId',$(form)).val();
		var itemId = $('#modularItemId',$(form)).val();
		var params = {
			'pageId' : pageId,
			'itemId' : itemId
		};
		$.eAjax({
			url : $webroot + 'modular-dynamic/publicFormLoad',
			data : params,
			success : function(returnInfo){
				if(returnInfo && callback && $.isFunction(callback)) callback(returnInfo);
			}
		});
	},
	/**
	 * 设置表单内容
	 * @param row 行对象
	 * @param elt 行源数据
	 */
	setBaseInfo : function(row,elt){
		$('#propItemId',$(row)).val(elt.id);//预览布局项属性关系表ID
		$('#propId',$(row)).val(elt.propId);//输入项ID
		$('#propName',$(row)).val(elt.propName);
		$('#ifHaveto',$(row)).val(elt.ifHaveto);
		$('#propValueType',$(row)).val(elt.propValueType);
		$('#controlPropId',$(row)).val(elt.controlPropId);
		$('#propGroupId',$(row)).val(elt.propGroupId);
		$('#sortNo',$(row)).val(elt.sortNo);
		$('#propValue',$(row)).val(elt.propValue);
		$('#propValueId',$(row)).val(elt.propValueId);
		$('#isAutobuild',$(row)).val(elt.isAutobuild);
		$('#remark',$(row)).val(elt.remark);
	},
	/**
	 * 设置自动生成的表单控件属性值
	 * 
	 * 若有手动生成字段，则返回源数据内容
	 * @param data
	 * 
	 * @return Array 手动生成的属性
	 */
	dynamicPropValueSet : function(data){
		//手动生成数据列表
		var manualList = new Array();
		if(data && $.type(data.props)!='undefined' && data.props.length > 0){
			var form = $('form.templateItemForms');
			$.each(data.props, function(i, elt) {
				if(elt.isAutobuild == '1'){//自动生成字段进行自动处理
					var key = $('#key_'+elt.pageId+'_'+elt.itemId+'_'+elt.propId,$(form));
					var row = null;
					if($(key).size()>0) row = $(key).closest('div.formItem');
					if(row && $(row).size() > 0){
						cmsTemplate.setBaseInfo(row,elt);
						switch (elt.propValueType) {
						case '03'://文本输入框
							$('.dynamicFormItem',$(row)).val(elt.propValue);
							break;
						case '04'://下拉框
							$('.dynamicFormItem',$(row)).val(elt.propValueId);
							break;
						case '05'://图片处理
							if(elt.propValue) $('span.imgUploadStatus',$(row)).removeClass('imgUnUpload').addClass('imgUploaded').text('已上传');
							//将图片放入预览框
							$('div.imagePreviewBox').append('<img src="'+cmsTemplate.loadImgUrlById(elt.propValue,150,150)+'">');
							break;
						default:
							break;
						}
					}
				}else if(elt.isAutobuild == '0') {//需要手动生成的部分
					manualList.push(elt);
				}
			});
		}
		return manualList;
	},
	//重新排列排序号
	resetSortNo : function(){
		var main = $('form.templateItemForms');
		var items = $('div.formItem',$(main));
		if(items && $(items).size()>0){
			$(items).each(function(i,row){
				$('#sortNo',$(row)).val(i+1);
			});
		}
	},
	/**
	 * 根据FileID获得文件URL
	 * @param fileId
	 */
	loadImgUrlById : function(fileId,width,height){		
		var url = '';
		if(!fileId) return '';
		//根据FileId读取图片
		$.eAjax({
			url : $webroot + "modular-dynamic/loadImgById",
			data : {fileId : fileId,
				 	width : width || "",
				 	height : height || ""
			},
			async : false,//同步执行
			success : function(returnInfo){
				url = returnInfo.imgUrl;
			}
		});
		return url;
	}
};