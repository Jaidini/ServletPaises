<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="welcome" method="post">
		<table align="center" border="1">
			<tr>
				<td align="center"><h2>Formulario</h2></td>
			</tr>
			<tr>
				<td><span>Nombre:</span> 
		
				<input type="text" name="name"></td>
			</tr>
			<tr>
				<td><span>Fecha nac:</span> 
		
				<input type="date" name="dob"></td>
			</tr>
			<tr>
				<td><span>Curso:</span> 
				<input type="text" name="course"></td>
			</tr>
			<tr>
				<td align="center"><input type="submit"></td>
			</tr>
		</table>		
	</form>
	</body>
</html>