package com.Team.Review.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.Team.Review.dao.ReViewCommentDao;
import com.Team.Review.dao.ReViewDao;
import com.Team.Review.vo.ReViewCommentList;
import com.Team.Review.vo.ReViewList;
import com.Team.Review.vo.ReViewSearchVO;
import com.Team.Review.vo.ReViewVO;
import com.Team.mybatis.MySession;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReViewService {
	
	String fileAddr = "http://localhost:8009/korea/upload/";	// 여기서 포트만 수정하세요 or (server.xml 경로도 자기에맞게)
	
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
		int flag = 1 ; 	// insert : 1 
						// update : 2
						// delete : 3
		int imgCount=0;		//업로드한 이미지갯수
		
//		파일업로드 시작 ===============================
		try {
			MultipartRequest mr = new MultipartRequest(request,
//					application.getRealPath("./upload/"),
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
		request.setAttribute("flag", flag);
		request.setAttribute("currentPage", 1);
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
		int currentPage = 1;	//현재페이지
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			
		}
		int idx = Integer.parseInt(request.getParameter("idx"));	//선택한 게시글 글번호
		
		//선택한 게시글의 조회수를 올려준다.
		ReViewDao.getInstance().ReHitUp(mapper,idx);
		
		//선택한 게시글 정보를 가져와 담아준다.
		ReViewVO vo = ReViewDao.getInstance().selectByIdx(mapper,idx);
		
		String[] fileNameList = vo.getRE_imgNames().split(",");
		
		
		for (int i = 0; i < fileNameList.length; i++) {
			String fileName =fileAddr;
			fileName+=fileNameList[i];
			request.setAttribute("fileName"+(i+1), fileName);
//			System.out.println(fileName);
		}
//		------------------------------------------------------------------------------아래부터는 댓글작업------------------------------
		
		
		ReViewCommentList commentList = new ReViewCommentList();
		int pageSize = 6;		//한페이지에 표시할 글의 개수
		int totalCount= ReViewCommentDao.getInstance().CommentTotalCount(mapper,idx);
//		int totalCount = ReViewDao.getInstance().CommentTotalCount(mapper, idx);
		System.out.println("totalCount:"+totalCount);
		ReViewList list = new ReViewList(pageSize, totalCount, currentPage);	//댓글 페이징
		System.out.println(list);
		HashMap<String, Integer> hmap = new HashMap<>();
		hmap.put("startNo", list.getStartNo());
		hmap.put("endNo", list.getEndNo());
		hmap.put("idx",idx);													//해당게시글의 글번호 (댓글에서 찾기 위함)
		
		//startNo,endNo만큼의 idx인 댓글을 읽고 저장해야함
		commentList.setList(ReViewCommentDao.getInstance().selectList(mapper, hmap));
		System.out.println(commentList.getList());
		//현재 commentList에 array에 해당 게시글의 댓글 리스트가 저장된 상태

		//디테일 페이지에 넘겨주기 위해 request영역에 저장
		request.setAttribute("vo", vo);
		System.out.println("vo(글) : "+vo);
		System.out.println("댓글 list : "+commentList.getList());
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("commentList", commentList);

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
		int currentPage=1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}catch (Exception e) {

		}
		
		ReViewVO vo = ReViewDao.getInstance().selectByIdx(mapper,idx);	// 1건의 정보를 vo 객체에 저장	
		
		String[] fileNameList = vo.getRE_imgNames().split(",");
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < fileNameList.length; i++) {
			String fileName =fileAddr;
			fileName+=fileNameList[i];
			request.setAttribute("fileName"+(i+1), fileName);
			if(i!=fileNameList.length-1) {
			buffer.append(fileNameList[i]+","); 	// 파일1,파일2,파일3
			}else {									// 파일,
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
			
			String fileName1=mr.getParameter("fileName1");			//선택한 파일없음 일때 들어옴
			String fileName2=mr.getParameter("fileName2");
			String fileName3=mr.getParameter("fileName3");
//			http://localhost:8009/korea/upload/iconfinder_arrow_left_13150912.png
			String local =fileAddr;						// TeamProject시 맞춰야할것

			//파일명 가져왓음!
//			현재  fileName2 만 mr로들어옴 => 1,3은 빈공간상태(hidden으로 받아온쌍태)
//			System.out.println(fileName1);	// 원하는 위치를 바꾸면 null
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
			if(fileName1!=null && fileName2!=null &&fileName3!=null) {
					fileName1=fileName1.replaceAll(local, "");
					imgNames+=fileName1;
					fileName2=fileName2.replaceAll(local, ",");
					imgNames+=fileName2;
					fileName3=fileName3.replaceAll(local, ",");
					imgNames+=fileName3;
			}else if(fileName1!=null || fileName2!=null ||fileName3!=null) {
				if(fileName1!=null) {
					fileName1=fileName1.replaceAll(local, "");
					imgNames+=fileName1;
				}
				if(fileName2!=null) {
					fileName2=fileName2.replaceAll(local, ",");
					imgNames+=fileName2;
				}
				if(fileName3!=null) {
					fileName3=fileName3.replaceAll(local, ",");
					imgNames+=fileName3;
				}
			}
			System.out.println(imgNames);
			//현재 : imgNames = (2번) + ,1번 + ,3번
//			======================vo 저장========================
			vo.setRE_title(mr.getParameter("title"));
			vo.setRE_content(mr.getParameter("content"));
			vo.setRE_categoryDetail(mr.getParameter("categoryDetail"));
			vo.setRE_idx(idx);
			String[] imgName = null; 										//이미지 카운트를 초기화 시킬 수단
//			======================vo 저장========================


		    imgName=imgNames.split(",");
		    imgCount = imgName.length;
		    vo.setRE_img(imgCount);
		    vo.setRE_imgNames(imgNames);	
		    ReViewDao.getInstance().ReViewUpdate(mapper,vo);			//정보 업데이트(새로 추가or삭제 한 이미지 수정까지)
		    mapper.commit();
	
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
			String fileName =fileAddr;
			fileName+=fileNameList[i];
			request.setAttribute("fileName"+(i+1), fileName);
		}
		
		
		mapper.close();

	}// ReViewUpdate() 종료
	
	//리뷰 게시글 (idx,currnetPage) 를 넘겨받고 삭제하는 메소드를 호출하는 메소드
	public void ReViewDelete(HttpServletRequest request, HttpServletResponse response) {
		SqlSession mapper = MySession.getSession();
		int idx = Integer.parseInt(request.getParameter("idx"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		int flag = 3 ; 	// insert : 1 
		                // update : 2
		                // delete : 3
		
		ReViewDao.getInstance().ReViewDelete(mapper,idx);		//삭제
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("flag", flag);
		mapper.commit();
		mapper.close();
	}
	public void ReViewSearch(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ReViewSearch");
		SqlSession mapper = MySession.getSession();
		String searchName = request.getParameter("searchName");
		String searchText = request.getParameter("searchText");
		System.out.println(searchName);
		ReViewSearchVO searchvo = new ReViewSearchVO(searchName, searchText);
		int currentPage = 1;	//현재페이지

		int pageSize = 6;		//한페이지에 표시할 글의 개수
		int totalCount= ReViewDao.getInstance().ReViewTotalCount(mapper,searchvo);		//검색타입과 검색 내용으로 검색한 리뷰글의 총 개수
		System.out.println("TOTAL:"+totalCount);
		
		
		ReViewList list = new ReViewList(pageSize, totalCount, currentPage);
		System.out.println(list);
		searchvo.setStartNo(list.getStartNo());
		searchvo.setEndNo(list.getEndNo());
		
		
		list.setList(ReViewDao.getInstance().ReViewSearch(mapper, searchvo));
		System.out.println(list.getList());
		request.setAttribute("ReViewList", list);

//		System.out.println("여기/");		
		
		mapper.close();
		
	}
	public void ReViewDetailSelect(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ReViewDetailSelect()");
		SqlSession mapper = MySession.getSession();
		int currentPage = 1;	//현재페이지
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			
		}
		int idx = Integer.parseInt(request.getParameter("idx"));	//선택한 게시글 글번호
		
		//선택한 게시글 정보를 가져와 담아준다.
		ReViewVO vo = ReViewDao.getInstance().selectByIdx(mapper,idx);
		
		String[] fileNameList = vo.getRE_imgNames().split(",");
		
		
		for (int i = 0; i < fileNameList.length; i++) {
			String fileName =fileAddr;
			fileName+=fileNameList[i];
			request.setAttribute("fileName"+(i+1), fileName);
			System.out.println(fileName);
		}
		System.out.println("반복문 정상종료");
//		------------------------------------------------------------------------------아래부터는 댓글작업------------------------------
		
		
		ReViewCommentList commentList = new ReViewCommentList();
		int pageSize = 6;		//한페이지에 표시할 글의 개수
		int totalCount= ReViewCommentDao.getInstance().CommentTotalCount(mapper,idx);
//		int totalCount = ReViewDao.getInstance().CommentTotalCount(mapper, idx);
		System.out.println("totalCount:"+totalCount);
		ReViewList list = new ReViewList(pageSize, totalCount, currentPage);	//댓글 페이징
//		System.out.println(list);
		HashMap<String, Integer> hmap = new HashMap<>();
		hmap.put("startNo", list.getStartNo());
		hmap.put("endNo", list.getEndNo());
		hmap.put("idx",idx);													//해당게시글의 글번호 (댓글에서 찾기 위함)
		
		//startNo,endNo만큼의 idx인 댓글을 읽고 저장해야함
		commentList.setList(ReViewCommentDao.getInstance().selectList(mapper, hmap));
		System.out.println(commentList.getList());
		//현재 commentList에 array에 해당 게시글의 댓글 리스트가 저장된 상태
		
		
		
		

		//디테일 페이지에 넘겨주기 위해 request영역에 저장
		request.setAttribute("vo", vo);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("commentList", commentList);

		
		mapper.commit();
		mapper.close();
		
	}

		
		
		
}
