<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script type="text/javascript">
		var contextPath = '${pageContext.request.contextPath}';
	</script>
	<script type="text/javascript" src="./admin/js/util.js"></script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<P> ${pageContext.request.localAddr}${pageContext.request.contextPath} </P>
<a href="${pageContext.request.contextPath}">${pageContext.request.contextPath}</a>
<input type="button" onclick="test();" />

</body>
</html>
