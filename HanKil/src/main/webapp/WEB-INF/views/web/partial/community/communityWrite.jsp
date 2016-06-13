<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8"/>
	<title>게시판 상세 내용</title>
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
		function back(){
			window.history.back();
			location.reload();
		}
		
		var buttonClickCnt=0;
		
		function save(form, pAction) {
			if(0 == buttonClickCnt){
				if("" == form.name.value) {
		            alert("이름을 입력해 주십시오.\n");
		            form.name.focus();
		            return;
		        }
				if("" == form.title.value) {
		            alert("제목을 입력해 주십시오.\n");
		            form.title.focus();
		            return;
		        }
				if("" == form.content.value) {
		            alert("내용을 입력해 주십시오.\n");
		            form.content.focus();
		            return;
		        }
	            else{
					buttonClickCnt++;
					form.action= pAction;
					form.submit();
					
		        	ButtonProcessingSmall(form);  // Loading Image
		            return false;
				}
			}else {
				alert("처리중입니다.");
				document.location.href="./notice.do";
			}
		}
		
		function isChkValidPolicyStr(pStr){
			var ch;
			if( (pStr.length < 4) || (pStr.length > 15) ){
				alert("문자열의 길이제한은 4 ~ 15자이며 \n숫자와 영문 대소문자는 사용가능하지만 \n[', \", ?, %, ^, &, \] 해당 특수문자는 사용할 수 없습니다.");
				return true;
			}else{
				for (var i=0;i<pStr.length;i++) {
					ch = pStr.charAt(i);
					if(ch=="'"||ch=="\""||ch=="?"||ch=="%"||ch=="^"||ch=="&"||ch=="\\"||!((ch >= "0" && ch <= "9") || (ch >= "a"  && ch <= "z") ||(ch >= "A"  && ch <= "Z"))){
						alert("문자열의 길이제한은 4 ~ 15자이며 \n숫자와 영문 대소문자는 사용가능하지만 \n[', \", ?, %, ^, &, \] 해당 특수문자는 사용할 수 없습니다.");
						return true;
					}
				}
			}
			return false;
		}
	</script>
</head>
<body onload="document.refresh();">
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
			<form name="docForm" method="post">
				<table class="detail02">
					<tbody>
					<tr>
						<th style="width:124px;border-top:5px solid #ddd;border-bottom:5px solid #ddd;padding:5px 0;" scope="row">제&nbsp;&nbsp;목</th>
						<td style="width:900px;border-top:5px solid #ddd;border-bottom:5px solid #ddd;padding:5px;" colspan="5">
							<input id="title" name="title" type="text" maxlength="120" style="width: 500px;">
						</td>
					</tr>
					<tr>
						<th style="width:124px;border-bottom:5px solid #ddd;padding:5px 0;" scope="row">작성자</th>
						<td style="width:900px;border-bottom:5px solid #ddd;padding:5px;" colspan="5">
							<input id="name" name="name" type="text" maxlength="30" style="width: 200px;">
						</td>
					</tr>
					</tbody>
				</table>
				<div style="padding-top:10px;">
					<textarea cols="40" rows="10" style="resize:none;background-color:#fff;padding:10px;" maxlength="1000" id="content" name="content"></textarea>
				</div>
			</form>
			<a href="JavaScript:save(document.docForm, 'communityWrite_action.do');" class="button" style="margin-right: 70px">저장</a>
			<a href='javascript:back();' class='button'>목록</a>
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