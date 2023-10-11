<%@page import="in.co.placement.selection.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.placement.selection.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="Header.jsp" %>
<jsp:useBean id="bean" class="in.co.placement.selection.bean.JobBean" scope="request"></jsp:useBean>
<div id="blue">
		<div class="container">
			<div class="row">
				<h3>VIEW JOB</h3>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
<div class="container mtb">
		<div class="row">
			<div class="row">

				<div class="col-sm-3"></div>
				<div class="col-sm-4">	
 <%
    int pageNo = ServletUtility.getPageNo(request);
    int pageSize = ServletUtility.getPageSize(request);
    int index = ((pageNo - 1) * pageSize) + 1;
    List list=ServletUtility.getList(request);
    Iterator<JobBean> itr=list.iterator();
    while(itr.hasNext()){
    bean = itr.next();
    %>
<table class="styled-table">

<tr><td>Name</td><td><%=DataUtility.getString(bean.getCompanyName()) %></td>
</tr>
<tr><td>Job Type</td><td><%=DataUtility.getString(bean.getJobType()) %></td></tr>
<tr><td>Job Post Date</td><td><%=DataUtility.getDateString(bean.getJobPostDate()) %></td></tr>
<tr><td>Skills</td><td><%=DataUtility.getString(bean.getSkills()) %></td></tr>
<tr><td><a href="ApplyJobCtl?id=<%=bean.getId() %>" class="btn btn-success">VIEW DETAILS</a></td><td></td></tr>
</table>
 <br>
<%} %></div></div></div></div><br><br><br><br><br><br><br><br><br><br><br><br>
<div style="top: 100%;">
<%@include file="Footer.jsp" %></div>
</body>
</html>