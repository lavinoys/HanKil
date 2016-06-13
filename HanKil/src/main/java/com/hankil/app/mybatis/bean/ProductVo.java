package com.hankil.app.mybatis.bean;

import java.io.Serializable;

public class ProductVo implements Serializable{

	private static final long serialVersionUID = 2086486520938327999L;

	private int product_seq;
	private int category_seq;
	private String category_name;
	private String product_name;
	private String product_info;
	private String use_yn;
	private int startIdx;
	private int endIdx;
	private int totIdx;
	
	
	public int getProduct_seq() {
		return product_seq;
	}
	public void setProduct_seq(int product_seq) {
		this.product_seq = product_seq;
	}
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
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_info() {
		return product_info;
	}
	public void setProduct_info(String product_info) {
		this.product_info = product_info;
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
		return "ProductVo [product_seq="
				+ product_seq
				+ ", category_seq="
				+ category_seq
				+ ", "
				+ (category_name != null ? "category_name=" + category_name
						+ ", " : "")
				+ (product_name != null ? "product_name=" + product_name + ", "
						: "")
				+ (product_info != null ? "product_info=" + product_info + ", "
						: "")
				+ (use_yn != null ? "use_yn=" + use_yn + ", " : "")
				+ "startIdx=" + startIdx + ", endIdx=" + endIdx + ", totIdx="
				+ totIdx + "]";
	}
}
