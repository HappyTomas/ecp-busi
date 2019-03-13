$(function(){
    $('#pageControlbar').bPage({
        url : GLOBAL.WEBROOT + '/homepage/qryrankinglist',
        asyncLoad : true,
        pageSizeMenu: [8,16,24],
        asyncTarget : '#ranking-pagebar',
        params : function(){
        	return {
                placeId : $("#placeId").val(),
                tabId : $("#tabId").val()
            };
        }
    });
});