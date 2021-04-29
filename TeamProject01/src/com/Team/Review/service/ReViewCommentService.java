package com.Team.Review.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.Team.Review.dao.ReViewCommentDao;
import com.Team.Review.dao.ReViewDao;
import com.Team.Review.vo.ReViewCommentVO;
import com.Team.mybatis.MySession;

public class ReViewCommentService {
	private static ReViewCommentService instance = new ReViewCommentService();
	private ReViewCommentService() {}
	public static ReViewCommentService getInstance() {	return instance;}
	
	
	//데이터를 넘겨받고 답글이 달린 글번호에 답글카운트 1개 추가후 답글DB에 insert
	public void commentInsert(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		SqlSession mapper = MySession.getSession();
		HttpSession session = request.getSession();		// 현재로그인ID를 받기위해 세션을받아온다.
		int flag= 4;
		String userId =""+session.getAttribute("session_id");   //답글작성자
		String content = request.getParameter("content");		//답글내용
		int currentPage =1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));	//현재페이지
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		int refIdx = Integer.parseInt(request.getParameter("refIdx"));	//답글을추가할 글번호

		System.out.println("userId:"+userId);
		System.out.println("content:"+content);
		System.out.println("currentPage:"+currentPage);
		System.out.println("refIdx:"+refIdx);
		
		
		
		
		ReViewCommentVO vo = new ReViewCommentVO(refIdx, content,userId);
//		System.out.println(vo);	//확인완료
		//답글이 달린 글번호에 들어가서 답글 갯수를 1올려야한다.
		ReViewDao.getInstance().CommentUp(mapper,refIdx);
		
		//vo에 초기화한 답글이 들어간 리뷰글번호,답글내용,답글  작성자를 전달
		ReViewCommentDao.getInstance().insertComment(mapper,vo);
		
		request.setAttribute("flag", flag);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("idx", refIdx);
		mapper.commit();
		mapper.close();
		
		
		
		
	}
	public void updateComment(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		SqlSession mapper = MySession.getSession();
		HttpSession session = request.getSession();		// 현재로그인ID를 받기위해 세션을받아온다.
		
		int Commentidx = Integer.parseInt(request.getParameter("Commentidx"));			//댓글번호
		String content =request.getParameter("content");					//수정한댓글
		//수정후
		HashMap<String, Object> hmap = new HashMap<>();
		hmap.put("idx", Commentidx);
		hmap.put("content", content);
		
		ReViewCommentDao.getInstance().updateComment(mapper,hmap);			//업데이트
		mapper.commit();
		
		
		
		ReViewService.getInstance().ReViewDetailSelect(request, response); // 업뎃후 본글 다시 가져와서 디테일뷰로 이동
		
		mapper.close();
		
		
		
		
		
	}
	public void deleteComment(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		SqlSession mapper = MySession.getSession();
		HttpSession session = request.getSession();		// 현재로그인ID를 받기위해 세션을받아온다.
		int CommentIdx = Integer.parseInt(request.getParameter("commentIdx"));			//댓글번호
		int idx = Integer.parseInt(request.getParameter("idx"));			//댓글을가진 게시글번호
		
		//게시글 댓글개수를 조정한다.
		ReViewDao.getInstance().minusCommentCount(mapper,idx);
		
		//댓글DB에서 해당 댓글번호를 열을 삭제
		ReViewCommentDao.getInstance().deleteComment(mapper,CommentIdx);
		mapper.commit();
		ReViewService.getInstance().ReViewDetailSelect(request, response); // 업뎃후 본글 다시 가져와서 디테일뷰로 이동
		mapper.close();
	}
	
	
	
}
