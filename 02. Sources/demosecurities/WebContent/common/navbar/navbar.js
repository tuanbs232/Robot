/**
 * 
 */
$(window).bind('scroll', function() {
//	var navHeight = $(window).height() - 300;
	if ($(window).scrollTop() > 50) {
		$('.navbar-default').addClass('on');
		$('.brand-des').hide();
	} else {
		$('.navbar-default').removeClass('on');
		$('.brand-des').toggle(500);
	}
});