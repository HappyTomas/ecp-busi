$(function(){
	prop_edit.init();
});

var prop_edit = {
	init : function(){//初始化
		
		if($('#isRead').val()=='1'||$('#isPubish').val()=='1'){
		   ebcForm.setFormReadonly('#detailInfoForm');
		   ebcForm.setFormReadonly('#propValForm');
		   $('#addProp').hide();
		}
		//验证模块名称是否重复
		$('#propName').live('blur',function(){
			if($('#isRead').val()=='1'||$('#isPubish').val()=='1'){
				
			}else{
				prop_edit.samePropName($(this));
			}
		});
		//发布按钮
		$('#btnPublish').click(function(){ 
			prop_edit.saveFrom("1");
		});
		//保存按钮
		$('#btnSave').click(function(){ 
			prop_edit.saveFrom();
		});
		if(prop_edit.isSubimitProp()){
			$(".propVal-tb-wrap").show();
		}
		$('#propValueType').change(function(){
			if(prop_edit.isSubimitProp()){
				$(".propVal-tb-wrap").show();
			}else{
				$(".propVal-tb-wrap").hide();
			}
		});
		
		$('.delRow').live('click',function(){
			var $this=$(this);
			eDialog.confirm("你确定要删除该行吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$this.parents('tr').remove();
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		});
		//添加属性
		$('#addProp').click(function(){ 
			var item=0;
			var trDom=$('#propVal-tb tbody tr');
	           if(trDom.size()>0){
	             item = parseInt(trDom.last().attr('row').split('_')[1]) + 1;
	           }
	           var data = {item:item};
	           var html = template('propVal-tpl',data);
	           $('#propVal-tb tbody').append(html);
		});
		
		//退回
		$('#btnReturn').click(function(){ 
			window.location.href = GLOBAL.WEBROOT+'/prop/init?modularId='+$('#modularId').val()+'&isValid='+$('#isValid').val();
		});
		
		
	},
	isSubimitProp:function(){
		if($('#propValueType').val()=="01" || $('#propValueType').val()=="02" || $('#propValueType').val()=="04"){
			return true;
		}
		return false;
	},
	samePropName:function($this){
		if($this.val()!=''){
			var params=ebcForm.formParams($("#detailInfoForm"));
			//$('#btnSave').attr("disabled",true);
			$.eAjax({
				url : $webroot + "prop/samePropName",
				data : params,
				async:false,
				success : function(returnInfo){
					if(returnInfo.resultFlag != 'ok'){
						eDialog.error($('#propName').val()+'已存在，请重新填写！',{
							onClose:function(){
								$('#propName').val('');
								$('#propName').focus('');
							}
						});
						
					}else{
					//	$('#btnSave').attr('disabled',false);
					}
				},
				error : function(e,xhr,opt){
					eDialog.error("查询遇到异常!");
				}
			});
		}
	},
	"initRemarkCount":function(){//初始化字数限制提醒
		$(".cms-count").each(function(){
			var $cmsTotalNum = $(this).find(".cms-totalNum");
			var $cmsText =$(this).find(".cms-text");
			var totalNum = $cmsTotalNum.text();
			totalNum?totalNum+0:0;
			var text = $cmsText.val();
			
			if(!totalNum || !text){
				return false;
			}
			if(totalNum < text.length){
				$cmsText.val(text.subString(0,totalNum-1));
				$cmsTotalNum.text(0);
			}else{
				$cmsTotalNum.text(totalNum - text.length);
			}
		});
	},
	
	saveFrom : function(status){//保存
		var successMsg="保存成功！";
		if(!$("#detailInfoForm").valid())return false;
		if(!$("#propValForm").valid())return false;
		//初始化成功函数  returnInfo为后台返回信息
		var params=ebcForm.formParams($("#detailInfoForm"));
		var _valStr="[";
		$('#propValForm tbody tr').each(function(item){
			var param=ebcForm.formParams(this);
			_valStr += "{";
		    $.each(param,function(item,map){
		    	_valStr += "'"+map.name.split('_')[0]+"':'"+map.value+"',";
		    });
			_valStr += "},";
		});
		_valStr += "]";
		if(prop_edit.isSubimitProp()){
			params.push({name:'propStr',value:_valStr});
		}else{
			params.push({name:'propStr',value:'[]'});
		}
		if(status){
			successMsg="发布成功！";
			params.push({name:'status',value:status});
		}
		var doSuccess;
		if((typeof doSuccess) != "function" ){
			doSuccess = function(returnInfo){
				if(returnInfo.resultFlag == 'ok'){//保存成功
					eDialog.success(successMsg,{
						buttons:[{
							caption:"确定",
							callback:function(){
								window.location.href = GLOBAL.WEBROOT+'/prop/init';
							}
						}]
					});
				}else{
					eDialog.error('保存出现异常，请联系管理员！');
					$.gridUnLoading();
				}
	        }
		}
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "prop/save",
			data : params,
			success : doSuccess,
			error : function(e,xhr,opt){
				eDialog.error("保存遇到异常!");
				$.gridUnLoading();
			},
			exception : function(msg){
				$.gridUnLoading();
			}
		});
	},
	"publishPage" : function(){
		//初始化成功函数  returnInfo为后台返回信息
		var doSuccess = function(returnInfo){
			if(returnInfo.resultFlag == 'ok'){//保存成功
				eDialog.success('发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							window.location.href = GLOBAL.WEBROOT+'/prop/init';
						}
					}]
				});
			}else{
				eDialog.error('发布出现异常，请联系管理员！');
				$.gridUnLoading();
			}
        }
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "prop/publish",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : doSuccess,
			error : function(e,xhr,opt){
				eDialog.error("发布遇到异常!");
			},
			exception : function(msg){
			}
		});
	}
};

