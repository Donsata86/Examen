<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome</title>
</head>
<body>
	<a href="/logout">Logout</a>

	<h1>Welcome, <c:out value="${user.name} ${user.lastName}" /></h1>


<form:form action="/shows/" modelAttribute="ninjaByDojo">
		<h3>
			TV Shows
		</h3>
		 <table Style="border: solid black;">
				<thead >
					<tr>
						<th ><h4>Show</h4></th>
						<th ><h4>Network</h4></th>
				
					</tr>					        		
				</thead>
				<tbody>
					<c:forEach items="${ tvShows }" var="tvShow">
						
						<tr>
							<td><c:out value="${ tvShow.name }"/></td>
							<td><c:out value="${ tvShow.networkId.name }"/></td>
							
						</tr>
						
												
					</c:forEach>
				</tbody>
			</table>
	</form:form>

</body>
</html>