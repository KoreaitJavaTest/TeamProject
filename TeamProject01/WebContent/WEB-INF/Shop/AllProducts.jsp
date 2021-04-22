<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> --%>
    
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<jsp:include page="/WEB-INF/Shop/mainCategory.jsp"></jsp:include>

<jsp:useBean id="date" class="java.util.Date"/>
<c:set var="list" value="${shopList.list}"/>

<div class="container">
    <h3 class="h3">전체 상품</h3>
    <div class="row">
    
    	<c:forEach var="vo" items="${list}">
        <div class="col-md-3 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
                    <a href="#">
                        <img class="pic-1" src="${vo.sh_img2}">
                        <img class="pic-2" src="${vo.sh_img1}">
                    </a>
                    <ul class="social">
                        <li><a href="" data-tip="Quick View"><i class="fa fa-search"></i></a></li>
                        <li><a href="" data-tip="Add to Wishlist"><i class="fa fa-shopping-bag"></i></a></li>
                        <li><a href="" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                    
                    <!-- 게시일이 오늘 날짜면 new 표기 -->
                    <c:if test="${date.year == vo.sh_writeDate.year && date.month == vo.sh_writeDate.month && date.date == vo.sh_writeDate.date}">
	                    <span class="product-new-label">New</span>
                    </c:if>
                    
                    <span class="product-discount-label">-${vo.sh_salePercent}%</span>
                </div>
                
                <ul class="rating">
                    <li class="fa fa-star"></li>
                    <li class="fa fa-star"></li>
                    <li class="fa fa-star"></li>
                    <li class="fa fa-star disable"></li>
                    <li class="fa fa-star disable"></li>
                </ul>
                
                
                   <div class="product-content">
                    <h3 class="title"><a href="#">${vo.sh_name}</a></h3>
                    	<div class="price">${vo.sh_salePriceFM}원
                        	<span>${vo.sh_priceFM}원</span>
                        </div>
                    <a class="add-to-cart" href="">장바구니 추가</a>
                </div>
                
            </div>
    	</div>
		</c:forEach>
<%-- 		${list.currentPage} --%>
	</div>
</div>

<!-- 	처음으로 -->
<%-- 	<c:if test="${list.currentPage > 1}"> --%>
<!-- 		<input class="button button1" type="button" value="맨앞" title="첫 번째 페이지로 이동" onclick="location.href='?currentPage=1'"/> -->
<%-- 	</c:if> --%>
<%-- 	<c:if test="${list.currentPage <= 1}"> --%>
<!-- 		<input class="button button2" type="button" value="맨앞" disabled="disabled" title="이미 첫 번째 페이지 입니다."/> -->
<%-- 	</c:if> --%>
	
<!-- 	10페이지 앞으로 -->
<%-- 	<c:if test="${list.startPage > 1 }"> --%>
<!-- 		<input class="button button1" type="button" value="이전" title="이전 페이지로 이동"  -->
<%-- 				onclick="location.href='?currentPage=${list.startPage - 1}'"/> --%>
<%-- 	</c:if> --%>
<%-- 	<c:if test="${list.startPage <= 1 }"> --%>
<!-- 		<input class="button button2" type="button" value="이전" disabled="disabled" title="이미 첫 번째 10페이지 입니다."/>			 -->
<%-- 	</c:if> --%>
	
<!-- 	페이지 이동 -->
<%-- 	<c:forEach var="i" begin="${list.startPage}" end="${list.endPage}" step="1"> --%>
<%-- 		<c:if test="${list.currentPage == i}"> --%>
<%-- 			<input class="button button2" type="button" value="${i}" disabled="disabled"/> --%>
<%-- 		</c:if> --%>
<%-- 		<c:if test="${list.currentPage != i}"> --%>
<%-- 			<input class="button button1" type="button" value="${i}" onclick="location.href='?currentPage=${i}'"/> --%>
<%-- 		</c:if> --%>
<%-- 	</c:forEach> --%>
	
<!-- 	10페이지 뒤로 -->
<%-- 	<c:if test="${list.endPage < list.totalPage}"> --%>
<!-- 		<input class="button button1" type="button" value="다음" title="다음 페이지로 이동"  -->
<%-- 				onclick="location.href='?currentPage=${list.endPage + 1}'"/> --%>
<%-- 	</c:if> --%>
<%-- 	<c:if test="${list.endPage >= list.totalPage}"> --%>
<!-- 		<input class="button button2" type="button" value="다음" disabled="disabled" title="이미 마지막 10페이지 입니다."/> -->
<%-- 	</c:if> --%>
	
<!-- 	마지막으로 -->
<%-- 	<c:if test="${list.currentPage < list.totalPage}"> --%>
<!-- 		<input class="button button1" type="button" value="맨뒤" title="마지막 페이지로 이동"  -->
<%-- 				onclick="location.href='?currentPage=${list.totalPage}'"/> --%>
	
<%-- 	</c:if> --%>
<%-- 	<c:if test="${list.currentPage >= list.totalPage}"> --%>
<!-- 		<input class="button button2" type="button" value="맨뒤" disabled="disabled" title="이미 마지막 페이지 입니다."/>			 -->
<%-- 	</c:if> --%>




<jsp:include page="/Layout/footer.jsp"></jsp:include>