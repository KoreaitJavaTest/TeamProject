package com.Team.Review.vo;

import java.util.ArrayList;

public class ReViewCommentList {
	ArrayList<ReViewCommentVO> list ;
	
	public ReViewCommentList() {
		// TODO Auto-generated constructor stub
	}
	

	public ReViewCommentList(ArrayList<ReViewCommentVO> list) {
		super();
		this.list = list;
	}

	public ArrayList<ReViewCommentVO> getList() {
		return list;
	}

	public void setList(ArrayList<ReViewCommentVO> list) {
		this.list = list;
	}
	
	
}
