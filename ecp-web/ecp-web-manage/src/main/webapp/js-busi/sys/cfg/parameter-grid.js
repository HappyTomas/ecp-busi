$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean': false,
        'pCheckColumn' : true, 
      // 'pCheck' : 'multi',
        "sAjaxSource": GLOBAL.WEBROOT + '/parameter/gridlist',
        'params' : [{name:'paraCode',value:$('#paraCode').val()},{name:'paraValue',value:$('#paraValue').val()},{name:'paraDesc',value:$('#paraDesc').val()}],
        //指定列数据位置
        "aoColumns": [
           // { "mData": "paraCode", "sTitle":"id","bVisible":false,"sWidth":"80px","bSortable":true},
			{ "mData": "paraCode", "sTitle":"参数名","sWidth":"80px","bSortable":false},
			{ "mData": "paraValue", "sTitle":"参数值","sWidth":"120px","bSortable":false},
			{ "mData": "paraDesc", "sTitle":"参数描述","sWidth":"120px","bSortable":false},
			{ "mData": "createStaff", "sTitle":"创建人","sWidth":"80px","bSortable":false,"bVisible":false},
			{ "mData": "createTime", "sTitle":"创建时间","sWidth":"80px","bVisible":false,"bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "updateStaff", "sTitle":"更改人","sWidth":"80px","bSortable":false,"bVisible":false},
			{ "mData": "updateTime", "sTitle":"更改时间","sWidth":"80px","bSortable":false,"bVisible":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "info", "sTitle":"操作","sWidth":"100px","bSortable":false, "mRender":function(data,type,row){
				
                var _html="<a href='javascript:void(0)' onclick=parameter.detail('"+ row.paraCode +"')>&nbsp;查看 &nbsp;</a>";
                    _html=_html+"| <a href='javascript:void(0)' onclick=parameter.edit('"+ row.paraCode +"')>&nbsp;编辑 &nbsp;</a>";
               
  				return _html;
  			}}
			
        ],
        "eDbClick" : function(){
        	var paraCode= $('#dataGridTable').getSelectedData();
        	parameter.detail(paraCode[0].paraCode);
        }
	});
	
	var modifyBiz = function(){
		var paraCode= $('#dataGridTable').getCheckIds();
		window.location.href = GLOBAL.WEBROOT+'/parameter/view/'+paraCode;
		
		/*var _ids = $('#dataGridTable').getCheckIds();
		if(_ids && _ids.length==1){
			window.location.href = GLOBAL.WEBROOT+'/parameter/view/'+paraCode;
		}else if(_ids && _ids.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!_ids || _ids.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}*/
	};
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	$('#btn_code_add').click(function(){
		eNav.setSubPageText("新增系统参数");
		window.location.href = GLOBAL.WEBROOT+'/parameter/add';
	});
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	$('#btn_code_del').click(function(){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			eDialog.confirm("您确定要删除这条数据吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						eDialog.alert('success','删除成功！');
						
						$.eAjax({
							url : GLOBAL.WEBROOT + "/parameter/remove",
							data : {'paraCode':val[0].paraCode},
							datatype:'json',
							success : function(returnInfo) {
								eDialog.success('删除成功！',{
									buttons:[{
										caption:"确定",
										callback:function(){
											$('#dataGridTable').gridReload();
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
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	});
});
var parameter = {
		//详情
		detail:function(Code){
			eNav.setSubPageText("查看系统参数");
			var path = GLOBAL.WEBROOT+'/parameter/view/'+Code;
			$("#searchForm").attr("action",path);
			$("#searchForm").submit();
			//window.location.href = GLOBAL.WEBROOT+'/parameter/view/'+paraCode;
		},
		//编辑
		edit:function(Code){
			eNav.setSubPageText("编辑系统参数");
			var path = GLOBAL.WEBROOT+'/parameter/edit/'+Code;
			$("#searchForm").attr("action",path);
			$("#searchForm").submit();
			//window.location.href = GLOBAL.WEBROOT+'/parameter/edit/'+paraCode;
		}
	};