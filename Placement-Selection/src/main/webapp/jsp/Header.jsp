<!DOCTYPE html>
<%@page import="in.co.placement.selection.controller.LoginCtl"%>
<%@page import="in.co.placement.selection.controller.PSView"%>
<%@page import="in.co.placement.selection.bean.*" %>
<html lang="en">
<head>

 <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>
<meta charset="utf-8">
<title>Training and Placement Portal</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicons -->
<link href="img/favicon.png" rel="icon">
<link href="img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Raleway:400,700,900|Lato:400,900"
	rel="stylesheet">

<!-- Bootstrap CSS File -->
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Libraries CSS Files -->
<link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- <link href="../lib/prettyphoto/css/prettyphoto.css" rel="stylesheet"> -->
<link href="lib/hover/hoverex-all.css" rel="stylesheet">

<!-- Main Stylesheet File -->
<link href="css/style.css" rel="stylesheet" type="text/css" />
 
</head>

<body>
	<%
UserBean userbean=(UserBean)session.getAttribute("user");

boolean userWhoIsLogin=userbean!=null;

String welcomeMsg="WELCOME : ";
if(userWhoIsLogin){
	String role=(String)session.getAttribute("roleid");
	welcomeMsg+=userbean.getLogin();
	if(userbean.getRoleId()==1){
		welcomeMsg+="";}
		else if(userbean.getRoleId()==2){
			welcomeMsg+="";	
		}
	}
else{
	welcomeMsg+="Guest";
}

%>
<% %>
	<!-- Fixed navbar -->
	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				
				<a class="navbar-brand" href="<%=PSView.WELCOME_CTL%>">Fabtech Technical Campus Training and Placement Department</a>
			</div>
			<div class="navbar-collapse collapse navbar-right">
				<ul class="nav navbar-nav">


					<li><a class="navbar-brand" href="<%=PSView.WELCOME_CTL%>">HOME</a></li>

					
					<%if(userWhoIsLogin){ %>
					<%--  <li><a href="<%=RTOView.WELCOME_CTL%>">HOME</a></li> --%>
					

					<%if(userbean.getRoleId()==1) {%>
					 
					<li><a class="navbar-brand" href="<%=PSView.COMPANY_REGISTRATION_CTL%>">ADD COMPANY</a></li> 
					<li><a class="navbar-brand" href="<%=PSView.COMPANY_LIST_CTL%>">COMPANY REPORT</a></li> 
					<li><a class="navbar-brand" href="<%=PSView.JOB_REGISTRATION_CTL%>">ADD JOB</a></li>
					<li><a class="navbar-brand" href="<%=PSView.JOB_LIST_CTL%>">JOB REPORT</a></li> 
					<li><a class="navbar-brand" href="<%=PSView.APPLICATION_LIST_CTL%>">APPLICATION REPORT</a></li> 
					<li><a href="<%=PSView.MY_PROFILE_CTL%>">MY PROFILE</a></li>
					<li><a href="<%=PSView.CHANGE_PASSWORD_CTL%>">CHANGE PASSWORD</a></li>
							
							<li><a
								href="<%=PSView.LOGOUT_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">LOG OUT</a></li>
					

					<%}else if(userbean.getRoleId()==3){ %>
					<li><a class="navbar-brand" href="<%=PSView.JOB_LIST_VIEW_CTL%>">VIEW JOBS</a></li>
					
					
					<li><a href="<%=PSView.MY_PROFILE_CTL%>">MY PROFILE</a></li>
					<li><a href="<%=PSView.CHANGE_PASSWORD_CTL%>">CHANGE PASSWORD</a></li>
							
							<li><a
								href="<%=PSView.LOGOUT_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">LOG OUT</a></li>
					
					<%}}else{ %>
					
					<li><a
						href="/Placement-Selection/<%=PSView.ABOUT_US_VIEW%>">ABOUT
							US</a></li>
					<li><a
						href="/Placement-Selection/<%=PSView.CONTACT_US_VIEW%>">CONTACT</a></li>
					<li><a href="<%=PSView.LOGIN_CTL%>">LOGIN</a></li>
					<li><a href="<%=PSView.USER_REGISTRATION_CTL %>">REGISTER</a></li>
					 <%} %> 
					<%-- <%if(userWhoIsLogin){ %>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><%=welcomeMsg %><b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="blog.html">My Profile</a></li>
							<li><a href="single-post.html">Change Password</a></li>
							<li><a
								href="<%=RTOView.User_Login_Ctl%>?operation=<%=LoginCtl.OP_LOG_OUT%>">LOG OUT</a></li>
						</ul></li>
					<%} %> --%> 
					<%if(userWhoIsLogin){ %>
					<li><a class=""><%=welcomeMsg %></a></li>
					<%-- <li class=""><a href="#" class=""
						data-toggle="dropdown"><%=welcomeMsg %><b class="caret"></b></a> --%>
						<%-- <ul class="dropdown-menu">
							<li><a href="blog.html">My Profile</a></li>
							<li><a href="single-post.html">Change Password</a></li>
							<li><a
								href="<%=RTOView.User_Login_Ctl%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Log
									out</a></li>
						</ul></li> --%>
					<%} %>
					
				</ul>


			</div>

			<!--/.nav-collapse -->
		</div>

	</div>