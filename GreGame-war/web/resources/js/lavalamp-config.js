jQuery(function() {
	/* Function for lavalamp navigation menu and dropdown */	
	$("#topnav").lavaLamp({
		fx: "backout",
		speed: 700
	});
	$(" #topnav ul ").css({display: "none"}); // Opera Fix
	$(" #topnav li").hover(function(){
	$(this).find('ul:first').css({visibility: "visible",display: "none"}).slideDown(400);
	},function(){
	$(this).find('ul:first').css({visibility: "hidden",display: "none"});
	});
			
});

