
$(function () { 
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	
	document.getElementById("shopId").value = _param.shopId;
	document.getElementById("shopName").value = _param.shopName;
	
    //全选或全不选 
    $("#all").click(function(){    
        if(this.checked){    
            $("#list :checkbox").attr("checked", true);   
        }else{    
            $("#list :checkbox").attr("checked", false); 
        }    
     });  
    //全选   
    $("#selectAll").click(function () { 
         $("#list :checkbox,#all").attr("checked", true);   
    });   
    //全不选 
    $("#unSelect").click(function () {   
         $("#list :checkbox,#all").attr("checked", false);   
    });   
    //反选  
    $("#reverse").click(function () {  
         $("#list :checkbox").each(function () {   
              $(this).attr("checked", !$(this).attr("checked"));   
         }); 
         allchk(); 
    }); 
     
    //设置全选复选框 
    $("#list :checkbox").click(function(){ 
        if(this.checked){    
        	 $(this).attr("checked", true);   
        }else{    
        	 $(this).attr("checked", false); 
        }  
        allchk(); 
    }); 

    //获取选中选项的值 
    $("#btnSave").click(function(){ 
        var valArr = new Array; 
        $("#list :checkbox[checked]").each(function(i){ 
            valArr[i] = $(this).val(); 
        }); 
        var vals = valArr.join(','); 

//		if(!$("#detailInfoForm").valid()) return false;
//		var val = ebcForm.formParams($("#detailInfoForm"));
		eDialog.confirm("您确认要保存该店铺物流信息吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					//eDialog.alert('success','保存成功！');
					
					$.eAjax({
						url : GLOBAL.WEBROOT + "/shop/saveexpress",
						data : {'shopId':_param.shopId,'shopName':_param.shopName,'expressIds':vals},
						datatype:'json',
						success : function(returnInfo) {
							eDialog.alert('编辑成功！',function(){
								bDialog.closeCurrent();
							},'confirmation');
						}
					});
					
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
	
    });
    
    $("#btnReturn").click(function(){ 
    	//history.back();
    	bDialog.closeCurrent();
    }); 
    
});  
function allchk(){ 
    var chknum = $("#list :checkbox").size();//选项总个数 
    var chk = 0; 
    $("#list :checkbox").each(function () {   
        if($(this).attr("checked")==true){ 
            chk++; 
        } 
    }); 
    if(chknum==chk){//全选 
        $("#all").attr("checked",true); 
    }else{//不全选 
        $("#all").attr("checked",false); 
    } 
} 