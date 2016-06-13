$(function(){
	
	$('header').css({'position':'fixed'});
	$('.depth_menu').css({'position':'fixed','margin-top':'70px'});
	$('.contents').css({'padding-top':'130px','zIndex':'-1'});

	$(".depth_menu ul li").click(function(){	
		$('.depth_menu ul li').removeClass('view');
		$(this).addClass('view');
		pos = $(this).parent().parent().parent().parent().find(".contents div img").eq($(this).index()).position().top;		
		console.log(pos);
		$("html,body").stop().animate({'scrollTop':pos-120},700);
	});

});