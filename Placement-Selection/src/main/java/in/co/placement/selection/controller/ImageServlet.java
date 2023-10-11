package in.co.placement.selection.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.placement.selection.util.JDBCDataSource;




/**
 * Servlet implementation class ImageServlet
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name="ImageServlet",urlPatterns={"/ctl/ImageServlet"})
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello inside image servlet");
		int id = Integer.parseInt(request.getParameter("id"));
		
		BufferedInputStream bin=null;
	    BufferedOutputStream bout=null;
	    InputStream in =null;
	    response.setContentType("image/jpeg");  
	    ServletOutputStream out;  
	    out = response.getOutputStream();  
	    System.out.println("get jsp page"+request.getRequestURI());
		try {
			
			Connection con=JDBCDataSource.getConnection();
			String sql="SELECT * FROM APPLICANT WHERE ID='"+id+"'";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
	           /* byte[] imgData = rs.getBytes("photo");//Here....... rs.getBytes() extract byte data from resultSet 
	            System.out.println(imgData);
	            response.setHeader("expires", "0");
	            response.setContentType("image/jpg");

	            OutputStream os = response.getOutputStream(); // output with the help of outputStream 
	            os.write(imgData);
	            os.flush();
	            os.close();*/
				  Blob blob = rs.getBlob("applicant_image");
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
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
