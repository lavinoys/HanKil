package com.hankil.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hankil.app.mybatis.bean.ServiceBoardVo;
import com.hankil.app.mybatis.persistence.ServiceBoardMapper;

@Service
public class ServiceBoardService {

	private static final Logger logger = LoggerFactory.getLogger(ServiceBoardService.class);
	
	@Autowired
	private ServiceBoardMapper serviceBoardMapper;
	
	/*
	 * 고객지원 게시판 리스트 조회
	 * */
	public List<ServiceBoardVo> getServiceBoardList(ServiceBoardVo serviceBoardVo){
		List<ServiceBoardVo>  rList = null;
		try{
			rList = serviceBoardMapper.getServiceBoardList(serviceBoardVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return rList;
	}
	
	/*
	 * 고객지원 게시판 입력
	 * */
	public void addServiceBoardInfo(ServiceBoardVo serviceBoardVo){
		try{
			serviceBoardMapper.addServiceBoardInfo(serviceBoardVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
	}
	
	/*
	 * 고객지원 게시판 상세 조회
	 * */
	public ServiceBoardVo getServiceBoardInfo(ServiceBoardVo serviceBoardVo){
		try{
			serviceBoardVo = serviceBoardMapper.getServiceBoardInfo(serviceBoardVo);
			int count = serviceBoardVo.getCount();
			serviceBoardVo.setCount(++count);
			serviceBoardMapper.modifyServiceBoardInfo(serviceBoardVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return serviceBoardVo;
	}
	
	/*
	 * 고객지원 게시판 수정
	 * */
	public void modifyServiceBoardInfo(ServiceBoardVo serviceBoardVo){
		try{
			serviceBoardMapper.modifyServiceBoardInfo(serviceBoardVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
	}
	
	/*
	 * 서비스 게시판 리스트 총 카운트
	 * */
	public int getServiceBoarVotCnt(ServiceBoardVo serviceBoardVo){
		int result = 0;
		try{
			result = serviceBoardMapper.getServiceBoarVotCnt(serviceBoardVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return result;
	}
	
	public int getMaxSeq(){
		int result = 0;
		try{
			result = serviceBoardMapper.getMaxSeq();
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return result;
	}
	
	public int getReplyMaxNum(ServiceBoardVo serviceBoardVo){
		int result = 0;
		try{
			result = serviceBoardMapper.getReplyMaxNum(serviceBoardVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return result;
	}
}
