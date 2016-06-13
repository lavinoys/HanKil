package com.hankil.app.mybatis.bean;

public class ResultVo <E>{
	
	String resultCode;
	String errMsg;
	String url;
	E value;
	
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public E getValue() {
		return value;
	}
	public void setValue(E value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Result ["
				+ (resultCode != null ? "resultCode=" + resultCode + ", " : "")
				+ (errMsg != null ? "errMsg=" + errMsg + ", " : "")
				+ (url != null ? "url=" + url + ", " : "")
				+ (value != null ? "value=" + value : "") + "]";
	}
}
