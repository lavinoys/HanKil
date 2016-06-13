<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        		console.log('fdas');
				buttonClickCnt++;
				form.action= pAction;
				form.submit();
				
	        	ButtonProcessingSmall(form);  // Loading Image
	            return false;
			}
		}else {
			alert("처리중입니다.");
			document.location.href="./service_list.do";
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
	
	function selectOpt(){
		var category_seq = document.getElementById("category_seq").value;
		var parameterVal = '{"category_seq":"'+category_seq+'"}';
		if(0<category_seq){
			var xmlhttp;
			if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp=new XMLHttpRequest();
			}else{// code for IE6, IE5
				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange=function(){
				if (xmlhttp.readyState==4 && xmlhttp.status==200){
					obj = JSON.parse(xmlhttp.responseText);
					var list = obj.value;
					removeAndAdd(document.getElementById("product_seq"),list);
				}
			};
			xmlhttp.open("POST","productListJson.do",false);
			xmlhttp.setRequestHeader("Content-type","application/json");
			xmlhttp.send(parameterVal);
		}else{
			box = document.getElementById("product_seq");
			var orgLen = box.length;
			if(1<orgLen){
				for(var i=1;i<orgLen;i++){
					box.remove(i);
				}
			}
		}
	}
	
	function removeAndAdd(box, list){
		var orgLen = box.length;
		
		if(1<orgLen){
			for(var i=1;i<orgLen;i++){
				box.remove(i);
			}
		}
		
		for(var j=0;j<list.length;j++){
			var option = document.createElement("option");
			option.text = list[j].product_name;
			option.value = list[j].product_seq;
			box.add(option);
			console.log(option.text);
		}
	}
</script>
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
    <form name="docForm" method="post">
     <table width="700" border="0" cellspacing="0" cellpadding="0">
       <tr>                 
         <td height="25"  align="left" class="darkgray12b" style="padding-left:2">[ 게시글입력 ]</td>
       </tr>
     </table>
     <table width="700" border="0" cellspacing="2" cellpadding="1">
		<tr> 
         <td width="150" height="2" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">제품군</td>
         <td style="padding-left:10">
         	<select  class="darkgray12" id="category_seq" name="category_seq" onchange="JavaScript:selectOpt();" style="width:140px;">
         		<option value="">제품군을 선택하세요</option>
         		<c:if test="${!empty requestScope.category_list}">
         			<c:forEach begin="0" end="${fn:length(requestScope.category_list)}" items="${requestScope.category_list}" var="list">
       					<option value="${list.category_seq}">${list.category_name}</option>
       				</c:forEach>
         		</c:if>
			</select>
	  	</td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">제품</td>
         <td style="padding-left:10">
         	<select  class="darkgray12" id="product_seq" name="product_seq" style="width:140px;">
				<option value="">제품을 선택하세요</option>
			</select>
		</td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">제목</td>
         <td style="padding-left:10">
         	<input name="ref_seq" value="0" style="display: none;"/>
         	<input name="reply_depth" value="0" style="display: none;"/>
         	<input name="reply_num" value="0" style="display: none;"/>
         	<input name="reply_yn" value="n" style="display: none;"/>
         	<input id="title" name="title" type="text" class="darkgray12" maxlength="120" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="" >
	  </td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">내용</td>
         <td style="padding-left:10">
         	<textarea id="content" name="content" rows="5" class="darkgray12" style="WIDTH: 100%; border:solid 1 #cccccc" maxlength="1000"></textarea>
	  </td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">이름</td>
         <td style="padding-left:10">
         	<input id="name" name="name" type="text" class="darkgray12" maxlength="30" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="사용자" >
	  	</td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">조회수</td>
         <td style="padding-left:10">
         	<input name="count" type="text" class="darkgray12" maxlength="120" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="0" >
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
         		<option value = "5" selected>5(일반)&nbsp;&nbsp;&nbsp;&nbsp;</option>
         		<option value = "4">4(공지)&nbsp;&nbsp;&nbsp;&nbsp;</option>
         		<option value = "3">3(공지)&nbsp;&nbsp;&nbsp;&nbsp;</option>
         		<option value = "2">2(공지)&nbsp;&nbsp;&nbsp;&nbsp;</option>
         		<option value = "1">1(공지)&nbsp;&nbsp;&nbsp;&nbsp;</option>
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
				<option value="y" selected>YES&nbsp;&nbsp;&nbsp;&nbsp;</option>
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
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">답글존재여부</td>
         <td style="padding-left:10">
         	<select class="darkgray12" id="reply_yn" name="reply_yn">
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
         	<input id="passwd" name="passwd" type="password" class="darkgray12" maxlength="4" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="" >
         	<br><font color="red">※ 비밀글 경우에 입력</font>
	  	</td>
       </tr>
       <tr> 
         <td width="150" height="2" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">파일</td>
         <td style="padding-left:10">
         	<input id="file" name="file" type="file" class="darkgray12" maxlength="120" style="WIDTH: 250px; HEIGHT: 19px; border:solid 1 #cccccc" value="" >
         	<br><font color="red">※ html 형식 업로드시 zip형태로 압축하며 메인화면은 index.html로 정의합니다.</font>
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
			<a href="JavaScript:save(document.docForm, 'service_write_action.do');"><img src="./images/btn_save.gif" border="0"></a>&nbsp;
			<a href="./service_list.do"><img src="./images/btn_list.gif" border="0"></a>
		</td>
	</tr>
</table>