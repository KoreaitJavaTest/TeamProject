<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>

<jsp:include page="/Layout/header.jsp"></jsp:include>
<c:set var="list" value="${ReViewList.list}"/>
<jsp:useBean id="date" class="java.util.Date"/>


<div class="container" style="margin-top: 50px;">
	<table class="table table-hover table-border">
		<tr>
			<th colspan="5">리뷰 게시판</th>
		</tr>
		<tr>
			<td align="center">글번호</td>
			<td align="center">제목</td>
			<td align="center">글쓴이</td>
			<td align="center">작성일</td>
			<td align="center">조회수</td>
		</tr>
		<c:choose>
			<c:when test="${list.size()==0}">
				<tr>
					<td colspan="5">
						<marquee>테이블에 저장된 글이 없습니다.</marquee>
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="vo" items="${list}">
					<tr onclick="location.href='ReHitUp.nhn?idx=${vo.RE_idx}&currentPage=${ReViewList.currentPage}'" style="cursor: pointer;">
						<td align="center">${vo.RE_idx}</td>					
						<td>${vo.RE_title}</td>					
						<td align="center">${vo.RE_userId}</td>					
						<td align="center">
							<c:if test="${date.year == vo.RE_writeDate.year && date.month == vo.RE_writeDate.month && date.date == vo.RE_writeDate.date}">
								<fmt:formatDate value="${vo.RE_writeDate}" pattern="a h:mm"/>
							</c:if>
							<c:if test="${date.year != vo.RE_writeDate.year || date.month != vo.RE_writeDate.month || date.date != vo.RE_writeDate.date}">
								<fmt:formatDate value="${vo.RE_writeDate}" pattern="yyyy.MM.dd(E)"/>
							</c:if>
						</td>					
						<td align="center">${vo.RE_hit}</td>					
					</tr>
				</c:forEach>		
			</c:otherwise>
		</c:choose>
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
			<td align="right" colspan="5">
				<c:choose>
					<c:when test="${sessionScope.session_id != null }">
						<input type="button" value="리뷰글 작성하기" onclick="location.href='ReViewInsert.nhn'">
					</c:when>
					<c:otherwise>
						<input type="button" value="리뷰글 작성하기" onclick="alert('로그인 후 작성하실 수 있습니다!')">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
</div>	
<jsp:include page="/Layout/footer.jsp"></jsp:include>
					
	

	