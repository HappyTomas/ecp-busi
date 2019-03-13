//页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {
			var shopId = $("#shopId").val();
			var shipTemplateId = $("#shipTemplateId").val();
			var shipTemplateName = $("#shipTemplateName").val();
			var shipTemplateType = $("#shipTemplateType").val();
			var ifFree = $("#ifFree").val();
			var data = new Object();
			data.shopId = shopId;
			data.shipTemplateId = shipTemplateId;
			data.shipTemplateName = shipTemplateName;
			data.shipTemplateType = shipTemplateType;
			data.ifFree = ifFree;
 			// 分页
			$('#pageControlbar').bPage({
				url : GLOBAL.WEBROOT + '/seller/gdsshiptemp/gridlist',
				asyncLoad : true,
				asyncTarget : '#shiptListDiv',
				params : function(){
					return data;
				}
			});


		            $('a[name=del]').click(function () {
		            	
		            	var tempId = $(this).attr("tempId");
		    			var shopId = $("#shopId").val();
		    			var param = {
		    					tempId : tempId,
		    					shopId : shopId
		    			};
		                eDialog.confirm("确定要删除信息吗？",{
		                    'title':"提示",
		                    'buttons':  [
		                        {caption: '确定', callback: function() { 
		                        	
		        						$.eAjax({
		        							url : GLOBAL.WEBROOT + "/seller/gdsshiptemp/deleteshiptemp",
		        							data : param,
		        							success : function(returnInfo) {
		        								
		        						
		        								if(returnInfo.resultFlag=='ok'){
		        									eDialog.success('删除成功！'); 
		        									 $('#shipSearchBtn').trigger('click');
		        								}else{
		        									eDialog.error(returnInfo.resultMsg);
		        								}
		        							}
		        						});
	
		                        }},
		                        {caption: '取消',callback : $.noop},
		                    ]
		                });
		            });


		            $('a[name=edit]').click(function () {
		            	
		            	var tempId = $(this).attr("tempId");		    		
		    			window.location.href =  GLOBAL.WEBROOT +  "/seller/gdsshiptemp/totempedit?templateId=" + tempId;
		            });
                     $('a[name=default]').click(function () {
		            	
		            	var tempId = $(this).attr("tempId");		    		
                         var shopId = $("#shopId").val();
		            	eDialog.confirm("您确认设置其为默认运费模版吗？", {
		        			buttons : [{
		        				caption : '确认',
		        				callback : function(){
		        					$.eAjax({
		        						url : GLOBAL.WEBROOT + "/seller/gdsshiptemp/setDefaultShip",
		        						data : {'shipid':tempId,'shopid':shopId},
		        						datatype:'json',
		        						success : function(returnInfo) {
		        							if(returnInfo.resultFlag=="ok"){
		        								eDialog.alert('设置成功！', function(){
		        									bDialog.closeCurrent();
		        									window.location.href = GLOBAL.WEBROOT+'/seller/gdsshiptemp';
		        								},'confirmation');
//		        								eDialog.success('设置成功！');
//		        								$('#shipdataGridTable').gridReload();
//		        								window.location.href = GLOBAL.WEBROOT+'/seller/gdsshiptemp';
		        							}else{
		        								eDialog.alert('分类运费模板无法设置为默认运费模板');
		        							}
		        							
		        						}
		        					});
		        				}
		        			},{
		        				caption : '取消',
		        				callback : $.noop
		        			}]
		        		});
                     
                     });
		            
		
		            
		};
		return {
			init : init
		};
	};
	pageConfig.config({
		// 指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : [ 'bPage' ],
		// 指定页面
		init : function() {
			var shipList = new pInit();
			shipList.init();
		}
	});
});
