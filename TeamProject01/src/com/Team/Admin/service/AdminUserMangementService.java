package com.Team.Admin.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.Team.Admin.dao.AdminUserMangementDao;
import com.Team.Admin.vo.AdminUserMangementList;
import com.Team.Review.dao.ReViewDao;
import com.Team.mybatis.MySession;

public class AdminUserMangementService {
	private static AdminUserMangementService instance = new AdminUserMangementService();
	private AdminUserMangementService() {}
	public static AdminUserMangementService getInstance() {	return instance;}
	
	// 유저관리 게시판 접속시 유저들의 목록을 받아오는 메소드
	public void selectUserList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("selectUserList()");
		SqlSession mapper = MySession.getSession();
		int currentPage = 1;	//현재페이지
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			
		}
		int pageSize = 10;		//한페이지에 표시할 글의 개수
		int totalCount= AdminUserMangementDao.getInstance().userTotalCount(mapper);		//작성된 리뷰글의 총 개수
		System.out.println("user_totalCount : "+totalCount);
		
		AdminUserMangementList list = new AdminUserMangementList(pageSize, totalCount, currentPage);
		HashMap<String, Integer> hmap = new HashMap<>();
		hmap.put("startNo", list.getStartNo());
		hmap.put("endNo", list.getEndNo());
		
		list.setList(AdminUserMangementDao.getInstance().AdminUserSelectList(mapper,hmap));
		
		request.setAttribute("AdminUserList", list);  		// request 영역에 저장 = 게시판을 출력해내기 위함
		
		mapper.close();
	}
}
