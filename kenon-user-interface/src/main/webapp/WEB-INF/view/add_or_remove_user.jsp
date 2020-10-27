<%@page import="org.springframework.ui.ModelMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>体温の入力</title>
<!-- bootstrap cdn for css,js -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body class="container-fluid mt-3">
 


<!-- -------------------Nav bar ----------------->

	<nav style="background-color: #DCDCDC" class="m-3 h4 ">
		<div class="d-inline-block p-3 font-weight-bold">
			
			<a href="/kenon/admin/add_or_remove_user"><img
					alt="" src="<%=request.getContextPath() %>/icon/pen_icon.png" width="25" height="25"/><span class="d-none d-sm-inline">Kenon</span></a>
		</div>
		
<!-- -------------------Jsp for controlling  admin  ----------------->

	
		<div class="d-inline-block p-3">

			<div class="dropdown">
				<button style="background-color: transparent"
					class="btn  dropdown-toggle" id="dropdownMenu1"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					メニュー <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="/kenon/admin/user_information/">体調の入力</a></li>
					<li><a href="/kenon/admin/add_or_remove_user">ユーザの取り込み</a></li>
					<li><a href="/kenon/admin/user_list">一覧の出力</a></li>
					</ul>
			</div>
		</div>
	
		<div class="d-inline-block p-3 float-right">
			<a href="/kenon/logout">ログアウト</a>
		</div>
		<div class="d-inline-block p-3 float-right">
			<a href="/kenon/password_reset">パスワード変更</a>
		</div>

	</nav>
 
 
 
 <!-- -------------------------------------------- Input information form --------------------------------- -->
 
 

<form method="post" action="/kenon/admin/add_or_remove_user" class="pl-3 m-4" style="font-size: 13px" enctype="multipart/form-data">
		<div class="pt-3">
			<h4>ユーザの取り込む</h4>
		</div>
		<div class="form-group pt-3">
				<a href="/kenon/admin/download/userlist.xlsx" class="btn btn-primary" download="userlist.xlsx">登録用最新ファイル</a>
		</div>
	
	  <div class="form-group">
    <label for="importedFile">登録用最新ファイル</label>
    <input type="file" class="form-control-file" value="ファイルを選択" id="importedFile" name="importedFile">
  </div>
	<div class="form-group pt-3">
			<input type="text" class="form-control" 
				value="情報を変更する前に登録用最新ファイルをDLしてください" disabled style="background-color:#ffcccb"/>
				
				</div>
				<p style="color:red"> ${errorMessage} </p>
	
	<input type="submit" class="form-control btn btn-primary mt-3"  value="ユーザの取り込む"/>
	</form>




	<!-- jQuey first, then Popper.js, then Bootstrap JS -->
	
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