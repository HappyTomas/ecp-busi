$(function(){
	$('.showall').live('click',function(){
		$(this).parents('.wl-box').find("li").show();
		$(this).parent().hide();
		$(this).parent().next().show();
	});
	
	$('.showtwo').live('click',function(){
		var lis = $(this).parents('.wl-box').find("li");
		lis.hide();
		lis.eq(0).show();
		lis.eq(1).show();
		$(this).parent().hide();
		$(this).parent().prev().show();
	});
		
	var getLocalTime=function (nS) {     
       return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");      
    };
    
    var add0 = function (m){return m<10?'0'+m:m}
    var format = function (shijianchuo)
    {
	    var time = new Date(shijianchuo);
	    var y = time.getFullYear();
	    var m = time.getMonth()+1;
	    var d = time.getDate();
	    var h = time.getHours();
	    var mm = time.getMinutes();
	    var s = time.getSeconds();
	    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
    };
    
	$('.serchWl').each(function(){
		 var orderId =  $(this).prev().val();
		 $(this).qtip({
		        content: {
		            text: 'Loading...', // The text to use whilst the AJAX request is loading
		            ajax: {
		                url: GLOBAL.WEBROOT+'/ord/queryExpress',
		                type: 'post',
		                data: {
		                	orderId:orderId
		                },
		                success: function(data, status) {
		                	if(data==''||data==null){
		                		this.set('content.text',"&nbsp;");		                		
		                	}else{
			                	var objs = eval("("+data+")");
			                	var str = "";		                	
			                	for(var i=0;i<objs.values.length;i++){
			                		if(i==0){
			                			str += "<div class='wl-box' style='max-width:inherit;padding-top:5px;padding-bottom:10px;border-bottom:1px solid #CCCCCC;border-top:1px solid #CCCCCC;'>";	
			                		}else{
			                			str += "<div class='wl-box' style='max-width:inherit;padding-top:5px;padding-bottom:10px;border-bottom:1px solid #CCCCCC;'>";	
			                		}
			                		var obj = objs.values[i];
			                		if(obj.deliveryType=='1'){	
			                			str = str+"<div class='tit' style='height:auto;max-height:60px;line-height:25px;border:0;margin-bottom: 5px; font-weight: bold;'>"
			                			str = str + "物流公司："+obj.expressName+"<br/>";
			                			str = str + "运单号码："+obj.expressNo+"</span>";
			                			str = str+"</div>";
			                			var expresses = obj.ordExpressDetailResps
			                			if(expresses.length==0){
			                				str = str + "<div class='cont'><span style='color:#FF9900;font-size:12px;'>暂时没有该运单号码的物流信息</span></br></div>";
			                			}else{
				                			str = str +"<div class='cont'><ul class='list'>";
				                			for(var k=0;k<expresses.length;k++){
				                				var express = expresses[k];
				                				if(k<2){
				                					if(k==0)
				                						str = str + "<li class='cur'><p class='txt'>"+express.context+"</p><p class='time'>"+format(express.expressTime)+"</p></li>";
				                					else
				                						str = str + "<li><p class='txt'>"+express.context+"</p><p class='time'>"+format(express.expressTime)+"</p></li>";
				                				}else{
				                					str = str + "<li style='display:none;'><p class='txt'>"+express.context+"</p><p class='time'>"+format(express.expressTime)+"</p></li>";
				                				}			                				
				                			}
				                			str = str +"</ul>";
				                			if(expresses.length>2){
				                				str = str + "<p><span style='font-size:12px;'>以上为最新跟踪信息&nbsp;&nbsp;</span><a href='javascript:void(0)' class='showall'><span style='color:#0000FF;font-size:12px;'>查看全部</span></a></p>"
				                				str = str + "<p style='display:none;'><span style='font-size:12px;'>以上为全部跟踪信息&nbsp;&nbsp;</span><a href='javascript:void(0)' class='showtwo'><span style='color:#0000FF;font-size:12px;'>查看部分</span></a></p>"
				                			}
				                			str = str + "</div>";	
				                		}
			                		}
			                		str = str + "</div>";
			                	}
	                			if(str!=""){
			                		  this.set('content.text',str);
			                	}else{
			                		  this.set('content.text',"&nbsp;");
			                	}		
			                			                  
		                	}
		                }
		            }
		        },
		        hide: {
		            fixed: true,
		            delay: 300
		        },
		        style: {
		            classes: 'qtip-bootstrap qtip-wl'
		        },
		        position:{
		            my: 'top center',
		            at: 'bottom center'
		        }
		    });
	}); 
});
