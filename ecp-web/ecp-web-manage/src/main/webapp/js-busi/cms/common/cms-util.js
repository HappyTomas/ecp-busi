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
					if(paramObj.hasOwnProperty(item) && null != paramObj[item]["value"]){
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
						var value =paramsObj[index];
						if(paramsObj.hasOwnProperty(index) && value === value && ((typeof value).toLowerCase()==="number" || (typeof value).toLowerCase()==="string")){
							var $input = document.createElement("input");
							$input.setAttribute("type","hidden");
							$input.setAttribute("name",index);
							$input.setAttribute("value",value);
							$form.appendChild($input);
							$input=null;
						}
						value = null;
					}
				}
				
				//提交表单
				document.body.appendChild($form) ; 
				$form.submit();
				document.body.removeChild($form) ; 
			}
		},
		HTMLEncode:function(str,isJs){
			 if((typeof str).toLowerCase() === "number" && str === str){
				 return str;
			 }
			 if (!str) return ""; 
			 str += "";
			  str = str.replace(/&/g, "&amp;");  
			  str = str.replace(/</g, "&lt;");  
			  str = str.replace(/>/g, "&gt;");  
			  str = str.replace(/ /g, "&nbsp;");  
			  if(isJs){
				  str = str.replace(/\'/g, "\\&#39;");  
				  str = str.replace(/\"/g, "\\&quot;"); 
			  }else{
				  str = str.replace(/\'/g, "&#39;");  
				  str = str.replace(/\"/g, "&quot;");
			  }
			  str = str.replace(/\n/g, "<br>");  
			  return str;
			  
		}
}

/**
 * 用于多选的函数对象 //只适用于弹出框
 */
var batchAddObj = {
		/**
		 * 从后台获取已选择的数据
		 * params {
					url:$webroot + "floorgds/getselectedgds",//请求获取已选择数据的地址
					param:{
						floorId : floorId || "",
						tabId : tabId || ""
					},//请求的参数
					text:"正在加载已选择商品....",//当在加载已选择的记录时遮罩显示的文本
					target:[gds_batch_add,"selectedGds"]//用于存储返回的数据，格式为",34,34,34,"
			}
		 * 必须把数据放在返回值的resultMsg中
		 * 
		 */
		"getSelectedItem":function(params){
			var $gridLoading = $.gridLoading || $.noop;
			var $gridUnLoading = $.gridUnLoading || $.noop;
			
			if(params){
				$gridLoading({"message":params.text||"正在加载已选择的记录...."});
				$.eAjax({
					url : params.url,
					data : params.param,
					success : function(returnInfo) {
						if(returnInfo && params.target && params.target[0]&& params.target[1]){
							params.target[0][params.target[1]] = returnInfo.resultMsg || "";
						}
						
						$gridUnLoading();
					},
					error : function(e,xhr,opt){
						$gridUnLoading();
					},
					exception : function(msg){
						$gridUnLoading();
					}
				});
			}
			return this;
		},
		/**
		 * 初始化checkbox的状态 selectedItem 的会无法编辑  
		 * @param selectedItem  格式为",1,3,4,"
		 * @param selectingItem 对象：格式为{"12":{},"13":{},"14":{}}
		 * @returns {___anonymous_batchAddObj}
		 * checkbox 必须是batch-add-item类 <input type='checkbox' class='batch-add-item' data-id='12' data-gds-name='sdf' data-is-prom='0'>"
		 * data-id 作为标识符  selectingItem的属性名
		 */
		"initCheckBox":function(selectedItem,selectingItem){
			if(bDialog && bDialog.getDialogParams && bDialog.getDialog){
				var params = bDialog.getDialogParams(bDialog.getDialog());
				if(params){
					params[selectedItem] = params[selectedItem] || "";
					params[selectingItem] = params[selectingItem] || {};
					
					if(selectedItem){
						var selected = params[selectedItem];
					}
					if(selectingItem){
						var selecting = params[selectingItem];
					}
					
					var $items = $(".batch-add-item");
					$items.each(function(i,el){
						var id = $(el).attr("data-id")+"";
						if(id && selecting && id in selecting){
							$(el).attr("checked","checked");
						}
						if(id &&  selected && (","+selected+",").indexOf(","+id +",") >=0){
							$(el).attr("checked","checked");
							$(el).attr("disabled","disabled");
							$(el).removeClass("batch-add-item");
						}
					});
				}
			}
			return this;
		},
		/**
		 * 绑定checkbox点击事件
		 * 
		 * @param $obj checkbox的对象数组
		 * @param selectingItem  弹出框的参数属性名  用来放数据
		 * @returns {___anonymous_batchAddObj}
		 * data-id 作为标识符 所有data开头属性作为属性值，为一个对象{"12":{},"13":{},"14":{}}
		 */
		"bindItem":function($obj,selectingItem){
			if(bDialog && bDialog.getDialogParams && bDialog.getDialog){
				var params = bDialog.getDialogParams(bDialog.getDialog());
			}
			if(params && selectingItem){
				var selecting = params[selectingItem];
			}
			if(selecting && $obj){
				$($obj).live("click",function(){
					var id = $(this).attr("data-id")+"";
					if(id && $(this).is(':checked')){
						var itemDt = $(this).data();
						for(var name in itemDt){
							if(itemDt[name] == undefined){
								itemDt[name] = "";
							}
						}
						selecting[id] = $(this).data();
					}else{
						delete selecting[id];
					}
				});
			}
			
			return this;
		},
		/**
		 * 获取弹出框指定属性的参数值
		 * @param prop
		 * @returns
		 */
		"getParam":function(prop){
			if(bDialog && bDialog.getDialogParams && bDialog.getDialog){
				var params = bDialog.getDialogParams(bDialog.getDialog());
			}
			
			if(!prop && prop !== 0){
				return params;
			}else{
				prop += "";
				if(params){
					return params[prop];
				}else{
					return null;
				}
			}
		},
		/**
		 * 将数据按表格形式显示出来
		 * {//表格显示数据的配置参数
		"$contain":$("#selecting-table"),
		"dataMap":[{title:"优惠券编码",data:"id"},{title:"优惠券名称",data:"coupName",width:"150"},{title:"优惠券类型",data:"coupTypeName",width:"150"},{title:"面额（元）",data:"coupValue",width:"30"},{title:"排序",data:"sortNo",width:"50",events:[{name:"change",event:batchAddEvent.changeValue}]},{title:"操作",data:"delete",events:[{name:"click",event:batchAddEvent.deleteRow}]}],
		"data":{obj:coupon_batch_add,prop:"selectingCp"}
		}
		 */
		"showDtInTle":function(params){
			if(params){
				var $contain = params["$contain"];
				var dataMap = params["dataMap"];
				if($contain && dataMap && dataMap.length > 0){
					var $table = $("<table class='table'></table>");
					var $thead = $("<thead ></thead>");
					var $tbody = $("<tbody ></tbody>");
					$table.append($thead).append($tbody);
					$($contain).empty().append($table);
					
					//拼装thead
					var $tr = $("<tr></tr>");
					$thead.append($tr);
					$.each(dataMap, function(i,value) {
						var td = "<td ";
						if(+(value.width) > 0 ){
							td += "style='width:"+value.width+"px'";
						}
						td += ">"+value.title+"</td>"
						$tr.append($(td));
					});
					$tr = null;
					//拼装tbody
					var dataInfo = params["data"];//数据存放的对象及对应属性的配置信息
					if(!dataInfo){
						$tbody.append("<tr><td colspan='"+dataMap.length+"'>暂未选定数据存放的地方，请联系管理员!</td></tr>");
						return this;
					}
					var data = dataInfo["obj"] && (dataInfo["prop"]!=null) && dataInfo["obj"][dataInfo["prop"]];
					if(data && Object.getOwnPropertyNames(data).length > 0){
						$.each(data, function(name,dValue) {
							var $tr = $("<tr></tr>");
							$tbody.append($tr);
							$.each(dataMap, function(i,value) {
								var $div = $("<div></div")
								$tr.append($("<td></td>").append($div));
								var $tleItem = $("#batch-tle-item-"+value.data).children();
								if($tleItem.length > 0){
									var $tleItemClone = $tleItem.clone(true);
									if(dValue[value.data] != null){
										$tleItemClone.val(dValue[value.data]);
									}
									$tleItemClone.attr("data-id",name);
									
									if(value.events && value.events.length > 0){
										$.each(value.events, function(i,e){
											if(e.name && (typeof e.event) == "function")
											$tleItemClone.bind(e.name,e.event);
										});
									}
									$div.append($tleItemClone);
								}else{
									$div.append(SearchObj.HTMLEncode(dValue[value.data]));
								}
							});
						});
					}else{
						$tbody.append("<tr><td colspan='"+dataMap.length+"'>暂未选择商品</td></tr>");
					}
				}
			}
		}
}