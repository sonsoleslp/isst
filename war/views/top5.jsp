<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset='utf-8' />
<link href='/css/fonts.css' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="/css/styleTop5.css" />
<script src="/js/jquery.min.js"></script>
<link rel="stylesheet" href="/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/default.css" />
<link rel="stylesheet" type="text/css" href="/css/component.css" />
<script src="/js/modernizr.custom.js"></script>
<script src='/js/jquery.min.js'></script>
<link rel="stylesheet" href="/css/animate.css">
 <link rel="stylesheet" href="/css/style.css"> 

</head>
<body>

 <nav class="navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
     <a class="navbar-brand" href="#" style="margin:2px; padding:0;"><img src="img/logo.png" height="40px"></a>
      <a class="navbar-brand" href="index.html">SocialTV</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
       <li><a href="index.html#"><i class="fa fa-home"></i> Home</a></li>
       <li class="active"><a href="/top"><i class="fa fa-star-o"></i> Top 5</a></li>
       <li><a href="/calendar"><i class="fa fa-line-chart"></i> Calendar</a></li> 
       <li><a href="/apitest"><i class="fa fa-wrench"></i> Config</a></li> 
 
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li> --> 
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<br>
	
<br><br>
		<div class="row">
			<div class=" col-xs-12 col-lg-8 col-lg-push-2">
				<h2 class="wow slideInLeft">TOP 5</h2>
				<table style="border:none; border-radius:15px !important;" cellspacing="10" id='top5' class="wow slideInDown table ">
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
	

	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/wow.min.js"></script>
	<script>
		new WOW().init();
	</script>
</body>
</html>
