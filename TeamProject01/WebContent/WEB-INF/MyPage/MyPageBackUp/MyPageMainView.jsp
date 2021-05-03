<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/Layout/header.jsp"></jsp:include>
<style>
  .affix-row .affix-sidebar{
    background-color: #f8f8f8;
    height: 100%;
    overflow: hidden;
  }
    .affix-content .container .page-header{
    margin-top: 0;
  }
  .sidebar-nav{
        position:fixed; 
        width:100%;
  }
  .affix-sidebar{
    padding-right:0; 
    font-size:small;
    padding-left: 0;
  }  
  .affix-row, .affix-container, .affix-content{
    height: 100%;
    margin-left: 0;
    margin-right: 0;    
  } 
  .affix-content{
    background-color:white; 
  } 
  .sidebar-nav .navbar .navbar-collapse {
    padding: 0;
    max-height: none;
  }
  .sidebar-nav .navbar{
    border-radius:0; 
    margin-bottom:0; 
    border:0;
  }
  .sidebar-nav .navbar ul {
    float: none;
    display: block;
  }
  .sidebar-nav .navbar li {
    float: none;
    display: block;
  }
  .sidebar-nav .navbar li a {
    padding-top: 12px;
    padding-bottom: 12px;
  }  
}

@media (min-width: 769px){
  .affix-content .container {
    width: 600px;
  }
    .affix-content .container .page-header{
    margin-top: 0;
  }  
}

@media (min-width: 992px){
  .affix-content .container {
  width: 900px;
  }
    .affix-content .container .page-header{
    margin-top: 0;
  }
}

