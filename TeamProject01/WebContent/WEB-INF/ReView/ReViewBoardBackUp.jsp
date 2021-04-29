<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>

<jsp:include page="/Layout/header.jsp"></jsp:include>
<c:set var="list" value="${ReViewList.list}"/>
<jsp:useBean id="date" class="java.util.Date"/>
<script type="text/javascript">
$(function() {
	$('#ReViewSearch').click(function() {
		var searchName = $("select option:selected").val();
		var searchText = $('input[name=searchText]').val();
		if(searchText.trim().length==0){
			alert('검색어를 입력해 주세요');
		}else{
			location.href='ReViewSearch.nhn?searchName='+searchName+'&searchText='+searchText;
		}
	
	})
})
</script>
<c:set var="list" value="${ReViewList.list}"/>

    <div class="container">
		<table class="table table-hover table-bordered">
			<tr>
				<th colspan="3">리뷰 게시판</th>
			</tr>
			<c:forEach var="vo" items="${list}">
			<c:set var="imgN" value="${fn:split(vo.RE_imgNames,',')}"/>
			<tr onclick="location.href='ReHitUp.nhn?idx=${vo.RE_idx}&currentPage=${ReViewList.currentPage}'" style="cursor: pointer;">
				<td style="width: 335px; height: 177px;">
				     <div style=" width: 330px;height: 177px;">
			          <a href="#">
			            <img class="img-fluid rounded mb-3 mb-md-0" src="http://localhost:8009/korea/upload/${imgN[0]}" alt="ㅁㄴㅇㄴㅁㅇ" style="width: 100%;height: 100%">
			          </a>
			        </div>
			    </td>
			    <td align="left">
			    	<h2 style="margin-top: 5px; width: 580px; border: 1px solid red;">
			    	${vo.RE_title}
			    	</h2><br>
			    	<div style="border: 1px solid blue;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;<h4 style="display:inline-block;">${vo.RE_content}</h4></div>
				</td>
				<td align="center">
					<h4 style="margin-top: 75px;">${vo.RE_userId}</h4>
				</td>
			</tr>
			<tr>
			</c:forEach>
			<td align="right" colspan="5">
				<c:choose>
					<c:when test="${sessionScope.session_id != null }">
						<input type="button" class="btn btn-default" value="리뷰글 작성하기" onclick="location.href='ReViewInsert.nhn'">
					</c:when>
					<c:otherwise>
						<input type="button" class="btn btn-default"  value="리뷰글 작성하기" onclick="alert('로그인 후 작성하실 수 있습니다!');location.href='LoginView.nhn'">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="center">
			
			<c:if test="${ReViewList.currentPage > 1}">
				<input class="button button1" type="button" value="맨앞" title="첫 번째 페이지로 이동" onclick="location.href='?currentPage=1'"/>
			</c:if>
			<c:if test="${ReViewList.currentPage <= 1}">
				<input class="button button2" type="button" value="맨앞" disabled="disabled" title="이미 첫 번째 페이지 입니다."/>
			</c:if>
			
			<c:if test="${ReViewList.startPage > 1 }">
				<input class="button button1" type="button" value="이전" title="이전 10페이지로 이동" 
						onclick="location.href='?currentPage=${ReViewList.currentPage - ReViewList.pageSize}'"/>
			</c:if>
			<c:if test="${ReViewList.startPage <= 1 }">
				<input class="button button2" type="button" value="이전" disabled="disabled" title="이미 첫 번째 10페이지 입니다."/>			
			</c:if>
			
			<c:forEach var="i" begin="${ReViewList.startPage}" end="${ReViewList.endPage}" step="1">
				<c:if test="${ReViewList.currentPage == i}">
					<input class="button button2" type="button" value="${i}" disabled="disabled"/>
				</c:if>
				<c:if test="${ReViewList.currentPage != i}">
					<input class="button button1" type="button" value="${i}" onclick="location.href='?currentPage=${i}'"/>
				</c:if>
			</c:forEach>
			
			<c:if test="${ReViewList.endPage < ReViewList.totalPage}">
				<input class="button button1" type="button" value="다음" title="다음 10페이지로 이동" 
						onclick="location.href='?currentPage=${ReViewList.endPage + 1}'"/>
			</c:if>
			<c:if test="${ReViewList.endPage >= ReViewList.totalPage}">
				<input class="button button2" type="button" value="다음" disabled="disabled" title="이미 마지막 10페이지 입니다."/>
			</c:if>
			
			<c:if test="${ReViewList.currentPage < ReViewList.totalPage}">
				<input class="button button1" type="button" value="맨뒤" title="마지막 페이지로 이동" 
						onclick="location.href='?currentPage=${ReViewList.totalPage}'"/>
			
			</c:if>
			<c:if test="${ReViewList.currentPage >= ReViewList.totalPage}">
				<input class="button button2" type="button" value="맨뒤" disabled="disabled" title="이미 마지막 페이지 입니다."/>			
			</c:if>
			
			</td>
		</tr>
		<tr>
			<td colspan="5" align="center">

					<select class="form-control" style="width: 100px; display: inline-block;" name="searchName">
						<option value="제목">제목</option>
						<option value="작성자">작성자</option>
						<option value="제목작성자">제목+작성자</option>
					</select>
					<input type="text" class="form-control" style="width: 300px; display: inline-block;" name="searchText">
					<input type="button" class="btn btn-primary" value="검색하기" id="ReViewSearch">

			</td>
			
		</tr>
		</table>
		<div class="row">
		  <div class="col-sm-6 col-md-4" style="height: 560px; width: 360px;">
		    <div class="thumbnail" style="width: 320px; height: 548px;">
		      <img src="http://localhost:8009/korea/upload/Duke07.gif" alt="..." style="width: 300px;height: 300px;">
		      <div class="caption">
		        <h3>이거 너무 좋아요!</h3>
		        <p>로렘 입숨은 출판이나 그래픽 디자인 분야에서 폰트, 타이포그래피, 레이아웃 같은 그래픽 요소나 시각적 연출을 보여줄 때 사용하는 표준 채우기 텍스트로, 최종 결과물에 들어가는 실제적인 문장 내용이 채워지기 전에 시각 디자인 프로젝트 모형의 채움 글로도 이용된다. </p>
		        <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
		      </div>
		    </div>
		  </div>
	</div>
		
		</div>


<jsp:include page="/Layout/footer.jsp"></jsp:include>
					
	

	