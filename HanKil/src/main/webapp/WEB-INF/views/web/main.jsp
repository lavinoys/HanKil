<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8"/>
	<title>한길 홈페이지</title>
	<link rel="stylesheet" href="css/common.css"/>
	<link rel="stylesheet" href="css/hangil.css"/>
	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
	<![endif]-->
	<!--[if lt IE 7]>
<script src="js/IE7.js"></script>
<![endif]-->
</head>
<body>
	<div class="wrap">
		<header>
			<div>
				<h1><a href="./main.do"><img src="images/logo.png" alt="로고"/><span class="hide">회사 로고</span></a></h1>
				<nav class="gnb">
					<ul>
						<li class="company"><a href="partial/company/greeting.do"><span class="hide">회사소개</span></a></li>
						<li class="product"><a href="partial/product/storage.do"><span class="hide">제품소개</span></a></li>
						<li class="solution"><a href="partial/solution/service.do"><span class="hide">기업솔루션</span></a></li>
						<li class="result"><a href="partial/result/result.do"><span class="hide">납품실적</span></a></li>
						<li class="support"><a href="partial/support/serviceguide.do"><span class="hide">고객지원</span></a></li>
						<li class="community"><a href="partial/community/hangilnews.do"><span class="hide">커뮤니티</span></a></li>
					</ul>
				</nav>
			</div>
		</header>
		<div class="report_bx">
			<div class="news">
				<a href="partial/community/hangilnews.do"><span class="hide">NEWS</span></a>
				<c:choose>
					<c:when test="${empty requestScope.news_list}">
						<p>데이터가 없습니다.</p>
					</c:when>
					<c:otherwise>
						<ul>
						<c:forEach begin="0" end="${fn:length(requestScope.news_list)}" items="${requestScope.news_list}" var="list">
							<li><a href="#">${list.title}</a><span>${list.update_date}</span></li>
						</c:forEach>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="notice">
				<a href="partial/community/notice.do"><span class="hide">NOTICE</span></a>
				<c:choose>
					<c:when test="${empty requestScope.notice_list}">
						<p>데이터가 없습니다.</p>
					</c:when>
					<c:otherwise>
						<ul>
						<c:forEach begin="0" end="${fn:length(requestScope.notice_list)}" items="${requestScope.notice_list}" var="list">
							<li><a href="#">${list.title}</a><span>${list.update_date}</span></li>
						</c:forEach>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="event">
				<a href="partial/community/hangilevent.do"><span class="hide">EVENT</span></a>
				<c:choose>
					<c:when test="${empty requestScope.event_list}">
						<p>데이터가 없습니다.</p>
					</c:when>
					<c:otherwise>
						<ul>
						<c:forEach begin="0" end="${fn:length(requestScope.event_list)}" items="${requestScope.event_list}" var="list">
							<li><a href="#">${list.title}</a><span>${list.update_date}</span></li>
						</c:forEach>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="container">
			<div class="contents">
				<section class="flash"><!-- flash -->
					<video controls="controls" width="480" height="300">
						<source src="images/video01.mp4" type="video/mp4" />
						<source src="images/video01.webm" type="video/webm" />
					</video>
				</section>
				<section class="video"><!-- <video> -->
					<video controls="controls" width="480" height="300">
						<source src="images/video03.mp4" type="video/mp4" />
						<source src="images/video03.webm" type="video/webm" />
					</video>
				</section>
				<section class="ebiz"><a href="#"><!-- E-Biz 사업부 --></a></section>
				<section class="security"><a href="#"><!-- 보안 사업부 --></a></section>
				<section class="inpra"><a href="#"><!-- IT인프라 사업부 --></a></section>
			</div><!-- contents -->
		</div>
		<div class="sitemap">
			<ul>
				<li class="company">회사소개
					<ul>
						<li><a href="partial/company/greeting.do">인사말&#47;CI</a></li>
						<li><a href="partial/company/greeting.do">연혁</a></li>
						<!--li><a href="partial/company/greeting.do">조직구성도</a></li-->
						<li><a href="partial/company/greeting.do">오시는 길</a></li>
					</ul>
				</li>
				<li class="product">제품소개
					<ul>
						<li><a href="partial/product/storage.do">서버&#47;스토리지</a></li>
						<li><a href="partial/product/storage.do">중고 서버&#47;부품</a></li>
						<li><a href="partial/product/storage.do">장비임대</a></li>
						<li><a href="partial/product/storage.do">Degausser</a></li>
						<li><a href="partial/product/storage.do">Destoryer</a></li>
						<li><a href="partial/product/storage.do">Baleno POS</a></li>
						<li><a href="partial/product/storage.do">Baleno Digital Signage</a></li>
						<li><a href="partial/product/storage.do">Baleno KIOSK</a></li>
						<li><a href="partial/product/storage.do">LED Vision</a></li>
					</ul>
				</li>
				<li class="solution">기업솔루션
					<ul>
						<li><a href="partial/solution/service.do">서비스</a></li>
						<li><a href="partial/solution/service.do">보안</a></li>
						<li><a href="partial/solution/service.do">통합유지보수</a></li>
						<li><a href="partial/solution/service.do">구축 및 Relocation</a></li>
						<li><a href="partial/solution/service.do">데이터센터</a></li>
						<li><a href="partial/solution/service.do">Digital Signage</a></li>
						<!--li><a href="partial/solution/service.do">Baleno</a></li-->
						<!--li><a href="partial/solution/solutionpos.do">Pos</a></li-->
						<!--li><a href="partial/solution/service.do">사이니즈</a></li-->
					</ul>
				</li>
				<li class="result">납품실적
					<ul>
						<li><a href="partial/result/result.do">납품실적</a></li>
					</ul>
				</li>
				<li class="support">고객지원
					<ul>
						<li><a href="partial/support/serviceguide.do">서비스안내</a></li>
						<li><a href="partial/support/serviceguide.do">A&#47;S안내</a></li>
						<li><a href="partial/support/serviceguide.do">FAQ</a></li>
						<li><a href="partial/support/serviceguide.do">질문&#47;답변</a></li>
						<li><a href="partial/support/serviceguide.do">자료실</a></li>
					</ul>
				</li>
				<li class="community">커뮤니티
					<ul>
						<li><a href="partial/community/hangilnews.do">한길뉴스</a></li>
						<li><a href="partial/community/hangilevent.do">한길이벤트</a></li>
						<li><a href="partial/community/notice.do">참여게시판</a></li>
					</ul>
				</li>
			</ul>
		</div><!-- sitemap -->
		<footer>
			<div>
			<address>
				<span>(주)한길더블류아이티</span>
				<span>대표자 : 선우영</span>
				<span>Tel : 031-740-9393</span>
				<span>Fax : 031-740-9188</span>
				<span class="email">E-mail : webma@hankilwit.co.kr</span><br/>
				<span>사업자등록번호 : 106-86-65514</span>
				<span class="addr">경기도 성남시 중원구 둔촌대로 449 중앙인더스피아 403호</span>
			</address>
			<p class="copyright">Copyright (c) 2014 HANKILWIT. All rights Reserved.</p>
			</div>
		</footer>
	</div><!-- wrap -->
</body>
</html>