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
<title>Applicant View</title>
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
</head>
<body>
<%@include file="Header.jsp" %>
<div id="blue">
		<div class="container">
			<div class="row">
				<h3>View Applicant Info</h3>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<div class="container mtb">
		<div class="row">
			<div class="col-lg-8">
				<h1 align="center">***Applicant Info***</h1>
				<div class="hline"></div>
				<p></p>
				
					<jsp:useBean id="bean"
						class="in.co.placement.selection.bean.ApplicantBean" scope="request"></jsp:useBean>
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
						<label for="" class="form-label">APPLICANT NAME</label> <input type="text"
							name="name" class="form-control" id="" value="<%=DataUtility.getStringData(bean.getApplicantName())%>"
							aria-describedby="emailHelp">
							<font  color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
					</div>
					<div class="mb-3">
						<label for="" class="form-label">EMAIL</label> <input type="text"
							name="email" class="form-control" id="" value="<%=DataUtility.getStringData(bean.getApplicantEmail())%>"
							aria-describedby="emailHelp">
							<font  color="red"><%=ServletUtility.getErrorMessage("email", request)%></font>
					</div>
					<div class="mb-3">
						<label for="" class="form-label">CONTACT NUMBER</label> <input type="text"
							name="mobile" class="form-control" id="" value="<%=DataUtility.getStringData(bean.getApplicantMobile())%>"
							aria-describedby="emailHelp">
							<font  color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font>
					</div>
					
					<%HashMap map = new HashMap();
					map.put("Pune", "Pune")	;
					map.put("Mumbai", "Mumbai")	;
					map.put("Indore", "Indore");
					map.put("Banglore", "Banglore");
					%>
					<div class="form-group">
					<label for="exampleInputPassword1" class="form-label">CITY</label>
								<%=HTMLUtility.getList("city",String.valueOf(bean.getApplicantCity()), map)%> 
									<font  color="red"><%=ServletUtility.getErrorMessage("city", request)%></font>
					</div>
					<%HashMap stateMap = new HashMap();
					stateMap.put("Maharashtra", "Maharashtra")	;
					stateMap.put("Madhya Pradesh", "Madhya Pradesh")	;
					stateMap.put("Karnataka", "Karnataka");
					%>
					<div class="form-group">
					<label for="exampleInputPassword1" class="form-label">STATE</label>
								<%=HTMLUtility.getList("state",String.valueOf(bean.getApplicantState()), stateMap)%> 
									<font  color="red"><%=ServletUtility.getErrorMessage("state", request)%></font>
					</div>
					<%HashMap countryMap = new HashMap();
					countryMap.put("India", "India")	;
					countryMap.put("Japan", "Japan")	;
					countryMap.put("Korea", "Korea");
					%>
					<div class="form-group">
					<label for="exampleInputPassword1" class="form-label">COUNTRY</label>
								<%=HTMLUtility.getList("country",String.valueOf(bean.getApplicantCountry()), countryMap)%> 
									<font  color="red"><%=ServletUtility.getErrorMessage("country", request)%></font>
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">ADDRESS</label>
						<input type="text" name="address" class="form-control" value="<%=DataUtility.getStringData(bean.getApplicantAddress()) %>"
							id="exampleInputPassword1">
							<font   color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font>
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">DATE OF BIRTH</label>
						<input type="text" name="dob" class="form-control" value="<%=DataUtility.getStringData(bean.getApplicantDob()) %>"
							id="exampleInputPassword1">
							<font   color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font>
					</div>
					
					<div class="form-group">
								<label for="password" class="cols-sm-2 control-label"><strong>Qualification</strong></label>
								<div class="cols-sm-10">
									<div class="input-group">
					
										<textarea type="text" class="form-control" name="qualification"
											cols="100" rows="3" ><%=DataUtility.getStringData(bean.getApplicantQualification()) %></textarea>
									</div>
								</div>
							</div>
					<div class="form-group">
    <label for="exampleFormControlFile1">Profile Pic</label>
   	<br>
    <img src="ImageServlet?id=<%=bean.getId()%>" alt="Avatar" width="200px" height="128" />
  </div>		
										
					<br>
					



				
			</div>


		</div>
	</div>
	<br>
	<br>
	<br>	
<%@include file="Footer.jsp" %>
</body>
</html>