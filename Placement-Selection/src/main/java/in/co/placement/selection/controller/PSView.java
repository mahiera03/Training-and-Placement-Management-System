package in.co.placement.selection.controller;

public interface PSView {
	
	public String APP_CONTEXT = "/Placement-Selection";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	
	
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	public String JOB_REGISTRATION_VIEW = PAGE_FOLDER + "/JobRegistrationView.jsp";
	public String COMPANY_REGISTRATION_VIEW = PAGE_FOLDER + "/CompanyRegister.jsp";
	public String ABOUT_US_VIEW = PAGE_FOLDER + "/OurAboutUsView.jsp";
	public String CONTACT_US_VIEW= PAGE_FOLDER+ "/OurContactUsView.jsp";
	
	public String Apply_VIEW = PAGE_FOLDER + "/ApplyJobView.jsp";
	public String Apply_LIST_VIEW = PAGE_FOLDER + "/ApplicationListView.jsp";
	public String Apply_REGISTER_VIEW = PAGE_FOLDER + "/ApplyRegister.jsp";
	public String JOB_VIEW = PAGE_FOLDER + "/JobView.jsp";	
	public String JOB_LIST_VIEW = PAGE_FOLDER + "/JobListView.jsp";
	
	public String APPLICATON_VIEW = PAGE_FOLDER + "/ApplicantView.jsp";
	public String APPLICATON_LIST_VIEW = PAGE_FOLDER + "/ApplicantListView.jsp";
	public String COMPANY_VIEW = PAGE_FOLDER + "/CompanyView.jsp";	
	public String COMPANY_LIST_VIEW = PAGE_FOLDER + "/CompanyListView.jsp";
	
	public String SEND_MAIL_VIEW = PAGE_FOLDER + "/SendMailView.jsp";	
		
	public String MAKE_RESUME_VIEW = PAGE_FOLDER + "/MakeResumeView.jsp";
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	public String JOB_DETAIL_VIEW = PAGE_FOLDER + "/JobDetailView.jsp";	

	public String ERROR_CTL = "/ctl/ErrorCtl";

	
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	
	public String COMPANY_CTL = APP_CONTEXT + "/ctl/CompanyCtl";
	public String COMPANY_LIST_CTL = APP_CONTEXT + "/ctl/CompanyListCtl";
	
	
	public String APPLICATION_CTL = APP_CONTEXT + "/ctl/ApplicantCtl";
	public String APPLICATION_LIST_CTL = APP_CONTEXT + "/ctl/ApplicationListCtl";
	public String JOB_CTL = APP_CONTEXT + "/ctl/JobCtl";
	public String JOB_LIST_CTL = APP_CONTEXT + "/ctl/JobListCtl";
	public String JOB_LIST_VIEW_CTL = APP_CONTEXT + "/ctl/JobListViewtCtl";
	public String Apply_CTL = APP_CONTEXT + "/ctl/ApplayJobCtl";
	public String APPLY_LIST_CTL = APP_CONTEXT + "/ctl/ApplayJobListCtl";
	public String APPLY_REGISTER_CTL = APP_CONTEXT + "/ctl/ApplyToJobCtl";
	public String SEND_MAIL_CTL = APP_CONTEXT + "/ctl/SendMailCtl";
	
	
	public String MAKE_RESUME_CTL = APP_CONTEXT + "/ctl/MakeResumeCtl";
	
	public String COMPANY_REGISTRATION_CTL = APP_CONTEXT + "/CompanyRegistrationCtl";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String JOB_REGISTRATION_CTL = APP_CONTEXT + "/JobRegisterCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";



}
