<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isErrorPage="true"%>
<html>
<head>
<title>Error</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/menu.js"></script></head>
</head>
<body>
	<div id="sse1">
		<div id="sses1">
			<ul>
				<li><a href="javascript:history.back()">Back</a></li>
			</ul>
		</div>
	</div>
	<table width="100%" border="0">
		<tr valign="top">
			<td width="10%"><font size="2" color="gray">Error:</font></td>
			<td>${pageContext.exception}</td>
		</tr>
		<tr valign="top">
			<td><font size="2" color="gray">URI:</font></td>
			<td>${pageContext.errorData.requestURI}</td>
		</tr>
		<tr valign="top">
			<td><font size="2" color="gray">Status code:</font></td>
			<td>${pageContext.errorData.statusCode}</td>
		</tr>
		<tr valign="top">
			<td><font size="2" color="gray">Stack trace:</font></td>
			<td><c:forEach var="trace"
					items="${pageContext.exception.stackTrace}">
					<font size="1">${trace}</font>
					<br>
				</c:forEach></td>
		</tr>
	</table>
	<div id="sse1">
		<div id="sses1">
			<ul>
				<li><a href="javascript:history.back()">Back</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
