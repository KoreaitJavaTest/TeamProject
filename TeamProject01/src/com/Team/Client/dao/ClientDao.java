package com.Team.Client.dao;

import org.apache.ibatis.session.SqlSession;

import com.Team.Client.vo.ClientVo;

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
	

	
}
