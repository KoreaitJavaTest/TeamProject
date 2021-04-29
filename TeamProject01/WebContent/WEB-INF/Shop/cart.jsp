<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
    
    function cart() {
    	for(var i = 0; i < sessionStorage.length; i++){
    		var cart = sessionStorage.key(i);
    			alert(cart + "번 상품");
    	}
    }
    
    </script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<input type="button" value="보기" onclick="cart()"/>

</body>
</html>