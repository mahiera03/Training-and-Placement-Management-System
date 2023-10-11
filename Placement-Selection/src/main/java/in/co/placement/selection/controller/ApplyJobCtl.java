package in.co.placement.selection.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.placement.selection.bean.JobBean;
import in.co.placement.selection.exception.ApplicationException;
import in.co.placement.selection.model.JobModel;
import in.co.placement.selection.util.DataUtility;
import in.co.placement.selection.util.ServletUtility;

/**
 * Servlet implementation class ApplyJobCtl
 */
@WebServlet(name = "ApplyJobCtl", urlPatterns = { "/ctl/ApplyJobCtl" })
public class ApplyJobCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplyJobCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		System.out.println("hi inside company ctl");
		System.out.println("hello");
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("op is " + op);
		JobModel model = new JobModel();
		int id = DataUtility.getInt(request.getParameter("id"));
		System.out.println("id is " + id);
		if (id > 0 || op != null) {
			JobBean bean;
			try {
				bean = model.findByPK(id);

				ServletUtility.setBean(bean, request);
				

			} catch (ApplicationException e) {
				// log.error(e);

				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return PSView.Apply_VIEW;
	}

}
