package com.hankil.app.mybatis.persistence;

import java.util.List;

import com.hankil.app.mybatis.bean.ServiceBoardVo;

public interface ServiceBoardMapper {

	List<ServiceBoardVo> getServiceBoardList(ServiceBoardVo serviceBoardVo) throws Exception;
	void addServiceBoardInfo(ServiceBoardVo serviceBoardVo) throws Exception;
	ServiceBoardVo getServiceBoardInfo(ServiceBoardVo serviceBoardVo) throws Exception;
	void modifyServiceBoardInfo(ServiceBoardVo serviceBoardVo) throws Exception;
	int getMaxSeq()throws Exception;
	int getServiceBoarVotCnt(ServiceBoardVo serviceBoardVo) throws Exception;
	int getReplyMaxNum(ServiceBoardVo serviceBoardVo) throws Exception;
}
