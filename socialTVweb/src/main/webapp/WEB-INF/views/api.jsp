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
	<form action="/api" method="post" accept-charset="utf-8">
		<input required type="text" name="titulo" placeholder="titulo" required/>
		<input required type="text" name="hash" placeholder="Hashtag" required/>
		<input required type="datetime-local" name="fecha_inicio" required placeholder="Fecha de inicio" />
		<input required type="datetime-local" name="fecha_fin" required placeholder="Fecha de fin" />
		<input type="submit" value="Solicitud" />
	</form>
</body>
</html>