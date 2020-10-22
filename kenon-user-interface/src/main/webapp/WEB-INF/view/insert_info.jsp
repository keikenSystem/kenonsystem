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
 
 <!-- Flag for admin or user role -->
 
	<%
		String userRole = (String) request.getAttribute("role");
	%>


<!-- -------------------Nav bar ----------------->

	<nav style="background-color: #DCDCDC" class="m-3 h4 ">
		<div class="d-inline-block p-3 font-weight-bold">
			
			<a href="/kenon/user_information"><img
					alt="" src="icon/pen_icon.png" width="25" height="25"/><span class="d-none d-sm-inline">Kenon</span></a>
		</div>
		
<!-- -------------------Jsp for controlling  admin  ----------------->

		<%
			if (userRole.equals("admin")) {
		%>
		<div class="d-inline-block p-3">

			<div class="dropdown">
				<button style="background-color: transparent"
					class="btn  dropdown-toggle" id="dropdownMenu1"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					メニュー <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="/kenon/admin/user_information/">体調の入力</a></li>
					<li><a href="/kenon/admin/add_user">ユーザの取り込み</a></li>
					<li><a href="/kenon/admin/user_list">一覧の出力</a></li>
					</ul>
			</div>
		</div>
		<%
			}
		%>
		<div class="d-inline-block p-3 float-right">
			<a href="/kenon/logout">ログアウト</a>
		</div>
		<div class="d-inline-block p-3 float-right">
			<a href="/kenon/password_reset">パスワード変更</a>
		</div>

	</nav>
 
 
 
 <!-- -------------------------------------------- Input information form --------------------------------- -->
 
 

	<form method="post" class="pl-3 m-4" style="font-size: 13px">
		<div class="pt-3">
			<h4>体調の入力</h4>
		</div>
		<div class="form-group pt-3">
			<input type="text" class="form-control" name="lastUsedTime"
				value="${lastUsedDate}" disabled />
		</div>
		<div class="form-group pt-3">
			<label for="userId">社員番号</label> <input type="text"
				class="form-control" name="userId" id="userId" maxlength="6"
				value="${userId}" disabled />
		</div>
		<div class="form-group pt-3">
			<label for="userName">氏名</label> <input type="text"
				class="form-control" name="userName" id="userName" maxlength="50"
				value="${userName}" disabled />
		</div>
		<p>本日の体温を入力してください。</p>
		<p>なお、37.5℃以上ある場合は所属部門長へ連絡の上、欠席の手続きをしてください。</p>
		<input type="text" name="temperature" maxlength="4" id="temperature" /><label
			for="temperature">℃</label>
		<p>風邪症状（発熱または熱感や悪寒、咳痰などの上気道症状、咽疼痛、鼻汁や鼻閉、倦怠感、関節痛、下痢、腹痛、</p>
		<p>
			吐き気、嘔吐）の有無を入力してください。

			<p>風症状がある場合は 所属部門長へ連絡の上、欠席の手続きをしてください。</p>
		<select class="form-control-sm">
			<option>無い</option>
			<option>有</option>
		</select>

		<button type="submit" class="btn btn-primary form-control"
			style="margin-top: 25px">登録</button>


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