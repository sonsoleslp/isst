$("#compareButton").click(function(e) {
	if(window.location.pathname.indexOf('compare') > -1) {
		$("#leyenda").toggle();
		e.preventDefault();
	}
});
