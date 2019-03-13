$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pCheckColumn':false,
        "sAjaxSource": GLOBAL.WEBROOT + '/msg/mgr/gridlist',
        //"params":[{name:"staffCode",value:$('#staffCode').val()},{name:"status",value:$('#status').val()}],
        //指定列数据位置
        "aoColumns": [
			{ "mData": "msgName", "sTitle":"模板描述","sWidth":"80px","bSortable":false},
			{ "mData": "insiteOnOff", "sTitle":"站内短信","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				if (data == '1') {
	                return '<div onclick="changeStyle(this,\'10\',\''+row.msgCode+'\');" class="bootstrap-switch bootstrap-switch-mini bootstrap-switch-on" style="width: 66px;"><div class="bootstrap-switch-container" style="width: 99px; margin-left: 0px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 33px;">ON</span><span class="bootstrap-switch-label" style="width: 33px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 33px;">OFF</span><input id="switch-size" type="checkbox" checked="" data-size="mini"></div></div>';
				} else {
	                return '<div onclick="changeStyle(this,\'10\',\''+row.msgCode+'\');" class="bootstrap-switch bootstrap-switch-mini bootstrap-switch-off" style="width: 66px;"><div class="bootstrap-switch-container" style="width: 99px; margin-left: -33px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 33px;">ON</span><span class="bootstrap-switch-label" style="width: 33px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 33px;">OFF</span><input id="switch-size" type="checkbox" checked="" data-size="mini"></div></div>';
				}
				
	         }},
			{ "mData": "smsOnOff", "sTitle":"手机短信","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
                if (data == '1') {
    				return '<div onclick="changeStyle(this,\'20\',\''+row.msgCode+'\');" class="bootstrap-switch bootstrap-switch-mini bootstrap-switch-on" style="width: 66px;"><div class="bootstrap-switch-container" style="width: 99px; margin-left: 0px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 33px;">ON</span><span class="bootstrap-switch-label" style="width: 33px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 33px;">OFF</span><input id="switch-size" type="checkbox" checked="" data-size="mini"></div></div>';
                } else {
    				return '<div onclick="changeStyle(this,\'20\',\''+row.msgCode+'\');" class="bootstrap-switch bootstrap-switch-mini bootstrap-switch-off" style="width: 66px;"><div class="bootstrap-switch-container" style="width: 99px; margin-left: -33px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 33px;">ON</span><span class="bootstrap-switch-label" style="width: 33px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 33px;">OFF</span><input id="switch-size" type="checkbox" checked="" data-size="mini"></div></div>';
                }
				
	         }},
			{ "mData": "emailOnOff", "sTitle":"邮件","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				if (data == '1') {
	                return '<div onclick="changeStyle(this,\'30\',\''+row.msgCode+'\');" class="bootstrap-switch bootstrap-switch-mini bootstrap-switch-on" style="width: 66px;"><div class="bootstrap-switch-container" style="width: 99px; margin-left: 0px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 33px;">ON</span><span class="bootstrap-switch-label" style="width: 33px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 33px;">OFF</span><input id="switch-size" type="checkbox" checked="" data-size="mini"></div></div>';
				} else {
	                return '<div onclick="changeStyle(this,\'30\',\''+row.msgCode+'\');" class="bootstrap-switch bootstrap-switch-mini bootstrap-switch-off" style="width: 66px;"><div class="bootstrap-switch-container" style="width: 99px; margin-left: -33px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 33px;">ON</span><span class="bootstrap-switch-label" style="width: 33px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 33px;">OFF</span><input id="switch-size" type="checkbox" checked="" data-size="mini"></div></div>';
				}
	         }},
	         { "mData": "shopId","sTitle":"操作","sWidth":"80px","sClass": "center",
					"mRender": function(data,type,row){
						return "<a href='#' onclick=\"modify('"+row.msgCode+"')\">编辑</a>";
					}
				}
        ]
	});
	
});

/**
 * 编辑模板
 * @param msgCode
 */
function modify(msgCode){
	bDialog.open({
		title : '模板编辑',
		width : 500,
		height : 550,
		url : GLOBAL.WEBROOT+'/msg/mgr/findTemplate?msgCode=' + msgCode,
		callback:function(data){
				$('#dataGridTable').gridReload();
			}
	});
}

function changeStyle(obj,type,msgCode){
	
	var flag = "1";
	if (obj.className == 'bootstrap-switch bootstrap-switch-mini bootstrap-switch-off') {
		obj.className = 'bootstrap-switch bootstrap-switch-mini bootstrap-switch-on';
	} else {
		obj.className = 'bootstrap-switch bootstrap-switch-mini bootstrap-switch-off';
		flag = "0";
	}
	if (obj.innerHTML == '<div class="bootstrap-switch-container" style="width: 99px; margin-left: -33px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 33px;">ON</span><span class="bootstrap-switch-label" style="width: 33px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 33px;">OFF</span><input id="switch-size" type="checkbox" checked="" data-size="mini"></div>') {
		obj.innerHTML = '<div class="bootstrap-switch-container" style="width: 99px; margin-left: 0px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 33px;">ON</span><span class="bootstrap-switch-label" style="width: 33px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 33px;">OFF</span><input id="switch-size" type="checkbox" checked="" data-size="mini"></div>';
	} else {
		obj.innerHTML = '<div class="bootstrap-switch-container" style="width: 99px; margin-left: -33px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 33px;">ON</span><span class="bootstrap-switch-label" style="width: 33px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 33px;">OFF</span><input id="switch-size" type="checkbox" checked="" data-size="mini"></div>';
	}
	//保存数据
	$.eAjax({
		url : GLOBAL.WEBROOT + "/msg/mgr/saveMsgSend",
		data : {'flag':flag,'msgCode':msgCode,'sendType':type},
		datatype:'json',
		success : function(returnInfo) {
			if (returnInfo.resultFlag != 'ok') {
				eDialog.alert("操作失败！");
				//样式回退
				if (obj.className == 'bootstrap-switch bootstrap-switch-mini bootstrap-switch-off') {
					obj.className = 'bootstrap-switch bootstrap-switch-mini bootstrap-switch-on';
				} else {
					obj.className = 'bootstrap-switch bootstrap-switch-mini bootstrap-switch-off';
				}
				if (obj.innerHTML == '<div class="bootstrap-switch-container" style="width: 99px; margin-left: -33px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 33px;">ON</span><span class="bootstrap-switch-label" style="width: 33px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 33px;">OFF</span><input id="switch-size" type="checkbox" checked="" data-size="mini"></div>') {
					obj.innerHTML = '<div class="bootstrap-switch-container" style="width: 99px; margin-left: 0px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 33px;">ON</span><span class="bootstrap-switch-label" style="width: 33px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 33px;">OFF</span><input id="switch-size" type="checkbox" checked="" data-size="mini"></div>';
				} else {
					obj.innerHTML = '<div class="bootstrap-switch-container" style="width: 99px; margin-left: -33px;"><span class="bootstrap-switch-handle-on bootstrap-switch-primary" style="width: 33px;">ON</span><span class="bootstrap-switch-label" style="width: 33px;">&nbsp;</span><span class="bootstrap-switch-handle-off bootstrap-switch-default" style="width: 33px;">OFF</span><input id="switch-size" type="checkbox" checked="" data-size="mini"></div>';
				}
			}
			$('#dataGridTable').gridReload();
		}
	});
}