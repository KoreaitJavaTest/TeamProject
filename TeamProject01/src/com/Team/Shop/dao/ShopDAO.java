package com.Team.Shop.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.Team.Shop.vo.ShopVO;
import com.Team.mybatis.MySession;

public class ShopDAO {
	private static ShopDAO instance = new ShopDAO();
	private ShopDAO() {}
	public static ShopDAO getInstance() {return instance;}
	
	
//	상품 등록 메서드
	public void insertProduct(ShopVO vo) {
		SqlSession mapper = MySession.getSession();
		System.out.println(vo.getSh_idx());
		
		if(vo.getSh_img1() != null && vo.getSh_img2() != null) {
			mapper.insert("insertProduct", vo);
		} else if(vo.getSh_img1() != null && vo.getSh_img2() == null) {
			mapper.insert("insertProduct1", vo);
		} else if(vo.getSh_img1() == null && vo.getSh_img2() != null) {
			mapper.insert("insertProduct2", vo);
		}
		mapper.commit();
		mapper.close();
	}
	
	public int selectCount(SqlSession mapper) {
		return (int) mapper.selectOne("selectCount");
	}
	
//	전체 상품
	public ArrayList<ShopVO> selectList(SqlSession mapper, HashMap<String, Integer> hmap) {
		return (ArrayList<ShopVO>) mapper.selectList("selectList", hmap);
	}
	
//	나이키
	public ArrayList<ShopVO> selectnike(SqlSession mapper, HashMap<String, Object> hmap) {
		System.out.println("ShopDAO => 나이키");
		
		return (ArrayList<ShopVO>) mapper.selectList("selectnike", hmap);
	}
	
//	뉴발란스
	public ArrayList<ShopVO> selectNewbalance(SqlSession mapper, HashMap<String, Object> hmap) {
		return (ArrayList<ShopVO>) mapper.selectList("selectNewbalance", hmap);
	}
	
//	아디다스
	public ArrayList<ShopVO> selectAdidas(SqlSession mapper, HashMap<String, Object> hmap) {
		return (ArrayList<ShopVO>) mapper.selectList("selectAdidas", hmap);
	}
	
	
}
