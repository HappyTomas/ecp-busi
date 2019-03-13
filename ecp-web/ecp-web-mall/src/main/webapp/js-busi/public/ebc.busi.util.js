//***************
// ebc.busi.utils.js
// 常用函数工具类
// jiangzh
//***************

//说明：获取商品中单品属性
//调用：doGdsProp(obj,propName)
//返回值：商品中单品属性（例如：作者名称）
function doGdsProp(obj,propName){
	//var author = "";//作者属性
	var authorValue = "";//作者属性值
	if(!obj || !obj.skuInfo){
		return "";
	}
	var allPropMaps = obj.skuInfo.allPropMaps;
	if(allPropMaps && $.isPlainObject(allPropMaps)){
		var propObj = allPropMaps[propName];
		if(propObj){
			//author = 1001.propName;
			var authorValues = propObj.values;
			if(authorValues && authorValues.length > 0){
				return authorValue = authorValues[0].propValue;
			}
		}
	}
}

//说明：根据传入参数，截取n个字节    中文两个字节  英文一个字节
//调用：cmsDoSubString(str,n)
//返回值：n个字节长的字符后加'...' 或者长度小于n的本身
function cmsDoSubString(_arg1,n){
	if(_arg1 && n && $.type(n) == 'number'){
         if(n == 0){
        	 return '';
         }else{
        	 var str = _arg1.toString();
        	 var strLen = str.length;
        	 var blen = 0; //字节长度 
        	 var i = 0;
        	 for (i = 0; i < strLen; i++) {
        			if ((str.charCodeAt(i) & 0xff00) != 0) {
        				blen++;
        			}
        			blen++;
        			if (blen > n) {
        				return str.substring(0,i)+'...';
        			}
        		}//end of for
        	 return str;
         }
	}else{
		return '';
	}
}

//说明：加入收藏夹
//ecpaddfavorite  必须的a标签的类
//返回值：无

$(".ecpaddfavorite").live("click",function(){
	$item = $(this);
	var url = window.location;
    var title = document.title;
	try{
		if(jQuery.browser.msie) {
            window.external.addFavorite(url, title);
        } else if (jQuery.browser.mozilla || jQuery.browser.opera) {
        	$item.attr("rel", "sidebar");
        	$item.attr("title", title);
        	$item.attr("href", url);
        } else {
        	eDialog.alert("请使用Ctrl+D将本页加入收藏夹！");
        }
	}catch(e){
		eDialog.alert("您的浏览器不支持，请使用Ctrl+D将本页加入收藏夹！");
	}
});
