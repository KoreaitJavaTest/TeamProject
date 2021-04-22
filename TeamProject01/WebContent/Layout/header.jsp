<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
#header{
	border: 1px solid red;
	display: flex;
	align-items: center;
	background: black;
}

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
#main2{
	border: 1px solid blue;
	display: flex;
	align-items: center;
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
	
	.category {
		display: flex;
		justify-content: center;
	}
	
	h3.h3{text-align:center;margin:1em;text-transform:capitalize;font-size:1.7em;}

/********************* shopping Demo-1 **********************/
.product-grid{font-family:Raleway,sans-serif;text-align:center;padding:0 0 72px;border:1px solid rgba(0,0,0,.1);overflow:hidden;position:relative;z-index:1}
.product-grid .product-image{position:relative;transition:all .3s ease 0s}
.product-grid .product-image a{display:block}
.product-grid .product-image img{width:100%;height:auto}
.product-grid .pic-1{opacity:1;transition:all .3s ease-out 0s}
.product-grid:hover .pic-1{opacity:1}
.product-grid .pic-2{opacity:0;position:absolute;top:0;left:0;transition:all .3s ease-out 0s}
.product-grid:hover .pic-2{opacity:1}
.product-grid .social{width:150px;padding:0;margin:0;list-style:none;opacity:0;transform:translateY(-50%) translateX(-50%);position:absolute;top:60%;left:50%;z-index:1;transition:all .3s ease 0s}
.product-grid:hover .social{opacity:1;top:50%}
.product-grid .social li{display:inline-block}
.product-grid .social li a{color:#fff;background-color:#333;font-size:16px;line-height:40px;text-align:center;height:40px;width:40px;margin:0 2px;display:block;position:relative;transition:all .3s ease-in-out}
.product-grid .social li a:hover{color:#fff;background-color:#ef5777}
.product-grid .social li a:after,.product-grid .social li a:before{content:attr(data-tip);color:#fff;background-color:#000;font-size:12px;letter-spacing:1px;line-height:20px;padding:1px 5px;white-space:nowrap;opacity:0;transform:translateX(-50%);position:absolute;left:50%;top:-30px}
.product-grid .social li a:after{content:'';height:15px;width:15px;border-radius:0;transform:translateX(-50%) rotate(45deg);top:-20px;z-index:-1}
.product-grid .social li a:hover:after,.product-grid .social li a:hover:before{opacity:1}
.product-grid .product-discount-label,.product-grid .product-new-label{color:#fff;background-color:#ef5777;font-size:12px;text-transform:uppercase;padding:2px 7px;display:block;position:absolute;top:10px;left:0}
.product-grid .product-discount-label{background-color:#333;left:auto;right:0}
.product-grid .rating{color:#FFD200;font-size:12px;padding:12px 0 0;margin:0;list-style:none;position:relative;z-index:-1}
.product-grid .rating li.disable{color:rgba(0,0,0,.2)}
.product-grid .product-content{background-color:#fff;text-align:center;padding:12px 0;margin:0 auto;position:absolute;left:0;right:0;bottom:-27px;z-index:1;transition:all .3s}
.product-grid:hover .product-content{bottom:0}
.product-grid .title{font-size:16px;font-weight:1000; letter-spacing:.5px;text-transform:capitalize;margin:0 0 10px;transition:all .3s ease 0s}
.product-grid .title a{color:#333}
.product-grid .title a:hover,.product-grid:hover .title a{color:#ef5777}
.product-grid .price{color:#333;font-size:15px;font-family:Montserrat,sans-serif;font-weight:700;letter-spacing:.6px;margin-bottom:8px;text-align:center;transition:all .3s}
.product-grid .price span{color:#999;font-size:12px;font-weight:400;text-decoration:line-through;margin-left:3px;display:inline-block}
.product-grid .add-to-cart{color:#333;font-size:13px;font-weight:600}

@media only screen and (max-width:2000px){.product-grid{margin-bottom:30px}
}
	
</style>

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
				<div class="col-xs-3"><span class="usercursor" onclick = "location.href = 'MyPageView.nhn'"> 마이페이지</span></div>
			</c:if>
		</div>	
	</div>
	<div class="row" id="main2" style="min-height: 200px;">
		<div class="col-xs-12" align="center"><span id="title" onclick="location.href='index.jsp'">Koreait Team A(img넣거나 그냥글자)</span></div>
	</div>
	<div class="row" id="menuLine" style="min-height:80px; ">
		<div class="col-xs-1"></div>
		<div class="col-xs-10" style="border: 1px solid gray;">
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='AllProducts.nhn?currentPage=1'">전체상품</span></div>
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='ReViewBoard.nhn'">리뷰게시판</span></div>
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='QAboard.nhn'">묻고 답하기</span></div>
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='?'">공지사항</span></div>
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='?'">뭘넣을까</span></div>
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='?'">여기도뭘넣지</span></div>
				<c:if test="${sessionScope.session_id != null }">
					<div class="col-xs-2"><span class="menuitem" onclick="location.href='insertProduct.nhn'">판매 상품 등록</span></div>
				</c:if>
			</div>
			<div class="col-xs-1"></div>
		</div>
	</div>		


		
		