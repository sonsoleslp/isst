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
	<h2> Hashtag de prueba</h2>
	<form action="/apitest" method="post" accept-charset="utf-8">
		<input required type="text" name="titulo" placeholder="Título" required/>
		<input required type="text" name="episode" placeholder="Episodio" required/>
		<input required type="text" name="hash" placeholder="Hashtag" required/>
		<input required type="date" name="fecha_inicio" required placeholder="Fecha de inicio" />
		<input required type="time" name="hora_inicio" placeholder="Hora de inicio" required/>
		<input required type="number" name="duracion" required placeholder="Duración en minutos" />
		<input type="submit" value="Solicitud" />
	</form>
	<br>
	<h2>Lista</h2>
	<table>
	<tr>
		<td><b>#</b></td>
		<td><b>Titulo</b></td>
		<td><b>Hashtag</b></td>
		<td><b>Count</b></td>
		<td><b>LastTweet</b></td>
	</tr>
	<c:forEach items="${progs }" var="prog">
	<tr>
		<td><c:out value="${prog.primaryKey }"/></td>
		<td><c:out value="${prog.titulo }"/></td>
		<td><c:out value="${prog.hashtag }"/></td>
		<td><c:out value="${prog.count }"/></td>
		<td><c:out value="${prog.lastTweet }"/></td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>