package com.Team.Client.point;

import java.util.Date;

public class attentionPointVO {
	private int idx;
	private String userId;
	private int point;
	private Date depositDate;
	private String content;
	
	
	public attentionPointVO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public attentionPointVO(String userId, int point, String content) {
		super();
		this.userId = userId;
		this.point = point;
		this.content = content;
	}


	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Date getDepositDate() {
		return depositDate;
	}
	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "attentionPointVO [idx=" + idx + ", userId=" + userId + ", point=" + point + ", depositDate="
				+ depositDate + ", content=" + content + "]";
	}
	
	
}
