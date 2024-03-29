<%@page import="org.springframework.ui.ModelMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧の出力</title>
<!-- bootstrap cdn for css,js -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<style>
.dropdown-menu > li > a:hover {
    background-image: none;
    background-color: #0275d8;
}

</style>
</head>
<body class="container-fluid mt-3">



	<!-- -------------------Nav bar ----------------->

	<nav style="background-color: #DCDCDC" class="m-3 h4 ">
		<div class="d-inline-block p-3 font-weight-bold">

			<a href="/kenon/admin/user_information"><img alt=""
				src="<%=request.getContextPath()%>/icon/pen_icon.png" width="25"
				height="25" /><span class="d-none d-sm-inline">Kenon</span></a>
		</div>

		<!-- -------------------Jsp for controlling  admin  ----------------->


		<div class="d-inline-block p-3">

	<div class="dropdown">
				<button style="background-color: transparent"
					class="btn  dropdown-toggle " 
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" data-hover="dropdown">
					メニュー <span class="caret"></span>
				</button>
				<ul class="dropdown-menu bg-style">
					<li><a class="dropdown-item" href="/kenon/admin/user_information/">体調の入力</a></li>
					<li><a class="dropdown-item" href="/kenon/admin/add_or_remove_user">ユーザの取り込み</a></li>
					<li><a class="dropdown-item" href="/kenon/admin/user_list">一覧の出力</a></li>
				</ul>
			</div>
		</div>

		<div class="d-inline-block p-3 float-right">
			<a href="/kenon/logout" class="btn">ログアウト</a>
		</div>
		<div class="d-inline-block p-3 float-right">
			<a href="/kenon/password_reset" class="btn">パスワード変更</a>
		</div>

	</nav>



	<!-- -------------------------------------------- Input information form --------------------------------- -->



	<form method="post"  class="pl-3 m-4"
		style="font-size: 13px">
		<div class="pt-3">
			<h4>一覧の出力</h4>
		</div>
		<label>出力期間 （選択日を 含む過去5日間）</label> <input type="date" min="${Min}"
			max="${Max}" value=${today} name="selectedDate"
			class="form-control" /> 
			
		<label>出力対象</label> 
		
		<select name="department" class="form-control">
		<%int i=0;
		%>
		<c:forEach items="${departments}" var="dept">
			   <option>${dept}</option>
			   <%
			    i=i+1;
			   %>
		</c:forEach>
		
		</select>
		
			<button type="submit" class="btn btn-primary form-control"
			style="margin-top: 25px">出力</button>
		
		
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