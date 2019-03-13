var status ="0";
$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : true,
        'pCheck' : 'multi',
        "sAjaxSource": GLOBAL.WEBROOT + '/unpf/gdssendlog/gridlist',
      //指定列数据位置
        "aoColumns": [
    				{ "mData": "gdsId", "sTitle":"商品编码","sWidth":"80px","sClass": "center","bSortable":false,"mRender":function(data,type,row){
   						var _html="<div><lable  name='shopId' id='flag-"+row.id+"'value='"+row.gdsId+"@"+row.platType+"@"+row.shopId+"'>"+row.gdsId+"</lable></div>";
	   	  				return _html;
	   	  			}},
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
					{ "mData": "shopId", "sTitle":"店铺Id","sWidth":"80px","sClass": "center","bVisible":false,"bSortable":false},
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
    				{ "mData": "platTypeName", "sTitle":"对接平台","sWidth":"80px","sClass": "center","bSortable":false,"bVisible":true},
	   	  			{ "mData": "status", "sTitle":"推送结果","sWidth":"80px","sClass": "center","bSortable":false,"mRender":function(data,type,row){
	   	  				return "<a href='javascript:void(0)'  onclick=\"detailLog(\'"+row.gdsId+"\',\'"+row.shopAuthId+"\')\">&nbsp;"+row.status+"&nbsp;</a>";
	   					}},
	   	  			{ "mData": "info", "sTitle":"操作","sWidth":"160px","bSortable":false, "mRender":function(data,type,row){
    					
    	                var _html="<a class='btn' href='javascript:void(0)'  onclick=\"gdsUp('"+row.gdsId+"','"+row.platType+"','"+row.shopId+"')\">&nbsp;<i class='icon-arrow-up icon-white'></i> 推送 &nbsp;</a>";
    	  				return _html;
    	  			}} 
    			],
        "params" : [{name : 'platType',value : $("#platType").val()},
                    {name : 'shopId',value : $("#shopId").val()},
                    {name : 'gdsId',value : $("#gdsId").val()},
                    {name : 'status',value : $("#status").val()}],
        "createdRow": function ( row, data, index ) {
			$('td', row).eq(2).css('word-break',"break-all");
		 	
        },
                    
	});
	//查询
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
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
	});
	//批量上架
	$("#btn_code_up").live('click',function(){
			GdsManage.gdsBatchUp();
	});
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

function gdsUp(gdsId,platType,shopId){
	GdsManage.gdsUp(gdsId,platType,shopId);
}

function detailLog(gdsId,shopAuthId){
	GdsManage.detailLog(gdsId,shopAuthId);
}
var GdsManage = {
		detailLog : function(gdsId,shopAuthId){
			window.location.href = GLOBAL.WEBROOT+'/unpf/gdssendlog/detailLog/'+gdsId+'/'+shopAuthId;
		},
		gdsUp : function(gdsId,platType,shopId){
			var gdsSend=new Array();
			gdsSend.push(gdsId);
			gdsSend.push(platType);
			gdsSend.push(shopId);
			var param = {
					sendGds:gdsSend.join('@'),
			};
			$.gridLoading({"message":"正在推送中...."});
			$.eAjax({
				url : GLOBAL.WEBROOT + "/unpf/gdssendlog/gdssend",
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
					}
				}
			});
		},
		gdsBatchUp : function(){
			var ids = $('#dataGridTable').getCheckIds();
			//var ids = $('#dataGridTable').getSelectedData();
			var _shopIdList=new Array();
			if(!ids || ids.length==0){
				eDialog.alert('请至少选择一条商品记录进行操作！');
				return ;
			}else if(ids[0]==undefined){
				eDialog.alert('请至少选择一条商品记录进行操作！');
				return ;
			}else{
				for(var i=0;i< ids.length;i++){
					var val = $("#flag-"+ids[i]).attr("value");
			    		  _shopIdList.push(val);
				}
			}
			var param = {
					sendGds:_shopIdList.join(','),
			};
			$.gridLoading({"message":"正在推送中...."});
			$.eAjax({
				url : GLOBAL.WEBROOT + "/unpf/gdssendlog/gdssend",
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
					}
				}
			});
		},
};