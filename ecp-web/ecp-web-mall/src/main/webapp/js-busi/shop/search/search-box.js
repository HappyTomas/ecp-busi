$(function(){
	//页面业务逻辑处理内容
    var pageInit = function(){
        var init = function(){
        	
		    //搜索热词加载
        	$.eAjax({
				url : GLOBAL.WEBROOT + "/shopgdssearch/hotkeyword",
				success : function(json) {
					
					if(json.length>0){
						for(var index in json){
                            $('#searchConten').append("<li><a href=\"#\">"+json[index]+"</a></li>");
						}
					}
					
				}
	    	});
        	
        	//为快速搜索添加单击绑定事件
        	$('#searchConten a').live('click',function(e){
        		
        		search($(this).text());
        		
        	});
        	
        	//搜索suggest事件绑定和处理
        	if($('#site-search').size()>0){
        		var searchBoxWidth=$('#searchBoxWidth').val();
        		if(!searchBoxWidth){
        			searchBoxWidth=550;
        		}
		        $('#site-search').autocomplete({
		                placeholder: '输入搜索关键字',
		                width: searchBoxWidth,
		                height: 28,
		                showButton: true,
		                buttonText: '搜索',
		                onSubmit:function(input){
							search(input.val());
		                },
		                inputSet:function(input,currentProposals,currentSelection){
		                	if(currentSelection>-1){
		                		var curPro=currentProposals[currentSelection];
		                        input.val(curPro.collationQueryString);
		                	}
		                },
		                onChange:function(params,input,proposalList,currentProposals){
		                	
		                	if($('#searchTxt').val()){
		                		//开始键入时隐藏搜索热词
		                		$('#searchConten').hide();
		                	}else{
		                		$('#searchConten').show();
		                	}
		                	
		                	var keyword=$.trim($('#searchTxt').val());
		                	
		                	if(!keyword) {
		                		proposalList.empty();
		                		return ;
		                	}
		                	
		                	$.eAjax({
		            			url : GLOBAL.WEBROOT + "/shopgdssearch/suggest",
		            			data : {'keyword':keyword},
		            			success : function(json) {
		            				//if(null != json && 'ok' == json.resultFlag){
		            					var vals=json;
		            					if(vals.length==0){
		            						proposalList.empty();
		            					}else{
		            						for(var index in vals){
		                                        var v=vals[index];
		                                        currentProposals.push({collationQueryString: v.collationQueryString,numberOfHits:v.numberOfHits});
		                                        var element = $('<li></li>')
		                                            .addClass('proposal')
		                                            .click(function(){
		                                              //  params.inputSet(input,currentProposals,$(this).index());
		                                            	input.val($(this).text());
		                                            	proposalList.empty();
		                                                params.onSubmit(input);
		                                            })
		                                            .mouseenter(function() {
		                                                $(this).addClass('selected');
		                                            })
		                                            .mouseleave(function() {
		                                                $(this).removeClass('selected');
		                                            });
		
		                                        var html= "<span class='search-val'>"+v.collationQueryString+"</span>";
		                                        if(index>proposalList.find('li').size()-1){
		                                            element.html(html);
		                                            proposalList.append(element);
		                                        }else{
		                                            proposalList.find('li').eq(index).html(html);
		                                        }
		                                    }
		                                   if(vals.length<proposalList.find('li').size()){
		                                       proposalList.find('li:gt('+(vals.length-1)+')').remove();
		                                   }
		            					}
		            					
		            				//}else{
		            				//	eDialog.alert("搜索返回数据遇到异常！错误信息:"+json.resultMsg);
		            				//}
		            			}
		            			/*,error : function(e,xhr,opt){
		            				eDialog.alert("搜索返回数据遇到异常!"); 
		            			}*/
		                	});
		                	}
		            }
		        );
		    }
        	
        };
        
        /**
         * 搜索
         */
        var search=function(keyword){
        	
        	keyword=$.trim(keyword);
        
        	//搜索关键字判空校验
    		//if(!keyword){
    			//return ;
    			//keyword='*';
    		//}
    		
    		//打开搜索组件页面
        	var url=encodeURI(GLOBAL.WEBROOT+"/shopgdssearch?keyword="+keyword);
//        	alert(url);
    		window.location.href = url;
        	
        }

        return {
            "init" : init
        };
    };
    var p = pageInit();
    p.init();
});