var status ="0";
$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
    	'pCheckRow' : false,
		'pCheckColumn' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/unpf/gdsManage/gridlist',
      //指定列数据位置
        "aoColumns": [
    				{ "mData": "id", "sTitle":"商品编码","sWidth":"80px","sClass": "center","bSortable":false},
					{ "mData": "gdsName", "sTitle":"商品名称","sWidth":"80px","sClass": "center","bSortable":false,
	  				  "mRender": function(data,type,row){
						  if(row.gdsStatus == '99'){
								//<a href='javascript:void(0)' onclick='gdsRestart(this)'>重新启用</a>
								return data;
						  }
						  return '<a  target="_blank" href="'+row.gdsDetailUrl+'">'+data+'</a>';
					  }},
					{ "mData": "mainCatgName", "sTitle":"主分类名称","sWidth":"80px","sClass": "center","bSortable":false},
					{ "mData": "isbn", "sTitle":"ISBN","sWidth":"80px","sClass": "center","bSortable":false},
    				{ "mData": "guidePrice", "sTitle":"商品指导价","sWidth":"80px","sClass": "center","bSortable":false,
    					"mRender": function(data,type,row){
    						var str = (data/100).toFixed(2) + '';
    						var intSum = str.substring(0,str.indexOf("."));//取到整数部分.replace( /\B(?=(?:\d{3})+$)/g, ',' )
    						var dot = str.substring(str.length,str.indexOf("."));//取到小数部分
    						var ret = intSum + dot;
    						return ret;
    					}
    				},
    				{ "mData": "gdsStatusName", "sTitle":"状态","sWidth":"80px","sClass": "center","bSortable":false},
    				{ "mData": "platTypes", "sTitle":"对接平台","sWidth":"120px","sClass": "center","bSortable":false,"bVisible":true,"mRender":function(data,type,row){
	   					 var _html="<div></div>";
	   					for(var key in row.platTypes){
	   						if("1" == row.platStatus[key]){
                                _html+="<div class='text-left'><lable class='control-label' style='text-align:left;width:10px;'><input  disabled='disabled' type='checkbox' onclick='event.stopPropagation();' name='platType' value='"+row.id+"@"+key+"'>"+row.platTypes[key] +"  (已推送)"+"</lable></div><br/>";
							} else {
                                _html+="<div class='text-left'><lable class='control-label' style='text-align:left;width:10px;'><input  class='"+key+"'  type='checkbox' onclick='event.stopPropagation();' name='platType' value='"+row.id+"@"+key+"'>"+row.platTypes[key] +"  (未推送)"+"</lable></div><br/>";
							}
	   					}
	   	  				return _html;
	   	  			}},
   	  			{ "mData": "info", "sTitle":"操作","sWidth":"120px","sClass": "center","bSortable":false, "mRender":function(data,type,row){
	   	  			for(var key in row.platTypes){
		   	  			var _html="<a class='btn' href='javascript:void(0)' onclick='gdsUp(this,"+row.shopId+")'>&nbsp;<i class='icon-arrow-up icon-white'></i> 推送&nbsp;</a>";
		   	  			_html +="&nbsp;<a class='btn' style='margin-left: 0px' href='javascript:void(0)' onclick='GdsManage.gdsUpdate(this,"+row.shopId+")'>&nbsp;<i class='icon-edit icon-white'></i> 修改&nbsp;</a>";
		   	  			return _html;
	   	  			}
	   	  				return "";
	    	  	}} 
    			],
        "params" : [{name : 'shopId',value : $("#shopId").val()},
                    {name : 'ifGdsScore',value : $("#ifGdsScore").val()}],
        "createdRow": function ( row, data, index ) {
			$('td', row).eq(2).css('word-break',"break-all");
		 	
        },
                    
	});
	//查询
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var param = new Object();
		param.shopId = $("#shopId").val();
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsmanage/shopStatus",
			data : param,
			success : function(returnInfo) {
			  if(returnInfo.status == '1'){
				  $("#batchOpt").show();
				  
				  
			  }else{
				  $("#batchOpt").hide();  
				  
			  }
			}
		});	
		
		var p = ebcForm.formParams($("#searchForm"));
		p.push({ 'name': 'catgCode','value' : $("#catgCode").attr('catgCode') });
		p.push({ 'name': 'ifGdsScore','value' : $("#ifGdsScore").val()});
		p.push({'name':'isbn','value':$("#isbn").val()});
		$.gridLoading({"messsage":"正在加载中...."});
		try {
			$('#dataGridTable').gridSearch(p);
		} catch (e) {
		}
		$.gridUnLoading();
	});
	//重置
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		var catgCode=$('#catgCode');
		if(catgCode.attr("catgcode")){
			catgCode.removeAttr("catgcode");
		}
		if($("#ifGdsScore").val()=="1"){
			var value=$("#scoreShop").val();
			$("#shopId option[value='"+value+"']").attr('selected','selected');
		}
	});
	//批量推送
	$("#btn_code_up").live('click',function(){
		GdsManage.gdsBatchUp();
	});
	//全选淘宝
	var qxtaobao=1;
	$('#qxtaobao').click(function(){
		if(qxtaobao==1){
			qxtaobao=2;
			$("input[class=taobao]").each(function(){
				$(this).attr('checked',true);  
			});
		}else{
			qxtaobao=1;
			$("input[class=taobao]").each(function(){
				$(this).attr('checked',false);  
			});
		}
	});
	//全选有赞
    var qxyouzan = 1;
	$('#qxyouzan').click(function(){
		if(qxyouzan==1){
			qxyouzan=2;
			$("input[class=youzan]").each(function(){
				$(this).attr('checked',true);  
			});
		}else{
			qxyouzan=1;
			$("input[class=youzan]").each(function(){
				$(this).attr('checked',false);  
			});
		}
	});
	
	

	$("#catgCode").click(function(){
		/*catlogId = '1';
		if($("#ifGdsScore").val()=='1'){
			catlogId = '2';
		}*/
		if(undefined == $('#shopId').val() || "" == $('#shopId').val()){
			 eDialog.alert('店铺必须选择!');
			 return;
		}
		bDialog.open({
	        title : '分类选择',
	        width : 350,
	        height : 550,
	        params:{multi : false,ignoreDataAuth:'false',shopIds:[$('#shopId').val()]},
	        url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=1",
	        callback:function(data){
	        	if(data && data.results && data.results.length > 0 ){
	                var _catgs = data.results[0].catgs;
					var size = _catgs.length;
					for(var i =0;i<size;i++){
						var obj = _catgs[i];
						$("#catgCode").val(obj.catgName);
						$("#catgCode").attr('catgCode',obj.catgCode);
					}
				}
	        }
	    });
	});
	
	/*$("input[name=platType]").click(function(){
		this.attr("checked", true);
		return false;
	});*/
});
var stop=function(e){
	e.stopPropagation();
}
$("input[name=platType]").click(function(e){  
    e.stopPropagation();   
});
function gdsUp(obj,shopId){
	GdsManage.gdsUp(obj,shopId);
}

