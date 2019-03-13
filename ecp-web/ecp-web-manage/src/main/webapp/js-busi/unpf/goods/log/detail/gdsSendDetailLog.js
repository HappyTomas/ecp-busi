var status ="0";
var dataCont=[];
$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : true,
       // 'pCheck' : 'multi',
        "sAjaxSource": GLOBAL.WEBROOT + '/unpf/gdssendlog/detailLog/gridList',
      //指定列数据位置
        "aoColumns": [
					{ "mData": "createStaff", "sTitle":"操作人","sWidth":"80px","sClass": "center","bSortable":false},
					{ "mData": "createTime", "sTitle":"操作时间","sWidth":"80px","sClass": "center","bSortable":false,"mRender": function(data,type,row){
						data = ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
						return "<span title='"+data+"' style='cursor:default'>"+data+"</span>";
					}},
					{ "mData": "errors", "sTitle":"错误描述","sWidth":"80px","sClass": "center","bSortable":false,"mRender": function(data,type,row){
						var s = "";
						if(row.errors){
							if(row.errors.size<120){
								 s = row.errors;
							}else{
								 s = row.errors.substring(0,120)+"......";
							}
						}//
						dataCont.push(row.errors);
						return "<span class='alink'>"+s+"</span>";
						// return "<p style='margin-top:10px;height:15px;' class=' pop_over' data-content="+row.errors+"data-trigger='hover' data-original-title="+' '+" data-placement='top' >"+s+"</p><div style=‘margin-bottom: 20px;’>";
					}},
    			],
        "params" : [{name : 'platType',value : $("#platType").attr("value")},
                    {name : 'shopAuthId',value : $("#shopAuthId").attr("value")},
                    {name : 'gdsId',value : $("#gdsId").attr("value")},
                    {name : 'shopId',value : $("#shopId").attr("value")}],
        "createdRow": function ( row, data, index ) {
			$('td', row).eq(2).css('word-break',"break-all");
		 	
        },
        "eDrawComplete":function(){
        	$(".alink").each(function(i){
        		var $this=$(this);
        		/*$this.popover({
            		"content":dataCont[i],
            		"trigger":'hover',
            		"placement":'left'
            	});*/
        		$this.qtip({
        	            content:function(){return dataCont[i]} ,
        	            hide: {
        	                fixed: true,
        	                delay: 300
        	            },
        	            position: {
        	                my: 'bottom center',
        	                at: 'bottom center',
        	                },
        	            style: {
        	                classes: 'qtip-bootstrap',
        	                width: false,
        	            }
        	        });
        	});
        	dataCont=[];
        }
                    
	});
	//返回
	$('#btnReturn').click(function(){
		 history.go(-1);
	});
	
});
