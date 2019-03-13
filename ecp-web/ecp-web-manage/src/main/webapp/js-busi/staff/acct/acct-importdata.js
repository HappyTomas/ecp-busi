$(function(){
	
	//导入资金账户
    
	//单个文件上传成功后的回调处理，实现上传结果处理等信息均在此处理
    var uploadSuccess = function(fileInfo){
    	$.gridUnLoading();//取消遮罩
    	
    	var showFileInfo = $("#showFileInfo");
    	if(!fileInfo && !fileInfo.fileId){
    		eDialog.error("上传失败！");
    		$("#dataGridTableTemp").gridReload();
    		showFileInfo.html("等待上传");
    		return;
    	}
    	var fileId = fileInfo.fileId;
    	var fileName = fileInfo.fileName.split(".")[0];
    	var fileExtName = fileInfo.fileName.split(".")[1];
    	
		$.eAjax({
			url : GLOBAL.WEBROOT + "/acct/importdatatemp",
			data : {"fileId":fileId, "fileName":fileName, "fileExtName":fileExtName},
			datatype:'json',
			success : function(data) {
				var reflag = data.resultFlag;//ok  fail
		    	var remsg = data.resultMsg;
		    	if(reflag=="ok"){
		    		eDialog.success("上传成功！");
		    		showFileInfo.html("上传到：" + fileInfo.fileId);
		    	}else{
		    		eDialog.error("上传失败！"+remsg);
		    		showFileInfo.html("等待上传...");
		    	}
		    	$("#dataGridTableTemp").gridReload();//刷新表格
			}
		});
    		
	};
	
	var uploadError = function(event, queueId, fileObj, errorObj){
		//eDialog.error("服务异常,请联系站点运维人员。");
	}
	
	//更多的参数请参考e.upload.js中的详细参数
	$("#acctExcelFile").eUploadBaseInit({
		fileTypeExts: ['xls','xlsx'],
		callback: uploadSuccess
	});
	
	//表格数据初始化
	$("#dataGridTableTemp").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/acct/temp/gridlist',
        //指定列数据位置
        "aoColumns": [
            { "mData": "shopId", "sTitle":"店铺编码","sWidth":"80px","bSortable":false},
            { "mData": "acctType", "sTitle":"资金类型","sWidth":"70px","bSortable":false},    
            { "mData": "staffCode", "sTitle":"登录工号","sWidth":"80px","bSortable":false},    
            { "mData": "tradeMoney", "sTitle":"账户增加金额(元)","sWidth":"80px","bSortable":false},         
			{ "mData": "isGood", "sTitle":"是否合法","sWidth":"50px","bSortable":false,"mRender": function(data,type,row){
				var isGood = "";
				if(data==1){
					isGood = "是";
				}else{
					isGood = "<font style='color:red'>否</font>";
				}
				return isGood;
			}},
			{ "mData": "recordDesc", "sTitle":"数据说明","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
				return data;
			}},
			{ "mData": "isCommit", "sTitle":"是否提交","sWidth":"50px","bSortable":false,"mRender": function(data,type,row){
				var isCommit = "";
				if(data==1){
					isCommit = "<font style='color:green'>是</font>";
				}else{
					isCommit = "否";
				}
				return isCommit;
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
	
	//acctDataCommit
	$('#acctDataCommit').click(function(){
		$.gridLoading({"messsage":"稍候，正在导入数据..."});//安置局部遮罩
		$.eAjax({
			url : GLOBAL.WEBROOT + "/acct/importdata",
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