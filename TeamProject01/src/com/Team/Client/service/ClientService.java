package com.Team.Client.service;

public class ClientService {
	private static ClientService instance = new ClientService();
	private ClientService() {}
	public static ClientService getInstance() {	return instance;}
	
	
}
