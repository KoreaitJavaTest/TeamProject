<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int sh_idx = Integer.parseInt(request.getParameter("sh_idx"));
	
// 	int currentPage = Integer.parseInt(request.getParameter("currentPage"));
	response.sendRedirect("selectProduct.nhn?sh_idx=" + sh_idx);
 %>
</body>
</html>