<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css" />
<title>Login</title>
</head>
<body>
	<div id="login-box">
		<c:url var="loginUrl" value="/j_spring_security_check"></c:url>
		<form action="${loginUrl}" method="POST">
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
			<table>
				<tr>
					<td>User ID:</td>
					<td><input type='text' name='username' required/></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' required/></td>
				</tr>
				<tr>
					<td colspan='2' align="center"><input name="submit"
						type="submit" value="Login" /></td>
				</tr>
				<tr>
					<td colspan='2' align="right"><a
						href="${pageContext.request.contextPath}/register"><font size="1">Create new user account</font></a></td>
				</tr>
			</table>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>
</body>
</html>