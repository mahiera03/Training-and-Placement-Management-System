<%@page import="in.co.placement.selection.controller.LoginCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="in.co.placement.selection.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Login</title>
<meta charset="utf-8">

<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

</head>
<body>
	<%@ include file="Header.jsp"%>
	<div id="blue">
		<div class="container">
			<div class="row">
				<h3>LOGIN PAGE</h3>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>

	<div class="container mtb">
		<div class="row">
			<div class="col-lg-8">
				<h1 align="center">LOGIN PAGE</h1>
				<div class="hline"></div>
				<p align="center"><b><font color="Green" > <%=ServletUtility.getSuccessMessage(request)%>
                </font></b></p>
				<form class="contact-form php-mail-form" role="form"
					action="<%=PSView.LOGIN_CTL %>" method="POST">
<jsp:useBean id="bean" class="in.co.placement.selection.bean.UserBean"
         					   scope="request"></jsp:useBean>
         			   <% String uri=(String)request.getAttribute("uri");%>
		
             			 <input type="hidden" name="uri" value="<%=uri%>">
             			 
             	<input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

					<div class="mb-3">
						<label for="" class="form-label">LOGINID(EMAIL-ID)</label> <input type="text" name="login" class="form-control"
							id="" aria-describedby="emailHelp">
							<b><font color="red"> <%=ServletUtility.getErrorMessage("login",request)%>
                </font></b><b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                						
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">Password</label>
						<input type="password" name="password" class="form-control"
							id="exampleInputPassword1">
							<b><font color="red"> <%=ServletUtility.getErrorMessage("password",request)%>
                </font></b><b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
					</div>
						<br>							
					<button type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_IN%>" class="btn btn-primary mb-2">Login</button>
  <a href="<%=PSView.USER_REGISTRATION_CTL %>" class="btn btn-primary mb-2" >Not yet Registered?</a>
					

				</form>
			</div>


		</div>
	</div><br>
	<br><br>
	<%@ include file="Footer.jsp"%>
	
</body>
</html>