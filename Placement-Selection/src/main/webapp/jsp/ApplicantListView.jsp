<%@page import="in.co.placement.selection.model.ApplicantModel"%>
<%@page import="in.co.placement.selection.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.placement.selection.controller.ApplicationListCtl"%>
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
<link href="../lib/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- <link href="../lib/prettyphoto/css/prettyphoto.css" rel="stylesheet"> -->
<link href="../lib/hover/hoverex-all.css" rel="stylesheet">

<!-- Main Stylesheet File -->
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link href="../css/tablestyle.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Applicant Report</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<div id="blue">
		<div class="container">
			<div class="row">
				<h3>Applicant Report</h3>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- Serach filed -->
	<div class="container mtb">
		<div class="row">
			<div class="row">

				<div class="col-sm-3"></div>
				<div class="col-sm-11">
					<form action="<%=PSView.APPLICATION_LIST_CTL%>" method="post">
						<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b><b><font
							color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b> <br>
						<!-- <div class="hline"></div> -->
						<div class="form-group col-lg-4">
							<input type="text" class="form-control" name="name"
								placeholder="Search by Name"
								value="<%=ServletUtility.getParameter("name", request)%>">
						</div>
						<div class="form-group col-lg-4">
							<input type="text" class="form-control" name="qualification"
								placeholder="Search by Qualification"
								value="<%=ServletUtility.getParameter("qualification", request)%>">
						</div>
						<div class="form-group col-lg-4">
							<input type="submit" name="operation" class="btn btn-success"
								value="<%=ApplicationListCtl.OP_SEARCH%>"> <input
								type="submit" name="operation" class="btn btn-success.focus"
								value="<%=ApplicationListCtl.OP_RESET%>">
						</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container mtb">
		<div class="row">
			<div class="row">

				<div class="col-sm-3"></div>
				<div class="col-sm-11">
					<jsp:useBean id="bean"
						class="in.co.placement.selection.bean.ApplicantBean"
						scope="request"></jsp:useBean>
					<table class="styled-table">
						<thead>
							<tr>
								<th>Select</th>
								<th>#</th>
								<th>Image</th>
								<th>Name</th>
								<th>Email</th>
								<th>Contact No.</th>
								<th>City</th>
								<th>State</th>
								<th>Country</th>
								<th>Address</th>
								<th>DOB</th>
								<th>Qualification</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<%
								int pageNo = ServletUtility.getPageNo(request);
								int pageSize = ServletUtility.getPageSize(request);
								int index = ((pageNo - 1) * pageSize) + 1;
								List list = ServletUtility.getList(request);
								Iterator<ApplicantBean> itr = list.iterator();
								while (itr.hasNext()) {
									bean = itr.next();
							%>
							<tr>
								<td><input type="checkbox" class="case" name="ids"
									value="<%=bean.getId()%>"></td>
								<td><%=index++%></td>
								<td><img src="ImageServlet?id=<%=bean.getId()%>" name="profilepic"
									alt="Not Found" height="120px" width="50px"></td>
								<td><%=DataUtility.getString(bean.getApplicantName())%></td>
								<td><%=DataUtility.getString(bean.getApplicantEmail())%></td>
								<td><%=DataUtility.getString(bean.getApplicantMobile())%></td>

								<td><%=DataUtility.getString(bean.getApplicantCity())%></td>
								<td><%=DataUtility.getString(bean.getApplicantState())%></td>
								<td><%=DataUtility.getString(bean.getApplicantCountry())%></td>
								<td><%=DataUtility.getString(bean.getApplicantAddress())%></td>
								<td><%=DataUtility.getDateString(bean.getApplicantDob())%></td>
								<td><%=DataUtility.getString(bean.getApplicantQualification())%></td>

								<td><a href="ApplicantCtl?id=<%=bean.getId()%>"
									class="btn btn-primary">View</a></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<div class="col-sm-7"></div>
					<div class="col-sm-11">
						<table class="table ">
							<tr>

								<td><input type="submit" name="operation"
									class="btn btn-primary pull-right"
									value="<%=ApplicationListCtl.OP_PREVIOUS%>"
									<%=(pageNo == 1) ? "disabled" : ""%>></td>

								<td><input type="submit" name="operation"
									class="btn btn-danger pull-right"
									value="<%=ApplicationListCtl.OP_DELETE%>"
									<%=(list.size() == 0) ? "disabled" : ""%>></td>
								<%
									ApplicantModel model = new ApplicantModel();
								%>
								<td align="right"><input type="submit" name="operation"
									class="btn btn-primary pull-right"
									value="<%=ApplicationListCtl.OP_NEXT%>"
									<%=((list.size() < pageSize)) ? "disabled" : ""%>></td>
							</tr>
						</table>
					</div>
					<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
						type="hidden" name="pageSize" value="<%=pageSize%>">
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>