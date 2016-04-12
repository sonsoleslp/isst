<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="graphBean" class="es.upm.dit.isst.socialTV.bs.beans.GraphBean">
</jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />

<link href='css/fonts.css' rel='stylesheet' type='text/css'>

<script src="js/jquery.min.js"></script>
<!-- <link rel="stylesheet" href="css/style.css"> -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/default.css" />
<link rel="stylesheet" type="text/css" href="css/component.css" />
<script src="js/modernizr.custom.js"></script>
<link href='css/fullcalendar.css' rel='stylesheet' />
<link href='css/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src="js/moment.min.js"></script>
<script src="js/jquery.min.js"></script>

<link href="css/chartist.css" rel="stylesheet">
<link rel="stylesheet" href="css/animate.css">


</head>
<body id="graphbody">
	
  <nav style="overflow-y:auto;" class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-right" id="cbp-spmenu-s2">
  
      <h3>Programas</h3>
      <a href="top5.html"><b>TOP 5</b></a>
      <a href="#">Modern Family</a>
      <a href="#">HIMYM</a>
      <a href="#">Friends</a>
      <a href="#">TBBT</a>
      <a href="#">New Girl</a>
      <a style="border-bottom:none; " href="calendar.html">All</a>
      
      <h3>Otros</h3>
      <a href="#">Settings</a>
      <a href="#">Logout</a>

    </nav>
    <div class="container "><BR>
        <div class="cal1 "></div>

    </div>
   
           <button id="showRight">
            <span id="menuicon" style="color:#777; font-size: 40px;" class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>
            <span id="closeicon" style="color:#777; font-size: 40px;" class="invisible glyphicon glyphicon-remove" aria-hidden="true"></span>
                        </button>
       
     <div class="logotype" >
      <img class="branding" src="img/logo.png" height="40px">
     <a class="branding" href="index.html">SocialTV</a>     
</div>


	 <div class="row">
      <div class="col-xs-12 col-lg-8 col-lg-push-2 ">
        <div class="wow slideInLeft" style="float:left;">
        <h1> Modern Family<h1>
        <h2  style="color:#1685CD;"> #MF2016</h2>
        <h3>Jueves, 10 de Marzo  -  20.00-20.30<br><br></h3>
        <a href="co.html"><button class="btn btn-default" >Datos regionales</button></a>
      </div>

        <img style="width:200px;float:right;" class="mediafoto wow slideInRight" src="img/fat.jpg">
        <br style="clear:both;">
      	<hr style="background-color:grey; height:2px;">
        <div class="ct-chart ct-golden-section" id="chart1"></div>
      </div>
    </div>
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
   <script src="js/jquery.min.js"></script>
       <script src="js/bootstrap.min.js"></script>
       <script src="js/chartist.min.js"></script>
      <script>


      /*var chart = new Chartist.Line('.ct-chart', {
       labels: ['20:00','20:05', '20:10','20:15', '20:20', '20:25', '20:30'],
         series: [
           
           [2000, 10000, 14345, 2341, 13233, 16445, 13452]
         ]
       }, {
       high: 20000,
       low: 0,
       fullWidth: true,
       showArea: true,
       showPoint: true,
       height:'300px'

      });*/
      
      var chart = new Chartist.Line('.ct-chart', {
          labels: ${graphBean.strHoras[0]},
            series: [
              
				${graphBean.numTweets[0]}
            ]
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
       <script src="js/wow.min.js"></script>
            <script>
            new WOW().init();
            </script>

    <script src="js/bootstrap.min.js"></script>
    <script src="js/underscore-min.js"></script>
    <script src="js/moment.min.js"></script>

</body>
</html>
