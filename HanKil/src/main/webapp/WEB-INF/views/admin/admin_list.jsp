<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript">
function searchReset(form) {
	form.user_rank.value  = "";
	form.searchStr.value  = "";
	searchList();
}
function searchList() {	    
	document.userList.action='admin_list.do';
	document.userList.submit();
}

function selectValue(seleObj, selVal){
	for(var i=0;i<seleObj.length;i++){
        for(var j=0;j<seleObj[i].options.length;j++){
            if(seleObj[i].options[j].value==selVal){
                seleObj[0].selectedIndex = j;
                break;
            }
        }
    }
}
	
window.onload=function(){
	var user_rank = '${sessionScope.searchAccountRank}';
	
	if(user_rank != null && user_rank != ''){
		selectValue(document.getElementsByName("user_rank"), user_rank);
	}
};
</script>
<form name="userList" action="JavaScript:searchList();" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="53" valign="bottom" class="darkgray16b">계정관리</td>
		</tr>
		<tr>
			<td height="3" bgcolor="#FF5500"></td>
		</tr>
		<tr>
			<td height="20"></td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25"  align="left" class="darkgray12b" style="padding-left:2">[ 목록보기 ]</td>
			<td height="25"  align="right" class="darkgray11" style="padding-left:2;">
				<select  class="darkgray12" id="user_rank" name="user_rank" onchange="JavaScript:searchList();" style="width:140px;">
					<option value="">권위를 선택하세요</option>
					<c:if test="${!empty requestScope.account_rank_list}">
	         			<c:forEach begin="0" end="${fn:length(requestScope.account_rank_list)}" items="${requestScope.account_rank_list}" var="list">
         					<option value="${list.user_rank}">${list.user_rank}</option>
         				</c:forEach>
	         		</c:if>
				</select>  |&nbsp;
				<select  class="darkgray12" id="searchOpt" name="searchOpt" style="width:90px;">
					<option value="id">아이디</option>
					<option value="name">이름</option>
				</select>  |&nbsp;
				<input name="searchStr" class="darkgray11" value="" style="width:140px;"/>  |&nbsp;
				<a href="javascript:searchReset(document.userList);"><img align="middle" src="./images/btn_reset3.gif" border="0"></a> |&nbsp;
				<img align="middle" src="./images/btn_search2.gif" border="0" onclick="searchList();"> |
			</td>
		</tr>
	</table>
</form>
<table width="100%" border="0" cellspacing="2" cellpadding="0">
	<tr align="center"> 
		<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
		<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
		<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
		<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
		<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
	</tr>
	<tr align="center">            
		<td width='5%'  bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>SEQ</strong></td>
		<td width='15%'  bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>아이디</strong></td>
		<td width='20%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>이름</strong></td>
		<td width='20%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>권위</strong></td>
		<td width='20%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>사용여부</strong></td>
	</tr>                     
	<tr align="center"> 
		<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
		<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
		<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
		<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
		<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
	</tr>
	<c:choose>
		<c:when test="${empty requestScope.account_list}">
			<tr align="center">
				<td class="text_c" colspan="8">조회할 자료가 없습니다.</td>
			</tr>
			<tr align="center"> 
				<td height="1" colspan="8" bgcolor="e1e1e1"><img src="./images/blank.gif" width="1" height="1"></td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach begin="0" end="${fn:length(requestScope.account_list)}" items="${requestScope.account_list}" var="list">
				<tr align="center" onmouseover="javascript:doMouseOverOnTR(this);" onmouseout="javascript:doMouseOutOnTR(this);">
							<td align="center" style="padding-bottom:1;padding-top:3">
								<a href="admin_info.do?user_seq=${list.user_seq}">${list.user_seq}</a>
							</td>
							<td align="center" style="padding-bottom:1;padding-top:3">${list.user_id}</td>
							<td align="center" style="padding-bottom:1;padding-top:3">${list.user_name}</td>
							<td align="center" style="padding-bottom:1;padding-top:3">${list.user_rank}</td>
							<td align="center" style="padding-bottom:1;padding-top:3">${list.use_yn}</td>
				</tr>
				<tr align="center"> 
					<td height="1" colspan="8" bgcolor="e1e1e1"><img src="./images/blank.gif" width="1" height="1"></td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="8"><img src="./images/blank.gif"></td>
	</tr>
	<tr>
		<td  height="2" align="center" >${pageNavigator}</td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">     
	<tr>
		<td align="right"><a href="admin_write.do"><img src="./images/btn_write.gif"  border="0"></a></td>
	</tr>
</table>