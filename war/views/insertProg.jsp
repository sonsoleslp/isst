<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html class="signinhtml" style="background-position:top;">

<head>

  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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

      <title>SocialTV</title>

      <link rel="stylesheet" href="css/font-awesome.min.css">
      <link rel="stylesheet" href="css/bootstrap.min.css" >
      <link rel='stylesheet' href='css/style.css' />


      <!-- Custom styles for this template -->
      <link href="css/dashboard.css" rel="stylesheet">
      <link href='css/fonts.css' rel='stylesheet' type='text/css'>
      <link rel="stylesheet" href="css/animate.css">


    </head>
    <body>



 <nav class="navbar-default navbar-fixed-top wow slideInDown">
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
     <li><a href="/top"><i class="fa fa-star-o"></i> Top 5</a></li>
     <li><a href="/calendar"><i class="fa fa-line-chart"></i> Calendar</a></li> 
     <li class="active"><a href="/apitest"><i class="fa fa-wrench"></i> Config</a></li>

	</ul>
    <ul class="nav navbar-nav navbar-right">
    <!--  <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li> --> 
      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </ul>
  </div>
</div>
</nav>
<br>

<div class="container-fluid">

  <div class="row">
    <div  class="text-center col-xs-12 col-md-6  col-lg-6 "><br><br><br>
     <div  class="login-panel panel panel-default wow zoomIn">
      <div class="panel-heading">
       <h3 class="panel-title" style="font-family:'Pacifico'; font-size:16px;" >Crear monitorización</h3>
      </div>
      <div class="panel-body">

        <form action="/apitest" style="text-align:left;" method="post" role="form" accept-charset="utf-8">
           <div class="form-group">
                Título
                <input class="form-control" required type="text" name="titulo" placeholder="Título" autofocus>
           </div>
          <div class="row">
            <div class="col-xs-12 col-lg-6">
              <div class="form-group">
            Episodio
            <input class="form-control" required type="text" name="episode" placeholder="Episodio" >
          </div>
             </div>
            <div class="col-xs-12 col-lg-6">
            <div class="form-group">
            Hashtag
              <input class="form-control" required type="text" name="hash" placeholder="Hashtag" >
            </div>       
            </div>
           
          </div>
          <div class="row">
            <div class="col-xs-12 col-lg-6">
              <div class="form-group">
            Inicio
                <input class="form-control" type="datetime-local" name="fecha_inicio" placeholder="Fecha de inicio" >
              </div>
             </div>
            <div class="col-xs-12 col-lg-6">
              <div class="form-group">
              Fin
                <input class="form-control" type="datetime-local" name="fecha_fin" placeholder="Fecha de fin" >
              </div>       
            </div>
           
          </div>
          
              
          
               
          <input class="btn btn-lg btn-primary btn-block" type="submit" value="Crear" />

          <!-- Button trigger modal -->
          
        </form>
        <br>
    </div>
  </div>
</div>
<div  class="text-center col-xs-12 col-md-6  col-lg-6 "><br><br><br>
 <div  class="login-panel panel panel-default wow zoomIn">
  <div class="panel-heading">
    <h3 class="panel-title" style="font-family:'Pacifico'; font-size:16px;" >Monitorizaciones programadas</h3>
  </div>
  <div class="panel-body">
    
    <table style="border:0;" class="table table-striped">
     <tr>
      <td><b>#</b></td>
      <td><b>Titulo</b></td>
      <td><b>Hashtag</b></td>
      <td><b>Count</b></td>
      <td><b>LastTweet</b></td>
    </tr>
    <c:forEach items="${progs }" var="prog">
    <tr>
      <td><a href="/grafica/<c:out value="${prog.primaryKey }"/>">Ver</a></td>
      <td><c:out value="${prog.titulo }"/></td>
      <td><c:out value="${prog.hashtag }"/></td>
      <td><c:out value="${prog.count }"/></td>
      <td><c:out value="${prog.lastTweet }"/></td>
    </tr>
  </c:forEach>
</table>
</div>
</div>
</div>
<div>
</div>


</div>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/wow.min.js"></script>
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


