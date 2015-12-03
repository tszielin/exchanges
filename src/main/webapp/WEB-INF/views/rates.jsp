<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Exchanges</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/menu.js"></script>
</head>
<body>
	<div id="sse1">
		<div id="sses1">
			<ul>
				<li><a href="/exchanges/rates/set">Sample exchanges</a></li>
				<li><a href="/exchanges/rates/all">All exchanges</a></li>
				<li><a href="/exchanges/login?logout">Logout</a></li>
			</ul>
		</div>
	</div>
	<hr>
<!-- 	<div class="datagrid"> -->
<!-- 		<input type="checkbox" name="all" value="all">Show all -->
<!-- 		currencies? -->
<!-- 	</div> -->
<!-- 	<hr> -->
	<div class="datagrid">
		<table>
			<tr>
				<td>Timestamp</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
						value="${exchanges.date}" /></td>
			</tr>
			<tr>
				<td>Source</td>
				<td>${exchanges.source}</td>
			</tr>
		</table>
	</div>
	<hr>
	<div class="datagrid">
		<table>
			<c:forEach var="item" items="${exchanges.quotes}">
				<tr>
					<td align="right"><fmt:formatNumber type="number"
							pattern="#,##0.00000" value="${item.rate}" /></td>
					<td align="left">${item.quote}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
