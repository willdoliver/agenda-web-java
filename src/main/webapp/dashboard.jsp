<%@page import="com.notebook.controller.ContactController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="com.notebook.dao.*, com.notebook.bean.*"   
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%
	String userId = null;
	String userName = null;
	List<Contact> contacts = new ArrayList<Contact>();

	Cookie[] cookies = request.getCookies();
	if(cookies != null) {
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("userid")) {
				userId = cookie.getValue();
			}
			if (cookie.getName().equals("username")) {
				userName = cookie.getValue();
			}
		}
	}

	if(userId != null && !userId.isEmpty()) {
		// User user = UserDAO.getUserById(userId);
		// request.setAttribute("user", user);
		contacts = ContactController.getAllContacts(Integer.parseInt(userId));
		request.setAttribute("contacts", contacts);
	} else {
		response.sendRedirect("login.html");
	}
%>

<h1>Agenda Topper JallCard</h1>
<h2>Bem Vindo <%= userName %></h2>

<form action="LogoutServlet" method="post">
	<input type="submit" value="Sair" >
</form>


<br>
<br>


<%
	if (contacts == null || contacts.isEmpty()) {
		%>
			<tr>
				<td>Nenhum contato registrado ainda<br>
				Para iniciar um cadastro <a href="createcontactform.jsp?id=<%=userId%>">clique aqui</a></td>
			</tr>
		<%
	} else {
		%>
			<a href="createcontactform.jsp?id=<%=userId%>">Cadastrar Novo Contato</a>
			<br>
			<br>

			<table border="1" style="background-color:#79C7E1">
				<tr>
					<th>Nome</th>
					<th>Sobrenome</th>
					<th>Data de Nascimento</th>
					<th>Parentesco</th>
					<th>Editar</th>
					<th>Remover</th>
					<th>Remover Agora</th>
				</tr>
				<c:forEach items="${contacts}" var="contact">
					<tr>
						<td>${contact.getFirstName()}</td>
						<td>${contact.getLastName()}</td>
						<td>${contact.getDateOfBirth()}</td>
						<td>${contact.getRelativeDegree()}</td>
						<td><a href="editcontactform.jsp?id=${contact.getId()}">Editar</a></td>
						<td><a href="removecontactform.jsp?id=${contact.getId()}">Remover</a></td>
						<td><a href="removecontactnow.jsp?id=${contact.getId()}">Remover Agora</a></td>
					</tr>
						<tbody style="background-color:#A4D2E2">
							<c:forEach items="${contact.getPhoneList()}" var="phone" varStatus="loop">
								<tr>
									<td>Numero ${loop.index+1}: </td>
									<td>${phone.getPhoneNumber()}</td>
								</tr>
							</c:forEach>
						</tbody>
				</c:forEach>
			</table>
		<%
	}
%>
