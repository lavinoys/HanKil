package com.hankil.app.mybatis.bean;

import java.io.Serializable;

public class FileVo implements Serializable{

	private static final long serialVersionUID = 8720806810274708122L;
	
	private int file_seq; 
	private String file_name; 
	private String file_url;
	private String file_type;
	private int startIdx;
	private int endIdx;
	private int totIdx;
	private String file_info;
	
	
	public int getFile_seq() {
		return file_seq;
	}
	public void setFile_seq(int file_seq) {
		this.file_seq = file_seq;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
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
	public String getFile_info() {
		return file_info;
	}
	public void setFile_info(String file_info) {
		this.file_info = file_info;
	}
	@Override
	public String toString() {
		return "FileVo [file_seq=" + file_seq + ", "
				+ (file_name != null ? "file_name=" + file_name + ", " : "")
				+ (file_url != null ? "file_url=" + file_url + ", " : "")
				+ (file_type != null ? "file_type=" + file_type + ", " : "")
				+ "startIdx=" + startIdx + ", endIdx=" + endIdx + ", totIdx="
				+ totIdx + ", "
				+ (file_info != null ? "file_info=" + file_info : "") + "]";
	}
}
