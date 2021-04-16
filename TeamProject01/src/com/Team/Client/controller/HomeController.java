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

@WebServlet("*.nhn")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
//	컨트롤러에서 사용할 service 클래스 객체를 선언한다.
	
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
		}
		viewPage += ".jsp";
	
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}














