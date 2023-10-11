<%@page import="in.co.placement.selection.controller.JobCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.placement.selection.util.HTMLUtility"%>
<%@page import="in.co.placement.selection.util.ServletUtility"%>
<%@page import="in.co.placement.selection.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job</title>
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
				<h3>Job Edit</h3>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
<div class="container mtb">
		<div class="row">
			<div class="col-lg-8">
				<h1 align="center">***Edit Job***</h1>
				<div class="hline"></div>
				<p></p>
				<form class="contact-form php-mail-form" role="form"
					action="<%=PSView.JOB_CTL %>" method="POST" >
					<jsp:useBean id="bean"
						class="in.co.placement.selection.bean.JobBean" scope="request"></jsp:useBean>
						
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
                    <!-- <h3 style="margin-bottom: 15px; text-align: left: ;">Registration</h3> -->
                    <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
					<div class="mb-3">
						<label for="" class="form-label">JOB TITLE</label> <input type="text"
							name="name" class="form-control" id="" value="<%=DataUtility.getStringData(bean.getJobTitle())%>"
							aria-describedby="emailHelp">
							<font  color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
					</div><br>
					<div class="mb-3">
						<label for="" class="form-label">SELECT COMPANY</label> <input type="text"
							name="companyName" class="form-control" id="" value="<%=DataUtility.getStringData(bean.getCompanyName())%>"
							aria-describedby="emailHelp" readonly="readonly">
							<font  color="red"><%=ServletUtility.getErrorMessage("companyName", request)%></font>
					</div>
					
					
					<%HashMap jobTypeMap = new HashMap();
							jobTypeMap.put("Part Time", "Part Time")	;
							jobTypeMap.put("Full Time", "Full Time")	;
							
					%>
					<div class="form-group">
					<label for="exampleInputPassword1" class="form-label">JOB TYPE</label>
								<%=HTMLUtility.getList("jobType",String.valueOf(bean.getJobType()), jobTypeMap)%> 
									<font  color="red"><%=ServletUtility.getErrorMessage("jobType", request)%></font>
					</div>
					
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">JOB POSTED DATE</label>
						<input type="text" name="dop" class="form-control" value="<%=DataUtility.getDateString(bean.getJobPostDate()) %>"
							id="exampleInputPassword1" >
							<font   color="red"> <%=ServletUtility.getErrorMessage("dop", request)%></font>
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">SKILLS REQUIRED</label>
						<input type="text" name="skills" class="form-control" value="<%=DataUtility.getStringData(bean.getSkills()) %>"
							id="exampleInputPassword1">
							<font   color="red"> <%=ServletUtility.getErrorMessage("skills", request)%></font>
					</div>
					<%HashMap cityMap = new HashMap();
								cityMap.put("Pune", "Pune")	;
								cityMap.put("Mumbai", "Mumbai")	;
								cityMap.put("Indore", "Indore");
								cityMap.put("Banglore", "Banglore");
					%>
					<div class="form-group">
					<label for="exampleInputPassword1" class="form-label">CITY</label>
								<%=HTMLUtility.getList("city",String.valueOf(bean.getCity()), cityMap)%> 
									<font  color="red"><%=ServletUtility.getErrorMessage("city", request)%></font>
					</div>
					<div class="form-group">
								<label for="password" class="cols-sm-2 control-label"><strong>JOB DESCRIPTION</strong></label>
								<div class="cols-sm-10">
									<div class="input-group">

										<textarea type="text" class="form-control" name="description"
											cols="100" rows="3" ><%=DataUtility.getStringData(bean.getDescription()) %></textarea>
									</div>
									<font  color="red"><%=ServletUtility.getErrorMessage("description", request)%></font>
								</div>
							</div>
						
					<br>
					<button type="submit" name="operation"
						value="<%=JobCtl.OP_SAVE%>"
						class="btn btn-primary mb-2">Update Job</button>



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