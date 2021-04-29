<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- ========================= 재훈 추가 ========================== -->
<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css'>
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300'>
<!-- ========================재훈 추가 끝========================== -->

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="Layout/style.css">
<link rel="shortcut icon" href="../images/favicon.ico">

<style>
/* ============================ 재훈 추가 (왼쪽 메뉴바) ===================================== */
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

.product-grid .product-image{position:relative; transition:all .3s ease 0s}
.product-grid .product-image a{display:block}
.product-grid .product-image img{width:100%; height:300px; object-fit: cover}
.product-grid .pic-1{opacity:1;transition:all .3s ease-out 0s}
.product-grid:hover .pic-1{opacity:1}
.product-grid .pic-2{opacity:0; position:absolute; top:0;left:0;transition:all .3s ease-out 0s}
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
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='AllProducts.nhn'">전체상품</span></div>
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


		
		