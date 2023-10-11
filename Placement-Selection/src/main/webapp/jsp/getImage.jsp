<%@page import="java.io.InputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="in.co.placement.selection.util.JDBCDataSource"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.sql.Blob"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%


int id = Integer.parseInt(request.getParameter("id"));

/* BufferedInputStream bin=null;
BufferedOutputStream bout=null;
InputStream in =null; */
response.setContentType("image/jpeg");  
/* ServletOutputStream out1;  
out1 = response.getOutputStream(); */

try {
	
	Connection con=JDBCDataSource.getConnection();
	String sql="SELECT * FROM COMPANY WHERE ID='"+id+"'";
	PreparedStatement ps=con.prepareStatement(sql);
	ResultSet rs=ps.executeQuery();
	if (rs.next()) {
      
		  Blob blob = rs.getBlob("logo");
	        byte byteArray[] = blob.getBytes(1, (int)blob.length());
	 
	        response.setContentType("image/jpg");
	        OutputStream os = response.getOutputStream();
	        os.write(byteArray);
	        os.flush();
	        os.close();

    }
	
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();

	 response.getOutputStream().flush();
	 response.getOutputStream().close();
}

%>
</body>
</html>