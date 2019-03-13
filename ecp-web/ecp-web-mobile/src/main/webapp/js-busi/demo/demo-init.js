/**
 * 
 */
$(function(){
	$('#btnAlert').click(function(e) {
		/*
		$('#my-alert').off('closed.modal.amui').on('closed.modal.amui', function(){
			alert();
		});
		$('#my-alert').modal({
			onConfirm : function(){
				alert();
			}
		});
		*/
		eDialog.alert('asdfasdfasfasdfasdf', function() {
			alert(11);
		});
	});
	$('#btnConfirm').click(function(e) {
		eDialog.confirm('bbbbbbbb fafdasdf', function(){
			alert(222);
		})
	});
});