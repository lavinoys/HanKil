package com.hankil.app.service;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hankil.app.mybatis.bean.FileVo;
import com.hankil.app.mybatis.persistence.FileMapper;

@Service
public class FileService {

	private static final Logger logger = LoggerFactory.getLogger(FileService.class);
	
	@Autowired
	private FileMapper fileMapper;
	
	public List<FileVo> getFileList(){
		List<FileVo> rList = null;
		try{
			rList = fileMapper.getFileList();
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return rList;
	}
	
	public FileVo getFileInfo(FileVo fileVo){
		try{
			fileVo = fileMapper.getFileInfo(fileVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return fileVo;
	}
	public void addFileInfo(FileVo fileVo){
		try{
			fileMapper.addFileInfo(fileVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
	}
	public void modifyFileInfo(FileVo fileVo){
		try{
			fileMapper.modifyFileInfo(fileVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
	}
	public int getMaxSeq(){
		int result = 0; 
		try{
			result = fileMapper.getMaxSeq();
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return result;
	}
	
}
