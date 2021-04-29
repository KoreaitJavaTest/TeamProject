<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">


</script>
<!-- 선택한 상품을 보여주는 페이지 -->
<jsp:include page="/WEB-INF/Shop/mainCategory.jsp"></jsp:include>

	<div class="container">
	
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
				
					<div class="panel-heading">
						<h3 class="panel-title">
							<span>${vo.sh_title}</span>
						</h3>
					</div>
					<div class="panel-heading">
						<h3 class="panel-title" align="right">
							<span onclick="location.href=''">${vo.sh_category} </span>
							>>
							<span>${vo.sh_categoryDetail}</span>
						</h3>
					</div>
				
					<div class="panel-body">
						<div class="media">
							<div class="media-left">
								<c:if test="${vo.sh_img1 != null && vo.sh_img2 == null}">
			                        <img src="${vo.sh_img1}" width="300" height="300">
		                    	</c:if>
		                    	<c:if test="${vo.sh_img1 == null && vo.sh_img2 != null}">
			                        <img src="${vo.sh_img2}" width="300" height="300">
		                    	</c:if>
		                    	<c:if test="${vo.sh_img1 != null && vo.sh_img2 != null}">
		                    		<img src="${vo.sh_img1}" width="300" height="300">
		                    		<img src="${vo.sh_img2}" width="300" height="300">
		                    	</c:if>
							</div>
							<div class="media-body">
								<h4 class="media-heading" style="font-size: 20pt;font-weight: bold"><strong>${vo.sh_name}</strong></h4>
								<br/>
								
							</div>
						</div>
					</div>
					
					<table class="table">
						<thead>
							<tr>
								<td align="center" style="font-weight: bold; font-size: 13pt; text-align: left;">
									판매가 <span> ${vo.sh_salePriceFM} 원</span>
									<c:if test="${vo.sh_salePercent != null}">
										<br/><br/>
										<span style="color: graytext;">
											<span style="text-decoration:line-through;"> ${vo.sh_priceFM} 원</span>
											 &nbsp;&nbsp;-${vo.sh_salePercent}% 할인
										</span>
									</c:if>
								</td>
							</tr>
							
							<tr>
								<td colspan="2" align="center">
									<a class="btn btn-default" href="#">바로 구매</a>
								</td>
								<td  colspan="2" align="right">
									<a class="btn btn-default" href="#">장바구니</a>
									&nbsp;
									<a class="btn btn-default" href="#">좋아요 ♡</a>
								</td>
								
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" align="right">
									
									★<input type="radio" name="check" value="1">&nbsp;
									★★<input type="radio" name="check" value="2">&nbsp;
									★★★<input type="radio" name="check" value="3">&nbsp;
									★★★★<input type="radio" name="check" value="4">&nbsp;
									★★★★★<input type="radio" name="check" value="5">&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" colspan="4">
									사이즈
									<select>
										<option>230</option>
										<option>240</option>
										<option>250</option>
										<option>260</option>
										<option>270</option>
										<option>280</option>
									</select>
									
									&nbsp;&nbsp;&nbsp;&nbsp;
									수량 <input type="text"/>
								</td>
							</tr>
							<tr>
								<td colspan="4" style="text-align: right; font-weight: bold;">평균 배송일 2 ~ 3일 </td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<a class="btn btn-default" href="#">상품 리뷰 보러가기</a>
								</td>
								<td colspan="2" align="center">
									<a class="btn btn-default" href="">목록으로</a>
								</td>
							</tr>
							
							<!-- 상품 등록 id와 로그인 id가 같으면 수정, 삭제버튼이 나온다. -->
							<%String userId = session.getAttribute("session_id") + "";%>
							<c:set var="userId" value="<%=userId%>"/>
							<c:if test="${vo.sh_seller eq userId}">
								<tr>
									<td colspan="4" align="center">
										<a class="btn btn-default" href="updateProduct.nhn?sh_idx=${vo.sh_idx}&sh_img1=${vo.sh_img1}&sh_img2=${vo.sh_img2}">수정</a>
										<a class="btn btn-default" href="deleteProduct.nhn?sh_idx=${vo.sh_idx}">삭제</a>
									</td>
								</tr>
							</c:if>
							
						</tbody>
					</table>
				
				</div>
			</div>
		</div>
			
	</div>

<jsp:include page="/Layout/footer.jsp"></jsp:include>