<%@page import="es.upm.dit.isst.socialTV.bs.beans.SpainMapBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
	<!-- <meta charset='utf-8' /> -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href='../css/fonts.css' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="/css/font-awesome.min.css">
	<script src="../js/jquery.min.js"></script>
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../css/default.css" />
	<link rel="stylesheet" type="text/css" href="../css/component.css" />
	<script src="../js/modernizr.custom.js"></script>
	<link href='../css/fullcalendar.css' rel='stylesheet' />
	<link href='../css/fullcalendar.print.css' rel='stylesheet'
		media='print' />
	<script src="../js/moment.min.js"></script>
	<script src="../js/jquery.min.js"></script>
	
	<link href="../css/chartist.css" rel="stylesheet">
	<link rel="stylesheet" href="../css/animate.css">
	<link rel="stylesheet" href="../css/style.css">
	<script type="text/javascript" src="../js/raphael-min.js"></script>
	<script type="text/javascript" src="../js/spain-map.js"></script>

</head>

<script>
	var tweets = ['${fn:join(spainMapBean.provinceTweets, "', '")}'];
	var provincias = ['${fn:join(spainMapBean.provinceName, "', '")}'];
	var max = 	<c:out value="${spainMapBean.max}" />
	var min = 	<c:out value="${spainMapBean.min}" />
	mapa(provincias, tweets, max, min);
</script>

<body id="bodycalendar">
	<nav class="navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#" style="margin: 2px; padding: 0;"><img
					src="/img/logo.png" height="40px"></a> <a class="navbar-brand"
					href="/">SocialTV</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="/"><i
							class="fa fa-home"></i> Home</a></li>
					<li><a href="/top"><i class="fa fa-star-o"></i> Top 5</a></li>
					<li><a href="/calendar"><i class="fa fa-line-chart"></i>
							Calendar</a></li>
			         <% if ("admin".equals(session.getAttribute("rol"))) { %>
			         <li><a href="/apitest"><i class="fa fa-wrench"></i> Config</a></li> 
			 		<% }%>
				</ul>
				<ul class="nav navbar-nav navbar-right">
			        <li>
			        	<a href="${urlLog_in_out}">
			        		<span class="glyphicon glyphicon-log-in"></span>
			        		<c:out value="${urlLinktext}"/>
			        	</a>
			        </li>
				</ul>
			</div>
		</div>
	</nav>
	<br>

	<div class="container ">

	<button id="showRight">
		<span id="menuicon" style="color: #777; font-size: 40px;"
			class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>
		<span id="closeicon" style="color: #777; font-size: 40px;"
			class="invisible glyphicon glyphicon-remove" aria-hidden="true"></span>
	</button>

	<div class="logotype">
		<img class="branding" src="img/logo.png" height="40px"> <a
			class="branding" href="index.html">SocialTV</a>
	</div>


	<div class="row ">
		<div class="col-xs-12 col-lg-10 col-lg-push-1 ">
			<div class="wow slideInLeft" style="float: left;">
				<h1 style="font-size: 36px;">
					<c:out value="${spainMapBean.title}" />
					<span style="font-family: 'Source Sans Pro';">
					(<c:out	value="${spainMapBean.episodeCode}" />)
					</span>
				</h1>
				<h2 style="color: #1685CD;">
					<c:out value="${spainMapBean.hashtag}" />
				</h2>
				<h3>
					<i style="color:#1685D0;" class="fa fa-hourglass-start"></i> <c:out value="${spainMapbean.dateStart}"/> - <i style="color:#1685D0;" class="fa fa-hourglass-end"></i> <c:out value="${spainMapbean.dateEnd}"/>

				</h3>
				<h3>
				<i style="color:#1685D0;" class="fa fa-balance-scale"></i>
				<c:out value="${spainMapBean.count}"/> tweets<br><br>
				</h3>
				<a href="/grafica/<c:out value="${spainMapBean.id}"/>">
				<button class="btn btn-default"><i style="color:#1685D0;" class="fa fa-area-chart"></i>  Evoluci&oacute;n Temporal</button></a>
				<a href="/report/<c:out value="${spainMapBean.id}"/>">
				<button class="btn btn-default"><i style="color:#1685D0;" class="fa fa-file-pdf-o"></i>  Informe pdf</button></a>								
				
			</div>
			
			<img style="width: 150px; float: right;"
					class="mediafoto wow slideInRight" src="/img/bird.png"> <br
					style="clear: both;">
				<hr style="background-color: grey; height: 2px;">
			</div>
		</div>
	</div>
			<div class="row centered2">
				<span class="wow slideInDown" id="mapa">Haz click en una
					provincia para ver su nº de Tweets</span>
			</div>
			<div id="map" class="wow fadeInUp"></div>
			<script src="js/jquery.min.js"></script>
			<script src="js/wow.min.js"></script>
			<script>
            new WOW().init();
            </script>
			<script type="text/javascript">
 			

			
			    new SpainMap({
			      id: 'map',
			      width: '90%',
			      height: '90%',
			      fillColor: "#eeeeee",
			      strokeColor: "#cccccc",
			      strokeWidth: 0.7,
			      selectedColor: "#aaa",
			      animationDuration: 200,
			      onMouseOver: function(province, event) {
			    	 $('#mapa').show()
			        document.getElementById('mapa').innerHTML= '<span style="left:'+event.clientX+'; top: '+event.clientY+' ;" class="toolMap wow slideInDown" > En <b>' + province.name+'</b> ha habido <b> '+ province.value+ '</b> tweets.</span>'       
			      },

			      onMouseOut: function(province, event) {
			    	 $('#mapa').hide()
			     }
			    });
  			</script>
		</div>
	</div>
	<script src="js/classie.js"></script>


	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/chartist.min.js"></script>

	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/underscore.min.js"></script>
	<script src="/js/moment.min.js"></script>

</body>
</html>
