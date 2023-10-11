<!DOCTYPE html>
<%@page import="in.co.placement.selection.controller.*"%>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Placement-Selection</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicons -->
   <link href="../img/favicon.png" rel="icon">
  <link href="../img/apple-touch-icon.png" rel="apple-touch-icon"> 

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Raleway:400,700,900|Lato:400,900" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="../lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="../lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <!-- <link href="../lib/prettyphoto/css/prettyphoto.css" rel="stylesheet"> -->
  <link href="../lib/hover/hoverex-all.css" rel="stylesheet"> 

  <!-- Main Stylesheet File -->
  <link href="../css/style.css" rel="stylesheet" type="text/css"/>
 
	
 
</head>

<body>
<% %>
  <!-- Fixed navbar -->
  <div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        <a class="navbar-brand" href="<%=PSView.WELCOME_CTL%>">Fabtech Technical Campus Training and Placement Department</a>
      </div>
      <div class="navbar-collapse collapse navbar-right">
        <ul class="nav navbar-nav">
          <li class="active"><a href="<%=PSView.WELCOME_CTL%>">HOME</a></li>
          <li><a href="/Placement-Selection/<%=PSView.ABOUT_US_VIEW%>">ABOUT US</a></li>
          <li><a
						href="/Placement-Selection/<%=PSView.CONTACT_US_VIEW%>">CONTACT</a></li>
          <li><a href="<%=PSView.LOGIN_CTL%>">LOGIN</a></li>
        </ul>
      </div>
      <!--/.nav-collapse -->
    </div>
  </div>