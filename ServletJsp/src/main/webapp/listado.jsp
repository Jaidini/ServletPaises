<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.*,java.util.*,es.salesianos.model.*" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Idiomas</title>
</head>
<body>
<form action="listado" method="post">
	<h1>AQUÍ ESTÁN LOS IDIOMAS</h1>
	<input type="submit" value="Ver listado">
	<a href="datos.jsp"><input type="button" value="Volver"></a>
</form>

<% 
 List<User> users = (List<User>)request.getAttribute("listAllUsers");
 //out.println(users);
 pageContext.setAttribute("users", users);
%>

<br/>


<table border="1">
	<thead>
		<tr>
			<td>Idioma</td>
			<td>País</td>
			<td>Borrar Idioma</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="user1" items="${listAllUsers}">
			<tr>
				<td><c:out value="${user1.languaje}"/> </td>
				<td><c:out value="${user1.name}"/></td>
				<td><a name="user" href="volver?user=${user1.languaje}">Borrar</a></td>
	    	</tr>
		</c:forEach>
	</tbody>
</table>


</body>
</html>