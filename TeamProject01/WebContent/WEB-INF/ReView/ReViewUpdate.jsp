<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/Layout/header.jsp"></jsp:include>

<div class="container" align="center" style="margin-top: 30px;">
	<form action="ReViewUpdateOK.nhn" method="post" enctype="multipart/form-data">
		<table class="table table-bordered table-hover">
			<tr align="center" >
				<th colspan="3">리뷰 글 작성</th>
			</tr>
			<tr>
				<td colspan="1" style="width: 15%;">
					<label for="tit">
						제목
					</label>
				</td>
				<td colspan="2">
					<input type="text" id="tit" name="title" placeholder="제목을 입력하세요" required="required" style="width: 95%" value="${vo.RE_title}">
				</td>
			</tr>
			<tr>
				<td>
					<label for="content">
						내용
					</label>
				</td>
				<td colspan="2">
					<textarea id="content" name="content" rows="15" cols="100" style="resize: none; width: 95%;" placeholder="내용을 입력하세요" >${vo.RE_content}</textarea>
				</td>
			</tr>
			<tr>
				<td>
					<span onclick="imgPlus()">&nbsp;+&nbsp;</span>
					&nbsp;&nbsp;이미지 업로드&nbsp;&nbsp;
					<span onclick="imgMinus()">&nbsp;-&nbsp;</span>
				</td>
				<td id="file">
					<input type="file" name="1">
				</td>
				<td>
				카테고리
					<select name="categoryDetail">
						<c:if test="${vo.RE_categoryDetail eq '나이키'}"><option value="나이키" selected="selected">나이키</option></c:if>
						<option value="나이키">나이키</option>
						<c:if test="${vo.RE_categoryDetail eq '아디다스'}"><option value="아디다스" selected="selected">아디다스</option></c:if>
						<option value="아디다스">아디다스</option>
						<c:if test="${vo.RE_categoryDetail eq 'New Balance'}"><option value="New Balance" selected="selected">New Balance</option></c:if>
						<option value="New Balance">New Balance</option>
						<c:if test="${vo.RE_categoryDetail eq '나이키'}"><option value="나이키" selected="selected">나이키</option></c:if>
						<option value="Doctor Martins">Doctor Martins</option>
						<c:if test="${vo.RE_categoryDetail eq 'Doctor Martins'}"><option value="Doctor Martins" selected="selected">Doctor Martins</option></c:if>
						<option value="Doctor Martins">Doctor Martins</option>
						<c:if test="${vo.RE_categoryDetail eq 'SoDa'}"><option value="SoDa" selected="selected">SoDa</option></c:if>
						<option value="SoDa">SoDa</option>
						<c:if test="${vo.RE_categoryDetail eq '고세'}"><option value="고세" selected="selected">고세</option></c:if>
						<option value="고세">고세</option>
						<c:if test="${vo.RE_categoryDetail eq '조던'}"><option value="조던" selected="selected">조던</option></c:if>
						<option value="조던">조던</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="3">
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
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="submit" value="수정하기">			
					<input type="button" value="돌아가기">			
				</td>
			</tr>
		</table>
		<input type="hidden" value="${vo.RE_idx}" name="idx">
		<input type="hidden" value="${currentPage}" name="currentPage">
	</form>

</div>

<jsp:include page="/Layout/footer.jsp"></jsp:include>