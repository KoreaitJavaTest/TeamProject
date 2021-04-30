package com.Team.QAboard;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

public class QAboardDAO {

	private static QAboardDAO instance = new QAboardDAO();
	private QAboardDAO() { }
	public static QAboardDAO getInstance() { return instance; }
	
	public void insert(SqlSession mapper, QAboardVo vo) {
		mapper.insert("insert", vo);
	}
	
	public int QAselectCount(SqlSession mapper) {
		return (int) mapper.selectOne("QAselectCount");
	}
	
	public ArrayList<QAboardVo> QAselectList(SqlSession mapper, HashMap<String, Integer> hmap) {
		System.out.println("QAboardDAO 클래스의 QAselectList() 메소드");
		return (ArrayList<QAboardVo>) mapper.selectList("QAselectList", hmap);
	}
	
	public QAboardVo QAselectByIdx(SqlSession mapper, int q_idx) {
		System.out.println("QAboardDAO 클래스의 QAselectByIdx() 메소드");
		return (QAboardVo) mapper.selectOne("QAselectByIdx", q_idx);
	}
	
	public AnswerVO AnswerByIdx(SqlSession mapper, int q_idx) {
		System.out.println("QAboardDAO 클래스의 AnswerByIdx() 메소드");
		return (AnswerVO) mapper.selectOne("AnswerByIdx", q_idx);
	}
	
	public void delete(SqlSession mapper, int idx) {
		System.out.println("QAboardDAO 클래스의 delete() 메소드");
		mapper.delete("delete", idx);
	}
	
	public void update(SqlSession mapper, QAboardVo vo) {
		System.out.println("QAboardDAO 클래스의 update() 메소드");
		mapper.update("update", vo);
	}
	
	public void Aupdate(SqlSession mapper, AnswerVO avo) {
		System.out.println("QAboardDAO 클래스의 Aupdate() 메소드");
		mapper.update("Aupdate", avo);
		
	}
	
	public void incrementSeq(SqlSession mapper, HashMap<String, Integer> hmap) {
		System.out.println("QAboardDAO 클래스의 incrementSeq() 메소드");
		mapper.update("incrementSeq", hmap);
		
	}

	public void ansReply(SqlSession mapper, AnswerVO vo) {
		mapper.insert("ansReply", vo);
		
	}
	
	public ArrayList<AnswerVO> selectAlist(SqlSession mapper) {
		return (ArrayList<AnswerVO>) mapper.selectList("selectAlist");
	}


	

	

	

	
	
}
