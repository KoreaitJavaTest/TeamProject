package com.Team.Client.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.Team.Client.dao.ClientDao;
import com.Team.mybatis.MySession;

@WebServlet("/IdoverlapcheckLogic.jsp")
public class IdoverlapcheckLogic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		String client_id = request.getParameter("client_id");
		response.getWriter().write(getJSON(client_id));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);	
		
	}
	
	private String getJSON(String client_id) {
		int daoresult = -1;
		StringBuffer result = new StringBuffer("");
		SqlSession mapper = MySession.getSession();
		if(client_id.equals("")) {
			result.append("아이디를 입력해주세요");
		}else {
			ClientDao dao = ClientDao.getInstance();
			daoresult =	 dao.idoverlapcheck(mapper,client_id);
			if(daoresult == 0) {
				result.append("사용 가능한 아이디입니다.");
			}
			if(daoresult == 1) {
				result.append("이미 사용중인 아이디입니다.");
			}
		}
		
		mapper.close();
		return result.toString();
	}

}
