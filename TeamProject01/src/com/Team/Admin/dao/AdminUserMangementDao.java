package com.Team.Admin.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.Team.Admin.vo.AdminUserMangementVO;

public class AdminUserMangementDao {
	private static AdminUserMangementDao instance = new AdminUserMangementDao();
	private AdminUserMangementDao() {}
	public static AdminUserMangementDao getInstance() {	return instance;}
	
	public int userTotalCount(SqlSession mapper) {

		return (int) mapper.selectOne("userTotalCount");
	}
	public ArrayList<AdminUserMangementVO> AdminUserSelectList(SqlSession mapper, HashMap<String, Integer> hmap) {
		return (ArrayList<AdminUserMangementVO>) mapper.selectList("AdminUserSelectList",hmap);
	}
}
