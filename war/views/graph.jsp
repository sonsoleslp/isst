<%@page import="es.upm.dit.isst.socialTV.bs.beans.GraphBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset='utf-8' />
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
<link href='../css/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src="../js/moment.min.js"></script>
<script src="../js/jquery.min.js"></script>

<link href="../css/chartist.css" rel="stylesheet">
<link rel="stylesheet" href="../css/animate.css">
<link rel="stylesheet" href="../css/style.css"> 


</head>
<body id="graphbody">


 <nav class="navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
     <a class="navbar-brand" href="#" style="margin:2px; padding:0;"><img src="/img/logo.png" height="40px"></a>
      <a class="navbar-brand" href="index.html">SocialTV</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
       <li class="active"><a href="index.html#"><i class="fa fa-home"></i> Home</a></li>
   		<li><a href="/top"><i class="fa fa-star-o"></i> Top 5</a></li>
        <li><a href="/calendar"><i class="fa fa-line-chart"></i> Calendar</a></li> 
        <li><a href="/apitest"><i class="fa fa-wrench"></i> Config</a></li> 
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <!-- <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li> --> 
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<br>
	<div class="container ">

	<div class="row">
		<div class="col-xs-12 col-lg-8 col-lg-push-2 ">
			<div class="wow slideInLeft" style="float: left;">
				<h1 style="font-size:36px;">
					<c:out value="${graphBean.title}"/><span style="font-family:'Source Sans Pro';">(<c:out value="${graphBean.episodeCode}"/>)</span>
				</h1>
				<h2 style="color: #1685CD;">
					<c:out value="${graphBean.hashtag}"/>
				</h2>
				<h3>
				<c:out value="${graphBean.dateStart}"/> - <c:out value="${graphBean.dateEnd}"/>
				<br><br>
				</h3>
				<a href="/mapa/<c:out value="${graphBean.id}"/>"><button class="btn btn-default">Datos
								regionales</button></a>
			</div>

			<img style="width: 200px; float: right;"
				class="mediafoto wow slideInRight" src="/img/bird.png"> <br
				style="clear: both;">
			<hr style="background-color: grey; height: 2px;">
			<div class="ct-chart ct-golden-section" id="chart1"></div>
		</div>
	</div>
	</div>
	<script src="../js/classie.js"></script>
	<script>
      var 
        menuRight = document.getElementById( 'cbp-spmenu-s2' ),
      
        showRight = document.getElementById( 'showRight' ),

        body = document.body;

  
      showRight.onclick = function() {
        classie.toggle( this, 'active' );
        classie.toggle( menuRight, 'cbp-spmenu-open' );
        disableOther( 'showRight' );
        $( '#menuicon' ).toggleClass('invisible')
        $( '#closeicon' ).toggleClass('invisible')

      };

      function disableOther( button ) {
      
        if( button !== 'showRight' ) {
          classie.toggle( showRight, 'disabled' );
        }
        
      }
    </script>
	<script src="../js/classie.js"></script>
	<script>
      var 
        menuRight = document.getElementById( 'cbp-spmenu-s2' ),
      
        showRight = document.getElementById( 'showRight' ),

        body = document.body;

  
      showRight.onclick = function() {
        classie.toggle( this, 'active' );
        classie.toggle( menuRight, 'cbp-spmenu-open' );
        disableOther( 'showRight' );
        $( '#menuicon' ).toggleClass('invisible')
        $( '#closeicon' ).toggleClass('invisible')

      };

      function disableOther( button ) {
      
        if( button !== 'showRight' ) {
          classie.toggle( showRight, 'disabled' );
        }
        
      }
    </script>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/chartist.min.js"></script>
	<script>
      
      var chart = new Chartist.Line('.ct-chart', {
        	labels: ['${fn:join(graphBean.strHoras, "', '")}'],
          series: [['${fn:join(graphBean.numTweets, "', '")}']]
        }, {
          high: ${graphBean.ptoMaximo},
          low: 0,
          fullWidth: true,
          showArea: true,
          showPoint: true,
          height:'300px'

         });

      chart.on('draw', function(data) {
        if(data.type === 'line' || data.type === 'area') {
          data.element.animate({
            d: {
              begin: 2000 * data.index,
              dur: 2000,
              from: data.path.clone().scale(1, 0).translate(0, data.chartRect.height()).stringify(),
              to: data.path.clone().stringify(),
              easing: Chartist.Svg.Easing.easeOutQuint
            }
          });
        }
      });

       

       </script>
	<script src="../js/wow.min.js"></script>
	<script>
            new WOW().init();
            </script>

	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/underscore.min.js"></script>
	<script src="../js/moment.min.js"></script>

</body>
</html>
