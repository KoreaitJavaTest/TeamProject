<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>부트스트랩 웹 페이지</title>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.css">
<style type="text/css">
*{
	font-family: "Nanum Gothic";
}

table tbody>tr>td>a,
table tbody>tr>td>a:hover,
table tbody>tr>td>a:focus{
	text-decoration: none;
	color:red;
	
}

/*네비영역*/
.navbar-default{
	background: #000000;
	border-color: #000000;
}
.navbar-default navbar-brand{ /*네비제목*/
	color: #ffffff;
}
.navbar-default .navbar-brand:hover,
 .navbar-default .navbar-brand:focus{
	background-color: transparent;
	color: tomato;	
}
.navbar-default .navbar-nav > .active > a,
.navbar-default .navbar-nav > .active > a:hover,
.navbar-default .navbar-nav > .active > a:focus{
	background-color: #000000;
	color: blue;
}
/*네비게이션 글자에 마우스를 올렸을때*/
.navbar-default .navbar-nav > li > a:hover,
.navbar-default .navbar-nav > li > a:focus{
	background-color: #000000;
	color: tomato;
}

.navbar-default .navbar-nav > .open >a,
.navbar-default .navbar-nav > .open >a:hover,
.navbar-default .navbar-nav > .open >a:focus{
	background-color: #ffffff;
	color: yellowgreen;
	text-decoration: none;
}
.navbar-default .navbar-nav > li > a{	
	color:#ffffff;
}
/*해상도가 작을때 드롭다운 아이템에 마우스를 올렸을때*/
@media (max-width:767px) {
	.navbar-default .navbar-nav > .open >dropdown-menu>li>a{
		color:#ffffff;
	}
	.navbar-default .navbar-nav > .open >dropdown-menu>li>a:hover{
		color:white;
	}
	.navbar-default .navbar-nav > .open >dropdown-menu>li>a:focus{
		color:skyblue;
	}
}

</style>
<link rel="icon" href="./images/me.png">
<style type="text/css">
	.jumbotron {
   	    padding-bottom: 0px;
   	    padding-top: 0px;
		background-color: black;
		background-size: cover;
		text-shadow: 0.2em 0.2em 0.2em black;
		color: white;
	}
	#lb-wrap img {
	  vertical-align: middle;
	}
	#lb-text {
	  padding: 10px 20px;
	  background-color: #FFEEBC;
	  text-align: center;
	}
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
	.navbar .navbar-nav {
	  display: inline-block;
	  float: none;
	}
	.navbar .navbar-collapse {
	  text-align: center;
	}

	@media screen and (min-width:769px) {
		.loginMenu{
		    margin-left: 0px;
		    padding-left: 15px;
		    left: 0px;
		}
}		
	#headerNav{
  	  height: 208px;		
	}		
</style>

</head>
<body>
	<div style = "background-color: black;">
<!-- 		<div align="center"> -->
<!-- 			<img src="./images/naver.png" onclick = "location.href='https://www.naver.com'"> -->
<!-- 			<img src="./images/navershop.png" onclick = "location.href='https://www.naver.com'"> -->
<!-- 		</div> -->
	</div>
	<nav class="navbar navbar-default">
		<div class="container-fluid" id="headerNav">
			<div class="navbar-header"> 
				<button type="button" id="togglebar" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
			<div class="jumbotron" onclick = "location.href = '/TeamProject01/'">
				<h1 class="text-center">LUXMEA</h1>
				<p class="text-center">LUXMEA MAKES YOU SHINE</p>
			</div>
			<div class="collapse navbar-collapse navheigt" id="bs-example-navbar-collapse-1" style="padding-left: 80px;">
				<ul class="nav navbar-nav" >
					<li class="nav-item">
						<a  class="nav-link" href="AllProducts.nhn?currentPage=1">전체상품</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="ReViewBoard.nhn">리뷰게시판</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="index5.html">묻고답하기</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="index5.html">공지사항</a>
					</li>
					<li class="nav-item">
						<c:if test = "${sessionScope.session_id eq null }">
							<a class="nav-link" onclick="alert('로그인후 사용가능 합니다.');location.href='LoginView.nhn'">마이페이지</a>
						</c:if>
						<c:if test = "${sessionScope.session_id != null }">
							<a class="nav-link" href="d.html">마이페이지</a>
						</c:if>
					</li>
					<li>
						<span class="nav navbar-nav navbar-right" style="margin-left: 10px;">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle loginMenu"  data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
									접속하기<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<c:if test = "${sessionScope.session_id eq null }">
										<li><a href="LoginView.nhn">로그인</a></li>
										<li><a href="JoinView.nhn">회원가입</a></li>
									</c:if>
									<c:if test = "${sessionScope.session_id != null }">
										<li><div align="center">${sessionScope.session_id}님<br/>point: ${sessionScope.session_point}점</div></li>
										<li><a href="LogoutView.nhn">로그아웃</a></li>
									</c:if>
								</ul>
							</li>
						</span>
					</li>
					</ul>
			</div>
		</div>
	</nav>