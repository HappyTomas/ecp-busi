$(function(){
//     var promTplCoupon=$('.promTpl-coupon');
//     promTplCoupon.each(function(){
//    	 var coupons=$('.couponList li',$(this)).size();
//         var couponMore=$('.more',$(this));
//         var promTap=$(this).parents('.promTpl-tab');
//         if(coupons>1){
//             promTap.css('padding-top',150);
//         }
//         if(coupons>4){
//             couponMore.show();
//             promTap.css('padding-top',165);
//         }
//     });
     $(".showMoreCoup").live('click',function(e){
    	 var couponList=$(this).parent().find('.couponList');
         promTab=$(this).parents('.promTpl-tab');
         if(!$(this).hasClass('couponUp')){
             couponList.height('auto');
             promTab.css("padding-top",couponList.height()+15);
         }else{
             couponList.height(150);
             promTab.css("padding-top",165);
         }
         $(this).toggleClass('couponUp');
         e.stopPropagation();
         return;
     });
 });