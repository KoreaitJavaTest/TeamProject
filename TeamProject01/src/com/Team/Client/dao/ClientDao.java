package com.Team.Client.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.Team.Client.vo.ClientVo;
import com.Team.Review.vo.ReViewList;
import com.Team.Review.vo.ReViewVO;

public class ClientDao {
	private static ClientDao instance = new ClientDao();
	private ClientDao() {}
	public static ClientDao getInstance() {	return instance;}
	
	
	public int join(SqlSession mapper, ClientVo vo) {
		return mapper.insert("join",vo);
	}
	public String getClientEmail(SqlSession mapper, String client_id) {
		return (String) mapper.selectOne("getClientEmail",client_id);
	}
	public void emailCheckAction(SqlSession mapper, String code) {
		mapper.update("emailCheckAction",code);
	}
	public int idoverlapcheck(SqlSession mapper, String client_id) {
		return (int) mapper.selectOne("idoverlapcheck",client_id);
	}
	public ClientVo login(SqlSession mapper, ClientVo vo) {
		return (ClientVo) mapper.selectOne("login",vo);
	}
	public ClientVo ClientInfo(SqlSession mapper ,ClientVo vo) {
		return (ClientVo) mapper.selectOne("ClientInfo",vo);
	}
	public void ClientUpdate(SqlSession mapper, ClientVo vo) {
		mapper.update("ClientUpdate",vo);
	}
	public int reviewListCount(SqlSession mapper, String id) {
		return (int) mapper.selectOne("reviewListCount",id);
	}
	public ArrayList<ReViewVO> selectList(SqlSession mapper, ReViewList reViewList) {
		return (ArrayList<ReViewVO>) mapper.selectList("selectList",reViewList);
	}
	

	
}
