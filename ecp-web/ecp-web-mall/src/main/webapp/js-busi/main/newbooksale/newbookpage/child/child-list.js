$(function(){
	var versionType = $("#versionType").val();
	versionType = versionType ? versionType :'';
    $('#pageControlbar').bPage({
        url : GLOBAL.WEBROOT + '/newbook/qrynewbookpage',
        asyncLoad : true,
        pageSizeMenu: [20,30,40],
        asyncTarget : '#new-book-content',
        params : function(){
        	return {
        		"placeWidth" : 200,
				"placeHeight" : 200,
				"versionType" : versionType
            };
        }
    });
});