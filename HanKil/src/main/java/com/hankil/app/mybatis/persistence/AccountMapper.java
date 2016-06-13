package com.hankil.app.mybatis.persistence;

import java.util.List;
import java.util.Map;

import com.hankil.app.mybatis.bean.AccountVo;

public interface AccountMapper {

	List<AccountVo> getAccountList(AccountVo accountVo);
	AccountVo getAccountInfoByID(AccountVo accountVo);
	AccountVo getAccountInfoBySeq(String user_seq);
	List<AccountVo> getAccountListByRank(Map<String, Object> pmap);
	void addAccountInfo(AccountVo accountVo);
	void modifyAccountInfo(AccountVo accountVo);
	int getAccountTotCnt(AccountVo accountVo);
	List<AccountVo> getAccountRankList(AccountVo accountVo);
}
