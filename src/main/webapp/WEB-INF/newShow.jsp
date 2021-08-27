<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Create new Show</h2>
	<form:form action="/shows/new" method="post" modelAttribute="tvShow">

		<tr>
			<td><form:label path="name">Title:</form:label></td>
			<td><form:errors path="name" /></td>
			<td><form:input path="name" /></td>
		</tr>
		<br>
		<br>
		<form:label path="network">Network :</form:label>

		<form:select path="network">
			<c:forEach items="${ networks }" var="network">
				<form:option value="${ network.id }">
					<c:out value=" ${ network.name }" />
				</form:option>
			</c:forEach>
		</form:select>
		<br>
		<br>
		<input type="submit" value="Create" />

	</form:form>

</body>
</html>