package com.Team.Shop.fileupload;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FileDAO2 {
	
	private FileDAO2 instance = new FileDAO2();
	private FileDAO2() {}
	public FileDAO2 getInstance() {return instance;}
	
	
//	파일이 업로드 될 때 마다 업로드하는 파일 이름과 실제로 저장되는 파일 이름을 테이블에 저장하는 메소드
	public int upload(String fileName, String fileRealName) {
		System.out.println("FileDAO 클래스의 upload() 메소드");
		String sql = "insert into fileupload (idx, filename, filerealname) values (fileupload_idx_seq.nextval, ?, ?)";
		return 0;
	}
	
//	테이블에 저장된 업로드된 전체 파일 목록을 얻어오는 메소드
	public ArrayList<FileVO> getList() {
		System.out.println("FileDAO 클래스의 getList() 메소드");
		ArrayList<FileVO> list = new ArrayList<FileVO>();
		String sql = "select * from fileupload";
		return list;
			
	}

}








