<%@page import="com.notebook.bean.Contact"%>
<%@page import="com.notebook.controller.ContactController"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<%
	String userId = null;
	Contact contact = new Contact();

	Cookie[] cookies = request.getCookies();
	if(cookies !=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("userid")) userId = cookie.getValue();
		}
	}
	if(userId == null) {
		response.sendRedirect("login.html");
	} else {
		String contactId = request.getParameter("id");
		contact = ContactController.getContactById(Integer.parseInt(contactId));
		request.setAttribute("contact", contact);
	}

	
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Editar Contato</title>
	</head>
	<body>
		<h1>Editar Contato</h1>
		<form action="editcontact.jsp" method="post" onsubmit="return validate();">
			<input type="hidden" name="id" value="<%= contact.getId() %>" />
			<table>
				<tr>
					<td>Nome: </td>
					<td><input type="text" id="firstName" name="firstName" value="<%= contact.getFirstName() %>" required/> </td>
				</tr>
				<tr>
					<td>Sobrenome: </td>
					<td><input type="text" id="lastName" name="lastName" value="<%= contact.getLastName() %>" required/> </td>
				</tr>
				<tr>
					<td>Data de Nascimento: </td>
					<td><input type="date" id="dateOfBirth" name="dateOfBirth" value=<%= contact.getDateOfBirth() %> required/></td>
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
						<select name="relativeDegree" required>
							<option value="Outro">Outro</option>
							<option value="Amigo(a)">Amigo(a)</option>
							<option value="Mae/Pai">Mae/Pai</option>
							<option value="Tio(a)">Tio(a)</option>
							<option value="Primo(a)">Primo(a)</option>
							<option value="Sobrinho(a)">Sobrinho(a)</option>
							<option value="Conjuge">Conjuge</option>
						</select>
					</td>
				</tr>
				<tr>
					<tbody id="phoneForm" style="background-color:#fff">
						<input type="hidden" name="phonesize" id="phonesize" value="${contact.getPhoneList().size()}">
						<c:forEach items="${contact.getPhoneList()}" var="phone" varStatus="loop">
							<c:choose>
							    <c:when test="${loop.index+1 == 1}">
							        <tr>
										<td>Numero ${loop.index+1}: </td>
										<td><input type="text" id="phone${loop.index+1}" name="phone${loop.index+1}" value="${phone.getPhoneNumber()}" placeholder="Somente numeros" required/></td>
									</tr>
							    </c:when>
							    <c:otherwise>
							        <tr>
										<td>Numero ${loop.index+1}: </td>
										<td><input type="text" id="phone${loop.index+1}" name="phone${loop.index+1}" value="${phone.getPhoneNumber()}" placeholder="Somente numeros" required/>
										<a href="#" name="removePhone" id="removePhone${loop.index+1}" onclick="removePhone()" size=10>Remove</a></td>
									</tr>
							    </c:otherwise>
							</c:choose>
						</c:forEach>
					</tbody>
				</tr>
				<td><a href="#" type="button" id="addphone" onclick="addPhone();"/>Adicionar Telefone</a></td>
				<tr>
					<td><input type="submit" value="Atualizar Dados"/></td>
				</tr>
			
			</table>
		</form>
	</body>

	<script src="static/editcontact.js"></script>
</html>
