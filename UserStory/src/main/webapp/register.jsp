<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Story</title>
</head>
<body>	
	<form action="registerUser" method="post">
		Enter username (atleast 5 chars with only alphanumerics allowed) : 							<input type="text" name="username"><br>
		Enter password (atleast 8 chars with atleast 1 uppercase char, 1 lowercase and 1 number) : 	<input type="password" name="password"><br>
		<input type="submit" value="Register">
	</form>	
</body>
</html>