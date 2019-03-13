$(function(){
    $('#pageControlbar').bPage({
        url : GLOBAL.WEBROOT + '/gdsinfoentry/gridmedialist?shopId='+$("#hiddenShopId").val(),
        pageSize : $("#pageSize").val(),
        asyncLoad : true,
        pageSizeMenu: [10,15,20],
        asyncTarget : '#mediaList',
        params : function(){
        	return {
                shopId : $("#hiddenShopId").val(),
                mediaName : $("#mediaName").val(),
                picCatgCode : 1,
                mediaType : 1
            };
        }
    });
    $(".dbchoose").each(function(){
        $(this).dblclick(function(){
        var $dbthis = $(this);
        $(".imgcont").each(function(){
            var $this = $(this);
            if($this.attr('style') != undefined){
                $this.children('img').attr('src',$dbthis.children('img').attr('src'));
                $this.parent().find("input[name='mainPicVfsId']").val($dbthis.children('img').attr('mediaId'));
                $this.parent().find("input[name='mainPicVfsId']").attr('mediaRtype','1');
                $this.parent().find("input[name='mainPicVfsId']").attr('mediaType','1');
                $this.parent().find("input[name='mainPicVfsId']").attr('mediaId',$dbthis.children('img').attr('id'));
                $this.removeAttr("style");
            }
        });
        });
    });
});