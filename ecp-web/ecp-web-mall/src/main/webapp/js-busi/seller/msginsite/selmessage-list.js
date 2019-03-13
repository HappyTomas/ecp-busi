$(function(){
    var pInit = function(){
    	var init = function(){
    		
	    	//载入"未读"页面
	    	$('#dataBody').load(GLOBAL.WEBROOT + '/seller/msginsite/unread?v='+Math.random());
	    	$('#unread').css("color","red");
	    	//绑定查询按钮事件
	    	$('#all').unbind('click');
	    	$('#all').click(function(){
	    		
	    		//加载指定页面
	    		$('#dataBody').load(GLOBAL.WEBROOT + '/seller/msginsite/msglist?v='+Math.random(),function(){
	    			$('#all').css("color","red");//添加字体为红色样式
		    		//移除查看未读和查看所有的增加的样式
		    		$('#unread').css("color","");
		    		$('#read').css("color","");
	    		});
	    	
	    	});
	    	//绑定查询按钮事件
	    	$('#unread').unbind('click');
	    	$('#unread').click(function(){
	    		$("#unread").css("color","red");//添加字体为红色样式
	    		//移除
	    		$('#read').css("color","");
	    		$('#all').css("color","");
	    		//加载指定页面
	    		$('#dataBody').load(GLOBAL.WEBROOT + '/seller/msginsite/unread?v='+Math.random());
	    	});
	    	//绑定查询按钮事件
	    	$('#read').unbind('click');
	    	$('#read').click(function(){
	    		$("#read").css("color","red");//添加字体为红色样式
	    		$('#all').css("color","");
	    		$('#unread').css("color","");
	    		//加载指定页面
	    		$('#dataBody').load(GLOBAL.WEBROOT + '/seller/msginsite/readed?v='+Math.random());
	    	});
	    	
	    	//全选、取消全选的事件
	    	$("#SelectAll").click(function() {
	    	    $("input[name='sub']").prop("checked", this.checked);
	    	});
	    	//子复选框的事件,当没有选中某个子复选框时，SelectAll取消选中
	    	$("input[name='sub']").click(function() {
				var $subs = $("input[name='sub']");
				$("#SelectAll").prop("checked" , $subs.length == $subs.filter(":checked").length ? true:false);
			});
	    	
		};
    	return {
    		init : init
    	};
	};    	
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bPage','bForm'],
		//指定页面
		init : function(){
			var scoreList = new pInit();
			scoreList.init();
		}
	});
});

//选中复选框批量删除
function batchdel(){
	var _idsArr=new Array();
	$("input[name='sub']:checkbox").each(function(){ 
		if($(this).attr("checked")){
			_idsArr.push($(this).val());
		}
	});
	if(_idsArr && _idsArr.length>=1){
		
		var _data=_idsArr.join("#");
		eDialog.confirm("您确认要批量删除吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					$.eAjax({
						url : GLOBAL.WEBROOT + "/seller/msginsite/batchdel",
						data : {'ids':_data},
						datatype:'json',
						success : function(returnInfo) {
							eDialog.success('删除成功！',{
								buttons:[{
									caption:"确定",
									callback:function(){
										//通过判断class找到加载页面
										if($("#all").hasClass("intro")){
											$('#dataBody').load(GLOBAL.WEBROOT + '/seller/msginsite/msglist?v='+Math.random());
										}
										else if($("#unread").hasClass("intro")){
											$('#dataBody').load(GLOBAL.WEBROOT + '/seller/msginsite/unread?v='+Math.random());
										}
										else if($("#read").hasClass("intro")){
											$('#dataBody').load(GLOBAL.WEBROOT + '/seller/msginsite/readed?v='+Math.random());
										}
										else{
											$('#dataBody').load(GLOBAL.WEBROOT + '/seller/msginsite/msglist?v='+Math.random());
										}
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
		
	}else if(!_idsArr || _idsArr.length==0){
		eDialog.alert('请至少选择一个消息进行删除操作！');
	}
};
//选中复选框批量标记已读
function markread(){
	var _idsArr=new Array();
	$("input[name='sub']:checkbox").each(function(){ 
		if($(this).attr("checked")){
			_idsArr.push($(this).val());
		}
	});
	if(_idsArr && _idsArr.length>=1){
		
		var _data=_idsArr.join("#");
		eDialog.confirm("您确认要标记为已读吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					$.eAjax({
						url : GLOBAL.WEBROOT + "/seller/msginsite/markread",
						data : {'ids':_data},
						datatype:'json',
						success : function(returnInfo) {
							eDialog.success('标记成功！',{
								buttons:[{
									caption:"确定",
									callback:function(){
										if($("#all").hasClass("intro")){
											$('#dataBody').load(GLOBAL.WEBROOT + '/seller/msginsite/msglist?v='+Math.random());
										}
										else if($("#unread").hasClass("intro")){
											$('#dataBody').load(GLOBAL.WEBROOT + '/seller/msginsite/unread?v='+Math.random());
										}
										else if($("#read").hasClass("intro")){
											$('#dataBody').load(GLOBAL.WEBROOT + '/seller/msginsite/readed?v='+Math.random());
										}
										else{
											$('#dataBody').load(GLOBAL.WEBROOT + '/seller/msginsite/msglist?v='+Math.random());
										}
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
		
	}else if(!_idsArr || _idsArr.length==0){
		eDialog.alert('请至少选择一个消息进行操作！');
	}
};