var GdsManage = {
		gdsUp : function(obj,shopId){
			var checked = $(obj).parent().siblings().find("input[name=platType]:checked");
			var _shopIdList=new Array();
			if(checked.length<=0){
	    		eDialog.alert('请选择需要推送的平台',null); 
	    		return ;
	      }else{
	    	  $(checked).each(function(){
	    		  _shopIdList.push(this.value);
	    	  });
	      }
			var param = {
					shopId : shopId,
					sendGds:_shopIdList.join(',')
			};
			$.gridLoading({"message":"正在推送中...."});
			$.eAjax({
				url : GLOBAL.WEBROOT + "/unpf/gdsManage/gdssend",
				data : param,
				success : function(returnInfo) {
					if(returnInfo.resultFlag=='ok'){
						eDialog.success('推送成功！',{
							buttons:[{
								caption:"确定",
								callback:function(){
									$.gridUnLoading();
									$('#dataGridTable').gridReload();
						        }
							}]
						}); 
					}else{
						eDialog.success(returnInfo.resultMsg,{
							buttons:[{
								caption:"确定",
								callback:function(){
									$.gridUnLoading();
									$('#dataGridTable').gridReload();
						        }
							}]
						}); 
						//eDialog.error('推送失败！:'+returnInfo.resultMsg);
					}
				}
			});
		},
		gdsBatchUp : function(){
            var checked = $("input[name=platType]:checked");
            var _shopIdList=new Array();
            if(checked.length<=0){
            	eDialog.alert('请至少选择一个平台类型进行操作！');
            	return;
            }
			$(checked).each(function(){
				_shopIdList.push(this.value);
			 });
			var param = {
					sendGds:_shopIdList.join(','),
					shopId : $("#shopId").val()
			};
			$.gridLoading({"message":"正在推送中...."});
			$.eAjax({
				url : GLOBAL.WEBROOT + "/unpf/gdsManage/gdssend",
				data : param,
				success : function(returnInfo) {
					if(returnInfo.resultFlag=='ok'){
						eDialog.success('全部推送成功！',{
							buttons:[{
								caption:"确定",
								callback:function(){
									$.gridUnLoading();
									$('#dataGridTable').gridReload();
						        }
							}]
						}); 
					}else{
						eDialog.success(returnInfo.resultMsg,{
							buttons:[{
								caption:"确定",
								callback:function(){
									$.gridUnLoading();
									$('#dataGridTable').gridReload();
						        }
							}]
						}); 
					
						//eDialog.error(returnInfo.resultMsg);
						//GdsManage.gridGdsList();
					}
				}
			});
		},
    gdsUpdate : function(obj,shopId){
        var checke = $(obj).parent().siblings().find("input[name=platType]")[0];
        var gdsId = $(checke).val().split("@")[0];
        bDialog.open({
            title : '对应关系修改',
            width : 400,
            height : 230,
            // params: {sendGds : _shopIdList.join(','),shopId:shopId},
            params: {},
            url:GLOBAL.WEBROOT + '/unpf/gdsManage/gdsUpdate?gdsId='+gdsId+'&shopId='+shopId,
            callback:function(data){
                $('#dataGridTable').gridReload();
            }
        });
    }
};