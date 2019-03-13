$(function(){
    $('#pageControlbar').bPage({
        url : GLOBAL.WEBROOT + '/gdsdetail/querygdseval',
        pageSize : 10,
        asyncLoad : true,
        pageSizeMenu: [10,15,20],
        asyncTarget : '#evaluationList',
        params : function(){
        	return {
                gdsId : $("#gdsId").val()
            };
        }
    });
});