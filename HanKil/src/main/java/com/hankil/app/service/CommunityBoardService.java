package com.hankil.app.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hankil.app.mybatis.bean.CommunityBoardVo;
import com.hankil.app.mybatis.persistence.CommunityBoardMapper;

@Service
public class CommunityBoardService {

	private static final Logger logger = LoggerFactory.getLogger(CommunityBoardService.class);
	
	@Autowired
	private CommunityBoardMapper communityBoardMapper;
	
	
	/*
	 * 커뮤니티 게시판 리스트 조회
	 * */
	public List<CommunityBoardVo> getCommunityBoardList(CommunityBoardVo communityBoardVo){
		List<CommunityBoardVo>  rList = null;
		try{
			rList = communityBoardMapper.getCommunityBoardList(communityBoardVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return rList;
	}
	
	/*
	 * 커뮤니티 게시판 입력
	 * */
	public void addCommunityBoardInfo(CommunityBoardVo communityBoardVo){
		try{
			communityBoardMapper.addCommunityBoardInfo(communityBoardVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
	}
	
	/*
	 * 커뮤니티 게시판 상세 조회
	 * */
	public CommunityBoardVo getCommunityBoardInfo(CommunityBoardVo communityBoardVo){
		try{
			communityBoardVo = communityBoardMapper.getCommunityBoardInfo(communityBoardVo);
			int count = communityBoardVo.getCount();
			communityBoardVo.setCount(++count);
			communityBoardMapper.modifyCommunityBoardCount(communityBoardVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return communityBoardVo;
	}
	
	
	/*
	 * 커뮤니티 게시판 수정
	 * */
	public void modifyCommunityBoardInfo(CommunityBoardVo communityBoardVo){
		try{
			communityBoardMapper.modifyCommunityBoardInfo(communityBoardVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
	}
	
	/*
	 * 
	 * */
	public int getCommunityBoarVotCnt(CommunityBoardVo communityBoardVo){
		int result = 0;
		try{
			result = communityBoardMapper.getCommunityBoarVotCnt(communityBoardVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return result;
	}
	
	public int getMaxSeq(){
		int result = 0;
		try{
			result = communityBoardMapper.getMaxSeq();
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return result;
	}
	
	public void addCommunityFile(CommunityBoardVo communityBoardVo){
		try{
			communityBoardMapper.addCommunityFile(communityBoardVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
	}
	
	public int getReplyMaxNum(CommunityBoardVo communityBoardVo){
		int result = 0;
		try{
			result = communityBoardMapper.getReplyMaxNum(communityBoardVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return result;
	}
	
	public List<CommunityBoardVo> getCommunityShortcutList(int type){
		List<CommunityBoardVo>  rList = null;
		try{
			rList = communityBoardMapper.getCommunityShortcutList(type);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return rList;
	}
}
