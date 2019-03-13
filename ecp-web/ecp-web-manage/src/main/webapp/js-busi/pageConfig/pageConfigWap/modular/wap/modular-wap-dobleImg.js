/**
 * 双图编辑块对应js 函数
 * 16.9.6 zhanbh
 */
;$(function () {
 //编辑框方法定义---------------------------------------------//
	var dobleImgEdit = {
		"init":function(){
			dobleImgEdit.bindMenu();
		},
		"bindMenu":function(){
			_eCmsModMenuTool.mUp();
			_eCmsModMenuTool.mDown();
		},
	}
//初始化--------------------
	dobleImgEdit.init();
});