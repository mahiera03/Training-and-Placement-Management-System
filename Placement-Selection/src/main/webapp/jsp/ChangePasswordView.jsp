<%@page import="in.co.placement.selection.controller.ChangePasswordCtl"%>
<%@page import="in.co.placement.selection.util.DataUtility"%>
<%@page import="in.co.placement.selection.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change password</title>
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
				<h3>Change Password</h3>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
<!-- Login form starts here -->
 <div class="container mtb">
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2 centered">
        <h2>Change Password</h2>
        <br>
        <div class="hline"></div>
        <form action="<%=PSView.CHANGE_PASSWORD_CTL%>" method="post">
        <jsp:useBean id="bean" class="in.co.placement.selection.bean.UserBean" scope="request"></jsp:useBean>
        <% String uri=(String)request.getAttribute("uri");%>
		
              <input type="hidden" name="uri" value="<%=uri%>">
            
             			 
             	<input type="hidden" name="id" value="<%=bean.getId()%>">
             	<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
  <div class="form-group">
    <label for="formGroupExampleInput">Old Password</label>
    <input type="password" class="form-control" name="oldPassword" id="formGroupExampleInput" placeholder="Enter Old Password" value="<%=DataUtility.getString(request.getParameter("old") == null ? ""
                            : DataUtility.getString(request.getParameter("oldPassword")))%>" required>
 	<font  color="red"><%=ServletUtility.getErrorMessage("oldPassword", request)%></font>
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">New Password</label>
    <input type="password" class="form-control" name="newPassword" id="formGroupExampleInput2" placeholder="Enter New Password" value="<%=DataUtility.getString(request.getParameter("new") == null ? ""
                            : DataUtility.getString(request.getParameter("newPassword")))%>" required>
  	<font  color="red"><%=ServletUtility.getErrorMessage("newPassword", request)%></font>
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">Confirm Password</label>
    <input type="password" class="form-control" name="confirmPassword" id="formGroupExampleInput2" placeholder="Enter Confirm Password" value="<%=DataUtility.getString(request.getParameter("new") == null ? ""
                            : DataUtility.getString(request.getParameter("confirmPassword")))%>" required>
  	<font  color="red"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
  </div>
  <button type="submit" name="operation" value="<%=ChangePasswordCtl.OP_SAVE%>" class="btn btn-primary mb-2">Change My Password</button>
  
</form>
      </div>
    </div>
  </div>
 
 
<div></div><br><br>		
<%@include file="Footer.jsp" %>
</body>
</html>