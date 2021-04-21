<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.Team.Review.vo.ReViewVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String userId= ""+session.getAttribute("session_id");		//세션에 저장되있는ID호출
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String category_Detail = request.getParameter("categoryDetail");
	ReViewVO vo = new ReViewVO();
	int imgCount=0;		//업로드한 이미지갯수
	String imgNames="";
	int flag=0;
	for (int i = 1; i <= 3; i++) {
		if(request.getParameter(""+i+"") != null) {
			imgCount++;
		}else {
			break;
		}
	}
	MultipartRequest mr = new MultipartRequest(
			request,
			"D:/upload",
			5*1024*1024,
			"UTF-8",
			new DefaultFileRenamePolicy()
			);
			
// 	저장후 이름 가져오기
	Enumeration filenames = mr.getFileNames();	//파일이름(들)
	while(filenames.hasMoreElements()) {				//파일있는지 묻기
		String parameter = ""+filenames.nextElement();	//다음파일
		String fileRealName = mr.getFilesystemName(parameter);	//실제저장된파일이름
		String fileName = mr.getOriginalFileName(parameter);	//파일이름
		if(fileName==null) {									//null이면 계속
			continue;
		}else{													
			if(imgCount==1) {									//업로드 파일의개수가 1개라면
				imgNames+=fileRealName;							//imgNames에 실제 파일이름추가
			}else { 											//1개아닐떄 => 최소 2개이상일때
				imgNames+=fileRealName;							//imgNames에 실제 파일이름추가
				if(++flag<imgCount) {							// 먼져 1증가시킨 flag가 업로드할 파이르이 개수보다 작으면
					imgNames+=",";								// imgNames에 ","를 붙임 (split)하기 위함
				}
			}
		}//if..end												//한파일의 처리가 끝남
	}	//while..end
	vo.setRE_imgNames(imgNames);							//넘어온 모든 파일의 처리가 끝나면 vo객체에 파일이름을 저장시킴.
	vo.setRE_userId(userId);
	vo.setRE_title(title);
	vo.setRE_content(content);
	vo.setRE_img(imgCount);
// 	vo.setRE_categoryDetail(category_Detail);
	
	request.setAttribute("vo", vo);
// 	pageContext.forward("ReViewUpLoad.nhn");
%>
<%=vo%>






</body>
</html>