package com.Team.Shop.fileupload;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FileDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
//	기본 생성자에서 Connection을 초기화시킨다.
	public FileDAO() { 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "team", "0000");
			System.out.println("연결 성공 : " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	파일이 업로드 될 때 마다 업로드하는 파일 이름과 실제로 저장되는 파일 이름을 테이블에 저장하는 메소드
	public int upload(String fileName, String fileRealName) {
		System.out.println("FileDAO 클래스의 upload() 메소드");
		try {
			String sql = "insert into fileupload (idx, filename, filerealname) values (fileupload_idx_seq.nextval, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fileName);
			pstmt.setString(2, fileRealName);
//			executeUpdate() 메소드는 정상적으로 실행된 sql 명령의 개수를 리턴한다.
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		오류가 발생되면 -1을 리턴시킨다.
		return -1;
	}
	
//	테이블에 저장된 업로드된 전체 파일 목록을 얻어오는 메소드
	public ArrayList<FileVO> getList() {
		System.out.println("FileDAO 클래스의 getList() 메소드");
		ArrayList<FileVO> list = new ArrayList<FileVO>();
		try {
			String sql = "select * from fileupload";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FileVO vo = new FileVO(rs.getString("fileName"), rs.getString("fileRealName"), rs.getInt("downloadCount"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}








