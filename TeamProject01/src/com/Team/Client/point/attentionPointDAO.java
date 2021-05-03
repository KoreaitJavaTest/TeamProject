package com.Team.Client.point;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

public class attentionPointDAO {
	private static attentionPointDAO instance = new attentionPointDAO();
	private attentionPointDAO() {}
	public static attentionPointDAO getInstance() {	return instance;}
	
	//포인트 지급내역 (출석)
	public void insertPointLog(SqlSession mapper, attentionPointVO logVo) {
		mapper.insert("insertPointLog",logVo);
		
	}
	public ArrayList<attentionPointVO> SelectMyPointDeposit(SqlSession mapper, String userId) {
		return (ArrayList<attentionPointVO>) mapper.selectList("SelectMyPointDeposit" ,userId);
	}
	
	
}
