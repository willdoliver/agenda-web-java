<%@page import="com.notebook.controller.UserController"%>
<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="com.notebook.dao.UserDAO, com.notebook.bean.User"
%>

<%
	String firstname = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String userName = request.getParameter("userName");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	String checkPassword = request.getParameter("passwordVerify");

	if (password.equals(checkPassword) && password.length() >= 8) {
		UserController userController = new UserController();
		User user = new User();
		int status = 0;

		// TODO
		// Check password rules

		user.setFirstName(firstname);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setEmail(email);
		user.setPassword(userController.encodePassword(password));
		user.setCreatedAt(new Date());
		
		status = userController.createUser(user);
		request.setAttribute("status", status);
		
		if (status == 1) {
			%>
				<p>Usuario criado com sucesso!</p>
				<jsp:include page="login.html"></jsp:include>
			<%
		} else {
			%>
				<p>Erro ao Criar Usuario</p>
				<jsp:include page="createuserform.jsp"></jsp:include>
			<%
		}

	} else {
		String message = "Senha invalida, favor verificar:<br>";
		if (password.length() < 8) {
			message += "Senha deve possuir no minimo 8 caracteres";
		}
		%>
			<p style="color:red"><%=message%></p>
			<jsp:include page="createuserform.jsp"></jsp:include>
		<%
	}
%>
