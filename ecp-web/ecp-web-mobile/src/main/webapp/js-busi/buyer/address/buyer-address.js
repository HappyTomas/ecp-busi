
$(function(){
	//新增地址
	$("#addNewAddress").click(function(){
    	window.location.href = GLOBAL.WEBROOT + "/custaddr/buyeraddrnew";
    });
	//编辑地址
	$(".editAddress").click(function(e){
		var $this = $(this);
    	window.location.href = GLOBAL.WEBROOT + "/custaddr/editaddr?id="+$this.attr("addrId")+"&staffId="+$this.attr("staffId");
    	e.preventDefault();
    });
	//删除地址
	$(".delAddress").click(function(e){
		var $this = $(this);
		eDialog.confirm("确定要删除吗？",function() {
			buyeredit.deladdr($this.attr("addrId"),$this.attr("staffId"));
			e.preventDefault();
		});
    });
	//设置为默认地址
	$(".setDefaultAddress").click(function(e){
		var $this = $(this);
		buyeredit.setaddr($this.attr("addrId"),$this.attr("staffId"));
		e.preventDefault();
    });
	$('#cancle').click(function(){
		history.back();
	});
});	

var buyeredit = {
		setaddr:function(addrid,staffid){
			$.eAjax({
				url : GLOBAL.WEBROOT + "/custaddr/setaddr",
				data : {'id':addrid,'staffId':staffid},
				datatype:'json',
				success : function(returnInfo) {
					if(returnInfo.resultFlag=="ok"){
						window.location.href = GLOBAL.WEBROOT+'/custaddr/index';
					}else{
						new AmLoad({content:'设置为默认收货地址失败',type:'2'});
					}
					
				}
			});
		},
		deladdr:function(addrid,staffid){
			$.eAjax({
				url : GLOBAL.WEBROOT + "/custaddr/deladdr",
				data : {'id':addrid,'staffId':staffid},
				datatype:'json',
				success : function(returnInfo) {
					if(returnInfo.resultFlag=="ok"){
						window.location.href = GLOBAL.WEBROOT+'/custaddr/index';
					}else{
						new AmLoad({content:'删除收货地址失败',type:'2'});
					}
					
				}
			});
		},
		editaddr:function(addrid,staffid){
			$('#addrid').val(addrid);
			$('#staffid').val(staffid);
			$('#editAddrFrom').submit();
		}
};