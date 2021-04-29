package com.Team.Client.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Team.Client.dao.ClientDao;
import com.Team.Client.service.ClientService;
import com.Team.QAboard.QAboardService;
import com.Team.Shop.service.ShopService;
import com.Team.Review.service.ReViewService;

@WebServlet("*.nhn")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ShopService shopService = ShopService.getInstance();
	
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
		// 마이페이지 메인 뷰페이지 
			case "/MyPageView.nhn":
				viewPage += "MyPage/MyPageMainView";
				break;
		// 회원정보 수정페이지가기 전 비밀번호 체크
			case "/MyEditViewPasswordCheck.nhn":
				viewPage += "MyPage/ClientMyEditPasswordView";
				break;
		// 내정보 수정 페이지
			case "/ClientEditView.nhn":
				ClientService.getInstance().edit(request,response);
				viewPage += "MyPage/ClientEditView";
				break;
		// 내정보 수정 완료
			case "/EditResultView.nhn":
				ClientService.getInstance().editOK(request,response);
				viewPage += "MyPage/EditResultView";
				break;
		// 나의 게시물 관리
			case "/MyListViewPage.nhn":
				ClientService.getInstance().reviewSelect(request,response);
				viewPage += "MyPage/MyListViewPage";
				break;
		// 나의 Q&A 게시물 관리
			case "/MyQnAviewPage.nhn":
				ClientService.getInstance().myQnASelect(request,response);
				viewPage += "MyPage/MyQnAviewPage";
				break;
		// 리뷰게시판 페이지
			case "/ReViewBoard.nhn":
				ReViewService.getInstance().ReViewSelect(request,response);
				viewPage += "ReView/ReViewBoard";
				break;
		// 리뷰작성 페이지
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
				
		// ==================== 상품 페이지 ======================
			case "/AllProducts.nhn":
				shopService.selectAllProduct(request,response);
				viewPage += "Shop/AllProducts";
				break;
			case "/insertProduct.nhn":
				viewPage += "Shop/insertProduct";
				break;
			case "/insertProductOK.nhn":
				shopService.insertProduct(request,response);
				shopService.selectAllProduct(request, response);
				viewPage += "Shop/AllProducts";
				break;
			case "/nike.nhn":
				shopService.selectNike(request, response);
				viewPage += "Shop/brand/nike";
				break;
			case "/adidas.nhn":
				shopService.selectAdidas(request, response);
				viewPage += "Shop/brand/adidas";
				break;
			case "/newbalance.nhn":
				shopService.selectNewbalance(request, response);
				viewPage += "Shop/brand/newbalance";
				break;
		// ==========================================================
		// 작성리뷰 DB추가 페이지
			case "/ReViewInsertOK.nhn":
				//저장된 세션 정보 + 리뷰 작성 게시글 정보를 받아 리뷰DB에 저장
				ReViewService.getInstance().ReViewInsert(request,response);
				viewPage += "ReView/ReViewBoardListOk";
				//여기서 그냥 보드로 가는게 맞을까? 아니면 ReViewBoard.nhn으로 가는게 맞을까?
				break;
			case "/ReHitUp.nhn":
				//선택한 게시글의 조회수를 1증가 + idx로 게시글 정보를 vo 객체에 저장 -> 선택한 게시글 자세히보기 페이지로 이동
				ReViewService.getInstance().ReHitUp(request,response);
				viewPage += "ReView/ReViewPostDetail";
				break;
			case "/ReViewReport.nhn":
				//게시글 디테일 정보에서 신고하기 버튼을 누르고 => 누적신고횟수를 1증가시킨다. ( 추후 한ID당 중복 신고횟수를 없애야함)
				ReViewService.getInstance().ReViewReport(request,response);
				ReViewService.getInstance().ReViewSelect(request,response);
				viewPage += "ReView/ReViewBoard";
				break;
			case "/ReViewUpdate.nhn":
				//수정하기 버튼 클릭후 수정할 게시글번호(idx) 수정할 글이 있는 currentPage가 request영역에 저장되어 옴.
				ReViewService.getInstance().selectByIdx(request,response);
				//idx로 해당 게시글 정보를 request 영역에 저장후 update View 단으로 이동
				viewPage += "ReView/ReViewUpdate";
				break;
			case "/ReViewUpdateOK.nhn":
				//수정하기 버튼 클릭후 수정할 게시글번호(idx) 수정할 글이 있는 currentPage가 request영역에 저장되어 옴.
				ReViewService.getInstance().ReViewUpdate(request,response);
				//idx로 해당 게시글 정보를 request 영역에 저장후 update View 단으로 이동
				viewPage += "ReView/ReViewPostDetail";
				break;
		}
				
			
		
		
		viewPage += ".jsp";
	
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}














