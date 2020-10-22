<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>

<!------------ bootstrap cdn for css,js ---------------->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
	
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
	
</head>
<body class="container-fluid">

	<!------------------------ Nav bar -------------------------------->
	
	
	<nav class="navbar" style="background-color: #DCDCDC">
		<ul class="navbar-nav font-weight-bold h4">
			<li class="nav-item"><a href="/kenon/login" class="nav-link"><img
					alt="" src="../icon/pen_icon.png" width="25" height="25"/><span class="d-none d-sm-inline">kenon</span></a></li>
		</ul>

	</nav>


	<!---------------- Login form -- --------->
	
	
	<form method="post" class="pl-3">
		<div class="pt-3">
			<h4>ログイン</h4>
			<p style="color: red">${errorMessage}</p>
		</div>
		<div class="form-group pt-3">
			<label for="userId">社員番号</label> <input type="text"
				class="form-control" id="" name="userId" required="required"/>
		</div>
		<div class="form-group pt-1">

			<div>
				<label for="password">パスワード</label>
			</div>
			<div style="text-align: right;">
				<a href="/kenon/login/recover">パスワードを忘れた方</a>
			</div>

			<input type="password" class="form-control" name="password" re/>
		</div>
		<button type="submit" class="btn btn-primary form-control" >ログイン</button>
		

	</form>



</body>
</html>