<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign in</title>
</head>
<body>
	<div class=log align="center" style="padding-top: 10px">
		<form action="login" method="post">

			<p>
				<input type="text" placeholder="Mail or phone" name="login" required/>
			</p>
			<p>
				<input type="password" placeholder="Enter your password"
					name="password" required/>
			</p>
			
			<p>${loginFailed}</p>
			
			<p>
				<input type="submit" value="Next" />
			</p>
		</form>
	</div>

</body>
</html>