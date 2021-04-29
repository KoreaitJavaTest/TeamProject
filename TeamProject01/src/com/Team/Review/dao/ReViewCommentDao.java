package com.Team.Review.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.Team.Review.vo.ReViewCommentVO;

public class ReViewCommentDao {
	private static ReViewCommentDao instance = new ReViewCommentDao();
	private ReViewCommentDao() {}
	public static ReViewCommentDao getInstance() {	return instance;}
	
	
	public void insertComment(SqlSession mapper, ReViewCommentVO vo) {
		mapper.insert("insertComment",vo);
		
	}
	
	public ArrayList<ReViewCommentVO> selectList(SqlSession mapper, HashMap<String, Integer> hmap) {
		return (ArrayList<ReViewCommentVO>) mapper.selectList("SelectComment",hmap);
	}
	public int CommentTotalCount(SqlSession mapper, int idx) {
		return (int) mapper.selectOne("CommentTotalCount",idx);
	}
	public void updateComment(SqlSession mapper, HashMap<String, Object> hmap) {
		mapper.update("updateComment",hmap);
		
	}
	public void deleteComment(SqlSession mapper, int commentIdx) {
		mapper.delete("deleteComment",commentIdx);
		
	}
	
	
}
