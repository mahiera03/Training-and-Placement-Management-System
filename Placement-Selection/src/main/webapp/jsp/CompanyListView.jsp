<%@page import="in.co.placement.selection.model.CompanyModel"%>
<%@page import="in.co.placement.selection.controller.CompanyListCtl"%>
<%@page import="in.co.placement.selection.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="in.co.placement.selection.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List</title>
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
				<h3>List of Companies</h3>
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
				<form action="<%=PSView.COMPANY_LIST_CTL%>" method="post">
				<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b><b><font
						color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font></b> <br>
					<!-- <div class="hline"></div> -->
					<div class="form-group col-lg-4">
						<input type="text" class="form-control" name="companyName"
							placeholder="Search by Company Name"
							value="<%=ServletUtility.getParameter("companyName", request)%>">
					</div>
					<div class="form-group col-lg-4">
						<input type="text" class="form-control" name="city"
							placeholder="Search by City"
							value="<%=ServletUtility.getParameter("city", request)%>">
					</div>
					<div class="form-group col-lg-4">
						<input type="submit" name="operation"
							class="btn btn-success"
							value="<%=CompanyListCtl.OP_SEARCH%>">
							 <input type="submit"
							name="operation" class="btn btn-success.focus"
							value="<%=CompanyListCtl.OP_RESET%>">
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
  <jsp:useBean id="bean" class="in.co.placement.selection.bean.CompanyBean" scope="request"></jsp:useBean>
  <table class="styled-table">
    <thead>
        <tr>
            <th>Select</th>
            <th>#</th>
            <th>Company Logo</th>
            <th>Name</th>
            <th>Email</th>
            <th>Contact No.</th>
            <th>Website</th>
            <th>City</th>
            <th>State</th>
            <th>Country</th>
            <th>Address</th>
            <th>Description</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
    <%
    int pageNo = ServletUtility.getPageNo(request);
    int pageSize = ServletUtility.getPageSize(request);
    int index = ((pageNo - 1) * pageSize) + 1;
    List list=ServletUtility.getList(request);
    Iterator<CompanyBean> itr=list.iterator();
    while(itr.hasNext()){
    bean = itr.next();
    %>
        <tr>
            <td><input type="checkbox" class="case" name="ids"
									value="<%=bean.getId()%>"></td>
            <td><%=index++ %></td>
            <td><img src="../jsp/getImage.jsp?id=<%=bean.getId()%>" alt="Not Found"></td>
            <td><%=DataUtility.getString(bean.getName())%></td>
            <td><%=DataUtility.getString(bean.getEmail()) %></td>
            <td><%=DataUtility.getString(bean.getContactNo()) %></td>
            <td><%=DataUtility.getString(bean.getWebSite()) %></td>
            <td><%=DataUtility.getString(bean.getCity()) %></td>
            <td><%=DataUtility.getString(bean.getState()) %></td>
            <td><%=DataUtility.getString(bean.getCountry()) %></td>
            <td><%=DataUtility.getString(bean.getAddress()) %></td>
            <td><%=DataUtility.getString(bean.getDescription()) %></td>
            
            <td><a href="CompanyCtl?id=<%=bean.getId()%>" class="btn btn-primary">Edit</a></td>
        </tr>
    <%} %>    
    </tbody>
</table>
<div class="col-sm-7"></div>
				<div class="col-sm-11">
					<table class="table ">
				<tr>

					<td><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=CompanyListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
					
					<td><input type="submit" name="operation" class="btn btn-danger pull-right" 
						value="<%=CompanyListCtl.OP_DELETE%>"
						<%=(list.size() == 0) ? "disabled" : ""%>></td>
					<%
						CompanyModel model = new CompanyModel();
					%>
					 <td align="right"><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=CompanyListCtl.OP_NEXT%>" 
						<%=((list.size() < pageSize))  ? "disabled" : ""%>></td>
				</tr>
			</table></div>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>			
  </div>
   </div></div>
</div>	 
<%@include file="Footer.jsp" %>
</body>
</html>