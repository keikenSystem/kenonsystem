<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メール アルデレスの入力</title>

<!-- bootstrap cdn for css,js -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>


<body class="container-fluid">

<!-- ------------------------nav bar------------------------ -->


	<nav class="navbar h4" style="background-color: #DCDCDC">
	<div class="d-inline-block p-3 font-weight-bold">
			<a href="/kenon/login"><img
					alt="" src="<%=request.getContextPath() %>/icon/pen_icon.png"" width="25" height="25"/><span class="d-none d-sm-inline">Kenon</span></a>
		</div>

	</nav>
 

 <!-- ------------------------Email address Form ------------------- -->


	<form method="post" class="pl-3">
	
		<div class="pt-3">
			<h4>パスワード再設定</h4>
			<p style="color: red">${errorMessage}</p>
		</div>
		<div class="form-group pt-3">
			<label for="email">メールアドレス</label> <input type="email"
				class="form-control" id="" name="userEmail" required="required" />
		</div>

		<button type="submit" class="btn btn-primary form-control">送信</button>

	</form>
	
	
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
				integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
				crossorigin="anonymous"></script>
			<script
				src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
				integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
				crossorigin="anonymous"></script>
	<script
				src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
				integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
				crossorigin="anonymous"></script>
	

</body>
</html>