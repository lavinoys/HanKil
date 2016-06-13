package com.hankil.app.common;

public class Config {

	/*
	 * result code setting
	 * */
	public static final String failedCode = "0";
	public static final String successCode = "1";
	public static final String errorCode = "2";
	public static final String errorIdCode = "3";
	public static final String errorPasswdCode = "4";
	
	/*
	 * board type setting
	 * */
	public static final int board_FAQ_Code = 1;
	public static final int board_QNA_Code = 2;
	public static final int board_data_Code = 3;
	public static final int board_news_Code = 4;
	public static final int board_events_Code = 5;
	public static final int board_community_Code = 6;
	
	/*
	 * board ranking setting
	 * */
	public static final int board_1stCode = 1;
	public static final int board_2stCode = 2;
	public static final int board_3stCode = 3;
	public static final int board_4stCode = 4;
	public static final int board_5stCode = 5;
	
	/*
	 * admin session key setting
	 * */
	public static final String adminSeq = "adminSeq";
	public static final String adminId = "adminId";
	public static final String adminRank = "adminRank";
	
	/*
	 * page search session key setting
	 * */
	public static final String searchCategorySeq = "searchCategorySeq";
	public static final String searchProductSeq = "searchProductSeq";
	public static final String searchAccountRank = "searchAccountRank";
	public static final String searchStr = "searchStr";
	public static final String searchOpt = "searchOpt";
	public static final String serviceType = "serviceType";
	public static final String communityType = "communityType";
	
	/*
	 * 
	 * */
	public static final int    NUM_OF_LINE50 = 50 ;
    public static final int    NUM_OF_LINE15 = 15 ;
    public static final int    NUM_OF_LINE20 = 20 ;
    public static final int    NUM_OF_LINE10 = 10 ;
    public static final int    NUM_OF_LINE5 = 5 ;
    public static final int    NUM_OF_LINE3 = 3 ;
    
    /*
     * 
     * */
    public static final String adminListUrl = "admin_list.do?";
    public static final String serviceListUrl = "service_list.do?";
    public static final String communityListUrl = "community_list.do?";
    
    
    /*
     * 
     * */
    public static final String IMG_UPLOAD_DIR = System.getProperty("catalina.home")+"/webapps/HanKil/upload/img";
    public static final String VDO_UPLOAD_DIR = System.getProperty("catalina.home")+"/webapps/HanKil/upload/vdo";
    public static final String FILE_UPLOAD_DIR = System.getProperty("catalina.home")+"/webapps/HanKil/upload/file";
    public static final String HTML_UPLOAD_DIR = System.getProperty("catalina.home")+"/webapps/HanKil/upload/html";
    public static final String TEMP_UPLOAD_DIR = System.getProperty("catalina.home")+"/webapps/HanKil/upload/temp";
//    public static final String IMG_UPLOAD_DIR = "D:/develop/04_mtelo/work/HanKil/src/main/webapp/upload/img";
//    public static final String VDO_UPLOAD_DIR = "D:/develop/04_mtelo/work/HanKil/src/main/webapp/upload/vdo";
//    public static final String FILE_UPLOAD_DIR = "D:/develop/04_mtelo/work/HanKil/src/main/webapp/upload/file";
//    public static final String HTML_UPLOAD_DIR = "D:/develop/04_mtelo/work/HanKil/src/main/webapp/upload/html";
//    public static final String TEMP_UPLOAD_DIR = "D:/develop/04_mtelo/work/HanKil/src/main/webapp/upload/temp";
    public static final String IMG_ALIAS_DIR = "/upload/img/";
    public static final String VDO_ALIAS_DIR = "/upload/vdo/";
    public static final String FILE_ALIAS_DIR = "/upload/file/";
    public static final String HTML_ALIAS_DIR = "/upload/html/";
    public static final String TEMP_ALIAS_DIR = "/upload/temp/";
    
    /*
     * 
     * */
    public static final String[] IMG_EXTEND_ARR = {"jpg","JPG","png","PNG","gif","GIF"};
    public static final String[] VDO_EXTEND_ARR = {"avi","AVI","mp4","MP4","wmv","WMV","mkv","MKV"};
    public static final String[] HTML_EXTEND_ARR = {"zip","ZIP"};
}
