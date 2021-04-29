package com.Team.Shop.service;

import java.io.File;
import java.io.IOException;
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
import com.Team.Shop.vo.ShopList;
import com.Team.Shop.vo.ShopVO;
import com.Team.mybatis.MySession;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ShopService {
	
	private static ShopService instance = new ShopService();
	private ShopService() {}
	public static ShopService getInstance() {return instance;}
	
	public void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("insertProduct() 메서드");
		SqlSession mapper = MySession.getSession();
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("session_id");
		ShopVO vo = new ShopVO();
		ShopDAO dao = ShopDAO.getInstance();
		
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
			System.out.println("price : " + price);
			System.out.println("salePercent : " + salePercent);
			System.out.println("salePrice : " + salePrice);
			
			vo.setSh_title(title);
			vo.setSh_name(name);
			vo.setSh_category(category);
			vo.setSh_categoryDetail(categoryDetail);
			vo.setSh_content(content);
//			vo.setSh_size(size);
			vo.setSh_price(price);
			vo.setSh_seller(userId);
			vo.setSh_salePercent(salePercent);
			vo.setSh_salePrice(salePrice);
			
			dao.insertProduct(vo);
			
//			request.setAttribute("idx", idx);
//			request.setAttribute("title", title);
//			request.setAttribute("name", name);
//			request.setAttribute("category", category);
//			request.setAttribute("categoryDetail", categoryDetail);
//			request.setAttribute("content", content);
//			request.setAttribute("size", size);
//			request.setAttribute("price", price);
//			
//			if(vo.getSh_img1() != null) {
//				request.setAttribute("img1", vo.getSh_img1());
//			}
//			if(vo.getSh_img2() != null) {
//				request.setAttribute("img2", vo.getSh_img2());
//			}
//			if(vo.getSh_img3() != null) {
//				request.setAttribute("img3", vo.getSh_img3());
//			}
			
			mapper.commit();
			mapper.close();
			
	}
	
	public void selectAllProduct(HttpServletRequest request, HttpServletResponse response) {
		DecimalFormat priceFm = new DecimalFormat("#,##0");
		
		SqlSession mapper = MySession.getSession();
		ShopDAO dao = ShopDAO.getInstance();
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException");
		}
		
		int pageSize = 6;
		int totalCount = dao.selectCount(mapper);
//		System.out.println(totalCount);
		
		ShopList shopList = new ShopList(pageSize, totalCount, currentPage);
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", shopList.getStartNo());
		hmap.put("endNo", shopList.getEndNo());
		shopList.setList(dao.selectList(mapper, hmap));
		
//		가격에 , 찍기
		for (ShopVO vo : shopList.getList()) {
			vo.setSh_priceFM(priceFm.format(vo.getSh_price()));
			vo.setSh_salePriceFM(priceFm.format(vo.getSh_salePrice()));
		}
		
		request.setAttribute("shopList", shopList);
		
		mapper.close();
	}
	
	
	public void selectNike(HttpServletRequest request, HttpServletResponse response) {
		DecimalFormat priceFm = new DecimalFormat("#,##0");
		SqlSession mapper = MySession.getSession();
		ShopDAO dao = ShopDAO.getInstance();
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException");
		}
		
		int pageSize = 24;
		int totalCount = dao.selectCount(mapper);
//		System.out.println(totalCount);
		
		ShopList shopList = new ShopList(pageSize, totalCount, currentPage);
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("startNo", shopList.getStartNo());
		hmap.put("endNo", shopList.getEndNo());
//		hmap.put("brand", request.getParameter("brand"));		// brand별로 분류 하려고 했으나 실패
		shopList.setList(dao.selectnike(mapper, hmap));
		
//		가격에 , 찍기
		for (ShopVO vo : shopList.getList()) {
			vo.setSh_priceFM(priceFm.format(vo.getSh_price()));			// 정상 가격
			vo.setSh_salePriceFM(priceFm.format(vo.getSh_salePrice()));	// 할인된 가격
		}
		
		request.setAttribute("shopList", shopList);
		 
		mapper.close();
	}
	
	
	public void selectAdidas(HttpServletRequest request, HttpServletResponse response) {
		DecimalFormat priceFm = new DecimalFormat("#,##0");
		SqlSession mapper = MySession.getSession();
		ShopDAO dao = ShopDAO.getInstance();
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException");
		}
		
		int pageSize = 24;
		int totalCount = dao.selectCount(mapper);
//		System.out.println(totalCount);
		
		ShopList shopList = new ShopList(pageSize, totalCount, currentPage);
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("startNo", shopList.getStartNo());
		hmap.put("endNo", shopList.getEndNo());
//		hmap.put("brand", request.getParameter("brand"));
		
		shopList.setList(dao.selectAdidas(mapper, hmap));
//		가격에 , 찍기
		for (ShopVO vo : shopList.getList()) {
			vo.setSh_priceFM(priceFm.format(vo.getSh_price()));
			
			vo.setSh_salePriceFM(priceFm.format(vo.getSh_salePrice()));
		}
		
		
		request.setAttribute("shopList", shopList);
		
		mapper.close();
	}
	
	
	public void selectNewbalance(HttpServletRequest request, HttpServletResponse response) {
		DecimalFormat priceFm = new DecimalFormat("#,##0");
		SqlSession mapper = MySession.getSession();
		ShopDAO dao = ShopDAO.getInstance();
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException");
		}
		
		int pageSize = 24;
		int totalCount = dao.selectCount(mapper);
//		System.out.println(totalCount);
		
		ShopList shopList = new ShopList(pageSize, totalCount, currentPage);
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("startNo", shopList.getStartNo());
		hmap.put("endNo", shopList.getEndNo());
//		hmap.put("brand", request.getParameter("brand"));
		
		shopList.setList(dao.selectNewbalance(mapper, hmap));
//		가격에 , 찍기
		for (ShopVO vo : shopList.getList()) {
			vo.setSh_priceFM(priceFm.format(vo.getSh_price()));
			
			vo.setSh_salePriceFM(priceFm.format(vo.getSh_salePrice()));
		}
		
		
		request.setAttribute("shopList", shopList);
		
		mapper.close();
	}
	
}





















