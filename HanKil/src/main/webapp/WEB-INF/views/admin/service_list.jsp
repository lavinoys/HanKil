<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript">
function searchReset(form) {
	form.category_seq.value  = "";
	form.product_seq.value  = "";
	form.searchStr.value  = "";
	searchList();
}
function searchList() {	    
	document.userList.action='service_list.do';
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
	var category_seq = '${sessionScope.searchCategorySeq}';
	var product_seq = '${sessionScope.searchProductSeq}';
	var searchOpt = '${sessionScope.searchOpt}';
	var searchStr = '${sessionScope.searchStr}';
	
	if(category_seq != null && category_seq != ''){
		selectValue(document.getElementsByName("category_seq"), category_seq);
	}
	if(product_seq != null && product_seq != ''){
		selectValue(document.getElementsByName("product_seq"), product_seq);
	}
	if(searchOpt != null && searchOpt != ''){
		selectValue(document.getElementsByName("searchOpt"), searchOpt);
	}
	if(searchStr != null && searchStr != ''){
		document.getElementsByName("searchStr")[0].value=searchStr;
	}
};
</script>
<form name="userList" action="JavaScript:searchList();" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="53" valign="bottom" class="darkgray16b">서비스관리</td>
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
				<select  class="darkgray12" id="category_seq" name="category_seq" onchange="JavaScript:searchList();" style="width:140px;">
	         		<option value="">제품군을 선택하세요</option>
	         		<c:if test="${!empty requestScope.category_list}">
	         			<c:forEach begin="0" end="${fn:length(requestScope.category_list)}" items="${requestScope.category_list}" var="list">
         					<option value="${list.category_seq}">${list.category_name}</option>
         				</c:forEach>
	         		</c:if>
				</select>  |&nbsp;
				<select  class="darkgray12" id="product_seq" name="product_seq" onchange="JavaScript:searchList();" style="width:140px;">
					<option value="">제품을 선택하세요</option>
					<c:if test="${!empty requestScope.product_list}">
	         			<c:forEach begin="0" end="${fn:length(requestScope.product_list)}" items="${requestScope.product_list}" var="list">
         					<option value="${list.product_seq}">${list.product_name}</option>
         				</c:forEach>
	         		</c:if>
				</select>  |&nbsp;
				<select  class="darkgray12" id="searchOpt" name="searchOpt" style="width:50px;">
					<option value="title">제목</option>
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
		<td height="2" bgcolor="67AEB8" ><img src="./images/blank.gif" width="1" height="1"></td>
	</tr>
	<tr align="center">            
		<td width='5%'  bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>SEQ</strong></td>
		<td width='15%'  bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>제목</strong></td>
		<td width='20%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>작성자</strong></td>
		<td width='20%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>조회수</strong></td>
		<td width='20%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>작성일</strong></td>
		<td width='20%' bgcolor="F6F5F5" class="darkgray11" style="padding-bottom:1;padding-top:3"><strong>사용여부</strong></td>
	</tr>                     
	<tr align="center"> 
		<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
		<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
		<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
		<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
		<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
		<td height="1" bgcolor="67AEB8"><img src="./images/blank.gif" width="1" height="1"></td>
	</tr>
	<c:choose>
		<c:when test="${empty requestScope.service_list}">
			<tr align="center">
				<td class="text_c" colspan="8">조회할 자료가 없습니다.</td>
			</tr>
			<tr align="center"> 
				<td height="1" colspan="8" bgcolor="e1e1e1"><img src="./images/blank.gif" width="1" height="1"></td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach begin="0" end="${fn:length(requestScope.service_list)}" items="${requestScope.service_list}" var="list">
				<tr align="center" onmouseover="javascript:doMouseOverOnTR(this);" onmouseout="javascript:doMouseOutOnTR(this);">
							<td align="center" style="padding-bottom:1;padding-top:3">
								<a href="service_info.do?service_seq=${list.service_seq}">${list.service_seq}</a>
							</td>
							<td align="center" style="padding-bottom:1;padding-top:3">${list.title}</td>
							<td align="center" style="padding-bottom:1;padding-top:3">${list.user_name}</td>
							<td align="center" style="padding-bottom:1;padding-top:3">${list.count}</td>
							<td align="center" style="padding-bottom:1;padding-top:3">${list.update_date}</td>
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
		<td align="right"><a href="service_write.do"><img src="./images/btn_write.gif"  border="0"></a></td>
	</tr>
</table>