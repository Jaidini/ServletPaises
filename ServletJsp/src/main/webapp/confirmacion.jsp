<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WARNING!!!!</title>
</head>
<body>
	<form action="borrar" method="post">
		<b><h1>Página de Confirmación</h1></b>
		<b>¿Está usted seguro de borrar el siguiente idioma? Tenga en cuenta que muchas personas se quedarían sin habla...</b><br>
		
		<input type="hidden" value="<%= request.getParameter("user") %>" name="user">
		
		<input type="submit" value="Sí">
		<a href="listado.jsp"><input type="button" value="No"></a>
	</form>
	
</body>
</html>