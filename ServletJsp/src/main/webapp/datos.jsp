<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="es.salesianos.model.Lenguaje"%>
<%@page import="es.salesianos.service.Repository" %>

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
					<%
						List<Lenguaje> listaIdiomas = new ArrayList<Lenguaje>();
						listaIdiomas = Repository.listaLenguaje();
						for(int i=0; i<listaIdiomas.size(); i++){
							out.println("<option value='" + listaIdiomas.get(i).getName() + "'>" + listaIdiomas.get(i).getName());
						}
					%>
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