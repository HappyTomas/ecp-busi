
$(function(){
	/**
     * Created by zqr on 2015/8/19.
     */
    var IM = window.IM || (window.IM = {});

    IM.base64encode=function(str){
    	var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    	var out, i, len;
        var c1, c2, c3;
        len = str.length;
        i = 0;
        out = "";
        while(i < len) {
        c1 = str.charCodeAt(i++) & 0xff;
        if(i == len)
        {
           out += base64EncodeChars.charAt(c1 >> 2);
           out += base64EncodeChars.charAt((c1 & 0x3) << 4);
           out += "==";
            break;
         }
        c2 = str.charCodeAt(i++);
        if(i == len)
       {
          out += base64EncodeChars.charAt(c1 >> 2);
          out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
          out += base64EncodeChars.charAt((c2 & 0xF) << 2);
          out += "=";
           break;
       }
           c3 = str.charCodeAt(i++);
	       out += base64EncodeChars.charAt(c1 >> 2);
	       out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
	       out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >>6));
	       out += base64EncodeChars.charAt(c3 & 0x3F);
       }
       return out;
    },
    IM.imUrl=function(infoStr){
    	return GLOBAL.WEBROOT+"/cust/chat/"+IM.base64encode(infoStr);
    }
    
    $('.imUrl').each(function(){
    	$(this).attr("href",IM.imUrl($('.imInfo',$(this)).html()));
    })
})