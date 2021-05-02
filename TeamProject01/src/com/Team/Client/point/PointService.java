package com.Team.Client.point;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.Team.Client.dao.ClientDao;
import com.Team.mybatis.MySession;

public class PointService {
	private static PointService instance = new PointService();
	private PointService() {}
	public static PointService getInstance() {	return instance;}
	
	
	
	
	//출석체크 버튼 클릭후 id검사후 출석체크 포인트 적립 or 노적립후 리턴
	public void AttentionCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset:UTF-8");
		HttpSession session = request.getSession();
		SqlSession mapper = MySession.getSession();
		int check = 0;
		int attentionPoint = 50;
		String content = "출석체크 포인트";
		String userId = request.getParameter("userId");
		System.out.println(userId);
		//point테이블에서 writeDate를 가져와서(해당 유저의) 오늘의 날짜와 match되면 적립x match가  안되면 적립한다.
		ArrayList<attentionPointVO> PointList = ClientDao.getInstance().selectPoint(mapper,userId);
		Date date = new Date(System.currentTimeMillis());
		attentionPointVO logVo = new attentionPointVO(userId, attentionPoint,content);
		System.out.println("메소드 시작전까진 잘됨");
		if(PointList.size()==0) {
			attentionPointDAO.getInstance().insertPointLog(mapper,logVo);
			ClientDao.getInstance().depositAttentionPoint(mapper,logVo);
			System.out.println("test");
			response.getWriter().write("출석포인트 50Point 접릭 되었습니다!");
		}else { //가져온 DB 가있으면 검사후추가
			for (int i = 0; i < PointList.size(); i++) {
//				System.out.println(PointList.get(i).getDepositDate().getMonth()+1);//5
				System.out.println(PointList.get(i).getDepositDate().getDate());
				if(PointList.get(i).getDepositDate().getMonth()+1!=date.getMonth()+1 && PointList.get(i).getDepositDate().getDate()!=date.getDate()
					|| PointList.get(i).getDepositDate().getMonth()+1==date.getMonth()+1 && PointList.get(i).getDepositDate().getDate()!=date.getDate()) {
					check++;
				}
			if(check==PointList.size()) { //포인트내역추가 , 유저 포인트 증가
				attentionPointDAO.getInstance().insertPointLog(mapper,logVo);
				ClientDao.getInstance().depositAttentionPoint(mapper,logVo);
				response.getWriter().write("출석포인트 50Point 적립 되었습니다!");
			}else {
				response.getWriter().write("오늘 이미 출석포인트를 적립 받으셨습니다!");
			}
				
		}
			
		}
		int user_point = ClientDao.getInstance().userPointSelect(mapper,userId);
		session.setAttribute("session_point", user_point);//다시 세션에저장해줘야함
		
		
		mapper.commit();
		mapper.close();
	}
	public void SelectMyPointDeposit(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset:UTF-8");
		HttpSession session = request.getSession();
		SqlSession mapper = MySession.getSession();
		
		String userId=""+session.getAttribute("session_id");
		
		ArrayList<attentionPointVO> list = attentionPointDAO.getInstance().SelectMyPointDeposit(mapper,userId);
		System.out.println("해당아이디 포인트 사용내역 : "+list);
		request.setAttribute("list", list);
		mapper.close();
		
		
	}
		
		
		

		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
