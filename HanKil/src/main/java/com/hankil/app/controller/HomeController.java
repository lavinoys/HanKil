package com.hankil.app.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hankil.app.common.Config;
import com.hankil.app.common.HttpParameter;
import com.hankil.app.common.PageNavigator;
import com.hankil.app.common.Util;
import com.hankil.app.mybatis.bean.CommunityBoardVo;
import com.hankil.app.mybatis.bean.FileVo;
import com.hankil.app.mybatis.bean.ServiceBoardVo;
import com.hankil.app.service.CommunityBoardService;
import com.hankil.app.service.FileService;
import com.hankil.app.service.ProductService;
import com.hankil.app.service.ServiceBoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ServiceBoardService serviceBoardService;
	@Autowired
	private CommunityBoardService communityBoardService;
	@Autowired
	private ProductService productService;
	@Autowired
	private FileService fileService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/web/{articleId}.do", method = RequestMethod.GET)
	public String convertPage(@PathVariable(value = "articleId") String articleId, HttpSession session) {
		logger.info("/web/"+articleId+".do");
		return "/web/"+articleId+".do";
	}
	
	@RequestMapping(value = "/web/{path1}/{path2}/{articleId}.do", method = RequestMethod.GET)
	public String convertPage2(@PathVariable(value = "path1") String path1,
								@PathVariable(value = "path2") String path2,
								@PathVariable(value = "articleId") String articleId, HttpSession session) {
		logger.info("/web/"+path1+"/"+path2+"/"+articleId+".do");
		return "/web/"+path1+"/"+path2+"/"+articleId+".do";
	}
	
	@RequestMapping(value = "/web/main.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getMainShortcutList(HttpServletRequest request) {
		logger.info("/web/main.do");
		
		request.setAttribute("news_list", communityBoardService.getCommunityShortcutList(Config.board_news_Code));
		request.setAttribute("notice_list", communityBoardService.getCommunityShortcutList(Config.board_community_Code));
		request.setAttribute("event_list", communityBoardService.getCommunityShortcutList(Config.board_events_Code));
		
		return "/web/main.do";
	}
	
	
	/*
	 * A/S안내 게시판 리스트 조회
	 * */
	@RequestMapping(value = "/web/partial/support/asguide.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getAsguideList(HttpServletRequest request) {
		logger.info("/web/partial/support/asguide.do");
		
		int serviceType = Config.board_data_Code;
		
		ServiceBoardVo serviceBoardVo = new ServiceBoardVo();
		serviceBoardVo.setType(serviceType);
		
		int pageNo = HttpParameter.getInt(request, "pageNo");
		int totCnt = serviceBoardService.getServiceBoarVotCnt(serviceBoardVo);
		int startIdx = Util.getStartIdx(pageNo, Config.NUM_OF_LINE10);
		int endIdx = Util.getEndIdx(startIdx, Config.NUM_OF_LINE10, totCnt);
		
		serviceBoardVo.setStartIdx(startIdx);
		serviceBoardVo.setEndIdx(endIdx);
		
		request.setAttribute("service_list", serviceBoardService.getServiceBoardList(serviceBoardVo));
		
		PageNavigator navigator = new PageNavigator(pageNo, totCnt, Config.NUM_OF_LINE10, "../support/asguide.do?", serviceType);
		request.setAttribute("pageNavigator", navigator.getHtml());
		
		return "/web/partial/support/asguide.do";
	}
	
	/*
	 * FAQ 게시판 리스트 조회
	 * */
	@RequestMapping(value = "/web/partial/support/faq.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getFaqList(HttpServletRequest request) {
		logger.info("/web/partial/support/faq.do");
		
		int communityType = Config.board_FAQ_Code;
		
		ServiceBoardVo serviceBoardVo = new ServiceBoardVo();
		serviceBoardVo.setType(communityType);
		
		int pageNo = HttpParameter.getInt(request, "pageNo");
		int totCnt = serviceBoardService.getServiceBoarVotCnt(serviceBoardVo);
		int startIdx = Util.getStartIdx(pageNo, Config.NUM_OF_LINE10);
		int endIdx = Util.getEndIdx(startIdx, Config.NUM_OF_LINE10, totCnt);
		
		serviceBoardVo.setStartIdx(startIdx);
		serviceBoardVo.setEndIdx(endIdx);
		
		request.setAttribute("service_list", serviceBoardService.getServiceBoardList(serviceBoardVo));
		
		PageNavigator navigator = new PageNavigator(pageNo, totCnt, Config.NUM_OF_LINE10, "../support/faq.do?", communityType);
		request.setAttribute("pageNavigator", navigator.getHtml());
		
		return "/web/partial/support/faq.do";
	}
	
	/*
	 * 질문/답변 게시판 리스트 조회
	 * */
	@RequestMapping(value = "/web/partial/support/qana.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getQanaList(HttpServletRequest request) {
		logger.info("/web/partial/support/qana.do");
		
		int communityType = Config.board_QNA_Code;
		
		ServiceBoardVo serviceBoardVo = new ServiceBoardVo();
		serviceBoardVo.setType(communityType);
		
		int pageNo = HttpParameter.getInt(request, "pageNo");
		int totCnt = serviceBoardService.getServiceBoarVotCnt(serviceBoardVo);
		int startIdx = Util.getStartIdx(pageNo, Config.NUM_OF_LINE10);
		int endIdx = Util.getEndIdx(startIdx, Config.NUM_OF_LINE10, totCnt);
		
		serviceBoardVo.setStartIdx(startIdx);
		serviceBoardVo.setEndIdx(endIdx);
		
		request.setAttribute("service_list", serviceBoardService.getServiceBoardList(serviceBoardVo));
		
		PageNavigator navigator = new PageNavigator(pageNo, totCnt, Config.NUM_OF_LINE10, "../support/qana.do?", communityType);
		request.setAttribute("pageNavigator", navigator.getHtml());
		
		return "/web/partial/support/qana.do";
	}
	
	/*
	 * 한길뉴스 게시판 리스트 조회
	 * */
	@RequestMapping(value = "/web/partial/community/hangilnews.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getHangilnewsList(HttpServletRequest request) {
		logger.info("/web/partial/community/hangilnews.do");
		
		int communityType = Config.board_news_Code;
		
		CommunityBoardVo communityBoardVo = new CommunityBoardVo();
		communityBoardVo.setType(communityType);
		
		int pageNo = HttpParameter.getInt(request, "pageNo");
		int totCnt = communityBoardService.getCommunityBoarVotCnt(communityBoardVo);
		int startIdx = Util.getStartIdx(pageNo, Config.NUM_OF_LINE10);
		int endIdx = Util.getEndIdx(startIdx, Config.NUM_OF_LINE10, totCnt);
		
		communityBoardVo.setStartIdx(startIdx);
		communityBoardVo.setEndIdx(endIdx);
		
		request.setAttribute("community_list", communityBoardService.getCommunityBoardList(communityBoardVo));
		
		PageNavigator navigator = new PageNavigator(pageNo, totCnt, Config.NUM_OF_LINE10, "../community/hangilnews.do?", communityType);
		request.setAttribute("pageNavigator", navigator.getHtml());
		
		return "/web/partial/community/hangilnews.do";
	}
	
	/*
	 * 한길이벤트 게시판 리스트 조회
	 * */
	@RequestMapping(value = "/web/partial/community/hangilevent.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getHangileventList(HttpServletRequest request) {
		logger.info("/web/partial/community/hangilevent.do");
		
		int communityType = Config.board_events_Code;
		
		CommunityBoardVo communityBoardVo = new CommunityBoardVo();
		communityBoardVo.setType(communityType);
		
		int pageNo = HttpParameter.getInt(request, "pageNo");
		int totCnt = communityBoardService.getCommunityBoarVotCnt(communityBoardVo);
		int startIdx = Util.getStartIdx(pageNo, Config.NUM_OF_LINE10);
		int endIdx = Util.getEndIdx(startIdx, Config.NUM_OF_LINE10, totCnt);
		
		communityBoardVo.setStartIdx(startIdx);
		communityBoardVo.setEndIdx(endIdx);
		
		request.setAttribute("community_list", communityBoardService.getCommunityBoardList(communityBoardVo));
		
		PageNavigator navigator = new PageNavigator(pageNo, totCnt, Config.NUM_OF_LINE10, "../community/hangilevent.do?", communityType);
		request.setAttribute("pageNavigator", navigator.getHtml());
		
		return "/web/partial/community/hangilevent.do";
	}
	
	/*
	 * 참여 게시판 리스트 조회
	 * */
	@RequestMapping(value = "/web/partial/community/notice.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getNoticeList(HttpServletRequest request) {
		logger.info("web/partial/community/notice.do");
		
		int communityType = Config.board_community_Code;
		
		CommunityBoardVo communityBoardVo = new CommunityBoardVo();
		communityBoardVo.setType(communityType);
		
		int pageNo = HttpParameter.getInt(request, "pageNo");
		int totCnt = communityBoardService.getCommunityBoarVotCnt(communityBoardVo);
		int startIdx = Util.getStartIdx(pageNo, Config.NUM_OF_LINE10);
		int endIdx = Util.getEndIdx(startIdx, Config.NUM_OF_LINE10, totCnt);
		
		communityBoardVo.setStartIdx(startIdx);
		communityBoardVo.setEndIdx(endIdx);
		
		request.setAttribute("community_list", communityBoardService.getCommunityBoardList(communityBoardVo));
		
		PageNavigator navigator = new PageNavigator(pageNo, totCnt, Config.NUM_OF_LINE10, "../community/notice.do?", communityType);
		request.setAttribute("pageNavigator", navigator.getHtml());
		
		return "/web/partial/community/notice.do";
	}
	
	/*
	 * 커뮤니티 게시글 상세 조회
	 * */
	@RequestMapping(value = "/web/partial/community/communityInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getCommunityInfo(HttpServletRequest request) {
		logger.info("/web/partial/community/communityInfo.do");
		
		CommunityBoardVo communityBoardVo = new CommunityBoardVo();
		try{
			communityBoardVo.setCommunity_seq(HttpParameter.getInt(request, "community_seq"));
			
			communityBoardVo = communityBoardService.getCommunityBoardInfo(communityBoardVo);
			//communityBoardVo.setContent(communityBoardVo.getContent().replaceAll("\n", "<br>"));
			FileVo fileVo = new FileVo();
			fileVo.setFile_seq(communityBoardVo.getFile_seq());
			request.setAttribute("community_info", communityBoardVo);
			//request.setAttribute("account_info", accountService.getAccountInfoBySeq(Integer.toString(communityBoardVo.getUser_seq())));
			request.setAttribute("file_info", fileService.getFileInfo(fileVo));
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		
		return "/web/partial/community/communityInfo.do";
	}
	
	/*
	 * community 게시판 입력
	 * */
	@RequestMapping(value = "/web/partial/community/communityWrite_action.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String addCommunityInfo(HttpServletRequest request) {
		logger.info("/web/partial/community/communityWrite_action.do");
		CommunityBoardVo communityBoardVo = new CommunityBoardVo();
		try{
			communityBoardVo.setTitle(HttpParameter.getString(request,"title"));
			communityBoardVo.setContent(HttpParameter.getString(request,"content"));
			communityBoardVo.setCount(0);
			communityBoardVo.setType(Config.board_community_Code);
			communityBoardVo.setRank(5);
			communityBoardVo.setUse_yn("y");
			communityBoardVo.setSecret_yn("n");
			communityBoardVo.setReply_yn("n");
			communityBoardVo.setPasswd("");
			communityBoardVo.setUser_name(HttpParameter.getString(request, "name"));
			
			communityBoardService.addCommunityBoardInfo(communityBoardVo);
			
			if(1>communityBoardVo.getRef_seq()){
				communityBoardVo.setCommunity_seq(communityBoardService.getMaxSeq());
				communityBoardVo.setRef_seq(communityBoardVo.getCommunity_seq());
				communityBoardService.modifyCommunityBoardInfo(communityBoardVo);
			}
			
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return "redirect:/web/partial/community/notice.do";
	}
}
