<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Test - Last Tweet con #AppITSS</h2>
	<p>Last Tweet: <c:out value="${tweet }"/></p>
	<br>
	<p> <b>Audiencia:</b> </p>
	<c:forEach items="${audiencia }" var="aud">
		<p>Hora: <c:out value="${aud.hora }"/></p>
		<p>Count: <c:out value="${aud.count }"/></p>
		<br>
	</c:forEach>
</body>
</html>