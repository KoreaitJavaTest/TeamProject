<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">

</script>
<script type="text/javascript">
function searchAddr(){
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = "";
            
            if(data.userSelectedType === 'R'){
            	addr = data.roadAddress;
            }else{
            	addr = data.jibunAddress;
            }
            
            document.getElementById('join_addr_headAndMiddle').value = addr;
        }
    }).open();
}
</script>
<jsp:include page="/Layout/header.jsp"></jsp:include>

<html>
	<form action="JoinResultView.nhn" method = "post">
		<table class = "table table-bordered table-hover" border = "1" align = "center" style = "width : 30%; margin-top:10px;">
			<tr>
				<td align = "center" colspan ="2"> 
					아이디
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					<input type = "text" class="form-control"  name = "id" placeholder="이름을 적어주세요" required="required">
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					비밀번호
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					<input type = "password" class="form-control"  name = "password" placeholder="비밀번호 적어주세요" required="required">
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					비밀번호 한번더
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					<input type = "password" class="form-control"  placeholder="비밀번호" required="required">
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					이름
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					<input type = "text" name = "name" class="form-control"  placeholder="이름을 적어주세요" required="required">
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="1">
					이메일
				</td>
				<td align = "center" colspan ="1">
					성별
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="1" style = "width : 80%;">
					<input type = "text" name = "eamil_head" placeholder="이메일을 적어주세요" required="required">@
					<select name = "email_end">
						<option selected="selected">이메일선택</option>
						<option>naver.com</option>
						<option>gmail.com</option>
					</select>
				</td>
				<td align = "center" colspan ="1" style = "width : 20%;">
					<select name = "gender">
						<option selected="selected">성별선택</option>
						<option>남자</option>
						<option>여자</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					핸드폰번호
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					<input type = "text" name = "phone" class="form-control" placeholder="핸드폰번호 적어주세요" required="required">
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					거주지를 선택해주세요&nbsp;&nbsp;<input type = "button" value = "거주지 검색" onclick = "searchAddr()">
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					일반주소 <input type = "text" id = "join_addr_headAndMiddle" name = "addr_head" style = "width : 80%;" readonly="readonly"><br/>
					상세주소 <input type = "text" name = "addr_end" style = "width : 80%;">
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					<input type = "submit" value = "회원가입!">
				</td>
			</tr>
		</table>
	</form>
</html>

<jsp:include page="/Layout/footer.jsp"></jsp:include>
