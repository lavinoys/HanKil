<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<link href="./css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="./js/util.js"></script>
		<script type="text/javascript">
			rootPath = '${pageContext.request.contextPath}';
			console.log(rootPath);
		</script>
	</head>
	<body background="./images/bg.gif" leftmargin="0" topmargin="0">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="146" valign="top" style="padding-left:15;padding-right:16">
					<tiles:insertAttribute name="menu"/>
				</td>
				<td width="98%" valign="top" style="padding-left:5">
					<tiles:insertAttribute name="content"/>
				</td>
				<td width="2%">&nbsp;</td>
			</tr>
		</table>
		<tiles:insertAttribute name="bottom"/>
	</body>
</html>