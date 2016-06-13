package com.hankil.app.mybatis.bean;

import java.io.Serializable;

public class ProductCategoryVo implements Serializable{

	private static final long serialVersionUID = 2086486520938327999L;

	private int category_seq;
	private String category_name;
	private String category_info;
	private String use_yn;
	private int startIdx;
	private int endIdx;
	private int totIdx;
	
	
	public int getCategory_seq() {
		return category_seq;
	}
	public void setCategory_seq(int category_seq) {
		this.category_seq = category_seq;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_info() {
		return category_info;
	}
	public void setCategory_info(String category_info) {
		this.category_info = category_info;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public int getStartIdx() {
		return startIdx;
	}
	public void setStartIdx(int startIdx) {
		this.startIdx = startIdx;
	}
	public int getEndIdx() {
		return endIdx;
	}
	public void setEndIdx(int endIdx) {
		this.endIdx = endIdx;
	}
	public int getTotIdx() {
		return totIdx;
	}
	public void setTotIdx(int totIdx) {
		this.totIdx = totIdx;
	}
	@Override
	public String toString() {
		return "ProductCategoryVo [category_seq="
				+ category_seq
				+ ", "
				+ (category_name != null ? "category_name=" + category_name
						+ ", " : "")
				+ (category_info != null ? "category_info=" + category_info
						+ ", " : "")
				+ (use_yn != null ? "use_yn=" + use_yn + ", " : "")
				+ "startIdx=" + startIdx + ", endIdx=" + endIdx + ", totIdx="
				+ totIdx + "]";
	}
}
