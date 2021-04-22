package com.Team.QAboard;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.Team.mybatis.MySession;
import com.Team.QAboard.QAboardVo;
import com.Team.QAboard.*;

public class QAboardService {

	private static QAboardService instance = new QAboardService();
	private QAboardService() { }
	public static QAboardService getInstance() { return instance; }
	
	private QAboardDAO dao = QAboardDAO.getInstance();
	
	public void insert(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("QAboardService 클래스의 insert() 메소드");
		SqlSession mapper = MySession.getSession();
		String q_userid = request.getParameter("q_userid");
		String q_title = request.getParameter("q_title");
		String q_content = request.getParameter("q_content");
		QAboardVo vo = new QAboardVo(q_userid, q_title, q_content);
		
		dao.insert(mapper, vo);
		
		mapper.commit();
		mapper.close();
	}
	
	public void selectList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("QAboardService 클래스의 selectList() 메소드");
		SqlSession mapper = MySession.getSession();
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {	}
		
		int pageSize = 10;
		int totalCount = dao.selectCount(mapper);
		
		QAboardList qaboardList = new QAboardList(pageSize, totalCount, currentPage);
		
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", qaboardList.getStartNo());
		hmap.put("endNo", qaboardList.getEndNo());
		qaboardList.setList(dao.selectList(mapper, hmap));

		request.setAttribute("qaboardList", qaboardList);
		
		mapper.close();
	} 
	
}
