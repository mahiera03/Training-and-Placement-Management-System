<%@page
	import="in.co.placement.selection.controller.UserRegistrationCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="in.co.placement.selection.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<div id="blue">
		<div class="container">
			<div class="row">
				<h3>STUDENT REGISTRATION PAGE</h3>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<div class="container mtb">
		<div class="row">
			<div class="col-lg-8">
				<h1 align="center">Register to Placement Portal</h1>
				<div class="hline"></div>
				<p></p>
				<form class="contact-form php-mail-form" role="form"
					action="<%=PSView.USER_REGISTRATION_CTL %>" method="POST" enctype="multipart/form-data">
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
                    <h3 style="margin-bottom: 15px; text-align: left: ;">Registration</h3>
                    <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
					<div class="mb-3">
						<label for="" class="form-label">FIRSTNAME</label> <input type="text"
							name="firstName" class="form-control" id="" value="<%=DataUtility.getStringData(bean.getFirstName())%>"
							aria-describedby="emailHelp">
							<font  color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font>
					</div>
					<div class="mb-3">
						<label for="" class="form-label">LASTNAME</label> <input type="text"
							name="lastName" class="form-control" id="" value="<%=DataUtility.getStringData(bean.getLastName())%>"
							aria-describedby="emailHelp">
							<font  color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font>
					</div>
					<div class="mb-3">
						<label for="" class="form-label">EMAIL-ID</label> <input type="email"
							name="login" class="form-control" id="" value="<%=DataUtility.getStringData(bean.getLogin())%>"
							aria-describedby="emailHelp">
							<font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">PASSWORD</label>
						<input type="password" name="password" class="form-control" value="<%=DataUtility.getStringData(bean.getPassword()) %>"
							id="exampleInputPassword1">
							<font   color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">CONFIRM PASSWORD</label>
						<input type="password" name="confirmPassword" class="form-control" value="<%=DataUtility.getStringData(bean.getConfirmPassword()) %>"
							id="exampleInputPassword1">
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
						value="<%=UserRegistrationCtl.OP_SIGN_UP%>"
						class="btn btn-primary mb-2">REGISTER</button>



				</form>
			</div>


		</div>
	</div>
	<br>
	<br>
	<br>
	<%@include file="Footer.jsp"%>
</body>
</html>