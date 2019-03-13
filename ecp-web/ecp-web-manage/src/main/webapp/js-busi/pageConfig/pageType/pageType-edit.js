$(function(){
	pageType_edit.init();
});

var pageType_edit = {
	init : function(){//初始化
		var id = $('#id').val();
		if(id){
			$('#platformType').attr('disabled','disabled');
		}else{
			$('#platformType').removeAttr('disabled');
		}
		
	    $.validator.addMethod("layoutNameFormat", function(value, element) {
             //var tel = /^[1-9]\d{1,3}$|^[1-9]\d{1,3}\-[1-9]\d{1,3}$|^[1-9]\d{1,3}\-[1-9]\d{1,3}\-[1-9]\d{1,3}$/;
             var tel = /^[1-9]\d{1,3}(\-[1-9]\d{1,3})*$/;
             return this.optional(element) || (tel.test(value));
        }, "请填写正确的格式,如:'900','200-700','200-700-300'");
		if($('#isRead').val()=='1'||$('#isPubish').val()=='1'){
		   ebcForm.setFormReadonly('#detailInfoForm');
		   ebcForm.setFormReadonly('#layoutForm');
		}
		/*if($('#isPubish').val()=='1'){
			ebcForm.setFormReadonly('#detailInfoForm');
			ebcForm.setFormReadonly('#layoutForm');
		}*/
		
		$('.layoutShowType').live('change',function(){
			pageType_edit.floatInput($(this));
		});
		//绑定选择内容位置
		$('#layoutAdd').bind('click', function(){	 
		   var item=0;
           if($('#layouts-tb tbody tr').size()>0){
             item = parseInt($('#layouts-tb tbody tr').last().attr('row').split('_')[1]) + 1;
           }
           var data = {item:item};
           var html = template('layouts-tpl',data);
           $('#layouts-tb tbody').append(html);
		});
		$('.delRow').live('click', function(){
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
		$('#pageTypeName').live('blur',function(){	
			if($(this).val()!=''){
				var params=ebcForm.formParams($("#detailInfoForm"));
				$.eAjax({
					url : $webroot + "pageType/samePageTypeName",
					data : params,
					async:false,
					success : function(returnInfo){
						if(returnInfo.resultFlag != 'ok'){
							eDialog.error($('#pageTypeName').val()+'已存在，请重新填写！',{
								onClose:function(){
									$('#pageTypeName').val('');
									$('#pageTypeName').focus();
								}
							});
							
						}
					},
					error : function(e,xhr,opt){
						eDialog.error("查询遇到异常!");
					}
				});
			}
		
		});
		//验证布局类型名称是否重复
		$('.layoutTypeName').live('blur',function(){
			var $this=$(this),b=true;
			var layoutName= $this.val();
			if(layoutName!=''){
				$('#layouts .layoutTypeName').not($this).each(function(){
					var $name=$(this);
					if((layoutName==$name.val())&&($this!=$(this))){
						eDialog.error($name.val()+'已存在，请重新填写！',{
							onClose:function(){
								$this.val('');
								$this.focus();
							}
						});
						b=false;
					}
				})
				if(b){
					var layoutItemSize=layoutName;
					if(layoutName.indexOf('-')>-1){
						layoutItemSize=layoutName.replace(/-/g,'|');
					}
					$this.parents('tr').find('.layoutItemSize').val(layoutItemSize);

				}
			}
		
		});
		//发布按钮
		$('#btnPublish').click(function(){ 
			pageType_edit.saveFrom("1");
		});
		//保存按钮
		$('#btnSave').click(function(){ 
			pageType_edit.saveFrom();
		});
		//退回
		$('#btnReturn').click(function(){ 
			window.location.href = GLOBAL.WEBROOT+'/pageType/init';
		});
		
		
	},
	floatInput:function($obj){
		/*if($('.layoutShowType').not($obj)){
			
		}*/
		var ptr=$obj.parents('tr'),
		typeName=ptr.find('.layoutTypeName'),
		itemSize=ptr.find('.layoutItemSize');
		var breturn=false;
		ptr.siblings('tr').each(function(){
			var sv=$(this).find('.layoutTypeName').val();
			if(sv!=''&& (sv==typeName.val())){
				eDialog.error("此布局仅允许一项，重新选择!");
				breturn=true;
			}
		});
		if(breturn){
			return true;
		}
		if($obj.val()=='03'){
			typeName.val('LXF');
			itemSize.val('LXF');
		}else if($obj.val()=='04'){
			typeName.val('RXF');
			itemSize.val('RXF');
		}else{
			typeName.val('');
			itemSize.val('');
		}
		
		if($obj.val()=='03' || $obj.val()=='04'){
			typeName.focus().blur();
			ptr.find('.f_error').removeClass('f_error').find('.error').hide();
			typeName.prop("readonly",true).removeClass('layoutNameFormat');
		}else{
			typeName.prop("readonly",false)
			typeName.addClass('layoutNameFormat');
		}
	
	},
	saveFrom : function(status){//保存
		var successMsg="保存成功！";
		if(!$("#detailInfoForm").valid())return false;
		if(!$("#layoutForm").valid())return false;
		var trs=$('#layouts tbody tr');
		if(trs.size()<=0){
			eDialog.error('请填写至少一项布局类型！');
			return false;
		}
		//var params= ebcForm.formParams($("#detailInfoForm"));
		//初始化成功函数  returnInfo为后台返回信息
		var params=ebcForm.formParams($("#detailInfoForm"));
		var _valStr="[";
		trs.each(function(item){
			var param=ebcForm.formParams(this);
			_valStr += "{";
		    $.each(param,function(item,map){
		    	_valStr += "'"+map.name.split('_')[0]+"':'"+map.value+"',";
		    });
			_valStr += "},";
		});
		_valStr += "]";
		params.push({name:'fieldStr',value:_valStr});
		if(status){
			successMsg="发布成功！";
			params.push({name:'status',value:status});
		}
		var doSuccess;
		if((typeof doSuccess) != "function" ){
			doSuccess = function(returnInfo){
				if(returnInfo.resultFlag == 'ok'){//发布成功
					eDialog.success(successMsg,{
						buttons:[{
							caption:"确定",
							callback:function(){
								window.location.href = GLOBAL.WEBROOT+'/pageType/init';
							}
						}]
					});
				}else{
					eDialog.error('发布出现异常，请联系管理员！');
					$.gridUnLoading();
				}
	        }
		}
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "pageType/save",
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
							window.location.href = GLOBAL.WEBROOT+'/pageType/init';
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
			url : $webroot + "pageType/publish",
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

