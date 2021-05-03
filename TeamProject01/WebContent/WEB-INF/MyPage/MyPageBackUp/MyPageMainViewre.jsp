<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/Layout/header.jsp"></jsp:include>
	<div class = "container_fluid">
		<div class = "col-xs-1"></div>
		<div class = "col-xs-2">
			<nav id = "admin_list" style = "float: left; border: 1px solid red; margin-top: 50px;">
				<ul>
					<li onclick = "location.href='MyEditViewPasswordCheck.nhn'">내정보 수정</li>
					<li onclick = "location.href='MyListViewPage.nhn'">리뷰게시글 관리</li>
					<li onclick = "location.href='MyQnAviewPage.nhn'">질문게시글 관리</li>
				</ul>
			</nav>
		</div>
		<div class = "col-xs-8">
			<div id = "admin_body" style = "float: left; border: 1px solid red; margin-top: 50px;" >
				
			</div>
		</div>
		<div class = "col-xs-1"></div>
	</div>



<jsp:include page="/Layout/footer.jsp"></jsp:include>