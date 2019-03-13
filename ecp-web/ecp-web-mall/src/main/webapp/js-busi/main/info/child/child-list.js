$(function(){
    $('#pageControlbar').bPage({
        url : GLOBAL.WEBROOT + '/info/qryinfolist',
        asyncLoad : true,
        pageSizeMenu: [20,30,40],
        asyncTarget : '#infoList',
        params : function(){
        	return {
                placeId : $("#placeId").val()
            };
        }
    });
});