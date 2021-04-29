package com.Team.Shop.vo;

public class CategoryDetail {

	
	private String category;
	private String categoryDetail;
	private int startNo;
	private int endNo;
	
	public CategoryDetail() {
	}
	public CategoryDetail(String categoryDetail, String category, int startNo, int endNo) {
		super();
		this.categoryDetail = categoryDetail;
		this.category = category;
		this.startNo = startNo;
		this.endNo = endNo;
	}

	public void Category(String category, int startNo, int endNo) {
		this.category = category;
		this.startNo = startNo;
		this.endNo = endNo;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public String getCategoryDetail() {
		return categoryDetail;
	}

	public void setCategoryDetail(String categoryDetail1) {
		this.categoryDetail = categoryDetail1;
	}

	
}
