<%@page import="com.notebook.bean.Phone"%>
<%@page import="com.notebook.controller.ContactController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="ctt" class="com.notebook.bean.Contact"></jsp:useBean>
<jsp:setProperty property="*" name="ctt"></jsp:setProperty>

<%
	int phoneCount = 1;
	String phone = "";
	long phoneNumber = 0;
	List<Phone> phones = new ArrayList<Phone>();

	if (request.getParameter("phonesize") != null && request.getParameter("phonesize") != "") {
		phoneCount = Integer.parseInt(request.getParameter("phonesize").toString());
	}
	
	for(int i=1; i<=phoneCount; i++) {
		phone = "phone"+i;
		phoneNumber = Long.parseLong(request.getParameter(phone).toString());

		Phone phoneObj = new Phone();
		/* out.println(phone); */
		/* out.println(phoneNumber); */
		phoneObj.setType(phone);
		phoneObj.setPhoneNumber(phoneNumber);
		phones.add(phoneObj);
	}

	int status = ContactController.updateContact(ctt, phones);

	if (status == 1) {
		%>
			<p style="color:green; font-size:20; font-weight:bold;">Contato atualizado com sucesso</p>
			<jsp:include page="dashboard.jsp"></jsp:include>
		<%
	} else {
		%>
			<p style="color:red; font-size:20; font-weight:bold;">Erro ao atualizar contato, tente novamente mais tarde...</p>
			<jsp:include page="dashboard.jsp"></jsp:include>
		<%
	}

%>
