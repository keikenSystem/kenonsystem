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

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style>
.swal-text {
  
  display: block;
  margin: 22px;
  text-align: left;
  color: #61534e;
}
.swal-button{
color:white;
background-color:#343a40;
}

.dropdown-menu > li > a:hover {
    background-image: none;
    background-color: #0275d8;
}

</style>
</head>
<body class="container-fluid mt-3">

	<!-- Flag for admin or user role -->

	<%
		String userRole = (String) request.getAttribute("role");
	    String symtom =  (String)session.getAttribute("symtom");
			
			if(symtom==null){
				symtom="3";
			}
			
	%>
	
<!-- Handle  registration sucessfull -->

	<%
		if (session.getAttribute("checkAlert")!=null){
	%>
	<script>
		swal({
			  text: "登録しました",
			  button: "閉じる",
			})
	</script>
	<%
		}
	%>
	<%
		
		session.removeAttribute("checkAlert");
	      
	%>
	
	
	
	<!-- -------------------Nav bar ----------------->

	<nav style="background-color: #DCDCDC" class="m-3 h4 ">
		<div class="d-inline-block p-3 font-weight-bold">

			<a href="/kenon/<%=request.getAttribute("role")%>/user_information"><img
				alt="" src="<%=request.getContextPath()%>/icon/pen_icon.png"
				width="25" height="25" /><span class="d-none d-sm-inline">Kenon</span></a>
		</div>

		<!-- -------------------Jsp for controlling  admin  ----------------->

		<%
			if (userRole.equals("admin")) {
		%>
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
		<%
			}
		%>
		<div class="d-inline-block p-3 float-right">
			<a href="/kenon/logout" class="btn">ログアウト</a>
		</div>
		<div class="d-inline-block p-3 float-right">
			<a href="/kenon/password_reset" class="btn">パスワード変更</a>
		</div>

	</nav>



	<!-- -------------------------------------------- Input information form --------------------------------- -->



	<form action="/kenon/inputdata/" class="pl-3 m-4"
		style="font-size: 13px">
		<div class="pt-3">
			<h4>体調の入力</h4>
		</div>
		<p style="color:green">${errorMessage}</p>
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

		<input type="number" name="temperature" maxlength="4" id="temperature"
			step=0.01 value="${temperature}" required /><label for="temperature">℃</label>
		<p>風邪症状（発熱または熱感や悪寒、咳痰などの上気道症状、咽疼痛、鼻汁や鼻閉、倦怠感、関節痛、下痢、腹痛、</p>
		<p>吐き気、嘔吐）の有無を入力してください。
		<p>風症状がある場合は 所属部門長へ連絡の上、欠席の手続きをしてください。</p>
		
		<% if(symtom.equals("1")){
				%>
		<select class="form-control-sm" name="gotSymtom">
			<option value="0">無い</option>
			<option value="1" selected>有</option>
		</select>
		  
		<%
		session.removeAttribute("symtom");
		}
		
		%>
		
		
		<%
		if(symtom=="3"||symtom.equals("0")) {%>
			<select class="form-control-sm" name="gotSymtom">
			<option value="0">無い</option>
			<option value="1">有</option>
		</select>
		<%}session.removeAttribute("symtom");
		%>

		<button type="submit" class="btn btn-primary form-control"
			style="margin-top: 25px">登録</button>


	</form>


	<div
		style="text-align: center; margin-left: 40%; margin-right: 40%; margin-bottom: 10%">
		<div style="border: 1px solid; padding: 3px">
			<p style="text-align: left">一連絡先</p>
			<p style="text-align: left">代表：TEL 026-228-6644</p>
		</div>

	</div>


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