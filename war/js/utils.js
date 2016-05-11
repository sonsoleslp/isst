window.onload = function () {
	if(window.location.pathname.indexOf('compare') > -1) {
		$("#compareButton").addClass("oculto");
		$("#graphicButton").removeClass("oculto");
	} else if (window.location.pathname.indexOf('grafica') > -1){
		$("#graphicButton").addClass("oculto");
		$("#compareButton").removeClass("oculto");
	}
};

