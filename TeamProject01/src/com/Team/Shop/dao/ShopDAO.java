package com.Team.Shop.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.Team.Shop.vo.CategoryDetail;
import com.Team.Shop.vo.ShopList;
import com.Team.Shop.vo.ShopVO;
import com.Team.mybatis.MySession;

public class ShopDAO {
	private static ShopDAO instance = new ShopDAO();
	private ShopDAO() {}
	public static ShopDAO getInstance() {return instance;}
	
	
//	상품 등록 메서드
	public void insertProduct(SqlSession mapper, ShopVO vo) {
		System.out.println("dao => insertProduct() 메서드 들어옴");
		System.out.println(vo);
		if(vo.getSh_img1() != null && vo.getSh_img2() != null) {
			mapper.insert("insertProduct", vo);
		} else if(vo.getSh_img1() != null && vo.getSh_img2() == null) {
			mapper.insert("insertProduct1", vo);
		} else if(vo.getSh_img1() == null && vo.getSh_img2() != null) {
			mapper.insert("insertProduct2", vo);
		}
	}
	
//	전체상품 카운트
	public int selectCount(SqlSession mapper) {
		return (int) mapper.selectOne("selectCount");
	}
	
//	브랜드별 카운트
	public int selectCountDetail(SqlSession mapper, HashMap<String, String> hmap) {
		return (int) mapper.selectOne("selectCountDetail", hmap);
	}
	
//	전체 상품
	public ArrayList<ShopVO> selectList(SqlSession mapper, CategoryDetail cd) {
		System.out.println("dao => selectList() 메서드 들어옴");
		return (ArrayList<ShopVO>) mapper.selectList("selectList", cd);
	}
	
//	브랜드별 상품
	public ArrayList<ShopVO> selectCategoryDetail(SqlSession mapper, CategoryDetail categoryDetail) {
//		System.out.println("ShopDAO => selectCategoryDetail");
		return (ArrayList<ShopVO>) mapper.selectList("selectCategoryDetail", categoryDetail);
	}
	
//	상품 상세 보기
	public ShopVO selectProduct(SqlSession mapper, int sh_idx) {
		
		return (ShopVO) mapper.selectOne("selectProduct", sh_idx);
	}
	
//	조회수 증가
	public void increment(SqlSession mapper, int sh_idx) {
		mapper.update("increment", sh_idx);
	}
	
//	상품 삭제
	public void deleteProduct(SqlSession mapper, int sh_idx) {
		mapper.delete("deleteProduct", sh_idx);
	}
	
//	상품 수정
	public void updateProduct(SqlSession mapper, ShopVO vo) {
		if(vo.getSh_img1() != null && vo.getSh_img2() != null) {
			System.out.println("updateProduct");
			mapper.update("updateProduct", vo);
		} else if(vo.getSh_img1() != null && vo.getSh_img2() == null) {
			System.out.println("updateProduct1");
			mapper.update("updateProduct1", vo);
		} else if(vo.getSh_img1() == null && vo.getSh_img2() != null) {
			System.out.println("updateProduct2");
			mapper.update("updateProduct2", vo);
		}
	}
	
/*
	====================== 수정 ing.... ========================================
	public int likeCheck(SqlSession mapper, Map<String, Object> map) {
		return (int) mapper.selectOne("likeCheck", map);
	}
	
	public void likeUpdate(SqlSession mapper, Map<String, Object> map) {
		mapper.insert("likeUpdate", map);
	}
	
	public void likeDelete(SqlSession mapper, Map<String, Object> map) {
		mapper.delete("likeDelete", map);
	}
	
	public void likeCount(SqlSession mapper, int like_idx) {
		mapper.selectOne("likeCount", like_idx);
	}
	==========================================================================	
*/
}
