package com.Team.Client.service;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.mapper.Mapper;
import org.apache.ibatis.session.SqlSession;

import com.Team.Client.dao.ClientDao;
import com.Team.Client.util.Gmail;
import com.Team.Client.util.SHA256;
import com.Team.Client.vo.ClientVo;
import com.Team.QAboard.QAboardList;
import com.Team.Review.dao.ReViewDao;
import com.Team.Review.vo.ReViewList;
import com.Team.mybatis.MySession;

public class ClientService {
	private static ClientService instance = new ClientService();
	private ClientService() {}
	public static ClientService getInstance() {	return instance;}
	
	ClientDao dao = ClientDao.getInstance();
	
	public void join(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String addr_head = request.getParameter("addr_head");
		String addr_end = request.getParameter("addr_end");
		String email_head = request.getParameter("email_head");
		String email_end = request.getParameter("email_end");
		String email = email_head + "@" + email_end;
		String phone = request.getParameter("phone");
		
		if(gender.equals("성별선택") || email_end.equals("이메일선택")){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안된 사항이 있습니다');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		ClientVo vo = new ClientVo(id, password, gender, addr_head, addr_end, phone, email, SHA256.getSHA256(email));
		SqlSession mapper = MySession.getSession();
		int result = dao.join(mapper,vo);
		
		if(result == -1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('회원가입에 문제가 생겼습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}	
		
		mapper.commit();
		
		String host = "http://localhost:9090/TeamProject01/";
		String to = dao.getClientEmail(mapper,vo.getClient_id());
		String from = "gygus7345@naver.com";
		String subject = "이메일인증입니다.";
		String content = "다음 링크에 접속하여 이메일 인증을 진행하세요" + "<a href ='"+host+"JoinEmailResultView.nhn?code=" + new SHA256().getSHA256(to) + "'>이메일 인증하기</a>";
		
		Properties p = new Properties();
		p.put("mail.smtp.user",from);
		p.put("mail.smtp.host","smtp.googlemail.com");
		p.put("mail.smtp.port","465");
		p.put("mail.smtp.starttls.enable","true");
		p.put("mail.smtp.auth","true");
		p.put("mail.smtp.debug","true");
		p.put("mail.smtp.socketFactory.port","465");
		p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback","false");
		
		try{
			
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p,auth);
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO,toAddr);
			msg.setContent(content,"text/html;charset=UTF-8");
			Transport.send(msg);
			
		}catch(Exception e){
			e.printStackTrace();
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('오류가 발생했습니다..');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		} 
		
		mapper.close();

		
	}
	public void emailCheckAction(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String code = request.getParameter("code");
		SqlSession mapper = MySession.getSession();
		dao.emailCheckAction(mapper,code);
		mapper.commit();
		mapper.close();
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		SqlSession mapper = MySession.getSession();
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		ClientVo vo = new ClientVo(id,password);
		vo = dao.login(mapper,vo);	
		if(null == vo) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('아이디 혹은 비밀번호가 틀립니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		if(vo.getClient_emailcheck().equals("false")) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이메일인증을 한 후 로그인을 시도해주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}else {
		HttpSession session = request.getSession();
		session.setAttribute("session_level", vo.getClient_level()); 
		session.setAttribute("session_id", vo.getClient_id()); 
		session.setAttribute("session_password", vo.getClient_password()); 
		session.setAttribute("session_gender", vo.getClient_gender()); 
		session.setAttribute("session_addr_head", vo.getClient_addr_head()); 
		session.setAttribute("session_addr_end", vo.getClient_addr_end()); 
		session.setAttribute("session_phone", vo.getClient_phone()); 
		session.setAttribute("session_email", vo.getClient_email()); 
		session.setAttribute("session_point", vo.getClient_point()); 
		}
		
		mapper.close();
		
	}
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("session_level");
		session.removeAttribute("session_id");
		session.removeAttribute("session_password");
		session.removeAttribute("session_gender");
		session.removeAttribute("session_addr_head");
		session.removeAttribute("session_addr_end");
		session.removeAttribute("session_phone");
		session.removeAttribute("session_email");
		session.removeAttribute("session_point");
		session.invalidate();	
	}
	public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		SqlSession mapper = MySession.getSession();
		String id = (String) session.getAttribute("session_id");
		String password = request.getParameter("password");
		ClientVo vo = null;
		if(password.equals((String)session.getAttribute("session_password"))) {
			vo = new ClientVo(id, password);
			vo = dao.ClientInfo(mapper, vo);
		}else {
			vo = new ClientVo(id, password);
			vo = dao.ClientInfo(mapper, vo);
			if(null == vo) {
				PrintWriter script;
				script = response.getWriter();
				script.println("<script>");
				script.println("alert('비밀번호가 일치하지 않습니다.');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
				return;
			}
		}
		request.setAttribute("vo", vo);
		mapper.close();
	}
	
