package com.Team.Shop.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.ibatis.session.SqlSession;

import com.Team.Shop.dao.ShopDAO;
import com.Team.Shop.vo.CategoryDetail;
import com.Team.Shop.vo.ShopList;
import com.Team.Shop.vo.ShopVO;
import com.Team.mybatis.MySession;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ShopService {
	
	private static ShopService instance = new ShopService();
	private ShopService() {}
	public static ShopService getInstance() {return instance;}
	DecimalFormat priceFm = new DecimalFormat("#,##0");
	
	
	
	public void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		SqlSession mapper = MySession.getSession();
		HttpSession session = request.getSession();
		ServletContext application = request.getServletContext();
		
		ShopDAO dao = ShopDAO.getInstance();
		ShopVO vo = new ShopVO();
		
		String userId = (String) session.getAttribute("session_id");
		
		MultipartRequest mr = new MultipartRequest(
				request,
//				application.getRealPath("./upload/"),
				"D:/upload/",
				5 * 1024 * 1024,
				"UTF-8",
				new DefaultFileRenamePolicy()
			);
			
//			업로드할 파일이름 여러개를 받는다.
			Enumeration<String> fileNames = mr.getFileNames();
//			hasMoreElements() : Enumeration 인터페이스 객체에 다음에 읽어들일 데이터가 있으면 true, 없으면 false를 리턴시킨다.

			int imgCount = 0;
			
//			이미지파일 이름 넣는 반복문
			while (fileNames.hasMoreElements()) {
				imgCount++;
				String parameter = (String) fileNames.nextElement();
				String fileName = mr.getOriginalFileName(parameter);
				String fileRealName = mr.getFilesystemName(parameter);
				
				if (fileName == null) {
					continue;
				}
				
				String imgNames = "http://localhost:8009/korea/upload/" + fileName;	// img 파일 이름
				
				if(imgCount == 1) {
					vo.setSh_img1(imgNames);
				} else if(imgCount == 2) {
					vo.setSh_img2(imgNames);
				} else if(imgCount == 3) {
					vo.setSh_img3(imgNames);
				}
				
			}
			
			String title = mr.getParameter("title");
			String name = mr.getParameter("name");
			String category = mr.getParameter("category");
			String categoryDetail = mr.getParameter("categoryDetail");
			String content = mr.getParameter("content");
//			int size = Integer.parseInt(mr.getParameter("size"));
			int price = Integer.parseInt(mr.getParameter("price"));
			double salePercent = Double.parseDouble(mr.getParameter("salePercent"));
			double salePrice = price - (price*(salePercent/100));
			
			vo.setSh_title(title);
			vo.setSh_name(name);
			vo.setSh_category(category);
			vo.setSh_categoryDetail(categoryDetail);
			vo.setSh_content(content);
//			vo.setSh_size(size);
			vo.setSh_price(price);
			vo.setSh_salePercent(salePercent);
			vo.setSh_salePrice(salePrice);
			vo.setSh_seller(userId);
			
			dao.insertProduct(mapper, vo);
			
			mapper.commit();
			mapper.close();
			
	}
	
	public void selectAllProduct(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("service => selectAllProduct() 메서드 들어옴");
		DecimalFormat priceFm = new DecimalFormat("#,##0");
		ShopDAO dao = ShopDAO.getInstance();
		CategoryDetail cd = new CategoryDetail();
		SqlSession mapper = MySession.getSession();
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) {
		}
		
		int pageSize = 24;
		int totalCount = dao.selectCount(mapper);
		
		ShopList shopList = new ShopList(pageSize, totalCount, currentPage);
		
		String category = request.getParameter("category");	// 이전 페이지에서 카테고리를 가져온다. ex) 신발, 상의, 하의
		System.out.println("category : " + category);
		cd.Category(category, shopList.getStartNo(), shopList.getEndNo());
		
		shopList.setList(dao.selectList(mapper, cd));
		
