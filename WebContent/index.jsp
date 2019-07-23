<%@page import="model.Person"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="Operations" method="POST">
		<input type="submit" name="action" value="Listar"> <input
			type="submit" name="action" value="Nuevo">
	</form>

	<table>
		<tr>
			<th>Id</th>
			<th>Nombres</th>
			<th>Foto</th>
			<th>Acciones</th>
		</tr>
		<%
			if (request.getAttribute("peopleList") != null) {
				List<Person> people = (List<Person>) request.getAttribute("peopleList");

				for (Person p : people) {
		%>
		<tr>
			<td><%=p.getId()%></td>
			<td><%=p.getNames()%></td>
			<td><img src="ControllerPhoto?idPerson=<%=p.getId()%>" width="150" height="150"></td>
			<td>Actualizar | Eliminar</td>
		</tr>
		<%
			}
		}
		%>
	</table>
</body>
</html>