	public void editOK(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ClientVo vo = null;
		
		HttpSession session = request.getSession();
		SqlSession mapper = MySession.getSession();
		
		String id = (String) session.getAttribute("session_id");
		String before_password = (String)session.getAttribute("session_password");
		String before_addr_head = (String)session.getAttribute("session_addr_head");
		String before_addr_end = (String)session.getAttribute("session_addr_end");
		
		String password = request.getParameter("password");
		String addr_head = request.getParameter("addr_head");
		String addr_end = request.getParameter("addr_end");
		if(password.equals("") || password.equals(before_password)) {
			if(addr_head.equals(before_addr_head)) {
				if(addr_end.equals(before_addr_end)) {
					PrintWriter script;
					try {
						script = response.getWriter();
						script.println("<script>");
						script.println("alert('변경된 내용이없어 메인페이지로 돌아갑니다.');");
						script.println("location.href = 'index.jsp';");
						script.println("</script>");
						script.close();
						return;
					} catch (IOException e) {e.printStackTrace();}
				}else {
					System.out.println("비밀번호는 변경하지않지만 주소만 변경");
					vo = new ClientVo();
					vo.setClient_password(before_password);
					vo.setClient_addr_head(addr_head);
					vo.setClient_addr_end(addr_end);
					vo.setClient_id(id);
					
					dao.ClientUpdate(mapper,vo);
					
					session.setAttribute("session_addr_head", addr_head);
					session.setAttribute("session_addr_end", addr_end);
					mapper.commit();
					mapper.close();
				}
			}else {
				System.out.println("비밀번호는 변경하지않지만 주소만 변경");
				vo = new ClientVo();
				vo.setClient_password(before_password);
				vo.setClient_addr_head(addr_head);
				vo.setClient_addr_end(addr_end);
				vo.setClient_id(id);
				
				dao.ClientUpdate(mapper,vo);
				
				session.setAttribute("session_addr_head", addr_head);
				session.setAttribute("session_addr_end", addr_end);
				mapper.commit();
				mapper.close();
			}
		}else {
			System.out.println("비밀번호 주소 둘다변경");
			vo = new ClientVo();
			vo.setClient_password(password);
			vo.setClient_addr_head(addr_head);
			vo.setClient_addr_end(addr_end);
			vo.setClient_id(id);
			dao.ClientUpdate(mapper,vo);
			
			session.setAttribute("session_password", password);
			session.setAttribute("session_addr_head", addr_head);
			session.setAttribute("session_addr_end", addr_end);
			mapper.commit();
			mapper.close();
		}

		
		
	}
	
	public void reviewSelect(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SqlSession mapper = MySession.getSession();
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) { }
		
		int pagesize = 10;
		
		String id = (String)session.getAttribute("session_id");
		ClientDao dao = ClientDao.getInstance();
		
		int totalcount = dao.reviewListCount(mapper,id);
//		System.out.println(listcount);
		
		ReViewList reViewList = new ReViewList(pagesize, totalcount, currentPage,id);

//		1페이지 분량의 글 목록을 얻어와서 mvcboardList의 ArrayList에 넣어준다.
		reViewList.setList(dao.selectList(mapper, reViewList));
		
		request.setAttribute("reViewList", reViewList);
		mapper.close();
		
	}
	public void myQnASelect(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SqlSession mapper = MySession.getSession();
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) { }
		
		int pagesize = 10;
		
		String id = (String)session.getAttribute("session_id");
		ClientDao dao = ClientDao.getInstance();
		
		int totalcount = dao.qnaTotalCount(mapper,id);
		System.out.println("토탈카운트 : "+totalcount);
		
		QAboardList qaBoardList = new QAboardList(pagesize, totalcount, currentPage,id);
		System.out.println("id: "+id);
