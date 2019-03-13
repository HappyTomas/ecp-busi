$(function(){
	$("#dataRuleTableBody").delegate("tr:last","click",function(){
		var tr = createTR();
		$("#dataRuleTableBody tr:last").before(tr);
	});
	//生成表格行
	function createTR(data){
		var tr = $("<tr>");
		tr.append(createTD(data==undefined?"":data.vipLevelName,"<td  relatedtd='2'>"));
		tr.append(createTD(data==undefined?"":data.orderPay));
		tr.append(createTD(data==undefined?"":data.tradesNum));
		tr.append(createTD(data==undefined?"":data.discount));
		if(data){
			tr.append(createTD("<li class='icon-minus-sign'></li><input type='hidden' value='"+data.vipLevelCode+"'>","<td edittype='uneditable'>", false));
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
	//绑定单元格编辑
	$("#dataRuleTableBody").delegate("td","click",tdclick);
	//单元格编辑动作
	function tdclick() {
        var td = $(this);
        var edittype = td.attr("edittype");//编辑状态
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
        //1,取出当前td中的文本内容保存起来  
        var text = td.text().trim();
        var tdwidth = td.css("width");
        var tdheight = td.css("height");
        //2,清空td里面的内容  
        td.html(""); //也可以用td.empty();  
        //3,创建dom[viewtype]
        var _initList = [];//UI初始化值
 
	        //b1.建立一个文本框，也就是input的元素节点  
	        var input = $("<input type='text'></input>");
	        //b2.设置文本框的值是保存起来的文本内容  
	        input.attr("value", text);
	        input.attr("style", "width: 80%;");
	        tagdom = input;
   
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
//            myEvent.preventDefault();
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
	//切换店铺，获得对应的等级设置列表
	$("#shopIdForSet").change(function(){
		var shopId = $("#shopIdForSet").val();
		var _url = GLOBAL.WEBROOT + "/seller/shopmgr/gradelist";
		$.eAjax({
			url : _url,
			data :  {'shopId':shopId},
			datatype:'json',
			success : function(data) {
	    		_refreshDataRuleTable(data);
			}
		});
	});
	
	//刷新表格区域
	function gradelist(){
		var shopId = $("#shopIdForSet").val();
		var _url = GLOBAL.WEBROOT + "/seller/shopmgr/gradelist";
		$.eAjax({
			url : _url,
			data :  {'shopId':shopId},
			datatype:'json',
			success : function(data) {
	    		_refreshDataRuleTable(data);
			}
		});
	};
	gradelist();
	//刷新数据表格
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
	//删除表格一行
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
						url : GLOBAL.WEBROOT + "/seller/shopmgr/gradelist/delete",
						data : {'vipLevelCode':dataRuleId},
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
	//提取表格的值,JSON格式
	function getRuleTableData(table, prop) {
        var tableData = new Array();
        var propArr= ["vipLevelName", "orderPay", "tradesNum", "discount","delete"];
        if($("#dataRuleTableHead").find("tr th").length!=propArr.length){
        	eDialog.error('getRuleTableData,取值异常:属性不一致！'); 
        	return;
        }
        if(table.find("tr").length<2){
        	return;
        }
        table.find("tr").each(function(index, domEle){
        	if(index==(table.find("tr").length-1)) return false;
        	var rowData = getRuleTableRowData($(domEle),index,propArr,prop);
        	//如果有一行空数据，则返回空，提示要输入完全
        	if (!rowData) {
        		tableData = "";
        		return;
        	}
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
	    		bean.value = $(domEle).text().trim();
	    	}
	    	
	    	if(index==0&&bean.value==''){//当itemId 不存在，为无效行
	    		active = false;
	    		return false;
	    	}
	    	rowData.push(bean);
	    });
	    if(!active) return false;
	    return rowData;

	}
	//保存等级设置
	$('#btnSave').click(function(){
		var shops = $("#shopIdForSet").val();
		var _val = [{"name":"shopId","value":shops}];
		
		var _url = GLOBAL.WEBROOT + "/seller/shopmgr/gradesave";
		//规则配置
		var ruleArr = getRuleTableData($("#dataRuleTableBody"),'ruleArr');
		//未输入或者行数据为空
		if (ruleArr == undefined || ruleArr.length == 0) {
			eDialog.alert("请输入完善的会员等级规则信息。");
			return;
		} 
		Array.prototype.push.apply(_val, ruleArr);
		$.eAjax({
			url : _url,
			data :_val,
			datatype:'json',
			success : function(data) {
				var reflag = data.resultFlag;//ok  fail
		    	var remsg = data.resultMsg;
		    	if(reflag=="ok"){
		    		eDialog.success('保存成功！'); 
		    	}else{
		    		eDialog.error('保存异常！'+remsg); 
		    	}
				
			}
		});
	})
})
