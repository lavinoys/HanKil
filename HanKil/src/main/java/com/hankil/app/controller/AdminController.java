package com.hankil.app.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hankil.app.common.Config;
import com.hankil.app.common.HttpParameter;
import com.hankil.app.common.PageNavigator;
import com.hankil.app.common.Util;
import com.hankil.app.common.upload.http.Uploader;
import com.hankil.app.mybatis.bean.AccountVo;
import com.hankil.app.mybatis.bean.CommunityBoardVo;
import com.hankil.app.mybatis.bean.FileVo;
import com.hankil.app.mybatis.bean.ProductCategoryVo;
import com.hankil.app.mybatis.bean.ProductVo;
import com.hankil.app.mybatis.bean.ServiceBoardVo;
import com.hankil.app.service.AccountService;
import com.hankil.app.service.CommunityBoardService;
import com.hankil.app.service.FileService;
import com.hankil.app.service.ProductService;
import com.hankil.app.service.ServiceBoardService;
import com.hankil.app.mybatis.bean.ResultVo;

@Controller
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private ServiceBoardService serviceBoardService;
	@Autowired
	private CommunityBoardService communityBoardService;
	@Autowired
	private ProductService productService;
	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/admin/{articleId}.do", method = RequestMethod.GET)
	public String convertPage(@PathVariable(value = "articleId") String articleId, HttpSession session) {
		logger.info("/admin/"+articleId+".do");
		if(session.getAttribute(Config.adminId)==null)
			return "/admin/admin_login.do";
		return "/admin/"+articleId+".do";
	}
	
	
	/*
	 * 로그인 관련
	 * */
	@RequestMapping(value = "/admin/login_action.do", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultVo<?> doAdminLogin(@RequestBody final AccountVo accountVo, HttpSession session) {
		logger.info("/admin/login_action.do");
		ResultVo<RequestMethod> result = new ResultVo<RequestMethod>();
		try{
			logger.info("connection ID : " + accountVo.getUser_id());
			result.setResultCode(accountService.getAccountLoginChk(accountVo, session));
			
			if(Config.successCode.equals(result.getResultCode())){
				result.setUrl("/admin/admin_list.do?pageNo=1");
			}
		}catch(Exception e){
			result.setResultCode(Config.errorCode);
		}
		return result;
	}
	
	/*
	 * 계정 리스트 조회
	 * */
	@RequestMapping(value = "/admin/admin_list.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getAccountList(HttpServletRequest request) {
		logger.info("/admin/admin_list.do");
		if(request.getSession().getAttribute(Config.adminId)==null)
			return "/admin/admin_login.do";
		
		if(request.getParameter("INIT")!= null){
			Util.resetSessionSearchKey(request.getSession());
		}
		
		String user_rank = HttpParameter.getString(request, "user_rank");
		String searchOpt = HttpParameter.getString(request, "searchOpt");
		String searchStr = HttpParameter.getString(request, "searchStr");
		String connect_rank = (String)request.getSession().getAttribute(Config.adminRank);
		
		request.getSession().setAttribute(Config.searchAccountRank, user_rank);
		request.getSession().setAttribute(Config.searchOpt, searchOpt);
		request.getSession().setAttribute(Config.searchStr, searchStr);
		
		AccountVo accountVo = new AccountVo();
		
		accountVo.setUser_rank(user_rank);
		if(searchOpt.equals("id")){
			accountVo.setUser_id(searchStr);
		}else if(searchOpt.equals("name")){
			accountVo.setUser_name(searchStr);
		}
		accountVo.setConnect_rank(connect_rank);
		
		int pageNo = HttpParameter.getInt(request, "pageNo");
		int totCnt = accountService.getAccountTotCnt(accountVo);
		int startIdx = Util.getStartIdx(pageNo, Config.NUM_OF_LINE10);
		int endIdx = Config.NUM_OF_LINE10;
		
		accountVo.setStartIdx(startIdx);
		accountVo.setEndIdx(endIdx);
		
		request.setAttribute("account_list", accountService.getAccountList(accountVo));
		request.setAttribute("account_rank_list", accountService.getAccountRankList(accountVo));
		
		PageNavigator navigator = new PageNavigator(pageNo, totCnt, Config.NUM_OF_LINE10, Config.adminListUrl);
		
		request.setAttribute("pageNavigator", navigator.getHtml());
		
		return "/admin/admin_list.do";
	}
	
	/*
	 * 계정 상세 조회
	 * */
	@RequestMapping(value = "/admin/admin_info.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getAccountInfo(HttpServletRequest request) {
		logger.info("/admin/admin_info.do");
		if(request.getSession().getAttribute(Config.adminId)==null)
			return "/admin/admin_login.do";
		
		request.setAttribute("account_info", accountService.getAccountInfoBySeq(HttpParameter.getString(request, "user_seq")));
		
		return "/admin/admin_info.do";
	}
	
	/*
	 * 중복 계정 조회
	 * */
	@RequestMapping(value = "/admin/admin_dupChk.do", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultVo<?> doDupliChk(@RequestBody final AccountVo accountVo) {
		logger.info("/admin/admin_dupChk.do");
		
		ResultVo<RequestMethod> result = new ResultVo<RequestMethod>();
		try{
			result.setResultCode(accountService.doDupliChk(accountVo));
		}catch(Exception e){
			logger.info(e.getMessage());
			result.setResultCode(Config.errorCode);
		}
		return result;
	}
	
	/*
	 * 계정 입력
	 * */
	@RequestMapping(value = "/admin/admin_write_action.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String addAccountInfo(HttpServletRequest request) {
		logger.info("/admin/admin_write_action.do");
		if(request.getSession().getAttribute(Config.adminId)==null)
			return "/admin/admin_login.do";
		AccountVo accountVo = new AccountVo();
		try{
			accountVo.setUser_id(HttpParameter.getString(request, "user_id"));
			accountVo.setUser_passwd(HttpParameter.getString(request, "user_passwd"));
			accountVo.setUser_name(HttpParameter.getString(request, "user_name"));
			accountVo.setUser_rank(HttpParameter.getString(request, "user_rank"));
			accountVo.setUser_Email(HttpParameter.getString(request, "user_Email"));
			accountVo.setUser_phone(HttpParameter.getString(request, "user_phone"));
			accountVo.setUser_address(HttpParameter.getString(request, "user_address"));
			accountVo.setUser_department(HttpParameter.getString(request, "user_department"));
			accountVo.setUse_yn("y");
			
			accountService.addAccountInfo(accountVo);
			
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "redirect:/admin/admin_list.do";
	}
	
	/*
	 * 계정 수정
	 * */
	@RequestMapping(value = "/admin/admin_modify_action.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyAccountInfo(HttpServletRequest request) {
		logger.info("/admin/admin_modify_action.do");
		if(request.getSession().getAttribute(Config.adminId)==null)
			return "/admin/admin_login.do";
		AccountVo accountVo = new AccountVo();
		try{
			accountVo.setUser_seq(HttpParameter.getInt(request, "user_seq"));
			accountVo.setUser_id(HttpParameter.getString(request, "user_id"));
			accountVo.setUser_passwd(HttpParameter.getString(request, "user_passwd"));
			accountVo.setUser_name(HttpParameter.getString(request, "user_name"));
			accountVo.setUser_rank(HttpParameter.getString(request, "user_rank"));
			accountVo.setUser_Email(HttpParameter.getString(request, "user_Email"));
			accountVo.setUser_phone(HttpParameter.getString(request, "user_phone"));
			accountVo.setUser_address(HttpParameter.getString(request, "user_address"));
			accountVo.setUser_department(HttpParameter.getString(request, "user_department"));
			accountVo.setUse_yn(HttpParameter.getString(request, "use_yn"));
			
			accountService.modifyAccountInfo(accountVo);
			
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "redirect:/admin/admin_list.do";
	}
	
	/*
	 * 고객지원 게시판 리스트 조회
	 * */
	@RequestMapping(value = "/admin/service_list.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getServiceBoardList(HttpServletRequest request) {
		logger.info("/admin/service_list.do");
		if(request.getSession().getAttribute(Config.adminId)==null)
			return "/admin/admin_login.do";
		
		if(request.getParameter("INIT")!= null){
			Util.resetSessionSearchKey(request.getSession());
		}
		
		int serviceType = HttpParameter.getInt(request, "serviceType");
		int category_seq = HttpParameter.getInt(request, "category_seq");
		int product_seq = HttpParameter.getInt(request, "product_seq");
		String searchOpt = HttpParameter.getString(request, "searchOpt");
		String searchStr = HttpParameter.getString(request, "searchStr");
		
		request.getSession().setAttribute(Config.searchCategorySeq, category_seq);
		request.getSession().setAttribute(Config.searchProductSeq, product_seq);
		request.getSession().setAttribute(Config.searchOpt, searchOpt);
		request.getSession().setAttribute(Config.searchStr, searchStr);
		if(0<serviceType){
			request.getSession().setAttribute(Config.serviceType, serviceType);
		}else{
			serviceType = (Integer)request.getSession().getAttribute(Config.serviceType);
		}
		
		ServiceBoardVo boardVo = new ServiceBoardVo();
		boardVo.setType(serviceType);
		boardVo.setCategory_seq(category_seq);
		boardVo.setProduct_seq(product_seq);
		if(searchOpt.equals("title")){
			boardVo.setTitle(searchStr);
		}else if(searchOpt.equals("name")){
			boardVo.setUser_name(searchStr);
		}
		
		int pageNo = HttpParameter.getInt(request, "pageNo");
		int totCnt = serviceBoardService.getServiceBoarVotCnt(boardVo);
		int startIdx = Util.getStartIdx(pageNo, Config.NUM_OF_LINE10);
		int endIdx = Config.NUM_OF_LINE10;
		
		boardVo.setStartIdx(startIdx);
		boardVo.setEndIdx(endIdx);
		
		request.setAttribute("service_list", serviceBoardService.getServiceBoardList(boardVo));
		request.setAttribute("category_list", productService.getProductCategoryList());
		
		if(0<category_seq){
			ProductVo productVo = new ProductVo();
			productVo.setCategory_seq(category_seq);
			request.setAttribute("product_list", productService.getProductList(productVo));
		}
		
		PageNavigator navigator = new PageNavigator(pageNo, totCnt, Config.NUM_OF_LINE10, Config.serviceListUrl, serviceType);
		request.setAttribute("pageNavigator", navigator.getHtml());
		
		return "/admin/service_list.do";
	}
	
	/*
	 * 고객지원 게시글 입력 폼으로 이동
	 * */
	@RequestMapping(value = "/admin/service_write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String moveServiceWrite(HttpServletRequest request) {
		logger.info("/admin/service_info.do");
		if((request.getSession().getAttribute(Config.adminId)==null)||(request.getSession().getAttribute(Config.adminSeq)==null))
			return "/admin/admin_login.do";
		try{
			request.setAttribute("category_list", productService.getProductCategoryList());
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "/admin/service_write.do";
	}
	
	/*
	 * 고객지원 게시판 입력
	 * */
	@RequestMapping(value = "/admin/service_write_action.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String addServiceInfo(HttpServletRequest request) {
		logger.info("/admin/service_write_action.do");
		if((request.getSession().getAttribute(Config.adminId)==null)||(request.getSession().getAttribute(Config.adminSeq)==null))
			return "/admin/admin_login.do";
		ServiceBoardVo serviceBoardVo = new ServiceBoardVo();
		int serviceType = 0;
		try{
			serviceType = (Integer) request.getSession().getAttribute("serviceType");
			
			if (request.getContentType() != null &&
					request.getContentType().toLowerCase().indexOf("multipart/form-data") > -1 ){
				Uploader up = new Uploader();
				String upUrl = "";
				String upAliasUrl = "";
				String fileExtend = "";
				boolean isSuccess = false;
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				upUrl = Config.TEMP_UPLOAD_DIR;
				upAliasUrl = Config.TEMP_ALIAS_DIR;
				up.setUploadDir(upUrl, 2);
				isSuccess = up.doUpload(request, dateFormat.format(new java.util.Date()));
				String aliasFileName = up.getRenames();
				fileExtend = up.extendDivision(aliasFileName, ".");
				String newUpUrl = Util.getUploadDirByExtend(fileExtend);
				upAliasUrl = Util.getUploadAliasByExtend(fileExtend);
				Util.fileMoveDir(upUrl, newUpUrl, aliasFileName,fileExtend);
				
				serviceBoardVo.setUser_seq((Integer)request.getSession().getAttribute(Config.adminSeq));
				serviceBoardVo.setTitle(up.getParameter("title"));
				serviceBoardVo.setContent(up.getParameter("content"));
				serviceBoardVo.setCount(Integer.parseInt(up.getParameter("count")));
				serviceBoardVo.setType(serviceType);
				serviceBoardVo.setRank(Integer.parseInt(up.getParameter("rank")));
				serviceBoardVo.setUse_yn(up.getParameter("use_yn"));
				serviceBoardVo.setSecret_yn(up.getParameter("secret_yn"));
				serviceBoardVo.setReply_yn(up.getParameter("reply_yn"));
				serviceBoardVo.setPasswd(up.getParameter("passwd"));
				serviceBoardVo.setCategory_seq(Integer.parseInt(up.getParameter("category_seq")));
				serviceBoardVo.setProduct_seq(Integer.parseInt(up.getParameter("product_seq")));
				serviceBoardVo.setUser_name(up.getParameter("name"));
				serviceBoardVo.setRef_seq(Integer.parseInt(up.getParameter("ref_seq")));
				serviceBoardVo.setReply_depth(Integer.parseInt(up.getParameter("reply_depth")));
				serviceBoardVo.setReply_num(Integer.parseInt(up.getParameter("reply_num")));
				
				serviceBoardService.addServiceBoardInfo(serviceBoardVo);
				
				if(isSuccess){
					FileVo fileVo = new FileVo();
					fileVo.setFile_name(aliasFileName);
					if(fileExtend.equals("zip")){
						fileVo.setFile_url(upAliasUrl+Util.removeExtend(aliasFileName, "."));
					}else{
						fileVo.setFile_url(upAliasUrl+aliasFileName);
					}
					fileVo.setFile_type(fileExtend);
					
					fileService.addFileInfo(fileVo);
					serviceBoardVo.setService_seq(serviceBoardService.getMaxSeq());
					serviceBoardVo.setFile_seq(fileService.getMaxSeq());
					if(1>serviceBoardVo.getRef_seq()){
						serviceBoardVo.setRef_seq(serviceBoardVo.getService_seq());
					}
					serviceBoardService.modifyServiceBoardInfo(serviceBoardVo);
				}
			}else{
				serviceBoardVo.setUser_seq((Integer)request.getSession().getAttribute(Config.adminSeq));
				serviceBoardVo.setTitle(HttpParameter.getString(request,"title"));
				serviceBoardVo.setContent(HttpParameter.getString(request,"content"));
				serviceBoardVo.setCount(HttpParameter.getInt(request,"count"));
				serviceBoardVo.setType(serviceType);
				serviceBoardVo.setRank(HttpParameter.getInt(request,"rank"));
				serviceBoardVo.setUse_yn(HttpParameter.getString(request,"use_yn"));
				serviceBoardVo.setSecret_yn(HttpParameter.getString(request,"secret_yn"));
				serviceBoardVo.setReply_yn(HttpParameter.getString(request,"reply_yn"));
				serviceBoardVo.setPasswd(HttpParameter.getString(request,"passwd"));
				serviceBoardVo.setCategory_seq(HttpParameter.getInt(request,"category_seq"));
				serviceBoardVo.setProduct_seq(HttpParameter.getInt(request,"product_seq"));
				serviceBoardVo.setUser_name(HttpParameter.getString(request, "name"));
				serviceBoardVo.setRef_seq(HttpParameter.getInt(request, "ref_seq"));
				serviceBoardVo.setReply_depth(HttpParameter.getInt(request, "reply_depth"));
				serviceBoardVo.setReply_num(HttpParameter.getInt(request, "reply_num"));
				
				serviceBoardService.addServiceBoardInfo(serviceBoardVo);
				
				if(1>serviceBoardVo.getRef_seq()){
					serviceBoardVo.setService_seq(serviceBoardService.getMaxSeq());
					serviceBoardVo.setRef_seq(serviceBoardVo.getService_seq());
					serviceBoardService.modifyServiceBoardInfo(serviceBoardVo);
				}
			}
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "redirect:/admin/service_list.do?serviceType="+serviceType;
	}
	
	/*
	 * 고객지원 게시글 상세 조회
	 * */
	@RequestMapping(value = "/admin/service_info.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getServiceInfo(HttpServletRequest request) {
		logger.info("/admin/service_info.do");
		if((request.getSession().getAttribute(Config.adminId)==null)||(request.getSession().getAttribute(Config.adminSeq)==null))
			return "/admin/admin_login.do";
		ServiceBoardVo serviceBoardVo = new ServiceBoardVo();
		try{
			serviceBoardVo.setService_seq(HttpParameter.getInt(request, "service_seq"));
			
			serviceBoardVo = serviceBoardService.getServiceBoardInfo(serviceBoardVo);
			FileVo fileVo = new FileVo();
			fileVo.setFile_seq(serviceBoardVo.getFile_seq());
			request.setAttribute("service_info", serviceBoardVo);
			//request.setAttribute("account_info", accountService.getAccountInfoBySeq(Integer.toString(serviceBoardVo.getUser_seq())));
			request.setAttribute("category_list", productService.getProductCategoryList());
			request.setAttribute("file_info", fileService.getFileInfo(fileVo));
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "/admin/service_info.do";
	}
	
	/*
	 * 고객지원 게시판 수정
	 * */
	@RequestMapping(value = "/admin/service_modify_action.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyServiceInfo(HttpServletRequest request) {
		logger.info("/admin/service_modify_action.do");
		if((request.getSession().getAttribute(Config.adminId)==null)||(request.getSession().getAttribute(Config.adminSeq)==null))
			return "/admin/admin_login.do";
		ServiceBoardVo serviceBoardVo = new ServiceBoardVo();
		int serviceType = 0;
		try{
			serviceType = (Integer) request.getSession().getAttribute("serviceType");
			if (request.getContentType() != null &&
					request.getContentType().toLowerCase().indexOf("multipart/form-data") > -1 ){
				
				Uploader up = new Uploader();
				String upUrl = "";
				String upAliasUrl = "";
				String fileExtend = "";
				boolean isSuccess = false;
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				upUrl = Config.TEMP_UPLOAD_DIR;
				upAliasUrl = Config.TEMP_ALIAS_DIR;
				up.setUploadDir(upUrl, 2);
				isSuccess = up.doUpload(request, dateFormat.format(new java.util.Date()));
				String aliasFileName = up.getRenames();
				fileExtend = up.extendDivision(aliasFileName, ".");
				String newUpUrl = Util.getUploadDirByExtend(fileExtend);
				upAliasUrl = Util.getUploadAliasByExtend(fileExtend);
				Util.fileMoveDir(upUrl, newUpUrl, aliasFileName, fileExtend);
				
				serviceBoardVo.setUser_seq((Integer)request.getSession().getAttribute(Config.adminSeq));
				serviceBoardVo.setService_seq(Integer.parseInt(up.getParameter("service_seq")));
				serviceBoardVo.setTitle(up.getParameter("title"));
				serviceBoardVo.setContent(up.getParameter("content"));
				serviceBoardVo.setCount(Integer.parseInt(up.getParameter("count")));
				serviceBoardVo.setType(serviceType);
				serviceBoardVo.setRank(Integer.parseInt(up.getParameter("rank")));
				serviceBoardVo.setUse_yn(up.getParameter("use_yn"));
				serviceBoardVo.setSecret_yn(up.getParameter("secret_yn"));
				serviceBoardVo.setReply_yn(up.getParameter("reply_yn"));
				serviceBoardVo.setPasswd(up.getParameter("passwd"));
				serviceBoardVo.setCategory_seq(Integer.parseInt(up.getParameter("category_seq")));
				serviceBoardVo.setProduct_seq(Integer.parseInt(up.getParameter("product_seq")));
				serviceBoardVo.setUser_name(up.getParameter("name"));
				serviceBoardVo.setRef_seq(Integer.parseInt(up.getParameter("ref_seq")));
				serviceBoardVo.setReply_depth(Integer.parseInt(up.getParameter("reply_depth")));
				serviceBoardVo.setReply_num(Integer.parseInt(up.getParameter("reply_num")));
				
				serviceBoardService.modifyServiceBoardInfo(serviceBoardVo);
				
				if(isSuccess){
					FileVo fileVo = new FileVo();
					fileVo.setFile_name(aliasFileName);
					if(fileExtend.equals("zip")){
						fileVo.setFile_url(upAliasUrl+Util.removeExtend(aliasFileName, "."));
					}else{
						fileVo.setFile_url(upAliasUrl+aliasFileName);
					}
					fileVo.setFile_type(fileExtend);
					
					fileService.addFileInfo(fileVo);
					serviceBoardVo.setFile_seq(fileService.getMaxSeq());
					serviceBoardService.modifyServiceBoardInfo(serviceBoardVo);
				}
				
			}else{
				logger.info(""+HttpParameter.getInt(request, "category_seq"));
				logger.info(""+HttpParameter.getInt(request, "product_seq"));
				
				serviceBoardVo.setService_seq(HttpParameter.getInt(request, "service_seq"));
				serviceBoardVo.setUser_seq((Integer)request.getSession().getAttribute(Config.adminSeq));
				serviceBoardVo.setTitle(HttpParameter.getString(request, "title"));
				serviceBoardVo.setContent(HttpParameter.getString(request, "content"));
				serviceBoardVo.setCount(HttpParameter.getInt(request, "count"));
				serviceBoardVo.setType(serviceType);
				serviceBoardVo.setRank(HttpParameter.getInt(request, "rank"));
				serviceBoardVo.setUse_yn(HttpParameter.getString(request, "use_yn"));
				serviceBoardVo.setSecret_yn(HttpParameter.getString(request, "secret_yn"));
				serviceBoardVo.setReply_yn(HttpParameter.getString(request, "reply_yn"));
				serviceBoardVo.setPasswd(HttpParameter.getString(request, "passwd"));
				serviceBoardVo.setCategory_seq(HttpParameter.getInt(request, "category_seq"));
				serviceBoardVo.setProduct_seq(HttpParameter.getInt(request, "product_seq"));
				serviceBoardVo.setFile_seq(HttpParameter.getInt(request, "file_seq"));
				serviceBoardVo.setUser_name(HttpParameter.getString(request, "name"));
				serviceBoardVo.setRef_seq(HttpParameter.getInt(request, "ref_seq"));
				serviceBoardVo.setReply_depth(HttpParameter.getInt(request, "reply_depth"));
				serviceBoardVo.setReply_num(HttpParameter.getInt(request, "reply_num"));
				
				serviceBoardService.modifyServiceBoardInfo(serviceBoardVo);
			}
			
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "redirect:/admin/service_list.do?serviceType="+serviceType;
	}
	
	/*
	 * service 답글 입력 폼 이동
	 * */
	@RequestMapping(value = "/admin/service_reply.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String moveServiceReply(HttpServletRequest request) {
		logger.info("/admin/community_info.do");
		if((request.getSession().getAttribute(Config.adminId)==null)||(request.getSession().getAttribute(Config.adminSeq)==null))
			return "/admin/admin_login.do";
		ServiceBoardVo serviceBoardVo = new ServiceBoardVo();
		try{
			serviceBoardVo.setRef_seq(HttpParameter.getInt(request, "service_seq"));
			int rnum =0;
			rnum = serviceBoardService.getReplyMaxNum(serviceBoardVo);
			
			serviceBoardVo.setReply_num(rnum+1);
			serviceBoardVo.setReply_depth(rnum+1);
			
			request.setAttribute("service_info", serviceBoardVo);
			request.setAttribute("category_list", productService.getProductCategoryList());
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "/admin/service_reply.do";
	}
	
	
	/*
	 * community 게시판 리스트 조회
	 * */
	@RequestMapping(value = "/admin/community_list.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getCommunityList(HttpServletRequest request) {
		logger.info("/admin/community_list.do");
		if(request.getSession().getAttribute(Config.adminId)==null)
			return "/admin/admin_login.do";
		
		if(request.getParameter("INIT")!= null){
			Util.resetSessionSearchKey(request.getSession());
		}
		
		int communityType = HttpParameter.getInt(request, "communityType");
		String searchOpt = HttpParameter.getString(request, "searchOpt");
		String searchStr = HttpParameter.getString(request, "searchStr");
		
		
		request.getSession().setAttribute(Config.searchOpt, searchOpt);
		request.getSession().setAttribute(Config.searchStr, searchStr);
		
		if(0<communityType){
			request.getSession().setAttribute(Config.communityType, communityType);
		}else{
			communityType = (Integer)request.getSession().getAttribute(Config.communityType);
		}
		
		
		CommunityBoardVo communityBoardVo = new CommunityBoardVo();
		communityBoardVo.setType(communityType);
		if(searchOpt.equals("title")){
			communityBoardVo.setTitle(searchStr);
		}else if(searchOpt.equals("name")){
			communityBoardVo.setUser_name(searchStr);
		}
		
		int pageNo = HttpParameter.getInt(request, "pageNo");
		int totCnt = communityBoardService.getCommunityBoarVotCnt(communityBoardVo);
		int startIdx = Util.getStartIdx(pageNo, Config.NUM_OF_LINE10);
		int endIdx = Config.NUM_OF_LINE10;
		
		communityBoardVo.setStartIdx(startIdx);
		communityBoardVo.setEndIdx(endIdx);
		
		request.setAttribute("community_list", communityBoardService.getCommunityBoardList(communityBoardVo));
		
		PageNavigator navigator = new PageNavigator(pageNo, totCnt, Config.NUM_OF_LINE10, Config.communityListUrl, communityType);
		request.setAttribute("pageNavigator", navigator.getHtml());
		
		return "/admin/community_list.do";
	}
	
	/*
	 * community 게시판 입력
	 * */
	@RequestMapping(value = "/admin/community_write_action.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String addCommunityInfo(HttpServletRequest request) {
		logger.info("/admin/community_write_action.do");
		if((request.getSession().getAttribute(Config.adminId)==null)||(request.getSession().getAttribute(Config.adminSeq)==null))
			return "/admin/admin_login.do";
		CommunityBoardVo communityBoardVo = new CommunityBoardVo();
		int communityType = 0;
		try{
			communityType = (Integer) request.getSession().getAttribute("communityType");
			if (request.getContentType() != null &&
					request.getContentType().toLowerCase().indexOf("multipart/form-data") > -1 ){
				Uploader up = new Uploader();
				String upUrl = "";
				String upAliasUrl = "";
				String fileExtend = "";
				boolean isSuccess = false;
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				upUrl = Config.TEMP_UPLOAD_DIR;
				upAliasUrl = Config.TEMP_ALIAS_DIR;
				up.setUploadDir(upUrl, 2);
				isSuccess = up.doUpload(request, dateFormat.format(new java.util.Date()));
				
				String aliasFileName = up.getRenames();
				fileExtend = up.extendDivision(aliasFileName, ".");
				
				String newUpUrl = Util.getUploadDirByExtend(fileExtend);
				upAliasUrl = Util.getUploadAliasByExtend(fileExtend);
				
				Util.fileMoveDir(upUrl, newUpUrl, aliasFileName, fileExtend);
				
				communityBoardVo.setUser_seq((Integer)request.getSession().getAttribute(Config.adminSeq));
				communityBoardVo.setTitle(up.getParameter("title"));
				communityBoardVo.setContent(up.getParameter("content"));
				communityBoardVo.setCount(Integer.parseInt(up.getParameter("count")));
				communityBoardVo.setType(communityType);
				communityBoardVo.setRank(Integer.parseInt(up.getParameter("rank")));
				communityBoardVo.setUse_yn(up.getParameter("use_yn"));
				communityBoardVo.setSecret_yn(up.getParameter("secret_yn"));
				communityBoardVo.setReply_yn(up.getParameter("reply_yn"));
				communityBoardVo.setPasswd(up.getParameter("passwd"));
				communityBoardVo.setUser_name(up.getParameter("name"));
				communityBoardVo.setRef_seq(Integer.parseInt(up.getParameter("ref_seq")));
				communityBoardVo.setReply_depth(Integer.parseInt(up.getParameter("reply_depth")));
				communityBoardVo.setReply_num(Integer.parseInt(up.getParameter("reply_num")));
				
				communityBoardService.addCommunityBoardInfo(communityBoardVo);
				if(isSuccess){
					FileVo fileVo = new FileVo();
					fileVo.setFile_name(aliasFileName);
					if(fileExtend.equals("zip")){
						fileVo.setFile_url(upAliasUrl+Util.removeExtend(aliasFileName, "."));
					}else{
						fileVo.setFile_url(upAliasUrl+aliasFileName);
					}
					fileVo.setFile_type(fileExtend);
					
					fileService.addFileInfo(fileVo);
					communityBoardVo.setCommunity_seq(communityBoardService.getMaxSeq());
					communityBoardVo.setFile_seq(fileService.getMaxSeq());
					if(1>communityBoardVo.getRef_seq()){
						communityBoardVo.setRef_seq(communityBoardVo.getCommunity_seq());
					}
					communityBoardService.modifyCommunityBoardInfo(communityBoardVo);
				}
			}else{
				communityBoardVo.setUser_seq((Integer)request.getSession().getAttribute(Config.adminSeq));
				communityBoardVo.setTitle(HttpParameter.getString(request,"title"));
				communityBoardVo.setContent(HttpParameter.getString(request,"content"));
				communityBoardVo.setCount(HttpParameter.getInt(request,"count"));
				communityBoardVo.setType(communityType);
				communityBoardVo.setRank(HttpParameter.getInt(request,"rank"));
				communityBoardVo.setUse_yn(HttpParameter.getString(request,"use_yn"));
				communityBoardVo.setSecret_yn(HttpParameter.getString(request,"secret_yn"));
				communityBoardVo.setReply_yn(HttpParameter.getString(request,"reply_yn"));
				communityBoardVo.setPasswd(HttpParameter.getString(request,"passwd"));
				communityBoardVo.setUser_name(HttpParameter.getString(request, "name"));
				communityBoardVo.setRef_seq(HttpParameter.getInt(request, "ref_seq"));
				communityBoardVo.setReply_depth(HttpParameter.getInt(request, "reply_depth"));
				communityBoardVo.setReply_num(HttpParameter.getInt(request, "reply_num"));
				
				communityBoardService.addCommunityBoardInfo(communityBoardVo);
				
				if(1>communityBoardVo.getRef_seq()){
					communityBoardVo.setCommunity_seq(communityBoardService.getMaxSeq());
					communityBoardVo.setRef_seq(communityBoardVo.getCommunity_seq());
					communityBoardService.modifyCommunityBoardInfo(communityBoardVo);
				}
			}
			
			
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "redirect:/admin/community_list.do?communityType="+communityType;
	}
	
	/*
	 * community 게시글 상세 조회
	 * */
	@RequestMapping(value = "/admin/community_info.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getCommunityInfo(HttpServletRequest request) {
		logger.info("/admin/community_info.do");
		if((request.getSession().getAttribute(Config.adminId)==null)||(request.getSession().getAttribute(Config.adminSeq)==null))
			return "/admin/admin_login.do";
		CommunityBoardVo communityBoardVo = new CommunityBoardVo();
		try{
			communityBoardVo.setCommunity_seq(HttpParameter.getInt(request, "community_seq"));
			
			communityBoardVo = communityBoardService.getCommunityBoardInfo(communityBoardVo);
			FileVo fileVo = new FileVo();
			fileVo.setFile_seq(communityBoardVo.getFile_seq());
			request.setAttribute("community_info", communityBoardVo);
			//request.setAttribute("account_info", accountService.getAccountInfoBySeq(Integer.toString(communityBoardVo.getUser_seq())));
			request.setAttribute("file_info", fileService.getFileInfo(fileVo));
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "/admin/community_info.do";
	}
	
	/*
	 * community 게시판 수정
	 * */
	@RequestMapping(value = "/admin/community_modify_action.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyCommunityInfo(HttpServletRequest request) {
		logger.info("/admin/community_modify_action.do");
		if((request.getSession().getAttribute(Config.adminId)==null)||(request.getSession().getAttribute(Config.adminSeq)==null))
			return "/admin/admin_login.do";
		CommunityBoardVo communityBoardVo = new CommunityBoardVo();
		int communityType = 0;
		try{
			communityType = (Integer) request.getSession().getAttribute("communityType");
			if (request.getContentType() != null &&
					request.getContentType().toLowerCase().indexOf("multipart/form-data") > -1 ){
				
				Uploader up = new Uploader();
				
				String upUrl = "";
				String upAliasUrl = "";
				String fileExtend = "";
				boolean isSuccess = false;
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				upUrl = Config.TEMP_UPLOAD_DIR;
				upAliasUrl = Config.TEMP_ALIAS_DIR;
				up.setUploadDir(upUrl, 2);
				isSuccess = up.doUpload(request, dateFormat.format(new java.util.Date()));
				
				String aliasFileName = up.getRenames();
				fileExtend = up.extendDivision(aliasFileName, ".");
				
				String newUpUrl = Util.getUploadDirByExtend(fileExtend);
				upAliasUrl = Util.getUploadAliasByExtend(fileExtend);
				
				Util.fileMoveDir(upUrl, newUpUrl, aliasFileName, fileExtend);
				
				communityBoardVo.setCommunity_seq(Integer.parseInt(up.getParameter("community_seq")));
				communityBoardVo.setUser_seq((Integer)request.getSession().getAttribute(Config.adminSeq));
				communityBoardVo.setTitle(up.getParameter("title"));
				communityBoardVo.setContent(up.getParameter("content"));
				communityBoardVo.setCount(Integer.parseInt(up.getParameter("count")));
				communityBoardVo.setType(communityType);
				communityBoardVo.setRank(Integer.parseInt(up.getParameter("rank")));
				communityBoardVo.setUse_yn(up.getParameter("use_yn"));
				communityBoardVo.setSecret_yn(up.getParameter("secret_yn"));
				communityBoardVo.setReply_yn(up.getParameter("reply_yn"));
				communityBoardVo.setPasswd(up.getParameter("passwd"));
				communityBoardVo.setUser_name(up.getParameter("name"));
				communityBoardVo.setRef_seq(Integer.parseInt(up.getParameter("ref_seq")));
				communityBoardVo.setReply_depth(Integer.parseInt(up.getParameter("reply_depth")));
				communityBoardVo.setReply_num(Integer.parseInt(up.getParameter("reply_num")));
				
				communityBoardService.modifyCommunityBoardInfo(communityBoardVo);
				if(isSuccess){
					FileVo fileVo = new FileVo();
					fileVo.setFile_name(aliasFileName);
					if(fileExtend.equals("zip")){
						fileVo.setFile_url(upAliasUrl+Util.removeExtend(aliasFileName, "."));
					}else{
						fileVo.setFile_url(upAliasUrl+aliasFileName);
					}
					fileVo.setFile_type(fileExtend);
					
					fileService.addFileInfo(fileVo);
					communityBoardVo.setFile_seq(fileService.getMaxSeq());
					communityBoardService.modifyCommunityBoardInfo(communityBoardVo);
				}
				
			}else{
				communityBoardVo.setCommunity_seq(HttpParameter.getInt(request, "community_seq"));
				communityBoardVo.setUser_seq((Integer)request.getSession().getAttribute(Config.adminSeq));
				communityBoardVo.setTitle(HttpParameter.getString(request, "title"));
				communityBoardVo.setContent(HttpParameter.getString(request, "content"));
				communityBoardVo.setCount(HttpParameter.getInt(request, "count"));
				communityBoardVo.setType(communityType);
				communityBoardVo.setRank(HttpParameter.getInt(request, "rank"));
				communityBoardVo.setUse_yn(HttpParameter.getString(request, "use_yn"));
				communityBoardVo.setSecret_yn(HttpParameter.getString(request, "secret_yn"));
				communityBoardVo.setReply_yn(HttpParameter.getString(request, "reply_yn"));
				communityBoardVo.setPasswd(HttpParameter.getString(request, "passwd"));
				communityBoardVo.setFile_seq(HttpParameter.getInt(request, "file_seq"));
				communityBoardVo.setUser_name(HttpParameter.getString(request, "name"));
				communityBoardVo.setRef_seq(HttpParameter.getInt(request, "ref_seq"));
				communityBoardVo.setReply_depth(HttpParameter.getInt(request, "reply_depth"));
				communityBoardVo.setReply_num(HttpParameter.getInt(request, "reply_num"));
				
				communityBoardService.modifyCommunityBoardInfo(communityBoardVo);
			}
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "redirect:/admin/community_list.do?communityType="+communityType;
	}
	
	/*
	 * community 답글 입력 폼 이동
	 * */
	@RequestMapping(value = "/admin/community_reply.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String moveCommunityReply(HttpServletRequest request) {
		logger.info("/admin/community_info.do");
		if((request.getSession().getAttribute(Config.adminId)==null)||(request.getSession().getAttribute(Config.adminSeq)==null))
			return "/admin/admin_login.do";
		CommunityBoardVo communityBoardVo = new CommunityBoardVo();
		try{
			communityBoardVo.setRef_seq(HttpParameter.getInt(request, "community_seq"));
			int rnum =0;
			rnum = communityBoardService.getReplyMaxNum(communityBoardVo);
			
			communityBoardVo.setReply_num(rnum+1);
			communityBoardVo.setReply_depth(rnum+1);
			
			request.setAttribute("community_info", communityBoardVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "/admin/community_reply.do";
	}
	
	/*
	 * 제품군 게시판 리스트 조회
	 * */
	@RequestMapping(value = "/admin/product_category_list.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getProductCategoryList(HttpServletRequest request) {
		logger.info("/admin/product_category_list.do");
		if(request.getSession().getAttribute(Config.adminId)==null)
			return "/admin/admin_login.do";
		
		if(request.getParameter("INIT")!= null){
			Util.resetSessionSearchKey(request.getSession());
		}
		
		request.setAttribute("category_list", productService.getProductCategoryList());
		
		return "/admin/product_category_list.do";
	}
	
	/*
	 * 제품군 게시판 입력
	 * */
	@RequestMapping(value = "/admin/product_category_write_action.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String addProductCategoryInfo(HttpServletRequest request) {
		logger.info("/admin/product_category_write_action.do");
		if((request.getSession().getAttribute(Config.adminId)==null)||(request.getSession().getAttribute(Config.adminSeq)==null))
			return "/admin/admin_login.do";
		ProductCategoryVo productCategoryVo = new ProductCategoryVo();
		try{
			productCategoryVo.setCategory_name(HttpParameter.getString(request, "category_name"));
			productCategoryVo.setCategory_info(HttpParameter.getString(request, "category_info"));
			productCategoryVo.setUse_yn(HttpParameter.getString(request, "use_yn"));
			
			productService.addProductCategoryInfo(productCategoryVo);
			
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "redirect:/admin/product_category_list.do";
	}
	
	/*
	 * 제품군 게시글 상세 조회
	 * */
	@RequestMapping(value = "/admin/product_category_info.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getProductCategoryInfo(HttpServletRequest request) {
		logger.info("/admin/product_category_info.do");
		if((request.getSession().getAttribute(Config.adminId)==null)||(request.getSession().getAttribute(Config.adminSeq)==null))
			return "/admin/admin_login.do";
		ProductCategoryVo productCategoryVo = new ProductCategoryVo();
		try{
			
			productCategoryVo.setCategory_seq(HttpParameter.getInt(request, "category_seq"));
			
			productCategoryVo = productService.getProductCategoryInfo(productCategoryVo);
			request.setAttribute("category_info", productCategoryVo);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "/admin/product_category_info.do";
	}
	
	/*
	 * 제품군 게시판 수정
	 * */
	@RequestMapping(value = "/admin/product_category_modify_action.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyProductCategoryInfo(HttpServletRequest request) {
		logger.info("/admin/product_category_modify_action.do");
		if((request.getSession().getAttribute(Config.adminId)==null)||(request.getSession().getAttribute(Config.adminSeq)==null))
			return "/admin/admin_login.do";
		ProductCategoryVo productCategoryVo = new ProductCategoryVo();
		try{
			productCategoryVo.setCategory_seq(HttpParameter.getInt(request, "category_seq"));
			productCategoryVo.setCategory_name(HttpParameter.getString(request, "category_name"));
			productCategoryVo.setCategory_info(HttpParameter.getString(request, "category_info"));
			productCategoryVo.setUse_yn(HttpParameter.getString(request, "use_yn"));
			
			productService.modifyProductCategoryInfo(productCategoryVo);
			
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "redirect:/admin/product_category_list.do";
	}
	
	/*
	 * 제품 게시판 리스트 조회
	 * */
	@RequestMapping(value = "/admin/product_list.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getProductList(HttpServletRequest request) {
		logger.info("/admin/product_list.do");
		if(request.getSession().getAttribute(Config.adminId)==null)
			return "/admin/admin_login.do";
		
		if(request.getParameter("INIT")!= null){
			Util.resetSessionSearchKey(request.getSession());
		}
		
		int category_seq = HttpParameter.getInt(request, "category_seq");
		request.getSession().setAttribute(Config.searchCategorySeq, category_seq);
		
		ProductVo productVo = new ProductVo();
		
		productVo.setCategory_seq(category_seq);
		
		request.setAttribute("product_list", productService.getProductList(productVo));
		request.setAttribute("category_list", productService.getProductCategoryList());
		
		return "/admin/product_list.do";
	}
	
	/*
	 * 제품 리스트 json 형태로 반환
	 * */
	@RequestMapping(value = "/admin/productListJson.do", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultVo<?> getProductListJson(@RequestBody final ProductVo productVo) {
		logger.info("/admin/productListJson");
		ResultVo<List<ProductVo>> result = new ResultVo<List<ProductVo>>();
		try{
			result.setValue(productService.getProductList(productVo));
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		
		return result;
	}
	
	/*
	 * 제품 입력 화면 이동
	 * */
	@RequestMapping(value = "/admin/product_write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String doProductWrite(HttpServletRequest request) {
		logger.info("/admin/product_write.do");
		if(request.getSession().getAttribute(Config.adminId)==null)
			return "/admin/admin_login.do";
		
		request.setAttribute("category_list", productService.getProductCategoryList());
		
		return "/admin/product_write.do";
	}
	
	/*
	 * 제품 게시판 입력
	 * */
	@RequestMapping(value = "/admin/product_write_action.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String addProductInfo(HttpServletRequest request) {
		logger.info("/admin/product_write_action.do");
		if((request.getSession().getAttribute(Config.adminId)==null)||(request.getSession().getAttribute(Config.adminSeq)==null))
			return "/admin/admin_login.do";
		ProductVo productVo = new ProductVo();
		ProductCategoryVo productCategoryVo = new ProductCategoryVo();
		int category_seq;
		try{
			
			category_seq = HttpParameter.getInt(request, "category_seq");
			
			productCategoryVo.setCategory_seq(category_seq);
			productCategoryVo = productService.getProductCategoryInfo(productCategoryVo);
			
			productVo.setCategory_seq(category_seq);
			productVo.setCategory_name(productCategoryVo.getCategory_name());
			productVo.setProduct_name(HttpParameter.getString(request, "product_name"));
			productVo.setProduct_info(HttpParameter.getString(request, "product_info"));
			productVo.setUse_yn(HttpParameter.getString(request, "use_yn"));
			
			productService.addProductInfo(productVo);
			
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "redirect:/admin/product_list.do";
	}
	
	/*
	 * 제품 게시글 상세 조회
	 * */
	@RequestMapping(value = "/admin/product_info.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getProductInfo(HttpServletRequest request) {
		logger.info("/admin/product_info.do");
		if((request.getSession().getAttribute(Config.adminId)==null)||(request.getSession().getAttribute(Config.adminSeq)==null))
			return "/admin/admin_login.do";
		ProductVo productVo = new ProductVo();
		try{
			productVo.setProduct_seq(HttpParameter.getInt(request, "product_seq"));
			
			productVo = productService.getProductInfo(productVo);
			request.setAttribute("product_info", productVo);
			request.setAttribute("category_list", productService.getProductCategoryList());
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "/admin/product_info.do";
	}
	
	/*
	 * 제품 게시판 수정
	 * */
	@RequestMapping(value = "/admin/product_modify_action.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyProductInfo(HttpServletRequest request) {
		logger.info("/admin/product_modify_action.do");
		if((request.getSession().getAttribute(Config.adminId)==null)||(request.getSession().getAttribute(Config.adminSeq)==null))
			return "/admin/admin_login.do";
		ProductVo productVo = new ProductVo();
		ProductCategoryVo productCategoryVo = new ProductCategoryVo();
		int category_seq;
		try{
			
			category_seq = HttpParameter.getInt(request, "category_seq");
			
			productCategoryVo.setCategory_seq(category_seq);
			productCategoryVo = productService.getProductCategoryInfo(productCategoryVo);
			
			productVo.setProduct_seq(HttpParameter.getInt(request, "product_seq"));
			productVo.setCategory_seq(category_seq);
			productVo.setCategory_name(productCategoryVo.getCategory_name());
			productVo.setProduct_name(HttpParameter.getString(request, "product_name"));
			productVo.setProduct_info(HttpParameter.getString(request, "product_info"));
			productVo.setUse_yn(HttpParameter.getString(request, "use_yn"));
			
			productService.modifyProductInfo(productVo);
			
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "redirect:/admin/product_list.do";
	}
}
