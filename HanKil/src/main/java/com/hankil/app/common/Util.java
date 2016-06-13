package com.hankil.app.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class Util {

	/**
     * 0
     */
    public static final int DEFAULT_INT_VALUE = 0;
    /**
     * ""
     */
    public static final String DEFAULT_STRING_BUFFER_VALUE = "";
	
	public static void resetSessionSearchKey(HttpSession session){
			session.removeAttribute(Config.searchCategorySeq);
			session.removeAttribute(Config.searchProductSeq);
			session.removeAttribute(Config.searchStr);
			session.removeAttribute(Config.searchOpt);
	}
	
	public static String getSessionStr(HttpSession session,String searchKey){
		String value = DEFAULT_STRING_BUFFER_VALUE;
        try {
            value = session.getAttribute(searchKey).toString().trim();
        } catch (NumberFormatException e) {
        } catch (NullPointerException e) {
        }
        return value;
	}
	
	public static int getSessionInt(HttpSession session,String searchKey){
		int value = DEFAULT_INT_VALUE;
        try {
           value = Integer.parseInt(session.getAttribute(searchKey).toString().trim());
        } catch (NumberFormatException e) {
        } catch (NullPointerException e) {
        }
        return value;
	}
	
	public static int getStartIdx(int pageNo, int LINE_NUM){
		int result = 0;
		
		if(pageNo<=1){
			result = 0;
		}else{
			result = (pageNo-1)*LINE_NUM;
		}
		
		return result;
	}
	
	public static int getEndIdx(int startIdx, int LINE_NUM, int totCnt){
		int result = 0;
		
		if(startIdx<totCnt){
			result = startIdx+LINE_NUM;
		}else{
			result = totCnt+LINE_NUM;
		}
		
		return result;
	}
	
	public static String setUpLoadFile(MultipartFile vfile){
		MultipartFile file = vfile;
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                System.out.println("file name : "+file.getName());
                System.out.println("file original name : "+file.getOriginalFilename());
                System.out.println("file contents type : "+file.getContentType());
 
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                
                System.out.println("Server File Location=" + serverFile.getAbsolutePath());
 
                return Config.successCode;
            } catch (Exception e) {
            	System.out.println(e.getMessage());
            	return Config.errorCode;
            }
        } else {
        	return Config.failedCode;
        }
	}
	
	/**
    *	Generates a Random Unique ID
    */
	public static String getUniqueID() {
		StringBuffer  sb = new StringBuffer();
		java.util.StringTokenizer st = new java.util.StringTokenizer (new java.rmi.server.UID().toString(), ":");

		while(st.hasMoreTokens()) {
			sb.append(st.nextToken());
		}
		return sb.toString();
	}
	
	public static String getUploadDirByExtend(String fileExtend){
		String result = "";
		try{
			for(int i=0;i<Config.IMG_EXTEND_ARR.length;i++){
				if(fileExtend.equals(Config.IMG_EXTEND_ARR[i])){
					result = Config.IMG_UPLOAD_DIR;
				}
			}
			for(int i=0;i<Config.VDO_EXTEND_ARR.length;i++){
				if(fileExtend.equals(Config.VDO_EXTEND_ARR[i])){
					result = Config.VDO_UPLOAD_DIR;
				}
			}
			for(int i=0;i<Config.HTML_EXTEND_ARR.length;i++){
				if(fileExtend.equals(Config.HTML_EXTEND_ARR[i])){
					result = Config.HTML_UPLOAD_DIR;
				}
			}
			if((0>result.length())||(result.equals(""))){
				result = Config.FILE_UPLOAD_DIR;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public static String getUploadAliasByExtend(String fileExtend){
		String result = "";
		try{
			for(int i=0;i<Config.IMG_EXTEND_ARR.length;i++){
				if(fileExtend.equals(Config.IMG_EXTEND_ARR[i])){
					result = Config.IMG_ALIAS_DIR;
				}
			}
			for(int i=0;i<Config.VDO_EXTEND_ARR.length;i++){
				if(fileExtend.equals(Config.VDO_EXTEND_ARR[i])){
					result = Config.VDO_ALIAS_DIR;
				}
			}
			for(int i=0;i<Config.HTML_EXTEND_ARR.length;i++){
				if(fileExtend.equals(Config.HTML_EXTEND_ARR[i])){
					result = Config.HTML_ALIAS_DIR;
				}
			}
			if((0>result.length())||(result.equals(""))){
				result = Config.FILE_ALIAS_DIR;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public static boolean fileMoveDir(String orgDir, String newDir, String fileName, String fileExtend) throws IOException{
		boolean result = false;
		
		if(fileExtend.equals("zip")){
			byte[] buffer = new byte[1024];
			
			File folder = new File(newDir);
			
			if(!folder.exists()){
	    		folder.mkdir();
	    	}
			
			ZipInputStream zis = new ZipInputStream(new FileInputStream(orgDir+File.separator+fileName));
			
			ZipEntry ze = zis.getNextEntry();
			
			while(ze!=null){
				String inFileName = ze.getName();
				File newFile = new File(newDir+File.separator+removeExtend(fileName, ".")+File.separator+inFileName);
				
				System.out.println("file unzip : "+ newFile.getAbsoluteFile());
				
				new File(newFile.getParent()).mkdir();
				
				FileOutputStream fos = new FileOutputStream(newFile);
				
				int len;
	            while ((len = zis.read(buffer)) > 0) {
	       		fos.write(buffer, 0, len);
	            }
	 
	            fos.close();   
	            ze = zis.getNextEntry();
			}
			
			zis.closeEntry();
        	zis.close();
        	
        	deleteFile(orgDir, fileName);
        	System.out.println("Done");
		}else{
			FileInputStream fis = null;
			FileOutputStream fos = null;
			try {
				File fileDir = new File(newDir);
				
				if (!fileDir.exists())
					fileDir.mkdirs();
				
				fis = new FileInputStream(orgDir+File.separator+fileName);
				fos = new FileOutputStream(newDir+File.separator+fileName);
				
				int data = 0;
				while((data=fis.read())!=-1){
					fos.write(data);
				}
				fis.close();
				fos.close();
				deleteFile(orgDir, fileName);
				
				result = true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} finally {
				if(fis != null){
					fis.close();
				}
				if(fos != null){
					fos.close();
				}
			}
		}
		return result;
	}
	
	public static boolean deleteFile(String dir, String fileName){
		boolean result = false;
		try{
			File f = new File(dir+File.separator+fileName);
			f.delete();
			
			result = true;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public static String removeExtend( String strFileName, String findChar ) {
		String strExtend="";
		try {
			int intdot = strFileName.lastIndexOf(findChar);
			strExtend = strFileName.substring( 0, intdot).toLowerCase();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return strExtend;
	}
}