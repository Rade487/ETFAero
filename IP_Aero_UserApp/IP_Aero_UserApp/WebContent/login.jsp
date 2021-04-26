<html>
<head>

<title>Aero User App</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style><%@include file="/WEB-INF/css/style.css"%></style>
<title>Login</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: DarkSlateGrey">
			

		</nav>
	</header>

	<div class="login"> 
		<section class="form animated flipInX">
	  <h2>Login To Your Account</h2>
	<form id="login" name="loginForm" method="POST"
		action="j_security_check">
		<div class="form-group">
			<label for="usr">Username</label> <br><input type="text"
				class="form-control" name="j_username" size="20" />
		</div>
		
		<div class="form-group">
			<label for="pwd">Password</label> <input type="password"
				class="form-control" size="20" name="j_password" />
		</div>
		<p>
			<input class="btn btn-primary" type="submit" value="Login" />
		</p>
	</form>
	</section>
	</div>
	
</body>
</html>