//		가격에 , 찍기
		for (ShopVO vo : shopList.getList()) {
			vo.setSh_priceFM(priceFm.format(vo.getSh_price()));
			vo.setSh_salePriceFM(priceFm.format(vo.getSh_salePrice()));
		}
		
		request.setAttribute("shopList", shopList);
		
		mapper.close();
	}
	
	
	public void selectCategoryDetail(HttpServletRequest request, HttpServletResponse response) {
		
		SqlSession mapper = MySession.getSession();
		ShopDAO dao = ShopDAO.getInstance();
		int currentPage = 1;
		try { currentPage = Integer.parseInt(request.getParameter("currentPage")); } catch (NumberFormatException e) {}
		
		int pageSize = 24;
		
		String categoryDetail = request.getParameter("categoryDetail");
		String category = request.getParameter("category");
		
		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("categoryDetail", categoryDetail);
		
		int totalCount = dao.selectCountDetail(mapper, hmap);
		
		ShopList shopList = new ShopList(pageSize, totalCount, currentPage, categoryDetail);
		CategoryDetail cd = new CategoryDetail(categoryDetail, category, shopList.getStartNo(), shopList.getEndNo());
		shopList.setList(dao.selectCategoryDetail(mapper, cd));
		
//		가격에 , 찍기
		for (ShopVO vo : shopList.getList()) {
			vo.setSh_priceFM(priceFm.format(vo.getSh_price()));			// 정상 가격
			vo.setSh_salePriceFM(priceFm.format(vo.getSh_salePrice()));	// 할인된 가격
		}
		request.setAttribute("shopList", shopList);
		 
		mapper.close();

	}
	
	
//	상품의 idx로 상품 정보를 얻어오는 메소드
	public void selectProduct(HttpServletRequest request, HttpServletResponse response) {
		
		SqlSession mapper = MySession.getSession();
		ShopDAO dao = ShopDAO.getInstance();
		
		int currentPage = 1;
		try { currentPage = Integer.parseInt(request.getParameter("currentPage")); } catch (NumberFormatException e) { }
		
		ShopVO vo = dao.selectProduct(mapper, Integer.parseInt(request.getParameter("sh_idx")));
		
		vo.setSh_priceFM(priceFm.format(vo.getSh_price()));
		vo.setSh_salePriceFM(priceFm.format(vo.getSh_salePrice()));
		
		request.setAttribute("vo", vo);
		System.out.println("vo : " + vo);
		mapper.close();
	}
	
	
//	조회수 증가 메소드
	public void increment(HttpServletRequest request, HttpServletResponse response) {
		SqlSession mapper = MySession.getSession();
		ShopDAO dao = ShopDAO.getInstance();
//		조회수를 증가시킬 글 번호를 받는다.
		int sh_idx = Integer.parseInt(request.getParameter("sh_idx"));
		
//		조회수를 증가시키는 메소드를 호출한다.
		dao.increment(mapper, sh_idx);
		
		mapper.commit();
		mapper.close();
	}
	
//	상품 삭제 메소드
	public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		SqlSession mapper = MySession.getSession();
		ShopDAO dao = ShopDAO.getInstance();
		System.out.println("deleteProduct 들어옴");
		
//		System.out.println(request.getParameter("sh_password"));
//		System.out.println(request.getParameter("sh_idx"));
		
		HttpSession session = request.getSession();
		String userPw = session.getAttribute("session_password") +"";		//	로그인시 사용되는 비밀번호
		String inputPw = request.getParameter("sh_password");				//	삭제페이지에서 입력한 비밀번호
		int sh_idx = Integer.parseInt(request.getParameter("sh_idx"));		// 	삭제할 비밀번호
		
		PrintWriter script = response.getWriter();
		if(userPw.trim().equals(inputPw.trim())) {
			dao.deleteProduct(mapper, sh_idx);
			script.println("<script>");
			script.println("alert('삭제되었습니다.');");
			script.println("location.href = 'index.jsp';");
			script.println("</script>");
			script.close();
			mapper.commit();
			mapper.close();
		} else {
			script.println("<script>");
			script.println("alert('비밀번호가 맞지 않습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
	}
}





















