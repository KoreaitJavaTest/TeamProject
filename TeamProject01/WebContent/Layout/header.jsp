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
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

<style type="text/css">
*{
	font-family: "Nanum Gothic";
}

table tbody>tr>td>a,
table tbody>tr>td>a:hover,
table tbody>tr>td>a:focus{
	text-decoration: none;
	color:red;
</style>

<!-- 왼쪽 메뉴바 start -->
<style> 
html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,center,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,details,embed,figure,figcaption,footer,header,hgroup,menu,nav,output,ruby,section,summary,time,mark,audio,video{margin:0;padding:0;border:0;font-size:100%;font:inherit;vertical-align:baseline}article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section{display:block}body{line-height:1}ol,ul{list-style:none}blockquote,q{quotes:none}blockquote:before,blockquote:after,q:before,q:after{content:'';content:none}table{border-collapse:collapse;border-spacing:0}

body {
  margin-top: 2rem;
 font: 100% "Open sans", "Trebuchet MS", sans-serif;
} 

a {
  text-decoration: none;
}

/**
 * Hidden fallback
 */
[hidden] {
  display: none;
  visibility: hidden;
}

/**
 * Styling navigation
 */
header {
  margin-right: auto;
  margin-left: auto;
  max-width: 22.5rem;
  box-shadow: 0 3px 12px rgba(0, 0, 0, 0.25);
}

/**
 * Styling top level items
 */
.nav a,
.nav label {
  display: block;
  padding: .85rem;
  color: #fff;
  background-color: #151515;
  box-shadow: inset 0 -1px #1d1d1d;
  -webkit-transition: all .25s ease-in;
          transition: all .25s ease-in;
}
.nav a:focus, .nav a:hover,
.nav label:focus,
.nav label:hover {
  color: rgba(255, 255, 255, 0.5);
  background: #030303;
}

.nav label {
  cursor: pointer;
}

/**
 * Styling first level lists items
 */
.group-list a,
.group-list label {
  padding-left: 2rem;
  background: #252525;
  box-shadow: inset 0 -1px #373737;
}
.group-list a:focus, .group-list a:hover,
.group-list label:focus,
.group-list label:hover {
  background: #131313;
}

/**
 * Styling second level list items
 */
.sub-group-list a,
.sub-group-list label {
  padding-left: 4rem;
  background: #353535;
  box-shadow: inset 0 -1px #474747;
}
.sub-group-list a:focus, .sub-group-list a:hover,
.sub-group-list label:focus,
.sub-group-list label:hover {
  background: #232323;
}

/**
 * Styling third level list items
 */
.sub-sub-group-list a,
.sub-sub-group-list label {
  padding-left: 6rem;
  background: #454545;
  box-shadow: inset 0 -1px #575757;
}
.sub-sub-group-list a:focus, .sub-sub-group-list a:hover,
.sub-sub-group-list label:focus,
.sub-sub-group-list label:hover {
  background: #333333;
}

/**
 * Hide nested lists
 */
.group-list,
.sub-group-list,
.sub-sub-group-list {
  height: 100%;
  max-height: 0;
  overflow: hidden;
  -webkit-transition: max-height .5s ease-in-out;
          transition: max-height .5s ease-in-out;
}

.nav__list input[type=checkbox]:checked + label + ul {
  /* reset the height when checkbox is checked */
  max-height: 1000px;
}

/**
 * Rotating chevron icon
 */
label > span {
  float: right;
  -webkit-transition: -webkit-transform .65s ease;
          transition: transform .65s ease;
}

.nav__list input[type=checkbox]:checked + label > span {
  -webkit-transform: rotate(90deg);
      -ms-transform: rotate(90deg);
          transform: rotate(90deg);
}

/**
 * Styling footer
 */
footer {
  padding-top: 1rem;
  padding-bottom: 1rem;
  background-color: #050505;
}

.soc-media {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: center;
  -webkit-justify-content: center;
      -ms-flex-pack: center;
          justify-content: center;
}

.soc-media li:nth-child(n+2) {
  margin-left: 1rem;
}

.soc-media a {
  font-size: 1.25rem;
  color: rgba(255, 255, 255, 0.65);
  -webkit-transition: color .25s ease-in;
          transition: color .25s ease-in;
}
.soc-media a:focus, .soc-media a:hover {
  color: rgba(255, 255, 255, 0.2);
}
/* ==========================재훈 추가 끝=============================== */


#header{
	border: 1px solid red;
	display: flex;
	align-items: center;
	background: black;
}

<!-- 부가적인 테마 -->
#main2{
	border: 1px solid blue;
	display: flex;
	align-items: center;
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
<!-- 왼쪽 메뉴바 end -->

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
	
/* 	=================== 재훈 변경 04-30 ======================== 	 */
/* 						이미지 크기 고정 */
	.product-grid .product-image img{width:100%;height:300px;} 
	.pic{width: 300px; height: 300px; object-fit: cover;}
/* 	=================== 재훈 변경 04-30 끝 ========================	 */
	 
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
			<div class="jumbotron" onclick = "location.href = '/TeamProject/'">
				<h1 class="text-center">LUXMEA</h1>
				<p class="text-center">LUXMEA MAKES YOU SHINE</p>
			</div>
			<div class="collapse navbar-collapse navheigt" id="bs-example-navbar-collapse-1" style="padding-left: 80px;">
				<ul class="nav navbar-nav" >
					<li class="nav-item">
						<a  class="nav-link" href="AllProducts.nhn?currentPage=1">전체상품</a>
					</li>
					<li class="nav-item">
						<a  class="nav-link" href="insertProduct.nhn">상품 등록</a>
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
	</nav>