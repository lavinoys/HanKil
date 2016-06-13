package com.hankil.app.common.upload.http;
public abstract class Part {
	private String name;
	
	Part(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	} 
	
	public boolean isFile() {
		return false;
	}
	
	public boolean isParam() {
		return false;
	}
}
