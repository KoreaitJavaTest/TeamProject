package com.Team.Review.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.Team.Review.vo.ReViewList;
import com.Team.Review.vo.ReViewSearchVO;
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
	public void ReViewUpdateNoImg(SqlSession mapper, ReViewVO vo) {
		mapper.update("ReViewUpdateNoImg",vo);
		
	}
	public void ReViewDelete(SqlSession mapper, int idx) {
		mapper.delete("ReViewDelete",idx);
		
	}
	public ArrayList<ReViewVO> ReViewSearch(SqlSession mapper, ReViewSearchVO searchvo) {
		return (ArrayList<ReViewVO>) mapper.selectList("ReViewSearch",searchvo);
		
	}
	public int ReViewTotalCount(SqlSession mapper, ReViewSearchVO searchvo) {
		
		return (int) mapper.selectOne("ReViewTotalCount",searchvo);
	}
	public void CommentUp(SqlSession mapper, int refIdx) {
		mapper.update("CommentUp",refIdx);
		
	}
	public void minusCommentCount(SqlSession mapper, int idx) {
		mapper.update("minusCommentCount",idx);
		
	}
	public void likeUp(SqlSession mapper, int idx) {
		mapper.update("likeUp",idx);
		
	}
	public void likeDown(SqlSession mapper, int idx) {
		mapper.update("likeDown",idx);
		
	}
	public void checkUserUpdate(SqlSession mapper, ReViewVO vo) {
		mapper.update("checkUserUpdate",vo);
		
	}

	
	
}
