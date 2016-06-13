<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="2">&nbsp;</td>
	</tr>  
	<tr>
		<td><a href="./admin_list.jsp?pageNo=1&INIT"><img src="./images/logo.jpg" width="146" height="36" border="0"></a></td>
	</tr>
	<tr>
		<!--td height="3" bgcolor="#FF5500"></td-->
		<td height="3">&nbsp;</td>
	</tr>
</table>
<!-- 로긴정보 -->
<table width="100%" border="0" cellpadding="3" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td class="darkgray11" style="padding-top:5"><span class="blue11">'<strong>${sessionScope.adminId}</strong>'</span>님<br>
		오늘도 즐거운 하루되세요~ </td>
	</tr>
	<tr>
		<td align="center" style="padding-bottom:5">&nbsp;</td>
	</tr>
</table>
<!-- 메뉴 -->
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="30"></td>
	</tr>
	<tr>
		<td><img src="./images/pic04.gif" width="146" height="4"></td>
	</tr>
	<tr>
		<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
		<a href="${pageContext.request.contextPath}/admin/admin_list.do?pageNo=1&INIT">계정관리</a></td>
	</tr>
	<tr>
		<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
		<a href="${pageContext.request.contextPath}/admin/service_list.do?pageNo=1&serviceType=1&INIT">FAQ관리</a></td>
	</tr>
	<tr>
		<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
		<a href="${pageContext.request.contextPath}/admin/service_list.do?pageNo=1&serviceType=2&INIT">질문/답변관리</a></td>
	</tr>
	<tr>
		<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
		<a href="${pageContext.request.contextPath}/admin/service_list.do?pageNo=1&serviceType=3&INIT">자료실관리</a></td>
	</tr>
	<tr>
		<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
		<a href="${pageContext.request.contextPath}/admin/community_list.do?pageNo=1&communityType=4&INIT">뉴스관리</a></td>
	</tr>
	<tr>
		<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
		<a href="${pageContext.request.contextPath}/admin/community_list.do?pageNo=1&communityType=5&INIT">이벤트관리</a></td>
	</tr>
	<tr>
		<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
		<a href="${pageContext.request.contextPath}/admin/community_list.do?pageNo=1&communityType=6&INIT">참여게시판관리</a></td>
	</tr>
	<tr>
		<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
		<a href="${pageContext.request.contextPath}/admin/product_category_list.do?pageNo=1&INIT">제품군관리</a></td>
	</tr>
	<tr>
		<td height="25" class="darkgray11" ><img src="./images/Bullet70-Box-Orange.png" align="absmiddle">
		<a href="${pageContext.request.contextPath}/admin/product_list.do?pageNo=1&INIT">제품관리</a></td>
	</tr>
	<tr>
		<td height="1" bgcolor="#CCCCCC"></td>
	</tr>
</table>