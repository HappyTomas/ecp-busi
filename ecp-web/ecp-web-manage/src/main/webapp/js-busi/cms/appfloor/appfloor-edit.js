$(function(){
	var appFloorData = {
			"data":{},
			"initData":function($floorplaceblock){
				var $linkTypes = null;
				if($floorplaceblock && 0 < $floorplaceblock.length ){
					$linkTypes  = $(".linkType",$floorplaceblock);
				}else{
					$linkTypes = $(".linkType",".floorplace-block");
				}
				$linkTypes.each(function(){
					appFloorData.updateData($(this));
				});
			},
			"updateData":function($linkType){//更新缓存数据
				if($linkType){
					var type = $linkType.val();
					var $floorPlace = $linkType.parents("div.floorplace-block");
					var $linkNameInput = $floorPlace.find(".linkName");
					var $linkUrlInput = $floorPlace.find(".linkUrl");
				    appFloorData["data"][$linkNameInput.attr("name")+"-"+type] = $linkNameInput.val();
					appFloorData["data"][$linkUrlInput.attr("name")+"-"+type] = $linkUrlInput.val();
				}
			},
			"changeInputVal":function($linkType){//将数据填到输入框
				if($linkType){
					var type = $linkType.val();
					var $floorPlace = $linkType.parents("div.floorplace-block");
					var $linkNameInput = $floorPlace.find(".linkName");
					var $linkUrlInput = $floorPlace.find(".linkUrl");
					var linkNameVal = appFloorData["data"][$linkNameInput.attr("name")+"-"+type];
					var linkUrlVal = appFloorData["data"][$linkUrlInput.attr("name")+"-"+type];
					$linkNameInput.val(linkNameVal?linkNameVal:"");
					$linkUrlInput.val(linkUrlVal?linkUrlVal:"");
				}
			}
	};
	var addBtnObj={//添加按钮对象
			"total":0,
			"current":0,
			"init":function(current,total){
				current = parseInt(current);
				total = parseInt(total);
				if(current && total && 0 < current && 0 < total && total >= current){
					addBtnObj.total = total;
					addBtnObj.current = current;
					addBtnObj.shader();
					return addBtnObj;
				}
				return null;
			},
			"goNext":function(){
				if(addBtnObj.current >= addBtnObj.total){
					return false;
				}
				addBtnObj.current++;
				addBtnObj.shader();
				return true;
			},
			"goPrev":function(){
				if(1 >= addBtnObj.current){
					return false;
				}
				addBtnObj.current--;
				addBtnObj.shader();
				return true;
			},
			"shader":function(){
				if(0 < addBtnObj.total ){
					$(".add-btn-tip","#add-place-data").text(addBtnObj.current+"/"+addBtnObj.total);
				}else{
					$(".add-btn-tip","#add-place-data").text("");
				}
			}
	}
	var placeHander = {//app楼层数据处理器
			"placeIds":[],
			"deletingDataIds":[],
			"geneNo":1,
			"setPlaceIds":function(ids){
				ids +="";
				var idList = [];
				var placeIds = [];
				if(ids){
				    idList = ids.split(",");
				}
				if(idList && 0 < idList.length ){
					for(var i in idList){
						if(parseInt(idList[i])){
							placeIds.push(idList[i]);
						}
					}
					placeHander.placeIds = placeIds;
				}
			},
			"setDelDataId":function(id){
				id= parseInt(id);
				if(id){
					placeHander.deletingDataIds.push(id);
				}
			},
			"getGeneKey":function(key){
				key = key+"";
				if(!key){
					key = "gene";
				}
				return key+placeHander.geneNo++;
			},
			"init":function($floorplaceblock){
				if($floorplaceblock && 0 < $floorplaceblock.length){
					//初始化显示“选择”按钮
					$(".linkType",$floorplaceblock).each(function(){
						appfloor_edit.initLinkName($(this));
					});
					//初始化备注统计数据
					appfloor_edit.initRemarkCount($floorplaceblock);
					//初始化存储数据  linkname/linkUrl
					appFloorData.initData($floorplaceblock);
					//初始化图片长传
					placeHander.initImageUpload($floorplaceblock);
				}
			},
			"resetPlace":function($floorplaceblock,key,sortNo){
				if(!$floorplaceblock || 0 >= $floorplaceblock.length || !key || !sortNo){
					return null;
				}
				$floorplaceblock.removeAttr("floor-place-id");
				//值置空
				$("input",$floorplaceblock).val("");
				$("select",$floorplaceblock).val("01");
				//属性重置
				$(".place-sort",$floorplaceblock).text(sortNo);
				$(".img-polaroid",$floorplaceblock).attr("src",$("#empty-img").val());
				$(".vfsId",$floorplaceblock).attr("name","vfsId-"+key);
				$(".uploadFileObj",$floorplaceblock).attr("id","uploadFileObj-"+key);
				$(".linkType",$floorplaceblock).attr("name","linkType-"+key);
				$(".linkName",$floorplaceblock).attr("name","linkName-"+key);
				$(".linkUrl",$floorplaceblock).attr("name","linkUrl-"+key);
				
				return $floorplaceblock;
				
			},
			"getEmptyPalce":function(key){
				var $places = $(".floorplace-block",".floorTemplate-block");
				if($places && 0 < $places.length){
					return placeHander.resetPlace($places.last().clone(true),placeHander.getGeneKey(key),addBtnObj.current);
				}
				return null;
			},
			"moveUp":function($floorplaceblock){
				if(!$floorplaceblock || 0 >= $floorplaceblock.length ){
					return this;
				}
				var $prev = $floorplaceblock.prev(".floorplace-block");
				if(!$prev || 0 >= $prev.length){
					return this;
				}
				var prevSort = $(".place-sort",$prev).text();
				$(".place-sort",$prev).text($(".place-sort",$floorplaceblock).text());
				$(".place-sort",$floorplaceblock).text(prevSort);
				$floorplaceblock.insertBefore($prev);
			},
			"moveDown":function($floorplaceblock){
				if(!$floorplaceblock || 0 >= $floorplaceblock.length){
					return null;
				}
				var $next = $floorplaceblock.next(".floorplace-block");
				if(!$next || 0 >= $next.length ){
					return this;
				}
				var nextSort = $(".place-sort",$next).text();
				$(".place-sort",$next).text($(".place-sort",$floorplaceblock).text());
				$(".place-sort",$floorplaceblock).text(nextSort);
				$floorplaceblock.insertAfter($next);
			},
			"moveDel":function($floorplaceblock){
				if(!$floorplaceblock || 0 >= $floorplaceblock.length || !addBtnObj.goPrev()){
					return null;
				}
				var $nextAll = $floorplaceblock.nextAll(".floorplace-block");
				if($nextAll && 0 < $nextAll.length){
					$nextAll.each(function(){
						var $placeSort = $(".place-sort",$(this));
						var sort =parseInt($placeSort.text());
						if(sort){
							$placeSort.text(sort-1);
						}
					});
				}
				placeHander.setDelDataId($(".floor-data-id",$floorplaceblock).val());
				$floorplaceblock.remove();
			},
			"initImageUpload":function($floorplaceblock){
				if(!$floorplaceblock || 0 >= $floorplaceblock.length){
					return null;
				}
				//点击图片上传触发事件
				$(".uploadFileObj",$floorplaceblock).each(function(){
					var $uploadObj = $(this);
					var $imageUploadForm = $uploadObj.closest(".imageUploadForm");
					$uploadObj.eUploadBaseInit({
						imageMaxWidth:$uploadObj.data("placeWidth")||0,
						imageMaxHeight:$uploadObj.data("placeHeight")||0,
						fileSizeLimit:$uploadObj.data("placeSize")?$uploadObj.data("placeSize")+'KB':'0KB',
						callback:function(fileInfo){
							if(fileInfo && fileInfo.success){
								$(".vfsId",$imageUploadForm).val(fileInfo.fileId);
								$(".imagePreview",$imageUploadForm).attr("src",fileInfo.url);
							}
						}
					});
				});
			}
			
	}
	var appfloor_edit = {
		"isStrict":true,//是否对内容位置严格控制,
		"switchMode":function(){
			var $palceIds = $("#place-ids");
			placeHander.setPlaceIds($palceIds.val());
			placeHander.deletingDataIds = [];
			var isSlider = $palceIds.attr("data-is-slider");
			if(isSlider == '1'){
				appfloor_edit.isStrict = false;
				addBtnObj.init($(".floorplace-block").length,$palceIds.attr("data-total"));
				appfloor_edit.bindAddFloorBtn();
				appfloor_edit.bindMoveMenu();
			}else{
				appfloor_edit.isStrict = true;
			}
		},
		init : function(){//初始化
			//绑定选择内容位置
			$('#floorTemplateId').live('change', function(){
				appfloor_edit.initFloorPlace();
			});
			//绑定链接类型
			$('.linkType').live('change', function(){
				appfloor_edit.changLinkName($(this));
			});
			//存储当前数据
			$('.linkType').live('click', function(){
				appFloorData.updateData($(this));
			});
			
			//绑定选择内容
			$('.select_link_detail').live("click",function(){
				appfloor_edit.openLinkDetail($(this));
			});
			//绑定保存按钮
			$('#btnFormSave').click(function(){ 
				appfloor_edit.saveFrom("0");
			});
			//绑定发布按钮
			$('#btnFormPubSave').click(function(){ 
				appfloor_edit.saveFrom("1");
			});
			//新增返回
			$('#btnReturn').click(function(){
				var searchParams = $("#searchParams").val();
				SearchObj.openPage({
					"url": $webroot+'appfloor/grid',
					"params" :{"searchParams":(searchParams?searchParams:"")},
					"method" :"post"
				});
			});
			//初始化字数
			$.isFunction(checkLen) && checkLen($("#remark").get(0),'count','250');
		},
		"bindAddFloorBtn":function(){
			$("#add-place-data").unbind("click.apd").bind("click.apd",function(){
				if(appfloor_edit.isStrict){
					return this;
				}
				if(addBtnObj.goNext()){
					var $emptyPlace=placeHander.getEmptyPalce("slide");
					if($emptyPlace && 0 < $emptyPlace.length){
						$(".floorplace-block:last",".floorTemplate-block").after($emptyPlace);
						placeHander.init($emptyPlace);
					}
				}
			});
		},
		"bindMoveMenu":function(){
			$(".mup",".menu-list",".floorplace-block").unbind("click.mup").bind("click.mup",function(){
				placeHander.moveUp($(this).closest(".floorplace-block"));
			});
			$(".mdown",".menu-list",".floorplace-block").unbind("click.mup").bind("click.mup",function(){
				placeHander.moveDown($(this).closest(".floorplace-block"));
			});
			$(".mdel",".menu-list",".floorplace-block").unbind("click.mup").bind("click.mup",function(){
				placeHander.moveDel($(this).closest(".floorplace-block"));
			});
		},
		"initFloorPlace":function(){//根据模板初始化楼层内容位置
			var FloorId =  $("#id").val();
			var floorTemplateId = $("#floorTemplateId").val();
			var $floorTemplate = $("#floorTemplateId").closest("div.formSep");
			//清除旧数据
			$(".floorTemplate-block").remove();
			//有选定楼层模板  则初始化楼层内容位置页面块
			if(floorTemplateId){
				$.eAjax({
					url : $webroot + "appfloor/queryFloorPlace",
					data : {"id":FloorId,"floorTemplateId":floorTemplateId},
					dataType:"html",
					success : function(returnInfo) {
						if(returnInfo){
							var $datas = $(returnInfo);
							$floorTemplate.after($datas);
							//初始app数据
							$(".floorplace-block",$datas).each(function(){
								placeHander.init($(this));
							});
							appfloor_edit.switchMode();
						}
					},
					error : function(e,xhr,opt){
						eDialog.error("查询楼层内容位置遇到异常!");
					},
					exception : function(msg){
						eDialog.error(msg);
					}
				});
			}
			
		},
		"changLinkName" : function($object){//根据链接类型调整链接内容显示
			if($object){
				//设置数据
				appFloorData.changeInputVal($object);
				
				//改变样式
				appfloor_edit.initLinkName($object);
			}
		},
		"initLinkName" : function($object){//初始化是否显示“选择”按钮
			if($object){
				var $floorPlace = $object.parents("div.floorplace-block");
				var $linkDetailBtn = $floorPlace.find(".select_link_detail");
				var $linkNameInput = $floorPlace.find(".linkName");
				var $linkUrlBlock = $floorPlace.find("div.linkUrl-block");
				var $linkUrlInput = $linkUrlBlock.find(".linkUrl");
				var linkType = $object.val();
				
				if(linkType == "09"){//其他
					//初始化连接内容（名称）
					$linkNameInput.removeAttr("readonly");
					$linkNameInput.attr("placeholder","请输入链接内容(2~256个字符之间)");
					$linkDetailBtn.hide();
					
					//初始化连接地址
					$linkUrlBlock.show();
					$linkUrlInput.removeAttr("readonly");
				}else{//商品 、公告  促销
					//初始化连接内容（名称
					$linkNameInput.attr("readonly","readonly");
					$linkNameInput.attr("placeholder","请选择链接内容");
					$linkDetailBtn.show();
					
					//初始化连接地址
					$linkUrlBlock.hide();
				}
			}
			
		},
		openLinkDetail : function($object){
			if($object){
				var $floorPlace = $object.parents("div.floorplace-block");
				var $linkNameInput = $floorPlace.find(".linkName");
				var $linkUrlInput = $floorPlace.find(".linkUrl");
				var linkType = $floorPlace.find(".linkType").val();
				$floorPlace = null;
				
				var title = "选择链接内容";
				if(!linkType || linkType.length==0){
					eDialog.alert('请先选择链接类型！');
					return ;
				}
				var url = "";
				var siteId = $("#siteId").val() || '';
				var shopId = $("#shopId").val() || '';
				if(linkType == "01"){//商品
					if(siteId==""){
						eDialog.alert('请先选择站点！');
						return;
					}
					url = "appfloor/opengds?shopId="+shopId+"&siteId="+siteId;
				}else if(linkType == "02"){//公告
					url = "appfloor/openinfo";
				}else if(linkType == "03"){//促销
					//app 只返回wap类型的促销也  特殊处理  start
					var platformType = "03";
					//app 只返回wap类型的促销也  特殊处理  end	
					if(siteId==""){
						eDialog.alert('请先选择站点！');
						return;
					}
					url = "pageInfo/openpageinfo?siteId="+siteId+"&platformType="+platformType;
				}else{
					return;
				}
				bDialog.open({
					title : title,
					width : 860,
					height : 500,
					url : $webroot + url,
					params : {
					},
					callback:function(data){
						if(data && data.results && data.results[0]){
							$linkUrlInput.val(data.results[0].linkUrl);
							$linkNameInput.val(data.results[0].infoTitle);
						}
					}
				});
			}
		},
		"initRemarkCount":function($floorplaceblock){//初始化字数限制提醒
			var $cmsCounts = null;
			if ($floorplaceblock && $floorplaceblock.length >0){
				$cmsCounts = $(".cms-count",$floorplaceblock);
			}else{
				$cmsCounts = $(".cms-count",".floorplace-block");
			}
			$cmsCounts.each(function(){
				var $cmsTotalNum = $(this).find(".cms-totalNum");
				var $cmsText =$(this).find(".cms-text");
				var totalNum = +$cmsTotalNum.text() || 0;
				var text = $cmsText.val();
				
				if(!totalNum || !text){
					return false;
				}
				if(totalNum < text.length){
					$cmsText.val(text.subString(0,totalNum-1));
					$cmsTotalNum.text(0);
				}else{
					$cmsTotalNum.text(totalNum - text.length);
				}
			});
		},
		
		"saveFrom": function(status){//保存
			if(!$("#detailInfoForm").valid())return false;
			$.gridLoading({"message":"正在加载中...."});
			
			var alertTitle =null;
			if(status == 1){
				alertTitle = "app楼层发布成功！";
			}else{
				alertTitle = "app楼层保存成功！";
			}
			//封装数据
			var appfloor = {
					"id": $("#id").val(),
					"floorName":$("#floorName").val(),
					"linkUrl":$("#linkUrl").val(),
					"status":status?status:"0",
					"sortNo":$("#sortNo").val(),
					"remark":$("#remark").val(),
					"siteId":$("#siteId").val(),
					"floorTemplateId":$("#floorTemplateId").val(),
					"cmsAppFloorDataVOList" : []
			};
			//获取楼层内容位置的数据
			appfloor.cmsAppFloorDataVOList = appfloor_edit.getFloorData();
			if(!appfloor_edit.isStrict){
				appfloor.cmsAppFloorDataVOList = appfloor_edit.getDelFloorData(appfloor.cmsAppFloorDataVOList);
			}
			$.eAjax({
				url : $webroot + "appfloor/save",
				data :  ebcUtils.serializeObject(appfloor),
				success : function(returnInfo) {
					if(returnInfo.resultFlag == "ok"){
						eDialog.success(alertTitle,{
							buttons:[{
								caption:"确定",
								callback:function(){
									var searchParams = $("#searchParams").val();
									SearchObj.openPage({
										"url": $webroot+'appfloor/grid',
										"params" :{"searchParams":(searchParams?searchParams:"")},
										"method" :"post"
									});
						        }
							}]
						}); 
					}else{
						eDialog.alert(returnInfo.resultMsg);
					}
				},
				error : function(e,xhr,opt){
					eDialog.error("保存遇到异常!");
					$.gridUnLoading();
				},
				exception : function(msg){
					$.gridUnLoading();
				}
			});
		},
		"getFloorData" : function(){
			var floorDatas = [];
			var $floorplaces = $(".floorplace-block");
			var cmsAppFloorDataVO = null;
			$floorplaces.each(function(i,el){
				var floorPlaceId = "";
				if(appfloor_edit.isStrict || !placeHander.placeIds || 0 >= placeHander.placeIds.length){
					floorPlaceId = $(this).attr("floor-place-id");
				}else{//自由模式 则按placeIds 对应
					floorPlaceId = placeHander.placeIds[i];
				}
				cmsAppFloorDataVO = null;
				cmsAppFloorDataVO = {
						"id" : $(this).find(".floor-data-id").val()||"",
						"appFloorId" : $("#id").val(),
						"floorPlaceId" : floorPlaceId||'',
						"name" : $(this).find(".linkName").val()||'',
						"vfsId" : $(this).find(".vfsId").val()||'',
						"linkType" : $(this).find(".linkType").val()||'',
						"linkUrl" : $(this).find(".linkUrl").val()||'',
						"remark" : $(this).find(".remark").val()||'',
						"status" : "1"
				}
				if(cmsAppFloorDataVO && cmsAppFloorDataVO.linkUrl &&cmsAppFloorDataVO.floorPlaceId){
					floorDatas.push(cmsAppFloorDataVO);
				}
			});
			return floorDatas;
		},
		"getDelFloorData" : function(floorDatas){
			var floorDatas = floorDatas ||[];
			var cmsAppFloorDataVO = null;
			var detelingIds = placeHander.deletingDataIds;
			if(!appfloor_edit.isStrict && detelingIds &&  detelingIds.length > 0){
				$.each(detelingIds,function(i,el){
					cmsAppFloorDataVO = null;
					cmsAppFloorDataVO = {
							"id" : detelingIds[i]||"",
							"status" : "2"
					}
					if(cmsAppFloorDataVO &&cmsAppFloorDataVO.id){
						floorDatas.push(cmsAppFloorDataVO);
					}
				});
			}
			return floorDatas;
		}
	};
	
	appfloor_edit.initFloorPlace();
	appfloor_edit.init();
});


