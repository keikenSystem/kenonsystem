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
<body class="container">
     <div class="row col-12">
	<nav class="navbar fixed-top">
		
			<div class="col-12">
				<a href="#">kenon</a>
			</div>
		
	</nav>
	</div>
	<div class="row">
	<div >
		<form method="post">
			<div>
				<h3>ログイン</h3>
			</div>
			<div class="form-group">
				<label for="">社員番号</label> <input type="text" class="form-control"
					id="" name="userId" />
			</div>
			<div class="form-group">
				<label for="">パスワード</label>
				<button class="btn-link align-right">
					<a href="#">パスワードを忘れた方</a>
				</button>
				<input type="password" class="form-control" 
					name="password">
			</div>
			<button type="submit" class="btn btn-primary">ログイン</button>

		</form>


	</div>
	</div>


</body>
</html>