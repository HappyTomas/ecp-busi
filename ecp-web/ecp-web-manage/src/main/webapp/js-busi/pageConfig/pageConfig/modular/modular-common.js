/**
 * 页面编辑
 * @author gongxq
 * 
 * propValueType（属性类型常量）
 * 01--单选
 * 02--多选
 * 03--手动输入
 * 04--下拉框
 * 05--图片
 * 06--自定义
 */
$(function(){
	var main = $('div.dynamicFormMainBox');
	//点击编辑弹窗口后。如果是不是新增状态，是编辑状态（propPre有值）。则进行值回填
	var propPre = eval($("#propPreStr").val());
	if(propPre != null && propPre.length >= 1){
		var form = $('form.templateItemForms');
		var formItems = $('div.formItem',$(form));
		if(formItems && $(formItems).size() > 0){
			$.each(formItems, function(i, row) {
				var propGroupId=$('#propGroupId',$(row)).val();
				var controlPropId=$('#controlPropId',$(row)).val();
				for(var i = 0;i< propPre.length;i++){
					var propPreObj =  propPre[i];
					//通过属性的id来判断赋值
					var propGroupId=$('#propGroupId',$(row)).val();
					var controlPropId=$('#controlPropId',$(row)).val();
					var rowPropId=$('#propId',$(row)).val();
					var mapKey=propPreObj.propId;
					if(null!=propGroupId && 0!=propGroupId){
						rowPropId+='_'+propGroupId+'_'+controlPropId;
						mapKey+='_'+propPreObj.propGroupId+'_'+propPreObj.controlPropId;
					}
					if(rowPropId==mapKey){
						switch (propPreObj.propValueType) {
						case "01"://单选。对于那种单选不是checkbox的。会直接在页面初始化的时候直接赋值，不在js里面赋值。参照商品分类或者内容位置属性、展示方式属性的初始化赋值（）
							if(propPreObj.propValue=="1"){
								$('input[type="checkbox"]',$(row)).attr("checked",'ckecked');
							}
							break;
						case "02"://多选
							if($(".lable-ck",$(formItems)).length>=2){
								$.each($(".lable-ck",$(formItems)),function(j,multiObj){
									if(propPreObj.itemPropValuePreRespDTOList!=null && propPreObj.itemPropValuePreRespDTOList.length >=1){
										for(var j =0;j<propPreObj.itemPropValuePreRespDTOList.length;j++){
											var propObj = propPreObj.itemPropValuePreRespDTOList[j];
											if(propObj != null && $('#propValueId',$(multiObj)).val()==propObj.propValueId){
												$('#propValue',$(multiObj)).val(propObj.propValue);
												$('#propItemId',$(multiObj)).val(propObj.id);
												$("input[type='checkbox']",$(multiObj)).attr("checked","checked");
												$("input[type='radio']",$(multiObj)).attr("checked","checked");
											}
										}
									}
								})
							}
							break;
						case "03"://手动输入
							$('input[type="text"]',$(row)).val(propPreObj.propValue);
							break;
						case "04"://下拉框
							$('select',$(row)).val(propPreObj.propValue);
							break;
						case "05"://图片
							$('input[type="text"]',$(row)).val(propPreObj.propValue);
							$("img",$(row)).attr('src',cmsTemplate.loadImgUrlById(propPreObj.propValue,150,150));
							break;
						case "06"://自定义
							//目前暂时没有
							break;
						default://
							break;
						}
					}
				}
			});
		}
	}
	//wap app楼层模板下拉选择框
	var $app_template_floor=$('.app_template_floor');
	if($app_template_floor.length>0){
		var propValue=$('#propValue',$app_template_floor.parent()).val();
		$.eAjax({
			url : GLOBAL.WEBROOT+"/floortemplate/syncList",
			data : '',
			success : function(dataList){
				if(dataList.length>0){
					$app_template_floor.empty();
					for (var i = 0; i < dataList.length; i++) {
						var data=dataList[i];
						$app_template_floor.append("<option value='"+data.templateNo+"'>"+data.templateName+"</option>");  
						if(i==0){ //初始化
							var value_0=data.templateNo;
							if(propValue==null||propValue.length==0||propValue==""){
								$('#propValue',$app_template_floor.parent()).val(value_0);
							}
						}
					}
					var selectValue=$app_template_floor.parent().children("#propValue").val();
					if(null!=selectValue && ""!=selectValue && selectValue.length!=0){
						$app_template_floor.val(selectValue);
						CommonModular.includeAppTemplateFloorContent(selectValue);
					}else{
						var data=dataList[0];
						if(data){
							$app_template_floor.val(data.templateNo);
							CommonModular.includeAppTemplateFloorContent(data.templateNo);
						}
					}
				}
			}
		});
		$(".app_template_floor").live("change",function(){
			CommonModular.includeAppTemplateFloorContent($(this).val());
		});
	}
	
	//添加导航按钮事件绑定
	$("#btnAddNav").live('click',function(){
		var obj = $("#addOneNav").html();
		$("div#oneNav").append(obj);
	});
	
	$(".close",$("div.row-block")).live('click',function(e){
		if($("div.row-block").length<=1){
			return;
		}
		$(this).parent().remove();
		e.preventDefault();
	});
	//点击选择商品分类
    $("#select_cms_tree").click(function(){
		bDialog.open({
            title : '分类选择',
            width : 350,
            height : 550,
            params:{'multi':false},
            url : GLOBAL.WEBROOT+"/cms/category/open/catgselect",
            callback:function(data){
            	if(data && data.results && data.results.length > 0 ){
                    var _catgs = data.results[0].catgs;
					var size = _catgs.length;
					for(var i =0;i<size;i++){
						var obj = _catgs[i];
						$('#categoryName',$(main)).val(obj.catgName);
						$('#categoryName',$(main)).parent().children("#propValue").val(obj.catgCode);
						$('#categoryName',$(main)).parent().children("#remark").val(obj.catgName);
					}
				}
            }
        });
	});
	
	//弹窗的取消事件
	$('#btnCancel').click(function(){
		if($(this).hasClass('wap')){
			$(this).parents(".page-phone-edit").hide();
	        $(".page-phone").css("padding-right", "0");
		}else{
			bDialog.closeCurrent();
		}
	});
	
	//选择展现的方式
	$('ul.goodsRowShowNum li',$(main)).click(function(e) {
		var mainRow = $(this).closest('div.row-fluid');
		$('ul.goodsRowShowNum li',$(main)).removeClass('selected');
		$(this).addClass('selected');
		$('#propValue',$(mainRow)).val($(this).data('result'));
	});
	//绑定单选的问题
	$("input[type='checkbox']").live('click',function(){
		if($(this).attr("checked")=="checked"){
			$(this).parent().children("#propValue").val(1);
		}else{
			$(this).parent().children("#propValue").val(0);
		}
	});
	
	//选择内容位置
	$("#contentLocationButton").live('click',function(){
		var title = "选择内容位置";
		var	url = "commonmodular/openlocationconten";
		bDialog.open({
			title : title,
			width : 860,
			height : 600,
			url : $webroot + url,
			params : {},
			callback:function(data){
				if(data && data.results && data.results[0]){
					$('#placeName',$(main)).val(data.results[0].templateName);
					$('#placeName',$(main)).parent().children("#propValue").val(data.results[0].templateId);
					$('#placeName',$(main)).parent().children("#remark").val(data.results[0].templateName);
				}
			}
		});
	});
	//去配置楼层
	$("#floorButton").live('click',function(){
		window.open($webroot+'floor/grid');
	});
	//去配置公告
	$("#infoButton").live('click',function(){
		window.open($webroot+'info/infogrid');
	});
	
	//去配置广告
	$("#advertiseButton").live('click',function(){
		//判断是否是卖家中心点击跳转过来的链接，如果是，则配置广告的时候，去卖家中心的广告管理里面配置
		if($("#mallskintomanage",$(window.parent.document)).val()=="true"){
			window.open($webroot+"cmsselleradvertise/init");
		}else{
			window.open($webroot+'advertise/grid?shopId='+$("#hiddenShopId",$(window.parent.document)).val());
		}
	});
	
	var preBox = $('div.imagePreviewBox');
	//***********************************************文件/图片上传处理*****************************************
	//返回的文件信息
	var fileInfo = {};
    //单个文件上传成功后的回调处理，实现上传结果处理等信息均在此处理
    //最终结果可在onQueueComplete中统一显示
//    var uploadSuccess = function(file,data,response){
//		if(response){
//			if(data){//data为后台返回的JSON内容
//				var tmp = JSON.parse(data);
//				//检查文件是否上传成功
//				if($.fn.eUpload.onUploadSuccessCheck(tmp)){
//					if(tmp && $.isPlainObject(tmp) && typeof(tmp.fileId) != 'undefined' && typeof(tmp.url) != 'undefined' ){
//						fileInfo.fileId = tmp.fileId;
//						fileInfo.url = tmp.url;
//					}else fileInfo = null;
//				}
//			}
//		}
//	};
//	//更多的参数请参考e.upload.js中的详细参数
//	if($("input.fileUploadPlugin").size() > 0){
//		$("input.fileUploadPlugin").each(function(i,n){
//			var main = $(this).closest('div.controls');
//			$(this).eUploadSilentInit({
//				'uploader' : $webroot + 'ecpupload/publicFileUpload',//后台接收文件处理的controller
//				'onUploadSuccess' : uploadSuccess,
//				//回调
//				'callback' : function(){
//					if(fileInfo){
//						$('#propValue',$(main)).val(fileInfo.fileId);
//						//TODO: 处理预览区唯一问题，同一位置，若多次上传图片，应覆盖原图
//						$('img',$(main)).attr("src",fileInfo.url);//移除原预览图
//					}else{
//						$('#propValue',$(main)).val('');
//					}
//				}
//			});
//		});
//	}  
	var ajaxFileUpload = function (url, secureuri, fileElementId, type, dataType, callback) {
		$.ajaxFileUpload({
					url : url, // 用于文件上传的服务器端请求地址
					secureuri : secureuri, // 一般设置为false
					fileElementId : fileElementId, // 文件上传空间的id属性 <input
					// type="file" id="imageFile"
					// name="imageFile" />
					type : type, // get 或 post
					dataType : dataType, // 返回值类型
					success : callback, // 服务器成功响应处理函数
					error : function(data, status, e) // 服务器响应失败处理函数
					{
						alert(e);
					}
				});
	};
	$('.com_input').each(function(){
    	$(this).bind("change", function(e) {
			var path = $(this).val();
			uploadImage(this, path);
			e.preventDefault();
		});
	});
	var uploadImage = function (object, path) {
		var main = $(object).closest('div.formItem');
    	var filepath = path;
    	filepath=(filepath+'').toLowerCase();
    	var regex = new RegExp(
    			'\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$', 'gi');
    	/** 上传图片文件格式验证 */
    	if (!filepath || !filepath.match(regex)) {
    		eDialog.alert('请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp).');
    		$(object).value = "";
    		return;
    	}
    	var url = GLOBAL.WEBROOT + '/commonmodular/uploadimage';
    	var callback = function(data, status) {
    		/** 上传成功，隐藏上传组件，并显示该图片 */
    		if (data.success == "ok") {
    			$('#propValue',$(main)).val(data.map.vfsId);
				//TODO: 处理预览区唯一问题，同一位置，若多次上传图片，应覆盖原图
				$('img',$(main)).attr("src",data.map.imagePath);//移除原预览图
    		} else {
    			eDialog.error(data.message);
    		}
    		$('.com_input').each(function(){
    	    	$(this).bind("change", function(e) {
    				var path = $(this).val();
    				uploadImage(this, path);
    				e.preventDefault();
    			});
    	    	
    		});
    	};
    	ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback);
    };
  
	//***********************************************文件/图片上传处理*****************************************
	//无图片上传内容，不显示图片预览区
	if($('.imgUploadStatus').size() > 0) $('div.imgPreviewArea').show();
	
	//***********************************************楼层导航*****************************************
	//添加楼层
	$("#btnAddFloor").live('click',function(){
		var obj = $("#floor_nav_add").html();
		$("div#floor_nav_list").append(obj);
	});
	//移除楼层
	$(".closeRow",$("div.floor_nav")).live('click',function(e){
		if($("div.floor_nav").length<=2){
			return;
		}
		$(this).parent().parent().parent().parent().remove();
		e.preventDefault();
	});
	
	//点击关联楼层
	$(".releatLayer",$("div.floor_nav")).live('click',function(e){
		var mainRow = $(this).closest('div.floor_nav');
		var remark = $('#remark',$(mainRow));
		var floorBox = $('div.floor-config-box');
		$(floorBox).data('target',remark);
		//清除所有选项的选中状态
		$(':radio',$(floorBox)).prop('checked', false);
		var floorId = $(remark).val();
		//设置关联项目选中
		if(floorId) $(':radio[value="'+floorId+'"]',$(floorBox)).prop('checked',true);
		$(floorBox).show();
	});
	//关闭楼层展示
	$('#btnFloorConfigCancel').click(function(){
		$('div.floor-config-box').hide();
	});
	//保存所选楼层
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
	
	//保存表单  **********************
	var formLock=false;
	$('#btnSave').unbind('click.bs').bind('click.bs',function(){
		$("#btnSave").attr("disabled","disabled");
		if(formLock){
			return ;
		}
		formLock=true;
		var $thisObj=$(this);
		var isWap;
		if($(this).hasClass('wap')){
			isWap = true;
		}else{
			isWap = false;
		}
		if(!$("#paramsSetForm").valid())return false;
		//获取公用参数
		var itemsData = CommonModular.getFormDataForCommon();
		if(itemsData.length==0){
			$("#error-msg").html("<span style='color:red'>请填写参数配置</span>").show();
			return;
		}else{
			$("#error-msg").hide();
		}
		var url = $('form.templateItemForms').data('url');
		if(itemsData && $.isArray(itemsData) && itemsData.length > 0 && url){
			var submitData = {'forms' : itemsData};
			$.eAjax({
				url : url,
				data : ebcUtils.serializeObject(submitData),
				success : function(returnInfo){
					if(returnInfo && $.type(returnInfo.resultFlag)!='undefined' && returnInfo.resultFlag == 'ok'){
						eDialog.alert('保存成功！',function(){
							if(isWap){
								//window.location.reload(); 
								var itemId = $("#modularItemId",'form.templateItemForms').val();
								$thisObj.parents(".page-phone-edit").hide();
						        $(".page-phone").css("padding-right", "0");
						        //进行局部刷新
						        if(itemId || 0 == itemId){
						        	var $item = $("#item-"+itemId).filter(".phone-layout");
						        	var componentVmUrl = $('#componentVmUrl',$item).val();//组件头部vm路径
						        	if(componentVmUrl){
						        		$.eAjax({
				            				url : $webroot +'page-edit/getItemComponentVm',
				            				async : true,
				        					type : "post",
				        					dataType : "html",
				            				data : {
				            					"id":itemId,
				            					"componentVmUrl":componentVmUrl
				            				},
				            				success : function(returnInfo){
				            					//进行局部刷新     表单的保存及wap的局部刷新在modular-common.js  
				            					$('.ecp-component,.nodata',$item).remove();
				            					$item.append(returnInfo);
				            					
				            					//有组件则启动组件
				            					var className = $(".ecp-component",$item).data("componentMethod") || "";
				            					var method = className.replace(/-/ig,"_");
				            					if(className && method){
				            						$("."+className,$item)[method]();
				            					}
				            				}
				            			});
						        	}
						        }
							}else{
								//web的局部刷新在page-common-edit.js
								bDialog.closeCurrent({'param' : 'saveBtn'});
							}
						},'confirmation');
					}
					$("#btnSave").removeAttr("disabled");
					formLock=false;
				},
				error : function(){
					$("#btnSave").removeAttr("disabled");
					formLock=false;
				}
			});
		}
	});
});

