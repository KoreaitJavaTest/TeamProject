package com.Team.Review.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.Team.Review.dao.ReViewDao;
import com.Team.Review.vo.ReViewList;
import com.Team.Review.vo.ReViewVO;
import com.Team.mybatis.MySession;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReViewService {
	private static ReViewService instance = new ReViewService();
	private ReViewService() {}
	public static ReViewService getInstance() {	return instance;}
	
	
	
	
	// 리뷰글 작성한 seesion Id, 리뷰글제목,내용,이미지 업로드, 카테고리디테일을 입력받고 request로 전달받음
	public void ReViewInsert(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		SqlSession mapper = MySession.getSession();		// mapper
		HttpSession session = request.getSession();		// 현재로그인ID를 받기위해 세션을받아온다.
		ServletContext application = request.getServletContext();
		ReViewVO vo = new ReViewVO();
		String userId= ""+session.getAttribute("session_id");		//세션에 저장되있는ID호출
		String imgNames="";
		int flag=0;
		int imgCount=0;		//업로드한 이미지갯수
		
//		파일업로드 시작 ===============================
		try {
			MultipartRequest mr = new MultipartRequest(request,
//					application.getRealPath("../upload"),
					"D:/upload",
					5*1024*1024,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);
//			저장후 이름 가져오기
			Enumeration<String> filenames = mr.getFileNames();	//파일이름(들)

			while(filenames.hasMoreElements()) {				//파일있는지 묻기
				String parameter = ""+filenames.nextElement();	//다음파일
				String fileRealName = mr.getFilesystemName(parameter);	//실제저장된파일이름
				String fileName = mr.getOriginalFileName(parameter);	//파일이름
				if(fileName==null) {									//null이면 계속
					continue;
				}
				imgNames+=fileRealName;							//imgNames에 실제 파일이름추가
				if(filenames.hasMoreElements()) {
					imgNames+=",";								// imgNames에 ","를 붙임 (split)하기 위함
				}
			}	//while..end	
			String[] imgName = imgNames.split(",");
			imgCount = imgName.length;
			vo.setRE_imgNames(imgNames);							//넘어온 모든 파일의 처리가 끝나면 vo객체에 파일이름을 저장시킴.
			vo.setRE_userId(userId);
			vo.setRE_title(mr.getParameter("title"));
			vo.setRE_content(mr.getParameter("content"));
			vo.setRE_img(imgCount);
			vo.setRE_categoryDetail(mr.getParameter("categoryDetail"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(vo);
		ReViewDao.getInstance().ReViewInsert(mapper,vo);				//넘어온정보 + 넘어온 파일의 이름들("," 포함해서)를 DB에저장하는 sql메소드호출
		mapper.commit();
		mapper.close();
	
	}// ReViewInsert...end


	public void ReViewSelect(HttpServletRequest request, HttpServletResponse response) {
		SqlSession mapper = MySession.getSession();
		int currentPage = 1;	//현재페이지
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			
		}
		int pageSize = 6;		//한페이지에 표시할 글의 개수
		int totalCount= ReViewDao.getInstance().totalCount(mapper); 		//작성된 리뷰글의 총 개수
		
		ReViewList list = new ReViewList(pageSize, totalCount, currentPage);
		
		HashMap<String, Integer> hmap = new HashMap<>();
		hmap.put("startNo", list.getStartNo());
		hmap.put("endNo", list.getEndNo());
		
		list.setList(ReViewDao.getInstance().selectReViewList(mapper,hmap));
		
		request.setAttribute("ReViewList", list);  		// request 영역에 저장 = 게시판을 출력해내기 위함
		
		mapper.close();
		
	}
	public void uploadImg(HttpServletRequest request, HttpServletResponse response) {
		SqlSession mapper = MySession.getSession();

		
		
	}
	public void ReHitUp(HttpServletRequest request, HttpServletResponse response) {
		SqlSession mapper = MySession.getSession();
		int currentPage = Integer.parseInt(request.getParameter("currentPage")); //선택한 게시글이 있는 페이지번호
		int idx = Integer.parseInt(request.getParameter("idx"));	//선택한 게시글 글번호
		
		//선택한 게시글의 조회수를 올려준다.
		ReViewDao.getInstance().ReHitUp(mapper,idx);
		
		//선택한 게시글 정보를 가져와 담아준다.
		ReViewVO vo = ReViewDao.getInstance().selectByIdx(mapper,idx);
		
		String[] fileNameList = vo.getRE_imgNames().split(",");
		
		
		for (int i = 0; i < fileNameList.length; i++) {
			String fileName ="http://localhost:8009/korea/upload/";
			fileName+=fileNameList[i];
			request.setAttribute("fileName"+(i+1), fileName);
			System.out.println(fileName);
		}
			
		

		//디테일 페이지에 넘겨주기 위해 request영역에 저장
		request.setAttribute("vo", vo);
		request.setAttribute("currentPage", currentPage);

		
		mapper.commit();
		mapper.close();
	}
	public void ReViewReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		SqlSession mapper = MySession.getSession();
		int idx = Integer.parseInt(request.getParameter("idx"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		ReViewDao.getInstance().ReViewReport(mapper,idx);	// 신고횟수 1회증가
		ReViewVO vo = ReViewDao.getInstance().selectByIdx(mapper,idx);	
		
		
		
		
		request.setAttribute("currentPage", currentPage);
		mapper.commit();
		mapper.close();
	}
	// idx를 전달받아 데이터 한건을 가지고 오는 메소드를 호출하는 메소드
	public void selectByIdx(HttpServletRequest request, HttpServletResponse response) {
		SqlSession mapper = MySession.getSession();
		int idx = Integer.parseInt(request.getParameter("idx"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		ReViewVO vo = ReViewDao.getInstance().selectByIdx(mapper,idx);	// 1건의 정보를 vo 객체에 저장	
		
		String[] fileNameList = vo.getRE_imgNames().split(",");
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < fileNameList.length; i++) {
			String fileName ="http://localhost:8009/korea/upload/";
			fileName+=fileNameList[i];
			request.setAttribute("fileName"+(i+1), fileName);
			if(i!=fileNameList.length-1) {
			buffer.append(fileNameList[i]+",");
			}else {
				buffer.append(fileNameList[i]);
			}
		}
				

		request.setAttribute("buffer", buffer);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("vo", vo);
		
		
		mapper.close();
	}
	// 이미지수정 View에서 이미지를안바꾸고 기존이미지를 들고가는방법
	// 기존이미지를 수정하고 새로운 이미지를 업데이트 하는 방법에 따라서 sql을 불러오는 방식이 다른 메소드
	public void ReViewUpdate(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ReViewUpdate()실행");
		SqlSession mapper = MySession.getSession();
		int flag=0;
		int imgCount=0;								//업로드한 이미지갯수
		int idx = 0;
		int currentPage = 0;
		String imgNames="";
		ReViewVO vo = new ReViewVO();				// vo 객체
		try {
			MultipartRequest mr = new MultipartRequest(request,
//					application.getRealPath("../upload"),
					"D:/upload",
					5*1024*1024,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);
//			저장후 이름 가져오기
			Enumeration<String> filenames = mr.getFileNames();				//업로드로 올라온 파일이름(들)

			idx = Integer.parseInt(mr.getParameter("idx"));
			currentPage = Integer.parseInt(mr.getParameter("currentPage"));
			while(filenames.hasMoreElements()) {							//파일있는지 묻기
				String parameter = ""+filenames.nextElement();				//다음파일
				String fileRealName = mr.getFilesystemName(parameter);		//실제저장된파일이름
				String fileName = mr.getOriginalFileName(parameter);		//파일이름
				if(fileName==null) {										//null이면 계속
					continue;
				}
				imgNames+=fileRealName;										//imgNames에 실제 파일이름추가
				if(filenames.hasMoreElements()) {
					imgNames+=",";											// imgNames에 ","를 붙임 (split)하기 위함
				}
			}//while..end	
			
//			======================vo 저장========================
			vo.setRE_title(mr.getParameter("title"));
			vo.setRE_content(mr.getParameter("content"));
			vo.setRE_categoryDetail(mr.getParameter("categoryDetail"));
			vo.setRE_idx(idx);
			String[] imgName = null; 										//이미지 카운트를 초기화 시킬 수단
			
//			=================== 수정한 업로드를 그대로 쓰냐 안쓰냐에 따른 업로드 방법이 나누어짐 =================
			if(imgNames.length()!=0) {										//이미지들이 null 이 아니면 정상적으로 수행
				System.out.println("no null");
				imgName=imgNames.split(",");
				imgCount = imgName.length;
				vo.setRE_img(imgCount);
				vo.setRE_imgNames(imgNames);	
				ReViewDao.getInstance().ReViewUpdate(mapper,vo);			//정보 업데이트(새로 추가or삭제 한 이미지 수정까지)
				mapper.commit();
			}else {															//null이면 sql에 imgNames는 건들지않고 넘어온 데이터만 update
				System.out.println("null");
				ReViewDao.getInstance().ReViewUpdateNoImg(mapper,vo);
				mapper.commit();
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
//		=================== 정보 수정이 끝나고 게시글상세보기로 가기전 뿌려줄 정보를 받아온다.=====================
		vo = ReViewDao.getInstance().selectByIdx(mapper,idx);	// update 완료한 정보를 다시 불러옴.
		request.setAttribute("currentPage", currentPage);		
		request.setAttribute("vo", vo);
		
		//다시 정보창에 뿌려줄 이미지 알고리즘 코딩
		String[] fileNameList = vo.getRE_imgNames().split(",");
		for (int i = 0; i < fileNameList.length; i++) {
			String fileName ="http://localhost:8009/korea/upload/";
			fileName+=fileNameList[i];
			request.setAttribute("fileName"+(i+1), fileName);
		}
		
		
		mapper.close();

	}// ReViewUpdate() 종료
}
