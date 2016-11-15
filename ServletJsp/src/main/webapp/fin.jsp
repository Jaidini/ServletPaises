<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Esto Insertas</title>
</head>
<body>
	<form action="volver" method="post">
		<b><h1>Respuesta del formulario</h1></b>
		<b>Nombre:&nbsp</b><%= request.getParameter("name") %><br>
		<b>Idioma:&nbsp</b><%= request.getParameter("course") %><br>
		<a href="datos.jsp"><input type="button" value="Volver"></a>
		<a href="listado.jsp"><input type="button" value="Ver listado"></a>
		
		<input type="hidden" value="<%= request.getParameter("name") %>" name="user">
		<input type="submit" value="Borrar">
	</form>
	
</body>
</html>