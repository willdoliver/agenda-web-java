<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<%
	String userId = null;
	int userIdInt = 0;

	Cookie[] cookies = request.getCookies();
	if(cookies !=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("userid")) userId = cookie.getValue();
		}
	}
	if(userId == null) {
		response.sendRedirect("login.html");	
	} else {
		userIdInt = Integer.parseInt(userId);
		request.setAttribute("userId", userIdInt);
	}
%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Criar Novo Contato</title>
	</head>
	<body>
		<h1>Criar Novo Contato</h1>
		
		<form action="createcontact.jsp" method="post" onsubmit="return validate();">
			<input type="hidden" name="userId" value="<%= userIdInt %>" />
			<table>
				<tr>
					<td>Nome: </td>
					<td><input type="text" id="firstName" name="firstName" placeholder="Insira seu primeiro nome" required/> </td>
				</tr>
				<tr>
					<td>Sobrenome: </td>
					<td><input type="text" id="lastName" name="lastName" placeholder="Insira seu ultimo nome" required/> </td>
				</tr>
				<tr>
					<td>Data de Nascimento: </td>
					<td><input type="date" id="dateOfBirth" name="dateOfBirth" required/></td>
				</tr>
				<tr>
					<td>Parentesco: </td>
					<td>
						<select name="relativeDegree" required>
							<option>Outro</option>
							<option>Amigo(a)</option>
							<option>Mae/Pai</option>
							<option>Tio(a)</option>
							<option>Primo(a)</option>
							<option>Sobrinho(a)</option>
							<option>Conjuge</option>
						</select>
					</td>
				</tr>
				<tr>
					<tbody id="phoneForm" style="background-color:#fff">
						<input type="hidden" name="phonesize" id="phonesize">
						<tr>
							<td>Numero 1: </td>
							<td><input type="text" id="phone1" name="phone1" placeholder="Somente numeros" required/></td>
						</tr>
					</tbody>
				</tr>
				<td><a href="#" type="button" id="addphone" onclick="addPhone();"/>Adicionar Telefone</a></td>
				<tr>
					<td><input type="submit" value="Criar contato"/></td>
				</tr>
			
			</table>
		</form>
	
	</body>
	
	<script src="static/editcontact.js"></script>
</html>
