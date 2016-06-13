<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>▒ HanKil - Admin ▒</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="./js/util.js"></script>

<script language="JavaScript">
	rootPath = '${pageContext.request.contextPath}';
	
	var i=0;
	function loginCheck(form,pAction) {
	//두번저장 방지
		if (i==0) {
	//*****************************************************************
	// 필수값 확인
	//*****************************************************************

			if(form.id.value=="") {
	            alert("아이디를 입력해 주십시오.\n");
	            form.id.focus();
	            return;
	        }
	        if(form.passwd.value=="") {
	            alert("비밀번호를 입력해 주십시오.\n");
	            form.passwd.focus();
	            return;
	        }else {
				i++;
				var admin_id = form.id.value;
				var admin_passwd = form.passwd.value;
				var parameterVal = '{"user_id":"'+admin_id+'","user_passwd":"'+admin_passwd+'"}';
				
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
							location.href = rootPath+obj.url;
							break;
						case '3':
							alert('아이디가 틀렸습니다.');
							i=0;
							break;
						case '4':
							alert('비밀번호가 틀렸습니다.');
							i=0;
							break;
						case '0':
							alert('아이디가 없습니다.');
							i=0;
							break;
						default:
							alert('일시적 시스템 장애입니다.');
							i=0;
							break;
						}
						
					}
				};
				xmlhttp.open("POST",pAction,false);
				xmlhttp.setRequestHeader("Content-type","application/json");
				xmlhttp.send(parameterVal);
				/*
				form.action= pAction;
				form.submit();
				*/
			}

	//두번이상 저장버튼 클릭한 경우
		}else {
			alert("처리중입니다.");
		}
	}
	function f_load(){
		document.loginInput.id.focus();
	}

	function pressEnterKey(){
		if(event.keyCode == 13){
			loginCheck(document.loginInput,'login_action.do');
		}else{
			return false;
		}
	}
</script>
</head>

<body leftmargin="0" topmargin="0" onload="f_load();">
<form name="loginInput" method="post">
<table cellpadding="0" cellspacing="0" border="0" width="100%" height="100%">
	<tr>
		<td align="center" valign="middle">
			
	      <table width="324" border="0" cellpadding="0" cellspacing="0" bgcolor="709185">
	        <tr>
	          <td height="30" colspan="3">&nbsp;</td>
	        </tr>
	        <tr>
	          <td width="82" align="right"><font color="#FFFFFF">아이디</font></td>
	          <td width="124" align="center">
				<input name="id" type="text" tabindex=1 class="darkgray12"   style="border:1 solid  #ffffff;height:19px;width:100px" ></td>
	          <td width="118" rowspan="2" align="center">
	          <img src="./images/btn_go.gif" width="59" height="59" border="0" onMouseOver="this.style.cursor='hand'" onclick="JavaScript:loginCheck(document.loginInput,'login_action.do');">
	          </td>
	        </tr>
	        <tr>
	          <td align="right"><font color="#FFFFFF">비밀번호</font></td>
	          <td align="center">
				<input name="passwd" type="password" tabindex=2 class="darkgray12"   style="border:1 solid  #ffffff;height:19px;width:100px" onkeypress="javascript:pressEnterKey();"></td>
	        </tr>
	        <tr>
	          <td height="20" colspan="3">&nbsp;</td>
	        </tr>
			  <tr>
			    <td class="gray11" align="center" colspan="3"><font color="white" bold>&nbsp;Copyright (c) 2014 HANKILWIT. All rights Reserved.</font></td>
			  </tr>
	      </table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
