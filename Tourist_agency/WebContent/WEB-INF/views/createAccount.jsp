<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Сreate your account</title>
</head>
<body>

	<script>
		
	<%@include file="/WEB-INF/js/isMailFree.js"%>
		
	</script>

	<div class=log align="center" style="padding-top: 10px">
		<form action="createAccount" method="post" name="vinform">
			<p>
				<input type="email" name="email" placeholder="Mail"
					onblur="sendInfo()" required/> <span id="mylocation"></span>
			</p>
			<p>
				<input type="text" placeholder="First name" name="userName" required />
			</p>
			<p>
				<input type="text" placeholder="Last name" name="userSurname"
					required />
			</p>
			<p>
				<input type="text" placeholder="Phone" name="userPhone" required />
			</p>
			<p>
				<input type="date" placeholder="dd.mm.yyyy" name="userBirthday"
					required />
			</p>
			<p>
				<input type="password" placeholder="Enter your password"
					name="userPassword" required />
			</p>
			<p>
				<input type="password" placeholder="Repeat your password"
					name="userPasswordRepeat" required />
			</p>
			<p>
				<input type="submit" value="Next" />
			</p>
		</form>
	</div>
</body>
</html>