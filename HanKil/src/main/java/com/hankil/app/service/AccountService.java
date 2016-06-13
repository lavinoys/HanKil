package com.hankil.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hankil.app.common.Config;
import com.hankil.app.mybatis.bean.AccountVo;
import com.hankil.app.mybatis.persistence.AccountMapper;

@Service
public class AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private AccountMapper accountMapper;
	
	/*
	 * 계정 리스트 조회 
	 * */
	public List<AccountVo> getAccountList(AccountVo accountVo){
		List<AccountVo> rList = null;
		
		try{
			rList = accountMapper.getAccountList(accountVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		
		return rList;
	}
	
	/*
	 * 계정정보 조회
	 * */
	public String getAccountLoginChk(AccountVo accountVo, HttpSession session){
		String resultCode = Config.successCode;
		
		try{
			String id = accountVo.getUser_id();
			String passwd = accountVo.getUser_passwd();
			
			accountVo = accountMapper.getAccountInfoByID(accountVo);
			
			if(null == accountVo){
				return Config.failedCode;
			}
			
			if(!id.equals(accountVo.getUser_id())){
				return Config.errorIdCode;
			}else if(!passwd.equals(accountVo.getUser_passwd())){
				return Config.errorPasswdCode;
			}
			
			if(Config.successCode.equals(resultCode)){
				session.setAttribute(Config.adminId, accountVo.getUser_id());
				session.setAttribute(Config.adminRank, accountVo.getUser_rank());
				session.setAttribute(Config.adminSeq, accountVo.getUser_seq());
			}
		}catch(Exception e){
			logger.info(e.getMessage());
			return Config.errorCode;
		}
		return resultCode;
	}
	
	/*
	 * 계정 리스트 조회 
	 * */
	public List<AccountVo> getAccountListByRank(Object user_rank){
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("user_rank", user_rank);
		List<AccountVo> rList = accountMapper.getAccountListByRank(pmap);
		
		return rList;
	}
	
	/*
	 * 계정 상세 조회
	 * */
	public AccountVo getAccountInfoBySeq(String user_seq){
		return accountMapper.getAccountInfoBySeq(user_seq);
	}
	
	/*
	 * 중복 계정 조회
	 * */
	public String doDupliChk(AccountVo accountVo){
		String resultCode = Config.failedCode;
		
		try{
			String id = accountVo.getUser_id();
			
			accountVo = accountMapper.getAccountInfoByID(accountVo);
			
			if(null == accountVo){
				resultCode = Config.successCode;
			}else if(id.equals(accountVo.getUser_id())){
				resultCode = Config.failedCode;
			}
			
		}catch(Exception e){
			logger.info(e.getMessage());
			return Config.errorCode;
		}
		return resultCode;
	}
	
	/*
	 * 계정 입력
	 * */
	public void addAccountInfo(AccountVo accountVo){
		try{
			accountMapper.addAccountInfo(accountVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
	}
	
	/*
	 * 계정 수정
	 * */
	public void modifyAccountInfo(AccountVo accountVo){
		try{
			accountMapper.modifyAccountInfo(accountVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
	}
	
	/*
	 * 계정의 계급 리스트
	 * */
	public List<AccountVo> getAccountRankList(AccountVo accountVo){
		List<AccountVo> rList = null;
		
		try{
			rList = accountMapper.getAccountRankList(accountVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		
		return rList;
	}
	
	public int getAccountTotCnt(AccountVo accountVo){
		int result = 0;
		try{
			result = accountMapper.getAccountTotCnt(accountVo);
		}catch(Exception e){
			
		}
		return result;
	}
}
