package in.co.placement.selection.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import in.co.placement.selection.bean.BaseBean;
import in.co.placement.selection.bean.CompanyBean;
import in.co.placement.selection.exception.ApplicationException;
import in.co.placement.selection.model.CompanyModel;
import in.co.placement.selection.util.DataUtility;
import in.co.placement.selection.util.DataValidator;
import in.co.placement.selection.util.PropertyReader;
import in.co.placement.selection.util.ServletUtility;

/**
 * Servlet implementation class CompanyCtl
 */

@WebServlet(name="CompanyCtl",urlPatterns = {"/ctl/CompanyCtl"})
@MultipartConfig(maxFileSize = 16177215)
public class CompanyCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hi inside company ctl");
		System.out.println("hello");
		String op=DataUtility.getString(request.getParameter("operation"));
		System.out.println("op is "+op);
		CompanyModel model=new CompanyModel();
		int id=DataUtility.getInt(request.getParameter("id"));
		System.out.println("id is "+id);
		if(id>0 ||op!=null){
			CompanyBean bean;
            try {
                bean = model.findByPK(id);
             
                ServletUtility.setBean(bean, request);
            
            } catch (ApplicationException e) {
               // log.error(e);
            
                ServletUtility.handleException(e, request, response);
                return;
            }
        

        ServletUtility.forward(getView(), request, response);
		}
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		// TODO Auto-generated method stub
		CompanyBean bean = new CompanyBean();
		bean.setId(Integer.parseInt(request.getParameter("id")));
    	bean.setName(DataUtility.getString(request.getParameter("name")));
    	bean.setEmail(DataUtility.getString(request.getParameter("email")));
    	bean.setContactNo(DataUtility.getString(request.getParameter("mobile")));
    	bean.setWebSite(DataUtility.getString(request.getParameter("website")));
    	bean.setCity(DataUtility.getString(request.getParameter("city")));
    	bean.setState(DataUtility.getString(request.getParameter("state")));
    	bean.setCountry(DataUtility.getString(request.getParameter("country")));
    	bean.setAddress(DataUtility.getString(request.getParameter("address")));
    	bean.setDescription(DataUtility.getString(request.getParameter("description")));
    	InputStream inputStream=null;  //input stream of the uploaded file...
		Part filepart;
		Blob blob=null;
		try {
			filepart = request.getPart("photo");
			if(filepart!=null)
			{
				System.out.println(filepart.getName());
	            System.out.println(filepart.getSize());
	            System.out.println(filepart.getContentType());
	            inputStream =filepart.getInputStream();
	            byte[] b=new byte[inputStream.available()];
	            blob = new SerialBlob(b);
			}
		} catch ( ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	//
    	/*InputStream inputStream=null; 
		Blob blob=null;
		try {
			 //input stream of the uploaded file...
			Part filepart=request.getPart("photo");
			inputStream =filepart.getInputStream();
			byte[] b=new byte[inputStream.available()];
			inputStream.read(b);
			
		         
		         try {
					blob = new SerialBlob(b);
				} catch (SerialException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		bean.setLogo(blob);
		populateDTO(bean, request);
		return bean;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside post");
		String op=DataUtility.getString(request.getParameter("operation"));
		CompanyModel model=new CompanyModel();
		int id=DataUtility.getInt(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			CompanyBean bean=(CompanyBean)populateBean(request);
			try {
			if(id>0){
				
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Company Updated Successfully", request);
					ServletUtility.setBean(bean, request);
					
			}
			else {
				long pk=model.add(bean);
				ServletUtility.setSuccessMessage("Successfully Registered", request);
				ServletUtility.setBean(bean, request);
			}
			
			
			} 
			
			catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ServletUtility.forward(PSView.ERROR_CTL, request, response);
				}
			}
		else if(OP_DELETE.equalsIgnoreCase(op)){
			CompanyBean bean=(CompanyBean) populateBean(request);
			try {
				model.delete(bean);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//ServletUtility.redirect(RTOView.USER_LIST_CTL, request, response);
			
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return PSView.COMPANY_VIEW;
	}

}
