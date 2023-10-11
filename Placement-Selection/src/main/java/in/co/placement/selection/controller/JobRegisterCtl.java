package in.co.placement.selection.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.placement.selection.bean.BaseBean;
import in.co.placement.selection.bean.CompanyBean;
import in.co.placement.selection.bean.JobBean;
import in.co.placement.selection.exception.ApplicationException;
import in.co.placement.selection.exception.DuplicateRecordException;
import in.co.placement.selection.model.CompanyModel;
import in.co.placement.selection.model.JobModel;
import in.co.placement.selection.util.DataUtility;
import in.co.placement.selection.util.DataValidator;
import in.co.placement.selection.util.PropertyReader;
import in.co.placement.selection.util.ServletUtility;

/**
 * Servlet implementation class JobRegisterCtl
 */
@WebServlet(name = "JobRegisterCtl",urlPatterns={"/JobRegisterCtl"})
public class JobRegisterCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SIGN_UP = "SignUp";
	private static Logger log = Logger.getLogger(JobRegisterCtl.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobRegisterCtl() {
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
    	/*if (DataValidator.isNull(request.getParameter("companyName"))) {
			request.setAttribute("companyName", PropertyReader.getValue("error.require", "Comapny Name"));
			pass = false;
		}*/ 
    	if (DataValidator.isNull(request.getParameter("dop"))) {
			request.setAttribute("dop", PropertyReader.getValue("error.require","Date of Posted"));
			pass = false;
		}else if(!DataValidator.isDate(request.getParameter("dop"))){
			request.setAttribute("dop", PropertyReader.getValue("error.invalid","Date of Posted"));
			pass=false;
		}
    	
    	if (DataValidator.isNull(request.getParameter("skills"))) {
			request.setAttribute("skills", PropertyReader.getValue("error.require", "Skills"));
			pass = false;
		} 
    	if ("-----Select-----".equalsIgnoreCase(request.getParameter("jobType"))) {
			request.setAttribute("jobType",
					PropertyReader.getValue("error.require", "Job Type"));
			pass = false;
		}
    	if ("-----Select-----".equalsIgnoreCase(request.getParameter("city"))) {
			request.setAttribute("city",
					PropertyReader.getValue("error.require", "City"));
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
    	JobBean bean=new JobBean();
    	bean.setId(Integer.parseInt(request.getParameter("id")));
    	bean.setJobTitle(DataUtility.getString(request.getParameter("name")));
    	bean.setCompanyName(DataUtility.getString(request.getParameter("companyName")));
    	bean.setJobType(DataUtility.getString(request.getParameter("jobType")));
    	bean.setJobPostDate(DataUtility.getDate(request.getParameter("dop")));
    	bean.setSkills(DataUtility.getString(request.getParameter("skills")));
    	bean.setCity(DataUtility.getString(request.getParameter("city")));
    	bean.setDescription(DataUtility.getString(request.getParameter("description")));
    	bean.setCompanyId(DataUtility.getInt(request.getParameter("companyName")));
    	
    	populateDTO(bean, request);
    	return bean;
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in post method of company");
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("op is "+op);
		JobModel model = new JobModel();
		int id=DataUtility.getInt(request.getParameter("id"));
		if(OP_SIGN_UP.equalsIgnoreCase(op)){
			//Populate bean
			JobBean bean=(JobBean)populateBean(request);
			try {
				System.out.println("before add");
				long pk=model.add(bean);
				System.out.println("after add"+pk);
				request.getSession().setAttribute("JobBean", bean);
				ServletUtility.setSuccessMessage("Job Added Successfully", request);
				ServletUtility.forward(getView(), request, response);
			} catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Job Already exist",
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
		return PSView.JOB_REGISTRATION_VIEW;
	}

}
