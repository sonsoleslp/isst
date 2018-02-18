<%@page import="edu.iit.itmd515.FormDataBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
	
<!DOCTYPE html>
<html lang="en">
    <head> 
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<!-- Website CSS style -->
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">

		<!-- Website Font style -->
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
		<link rel="stylesheet" href="stylesheet.css">
		<!-- Google Fonts -->
		<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

		<title>Programming Assignment 2</title>
	</head>
	<c:if test="${not empty errorMsg }">
 
	    <script>
	    	alert("${errorMsg}");
	    </script>
   	 <%
    	//remove alert so it does not show again
    	session.setAttribute("errorMsg", null);
     %>
	</c:if>
	<body>
		<div class="container" >
			<div class="row main">
				<div class="main-login main-center">
					<form class="" method="post" action="/ValidateFormData">
						<div class="form-group">
							<label for="name" class="cols-sm-2 control-label">Your name</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" class="form-control" name="name" id="name"  placeholder="Enter your name" required="" pattern=".{4,50}"/>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="ssn" class="cols-sm-2 control-label">Your Social Security Number</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" class="form-control" name="ssn" id="ssn"  required="" pattern="\d{3}-?\d{2}-?\d{4}" placeholder="Enter your Social Security Number"/>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="zipcode" class="cols-sm-2 control-label">Your zipcode</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" class="form-control" name="zipcode" id="zipcode"  placeholder="Enter your zipcode"/>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="email" class="cols-sm-2 control-label">Your email</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" class="form-control" name="email" id="email"  placeholder="Enter your email"/>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="address" class="cols-sm-2 control-label">Your address</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" class="form-control" name="address" id="address"  placeholder="Enter your address" required="" pattern=".{4,50}"/>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="city" class="cols-sm-2 control-label">Your city</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" class="form-control" name="city" id="city"  placeholder="Enter your city" required="" pattern=".{3,50}"/>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="state" class="cols-sm-2 control-label">Your state</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<select name="state">
									   <option value="" disabled selected hidden>Please select a state...</option>
									   <option value="2">AL</option> 
									   <option value="3">AK</option> 
									   <option value="4">AZ</option> 
									   <option value="5">AR</option> 
									   <option value="6">CA</option> 
									   <option value="7">CO</option> 
									   <option value="8">CT</option> 
									   <option value="9">DE</option> 
									   <option value="10">FL</option> 
									   <option value="11">GA</option> 
									   <option value="12">HI</option> 
									   <option value="13">ID</option> 
									   <option value="14">IL</option> 
									   <option value="15">IN</option>
									   <option value="16">IA</option> 
									   <option value="17">KS</option> 
									   <option value="18">KY</option> 
									   <option value="19">LA</option>
									   <option value="20">ME</option> 
									   <option value="21">MD</option> 
									   <option value="22">MA</option>
									   <option value="23">MI</option> 
									   <option value="24">MN</option> 
									   <option value="25">MS</option>
									   <option value="26">MO</option> 
									   <option value="27">MT</option>
									   <option value="28">NE</option>
									   <option value="29">NV</option>
									   <option value="30">NH</option> 
									   <option value="31">NJ</option>
									   <option value="32">NM</option>
									   <option value="33">NY</option>
									   <option value="34">NC</option>
									   <option value="35">ND</option>
									   <option value="36">OH</option>
									   <option value="37">OK</option>
									   <option value="38">OR</option>
									   <option value="39">PA</option>
									   <option value="40">RI</option>
									   <option value="41">SC</option>
									   <option value="42">SD</option>
									   <option value="43">TN</option>
									   <option value="44">TX</option>
									   <option value="45">UT</option>
									   <option value="46">VT</option>
									   <option value="47">VA</option>
									   <option value="48">WA</option>
									   <option value="49">WV</option>
									   <option value="50">WI</option>
									   <option value="51">WY</option>
									   <option value="52">GU</option>
									   <option value="53">PR</option>
									   <option value="54">VI</option>
									</select>
								</div>
							</div>
						</div>

						<div class="form-group ">
							<button type="submit" id="button" class="btn btn-primary btn-lg btn-block login-button">Submit</button>
						</div>
						
					</form>
				</div>
			</div>
		</div>

		 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script>
    
    </script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	</body>
</html>