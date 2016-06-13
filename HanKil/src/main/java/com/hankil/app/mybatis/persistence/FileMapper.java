package com.hankil.app.mybatis.persistence;

import java.util.List;

import com.hankil.app.mybatis.bean.FileVo;

public interface FileMapper {

	List<FileVo> getFileList()throws Exception;
	FileVo getFileInfo(FileVo fileVo)throws Exception;
	void addFileInfo(FileVo fileVo)throws Exception;
	void modifyFileInfo(FileVo fileVo)throws Exception;
	int getMaxSeq()throws Exception;
}
