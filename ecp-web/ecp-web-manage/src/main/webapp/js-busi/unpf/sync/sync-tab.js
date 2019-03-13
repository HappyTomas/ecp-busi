$(function () { 
        //$('#myTab a:first').tab('show');//初始化显示哪个tab 
        $('#myTab a').click(function (e) {
         e.preventDefault();
          $(this).tab('show');
        });
});