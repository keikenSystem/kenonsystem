<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>kenon</title>
<!-- bootstrap cdn for css,js -->
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

<!-- ---------------------------Nav bar ---------------------- -->

	<nav style="background-color: #DCDCDC" class="m-3 h4">
		<div class="d-inline-block p-3 font-weight-bold">
			<a href="/kenon/password_reset"><img
					alt="" src="icon/pen_icon.png" width="25" height="25"/><span class="d-none d-sm-inline">Kenon</span></a>
		</div>
		<div class="d-inline-block p-3 float-right">
			<a href="/kenon/logout">ログアウト</a>
		</div>
		<div class="d-inline-block p-3 float-right">
			<a style="color: black" href="/kenon/password_reset">パスワード変更</a>
		</div>

	</nav>


<!-- ------------------------------------Passowrd reset form ---------------------- -->


	<form action="/kenon/password_reset" method="post" class="pl-3">
		<div class="pt-3">
			<h4>パスワード変更</h4>
			<p style="color: red">${errorMessage}</p>
		</div>
		<div class="form-group pt-3">
			<label for="current_password">現在のパスワード</label> <input type="password"
				class="form-control" id="current_password" name="currentPassword" />
		</div>
		<div class="form-group pt-1">


			<label for="new_password">パスワード</label> <input type="password"
				class="form-control" id="new_password" name="newPassword" />
		</div>
		<div class="form-group pt-1">

			<label for="confirm_password">パスワード（確認用）</label> <input
				type="password" class="form-control" name="confirmPassword"
				id="confirm_password">
		</div>

		<button type="submit" class="btn btn-primary form-control">パスワード変更</button>

	</form>
</body>
</html>