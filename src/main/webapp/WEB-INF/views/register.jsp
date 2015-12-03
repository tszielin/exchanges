<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<title>Registration</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/form.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/glDatePicker.default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery-validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/glDatePicker.min.js"></script>
<script type="text/javascript">
	var currentTime = new Date();
	var month = currentTime.getMonth();
	var day = currentTime.getDate();
	var year = currentTime.getFullYear()
	$('birthday').glDatePicker({
		selectedDate: Date(year-18, month, day),
		selectableDateRange: [{
			from: new Date(year-150, month, day),
			to: new Date(year-13, month, day)
        }]
	});
	$(document).ready(function() {
		$('#registerForm').validate({
			rules : {
				username : {
					required : true,
					minlength : 5,
					maxlength : 25
				},
				password : {
					required : true,
					minlength : 5,
					maxlength : 25
				},
				confirm_password : {
					required : true,
					minlength : 5,
					equalTo : "#password"
				},
				firstname : {
					required : true,
					minlength : 2,
					maxlength : 30
				},
				lastname : {
					required : true,
					minlength : 2,
					maxlength : 50
				},
				email : {
					required : true,
					email : true
				},
				birthday : {
					required : true,
					date : true
				},
				address : {
					required : true,
					minlength : 5,
					maxlength : 150
				},
				town : {
					required : true,
					minlength : 5,
					maxlength : 50
				},
				country : {
					required : true,
					minlength : 5,
					maxlength : 70
				}
			},
			messages : {
				username : {
					required : "Please enter an username",
					minlength : jQuery.validator.format("At least {0} characters required!")
				},
				password : {
					required : "Please provide a password",
					minlength : jQuery.validator.format("At least {0} characters required!")
				},
				confirm_password : {
					required : "Please provide a password",
					minlength : jQuery.validator.format("At least {0} characters required!"),
					equalTo : "Please enter the same password as above"
				},
				email : {
					required : "Please enter an email address",
					email : "Your email address must be in the format of name@domain.xx"
				},
				address : {
					required : "Please enter an address (street, house number, etc.)",
					minlength : jQuery.validator.format("At least {0} characters required!")
				},
				town : {
					required : "Please enter a town",
					minlength : jQuery.validator.format("At least {0} characters required!")
				},
				country : {
					required : "Please enter a country",
					minlength : jQuery.validator.format("At least {0} characters required!")
				}
			}
		});
	});
</script>
</head>
<body>
	<h2>Register</h2>
	<form class="form" id="registerForm" method="POST" action="register">
		<table border="0" cellpadding="5" cellspacing="0" width="50%">
			<tr>
				<td width="20%"><font size="2" color="gray">Username:</font></td>
				<td><input id="username" name="username" type="text" 
					maxlength="25" style="width: 300px;" required /></td>
			</tr>
			<tr>
				<td><font size="2" color="gray">Password:</font></td>
				<td><input id="password" name="password" type="password"
					 maxlength="50" style="width: 300px;" required /></td>
			</tr>
			<tr>
				<td><font size="2" color="gray">Repeat password:</font></td>
				<td><input id="rep_password" name="rep_password"
					type="password" maxlength="50"  style="width: 300px;"
					required /></td>
			</tr>
			<tr>
				<td><font size="2" color="gray">First name:</font></td>
				<td><input id="firstname" name="firstname" type="text"
					 maxlength="30" style="width: 300px;" required /></td>
			</tr>
			<tr>
				<td><font size="2" color="gray">Last name:</font></td>
				<td><input id="lastname" name="lastname" type="text" 
					maxlength="50" style="width: 300px;" required /></td>
			</tr>
			<tr>
				<td><font size="2" color="gray">Email address:</font></td>
				<td><input id="email" name="email" type="email"
					maxlength="100" style="width: 300px;" required /></td>
			</tr>
			<tr>
				<td><font size="2" color="gray">Phone number:</font></td>
				<td><input id="phone" name="phone" type="tel" maxlength="15"
					 /></td>
			</tr>
			<tr>
				<td><font size="2" color="gray">Birthday:</font></td>
				<td><input name="birthday" type="date" id="birthday"
					gldp-id="birthday" required /></td>
			</tr>
			<tr>
				<td><font size="2" color="gray">Address:</font></td>
				<td><input id="address" name="address" type="text" 
					maxlength="150" style="width: 300px;" required /></td>
			</tr>
			<tr>
				<td><font size="2" color="gray">City:</font></td>
				<td><input id="town" name="town" type="text" maxlength="50"
					 style="width: 300px;" required /></td>
			</tr>
			<tr>
				<td><font size="2" color="gray">Zip/Postal Code:</font></td>
				<td><input id="postbox" name="postbox" type="text" 
					maxlength="10" style="width: 300px;" /></td>
			</tr>
			<tr>
				<td><font size="2" color="gray">Country:</font></td>
				<td><input id="country" name="country" type="text" 
					maxlength="70" style="width: 300px;" required /></td>
			</tr>
		</table>
		<hr>
		<div align="center">
			<input class="submit" type="submit" value="Submit" />
		</div>
	</form>
</body>
</html>