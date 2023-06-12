<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.notebook.dao.ContactDAO"%>
<%@page import="com.notebook.bean.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

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
		// out.println(phone);
		// out.println(phoneNumber);
		phoneObj.setType(phone);
		phoneObj.setPhoneNumber(phoneNumber);
		phones.add(phoneObj);
	}

	int status = ContactDAO.createContact(ctt, phones);
	
	if (status == 1) {
		%>
			<p style="color: green">Contato criado com sucesso</p>
			<jsp:include page="dashboard.jsp"></jsp:include>
		<%
	} else {
		%>
			<p style="color: red">Erro ao criar contato, tente novamente mais tarde...</p>
			<jsp:include page="dashboard.jsp"></jsp:include>
		<%
	}
%>