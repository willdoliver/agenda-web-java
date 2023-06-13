<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
	<html>
		<head>
		<meta charset="UTF-8">
		<title>Criar Usuario</title>
	</head>
	<body>
		<h1>Criar Usuario</h1>
		
		<form action="createuser.jsp" method="post">
			<table>
				<tr>
					<td><span>Nome: </span></td>
					<td><input type="text" name="firstName" placeholder="Insira seu primeiro nome" /></td>
				</tr>
				<tr>
					<td><span>Sobrenome: </span></td>
					<td><input type="text" name="lastName" placeholder="Insira seu ultimo nome"/></td>
				</tr>
				<tr>
					<td><span>Usuario: </span></td>
					<td><input type="text" name="userName" placeholder="Insira seu usuario"/></td>
				</tr>
				<tr>
					<td><span>Email: </span></td>
					<td><input type="email" name="email" placeholder="Insira email"/></td>
				</tr>
				<tr>
					<td><span>Senha: </span></td>
					<td><input type="password" name="password" placeholder="Insira sua senha"/></td>
				</tr>
				<tr>
					<td><span>Repetir Senha: </span></td>
					<td><input type="password" name="passwordVerify" placeholder="Repita sua senha"/></td>
				</tr>
				<tr>
					<td><button type="submit">Criar Usuario</button></td>
				</tr>
			</table>
		</form>
	</body>
</html>