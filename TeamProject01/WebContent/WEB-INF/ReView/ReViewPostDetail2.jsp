<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<jsp:include page="/Layout/header.jsp"></jsp:include>
<style>
.setImg{
	height: 200px;
}

.media-carousel 
{
  margin-bottom: 0;
  padding: 0 40px 0px 40px;
  margin-top: 10px;
}
/* Previous button  */
.media-carousel .carousel-control.left 
{
  left: -12px;
  background-image: none;
  background: none repeat scroll 0 0 #222222;
  border: 4px solid #FFFFFF;
  border-radius: 23px 23px 23px 23px;
  height: 40px;
  width : 40px;
  margin-top: 30px
}
/* Next button  */
.media-carousel .carousel-control.right 
{
  right: -12px !important;
  background-image: none;
  background: none repeat scroll 0 0 #222222;
  border: 4px solid #FFFFFF;
  border-radius: 23px 23px 23px 23px;
  height: 40px;
  width : 40px;
  margin-top: 30px
}
/* Changes the position of the indicators */
.media-carousel .carousel-indicators 
{
  right: 50%;
  top: auto;
  bottom: 0px;
  margin-right: -19px;
}
/* Changes the colour of the indicators */
.media-carousel .carousel-indicators li 
{
  background: #c0c0c0;
}
.media-carousel .carousel-indicators .active 
{
  background: #333333;
}
.media-carousel img
{
  width: 250px;
  height: 150px
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	  $('#media').carousel({
	    pause: true,
	    interval: false,
	  });
$('.dropdown-toggle').dropdown()
	});
</script>
<jsp:include page="/WEB-INF/ReView/ReViewModal.jsp"></jsp:include>
<div class="container" style="margin-top: 50px;">
	<table align="center" class="table table-hover">
		<thead>
			<tr>
				<th>상세보기</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td width="10%">제목</td>
				<td>${vo.RE_title}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${vo.RE_userId}</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td><fmt:formatDate value="${vo.RE_writeDate}" pattern="yyyy.MM.dd(E)"/></td>
			</tr>
			<tr>
				<td colspan="2" height="200">
				<!-- 상품이 보여질 테이블 행 -->
				<div class='col-md-12 container'>
				      <div class="carousel media-carousel" id="media">
				        <div class="carousel-inner">
				          <div class="item  active">
				            <div class="row">
				            <c:if test="${vo.RE_img==1}">
				              <div class="col-md-2">
				              </div>          
				              <div class="col-md-8">
				                <a class="thumbnail" href="" data-toggle="modal" data-target="#myModal1"><img alt="" style="height: 200px;" src="${fileName1}"></a>
								<!-- Modal -->
				              </div>          
				              <div class="col-md-2">
				              </div>          
				    		</c:if>
				            <c:if test="${vo.RE_img==2}">
				              <div class="col-md-6">
				                <a class="thumbnail" style="" href="" data-toggle="modal" data-target="#myModal2_1"><img alt="" style="height: 200px;" class="setImg" src="${fileName1}"></a>
				                <!-- Modal -->
				              </div>          
				              <div class="col-md-6">
				                <a class="thumbnail" style="" href="" data-toggle="modal" data-target="#myModal2_2"><img alt="" style="height: 200px;" class="setImg" src="${fileName2}"></a>
				                <!-- Modal -->
				              </div> 
				              
				                       
				    		</c:if>
				            <c:if test="${vo.RE_img==3}">
				              <div class="col-md-4">
				                <a class="thumbnail" style="" href="" data-toggle="modal" data-target="#myModal3_1"><img alt="" style="height: 200px;" class="setImg" src="${fileName1}"></a>
				                <!-- Modal -->
				              </div>          
				              <div class="col-md-4">
				                <a class="thumbnail" style="" href="" data-toggle="modal" data-target="#myModal3_2"><img alt="" style="height: 200px;" class="setImg" src="${fileName2}"></a>
				                <!-- Modal -->
				              </div>          
				              <div class="col-md-4">
				                <a class="thumbnail" style="" href="" data-toggle="modal" data-target="#myModal3_3"><img alt="" style="height: 200px;" class="setImg" src="${fileName3}"></a>
				                <!-- Modal -->
				              </div>          
				    		</c:if>  
				    		      
				            </div>
				          </div>
        				</div>
			      </div>                          
			    </div>
				<!-- td의끝 -->				
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>${vo.RE_content}</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" class="btn btn-default" value="목록보기" onclick="location.href='ReViewBoard.nhn?currentPage=${currentPage}'"/>
					<div class="dropdown" style="float: right;">
						<a class="dropdown-toggle" data-toggle="dropdown" data-target="#"><span class="glyphicon glyphicon-option-horizontal"></span></a>
						<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
							 <li role="presentation">
							 <c:choose>
							 	<c:when test="${sessionScope.session_id eq vo.RE_userId }">
							 	<a href="ReViewUpdate.nhn?idx=${vo.RE_idx}&currentPage=${currentPage}" role="menuitem" tabindex="-1">
							 		수정하기
							 	</a>
							 	</c:when>
							 	<c:otherwise>
								 	<a href="#" role="menuitem" tabindex="-1" onclick="alert('작성자 아이디가 아닙니다!');location.href='LoginView.nhn'">
								 		수정하기
								 	</a>
							 	</c:otherwise>
							 </c:choose>
							 </li>
							 <li role="presentation">
								 <c:choose>
							 	<c:when test="${sessionScope.session_id eq vo.RE_userId }">
							 	<a href="ReViewDeleteOK.nhn?idx=${vo.RE_idx}&currentPage=${currentPage}" role="menuitem" tabindex="-1">
							 		삭제하기
							 	</a>
							 	</c:when>
							 	<c:otherwise>
								 	<a href="#" role="menuitem" tabindex="-1" onclick="alert('작성자 아이디가 아닙니다!')location.href='LoginView.nhn'">
								 		삭제하기
								 	</a>
							 	</c:otherwise>
							 </c:choose>
							 </li>
						</ul>
					</div>
				</td>				
			</tr>
			<tr>
				<td colspan="2" align="right">
					<div style="cursor: pointer;" onclick="location.href='ReViewReport.nhn?idx=${vo.RE_idx}&currentPage=${currentPage}'"><span class="glyphicon glyphicon-bullhorn"></span>신고하기</div>
				</td>
			</tr>				
		</tbody>
	</table>
</div>
<jsp:include page="/Layout/footer.jsp"></jsp:include>