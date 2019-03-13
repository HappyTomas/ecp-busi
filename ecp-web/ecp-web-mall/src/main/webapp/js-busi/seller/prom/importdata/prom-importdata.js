$(function() {

	//页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			//统计上传成功文件个数
			var successCount = 0;
		    $('#fileUploaderUpload').click(function(){
		    	if($('div.uploadify-queue-item',$('#attachmentFileQueue')).size()>0){
		        	$(this).button('loading');//设置状态按钮的状态为处理中
		        	//$.gridLoading({"el":"#importdata_id", "messsage":"正在加载中...."});//增加遮罩
		        	successCount = 0;
		        	$("#excelFileInput").eUpload();
		    	}else{
		    		eDialog.warning("请选择导入文件!");
		    	}
		    });
			
			//单个文件上传成功后的回调处理，实现上传结果处理等信息均在此处理
		    //最终结果可在onQueueComplete中统一显示
		    var uploadSuccess = function(file,data,response){
		    	data = $.parseJSON(data);
		    	var reflag = data.resultFlag;
		    	var remsg = data.resultMsg;
		    	if(reflag=="ok"){
		    		var promId=$('#promId').val();
		    		$.eAjax({
		    			url : GLOBAL.WEBROOT + "/seller/gdsprom/importdata",
		    			data : {"fileId":data.fileId, "fileName":data.fileName, "fileExtName":data.fileExtName,"promId":promId},
		    			datatype:'json',
		    			success : function(data) {
		    				var reflag = data.resultFlag;//ok  fail
		    		    	var remsg = data.resultMsg;
		    		    	if(reflag=="ok"){
		    		    		eDialog.success("上传成功！"+remsg);
		    		    	}else{
		    		    		eDialog.error("上传失败！"+remsg);
		    		    	}
		    		    //	$.gridUnLoading({"el":"#importdata_id"});//取消遮罩
		    		    //	$("#dataGridTableTemp").gridReload();//刷新表格
		    		    	$('#divDataGridTableTemp').load(GLOBAL.WEBROOT + "/seller/gdsprom/importGdsList?promTypeCode="+$('#promTypeCode').val(),{"promId":$('#promId').val()});
		    			}
		    		});
		    	}else{
		    		eDialog.error("上传失败！"+remsg);
		        	//$.gridUnLoading({"el":"#importdata_id"});//取消遮罩
		    		//$("#dataGridTableTemp").gridReload();
		    		$('#divDataGridTableTemp').load(GLOBAL.WEBROOT + "/seller/gdsprom/importGdsList?promTypeCode="+$('#promTypeCode').val(),{"promId":$('#promId').val()});
		    	}

			};
			
			var uploadError = function(event, queueId, fileObj, errorObj){
				//eDialog.error("服务异常,请联系站点运维人员。");
		    //	$.gridUnLoading({"el":"#importdata_id"});//取消遮罩
			};
			
			//更多的参数请参考e.upload.js中的详细参数
			$("#excelFileInput").eUploadBaseInit({
				 uploadUrl :  $webroot + 'seller/gdsprom/uploadfile;' + $('#pageSessionId').val(),//后台接收文件处理的controller 
				uploadFileObjName : 'excelFile',
				fileTypeExts : ['xls','xlsx'], // 文件选择类型限制
				//'queueID' : "attachmentFileQueue",//队列内容显示元素ID指定默认ID为attachmentFileQueue
				//回调
				 callback : function(data){
			    		var promId=$('#promId').val();
			    		$.eAjax({
			    			url : GLOBAL.WEBROOT + "/seller/gdsprom/importdata",
			    			data : {"fileId":data.fileId, "fileName":data.fileName, "fileExtName":data.fileExtName,"promId":promId},
			    			datatype:'json',
			    			success : function(data) {
			    				var reflag = data.resultFlag;//ok  fail
			    		    	var remsg = data.resultMsg;
			    		    	if(reflag=="ok"){
			    		    		eDialog.success("上传成功！"+remsg);
			    		    	}else{
			    		    		eDialog.error("上传失败！"+remsg);
			    		    	}
			    		    	$('#divDataGridTableTemp').load(GLOBAL.WEBROOT + "/seller/gdsprom/importGdsList?promTypeCode="+$('#promTypeCode').val(),{"promId":$('#promId').val()});
			    			}
			    		});
			    	}
			});
			
			//promGdsDataCommit
			$('#promGdsDataCommit').click(function(){
				//$.gridLoading({"messsage":"稍候，正在导入数据..."});//安置局部遮罩
				$.eAjax({
					url : GLOBAL.WEBROOT + "/seller/gdsprom/importdata",
					datatype:'json',
					success : function(data) {
						var reflag = data.resultFlag;//ok  fail
				    	var remsg = data.resultMsg;
				    	if(reflag=="ok"){
							eDialog.success('导入成功！');
				    	}else{
				    		eDialog.error('导入失败！'+remsg); 
				    	}
				    //	$.gridUnLoading();//取消遮罩
				    	//$("#dataGridTableTemp").gridReload();//刷新表格
				    	//window.location,href= GLOBAL.WEBROOT + "/seller/gdsprom/importGds;"//?promId=" + id;
					$('#divDataGridTableTemp').load(GLOBAL.WEBROOT + "/seller/gdsprom/importGdsList?promTypeCode="+$('#promTypeCode').val(),{"promId":$('#promId').val()});
					}
				});
			});
			

			
			//关闭
			$('#btnReturn').click(function() {
				bDialog.closeCurrent();
			});

			//初始化列表
			$('#divDataGridTableTemp').load(GLOBAL.WEBROOT + "/seller/gdsprom/importGdsList?promTypeCode="+$('#promTypeCode').val(),{"promId":$('#promId').val()});
		};

		return {
			init : init
		};
	};
	pageConfig.config({
		//指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm','eUpload' ],
		//指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});
});
//删除
function del(id,promId){
	eDialog.confirm("您确认删除该数据吗？", {
		buttons : [{
			caption : '确认',
			callback : function(){
				$.eAjax({
					url : GLOBAL.WEBROOT + "/seller/gdsprom/del?id="+id+'&promId='+promId,
					//data : {'id':id,'promId':promId},
					datatype:'json',
					success : function(returnInfo) {
						eDialog.success('已删除！',{
							buttons:[{
								caption:"确定",
								callback:function(){
									$('#divDataGridTableTemp').load(GLOBAL.WEBROOT + "/seller/gdsprom/importGdsList?promTypeCode="+$('#promTypeCode').val(),{"promId":$('#promId').val()});
						        }
							}]
						}); 
					}
				});
			}
		},{
			caption : '取消',
			callback : $.noop
		}]
	});
}