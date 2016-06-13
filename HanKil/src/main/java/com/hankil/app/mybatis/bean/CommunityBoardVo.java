package com.hankil.app.mybatis.bean;

import java.io.Serializable;

public class CommunityBoardVo implements Serializable{

	private static final long serialVersionUID = 8527799396265579319L;

	private int community_seq; 
	private int user_seq;
	private int file_seq;
	private int reply_seq;
	private String title; 
	private String content; 
	private int count; 
	private int type; 
	private int rank; 
	private String write_date; 
	private String update_date; 
	private String use_yn; 
	private String secret_yn; 
	private String reply_yn;
	private String user_id;
	private String user_name;
	private String passwd;
	private String name;
	private int startIdx;
	private int endIdx;
	private int totIdx;
	private int ref_seq;
	private int reply_depth;
	private int reply_num;
	
	public int getCommunity_seq() {
		return community_seq;
	}
	public void setCommunity_seq(int community_seq) {
		this.community_seq = community_seq;
	}
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	public int getFile_seq() {
		return file_seq;
	}
	public void setFile_seq(int file_seq) {
		this.file_seq = file_seq;
	}
	public int getReply_seq() {
		return reply_seq;
	}
	public void setReply_seq(int reply_seq) {
		this.reply_seq = reply_seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getSecret_yn() {
		return secret_yn;
	}
	public void setSecret_yn(String secret_yn) {
		this.secret_yn = secret_yn;
	}
	public String getReply_yn() {
		return reply_yn;
	}
	public void setReply_yn(String reply_yn) {
		this.reply_yn = reply_yn;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getRef_seq() {
		return ref_seq;
	}
	public void setRef_seq(int ref_seq) {
		this.ref_seq = ref_seq;
	}
	public int getReply_depth() {
		return reply_depth;
	}
	public void setReply_depth(int reply_depth) {
		this.reply_depth = reply_depth;
	}
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	@Override
	public String toString() {
		return "CommunityBoardVo [community_seq="
				+ community_seq
				+ ", user_seq="
				+ user_seq
				+ ", file_seq="
				+ file_seq
				+ ", reply_seq="
				+ reply_seq
				+ ", "
				+ (title != null ? "title=" + title + ", " : "")
				+ (content != null ? "content=" + content + ", " : "")
				+ "count="
				+ count
				+ ", type="
				+ type
				+ ", rank="
				+ rank
				+ ", "
				+ (write_date != null ? "write_date=" + write_date + ", " : "")
				+ (update_date != null ? "update_date=" + update_date + ", "
						: "")
				+ (use_yn != null ? "use_yn=" + use_yn + ", " : "")
				+ (secret_yn != null ? "secret_yn=" + secret_yn + ", " : "")
				+ (reply_yn != null ? "reply_yn=" + reply_yn + ", " : "")
				+ (user_id != null ? "user_id=" + user_id + ", " : "")
				+ (user_name != null ? "user_name=" + user_name + ", " : "")
				+ (passwd != null ? "passwd=" + passwd + ", " : "")
				+ (name != null ? "name=" + name + ", " : "") + "startIdx="
				+ startIdx + ", endIdx=" + endIdx + ", totIdx=" + totIdx
				+ ", ref_seq=" + ref_seq + ", reply_depth=" + reply_depth
				+ ", reply_num=" + reply_num + "]";
	}
}
