<%@page import="com.Team.Shop.fileupload.FileDAO"%>
<%@page import="com.Team.Shop.fileupload.FileVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
//	테이블에 저장된 업로드된 전체 파일 정보를 얻어온다.
	ArrayList<FileVO> fileList = new FileDAO().getList();
	for (FileVO vo : fileList) {
%>
	<a href="<%=request.getContextPath()%>/downloadAction?file=<%=URLEncoder.encode(vo.getFileRealName(), "UTF-8")%>">
		<%=vo.getFileName()%>(다운로드 횟수 : <%=vo.getDownloadCount()%>)
	</a><br/>
<%
	}
%>

	<a href="index.jsp">돌아가기</a>

</body>
</html>