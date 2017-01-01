<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<body>
		<form action = "${pageContext.request.contextPath}/user/login.do" method = "post" >
			username:<input type = "text" name = "username"><br>
			password:<input type = "password" name = "password"><br>
			<input type = "submit" value = "Login">
		</form>
	</body>
</html>
