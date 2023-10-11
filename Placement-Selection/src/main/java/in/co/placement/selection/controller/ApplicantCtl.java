package in.co.placement.selection.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.placement.selection.bean.ApplicantBean;
import in.co.placement.selection.bean.CompanyBean;
import in.co.placement.selection.exception.ApplicationException;
import in.co.placement.selection.model.ApplicantModel;
import in.co.placement.selection.model.CompanyModel;
import in.co.placement.selection.util.DataUtility;
import in.co.placement.selection.util.ServletUtility;

/**
 * Servlet implementation class ApplicantCtl
 */
@WebServlet(name="ApplicantCtl",urlPatterns={"/ctl/ApplicantCtl"})
@MultipartConfig(maxFileSize = 16177215)
public class ApplicantCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicantCtl() {
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
		ApplicantModel model=new ApplicantModel();
		int id=DataUtility.getInt(request.getParameter("id"));
		System.out.println("id is "+id);
		if(id>0 ||op!=null){
			ApplicantBean bean;
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return PSView.APPLICATON_VIEW;
	}

}
