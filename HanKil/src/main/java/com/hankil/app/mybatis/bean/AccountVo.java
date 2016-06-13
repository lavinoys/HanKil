package com.hankil.app.mybatis.bean;

import java.io.Serializable;

public class AccountVo implements Serializable{

	private static final long serialVersionUID = 4300195517315254715L;

	private int user_seq;
	private String user_id;
	private String user_passwd;
	private String user_name;
	private String user_rank;
	private String user_Email;
	private String user_phone;
	private String user_address;
	private String user_department;
	private String use_yn;
	private String connect_rank;
	private int startIdx;
	private int endIdx;
	private int totIdx;
	
	
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_passwd() {
		return user_passwd;
	}
	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_rank() {
		return user_rank;
	}
	public void setUser_rank(String user_rank) {
		this.user_rank = user_rank;
	}
	public String getUser_Email() {
		return user_Email;
	}
	public void setUser_Email(String user_Email) {
		this.user_Email = user_Email;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_department() {
		return user_department;
	}
	public void setUser_department(String user_department) {
		this.user_department = user_department;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getConnect_rank() {
		return connect_rank;
	}
	public void setConnect_rank(String connect_rank) {
		this.connect_rank = connect_rank;
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
		return "AccountVo [user_seq="
				+ user_seq
				+ ", "
				+ (user_id != null ? "user_id=" + user_id + ", " : "")
				+ (user_passwd != null ? "user_passwd=" + user_passwd + ", "
						: "")
				+ (user_name != null ? "user_name=" + user_name + ", " : "")
				+ (user_rank != null ? "user_rank=" + user_rank + ", " : "")
				+ (user_Email != null ? "user_Email=" + user_Email + ", " : "")
				+ (user_phone != null ? "user_phone=" + user_phone + ", " : "")
				+ (user_address != null ? "user_address=" + user_address + ", "
						: "")
				+ (user_department != null ? "user_department="
						+ user_department + ", " : "")
				+ (use_yn != null ? "use_yn=" + use_yn + ", " : "")
				+ (connect_rank != null ? "connect_rank=" + connect_rank + ", "
						: "") + "startIdx=" + startIdx + ", endIdx=" + endIdx
				+ ", totIdx=" + totIdx + "]";
	}
}
