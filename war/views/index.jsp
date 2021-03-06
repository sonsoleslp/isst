<%@page import="es.upm.dit.isst.socialTV.bs.beans.AllProgramsBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/favicon.ico">


    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!--<script src="../../assets/js/ie-emulation-modes-warning.js"></script>-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <!-- Error de login -->
    <c:if test="${not empty errorMsg }">
 
	    <script>
	    	alert("${errorMsg}");
	    </script>
   	 <%
    	//quitar la alerta para que no salga otra vez
    	session.setAttribute("errorMsg", null);
     %>
	</c:if>
    <title>SocialTV</title>

    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css" >
    <link rel='stylesheet' href='/css/style.css' />


    <!-- Custom styles for this template -->
    <link href="/css/dashboard.css" rel="stylesheet">
    <link href='/css/fonts.css' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="/css/animate.css">


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
     <a class="navbar-brand" href="/" style="margin:2px; padding:0;"><img src="img/logo.png" height="40px"></a>
      <a class="navbar-brand" href="/">SocialTV</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
    <% if (!"".equals(session.getAttribute("user"))) { %>
      <ul class="nav navbar-nav">
       <li class="active"><a href="/"><i class="fa fa-home"></i> Home</a></li>
        <li><a href="/top"><i class="fa fa-star-o"></i> Top 5</a></li>
        <li><a href="/calendar"><i class="fa fa-line-chart"></i> Calendar</a></li> 
         <% if ("admin".equals(session.getAttribute("rol"))) { %>
         <li><a href="/apitest"><i class="fa fa-wrench"></i> Config</a></li> 
 		<% }%>
      </ul>
      <% }%>
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
 <div class="jumbotron wow bounceInDown">
     
    </div>
  <div class="container-fluid">
   
    <div style="font-size:17px;"class="row">
      <div class="text-center col-xs-12 col-md-4 wow bounceIn">
        <i class=" fa fa-twitter fa-5x"></i><br>
        <p>Monitoriza las menciones en Twitter de los hashtags de los programas más populares en la región que decidas.</p>
      </div>
      <div class="text-center col-xs-12 col-md-4 wow bounceIn">
        <i class=" fa fa-bar-chart fa-5x"></i><br>
        <p>Obtén un informe de las menciones a cada programa durante su emisión, pudiendo comprobar en qué momento  han llegado a su nivel más alto</p>
      </div>
      <div class=" text-center col-xs-12 col-md-4 wow bounceIn">
        <i class=" fa fa-trophy fa-5x"></i><br>
        <p>Conoce los programas más populares. Comprueba quién está generando mayor impacto social.</p>
      </div>
  

</div></div>
    
       <script src="/js/jquery.min.js"></script>
       <script src="/js/bootstrap.min.js"></script>
       <script src="/js/wow.min.js"></script>
            <script>
            new WOW().init();
            </script>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
 
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!--<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>-->
  

<script type="text/javascript">(function () {
        return window.SIG_EXT = {};
      })()</script>
  </body>
</html>


