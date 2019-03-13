/**
 * 双图编辑块对应js 函数
 * 16.9.6 zhanbh
 */
;$(function () {
 //编辑框方法定义---------------------------------------------//
	var columnEdit = {
		"init":function(){
			columnEdit.bindAdd();
			columnEdit.bindMenu();
		},
		"bindAdd" :function(){//添加广告按钮事件
			$(".btn-add",".focus-column-form").bind('click',function () {
				var $this = $(this);
				var $num = $this.find("em.num");
				var groupNum = +$num.text();
				var totalNum = +$this.find("span.total-num").text();
				if(totalNum && groupNum && groupNum < totalNum){
					var $lastGroup = $(".form-group:visible",$this.closest(".dynamicFormMainBox")).last();
					if(!_eCmsModMenuTool.isEmptyGroup($lastGroup,[1020])){//1020为图片  如果图片为空 则代表数据无效
						$num.html(""+(groupNum+1));
						var $newGroup = _eCmsModMenuTool.getGroup($("#focus-form-group-pro"),groupNum+1);
						if($newGroup && $newGroup.length > 0){
							$newGroup.insertAfter($lastGroup);
						}
						_eCmsModMenuTool.updateScrollbar();
					}else{
						eDialog.alert("最后一组图片未上传！");
					}
		  		}else{
		  			eDialog.alert("该模块可编辑数最多为"+totalNum+"个哦!");
		  		}
		  	});
		},
		"bindMenu":function(){
			_eCmsModMenuTool.mUp();
			_eCmsModMenuTool.mDown();
			
			var delCallback = function($obj){
				var $group = $obj.closest(".form-group");
				var $valueNo = $group.nextAll(".btn-add").find("em.num");
				$valueNo.text(parseInt($valueNo.text())-1);
			}
			_eCmsModMenuTool.mDel(delCallback);
		},
	}
//初始化--------------------
	columnEdit.init();
});