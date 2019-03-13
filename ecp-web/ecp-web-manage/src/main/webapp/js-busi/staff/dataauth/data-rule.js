$(function(){
	/*TODO------------一般事件：start------------*/
	//切换子系统
	$('select[name="sysCode"]').on("change", function(){
		//变换菜单树
		var self = $(this);
		var treeObj = _getZTreeObj();
		treeObj.setting.async.otherParam = {"sysCode":self.val()};
		treeObj.reAsyncChildNodes(null, "refresh", true);
	});
	
	
	//配置明细   结合“规则来源”
	$("#btnFormAuthConfigSubmit").on("click", function (event) {//配置明细事件
		//得到当前数据功能点
		
		//配置
		var _self = $(this);
		var _val = _self.attr("value");
		if(_val==1){
			var ifhandle = handleBiz(function(data){
				var gridRow = data[0];
				if(gridRow.authSrc==1){//行规则
					_refreshDataRuleData(gridRow.id);//刷新明细表格
					$("#dataAuthDiv").fadeOut("fast",function(){
						$("#dataRuleDiv").fadeIn("fast");
					});
					$("#ruleOjbectTableTips").show();//tips
				}else if(gridRow.authSrc==2){//列规则
					_refreshFieldRuleData(gridRow.id);//刷新过滤规则明细表格
					$("#dataAuthDiv").fadeOut("fast",function(){
						$("#fieldRuleDiv").fadeIn("fast");
					});
				}
			});
			if(!ifhandle) return;
			$(this).html('<i class="icon-repeat"></i>确认保存');
			_self.attr("value", "2");//状态变更
			
		}else if(_val==2){
			var ifhandle = handleBiz(function(data){
				var gridRow = data[0];
				if(gridRow.authSrc==1){//行规则
					_saveDataRuleAjax(gridRow);//保存并刷新明细表格
					$("#dataRuleDiv").fadeOut("fast",function(){
						$("#dataAuthDiv").fadeIn("fast");
					});
					$("#ruleOjbectTableTips").hide();//tips
				}else if(gridRow.authSrc==2){//列规则
					_saveDataFieldRuleAjax(gridRow);//保存并刷新过滤规则明细表格
					$("#fieldRuleDiv").fadeOut("fast",function(){
						$("#dataAuthDiv").fadeIn("fast");
					});
				}
			});
			if(!ifhandle) return;
			$(this).html('<i class="icon-edit"></i>配置明细');
			_self.attr("value", "1");//状态变更
		}
		
	});
	
	//规则来源快速查询
	$("select[name='authSrc']").change(function(){
		$("#btnFormAuthSearchSubmit").click();
	});
	
	/*TODO------------一般事件：end------------*/
	
	
	
	
	
	
	
	
	
	/*TODO-------------规则权限:start-----------------*/
	
	//表格数据初始化
		$("#dataAuthGridTable").initDT({
			'pSingleCheckClean' : true,
			"pPageBar" : false,
	        'pTableTools' : false,
	        'pCheckColumn' : true,
	        'params' : [{"name":"listAll","value":1},{"name":"funcId","value":-1}],
	        "sAjaxSource": GLOBAL.WEBROOT + '/dataauth/dataauth/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	            { "mData": "name", "sTitle":"规则名称","sWidth":"80px","bSortable":false},
	            { "mData": "authCode", "sTitle":"规则编码","sWidth":"80px","bSortable":false},    
	            { "mData": "authType", "sTitle":"规则类型","sWidth":"40px","bSortable":false,"mRender": function(data,type,row){
					var res = "";
	            	if(data==1){
						res = "排他";
					}else if(data==0){
						res = "包含";
					}else{
						res = "";
					}
	            	return res;
				}},    
	            { "mData": "enabled", "sTitle":"是否启用","sWidth":"40px","bSortable":false,"mRender": function(data,type,row){
	            	var res = "";
	            	if(data==1){
						res = "是";
					}else if(data==0){
						res = "否";
					}else{
						res = "";
					}
	            	return res;
				}},    
				{ "mData": "authDesc", "sTitle":"规则描述","sWidth":"80px","bSortable":false,"bVisible":true,"mRender": function(data,type,row){
					return data;
				}},
				{ "mData": "authSrc", "sTitle":"规则来源","sWidth":"40px","bSortable":false,"bVisible":true,"mRender": function(data,type,row){
					if(data == 1){
						return "行规则";
					} else if(data == 2){
						return "列规则";
					} else {
						return "未定义";
					}
				}}
	        ],
	        "eDbClick" : function(){
	        },
	        "eClick" : function(row,dom){
	        	if($("#dataAuthGridTable").getSelectedData()){//选中行
	        		$("#id").val(row.id);
//	        		$("#name").val(row.name);
//	        		$("#authCode").val(row.authCode);
	        		
	        		//权限类型
	        		$("input[name='authType'][value="+row.authType+"]").attr("checked",true); 
	        		//是否启用
	        		$("#enabled").attr("value", row.enabled);
	        		$("#enabledCB").attr("checked",row.enabled==1);
	        	}else{//取消选中;“保存”时,新增规则项目
	        		$("#id").val("");
//	        		$("#name").val("");
//	        		$("#authCode").val("");
	        	}
	        },
	        "onSuccess" : function(){
	        }
		});
	
	//handleBiz
	var handleBiz = function(callback){
		var val = $('#dataAuthGridTable').getSelectedData();
		if(val && val.length==1){
			if(callback){
				callback(val);
			}
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
			return false;
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
			return false;
		}
		return true;
	};
	
	//刷新表格dataAuthGridTable
	function _refreshDataAuthGridTable(args){
		//1.验证功能点
		var selectedNode = _getOnlySelectedNode();
		if(!selectedNode){
			return;
		}
		var params = [];
		params.push({"name":"funcId","value":selectedNode.id||-1});
		params.push({"name":"name","value":''});
		params.push({"name":"authCode","value":''});
		params.push({"name":"authSrc","value":args.results==null?"1":args.results[0].authSrc});
		if(args && args.results && args.results[0].authSrc){//设置“规则来源”
			$("select[name='authSrc']").val(args.results[0].authSrc);
		}
		if($('#dataAuthGridTable')[0]) $('#dataAuthGridTable').gridSearch(params);
	}
	
	//是否启用事件
	$("#enabledCB").on("click",function(){
		if(this.checked){
			$("#enabled").val("1");
		}else{
			$("#enabled").val("0");
		}
	});
	
	//数据规则新增提交
	$('#btnFormAuthAddSubmit').click(function(){
		if($('#btnFormAuthConfigSubmit').val()=='2'){
			eDialog.warning('请先“确认保存”！'); 
			return;
		}
		//1.验证功能点
		var selectedNode = _getOnlySelectedNode();
		if(!selectedNode){
			return;
		}
		var status = selectedNode.status;//所属功能状态
		
		var funcId = selectedNode.id;//所属数据功能
		
		var sysCode = $("#sysCode").val();//所属子系统
		
		bDialog.open({
		    title : '新增规则',
		    width : 400,
		    height : 480,
		    url : GLOBAL.WEBROOT + '/dataauth/dataauth/add/open',
		    scroll : false,
		    params : {
		        'status' : status,
		        'funcId' : funcId,
		        'sysCode' : sysCode
		    },
		    callback:function(data){
		        _refreshDataAuthGridTable(data);//刷新表格
		    }
		});
	});
	//数据规则修改提交
	$('#btnFormAuthUpdateSubmit').click(function(){
		if($('#btnFormAuthConfigSubmit').val()=='2'){
			eDialog.warning('请先“确认保存”！'); 
			return;
		}
		//1.验证功能点
		var selectedNode = _getOnlySelectedNode();
		if(!selectedNode){
			return;
		}
		//2.表格数据验证
		var gridlist = $('#dataAuthGridTable').getSelectedData();
		if(!(gridlist&&gridlist.length==1)){
			eDialog.warning("请选择一个规则权限！");
			return false;
		}
		
		var row = gridlist[0];
		
		var status = selectedNode.status;//所属功能状态
		
		var funcId = selectedNode.id;//所属数据功能
		
		var sysCode = $("#sysCode").val();//所属子系统
		
		var id = $("#id").val();//规则id
		
		bDialog.open({
		    title : '修改规则',
		    width : 400,
		    height : 480,
		    url : GLOBAL.WEBROOT + '/dataauth/dataauth/update/open',
		    scroll : false,
		    params : {
		    	'status' : status,
		        'funcId' : funcId,
		        'sysCode' : sysCode,
		        'id' : id,
		        'name' : row.name,
		        'authCode' : row.authCode,
		        'authType' : row.authType,
		        'enabled' : row.enabled,
		        'authSrc' : row.authSrc,
		        'authDesc' : row.authDesc
		    },
		    callback:function(data){
		    	_refreshDataAuthGridTable(data);//刷新表格
		    }
		});
	});
	//数据规则查询提交
	$('#btnFormAuthSearchSubmit').click(function(){
		//刷新规则配置页面内容
		var btnFormAuthConfigSubmit = $("#btnFormAuthConfigSubmit");
		if(btnFormAuthConfigSubmit[0]&&btnFormAuthConfigSubmit.attr("value")==2){
			btnFormAuthConfigSubmit.html('<i class="icon-edit"></i>配置明细');
			if($("#dataRuleDiv").css("display")=="block"){
				$("#dataRuleDiv").fadeOut("fast",function(){
					$("#dataAuthDiv").fadeIn("fast");
				});
				$("#ruleOjbectTableTips").hide();//tips
			}
			if($("#fieldRuleDiv").css("display")=="block"){
				$("#fieldRuleDiv").fadeOut("fast",function(){
					$("#dataAuthDiv").fadeIn("fast");
				});
			}
			btnFormAuthConfigSubmit.attr("value", "1");//状态变更
			
		}
		//查询
		var node = _getOnlySelectedNode();
		
		var searchParams = [{"name":"funcId","value":node.id||-1}];
		searchParams.push({"name":"name", "value":$("#name").val()});
		searchParams.push({"name":"authCode", "value":$("#authCode").val()});
		searchParams.push({"name":"authSrc", "value":$("#authSrc").val()});
		
		$('#dataAuthGridTable').gridSearch(searchParams);
		
	});
	
	//删除
	//TODO 功能[删除规则权限]待定，代码暂置
	/*$('#btnFormDeleteSubmit').click(function(){
		handleBiz(function(val){
			eDialog.confirm("是否确定删除？", {
				buttons : [{
					caption : '删除',
					callback : function(){
						var rowData = $('#dataGridTable').getSelectedData()[0];
						$.eAjax({
							url : GLOBAL.WEBROOT + "/acct/type/delete",
							data : {'acctType':rowData.acctType,'adaptType':rowData.adaptType,'shopId':rowData.shopId},
							datatype:'json',
							success : function(returnInfo) {
								eDialog.success('删除成功！'); 
								$('#btnFormSearch').click();//刷新列表
							}
						});
					}
				},{
					caption : '返回',
					callback : function(){
						bDialog.closeCurrent();
					}
				}]
			});
		});
	});*/
	
	/*TODO-------------规则权限:end-----------------*/
	
	
	
	
	
	
	
	
	
	
	/*TODO-------------规则明细:start------------*/
	
	//添加规则明细
	$("#dataRuleTableBody").delegate("tr:last","click",function(){
		var tr = createTR();
		$("#dataRuleTableBody tr:last").before(tr);
	});
	//删除规则明细
	$("#dataRuleTableBody").delegate(".icon-minus-sign","click",function(){
		var self = this;
		var dataRuleId = $(this).next("input")[0].value;//规则id
		eDialog.confirm("是否确定删除？", {
			buttons : [{
				caption : '删除',
				callback : function(){
					if(!dataRuleId) {//后来的直接删除
						$(self).parentsUntil("tr").parent().remove();
						return;
					}
					$.eAjax({//已有的请求服务删除
						url : GLOBAL.WEBROOT + "/dataauth/datarule/delete",
						data : {'id':dataRuleId},
						datatype:'json',
						success : function(data) {
							var reflag = data.resultFlag;//ok  fail
					    	var remsg = data.resultMsg;
					    	if(reflag=="ok"){
					    		$(self).parentsUntil("tr").parent().remove();
					    	}else{
					    		eDialog.error('删除失败！'+remsg); 
					    	}
						}
					});
				}
			},{
				caption : '返回',
				callback : function(){
					bDialog.closeCurrent();
				}
			}]
		});
		
		
	});
	//绑定单元格编辑
	$("#dataRuleTableBody").delegate("td","click",tdclick);
	
	
	//生成表格行
	function createTR(data){
		var tr = $("<tr>");
		tr.append(createTD(data==undefined?"":data.id,"<td style='display:none'>"));//1
		tr.append(createTD(data==undefined?"":data.itemId,"<td style='display:none'>"));//2
		tr.append(createTD(data==undefined?"":data.itemName,"<td tagtype='select' relatedtd='2'>"));//3
		tr.append(createTD(data==undefined?"":data.frontChar));//4
		tr.append(createTD(data==undefined?"":data.operChar));
		tr.append(createTD(data==undefined?"":data.inputValue, "<td dynamic='dynamic'>"));
		tr.append(createTD(data==undefined?"":data.logicChar));
		tr.append(createTD(data==undefined?"":data.postChar));
		if(data){
			tr.append(createTD("<li class='icon-minus-sign'></li><input type='hidden' value='"+data.id+"'>","<td edittype='uneditable'>", false));
		}
		else{
			tr.append(createTD("<li class='icon-minus-sign'></li><input type='hidden'>","<td edittype='uneditable'>", false));
		}
		return tr;
	}
	/**
	 * 生成单元格
	 * @param data td呈现的内容
	 * @param diy 自定义td
	 * @param ifTitle 是否设置title true:是，false:否，缺省为“true”
	 * @param TODO colname td映射数据模型数据属性名
	 */
	function createTD(data, diy, ifTitle){
		var td = $('<td>');
		if(diy){
			td = $(diy);
		}
		if(data){
			td.html(data);
		}
		//td title
		if(ifTitle==undefined||ifTitle==true) td.attr("title","点击进行编辑");
		return td;
	}
	
	/*
	 * 生成select控件
	 * data
	 * {
	 * 	list: list,
	 * 	val: val,[option value]
	 * 	text: text,[option text]
	 * 	selected: selected,
	 * }
	 */
	function createSelect(data){
		//1.建立一个下拉框，也就是select的元素节点
    	var select = $("<select>");
    	//a2.设置select内容,并初始化
    	var ops = data.list;
    	if(ops&&ops.length>0){
    		$.each(ops, function(i, n){
    			var op = $("<option>");
    			op.val(n[data.val]);
    			op.text(n[data.text]);
    			select.append(op);
    		});
    		if(data.selected){
//    			select.find("option[text='"+data.selected+"']").attr("selected",true);
    			select.children().filter(function(index){
    				return $(this).text() == data.selected;
    			}).eq(0).attr("selected",true);
    		}else{//缺省选中第一项
    			select.find("option:first").attr("selected", true);
    		}
    	}
 
    	return select;
	}
	
	/*
	 * 生成 multiselect控件
	 * data
	 * {
	 * 	list: list,
	 * 	val: val,[option value]
	 * 	text: text,[option text]
	 * 	selected: selected,
	 * }
	 */
	function createMultiSelect(data){
		//1.建立一个多选下拉框，也就是multiselect的元素节点
    	var select = $("<select multiple='multiple'>");
    	//a2.设置select内容,并初始化
    	var ops = data.list;
    	if(ops&&ops.length>0){
    		$.each(ops, function(i, n){
    			var op = $("<option>");
    			op.val(n[data.val]);
    			op.text(n[data.text]);
    			select.append(op);
    		});
    	}
    	return select;
	}
	
	/**
	 * 创建一个bootstrapt multi select
	 */
	function createBootstraptMSelect(data){
		
		//1.建立一个多选下拉框，也就是multiselect的元素节点
    	var select = $('<select class="selectpicker" multiple data-actions-box="true"></select>');
//    	var select = $('#createBootstraptMSelect');
    	select.show();
    	//a2.设置select内容,并初始化
    	var ops = data.list;
    	var optData = [];
    	if(ops&&ops.length>0){
    		$.each(ops, function(i, n){
    			optData.push({label:n[data.text],value:n[data.val]});
    		});
    	}
    	select.multiselect('dataprovider', optData);
    	
    	return select;
	}
	
	/**
	 * 创建一个easyui multi select
	 */
	function createEeasyUIMultiSelect(data){
		
		//1.建立一个多选下拉框，也就是multiselect的元素节点
    	var select = $("<input multiple='multiple'>");
    	//a2.设置select内容,并初始化
    	var ops = data.list;
    	var optData = [];
    	if(ops&&ops.length>0){
    		$.each(ops, function(i, n){
    			optData.push({label:n[data.text],value:n[data.val]});
    		});
    	}
    	
    	return select;
	}
	
	//单元格编辑动作
	function tdclick() {
        var td = $(this);
        var edittype = td.attr("edittype");//编辑状态
        var tagtype = td.attr("tagtype");//控件类型
        var dynamic = td.attr("dynamic");//动态控件
        var relatedtdSeq = td.attr("relatedtd");//关联td的序号、事件结果受者(同一行)
        var relatedtd;
        var tagdom;
        var tagObj;
        if(edittype=="uneditable"){//不可编辑
        	return;
        }
        if(!!relatedtdSeq){
        	relatedtd = td.parent().eq(0).children().eq(relatedtdSeq-1);
        }
        //得到规则项目id
    	var td2Id = td.parent().eq(0).children().eq(1).html();//得到规则项目id
    	var dataItemList = $.parseJSON($('#dataItemList').val());
    	$.each(dataItemList, function(i,n){
    		if(n.id==td2Id) {
    			if(dynamic=="dynamic"){
    				tagtype = n.viewType;
    			}
    			tagObj = n;
    			return false;
    		}
    	});
        //1,取出当前td中的文本内容保存起来  
        var text = td.text().trim();
        var tdwidth = td.css("width");
        var tdheight = td.css("height");
        //2,清空td里面的内容  
        td.html(""); //也可以用td.empty();  
        //3,创建dom[viewtype]
        var _initList = [];//UI初始化值
        if(!!tagtype){//select
        	if(tagObj!=undefined&&tagObj.initValue){
        		_initList = $.map(tagObj.initValue.split(','),function(n){return {"id":n,"name":n}});
        	}
        	var controlArg = {
        			"type":tagtype,
        			"init":{
        				"list":!!relatedtdSeq?$.parseJSON($('#dataItemList').val()):_initList,
        				"val":'id',
        				"text":'name',
        				"selected":text,
        				"mess":text
        			},
        			"container": td,
        			"default":tagObj!=undefined&&tagObj.hasOwnProperty('default')?tagObj['default']:'',
        	};
        	if(relatedtd){
        		controlArg.receptor = relatedtd;
        	}
        	tagdom = buildFormControl(controlArg);
        	
        }else{//input
	        //b1.建立一个文本框，也就是input的元素节点  
	        var input = $("<input type='text'></input>");
	        //b2.设置文本框的值是保存起来的文本内容  
	        input.attr("value", text);
	        input.attr("style", "width: 80%;");
	        tagdom = input;
        }
        //4.将文本框加入到td中  
        td.append(tagdom);
        //4.1******
        if(tagdom.attr('multiple')=='multiple'){
        	tagdom.combobox({
				data:_initList,
   	 			valueField:'id',   
   	 			textField:'name',
   	 			width:tdwidth,
   	 			panelHeight:'auto',
   	 			separator:',',
   	 			multiple: true,
   	 			onHidePanel: function(){
   	 				var val = tagdom.combobox('getValues');
   	 				var text = tagdom.combobox('getText');
   	 				td.html(text);
   	 			}
			});
        	if($.trim(text).length>0){
        		var t = text.split(",");
        		tagdom.combobox('setValues', t);
        	}
        }
        //5.绑定事件
        td.children().on("click",function(e){
        	var myEvent = e || window.event;
            var kcode = myEvent.keyCode;
            myEvent.stopPropagation();
        });
        tagdom.click(function(event){
        	var myEvent = event || window.event;
            var kcode = myEvent.keyCode;
            myEvent.stopPropagation();
        });
    	tagdom.keyup(function(event) {
            var myEvent = event || window.event;
            var kcode = myEvent.keyCode;
            if (kcode == 13) {
                var tagnode = $(this);
                var tagvalue = tagnode.val();
                var tdNode = tagnode.parent();
                var isSelect = tagnode.is('select');
                if(isSelect){
                	tdNode.html(tagnode.find('option:selected').text());
                }else{
                	tdNode.html(tagvalue);
                }
            }
        });
    	tagdom.blur(function() {
            var tagnode = $(this);
            
            if(tagnode.attr('kind')=='datepicker'){
    			return false;
    		}
            if(tagnode.attr('multiple')=='multiple'){
            	return false;
            }
            
            var tagvalue = tagnode.val();
            var tdNode = tagnode.parent();
            var isSelect = tagnode.is('select');
            if(isSelect){
            	tdNode.html(tagnode.find('option:selected').text());
            }else{
            	tdNode.html(tagvalue);
            }
        });
    	
        //6.获得焦点
        tagdom.focus();
        
    }
	
	/**
	 * 由类型动态创建表单控件
	 * controlArg.type 控件类型
	 * controlArg.init 控件缺省值
	 * controlArg.default 控件缺省值
	 * controlArg.receptor 控件事件受体 jquery对象
	 * controlArg.container 父容器
	 * 
	 * controlArg.init.list
	 * controlArg.init.val
	 * controlArg.init.text
	 * controlArg.init.selected
	 * controlArg.init.mess //文本框，数值框初值
	 */
	function buildFormControl(controlArg){
		var formControl;
		switch(controlArg.type){
			case "select":
		    	var select = createSelect({
		    		'list':controlArg.init.list,
		    		'val':controlArg.init.val,
		    		'text':controlArg.init.text,
		    		'selected':controlArg.init.selected
		    		});
		    	select.blur(function(event){
		    		var self = $(this);
		    		if(controlArg.receptor){
		    			controlArg.receptor.html(self.val());
		    		}
		    	});
		    	select.attr("style", "width: 100%;");
		    	formControl = select;
		    	break;
			case "multiselect":
				var multiselect = createEeasyUIMultiSelect({
					'list':controlArg.init.list,
		    		'val':controlArg.init.val,
		    		'text':controlArg.init.text,
		    		'selected':controlArg.init.selected
		    		});
				/*
				multiselect.blur(function(event){
		    		var self = $(this);
		    		if(controlArg.receptor){
		    			controlArg.receptor.html(self.val());
		    		}
		    	});
		    	*/
				multiselect.attr("style", "width: 100%;");
		    	formControl = multiselect;
		    	break;
			case "datepicker":
				var datepicker = $("<input type='text' kind='datepicker'></input>");
				datepicker.attr("value", controlArg.init.mess);
				datepicker.change(function(e){
					controlArg.container.html(datepicker.val());
				});
				datepicker.focus(function(){WdatePicker({
					isShowClear:false,
					readOnly:true,
					dateFmt:'yyyy-MM-dd HH:mm:ss',
					onpicked:function(){
						controlArg.container.html(datepicker.val());
					},
					oncleared: function(){
						controlArg.container.html(datepicker.val());
					}
						})});
				datepicker.attr("style", "width: 80%;");
				formControl = datepicker;
				break;
			case "numberinput":
		        var numberinput = $("<input type='text'></input>");
		        numberinput.attr("value", controlArg.init.mess);
		        numberinput.attr("style", "width: 80%;");
		        formControl = numberinput;
		        break;
			case "gdscatgtree":
				var gdscatgtree = $("<input type='text'></input>");
				gdscatgtree.attr("value", controlArg.init.mess);
				gdscatgtree.attr("style", "width: 80%;");
				gdscatgtree.focus(function(e){
					bDialog.open({
			            title : '分类选择',
			            width : 350,
			            height : 550,
			            params:{multi : true, showRoot : false},
			            url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=1&catlogId=1",//catlogId=1(1：管理平台，2：积分 )
			            callback:function(data){
			            	if(data && data.results && data.results.length > 0 ){
			                    var _catgs = data.results[0].catgs;
								var size = _catgs.length;
								var codes = '';
								if(size>0) codes = $.map(_catgs, function(n){
									return n.catgCode;
								}).join(',');
								controlArg.container.html(codes);
							}
			            }
			        });
				});
				formControl = gdscatgtree;
				break;
			case "checkbox":
			case "password":
			case "textinput":
		        var textinput = $("<input type='text'></input>");
		        textinput.attr("value", controlArg.init.mess);
		        textinput.attr("style", "width: 80%;");
		        formControl = textinput;
		        break;
			default: ;
		}
		
		return formControl;
	}
	
	//提取表格的值,JSON格式
	function getRuleTableData(table, prop) {
        var tableData = new Array();
        var propArr= ["id", "itemId", "itemName", "frontChar", "operChar", "inputValue", "logicChar", "postChar","delete"];
        if($("#dataRuleTableHead").find("tr th").length!=propArr.length){
        	eDialog.error('getRuleTableData,取值异常:属性不一致！'); 
        	return tableData;
        }
        if(table.find("tr").length<2){
        	return tableData;
        }
        table.find("tr").each(function(index, domEle){
        	if(index==(table.find("tr").length-1)) return false;
        	var rowData = getRuleTableRowData($(domEle),index,propArr,prop)
        	if(rowData&&rowData.length>0){
        		Array.prototype.push.apply(tableData, rowData);
        	}
        });

        return tableData;

    }
	//提取指定行的数据，JSON格式
	function getRuleTableRowData(row,rowindex,propArr,prop) {
	    var rowData = [];
	    var active = true;
	    row.children("td").each(function(index, domEle){
	    	if(index==(propArr.length-1)) return false;
	    	var bean = {};
	    	bean.name = prop+'['+rowindex+'].'+propArr[index];
	    	if($(domEle).html().indexOf('<input')!=-1||$(domEle).html().indexOf('<div')!=-1){
	    		var el = $("'"+$(domEle).html()+"'");
	    		var v = el.val()||el.text();
	    		var a = el.val()|el.text();
	    		bean.value = "";
	    	}else{
//	    		bean.value = $(domEle).html().trim();
	    		bean.value = $(domEle).text().trim();
	    	}
	    	if(index==1&&bean.value==''){//当itemId 不存在，为无效行
	    		active = false;
	    		return false;
	    	}
	    	rowData.push(bean);
	    });
	    if(!active) return false;
	    return rowData;

	}
	
	//保存规则明细
	function _saveDataRuleAjax(obj){
		var _val = [{"name":"authId","value":obj.id}];//DataAuth
		var _url = GLOBAL.WEBROOT + "/dataauth/datarule/save";
		//规则配置
		var ruleArr = getRuleTableData($("#dataRuleTableBody"),'ruleArr');
		if(ruleArr && ruleArr.length==0){//没有新增数据，直接返回
			return;
		}
		Array.prototype.push.apply(_val, ruleArr);
		$.eAjax({
			url : _url,
			data : _val,
			datatype:'json',
			success : function(data) {
				var reflag = data.resultFlag;//ok  fail
		    	var remsg = data.resultMsg;
		    	if(reflag=="ok"){
		    		eDialog.success('规则明细保存成功！'); 
		    	}else{
		    		eDialog.error('规则明细保存异常！'+remsg); 
		    	}
				
			}
		});
	}
	//刷新规则明细配置区域
	function _refreshDataRuleData(authId){
		var _val = {
				"authId" : authId
		};
		var _url = GLOBAL.WEBROOT + "/dataauth/datarule/list";
		$.eAjax({
			url : _url,
			data : _val,
			datatype:'json',
			success : function(data) {
	    		_refreshDataRuleTable(data);
			}
		});
	}
	
	//刷新数据规则明细表格
	function _refreshDataRuleTable(data){
		//table 
		var ruleTable = $("#dataRuleTableBody tr");
		var rowLast = $("#dataRuleTableBody tr:last");
		var rowCount = ruleTable.length-1;
		//clear
		ruleTable.each(function(index, row){
			if(index==rowCount) return false;
			$(row).remove();
		});
		//fill
		if(data&&data.length>0){
			$.each(data, function(i, n){
				rowLast.before(createTR(n));
			});
		}
	}
	
	/*TODO-------------规则明细:end------------*/
	
	
	
	
	
	
	
	
	
	/*TODO-------------规则明细-列:start------------*/
	$("#fieldPickAll").click(function(e){//全选事件
		$("#fieldRuleTableBody .fieldCheckbox").each(function(i,n){
			$(this).attr('checked', true);
		});
	});
	$("#fieldPickOther").click(function(e){//反选事件
		$("#fieldRuleTableBody .fieldCheckbox").each(function(i,n){
			var checked = $(this).prop('checked');
			$(this).attr('checked', !checked);
		});
	});
	
	/**
	 * 创建结果属性表单元格
	 */
	function createFieldRuleTD(data, config){
		var td = $('<td>');
		if(config && config.css){
			td.css(config.css);
		}
		td.html(data==null?"":data);
		return td;
	}
	
	/**
	 * 创建结果属性表表格行
	 */
	function createFieldRuleTR(data){
		var tr = $('<tr>');
		
		tr.append(createFieldRuleTD(data==undefined?"":data.id, {css: {display: "none"}}));//明细id
		tr.append(createFieldRuleTD(data==undefined?"":data.itemId, {css: {display: "none"}}));//属性id
		
		var bx = $('<input type="checkbox" class="fieldCheckbox">'); 
		if(data.id){//初始化选中
			var bx = $('<input type="checkbox" class="fieldCheckbox" checked >'); //兼容IE
		}
		tr.append(createFieldRuleTD(bx, {css: {"text-align": "center"}}));//选择
		tr.append(createFieldRuleTD(data==undefined?"":data.itemAttrName));//属性名称
		tr.append(createFieldRuleTD(data==undefined?"":data.itemValueType));//数据类型
		
		var valueFormate = data==undefined?"":data.itemValueFormate||"";
		var fm = $('<input type="text" style="width:90%" value="'+valueFormate+'" >');
		tr.append(createFieldRuleTD(fm));//显示格式
		
		var defalueValue = data==undefined?"":data.itemDefaultValue||"";
		var dv = $('<input type="text" style="width:90%" value="'+defalueValue+'" >');
		tr.append(createFieldRuleTD(dv));//默认值
		
		return tr;
	}
	
	//刷新过滤规则明细（列规则）配置区域
	function _refreshFieldRuleData(authId){
		//1.验证功能点
		var selectedNode = _getOnlySelectedNode();
		if(!selectedNode){
			return;
		}
		
		var _funcId = selectedNode.id;
		var _val = {
				"authId" : authId,
				"funcId" : _funcId
		};
		var _url = GLOBAL.WEBROOT + "/dataauth/fieldrule/list";
		$.eAjax({
			url : _url,
			data : _val,
			datatype:'json',
			success : function(data) {
	    		_refreshFieldRuleTable(data);
			}
		});
	}
	
	//刷新过滤规则明细（列规则）明细表格
	function _refreshFieldRuleTable(data){
		//table 
		var ruleTable = $("#fieldRuleTableBody tr");
		var ruleTBody = $("#fieldRuleTableBody");
		//clear
		ruleTable.each(function(index, row){
			$(row).remove();
		});
		//fill
		if(data&&Object.getOwnPropertyNames(data).length>0){
			$.each(data, function(i, n){
				ruleTBody.append(createFieldRuleTR(n));
			});
		}else{
			//没有返回
			ruleTBody.append('<tr> <td colspan="5">  <label class="text-center">没有任何数据返回</label> </td> </tr>');
		}
	}
	
	//保存过滤规则明细
	function _saveDataFieldRuleAjax(obj){
		var _val = [{"name":"authId","value":obj.id}];//DataAuth
		var _url = GLOBAL.WEBROOT + "/dataauth/fieldrule/save";
		//规则配置
		var ruleArr = getFieldRuleTableData();
		Array.prototype.push.apply(_val, ruleArr);
		$.eAjax({
			url : _url,
			data : _val,
			datatype:'json',
			success : function(data) {
				var reflag = data.resultFlag;//ok  fail
		    	var remsg = data.resultMsg;
		    	if(reflag=="ok"){
		    		eDialog.success('规则明细保存成功！'); 
		    	}else{
		    		eDialog.error('规则明细保存异常！'+remsg); 
		    	}
				
			}
		});
	}
	
	/**
	 * 得到表格中选中的记录
	 */
	function getFieldRuleTableData(){
		var tableData = [];//返回结果
		var ruleTBody = $("#fieldRuleTableBody");
		if(ruleTBody.find("tr:first td").length==1){
			return tableData;
		}
		var prop = "ruleArr";
		var propArray = ['id','itemId','checkbox','attrName','valueType','valueFormate','inputValue'];
		
		ruleTBody.find("tr").each(function(i, n){
			var tr = $(this);
			var trBeans = [];
			tr.find("td").each(function(k, m){
				var td = $(this);
				var bean = {};
				var input = td.find("input").eq(0);
				if(input[0]){
					if(input.prop("type")=="checkbox" && !input.prop("checked")){
						trBeans = [];
						return false;
					}else{
						bean.value = input.val();
					}
				}else{
					bean.value = td.html();
				}
				bean.name = prop+'['+i+'].'+propArray[k];
				
				trBeans.push(bean);
			});
			Array.prototype.push.apply(tableData, trBeans);
		});
		return tableData;
	}
	
	
	/*TODO-------------规则明细-列:end------------*/
	
	
	/*----------树相关-------------*/
	
	
	//得到当前选中的树节点
	function _getSelectedNodes(){
		var treeObj = $.fn.zTree.getZTreeObj("treeFunc");
		var nodes = treeObj.getSelectedNodes();
		return nodes;
	}
	
	//得到当前唯一节点
	function _getOnlySelectedNode(){
		var treeObj = $.fn.zTree.getZTreeObj("treeFunc");
		var nodes = treeObj.getSelectedNodes();
		if(nodes.length!=1){
			eDialog.warning("请选择一个功能点！");
			return false;
		}
		return nodes[0];
	}
	
	//得到树对象
	function _getZTreeObj(){
		return $.fn.zTree.getZTreeObj("treeFunc");
	}
});