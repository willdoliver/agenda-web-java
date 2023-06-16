<%@page import="com.notebook.controller.ContactController"%>

<jsp:useBean id="c" class="com.notebook.bean.Contact"></jsp:useBean>
<jsp:setProperty property="*" name="c"></jsp:setProperty>

<%
	int status = ContactController.removeContact(c);

	if (status == 1) {
		%>
			<p style="color:green; font-size:20; font-weight:bold;">Contato removido com sucesso</p>
			<jsp:include page="dashboard.jsp"></jsp:include>
		<%
	} else {
		%>
			<p style="color:red; font-size:20; font-weight:bold;">Erro ao remover contato, tente novamente mais tarde...</p>
			<jsp:include page="dashboard.jsp"></jsp:include>
		<%
	}
%>
