var templateConfigWap = {
	"init" : function(){//初始化配置页面
		calPageDecorateHeight();
		templateConfigWap.showLayoutDesign();
		var $layoutItemDiv=$('.active',$('#tpl-content'));
		$(".page-phone").css("padding-right", "0px");
        $(".page-phone-edit").hide();
		//发布功能
		$('#lnkTemplatePub').click(function(e) {
			var pId = $('#templateId').val();
			eDialog.confirm('确认要发布该模板吗？',{
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : $webroot + "templateLib/changestatus",
							data : {'id' : pId,status:1},
							success : function(returnInfo){
								if(returnInfo && returnInfo.resultFlag == 'ok'){//发布成功
									eDialog.alert('模板发布成功！',function(){
										window.location.replace($webroot + 'templateLib/grid');
										//templateConfig.goList();
									},'confirmation');
								}
						    }
						});
					}
				},{
					caption : '取消',callback : $.noop
				}]
			});
		});
	},
	"designModeSelect" :function(){//布局管理与页面编辑切换
		$(".design-mode-select li").click(function(){
	        var index=$(this).index();
	        var pageType;
	        if(index == 0){//布局管理
	        	pageType = "layout";
	        }else if(index == 1){//页面编辑
	        	pageType = "edit";
	        }
	        SearchObj.openPage({
				"url": $webroot+'pageConfig/init?pageId='+$('#pageId').val()+'&pageType='+pageType,
				"params" :{"pageId":$('#pageId').val(),"mallskintomanage":$("#mallskintomanage").val()},
				"method" :"post"
			});
	    });
	},
	includeCss: function(file) 
	{ 
		 
			var name = file.replace(/^\s|\s$/g, "");
			var att = name.split('.');
			var ext = att[att.length - 1].toLowerCase();
			var isCSS = ext == "css";
			var tag = isCSS ? "link" : "script";
			var attr = isCSS ? " type='text/css' rel='stylesheet' "
					: " type='text/javascript' ";
			var link = (isCSS ? "href" : "src") + "='" +  name
					+ "'";
			//$.includePath +
			if ($(tag + "[" + link + "]").length == 0)
				$("head").prepend("<" + tag + attr + link + "></" + tag + ">");
		  
	},	
	loadjscssfile: function(filename, filetype){
		if(!filename || !filetype) return;
		if (filetype=="js"){ //if filename is a external JavaScript file 
			var fileref=document.createElement('script') ;
			fileref.setAttribute("type","text/javascript") ;
			fileref.setAttribute("src", filename); 
		}else if (filetype=="css"){ //if filename is an external CSS file 
			var fileref=document.createElement("link"); 
			fileref.setAttribute("rel", "stylesheet");
			fileref.setAttribute("type", "text/css");
			fileref.setAttribute("href", filename);
		} 
		if (typeof fileref!="undefined") {
			try {
				document.getElementsByTagName("head")[0].appendChild(fileref);
			} catch (e) {}
		} 
//		 
//		loadjscssfile("myscript.js", "js") ;//dynamically load and add this .js file 
//		loadjscssfile("javascript.php", "js") ;//dynamically load "javascript.php" as a JavaScript file 
//		loadjscssfile("mystyle.css", "css"); //
	},
	removejscssfile: function(filename, filetype){
		//判断文件类型 
		var targetelement=(filetype=="js")? "script" : (filetype=="css")? "link" : "none"; 
		//判断文件名 
		var targetattr=(filetype=="js")? "src" : (filetype=="css")? "href" : "none"; 
		var allsuspects=document.getElementsByTagName(targetelement); 
		//遍历元素， 并删除匹配的元素 
		for (var i=allsuspects.length; i>=0; i--){ 
			if (allsuspects[i] && allsuspects[i].getAttribute(targetattr)!=null && allsuspects[i].getAttribute(targetattr).indexOf(filename)!=-1){
				allsuspects[i].parentNode.removeChild(allsuspects[i]); 
			} 
		}
	},
	"showLayoutDesign":function(){
		var reqType = $('#reqType').val();
		var templateId = $("#templateId").val();
		var pageTypeId = $("#pageTypeId").val();
		var $content = $("#tpl-content");
		if(!templateId){
			$content.html("<span>模板不存在，请返回模板列表确认信息！</span>");
		}
		//请求参数
		var param = {
					reqType:reqType?reqType:"",
					templateId:templateId?templateId:"",
					pageTypeId:pageTypeId?pageTypeId:"",
					platformType:"wap"
				};
		
		$.eAjax({
			url : $webroot + "templateLayout/init",
			data : param,
			async : true,
			type : "post",
			dataType : "html",
			success : function(returnInfo){
				if(returnInfo && returnInfo !='error'){//查询成功
					$content.html(returnInfo);
					//刷新拖拽功能作用对象
					if(doJsmartdrag.jsmartdragObj){
						doJsmartdrag.jsmartdragObj.freshTarget();
					}
					$('#pageDecorate').mCustomScrollbar('update');
				}else{
					$content.html("<span>查询模板布局异常！</span>");
				}
		    },
			error : function(e,xhr,opt){
				$content.html("<span>查询模板布局错误！</span>");
			},
			exception : function(msg){
				$content.html("<span>获取模板布局异常！</span>");
			}
		});
	},
	/**
	 * 图片上传
	 * @param {} object  file对象
	 * @param {} path 本地文件路径
	 */
	"uploadImage" : function (object, path ,callback) {
		if(callback && (typeof callback) == "function"){
			var data = {
					'place_width' : 280,
					'place_height' : 200,
					'place_size' : 100
				};
		    	var filepath = path;
		    	filepath=(filepath+'').toLowerCase();
		    	var regex = new RegExp('\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$');
		    	/** 上传图片文件格式验证 */
		    	if (!filepath || !filepath.match(regex)) {
		    		eDialog.alert('请选择图片格式为(.jpg,.png,.jpeg,.gif,.bmp).');
		    		return;
		    	}
		    	var url = $webroot + 'common/uploadImage';
		    	
		    	pageConfigSideBarWap.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback,data);
		}else{
			eDialog.alert("无效的回调函数，请联系管理员！");
		}
    },
    "ajaxFileUpload" : function (url, secureuri, fileElementId, type, dataType, callback,data) {
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
				eDialog.alert(e);
			}
		});
	}
};
var doJsmartdrag = {
	"jsmartdragObj" : null,//存放拖拽功能作用的对象
	"bandJsmartdrag" :function($obj,jsmartdragParam){
		if(!($obj instanceof jQuery) || !jsmartdragParam || !jsmartdragParam.target){
			return false;
		}
		var param = 
		doJsmartdrag.jsmartdragObj = $obj.jsmartdrag(jsmartdragParam);
	}
};
function calPageDecorateHeight() {
    $('#pageDecorate').height($(window).height()
            - $('.tpl-navbar').height()
            - $('.design-navigation-wrap').height()
            - 10);
}
$(function(){
	templateConfigWap.init();
	$('#goBack').click(function(){ 
		var reqType=$('#reqType').val();
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
				"id":$("#templateId").val(),
				"reqType":reqType
		}
		SearchObj.openPage({
			"url" : $webroot+'templateLib/edit',
			"params" : params,
			"method" :"post"
		});
	});
	$('#backtogrid').click(function(){
		var params = {
				"mallskintomanage" : $("#mallskintomanage").val()
		};
		var url = "";
		if($("#mallskintomanage").val()=="true"){
			url = $webroot+'cmssellerpageinfo/init';
		}else{
			url = $webroot+'pageInfo/grid';
		}
		SearchObj.openPage({
			"url" : url,
			"params" : params,
			"method" :"post"
		});
	});

    $(".phone-layout").live('click',function () {
    	$(this).toggleClass('active')
    	.siblings('.phone-layout')
    	.removeClass('active');
    });

    //上移
    $(".mup").live('click',function(){
    	var $div_layout=$(this).parents(".phone-layout");
    	var $prevOne= $div_layout.prev(".phone-layout");
    	if($prevOne.length==0){
    		$div_layout.addClass("active");
    		return ;
    	}
    	var this_modularId=$('#modularId',$div_layout).val();
    	if(this_modularId==111){
    		eDialog.alert("该模块只能放在最下方，上移失败");
			return;
    	}
    	//异步保存入库 start
    	var param = {
				"templateLayoutItemList":[]
			};
    	var thisLayoutItem = {};
    	thisLayoutItem.id = $('#itemId',$div_layout).val();
    	thisLayoutItem.rowNo = $('#rowNo',$prevOne).val();
        param.templateLayoutItemList.push(thisLayoutItem);
        
        var downLayoutItem = {};
        downLayoutItem.id = $('#itemId',$prevOne).val();
        downLayoutItem.rowNo = $('#rowNo',$div_layout).val();
        param.templateLayoutItemList.push(downLayoutItem);
        
        templateConfigSideBarWap.updateLayoutItemBatch(param,null,null); 
        //异步保存入库  end
    	var rowNo=$('#rowNo',$div_layout).val();
    	$('#rowNo',$div_layout).val($('#rowNo',$prevOne).val());
    	$('#rowNo',$prevOne).val(rowNo);
    	
    	$prevOne.addClass("active");
    	$div_layout.removeClass("active");
    	
    	var inHtml=$div_layout.clone(true);
    	var inHtml2=$prevOne.clone(true);
    	$prevOne.replaceWith(inHtml);
    	$div_layout.replaceWith(inHtml2);
    	
    	doJsmartdrag.jsmartdragObj.freshTarget();
        $('#pageDecorate').mCustomScrollbar('update');
    });
    //下移
    $(".mdown").live('click',function(){
    	var $div_layout=$(this).parents(".phone-layout");
    	var $next_layout= $div_layout.next(".phone-layout");
    	if($next_layout.length==0){
    		$div_layout.addClass("active");
    		return ;
    	}
    	var next_modularId=$('#modularId',$next_layout).val();
    	if(next_modularId==111){
    		eDialog.alert("下方模块只能放在最下方，下移失败");
			return;
    	}
    	//异步保存入库 start
    	var param = {
				"templateLayoutItemList":[]
			};
    	var thisLayoutItem = {};
    	thisLayoutItem.id = $('#itemId',$div_layout).val();
    	thisLayoutItem.rowNo = $('#rowNo',$next_layout).val();
        param.templateLayoutItemList.push(thisLayoutItem);
        
        var downLayoutItem = {};
        downLayoutItem.id = $('#itemId',$next_layout).val();
        downLayoutItem.rowNo = $('#rowNo',$div_layout).val();
        param.templateLayoutItemList.push(downLayoutItem);
        
        templateConfigSideBarWap.updateLayoutItemBatch(param,null,null); 
        //异步保存入库  end
        var rowNo=$('#rowNo',$div_layout).val();
    	$('#rowNo',$div_layout).val($('#rowNo',$next_layout).val());
    	$('#rowNo',$next_layout).val(rowNo);
        
    	$next_layout.addClass("active");
    	$div_layout.removeClass("active");
    	
    	var inHtml=$div_layout.html();
    	$div_layout.html($next_layout.html());
    	$next_layout.html(inHtml);
    	
    	doJsmartdrag.jsmartdragObj.freshTarget();
    	$('#pageDecorate').mCustomScrollbar('update');
    });
    //删除
    $(".mdel",".phone-content").live('click',function(){
    	var $div_layout=$(this).parents(".phone-layout");
    	eDialog.confirm('确定移除该布局项吗？',{
			buttons : [{
				caption : '确定',
				callback : function(){
					// $(this).parents(".phone-layout").hide();
			        var param = {
							"templateLayoutItemList":[]
						};
			        var delLayoutItem = {};
			        delLayoutItem.id = $('#itemId',$div_layout).val();
			        delLayoutItem.status = 2;
			        param.templateLayoutItemList.push(delLayoutItem);
			        var $prevOne= $div_layout.prev(".phone-layout");
			        var $nextOne= $div_layout.next(".phone-layout");
			        var allDel=false;
			        if($prevOne.length==0&&$nextOne.length==0){
			        	allDel=true; 
			        }
			        var i=0;
			        var thisRowNo=$('#rowNo',$div_layout).val();
			        while(true){
			        	var divId=$('#id',$nextOne).val()
						if($nextOne.length>0 && 'default_layout_item'!=divId){
							var nextRowNo=$('#rowNo',$nextOne).val();
							var nextItemId=$('#itemId',$nextOne).val();
							var nextModularId=$('#modularId',$nextOne).val();
							var nextLayoutItem = {};
//							nextRowNo=parseFloat(nextRowNo)-1;
//							nextLayoutItem.rowNo=nextRowNo;
//							$('#rowNo',$nextOne).val(nextRowNo);
							var rowNoTemp=parseFloat(thisRowNo)+i;
							nextLayoutItem.rowNo=rowNoTemp;
							$('#rowNo',$nextOne).val(rowNoTemp);
							
							
							nextLayoutItem.id = nextItemId;
							nextLayoutItem.modularId = nextModularId;
							nextLayoutItem.itemSize = 0;
							nextLayoutItem.itemNo = 1;
							param.templateLayoutItemList.push(nextLayoutItem);
						}else{
							break;
						}
						$nextOne=$nextOne.next('.phone-layout');
						i=i+1;
					}
			        $div_layout.remove();
			        if(allDel){
			        	var $defaultLayoutItem=$('<div class="phone-layout" id="default_layout_item"><div class="module-placeholder">亲，您还未添加任何模块，请将左侧模块拖动到这里开始装修哦~</div></div>');
			        	$('#tpl-content').append($defaultLayoutItem);
			        }
			        doJsmartdrag.jsmartdragObj.freshTarget();
			        $('#pageDecorate').mCustomScrollbar('update');
			        var successCallback2 = function(itemData){
					}
					var errorCallback2 = function(errorMsg){
						if(errorMsg){
							eDialog.alert(errorMsg);
						}else{
							eDialog.alert("更新布局项错误，请刷新页面！");
						}
					}
					templateConfigSideBarWap.updateLayoutItemBatch(param,successCallback2,errorCallback2); 
				}
			},{caption : '取消',callback : $.noop}]
		});
    });
    function debounce(func, wait, immediate) {
        var timeout;
        return function () {
            var context = this, args = arguments;
            var later = function () {
                timeout = null;
                if (!immediate) func.apply(context, args);
            };
            var callNow = immediate && !timeout;
            clearTimeout(timeout);
            timeout = setTimeout(later, wait);
            if (callNow) func.apply(context, args);
        };
    };

    
	if($("#pageDecorate").hasClass('mCustomScrollbar')){
		$('#pageDecorate').mCustomScrollbar('update');
	}else{
		var pageDecorate = $("#pageDecorate").mCustomScrollbar({
			scrollInertia: 150,
			advanced: {
				autoScrollOnFocus: false
			}
		});
	}

    $(window).on('resize.scroll', debounce(function () {
        calPageDecorateHeight();
        $('#pageDecorate').mCustomScrollbar('update');
    }, 300));


    var w = 0;
    $('#proList li').each(function () {
        w = w + $(this).width() + 10;
    });
    $('#proList .tScroll').width(w);
     var $scrollObj = $('#proList')[0];
   // new $.AMUI.iScroll($scrollObj, {
   //     scrollX: true,
   //     scrollY: false
   // });

   
});
