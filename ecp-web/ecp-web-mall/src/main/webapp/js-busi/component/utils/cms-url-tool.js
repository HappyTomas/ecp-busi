var _eCmsUrlTool = {
		"isAbsUrl":function(url){
			if(url){
				url += "";
				var reg = /^(?:[a-z]+:(?:\/|\\){2})|(?:[a-z0-9]+(?:-[a-z0-9]+)*\.){2,}/ig;//不支持如localhost等只有单个单词当域名的绝对地址
				return reg.test(url);
			}else{
				return false;
			}
		},
		"rmSlash":function(str,isHead){
			if(str != null){
				str += "";
				var reg = null;
				if(isHead){
					reg = /^(\/|\\)+/ig;
				}else{
					reg = /(\/|\\)+$/ig;
				}
				return str.replace(reg,'');
			}else{
				return "";
			}
		},
		"getHtmlAbsUrl":function(webRoot,url){
			if(!url){
				return "javascript:void(0);";
			}else{
				url = url+"";
				if(_eCmsUrlTool.isAbsUrl(url)){
					var reg = /^(?:[a-z]+:(?:\/|\\){2})/;
					if(reg.test(url)){
						return url;
					}else{
						return "http://"+url;
					}
				}else{
					return _eCmsUrlTool.rmSlash(webRoot,false)+"/"+_eCmsUrlTool.rmSlash(url,true);
				}

			}
		}
}