@media (min-width: 1220px){
  .affix-row{
    overflow: hidden;
  }

  .affix-content{
    overflow: auto;
  }

  .affix-content .container {
    width: 1000px;
  }

  .affix-content .container .page-header{
    margin-top: 0;
  }
  .affix-content{
    padding-right: 30px;
    padding-left: 30px;
  }  
  .affix-title{
    border-bottom: 1px solid #ecf0f1; 
    padding-bottom:10px;
  }
  .navbar-nav {
    margin: 0;
  }
  .navbar-collapse{
    padding: 0;
  }
  .sidebar-nav .navbar li a:hover {
    background-color: #428bca;
    color: white;
  }
  .sidebar-nav .navbar li a > .caret {
    margin-top: 8px;
  }  
}
@import url(http://fonts.googleapis.com/css?family=Lato:400,700);
.profile
{
    font-family: 'Lato', 'sans-serif';
    }
.profile 
{
/*    height: 321px;
    width: 265px;*/
margin-top: 2px;
padding:1px;
    display: inline-block;
    }
.divider 
{
    border-top:1px solid rgba(0,0,0,0.1);
    }
.emphasis 
{
    border-top: 1px solid transparent;
    }

.emphasis h2
{
    margin-bottom:0;
    }
span.tags 
{
    background: #1abc9c;
    border-radius: 2px;
    color: #f5f5f5;
    font-weight: bold;
    padding: 2px 4px;
    }
.profile strong,span,div{
    text-transform: initial;
}


</style>
<div class="container_fluid col-xs-12" style="
    padding-bottom: 30px;
">
	<div class="row affix-row">
	    <div class="col-sm-3 col-md-2 affix-sidebar">
			<div class="sidebar-nav">
	  <div class="navbar navbar-default" role="navigation" style="width: 225px;">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
	      <span class="sr-only">Toggle navigation</span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	      </button>
	      <span class="visible-xs navbar-brand">Sidebar menu</span>
	    </div>
	    <div class="navbar-collapse collapse sidebar-navbar-collapse" style="text-align: left; width: 242px;">
	      <ul class="nav navbar-nav" id="sidenav01">
	        <li class="active">
	          <a href="#" data-toggle="collapse" data-target="#toggleDemo0" data-parent="#sidenav01" class="collapsed">
	          <h4 style="color: white">
	          <span class="glyphicon glyphicon-user"></span>&nbsp;내 정보
	          <br>
	          <small>${sessionScope.session_id} 님<span class="caret"></span></small>
	          </h4>
	          </a>
	          <div class="collapse" id="toggleDemo0" style="height: 0px;">
	            <ul class="nav nav-list">
	              <li><a href="MyEditViewPasswordCheck.nhn">개인정보 변경</a></li>
	              <li><a href="#">비밀번호 변경</a></li>
	              <li><a href="#">결제수단 변경</a></li>
	              <li><a href="#">배송지 관리</a></li>
	            </ul>
	          </div>
	        </li>
	        <li>
	          <a href="#" data-toggle="collapse" data-target="#toggleDemo" data-parent="#sidenav01" class="collapsed">
	          <span class="glyphicon glyphicon-cloud"></span> 게시글 관리 <span class="caret pull-right"></span>
	          </a>
	          <div class="collapse" id="toggleDemo" style="height: 0px;">
	            <ul class="nav nav-list">
	              <li><a href="#">판매 상품</a></li>
	              <li><a href="#">내가 올린 리뷰</a></li>
	              <li><a href="#">1:1 상담</a></li>
	            </ul>
	          </div>
	        </li>
	        <li><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;장바구니</a></li>
	        <li><a href="#"><span class="glyphicon glyphicon-copyright-mark"></span> 포인트 관리 <span class="badge pull-right">${sessionScope.session_point}</span></a></li>
	        <li><a href=""><span class="glyphicon glyphicon-cog"></span> 회원 탈퇴</a></li>
	      </ul>
	      </div><!--/.nav-collapse -->
	    </div>
	  </div>
		</div>
		<div class="col-sm-9 col-md-10 col-xs-12 affix-content" style="padding-left: 30px;"><!-- 메인글테두리시작 -->
				
		<div class="container" style="margin-left: 0px; padding-left: 0px;"><!-- 메인글시작 -->
			<div class="page-header">
				<h3><span class="glyphicon glyphicon-th-list"></span> 내 프로필</h3>
			</div>
			 <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" >
                <div class="row">
			            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="border-radius: 16px;">
			                <div class="well profile col-lg-12 col-md-12 col-sm-12 col-xs-12" style="position: relative; left: 400px;">
			                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
			                        <figure>
			                             <img src="./htmll.jpg" alt="tq" class="img-circle" style="width:75px;" id="user-img">
			                        </figure>
			                        <h5 style="text-align:center;"><strong id="user-name">${sessionScope.session_id} 님</strong></h5>
			                        <p style="text-align:center;font-size: smaller;" id="user-frid">${sessionScope.session_gender}</p>
			                        <p style="text-align:center;font-size: smaller;overflow-wrap: break-word;" id="user-email">${sessionScope.session_email}</p>
			                        <p style="text-align:center;font-size: smaller;"><strong>
			                        	<c:if test="${sessionScope.session_level==0}">관리자</c:if>		                      
			                        	<c:if test="${sessionScope.session_level==1}">판매자</c:if>		                      
			                        	<c:if test="${sessionScope.session_level==2}">손님</c:if>		                      
			                        	</strong><span class="tags" id="user-status">
			                        	<c:if test="${sessionScope.session_level==0}">ADMIN</c:if>		                      
			                        	<c:if test="${sessionScope.session_level==1}">SELLER</c:if>		                      
			                        	<c:if test="${sessionScope.session_level==2}">CUSTOMER</c:if>	
			                        	</span></p>
			                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 divider text-center" style="margin-top: 3px;"></div>
			                        <p style="text-align:center;font-size: smaller;"><strong>Point</strong></p>
			                        <p style="text-align:center;font-size: smaller;" id="user-role">${sessionScope.session_point}점</p>
			                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 divider text-center"></div>
			                            <div class="col-lg-12 left" style="text-align:center;overflow-wrap: break-word;">
			                               <input type="button" class="btn btn-info" value="출석체크">
			                            </div>
			                      </div>
			                    </div>
			            </div>
                    </div>
                </div>
            </div><!-- 메인글끝 -->    
        </div><!-- 메인글테두리끝 -->
	</div><!-- 내정보nav바 + 메인글끝 -->
		</div><!-- 내정보nav바 + 메인글끝 테두리 끝-->



<jsp:include page="/Layout/footer.jsp"></jsp:include>