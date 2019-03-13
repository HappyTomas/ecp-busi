/**
 * 
 */

$(function() {
			$.eAjax({
						url : GLOBAL.WEBROOT + "/searchExtern/hotSale",						
						dataType : 'html',
						success : function(returnInfo) {
							$("#hotSale").html(returnInfo);
						},
						exception : function() {

						}
					});
		})