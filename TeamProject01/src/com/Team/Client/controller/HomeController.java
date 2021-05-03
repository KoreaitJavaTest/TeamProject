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
import com.Team.Client.point.PointService;
import com.Team.Client.service.ClientService;
import com.Team.QAboard.QAboardService;
import com.Team.Shop.service.ShopService;
import com.Team.Review.service.ReViewCommentService;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			ClientService.getInstance().join(request, response);
			viewPage += "ClitenJoin/JoinResultView";
			break;
		// 회원가입 이메일 페이지
		case "/JoinEmailResultView.nhn":
			ClientService.getInstance().emailCheckAction(request, response);
			viewPage += "ClitenJoin/JoinEmailResultView";
			break;
		// 로그인 페이지
		case "/LoginView.nhn":
			viewPage += "Login/LoginView";
			break;
		// 로그인 결과 페이지
		case "/LoginResultView.nhn":
			ClientService.getInstance().login(request, response);
			viewPage += "Login/LoginResultView";
			break;
		// 로그아웃 페이지
		case "/LogoutView.nhn":
			ClientService.getInstance().logout(request, response);
			viewPage += "Login/LogoutView";
			break;
		// 마이페이지 메인 뷰페이지
		case "/MyPageView.nhn":
			viewPage += "MyPage/MyPageMainView";
			break;
		// 회원정보 수정페이지 가기 전 비밀번호 체크
		case "/MyEditViewPasswordCheck.nhn":
			viewPage += "MyPage/ClientMyEditPasswordView";
			break;
		// 내정보 수정 페이지
		case "/ClientEditView.nhn":
			ClientService.getInstance().edit(request, response);
			viewPage += "MyPage/ClientEditView";
			break;
		// 내정보 수정 완료
		case "/EditResultView.nhn":
			ClientService.getInstance().editOK(request, response);
			viewPage += "MyPage/EditResultView";
			break;
		// 나의 게시물 관리
		case "/MyListViewPage.nhn":
			ClientService.getInstance().reviewSelect(request, response);
			viewPage += "MyPage/MyListViewPage";
			break;
		// 나의 Q&A 게시물 관리
		case "/MyQnAviewPage.nhn":
			ClientService.getInstance().myQnASelect(request, response);
			viewPage += "MyPage/MyQnAviewPage";
			break;

			// 상품 상세보기 페이지
			case "/selectProduct.nhn":
				shopService.selectProduct(request, response);
				viewPage += "Shop/selectProduct";
				break;
				
			// 조회수 증가
			case "/increment.nhn":
				shopService.increment(request, response);
				viewPage += "Shop/increment";
				break;
				
			// 장바구니 테스트
			case "/cart.nhn":
				viewPage += "Shop/cart";
				break;
			
			// 상품 삭제 페이지
			case "/deleteProduct.nhn":
				shopService.selectProduct(request, response);
				viewPage += "Shop/deleteProduct";
				break;
				
			// 상품 삭제 완료
			case "/deleteOK.nhn":
				shopService.deleteProduct(request, response);
				viewPage += "Shop/goMain";
				break;
				
			// 상품 수정 페이지
			case "/updateProduct.nhn":
				shopService.selectProduct(request, response);
				viewPage += "Shop/updateProduct";
				break;
				
			// 상품 수정 완료
			case "/updateProductOK.nhn":
				shopService.updateProduct(request, response);
				viewPage += "Shop/goMain";
				break;
/*
===================== 수정 ing =========================
			//	좋아요 업데이트
			case "/likeUpdate.nhn":
				shopService.likeUpdate(request, response);
				viewPage += "Shop/selectProduct";
				break;
			
			// 좋아요 개수 카운트
			case "/likeCount.nhn":
				shopService.likeCount(request, response);
				viewPage += "Shop/selectProduct";
				break;
========================================================
*/
			
		// ==========================================================
				// 리뷰게시판 페이지
			case "/ReViewBoard.nhn":
				ReViewService.getInstance().ReViewSelect(request,response);
				viewPage += "ReView/ReViewBoard";
				break;
				// 리뷰작성 페이지
			case "/ReViewInsert.nhn":
				viewPage += "ReView/ReViewInsert";
				break;	
				// 작성리뷰 DB추가 페이지
			case "/ReViewInsertOK.nhn":
				//저장된 세션 정보 + 리뷰 작성 게시글 정보를 받아 리뷰DB에 저장
				ReViewService.getInstance().ReViewInsert(request,response);
				viewPage += "ReView/ReViewBoardListOk";
				//여기서 그냥 보드로 가는게 맞을까? 아니면 ReViewBoard.nhn으로 가는게 맞을까?
				break;
			case "/ReHitUp.nhn":
				//선택한 게시글의 조회수를 1증가 + idx로 게시글 정보를 vo 객체에 저장 -> 선택한 게시글 자세히보기 페이지로 이동
				ReViewService.getInstance().ReHitUp(request, response);
				viewPage += "ReView/ReViewPostDetail";
				break;
			case "/ReViewSelect.nhn":
				ReViewService.getInstance().ReViewDetailSelect(request,response);
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
			case "/ReViewDeleteOK.nhn":
				//게시글 삭제하기 버튼 클릭후 예,아니오 클릭시에따라 가지는 jsp 파일로 가게함
