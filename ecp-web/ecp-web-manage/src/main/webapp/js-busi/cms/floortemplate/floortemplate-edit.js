$(function(){
	floortemplate_edit.init();
});

var floortemplate_edit = {
	init : function(){//初始化
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			floortemplate_edit.saveFrom();
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			floortemplate_edit.pubSaveFrom();
		});
		//新增返回
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'floortemplate/grid',
				"params" :{"searchParams":(searchParams?searchParams:"")},
				"method" :"post"
			});
		});
		
		//初始化内容位置
		if($("#floorPlaceNum").val() && $(".floorPlace").size() < 1){
			floortemplate_edit.creatFloorPlace();
		}else{
			$("#delFloorPlaceButton").hide();
		}
		
		//内容位置个数发生变化时，清空内容位置元素
		$("#floorPlaceNum").live("change", function(o) {
			var floorPlaceNum = $("#floorPlaceNum").val();
			if(floorPlaceNum != null && floorPlaceNum != ''){
				floortemplate_edit.creatFloorPlace();
				$('#delFloorPlaceButton').show();
			}else{
				$('#floorPlaceDiv').empty();
				$('#delFloorPlaceButton').hide();
			}
		});
		//清除内容位置
		$('#delFloorPlaceButton').click(function(){
			if($(".floorPlace").size() > 0){
				eDialog.confirm("您将把内容位置的值清空，确认此操作吗？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							//$('#floorPlaceDiv').empty();
							$('.input-floorPlace').val('');
						}
					},{
						caption : '取消',
						callback : $.noop
					}]
				});
			}
		});
		//生成内容位置
		$('#addFloorPlaceButton').click(function(){
			floortemplate_edit.creatFloorPlace();
		});
		//点击图片上传触发事件
		var $uploadObj = $("#uploadFileObj");
		$uploadObj.eUploadBaseInit({
			imageMaxWidth:$uploadObj.data("placeWidth")||0,
			imageMaxHeight:$uploadObj.data("placeHeight")||0,
			fileSizeLimit:$uploadObj.data("placeSize")?$uploadObj.data("placeSize")+'KB':'0KB',
			callback:function(fileInfo){
				if(fileInfo && fileInfo.success){
					$("#vfsName").val(fileInfo.fileName);
					$("#vfsId").val(fileInfo.fileId);
					$("#imagePreview").attr("src",fileInfo.url);
				}
			}
		});
	},
	creatFloorPlace : function(){//生成内容位置
		var _floorPlaceDiv  =  $("#floorPlaceDiv");
		var floorPlaceNum = $("#floorPlaceNum").val();
		if(!floorPlaceNum || floorPlaceNum==0){
			eDialog.alert('请先选择内容位置个数！');
			return ;
		}
		var floorPlaceStr = '';
		for(var i=1;i<=floorPlaceNum;i++){
			var str = '<div class="formSep floorPlace">';
			str += '<label class="control-label">内容位置'+i+'：</label>';
			str += '<div class="control-group">';
			str += '<div class="controls">';
			str += '<table class="set-cont">';
			str += '<tr>';
			str += '<td style="display:none"><input type="text" class="input-mini" name="floorPlaceId" value=""/></td>';
			str += '<th><span style="color:red">*</span>宽：</th>';
			str += '<td><input type="text" class="input-medium digits input-floorPlace" name="placeWidth" placeholder="请输入1-1000的整数"/>px</td>';
			str += '<th><span style="color:red">*</span>高： </th>';
			str += '<td><input type="text" class="input-medium digits input-floorPlace" name="placeHeight" placeholder="请输入1-1000的整数"/>px</td>';
			str += '</tr>';
			str += '<tr>';
			str += '<th><span style="color:red">*</span>大小：</th>';
			str += '<td><input type="text" class="input-medium digits input-floorPlace" name="placeSize" placeholder="请输入1-1000的整数"/></td>';
			str += '<th>排序：</th>';
			str += '<td><input type="text" class="input-medium digits" name="sortNo" value="'+i+'" readonly/></td>';
			str += '</tr>';
			str += '</table>';
			str += '</div>';
			str += '</div>';
			str += '</div>';
			
			floorPlaceStr = floorPlaceStr + str;
		}
		_floorPlaceDiv.empty();
		_floorPlaceDiv.append(floorPlaceStr);
	},
	saveFrom : function(){//保存
		if(!$("#detailInfoForm").valid())return false;
		
		var floortemplate = {
			id : $('#id').val(),
			templateName : $('#templateName').val(),
			templateNo : $('#templateNo').val(),
			vfsId : $('#vfsId').val(),
			floorPlaceNum : $('#floorPlaceNum').val(),
			sortNo : $('#sortNo').val(),
			floorPlaceReqDTOList : []
		};
		
		//设置选中的商品信息
		$('.floorPlace').each(function(i,row){
			var floorPlaceReqDTO = {};
			floorPlaceReqDTO.floorTemplateId = $("id").val();
			floorPlaceReqDTO.id = $(row).find("input[name='floorPlaceId']").val();
			floorPlaceReqDTO.status = "1";
			floorPlaceReqDTO.placeWidth = $(row).find("input[name='placeWidth']").val();
			floorPlaceReqDTO.placeHeight = $(row).find("input[name='placeHeight']").val();
			floorPlaceReqDTO.placeSize = $(row).find("input[name='placeSize']").val();
			floorPlaceReqDTO.sortNo = $(row).find("input[name='sortNo']").val();
			floortemplate.floorPlaceReqDTOList.push(floorPlaceReqDTO);
		});
		
		$.eAjax({
			url : $webroot + "floortemplate/save",
			type : "POST",
			//data : ebcForm.formParams($("#detailInfoForm")),
		    data : ebcUtils.serializeObject(floortemplate),
		    datatype:'json',
		    beforeSend:function(){
				$.gridLoading({"message":"正在加载中...."});
			},
			success : function(returnInfo) {
				if(returnInfo && returnInfo.resultFlag == 'ok'){
					eDialog.success('楼层模板保存成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								var searchParams = $("#searchParams").val();
								SearchObj.openPage({
									"url": $webroot+'floortemplate/grid',
									"params" :{"searchParams":(searchParams?searchParams:"")},
									"method" :"post"
								});
					        }
						}]
					}); 
				}else{
					if(returnInfo){
						eDialog.alert(returnInfo.resultMsg);
						$.gridUnLoading();
					}else{
						eDialog.alert("保存失败！");
						$.gridUnLoading();
					}
				}
			},
			error : function(e,xhr,opt){
				eDialog.error("保存遇到异常!");
				$.gridUnLoading();
			},
			exception : function(msg){
				eDialog.error("保存遇到异常!");
				$.gridUnLoading();
			}
		});
	},
	pubSaveFrom : function(){//发布保存
		if(!$("#detailInfoForm").valid())return false;
		
		var floortemplate = {
			id : $('#id').val(),
			templateName : $('#templateName').val(),
			templateNo : $('#templateNo').val(),
			vfsId : $('#vfsId').val(),
			floorPlaceNum : $('#floorPlaceNum').val(),
			sortNo : $('#sortNo').val(),
			floorPlaceReqDTOList : []
		};
		
		//设置选中的商品信息
		$('.floorPlace').each(function(i,row){
			var floorPlaceReqDTO = {};
			floorPlaceReqDTO.floorTemplateId = $("id").val();
			floorPlaceReqDTO.id = $(row).find("input[name='floorPlaceId']").val();
			floorPlaceReqDTO.status = "1";
			floorPlaceReqDTO.placeWidth = $(row).find("input[name='placeWidth']").val();
			floorPlaceReqDTO.placeHeight = $(row).find("input[name='placeHeight']").val();
			floorPlaceReqDTO.placeSize = $(row).find("input[name='placeSize']").val();
			floorPlaceReqDTO.sortNo = $(row).find("input[name='sortNo']").val();
			floortemplate.floorPlaceReqDTOList.push(floorPlaceReqDTO);
		});
		
		$.eAjax({
			url : $webroot + "floortemplate/pubsave",
			type : "POST",
		    data : ebcUtils.serializeObject(floortemplate),
		    datatype:'json',
		    beforeSend:function(){
				$.gridLoading({"message":"正在加载中...."});
			},
			success : function(returnInfo) {
				if(returnInfo && returnInfo.resultFlag == 'ok'){
					eDialog.success('楼层模板发布成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								var searchParams = $("#searchParams").val();
								SearchObj.openPage({
									"url": $webroot+'floortemplate/grid',
									"params" :{"searchParams":(searchParams?searchParams:"")},
									"method" :"post"
								});
					        }
						}]
					}); 
				}else{
					if(returnInfo){
						eDialog.alert(returnInfo.resultMsg);
						$.gridUnLoading();
					}else{
						eDialog.alert("发布失败！");
						$.gridUnLoading();
					}
				}
			},
			error : function(e,xhr,opt){
				eDialog.error("发布遇到异常!");
				$.gridUnLoading();
			},
			exception : function(msg){
				$.gridUnLoading();
			}
		});
	},
	delRow : function (obj) {
		var s  = $(obj).closest(".floorPlace").remove();
	},
	/**
	 * 图片上传
	 * @param {} object  file对象
	 * @param {} path 本地文件路径
	 */
	uploadImage : function (object, path) {
		var data = {
				'place_width' : '650',
				'place_height' : '100',
				'place_size' : '100'
			};
    	var filepath = path;
    	filepath=(filepath+'').toLowerCase();
    	var regex = new RegExp('\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$');
    	/** 上传图片文件格式验证 */
    	if (!filepath || !filepath.match(regex)) {
    		eDialog.alert('请选择图片格式为(.jpg,.png,.jpeg,.gif,.bmp).');
    		$("#uploadFileObj").val("");
			$("#vfsId").val("");
			$("#vfsName").val("");
    		return;
    	}
    	var url = $webroot + 'common/uploadImage';
    	var callback = function(returnInfo) {
    		/** 上传成功，隐藏上传组件，并显示该图片 */
    		if (returnInfo.success == "ok") {
				$("#imagePreview").attr("src",returnInfo.resultMap.vfsUrlPri);
				$("#vfsId").val(returnInfo.resultMap.vfsId);
				$("#vfsName").val(returnInfo.resultMap.vfsName);
				eDialog.alert(returnInfo.message);
    		} else {
    			eDialog.alert(returnInfo.message);
    		}
    	};
    	floortemplate_edit.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback,data);
    },
    ajaxFileUpload : function (url, secureuri, fileElementId, type, dataType, callback,data) {
		$.ajaxFileUpload({
			url : url, // 用于文件上传的服务器端请求地址
			secureuri : secureuri, // 一般设置为false
			data : data,
			fileElementId : fileElementId, // 文件上传空间的id属性 <input type="file" id="imageFile" name="imageFile" />
			type : type, // get 或 post
			dataType : dataType, // 返回值类型
			success : callback, // 服务器成功响应处理函数
			error : function(data, status, e) // 服务器响应失败处理函数
			{
				$("#uploadFileObj").val("");
				$("#vfsId").val("");
				$("#vfsName").val("");
				eDialog.alert(e);
			}
		});
	}
	
};

