<%@page import="es.upm.dit.isst.socialTV.bs.beans.AllProgramsBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='css/fonts.css' rel='stylesheet' type='text/css'>
<script src="js/jquery.min.js"></script>
<link rel="stylesheet" href="/css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/default.css" />
<link rel="stylesheet" type="text/css" href="css/component.css" />
<script src="js/modernizr.custom.js"></script>
<link href='css/fullcalendar.css' rel='stylesheet' />
<link href='css/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='js/moment.min.js'></script>
<script src='js/jquery.min.js'></script>
<script src='js/fullcalendar.min.js'></script>
 <link rel="stylesheet" href="css/animate.css">
 <link rel='stylesheet' href='css/style.css' />
 
<script>

	$(document).ready(function() {
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,basicWeek,basicDay'
			},
			defaultDate: new Date(),
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			events: [

				<c:forEach items="${allProgramsBean.progs}" var="prog" varStatus="status">  
				{title: '${prog.titulo}',
				 start:  '${prog.fechaInicio}',
				 end: '${prog.fechaFin}',
				 url: '/grafica/${prog.primaryKey}'
				   
				}
				<c:if test="${!status.last}">    
				  ,    
				</c:if>  
				</c:forEach>  

			]
		});
		
	});

</script>

</head>
<body id="bodycalendar">

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
    
      <ul class="nav navbar-nav">
       <li><a href="/"><i class="fa fa-home"></i> Home</a></li>
        <li><a href="/top"><i class="fa fa-star-o"></i> Top 5</a></li>
        <li class="active"><a href="/calendar"><i class="fa fa-line-chart"></i> Calendar</a></li> 
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
    <div class="container "><BR>
        <div class="cal1 "></div>



	<div id='calendar' class="wow slideInDown"></div>
    <script src="js/classie.js"></script>
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
        <script src="js/classie.js"></script>
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

<style>
tbody>tr {
     background-color: transparent;
}
</style>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/underscore-min.js"></script>
		<script src="js/moment.min.js"></script>    
		<script src="js/wow.min.js"></script>
        <script>
        new WOW().init();
        </script>
</body>
</html>
	