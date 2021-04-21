package com.Team.Review.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.Team.Review.vo.ReViewVO;

public class ReViewDao {
	private static ReViewDao instance = new ReViewDao();
	private ReViewDao() {}
	public static ReViewDao getInstance() {	return instance;}
	public void ReViewInsert(SqlSession mapper, ReViewVO vo) {
		mapper.insert("ReViewInsert",vo);
		
	}
	public int totalCount(SqlSession mapper) {
		return (int) mapper.selectOne("totalCount");
	}
	public ArrayList<ReViewVO> selectReViewList(SqlSession mapper, HashMap<String, Integer> hmap) {
		return (ArrayList<ReViewVO>) mapper.selectList("selectReViewList",hmap);
	}
	public void ReHitUp(SqlSession mapper, int idx) {
		mapper.update("ReHitUp",idx);
		
	}
	public ReViewVO selectByIdx(SqlSession mapper, int idx) {
		return (ReViewVO) mapper.selectOne("selectByIdx",idx);
		
	}
	public void ReViewReport(SqlSession mapper, int idx) {
		mapper.update("ReViewReport",idx);
		
	}
	public void ReViewUpdate(SqlSession mapper, ReViewVO vo) {
		mapper.update("ReViewUpdate",vo);
		
	}
	
	
}
