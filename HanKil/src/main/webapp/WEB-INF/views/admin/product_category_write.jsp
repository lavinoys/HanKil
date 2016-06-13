<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	
	var buttonClickCnt=0;
	
	function save(form, pAction) {
		if(0 == buttonClickCnt){
			if("" == form.category_name.value) {
	            alert("제품군 명을 입력해 주십시오.\n");
	            form.admin_id.focus();
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
			document.location.href="./product_category_list.do";
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
        <td height="53" valign="bottom" class="darkgray16b">제품군관리</td>
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
         <td height="25"  align="left" class="darkgray12b" style="padding-left:2">[ 제품군입력 ]</td>
       </tr>
     </table>
     <table width="700" border="0" cellspacing="2" cellpadding="1">
		<tr> 
         <td width="150" height="2" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">이름</td>
         <td style="padding-left:10">
         	<input id="category_name" name="category_name" type="text" class="darkgray12" maxlength="120" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" value="" >
	  </td>
       </tr>
       <tr> 
         <td height="1" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
       <tr> 
         <td align="right" bgcolor="F8F6F6" style="padding-right:10">정보</td>
         <td style="padding-left:10">
         	<input name="category_info" type="text" class="darkgray12" maxlength="120" style="WIDTH: 130px; HEIGHT: 19px; border:solid 1 #cccccc" />
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
         <td width="150" height="2" bgcolor="#67AEB8"></td>
         <td bgcolor="cccccc"></td>
       </tr>
      </table>   	
</form>	
<table width="700" border="0" cellspacing="0" cellpadding="0">
	<tr> 
		<td align="right" >
			<a href="JavaScript:save(document.docForm, 'product_category_write_action.do');"><img src="./images/btn_save.gif" border="0"></a>&nbsp;
			<a href="./product_category_list.do"><img src="./images/btn_list.gif" border="0"></a>
		</td>
	</tr>
</table>