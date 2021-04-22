<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<jsp:include page="/WEB-INF/Shop/mainCategory.jsp"></jsp:include>
<jsp:useBean id="date" class="java.util.Date"/>
<c:set var="list" value="${shopList.list}"/>

<div class="container">
    <h3 class="h3">뉴발란스</h3>
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
	                
	                
<!-- 	                <div class="product-content"> -->
<%-- 	                    <h3 class="title"><a href="#">${vo.sh_name}</a></h3> --%>
	                
<!-- 	                    <div class="price">1231 원 -->
<!-- 	                        <span>123</span> -->
<!-- 	                    </div> -->
<!-- 	                    <a class="add-to-cart" href="">장바구니 추가</a> -->
<!-- 	                </div> -->
	            </div>
	    	</div>
		</c:forEach>
	</div>
</div>

<jsp:include page="/Layout/footer.jsp"></jsp:include>