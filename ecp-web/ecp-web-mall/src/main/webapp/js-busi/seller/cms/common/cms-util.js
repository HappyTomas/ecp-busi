var SearchObj = {
		/**
		 * 获取指定from表单的参数,拼装成字符串 "name1:value1;name2:value2"
		 * zhanbh
		 * @param $from 搜索表单ID
		 */
		"getFormParam" : function($form){
			var params = "";
			var paramObj = ebcForm.formParams($form);
			//var paramStr =  JSON.stringify(paramObj);
			if( paramObj && (typeof paramObj).toLowerCase()==="object"){
				for(var item in paramObj){
					if(paramObj.hasOwnProperty(item) && paramObj[item]["value"]){
						params += ";"+paramObj[item]["name"];
						params += ":"+ paramObj[item]["value"];
					}
				}
			}
			if(params && params.length > 1){
				params = params.substr(1,params.length);
			}
			return params;
		} ,
		/**
		 * 初始化查询条件
		 * zhanbh
		 * option  {paramsId:"",formId:""}
		 * paramsId:为搜索参数隐藏域id,formId:为搜索域表单id
		 */
		"initSearchParams" : function(option){
			if(!option){
				option = {};
			}
			if(!option.formId){
				option.formId = "searchForm";//默认查询表单ID
			}
			if(!option.paramsId){
				option.paramsId = "searchParams";//默认存放查询条件字符串
			}
			var params = $("#"+option.paramsId).val();
			var paramsArray = [];
			if(params){
				paramsArray = params.split(";");
			}
			if(paramsArray && paramsArray.length > 0 ){
				for(var index in paramsArray){
					if(paramsArray.hasOwnProperty(index) && paramsArray[index]){
						var keyValue = paramsArray[index].split(":");
						if(keyValue && keyValue.length==2 && keyValue[0] && keyValue[1]){
							//站点-模板-内容位置三级联动特殊处理（带有一个“attrId”属性，用于区别为联动模块，公共模块）
							if("siteId"==keyValue[0] || "templateId"==keyValue[0] || "placeId"==keyValue[0]){
								var attrId = $("#"+keyValue[0],"#"+option.formId).attr("attrId");
								if(typeof(attrId)=="undefined"){
									$("#"+keyValue[0],"#"+option.formId).val(keyValue[1]);
								}else{
									$("#"+keyValue[0],"#"+option.formId).attr("attrId",keyValue[1]);
								}
							}else{
								$("#"+keyValue[0],"#"+option.formId).val(keyValue[1]);
							}
						}
					}
				}
			}
		},
		/**
		 * 模仿post表单提交
		 * option{url:"",params:{},method:""}
		 */
		"openPage" : function (option){
			if(option && option.url){
				//创建form元素
				if(!option.method && option.method.toLowerCase() != "post" 
						&& option.method.toLowerCase() != "get"){
					option.method = "post";
				}
				var $form = document.createElement("form");
				$form.setAttribute("style","display:none;");
				$form.setAttribute("method",option.method);
				$form.setAttribute("action",option.url);
				
				//创建input
				var paramsObj = option.params;
				if( paramsObj && (typeof paramsObj).toLowerCase()==="object"){
					for(var index in paramsObj){
						if(paramsObj.hasOwnProperty(index) && paramsObj[index]){
							var $input = document.createElement("input");
							$input.setAttribute("type","hidden");
							$input.setAttribute("name",index);
							$input.setAttribute("value",paramsObj[index]);
							$form.appendChild($input);
							$input=null;
						}
					}
				}
				
				//提交表单
				document.body.appendChild($form) ; 
				$form.submit();
				document.body.removeChild($form) ; 
			}
		}
}