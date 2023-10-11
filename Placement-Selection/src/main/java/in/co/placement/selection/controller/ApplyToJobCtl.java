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

import in.co.placement.selection.bean.ApplicantBean;
import in.co.placement.selection.bean.BaseBean;
import in.co.placement.selection.bean.CompanyBean;
import in.co.placement.selection.bean.UserBean;
import in.co.placement.selection.exception.ApplicationException;
import in.co.placement.selection.exception.DuplicateRecordException;
import in.co.placement.selection.model.ApplicantModel;
import in.co.placement.selection.model.CompanyModel;
import in.co.placement.selection.model.UserModel;
import in.co.placement.selection.util.DataUtility;
import in.co.placement.selection.util.DataValidator;
import in.co.placement.selection.util.PropertyReader;
import in.co.placement.selection.util.ServletUtility;

/**
 * Servlet implementation class ApplyToJobCtl
 */
@WebServlet(name="ApplyToJobCtl",urlPatterns={"/ctl/ApplyToJobCtl"})

@MultipartConfig(maxFileSize = 16177215)
public class ApplyToJobCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SIGN_UP = "SignUp";
	private static Logger log = Logger.getLogger(ApplyToJobCtl.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyToJobCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected boolean validate(HttpServletRequest request) {
    	// TODO Auto-generated method stub
    	boolean pass = true;
    	/*if (DataValidator.isNull(request.getParameter("name"))) {
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
		}*/
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
    	if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require","Date of Birth"));
			pass = false;
		}else if(!DataValidator.isDate(request.getParameter("dob"))){
			request.setAttribute("dob", PropertyReader.getValue("error.invalid","Date of Birth"));
			pass=false;
		}
    	if (DataValidator.isNull(request.getParameter("qualification"))) {
			request.setAttribute("qualification", PropertyReader.getValue("error.require", "Qualification"));
			pass = false;
		} 
    	System.out.println("pass is"+pass);
    	return pass;
    }
    
    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
    	// TODO Auto-generated method stub
    	ApplicantBean bean = new ApplicantBean();
    	bean.setId(Integer.parseInt(request.getParameter("id")));
    	bean.setApplicantName(DataUtility.getString(request.getParameter("name")));
    	System.out.println("name "+request.getParameter("name"));
    	System.out.println("email is"+request.getParameter("email"));
    	System.out.println("mobile is "+request.getParameter("mobile"));
    	bean.setApplicantEmail(DataUtility.getString(request.getParameter("email")));
    	bean.setApplicantMobile(DataUtility.getString(request.getParameter("mobile")));
    	
    	bean.setApplicantCity(DataUtility.getString(request.getParameter("city")));
    	bean.setApplicantState(DataUtility.getString(request.getParameter("state")));
    	bean.setApplicantCountry(DataUtility.getString(request.getParameter("country")));
    	bean.setApplicantAddress(DataUtility.getString(request.getParameter("address")));
    	bean.setApplicantDob(DataUtility.getDate(request.getParameter("dob")));
    	bean.setApplicantQualification(DataUtility.getString(request.getParameter("qualification")));
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
		     
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setApplicantImage(blob);
    	
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
	//	session.setAttribute("id", id);
		String op=DataUtility.getString(request.getParameter("operation"));
		//get Model
		
		UserModel model=new UserModel();
		
		
		if(id>0||op !=null){
			
			UserBean bean;
			try{
				bean=model.findByPK(id);
				String Fname=bean.getFirstName();
				String Lname=bean.getLastName();
				String login=bean.getLogin();
				String mobile=bean.getMobileNo();
				System.out.println("Fame is "+Fname);
				request.setAttribute("Fname",Fname);
				request.setAttribute("Lname",Lname);
				request.setAttribute("login",login);
				request.setAttribute("Umobile",mobile);
				//ServletUtility.setBean(bean, request);
				
			}catch(ApplicationException e){
				
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in post method of company");
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("op is "+op);
		ApplicantModel model = new ApplicantModel();
		//HttpSession session = request.getSession(false);
		//ApplicantBean b=(ApplicantBean)session.getAttribute("id");
		//long b_id=b.getId();
		int id=DataUtility.getInt(request.getParameter("id"));
		//long id =  b_id;
		if(OP_SAVE.equalsIgnoreCase(op)){
			//Populate bean
			ApplicantBean bean=(ApplicantBean)populateBean(request);
			try {
				System.out.println("before add");
				long pk=model.add(bean);
				System.out.println("after add"+pk);
				request.getSession().setAttribute("ApplicantBean", bean);
				request.getSession().setAttribute("id", id);
				ServletUtility.setSuccessMessage("Thank you for applying to this Job", request);
				ServletUtility.forward(getView(), request, response);
			} catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Applicant Already exist",
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
		return PSView.Apply_REGISTER_VIEW;
	}

}
