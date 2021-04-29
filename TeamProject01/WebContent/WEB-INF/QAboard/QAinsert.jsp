<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="vo" class="com.Team.QAboard.QAboardVo">
	<jsp:setProperty property="*" name="vo"/>
</jsp:useBean>
<!DOCTYPE html>
<jsp:include page="/Layout/header.jsp"></jsp:include>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="insertOK.nhn" method="post">
		<table width="600" align="center" border="1" cellpadding="5" cellspacing="0">
			<tr>
				<th colspan="3">상품 Q&A</th>
			</tr>
			<tr>
				<td width="200">
					<label for="q_userid">이름</label>
				</td>
				<td colsapn="2" width="400">
					<input id="q_userid" type="text" name="q_userid"/>
				</td>
			</tr>	
			<tr>
				<td>
					<label for="q_title">제목</label>
				</td>
				<td colspan="2">
					<input id="q_title" type="text" name="q_title"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="q_content">내용</label>
				</td>
				<td colspan="2">
					<textarea id="q_content" rows="10" cols="65" name="q_content" style="resize:none;"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="submit" value="저장하기"/>
					<input type="button" value="돌아가기" onclick="history.back()"/>
				</td>
			</tr>
	</body>
</html>
<jsp:include page="/Layout/footer.jsp"></jsp:include>

