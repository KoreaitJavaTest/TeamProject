package com.Team.Client.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Team.Client.dao.ClientDao;
import com.Team.Client.service.ClientService;
import com.Team.QAboard.QAboardService;

@WebServlet("*.nhn")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
//	컨트롤러에서 사용할 service 클래스 객체를 선언한다.
	private QAboardService service = QAboardService.getInstance();
	
    public HomeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String context = url.substring(contextPath.length());
	
		String viewPage = "/WEB-INF/";
		switch (context) {
		
		// 회원가입 페이지
			case "/JoinView.nhn":
				viewPage += "ClitenJoin/JoinView";
				break;
		// 회원가입 결과 페이지
			case "/JoinResultView.nhn":
				// db , dao , sevice , db.mxl 만들기
				ClientService.getInstance().join(request,response);
				viewPage += "ClitenJoin/JoinResultView";
				break;
		// 회원가입 이메일 페이지
			case "/JoinEmailResultView.nhn":
				ClientService.getInstance().emailCheckAction(request,response);
				viewPage += "ClitenJoin/JoinEmailResultView";
				break;
		// 로그인 페이지
			case "/LoginView.nhn":
				viewPage += "Login/LoginView";
				break;
		// 로그인 결과 페이지
			case "/LoginResultView.nhn":
				ClientService.getInstance().login(request,response);
				viewPage += "Login/LoginResultView";
				break;
		// 로그아웃 페이지
			case "/LogoutView.nhn":
				ClientService.getInstance().logout(request,response);
				viewPage += "Login/LogoutView";
				break;
		// 회원정보 수정페이지가기 전 비밀번호 체크
			case "/MyEditViewPasswordCheck.nhn":
				viewPage += "ClientEdit/ClientMyEditPasswordView";
				break;
		// 내정보 수정 페이지
			case "/ClientEditView.nhn":
				ClientService.getInstance().edit(request,response);
				viewPage += "ClientEdit/ClientEditView";
				break;
		// 수정 완료
			case "/EditResultView.nhn":
				ClientService.getInstance().editOK(request,response);
				viewPage += "ClientEdit/EditResultView";
				break;
		// 리뷰게시판 페이지
			case "/ReViewBoard.nhn":
				viewPage += "ReView/ReViewBoard";
				break;
			case "/ReViewInsert.nhn":
				viewPage += "ReView/ReViewInsert";
				break;
		// ================== Q&A 게시판 =====================		
			case "/QAboard.nhn":
				service.selectList(request, response);
				viewPage += "QAboard/QAlist";
				break;
			case "/QAinsert.nhn":
				viewPage += "QAboard/QAinsert";
				break;
			case "/insertOK.nhn":
				System.out.println(request);
				service.insert(request, response);
				viewPage += "QAboard/index";
				System.out.println(viewPage);
				break;
			case "/list.nhn":
				service.selectList(request, response);
				viewPage += "QAboard/QAlist";
				break;
				
		}
		
		
		viewPage += ".jsp";
	
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}














