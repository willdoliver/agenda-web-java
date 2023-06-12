<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<%
	String userId = null;
	Cookie[] cookies = request.getCookies();
	if(cookies !=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("userid")) userId = cookie.getValue();
		}
	}
	if(userId == null) response.sendRedirect("login.html");
%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Remover Contato</title>
	</head>
	<body>
		<%@ page import="com.notebook.bean.Contact, com.notebook.dao.ContactDAO" %>
		<%
			String id = request.getParameter("id");
			Contact contact = ContactDAO.getContactById(Integer.parseInt(id));
			request.setAttribute("contact", contact);
		%>
		
		<h1>Deseja realmente remover o seguinte contato?</h1>
		
		<form action="removecontact.jsp" method="post">
			<input type="hidden" name="id" value="<%= contact.getId() %>" />
			<table>
				<tr>
					<td>Nome: </td>
					<td><input type="text" name="name" value="<%= contact.getFirstName() %>" disabled/> </td>
				</tr>
				<tr>
					<td>Sobrenome: </td>
					<td><input type="text" name="name" value="<%= contact.getLastName() %>" disabled/> </td>
				</tr>
				<tr>
					<td>Data de Nascimento: </td>
					<td><input type="date" name="dateOfBirth" value=<%= contact.getDateOfBirth() %> disabled/></td>
				</tr>
				<tr>
					<td>Data Criacao: </td>
					<td><input type="date" name="createdAt" value=<%= contact.getCreatedAt() %> disabled/></td>
				</tr>
				<tr>
					<td>Data Atualizacao: </td>
					<td><input type="date" name="updatedAt" value=<%= contact.getUpdatedAt() %> disabled/></td>
				</tr>
				<tr>
					<td>Parentesco: </td>
					<td>
						<select name="relativeDegree" disabled>
							<option>Mae/Pai</option>
							<option>Tio(a)</option>
							<option>Primo(a)</option>
							<option>Sobrinho(a)</option>
							<option>Conjuge</option>
							<option>Amigo(a)</option>
							<option>Outro</option>
						</select>
					</td>
				</tr>
				<tr>
					<tbody style="background-color:#fff">
						<c:forEach items="${contact.getPhoneList()}" var="phone">
							<tr>
								<td>Numero ${phone.getType()}: </td>
								<td>${phone.getPhoneNumber()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</tr>
				<tr>
					<td><input type="submit" value="Sim, Deletar Contato"/></td>
				</tr>
			</table>
		</form>
	</body>
</html>