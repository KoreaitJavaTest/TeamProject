<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/Layout/header.jsp"></jsp:include>


	<div class="container_fluid " id="main">	
			<h3 style="text-align: center;">브랜드 목록</h3>
		<div class="row category" id="menuLine" style="min-height: 120px;" >
			<div class="col-xs-10" style="border: 1px solid gray;">
				<div class="col-xs-2"><strong><span class="menuitem" onclick = "location.href='nike.nhn?brand=nike'">나이키</span></strong></div>
				<div class="col-xs-2"><strong><span class="menuitem" onclick = "location.href='adidas.nhn?brand=adidas'">아디다스</span></strong></div>
				<div class="col-xs-2"><strong><span class="menuitem" onclick = "location.href='newbalance.nhn?brand=newbalance'">뉴발란스</span></strong></div>
				<div class="col-xs-2"><strong><span class="menuitem" onclick = "location.href='?'">브랜드1</span></strong></div>
				<div class="col-xs-2"><strong><span class="menuitem" onclick = "location.href='?'">브랜드2</span></strong></div>
				<div class="col-xs-2"><strong><span class="menuitem" onclick = "location.href='?'">브랜드3</span></strong></div>
			</div>
		</div>
	</div>
	