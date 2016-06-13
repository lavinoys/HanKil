package com.hankil.app.mybatis.persistence;

import java.util.List;

import com.hankil.app.mybatis.bean.CommunityBoardVo;


public interface CommunityBoardMapper {

	List<CommunityBoardVo> getCommunityBoardList(CommunityBoardVo communityBoardVo) throws Exception;
	void addCommunityBoardInfo(CommunityBoardVo communityBoardVo) throws Exception;
	CommunityBoardVo getCommunityBoardInfo(CommunityBoardVo communityBoardVo) throws Exception;
	void modifyCommunityBoardInfo(CommunityBoardVo communityBoardVo) throws Exception;
	void modifyCommunityBoardCount(CommunityBoardVo communityBoardVo) throws Exception;
	int getCommunityBoarVotCnt(CommunityBoardVo communityBoardVo) throws Exception;
	void addCommunityFile(CommunityBoardVo communityBoardVo)throws Exception;
	int getMaxSeq()throws Exception;
	int getReplyMaxNum(CommunityBoardVo communityBoardVo) throws Exception;
	List<CommunityBoardVo> getCommunityShortcutList(int type) throws Exception;
}
