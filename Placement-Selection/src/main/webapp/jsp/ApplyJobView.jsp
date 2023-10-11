<%@page import="in.co.placement.selection.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.placement.selection.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View</title>
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
<link href="../css/tablestyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@include file="Header.jsp" %>
<div id="blue">
		<div class="container">
			<div class="row">
				<h3>View Full Details</h3>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
<jsp:useBean id="bean" class="in.co.placement.selection.bean.JobBean" scope="request"></jsp:useBean>

<div class="container mtb">
		<div class="row">
			<div class="row">

				<div class="col-sm-3"></div>
				<div class="col-sm-4">	

<table class="styled-table">
<tr><td align="center"><img src="../jsp/getImage.jsp?id=<%=bean.getId()%>" alt="Not Found" height="150px" width="220px"></td></tr>
<tr><td>Job Title</td><td><%=DataUtility.getString(bean.getJobTitle()) %></td>
</tr>
<tr><td>Company Name</td><td><%=DataUtility.getString(bean.getCompanyName()) %></td>
</tr>
<tr><td>Job Type</td><td><%=DataUtility.getString(bean.getJobType()) %></td></tr>
<tr><td>Job Post Date</td><td><%=DataUtility.getDateString(bean.getJobPostDate()) %></td></tr>
<tr><td>Skills</td><td><%=DataUtility.getString(bean.getSkills()) %></td></tr>
<tr><td>City</td><td><%=DataUtility.getString(bean.getCity()) %></td></tr>
<tr><td>Description</td><td><%=DataUtility.getString(bean.getDescription()) %></td></tr>
<tr><td></td><td><a href="ApplyToJobCtl?id=<%=bean.getId() %>" class="btn btn-success">Apply for this Job</a></td></tr>
</table>
 <br>
</div></div></div></div>	
	
<%@include file="Footer.jsp" %>
</body>
</html>