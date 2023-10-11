<%@page import="in.co.placement.selection.controller.MyProfileCtl"%>
<%@page import="in.co.placement.selection.util.DataUtility"%>
<%@page import="in.co.placement.selection.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
<!-- Favicons -->
<link href="img/favicon.png" rel="icon">
<link href="img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Raleway:400,700,900|Lato:400,900"
	rel="stylesheet">

<!-- Bootstrap CSS File -->
<link href="../lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Libraries CSS Files -->
<link href="../lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- <link href="../lib/prettyphoto/css/prettyphoto.css" rel="stylesheet"> -->
<link href="../lib/hover/hoverex-all.css" rel="stylesheet">

<!-- Main Stylesheet File -->
<link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@include file="Header.jsp" %>
<div id="blue">
		<div class="container">
			<div class="row">
				<h3>My Profile</h3>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
<div class="container mtb">
		<div class="row">
			<div class="col-lg-8">
				<h1 align="center">Update Profile</h1>
				<div class="hline"></div>
				<p></p>
				<form class="contact-form php-mail-form" role="form"
					action="<%=PSView.MY_PROFILE_CTL %>" method="POST" >
					<jsp:useBean id="bean"
						class="in.co.placement.selection.bean.UserBean" scope="request"></jsp:useBean>
					<% String uri=(String)request.getAttribute("uri");%>

					<input type="hidden" name="uri" value="<%=uri%>"> <input
						type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedBy()%>"> <input type="hidden"
						name="createdDatetime"
						value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
					<input type="hidden" name="modifiedDatetime"
						value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
						 <br style="clear:both">
                    
                    <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
					<div class="mb-3">
						<label for="" class="form-label">FIRSTNAME</label> <input type="text"
							name="firstName" class="form-control" id="" value="<%=DataUtility.getStringData(bean.getFirstName())%>"
							aria-describedby="emailHelp" >
							<font  color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font>
					</div>
					<div class="mb-3">
						<label for="" class="form-label">LASTNAME</label> <input type="text"
							name="lastName" class="form-control" id="" value="<%=DataUtility.getStringData(bean.getLastName())%>"
							aria-describedby="emailHelp" >
							<font  color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font>
					</div>
					<div class="mb-3">
						<label for="" class="form-label">EMAIL-ID</label> <input type="text"
							name="login" class="form-control" id="" value="<%=DataUtility.getStringData(bean.getLogin())%>"
							aria-describedby="emailHelp">
							<font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">PASSWORD</label>
						<input type="password" name="password" class="form-control" value="<%=DataUtility.getStringData(bean.getPassword()) %>"
							id="exampleInputPassword1" >
							<font   color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">CONFIRM PASSWORD</label>
						<input type="password" name="confirmPassword" class="form-control" value="<%=DataUtility.getStringData(bean.getConfirmPassword()) %>"
							id="exampleInputPassword1" >
						<font   color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>	
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">MOBILE NO.</label>
						<input type="text" name="mobile" class="form-control" value="<%=DataUtility.getStringData(bean.getMobileNo())%>"
							id="exampleInputPassword1">
							<font  color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font>
					</div>
					<br>
					<button type="submit" name="operation"
						value="<%=MyProfileCtl.OP_SAVE%>"
						class="btn btn-primary mb-2">Update Profile</button>
						<button type="submit" name="operation"
						value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>"
						class="btn btn-primary.disabled.focus mb-2">Change Password</button>



				</form>
			</div>


		</div>
	</div>
	<br>
	<br>
	<br>	
<%@include file="Footer.jsp" %>
</body>
</html>