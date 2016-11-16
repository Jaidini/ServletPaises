<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.io.*,java.util.*,es.salesianos.model.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario País</title>
</head>
<body>
	<form action="welcome" method="post">
		<table align="center" border="1">
			<tr>
				<td align="center"><h2>Formulario</h2></td>
			</tr>
			<tr>
				<td><span>Nombre País:</span> 
		
				<input type="text" name="name"></td>
			</tr>
			<tr>
				<td><span>Tabla de idiomas:</span>
				<select>
					<option value="0">Idiomas...</option>
					<tbody>
						<c:forEach var="idiomas" items="${listaIdiomas}">
							<option><c:out value="${idiomas.nombreDelLenguaje}"/> </option>
						</c:forEach>
					</tbody>
				</select>
				
				</td>
			</tr>
			<tr>
				<td><span>Introducir Idioma:</span> 
				<input type="text" name="course"></td>
			</tr>
			<tr>
				<td align="center"><input type="submit"></td>
			</tr>
		</table>		
	</form>
	</body>
</html>