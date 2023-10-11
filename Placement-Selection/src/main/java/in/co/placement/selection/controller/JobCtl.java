package in.co.placement.selection.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.placement.selection.bean.BaseBean;
import in.co.placement.selection.bean.CompanyBean;
import in.co.placement.selection.bean.JobBean;
import in.co.placement.selection.exception.ApplicationException;
import in.co.placement.selection.model.CompanyModel;
import in.co.placement.selection.model.JobModel;
import in.co.placement.selection.util.DataUtility;
import in.co.placement.selection.util.ServletUtility;

/**
 * Servlet implementation class JobCtl
 */
@WebServlet(name="JobCtl",urlPatterns={"/ctl/JobCtl"})
public class JobCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobCtl() {
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
		JobModel model=new JobModel();
		int id=DataUtility.getInt(request.getParameter("id"));
		System.out.println("id is "+id);
		if(id>0 ||op!=null){
			JobBean bean;
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
		JobBean bean = new JobBean();
		bean.setId(Integer.parseInt(request.getParameter("id")));
    	bean.setJobTitle(DataUtility.getString(request.getParameter("name")));
    	bean.setCompanyName(DataUtility.getString(request.getParameter("companyName")));
    	bean.setJobType(DataUtility.getString(request.getParameter("jobType")));
    	bean.setJobPostDate(DataUtility.getDate(request.getParameter("dop")));
    	bean.setSkills(DataUtility.getString(request.getParameter("skills")));
    	bean.setCity(DataUtility.getString(request.getParameter("city")));
    	bean.setDescription(DataUtility.getString(request.getParameter("description")));
		return bean;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside post");
		String op=DataUtility.getString(request.getParameter("operation"));
		JobModel model=new JobModel();
		int id=DataUtility.getInt(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			JobBean bean=(JobBean)populateBean(request);
			try {
			if(id>0){
				
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Job Updated Successfully", request);
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
			JobBean bean=(JobBean) populateBean(request);
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
		return PSView.JOB_VIEW;
	}

}
