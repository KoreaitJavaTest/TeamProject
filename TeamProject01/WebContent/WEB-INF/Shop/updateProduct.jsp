<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	function check() {
		var flag = true;
		
		if(isNaN($('#input_price').val())) {
			alert('가격은 숫자로만 입력이 가능합니다.');
			$('#input_price').val("");
			$('#input_price').focus();
			flag = false;
		}
		
		if(isNaN($('#input_salePercent').val())) {
			alert('할인율은 숫자로만 입력이 가능합니다.');
			$('#input_salePercent').val("");
			$('#input_salePercent').focus();
			flag = false;
		}
		
		if($('#1').val() == '' && $('#2').val() == '' ){
			alert("이미지는 최소 1장 이상입니다.");
			flag = false;
		}
		return flag;
	}
	
	$(function(){
		$('#1').on('change', function(){
			readURL(1, this);
		});
		
		$('#2').on('change', function(){
			readURL(2, this);
		});
	});
	
	function readURL(i, input) {
		var filename = input.files[0].name;
		var ext = filename.substr(filename.length-3,filename.length);
		console.log(ext);
		if(ext.toLowerCase() != "jpg" && ext.toLowerCase() != "gif" &&ext.toLowerCase() != "png") {
			alert("이미지 파일이 아닙니다. 다시 선택해주세요")
			input.value = "";
		} else{
			// 파일을 읽을 수 있는 객체
			var reader = new FileReader();
			
			// 선택한 파일 읽기
			reader.readAsDataURL(input.files[0]);
			// 파일을 전부 읽으면
			reader.onload = function() {
				if(i == 1){
					$('#img_1').attr('src', reader.result);
// 					$('#img1').html('<input type="button" value="삭제하기" onclick="deleteImg(2)"/>');
				} else if(i == 2){
					$('#img_2').attr('src', reader.result);
				}
				
			}
		}
	}
	
	function deleteImg(i) {
		if(i == 1) {
// 			alert('1번 이미지');
			$('#img1').remove();
			$('#img1_insert').append('<div style="float: left;" id="img1">'+
										'<img src="" alt="이미지1" id="img_1" width="300px" height="300px">'+
									'</div>'
			);
			
		} else if(i == 2) {
// 			alert('2번 이미지');
			$('#img2').remove();
			$('#img2_insert').append('<div style="float: left;" id="img2">'+
										'<img src="" id="img_2" width="300px" height="300px">'+
									'</div>'
			);
		}
	}

</script>

<jsp:include page="/Layout/header.jsp"></jsp:include>

<form action="insertProductOK.nhn" method="post" onsubmit="return check()" enctype="multipart/form-data">

	<div class="container" style="margin-top: 50px;">
		<table class="table table-hover table-border">
			<tr>
				<th colspan="2" style="text-align: center; font-size: 30pt; font-weight: bold;">상품 수정</th>
			</tr>
			
			<tr>
				<td width="100">제목</td>
				<td>
					<input type="text" name="title" id="input_title" style="width: 98%" value="${vo.sh_title}"/>
				</td>
			</tr>
			
			<tr>
				<td width="100">상품 종류</td>
				<td>
					<select name="category" id="input_category">
						<option value="신발">신발</option>
						<option value="상의">상의</option>
						<option value="하의">하의</option>
					</select>
				</td>
			</tr>
		
			<tr>
				<td width="100">상품 브랜드</td>
				<td>
					<select name="categoryDetail" id="input_categoryDetail" >
						<option value="nike">나이키</option>
						<option value="adidas">아디다스</option>
						<option value="newbalance">뉴발란스</option>
					</select>
				</td>
			</tr>
		
			<tr>
				<td width="100">상품명</td>
				<td>
					<input type="text" name="name" id="input_name" value="${vo.sh_name}" style="width: 98%"/>
				</td>
			</tr>
			
			<tr>
				<td width="100">상품 내용</td>
				<td>
					<textarea rows="10" cols="60" name="content" id="input_content" style="resize: none;">${vo.sh_content}</textarea>
				</td>
			</tr>
	
			<tr>
				<td width="100">사이즈</td>
				<td>
					<select name="size" id="input_size">
						<option value="230">230</option>
						<option value="240">240</option>
						<option value="250">250</option>
						<option value="260">260</option>
						<option value="270">270</option>
						<option value="280">280</option>
					</select>
				</td>
			</tr>
		
			<tr>
				<td width="100">가격</td>
				<td>
					<input type="text" name="price" id="input_price" style="width: 20%" value="${vo.sh_salePrice}"/>
				</td>
			</tr>
			
			<tr>
				<td width="100">할인율</td>
				<td>
					<input type="text" name="salePercent" id="input_salePercent" style="width: 20%" value="${vo.sh_salePercent}"/> %
				</td>
			</tr>
		
			<c:if test="${vo.sh_img1 != null}">
				<tr>
		            <td colspan="2">
		            	<div id="img1_insert"></div>			<!-- 이미지 삭제 후 다시 추가할 경우 이미지가 들어갈 공간 -->
		            	<div style="float: left;" id="img1">
	                		<img src="${vo.sh_img1}" alt="이미지1" id="img_1" width="300px" height="300px">
	                	</div>
	                	<div align="left" style="float: right; margin-right: 40%; margin-top: 10%" id="imgButton1">
		                	<input type="file" id="1"/>
		                	<br/>
		                	<input type="button" value="삭제하기" onclick="deleteImg(1)"/>
	                	</div>
		         	</td>
				</tr>
      		</c:if>
			<c:if test="${vo.sh_img2 != null}">
				<tr>
		            <td colspan="2">
		            	<div id="img2_insert"></div>			<!-- 이미지 삭제 후 다시 추가할 경우 이미지가 들어갈 공간 -->
	                	<div style="float: left;" id="img2">
	                		<img src="${vo.sh_img2}" alt="이미지2" id="img_2" width="300px" height="300px">
	                	</div>
	                	<div align="left" style="float: right; margin-right: 40%; margin-top: 10%" id="imgButton2">
		                	<input type="file" id="2"/>
		                	<br/>
		                	<input type="button" value="삭제하기" onclick="deleteImg(2)"/>
	                	</div>
		         	</td>
		         	
				</tr>
           	</c:if>
			
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="상품 수정"/>
				</td>
			</tr>
			
		</table>
	</div>
</form>

<jsp:include page="/Layout/footer.jsp"></jsp:include>

















