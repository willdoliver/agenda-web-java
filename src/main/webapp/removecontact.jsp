<%@page import="com.notebook.dao.ContactDAO" %>
<jsp:useBean id="c" class="com.notebook.bean.Contact"></jsp:useBean>
<jsp:setProperty property="*" name="c"></jsp:setProperty>

<%
	int status = ContactDAO.removeContact(c);

	if (status == 1) {
		%>
			<p style="color: green">Contato removido com sucesso</p>
			<jsp:include page="dashboard.jsp"></jsp:include>
		<%
	} else {
		%>
			<p style="color: red">Erro ao remover contato, tente novamente mais tarde...</p>
			<jsp:include page="dashboard.jsp"></jsp:include>
		<%
	}
%>
