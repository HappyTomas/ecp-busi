$(function(){
	var url =  GLOBAL.WEBROOT + '/staffadmin/addadmin';

	$('#btnFormSave').click(function(){ 
		if(!$("#detailInfoForm").valid())return false;
		
		//用户组多选框
		var custom = $("#custom-headers").multiSelect();
		//角色组多选框
		var role = $("#role-headers").multiSelect();
	
		//赋值
		$("#staffRole").val(role.val());
		$("#staffGroup").val(custom.val());
		$.eAjax({
			url : url,
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				eDialog.success(returnInfo.resultMsg,{
					buttons:[{
						caption:"确定",
						callback:function(){
							window.location.href = 'grid';
				        }
					}]
				}); 
				//刷新缓存
			}
		});
		
	});
	$('#btnReturn').click(function(){
		history.back();
	});
	
	
	
	//multi-select  部分
	gebo_multiselect = {
		init: function(){
			
			if($('#custom-headers').length) {
                //* custom headers
                $('#custom-headers').multiSelect({
                    selectableHeader: "<div class='custom-header'>可选用户组</div>",
                    selectionHeader: "<div class='custom-header'>已选用户组</div>"
                });
            }
            
            if($('#role-headers').length) {
                //* custom headers
                $('#role-headers').multiSelect({
                    selectableHeader: "<div class='custom-header'>可选角色</div>",
                    selectionHeader: "<div class='custom-header'>已选角色</div>"
                });
            }
		}
	};
	
	gebo_multiselect.init();
});