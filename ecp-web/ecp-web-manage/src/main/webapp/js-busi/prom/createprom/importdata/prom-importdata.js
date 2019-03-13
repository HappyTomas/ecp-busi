$(function(){
	
	//统计上传成功文件个数
	var successCount = 0;
    $('#fileUploaderUpload').click(function(){
    	if($('div.uploadify-queue-item',$('#attachmentFileQueue')).size()>0){
        	$(this).button('loading');//设置状态按钮的状态为处理中
        	$.gridLoading({"el":"#importdata_id", "messsage":"正在加载中...."});//增加遮罩
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
    			url : GLOBAL.WEBROOT + "/gdsprom/importdata",
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
    		    	$.gridUnLoading({"el":"#importdata_id"});//取消遮罩
    		    	$("#dataGridTableTemp").gridReload();//刷新表格
    			}
    		});
    	}else{
    		eDialog.error("上传失败！"+remsg);
        	$.gridUnLoading({"el":"#importdata_id"});//取消遮罩
    		$("#dataGridTableTemp").gridReload();
    	}

	};
	
	var uploadError = function(event, queueId, fileObj, errorObj){
		//eDialog.error("服务异常,请联系站点运维人员。");
    	$.gridUnLoading({"el":"#importdata_id"});//取消遮罩
	};
	
	//更多的参数请参考e.upload.js中的详细参数
	$("#excelFileInput").eUploadBaseInit({
		 uploadUrl : $webroot + 'gdsprom/uploadfile;' + $('#pageSessionId').val(),//后台接收文件处理的controller 
		uploadFileObjName : 'excelFile',
		fileTypeExts : ['xls','xlsx'], // 文件选择类型限制
		//'queueID' : "attachmentFileQueue",//队列内容显示元素ID指定默认ID为attachmentFileQueue
		//回调
		 callback : function(data){
    		var promId=$('#promId').val();
    		$.eAjax({
    			url : GLOBAL.WEBROOT + "/gdsprom/importdata",
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
    		    	$("#dataGridTableTemp").gridReload();//刷新表格
    			}
    		});
		}
	});
	
	//表格数据初始化
	$("#dataGridTableTemp").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/gdsprom/gridlist',
        'params' : [{name:'promId',value:$('#promId').val()}],
        //指定列数据位置
        "aoColumns": [
            { "mData": "shopName", "sTitle":"店铺","sWidth":"80px","bSortable":false},
            { "mData": "siteName", "sTitle":"站点","sWidth":"70px","bSortable":false},
            { "mData": "promId", "sTitle":"促销编码","sWidth":"70px","bSortable":false}, 
            { "mData": "mainCatgName", "sTitle":"分类","sWidth":"80px","bSortable":false},
            { "mData": "gdsId", "sTitle":"商品编码","sWidth":"60px","bSortable":false},
            { "mData": "skuId", "sTitle":"单品编码","sWidth":"60px","bSortable":false},
            { "mData": "gdsName", "sTitle":"单品名称","sWidth":"100px","bSortable":false},
            { "mData": "promCnt", "sTitle":"促销量","sWidth":"70px","bSortable":false},
            { "mData": "info", "sTitle":"操作","sWidth":"50px","bSortable":false, "mRender":function(data,type,row){
                var _html="<a href='javascript:void(0)' onclick='promImportData.del("+row.id+","+row.promId+")'>&nbsp;删除 &nbsp;</a>";
  				return _html;
  			}}
        ],
        "rowCallback": function( row, data ) {
//            if ( $.inArray(data.DT_RowId, selected) !== -1 ) {
//                $(row).addClass('selected');
//            }
        },
        "eDbClick" : function(){
        	//modifyBiz();
        }
	});
	
	//promGdsDataCommit
	$('#promGdsDataCommit').click(function(){
		$.gridLoading({"messsage":"稍候，正在导入数据..."});//安置局部遮罩
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsprom/importdata",
			datatype:'json',
			success : function(data) {
				var reflag = data.resultFlag;//ok  fail
		    	var remsg = data.resultMsg;
		    	if(reflag=="ok"){
					eDialog.success('导入成功！');
		    	}else{
		    		eDialog.error('导入失败！'+remsg); 
		    	}
		    	$.gridUnLoading();//取消遮罩
		    	$("#dataGridTableTemp").gridReload();//刷新表格
			}
		});
	});

	
});


var promImportData = {
		//删除
		del:function(id,promId){
			eDialog.confirm("您确认删除该数据吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/gdsprom/del",
							data : {'id':id,'promId':promId},
							datatype:'json',
							success : function(returnInfo) {
								eDialog.success('已删除！',{
									buttons:[{
										caption:"确定",
										callback:function(){
											$('#dataGridTableTemp').gridSearch({'pForwardPage' : 1});
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
	};