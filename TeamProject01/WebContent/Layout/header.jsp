<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
#header{
	border: 1px solid red;
	display: flex;
	align-items: center;
	background: black;
}

#main2{
	border: 1px solid blue;
	display: flex;
	align-items: center;
/* background: black; */
}


#menuLine{
	border: 1px solid pink;
	display: flex;
	align-items: center;
}
.test01{
	border: 1px solid red;
}
.user{
	float: right;
}
span{
	border: 1px solid red;
}
.usercursor{ 
	cursor: pointer;
	font-size: 15pt;
	color: white;
	}
#title{
	font-size: 40pt;
	font-weight: bold;
	cursor: pointer;
}
.menuitem{
	width: 100px;
	cursor: pointer;
	font-size: 13pt;
}

</style>
<link rel="stylesheet" href="Layout/style.css">
<link rel="shortcut icon" href="../images/favicon.ico">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>header</title>
</head>
<body>
<div class="container_fluid" id="main">
	<div class="row" id="header" style="min-height: 50px;">
		<div class="col-xs-8 test01" ></div>	
		<div class="col-xs-4 text-center test01 user">
			<div class="col-xs-3"></div>
			<c:if test = "${sessionScope.session_id eq null }">
				<div class="col-xs-3"><span class="usercursor" onclick = "location.href = 'LoginView.nhn'">로그인</span></div>
				<div class="col-xs-3"><span class="usercursor" onclick = "location.href = 'JoinView.nhn'">회원가입</span></div>
			</c:if>
			<c:if test = "${sessionScope.session_id != null }">
				<div class="col-xs-3"><span class="usercursor">${sessionScope.session_id}님<br/>point: ${sessionScope.session_point}점</span></div>
				<div class="col-xs-3"><span class="usercursor" onclick = "location.href = 'LogoutView.nhn'">로그아웃</span></div>
			</c:if>
			<c:if test = "${sessionScope.session_id != null }">
				<div class="col-xs-3"><span class="usercursor" onclick = "location.href = 'MyEditViewPasswordCheck.nhn'"> 마이페이지</span></div>
			</c:if>
		</div>	
	</div>
	<div class="row" id="main2" style="min-height: 200px;">
		<div class="col-xs-12" align="center"><span id="title" onclick="location.href='index.jsp'">Koreait Team A(img넣거나 그냥글자)</span></div>
	</div>
	<div class="row" id="menuLine" style="min-height:80px; ">
		<div class="col-xs-1"></div>
		<div class="col-xs-10" style="border: 1px solid gray;">
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='?'">전체상품</span></div>
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='ReViewBoard.nhn'">리뷰게시판</span></div>
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='?'">묻고 답하기</span></div>
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='?'">공지사항</span></div>
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='?'">뭘넣을까</span></div>
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='?'">여기도뭘넣지</span></div>
<!-- 			onclikc은 나중에 JS로 통합해서 코드수를 줄일 예정 -->
		</div>
		<div class="col-xs-1"></div>
	</div>
</div>		

		
		
