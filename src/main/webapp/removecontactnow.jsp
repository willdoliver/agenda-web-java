<%@page import="com.notebook.controller.ContactController"%>

<%
	String id = request.getParameter("id");
	ContactController.removeContactById(Integer.parseInt(id));
	response.sendRedirect("dashboard.jsp");
%>