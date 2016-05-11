<%@page import="es.upm.dit.isst.socialTV.bs.beans.GraphBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
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
     <a class="navbar-brand" href="/" style="margin:2px; padding:0;"><img src="/img/logo.png" height="40px"></a>
      <a class="navbar-brand" href="/">SocialTV</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
       	<li><a href="/"><i class="fa fa-home"></i> Home</a></li>
   		<li><a href="/top"><i class="fa fa-star-o"></i> Top 5</a></li>
        <li><a href="/calendar"><i class="fa fa-line-chart"></i> Calendar</a></li> 
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

	<div class="row">
		<div class="col-xs-12 col-lg-10 col-lg-push-1 ">
			<div class="wow slideInLeft" style="float: left;">
				<h1 id="titulo" style="font-size:36px;">
					<c:out value="${graphBean.title}"/><span style="font-family:'Source Sans Pro';">(<c:out value="${graphBean.episodeCode}"/>)</span>
				</h1>
				<h2 style="color: #1685CD;">
					<c:out value="${graphBean.hashtag}"/>
				</h2>
				<h3>
				<i style="color:#1685D0;" class="fa fa-hourglass-start"></i> <c:out value="${graphBean.dateStart}"/> - <i style="color:#1685D0;" class="fa fa-hourglass-end"></i> <c:out value="${graphBean.dateEnd}"/>
				
				</h3>
				<h3>
				<i style="color:#1685D0;" class="fa fa-balance-scale"></i>
				<c:out value="${graphBean.count}"/> tweets<br><br>
			
				</h3>
				<a title="Datos regionales" href="/mapa/<c:out value="${graphBean.id}"/>">
					<button class="roundButton btn btn-default"><i style="color:#1685D0;" class="fa fa-2x fa-map-marker"></i></button>
				</a>
				<a title="Informe pdf" href="/report/<c:out value="${graphBean.id}"/>">
					<button class="roundButton btn btn-default"><i style="color:#1685D0;" class="fa fa-2x fa-file-pdf-o"></i></button>
				</a>								
				<a title=" Evoluci&oacute;n Temporal" href="/grafica/<c:out value="${graphBean.id}"/>">
					<button id="graphicButton" class="roundButton btn btn-default"><i style="color:#1685D0;" class="fa fa-2x fa-area-chart"></i></button>
				</a>
				<a title="Comparar emisiones simult&aacute;neas" href="/compare/${graphBean.id}">
					<button id="compareButton" class="roundButton btn btn-default"><i style="color:#1685D0;" class="fa fa-2x fa-tachometer"></i></button>
				</a>
				
			</div>

			<img style="width: 150px; float: right;"
				class="visible-lg-block mediafoto wow slideInRight" src="/img/bird.png"> <br
				style="clear: both;">
			<hr style="background-color: grey; height: 2px;">
			
			<div id="leyenda" >
				<ul>
					<c:forEach var="programa" items="${compareGraphBean}" varStatus="loop">
						<li id="prog_<c:out value='${programa.id}' />" class="compGraphics">
							<i id="icon_<c:out value='${programa.id}' />"  class="interruptor fa fa-toggle-off"></i> <c:out value='${programa.title}' />
						</li>
			
					</c:forEach>
				</ul>
			</div>
			
			<div class="ct-chart ct-golden-section" id="chart1"></div>
		</div>
	</div>
	</div>
	<script src="../js/classie.js"></script>
	
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/chartist.min.js"></script>
	<script src="../js/chartist-plugin-tooltip.js"></script>
	<style>

    </style>
	<script>
		<%-- Guardamos los valores de los programas simultaneos --%>
		var series = [];
		
		<c:forEach var="programa" items="${compareGraphBean}">
		series["prog_${programa.id}"]  = [
			<c:forEach items="${programa.numTweets}"  varStatus="loop">  
				{
					meta: 'Hora:      ${programa.strHoras[loop.index]}    Tweets:',
					value: '${programa.numTweets[loop.index]}'
				}
				<%-- Adding commas except last element --%>
				<c:if test="${!status.last}">    
				,    
				</c:if>
			</c:forEach>
			];			
		</c:forEach>
		
		<%-- Guardamos los valores del programa actual --%>
		series["actual"]  = [
			<c:forEach items="${graphBean.numTweets}"  varStatus="loop">  
				{
					meta: 'Hora:      ${graphBean.strHoras[loop.index]}    Tweets:',
					value: '${graphBean.numTweets[loop.index]}'
				}
				<%-- Adding commas except last element --%>
				<c:if test="${!status.last}">    
				,    
				</c:if>
			</c:forEach>
			];
		
		<%-- Guardamos las labels --%>
		var labels = ['${fn:join(graphBean.strHoras, "', '")}'];

		<%-- Cargamos el chart con los valores del programa actual --%>
		var chart = new Chartist.Line('.ct-chart', {
			<c:if test="${fn:length(graphBean.numTweets)<25}">    
			labels: labels,
			</c:if>
			series: [
				series["actual"]
			]
        }, {
         
          low: 0,
          fullWidth: true,
          plugins: [
            Chartist.plugins.tooltip()
          ],
          showArea: true,
          showPoint: true,
          height:'300px'

         });

      chart.on('draw', function(data) {
        if(data.type === 'line' || data.type === 'area') {
          data.element.animate({
            d: {
              begin: 100 * data.index,
              dur: 2000,
              from: data.path.clone().scale(1, 0).translate(0, data.chartRect.height()).stringify(),
              to: data.path.clone().stringify(),
              easing: Chartist.Svg.Easing.easeOutQuint
            }
          });
        }
      });
      
      <%-- Actualizamos los valores a mostrar --%>
 		$(".compGraphics").click(function(e) {
 			// Activamos o desactivamos el elemento
 			
 			var icon = $('#icon_'+$(this).attr("id").substr(5))
 			icon.toggleClass('fa-toggle-off');
 			icon.toggleClass('fa-toggle-on');
 			
 			if(this.className.indexOf('activeGraph') > -1) {
 				$(this).removeClass('activeGraph');
 			} else {
 				$(this).addClass('activeGraph');
 			}
 			
 			// Guardamos el data a pasar a la grafica
			var data = {};
			<c:if test="${fn:length(graphBean.numTweets)<25}">
			data["labels"] = labels;
			</c:if>
			data["series"] = [series["actual"]];
			var pos = 1;
			// Pasamos las series actual, y active
			
			$(".activeGraph").each( function () {
				data["series"][pos] = series[$(this).attr("id")];
				pos++;
			});
			chart.update(data);
		}); 


	</script>
	<script src="../js/wow.min.js"></script>
	<script>
            new WOW().init();
    </script>

	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/moment.min.js"></script>
	<script src="../js/utils.js"></script>
</body>
</html>
