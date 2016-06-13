<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	
	var buttonClickCnt=0;
	
	function save(form, pAction) {
		if(0 == buttonClickCnt){
			if("" != form.file.value){
            	form.enctype = 'multipart/form-data';
        	}
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
            if(("y" == form.secret_yn.value)&&("" == form.passwd.value)) {
	            alert("비밀번호를 입력해 주십시오.\n");
	            form.passwd.focus();
	            return;
	        }else{
				buttonClickCnt++;
				form.action= pAction;
				form.submit();
				
	        	ButtonProcessingSmall(form);  // Loading Image
	            return false;
			}
		}else {
			alert("처리중입니다.");
			document.location.href="./community_list.do";
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
		selectValue(document.getElementsByName("rank"), '${community_info.rank}');
	    selectValue(document.getElementsByName("use_yn"),'${community_info.use_yn}');
	    selectValue(document.getElementsByName("secret_yn"), '${community_info.secret_yn}');
	};
</script>
 <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="53" valign="bottom" class="darkgray16b">참여게시판관리</td>
      </tr>
<tr>
	<td height="3" bgcolor="#FF5500"></td>
</tr>
      <tr>
        <td height="20"></td>
      </tr>
    </table>
    <form name="docForm" method="post">
     <table width="700" border="0" cellspacing="0" cellpadding="0">
       <tr>                 
         <td height="25"  align="left" class="darkgray12b" style="padding-left:2">[ 게시글조회 ]</td>
       </tr>
     </table>
     <table width="700" border="0" cellspacing="2" cellpadding="1">
		<tr> 
         <td width="150" height="2" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">제목</td>
         <td style="padding-left:10">
         	<input name="community_seq" value="${community_info.community_seq}" style="display: none;"/>
         	<input name="user_seq" value="${community_info.user_seq}" style="display: none;"/>
         	<input name="ref_seq" value="${community_info.ref_seq}" style="display: none;"/>
         	<input name="reply_depth" value="${community_info.reply_depth}" style="display: none;"/>
         	<input name="reply_num" value="${community_info.reply_num}" style="display: none;"/>
         	<input name="reply_yn" value="${community_info.reply_yn}" style="display: none;"/>
         	<input id="title" name="title" type="text" class="darkgray12" maxlength="120" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="${community_info.title}" >
	  </td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">내용</td>
         <td style="padding-left:10">
         	<textarea id="content" name="content" rows="5" class="darkgray12" style="WIDTH: 100%; border:solid 1 #cccccc" maxlength="1000">${community_info.content}</textarea>
	  </td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">이름</td>
         <td style="padding-left:10">
         	<input id="name" name="name" type="text" class="darkgray12" maxlength="120" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="${community_info.user_name}" >
	  	</td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">조회수</td>
         <td style="padding-left:10">
         	<input name="count" type="text" class="darkgray12" maxlength="120" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="${community_info.count}" >
	  	</td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">게시글우선순위</td>
         <td style="padding-left:10">
         	<select class="darkgray12" id="rank" name="rank">
         		<option value="5">5(일반)&nbsp;&nbsp;&nbsp;&nbsp;</option>
         		<option value="4">4(공지)&nbsp;&nbsp;&nbsp;&nbsp;</option>
         		<option value="3">3(공지)&nbsp;&nbsp;&nbsp;&nbsp;</option>
         		<option value="2">2(공지)&nbsp;&nbsp;&nbsp;&nbsp;</option>
         		<option value="1">1(공지)&nbsp;&nbsp;&nbsp;&nbsp;</option>
         	</select>
         	<br><font color="red">※ 5는 일반글이며 숫자가 낮아질수록 우선도가 올라감</font>
	  </td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">사용여부</td>
         <td style="padding-left:10">
         	<select class="darkgray12" id="use_yn" name="use_yn">
				<option value="y">YES&nbsp;&nbsp;&nbsp;&nbsp;</option>
				<option value="n">NO&nbsp;&nbsp;&nbsp;&nbsp;</option>
			</select>
	  </td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
              <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">비밀글여부</td>
         <td style="padding-left:10">
         	<select class="darkgray12" id="secret_yn" name="secret_yn">
				<option value="y">YES&nbsp;&nbsp;&nbsp;&nbsp;</option>
				<option value="n" selected>NO&nbsp;&nbsp;&nbsp;&nbsp;</option>
			</select>
	  </td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">비밀번호</td>
         <td style="padding-left:10">
         	<input id="passwd" name="passwd" type="password" class="darkgray12" maxlength="4" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="${community_info.passwd}" >
         	<br><font color="red">※ 비밀글 경우에 입력</font>
	  	</td>
       </tr>
       <tr> 
         <td width="150" height="2" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <c:choose>
        	<c:when test="${empty requestScope.file_info}">
        		<tr>
        			<td align="right" bgcolor="F8F6F6" style="padding-right:10">파일</td>
				<td style="padding-left:10">
		         	<input id="file" name="file" type="file" class="darkgray12" maxlength="120" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="" >
				</td>
        		</tr>
        	</c:when>
        	<c:otherwise>
        		<script type="text/javascript">
        			selectValue(document.getElementsByName("hold_file"), 'y');
        			function changeSelectVal(){
        				var x = document.getElementById("hold_file").value;
        				if(x=='y'){
        					document.getElementById("file").style.display = "none";
        					document.getElementById("link").style.display = "block";
        				}else{
        					document.getElementById("file").style.display = "block";
        					document.getElementById("link").style.display = "none";
        				}
        			}
         	</script>
         	<tr>
         		<td align="right" bgcolor="F8F6F6" style="padding-right:10">파일</td>
         		<td style="padding-left:10">
		         	<select class="darkgray12" id="hold_file" name="hold_file" onchange="changeSelectVal()">
		         		<option value="y">파일유지&nbsp;&nbsp;&nbsp;&nbsp;</option>
						<option value="n">파일변경&nbsp;&nbsp;&nbsp;&nbsp;</option>
		         	</select>
		         	<br><font color="red">※ html 형식 업로드시 zip형태로 압축하며 메인화면은 index.html로 정의합니다.</font>
				</td>
         	</tr>
         	<tr>
         		<td align="right" bgcolor="F8F6F6" style="padding-right:10"></td>
         		<td style="padding-left:10">
		         	<input id="file" name="file" type="file" class="darkgray12" maxlength="120" style="WIDTH: 250px; HEIGHT: 19px; border:solid 1 #cccccc;display: none;" value="" />
		         	<c:choose>
		         		<c:when test="${file_info.file_type == 'zip'}">
							<a id="link" href="${pageContext.request.contextPath}${file_info.file_url}/index.html" target="_blank">[ ${file_info.file_name} ]<img src='./images/icon_file.gif'></a>		         		
		         		</c:when>
		         		<c:otherwise>
		         			<a id="link" href="${pageContext.request.contextPath}${file_info.file_url}" target="_blank">[ ${file_info.file_name} ]<img src='./images/icon_file.gif'></a>
		         		</c:otherwise>
		         	</c:choose>
					<input name="file_seq" value="${file_info.file_seq}" style="display: none;"/>
					<br><font color="red">※ html 형식 업로드시 zip형태로 압축하며 메인화면은 index.html로 정의합니다.</font>
				</td>
         	</tr>
        	</c:otherwise>
        </c:choose>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
      </table>   	
</form>	
<table width="700" border="0" cellspacing="0" cellpadding="0">
	<tr> 
		<td align="right" >
			<c:if test="${community_info.reply_yn == 'n'}">
				<a href="./community_reply.do?community_seq=${community_info.community_seq}"><img src="./images/btn_reply.jpg" border="0"></a>&nbsp;
			</c:if>
			<a href="JavaScript:save(document.docForm, 'community_modify_action.do');"><img src="./images/btn_save.gif" border="0"></a>&nbsp;
			<a href="./community_list.do"><img src="./images/btn_list.gif" border="0"></a>
		</td>
	</tr>
</table>