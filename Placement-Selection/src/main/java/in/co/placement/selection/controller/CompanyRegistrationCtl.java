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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.log4j.Logger;

import in.co.placement.selection.bean.BaseBean;
import in.co.placement.selection.bean.CompanyBean;
import in.co.placement.selection.bean.UserBean;
import in.co.placement.selection.exception.ApplicationException;
import in.co.placement.selection.exception.DuplicateRecordException;
import in.co.placement.selection.model.CompanyModel;
import in.co.placement.selection.model.UserModel;
import in.co.placement.selection.util.DataUtility;
import in.co.placement.selection.util.DataValidator;
import in.co.placement.selection.util.PropertyReader;
import in.co.placement.selection.util.ServletUtility;

/**
 * Servlet implementation class CompanyCtl
 */
@WebServlet(name = "CompanyRegistrationCtl", urlPatterns = { "/CompanyRegistrationCtl" })
@MultipartConfig(maxFileSize = 16177215)
public class CompanyRegistrationCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SIGN_UP = "SignUp";
	private static Logger log = Logger.getLogger(CompanyRegistrationCtl.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyRegistrationCtl() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected boolean validate(HttpServletRequest request) {
    	// TODO Auto-generated method stub
    	boolean pass = true;
    	if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.name", "Name"));
			pass = false;
		}
    	if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "email"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.name", "email"));
			pass = false;
		}
    	if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require","Mobile No"));
			pass = false;
		}else if(!DataValidator.isPhoneNo(request.getParameter("mobile"))){
			request.setAttribute("mobile", PropertyReader.getValue("error.invalid","Mobile No"));
			pass=false;
		}
    	if (DataValidator.isNull(request.getParameter("website"))) {
			request.setAttribute("website", PropertyReader.getValue("error.require", "Webiste"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("website"))) {
			request.setAttribute("website", PropertyReader.getValue("error.name", "Website"));
			pass = false;
		}
    	if ("-----Select-----".equalsIgnoreCase(request.getParameter("city"))) {
			request.setAttribute("city",
					PropertyReader.getValue("error.require", "City"));
			pass = false;
		}
    	if ("-----Select-----".equalsIgnoreCase(request.getParameter("state"))) {
			request.setAttribute("state",
					PropertyReader.getValue("error.require", "State"));
			pass = false;
		}
    	if ("-----Select-----".equalsIgnoreCase(request.getParameter("country"))) {
			request.setAttribute("country",
					PropertyReader.getValue("error.require", "Country"));
			pass = false;
		}
    	if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		} 
    	if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		} 
    	System.out.println("pass is"+pass);
    	return pass;
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
    	InputStream inputStream=null; 
		Blob blob=null;
		try {
			 //input stream of the uploaded file...
			Part filepart=request.getPart("photo");
			inputStream =filepart.getInputStream();
			byte[] b=new byte[inputStream.available()];
			inputStream.read(b);
			
		         
		         try {
					blob = new SerialBlob(b );
				} catch (SerialException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setLogo(blob);
    	
    	populateDTO(bean, request);
    	return bean;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		
		UserBean userBean=(UserBean) session.getAttribute("user");
		
		long id=userBean.getId();
		System.out.println("ID is "+id);
		
		String op=DataUtility.getString(request.getParameter("operation"));
		//get Model
		
		UserModel model=new UserModel();
		
		
		if(id>0||op !=null){
			
			UserBean bean;
			try{
				bean=model.findByPK(id);
				String name=bean.getFirstName();
				request.setAttribute("Aname",name);
				//ServletUtility.setBean(bean, request);
				
			}catch(ApplicationException e){
				
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in post method of company");
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("op is "+op);
		CompanyModel model = new CompanyModel();
		int id=DataUtility.getInt(request.getParameter("id"));
		if(OP_SIGN_UP.equalsIgnoreCase(op)){
			//Populate bean
			CompanyBean bean=(CompanyBean)populateBean(request);
			try {
				System.out.println("before add");
				long pk=model.add(bean);
				System.out.println("after add"+pk);
				request.getSession().setAttribute("CompanyBean", bean);
				ServletUtility.setSuccessMessage("Company Added Successfully", request);
				ServletUtility.forward(getView(), request, response);
			} catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Company Already exist",
						request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
				return;
			}
		}
		
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return PSView.COMPANY_REGISTRATION_VIEW;
	}

}
