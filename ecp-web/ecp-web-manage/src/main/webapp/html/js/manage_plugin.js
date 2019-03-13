$(function(){
	$('#btnOpenWindow').click(function(){
		bDialog.open({
			title : '弹出窗口',
			width : 400,
			height : 550,
			url : 'manage_modify.html',
			callback:function(data){}
		});
	});
	$('#btnAlert').click(function(){
		eDialog.info("这是一个消息提示框！");
	});
	$('#btnStatus').click(function(){
		var btn = $(this);
		btn.button('loading');//设置按钮为处理状态，并为中读，不允许点击
		setTimeout(function () {
			btn.button('reset');
		}, 3000);
	});
	$('.btns_state').find('.btn').click(function(e) {
		$this_btn = $(this);
		$this = $this_btn.closest('.btns_state');
		if(!$this_btn.hasClass('active')) {
			$this.find('.btn_txt').html($this_btn.text()+" 激活");
		} else {
			$this.find('.btn_txt').html($this_btn.text()+" 空闲");
		}
		e.preventDefault();
	});
});