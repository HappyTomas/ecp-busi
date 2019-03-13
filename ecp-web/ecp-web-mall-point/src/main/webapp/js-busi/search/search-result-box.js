$(function(){
	//页面业务逻辑处理内容
    var pageInit = function(){
        var init = function(){
        	
        };
        /**
         * 搜索
         */
        var search=function(keyword){
        	$("#searchingTxt").val(keyword);
			$gdsListRefresh.refresh({"pageNumber":1});
        	
        };
      //搜索suggest事件绑定和处理
//    	if($('#searchTxt').size()>0){
	        $('#serList-search').autocomplete({
	                placeholder: '输入搜索关键字',
	                width: 120,
	                height: 20,
	                showButton: true,
	                buttonText: '查询',
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
	            			url : GLOBAL.WEBROOT + "/search/suggest",
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
//    	}
        return {
            "init" : init
        };
    };
    
    var p = pageInit();
    p.init();
});