var CommonModular = {
		//获取隐藏域的表单数据。隐藏域的表单数据是我们要传到后场的参数
		getFormDataForCommon : function(){
			var form = $('form.templateItemForms');
			var formItems = $('div.formItem',$(form));
			var pageId = $('#modularPageId',$(form)).val();
			var itemId = $('#modularItemId',$(form)).val();
			var result = [];
			if(formItems && $(formItems).size() > 0){
				$.each(formItems, function(i, row) {
					//通过这个id数量判断是否有多选值的存在--多选
					if($(".lable-ck",$(row)).length>=2){
						$.each($(".lable-ck",$(formItems)),function(j,jrow){
							if($('#propValue',$(jrow)).val()=='1'){
								
								//单选框选中
								var item = {};
								item.id = $('#propItemId',$(jrow)).val();//预览布局项属性关系表ID
								item.propId = $('#propId',$(jrow)).val();//输入项ID
								item.propValue = $('#propValue',$(jrow)).val();
								if($('#controlPropId',$(row)).val()!= undefined && $('#controlPropId',$(row)).val() !=""){
									item.controlPropId = $('#controlPropId',$(row)).val();
								}
								if($('#propGroupId',$(row)).val()!= undefined && $('#propGroupId',$(row)).val() !=""){
									item.propGroupId = $('#propGroupId',$(row)).val();
								}
								if($("input[type='checkbox']",$(jrow)).attr("checked")=="checked" && $('#propValueId',$(jrow)).val()!= undefined || $('#propValueId',$(jrow)).val() !=""){
									item.propValueId = $('#propValueId',$(jrow)).val();
								}
								item.remark = "";
								item.pageId = pageId;
								item.itemId = itemId;
								result.push(item);
							}
						});
					}else{
						if($.trim($('#propValue',$(row)).val())!=""){
							var item = {};
							item.id = $('#propItemId',$(row)).val();//预览布局项属性关系表ID
							item.propId = $('#propId',$(row)).val();//输入项ID
							item.propValue = $('#propValue',$(row)).val();
							if($('#controlPropId',$(row)).val()!= undefined && $('#controlPropId',$(row)).val() !=""){
								item.controlPropId = $('#controlPropId',$(row)).val();
							}
							if($('#propGroupId',$(row)).val()!= undefined && $('#propGroupId',$(row)).val() !=""){
								item.propGroupId = $('#propGroupId',$(row)).val();
							}
							if($('#propValueId',$(row)).val()!= undefined && $('#propValueId',$(row)).val() !=""){
								item.propValueId = $('#propValueId',$(row)).val();
							}
							if($('#remark',$(row)).val()!= undefined && $('#remark',$(row)).val() !=""){
								item.remark = $('#remark',$(row)).val();
							}else{
								item.remark = "";
							}
							if(item.propId == 1019){
								item.sortNo=i;
							}
							item.pageId = pageId;
							item.itemId = itemId;
							result.push(item);
						}
					}
				});
			}
			return result;
		},
		//对于手动输入表单。输入值的时候对其进行propValue的赋值
		inputAssignMent : function(obj){
			$(obj).parents('.formItem').find("#propValue").val($(obj).val());
		},
		//对于下拉框输入。进行选择的时候对其进行propValueId的赋值
		selectAssignMent : function(obj){
			$(obj).parent().children("#propValue").val($(obj).val());
			$(obj).parent().children("#propValueId").val($(obj).attr('valueId'));
			
		},
		//
		includeAppTemplateFloorContent: function(obj){
			$('#app_template_floor_content').html("");
			if($("#pageEdit").hasClass('mCustomScrollbar')){
				$('#pageEdit').mCustomScrollbar('update');
			}else{
				var pageEdit = $("#pageEdit").mCustomScrollbar({
					scrollInertia: 150,
					advanced: {
						autoScrollOnFocus: false
					}
				});
			}
			$.eAjax({
				url : GLOBAL.WEBROOT+"/commonmodular/goFloorContent",
				data : {templateNo:obj,pageId:$('#publicPageId').val(),itemId:$('#publicItemId').val(),modularId:$('#publicModularId').val()},
				dataType : "text",
//				async: false,
				success : function(returnInfo){
					if(returnInfo) {
						$('#app_template_floor_content').html(returnInfo);
						$('#pageEdit').imagesLoaded(function(){
							if($("#pageEdit").hasClass('mCustomScrollbar')){
								$('#pageEdit').mCustomScrollbar('update');
							}else{
								var pageEdit = $("#pageEdit").mCustomScrollbar({
									scrollInertia: 150,
									advanced: {
										autoScrollOnFocus: false
									}
								});
							}
						}); 
					}
				},
				error : function (data){
					$('#app_template_floor_content').html();
				},complete: function (XMLHttpRequest, textStatus) {  
				    //textStatus的值：success,notmodified,nocontent,error,timeout,abort,parsererror  
					$('#btnSave').show();
				}
			});
		}
};
