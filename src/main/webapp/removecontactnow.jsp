<%@page import="com.notebook.dao.ContactDAO" %>

<%
	String id = request.getParameter("id");
	ContactDAO.removeContactById(Integer.parseInt(id));
	response.sendRedirect("dashboard.jsp");
%>