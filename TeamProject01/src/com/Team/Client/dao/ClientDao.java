package com.Team.Client.dao;

public class ClientDao {
	private static ClientDao instance = new ClientDao();
	private ClientDao() {}
	public static ClientDao getInstance() {	return instance;}
	
	
}