//		1페이지 분량의 글 목록을 얻어와서 mvcboardList의 ArrayList에 넣어준다.
		qaBoardList.setList(dao.QAselectList(mapper, qaBoardList));

		request.setAttribute("qaList", qaBoardList);
		mapper.close();
	}
	public void MyClientWithdrawal(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SqlSession mapper = MySession.getSession();
		
		String id = (String) session.getAttribute("session_id");
		ClientDao dao = ClientDao.getInstance();
		dao.deleteId(mapper,id);

		session.removeAttribute("session_level");
		session.removeAttribute("session_id");
		session.removeAttribute("session_password");
		session.removeAttribute("session_gender");
		session.removeAttribute("session_addr_head");
		session.removeAttribute("session_addr_end");
		session.removeAttribute("session_phone");
		session.removeAttribute("session_email");
		session.removeAttribute("session_point");
		session.invalidate();	
		
		mapper.commit();
		mapper.close();
	}
	public void SearchMyIdByEmailDo(HttpServletRequest request, HttpServletResponse response) {
		SqlSession mapper = MySession.getSession();
		String email = request.getParameter("email");
		ClientDao dao = ClientDao.getInstance();
		String result =  dao.SearchMyIdByEmailDo(mapper,email);
		
		request.setAttribute("result", result);		
		mapper.commit();
		mapper.close();
	}
	public void SearchMyPw(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SqlSession mapper = MySession.getSession();
		ClientDao dao = ClientDao.getInstance();
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		ClientVo vo = dao.checkidandemail(mapper,id);
		
		if(email.equals(vo.getClient_email())) {
			mapper.commit();
			
			String host = "http://localhost:9090/TeamProject01/";
			String to = dao.getClientEmail(mapper,vo.getClient_id());
			String from = "gygus7345@naver.com";
			String subject = "비밀번호를 변경합니다..";
			String content = "다음 링크에 접속하여 비밀번호를 변경하세요" + "<a href ='"+host+"MyPasswordChange.nhn?id=" + id + "'>비밀번호 변경하기.</a>";
			
			Properties p = new Properties();
			p.put("mail.smtp.user",from);
			p.put("mail.smtp.host","smtp.googlemail.com");
			p.put("mail.smtp.port","465");
			p.put("mail.smtp.starttls.enable","true");
			p.put("mail.smtp.auth","true");
			p.put("mail.smtp.debug","true");
			p.put("mail.smtp.socketFactory.port","465");
			p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.socketFactory.fallback","false");
			
			try{
				
				Authenticator auth = new Gmail();
				Session ses = Session.getInstance(p,auth);
				ses.setDebug(true);
				MimeMessage msg = new MimeMessage(ses);
				msg.setSubject(subject);
				Address fromAddr = new InternetAddress(from);
				msg.setFrom(fromAddr);
				Address toAddr = new InternetAddress(to);
				msg.addRecipient(Message.RecipientType.TO,toAddr);
				msg.setContent(content,"text/html;charset=UTF-8");
				Transport.send(msg);
				
			}catch(Exception e){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('오류가 발생했습니다..');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
				return;
			} 
			
			mapper.close();
		}else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('아이디 혹은 이메일이 잘못되었습니다.<br/>다시입력해주세요');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		
	}
	public void ChangePassword(HttpServletRequest request, HttpServletResponse response) {
		SqlSession mapper = MySession.getSession();
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		ClientVo vo = new ClientVo(id, pw);
		
		ClientDao dao = ClientDao.getInstance();
		dao.ChangePassword(mapper,vo);
		
		mapper.commit();
		mapper.close();
		
	}
	public void getBaguni(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<String> baguni = new ArrayList<String>();
		HttpSession session = request.getSession();
		String item1 = (String)request.getSession().getAttribute("session_cart_1");  
		String item2 = (String)request.getSession().getAttribute("2");
		System.out.println(item1);
		System.out.println(item2);
		
		for(int i = 0; i < 10; i++) {
			String check = (String) session.getAttribute("session_cart_" + i);
			System.out.println(check + "체크");
//			baguni.add((String)session.getAttribute("session_cart_" + i));
		}
		System.out.println("바구니 : " + baguni);
		request.setAttribute("list", baguni);
	}
}





