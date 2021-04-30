package com.Team.QAboard;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.Team.QAboard.AnswerVO;
import com.Team.Review.vo.ReViewVO;
import com.Team.mybatis.MySession;

public class QAboardService {

	private static QAboardService instance = new QAboardService();
	private QAboardService() { }
	public static QAboardService getInstance() { return instance; }
	
	private QAboardDAO dao = QAboardDAO.getInstance();
	
	public void insert(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("QAboardService 클래스의 insert() 메소드");
		SqlSession mapper = MySession.getSession();

//		System.out.println(request);
		String q_userid = request.getParameter("q_userid");
//		System.out.println(q_userid);
		String q_title = request.getParameter("q_title");
//		System.out.println(q_title);
		String q_content = request.getParameter("q_content");
//		System.out.println(q_content);
		
		QAboardVo vo = new QAboardVo(q_userid, q_title, q_content);
		dao.insert(mapper, vo);
		mapper.commit();
		mapper.close();
	}
	
	public void QAselectList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("QAboardService 클래스의 QAselectList() 메소드");
		SqlSession mapper = MySession.getSession();
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {	}
		
		int pageSize = 10;
		int totalCount = dao.QAselectCount(mapper);
		System.out.println(totalCount);
		
		QAboardList qaboardList = new QAboardList(pageSize, totalCount, currentPage);
		
		ArrayList<AnswerVO> Alist = new ArrayList<AnswerVO>();
		Alist = dao.selectAlist(mapper);
//		System.out.println(Alist);
		request.setAttribute("Alist", Alist);
		
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", qaboardList.getStartNo());
		hmap.put("endNo", qaboardList.getEndNo());
		qaboardList.setList(dao.QAselectList(mapper, hmap));

		request.setAttribute("qaboardList", qaboardList);
		
		mapper.close();
	} 
	
	public void QAselectByIdx(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("QAboardService 클래스의 QAselectByIdx() 메소드");
		SqlSession mapper = MySession.getSession();
		
//		request 객체로 넘어오는 얻어올 글번호와 작업 후 돌아갈 페이지 번호를 받는다.
		int q_idx = Integer.parseInt(request.getParameter("idx"));
		System.out.println(q_idx);
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
				
		QAboardVo vo = dao.QAselectByIdx(mapper, q_idx);
		System.out.println(vo);
		AnswerVO avo = dao.AnswerByIdx(mapper,q_idx);
		System.out.println(avo);
		
//		브라우저에 표시할 글이 저장된 객체, 작업 후 돌아갈 페이지 번호, 줄바꿈에 사용할 이스케이프 시퀀스 request 영역에 저장한다.
		request.setAttribute("vo", vo);
		request.setAttribute("avo", avo);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("enter", "\r\n");
		mapper.close();
	}
	
	
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("QAboardService 클래스의 delete() 메소드");
		SqlSession mapper = MySession.getSession();
//		request 객체로 넘어오는 삭제할 글번호를 받는다.
		int idx = Integer.parseInt(request.getParameter("idx"));
//		글 1건을 삭제하는 메소드를실행한다.
		dao.delete(mapper, idx);
		mapper.commit();
		mapper.close();
		
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("QAboardService 클래스의 update() 메소드");
		SqlSession mapper = MySession.getSession();

		int q_idx = Integer.parseInt(request.getParameter("q_idx"));
		String q_title = request.getParameter("q_title");
		String q_content = request.getParameter("q_content");
		
		QAboardVo vo = new QAboardVo();
		
		vo.setQ_idx(q_idx);
		vo.setQ_title(q_title);
		vo.setQ_content(q_content);
		
		dao.update(mapper, vo);
		mapper.commit();
		mapper.close();
		
	}
	
	public void replyInsert(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		System.out.println("QAboardService 클래스의 replyInsert() 메소드");
		SqlSession mapper = MySession.getSession();
		
		request.setCharacterEncoding("UTF-8");
		
		int q_idx = Integer.parseInt(request.getParameter("q_idx"));
		System.out.println(q_idx);
		String a_userid = request.getParameter("a_userid");
		System.out.println(a_userid);
		String a_title = request.getParameter("a_title");
		System.out.println(a_title);
		String a_content = request.getParameter("a_content");
		System.out.println(a_content);
		
		AnswerVO vo = new AnswerVO(a_userid, a_title, a_content, q_idx);
		System.out.println(vo);
		dao.ansReply(mapper, vo);
		
		
		mapper.commit();
		mapper.close();
	}
	

	
	
	
}
