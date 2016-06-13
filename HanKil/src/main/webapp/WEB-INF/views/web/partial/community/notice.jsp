<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8"/>
	<title>참여게시판</title>
	<link rel="stylesheet" href="../../css/common.css"/>
	<link rel="stylesheet" href="../../css/hangil.css"/>
	<!--[if lt IE 9]>
		<script src="../../js/html5shiv.js"></script>
	<![endif]-->
	<style>
		.detail02 tr th:after{content:'|';color:#ddd;padding-left:10px;}
		.button {position:absolute;right:0;border: 1px solid #0a3c59;border-radius:3px;background: #666;padding: 3.5px 12px;box-shadow: rgba(255,255,255,0.4) 0 0px 0, inset rgba(255,255,255,0.4) 0 1px 0;text-shadow: #7ea4bd 0 1px 0;color: #ffffff;font-size: 16px;text-decoration: none;vertical-align: middle;}
		.button:hover {border: 1px solid #0a3c59;text-shadow: #1e4158 0 1px 0;background: #888;color: #ffffff;}
		.button:active {text-shadow: #1e4158 0 1px 0;border: 1px solid #0a3c59;background: #444;color: #fff;}
	</style>
	<script type="text/javascript">
		window.onpageshow = function(event) {
		    if (event.persisted) {
		        window.location.reload(); 
		    }
		};
	</script>
</head>
<body>
	<div class="wrap">
		<header>
			<div>
				<h1><a href="../../main.do"><img src="../../images/logo.png" alt="로고"/><!-- HANKILWIT --></a></h1>
				<nav class="gnb">
					<ul>
						<li class="company"><a href="../company/greeting.do"><span class="hide">회사소개</span></a></li>
						<li class="product"><a href="../product/storage.do"><span class="hide">제품소개</span></a></li>
						<li class="solution"><a href="../solution/service.do"><span class="hide">기업솔루션</span></a></li>
						<li class="result"><a href="../result/result.do"><span class="hide">납품실적</span></a></li>
						<li class="support"><a href="../support/serviceguide.do"><span class="hide">고객지원</span></a></li>
						<li class="community"><a class="view" href="../community/hangilnews.do"><span class="hide">커뮤니티</span></a></li>
					</ul>
				</nav>
			</div>
		</header>
		<div class="depth_menu">
			<div>
				<h2>커뮤니티</h2>
				<ul class="depth06">
					<li class="hangilnews"><a href="../community/hangilnews.do"><span class="hide">한길뉴스</span></a></li>
					<li class="hangilevent"><a href="../community/hangilevent.do"><span class="hide">한길이벤트</span></a></li>
					<li class="notice"><a class="view" href="../community/notice.do"><span class="hide">참여게시판</span></a></li>
				</ul>
			</div>
		</div>
		<div class="contents" style="height: 470px;">
			<!-- 참여게시판 page -->
			<table class="type02">
				<c:choose>
					<c:when test="${empty requestScope.community_list}">
						<thead>
						<tr>
							<th scope="cols">번호</th>
							<th scope="cols">제목</th>
							<th scope="cols">작성자</th>
							<th scope="cols">작성일</th>
							<th scope="cols">조회</th>
						</tr>
						</thead>
						<tbody>
						<tr>
			               <td colspan="5" style="text-align: center;width:1024px;">조회할 자료가 없습니다.</td>
			            </tr>
						</tbody>
					</c:when>
					<c:otherwise>
						<thead>
						<tr>
							<th scope="cols">번호</th>
							<th scope="cols">제목</th>
							<th scope="cols">작성자</th>
							<th scope="cols">작성일</th>
							<th scope="cols">조회</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach begin="0" end="${fn:length(requestScope.community_list)}" items="${requestScope.community_list}" var="list">
							<tr>
								<th scope="row"><a href="communityInfo.do?community_seq=${list.community_seq}">${list.community_seq}</a></th>
								<td class="title"><a href="communityInfo.do?community_seq=${list.community_seq}">${list.title}</a></td>
								<td>${list.user_name}</td>
								<td>${list.update_date}</td>
								<td>${list.count}</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="5" style="text-align: center;">${pageNavigator}</td>
						</tr>
						</tbody>
					</c:otherwise>
				</c:choose>
			</table>
			<a href='communityWrite.do' class='button'>입력</a>
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
		</footer>
	</div><!-- wrap -->
</body>
</html>