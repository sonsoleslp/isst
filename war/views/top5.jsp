<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset='utf-8' />
<link href='css/fonts.css' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/styleTop5.css" />
<script src="js/jquery.min.js"></script>
<!-- <link rel="stylesheet" href="css/style.css"> -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/default.css" />
<link rel="stylesheet" type="text/css" href="css/component.css" />
<script src="js/modernizr.custom.js"></script>
<script src='js/jquery.min.js'></script>
<link rel="stylesheet" href="css/animate.css">
</head>
<body>

	<nav style="overflow-y: auto;"
		class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-right"
		id="cbp-spmenu-s2">

		<h3>Programas</h3>
		<a href="top5"><b>TOP 5</b></a> 
		<a href="#">Modern Family</a> 
		<a href="#">HIMYM</a> 
		<a href="#">Friends</a> 
		<a href="#">TBBT</a> 
		<a href="#">New Girl</a> <a style="border-bottom: none;" href="calendar.html">All</a>

		<h3>Otros</h3>
		<a href="#">Settings</a> <a href="#">Logout</a>

	</nav>
	
	<div class="container ">
		<BR>
		<div class="cal1 "></div>
	</div>

	<button id="showRight">
		<span id="menuicon" style="color: #777; font-size: 40px;"
			class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>
		<span id="closeicon" style="color: #777; font-size: 40px;"
			class="invisible glyphicon glyphicon-remove" aria-hidden="true"></span>
	</button>

	<div class="logotype">
		<img class="branding" src="img/logo.png" height="40px"> 
		<a class="branding" href="index.html">SocialTV</a>
	</div>
	
	<div>
		<div class="row">
			<div class=" col-xs-12 col-lg-8 col-lg-push-2">
				<h1 class="wow slideInLeft">TOP 5</h1>
				<table cellspacing="10" id='top5' class="wow slideInDown table ">
					<thead>
						<tr>
							<th>
								<span style="color: orange;" class="glyphicon glyphicon-star" aria-hidden="true"></span>
								Serie
							</th>
							<th>Episodio</th>
							<th># Hashtag</th>
							<th>
								<span style="color: grey;" class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
								Emisión
							</th>
							<th>Menciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="programa" items="${top5Bean}">
							<tr>
							<td><c:out value="${programa.titulo}"/></td>
							<td><c:out value="${programa.episodeCode}"/></td>
							<td><c:out value="${programa.hashtag}"/></td>
							<td><c:out value="${programa.emision}"/></td>
							<td><c:out value="${programa.numTweets}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
	</div>
	<script src="js/classie.js"></script>
	<script>
		var menuRight = document.getElementById('cbp-spmenu-s2'),
		showRight = document.getElementById('showRight'),
		body = document.body;
		showRight.onclick = function() {
			classie.toggle(this, 'active');
			classie.toggle(menuRight, 'cbp-spmenu-open');
			disableOther('showRight');
			$('#menuicon').toggleClass('invisible')
			$('#closeicon').toggleClass('invisible')

		};

		function disableOther(button) {
			if (button !== 'showRight') {
				classie.toggle(showRight, 'disabled');
			}
		}
	</script>
	<script src="js/classie.js"></script>
	<script>
		var menuRight = document.getElementById('cbp-spmenu-s2'),
		showRight = document.getElementById('showRight'),
		body = document.body;
		showRight.onclick = function() {
			classie.toggle(this, 'active');
			classie.toggle(menuRight, 'cbp-spmenu-open');
			disableOther('showRight');
			$('#menuicon').toggleClass('invisible')
			$('#closeicon').toggleClass('invisible')
		};

		function disableOther(button) {
			if (button !== 'showRight') {
				classie.toggle(showRight, 'disabled');
			}
		}
	</script>

	<script src="js/bootstrap.min.js"></script>
	<script src="js/wow.min.js"></script>
	<script>
		new WOW().init();
	</script>
</body>
</html>