//				ReViewService.getInstance().ReViewDelete(request,response);
				viewPage += "ReView/ReViewDeleteOK";
				break;
			case "/ReViewDelete.nhn":
				// idx,currentPage를 넘겨받고 idx 게시글을 삭제함
				ReViewService.getInstance().ReViewDelete(request, response);
				viewPage += "ReView/ReViewBoardListOk";
				break;
			case "/ReViewSearch.nhn":
				//searchName , searchText를 넘겨받고 해당 게시글을 검색하여 다시 ReViewBoard로 돌려준다.
				ReViewService.getInstance().ReViewSearch(request,response);
				viewPage += "ReView/ReViewBoard";	
				break;
			case "/ReViewComment.nhn":
				//ReViewDetail 받은 답글정보(내용,답글을쓴아이디,답글이 넣은 리뷰글번호,현재페이지) 를 넘겨받고 답글을 추가하는 메소드를 호출.
				ReViewCommentService.getInstance().commentInsert(request,response);
				//리뷰글(idx), currentPage => request에 저장되있는상태
				viewPage += "ReView/ReViewBoardListOk";
				break;
			case "/updateComment.nhn":
				
				ReViewCommentService.getInstance().updateComment(request,response);
				//리뷰글(idx), currentPage => request에 저장되있는상태
				viewPage += "ReView/ReViewPostDetail";
				break;
			case "/commentDelete.nhn":
				
				ReViewCommentService.getInstance().deleteComment(request,response);
				//리뷰글(idx), currentPage => request에 저장되있는상태
				viewPage += "ReView/ReViewPostDetail";
				break;
				
			// MYPage (진호추가)==============================================
			//출석체크후 포인트 검사 후 증가or경고문

			case "/depositPoint.nhn":
				PointService.getInstance().AttentionCheck(request,response);
//				response.getWriter().write("T");
				return;
			case "/MyPointSelect.nhn":
				PointService.getInstance().SelectMyPointDeposit(request,response);
				viewPage+="MyPage/MyPagePointLogView";
				break;
		// MYPage (진호추가끝)=============================================

			// ================== Q&A 게시판 =====================		
		case "/QAboard.nhn":
			service.QAselectList(request, response);
			viewPage += "QAboard/QAlist";
			break;
		case "/QAinsert.nhn":
			viewPage += "QAboard/QAinsert";
			break;
		case "/insertOK.nhn":
			service.insert(request, response);
			viewPage += "QAboard/index";
//			System.out.println(viewPage);
			break;
		case "/list.nhn":
			service.QAselectList(request, response);
			viewPage += "QAboard/QAlist";
			break;
		case "/contentView.nhn":
			service.QAselectByIdx(request, response);
			viewPage += "QAboard/QAcontentView";
			break;
		case "/delete.nhn":
			service.delete(request, response);
			viewPage += "QAboard/goList";
			break;
		case "/update.nhn":
			service.update(request, response);
			viewPage += "QAboard/goList";
			break;
		case "/reply.nhn":
			service.QAselectByIdx(request, response);
			viewPage += "QAboard/QAreply";
			break;
		case "/replyView.nhn":
			service.QAselectByIdx(request, response);
			viewPage += "QAboard/QAreplyView";
			break;
		case "/replyInsert.nhn":
			service.replyInsert(request, response);
			viewPage += "QAboard/goList";
			break;
		case "/replyUpdate.nhn":
			service.aupdate(request,response);
			viewPage += "QAboard/goList";
			break;
		case "/AnsDelete.nhn":
			service.ansdelete(request,response);
			viewPage += "QAboard/goList";
			break;
		// ==================== 상품 페이지 ======================

		// 전체 상품 페이지
		case "/AllProducts.nhn":
			shopService.selectAllProduct(request, response);
			viewPage += "Shop/AllProducts";
			break;

		// 상품 등록 페이지
		case "/insertProduct.nhn":
			viewPage += "Shop/insertProduct";
			break;
		case "/insertProductOK.nhn":
			shopService.insertProduct(request, response);
//				System.out.println("상품 등록 끝");
			viewPage += "Shop/goMain";
			break;

		// 브랜드별로 나눔
		case "/categoryDetail.nhn":
			shopService.selectCategoryDetail(request, response);
			viewPage += "Shop/categoryDetail";
			break;

		}
		viewPage += ".jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);

	}

}
