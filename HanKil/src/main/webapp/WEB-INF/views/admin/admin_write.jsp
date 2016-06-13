<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">

	var idChk = 'N';
	var buttonClickCnt=0;
	var finalChkId = '';
	
	function duplicationAdminId(){
		
		var user_id = document.getElementsByName("user_id")[0].value;
		
		if(isChkValidPolicyStr(user_id)){
			document.getElementsByName("user_id")[0].focus();
			return;
		}
		
		if('' == user_id ||
				null == user_id ||
				'undefined' == user_id){
			
			alert('아이디를 입력해주세요.');
			document.getElementsByName("user_id")[0].focus();
			
		}else{
			var user_id = document.docForm.user_id.value;
			var parameterVal = '{"user_id":"'+user_id+'"}';
			
			var xmlhttp;
			if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp=new XMLHttpRequest();
			}else{// code for IE6, IE5
				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange=function(){
				if (xmlhttp.readyState==4 && xmlhttp.status==200){
					obj = JSON.parse(xmlhttp.responseText);
					var code = obj.resultCode;
					switch (code) {
					case '1':
						alert('사용 가능한 아이디입니다.');
						idChk = 'Y';
						finalChkId = user_id;
						break;
					default:
						alert('중복된 아이디입니다.');
						break;
					}
				}
			};
			xmlhttp.open("POST","admin_dupChk.do",true);
			xmlhttp.setRequestHeader("Content-type","application/json");
			xmlhttp.send(parameterVal);
		}
	}
	
	function save(form, pAction) {
		if(0 == buttonClickCnt){
			if("" == form.user_id.value) {
	            alert("아이디를 입력해 주십시오.\n");
	            form.admin_id.focus();
	            return;
	        }
			if("" == form.user_passwd.value) {
	            alert("비밀번호를 입력해 주십시오.\n");
	            form.admin_pass.focus();
	            return;
	        }
			if("" == form.user_passwd_confirm.value) {
	            alert("재확인 비밀번호를 입력해 주십시오.\n");
	            form.admin_pass_confirm.focus();
	            return;
	        }
			if(('N'==idChk)||
				(finalChkId != form.user_id.value && idChk == 'Y')){
				alert("중복체크 해주십시오.\n");
				return;
			}
			if(isChkValidPolicyStr(form.user_id.value)){
	            form.admin_id.focus();
	            return;
			}
			if(isChkValidPolicyStr(form.user_passwd.value)){
	            form.admin_pass.focus();
	            return;
			}
			if("" == form.user_rank.value) {
	            alert("권위를 선택해 주십시오.\n");
	            form.user_rank.focus();
	            return;
	        }
			if(form.user_passwd.value != form.user_passwd_confirm.value){
				alert("비밀번호가 일치하지않습니다.\n");
	            form.user_passwd_confirm.focus();
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
			document.location.href="./admin_list.do";
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
    <form name="docForm" method="post">
     <table width="700" border="0" cellspacing="0" cellpadding="0">
       <tr>                 
         <td height="25"  align="left" class="darkgray12b" style="padding-left:2">[ 정보 입력 ]</td>
       </tr>
     </table>
     <table width="700" border="0" cellspacing="2" cellpadding="1">
	<tr> 
         <td width="150" height="2" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">아이디</td>
         <td style="padding-left:10">
         	<input name="user_id" type="text" class="darkgray12" maxlength="15" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="" >
         	<input type="button" value="중복체크" onclick="duplicationAdminId();">
	  </td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">비밀번호</td>
         <td style="padding-left:10">
         	<input name="user_passwd" type="password" class="darkgray12" maxlength="15" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="">
	  </td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">비밀번호 재확인</td>
         <td style="padding-left:10">
         	<input name="user_passwd_confirm" type="password" class="darkgray12" maxlength="15" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="">
	  </td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">이름</td>
         <td style="padding-left:10">
         	<input name="user_name" type="text" class="darkgray12" maxlength="60" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="">
	  </td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">권위</td>
         <td style="padding-left:10">
         	<select class="darkgray12" id="user_rank" name="user_rank">
					<option value = "">선택</option>
					<c:choose>
						<c:when test="${sessionScope.adminRank == 'master'}">
							<option value="master">master&nbsp;&nbsp;&nbsp;&nbsp;</option>
							<option value="admin">admin&nbsp;&nbsp;&nbsp;&nbsp;</option>
							<option value="client">client&nbsp;&nbsp;&nbsp;&nbsp;</option>							
						</c:when>
						<c:otherwise>
							<option value="admin">admin&nbsp;&nbsp;&nbsp;&nbsp;</option>
							<option value="client">client&nbsp;&nbsp;&nbsp;&nbsp;</option>
						</c:otherwise>
					</c:choose>
			</select>
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
      </table>   	
</form>	
<table width="700" border="0" cellspacing="0" cellpadding="0">
	<tr> 
		<td align="right" >
			<a href="JavaScript:save(document.docForm, 'admin_write_action.do');"><img src="./images/btn_save.gif" border="0"></a>&nbsp;
			<a href="./admin_list.do"><img src="./images/btn_list.gif" border="0"></a>
		</td>
	</tr>
</table>