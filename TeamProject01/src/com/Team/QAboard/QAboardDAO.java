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
	
	public int selectCount(SqlSession mapper) {
		return (int) mapper.selectOne("selectCountQA");
	}
	
	public ArrayList<QAboardVo> selectList(SqlSession mapper, HashMap<String, Integer> hmap) {
		System.out.println("QAboardDAO 클래스의 selectList() 메소드");
		return (ArrayList<QAboardVo>) mapper.selectList("selectListQA", hmap);
	}

	
	
}
